package me.tud.diskuise.elements.entities.hostile.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Hostile Disguise - Make Aggressive")
@Description("Sets whether a hostile disguise is aggressive.")
@Examples("make player's disguise aggressive")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class EffMakeAggressive extends WatcherMakeEffect<EndermanWatcher> {

    static {
        register(EffMakeAggressive.class, "[:not] aggressive");
    }

    @Override
    protected String getProperty() {
        return "aggressive";
    }

    @Override
    protected void make(Event e, EndermanWatcher endermanWatcher) {
        endermanWatcher.setAggressive(!isNegated());
    }
}
