package com.teammetallurgy.tradingcard.common.items;

import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.cards.CardSet;
import com.teammetallurgy.tradingcard.common.lib.LibMisc;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCards extends Item {

    private HashMap<Integer, String> names = new HashMap<Integer, String>();
    private HashMap<Integer, String> textures = new HashMap<Integer, String>();
    private HashMap<Integer, IIcon> icons = new HashMap<Integer, IIcon>();
    private HashMap<Integer, CardSet.Cards> cards = new HashMap<Integer, CardSet.Cards>();


    public ItemCards(String postfix) {
        this.setTextureName(LibMisc.MODID + ":BlankCommonCard");
        this.setUnlocalizedName("item." + postfix + ".cards");
        this.setCreativeTab(TradingCard.instance.creativeTabItems);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public void addSubItem(int meta, String name, String texture, CardSet.Cards card) {
        this.names.put(meta, name);
        this.textures.put(meta, texture);
        this.cards.put(meta, card);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemstack) {
        return names.get(itemstack.getItemDamage());
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (this.icons.containsKey(meta)) {
            return this.icons.get(meta);
        } else {
            return this.itemIcon;
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (Map.Entry<Integer, String> name : this.names.entrySet()) {
            list.add(new ItemStack(item, 1, name.getKey()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage();

        if (this.names.get(meta) != null) {
            String unlocalizedName = this.names.get(meta).replace(" ", ".").toLowerCase();

            String prefix = "item." + LibMisc.MODID.toLowerCase() + ".";
            return prefix + unlocalizedName;
        } else {
            return this.getUnlocalizedName();
        }
    }

    @Override
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(this.getIconString());

        // Sub-Items textures
        for (Map.Entry<Integer, String> texture : this.textures.entrySet()) {
            int meta = texture.getKey();
            String textureName = texture.getValue();

            IIcon icon = register.registerIcon(LibMisc.MODID + ":" + textureName);

            this.icons.put(meta, icon);
        }
    }

}
