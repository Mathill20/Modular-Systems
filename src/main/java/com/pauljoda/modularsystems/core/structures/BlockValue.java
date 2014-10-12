package com.pauljoda.modularsystems.core.structures;

import net.minecraft.block.Block;
import org.w3c.dom.Element;

public class BlockValue
{
    private String unlocalizedName;
    private double speedValue;
    private double efficiencyValue;

    public BlockValue(String name, double speed, double efficiency)
    {
        unlocalizedName = name;
        speedValue = speed;
        efficiencyValue = efficiency;
    }

    public BlockValue(Block block, double speed, double efficiency)
    {
        this(block.getUnlocalizedName(), speed, efficiency);
    }

    public double getSpeedValue()
    {
        return speedValue;
    }

    public double getEfficiencyValue()
    {
        return efficiencyValue;
    }

    public boolean compareBlock(Block block)
    {
        return block.getUnlocalizedName().equals(unlocalizedName);
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public static BlockValue fromConfigElement(Element element) {
        return BlockValue.fromConfigValues(
            element.getAttribute("unlocalizedName"),
            element.getElementsByTagName("speedValue").item(0).getTextContent(),
            element.getElementsByTagName("efficiencyValue").item(0).getTextContent()
        );
    }

    public static BlockValue fromConfigValues(String name, String speed, String efficiency) {
        return new BlockValue(
            name,
            Double.parseDouble(speed),
            Double.parseDouble(efficiency)
        );
    }
}
