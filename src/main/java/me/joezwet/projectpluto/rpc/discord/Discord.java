package me.joezwet.projectpluto.rpc.discord;

import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class Discord {

    private DiscordRichPresence presence;

    public Discord(DiscordRichPresence presence) {
        this.presence = presence;
    }

    public void setPresence(DiscordRichPresence presence) {
        this.presence = presence;
        DiscordRPC.discordUpdatePresence(presence);
    }

    public DiscordRichPresence getPresence() {
        return presence;
    }
}
