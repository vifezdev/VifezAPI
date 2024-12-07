package lol.vifez.api;

import lol.vifez.api.command.CommandHandler;
import lol.vifez.api.config.ConfigAPI;
import lol.vifez.api.example.command.ExampleCommand;
import lol.vifez.api.example.command.MenuCommand;
import lol.vifez.api.menu.listener.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {

    private CommandHandler commandHandler;
    private ConfigAPI configAPI;

    @Override
    public void onEnable() {
        configAPI = new ConfigAPI(this);
        commandHandler = new CommandHandler(this);
        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        commandHandler.register(new ExampleCommand(configAPI));
        commandHandler.register(new MenuCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }
}