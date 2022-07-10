package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Swimming")
@Description("Checks whether the disguise is swimming")
@Examples("if player's disguise is swimming:")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class CondIsSwimming extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsSwimming.class, "swimming");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.isSwimming();
    }

    @Override
    protected String getPropertyName() {
        return "swimming";
    }
}
