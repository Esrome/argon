package esrome.scienceMod.init;

import java.util.ArrayList;
import java.util.List;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.items.ItemBase;
import esrome.scienceMod.items.ItemEnergyReader;
import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item ENERGY_READER = new ItemEnergyReader("energy_reader");
	
	public static final Item URANIUM_INGOT = new ItemBase("uranium_ingot", ScienceMod.TAB);
	public static final Item RADIUM_INGOT = new ItemBase("radium_ingot", ScienceMod.TAB);
	
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot", ScienceMod.TAB);
	public static final Item COPPER_NUGGET = new ItemBase("copper_nugget", ScienceMod.TAB);
	public static final Item LEAD_INGOT = new ItemBase("lead_ingot", ScienceMod.TAB);
	public static final Item LEAD_NUGGET = new ItemBase("lead_nugget", ScienceMod.TAB);
	
	public static final Item LEAD_DUST = new ItemBase("lead_dust", ScienceMod.TAB);
	
}
