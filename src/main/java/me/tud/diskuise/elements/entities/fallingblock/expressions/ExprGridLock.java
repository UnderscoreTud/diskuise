package me.tud.diskuise.elements.entities.fallingblock.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Falling Block Disguise - Grid Lock State")
@Description("Set or get whether a falling block disguise is locked to a grid.")
@Examples("set grid lock state of player's disguise to true")
@Since("0.3.3")
@RequiredPlugins("LibsDisguises")
public class ExprGridLock extends WatcherBooleanExpression<FallingBlockWatcher> {

    static {
        register(ExprGridLock.class, "grid lock");
    }

    @Override
    protected Boolean convert(FallingBlockWatcher fallingBlockWatcher) {
        return fallingBlockWatcher.isGridLocked();
    }

    @Override
    protected String getPropertyName() {
        return "grid lock";
    }

    @Override
    protected void change(Event e, FallingBlockWatcher fallingBlockWatcher, boolean state) {
        fallingBlockWatcher.setGridLocked(state);
    }

}
