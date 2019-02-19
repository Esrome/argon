package esrome.scienceMod.world.gen;

import java.util.Random;

import esrome.scienceMod.init.ModBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Loader;

public class WorldGenMetalOres implements IWorldGenerator {

	private WorldGenerator ore_overworld_radium, ore_overworld_uranium, ore_overworld_copper, ore_overworld_lead, ore_overworld_magnetite;
	
	public WorldGenMetalOres(){
		ore_overworld_radium = new WorldGenMinable(ModBlocks.RADIUM_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_uranium = new WorldGenMinable(ModBlocks.URANIUM_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_copper = new WorldGenMinable(ModBlocks.COPPER_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_lead = new WorldGenMinable(ModBlocks.LEAD_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_magnetite = new WorldGenMinable(ModBlocks.MAGNETITE_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkx, int chunkz, int chance, int minHeight, int maxHeight){
		if (minHeight > maxHeight || minHeight < 0|| maxHeight > 256){
			throw new IllegalArgumentException("Ore out of bounds or heights are reversed.");
		}
		int heightDiff = maxHeight - minHeight + 1;
		for(int i = 0; i < chance ; i++){
			int x = chunkx * 16 + rand.nextInt(16);
			int z = chunkz * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			gen.generate(world, rand, new BlockPos(x, y, z));
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()){
		case 0:
			runGenerator(ore_overworld_radium, world, random, chunkX, chunkZ, 2, 0, 24);
			runGenerator(ore_overworld_uranium, world, random, chunkX, chunkZ, 2, 0, 24);
			runGenerator(ore_overworld_magnetite, world, random, chunkX, chunkZ, 2, 0, 24);
			if(!Loader.isModLoaded("mcchoc")) {
				runGenerator(ore_overworld_copper, world, random, chunkX, chunkZ, 5, 0, 60);
				runGenerator(ore_overworld_lead, world, random, chunkX, chunkZ, 4, 0, 36);
			}
			break;
		case -1:
			break;
		case 1:
			
		}
	}
	
}
