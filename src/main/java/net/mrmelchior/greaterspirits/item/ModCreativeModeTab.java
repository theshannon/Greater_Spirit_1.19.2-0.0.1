package net.mrmelchior.greaterspirits.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab("TutorialTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BLOOD_DROP.get());
        }
    };
}
