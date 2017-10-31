package kaasschaaf.multiMachine.common;

import kaasschaaf.multiMachine.handler.EventHandler;
import kaasschaaf.multiMachine.handler.NetworkHandler;
import kaasschaaf.multiMachine.tileentity.TileEntities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy{

    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        TileEntities.registertileEntities();
        NetworkHandler.init();
    }

    public void init(FMLInitializationEvent event){}

    public void postInit(FMLPostInitializationEvent event){}

    public EntityPlayer getClientPlayer(){
        return null;
    }

}
