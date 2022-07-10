package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Disguise - Make Invisible")
@Description("Sets whether the disguise is invisible")
@Examples("make player's disguise invisible")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeInvisible extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeInvisible.class, "(:not |not:in)visible");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher) {
        flagWatcher.setInvisible(isNegated());
    }

    @Override
    protected String getProperty() {
        return "visible";
    }
}
