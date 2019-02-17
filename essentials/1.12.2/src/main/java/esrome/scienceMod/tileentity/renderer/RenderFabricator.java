package esrome.scienceMod.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import esrome.scienceMod.blocks.models.ModelFabricator;
import esrome.scienceMod.tileentity.TileEntityFabricator;
import esrome.scienceMod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFabricator extends TileEntitySpecialRenderer<TileEntityFabricator>{

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/blocks/fabricator.png");
	private final ModelFabricator MODEL = new ModelFabricator();
	
	@Override
    public void render(TileEntityFabricator te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        
        float facingD = te.facing * 90;
        double facingX = 0D, facingZ = 0D;
        if(EnumFacing.getHorizontal(te.facing)==EnumFacing.EAST) {
        	facingX = 1D;
        	
        }
        if(EnumFacing.getHorizontal(te.facing)==EnumFacing.NORTH) {
        	facingX = 1D; facingZ = 1D;
        }
        if(EnumFacing.getHorizontal(te.facing)==EnumFacing.WEST) {
        	facingZ = 1D;
        }
        
        ModelFabricator model = MODEL;
       
        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else this.bindTexture(TEXTURES);
       
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        GlStateManager.translate(facingX, 0, facingZ);
        GlStateManager.rotate(facingD, 0f, 1f, 0f);
       
        model.renderAll();
        
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
 
        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
	
}
