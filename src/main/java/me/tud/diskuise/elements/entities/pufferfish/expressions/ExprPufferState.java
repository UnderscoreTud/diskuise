package me.tud.diskuise.elements.entities.pufferfish.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PufferFishWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Puffer Fish Disguise - Puff State")
@Description({"Set or get the puff state of a puffer fish disguise (how infalted it is)",
        "The stages go from 1-3"})
@Examples("set the puffer stage of {_dis} to 3")
@Since("0.3.2")
@RequiredPlugins("LibsDisguises")
public class ExprPufferState extends WatcherPropertyExpression<PufferFishWatcher, Number> {

    static {
        register(ExprPufferState.class, Number.class, "[puffer[ ]fish] puff state");
    }

    @Override
    protected Number convert(PufferFishWatcher pufferFishWatcher) {
        return pufferFishWatcher.getPuffState() + 1;
    }

    @Override
    protected String getPropertyName() {
        return "puffer stage";
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

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, DELETE, RESET -> CollectionUtils.array(Number.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, PufferFishWatcher pufferFishWatcher, @Nullable Object[] delta, ChangeMode mode) {
        switch (mode) {
            case DELETE, RESET -> {
                pufferFishWatcher.setPuffState(0);
                return;
            }
        }
        if (delta[0] == null) return;
        int stage = ((Number) delta[0]).intValue();
        int current = pufferFishWatcher.getPuffState();
        switch (mode) {
            case ADD -> current += stage;
            case REMOVE -> current -= stage;
            case SET -> current = stage;
        }
        pufferFishWatcher.setPuffState(current - 1);
    }
}
