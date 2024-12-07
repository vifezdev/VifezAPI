package lol.vifez.api.example.command;

import lol.vifez.api.chat.CC;
import lol.vifez.api.command.annotation.CommandPermission;
import lol.vifez.api.config.ConfigAPI;
import lol.vifez.api.command.annotation.CommandAlias;
import lol.vifez.api.command.annotation.DefaultCommand;
import lol.vifez.api.command.annotation.Subcommand;
import lol.vifez.api.example.config.ExampleConfig;
import org.bukkit.command.CommandSender;

@CommandAlias("example")
@CommandPermission("example.permission")
public class ExampleCommand {

    private final ExampleConfig config;

    public ExampleCommand(ConfigAPI configAPI) {
        this.config = configAPI.fromFile("messages", ExampleConfig.class);
    }

    @DefaultCommand
    public void onDefault(CommandSender sender, String[] args) {
        sender.sendMessage(CC.translate(config.defaultMessage));
    }

    @Subcommand("greet")
    public void onGreet(CommandSender sender, String[] args) {
        String message = config.greetMessage.replace("{name}", sender.getName());
        sender.sendMessage(CC.translate(message));
    }
}