package me.tud.diskuise.elements.watchers.axolotl.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Axolotl Disguise - Is Playing dead")
@Description("Checks if an axolotl disguise appears to be playing dead")
@Examples({"if {dis} is playing dead:",
            "\tset playing dead state of {dis} to false"})
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class CondIsPlayingDead extends Condition {

    static {
        Skript.registerCondition(CondIsPlayingDead.class,
                "[dis(k|g)uise] %disguise% [(1¦is|2¦is(n't| not))] play[(ing|s)] dead");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        AxolotlWatcher watcher;
        try {
            watcher = (AxolotlWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return false; }
        return watcher.isPlayingDead() != isNegated();
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
