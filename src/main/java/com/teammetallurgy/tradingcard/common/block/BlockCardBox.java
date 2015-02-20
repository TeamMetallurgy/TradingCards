package com.teammetallurgy.tradingcard.common.block;


import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCardBox extends BlockContainer {
    protected BlockCardBox() {
        super(Material.wood);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
