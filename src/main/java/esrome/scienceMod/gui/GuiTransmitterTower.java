package esrome.scienceMod.gui;

import esrome.scienceMod.blocks.containers.ContainerCrystalizer;
import esrome.scienceMod.blocks.containers.ContainerTransmitterTower;
import esrome.scienceMod.tileentity.TileEntityTransmitterTower;
import esrome.scienceMod.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiTransmitterTower extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/transmitter_tower.png");
	private final InventoryPlayer player;
	private final TileEntityTransmitterTower tileentity;
	
	public GuiTransmitterTower(InventoryPlayer player, TileEntityTransmitterTower tileentity) 
	{
		super(new ContainerTransmitterTower(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 35, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
		this.fontRenderer.drawString(String.valueOf((this.tileentity.getEnergyStored())), (this.xSize / 2 - this.fontRenderer.getStringWidth(String.valueOf(this.tileentity.getEnergyStored())) / 2), 45, 16768873);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int k = this.getEnergyStoredScaled(158);
		this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 9, 0 + k, 166, 158 - k, 20);
		
	}
	
	private int getEnergyStoredScaled(int pixels){
		int i = this.tileentity.getEnergyStored();
		int j = this.tileentity.getMaxEnergyStored();
		if(i==j) {
			return i;
		}
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}
	
}
