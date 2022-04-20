package me.tud.diskuise.elements.watchers.flag.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Has Custom name")
@Description("Checks if a disguise has a custom name")
@Examples({"if {dis} has a name:",
            "\tbroadcast name of {dis}"})
@Since("0.2")
@RequiredPlugins({"LibsDisguises"})
public class CondHasCustomName extends Condition {

    static {
        Skript.registerCondition(CondHasCustomName.class,
                "[dis(k|g)uise] %disguise% (has|have) [a] [custom[( |-)]]name [set]",
                "[dis(k|g)uise] %disguise% (doesn't|does not|do not|don't) [a] [custom[( |-)]]name [set]");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        if (!disguise.isMobDisguise()) return false;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return false;
        return watcher.hasCustomName() != isNegated();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (matchedPattern == 1) setNegated(true);
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
