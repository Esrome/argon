package esrome.scienceMod.tileentity;

import esrome.scienceMod.blocks.energy.BlockTransmitterTowerSupport;
import esrome.scienceMod.energy.ElectricityStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntityTransmitterTower extends TileEntity implements ITickable {

	private ElectricityStorage storage = new ElectricityStorage(2000, 100);
	private String customName;
	public int energy = storage.getEnergyStored();
	public int facing;
	public boolean n = false, e = false, s = false, w = false, pn = false, pe = false, ps = false, pw = false;
	public static EnumFacing[] faces = {EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.NORTH, EnumFacing.EAST};

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY && (facing==null || facing == EnumFacing.getHorizontal(this.facing).rotateY() || facing == EnumFacing.getHorizontal(this.facing).rotateYCCW())) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY && (facing==null || facing == EnumFacing.getHorizontal(this.facing).rotateY() || facing == EnumFacing.getHorizontal(this.facing).rotateYCCW())) return (T)this.storage;
		return super.getCapability(capability, facing);
	}
	
	public void setE(boolean e) {
        this.e = e;
        markDirty();
    }
    public void setN(boolean n) {
        this.n = n;
        markDirty();
    }
    public void setS(boolean s) {
        this.s = s;
        markDirty();
    }
    public void setW(boolean w) {
        this.w = w;
        markDirty();
    }
	public boolean isE() {
        return e;
    }
    public boolean isN() {
        return n;
    }
    public boolean isS() {
        return s;
    }
    public boolean isW() {
        return w;
    }
	
	public int getEnergyStored() {
		return this.energy;
	}
	
	public int getMaxEnergyStored(){
		return this.storage.getMaxEnergyStored();
	}
	
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return new TextComponentTranslation("container.transmitter_tower");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.customName = compound.getString("CustomName");
		this.energy = compound.getInteger("GuiEnergy");
		this.storage.readFromNBT(compound);
		this.e = compound.getBoolean("E");
		this.w = compound.getBoolean("W");
		this.s = compound.getBoolean("S");
		this.n = compound.getBoolean("N");
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setString("CustomName", getDisplayName().toString());
		compound.setInteger("GuiEnergy", this.energy);
		compound.setBoolean("N", n);
		compound.setBoolean("E", e);
		compound.setBoolean("S", s);
		compound.setBoolean("W", w);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
		return writeToNBT(tag);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), getUpdateTag());
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		readFromNBT(tag);
	}
	
	private void notifyBlockUpdate() {
        final IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }
	
	@Override
    public void markDirty() {
        super.markDirty();
        notifyBlockUpdate();
    }
	
	@Override
	public void update(){
		energy = storage.getEnergyStored();
		if(world.isBlockPowered(pos) && energy<getMaxEnergyStored()) energy += 100;
		if(energy>getMaxEnergyStored()) energy = getMaxEnergyStored();
		int worldFacing = world.getBlockState(pos).getValue(BlockTransmitterTowerSupport.FACING).getHorizontalIndex();
		if(worldFacing!=facing) {
			this.facing=worldFacing;
		}
		storage.setEnergy(energy);
		if(pn!=n) {
			pn = n;
			markDirty();
		}
		if(pe!=e) {
			pe = e;
			markDirty();
		}
		if(ps!=s) {
			ps = s;
			markDirty();
		}
		if(pw!=w) {
			pw = w;
			markDirty();
		}
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
}
