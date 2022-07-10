package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Sleeping")
@Description("Checks whether the disguise is sleeping")
@Examples("if player's disguise is sleeping:")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class CondIsSleeping extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsSleeping.class, "sleeping");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.isSleeping();
    }

    @Override
    protected String getPropertyName() {
        return "sleeping";
    }
}
