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

import me.joezwet.galacticraft.rpc.RPC;
import micdoodle8.mods.galacticraft.core.dimension.SpaceRaceManager;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

import java.util.List;
import java.util.UUID;

public class DiscordTeamUpdater {

    public static void updateTeam(String name) {
        if (SpaceRaceManager.getSpaceRaceFromPlayer(name) != null) {
            if(Minecraft.getMinecraft().getCurrentServerData() == null) {
                RPC.instance.discord.getPresence().partySize = 1;
                RPC.instance.discord.getPresence().partyMax = 1;
                RPC.instance.discord.getPresence().partyId = UUID.randomUUID().toString();
                RPC.instance.discord.setPresence(RPC.instance.discord.getPresence());
            } else {
                List<String> players = SpaceRaceManager.getSpaceRaceFromPlayer(name).getPlayerNames();

                int partySize = 0;
                for (String player : players) {
                    if (Minecraft.getMinecraft().getCurrentServerData().playerList.contains(player)) {
                        partySize++;
                    }
                }

                RPC.instance.discord.getPresence().partySize = partySize;
                RPC.instance.discord.getPresence().partyId = UUID.randomUUID().toString();
                RPC.instance.discord.getPresence().partyMax = players.size();
                RPC.instance.discord.setPresence(RPC.instance.discord.getPresence());
            }
        }
    }
}
