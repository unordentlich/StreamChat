package de.unordentlich.core.listener;

import static de.unordentlich.core.StreamChatPlus.bot;

import com.google.inject.Inject;
import de.unordentlich.core.StreamChatPlus;
import de.unordentlich.core.utils.support.DebugTool;
import de.unordentlich.core.utils.support.objects.DebugActionExecuter;
import de.unordentlich.core.utils.support.objects.DebugPriority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.labymod.config.SettingUpdateEvent;
import net.labymod.api.event.labymod.config.SettingWidgetInitializeEvent;

public class PropertyUpdateListener {

  private final StreamChatPlus addon;

  @Inject
  private PropertyUpdateListener(StreamChatPlus addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onSettingUpdate(SettingUpdateEvent event) {
    DebugTool.log("SettingUpdateEvent has been triggered", DebugActionExecuter.USER, getClass());
    if (event.setting().getId().equals("enabled")) {
      if (!(boolean) event.getValue()) {
        if (bot.isConnected()) {
          bot.stop();
          DebugTool.log("TwitchBot was stopped because addon was disabled", DebugPriority.WARNING, getClass());
        }
      }
    }
    return;
  }

  @Subscribe
  public void onSettingInit(SettingWidgetInitializeEvent event) {
    DebugTool.log("SettingWidgetInitializeEvent has been triggered", getClass());
    /**if(!event.holder().getPath().equals("settings.streamchatplus")) {
     return;
     }

     for (Widget setting : event.getSettings()) {
     if(!(setting instanceof SettingHeaderWidget)) {
     continue;
     }

     SettingHeaderWidget settingHeaderWidget = (SettingHeaderWidget) setting;
     //settingHeaderWidget.component(Component.text("sdfwsdfsdf"));
     }**/
  }

}
