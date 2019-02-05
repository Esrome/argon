package esrome.scienceMod.blocks.energy;

import java.util.Random;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.blocks.BlockBase;
import esrome.scienceMod.init.ModBlocks;
import esrome.scienceMod.tileentity.TileEntityCable;
import esrome.scienceMod.tileentity.TileEntityTransmitterTower;
import esrome.scienceMod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTransmitterTowerSupport extends BlockBase {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool STRAIGHT = PropertyBool.create("straight");
    public static final PropertyBool N = PropertyBool.create("n");
    public static final PropertyBool E = PropertyBool.create("e");
    public static final PropertyBool S = PropertyBool.create("s");
    public static final PropertyBool W = PropertyBool.create("w");
    protected static final AxisAlignedBB AABB_WE = new AxisAlignedBB(0.0D, 0.4375D, 0.4375D, 1.0D, 0.75D, 0.5625D);
    protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.4375D, 0.4375D, 0.0D, 0.5625D, 0.75D, 1.0D);
    
	public BlockTransmitterTowerSupport(String name) {
		super(name, Material.IRON, 3.0f, -1, SoundType.METAL, ScienceMod.TAB);
	}
	
	@Override
	@SideOnly(Side.SERVER)
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		Block block = worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock();
		if(!(block==ModBlocks.TRANSMITTER_TOWER_BASE || block==ModBlocks.TRANSMITTER_TOWER_SUPPORT)){
			worldIn.setBlockToAir(pos);
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.TRANSMITTER_TOWER_SUPPORT, 1)));
		}
		Block block1 = worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock();
		if((block==ModBlocks.TRANSMITTER_TOWER_SUPPORT || block==ModBlocks.TRANSMITTER_TOWER_BASE) && (block1==ModBlocks.TRANSMITTER_TOWER_SUPPORT || block1==ModBlocks.TRANSMITTER_TOWER_BASE)) {
			worldIn.setBlockState(pos, state.withProperty(STRAIGHT, true));
		}else {
			if(worldIn.getBlockState(pos).getValue(STRAIGHT)==true) {
				worldIn.setBlockState(pos, state.withProperty(STRAIGHT, false));
			}
		}
		TileEntityTransmitterTower te = (TileEntityTransmitterTower) worldIn.getTileEntity(pos);
        boolean n = false;
        boolean s = false;
        boolean w = false;
        boolean e = false;
        if(state.getValue(FACING)==EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.EAST)) instanceof TileEntityCable) {
        		e = true;
        	}
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.WEST)) instanceof TileEntityCable) {
        		w = true;
        	}
        }else if(state.getValue(FACING)==EnumFacing.EAST || state.getValue(FACING) == EnumFacing.WEST) {
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.NORTH)) instanceof TileEntityCable) {
        		n = true;
        	}
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.SOUTH)) instanceof TileEntityCable) {
        		s = true;
        	}
        }
        System.out.println("N: " + n + " S: " + s + " E: " + e + " W: " + w);
        te.setN(n);
        te.setS(s);
        te.setW(w);
        te.setE(e);
        worldIn.setBlockState(pos, state.withProperty(N, n).withProperty(E, e).withProperty(S, s).withProperty(W, w));
	}
	
	@Override
	@SideOnly(Side.SERVER)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		Block block = worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock();
		if(!(block==ModBlocks.TRANSMITTER_TOWER_BASE || block==ModBlocks.TRANSMITTER_TOWER_SUPPORT)){
			worldIn.setBlockToAir(pos);
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.TRANSMITTER_TOWER_SUPPORT, 1)));
		}
		Block block1 = worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock();
		if((block==ModBlocks.TRANSMITTER_TOWER_SUPPORT || block==ModBlocks.TRANSMITTER_TOWER_BASE) && (block1==ModBlocks.TRANSMITTER_TOWER_SUPPORT || block1==ModBlocks.TRANSMITTER_TOWER_BASE)) {
			worldIn.setBlockState(pos, state.withProperty(STRAIGHT, true));
		}else {
			if(worldIn.getBlockState(pos).getValue(STRAIGHT)==true) {
				worldIn.setBlockState(pos, state.withProperty(STRAIGHT, false));
			}
		}
		TileEntityTransmitterTower te = (TileEntityTransmitterTower) worldIn.getTileEntity(pos);
        boolean n = false;
        boolean s = false;
        boolean w = false;
        boolean e = false;
        if(state.getValue(FACING)==EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.EAST)) instanceof TileEntityCable) {
        		e = true;
        	}
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.WEST)) instanceof TileEntityCable) {
        		w = true;
        	}
        }else if(state.getValue(FACING)==EnumFacing.EAST || state.getValue(FACING) == EnumFacing.WEST) {
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.NORTH)) instanceof TileEntityCable) {
        		n = true;
        	}
        	if(worldIn.getTileEntity(pos.offset(EnumFacing.SOUTH)) instanceof TileEntityCable) {
        		s = true;
        	}
        }
        System.out.println("N: " + n + " S: " + s + " E: " + e + " W: " + w);
        te.setN(n);
        te.setS(s);
        te.setW(w);
        te.setE(e);
        worldIn.setBlockState(pos, state.withProperty(N, n).withProperty(E, e).withProperty(S, s).withProperty(W, w));
	}
	
	@Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		state = super.getActualState(state, worldIn, pos);
    	TileEntityTransmitterTower te = (TileEntityTransmitterTower) worldIn.getTileEntity(pos);
        return state.withProperty(N, te.isN())
                .withProperty(E, te.isE())
                .withProperty(S, te.isS())
                .withProperty(W, te.isW());
    }
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		Block block = worldIn.getBlockState(pos.offset(side.getOpposite())).getBlock();
		if(block==ModBlocks.TRANSMITTER_TOWER_BASE || block==ModBlocks.TRANSMITTER_TOWER_SUPPORT) {
			return true;
		}
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(state.getValue(FACING)==EnumFacing.EAST || state.getValue(FACING)==EnumFacing.WEST) return AABB_WE;
		return AABB_NS;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if(state.getValue(FACING)==EnumFacing.EAST || state.getValue(FACING)==EnumFacing.WEST) return AABB_WE;
		return AABB_NS;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.TRANSMITTER_TOWER_SUPPORT);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(ModBlocks.TRANSMITTER_TOWER_SUPPORT));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(ScienceMod.instance, Reference.GUI_TRANSMITTER_TOWER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
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
	
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        EnumFacing enumfacing = placer.getHorizontalFacing().getOpposite();
        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, enumfacing).withProperty(STRAIGHT, false);
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityTransmitterTower();
	}
	
    public IBlockState getStateFromMeta(int meta){
    	boolean straight = false;
    	if(meta/10==1) straight=true;
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta%10)).withProperty(STRAIGHT, straight);
    }

    public int getMetaFromState(IBlockState state){
    	int straight = 0;
    	if(state.getValue(STRAIGHT)==true) straight=10;
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex()+straight;
    }

    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING, STRAIGHT, N, E, S, W});
    }
	
}
