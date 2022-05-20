package me.tud.diskuise.elements.watchers.bat.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.BatWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Bat Disguise - Hanging")
@Description("Set or get if a bat disguise appears to be hanging upside down")
@Examples("set is hanging state of disguise {dis} to true")
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseHanging extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprDisguiseHanging.class, Boolean.class, ExpressionType.PROPERTY,
                "[the] [is] hang[ing] [upside[ ]down] [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [is] hang[ing] [upside[ ]down] [(value|option|state)]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Boolean[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        return new Boolean[]{disguise.getWatcher() instanceof BatWatcher ?
                ((BatWatcher) disguise.getWatcher()).isHanging() : null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
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
        if (mode == Changer.ChangeMode.SET) return CollectionUtils.array(Boolean.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        BatWatcher watcher;
        if (disguise.getWatcher() instanceof BatWatcher) watcher = (BatWatcher) disguise.getWatcher();
        else return;
        boolean bool = Boolean.TRUE.equals(delta[0]);
        watcher.setHanging(bool);
        DisguiseUtil.update(disguise);
    }
}
