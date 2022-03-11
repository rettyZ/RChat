package pl.rettyz.rchat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.rettyz.rchat.commands.Chat;
import pl.rettyz.rchat.listeners.ChatCMD;

public final class RChat extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("chat").setExecutor(new Chat());
        Bukkit.getPluginManager().registerEvents(new ChatCMD(), this);

        getLogger().info("Made by RettyZ");
        getLogger().info("Release date: 11.03.2022");

    }
}