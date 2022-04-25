package me.tud.diskuise.elements.watchers.fallingblock.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Falling Block Disguise - Is Grid locked")
@Description("Checks if a falling block disguise is grid locked")
@Examples({"if {dis} is grid locked:",
            "\tset grid lock state of {dis} to false"})
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class CondIsGridLocked extends Condition {

    static {
        Skript.registerCondition(CondIsGridLocked.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) grid[( |-)]lock[ed]");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        FallingBlockWatcher watcher;
        try {
            watcher = (FallingBlockWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return false; }
        return watcher.isGridLocked() != isNegated();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.mark == 2) setNegated(true);
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
