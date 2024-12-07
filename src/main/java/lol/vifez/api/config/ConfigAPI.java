package lol.vifez.api.config;

import org.bukkit.plugin.Plugin;

public class ConfigAPI {

    private final ConfigHandler configHandler;

    public ConfigAPI(Plugin plugin) {
        this.configHandler = new ConfigHandler(plugin);
    }

    public <T> T fromFile(String fileName, Class<T> configClass) {
        return configHandler.load(fileName, configClass);
    }

    public void save(String fileName, Object config) {
        configHandler.save(fileName, config);
    }
}