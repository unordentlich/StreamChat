package de.unordentlich.streamchatplus.core;

import com.google.inject.Singleton;
import de.unordentlich.streamchatplus.core.listener.ChatMessageSendListener;
import de.unordentlich.streamchatplus.core.listener.PropertyUpdateListener;
import de.unordentlich.streamchatplus.core.listener.WorldJoinListener;
import de.unordentlich.streamchatplus.core.listener.WorldLeaveListener;
import de.unordentlich.streamchatplus.core.utils.TwitchBot;
import de.unordentlich.streamchatplus.core.utils.autobroadcast.AutoBroadcastManager;
import de.unordentlich.streamchatplus.core.utils.customcommands.CustomCommandManager;
import de.unordentlich.streamchatplus.core.utils.support.objects.DebugEntry;
import java.util.ArrayList;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonListener;
import net.labymod.api.models.addon.info.InstalledAddonInfo;

@Singleton
@AddonListener
public class StreamChatPlus extends LabyAddon<Configuration> {
    public static TwitchBot bot;
    public static CustomCommandManager customCommandManager;
    public static AutoBroadcastManager autoBroadcastManager;

    public static ArrayList<DebugEntry> debugLog = new ArrayList<>();

    public static InstalledAddonInfo addonInfo;

    @Override
    protected void enable() {
        this.registerSettingCategory();
        bot = new TwitchBot(this);

        this.registerListener(ChatMessageSendListener.class);
        this.registerListener(WorldJoinListener.class);
        this.registerListener(WorldLeaveListener.class);
        this.registerListener(PropertyUpdateListener.class);

        customCommandManager = new CustomCommandManager(this);
        customCommandManager.initializeCustomCommands();

        autoBroadcastManager = new AutoBroadcastManager(this);
        autoBroadcastManager.initializeAutoBroadcasts();

        this.logger().info("StreamChat+ | Addon successfully enabled. (v" + this.addonInfo().getVersion() + ")");
        addonInfo = this.addonInfo();
    }

    @Override
    protected Class<Configuration> configurationClass() {
        return Configuration.class;
    }
}
