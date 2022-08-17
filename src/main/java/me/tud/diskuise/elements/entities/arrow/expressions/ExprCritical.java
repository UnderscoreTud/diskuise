package me.tud.diskuise.elements.entities.arrow.expressions;

import ch.njol.skript.doc.*;
import me.tud.diskuise.elements.entities.arrow.BetterTippedArrowWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Arrow Disguise - Critical")
@Description("Set or get whether the arrow disguise is a critical arrow")
@Examples("set critical of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprCritical extends WatcherBooleanExpression<BetterTippedArrowWatcher> {

    static {
        register(ExprCritical.class, Boolean.class, "critical");
    }

    @Override
    protected Boolean convert(BetterTippedArrowWatcher tippedArrowWatcher) {
        return tippedArrowWatcher.isCritical();
    }

    @Override
    protected String getPropertyName() {
        return "critical";
    }

    @Override
    protected void change(Event e, BetterTippedArrowWatcher tippedArrowWatcher, boolean bool) {
        tippedArrowWatcher.setCritical(bool);
    }
}
