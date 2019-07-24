package esrome.scienceMod.items;

import esrome.scienceMod.Argon;
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
		Argon.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
