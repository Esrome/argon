package esrome.scienceMod.init;

import java.util.List;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.blocks.BlockBase;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block URANIUM_ORE = new BlockBase("uranium_ore", Material.ROCK, 3.0f, 2, SoundType.STONE, ScienceMod.TAB);	
	public static final Block URANIUM_BLOCK = new BlockBase("uranium_block", Material.IRON, 3.0f, 2, SoundType.METAL, ScienceMod.TAB);
	public static final Block RADIUM_ORE = new BlockBase("radium_ore", Material.ROCK, 3.0f, 2, SoundType.STONE, ScienceMod.TAB);	
	public static final Block RADIUM_BLOCK = new BlockBase("radium_block", Material.IRON, 3.0f, 2, SoundType.METAL, ScienceMod.TAB);
	
}
