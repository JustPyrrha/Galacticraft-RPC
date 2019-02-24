package me.joezwet.galacticraft.rpc.proxy;

import me.joezwet.galacticraft.rpc.RPC;
import me.joezwet.galacticraft.rpc.discord.Dimension;
import me.joezwet.galacticraft.rpc.discord.DimensionInfo;
import me.joezwet.galacticraft.rpc.discord.DiscordConnectionHandler;
import me.joezwet.galacticraft.rpc.discord.DiscordEventHandler;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DiscordConnectionHandler connectionHandler = new DiscordConnectionHandler();
        MinecraftForge.EVENT_BUS.register(new DiscordEventHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        LogManager.getLogger("Galacticraft RPC").info("Checking planet ids..");
        GalaxyRegistry.getRegisteredPlanets().forEach((s,p) -> {
            LogManager.getLogger("Galacticraft RPC").info("n:" + s + " d:" + p.getDimensionID() + " i:" + p.getID());
            if (p.getDimensionID() != -1 && p.getDimensionID() != 0) {
                String capName = s.substring(0, 1).toUpperCase() + s.substring(1);
                RPC.instance.dimensionInfo.registerDimnesionInfo(p.getDimensionID(), new Dimension("Exploring " + capName, "planet_" + s, capName));
            }
        });

        LogManager.getLogger("Galacticraft RPC").info("Checking moon ids..");
        GalaxyRegistry.getRegisteredMoons().forEach((s,m) -> {
            LogManager.getLogger("Galacticraft RPC").info("n:" + s + " d:" + m.getDimensionID() + " i:" + m.getID());
            if (m.getDimensionID() != -1 && m.getDimensionID() != 0) {
                String capName = s.substring(0, 1).toUpperCase() + s.substring(1);
                if(capName.equalsIgnoreCase("moon")) {
                    RPC.instance.dimensionInfo.registerDimnesionInfo(m.getDimensionID(), new Dimension("Exploring the " + capName, "moon_" + s, capName));
                } else {
                    RPC.instance.dimensionInfo.registerDimnesionInfo(m.getDimensionID(), new Dimension("Exploring " + capName, "moon_" + s, capName));
                }

            }
        });

        LogManager.getLogger("Galacticraft RPC").info("Checking space station ids..");
        GalaxyRegistry.getRegisteredSatellites().forEach((s,l) -> {
            LogManager.getLogger("Galacticraft RPC").info("n:" + s + " d:" + l.getDimensionID() + " i:" + l.getID());
            String capName = l.getParentPlanet().getName().substring(0,1).toUpperCase() + l.getParentPlanet().getName().substring(1);
            RPC.instance.dimensionInfo.registerDimnesionInfo(l.getDimensionID(), new Dimension("Orbiting " + capName, "planet_" + l.getParentPlanet().getName(), capName));
        });

        RPC.instance.dimensionInfo.registerDimnesionInfo(-1, new Dimension("Exploring the Nether", "planet_earth", "Earth"));
        RPC.instance.dimensionInfo.registerDimnesionInfo(0, new Dimension("Exploring Earth", "planet_earth", "Earth"));
        RPC.instance.dimensionInfo.registerDimnesionInfo(1, new Dimension("Exploring The End", "planet_earth", "Earth"));
    }
}
