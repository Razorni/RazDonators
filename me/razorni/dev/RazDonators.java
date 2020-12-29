package me.razorni.dev;

import net.md_5.bungee.api.ChatColor;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.razorni.dev.utils.Color;

public class RazDonators extends JavaPlugin {
	
    public static RazDonators instance;

    public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[RazDonators] Plugin has been loaded successfully.");
        instance = this;    
        new BukkitRunnable() {
            public void run() {
                String players = Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission("online.donator") && !player.isOp() && !player.hasPermission("*")).map(HumanEntity::getName).collect(Collectors.joining(", "));
                if (players.isEmpty()) {
                    players = "No Donators Online";
                }
                Bukkit.broadcastMessage(Color.translate(" "));
                Bukkit.broadcastMessage(Color.translate("&6Online Donators&7: ") + ChatColor.WHITE + players);
                Bukkit.broadcastMessage(Color.translate("&7If you want to get featured on list purchase rank."));
                Bukkit.broadcastMessage(Color.translate(" "));
            }
        }.runTaskTimer(this, 1500L, 1500L);
      
    }

    public static RazDonators getInstance() {
        return instance;
      }
}
