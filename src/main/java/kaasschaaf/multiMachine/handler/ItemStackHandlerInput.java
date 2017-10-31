package kaasschaaf.multiMachine.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerInput extends ItemStackHandler {

    private final ItemStackHandler internalSlot;

    public ItemStackHandlerInput(ItemStackHandler hidden) {
        super();
        internalSlot = hidden;
    }

    @Override
    public void setSize(int size) {
        return;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        return;
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return ItemStack.EMPTY;
    }
}
