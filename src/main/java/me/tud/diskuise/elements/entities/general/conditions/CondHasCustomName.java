package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Has Custom Name")
@Description("Checks whether the disguise has a custom name")
@Examples("if player's disguise has custom name:")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondHasCustomName extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondHasCustomName.class, PropertyType.HAVE, "[a] [custom[ ]]name");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.hasCustomName();
    }

    @Override
    protected String getPropertyName() {
        return "custom name";
    }
}
