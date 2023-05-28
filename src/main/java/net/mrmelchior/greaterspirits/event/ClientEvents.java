package net.mrmelchior.greaterspirits.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrmelchior.greaterspirits.GreaterSpirits;
import net.mrmelchior.greaterspirits.client.ThirstHudOverlay;
import net.mrmelchior.greaterspirits.networking.ModMessages;
import net.mrmelchior.greaterspirits.networking.packet.DrinkWaterC2SPacket;
import net.mrmelchior.greaterspirits.networking.packet.ManaUseC2SPacket;
import net.mrmelchior.greaterspirits.util.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = GreaterSpirits.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new DrinkWaterC2SPacket());
            } else if (KeyBinding.USE_MANA_KEY.consumeClick()) {
                ModMessages.sendToServer(new ManaUseC2SPacket());
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed Mana Key!"));
            }
        }
    }

    @Mod.EventBusSubscriber(modid = GreaterSpirits.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
            event.register(KeyBinding.USE_MANA_KEY);
        }
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }
    }
}