package esrome.scienceMod.blocks.containers.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotOutput extends SlotItemHandler{

	public SlotOutput(IItemHandler handler, int index, int x, int y) {
		super(handler, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
}
