package com.teammetallurgy.tradingcard.client.gui;

import com.teammetallurgy.tradingcard.common.utils.LibMisc;
import com.teammetallurgy.tradingcard.common.inventory.ContainerCardAlbum;
import com.teammetallurgy.tradingcard.common.inventory.InventoryCardAlbum;
import com.teammetallurgy.tradingcard.common.inventory.SlotCard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GuiAlbum extends GuiContainer {

    private Slot theSlot;
    private Slot clickedSlot;
    private boolean isRightMouseClick;
    private ItemStack draggedStack;
    private int field_147011_y;
    private int field_147010_z;
    private Slot returningStackDestSlot;
    private long returningStackTime;
    private ItemStack returningStack;
    protected final Set field_147008_s = new HashSet();
    protected boolean field_147007_t;
    private int field_146987_F;
    private int field_146996_I;

    int slotW = 12;
    int slotH = 16;

    public GuiAlbum(EntityPlayer inventoryPlayer, InventoryCardAlbum tileEntity) {
        super(new ContainerCardAlbum(inventoryPlayer, tileEntity));
        this.xSize = 176;
        this.ySize = 222;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float parital, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(LibMisc.MODID + ":" + "textures/gui/CardAlbum.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    private void drawButton(int p_73863_1_, int p_73863_2_) {
        for (int k = 0; k < this.buttonList.size(); ++k) {
            ((GuiButton) this.buttonList.get(k)).drawButton(this.mc, p_73863_1_, p_73863_2_);
        }

        for (int k = 0; k < this.labelList.size(); ++k) {
            ((GuiLabel) this.labelList.get(k)).func_146159_a(this.mc, p_73863_1_, p_73863_2_);
        }

    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawGuiContainerBackgroundLayer(p_73863_3_, p_73863_1_, p_73863_2_);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        drawButton(p_73863_1_, p_73863_2_);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) k, (float) l, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        this.theSlot = null;
        short short1 = 240;
        short short2 = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) short1 / 1.0F, (float) short2 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int k1;

        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1) {

            Slot slot = (Slot) this.inventorySlots.inventorySlots.get(i1);

            func_146977_a(slot);

            if (isMouseOverSlot(slot, p_73863_1_, p_73863_2_) && slot.func_111238_b()) {
                this.theSlot = slot;
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                int j1 = slot.xDisplayPosition;
                k1 = slot.yDisplayPosition;
                GL11.glColorMask(true, true, true, false);
                int ww = 16;
                int hh = 16;
                if (slot instanceof SlotCard) {
                    ww = slotW;
                    hh = slotH;
                }
                this.drawGradientRect(j1, k1, j1 + ww, k1 + hh, -2130706433, -2130706433);
                GL11.glColorMask(true, true, true, true);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }
        }

        //Forge: Force lighting to be disabled as there are some issue where lighting would
        //incorrectly be applied based on items that are in the inventory.
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawGuiContainerForegroundLayer(p_73863_1_, p_73863_2_);
        GL11.glEnable(GL11.GL_LIGHTING);
        InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
        ItemStack itemstack = this.draggedStack == null ? inventoryplayer.getItemStack() : this.draggedStack;

        if (itemstack != null) {
            byte b0 = 8;
            k1 = this.draggedStack == null ? 8 : 16;
            String s = null;

            if (this.draggedStack != null && this.isRightMouseClick) {
                itemstack = itemstack.copy();
                itemstack.stackSize = MathHelper.ceiling_float_int((float) itemstack.stackSize / 2.0F);
            } else if (this.field_147007_t && this.field_147008_s.size() > 1) {
                itemstack = itemstack.copy();
                itemstack.stackSize = this.field_146996_I;

                if (itemstack.stackSize == 0) {
                    s = "" + EnumChatFormatting.YELLOW + "0";
                }
            }

            drawItemStack(itemstack, p_73863_1_ - k - b0, p_73863_2_ - l - k1, s);
        }

        if (this.returningStack != null) {
            float f1 = (float) (Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;

            if (f1 >= 1.0F) {
                f1 = 1.0F;
                this.returningStack = null;
            }

            k1 = this.returningStackDestSlot.xDisplayPosition - this.field_147011_y;
            int j2 = this.returningStackDestSlot.yDisplayPosition - this.field_147010_z;
            int l1 = this.field_147011_y + (int) ((float) k1 * f1);
            int i2 = this.field_147010_z + (int) ((float) j2 * f1);
            drawItemStack(this.returningStack, l1, i2, (String) null);
        }

        GL11.glPopMatrix();

        if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack()) {
            ItemStack itemstack1 = this.theSlot.getStack();
            this.renderToolTip(itemstack1, p_73863_1_, p_73863_2_);
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }

    private void func_146977_a(Slot p_146977_1_) {
        int i = p_146977_1_.xDisplayPosition;
        int j = p_146977_1_.yDisplayPosition;
        ItemStack itemstack = p_146977_1_.getStack();
        boolean flag = false;
        boolean flag1 = p_146977_1_ == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
        ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
        String s = null;

        if (p_146977_1_ == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null) {
            itemstack = itemstack.copy();
            itemstack.stackSize /= 2;
        } else if (this.field_147007_t && this.field_147008_s.contains(p_146977_1_) && itemstack1 != null) {
            if (this.field_147008_s.size() == 1) {
                return;
            }

            if (Container.func_94527_a(p_146977_1_, itemstack1, true) && this.inventorySlots.canDragIntoSlot(p_146977_1_)) {
                itemstack = itemstack1.copy();
                flag = true;
                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack, p_146977_1_.getStack() == null ? 0 : p_146977_1_.getStack().stackSize);

                if (itemstack.stackSize > itemstack.getMaxStackSize()) {
                    s = EnumChatFormatting.YELLOW + "" + itemstack.getMaxStackSize();
                    itemstack.stackSize = itemstack.getMaxStackSize();
                }

                if (itemstack.stackSize > p_146977_1_.getSlotStackLimit()) {
                    s = EnumChatFormatting.YELLOW + "" + p_146977_1_.getSlotStackLimit();
                    itemstack.stackSize = p_146977_1_.getSlotStackLimit();
                }
            } else {
                this.field_147008_s.remove(p_146977_1_);
                this.func_146980_g();
            }
        }
        int ww = 16;
        int hh = 16;
        if (p_146977_1_ instanceof SlotCard) {
            ww = slotW;
            hh = slotH;
            i = i - 2;
        }

        this.zLevel = 100.0F;
        itemRender.zLevel = 100.0F;

        if (itemstack == null) {
            IIcon iicon = p_146977_1_.getBackgroundIconIndex();

            if (iicon != null) {
                GL11.glDisable(GL11.GL_LIGHTING);
                this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
                this.drawTexturedModelRectFromIcon(i, j, iicon, ww, hh);
                GL11.glEnable(GL11.GL_LIGHTING);
                flag1 = true;
            }
        }

        if (!flag1) {
            if (flag) {
                drawRect(i, j, i + ww, j + hh, -2130706433);
            }

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, i, j);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, i, j, s);
        }

        itemRender.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }

    private void func_146980_g() {
        ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();

        if (itemstack != null && this.field_147007_t) {
            this.field_146996_I = itemstack.stackSize;
            ItemStack itemstack1;
            int i;

            for (Iterator iterator = this.field_147008_s.iterator(); iterator.hasNext(); this.field_146996_I -= itemstack1.stackSize - i) {
                Slot slot = (Slot) iterator.next();
                itemstack1 = itemstack.copy();
                i = slot.getStack() == null ? 0 : slot.getStack().stackSize;
                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack1, i);

                if (itemstack1.stackSize > itemstack1.getMaxStackSize()) {
                    itemstack1.stackSize = itemstack1.getMaxStackSize();
                }

                if (itemstack1.stackSize > slot.getSlotStackLimit()) {
                    itemstack1.stackSize = slot.getSlotStackLimit();
                }
            }
        }
    }

    private boolean isMouseOverSlot(Slot slot, int p_146981_2_, int p_146981_3_) {
        int ww = 16;
        int hh = 16;
        if (slot instanceof SlotCard) {
            ww = slotW;
            hh = slotH;
        }
        return this.func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, ww, hh, p_146981_2_, p_146981_3_);
    }

    private void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_) {
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if (p_146982_1_ != null) font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
        if (font == null) font = fontRendererObj;
        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_);
        itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_ - (this.draggedStack == null ? 0 : 8), p_146982_4_);
        this.zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
    }
}
