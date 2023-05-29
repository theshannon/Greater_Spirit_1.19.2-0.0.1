package net.mrmelchior.greaterspirits.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.mrmelchior.greaterspirits.GreaterSpirits;
import net.mrmelchior.greaterspirits.mana.PlayerMana;
import net.mrmelchior.greaterspirits.mana.PlayerManaProvider;
import net.mrmelchior.greaterspirits.networking.ModMessages;
import net.mrmelchior.greaterspirits.networking.packet.ManaDataSyncS2CPacket;
import net.mrmelchior.greaterspirits.networking.packet.ThirstDataSyncS2CPacket;
import net.mrmelchior.greaterspirits.thirst.PlayerThirst;
import net.mrmelchior.greaterspirits.thirst.PlayerThirstProvider;

@Mod.EventBusSubscriber(modid = GreaterSpirits.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        //add first mana/thirst if lacking
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(GreaterSpirits.MOD_ID, "properties"), new PlayerThirstProvider());
            }
            if(!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(GreaterSpirits.MOD_ID, "manaproperties"), new PlayerManaProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        //Retain Thirst after death
        if(event.isWasDeath()) { //|| event.PlayerRespawnEvent.isEndConquered() i want to add
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                    event.getOriginal().invalidateCaps();
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        //registering the class
        event.register(PlayerThirst.class);
        event.register(PlayerMana.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        //+

        if(event.side == LogicalSide.SERVER) {
            //thirst reduction random tick effect
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.001f) { // Once Every 50 Seconds on Avg
                    thirst.subThirst(1);
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), ((ServerPlayer) event.player));
                } else if (!(thirst.getThirst() >= 0 && thirst.getThirst() <= 10)) {

                }
            });
            event.player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                //event.player.sendSystemMessage(Component.literal("Mana Tick"));
                if(mana.getMana() < 10 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                    mana.addMana(1);
                    /*event.player.sendSystemMessage(Component.literal("Added Mana"));
                    event.player.sendSystemMessage(Component.literal("Current Mana " + mana.getMana())
                            .withStyle(ChatFormatting.AQUA));*/
                    ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), ((ServerPlayer) event.player));
                } else if (!(mana.getMana() >= 0 && mana.getMana() <= 10)) {
                    mana.resetMana();
                    event.player.sendSystemMessage(Component.literal("Reset Mana"));
                }
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                });
            }
        }
    }
}