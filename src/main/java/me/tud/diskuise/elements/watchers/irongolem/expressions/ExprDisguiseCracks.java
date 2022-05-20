package me.tud.diskuise.elements.watchers.irongolem.expressions;

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
import me.libraryaddict.disguise.disguisetypes.GolemCrack;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.IronGolemWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.ItemFrameWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Iron Golem Disguise - Cracks")
@Description({"Set or get the cracks in an iron golem disguise", "The stages go from 1-4 (1 being no cracks and 4 being full of cracks)"})
@Examples("set the golem cracks of {_dis} to 3")
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseCracks extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprDisguiseCracks.class, Number.class, ExpressionType.PROPERTY,
                "[the] [[iron[ ]]golem] crack[s] [stage] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [[iron[ ]]golem] crack[s] [stage]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Number[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        IronGolemWatcher watcher;
        if (disguise.getWatcher() instanceof IronGolemWatcher) watcher = (IronGolemWatcher) disguise.getWatcher();
        else return null;
        if (watcher.getCracks() == null) return new Number[]{1};
        Integer stage = null;
        switch (watcher.getCracks()) {
            case HEALTH_100 -> stage = 1;
            case HEALTH_75 -> stage = 2;
            case HEALTH_50 -> stage = 3;
            case HEALTH_25 -> stage = 4;
        }
        return new Number[]{stage};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
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
        switch (mode) {
            case SET, RESET, REMOVE_ALL -> { return CollectionUtils.array(Number.class); }
        }
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (delta[0] == null) return;
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        IronGolemWatcher watcher;
        if (disguise.getWatcher() instanceof IronGolemWatcher) watcher = (IronGolemWatcher) disguise.getWatcher();
        else return;
        GolemCrack crack = GolemCrack.HEALTH_100;
        if (mode == Changer.ChangeMode.SET) {
            switch (((Number) delta[0]).intValue()) {
                case 2 -> crack = GolemCrack.HEALTH_75;
                case 3 -> crack = GolemCrack.HEALTH_50;
                case 4 -> crack = GolemCrack.HEALTH_25;
            }
        }

        watcher.setCracks(crack);
        DisguiseUtil.update(disguise);
    }
}
