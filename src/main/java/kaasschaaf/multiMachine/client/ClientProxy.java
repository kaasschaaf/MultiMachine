package kaasschaaf.multiMachine.client;

import kaasschaaf.multiMachine.MultiMachine;
import kaasschaaf.multiMachine.common.CommonProxy;
import kaasschaaf.multiMachine.Handler.GuiHandler;
import kaasschaaf.multiMachine.common.block.Blocks;
import kaasschaaf.multiMachine.common.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(MultiMachine.instance, new GuiHandler());

        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        Items.initClient(mesher);
        Blocks.initClient(mesher);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

    @Override
    public EntityPlayer getClientPlayer(){
        return Minecraft.getMinecraft().player;
    }
}
