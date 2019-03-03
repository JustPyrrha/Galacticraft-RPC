/*
 * Copyright (c) 2019 Joe van der Zwet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.joezwet.galacticraft.rpc;

import me.joezwet.galacticraft.rpc.config.ConfigHandler;
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
    public ConfigHandler config;
    public String currentPlanetName;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        instance = this;
        discord = new Discord(new DiscordRichPresence());
        dimensionInfo = new DimensionInfo();
        config = new ConfigHandler();
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

    public String getCurrentPlanetName() {
        return currentPlanetName;
    }

    public void setCurrentPlanetName(String currentPlanetName) {
        this.currentPlanetName = currentPlanetName;
    }
}
