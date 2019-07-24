package esrome.scienceMod.init;

import java.util.ArrayList;
import java.util.List;

import esrome.scienceMod.Argon;
import esrome.scienceMod.blocks.BlockBase;
import esrome.scienceMod.blocks.BlockFluid;
import esrome.scienceMod.blocks.energy.BlockTransmitterTowerBase;
import esrome.scienceMod.blocks.energy.BlockTransmitterTowerSupport;
import esrome.scienceMod.blocks.machines.BlockBeltGrinder;
import esrome.scienceMod.blocks.machines.BlockCrucible;
import esrome.scienceMod.blocks.machines.BlockCrystalizer;
import esrome.scienceMod.blocks.machines.BlockFabricator;
import esrome.scienceMod.blocks.machines.BlockSteamGenerator;
import esrome.scienceMod.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Machines
		//non-powered
	public static final Block CRUCIBLE = new BlockCrucible("crucible");
		//generators
	public static final Block STEAM_GENERATOR = new BlockSteamGenerator("steam_generator");
		//powered
	public static final Block CRYSTALIZER = new BlockCrystalizer("crystalizer");
	public static final Block BELT_GRINDER = new BlockBeltGrinder("belt_grinder");
	public static final Block FABRICATOR = new BlockFabricator("fabricator");
		//storage
	public static final Block TRANSMITTER_TOWER_BASE = new BlockTransmitterTowerBase("transmitter_tower_base");
	public static final Block TRANSMITTER_TOWER_SUPPORT = new BlockTransmitterTowerSupport("transmitter_tower_support");
		//cables
	public static final Item COPPER_WIRE = new ItemBase("copper_wire", Argon.TAB);
	
	public static final Block URANIUM_ORE = new BlockBase("uranium_ore", Material.ROCK, 3.0f, 2, SoundType.STONE, Argon.TAB);	
	public static final Block RADIUM_ORE = new BlockBase("radium_ore", Material.ROCK, 3.0f, 2, SoundType.STONE, Argon.TAB);	
	public static final Block MAGNETITE_ORE = new BlockBase("magnetite_ore", Material.ROCK, 3.0f, 1, SoundType.STONE, Argon.TAB);
	
	public static final Block URANIUM_BLOCK = new BlockBase("uranium_block", Material.IRON, 3.0f, 2, SoundType.METAL, Argon.TAB);
	public static final Block RADIUM_BLOCK = new BlockBase("radium_block", Material.IRON, 3.0f, 2, SoundType.METAL, Argon.TAB);
	public static final Block MAGNETITE_BLOCK = new BlockBase("magnetite_block", Material.IRON, 3.0f, 1, SoundType.METAL, Argon.TAB);
	
	//Implementations of blocks from Minecraft Chocolate
	public static final Block COPPER_ORE = new BlockBase("copper_ore", Material.ROCK, 1.6f, 1, SoundType.STONE, Argon.TAB);
	public static final Block LEAD_ORE = new BlockBase("lead_ore", Material.ROCK, 1.6f, 1, SoundType.STONE, Argon.TAB);
	
	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Material.IRON, 3.0F, 0, SoundType.METAL, Argon.TAB);
	public static final Block LEAD_BLOCK = new BlockBase("lead_block", Material.IRON, 5.0F, 0, SoundType.METAL, Argon.TAB);
	
	
	//Fluids
	//public static final Block MERCURY_BLOCK = new BlockMercury("mercury", ModFluids.MERCURY, Material.WATER);
	public static final Block HOT_WATER_BLOCK = new BlockFluid("hot_water", ModFluids.HOT_WATER, Material.WATER);
	
}
