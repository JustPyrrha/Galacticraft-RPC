package me.joezwet.galacticraft.rpc.discord;

import com.sun.scenario.effect.Offset;
import me.joezwet.galacticraft.rpc.RPC;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.time.OffsetDateTime;

public class DiscordDimSwitcher {

    public static void switchDim(int dim) {

        DiscordRichPresence presence = RPC.instance.discord.getPresence();
        Dimension dimension = RPC.instance.dimensionInfo.getDimensionInfo(dim);

        if(dimension == null) {
            presence.state = "Exploring";
            presence.largeImageText = "";
            presence.largeImageKey = "";
            presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
            RPC.instance.discord.setPresence(presence);
            return;
        }

        presence.state = dimension.getState();
        presence.largeImageKey = dimension.getLargeImageKey();
        presence.largeImageText = dimension.getLargeImageText();

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
                presence.details = "Playing Multiplayer";
                break;
        }

        RPC.instance.discord.setPresence(presence);
    }
}
