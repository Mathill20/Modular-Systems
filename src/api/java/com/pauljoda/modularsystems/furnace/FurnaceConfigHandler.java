package com.pauljoda.modularsystems.furnace;

import com.pauljoda.modularsystems.furnace.config.BlockConfig;
import net.minecraft.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;

public final class FurnaceConfigHandler {

    private static Map<Block, BlockConfig> blockConfigMap = new LinkedHashMap<Block, BlockConfig>();

    private FurnaceConfigHandler() {}

    public static void publishBlockConfig(Block block, BlockConfig config) {
        blockConfigMap.put(block, config);
    }

    public static BlockConfig retrieveBlockConfig(Block block, BlockConfig config) {
        return blockConfigMap.get(block);
    }
}
