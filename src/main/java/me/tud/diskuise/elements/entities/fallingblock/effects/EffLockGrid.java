package me.tud.diskuise.elements.entities.fallingblock.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Falling Block Disguise - Lock Grid")
@Description("Sets whether a falling block disguise is locked to a grid.")
@Examples({
        "make player's disguise grid locked",
        "lock {_dis}'s grid"
})
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class EffLockGrid extends WatcherMakeEffect<FallingBlockWatcher> {

    static {
        Skript.registerEffect(EffLockGrid.class, "[not:un]lock grid of [disguise[s]] %disguises%",
                "[not:un]lock [disguise[s]] %disguises%'[s] grid",
                "make [disguise[s]] %disguises% [:not] grid[ ]lock[ed]");
    }

    @Override
    protected String getProperty() {
        return null;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "lock grid of " + getExpr().toString(e, debug);
    }

    @Override
    protected void make(Event e, FallingBlockWatcher fallingBlockWatcher, boolean state) {
        fallingBlockWatcher.setGridLocked(state);
    }
}
