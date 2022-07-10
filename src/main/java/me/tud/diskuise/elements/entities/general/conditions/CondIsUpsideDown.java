package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Upside Down")
@Description("Checks whether the disguise is upside down")
@Examples("if player's disguise is upside down:")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondIsUpsideDown extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsUpsideDown.class, "upside( |-)down");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.isUpsideDown();
    }

    @Override
    protected String getPropertyName() {
        return "upside down";
    }
}
