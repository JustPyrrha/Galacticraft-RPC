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

package me.joezwet.galacticraft.rpc.discord;

import com.google.gson.annotations.Since;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.joezwet.galacticraft.rpc.RPC;
import me.joezwet.galacticraft.rpc.util.FieldUtils;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DiscordEventHandler {

    private Logger logger = LogManager.getLogger("Galacticraft RPC");
    private boolean firstInfoSent = false;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiScreenDetect(GuiScreenEvent.InitGuiEvent.Pre event) {
        if (event.getGui() instanceof GuiMainMenu) {
            logger.info("Detected main menu, updating presence");
            DiscordRPC.discordUpdatePresence(new DiscordRichPresence.Builder("On the Main Menu")
                    .setBigImage("planet_pluto", "")
                    .build()
            );
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        try {
            if(!firstInfoSent) {
                DiscordDimSwitcher.switchDim(Minecraft.getMinecraft().player.world.provider.getDimension());
                firstInfoSent = true;
            }
        } catch (NoSuchMethodError | NoSuchFieldError  | NullPointerException error) {
            firstInfoSent = false;
            // Yeet that error into the sunset, minecraft is still loading
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerLoggedout(PlayerEvent.PlayerLoggedOutEvent e) {
        firstInfoSent = false;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent()
    public void onServerJoin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (!event.isLocal()) {
            DiscordDimSwitcher.switchDim(1);
        } else {
            DiscordDimSwitcher.switchServer(0);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerSwitchDim(PlayerEvent.PlayerChangedDimensionEvent event) {
        DiscordDimSwitcher.switchDim(event.toDim);
    }
}