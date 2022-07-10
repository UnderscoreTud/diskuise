package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Sneaking")
@Description("Checks whether the disguise is sneaking")
@Examples("if player's disguise is sneaking:")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondIsSneaking extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsSneaking.class, "(sneaking|crouching)");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.isSneaking();
    }

    @Override
    protected String getPropertyName() {
        return "sneaking";
    }
}
