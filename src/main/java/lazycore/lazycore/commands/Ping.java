package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (FileConfig.checkStatus(player)) {
                if (args.length == 1) {
                    Player pingedPlayer = Bukkit.getPlayerExact(args[0]);

                    if (pingedPlayer instanceof Player) {
                        int ping = pingedPlayer.getPing();

                        if (ping <= 30) {
                            sender.sendMessage(ChatColor.GREEN + pingedPlayer.getName() + " ping : " + ping);
                        } else if (ping > 30 && ping <= 60) {
                            sender.sendMessage(ChatColor.YELLOW + pingedPlayer.getName() + " ping : " + ping);
                        } else {
                            sender.sendMessage(ChatColor.RED + pingedPlayer.getName() + " ping : " + ping);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + args[0] + " isn't a valid player");
                    }
                } else if (args.length == 0) {
                    int ping = player.getPing();

                    if (ping <= 30) {
                        sender.sendMessage(ChatColor.GREEN + "Ping : " + ping);
                    } else if (ping > 30 && ping <= 60) {
                        sender.sendMessage(ChatColor.YELLOW + "Ping : " + ping);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Ping : " + ping);
                    }
                } else {
                    sender.sendMessage("Incorrect usage of argument, supposed to be : /ping or /ping {playerName}");
                }
            }
        } else {
            if (args.length == 1) {
                Player pingedPlayer = Bukkit.getPlayerExact(args[0]);

                if (pingedPlayer instanceof Player) {
                    int ping = pingedPlayer.getPing();

                    if (ping <= 30) {
                        sender.sendMessage(ChatColor.GREEN + pingedPlayer.getName() + " ping : " + ping);
                    } else if (ping > 30 && ping <= 60) {
                        sender.sendMessage(ChatColor.YELLOW + pingedPlayer.getName() + " ping : " + ping);
                    } else {
                        sender.sendMessage(ChatColor.RED + pingedPlayer.getName() + " ping : " + ping);
                    }
                } else {
                    sender.sendMessage(args[0] + " isn't a valid player");
                }
            }
        }

        return true;
    }
}
