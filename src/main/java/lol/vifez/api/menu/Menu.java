package lol.vifez.api.menu;

import lol.vifez.api.menu.button.Button;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu {

    private static final Map<Player, Menu> openedMenus = new HashMap<>();
    protected Map<Integer, Button> buttons;

    protected Button placeholder = new Button() {
        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        }
    };

    public void open(Player player) {
        String title = getTitle(player).substring(0, Math.min(getTitle(player).length(), 32));
        buttons = getButtons(player);

        int size = getSize();

        Inventory inventory = Bukkit.createInventory(player, size, title);
        for (int i = 0; i < size; i++) {
            buttons.putIfAbsent(i, placeholder);
            inventory.setItem(i, buttons.get(i).getButtonItem(player));
        }

        openedMenus.put(player, this);
        player.openInventory(inventory);
    }

    public abstract String getTitle(Player player);

    public abstract Map<Integer, Button> getButtons(Player player);

    /**
     * Define the inventory size in the menu implementation.
     * Must be a multiple of 9 (e.g., 9, 18, 27, etc.).
     */
    public abstract int getSize();

    public static Map<Player, Menu> getOpenedMenus() {
        return openedMenus;
    }
}