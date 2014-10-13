package com.pauljoda.modularsystems.storage.tiles;

import com.pauljoda.modularsystems.core.lib.Reference;
import com.pauljoda.modularsystems.storage.StorageTileEntity;
import net.minecraft.block.Block;

public class ArmorStorageTileEntity extends StorageTileEntity {
    @Override
    public void setUpgradeToCore(Block block) {
        getCore().hasArmorUpgrade = true;
    }

    @Override
    protected void doCoreInvalidation() {

        if(getCore() != null && this.tileType == Reference.ARMOR_STORAGE_EXPANSION) {
            TileEntityStorageCore core = getCore();
            getCore().hasArmorUpgrade = false;
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }

    @Override
    protected void doCoreValidation() {

        if(getCore() != null && this.tileType == Reference.ARMOR_STORAGE_EXPANSION)
        {
            TileEntityStorageCore core = getCore();
            getCore().hasArmorUpgrade = true;
            worldObj.markBlockForUpdate(core.xCoord, core.yCoord, core.zCoord);
        }
    }
}
