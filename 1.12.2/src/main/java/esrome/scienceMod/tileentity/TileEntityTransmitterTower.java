package esrome.scienceMod.tileentity;

import esrome.scienceMod.energy.ElectricityStorage;
import esrome.scienceMod.recipes.RecipesCrystalizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityTransmitterTower extends TileEntity implements ITickable {

	private ElectricityStorage storage = new ElectricityStorage(2000, 100);
	private String customName;
	public int energy = storage.getEnergyStored();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY) return (T)this.storage;
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
		return new TextComponentTranslation("container.transmitter_tower");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.customName = compound.getString("CustomName");
		this.energy = compound.getInteger("GuiEnergy");
		this.storage.readFromNBT(compound);
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setString("CustomName", getDisplayName().toString());
		compound.setInteger("GuiEnergy", this.energy);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public void update()
	{
		if(world.isBlockPowered(pos) && energy<getMaxEnergyStored()) energy += 100;
		if(energy>getMaxEnergyStored()) energy = getMaxEnergyStored();
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
}
