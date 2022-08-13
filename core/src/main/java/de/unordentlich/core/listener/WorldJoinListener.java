package de.unordentlich.core.listener;

import static de.unordentlich.core.StreamChatPlus.bot;

import com.google.inject.Inject;
import de.unordentlich.core.StreamChatPlus;
import de.unordentlich.core.utils.TwitchBot;
import de.unordentlich.core.utils.support.DebugTool;
import de.unordentlich.core.utils.support.objects.DebugActionExecuter;
import de.unordentlich.core.utils.support.objects.DebugPriority;
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
    bot = new TwitchBot(addon);
    DebugTool.log("bot was redefined by 'new TwitchBot(addon)'", getClass());
    bot.start();
    DebugTool.log("TwitchBot was started because user joined world or server", DebugPriority.WARNING, getClass());
    return;
  }
}
