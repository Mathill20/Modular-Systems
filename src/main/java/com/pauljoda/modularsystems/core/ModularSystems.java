package com.pauljoda.modularsystems.core;

import com.pauljoda.modularsystems.api.nei.INEICallback;
import com.pauljoda.modularsystems.core.fakeplayer.FakePlayerPool;
import com.pauljoda.modularsystems.core.helper.BlockValueHelper;
import com.pauljoda.modularsystems.core.helper.ConfigHelper;
import com.pauljoda.modularsystems.core.helper.LogHelper;
import com.pauljoda.modularsystems.core.helper.VersionHelper;
import com.pauljoda.modularsystems.core.lib.Reference;
import com.pauljoda.modularsystems.core.managers.ModuleManager;
import com.pauljoda.modularsystems.core.network.PacketPipeline;
import com.pauljoda.modularsystems.core.proxy.ClientProxy;
import com.pauljoda.modularsystems.core.proxy.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

@Mod(name = Reference.MOD_NAME, modid = Reference.MOD_ID, version = Reference.VERSION)

public class ModularSystems {

    @Instance(Reference.MOD_ID)
    public static ModularSystems instance;

    @SidedProxy( clientSide="com.pauljoda.modularsystems.core.proxy.ClientProxy", serverSide="com.pauljoda.modularsystems.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static INEICallback nei;

    //Creates the Creative Tab
    public static CreativeTabs tabModularSystems = new CreativeTabs("tabModularSystems"){

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.furnace);

        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){

        //Set up config
        ConfigHelper.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator  + "ModularSystems/ModularSystems.cfg"));
        FMLCommonHandler.instance().bus().register(new ConfigHelper());

        //Load block values for speed/efficiency
        try
        {
            BlockValueHelper.init();
        } catch (ParserConfigurationException e)
        {
            LogHelper.error(e.getMessage());
        } catch (TransformerException e)
        {
            LogHelper.error(e.getMessage());
        } catch (SAXException e)
        {
            LogHelper.error(e.getMessage());
        } catch (IOException e)
        {
            LogHelper.error(e.getMessage());
        }

        //Version Check
        VersionHelper.execute();

        if(ConfigHelper.enableFurnace)
            ModuleManager.enableFurnaceModule();

        if(ConfigHelper.enableStorage)
            ModuleManager.enableStorageModule();

        if(ConfigHelper.enableEnchanting)
            ModuleManager.enableEnchantingModule();

        if(ConfigHelper.enableOreProcessing)
            ModuleManager.enableOreProcessingModule();

        MinecraftForge.EVENT_BUS.register(FakePlayerPool.instance);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        //Open Network Pipeline
        packetPipeline.initalise();

        //Register Tiles
        proxy.registerTileEntities();

        //Set Up Custom Renderers
        ClientProxy.setCustomRenderers();

        //Setup GUIs
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //Close Network
        packetPipeline.postInitialise();
    }
}
