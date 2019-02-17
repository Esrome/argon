package esrome.scienceMod.tileentity;

import esrome.scienceMod.blocks.machines.BlockCrystalizer;
import esrome.scienceMod.energy.ElectricityStorage;
import esrome.scienceMod.init.ModItems;
import esrome.scienceMod.recipes.RecipesFabricator;
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

public class TileEntityFabricator extends TileEntity implements ITickable {

	public ItemStackHandler handler = new ItemStackHandler(5);
	private ElectricityStorage storage = new ElectricityStorage(2000, 100);
	private String customName;
	public int burnTime = 100;
	public int currentBurnTime;
	public int energy = storage.getEnergyStored();
	public int facing;
	public ItemStack[] itemstacks = new ItemStack[5];
	
	

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
	
	//getters and setters
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
	
	//used for translations
	@Override
	public ITextComponent getDisplayName() 
	{
		return new TextComponentTranslation("container.fabricator");
	}
	
	//start of syncing
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
	//end of syncing
	
	@Override
	public void update(){
		//getting storage energy
		energy = storage.getEnergyStored();
		//placeholder until cables
		if(world.isBlockPowered(pos) && energy<getMaxEnergyStored()) energy += 100;
		
		if(energy>getMaxEnergyStored()) energy = getMaxEnergyStored();
		//facing updater
		int worldFacing = world.getBlockState(pos).getValue(BlockCrystalizer.FACING).getHorizontalIndex();
		if(worldFacing!=facing) {
			this.facing=worldFacing;
		}
		//getting the recipe output for the inputs (if there is one)
		ItemStack stack = handler.getStackInSlot(0);
		ItemStack stack1 = handler.getStackInSlot(1);
		ItemStack stack2 = handler.getStackInSlot(2);
		ItemStack stack3 = handler.getStackInSlot(3);
		ItemStack output = RecipesFabricator.getInstance().getFabricatorRecipe(stack, stack1, stack2, stack3);
		
		if(energy>=3 && handler!=null) {
			//testing to see if the inputs and outputs all work
			if(output!=null)if(handler.getStackInSlot(4).getCount()<=output.getMaxStackSize()-output.getCount() && (output.isItemEqual(handler.getStackInSlot(4))||handler.getStackInSlot(4).getCount()==0)){
				currentBurnTime++;
				energy-=10;
				//code for if it is finished burning
				if(currentBurnTime==burnTime) {
					//reset burntime
					currentBurnTime=0;
					if(handler.getStackInSlot(4).isItemEqual(output)) {
						handler.getStackInSlot(4).grow(output.getCount());
					}else {
						handler.setStackInSlot(4, output);
					}
					//shrinking inputs
					handler.getStackInSlot(0).shrink(1);
					handler.getStackInSlot(1).shrink(1);
					handler.getStackInSlot(2).shrink(1);
				}
			//shrinking burn time if the output and inputs are incompatible
			}else if(currentBurnTime>0) {
				currentBurnTime--;
			}
		//shrinking burn time if there isn't enough energy
		}else if(currentBurnTime>0) {
			currentBurnTime--;
		}
		
		//updating storage energy
		storage.setEnergy(energy);
		
		//updating item stacks and rendering
		for(int i = 0; i<5; i++) {
			if(handler.getStackInSlot(i)!=itemstacks[i]) {
				itemstacks[i] = handler.getStackInSlot(i);
				markDirty();
			}
		}
	}
	
	//is the player close enough to use?
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
}
