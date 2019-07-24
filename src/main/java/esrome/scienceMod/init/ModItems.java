package esrome.scienceMod.init;

import java.util.ArrayList;
import java.util.List;

import esrome.scienceMod.Argon;
import esrome.scienceMod.items.ItemBase;
import esrome.scienceMod.items.ItemEnergyReader;
import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item ENERGY_READER = new ItemEnergyReader("energy_reader");

	public static final Item BLANK_BLUEPRINT = new ItemBase("empty_blueprint", Argon.TAB);
	public static final Item GEAR_BLUEPRINT = new ItemBase("gear_blueprint", Argon.TAB);
	
	public static final Item ROTOR = new ItemBase("rotor", Argon.TAB);
	public static final Item GENERATOR = new ItemBase("generator", Argon.TAB);
	
	public static final Item URANIUM_INGOT = new ItemBase("uranium_ingot", Argon.TAB);
	public static final Item RADIUM_INGOT = new ItemBase("radium_ingot", Argon.TAB);
	public static final Item MAGNETITE_INGOT = new ItemBase("magnetite_ingot", Argon.TAB);
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot", Argon.TAB);
	public static final Item LEAD_INGOT = new ItemBase("lead_ingot", Argon.TAB);
	
	public static final Item COPPER_NUGGET = new ItemBase("copper_nugget", Argon.TAB);
	public static final Item LEAD_NUGGET = new ItemBase("lead_nugget", Argon.TAB);
	
	public static final Item IRON_DUST = new ItemBase("iron_dust", Argon.TAB);
	public static final Item GOLD_DUST = new ItemBase("gold_dust", Argon.TAB);
	public static final Item COPPER_DUST = new ItemBase("copper_dust", Argon.TAB);
	public static final Item LEAD_DUST = new ItemBase("lead_dust", Argon.TAB);
	public static final Item SALT = new ItemBase("salt", Argon.TAB);
	public static final Item SILICA = new ItemBase("silica", Argon.TAB);
	public static final Item SULFUR = new ItemBase("sulfur", Argon.TAB);
	
	public static final Item SILICON = new ItemBase("silicon", Argon.TAB);
	public static final Item CRYSTALLINE_SILICON = new ItemBase("crystalline_silicon", Argon.TAB);
	
	public static final Item LEAD_PLATE = new ItemBase("lead_plate", Argon.TAB);
	public static final Item IRON_PLATE = new ItemBase("iron_plate", Argon.TAB);
	
	public static final Item RUBBER_SEPARATOR = new ItemBase("rubber_separator", Argon.TAB);
	public static final Item SILICON_WAFER = new ItemBase("silicon_wafer", Argon.TAB);
	
	public static final Item GEAR_MODEL = new ItemBase("gear_model", Argon.TAB);
	public static final Item IRON_GEAR = new ItemBase("gear_iron", Argon.TAB);
	public static final Item GOLD_GEAR = new ItemBase("gear_gold", Argon.TAB);
	public static final Item COPPER_GEAR = new ItemBase("gear_copper", Argon.TAB);
	public static final Item LEAD_GEAR = new ItemBase("gear_lead", Argon.TAB);
	
}
