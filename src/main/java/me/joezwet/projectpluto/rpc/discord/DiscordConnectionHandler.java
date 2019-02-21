package me.joezwet.projectpluto.rpc.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.OffsetDateTime;


public class DiscordConnectionHandler {

    private String appId = "540788137266380811";
    private Logger logger;
    private Thread callbackThread;


    public DiscordConnectionHandler() {
        logger = LogManager.getLogger("Discord");
        init();
    }

    private void init() {
        logger.info("Initializing RPC");

        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
                .setReadyEventHandler((discordUser -> {
                    String avatar_url = "https://discordapp.com/api/avatars/";
                    if(discordUser.avatar == null) {
                        avatar_url += (Integer.parseInt(discordUser.discriminator) % 5) + ".png";
                    } else if(discordUser.avatar.startsWith("a_")) {
                        avatar_url += discordUser.userId + "/" + discordUser.avatar + ".gif";
                    } else {
                        avatar_url += discordUser.userId + "/" + discordUser.avatar + ".png";
                    }
                    LogManager.getLogger("Discord").info("RPC Ready. Registered user {}#{} (ID: {}, avatar: {})", discordUser.username, discordUser.discriminator, discordUser.userId, avatar_url);
                })).build();

        DiscordRPC.discordInitialize(appId, handlers, true);

        DiscordRichPresence richPresence = new DiscordRichPresence.Builder("Loading Modpack...")
                .setBigImage("planet_pluto", "")
                .setStartTimestamps(OffsetDateTime.now().toEpochSecond())
                .build();
        DiscordRPC.discordUpdatePresence(richPresence);

        startThread();
    }

    private void startThread() {
        if(callbackThread != null) {
            LogManager.getLogger("Discord").info("Starting callback thread.");
            callbackThread = new Thread(() -> {
                while (true) {
                    try {
                        DiscordRPC.discordRunCallbacks();
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Discord-Callback");
        }
    }
}
