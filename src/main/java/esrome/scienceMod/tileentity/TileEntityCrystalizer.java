package esrome.scienceMod.tileentity;

import javax.annotation.Nullable;

import esrome.scienceMod.blocks.machines.BlockCrystalizer;
import esrome.scienceMod.energy.ElectricityStorage;
import esrome.scienceMod.recipes.RecipesCrystalizer;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
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

public class TileEntityCrystalizer extends TileEntity implements ITickable {

	public ItemStackHandler handler = new ItemStackHandler(3);
	private ElectricityStorage storage = new ElectricityStorage(2000, 100);
	private String customName;
	public int burnTime = 100;
	public int currentBurnTime;
	public int energy = storage.getEnergyStored();
	public int facing;
	public ItemStack[] itemstacks = new ItemStack[3];

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		if(capability == CapabilityEnergy.ENERGY && facing!=EnumFacing.getHorizontal(this.facing)) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		if(capability == CapabilityEnergy.ENERGY && facing!=EnumFacing.getHorizontal(this.facing)) return (T)this.storage;
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
		return new TextComponentTranslation("container.crystalizer");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
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
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("CurrentBurnTime", this.currentBurnTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setString("CustomName", getDisplayName().toString());
		compound.setInteger("GuiEnergy", this.energy);
		compound.setInteger("Facing", this.facing);
		storage.writeToNBT(compound);
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
		int worldFacing = world.getBlockState(pos).getValue(BlockCrystalizer.FACING).getHorizontalIndex();
		if(worldFacing!=facing) {
			this.facing=worldFacing;
		}
		
		ItemStack stack = handler.getStackInSlot(0);
		ItemStack stack1 = handler.getStackInSlot(1);
		ItemStack output = RecipesCrystalizer.getInstance().getCrystalizerRecipe(stack, stack1);
		int outputCount = RecipesCrystalizer.getInstance().getRecipeCount(stack, stack1);
		if(output != null && energy>=3 && handler!=null) {
			if((handler.getStackInSlot(2).isEmpty() || handler.getStackInSlot(2).isEmpty()) || (handler.getStackInSlot(2).getCount()<=output.getMaxStackSize()-output.getCount() && output.isItemEqual(handler.getStackInSlot(2)))){
				currentBurnTime++;
				energy-=3;
				if(currentBurnTime==burnTime) {
					currentBurnTime=0;
					if(!handler.getStackInSlot(2).isEmpty()) {
						handler.getStackInSlot(2).grow(outputCount);;
					}else {
						handler.setStackInSlot(2, output.copy());
					}
					handler.getStackInSlot(0).shrink(1);
					handler.getStackInSlot(1).shrink(1);
				}
			}else if(currentBurnTime>0) currentBurnTime--;
		}else if(currentBurnTime>0) currentBurnTime--;
		if(output==null) currentBurnTime = 0;
		
		storage.setEnergy(energy);
		for(int i = 0; i <= 2; i++) {
			if(handler.getStackInSlot(i)!=itemstacks[i]) {
				markDirty();
				itemstacks[i] = handler.getStackInSlot(i);
			}
		}
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
}
