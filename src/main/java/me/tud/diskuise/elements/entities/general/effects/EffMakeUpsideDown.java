package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Disguise - Make Upside Down")
@Description("Sets whether the disguise is upside down")
@Examples("make player's disguise upside down")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeUpsideDown extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeUpsideDown.class, "([:not] upside( |-)down|not:right[ ]side( |-)up)");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean state) {
        flagWatcher.setUpsideDown(state);
    }

    @Override
    protected String getProperty() {
        return "upside down";
    }
}
