package de.unordentlich.streamchatplus.core.utils.support;

import de.unordentlich.streamchatplus.core.Configuration;
import de.unordentlich.streamchatplus.core.StreamChatPlus;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugActionExecuter;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugEntry;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugPriority;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.labymod.api.Laby;

public class DebugTool {

    private final Configuration configuration;
    public static boolean isFirstClick = true;
    public static long lastClick = 0;

    @Inject
    public DebugTool(Configuration config) {
        this.configuration = config;
    }

    public String createDebugLog() throws IOException {
        URL url = new URL("https://paste.labymod.net/documents");
        URLConnection connection = url.openConnection();

        connection.setRequestProperty("authority", "paste.labymod.net");
        connection.setRequestProperty("accept", "application/json, text/javascript, /; q=0.01");
        connection.setRequestProperty("x-requested-with", "XMLHttpRequest");
        connection.setRequestProperty("user-agent",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Safari/537.36'");
        connection.setRequestProperty("content-type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        OutputStream stream = connection.getOutputStream();
        stream.write((prepareDebugLog()).getBytes());
        stream.flush();
        stream.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.lines().collect(Collectors.joining("\n"));

        return "https://paste.labymod.net/" + response.split("\"")[3];
    }

    private String prepareDebugLog() {
        StringBuilder log = new StringBuilder(new SimpleDateFormat("dd.MM.yyyy '//' HH:mm:ss").format(
                new Date(System.currentTimeMillis())) + " (" + System.currentTimeMillis() + ")");

        /** LOGO **/
        log
                .append("\n\n")
                .append("  _____ _                             _____ _           _         \n")
                .append(" / ____| |                           / ____| |         | |    _   \n")
                .append("| (___ | |_ _ __ ___  __ _ _ __ ___ | |    | |__   __ _| |_ _| |_ \n")
                .append(" \\___ \\| __| '__/ _ \\/ _` | '_ ` _ \\| |    | '_ \\ / _` | __|_   _|\n")
                .append(" ____) | |_| | |  __/ (_| | | | | | | |____| | | | (_| | |_  |_|  \n")
                .append("|_____/ \\__|_|  \\___|\\__,_|_| |_| |_|\\_____|_| |_|\\__,_|\\__|      \n\n")
                .append("                       made by unordentlich                       \n")
                .append("\n");

        /** DESCRIPTION **/
        log
                .append(
                        "\n\n This log helps the Addon Support Team to evaluate possible errors and problems. \n"
                                + " If you are asked for this log in the support, just double-click on the \"Support\" button and \n"
                                + " paste the link via the context menu or CTRL+V in the support ticket. We will help you as soon as possible!\n\n");

        /** DISCLAIMER **/
        log
                .append("          _____________________________________________\n")
                .append("          |                                           |\n")
                .append("          | By sharing this log,                      |\n")
                .append("          | you are revealing the following data:     |\n")
                .append("          |                                           |\n")
                .append("          |———————————————————————————————————————————|\n")
                .append("          |                                           |\n")
                .append("          | › Your Minecraft Username                 |\n")
                .append("          | › Your Minecraft UUID                     |\n")
                .append("          | › Your Minecraft Version                  |\n")
                .append("          | › Your LabyMod Version                    |\n")
                .append("          | › Your Addon Version                      |\n")
                .append("          | › Your Operating System                   |\n")
                .append("          | › Your JVM Version                        |\n")
                .append("          | › Your Minecraft Language                 |\n")
                .append("          | › Your configuration of this addon        |\n")
                .append("          | › The names of your installed addons      |\n")
                .append("          |                                           |\n")
                .append("          |———————————————————————————————————————————|\n")
                .append("          |                                           |\n")
                .append("          | However, by sharing this log,             |\n")
                .append(
                        "          | we will »NEVER« reveal the following data |\n")
                .append("          |                                           |\n")
                .append("          |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|\n")
                .append("          |                                           |\n")
                .append("          | › Your Twitch Auth Token                  |\n")
                .append("          | › Any account credentials                 |\n")
                .append("          | › Your IP address                         |\n")
                .append("          | › Any personal information like mail      |\n")
                .append("          |                                           |\n")
                .append("          ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾")
                .append("\n");

        /** USER INFORMATIONS **/
        log
                .append("\n")
                .append(" #################################################################\n")
                .append(" # User Information |");
        int leftSpaces = 63 - 20 - 18 - Laby.labyAPI().getName().length();
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }
        log
                .append("https://laby.net/@")
                .append(Laby.labyAPI().getName())
                .append(" #\n")
                .append(" # ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ #\n")
                .append(" # Username: ")
                .append(Laby.labyAPI().getName());
        leftSpaces = (63 - 11) - Laby.labyAPI().getName().length();
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }

        // UUID
        log
                .append("#\n")
                .append(" # UUID: ")
                .append(Laby.labyAPI().getUniqueId().toString());
        leftSpaces =
                (63 - 7) - Laby.labyAPI().getUniqueId().toString().length();
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }

