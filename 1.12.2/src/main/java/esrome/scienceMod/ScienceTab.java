package esrome.scienceMod;

import esrome.scienceMod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ScienceTab extends CreativeTabs {
	public ScienceTab(String label) { super("escm"); }
	public ItemStack getTabIconItem() { return new ItemStack(ModItems.URANIUM_INGOT);}
}	
