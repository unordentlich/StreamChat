package de.unordentlich.streamchatplus.core.utils.autobroadcast;

import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.subconfigurations.AutoBroadcastSubSettings;
import de.unordentlich.streamchatplus.core.utils.autobroadcast.objects.AutoBroadcast;
import de.unordentlich.streamchatplus.core.utils.support.DebugTool;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugPriority;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class AutoBroadcastManager {

  private final StreamChatPlus addon;
  private AutoBroadcastSubSettings config;

  public ArrayList<AutoBroadcast> autoBroadcast = new ArrayList();
  public ScheduledExecutorService executor;

  //TODO option to decide, if the broadcast start/stop should be bound to client- or world/server-startup

  @Inject
  public AutoBroadcastManager(StreamChatPlus addon) {
    this.addon = addon;
    config = this.addon.configuration().autoBroadcastSubSettings();
    DebugTool.log("AutoBroadcaster has been initialized", getClass());
  }

  public void startAutoBroadcasts() {
    executor = Executors.newScheduledThreadPool(1);
    autoBroadcast.forEach(ab -> {
      Runnable broadcastRunnable = new Runnable() {
        public void run() {
          if (StreamChatPlus.bot.isConnected()) {
            StreamChatPlus.bot.sendMessage(ab.getMessage());
            DebugTool.log("AutoBroadcaster sent broadcast \"" + (ab.getMessage().length() > 10 ? ab.getMessage().substring(0, 10) + "..." : ab.getMessage()) + "\" with an interval of " + ab.getInterval() + " minutes", getClass());
          }
        }
      };
      executor.scheduleAtFixedRate(broadcastRunnable, ab.getInterval(), ab.getInterval(),
          TimeUnit.MINUTES);
    });
    DebugTool.log("AutoBroadcaster has been started", getClass());
  }

  public void stopAutoBroadcasts() {
    executor.shutdown();
    DebugTool.log("AutoBroadcaster has been manually stopped", DebugPriority.WARNING, getClass());
  }

  public void initializeAutoBroadcasts() {
    if (config.enabled().get()) {
      autoBroadcast.clear();
      if (!Objects.equals(config.message1().get(), "")) {
        if (!config.message1().get().equalsIgnoreCase(
            "This is your first Custom Broadcast. It will be executed every 5 minutes in your twitch chat!")) {
          autoBroadcast.add(new AutoBroadcast(config.interval1().get(), config.message1().get()));
          DebugTool.log("  AutoBroadcast #1 has been loaded from configuration", getClass());
        }
      }
      if (!Objects.equals(config.message2().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval2().get(), config.message2().get()));
        DebugTool.log("  AutoBroadcast #2 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message3().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval3().get(), config.message3().get()));
        DebugTool.log("  AutoBroadcast #3 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message4().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval4().get(), config.message4().get()));
        DebugTool.log("  AutoBroadcast #4 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message5().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval5().get(), config.message5().get()));
        DebugTool.log("  AutoBroadcast #5 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message6().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval6().get(), config.message6().get()));
        DebugTool.log("  AutoBroadcast #6 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message7().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval7().get(), config.message7().get()));
        DebugTool.log("  AutoBroadcast #7 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message8().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval8().get(), config.message8().get()));
        DebugTool.log("  AutoBroadcast #8 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message9().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval9().get(), config.message9().get()));
        DebugTool.log("  AutoBroadcast #9 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.message10().get(), "")) {
        autoBroadcast.add(new AutoBroadcast(config.interval10().get(), config.message10().get()));
        DebugTool.log("  AutoBroadcast #10 has been loaded from configuration", getClass());
      }
    }
  }
}
