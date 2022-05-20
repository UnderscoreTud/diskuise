package me.tud.diskuise.elements.watchers.bat.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.BatWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Bat Disguise - Is Hanging")
@Description("Checks if a bat disguise appears to be hanging upside down")
@Examples({"if {dis} is hanging:",
        "\tset hanging state of {dis} to false"})
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class CondIsHanging extends Condition {

    static {
        Skript.registerCondition(CondIsHanging.class,
                "[dis(k|g)uise] %disguise% [(1¦is|2¦is(n't| not))] hanging [upside[ ]down]");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        return disguise.getWatcher() instanceof BatWatcher &&
                ((BatWatcher) disguise.getWatcher()).isHanging() != isNegated();
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
