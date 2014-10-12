package com.pauljoda.modularsystems.furnace.config;

public interface Calculation {

    double calculate(int blockCount);

    public static class LinearEfficiency implements Calculation {

        private int perBlock;

        public LinearEfficiency(int perBlock) {
            this.perBlock = perBlock;
        }

        public double calculate(int blockCount) {
            return Math.max(0, blockCount * perBlock);
        }
    }

    public static class ConstantEfficiency implements Calculation {

        private final int efficiency;

        public ConstantEfficiency(int efficiency) {
            this.efficiency = efficiency;
        }

        @Override
        public double calculate(int blockCount) {
            return blockCount > 0 ? efficiency : 0;
        }
    }

    public static class LogEfficiency implements Calculation {

        private final int scalar;

        public LogEfficiency(int scalar) {
            this.scalar = scalar;
        }

        @Override
        public double calculate(int blockCount) {
            return scalar * Math.log(blockCount);
        }
    }

    public static class ParabolicEfficiency implements Calculation {

        private final int coefficient;

        public ParabolicEfficiency(int coefficient) {
            this.coefficient = coefficient;
        }

        @Override
        public double calculate(int blockCount) {
            return coefficient * blockCount * blockCount;
        }
    }
}
