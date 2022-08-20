package de.unordentlich.streamchatplus.core.utils.customcommands;

import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.subconfigurations.CustomCommandSubSettings;
import de.unordentlich.streamchatplus.core.utils.customcommands.objects.CustomCommand;
import java.util.HashMap;
import java.util.Objects;
import javax.inject.Inject;
import de.unordentlich.streamchatplus.core.utils.support.DebugTool;
import net.labymod.api.Laby;

public class CustomCommandManager {

  private final StreamChatPlus addon;
  private CustomCommandSubSettings config;

  public HashMap<String, CustomCommand> commands = new HashMap<>();

  @Inject
  public CustomCommandManager(StreamChatPlus addon) {
    this.addon = addon;
    config = this.addon.configuration().customCommandSubSettings();
    DebugTool.log("CustomCommands have been initialized", getClass());
  }

  public boolean checkForCommand(String message) {
    if (config.enabled().get()) {
      if (commands.containsKey(message.toLowerCase())) {
        if (StreamChatPlus.bot.isConnected()) {
          StreamChatPlus.bot.sendMessage(initializeResponse(message.toLowerCase()));
          DebugTool.log("String was interpreted as a command and responded", getClass());
          return true;
        }
        return false;
      }
      return false;
    }
    return false;
  }

  public String initializeResponse(String keyword) {
    return commands.get(keyword).getResponse()
        .replace("%name%", Laby.labyAPI().minecraft().clientPlayer().getName())
        .replace("%version%", Laby.labyAPI().minecraft().getVersion())
        .replace("%server%", (Laby.labyAPI().minecraft().isSingleplayer() ? "Singleplayer"
            : Laby.labyAPI().serverController().getCurrentServerData().getName()))
        .replace("%labynet%",
            "https://laby.net/@" + Laby.labyAPI().minecraft().clientPlayer().getName());
  }

  public void initializeCustomCommands() {
    if (config.enabled().get()) {
      commands.clear();
      if (!Objects.equals(config.command1().get(), "")) {
        if (!config.command1().get().equalsIgnoreCase("!example")
            && !config.response1().get().equalsIgnoreCase(
            "This is your first Custom Command. It can be executed in your twitch chat!")) {
          commands.put(config.command1().get().toLowerCase(),
              new CustomCommand(config.command1().get(), config.response1().get()));
          DebugTool.log("  CustomCommand #1 has been loaded from configuration", getClass());
        }
      }
      if (!Objects.equals(config.command2().get(), "") && !Objects.equals(config.response2().get(),
          "")) {
        commands.put(config.command2().get().toLowerCase(),
            new CustomCommand(config.command2().get(), config.response2().get()));
        DebugTool.log("  CustomCommand #2 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command3().get(), "") && !Objects.equals(config.response3().get(),
          "")) {
        commands.put(config.command3().get().toLowerCase(),
            new CustomCommand(config.command3().get(), config.response3().get()));
        DebugTool.log("  CustomCommand #3 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command4().get(), "") && !Objects.equals(config.response4().get(),
          "")) {
        commands.put(config.command4().get().toLowerCase(),
            new CustomCommand(config.command4().get(), config.response4().get()));
        DebugTool.log("  CustomCommand #4 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command5().get(), "") && !Objects.equals(config.response5().get(),
          "")) {
        commands.put(config.command5().get().toLowerCase(),
            new CustomCommand(config.command5().get(), config.response5().get()));
        DebugTool.log("  CustomCommand #5 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command6().get(), "") && !Objects.equals(config.response6().get(),
          "")) {
        commands.put(config.command6().get().toLowerCase(),
            new CustomCommand(config.command6().get(), config.response6().get()));
        DebugTool.log("  CustomCommand #6 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command7().get(), "") && !Objects.equals(config.response7().get(),
          "")) {
        commands.put(config.command7().get().toLowerCase(),
            new CustomCommand(config.command7().get(), config.response7().get()));
        DebugTool.log("  CustomCommand #7 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command8().get(), "") && !Objects.equals(config.response8().get(),
          "")) {
        commands.put(config.command8().get().toLowerCase(),
            new CustomCommand(config.command8().get(), config.response8().get()));
        DebugTool.log("  CustomCommand #8 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command9().get(), "") && !Objects.equals(config.response9().get(),
          "")) {
        commands.put(config.command9().get().toLowerCase(),
            new CustomCommand(config.command9().get(), config.response9().get()));
        DebugTool.log("  CustomCommand #9 has been loaded from configuration", getClass());
      }
      if (!Objects.equals(config.command10().get(), "") && !Objects.equals(
          config.response10().get(),
          "")) {
        commands.put(config.command10().get().toLowerCase(),
            new CustomCommand(config.command10().get(), config.response10().get()));
        DebugTool.log("  CustomCommand #10 has been loaded from configuration", getClass());
      }
    }
  }
}
