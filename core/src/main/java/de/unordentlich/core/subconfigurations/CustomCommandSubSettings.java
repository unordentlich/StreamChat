package de.unordentlich.core.subconfigurations;

import de.unordentlich.core.StreamChatPlus;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;

public class CustomCommandSubSettings extends Config {

  @ParentSwitch
  @SwitchSetting
  private ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @MethodOrder(after = "enabled")
  @ButtonSetting(translation = "streamchatplus.settings.customCommandSubSettings.reloadCommands.text")
  public void reloadCommands(Setting setting) {
    StreamChatPlus.customCommandManager.initializeCustomCommands();
  }

  @SettingSection("introduction")
  @TextFieldSetting
  private final ConfigProperty<String> command1 = new ConfigProperty<>("!example");

  @TextFieldSetting
  private final ConfigProperty<String> response1 = new ConfigProperty<>(
      "This is your first Custom Command. It can be executed in your twitch chat!");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command2 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response2 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command3 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response3 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command4 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response4 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command5 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response5 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command6 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response6 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command7 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response7 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command8 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response8 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command9 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response9 = new ConfigProperty<>("");

  @SettingSection("customCommand")
  @TextFieldSetting
  private final ConfigProperty<String> command10 = new ConfigProperty<>("");

  @TextFieldSetting
  private final ConfigProperty<String> response10 = new ConfigProperty<>("");

  public ConfigProperty<Boolean> enabled() {
    return enabled;
  }

  public ConfigProperty<String> command1() {
    return command1;
  }

  public ConfigProperty<String> response1() {
    return response1;
  }

  public ConfigProperty<String> command2() {
    return command2;
  }

  public ConfigProperty<String> response2() {
    return response2;
  }

  public ConfigProperty<String> command3() {
    return command3;
  }

  public ConfigProperty<String> response3() {
    return response3;
  }

  public ConfigProperty<String> command4() {
    return command4;
  }

  public ConfigProperty<String> response4() {
    return response4;
  }

  public ConfigProperty<String> command5() {
    return command5;
  }

  public ConfigProperty<String> response5() {
    return response5;
  }

  public ConfigProperty<String> command6() {
    return command6;
  }

  public ConfigProperty<String> response6() {
    return response6;
  }

  public ConfigProperty<String> command7() {
    return command7;
  }

  public ConfigProperty<String> response7() {
    return response7;
  }

  public ConfigProperty<String> command8() {
    return command8;
  }

  public ConfigProperty<String> response8() {
    return response8;
  }

  public ConfigProperty<String> command9() {
    return command9;
  }

  public ConfigProperty<String> response9() {
    return response9;
  }

  public ConfigProperty<String> command10() {
    return command10;
  }

  public ConfigProperty<String> response10() {
    return response10;
  }
}
