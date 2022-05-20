package me.tud.diskuise;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.libraryaddict.disguise.DisguiseAPI;
import me.tud.diskuise.listeners.JoinListener;
import me.tud.diskuise.utils.Metrics;
import me.tud.diskuise.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Diskuise extends JavaPlugin {

    private static Diskuise instance;
    private static SkriptAddon addon;
    private static final int resourceId = 101529;

    @Override
    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);

        UpdateChecker updateChecker = new UpdateChecker(this, resourceId);
        updateChecker.checkForUpdates(Bukkit.getConsoleSender());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        new Metrics(this, 14998);

        try {
            addon.loadClasses("me.tud.diskuise", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) DisguiseAPI.undisguiseToAll(player);
    }

    public static Diskuise getInstance() {
        return instance;
    }
    public static SkriptAddon getAddonInstance() {
        return addon;
    }
    public static int getResourceId() {
        return resourceId;
    }
}
