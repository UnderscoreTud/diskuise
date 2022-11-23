package me.tud.diskuise.elements.entities.bee.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Bee Disguise - Anger")
@Description("Set or get the anger of a bee disguise")
@Examples("set anger of player's disguise to true")
@Since("0.3.3")
@RequiredPlugins("LibsDisguises")
public class ExprBeeAnger extends WatcherBooleanExpression<BeeWatcher> {

    static {
        register(ExprBeeAnger.class, "[bee] anger");
    }

    @Override
    protected Boolean convert(BeeWatcher beeWatcher) {
        return beeWatcher.getBeeAnger() > 0;
    }

    @Override
    protected String getPropertyName() {
        return "bee anger";
    }

    @Override
    protected void change(Event e, BeeWatcher beeWatcher, boolean state) {
        beeWatcher.setBeeAnger(state ? 1 : 0);
    }
}
