package me.tud.diskuise.elements.watchers.living.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Living Disguise - Particle color")
@Description("Set or get a disguise's particle color")
@Examples("set particle color of player's disguise to red")
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseParticleColor extends SimpleExpression<SkriptColor> {

    static {
        Skript.registerExpression(ExprDisguiseParticleColor.class, SkriptColor.class, ExpressionType.PROPERTY,
                "[the] particle[s] colo[u]r[s] [value] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s particle[s] colo[u]r[s] [value]");
    }

    Expression<Disguise> disguise;

    @Override
    protected SkriptColor[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        return new SkriptColor[]{SkriptColor.fromBukkitColor(watcher.getParticlesColor())};
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
        if (mode == Changer.ChangeMode.SET ||
        mode == Changer.ChangeMode.RESET ||
        mode == Changer.ChangeMode.DELETE) return CollectionUtils.array(Color.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }

        org.bukkit.Color color = org.bukkit.Color.fromRGB(0);
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] != null) {
                color = ((SkriptColor) delta[0]).asBukkitColor();
            }
        }
        watcher.setParticlesColor(color);
        DisguiseUtil.update(disguise);
    }
}
