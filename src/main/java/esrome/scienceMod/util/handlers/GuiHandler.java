package esrome.scienceMod.util.handlers;

import esrome.scienceMod.blocks.containers.ContainerBeltGrinder;
import esrome.scienceMod.blocks.containers.ContainerCrystalizer;
import esrome.scienceMod.blocks.containers.ContainerSteamGenerator;
import esrome.scienceMod.blocks.containers.ContainerTransmitterTower;
import esrome.scienceMod.gui.GuiBeltGrinder;
import esrome.scienceMod.gui.GuiCrystalizer;
import esrome.scienceMod.gui.GuiSteamGenerator;
import esrome.scienceMod.gui.GuiTransmitterTower;
import esrome.scienceMod.tileentity.TileEntityBeltGrinder;
import esrome.scienceMod.tileentity.TileEntityCrystalizer;
import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import esrome.scienceMod.tileentity.TileEntityTransmitterTower;
import esrome.scienceMod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_STEAM_GENERATOR) return new ContainerSteamGenerator(player.inventory, (TileEntitySteamGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_CRYSTALIZER) return new ContainerCrystalizer(player.inventory, (TileEntityCrystalizer)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_TRANSMITTER_TOWER) return new ContainerTransmitterTower(player.inventory, (TileEntityTransmitterTower)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_BELT_GRINDER) return new ContainerBeltGrinder(player.inventory, (TileEntityBeltGrinder)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_STEAM_GENERATOR) return new GuiSteamGenerator(player.inventory, (TileEntitySteamGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_CRYSTALIZER) return new GuiCrystalizer(player.inventory, (TileEntityCrystalizer)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_TRANSMITTER_TOWER) return new GuiTransmitterTower(player.inventory, (TileEntityTransmitterTower)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_BELT_GRINDER) return new GuiBeltGrinder(player.inventory, (TileEntityBeltGrinder)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}


	
}
