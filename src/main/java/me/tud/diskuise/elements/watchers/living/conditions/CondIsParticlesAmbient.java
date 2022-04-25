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

@Name("Living Disguise - Are Particles ambient")
@Description("Checks if the particles of a disguise are ambient")
@Examples({"if particles of {dis} are ambient:",
            "\tset ambient particles of {dis} to false"})
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class CondIsParticlesAmbient extends Condition {

    static {
        Skript.registerCondition(CondIsParticlesAmbient.class,
                "[dis(k|g)uise] [potion[s]][( |-)]particle[s] of %disguise% (1¦(is|are)|2¦(is|are)(n't| not)) ambient[s]");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return false; }
        return watcher.isPotionParticlesAmbient() != isNegated();
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
