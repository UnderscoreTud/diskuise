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

@Name("Player Disguise - Has Shoulder parrot")
@Description("Checks if the player disguise has a parrot on either of its shoulders")
@Examples({"if right shoulder of {dis} has a parrot:",
            "\tset left shoulder parrot of {dis} to true"})
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class CondHasShoulderParrot extends Condition {

    static {
        Skript.registerCondition(CondHasShoulderParrot.class,
                "right [shoulder] of [dis(k|g)uise] %disguise% [(1¦has|2¦does(n't| not) have)] [a] parrot",
                "left [shoulder] of [dis(k|g)uise] %disguise% [(1¦has|2¦does(n't| not) have)] [a] parrot");
    }

    Expression<Disguise> disguise;
    boolean isRight = true;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        PlayerWatcher watcher;
        try {
            watcher = (PlayerWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return false; }
        if (isRight) return watcher.isRightShoulderHasParrot() != isNegated();
        else return watcher.isLeftShoulderHasParrot() != isNegated();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.mark == 2) setNegated(true);
        if (matchedPattern == 1) isRight = false;
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
