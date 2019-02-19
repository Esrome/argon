package esrome.scienceMod.util.compatibility;

import esrome.scienceMod.init.ModBlocks;
import esrome.scienceMod.init.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompatibility {

	public static void registerOres() {
		OreDictionary.registerOre("crucible", ModBlocks.CRUCIBLE);
		OreDictionary.registerOre("generatorSteam", ModBlocks.STEAM_GENERATOR);
		OreDictionary.registerOre("crystalizer", ModBlocks.CRYSTALIZER);
		OreDictionary.registerOre("grinder", ModBlocks.BELT_GRINDER);
		OreDictionary.registerOre("fabricator", ModBlocks.FABRICATOR);
		OreDictionary.registerOre("towerTransmitter", ModBlocks.TRANSMITTER_TOWER_BASE);
		OreDictionary.registerOre("towerTransmitter", ModBlocks.TRANSMITTER_TOWER_SUPPORT);
		
		OreDictionary.registerOre("oreUranium", ModBlocks.URANIUM_ORE);
		OreDictionary.registerOre("oreRadium", ModBlocks.RADIUM_ORE);
		OreDictionary.registerOre("oreCopper", ModBlocks.COPPER_ORE);
		OreDictionary.registerOre("oreLead", ModBlocks.LEAD_ORE);
		OreDictionary.registerOre("oreMagnetite", ModBlocks.MAGNETITE_ORE);
		
		OreDictionary.registerOre("blockUranium", ModBlocks.URANIUM_BLOCK);
		OreDictionary.registerOre("blockRadium", ModBlocks.RADIUM_BLOCK);
		OreDictionary.registerOre("blockCopper", ModBlocks.COPPER_BLOCK);
		OreDictionary.registerOre("blockLead", ModBlocks.LEAD_BLOCK);
		OreDictionary.registerOre("blockMagnetite", ModBlocks.MAGNETITE_BLOCK);
		
		OreDictionary.registerOre("energyReader", ModItems.ENERGY_READER);
		OreDictionary.registerOre("rotor", ModItems.ROTOR);
		OreDictionary.registerOre("generator", ModItems.GENERATOR);
		
		OreDictionary.registerOre("blueprint", ModItems.BLANK_BLUEPRINT);
		OreDictionary.registerOre("blueprintGear", ModItems.GEAR_BLUEPRINT);
		
		OreDictionary.registerOre("ingotUranium", ModItems.URANIUM_INGOT);
		OreDictionary.registerOre("ingotRadium", ModItems.RADIUM_INGOT);
		OreDictionary.registerOre("ingotCopper", ModItems.COPPER_INGOT);
		OreDictionary.registerOre("ingotLead", ModItems.LEAD_INGOT);
		OreDictionary.registerOre("ingotMagnetite", ModItems.MAGNETITE_INGOT);
		
		OreDictionary.registerOre("nuggetLead", ModItems.LEAD_NUGGET);
		OreDictionary.registerOre("nuggetCopper", ModItems.COPPER_NUGGET);
		
		OreDictionary.registerOre("dustLead", ModItems.LEAD_DUST);
		OreDictionary.registerOre("dustIron", ModItems.IRON_DUST);
		OreDictionary.registerOre("dustSalt", ModItems.SALT);
		OreDictionary.registerOre("dustSilica", ModItems.SILICA);
		OreDictionary.registerOre("dustSulfur", ModItems.SULFUR);
		
		OreDictionary.registerOre("gemSilicon", ModItems.SILICON);
		OreDictionary.registerOre("gemSiliconCrystalline", ModItems.CRYSTALLINE_SILICON);
		
		OreDictionary.registerOre("plateLead", ModItems.LEAD_PLATE);
		OreDictionary.registerOre("plateIron", ModItems.IRON_PLATE);
		
		OreDictionary.registerOre("coverRubber", ModItems.RUBBER_SEPARATOR);
		
		OreDictionary.registerOre("gearIron", ModItems.IRON_GEAR);
		OreDictionary.registerOre("gearGold", ModItems.GOLD_GEAR);
		OreDictionary.registerOre("gearCoppper", ModItems.COPPER_GEAR);
		OreDictionary.registerOre("gearLead", ModItems.LEAD_GEAR);
		OreDictionary.registerOre("gearWood", ModItems.GEAR_MODEL);
	}
	
}
