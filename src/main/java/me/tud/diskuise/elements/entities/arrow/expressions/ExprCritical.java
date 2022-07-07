package me.tud.diskuise.elements.entities.arrow.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.TippedArrowWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Arrow Disguise - Critical")
@Description("Set or get whether the arrow disguise is a critical arrow")
@Examples("set critical of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprCritical extends WatcherPropertyExpression<TippedArrowWatcher, Boolean> {

    static {
        register(ExprCritical.class, Boolean.class, "critical");
    }

    @Override
    protected Boolean convert(TippedArrowWatcher tippedArrowWatcher) {
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
    protected void change(Event e, TippedArrowWatcher tippedArrowWatcher, Object[] delta, Changer.ChangeMode mode) {
        tippedArrowWatcher.setCritical((boolean) delta[0]);
    }
}
