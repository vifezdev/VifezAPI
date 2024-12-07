package lol.vifez.api.example.command;

import lol.vifez.api.command.annotation.CommandAlias;
import lol.vifez.api.command.annotation.DefaultCommand;
import lol.vifez.api.example.menu.ExampleMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("menu")
public class MenuCommand {

    @DefaultCommand
    public void onDefault(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return;
        }

        Player player = (Player) sender;
        new ExampleMenu().open(player);
    }
}