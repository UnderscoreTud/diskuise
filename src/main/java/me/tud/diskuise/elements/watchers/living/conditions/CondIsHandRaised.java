package me.tud.diskuise.elements.watchers.living.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Is Hand raised")
@Description("Checks if a disguise has its main hand or offhand raised")
@Examples({"if {dis} is hand raised:",
            "\tset offhand raised state of {dis} to false"})
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsHandRaised extends Condition {

    static {
        Skript.registerCondition(CondIsHandRaised.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) [main[( |-)]]hand rais(e[(d|s)]|ing)",
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) off[( |-)]hand rais(e[(d|s)]|ing)");
    }

    Expression<Disguise> disguise;
    boolean isMainHand = true;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return false; }
        if (!isMainHand) return watcher.isOffhandRaised() != isNegated();
        return watcher.isMainHandRaised() != isNegated();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.mark == 2) setNegated(true);
        if (matchedPattern == 1) isMainHand = false;
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
