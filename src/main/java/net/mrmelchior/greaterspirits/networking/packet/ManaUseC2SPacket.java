package net.mrmelchior.greaterspirits.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mrmelchior.greaterspirits.firemagic.spells.FireBall_1;
import net.mrmelchior.greaterspirits.mana.PlayerManaProvider;
import net.mrmelchior.greaterspirits.networking.ModMessages;

import java.util.function.Supplier;

public class ManaUseC2SPacket {
    private static final String MESSAGE_MANA_USE = "message.greaterspirits.use.mana";
    private static final String MESSAGE_NO_MANA = "message.greaterspirits.no.mana";

    public ManaUseC2SPacket() {

    }
    public ManaUseC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE ON SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            /*player.sendSystemMessage(Component.literal("Function opened ")
                    .withStyle(ChatFormatting.AQUA));*/
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                mana.subMana(1);
                FireBall_1.fireball_1(level,player);
                /*player.sendSystemMessage(Component.literal("Current Mana " + mana.getMana())
                        .withStyle(ChatFormatting.AQUA));*/
                //sync up mana property to hud element
                ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);

            });
        });
        return true;
    }
}
