package de.crowraw.discordhook.listener;

import de.crowraw.discordhook.DiscordHook;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadyHandler extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        String id = DiscordHook.getInstance().getConfigUtil().getStringMessage("000000000ID","discord.channel.id");
        DiscordHook.getInstance().setTextChannel(DiscordHook.getInstance().getApi().getTextChannelById(id));
        DiscordHook.getInstance().sendMessage("Bot is now online!");
    }
}
