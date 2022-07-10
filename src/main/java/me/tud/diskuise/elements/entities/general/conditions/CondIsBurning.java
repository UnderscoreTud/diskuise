package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Burning")
@Description("Checks whether the disguise is burning")
@Examples("if player's disguise is burning:")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondIsBurning extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsBurning.class, "(burning|on fire)");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.isBurning();
    }

    @Override
    protected String getPropertyName() {
        return "burning";
    }
}
