package me.joezwet.projectpluto.rpc;

import me.joezwet.projectpluto.rpc.discord.Discord;
import me.joezwet.projectpluto.rpc.proxy.CommonProxy;
import me.joezwet.projectpluto.rpc.util.Strings;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Strings.MODID, name = Strings.NAME, version = Strings.VERSION, updateJSON = Strings.UPDATEJSON)
public class RPC {

    @Mod.Instance
    public static RPC instance;

    @SidedProxy(clientSide = "me.joezwet.projectpluto.rpc.proxy.ClientProxy", serverSide = "me.joezwet.projectpluto.rpc.proxy.CommonProxy")
    public static CommonProxy proxy;

    public Discord discord;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        instance = this;
        discord = new Discord(new DiscordRichPresence());
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
