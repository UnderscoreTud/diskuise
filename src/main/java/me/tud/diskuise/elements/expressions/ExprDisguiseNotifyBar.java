package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprDisguiseNotifyBar extends SimpleExpression<DisguiseConfig.NotifyBar> {

    static {
        Skript.registerExpression(ExprDisguiseNotifyBar.class, DisguiseConfig.NotifyBar.class, ExpressionType.PROPERTY,
                "[the] [view] [self] notify[( |-)]bar [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [view] [self] notify[( |-)]bar [(value|option|state)]");
    }

    Expression<Disguise> disguise;

    @Override
    protected @Nullable
    DisguiseConfig.NotifyBar[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        return new DisguiseConfig.NotifyBar[]{disguise.getNotifyBar()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends DisguiseConfig.NotifyBar> getReturnType() {
        return DisguiseConfig.NotifyBar.class;
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
    public Class<?>[] acceptChange(final Changer.ChangeMode changeMode) {
        if (changeMode == Changer.ChangeMode.SET) return CollectionUtils.array(DisguiseConfig.NotifyBar.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        disguise.setNotifyBar((DisguiseConfig.NotifyBar) delta[0]);
    }
}
