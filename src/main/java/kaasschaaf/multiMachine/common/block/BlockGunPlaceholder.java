package kaasschaaf.multiMachine.common.block;

import kaasschaaf.multiMachine.MultiMachine;
import kaasschaaf.multiMachine.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGunPlaceholder extends Block {

    public BlockGunPlaceholder() {
        super(Material.WOOD);
        this.setRegistryName(new ResourceLocation(MultiMachine.MODID, Names.Blocks.BLOCKGUNPLACEHOLDER));
        this.setUnlocalizedName(Names.Blocks.BLOCKGUNPLACEHOLDER);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
