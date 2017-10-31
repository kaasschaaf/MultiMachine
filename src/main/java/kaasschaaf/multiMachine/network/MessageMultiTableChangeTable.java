package kaasschaaf.multiMachine.network;

import io.netty.buffer.ByteBuf;
import kaasschaaf.multiMachine.container.ContainerMultiTable;
import net.minecraft.entity.player.EntityPlayer;

public class MessageMultiTableChangeTable extends Message<MessageMultiTableChangeTable> {

    private int table;

    public MessageMultiTableChangeTable() {}

    public MessageMultiTableChangeTable(int table) {
        this.table = table;
    }

    @Override
    public void handleClientSide(MessageMultiTableChangeTable message, EntityPlayer player) {

    }

    @Override
    public void handleServerSide(MessageMultiTableChangeTable message, EntityPlayer player) {
        ContainerMultiTable containerMultiTable = (ContainerMultiTable) player.openContainer;
        containerMultiTable.changeTable(message.table);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.table = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.table);
    }
}