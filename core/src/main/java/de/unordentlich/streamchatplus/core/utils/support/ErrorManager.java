package de.unordentlich.streamchatplus.core.utils.support;

import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugPriority;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.labymod.api.inject.LabyGuice;

public class ErrorManager {

    public static void sendErrorMessage() {
        System.out.println("StreamChat+ | Addon couldn't connect to twitch profile!");
        LabyGuice.getInstance(StreamChatPlus.class).displayMessage(Component
                .translatable("streamchatplus.messages.error.message"));
        DebugTool.log("An error was thrown!", DebugPriority.ERROR);
    }

    public static void sendErrorMessage(ErrorCode code) {
        System.out.println("StreamChat+ | Addon couldn't connect to twitch profile! (#" + code + ")");
        DebugTool.log("An error with the code #" + code + " was thrown!", DebugPriority.ERROR);
        Component message = Component
                .translatable("streamchatplus.messages.error.message")
                .hoverEvent(HoverEvent.showText(Component
                        .translatable("streamchatplus.messages.error.description")
                        .append(Component.text(" §c#" + code))
                        .append(Component.text(" §7(" + code.ExceptionName + "§7)"))));
        LabyGuice.getInstance(StreamChatPlus.class).displayMessage(message);
    }
}

