package io.github.logics4.individualkeepinventory.sponge;

import com.google.inject.Inject;

import io.github.logics4.individualkeepinventory.common.Constants;

import org.bstats.sponge.Metrics;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "individualkeepinventory",
    name = PluginInfo.NAME,
    authors = {"Logics4"},
    version = PluginInfo.VERSION,
    description = PluginInfo.DESCRIPTION,
    url = PluginInfo.URL)
public class IKI {

    @Inject
    public IKI(Metrics.Factory metricsFactory) {
        int bStatsId = 10158; // plugin ID for bStats for Sponge
        metricsFactory.make(bStatsId);
    }

    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event, @Getter("getTargetEntity")Player player) {
        if (player.hasPermission(Constants.IKI_KEEPINVENTORY_PERMISSION)) {
            event.setKeepInventory(true);
        }
    }
}
