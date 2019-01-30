package esrome.scienceMod.util.handlers;

import esrome.scienceMod.blocks.containers.ContainerSteamGenerator;
import esrome.scienceMod.gui.GuiSteamGenerator;
import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import esrome.scienceMod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_STEAM_GENERATOR) return new ContainerSteamGenerator(player.inventory, (TileEntitySteamGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_STEAM_GENERATOR) return new GuiSteamGenerator(player.inventory, (TileEntitySteamGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}


	
}
