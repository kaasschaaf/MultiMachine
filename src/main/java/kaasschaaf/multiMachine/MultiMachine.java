package kaasschaaf.multiMachine;

import kaasschaaf.multiMachine.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MultiMachine.MODID,name = MultiMachine.MODNAME, version = MultiMachine.VERSION, dependencies = MultiMachine.DEPENDENCIES)
public class MultiMachine {
    public static final String MODID = "multimachine";
    public static final String MODNAME = "Multi Machine";
    public static final String VERSION = "0.0.0.1";
    public static final String DEPENDENCIES = "required-after:forge";
    public static final String RESCOURCE_PREFIX = MODID.toLowerCase() + ":";

    @Instance(MODID)
    public static MultiMachine instance;

    @SidedProxy(clientSide = "kaasschaaf.multiMachine.client.ClientProxy", serverSide = "kaasschaaf.multiMachine.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}