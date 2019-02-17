package esrome.scienceMod.blocks.fluids;

import esrome.scienceMod.blocks.BlockFluid;
import esrome.scienceMod.init.ModPotions;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockMercury extends BlockFluid {

	public BlockMercury(String name, Fluid fluid, Material material) {
		super(name, fluid, material);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		if(entityIn instanceof EntityLiving) {
			((EntityLiving)entityIn).addPotionEffect(new PotionEffect(ModPotions.MERCURY_BURN_EFFECT, 1, 1));
		}
	}
	
	@Override
	public Boolean isEntityInsideMaterial(IBlockAccess world, BlockPos blockpos, IBlockState iblockstate, Entity entity, double yToTest, Material materialIn, boolean testingHead) {
		if(entity instanceof EntityLiving) {
			((EntityLiving)entity).addPotionEffect(new PotionEffect(ModPotions.MERCURY_BURN_EFFECT, 1, 1));
		}
		return super.isEntityInsideMaterial(world, blockpos, iblockstate, entity, yToTest, materialIn, testingHead);
	}

}
