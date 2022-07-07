package me.tud.diskuise.elements.entities.hostile.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Hostile Disguise - Enraged")
@Description("Set or get whether a hostile disguise is enraged.")
@Examples("set enraged of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprEnraged extends WatcherPropertyExpression<InsentientWatcher, Boolean> {

    static {
        register(ExprEnraged.class, Boolean.class, "[is] enrage[d]");
    }

    @Override
    protected Boolean convert(InsentientWatcher insentientWatcher) {
        return insentientWatcher.isEnraged();
    }

    @Override
    protected String getPropertyName() {
        return "enraged";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    protected void change(Event e, InsentientWatcher insentientWatcher, Object[] delta, Changer.ChangeMode mode) {
        insentientWatcher.setEnraged((boolean) delta[0]);
    }
}
