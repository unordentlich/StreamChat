package de.unordentlich.streamchatplus.core;

import de.unordentlich.streamchatplus.core.subconfigurations.AutoBroadcastSubSettings;
import de.unordentlich.streamchatplus.core.subconfigurations.CustomCommandSubSettings;
import de.unordentlich.streamchatplus.core.utils.support.DebugTool;
import java.io.IOException;

import net.labymod.api.Laby;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public class Configuration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @TextFieldSetting
  private final ConfigProperty<String> prefix = new ConfigProperty<>(
      "§5✪ §d%user% §8> §7%message%");

  @MethodOrder(after = "prefix")
  @ButtonSetting(translation = "streamchatplus.settings.openSupportLink.text")
  public void openSupportLink(Setting setting) {
    DebugTool.lastClick = System.currentTimeMillis();
    long thisClick = System.currentTimeMillis();
    Configuration thisConfig = this;
    if(DebugTool.isFirstClick) {
      DebugTool.isFirstClick = false;
      new java.util.Timer().schedule(
          new java.util.TimerTask() {
            @Override
            public void run() {
              if (DebugTool.lastClick > thisClick) {
                try {
                  String link = new DebugTool(thisConfig).createDebugLog();
                  Laby.labyAPI().minecraft().chatExecutor()
                      .openUrl(link, false);
                  Laby.labyAPI().minecraft().setClipboard(link);
                  DebugTool.isFirstClick = true;
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
              } else {
                Laby.labyAPI().minecraft().chatExecutor()
                    .openUrl("https://discord.gg/GhdDA4yyvR", false);
                DebugTool.isFirstClick = true;
              }
            }
          }, 650);
    }
  }

  @SettingSection("twitch")
  @TextFieldSetting
  private final ConfigProperty<String> botName = new ConfigProperty<>("StreamChatPlus");

  @TextFieldSetting
  private final ConfigProperty<String> twitchChannel = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> twitchToken = new ConfigProperty<>("");

  @SettingSection("ingame")
  @SwitchSetting
  private final ConfigProperty<Boolean> ingameChatEnabled = new ConfigProperty<>(false);

  @TextFieldSetting
  private final ConfigProperty<String> ingameChatPrefix = new ConfigProperty<>("#");

  @SettingSection("extras")
  private AutoBroadcastSubSettings autoBroadcastSubSettings = new AutoBroadcastSubSettings();
  private CustomCommandSubSettings customCommandSubSettings = new CustomCommandSubSettings();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<String> botName() {
    return this.botName;
  }

  public ConfigProperty<String> prefix() {
    return this.prefix;
  }

  public ConfigProperty<String> twitchchannel() {
    return this.twitchChannel;
  }

  public ConfigProperty<String> twitchtoken() {
    return this.twitchToken;
  }

  public ConfigProperty<Boolean> ingameChatEnabled() {
    return this.ingameChatEnabled;
  }

  public ConfigProperty<String> ingameChatPrefix() {
    return this.ingameChatPrefix;
  }

  public AutoBroadcastSubSettings autoBroadcastSubSettings() {
    return autoBroadcastSubSettings;
  }

  public CustomCommandSubSettings customCommandSubSettings() {
    return customCommandSubSettings;
  }
}
