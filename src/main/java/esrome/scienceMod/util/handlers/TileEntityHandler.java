package esrome.scienceMod.util.handlers;

import esrome.scienceMod.tileentity.TileEntityBeltGrinder;
import esrome.scienceMod.tileentity.TileEntityCrystalizer;
import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import esrome.scienceMod.tileentity.TileEntityTransmitterTower;
import esrome.scienceMod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySteamGenerator.class, new ResourceLocation(Reference.MODID + ":steam_generator"));
		GameRegistry.registerTileEntity(TileEntityCrystalizer.class, new ResourceLocation(Reference.MODID + ":crystalizer"));
		GameRegistry.registerTileEntity(TileEntityTransmitterTower.class, new ResourceLocation(Reference.MODID + ":transmitter_tower"));
		GameRegistry.registerTileEntity(TileEntityBeltGrinder.class, new ResourceLocation(Reference.MODID + ":belt_grinder"));
	}
	
}
