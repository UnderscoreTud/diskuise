package me.tud.diskuise.elements.entities.arrow.effects;

import ch.njol.skript.doc.*;
import me.tud.diskuise.elements.entities.arrow.BetterTippedArrowWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Arrow Disguise - Make Critical")
@Description("Set whether the arrow disguise is a critical arrow")
@Examples("make player's disguise a critical arrow")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class EffMakeCritical extends WatcherMakeEffect<BetterTippedArrowWatcher> {

    static {
        register(EffMakeCritical.class, "[:not] [a] critical [arrow]");
    }

    @Override
    protected String getProperty() {
        return "a critical arrow";
    }

    @Override
    protected void make(Event e, BetterTippedArrowWatcher tippedArrowWatcher, boolean bool) {
        tippedArrowWatcher.setCritical(bool);
    }
}
