package esrome.scienceMod.gui;

import esrome.scienceMod.blocks.containers.ContainerCrystalizer;
import esrome.scienceMod.tileentity.TileEntityCrystalizer;
import esrome.scienceMod.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCrystalizer extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/crystalizer.png");
	private final InventoryPlayer player;
	private final TileEntityCrystalizer tileentity;
	
	public GuiCrystalizer(InventoryPlayer player, TileEntityCrystalizer tileentity) 
	{
		super(new ContainerCrystalizer(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) - 10, 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
		this.fontRenderer.drawString(String.valueOf((this.tileentity.getEnergyStored())), 120, this.ySize- 96 + 2, 16768873);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int l = this.getCookProgressScaled(14);
		this.drawTexturedModalRect(this.guiLeft + 66, this.guiTop + 38 - l, 176, 14 - l, 14, l);
		
		int k = this.getEnergyStoredScaled(75);
		this.drawTexturedModalRect(this.guiLeft + 148, this.guiTop + 6, 176, 14, 16, 76 - k);
		
	}
	
	private int getEnergyStoredScaled(int pixels){
		int i = this.tileentity.getEnergyStored();
		int j = this.tileentity.getMaxEnergyStored();
		if(i==j) {
			return i;
		}
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}
	
	private int getCookProgressScaled(int pixels){
		int i = this.tileentity.currentBurnTime;
		int j = this.tileentity.burnTime;
		if(i==25) {
			return pixels;
		}
		if(i==0) {
			return 0;
		}
		return i != 0 ? i * pixels / j : 0;
	}
	
}
