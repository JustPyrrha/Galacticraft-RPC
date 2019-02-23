package me.joezwet.galacticraft.rpc.proxy;

import me.joezwet.galacticraft.rpc.discord.DiscordConnectionHandler;
import me.joezwet.galacticraft.rpc.discord.DiscordEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
        System.out.println("Client PostInit");
    }
}
