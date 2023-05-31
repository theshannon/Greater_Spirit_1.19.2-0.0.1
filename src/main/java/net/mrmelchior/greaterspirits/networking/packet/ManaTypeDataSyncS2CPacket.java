package net.mrmelchior.greaterspirits.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.mrmelchior.greaterspirits.client.ClientManaData;

import java.util.function.Supplier;

public class ManaTypeDataSyncS2CPacket {
    private final int manaType;

    public ManaTypeDataSyncS2CPacket(int manatype) {
        this.manaType = manatype;
    }

    public ManaTypeDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.manaType = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(manaType);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientManaData.set(manaType);
        });
        return true;
    }
}