package me.tud.diskuise.elements.entities.hostile.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.GhastWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Hostile Disguise - Make Aggressive")
@Description("Sets whether a hostile disguise is aggressive.")
@Examples("make player's disguise aggressive")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class EffMakeAggressive extends WatcherMakeEffect<InsentientWatcher> {

    static {
        register(EffMakeAggressive.class, "[:not] aggressive");
    }

    @Override
    protected String getProperty() {
        return "aggressive";
    }

    @Override
    protected void make(Event e, InsentientWatcher insentientWatcher, boolean state) {
        if (insentientWatcher instanceof EndermanWatcher endermanWatcher)
            endermanWatcher.setAggressive(state);
        else if (insentientWatcher instanceof GhastWatcher ghastWatcher)
            ghastWatcher.setAggressive(state);
    }
}
