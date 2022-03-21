package io.github.logics4.individualkeepinventory.bukkit;

import io.github.logics4.individualkeepinventory.common.Constants;

import org.bstats.bukkit.Metrics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class IKI extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        int bStatsId = 10156; // Plugin ID for bStats for Bukkit
        new Metrics(this, bStatsId);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().hasPermission(Constants.IKI_KEEPINVENTORY_PERMISSION)) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }
}
