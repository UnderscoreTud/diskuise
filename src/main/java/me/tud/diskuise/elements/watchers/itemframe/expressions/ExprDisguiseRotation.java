package me.tud.diskuise.elements.watchers.itemframe.expressions;

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
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.disguisetypes.watchers.ItemFrameWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Item Frame Disguise - Rotation")
@Description("Set or get the item rotation of an item frame disguise")
@Examples("set the frame rotation of {_dis} to 4")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseRotation extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprDisguiseRotation.class, Number.class, ExpressionType.PROPERTY,
                "[the] [item[( |-)]]frame [item] rotation of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [item[( |-)]]frame [item] rotation");
    }

    Expression<Disguise> disguise;

    @Override
    protected Number[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        ItemFrameWatcher watcher;
        try {
            watcher = (ItemFrameWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        return new Number[]{watcher.getRotation()};
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
        ItemFrameWatcher watcher;
        try {
            watcher = (ItemFrameWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }

        int value = ((Number) delta[0]).intValue();
        int rotation = watcher.getRotation();

        if (mode != Changer.ChangeMode.SET) {
            if (mode == Changer.ChangeMode.REMOVE) value *= -1;
            rotation += value;
        }
        else rotation = value;
        watcher.setRotation(rotation);
        DisguiseUtil.update(disguise);
    }
}
