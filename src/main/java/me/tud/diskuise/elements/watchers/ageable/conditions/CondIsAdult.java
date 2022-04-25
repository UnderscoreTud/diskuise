package me.tud.diskuise.elements.watchers.ageable.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Ageable Disguise - Is Adult")
@Description("Checks if a disguise is an adult")
@Examples({"if {dis} is adult:",
        "\tset age of {dis} to baby"})
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsAdult extends Condition {

    static {
        Skript.registerCondition(CondIsAdult.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) [(a|an)] adult");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        MobDisguise mobDisguise = (MobDisguise) disguise;
        assert mobDisguise != null;
        LivingWatcher watcher = mobDisguise.getWatcher();
        try {
            return ((AgeableWatcher) watcher).isBaby() != isNegated();
        } catch (ClassCastException ignore) {}
        try {
            return ((ZombieWatcher) watcher).isBaby() != isNegated();
        } catch (ClassCastException ignore) {}
        try {
            return ((PiglinWatcher) watcher).isBaby() != isNegated();
        } catch (ClassCastException ignore) {}
        try {
            return ((ZoglinWatcher) watcher).isBaby() != isNegated();
        } catch (ClassCastException ignore) {}
        return false;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.mark == 1) setNegated(true);
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
