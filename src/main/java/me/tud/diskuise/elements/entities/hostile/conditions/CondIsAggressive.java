package me.tud.diskuise.elements.entities.hostile.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.GhastWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Hostile Disguise - Is Aggressive")
@Description("Checks whether a hostile disguise is aggressive.")
@Examples("if {_disguise} is aggressive")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class CondIsAggressive extends WatcherPropertyCondition<InsentientWatcher> {

    static {
        register(CondIsAggressive.class, "aggressive");
    }

    @Override
    protected boolean check(InsentientWatcher insentientWatcher) {
        if (insentientWatcher instanceof EndermanWatcher endermanWatcher)
            return endermanWatcher.isAggressive();
        if (insentientWatcher instanceof GhastWatcher ghastWatcher)
            return ghastWatcher.isAggressive();
        return false;
    }

    @Override
    protected String getPropertyName() {
        return "aggressive";
    }
}
