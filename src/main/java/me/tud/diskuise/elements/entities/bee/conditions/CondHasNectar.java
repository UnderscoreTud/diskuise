package me.tud.diskuise.elements.entities.bee.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Bee Disguise - Has Nectar")
@Description("Check whether the bee disguise has nectar")
@Examples("if player's disguise has nectar")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class CondHasNectar extends WatcherPropertyCondition<BeeWatcher> {

    static {
        register(CondHasNectar.class, PropertyType.HAVE, "nectar");
    }

    @Override
    protected boolean check(BeeWatcher beeWatcher) {
        return beeWatcher.hasNectar();
    }

    @Override
    protected String getPropertyName() {
        return "nectar";
    }
}
