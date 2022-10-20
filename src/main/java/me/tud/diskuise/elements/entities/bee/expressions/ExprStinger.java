package me.tud.diskuise.elements.entities.bee.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Bee Disguise - Stinger")
@Description("Set or get whether the bee disguise has stinger")
@Examples("set has stinger state of player's disguise to true")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class ExprStinger extends WatcherBooleanExpression<BeeWatcher> {

    static {
        register(ExprStinger.class, "[has] stinger");
    }

    @Override
    protected Boolean convert(BeeWatcher beeWatcher) {
        return !beeWatcher.hasStung();
    }

    @Override
    protected String getPropertyName() {
        return "has stinger";
    }

    @Override
    protected void change(Event e, BeeWatcher beeWatcher, boolean state) {
        beeWatcher.setHasStung(!state);
    }
}
