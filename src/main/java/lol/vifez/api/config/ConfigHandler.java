package lol.vifez.api.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ConfigHandler {

    private final Plugin plugin;

    public ConfigHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    public <T> T load(String fileName, Class<T> configClass) {
        File file = new File(plugin.getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                T instance = createDefaultFile(file, configClass);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create default configuration: " + fileName, e);
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            T instance = configClass.getDeclaredConstructor().newInstance();
            for (Field field : configClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (config.contains(field.getName())) {
                    Object value = config.get(field.getName());
                    if (field.getType().isAssignableFrom(value.getClass())) {
                        field.set(instance, value);
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration: " + fileName, e);
        }
    }

    public void save(String fileName, Object config) {
        File file = new File(plugin.getDataFolder(), fileName + ".yml");
        YamlConfiguration yamlConfig = new YamlConfiguration();

        try {
            for (Field field : config.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(config);
                if (value != null) {
                    yamlConfig.set(field.getName(), value);
                }
            }
            yamlConfig.save(file);
        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException("Failed to save configuration: " + fileName, e);
        }
    }

    private <T> T createDefaultFile(File file, Class<T> configClass) throws Exception {
        T instance = configClass.getDeclaredConstructor().newInstance();
        YamlConfiguration yamlConfig = new YamlConfiguration();

        for (Field field : configClass.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(instance);
            if (value != null) {
                yamlConfig.set(field.getName(), value);
            }
        }

        yamlConfig.save(file);
        return instance;
    }
}