package me.tud.diskuise.elements.entities.hostile.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Hostile Disguise - Aggressive")
@Description("Set or get whether a hostile disguise is aggressive.")
@Examples("set aggressive of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprAggressive extends WatcherPropertyExpression<EndermanWatcher, Boolean> {

    static {
        register(ExprAggressive.class, Boolean.class, "[is] enrage[d]");
    }

    @Override
    protected Boolean convert(EndermanWatcher endermanWatcher) {
        return endermanWatcher.isAggressive();
    }

    @Override
    protected String getPropertyName() {
        return "aggressive";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    protected void change(Event e, EndermanWatcher endermanWatcher, Object[] delta, Changer.ChangeMode mode) {
        endermanWatcher.setAggressive((boolean) delta[0]);
    }
}
