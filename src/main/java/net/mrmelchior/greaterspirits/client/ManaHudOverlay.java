package net.mrmelchior.greaterspirits.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.mrmelchior.greaterspirits.GreaterSpirits;

public class ManaHudOverlay {
    private static final ResourceLocation FILLED_MANA = new ResourceLocation(GreaterSpirits.MOD_ID,
            "textures/mana/filled_mana.png");
    private static final ResourceLocation EMPTY_MANA = new ResourceLocation(GreaterSpirits.MOD_ID,
            "textures/mana/empty_mana.png");


    public static final IGuiOverlay HUD_MANA = (((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F, 1.0F,1.0F);
        RenderSystem.setShaderTexture(0,EMPTY_MANA);
        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack,x + 12 + (i * 8), y - 50,0,0,6,6,
                    6,6);
            RenderSystem.setShaderTexture(0, EMPTY_MANA);
        }
        for(int i = 0; i < 10; i++) {
            if(ClientManaData.getPlayerMana() > i) {
                if(i == 0) {
                    GuiComponent.blit(poseStack,x + 12 + (i * 8),y - 50,0,0,6,6,
                            6,6);
                    RenderSystem.setShaderTexture(0, FILLED_MANA);
                }
                GuiComponent.blit(poseStack,x + 12 + (i * 8),y - 50,0,0,6,6,
                        6,6);
                RenderSystem.setShaderTexture(0, FILLED_MANA);
            } else {
                break;
            }
        }

    }));
}
