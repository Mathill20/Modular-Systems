package com.pauljoda.modularsystems.core.helper;

import com.pauljoda.modularsystems.core.structures.BlockValue;
import com.pauljoda.modularsystems.core.structures.MaterialValue;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlockValueHelper
{
    private static Map<String, BlockValue> blockValuesMap = new LinkedHashMap<String, BlockValue>();
    private static Map<Material, MaterialValue> materialValuesMap = new LinkedHashMap<Material, MaterialValue>();

    private BlockValueHelper() {}

    public static void init() throws ParserConfigurationException, TransformerException, IOException, SAXException
    {
        File valuesFile = new File("config/ModularSystems/blockValues.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        if(!valuesFile.exists())
        {
            generateDefaultValues();
            return;
        }

        Document doc = dBuilder.parse(valuesFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("block");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;
                BlockValue block = BlockValue.fromConfigElement(eElement);
                blockValuesMap.put(block.getUnlocalizedName(), block);
            }
        }

        nList = doc.getElementsByTagName("material");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;
                MaterialValue m = MaterialValue.fromConfigElement(eElement);
                materialValuesMap.put(m.getMaterial(), m);
            }
        }
    }

    private static void generateDefaultValues() throws TransformerException, ParserConfigurationException, IOException, SAXException
    {
        URL original = BlockValueHelper.class.getClassLoader().getResource("blockValues.xml");
        File destination = new File("config/ModularSystems/blockValues.xml");
        boolean flag = true;
        try
        {
            FileUtils.copyURLToFile(original, destination);
        } catch (IOException e)
        {
            flag = false;
            LogHelper.error(e.getMessage());
        }
        if(flag) init();
    }

    public static BlockValue getBlockValueForBlock(Block block) {
        return blockValuesMap.get(block.getUnlocalizedName());
    }

    public static MaterialValue getMaterialValueForBlock(Block block) {
        return materialValuesMap.get(block.getMaterial());
    }
}
