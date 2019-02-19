package esrome.scienceMod.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import esrome.scienceMod.blocks.machines.BlockCrystalizer;
import esrome.scienceMod.tileentity.TileEntityCrystalizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderCrystalizer extends TileEntitySpecialRenderer<TileEntityCrystalizer> {

	@Override
	public void render(TileEntityCrystalizer te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ItemStack stack = te.handler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();
			if(te.facing==EnumFacing.NORTH.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.25, y + 0.32, z + 0.52);
			}else if(te.facing==EnumFacing.EAST.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.52, y + 0.32, z + 0.25);
			}else if(te.facing==EnumFacing.SOUTH.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.75, y + 0.32, z + 0.52);
			}else {
				GlStateManager.translate(x + 0.52, y + 0.32, z + 0.75);
			}
	
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
			model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
	
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
	
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
		ItemStack stack1 = te.handler.getStackInSlot(1);
		if (!stack1.isEmpty()) {
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();
			if(te.facing==EnumFacing.NORTH.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.75, y + 0.32, z + 0.52);
			}else if(te.facing==EnumFacing.EAST.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.52, y + 0.32, z + 0.75);
			}else if(te.facing==EnumFacing.SOUTH.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.25, y + 0.32, z + 0.52);
			}else {
				GlStateManager.translate(x + 0.52, y + 0.32, z + 0.25);
			}
	
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack1, te.getWorld(), null);
			model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
	
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack1, model);
	
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
		
		ItemStack stack2 = te.handler.getStackInSlot(2);
		if (!stack2.isEmpty()) {
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();
			if(te.facing==EnumFacing.NORTH.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.52, y + 0.03125, z + 0.1875);
			}else if(te.facing==EnumFacing.EAST.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.8125, y + 0.03125, z + 0.52);
			}else if(te.facing==EnumFacing.SOUTH.getHorizontalIndex()) {
				GlStateManager.translate(x + 0.52, y + 0.03125, z + 0.8125);
			}else {
				GlStateManager.translate(x + 0.1875, y + 0.03125, z + 0.52);
			}
	
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack2, te.getWorld(), null);
			model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
	
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack2, model);
	
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}
	
}
