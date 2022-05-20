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

@Name("Player Disguise - Is Left pants enabled")
@Description("Checks if the left pants of a player disguise are enabled")
@Examples({"if left pants of {dis} are enabled:",
            "\tset left pants of {dis} to false"})
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class CondIsLeftPantsEnabled extends Condition {

    static {
        Skript.registerCondition(CondIsLeftPantsEnabled.class,
                "left pants of [dis(k|g)uise] %disguise% [(1¦are|2¦are(n't| not))] enabled",
                "left pants of [dis(k|g)uise] %disguise% [(1¦are|2¦are(n't| not))] disabled");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        return disguise.getWatcher() instanceof PlayerWatcher &&
                ((PlayerWatcher) disguise.getWatcher()).isLeftPantsEnabled() != isNegated();
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
