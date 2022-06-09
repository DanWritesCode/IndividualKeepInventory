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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class IKI extends JavaPlugin implements Listener {

    private final String SAVE_FILE = getDataFolder()+"/keepInventory.txt";
    private final HashMap<UUID, Boolean> keepInventory = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        Path file = Paths.get(SAVE_FILE);

        if(Files.exists(file))
            try {
                List<String> r = Files.readAllLines(file);
                if(r.size() > 0)
                    for(String s : r)
                        if(s.contains(":"))
                            keepInventory.put(UUID.fromString(s.split(":")[0]), Boolean.parseBoolean(s.split(":")[1]));
        } catch (IOException ignored) { }
    }

    @Override
    public void onDisable() {
        Path file = Paths.get(SAVE_FILE);
        try {
            List<String> writeMe = new ArrayList<>();
            keepInventory.forEach((k, v) -> writeMe.add(k+":"+v));
            Files.write(file, writeMe);
        } catch (IOException ignored) { }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (keepInventory.containsKey(event.getEntity().getUniqueId()) && keepInventory.get(event.getEntity().getUniqueId())) {
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

        if (!keepInventory.containsKey(player.getUniqueId()) || !keepInventory.get(player.getUniqueId())) {
            keepInventory.put(player.getUniqueId(), true);
            sender.sendMessage("Keep inventory toggled ON");
        } else {
            keepInventory.put(player.getUniqueId(), false);
            sender.sendMessage("Keep inventory toggled OFF");
        }

        return true;
    }

}
