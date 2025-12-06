package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Is Glowing")
@Description("Checks whether the disguise is glowing")
@Examples({
        "if player's disguise is glowing:",
        "if {disguise} is glowing red:"
})
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondIsGlowing extends Condition {

    static {
        Skript.registerCondition(CondIsGlowing.class,
                "%disguises% (is|are) glowing [%-color%]",
                "%disguises% (isn't|is not|aren't|are not) glowing [%-color%]");
    }

    private Expression<Disguise> disguiseExpr;
    private Expression<SkriptColor> skriptColorExpr;

    @Override
    public boolean check(Event e) {
        return disguiseExpr.check(e, disguise -> {
            if (skriptColorExpr != null) {
                SkriptColor skriptColor = skriptColorExpr.getSingle(e);
                if (skriptColor == null) return false;
                return disguise.getWatcher().getGlowColor().equals(skriptColor.asChatColor());
            }
            return disguise.getWatcher().isGlowing();
        }, isNegated());
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return disguiseExpr.toString(e, debug) + (disguiseExpr.isSingle() ? " is " : " are ") + (isNegated() ? "not " : "") + "glowing"
                + (skriptColorExpr == null ? "" : " " + skriptColorExpr.toString(e, debug));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguiseExpr = (Expression<Disguise>) exprs[0];
        skriptColorExpr = (Expression<SkriptColor>) exprs[1];
        setNegated(matchedPattern == 1);
        return true;

    }
}
