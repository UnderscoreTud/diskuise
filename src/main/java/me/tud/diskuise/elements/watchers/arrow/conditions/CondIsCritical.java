package me.tud.diskuise.elements.watchers.arrow.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.ArrowWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Arrow Disguise - Is Critical")
@Description("Checks if an arrow disguise appears to be critical")
@Examples({"if {dis} is critical:",
            "\tset critical state of {dis} to false"})
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class CondIsCritical extends Condition {

    static {
        Skript.registerCondition(CondIsCritical.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) [arrow] crit[ical][(s|ing)]");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        return disguise.getWatcher() instanceof ArrowWatcher &&
                ((ArrowWatcher) disguise.getWatcher()).isCritical() != isNegated();
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
