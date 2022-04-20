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

@Name("Disguise - Is Invisible")
@Description("Checks if a disguise is invisible")
@Examples({"if {dis} is invisible:",
        "\tbroadcast \"%{dis}% is invisible!\""})
@Since("0.2")
@RequiredPlugins({"LibsDisguises"})
public class CondIsInvisible extends Condition {

    static {
        Skript.registerCondition(CondIsInvisible.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) invisible",
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) visible");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        if (!disguise.isMobDisguise()) return false;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return false;
        return watcher.isInvisible() != isNegated();
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
        if (matchedPattern == 1) setNegated(!isNegated());
        return true;
    }
}
