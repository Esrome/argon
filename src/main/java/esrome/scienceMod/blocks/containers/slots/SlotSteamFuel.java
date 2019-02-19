package esrome.scienceMod.blocks.containers.slots;

import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotSteamFuel extends SlotItemHandler{

	public SlotSteamFuel(IItemHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntitySteamGenerator.isItemHot(stack);
	}
	
}
