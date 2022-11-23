package me.tud.diskuise.elements.entities.hostile.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.GhastWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Hostile Disguise - Aggressive")
@Description("Set or get whether a hostile disguise is aggressive.")
@Examples("set aggressive of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprAggressive extends WatcherBooleanExpression<InsentientWatcher> {

    static {
        register(ExprAggressive.class, "[is] aggressive");
    }

    @Override
    protected Boolean convert(InsentientWatcher insentientWatcher) {
        if (insentientWatcher instanceof EndermanWatcher endermanWatcher)
            return endermanWatcher.isAggressive();
        if (insentientWatcher instanceof GhastWatcher ghastWatcher)
            return ghastWatcher.isAggressive();
        return false;
    }

    @Override
    protected String getPropertyName() {
        return "aggressive";
    }

    @Override
    protected void change(Event e, InsentientWatcher insentientWatcher, boolean state) {
        if (insentientWatcher instanceof EndermanWatcher endermanWatcher)
            endermanWatcher.setAggressive(state);
        else if (insentientWatcher instanceof GhastWatcher ghastWatcher)
            ghastWatcher.setAggressive(state);
    }
}
