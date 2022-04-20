package me.tud.diskuise.elements.watchers.flag.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Glow color")
@Description("Sets or get a disguise's glowing color")
@Examples("set glow color of player's disguise to red")
@Since("0.2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseGlowColor extends SimpleExpression<SkriptColor> {

    static {
        Skript.registerExpression(ExprDisguiseGlowColor.class, SkriptColor.class, ExpressionType.PROPERTY,
                "[the] glow[(ing|s)] col[u]or[ing] [(value|option)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s glow[(ing|s)] col[u]or[ing] [(value|option)]");
    }

    Expression<Disguise> disguise;

    @Override
    protected SkriptColor[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return null;
        return new SkriptColor[]{SkriptColor.valueOf(watcher.getGlowColor().name())};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends SkriptColor> getReturnType() {
        return SkriptColor.class;
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
        if (mode == Changer.ChangeMode.SET) return CollectionUtils.array(Color.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        SkriptColor color = (SkriptColor) delta[0];
        if (color == null) return;
        disguise.getWatcher().setGlowColor(color.asChatColor());
    }
}
