package me.tud.diskuise.elements.entities.arrow.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.tud.diskuise.elements.entities.arrow.BetterTippedArrowWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Arrow Disguise - Critical")
@Description("Set or get whether the arrow disguise is a critical arrow")
@Examples("set critical of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprCritical extends WatcherPropertyExpression<BetterTippedArrowWatcher, Boolean> {

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
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    protected void change(Event e, BetterTippedArrowWatcher tippedArrowWatcher, Object[] delta, Changer.ChangeMode mode) {
        tippedArrowWatcher.setCritical((boolean) delta[0]);
    }
}
