package esrome.scienceMod.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class ElectricityStorage extends EnergyStorage {

	public ElectricityStorage(int capacity){
        super(capacity, capacity, capacity, 0);
    }

    public ElectricityStorage(int capacity, int maxTransfer){
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    public ElectricityStorage(int capacity, int maxReceive, int maxExtract){
        super(capacity, maxReceive, maxExtract, 0);
    }

    public ElectricityStorage(int capacity, int maxReceive, int maxExtract, int energy){
    	super(capacity, maxReceive, maxExtract, energy);
    }
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
    	return super.receiveEnergy(maxReceive, simulate);
    }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
    	return super.extractEnergy(maxExtract, simulate);
    }
    
    @Override
    public int getEnergyStored() {
    	return super.getEnergyStored();
    }
    
    @Override
    public int getMaxEnergyStored() {
    	return super.getMaxEnergyStored();
    }
    
    @Override
    public boolean canExtract() {
    	return super.canExtract();
    }
    
    @Override
    public boolean canReceive() {
    	return super.canReceive();
    }
    
    public void readFromNBT(NBTTagCompound compound) {
    	this.energy = compound.getInteger("energy");
    	this.capacity = compound.getInteger("capacity");
    	this.maxExtract = compound.getInteger("maxExtract");
    	this.maxReceive = compound.getInteger("maxReceive");
    }
    
    public void writeToNBT(NBTTagCompound compound) {
    	compound.setInteger("energy", this.energy);
    	compound.setInteger("capacity", this.capacity);
    	compound.setInteger("maxExtract", this.maxExtract);
    	compound.setInteger("maxReceive", this.maxReceive);
    }

	public int getMaxExtract() {
		return this.maxExtract;
	}
	
}
