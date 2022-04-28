package me.tud.diskuise.elements.watchers.player.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Is Jacket enabled")
@Description("Checks if the jacket of a player disguise is enabled")
@Examples({"if jacket of {dis} is enabled:",
            "\tset jacket of {dis} to false"})
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class CondIsJacketEnabled extends Condition {

    static {
        Skript.registerCondition(CondIsJacketEnabled.class,
                "jacket of [dis(k|g)uise] %disguise% [(1¦is|2¦is(n't| not))] enabled",
                "jacket of [dis(k|g)uise] %disguise% [(1¦is|2¦is(n't| not))] disabled");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        PlayerWatcher watcher;
        try {
            watcher = (PlayerWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return false; }
        return watcher.isJacketEnabled() != isNegated();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.mark == 2) setNegated(true);
        if (matchedPattern == 1) setNegated(!isNegated());
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
