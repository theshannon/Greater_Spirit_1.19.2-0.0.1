package net.mrmelchior.greaterspirits.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaTypeC2SPacket {

    public ManaTypeC2SPacket() {

    }
    public ManaTypeC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE ON SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            /*player.getCapability(PlayerManaTypeProvider.PLAYER_MANA_TYPE).ifPresent(manaType -> {
                player.sendSystemMessage(Component.literal("Function opened ")
                        .withStyle(ChatFormatting.AQUA));
                manaType.randManaType();
                player.sendSystemMessage(Component.literal("Type " + manaType.getManaType())
                        .withStyle(ChatFormatting.AQUA));
            });*/
        });
        return true;
    }
}
