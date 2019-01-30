package esrome.scienceMod.util.handlers;

import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import esrome.scienceMod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySteamGenerator.class, new ResourceLocation(Reference.MODID + ":steam_generator"));
	}
	
}
