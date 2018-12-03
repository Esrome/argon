package esrome.scienceMod.init;

import java.util.ArrayList;
import java.util.List;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.items.ItemBase;
import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item URANIUM_INGOT = new ItemBase("uranium_ingot", ScienceMod.TAB);
	public static final Item RADIUM_INGOT = new ItemBase("radium_ingot", ScienceMod.TAB);
	
}
