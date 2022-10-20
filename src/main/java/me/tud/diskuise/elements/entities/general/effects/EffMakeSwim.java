package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Disguise - Make Swim")
@Description("Sets whether the disguise is swimming")
@Examples("make player's disguise swim")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class EffMakeSwim extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeSwim.class, "[:not] swim");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean state) {
        flagWatcher.setSwimming(state);
    }

    @Override
    protected String getProperty() {
        return "swim";
    }
}
