package me.tud.diskuise.elements.watchers.insentient.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.GhastWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Insentient Disguise - Aggressive")
@Description("Set or get if an insentient disguise appears to be aggressive")
@Examples("set aggressiveness of disguise {dis} to true")
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseAggressive extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprDisguiseAggressive.class, Boolean.class, ExpressionType.PROPERTY,
                "[the] [is] aggressive[ness] [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [is] aggressive[ness] [(value|option|state)]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Boolean[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        boolean isAggressive = false;
        if (disguise.getWatcher() instanceof EndermanWatcher) isAggressive = ((EndermanWatcher) disguise.getWatcher()).isAggressive();
        else if (disguise.getWatcher() instanceof GhastWatcher) isAggressive = ((GhastWatcher) disguise.getWatcher()).isAggressive();
        return new Boolean[]{isAggressive};
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
        boolean bool = Boolean.TRUE.equals(delta[0]);
        if (disguise.getWatcher() instanceof EndermanWatcher) ((EndermanWatcher) disguise.getWatcher()).setAggressive(bool);
        else if (disguise.getWatcher() instanceof GhastWatcher) ((GhastWatcher) disguise.getWatcher()).setAggressive(bool);
        else return;
        DisguiseUtil.update(disguise);
    }
}
