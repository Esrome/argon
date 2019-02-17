package esrome.scienceMod.items;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.init.ModItems;
import esrome.scienceMod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name, CreativeTabs creativeTab){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(creativeTab);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		ScienceMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
