package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (FileConfig.checkStatus((Player) sender)) {
                if (args.length == 2) {
                    Player targetPlayer = Bukkit.getPlayerExact(args[1]);

                    if (targetPlayer instanceof Player) {
                        targetPlayer.setHealth(20.0d);
                    } else {
                        sender.sendMessage(ChatColor.RED + args[0] + " isn't a valid player");
                    }
                } else if (args.length == 1) {
                    Player player = (Player) sender;

                    player.setHealth(20.0d);
                } else {
                    sender.sendMessage(ChatColor.RED + "Incorrect usage of argument, supposed to be : /fly or /fly {playerName}");
                }
            }
        } else {
            if (args.length == 2) {
                Player targetPlayer = Bukkit.getPlayerExact(args[1]);

                if (targetPlayer instanceof Player) {
                    targetPlayer.setHealth(20.0d);
                } else {
                    sender.sendMessage(ChatColor.RED + args[0] + " isn't a valid player");
                }
            } else {
                sender.sendMessage("Incorrect usage of argument, supposed to be : /fly or /fly {playerName}");
            }
        }

        return true;
    }
}
