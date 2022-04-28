package me.tud.diskuise.elements.watchers.painting.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.PaintingWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.Art;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Painting Disguise - Art")
@Description("Set or get a painting disguise's art")
@Examples("set painting art of player's disguise to kebab")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseArt extends SimpleExpression<Art> {

    static {
        Skript.registerExpression(ExprDisguiseArt.class, Art.class, ExpressionType.PROPERTY,
                "[the] [painting] art [value] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [painting] art [value]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Art[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        PaintingWatcher watcher;
        try {
            watcher = (PaintingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        return new Art[]{watcher.getArt()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Art> getReturnType() {
        return Art.class;
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
        if (mode == Changer.ChangeMode.SET) return CollectionUtils.array(Art.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (delta[0] == null) return;
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        PaintingWatcher watcher;
        try {
            watcher = (PaintingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }

        watcher.setArt((Art) delta[0]);
        DisguiseUtil.update(disguise);
    }
}
