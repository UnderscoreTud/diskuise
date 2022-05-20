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
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Living Disguise - Arrows stuck")
@Description("Set or get the amount of arrows stuck in a disguise")
@Examples("set number of arrows stuck in player's disguise to 10")
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseArrowsStuck extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprDisguiseArrowsStuck.class, Number.class, ExpressionType.PROPERTY,
                "[the] [(amount|number) of] arrow[s] [(stuck|sticking)] in [dis(k|g)uise] %disguise%");
    }

    Expression<Disguise> disguise;

    @Override
    protected Number[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        return new Number[]{disguise.getWatcher() instanceof LivingWatcher ?
                ((LivingWatcher) disguise.getWatcher()).getArrowsSticking() : null};
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
        switch (mode) {
            case SET, ADD, REMOVE, REMOVE_ALL -> { return CollectionUtils.array(Number.class); }
        }
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (delta[0] == null) return;
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        LivingWatcher watcher;
        if (disguise.getWatcher() instanceof LivingWatcher) watcher = (LivingWatcher) disguise.getWatcher();
        else return;

        int value = mode != Changer.ChangeMode.REMOVE_ALL ? ((Number) delta[0]).intValue() : 0;
        int arrows = watcher.getArrowsSticking();

        switch (mode) {
            case SET -> arrows = value;
            case REMOVE_ALL -> arrows = 0;
            case ADD, REMOVE -> {
                if (mode == Changer.ChangeMode.REMOVE) value *= -1;
                arrows += value;
            }
        }

        watcher.setArrowsSticking(arrows);
        DisguiseUtil.update(disguise);
    }
}
