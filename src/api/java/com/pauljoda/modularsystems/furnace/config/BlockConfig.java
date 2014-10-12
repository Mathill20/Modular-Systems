package com.pauljoda.modularsystems.furnace.config;

public class BlockConfig {

    private final Calculation efficiency;
    private final Calculation speed;

    public BlockConfig(Calculation efficiency, Calculation speed) {
        this.efficiency = efficiency;
        this.speed = speed;
    }

    public double efficiency(int blockCount) {
        return efficiency(blockCount);
    }

    public double speed(int blockCount) {
        return speed(blockCount);
    }
}
