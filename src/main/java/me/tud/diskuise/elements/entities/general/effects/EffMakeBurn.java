package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Disguise - Make Burn")
@Description("Sets whether the disguise is burning")
@Examples("make player's disguise burn")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeBurn extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeBurn.class, "[:not] (burn|on fire)");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean state) {
        flagWatcher.setBurning(state);
    }

    @Override
    protected String getProperty() {
        return "burn";
    }
}
