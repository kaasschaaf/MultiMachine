package kaasschaaf.multiMachine.common.item;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Items {

    public static final Item[] ITEMS = {
            new ItemMagazine(),
            new ItemTennisracket()
    };

    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ITEMS);
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher){
        for (Item item: ITEMS) {
            ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelLoader.registerItemVariants(item, model);
            mesher.register(item, 0, model);
        }
    }
}
