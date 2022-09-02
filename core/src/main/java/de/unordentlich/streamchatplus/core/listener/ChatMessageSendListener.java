package de.unordentlich.streamchatplus.core.listener;

import com.google.inject.Inject;
import de.unordentlich.streamchatplus.core.Configuration;
import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.utils.ColorCodeTranslator;
import de.unordentlich.streamchatplus.core.utils.support.DebugTool;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugActionExecuter;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugPriority;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;
import net.labymod.api.inject.LabyGuice;

public class ChatMessageSendListener {

    private final StreamChatPlus addon;

    @Inject
    private ChatMessageSendListener(StreamChatPlus addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onChatMessageSend(ChatMessageSendEvent event) {
        DebugTool.log("ChatMessageSendEvent has been triggered", DebugActionExecuter.USER, getClass());
        Configuration config = this.addon.configuration();
        if (event.getMessage().startsWith(config.ingameChatPrefix().get())) {
            if (config.ingameChatEnabled().get()) {
                StreamChatPlus.bot.sendMessage(event.getOriginalMessage());
                DebugTool.log("Sent message in Twitch Chat", getClass());
                LabyGuice.getInstance(StreamChatPlus.class)
                        .displayMessage(
                                ColorCodeTranslator.translateColorCodes(config.prefix().get()).replace("%user%",
                                        Laby.labyAPI().minecraft().clientPlayer().getName()).replace("%message%",
                                        event.getOriginalMessage().replaceFirst(config.ingameChatPrefix().get(), "")));
                DebugTool.log("ChatMessageSendEvent has been cancelled", DebugPriority.WARNING, getClass());
                event.setCancelled(true);
            }
        }
        return;
    }

}
