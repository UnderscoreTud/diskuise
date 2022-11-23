package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;

@Name("Disguise - Is Custom Name Visible")
@Description("Checks whether the custom name of a disguise is visible")
@Examples("if custom name of player's disguise is visible:")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondIsCustomNameVisible extends Condition {

    static {
        Skript.registerCondition(CondIsCustomNameVisible.class,
                "[custom[ ]]name of [disguise[s]] %disguises% (is|are) visible",
                "[custom[ ]]name of [disguise[s]] %disguises% (isn't|is not|aren't|are not) visible",
                "[custom[ ]]name of [disguise[s]] %disguises% (isn't|is not|aren't|are not) invisible",
                "[custom[ ]]name of [disguise[s]] %disguises% (is|are) invisible");
    }

    private Expression<? extends Disguise> expr;

    @Override
    public boolean check(Event e) {
        Disguise disguise = expr.getSingle(e);
        assert disguise != null;
        return disguise.getWatcher().isCustomNameVisible();
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "custom name of " + expr.toString(e, debug) + (expr.isSingle() ? " is " : " are ") + (isNegated() ? "not " : "") + "visible";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        expr = (Expression<? extends Disguise>) exprs[0];
        setNegated(matchedPattern % 2 == 0);
        return true;
    }
}
