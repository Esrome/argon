package esrome.scienceMod.blocks.machines;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.blocks.BlockBase;
import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import esrome.scienceMod.util.Reference;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSteamGenerator extends BlockBase {
	
	public BlockSteamGenerator(String name) {
		super(name, Material.IRON, 3.0f, 0, SoundType.METAL, ScienceMod.TAB);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(ScienceMod.instance, Reference.GUI_STEAM_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySteamGenerator();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntitySteamGenerator tileentity = (TileEntitySteamGenerator)worldIn.getTileEntity(pos);
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(1)));
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(2)));
		super.breakBlock(worldIn, pos, state);
	}
	
}

