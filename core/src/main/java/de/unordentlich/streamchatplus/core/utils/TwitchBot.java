package de.unordentlich.streamchatplus.core.utils;

import de.unordentlich.streamchatplus.core.Configuration;
import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.utils.support.DebugTool;
import de.unordentlich.streamchatplus.core.utils.support.ErrorCode;
import de.unordentlich.streamchatplus.core.utils.support.ErrorManager;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugPriority;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import net.labymod.api.inject.LabyGuice;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

public class TwitchBot extends PircBot {

    private final StreamChatPlus addon;
    private final Configuration config;

    @Inject
    public TwitchBot(StreamChatPlus addon) {
        this.addon = addon;
        config = this.addon.configuration();
        this.setName(config.botName().get());
        this.isConnected();
        DebugTool.log("TwitchBot instance has been initialized with the configured name", this.getClass());
    }

    public void start() {
        if (config.enabled().get()) {
            if (isConfiguredCorrectly()) {
                DebugTool.log("TwitchBot instance has been requested to connect...");
                boolean token_working;
                try {
                    this.connect("irc.twitch.tv", 6667, config.twitchtoken().get());
                    DebugTool.log("(TwitchBot) Connecting to irc.twitch.tv:6667 with token from configuration...");
                    token_working = true;
                } catch (IOException e) {
                    ErrorManager.sendErrorMessage(ErrorCode.SCP01);
                    DebugTool.log("TwitchBot instance has not been connected! (failed)", DebugPriority.ERROR, this.getClass());
                    token_working = false;
                } catch (IrcException e) {
                    ErrorManager.sendErrorMessage(ErrorCode.SCP02);
                    DebugTool.log("TwitchBot instance has not been connected! (failed)", DebugPriority.ERROR, this.getClass());
                    token_working = false;
                }
                if (token_working) {
                    this.joinChannel("#" + config.twitchchannel().get().toLowerCase());
                    StreamChatPlus.autoBroadcastManager.startAutoBroadcasts();
                    DebugTool.log("TwitchBot instance has been connected successfully", this.getClass());
                }
            }
        }
    }

    public void stop() {
        this.disconnect();
        StreamChatPlus.autoBroadcastManager.stopAutoBroadcasts();
    }

    public void restart() {
        stop();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname,
                          String message) {
        if (config.enabled().get()) {
            if (!StreamChatPlus.customCommandManager.checkForCommand(message)) {
                LabyGuice.getInstance(StreamChatPlus.class)
                        .displayMessage(ColorCodeTranslator.translateColorCodes(config.prefix().get()).replace("%user%", sender)
                                .replace("%message%", message).replace("@" + config.twitchchannel().get(),
                                        "§l@" + config.twitchchannel().get()));
            }
        }
    }

    public void sendMessage(String message) {
        this.sendMessage("#" + config.twitchchannel().get().toLowerCase(),
                message.replaceFirst(config.ingameChatPrefix().get(), ""));
    }

    private boolean isConfiguredCorrectly() {
        DebugTool.log("Checking configuration for all important data...", getClass());
        if (config.twitchchannel() != null &&
                config.twitchtoken() != null &&
                config.botName() != null) {
            if (!config.twitchtoken().get().startsWith("oauth:")) {
                ErrorManager.sendErrorMessage(ErrorCode.SCP03);
                DebugTool.log("(ConfigurationCheck) Configuration contains invalid oauth format! (failed)", DebugPriority.ERROR, getClass());
                return false;
            } else {
                return true;
            }
        } else {
            ErrorManager.sendErrorMessage(ErrorCode.SCP05);
            DebugTool.log("(ConfigurationCheck) Required configuration fields \"Twitch Channel\", \"Twitch Token\" or \"Bot Name\" were not completed! (failed)", DebugPriority.ERROR, getClass());
            return false;
        }
    }
}
