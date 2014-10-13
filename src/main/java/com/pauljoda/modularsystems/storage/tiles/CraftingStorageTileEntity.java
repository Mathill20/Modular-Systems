package com.pauljoda.modularsystems.storage.tiles;

import com.pauljoda.modularsystems.core.lib.Reference;
import com.pauljoda.modularsystems.storage.StorageTileEntity;
import net.minecraft.block.Block;

public class CraftingStorageTileEntity extends StorageTileEntity {
    @Override
    public void setUpgradeToCore(Block block) {
        getCore().hasCraftingUpgrade = true;
    }

    @Override
    protected void doCoreInvalidation() {

        if(getCore() != null && this.tileType == Reference.CRAFTING_STORAGE_EXPANSION)
        {
            TileEntityStorageCore core = getCore();
            getCore().hasCraftingUpgrade = false;
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }

    @Override
    protected void doCoreValidation() {

        if(getCore() != null && this.tileType == Reference.CRAFTING_STORAGE_EXPANSION)
        {
            TileEntityStorageCore core = getCore();
            getCore().hasCraftingUpgrade = true;
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }
}
