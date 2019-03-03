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

import com.sun.scenario.effect.Offset;
import me.joezwet.galacticraft.rpc.RPC;
import me.joezwet.galacticraft.rpc.config.Config;
import me.joezwet.galacticraft.rpc.config.ConfigHandler;
import me.joezwet.galacticraft.rpc.util.Utils;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.core.tile.TileEntitySpaceStationBase;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.time.OffsetDateTime;

public class DiscordDimSwitcher {

    public static void switchDim(int dim) {
        DiscordRichPresence presence = RPC.instance.discord.getPresence();
        Config.Messages messages = RPC.instance.config.getConfig().getMessages();
        Config.General general = RPC.instance.config.getConfig().getGeneral();

        if(general.shouldShowPlanet()) {
            presence.largeImageText = "Earth";
            presence.largeImageKey = "planet_earth";
            if(dim  == -1) {
                presence.state = Utils.replacePlaceholders(messages.getOnPlanet(), "the Nether");
                presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
                RPC.instance.discord.setPresence(presence);
                return;
            } else if (dim == 0) {
                presence.state = Utils.replacePlaceholders(messages.getOnPlanet(), "Earth");
                presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
                RPC.instance.discord.setPresence(presence);
                return;
            } else if (dim == 1) {
                presence.state = Utils.replacePlaceholders(messages.getOnPlanet(), "The End");
                presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
                RPC.instance.discord.setPresence(presence);
                return;
            }
        }

        CelestialBody celestialBody = GalaxyRegistry.getCelestialBodyFromDimensionID(dim);

        String name = "";

        if(celestialBody instanceof  Planet || celestialBody instanceof  Moon) {
            name = celestialBody.getName();
        } else if(celestialBody instanceof Satellite) {
            name = ((Satellite) celestialBody).getParentPlanet().getName();
        }
        if(name.substring(name.length()-2).equals("EP")) {
            name = name.substring(0, name.length()-2);
        }
        String cName = name.substring(0, 1).toUpperCase() + name.substring(1);


        if (celestialBody instanceof Planet) {
            if (!general.shouldShowPlanet()) return;

            if (cName.equals("Asteroids")) cName = "the Asteroids";
            presence.state = Utils.replacePlaceholders(messages.getOnPlanet(), cName);
            presence.largeImageKey = "planet_" + name;
            presence.largeImageText = cName;

            presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
            RPC.instance.discord.setPresence(presence);
        } else if (celestialBody instanceof Moon) {
            if (!general.shouldShowMoon()) return;

            if (cName.equals("Moon")) cName = "the Moon";
            presence.state = Utils.replacePlaceholders(messages.getOnMoon(), cName);
            presence.largeImageKey = "moon_" + name;
            presence.largeImageText = cName;

            presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
            RPC.instance.discord.setPresence(presence);
        } else {
            if (!general.shouldShowStation()) return;

            presence.state = Utils.replacePlaceholders(messages.getOnStation(), cName);
            presence.largeImageKey = "planet_" + name;
            presence.largeImageText = cName;

            presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
            RPC.instance.discord.setPresence(presence);
        }
    }

    public static void switchServer(int server) {

        DiscordRichPresence presence = RPC.instance.discord.getPresence();

        switch (server) {
            case 0:
                presence.details = "Playing Singleplayer";
                break;
            case 1:
                presence.details = "Playing Multiplayer";
                break;
        }

        RPC.instance.discord.setPresence(presence);
    }
}
