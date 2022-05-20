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

@Name("Disguise - Is Custom name visible")
@Description("Checks if a disguise has its custom name visible")
@Examples({"if custom name of {dis} is visible:",
            "\tset custom name visible state of {dis} to false"})
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsCustomNameVisible extends Condition {

    static {
        Skript.registerCondition(CondIsCustomNameVisible.class,
                "[custom[( |-)]]name of [dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) visible");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        return disguise.getWatcher() != null &&
                disguise.getWatcher().isCustomNameVisible() != isNegated();
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
