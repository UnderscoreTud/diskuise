package me.tud.diskuise.elements.entities.bee.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Bee Disguise - Is Flipped")
@Description("Check whether the bee disguise is flipped")
@Examples("if player's disguise is flipped")
@Since("0.3.3")
@RequiredPlugins("LibsDisguises")
public class CondIsFlipped extends WatcherPropertyCondition<BeeWatcher> {

    static {
        register(CondIsFlipped.class, "flipped");
    }

    @Override
    protected boolean check(BeeWatcher beeWatcher) {
        return beeWatcher.isFlipped();
    }

    @Override
    protected String getPropertyName() {
        return "flipped";
    }
}
