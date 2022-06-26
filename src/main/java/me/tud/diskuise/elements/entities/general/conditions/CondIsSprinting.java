package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Sprinting")
@Description("Checks whether the disguise is sprinting")
@Examples("if player's disguise is sprinting:")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class CondIsSprinting extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsSprinting.class, "(sprinting|crouching)");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.isSprinting();
    }

    @Override
    protected String getPropertyName() {
        return "sprinting";
    }
}
