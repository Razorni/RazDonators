package me.razorni.dev;

import net.md_5.bungee.api.ChatColor;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import me.razorni.dev.utils.CC;
import me.razorni.dev.utils.Color;

public class RazDonators extends JavaPlugin {
	
    public static RazDonators instance;
    
    public static File conf;
	
    public static FileConfiguration config;

    public void onEnable() {
	Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[RazDonators] Plugin has been enabled successfully.");
        instance = this; 
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
        new BukkitRunnable() {
            public void run() {
                String players = Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(CC.translate(getConfig().getString("DONATOR-PERMISSION"))) && !player.isOp() && !player.hasPermission("*")).map(HumanEntity::getName).collect(Collectors.joining(", "));
                if (players.isEmpty()) {
                    players = Color.translate(getConfig().getString("NO-DONATORS-ONLINE"));
                }
                for (String onlinedonator : RazDonators.config.getStringList("ANNOUNCE-MESSAGE")) {
					getServer().broadcastMessage(
							ChatColor.translateAlternateColorCodes('&', onlinedonator).replace("%donators%", players));
				}
            }
        }.runTaskTimer(this, 1500L, 1500L);
      
    }
    
    public void onDisable() {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[RazDonators] Plugin has been disabled successfully.");	
    }

    public static RazDonators getInstance() {
        return instance;
      }
    
}
