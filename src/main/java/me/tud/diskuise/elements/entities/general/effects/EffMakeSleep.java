package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Disguise - Make Sleep")
@Description("Sets whether the disguise is sleeping")
@Examples("make player's disguise sleep")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class EffMakeSleep extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeSleep.class, "([:not] sleep|not:wake up)");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean state) {
        flagWatcher.setSleeping(state);
    }

    @Override
    protected String getProperty() {
        return "sleep";
    }
}
