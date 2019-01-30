package esrome.scienceMod.blocks.machines;

import java.util.List;

import esrome.scienceMod.blocks.BlockBase;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrucible extends BlockBase {

    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.625D, 0.75D);
	
	public BlockCrucible(String name, Material material, float toughness, int harvestlevel, SoundType type, CreativeTabs creativeTab) {
		super(name, material, toughness, harvestlevel, type, creativeTab);
	}
	
	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return AABB;
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add("A terracotta container used for melting and mixing ingrediants.");
	}

}
