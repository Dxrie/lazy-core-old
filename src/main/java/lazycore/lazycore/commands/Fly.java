package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (FileConfig.checkStatus(player)) {
                if (args.length == 0) {
                    player.setAllowFlight(!player.getAllowFlight());
                } else if (args.length == 1) {
                    Player targetPlayer = Bukkit.getPlayerExact(args[0]);

                    if (targetPlayer instanceof Player) {
                        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());
                    } else {
                        sender.sendMessage(ChatColor.RED + args[0] + " isn't a valid player");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Incorrect usage of argument, supposed to be : /fly or /fly {playerName}");
                }
            }
        } else {
            if (args.length == 2) {
                Player targetPlayer = Bukkit.getPlayerExact(args[1]);

                if (targetPlayer instanceof Player) {
                    targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());
                } else {
                    sender.sendMessage(args[0] + " isn't a valid player");
                }
            } else {
                sender.sendMessage("Incorrect usage of argument, supposed to be : /fly {playerName}");
            }
        }

        return true;
    }
}
