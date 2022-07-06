package me.tud.diskuise.elements.entities.fallingblock.conditions;

import ch.njol.skript.Skript;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class CondIsGridLocked extends WatcherPropertyCondition<FallingBlockWatcher> {

    static {
        Skript.registerCondition(CondIsGridLocked.class,
                "grid of [dis(g|k)uise[s]] %disguises% (is|are) locked",
                "grid of [dis(g|k)uise[s]] %disguises% (isn't|is not|aren't|are not) locked");
    }

    @Override
    protected boolean check(FallingBlockWatcher fallingBlockWatcher) {
        return fallingBlockWatcher.isGridLocked();
    }

    @Override
    protected String getPropertyName() {
        return null;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "grid of " + getExpr().toString(e, debug) + "is locked";
    }
}
