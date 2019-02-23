package me.joezwet.galacticraft.rpc.discord;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DiscordEventHandler {

    private Logger logger = LogManager.getLogger("Galacticraft RPC");
    private int serverType = 0;

    @SubscribeEvent
    public void guiScreenDetect(GuiScreenEvent.InitGuiEvent.Pre event) {
        if(event.getGui() instanceof GuiMainMenu) {
            logger.info("Detected main menu, updating presence");
            DiscordRPC.discordUpdatePresence(new DiscordRichPresence.Builder("On the Main Menu")
                    .setBigImage("planet_pluto", "")
                    .build()
            );
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onServerJoin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if(!event.isLocal()) {
            if(Minecraft.getMinecraft().getCurrentServerData().serverIP.equalsIgnoreCase("OFFICIAL SERVER")) {
                DiscordDimSwitcher.switchDim(2);
            } else {
                DiscordDimSwitcher.switchDim(1);
            }
        } else {
            DiscordDimSwitcher.switchServer(0);
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        DiscordDimSwitcher.switchDim(event.player.dimension);
        if(ForgeVersion.getResult(Loader.instance().activeModContainer()).status == ForgeVersion.Status.OUTDATED) {
            event.player.addChatMessage(new TextComponentString(ChatFormatting.BLUE + "[Discord] New version available: " + ForgeVersion.getResult(Loader.instance().activeModContainer()).target.toString()));
        }
    }

    @SubscribeEvent
    public void onPlayerSwitchDim(PlayerEvent.PlayerChangedDimensionEvent event) {
        DiscordDimSwitcher.switchDim(event.toDim);
    }
}
