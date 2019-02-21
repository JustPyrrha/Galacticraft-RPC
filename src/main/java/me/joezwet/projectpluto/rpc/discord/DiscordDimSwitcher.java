package me.joezwet.projectpluto.rpc.discord;

import me.joezwet.projectpluto.rpc.RPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.time.OffsetDateTime;

public class DiscordDimSwitcher {

    public static void switchDim(int dim) {

        DiscordRichPresence presence = RPC.instance.discord.getPresence();

        switch(dim) {
            case 1:
                presence.state = "Exploring the End";
                presence.largeImageKey = "planet_earth";
                presence.largeImageText = "Earth";
                break;
            case 0:
                presence.state = "Exploring Earth";
                presence.largeImageKey = "planet_earth";
                presence.largeImageText = "Earth";
                break;
            case -1:
                presence.state = "Exploring the Nether";
                presence.largeImageKey = "planet_earth";
                presence.largeImageText = "Earth";
                break;
            case -27:
                presence.state = "Orbiting Earth in a Space Station";
                presence.largeImageKey = "planet_earth";
                presence.largeImageText = "Earth";
                break;
            case -28:
                presence.state = "Exploring the Moon";
                presence.largeImageKey = "moon_moon";
                presence.largeImageText = "The Moon";
                break;
            case -29:
                presence.state = "Exploring Mars";
                presence.largeImageKey = "planet_mars";
                presence.largeImageText = "Mars";
                break;
            case -30:
                presence.state = "Dodging Asteroids";
                presence.largeImageKey = "planet_earth";
                presence.largeImageText = "Earth";
                break;
            case -31:
                presence.state = "Exploring Venus";
                presence.largeImageKey = "planet_venus";
                presence.largeImageText = "Venus";
                break;
        }

        presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
        RPC.instance.discord.setPresence(presence);
    }

    public static void switchServer(int server) {

        DiscordRichPresence presence = RPC.instance.discord.getPresence();

        switch (server) {
            case 0:
                presence.details = "Playing Singleplayer";
                break;
            case 1:
                presence.details = "Playing on a server";
                break;
            case 2:
                presence.details = "Playing on the Official server";
                break;
        }


        RPC.instance.discord.setPresence(presence);
    }
}
