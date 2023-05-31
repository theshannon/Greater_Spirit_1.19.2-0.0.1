package net.mrmelchior.greaterspirits.manatype;

import net.minecraft.nbt.CompoundTag;

public class PlayerManaType {

    private int manatype;
    private final String  NO_MANA = "key.greaterspirits.manatype.no_mana" ;
    private final String  HALF_BLOOD = "key.greaterspirits.manatype.half_blood";
    private final String  EARTH = "key.greaterspirits.manatype.earth";
    private final String  FIRE = "key.greaterspirits.manatype.fire";
    private final String  WATER = "key.greaterspirits.manatype.water";
    private final String  AIR = "key.greaterspirits.manatype.air";
    private final String  LIGHT = "key.greaterspirits.manatype.light";
    private final String  DARK = "key.greaterspirits.manatype.dark";

    //hashtable
    /*private static Map<Integer, String> manatypemap;

    static {
        manatypemap = new HashMap<Integer, String>();
        manatypemap.put(0, "no_mana");
        manatypemap.put(1, "half_blood");
        manatypemap.put(2, "earth");
        manatypemap.put(3, "fire");
        manatypemap.put(4, "water");
        manatypemap.put(5, "air");
        manatypemap.put(6, "light");
        manatypemap.put(7, "dark");
    }*/

    //int manatype
    public int  getManaType() {
        return manatype;
    }

    //public translatiable string
    public  String getTransString(){
        switch (manatype) {
            case 0:
                return NO_MANA;
            case 1:
                return HALF_BLOOD;
            case 2:
                return EARTH;
            case 3:
                return FIRE;
            case 4:
                return WATER;
            case 5:
                return AIR;
            case 6:
                return LIGHT;
            case 7:
                return DARK;
        }
        return "Invalid";
    }
    public void resetManaType() {
        manatype = 0;
    }

    public void ChangeManaType(int mana_type) {
        manatype = mana_type;
    }
    public boolean canUseManaType(PlayerManaType type){
        if(type.getManaType() == 0)
        {
            return false;
        } else if (type.getManaType() > 0 || type.getManaType() < 8 ){
            return true;
        }
        type.resetManaType();
        return false;
    }

    public void randManaType(){
        double d = Math.random()*8;
        manatype = Math.round((long)d);
    }
    public void copyFrom(PlayerManaType source) {
        this.manatype = source.manatype;
    }
    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("manatype", manatype);
    }
    public void loadNBTData(CompoundTag nbt) {
        manatype = nbt.getInt("manatype");
    }
}