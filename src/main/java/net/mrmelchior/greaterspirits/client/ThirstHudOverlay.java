package net.mrmelchior.greaterspirits.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.mrmelchior.greaterspirits.GreaterSpirits;

public class ThirstHudOverlay {
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(GreaterSpirits.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(GreaterSpirits.MOD_ID,
            "textures/thirst/empty_thirst.png");
    public static final IGuiOverlay HUD_THIRST = (((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F, 1.0F,1.0F);
        RenderSystem.setShaderTexture(0,EMPTY_THIRST);
        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack,x - 94 + (i * 9), y - 54,0,0,12,12,
                    12,12);
            RenderSystem.setShaderTexture(0, EMPTY_THIRST);
        }
        for(int i = 0; i < 10; i++) {
            if(ClientThirstData.getPlayerThirst() > i) {
                if(i == 0) {
                    GuiComponent.blit(poseStack,x - 94 + (i * 9),y - 54,0,0,12,12,
                            12,12);
                    RenderSystem.setShaderTexture(0, FILLED_THIRST);
                }
                GuiComponent.blit(poseStack,x - 94 + (i * 9),y - 54,0,0,12,12,
                        12,12);
                RenderSystem.setShaderTexture(0, FILLED_THIRST);
            } else {
                break;
            }
        }

    }));
}
