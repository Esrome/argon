package esrome.scienceMod.gui;

import esrome.scienceMod.blocks.containers.ContainerBeltGrinder;
import esrome.scienceMod.tileentity.TileEntityBeltGrinder;
import esrome.scienceMod.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBeltGrinder extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/belt_grinder.png");
	private final InventoryPlayer player;
	private final TileEntityBeltGrinder tileentity;
	
	public GuiBeltGrinder(InventoryPlayer player, TileEntityBeltGrinder tileentity) 
	{
		super(new ContainerBeltGrinder(player, tileentity));
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
		
		int l = this.getCookProgressScaled(35);
		this.drawTexturedModalRect(this.guiLeft + 60, this.guiTop + 34, 176, 0, l, 16);
		
		int k = this.getEnergyStoredScaled(73);
		this.drawTexturedModalRect(this.guiLeft + 148, this.guiTop + 6, 176, 16, 16, 73 - k);
		
	}
	
	private int getEnergyStoredScaled(int pixels){
		int i = this.tileentity.getEnergyStored();
		int j = this.tileentity.getMaxEnergyStored();
		if(i==j) return pixels;
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}
	
	private int getCookProgressScaled(int pixels){
		int i = this.tileentity.currentBurnTime;
		int j = this.tileentity.burnTime;
		if(i==j) return pixels;
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
}
