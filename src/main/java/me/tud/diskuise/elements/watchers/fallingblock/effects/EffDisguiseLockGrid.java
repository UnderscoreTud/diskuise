package me.tud.diskuise.elements.watchers.fallingblock.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Falling Block Disguise - Lock grid")
@Description("Sets if a falling block disguise is locked to a grid")
@Examples({"make player's disguise grid locked", "lock {_dis}'s grid"})
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseLockGrid extends Effect {

    static {
        Skript.registerEffect(EffDisguiseLockGrid.class,
                "make [dis(k|g)uise] %disguise% [1¦not] grid[( |-)]lock[ed]",
                "[1¦un]lock [dis(k|g)uise] %disguise%'s grid",
                "[1¦un]lock grid of [dis(k|g)uise] %disguise%");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        FallingBlockWatcher watcher;
        try {
            watcher = (FallingBlockWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        watcher.setGridLocked(bool);
        DisguiseUtil.update(disguise);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        bool = parseResult.mark != 1;
        return true;
    }
}
