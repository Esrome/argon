package esrome.scienceMod.util.handlers;

import esrome.scienceMod.tileentity.TileEntityCrystalizer;
import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import esrome.scienceMod.tileentity.TileEntityTransmitterTower;
import esrome.scienceMod.util.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySteamGenerator.class, Reference.MODID + ":steam_generator");
		GameRegistry.registerTileEntity(TileEntityCrystalizer.class, Reference.MODID + ":crystalizer");
		GameRegistry.registerTileEntity(TileEntityTransmitterTower.class, Reference.MODID + ":transmitter_tower");
	}
	
}
