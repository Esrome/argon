package esrome.scienceMod.util.handlers;

import esrome.scienceMod.init.ModBlocks;
import esrome.scienceMod.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingHandler {

	public static void registerRecipes() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.URANIUM_ORE), new ItemStack(ModItems.URANIUM_INGOT), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.RADIUM_ORE), new ItemStack(ModItems.RADIUM_INGOT), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.COPPER_ORE), new ItemStack(ModItems.COPPER_INGOT), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.LEAD_ORE), new ItemStack(ModItems.LEAD_INGOT), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.MAGNETITE_ORE), new ItemStack(ModItems.MAGNETITE_INGOT), 1.0f);
	}
}
