package kaasschaaf.multiMachine.gui;

import kaasschaaf.multiMachine.Handler.NetworkHandler;
import kaasschaaf.multiMachine.MultiMachine;
import kaasschaaf.multiMachine.Network.MessageMultiTableChangeTable;
import kaasschaaf.multiMachine.container.ContainerMultiTable;
import kaasschaaf.multiMachine.tileentity.TileEntityMultiTable;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.io.IOException;

public class GuiMultiTable extends GuiContainer {

    private static final ResourceLocation GUI_TEXTURES = new ResourceLocation(MultiMachine.MODID, "textures/gui/container/multitable.png");
    private GuiButtonImage prevButton;
    private GuiButtonImage nextButton;
    private TileEntityMultiTable te;
    private InventoryPlayer playerInv;
    private int table;
    private ContainerMultiTable containerMultiTable;

    public GuiMultiTable(InventoryPlayer playerInv, TileEntityMultiTable te, World worldIn, BlockPos pos) {
        super(new ContainerMultiTable(playerInv, (TileEntityMultiTable) worldIn.getTileEntity(pos), worldIn, pos));

        this.xSize = 176;
        this.ySize = 166;

        this.te = te;
        this.playerInv = playerInv;
        this.table = 0;
        this.containerMultiTable = (ContainerMultiTable) inventorySlots;
    }

    @Override
    public void initGui() {
        super.initGui();
        prevButton = new GuiButtonImage(10, this.guiLeft + 5, this.height / 2 - 49, 20, 18, 0, 168, 19, GUI_TEXTURES);
        nextButton = new GuiButtonImage(11, this.guiLeft + 151 , this.height / 2 - 49, 20, 18, 21, 168, 19, GUI_TEXTURES);
        this.buttonList.add(prevButton);
        this.buttonList.add(nextButton);
        prevButton.visible = false;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 10)
        {
            this.containerMultiTable.changeTable(--table);
            if(table == 0)this.buttonList.get(0).visible = false;
            this.buttonList.get(1).visible = true;
        }else if (button.id == 11){
            this.containerMultiTable.changeTable(++table);
            if(table == this.te.maxTables - 1)this.buttonList.get(1).visible = false;
            this.buttonList.get(0).visible = true;
        }

        NetworkHandler.sendToServer(new MessageMultiTableChangeTable(table));

        super.actionPerformed(button);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("tile.multitable.name"); //Gets the formatted name for the block breaker from the language file - NOTE ADD "container.block_breaker=Block Breaker" to the language file (without quotes) and then delete this note
        this.mc.fontRenderer.drawString(s, 28, 6, 0x404040); //Draws the block breaker name in the center on the top of the gui
        this.mc.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, this.ySize - 93, 0x404040); //The player's inventory name

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(new ResourceLocation(MultiMachine.MODID, "textures/gui/container/multitable.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
