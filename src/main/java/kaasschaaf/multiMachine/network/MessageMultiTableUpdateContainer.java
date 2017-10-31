package kaasschaaf.multiMachine.network;

import io.netty.buffer.ByteBuf;
import kaasschaaf.multiMachine.MultiMachine;
import kaasschaaf.multiMachine.container.ContainerMultiTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class MessageMultiTableUpdateContainer extends Message<MessageMultiTableUpdateContainer> {

    public MessageMultiTableUpdateContainer() {}

    @Override
    public void handleClientSide(MessageMultiTableUpdateContainer message, EntityPlayer player) {
        Container container = player.openContainer;
        if(MultiMachine.proxy.getClientPlayer().openContainer == container && container.getClass() == ContainerMultiTable.class) {
            ContainerMultiTable containerMultiTable = (ContainerMultiTable) container;
            containerMultiTable.updateContainer();
        }
    }

    @Override
    public void handleServerSide(MessageMultiTableUpdateContainer message, EntityPlayer player) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
}
