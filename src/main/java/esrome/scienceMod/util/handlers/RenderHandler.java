package esrome.scienceMod.util.handlers;

import esrome.scienceMod.init.ModBlocks;
import esrome.scienceMod.tileentity.TileEntityCrystalizer;
import esrome.scienceMod.tileentity.renderer.RenderCrystalizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class RenderHandler {

	public static void registerCustomMeshesAndStates() {
		/*ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.MERCURY_BLOCK), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("argon:mercury", "fluid");
			}
		});
		*/
		
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.HOT_WATER_BLOCK), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("argon:hot_water", "fluid");
			}
		});
		
		
		/*ModelLoader.setCustomStateMapper(ModBlocks.MERCURY_BLOCK, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("argon:mercury", "fluid");
			}
		});
		*/
		
		ModelLoader.setCustomStateMapper(ModBlocks.HOT_WATER_BLOCK, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("argon:hot_water", "fluid");
			}
		});
	}
	
	public static void registerTileEntitySpecialRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalizer.class, new RenderCrystalizer());
	}
	
}
