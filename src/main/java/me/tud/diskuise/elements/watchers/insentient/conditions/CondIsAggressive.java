package me.tud.diskuise.elements.watchers.insentient.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.GhastWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Insentient Disguise - Is Aggressive")
@Description("Checks if an insentient disguise is aggressive")
@Examples({"if {dis} is aggressive:",
            "\tset aggressive state of {dis} to false"})
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class CondIsAggressive extends Condition {

    static {
        Skript.registerCondition(CondIsAggressive.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) aggressive");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        boolean isAggressive = false;
        if (disguise.getWatcher() instanceof EndermanWatcher) isAggressive = ((EndermanWatcher) disguise.getWatcher()).isAggressive();
        else if (disguise.getWatcher() instanceof GhastWatcher) isAggressive = ((GhastWatcher) disguise.getWatcher()).isAggressive();
        return isAggressive != isNegated();
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