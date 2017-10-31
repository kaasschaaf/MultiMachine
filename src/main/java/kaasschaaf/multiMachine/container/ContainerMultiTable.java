package kaasschaaf.multiMachine.container;

import kaasschaaf.multiMachine.Handler.NetworkHandler;
import kaasschaaf.multiMachine.Network.MessageMultiTableUpdateContainer;
import kaasschaaf.multiMachine.tileentity.TileEntityMultiTable;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerMultiTable extends Container {

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    private final World world;
    private final EntityPlayer player;
    private final BlockPos pos;
    private final TileEntityMultiTable te;
    private final IItemHandler handler;
    private int table;

    public ContainerMultiTable(InventoryPlayer playerInv, TileEntityMultiTable te, World worldIn, BlockPos posIn){
        this.world = worldIn;
        this.player = playerInv.player;
        this.pos = posIn;
        this.te = te;
        this.table = 0;

        this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotCrafting(playerInv.player, this.craftMatrix, this.craftResult, 0, 124, 35));
        //this.addSlotToContainer(new Slot(this.handler, 0, 124, 55));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.craftMatrix.setInventorySlotContents(j + i * 3,this.handler.getStackInSlot(j + i * 3 + this.table * 9));
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        //Player inv
        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 142));
        }
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        ItemStack superb = super.slotClick(slotId, dragType, clickTypeIn, player);

        if(!world.isRemote && slotId >= 1 && slotId <=9) {
            player.sendMessage(new TextComponentString("save" + world.isRemote));
            for (int i = 0; i < 9; ++i) {
                this.handler.extractItem(i + this.table * 9, this.handler.getSlotLimit(i + this.table * 9), false);
                this.handler.insertItem(i + this.table * 9, this.craftMatrix.getStackInSlot(i), false);
            }
            for (EntityPlayer entityPlayer : world.playerEntities) {
                if (entityPlayer.openContainer == this)
                    ((EntityPlayerMP) entityPlayer).connection.sendPacket(te.getUpdatePacket());
            }
            player.sendMessage(new TextComponentString(this.handler.getStackInSlot(0 + this.table * 9).getDisplayName()));
            NetworkHandler.sendToAll(new MessageMultiTableUpdateContainer());
        }
        return superb;
    }

    public void updateContainer(){
        player.sendMessage(new TextComponentString("update" + world.isRemote));

        for (int i = 0; i < 9; ++i) {
            this.craftMatrix.setInventorySlotContents(i,this.handler.getStackInSlot(i + this.table * 9));
        }
        player.sendMessage(new TextComponentString(this.craftMatrix.getStackInSlot(0).getDisplayName()));
    }

    public void changeTable(int newTable){
        for (int i = 0; i < 9; ++i){
            this.craftMatrix.setInventorySlotContents(i,this.handler.getStackInSlot(i + newTable * 9));
        }
        this.table = newTable;
    }

    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return (playerIn.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0)
            {
                itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index >= 10 && index < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 37 && index < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

            if (index == 0)
            {
                playerIn.dropItem(itemstack2, false);
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.handler.extractItem(i + this.table * 9, this.handler.getSlotLimit(i + this.table * 9), false);
            this.handler.insertItem(i + this.table * 9, this.craftMatrix.getStackInSlot(i), false);
        }
        NetworkHandler.sendToAll(new MessageMultiTableUpdateContainer());

        return itemstack;
    }
}
