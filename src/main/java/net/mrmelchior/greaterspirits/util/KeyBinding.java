package net.mrmelchior.greaterspirits.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_GREATER_SPIRITS = "key.category.greaterspirits.greaterspirits";
    public static final String KEY_DRINK_WATER = "key.greaterspirits.drink_water";//remove drink
    public static final String KEY_USE_MANA = "key.greaterspirits.use_mana";

    //drinking remove
    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_DRINK_WATER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_GREATER_SPIRITS);
    public static final KeyMapping USE_MANA_KEY = new KeyMapping(KEY_USE_MANA, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_CAPS_LOCK, KEY_CATEGORY_GREATER_SPIRITS);


}
