package me.tud.diskuise.elements.entities.bee.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Bee Disguise - Has Stinger")
@Description("Check whether the bee disguise has stinger")
@Examples("if player's disguise has stinger")
@Since("0.3.3")
@RequiredPlugins("LibsDisguises")
public class CondHasStinger extends WatcherPropertyCondition<BeeWatcher> {

    static {
        register(CondHasStinger.class, PropertyType.HAVE, "stinger");
    }

    @Override
    protected boolean check(BeeWatcher beeWatcher) {
        return !beeWatcher.hasStung();
    }

    @Override
    protected String getPropertyName() {
        return "stinger";
    }
}
