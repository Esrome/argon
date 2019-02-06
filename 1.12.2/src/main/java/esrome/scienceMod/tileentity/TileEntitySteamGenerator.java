package esrome.scienceMod.tileentity;

import esrome.scienceMod.blocks.machines.BlockSteamGenerator;
import esrome.scienceMod.energy.ElectricityStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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

public class TileEntitySteamGenerator extends TileEntity implements ITickable{

	public ItemStackHandler handler = new ItemStackHandler(3);
	private ElectricityStorage storage = new ElectricityStorage(2000, 100);
	private String customName;
	public int cookTime;
	public int energy = storage.getEnergyStored();
	public int maxEnergy = storage.getMaxEnergyStored();
	public int steam = 0;
	public int maxSteam = 1000;
	public int fuelTime = 0;
	public int maxFuelTime=0;
	public int facing;
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY && (facing==EnumFacing.getHorizontal(this.facing).rotateY() || facing==EnumFacing.getHorizontal(this.facing).rotateYCCW())) return (T)this.storage;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T)this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY && (facing==EnumFacing.getHorizontal(this.facing).rotateY() || facing==EnumFacing.getHorizontal(this.facing).rotateYCCW())) return true;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public void update() {
		this.energy = storage.getEnergyStored();
		int worldFacing = world.getBlockState(pos).getValue(BlockSteamGenerator.FACING).getHorizontalIndex();
		if(worldFacing!=facing) {
			this.facing=worldFacing;
		}
		if(fuelTime>0) {
			fuelTime--;
		}
		if(steam>=10&&energy<storage.getMaxEnergyStored()) {
			steam-=10;
			energy+=1;
			if(energy>storage.getMaxEnergyStored())energy=storage.getMaxEnergyStored();
		}
		if(isItemHot(handler.getStackInSlot(1))&&fuelTime==0&&isItemFuel(handler.getStackInSlot(0))&&canActive(handler.getStackInSlot(0))){
			fuelTime+=getHeatValue(handler.getStackInSlot(1));
			maxFuelTime=getHeatValue(handler.getStackInSlot(1));
			if(handler.getStackInSlot(1).getItem()==Items.LAVA_BUCKET) {
				handler.setStackInSlot(1, new ItemStack(Items.BUCKET));
			}else {
				handler.getStackInSlot(1).shrink(1);
			}
		}
		if(!handler.getStackInSlot(0).isEmpty() && fuelTime > 0 && isItemFuel(handler.getStackInSlot(0))&&steam<maxSteam&&canActive(handler.getStackInSlot(0))) {
			cookTime++;
			if(cookTime == 25) {
				cookTime = 0;
				steam += getFuelValue(handler.getStackInSlot(0));
				if(handler.getStackInSlot(0).getItem()==Items.WATER_BUCKET) {
					handler.getStackInSlot(0).shrink(1);
					if(handler.getStackInSlot(2).getItem()==Items.BUCKET) {
						handler.getStackInSlot(2).grow(1);
					}else {
						handler.setStackInSlot(2, new ItemStack(Items.BUCKET));
					}
				}else if(handler.getStackInSlot(0).getItem()==new ItemStack(Items.POTIONITEM).getItem()) {
					handler.getStackInSlot(0).shrink(1);
					if(handler.getStackInSlot(2).getItem()==Items.GLASS_BOTTLE) {
						handler.getStackInSlot(2).grow(1);
					}else {
						handler.setStackInSlot(2, new ItemStack(Items.GLASS_BOTTLE));
					}
				}
				if(steam>maxSteam) {
					steam = maxSteam;
				}
			}
		}else if(cookTime>0)cookTime--;
		EnumFacing thisFacing = EnumFacing.getHorizontal(facing);
		TileEntity te = world.getTileEntity(pos.offset(thisFacing.rotateY()));
		if(te!=null)if(te.hasCapability(CapabilityEnergy.ENERGY, thisFacing.rotateYCCW())) {
			int extract = this.storage.getMaxExtract();
			if(extract>energy) {
				extract = energy;
			}
			this.energy-=te.getCapability(CapabilityEnergy.ENERGY, thisFacing.rotateYCCW()).receiveEnergy(extract, false);
		}
		te = world.getTileEntity(pos.offset(thisFacing.rotateYCCW()));
		if(te!=null)if(te.hasCapability(CapabilityEnergy.ENERGY, thisFacing.rotateY())) {
			int extract = this.storage.getMaxExtract();
			if(extract>energy) {
				extract = energy;
			}
			this.energy-=te.getCapability(CapabilityEnergy.ENERGY, thisFacing.rotateY()).receiveEnergy(extract, false);
		}
		storage.setEnergy(this.energy);
		markDirty();
	}
	
	public static boolean isItemHot(ItemStack stack) {
		return getHeatValue(stack)>0;
	}
	
	private static int getHeatValue(ItemStack stack) {
		if(stack.getItem()==Items.LAVA_BUCKET) return 500;
		if(stack.getItem()==Items.COAL) return 65;
		if(stack.getItem()==Items.BLAZE_POWDER) return 100;
		if(stack.getItem()==Items.BLAZE_ROD) return 225;
		return 0;
	}
	
	private boolean canActive(ItemStack stack) {
		if(stack.getItem()==Items.WATER_BUCKET&&(handler.getStackInSlot(2).getItem()==Items.BUCKET || handler.getStackInSlot(2).isEmpty())) return true;
		if(stack.getItem()==Items.POTIONITEM&&(handler.getStackInSlot(2).getItem()==Items.GLASS_BOTTLE || handler.getStackInSlot(2).isEmpty())) return true;
		return false;
	}
	
	private void alterStack(ItemStack stack) {
		if(stack.getItem()==Items.WATER_BUCKET) {
			stack = new ItemStack(Items.BUCKET);
			return;
		}else if(stack.getItem()==new ItemStack(Items.POTIONITEM, 0).getItem()) {
			stack.shrink(1);
		}
	}
	
	private boolean isItemFuel(ItemStack stack) {
		return getFuelValue(stack)>0;
	}
	
	private int getFuelValue(ItemStack stack) {
		if(stack.getItem()==Items.WATER_BUCKET) return 300;
		if(stack.getItem()==Items.POTIONITEM) return 100;
		return 0;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("HeatTime", this.cookTime);
		compound.setInteger("GuiEnergy", this.energy);
		compound.setString("Name", getDisplayName().toString());
		compound.setInteger("Steam", this.steam);
		compound.setInteger("FuelTime", this.fuelTime);
		compound.setInteger("MaxFuelTime", this.maxFuelTime);
		this.storage.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.cookTime = compound.getInteger("HeatTime");
		this.energy = compound.getInteger("GuiEnergy");
		this.customName = compound.getString("Name");
		this.steam = compound.getInteger("Steam");
		this.fuelTime = compound.getInteger("FuelTime");
		this.maxFuelTime = compound.getInteger("MaxFuelTime");
		this.storage.readFromNBT(compound);
		this.updateContainingBlockInfo();
		
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container.steam_generator");
	}
	
	public int getEnergyStored() {
		return this.energy;
	}
	
	public int getMaxEnergyStored() {
		return this.maxEnergy;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
}
