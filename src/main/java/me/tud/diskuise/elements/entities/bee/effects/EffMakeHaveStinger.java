package me.tud.diskuise.elements.entities.bee.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Bee Disguise - Have Stinger")
@Description("Set whether the bee disguise has stinger")
@Examples("make {_disguise} have stinger")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class EffMakeHaveStinger extends WatcherMakeEffect<BeeWatcher> {

    static {
        register(EffMakeHaveStinger.class, "[:not] have [a] stinger");
    }

    @Override
    protected String getProperty() {
        return "have stinger";
    }

    @Override
    protected void make(Event e, BeeWatcher beeWatcher, boolean state) {
        beeWatcher.setHasStung(!state);
    }
}
