package pl.rettyz.rchat.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import pl.rettyz.rchat.utils.ColorUtil;
import pl.rettyz.rchat.utils.ItemBuilder;

public class ChatCMD implements Listener {

    public static boolean chatDisabled = false;

    public static InventoryView showInv(Player p) {
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 9, ColorUtil.fixZ("&7>> &8Chat management"));

        ItemBuilder ChatOn = (new ItemBuilder(Material.ENCHANTED_BOOK, 1))
                .setTitle(ColorUtil.fixZ("&8>> &aEnable"))
                .addLore(ColorUtil.fixZ("&7Allow messages from clowns."));


        ItemBuilder ChatOff = (new ItemBuilder(Material.ENCHANTED_BOOK, 1))
                .setTitle(ColorUtil.fixZ("&8>> &cDisable"))
                .addLore(ColorUtil.fixZ("&7Let the spammers cry."));

        ItemBuilder ChatClear = (new ItemBuilder(Material.ENCHANTED_BOOK, 1))
                .setTitle(ColorUtil.fixZ("&8>> &eClear"))
                .addLore(ColorUtil.fixZ("&7Hide crimes (⌐■_■)."));

        inv.setItem(3, ChatOn.build());
        inv.setItem(4, ChatOff.build());
        inv.setItem(5, ChatClear.build());

        return p.openInventory(inv);
    }

    @EventHandler
    public void onOpenMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (ColorUtil.fixZ("&7>> &8Chat management").equalsIgnoreCase(e.getView().getTitle())) {
            e.setCancelled(true);

            if (e.getSlot() == 3) {
                chatDisabled = false;
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage(ColorUtil.fixZ("&8>> &7Chat has been &aenabled&7!"));
                Bukkit.broadcastMessage(ColorUtil.fixZ("&8>> &7By: &c") + p.getDisplayName());
                Bukkit.broadcastMessage(" ");
                p.sendMessage(ColorUtil.fixZ("&8>> &aYou enabled chat."));
                p.sendMessage("");
            }
            if (e.getSlot() == 4) {
                chatDisabled = true;
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage(ColorUtil.fixZ("&8>> &7Chat has been &cdisabled&7!"));
                Bukkit.broadcastMessage(ColorUtil.fixZ("&8>> &7By: &c") + p.getDisplayName());
                Bukkit.broadcastMessage(" ");
                p.sendMessage(ColorUtil.fixZ("&8>> &cYou disabled chat."));
                p.sendMessage("");

            }
            if (e.getSlot() == 5) {
                for (int i = 0; i < 100; i++) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(" ");
                    }
                }
                Bukkit.broadcastMessage(ColorUtil.fixZ("&8>> &7Chat has been &ecleared!"));
                Bukkit.broadcastMessage(ColorUtil.fixZ("&8>> &7By: &c") + p.getDisplayName());
                p.sendMessage("");
                p.sendMessage(ColorUtil.fixZ("&8>> &cYou cleared chat."));
                p.sendMessage("");
            }
        }
    }

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e) {
        if (chatDisabled && (!e.getPlayer().hasPermission("rchat.admin") || !e.getPlayer().isOp() || !e.getPlayer().hasPermission("rchat.use"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(" ");
            e.getPlayer().sendMessage(ColorUtil.fixZ("&8>> &7Chat is &cdisabled&7."));
            e.getPlayer().sendMessage(" ");
        }
    }
}