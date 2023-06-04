package net.mrmelchior.greaterspirits.spellscatalogue;

import net.minecraft.world.entity.player.Player;
import net.mrmelchior.greaterspirits.manatype.PlayerManaTypeProvider;

public class Spells {
    public int spellid;
    public void spells(Player player){
        player.getCapability(PlayerManaTypeProvider.PLAYER_MANA_TYPE).ifPresent(manaType -> {

        });
    }
}
