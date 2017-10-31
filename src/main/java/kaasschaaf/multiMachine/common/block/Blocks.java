package kaasschaaf.multiMachine.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Blocks {

    public static final Block[] BLOCKS = {
            new BlockMultiTable(),
            new BlockGunPlaceholder()
    };

    public static void registerItemBlocks(RegistryEvent.Register<Item> event){
        for (Block block: BLOCKS) {
            Item itemBlock = new ItemBlock(block).setRegistryName(block.getRegistryName());
            event.getRegistry().register(itemBlock);
        }
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(BLOCKS);
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher){
    for (Block block: BLOCKS) {
        Item item = Item.getItemFromBlock(block);
            ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
            ModelLoader.registerItemVariants(item, model);
            mesher.register(item, 0, model);
        }
    }
}