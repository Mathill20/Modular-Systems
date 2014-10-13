package com.pauljoda.modularsystems.storage.tiles;

import com.pauljoda.modularsystems.core.lib.Reference;
import com.pauljoda.modularsystems.storage.StorageTileEntity;
import net.minecraft.block.Block;

public class SortingStorageTileEntity extends StorageTileEntity {
    @Override
    public void setUpgradeToCore(Block block) {
        getCore().hasSortingUpgrade = true;
    }

    @Override
    protected void doCoreInvalidation() {

        if(getCore() != null && this.tileType == Reference.SORTING_STORAGE_EXPANSION)
        {
            TileEntityStorageCore core = getCore();
            getCore().hasSortingUpgrade = false;
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }

    @Override
    protected void doCoreValidation() {

        if(getCore() != null && this.tileType == Reference.SORTING_STORAGE_EXPANSION)
        {
            TileEntityStorageCore core = getCore();
            getCore().hasSortingUpgrade = true;
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }
}
