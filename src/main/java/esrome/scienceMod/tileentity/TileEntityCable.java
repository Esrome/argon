package esrome.scienceMod.tileentity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import esrome.scienceMod.energy.ElectricityStorage;
import esrome.scienceMod.util.handlers.EnergyHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntityCable extends TileEntity implements ITickable {

	private ElectricityStorage storage = new ElectricityStorage(128, 128);
    boolean u = false,
            d = false,
            n = false,
            s = false,
            w = false,
            e = false;

    public void setU(boolean u) {
        this.u = u;
        markDirty();
    }
    public void setD(boolean d) {
        this.d = d;
        markDirty();
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
    public boolean isU() {
        return u;
    }
    public boolean isD() {
        return d;
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

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        storage.readFromNBT(nbt);
        u = nbt.getBoolean("u");
        d = nbt.getBoolean("d");
        n = nbt.getBoolean("n");
        s = nbt.getBoolean("s");
        w = nbt.getBoolean("w");
        e = nbt.getBoolean("e");
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        storage.writeToNBT(nbt);
        nbt.setBoolean("u", u);
        nbt.setBoolean("d", d);
        nbt.setBoolean("n", n);
        nbt.setBoolean("s", s);
        nbt.setBoolean("w", w);
        nbt.setBoolean("e", e);
        super.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
            return (T) storage;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public void update() {
        if(storage.getEnergyStored() > 0) extractAnywhereConnected(this, storage);
        markDirty();
    }

    public static EnumFacing[] faces = {EnumFacing.UP, EnumFacing.DOWN, EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.NORTH, EnumFacing.EAST};
    public static int extractAnywhereConnected(TileEntityCable conduit, ElectricityStorage storage) {
        List<BlockPos> conduits = new ArrayList<BlockPos>();
        conduits.add(conduit.getPos());
        vein(conduit.getWorld(), conduits, null);

        int given = 0;
        for(BlockPos bpos : conduits) {
            given += EnergyHandler.giveEnergyAllSidesCond(storage, conduit.getWorld(), bpos);
        }
        return given;
    }
    public static void vein(IBlockAccess world, List<BlockPos> conduits, List<BlockPos> dconduits) {
        if(dconduits == null) dconduits = new ArrayList<BlockPos>();
        for(EnumFacing face : faces) {
            List<BlockPos> tconduits = new ArrayList<BlockPos>();
            for(BlockPos bp : conduits.toArray(new BlockPos[] {}))
                tconduits.add(bp);
            for(BlockPos pos : tconduits) {
                if(world.getTileEntity(pos.offset(face)) instanceof TileEntityCable && !dconduits.contains(pos.offset(face))) {
                    conduits.add(pos.offset(face));
                    dconduits.add(pos.offset(face));
                    vein(world, conduits, dconduits);
                }
            }
        }
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        u = tag.getBoolean("u");
        d = tag.getBoolean("d");
        n = tag.getBoolean("n");
        s = tag.getBoolean("s");
        w = tag.getBoolean("w");
        e = tag.getBoolean("e");
    }
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("u", u);
        nbt.setBoolean("d", d);
        nbt.setBoolean("n", n);
        nbt.setBoolean("s", s);
        nbt.setBoolean("w", w);
        nbt.setBoolean("e", e);
        return nbt;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("u", u);
        nbt.setBoolean("d", d);
        nbt.setBoolean("n", n);
        nbt.setBoolean("s", s);
        nbt.setBoolean("w", w);
        nbt.setBoolean("e", e);
        return new SPacketUpdateTileEntity(getPos(), 1, nbt);
    }
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
        NBTTagCompound nbt = pkt.getNbtCompound();
        u = nbt.getBoolean("u");
        d = nbt.getBoolean("d");
        n = nbt.getBoolean("n");
        s = nbt.getBoolean("s");
        w = nbt.getBoolean("w");
        e = nbt.getBoolean("e");
        notifyBlockUpdate();
    }

    private void notifyBlockUpdate() {
        final IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        notifyBlockUpdate();
    }
	
}
