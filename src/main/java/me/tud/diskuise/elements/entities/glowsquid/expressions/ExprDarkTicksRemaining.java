package me.tud.diskuise.elements.entities.glowsquid.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.util.Timespan;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.GlowSquidWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Glow Squid Disguise - Dark Time Remaining")
@Description("Set or get the remaining dark time of a glow squid disguise")
@Examples("set dark time remaining of player's disguise to 10 seconds")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class ExprDarkTicksRemaining extends WatcherPropertyExpression<GlowSquidWatcher, Timespan> {

    static {
        if (Skript.classExists("org.bukkit.entity.GlowSquid"))
            register(ExprDarkTicksRemaining.class, Timespan.class, "dark (ticks|time) remaining");
    }

    @Override
    protected Timespan convert(GlowSquidWatcher glowSquidWatcher) {
        int ticks = glowSquidWatcher.getDarkTicksRemaining();
        return Timespan.fromTicks_i(ticks < 0 ? Integer.MAX_VALUE : ticks);
    }

    @Override
    protected String getPropertyName() {
        return "dark ticks remaining";
    }

    @Override
    public Class<? extends Timespan> getReturnType() {
        return Timespan.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, DELETE, RESET -> CollectionUtils.array(Timespan.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, GlowSquidWatcher glowSquidWatcher, @Nullable Object[] delta, ChangeMode mode) {
        int a = delta == null ? 0 : (int) ((Timespan) delta[0]).getTicks_i();
        int changeValue = glowSquidWatcher.getDarkTicksRemaining();
        switch (mode) {
            case REMOVE:
                a *= -1;
            case ADD:
                changeValue += a;
                break;
            case SET:
                changeValue = a;
                break;
            case RESET, DELETE:
                changeValue = 0;
                break;
        }
        glowSquidWatcher.setDarkTicksRemaining(changeValue);
    }
}
