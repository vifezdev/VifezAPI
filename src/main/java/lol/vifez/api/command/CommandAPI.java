package lol.vifez.api.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommandAPI {

    private final String name;
    private final boolean playerOnly;
    private final String permission;

    public CommandAPI(String name, boolean playerOnly, String permission) {
        this.name = name;
        this.playerOnly = playerOnly;
        this.permission = permission;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public List<String> complete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    public boolean hasPermission(CommandSender sender) {
        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage("§cYou do not have permission to use this api.");
            return false;
        }
        return true;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public String getName() {
        return name;
    }

    public List<String> getPlayerNames() {
        List<String> names = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            names.add(player.getName());
        }
        return names;
    }
}


