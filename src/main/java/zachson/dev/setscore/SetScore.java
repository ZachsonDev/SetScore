package zachson.dev.setscore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import zachson.dev.setscore.extra.PlayerListeningEvent;
import zachson.dev.setscore.extra.SetScoreboard;

public final class SetScore extends JavaPlugin {

    private static SetScore INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        registerEvents(new PlayerListeningEvent());
        saveDefaultConfig();
        new BukkitRunnable() {

            @Override
            public void run() {
                Bukkit.getServer().getOnlinePlayers().forEach(player -> player.setScoreboard(new SetScoreboard(player).get()));
            }
        }.runTaskTimer(this, 0, getConfig().getInt("refresh-ticks"));
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Scoreboards made easy! Using " + getName() + "!");

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Thank you for using " + getName() + "!");
    }

    private void registerEvents(Listener... listeners) {
        for(Listener listener : listeners) getServer().getPluginManager().registerEvents(listener, this);
    }

    public static SetScore getInstance() {
        return INSTANCE;
    }
}
