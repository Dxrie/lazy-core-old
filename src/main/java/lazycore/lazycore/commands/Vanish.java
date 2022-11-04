package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import lazycore.lazycore.Lazycore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {

    Lazycore plugin;

    public Vanish(Lazycore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (FileConfig.checkStatus((Player) sender)) {
                Player player = (Player) sender;

                if (args.length == 0) {
                    if (Lazycore.vanishList.contains(player)) {
                        Lazycore.vanishList.remove(player);
                        sender.sendMessage(ChatColor.YELLOW + "You are now visible to the whole server");

                        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                            onlinePlayers.showPlayer(plugin, player);
                            onlinePlayers.sendMessage(plugin.getConfig().getString("joinMessage").replaceAll("\\{playerName}", player.getName()));
                        }
                    } else {
                        Lazycore.vanishList.add(player);
                        sender.sendMessage(ChatColor.YELLOW + "You are invisible to the whole server except for operators");

                        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                            if (!onlinePlayers.isOp()) {
                                onlinePlayers.hidePlayer(plugin, player);
                            } else {
                                onlinePlayers.sendMessage(player.getName() + " is vanished.");
                            }
                            onlinePlayers.sendMessage(plugin.getConfig().getString("quitMessage").replaceAll("\\{playerName}", player.getName()));
                        }
                    }
                } else if (args.length == 1) {
                    Player selectedPlayer = Bukkit.getPlayerExact(args[0]);

                    if (selectedPlayer instanceof Player) {
                        if (selectedPlayer.isOp()) {
                            if (Lazycore.vanishList.contains(selectedPlayer)) {
                                Lazycore.vanishList.remove(selectedPlayer);
                                selectedPlayer.sendMessage(ChatColor.YELLOW + "You are now visible to the whole server");

                                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                                    onlinePlayers.showPlayer(plugin, selectedPlayer);
                                    onlinePlayers.sendMessage(plugin.getConfig().getString("joinMessage").replaceAll("\\{playerName}", selectedPlayer.getName()));
                                }
                            } else {
                                Lazycore.vanishList.add(selectedPlayer);
                                selectedPlayer.sendMessage(ChatColor.YELLOW + "You are invisible to the whole server except for operators");

                                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                                    if (!onlinePlayers.isOp()) {
                                        onlinePlayers.hidePlayer(plugin, selectedPlayer);
                                    } else {
                                        onlinePlayers.sendMessage(selectedPlayer.getName() + " is vanished.");
                                    }
                                    onlinePlayers.sendMessage(plugin.getConfig().getString("quitMessage").replaceAll("\\{playerName}", selectedPlayer.getName()));
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + selectedPlayer.getName() + " isn't an operator");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + args[0] + " isn't a valid player");
                    }
                } else {
                    sender.sendMessage("Incorrect usage of argument, supposed to be : /vanish or /vanish {playerName}");
                }
            }
        } else {
            if (args.length == 1) {
                Player selectedPlayer = Bukkit.getPlayerExact(args[0]);

                if (selectedPlayer instanceof Player) {
                    if (selectedPlayer.isOp()) {
                        if (Lazycore.vanishList.contains(selectedPlayer)) {
                            Lazycore.vanishList.remove(selectedPlayer);
                            selectedPlayer.sendMessage(ChatColor.YELLOW + "You are now visible to the whole server");

                            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                                onlinePlayers.showPlayer(plugin, selectedPlayer);
                                onlinePlayers.sendMessage(plugin.getConfig().getString("joinMessage").replaceAll("\\{playerName}", selectedPlayer.getName()));
                            }
                        } else {
                            Lazycore.vanishList.add(selectedPlayer);
                            selectedPlayer.sendMessage(ChatColor.YELLOW + "You are invisible to the whole server except for operators");

                            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                                if (!onlinePlayers.isOp()) {
                                    selectedPlayer.hidePlayer(plugin, onlinePlayers);
                                } else {
                                    onlinePlayers.sendMessage(selectedPlayer.getName() + " is vanished.");
                                }
                                onlinePlayers.sendMessage(plugin.getConfig().getString("quitMessage").replaceAll("\\{playerName}", selectedPlayer.getName()));
                            }
                        }
                    } else {
                        sender.sendMessage(selectedPlayer.getName() + " isn't an operator");
                    }
                } else {
                    sender.sendMessage(args[0] + " isn't a valid player");
                }
            } else {
                sender.sendMessage("Incorrect usage of argument, supposed to be : /vanish {playerName}");
            }
        }

        return true;
    }
}
