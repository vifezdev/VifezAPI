package lol.vifez.api.menu;

import lol.vifez.api.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuHandler {

    public void handleInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu menu = Menu.getOpenedMenus().get(player);

        if (menu != null) {
            event.setCancelled(true);
            Button button = menu.getButtons(player).get(event.getSlot());
            if (button != null) {
                button.clicked(player, event.getClick());
            }
        }
    }
}