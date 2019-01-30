package esrome.scienceMod.util.handlers;

import esrome.scienceMod.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler {

	public static void registerCustomMeshesAndStates() {
		/*ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.MERCURY_BLOCK), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("escm:mercury", "fluid");
			}
		});
		*/
		
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.HOT_WATER_BLOCK), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("escm:hot_water", "fluid");
			}
		});
		
		
		/*ModelLoader.setCustomStateMapper(ModBlocks.MERCURY_BLOCK, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("escm:mercury", "fluid");
			}
		});
		*/
		
		ModelLoader.setCustomStateMapper(ModBlocks.HOT_WATER_BLOCK, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("escm:hot_water", "fluid");
			}
		});
	}
	
}
