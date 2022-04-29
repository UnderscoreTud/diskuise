package me.tud.diskuise.elements.watchers.areaeffectcloud.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.AreaEffectCloudWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("AoE Disguise - Radius")
@Description("Set or get the radius of an area of effect cloud disguise")
@Examples("set the cloud radius of {_disguise} to 10")
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseRadius extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprDisguiseRadius.class, Number.class, ExpressionType.PROPERTY,
                "[the] [(area [of effect]|AoE)] cloud radius of [dis(k|g)uise] %disguise%",
                "[the] [dis(k|g)uise] %disguise%'s [(area [of effect]|AoE)] cloud radius");
    }

    Expression<Disguise> disguise;

    @Override
    protected Number[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        AreaEffectCloudWatcher watcher;
        try {
            watcher = (AreaEffectCloudWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        return new Number[]{watcher.getRadius()};
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
        if (mode == Changer.ChangeMode.SET ||
                mode == Changer.ChangeMode.ADD ||
                mode == Changer.ChangeMode.REMOVE ||
                mode == Changer.ChangeMode.RESET) return CollectionUtils.array(Number.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (delta[0] == null) return;
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        AreaEffectCloudWatcher watcher;
        try {
            watcher = (AreaEffectCloudWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }

        float value = ((Number) delta[0]).floatValue();
        float radius = watcher.getRadius();

        if (mode != Changer.ChangeMode.SET && mode != Changer.ChangeMode.RESET) {
            if (mode == Changer.ChangeMode.REMOVE) value *= -1;
            radius += value;
        }
        else if (mode == Changer.ChangeMode.RESET) radius = 3f;
        else radius = value;
        watcher.setRadius(radius);
        DisguiseUtil.update(disguise);
    }
}
