package esrome.scienceMod.tileentity;

import esrome.scienceMod.blocks.machines.BlockBeltGrinder;
import esrome.scienceMod.energy.ElectricityStorage;
import esrome.scienceMod.recipes.RecipesBeltGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBeltGrinder extends TileEntity implements ITickable {

	public ItemStackHandler handler = new ItemStackHandler(4);
	private ElectricityStorage storage = new ElectricityStorage(2000, 100);
	private String customName;
	public int burnTime = 100;
	public int currentBurnTime;
	public int energy = storage.getEnergyStored();
	public int facing;
	boolean up = false, prevUp = false;
	
	public void setUp(boolean u) {
		this.up = u;
		markDirty();
	}
	
	public boolean isUp() {
		return up;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		if(capability == CapabilityEnergy.ENERGY && (facing == null || facing==EnumFacing.UP || facing==EnumFacing.DOWN)) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		if(capability == CapabilityEnergy.ENERGY && (facing == null || facing==EnumFacing.UP || facing==EnumFacing.DOWN)) return (T)this.storage;
		return super.getCapability(capability, facing);
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
		return new TextComponentTranslation("container.belt_grinder");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.up = compound.getBoolean("Up");
		this.burnTime = compound.getInteger("BurnTime");
		this.currentBurnTime = compound.getInteger("CurrentBurnTime");
		this.customName = compound.getString("CustomName");
		this.energy = compound.getInteger("GuiEnergy");
		this.facing = compound.getInteger("Facing");
		this.storage.readFromNBT(compound);
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setBoolean("Up", this.up);
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("CurrentBurnTime", this.currentBurnTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setString("CustomName", getDisplayName().toString());
		compound.setInteger("GuiEnergy", this.energy);
		compound.setInteger("Facing", this.facing);
		this.storage.writeToNBT(compound);
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
	
	@Override
	public void update(){
		energy = storage.getEnergyStored();
		if(world.isBlockPowered(pos) && energy<getMaxEnergyStored()) energy += 100;
		if(energy>getMaxEnergyStored()) energy = getMaxEnergyStored();
		int worldFacing = world.getBlockState(pos).getValue(BlockBeltGrinder.FACING).getHorizontalIndex();
		if(worldFacing!=facing) {
			this.facing=worldFacing;
		}
		ItemStack stack = handler.getStackInSlot(0);
		ItemStack[] output = RecipesBeltGrinder.getInstance().getGrinderRecipe(stack);
		if(energy>=3 && handler!=null) {
			if(handler!=null && output!=null)if(output[0]!=ItemStack.EMPTY && handler.getStackInSlot(1).getCount()<=64-output[0].getCount() && (output[0].isItemEqual(handler.getStackInSlot(1))||handler.getStackInSlot(1).getCount()==0) &&
					handler.getStackInSlot(2).getCount()<=64-output[1].getCount() && (output[1].isItemEqual(handler.getStackInSlot(2))||handler.getStackInSlot(2).getCount()==0) &&
					handler.getStackInSlot(3).getCount()<=64-output[2].getCount() && (output[2].isItemEqual(handler.getStackInSlot(3))||handler.getStackInSlot(3).getCount()==0)){
				currentBurnTime++;
				energy-=3;
				if(currentBurnTime==burnTime) {
					currentBurnTime=0;
					if(handler.getStackInSlot(1).isItemEqual(output[0])) {
						handler.getStackInSlot(1).grow(1);
					}else {
						handler.setStackInSlot(1, output[0]);
					}
					if(handler.getStackInSlot(2).isItemEqual(output[1])) {
						handler.getStackInSlot(2).grow(1);
					}else {
						handler.setStackInSlot(2, output[1]);
					}
					if(handler.getStackInSlot(3).isItemEqual(output[2])) {
						handler.getStackInSlot(3).grow(1);
					}else {
						handler.setStackInSlot(3, output[2]);
					}
					handler.getStackInSlot(0).shrink(1);
				}
			}else if(currentBurnTime>0) {
				currentBurnTime--;
			}
		}else if(currentBurnTime>0) {
			currentBurnTime--;
		}
		storage.setEnergy(energy);
		if(up!=prevUp) {
			prevUp = up;
			markDirty();
		}
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
}
