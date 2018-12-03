package esrome.scienceMod.util.handlers;

import esrome.scienceMod.init.ModBlocks;
import esrome.scienceMod.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingHandler {

	public static void registerRecipes() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.URANIUM_ORE, 1), new ItemStack(ModItems.URANIUM_INGOT), 1.0f);
	}
	
}
