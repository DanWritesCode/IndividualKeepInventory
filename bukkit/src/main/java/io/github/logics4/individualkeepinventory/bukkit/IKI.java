/*
This file is part of the "IndividualKeepInventory" project.
You can find it here: https://github.com/Logics4/IndividualKeepInventory

Copyright (C) 2020  Logics4

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package io.github.logics4.individualkeepinventory.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class IKI extends JavaPlugin implements Listener {

    public static final String IKI_KEEPINVENTORY_PERMISSION = "iki.events.playerdeath.keepinventory";

    private HashMap<UUID, Boolean> keepInventory = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().hasPermission(IKI_KEEPINVENTORY_PERMISSION)) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        /*if(!permManager.containsKey(player.getUniqueId()))
            permManager.put(player.getUniqueId(), player.addAttachment(this));

        if (sender.hasPermission(IKI_KEEPINVENTORY_PERMISSION)) {
            permManager.get(player.getUniqueId()).setPermission(IKI_KEEPINVENTORY_PERMISSION, false);
            sender.sendMessage("Keep inventory toggled OFF");
        } else {
            permManager.get(player.getUniqueId()).setPermission(IKI_KEEPINVENTORY_PERMISSION, true);
            sender.sendMessage("Keep inventory toggled ON");
        }*/

        return true;
    }

}
