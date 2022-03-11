package pl.rettyz.rchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import pl.rettyz.rchat.listeners.ChatCMD;
import pl.rettyz.rchat.utils.ColorUtil;

public class Chat implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[RChat] You cannot do this from the console!");
            sender.sendMessage(" ");
            return false;
        } else {
            Player p = (Player) sender;
            if(!(p.hasPermission("rchat.admin") || p.isOp())){
                p.sendMessage(" ");
                p.sendMessage(ColorUtil.fixZ("&8>> &7You don't have permissions (&crchat.admin&7)!"));
                p.sendMessage(" ");
                return false;
            } else {
                ChatCMD.showInv((Player) sender);
            }
            return true;
        }
    }
}