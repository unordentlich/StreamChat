package de.unordentlich.streamchatplus.core.subconfigurations;

import de.unordentlich.streamchatplus.core.StreamChatPlus;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;

public class AutoBroadcastSubSettings extends Config {

  @ParentSwitch
  @SwitchSetting
  private ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @MethodOrder(after = "enabled")
  @ButtonSetting(translation = "streamchatplus.settings.autoBroadcastSubSettings.reloadBroadcasts.text")
  public void reloadBroadcasts(Setting setting) {
    StreamChatPlus.customCommandManager.initializeCustomCommands();
  }

  @SettingSection("introduction")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval1 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message1 = new ConfigProperty<>(
      "This is your first Custom Broadcast. It will be executed every 5 minutes in your twitch chat!");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval2 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message2 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval3 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message3 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval4 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message4 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval5 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message5 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval6 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message6 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval7 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message7 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval8 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message8 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval9 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message9 = new ConfigProperty<>("");

  @SettingSection("autoBroadcast")
  @SliderSetting(min = 3, max = 60, steps = 1)
  private final ConfigProperty<Integer> interval10 = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> message10 = new ConfigProperty<>("");

  public ConfigProperty<Boolean> enabled() {
    return enabled;
  }

  public ConfigProperty<Integer> interval1() {
    return interval1;
  }

  public ConfigProperty<String> message1() {
    return message1;
  }

  public ConfigProperty<Integer> interval2() {
    return interval2;
  }

  public ConfigProperty<String> message2() {
    return message2;
  }

  public ConfigProperty<Integer> interval3() {
    return interval3;
  }

  public ConfigProperty<String> message3() {
    return message3;
  }

  public ConfigProperty<Integer> interval4() {
    return interval4;
  }

  public ConfigProperty<String> message4() {
    return message4;
  }

  public ConfigProperty<Integer> interval5() {
    return interval5;
  }

  public ConfigProperty<String> message5() {
    return message5;
  }

  public ConfigProperty<Integer> interval6() {
    return interval6;
  }

  public ConfigProperty<String> message6() {
    return message6;
  }

  public ConfigProperty<Integer> interval7() {
    return interval7;
  }

  public ConfigProperty<String> message7() {
    return message7;
  }

  public ConfigProperty<Integer> interval8() {
    return interval8;
  }

  public ConfigProperty<String> message8() {
    return message8;
  }

  public ConfigProperty<Integer> interval9() {
    return interval9;
  }

  public ConfigProperty<String> message9() {
    return message9;
  }

  public ConfigProperty<Integer> interval10() {
    return interval10;
  }

  public ConfigProperty<String> message10() {
    return message10;
  }
}
