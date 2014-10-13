package com.pauljoda.modularsystems.storage.tiles;

import com.pauljoda.modularsystems.core.lib.Reference;
import com.pauljoda.modularsystems.storage.StorageTileEntity;
import net.minecraft.block.Block;

public class ExpansionStorageTileEntity extends StorageTileEntity{
    @Override
    public void setUpgradeToCore(Block block) {
        getCore().setInventoryRows(getCore().inventoryRows + 1);
    }

    @Override
    protected void doCoreInvalidation() {
        if(getCore() != null && this.tileType == Reference.STORAGE_EXPANSION) {
            TileEntityStorageCore core = getCore();
            core.dropItems(xCoord, yCoord, zCoord);
            getCore().setInventoryRows(core.inventoryRows - 1);
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }

    @Override
    protected void doCoreValidation() {

        if(getCore() != null && this.tileType == Reference.STORAGE_EXPANSION)
        {
            TileEntityStorageCore core = getCore();
            core.dropItems(xCoord, yCoord, zCoord);
            getCore().setInventoryRows(core.inventoryRows + 1);
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }
}
