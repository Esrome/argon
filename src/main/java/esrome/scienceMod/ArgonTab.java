package esrome.scienceMod;

import esrome.scienceMod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ArgonTab extends CreativeTabs {
	public ArgonTab(String label) { super("argon"); }
	public ItemStack getTabIconItem() { return new ItemStack(ModItems.ENERGY_READER);}
}	
