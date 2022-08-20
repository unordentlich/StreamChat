package de.unordentlich.streamchatplus.core.listener;

import com.google.inject.Inject;
import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.utils.TwitchBot;
import de.unordentlich.streamchatplus.core.utils.support.DebugTool;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugActionExecuter;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugPriority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.world.WorldEnterEvent;

public class WorldJoinListener {

    private final StreamChatPlus addon;

    @Inject
    private WorldJoinListener(StreamChatPlus addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onWorldEnter(WorldEnterEvent event) {
        DebugTool.log("WorldJoinEvent has been triggered", DebugActionExecuter.USER, getClass());
        StreamChatPlus.bot = new TwitchBot(addon);
        DebugTool.log("bot was redefined by 'new TwitchBot(addon)'", getClass());
        StreamChatPlus.bot.start();
        DebugTool.log("TwitchBot was started because user joined world or server", DebugPriority.WARNING, getClass());
        return;
    }
}
