package me.tud.diskuise.elements.entities.hostile.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Hostile Disguise - Enraged")
@Description("Set or get whether a hostile disguise is enraged.")
@Examples("set enraged of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprEnraged extends WatcherBooleanExpression<InsentientWatcher> {

    static {
        register(ExprEnraged.class, "[is] enrage[d]");
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
    protected void change(Event e, InsentientWatcher insentientWatcher, boolean state) {
        insentientWatcher.setEnraged(state);
    }
}
