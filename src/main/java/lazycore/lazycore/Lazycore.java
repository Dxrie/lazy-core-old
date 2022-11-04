package lazycore.lazycore;

import lazycore.lazycore.commands.*;
import lazycore.lazycore.listener.ServerHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Lazycore extends JavaPlugin {

    public static Lazycore plugin;
    public static ArrayList<Player> waitingList = new ArrayList<>();
    public static ArrayList<Player> vanishList = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = Lazycore.this;

        new ServerHandler(plugin);

        getCommand("login").setExecutor(new Login());
        getCommand("register").setExecutor(new Register());
        getCommand("vanish").setExecutor(new Vanish(plugin));
        getCommand("ping").setExecutor(new Ping());
        getCommand("filereload").setExecutor(new FileReload());
        getCommand("gmc").setExecutor(new GamemodeCreative());
        getCommand("gms").setExecutor(new GamemodeSurvival());
        getCommand("gmsp").setExecutor(new GamemodeSpectator());
        getCommand("gma").setExecutor(new GamemodeAdventure());
        getCommand("fly").setExecutor(new Fly());

        FileConfig.setup();
        FileConfig.getConfig().options().copyDefaults(true);
        FileConfig.save();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        FileConfig.setup();
        FileConfig.getConfig().options().copyDefaults(true);
        FileConfig.save();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
