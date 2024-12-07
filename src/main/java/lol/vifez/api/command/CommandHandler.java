package lol.vifez.api.command;

import lol.vifez.api.command.annotation.CommandAlias;
import lol.vifez.api.command.annotation.CommandPermission;
import lol.vifez.api.command.annotation.DefaultCommand;
import lol.vifez.api.command.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private final Plugin plugin;
    private final CommandMap commandMap;
    private final Map<String, CommandAPI> commands = new HashMap<>();

    public CommandHandler(Plugin plugin) {
        this.plugin = plugin;
        this.commandMap = getCommandMap();
    }

    public void register(Object commandObject) {
        Class<?> clazz = commandObject.getClass();

        if (!clazz.isAnnotationPresent(CommandAlias.class)) {
            throw new IllegalArgumentException("Missing @CommandAlias annotation.");
        }

        CommandAlias alias = clazz.getAnnotation(CommandAlias.class);
        String mainCommand = alias.value();

        CommandAPI commandAPI = new CommandAPI(mainCommand, isPlayerOnly(clazz), getPermission(clazz)) {
            @Override
            public void execute(CommandSender sender, String[] args) {
                handleCommand(commandObject, sender, args);
            }
        };

        commands.put(mainCommand, commandAPI);

        Bukkit.getScheduler().runTask(plugin, () -> registerCommand(commandAPI));
    }

    private void handleCommand(Object commandObject, CommandSender sender, String[] args) {
        Class<?> clazz = commandObject.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DefaultCommand.class) && args.length == 0) {
                invokeCommand(method, commandObject, sender, args);
                return;
            }

            if (method.isAnnotationPresent(Subcommand.class)) {
                Subcommand sub = method.getAnnotation(Subcommand.class);
                if (args.length > 0 && args[0].equalsIgnoreCase(sub.value())) {
                    invokeCommand(method, commandObject, sender, args);
                    return;
                }
            }
        }

        sender.sendMessage("§cInvalid api usage.");
    }

    private void invokeCommand(Method method, Object commandObject, CommandSender sender, String[] args) {
        try {
            method.setAccessible(true);
            if (method.isAnnotationPresent(CommandPermission.class)) {
                String permission = method.getAnnotation(CommandPermission.class).value();
                if (!sender.hasPermission(permission)) {
                    sender.sendMessage("§cYou do not have permission to execute this api.");
                    return;
                }
            }
            method.invoke(commandObject, sender, args);
        } catch (Exception e) {
            sender.sendMessage("§cAn error occurred while executing the api.");
            e.printStackTrace();
        }
    }

    private void registerCommand(CommandAPI command) {
        try {
            commandMap.register(plugin.getName(), new CommandWrapper(plugin, command));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CommandMap getCommandMap() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to retrieve the command map", e);
        }
    }


    private boolean isPlayerOnly(Class<?> clazz) {
        return clazz.isAnnotationPresent(CommandPermission.class) && clazz.getAnnotation(CommandPermission.class).value().equalsIgnoreCase("player-only");
    }

    private String getPermission(Class<?> clazz) {
        if (clazz.isAnnotationPresent(CommandPermission.class)) {
            return clazz.getAnnotation(CommandPermission.class).value();
        }
        return null;
    }

    private static class CommandWrapper extends Command {
        private final Plugin plugin;
        private final CommandAPI command;

        protected CommandWrapper(Plugin plugin, CommandAPI command) {
            super(command.getName());
            this.plugin = plugin;
            this.command = command;
        }

        @Override
        public boolean execute(CommandSender sender, String label, String[] args) {
            if (command.isPlayerOnly() && !(sender instanceof Player)) {
                sender.sendMessage("§cOnly players can use this api.");
                return true;
            }

            if (!command.hasPermission(sender)) {
                sender.sendMessage("§cYou do not have permission to use this api.");
                return true;
            }

            command.execute(sender, args);
            return true;
        }
    }
}