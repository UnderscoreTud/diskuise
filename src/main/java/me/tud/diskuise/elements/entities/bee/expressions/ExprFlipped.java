package me.tud.diskuise.elements.entities.bee.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Bee Disguise - Flipped")
@Description("Set or get whether the bee disguise is flipped")
@Examples("set flipped state of player's disguise to true")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class ExprFlipped extends WatcherBooleanExpression<BeeWatcher> {

    static {
        register(ExprFlipped.class, "flipped");
    }

    @Override
    protected Boolean convert(BeeWatcher beeWatcher) {
        return beeWatcher.isFlipped();
    }

    @Override
    protected String getPropertyName() {
        return "flipped";
    }

    @Override
    protected void change(Event e, BeeWatcher beeWatcher, boolean state) {
        beeWatcher.setFlipped(state);
    }
}
