package kaasschaaf.multiMachine.common.item;

import kaasschaaf.multiMachine.MultiMachine;
import kaasschaaf.multiMachine.lib.Names;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemTennisracket extends Item {

    public ItemTennisracket(){
        this.setRegistryName(new ResourceLocation(MultiMachine.MODID, Names.Items.TENNISRACKET));
        this.setUnlocalizedName(Names.Items.TENNISRACKET);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
        if(!worldIn.isRemote){playerIn.sendMessage(new TextComponentString("Whoosh!"));}

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        attacker.sendMessage(new TextComponentString("Whack!"));

        return super.hitEntity(stack, target, attacker);
    }
}
