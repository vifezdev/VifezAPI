package lol.vifez.api.chat;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CC {

    private static Map<String, String> colorMap = new HashMap<>();

    static {
        colorMap.put("Aqua", "&b");
        colorMap.put("Black", "&0");
        colorMap.put("Blue", "&9");
        colorMap.put("Dark Aqua", "&3");
        colorMap.put("Dark Blue", "&1");
        colorMap.put("Dark Gray", "&8");
        colorMap.put("Dark Green", "&2");
        colorMap.put("Dark Purple", "&5");
        colorMap.put("Dark Red", "&4");
        colorMap.put("Gold", "&6");
        colorMap.put("Gray", "&7");
        colorMap.put("Green", "&a");
        colorMap.put("Purple", "&d");
        colorMap.put("Red", "&c");
        colorMap.put("White", "&f");
        colorMap.put("Yellow", "&e");
    }

    public static final String AQUA = ChatColor.AQUA.toString();
    public static final String BLACK = ChatColor.BLACK.toString();
    public static final String BLUE = ChatColor.BLUE.toString();
    public static final String BOLD = ChatColor.BOLD.toString();
    public static final String DARK_AQUA = ChatColor.DARK_AQUA.toString();
    public static final String DARK_BLUE = ChatColor.DARK_BLUE.toString();
    public static final String DARK_GRAY = ChatColor.DARK_GRAY.toString();
    public static final String DARK_GREEN = ChatColor.DARK_GREEN.toString();
    public static final String DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
    public static final String DARK_RED = ChatColor.DARK_RED.toString();
    public static final String GOLD = ChatColor.GOLD.toString();
    public static final String GRAY = ChatColor.GRAY.toString();
    public static final String GREEN = ChatColor.GREEN.toString();
    public static final String ITALICS = ChatColor.ITALIC.toString();
    public static final String PURPLE = ChatColor.LIGHT_PURPLE.toString();
    public static final String RED = ChatColor.RED.toString();
    public static final String RESET = ChatColor.RESET.toString();
    public static final String STRIKETHROUGH = ChatColor.STRIKETHROUGH.toString();
    public static final String UNDERLINE = ChatColor.UNDERLINE.toString();
    public static final String WHITE = ChatColor.WHITE.toString();
    public static final String YELLOW = ChatColor.YELLOW.toString();

    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> translate(List<String> strings) {
        List<String> toReturn = new ArrayList<>();

        for (String string : strings) {
            toReturn.add(translate(string));
        }

        return toReturn;
    }

    public static String[] translate(String[] strings) {
        String[] toReturn = new String[strings.length];

        for (int i = 0; i < strings.length; i++) {
            toReturn[i] = translate(strings[i]);
        }

        return toReturn;
    }

    public static Map<String, String> getColorMap() {
        return colorMap;
    }
}