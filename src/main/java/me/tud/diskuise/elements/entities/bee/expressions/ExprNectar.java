package me.tud.diskuise.elements.entities.bee.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Bee Disguise - Nectar")
@Description("Set or get whether the bee disguise has nectar")
@Examples("set has nectar state of player's disguise to true")
@Since("0.3.3")
@RequiredPlugins("LibsDisguises")
public class ExprNectar extends WatcherBooleanExpression<BeeWatcher> {

    static {
        register(ExprNectar.class, "[has] nectar");
    }

    @Override
    protected Boolean convert(BeeWatcher beeWatcher) {
        return beeWatcher.hasNectar();
    }

    @Override
    protected String getPropertyName() {
        return "has nectar";
    }

    @Override
    protected void change(Event e, BeeWatcher beeWatcher, boolean state) {
        beeWatcher.setHasNectar(state);
    }
}