        // VERSION
        log
                .append("#\n")
                .append(" #                                                               #\n")
                .append(" # Version: ")
                .append(Laby.labyAPI().minecraft().getVersion());
        leftSpaces =
                (63 - 10) - Laby.labyAPI().minecraft().getVersion().length();
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }

        // LABYMOD VERSION
        log
                .append("#\n")
                .append(" # LabyMod Version: ")
                .append(Laby.labyAPI().getVersion());
        leftSpaces = (63 - 18) - Laby.labyAPI().getVersion().length();
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }

        // ADDON VERSION
        log
                .append("#\n")
                .append(" # Addon Version: ")
                .append("v" + StreamChatPlus.addonInfo.getVersion());
        leftSpaces = (63 - 16) - (StreamChatPlus.addonInfo.getVersion().length() + 1);
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }

        //ADDONS
        log
                .append("#\n");
        int addonsAmount = Laby.labyAPI().addonService().getLoadedAddons().size();
        log
                .append(" # Addons (")
                .append(addonsAmount)
                .append("): ")
                .append(calculateSpaces(63 - 13, String.valueOf(addonsAmount)))
                .append("#\n");

        Laby.labyAPI().addonService().getLoadedAddons().forEach(addon -> {
            log
                    .append(" #   › " + addon.info().getDisplayName())
                    .append(calculateSpaces(63 - 6, addon.info().getDisplayName()))
                    .append("#\n");
        });

        // OPERATING SYSTEM
        log
                .append(" #                                                               #\n")
                .append(" # Operating System (OS): ")
                .append(System.getProperty("os.name"));
        leftSpaces = (63 - 24) - (System.getProperty("os.name").length());
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }

        // JVM SYSTEM
        log
                .append("#\n")
                .append(" # Java VM Version: ")
                .append(System.getProperty("java.vm.version"));
        leftSpaces = (63 - 18) - (System.getProperty("java.vm.version").length());
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }

        // MINECRAFT LANGUAGE
        log
                .append("#\n")
                .append(" # Language: ")
                .append(Laby.labyAPI().minecraft().options().getCurrentLanguage());
        leftSpaces = (63 - 11) - (Laby.labyAPI().minecraft().options().getCurrentLanguage().length());
        for (int i = 0; i < leftSpaces; i++) {
            log.append(" ");
        }
        log.append("#\n")
                .append(" #                                                               #\n")
                .append(" #################################################################");

        log.append("\n\n\n");

        log
                .append(
                        " #################################################################################################################################################\n")
                .append(
                        " # Addon Configuration |                                                                                                                         #\n")
                .append(
                        " # ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ #\n")
                .append(" # ")
                .append("enabled: ")
                .append(configuration.enabled().get())
                .append(calculateSpaces(141 - 9, configuration.enabled().get().toString()))
                .append("#\n")
                .append(" # ")
                .append("prefix: ")
                .append(configuration.prefix().get())
                .append(calculateSpaces(141 - 8, configuration.prefix().get()))
                .append("#\n")
                .append(" # ")
                .append("botName: ")
                .append(configuration.botName().get())
                .append(calculateSpaces(141 - 9, configuration.botName().get()))
                .append("#\n")
                .append(" # ")
                .append("twitchChannel: ")
                .append(configuration.twitchchannel().get())
                .append(calculateSpaces(141 - 15, configuration.twitchchannel().get()))
                .append("#\n")
                .append(" # ")
                .append("twitchToken: ")
                .append(hidingInformation())
                .append(calculateSpaces(141 - 8, "Hidden due to security restrictions"))
                .append("#\n")
                .append(" # ")
                .append("ingameChatEnabled: ")
                .append(configuration.ingameChatEnabled().get())
                .append(calculateSpaces(141 - 19, configuration.ingameChatEnabled().get().toString()))
                .append("#\n")
                .append(" # ")
                .append("ingameChatPrefix: ")
                .append(configuration.ingameChatPrefix().get())
                .append(calculateSpaces(141 - 18, configuration.ingameChatPrefix().get()))
                .append("#\n")
                .append(
                        " # autoBroadcastSubSettings:                                                                                                                     #\n") // AutoBroadcast (subtract 4 from every SpaceCalculation due to indentation)
                .append(" #     ")
                .append("enabled: ")
                .append(configuration.autoBroadcastSubSettings().enabled().get())
                .append(calculateSpaces(141 - 4 - 9,
                        configuration.autoBroadcastSubSettings().enabled().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("interval1: ")
                .append(configuration.autoBroadcastSubSettings().interval1().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval1().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message1: ")
                .append(configuration.autoBroadcastSubSettings().message1().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message1().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval2: ")
                .append(configuration.autoBroadcastSubSettings().interval2().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval2().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message2: ")
                .append(configuration.autoBroadcastSubSettings().message2().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message2().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval3: ")
                .append(configuration.autoBroadcastSubSettings().interval3().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval3().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message3: ")
                .append(configuration.autoBroadcastSubSettings().message3().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message3().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval4: ")
                .append(configuration.autoBroadcastSubSettings().interval4().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval4().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message4: ")
                .append(configuration.autoBroadcastSubSettings().message4().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message4().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval5: ")
                .append(configuration.autoBroadcastSubSettings().interval5().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval5().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message5: ")
                .append(configuration.autoBroadcastSubSettings().message5().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message5().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval6: ")
                .append(configuration.autoBroadcastSubSettings().interval6().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval6().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message6: ")
                .append(configuration.autoBroadcastSubSettings().message6().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message6().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval7: ")
                .append(configuration.autoBroadcastSubSettings().interval7().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval7().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message7: ")
                .append(configuration.autoBroadcastSubSettings().message7().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message7().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval8: ")
                .append(configuration.autoBroadcastSubSettings().interval8().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval8().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message8: ")
                .append(configuration.autoBroadcastSubSettings().message8().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message8().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval9: ")
                .append(configuration.autoBroadcastSubSettings().interval9().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().interval9().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message9: ")
                .append(configuration.autoBroadcastSubSettings().message9().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.autoBroadcastSubSettings().message9().get()))
                .append("#\n")
                .append(" #     ")
                .append("interval10: ")
                .append(configuration.autoBroadcastSubSettings().interval10().get())
                .append(calculateSpaces(141 - 4 - 12,
                        configuration.autoBroadcastSubSettings().interval10().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("message10: ")
                .append(configuration.autoBroadcastSubSettings().message10().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.autoBroadcastSubSettings().message10().get()))
                .append("#\n") // AutoBroadcast end
                .append(
                        " # customCommandSubSettings:                                                                                                                     #\n") // CustomCommand (subtract 4 from every SpaceCalculation due to indentation)
                .append(" #     ")
                .append("enabled: ")
                .append(configuration.customCommandSubSettings().enabled().get())
                .append(calculateSpaces(141 - 4 - 9,
                        configuration.customCommandSubSettings().enabled().get().toString()))
                .append("#\n")
                .append(" #     ")
                .append("command1: ")
                .append(configuration.customCommandSubSettings().command1().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command1().get()))
                .append("#\n")
                .append(" #     ")
                .append("response1: ")
                .append(configuration.customCommandSubSettings().response1().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response1().get()))
                .append("#\n")
                .append(" #     ")
                .append("command2: ")
                .append(configuration.customCommandSubSettings().command2().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command2().get()))
                .append("#\n")
                .append(" #     ")
                .append("response2: ")
                .append(configuration.customCommandSubSettings().response2().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response2().get()))
                .append("#\n")
                .append(" #     ")
                .append("command3: ")
                .append(configuration.customCommandSubSettings().command3().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command3().get()))
                .append("#\n")
                .append(" #     ")
                .append("response3: ")
                .append(configuration.customCommandSubSettings().response3().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response3().get()))
                .append("#\n")
                .append(" #     ")
                .append("command4: ")
                .append(configuration.customCommandSubSettings().command4().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command4().get()))
                .append("#\n")
                .append(" #     ")
                .append("response4: ")
                .append(configuration.customCommandSubSettings().response4().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response4().get()))
                .append("#\n")
                .append(" #     ")
                .append("command5: ")
                .append(configuration.customCommandSubSettings().command5().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command5().get()))
                .append("#\n")
                .append(" #     ")
                .append("response5: ")
                .append(configuration.customCommandSubSettings().response5().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response5().get()))
                .append("#\n")
                .append(" #     ")
                .append("command6: ")
                .append(configuration.customCommandSubSettings().command6().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command6().get()))
                .append("#\n")
                .append(" #     ")
                .append("response6: ")
                .append(configuration.customCommandSubSettings().response6().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response6().get()))
                .append("#\n")
                .append(" #     ")
                .append("command7: ")
                .append(configuration.customCommandSubSettings().command7().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command7().get()))
                .append("#\n")
                .append(" #     ")
                .append("response7: ")
                .append(configuration.customCommandSubSettings().response7().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response7().get()))
                .append("#\n")
                .append(" #     ")
                .append("command8: ")
                .append(configuration.customCommandSubSettings().command8().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command8().get()))
                .append("#\n")
                .append(" #     ")
                .append("response8: ")
                .append(configuration.customCommandSubSettings().response8().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response8().get()))
                .append("#\n")
                .append(" #     ")
                .append("command9: ")
                .append(configuration.customCommandSubSettings().command9().get())
                .append(calculateSpaces(141 - 4 - 10,
                        configuration.customCommandSubSettings().command9().get()))
                .append("#\n")
                .append(" #     ")
                .append("response9: ")
                .append(configuration.customCommandSubSettings().response9().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().response9().get()))
                .append("#\n")
                .append(" #     ")
                .append("command10: ")
                .append(configuration.customCommandSubSettings().command10().get())
                .append(calculateSpaces(141 - 4 - 11,
                        configuration.customCommandSubSettings().command10().get()))
                .append("#\n")
                .append(" #     ")
                .append("response10: ")
                .append(configuration.customCommandSubSettings().response10().get())
                .append(calculateSpaces(141 - 4 - 12,
                        configuration.customCommandSubSettings().response10().get()))
                .append("#\n") // CustomCommands end
                .append(
                        " #                                                                                                                                               #\n")
                .append(
                        " #################################################################################################################################################\n")

                .append("\n\n")
                .append(" // Debug Console //")
                .append(loadDebugEntries());
        return log.toString();
    }

    public static void log(String event, DebugPriority priority, DebugActionExecuter executer) {
        DebugEntry entry = new DebugEntry(event, priority).executer(executer);
        StreamChatPlus.debugLog.add(entry);
    }

    public static void log(String event, DebugPriority priority) {
        DebugEntry entry = new DebugEntry(event, priority);
        StreamChatPlus.debugLog.add(entry);
    }

    public static void log(String event, DebugActionExecuter executer) {
        DebugEntry entry = new DebugEntry(event).executer(executer);
        StreamChatPlus.debugLog.add(entry);
    }

    public static void log(String event) {
        DebugEntry entry = new DebugEntry(event);
        StreamChatPlus.debugLog.add(entry);
    }

    public static void log(String event, DebugPriority priority, DebugActionExecuter executer, Class executedClass) {
        DebugEntry entry = new DebugEntry(event, priority, executedClass).executer(executer);
        StreamChatPlus.debugLog.add(entry);
    }

    public static void log(String event, DebugPriority priority, Class executedClass) {
        DebugEntry entry = new DebugEntry(event, priority, executedClass);
        StreamChatPlus.debugLog.add(entry);
    }

    public static void log(String event, DebugActionExecuter executer, Class executedClass) {
        DebugEntry entry = new DebugEntry(event, executedClass).executer(executer);
        StreamChatPlus.debugLog.add(entry);
    }

    public static void log(String event, Class executedClass) {
        DebugEntry entry = new DebugEntry(event, executedClass);
        StreamChatPlus.debugLog.add(entry);
    }

    private String calculateSpaces(int expectedAmount, String value) {
        int leftSpaces = expectedAmount - value.length();
        StringBuilder returnBuild = new StringBuilder();
        for (int i = 0; i <= leftSpaces; i++) {
            returnBuild.append(" ");
        }
        return returnBuild.toString();
    }

    private String hidingInformation() {
        //https://ts3index.com/?page=generator (Math Sans Italic)
        return "\uD835\uDE0F\uD835\uDE2A\uD835\uDE25\uD835\uDE25\uD835\uDE26\uD835\uDE2F "
                + "\uD835\uDE25\uD835\uDE36\uD835\uDE26 "
                + "\uD835\uDE35\uD835\uDE30 "
                + "\uD835\uDE34\uD835\uDE26\uD835\uDE24\uD835\uDE36\uD835\uDE33\uD835\uDE2A\uD835\uDE35\uD835\uDE3A "
                + "\uD835\uDE33\uD835\uDE26\uD835\uDE34\uD835\uDE35\uD835\uDE33\uD835\uDE2A\uD835\uDE24\uD835\uDE35\uD835\uDE2A\uD835\uDE30\uD835\uDE2F\uD835\uDE34";
    }

    private String loadDebugEntries() {
        StringBuilder returnBuilder = new StringBuilder();
        StreamChatPlus.debugLog.forEach(entry -> {
            returnBuilder
                    .append("\n")
                    .append(" [")
                    .append(entry.getExecuter().name())
                    .append("] ")
                    .append(" [")
                    .append(entry.getType().name())
                    .append("] ")
                    .append("[")
                    .append(new SimpleDateFormat("HH:mm:ss:SS").format(new Date(entry.getTimestamp())))
                    .append(" (")
                    .append(entry.getTimestamp())
                    .append(")");
            if (entry.getExecutedClass() != null) {
                returnBuilder
                        .append(" | ")
                        .append(entry.getExecutedClass().getSimpleName());
            }
            returnBuilder
                    .append("] ")
                    .append(entry.getEvent());
        });
        return returnBuilder.toString();
    }
}
