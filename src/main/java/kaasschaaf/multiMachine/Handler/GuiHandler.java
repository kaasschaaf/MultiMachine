package kaasschaaf.multiMachine.Handler;

import kaasschaaf.multiMachine.container.ContainerMultiTable;
import kaasschaaf.multiMachine.gui.GuiMultiTable;
import kaasschaaf.multiMachine.lib.Names;
import kaasschaaf.multiMachine.tileentity.TileEntityMultiTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID) {
            case Names.GUI.GUI_MULTITABLE_ID: return new ContainerMultiTable(player.inventory, (TileEntityMultiTable) world.getTileEntity(new BlockPos(x,y,z)), world, new BlockPos(x,y,z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID) {
            case Names.GUI.GUI_MULTITABLE_ID: return new GuiMultiTable(player.inventory, (TileEntityMultiTable) world.getTileEntity(new BlockPos(x,y,z)), world, new BlockPos(x,y,z));
        }
        return null;
    }
}
