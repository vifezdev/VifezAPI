package lol.vifez.api.chat;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Set;

public class Constant {

    public static final Gson GSON = new Gson();
    public static final JsonParser JSON_PARSER = new JsonParser();
    public static final Type SET_STRING_TYPE = new TypeToken<Set<String>>(){}.getType();

    public static final String PLAYER_ONLY = CC.RED + "This command can only be executed by players.";
    public static final String NO_PERMISSION = CC.RED + "No permission.";

    public static final String ITEM_LINE = CC.GRAY + CC.STRIKETHROUGH + "-----------------------------------";
    public static final String CHAT_LINE = CC.GRAY + CC.STRIKETHROUGH + "--------------------------------------------------";

    public static final String INDENT = CC.GRAY + ' ' + Chars.DOUBLE_RIGHT + ' ';

    public static String playerNotOnline(String name) {
        return CC.RED + name + " is not online.";
    }

    public static String dataNotFound(String name) {
        return CC.RED + "No data for player with name " + name + " found.";
    }

}