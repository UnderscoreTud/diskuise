package me.tud.diskuise;

import ch.njol.skript.Skript;
import ch.njol.skript.util.Version;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.skriptlang.skript.addon.SkriptAddon;

public final class Diskuise extends JavaPlugin {

    private static final int BSTATS_SERVICE_ID = 14998;

    private static Diskuise instance;
    private SkriptAddon addon;

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("LibsDisguises")) {
            getLogger().severe("Could not find LibsDisguises! Disabling Diskuise...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            getLogger().severe("Could not find ProtocolLib! Disabling Diskuise...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (Skript.getVersion().isSmallerThan(new Version(2, 10))) {
            getLogger().severe("Diskuise requires Skript 2.10 or higher! Disabling Diskuise...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;
        addon = Skript.instance().registerAddon(getClass(), "Diskuise");
        addon.localizer().setSourceDirectories("lang", null);

        Metrics metrics = new Metrics(this, BSTATS_SERVICE_ID);
        metrics.addCustomChart(new SimplePie("skript_version", () -> Skript.getVersion().toString()));
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    public static Diskuise getInstance() {
        return instance;
    }

}
