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

@Name("Player Disguise - Shoulder parrot")
@Description("Set or get if the player disguise has a parrot on either of its shoulders")
@Examples("set left shoulder parrot of disguise {dis} to true")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseShoulderHasParrot extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprDisguiseShoulderHasParrot.class, Boolean.class, ExpressionType.PROPERTY,
                "[the] (1¦right|2¦left) shoulder [has [a]] parrot [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s (1¦right|2¦left) shoulder [has [a]] parrot [(value|option|state)]");
    }

    Expression<Disguise> disguise;
    boolean isRight = true;

    @Override
    protected Boolean[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        if (isRight) return new Boolean[]{disguise.getWatcher() instanceof PlayerWatcher ?
                ((PlayerWatcher) disguise.getWatcher()).isRightShoulderHasParrot() : null};
        return new Boolean[]{disguise.getWatcher() instanceof PlayerWatcher ?
                ((PlayerWatcher) disguise.getWatcher()).isLeftShoulderHasParrot() : null};
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
        if (parseResult.mark == 2) isRight = false;
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
        if (disguise.getWatcher() instanceof PlayerWatcher) watcher = (PlayerWatcher) disguise.getWatcher();
        else return;
        boolean bool = Boolean.TRUE.equals(delta[0]);
        if (isRight) watcher.setRightShoulderHasParrot(bool);
        else watcher.setLeftShoulderHasParrot(bool);
        DisguiseUtil.update(disguise);
    }
}
