package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import lazycore.lazycore.Lazycore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Register implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (FileConfig.getConfig().contains(player.getUniqueId().toString())) {
                    sender.sendMessage(ChatColor.RED + "Please use /login since you are already registered");
                } else {
                    if (args[0].length() >= 3) {
                        FileConfig.getConfig().set(player.getUniqueId().toString(), args[0]);
                        sender.sendMessage(ChatColor.GREEN + "Successfully registered, password : " + args[0]);
                        Lazycore.waitingList.remove(player);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Password length must be longer or equals than 3 characters");
                    }
                }
            } else {
                sender.sendMessage("Incorrect usage of argument, supposed to be : /register {password}");
            }
        } else {
            sender.sendMessage("Console are unable to use this command");
        }

        return true;
    }
}
