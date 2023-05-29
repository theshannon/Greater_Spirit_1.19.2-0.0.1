package net.mrmelchior.greaterspirits.firemagic;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class FireSpells {

    //fireball small
    public static void fireball_1(ServerLevel level, Player player){
        String playerdir = player.getDirection().getName();
        Vec3 playeryaw = player.getLookAngle();
        double x,y,z;
        x = playeryaw.x;
        y = playeryaw.y;
        z = playeryaw.z;
        BlockPos blockpos = player.blockPosition();

        if (y > 0 ){
            blockpos = blockpos.above(1 + (int)(3 * y));
            blockpos = blockpos.south((int)(4 * z));
            blockpos = blockpos.east((int)(4 * x));
        } else {
            blockpos = blockpos.above(1);
            blockpos = blockpos.south((int)(4 * z));
            blockpos = blockpos.east((int)(4 * x));

        }

        //direction attempt with switch
        /*switch (playerdir){
            case "north":
            blockpos = blockpos.north(2);
            break;
            case "south":
            blockpos = blockpos.south(2);
            break;
            case "east":
            blockpos = blockpos.east(2);
            break;
            case "west":
            blockpos = blockpos.west(2);
            break;
        }*/
        //player.sendSystemMessage(Component.literal(x + playerdir + z ));
        EntityType.FIREBALL.spawn(level, null,null, blockpos,
                MobSpawnType.COMMAND, true, false);
    }
}
