package me.tud.diskuise.elements.entities.irongolem.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.GolemCrack;
import me.libraryaddict.disguise.disguisetypes.watchers.IronGolemWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Iron Golem Disguise - Cracks")
@Description({"Set or get the cracks in an iron golem disguise",
        "The stages go from 1-4 (1 being no cracks and 4 being full of cracks)"})
@Examples("set the golem cracks of {_dis} to 3")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprCracks extends WatcherPropertyExpression<IronGolemWatcher, Number> {

    static {
        register(ExprCracks.class, Number.class, "[iron[ ]golem] crack[s] [stage]");
    }

    @Override
    protected Number convert(IronGolemWatcher ironGolemWatcher) {
        return toStage(ironGolemWatcher.getCracks());
    }

    @Override
    protected String getPropertyName() {
        return "iron golem crack stage";
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<? extends Disguise>) exprs[0]);
        return true;
    }

    private int toStage(GolemCrack golemCrack) {
        for (int i = 0; i < GolemCrack.values().length; i++) {
            if (golemCrack == GolemCrack.values()[i])
                return i + 1;
        }
        return 1;
    }

    private GolemCrack fromStage(int stage) {
        stage = Math.min(Math.max(1, stage), 4);
        return GolemCrack.values()[stage - 1];
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, DELETE, RESET -> CollectionUtils.array(Number.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, IronGolemWatcher ironGolemWatcher, @Nullable Object[] delta, ChangeMode mode) {
        switch (mode) {
            case DELETE, RESET -> {
                ironGolemWatcher.setCracks(GolemCrack.HEALTH_100);
                return;
            }
        }
        if (delta[0] == null) return;
        int stage = ((Number) delta[0]).intValue();
        int current = toStage(ironGolemWatcher.getCracks());
        switch (mode) {
            case ADD -> current += stage;
            case REMOVE -> current -= stage;
            case SET -> current = stage;
        }
        ironGolemWatcher.setCracks(fromStage(current));
    }
}
