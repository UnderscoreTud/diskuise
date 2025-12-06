package me.tud.diskuise.elements.entities.itemframe.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.elements.entities.itemframe.BetterItemFrameWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Item Frame Disguise - Rotation")
@Description("Set or get the rotation of an item frame disguise")
@Examples("set frame rotation of player's disguise to 5")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class ExprRotation extends WatcherPropertyExpression<BetterItemFrameWatcher, Number> {

    static {
        register(ExprRotation.class, Number.class, "frame rotation");
    }

    @Override
    protected Number convert(BetterItemFrameWatcher itemFrameWatcher) {
        return itemFrameWatcher.getRotation();
    }

    @Override
    protected String getPropertyName() {
        return "frame rotation";
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
            case SET, ADD, REMOVE, RESET -> CollectionUtils.array(Number.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, BetterItemFrameWatcher itemFrameWatcher, Object[] delta, ChangeMode mode) {
        int i = itemFrameWatcher.getRotation();
        int change = delta == null ? 0 : ((Number) delta[0]).intValue();
        switch (mode) {
            case REMOVE:
                change = -change;
            case ADD:
                i += change;
                break;
            case SET:
                i = change;
                break;
            case RESET:
                i = 0;
                break;
        }
        itemFrameWatcher.setRotation(i);
    }
}
