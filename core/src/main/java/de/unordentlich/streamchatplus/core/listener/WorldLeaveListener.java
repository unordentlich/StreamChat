package de.unordentlich.streamchatplus.core.listener;

import com.google.inject.Inject;
import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.utils.support.DebugTool;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugActionExecuter;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugPriority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.world.WorldLeaveEvent;

public class WorldLeaveListener {

    private final StreamChatPlus addon;

    @Inject
    private WorldLeaveListener(StreamChatPlus addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onWorldLeave(WorldLeaveEvent event) {
        DebugTool.log("WorldLeaveEvent has been triggered", DebugActionExecuter.USER, getClass());
        StreamChatPlus.bot.stop();
        DebugTool.log("TwitchBot was stopped because user left world or server", DebugPriority.WARNING, getClass());
        return;
    }

}
