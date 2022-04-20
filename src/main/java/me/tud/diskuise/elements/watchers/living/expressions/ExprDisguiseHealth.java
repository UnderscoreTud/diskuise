package me.tud.diskuise.elements.watchers.living.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Health")
@Description("Set or get a disguise's Health")
@Examples({"set health of player's disguise to 10", "remove 1 from health of player's disguise", "add 60 to health of player's disguise"})
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseHealth extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprDisguiseHealth.class, Number.class, ExpressionType.PROPERTY,
                "[the] (health|hp|health[( |-)]point[s]) [value] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s (health|hp|health[( |-)]point[s]) [value]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Number[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        return new Number[]{watcher.getHealth()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) return CollectionUtils.array(Number.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (delta[0] == null) return;
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }

        float value = ((Number) delta[0]).floatValue();
        float health = watcher.getHealth();

        if (mode != Changer.ChangeMode.SET) {
            if (mode == Changer.ChangeMode.REMOVE) value *= -1;
            health += value;
        }
        else health = value;
        watcher.setHealth(health);
    }
}
