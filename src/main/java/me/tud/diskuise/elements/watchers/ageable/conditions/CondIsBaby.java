package me.tud.diskuise.elements.watchers.ageable.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.AgeableWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.ZombieWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Is Baby")
@Description("Checks if a disguise is a baby")
@Examples({"if {dis} is baby:",
        "\tset age of {dis} to adult"})
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsBaby extends Condition {

    static {
        Skript.registerCondition(CondIsBaby.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) [(a|an)] baby");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        MobDisguise mobDisguise = (MobDisguise) disguise;
        assert mobDisguise != null;
        AgeableWatcher ageableWatcher = null;
        ZombieWatcher zombieWatcher = null;
        try {
            ageableWatcher = (AgeableWatcher) mobDisguise.getWatcher();
        } catch (ClassCastException ignored) {}
        try {
            zombieWatcher = (ZombieWatcher) mobDisguise.getWatcher();
        } catch (ClassCastException ignored) {}
        if (ageableWatcher == null && zombieWatcher == null) return false;
        if (ageableWatcher != null) return ageableWatcher.isBaby() == isNegated();
        return zombieWatcher.isBaby() == isNegated();
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
