package esrome.scienceMod.util.handlers;

import esrome.scienceMod.energy.ElectricityStorage;
import esrome.scienceMod.tileentity.TileEntityCable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

public class EnergyHandler {
	
	public static final EnumFacing[] ENERGY_IO_SIDES = {EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.EAST};

	public static int giveEnergyAllSidesCond(ElectricityStorage m, World w, BlockPos p) {
		return giveEnergyAllSidesCond(m, w, p, false);
	}
	
	public static int giveEnergyAllSidesCond(ElectricityStorage m, World w, BlockPos p, boolean calcOnly) {
		if(!m.canExtract()) return 0;
		if(m.getEnergyStored() <= 0) return 0;
		if(m.getMaxEnergyStored() <= 0) return 0;

		int fakeStored = Math.min(m.getEnergyStored(), m.getMaxExtract());
		int totalRemove = 0;

		for(EnumFacing side : ENERGY_IO_SIDES) {
			BlockPos op = p.offset(side);
			TileEntity te = w.getTileEntity(op);

			if(te == null) continue;
			if(!te.hasCapability(CapabilityEnergy.ENERGY, null)) continue;
			if(te instanceof TileEntityCable) continue;
			ElectricityStorage oduh = (ElectricityStorage) te.getCapability(CapabilityEnergy.ENERGY, null);

			if(!oduh.canReceive()) continue;

			totalRemove += oduh.receiveEnergy(fakeStored - totalRemove, calcOnly);
		}

		m.extractEnergy(totalRemove, calcOnly);
		return totalRemove;
	}
	
}
