package me.tud.diskuise;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.libraryaddict.disguise.DisguiseAPI;
import me.tud.diskuise.utils.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Diskuise extends JavaPlugin {

    private Diskuise instance;
    private SkriptAddon addon;

    @Override
    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);
        Metrics metrics = new Metrics(this, 14998);
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

    public Diskuise getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
}
