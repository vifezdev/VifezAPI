package lol.vifez.api.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    public abstract ItemStack getButtonItem(Player player);

    public void clicked(Player player, ClickType clickType) {}

}