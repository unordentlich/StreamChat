package de.unordentlich.core.listener;

import static de.unordentlich.core.StreamChatPlus.bot;

import com.google.inject.Inject;
import de.unordentlich.core.StreamChatPlus;
import de.unordentlich.core.utils.support.DebugTool;
import de.unordentlich.core.utils.support.objects.DebugActionExecuter;
import de.unordentlich.core.utils.support.objects.DebugPriority;
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
    bot.stop();
    DebugTool.log("TwitchBot was stopped because user left world or server", DebugPriority.WARNING, getClass());
    return;
  }

}
