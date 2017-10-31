package kaasschaaf.multiMachine.handler;

import kaasschaaf.multiMachine.MultiMachine;
import kaasschaaf.multiMachine.network.MessageMultiTableChangeTable;
import kaasschaaf.multiMachine.network.MessageMultiTableUpdateContainer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MultiMachine.MODID);

    public static void init(){
        INSTANCE.registerMessage(MessageMultiTableChangeTable.class, MessageMultiTableChangeTable.class, 0, Side.SERVER);
        INSTANCE.registerMessage(MessageMultiTableUpdateContainer.class, MessageMultiTableUpdateContainer.class, 0, Side.CLIENT);
    }

    public static void sendToServer(IMessage message){
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player){
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAll(IMessage message){
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
        INSTANCE.sendToAll(message);
    }

}
