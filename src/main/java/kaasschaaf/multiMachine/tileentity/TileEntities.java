package kaasschaaf.multiMachine.tileentity;

import kaasschaaf.multiMachine.MultiMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntities {

    public static final class TitleEntryName{
        public static String name;
        public static Class<? extends TileEntity> tileEntityClass;

        public TitleEntryName(String name, Class<? extends TileEntity> tileEntityClass){
            this.name = name;
            this.tileEntityClass = tileEntityClass;
        }
    }

    public static final TitleEntryName TileEntries[] = {
            new TitleEntryName("multitable", TileEntityMultiTable.class)
    };


    public static void registertileEntities() {
        for (TitleEntryName tileentity : TileEntries) {
            GameRegistry.registerTileEntity(tileentity.tileEntityClass, MultiMachine.MODID + ":" + tileentity.name);
        }
    }
}
