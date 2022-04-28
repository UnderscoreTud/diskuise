package me.tud.diskuise.elements.watchers.player.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Right sleeve")
@Description("Set or get if the right sleeve of a player disguise is enabled")
@Examples("set right sleeve of disguise {dis} to true")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseRightSleeve extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprDisguiseRightSleeve.class, Boolean.class, ExpressionType.PROPERTY,
                "[the] right sleeve [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s right sleeve [(value|option|state)]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Boolean[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        PlayerWatcher watcher;
        try {
            watcher = (PlayerWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        if (watcher == null) return null;
        return new Boolean[]{watcher.isRightSleeveEnabled()};
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
        PlayerWatcher watcher;
        try {
            watcher = (PlayerWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        boolean bool = Boolean.TRUE.equals(delta[0]);
        watcher.setRightSleeveEnabled(bool);
        DisguiseUtil.update(disguise);
    }
}
