package kaasschaaf.multiMachine.handler;

import kaasschaaf.multiMachine.common.block.Blocks;
import kaasschaaf.multiMachine.common.item.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        Items.registerItems(event);
        Blocks.registerItemBlocks(event);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        Blocks.registerBlocks(event);
    }
}
