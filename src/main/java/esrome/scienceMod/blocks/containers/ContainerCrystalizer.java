package esrome.scienceMod.blocks.containers;

import esrome.scienceMod.blocks.containers.slots.SlotOutput;
import esrome.scienceMod.tileentity.TileEntityCrystalizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCrystalizer extends Container {

		private final TileEntityCrystalizer tileentity;
		
		public ContainerCrystalizer(InventoryPlayer player, TileEntityCrystalizer tileentity) 
		{
			this.tileentity = tileentity;
			IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			
			this.addSlotToContainer(new SlotItemHandler(handler, 0, 43, 21));
			this.addSlotToContainer(new SlotItemHandler(handler, 1, 87, 21));
			this.addSlotToContainer(new SlotOutput(handler, 2, 65, 56));
			
			//adding player inventory
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 9; x++)
				{
					this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
				}
			}
			//hotbar
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
			}
		}
		
		//can you open the container
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) 
		{
			return this.tileentity.isUsableByPlayer(playerIn);
		}
		
		//handles shift-click events
		@Override
		public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
		{
			ItemStack stack = ItemStack.EMPTY;
			Slot slot = (Slot)this.inventorySlots.get(index);
			
			if(slot != null && slot.getHasStack())
			{
				ItemStack stack1 = slot.getStack();
				stack = stack1.copy();
				
				if(index >= 0 && index < 3) {
					if(!this.mergeItemStack(stack1, 30, 39, false))
						if(!this.mergeItemStack(stack1, 3, 30, false)) return ItemStack.EMPTY;
				}if(index >= 3 && index < 30){
					if(!this.mergeItemStack(stack1, 0, 2, false))
						if(!this.mergeItemStack(stack1, 30, 39, false)) return ItemStack.EMPTY;
				}else if(index >= 30 && index < 39){
					if(!this.mergeItemStack(stack1, 0, 2, false))
						if(!this.mergeItemStack(stack1, 3, 30, false)) return ItemStack.EMPTY;
				}else if(!this.mergeItemStack(stack1, 0, 39, false)){
					return ItemStack.EMPTY;
				}
				
				if(stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
				else slot.onSlotChanged();
				
				if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
				slot.onTake(playerIn, stack1);
			}
			
			return stack;
		}
	
}
