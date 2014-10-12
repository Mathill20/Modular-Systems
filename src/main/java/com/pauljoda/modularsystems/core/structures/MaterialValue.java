package com.pauljoda.modularsystems.core.structures;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.w3c.dom.Element;

public class MaterialValue
{
    private Material material;
    private double speedValue;
    private double efficiencyValue;

    public MaterialValue(Material materialName, double speed, double eff)
    {
        material = materialName;
        speedValue = speed;
        efficiencyValue = eff;
    }

    public double getSpeedValue()
    {
        return speedValue;
    }

    public double getEfficiencyValue()
    {
        return efficiencyValue;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean compareBlock(Block block)
    {
        return block.getMaterial() == material;
    }

    public static Material getMaterialFromString(String mat)
    {
        if(mat.equals("rock"))
            return Material.rock;
        else if(mat.equals("iron"))
            return Material.iron;
        return null;
    }

    public static MaterialValue fromConfigElement(Element element) {

        return MaterialValue.fromConfigValues(
                element.getAttribute("name"),
                element.getElementsByTagName("speedValue").item(0).getTextContent(),
                element.getElementsByTagName("efficiencyValue").item(0).getTextContent()
        );
    }

    public static MaterialValue fromConfigValues(String matName, String speed, String efficiency) {
        return new MaterialValue(
                getMaterialFromString(matName),
                Double.parseDouble(speed),
                Double.parseDouble(efficiency)
        );
    }
}
