package me.tud.diskuise.templates;

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
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Template")
@Description("Set or get if a disguise appears to be templating")
@Examples("set template of disguise {dis} to true")
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseTemplate extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprDisguiseTemplate.class, Boolean.class, ExpressionType.PROPERTY,
                "[the] (burn[(ing|s)]|on fire) [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s (burn[(ing|s)]|on fire) [(value|option|state)]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Boolean[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return null;
        return new Boolean[]{watcher.isBurning()};
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
        disguise.getWatcher().setBurning(bool);
    }
}
