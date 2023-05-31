package net.mrmelchior.greaterspirits.magic;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.mrmelchior.greaterspirits.manatype.PlayerManaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerMagicProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerManaType> PLAYER_MAGIC = CapabilityManager.get(new CapabilityToken<PlayerManaType>() { });

    private PlayerManaType magic = null;
    private final LazyOptional<PlayerManaType> optional = LazyOptional.of(this::createPlayerManaType);

    private PlayerManaType createPlayerManaType() {
        if(this.magic == null) {
            this.magic = new PlayerManaType();
        }
        return this.magic;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MAGIC) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerManaType().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerManaType().loadNBTData(nbt);
    }
}
