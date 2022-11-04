package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import lazycore.lazycore.Lazycore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Login implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (FileConfig.getConfig().contains(player.getUniqueId().toString())) {
                    if (FileConfig.getConfig().get(player.getUniqueId().toString()) == args[0]) {
                        Lazycore.waitingList.remove(player);
                        sender.sendMessage(ChatColor.GREEN + "Logged in !");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Incorrect password");
                    }
                } else {
                    sender.sendMessage("Please use /register first since you are not registered");
                }
            } else {
                sender.sendMessage("Incorrect usage of argument, supposed to be : /login {password}");
            }
        } else {
            sender.sendMessage("Console are unable to use this command");
        }

        return true;
    }
}
