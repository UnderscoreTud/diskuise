package me.tud.diskuise.templates;

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

@Name("Disguise - Is Templating")
@Description("Checks if a disguise appears to be templating")
@Examples({"if {dis} is templating:",
            "\tset templating state of {dis} to false"})
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsTemplating extends Condition {

    static {
        Skript.registerCondition(CondIsTemplating.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) templating");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        if (!disguise.isMobDisguise()) return false;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return false;
        return watcher.isBurning() != isNegated();
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
