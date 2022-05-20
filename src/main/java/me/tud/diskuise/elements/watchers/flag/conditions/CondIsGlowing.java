package me.tud.diskuise.elements.watchers.flag.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Is Glowing")
@Description("Checks if a disguise is appears to be glowing")
@Examples({"if {dis} is glowing red:",
            "\tset glow color of {dis} to blue"})
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsGlowing extends Condition {

    static {
        Skript.registerCondition(CondIsGlowing.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) glow[(ing|s)] [%-color%]");
    }

    Expression<Disguise> disguise;
    Expression<Color> color;


    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        SkriptColor color = this.color != null ? (SkriptColor) this.color.getSingle(e) : null;
        if (disguise == null || disguise.getWatcher() == null) return false;
        if (color != null && disguise.getWatcher().isGlowing()) {
            if (color.asChatColor() == disguise.getWatcher().getGlowColor()) return disguise.getWatcher().isGlowing() != isNegated();
            return disguise.getWatcher().isGlowing() == isNegated();
        }
        return disguise.getWatcher().isGlowing() != isNegated();
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
        if (exprs.length == 2) color = (Expression<Color>) exprs[1];
        return true;
    }
}
