package de.crowraw.discordhook;

import de.crowraw.discordhook.api.ConfigUtil;
import de.crowraw.discordhook.listener.ListenerHandler;
import de.crowraw.discordhook.listener.ReadyHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordHook extends JavaPlugin {
    private ConfigUtil configUtil = new ConfigUtil("plugins//DiscordHook//config.yml");
    private static DiscordHook instance;
    private JDA api;
    private TextChannel textChannel;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        startBot();
        registerEvents(Bukkit.getPluginManager());
    }

    private void registerEvents(PluginManager pm) {
        pm.registerEvents(new ListenerHandler(), this);
    }

    private void startBot() {
        try {
            saveConfig();
            if (getConfig().get("discord.token") == null) {
                getConfig().set("discord.token", "yourDiscordToken");
                getLogger().warning("THERE IS NO TOKEN! CREATE ONE HERE https://discord.com/developers/applications ");
                saveConfig();
            }
            JDABuilder  builder=  JDABuilder.createDefault(getConfig().getString("discord.token"));
            builder.addEventListeners(new ReadyHandler());
            builder.setActivity(Activity.playing(configUtil.getStringMessage("My Minecraft Server!", "discord.activity")));
            builder.setStatus(OnlineStatus.ONLINE);
            api=  builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DiscordHook getInstance() {
        return instance;
    }

    public ConfigUtil getConfigUtil() {
        return configUtil;
    }


    public void setTextChannel(TextChannel textChannel) {
        this.textChannel = textChannel;
    }

    public JDA getApi() {
        return api;
    }

    public void sendMessage(String message) {
        textChannel.sendMessage(message).queue();
    }
}
