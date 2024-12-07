package lol.vifez.api.example.menu;

import lol.vifez.api.chat.CC;
import lol.vifez.api.menu.button.Button;
import lol.vifez.api.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExampleMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Example Menu";
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(12, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemStack compass = new ItemStack(Material.COMPASS);
                ItemMeta meta = compass.getItemMeta();

                if (meta != null) {
                    meta.setDisplayName(CC.translate("&cInfo"));
                    meta.setLore(Arrays.asList(
                            CC.translate("&7&m------------------------"),
                            CC.translate("&fThis API was developed by &cVifez"),
                            CC.translate("&7To remove this menu, delete the 'example' directory"),
                            CC.translate("&7&m------------------------")
                    ));
                    compass.setItemMeta(meta);
                }

                return compass;
            }

            @Override
            public void clicked(Player player, ClickType clickType) {
                player.sendMessage(CC.translate("&cYou clicked the author info!"));
            }
        });

        buttons.put(14, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                ItemStack eyeOfEnder = new ItemStack(Material.EYE_OF_ENDER);
                ItemMeta meta = eyeOfEnder.getItemMeta();

                if (meta != null) {
                    meta.setDisplayName(CC.translate("&c&lVifez API &7- &c1.1"));
                    meta.setLore(Arrays.asList(
                            CC.translate("&7&m------------------------"),
                            CC.translate("&cFeatures&7:"),
                            CC.translate("&7&m------------------------"),
                            CC.translate("&7* &cCommand API"),
                            CC.translate("&7* &cConfig API"),
                            CC.translate("&7* &cMenu API"),
                            CC.translate("&7* &cChat API"),
                            CC.translate("&7&m------------------------")
                    ));
                    eyeOfEnder.setItemMeta(meta);
                }

                return eyeOfEnder;
            }

            @Override
            public void clicked(Player player, ClickType clickType) {
                player.sendMessage(CC.translate("&chttps://discord.gg/stkWZEKVd5"));
            }
        });

        return buttons;
    }
}