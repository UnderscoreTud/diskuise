package me.tud.diskuise.elements.entities.fallingblock.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Falling Block Disguise - Is Grid Locked")
@Description("Checks whether a falling block disguise is locked to a grid.")
@Examples("if grid of player's disguise is locked")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class CondIsGridLocked extends WatcherPropertyCondition<FallingBlockWatcher> {

    static {
        Skript.registerCondition(CondIsGridLocked.class,
                "grid of [disguise[s]] %disguises% (is|are) locked",
                "grid of [disguise[s]] %disguises% (isn't|is not|aren't|are not) locked");
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
