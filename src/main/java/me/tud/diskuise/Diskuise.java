package me.tud.diskuise;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.command.Commands;
import me.libraryaddict.disguise.DisguiseAPI;
import me.tud.diskuise.commands.DiskuiseCommand;
import me.tud.diskuise.listeners.JoinListener;
import me.tud.diskuise.util.Metrics;
import me.tud.diskuise.util.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Diskuise extends JavaPlugin {

    private static Diskuise instance;
    private SkriptAddon addon;
    private final int resourceId = 101529;
    private UpdateChecker updateChecker;

    @Override
    public void onEnable() {

        if (!Bukkit.getPluginManager().isPluginEnabled("LibsDisguises")) {
            getLogger().severe("Plugin not found: LibsDisguises");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            getLogger().severe("Plugin not found: ProtocolLib");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;
        addon = Skript.registerAddon(this);
        addon.setLanguageFileDirectory("lang");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        updateChecker = new UpdateChecker(this, resourceId);
        updateChecker.checkForUpdates(Bukkit.getConsoleSender());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Metrics metrics = new Metrics(this, 14998);
        metrics.addCustomChart(new Metrics.SimplePie("skript_version", () -> Skript.getInstance().getDescription().getVersion()));

        try {
            addon.loadClasses("me.tud.diskuise", "elements");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        CommandMap commandMap = Commands.getCommandMap();
        if (commandMap == null)
            return;
        commandMap.register("diskuise", new DiskuiseCommand());
    }

    @Override
    public void onDisable() {
        for (World world : Bukkit.getWorlds())
            for (Entity entity : world.getEntities()) DisguiseAPI.undisguiseToAll(entity);
    }

    public static Diskuise getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    public int getResourceId() {
        return resourceId;
    }

    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
}
