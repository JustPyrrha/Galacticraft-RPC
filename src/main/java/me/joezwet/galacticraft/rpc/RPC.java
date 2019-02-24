package me.joezwet.galacticraft.rpc;

import me.joezwet.galacticraft.rpc.discord.DimensionInfo;
import me.joezwet.galacticraft.rpc.proxy.CommonProxy;
import me.joezwet.galacticraft.rpc.util.Strings;
import me.joezwet.galacticraft.rpc.discord.Discord;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Strings.MODID, name = Strings.NAME, version = Strings.VERSION, updateJSON = Strings.UPDATEJSON, dependencies = "required-after:galacticraftplanets;")
public class RPC {

    @Mod.Instance
    public static RPC instance;

    @SidedProxy(clientSide = "me.joezwet.galacticraft.rpc.proxy.ClientProxy", serverSide = "me.joezwet.galacticraft.rpc.proxy.CommonProxy")
    public static CommonProxy proxy;

    public DimensionInfo dimensionInfo;

    public Discord discord;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        instance = this;
        discord = new Discord(new DiscordRichPresence());
        dimensionInfo = new DimensionInfo();
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
