package me.tud.diskuise.elements.entities.bee.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Bee Disguise - Have Nectar")
@Description("Set whether the bee disguise has nectar")
@Examples("make {_disguise} have nectar")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class EffMakeHaveNectar extends WatcherMakeEffect<BeeWatcher> {

    static {
        register(EffMakeHaveNectar.class, "[:not] have nectar");
    }

    @Override
    protected String getProperty() {
        return "have nectar";
    }

    @Override
    protected void make(Event e, BeeWatcher beeWatcher, boolean state) {
        beeWatcher.setHasNectar(state);
    }
}
