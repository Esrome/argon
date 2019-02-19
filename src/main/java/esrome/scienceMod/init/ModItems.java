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

	public static final Item BLANK_BLUEPRINT = new ItemBase("empty_blueprint", ScienceMod.TAB);
	public static final Item GEAR_BLUEPRINT = new ItemBase("gear_blueprint", ScienceMod.TAB);
	
	public static final Item ROTOR = new ItemBase("rotor", ScienceMod.TAB);
	public static final Item GENERATOR = new ItemBase("generator", ScienceMod.TAB);
	
	public static final Item URANIUM_INGOT = new ItemBase("uranium_ingot", ScienceMod.TAB);
	public static final Item RADIUM_INGOT = new ItemBase("radium_ingot", ScienceMod.TAB);
	public static final Item MAGNETITE_INGOT = new ItemBase("magnetite_ingot", ScienceMod.TAB);
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot", ScienceMod.TAB);
	public static final Item LEAD_INGOT = new ItemBase("lead_ingot", ScienceMod.TAB);
	
	public static final Item COPPER_NUGGET = new ItemBase("copper_nugget", ScienceMod.TAB);
	public static final Item LEAD_NUGGET = new ItemBase("lead_nugget", ScienceMod.TAB);
	
	public static final Item IRON_DUST = new ItemBase("iron_dust", ScienceMod.TAB);
	public static final Item GOLD_DUST = new ItemBase("gold_dust", ScienceMod.TAB);
	public static final Item COPPER_DUST = new ItemBase("copper_dust", ScienceMod.TAB);
	public static final Item LEAD_DUST = new ItemBase("lead_dust", ScienceMod.TAB);
	public static final Item SALT = new ItemBase("salt", ScienceMod.TAB);
	public static final Item SILICA = new ItemBase("silica", ScienceMod.TAB);
	public static final Item SULFUR = new ItemBase("sulfur", ScienceMod.TAB);
	
	public static final Item SILICON = new ItemBase("silicon", ScienceMod.TAB);
	public static final Item CRYSTALLINE_SILICON = new ItemBase("crystalline_silicon", ScienceMod.TAB);
	
	public static final Item LEAD_PLATE = new ItemBase("lead_plate", ScienceMod.TAB);
	public static final Item IRON_PLATE = new ItemBase("iron_plate", ScienceMod.TAB);
	
	public static final Item RUBBER_SEPARATOR = new ItemBase("rubber_separator", ScienceMod.TAB);
	public static final Item SILICON_WAFER = new ItemBase("silicon_wafer", ScienceMod.TAB);
	
	public static final Item GEAR_MODEL = new ItemBase("gear_model", ScienceMod.TAB);
	public static final Item IRON_GEAR = new ItemBase("gear_iron", ScienceMod.TAB);
	public static final Item GOLD_GEAR = new ItemBase("gear_gold", ScienceMod.TAB);
	public static final Item COPPER_GEAR = new ItemBase("gear_copper", ScienceMod.TAB);
	public static final Item LEAD_GEAR = new ItemBase("gear_lead", ScienceMod.TAB);
	
}
