package esrome.scienceMod.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesCrystalizer {

	public static ItemStack getOutput(ItemStack stack, ItemStack stack1) {
		if(stack.getItem()==Item.getItemFromBlock(Blocks.SAND)&&stack1.getItem()==Item.getItemFromBlock(Blocks.SAND)) return new ItemStack(Blocks.GLASS, 3);
		return null;
	}
	
}
