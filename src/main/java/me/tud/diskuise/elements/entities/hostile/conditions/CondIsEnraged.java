package me.tud.diskuise.elements.entities.hostile.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Hostile Disguise - Is Enraged")
@Description("Checks whether a hostile disguise is enraged.")
@Examples("if {_disguise} is enraged")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class CondIsEnraged extends WatcherPropertyCondition<InsentientWatcher> {

    static {
        register(CondIsEnraged.class, "enraged");
    }

    @Override
    protected boolean check(InsentientWatcher insentientWatcher) {
        return insentientWatcher.isEnraged();
    }

    @Override
    protected String getPropertyName() {
        return "enraged";
    }
}
