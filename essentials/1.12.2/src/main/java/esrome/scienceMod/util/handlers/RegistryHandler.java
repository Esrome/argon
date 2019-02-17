package esrome.scienceMod.util.handlers;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.init.ModBlocks;
import esrome.scienceMod.init.ModFluids;
import esrome.scienceMod.init.ModItems;
import esrome.scienceMod.init.ModPotions;
import esrome.scienceMod.util.IHasModel;
import esrome.scienceMod.util.compatibility.OreDictionaryCompatibility;
import esrome.scienceMod.world.gen.WorldGenMetalOres;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event){
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		RenderHandler.registerTileEntitySpecialRenderers();
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event){
		for(Item item : ModItems.ITEMS){
			if(item instanceof IHasModel){
				((IHasModel)item).registerModels();
			}
		}
		for(Block block : ModBlocks.BLOCKS){
			if(block instanceof IHasModel){
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInit() {
		ModFluids.registerFluids();
		ModPotions.registerPotions();
		
		GameRegistry.registerWorldGenerator(new WorldGenMetalOres(), 0);
		
		RenderHandler.registerCustomMeshesAndStates();

		OreDictionaryCompatibility.registerOres();
	}
	
	public static void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ScienceMod.instance, new GuiHandler());
		SmeltingHandler.registerRecipes();
	}
	
}
