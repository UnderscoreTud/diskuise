package me.tud.diskuise.elements.entities.minecart.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.MinecartWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Minecart Disguise - Y Block Offset")
@Description("Set or get the y block offset of a minecart disguise")
@Examples("add 10 to y block offset of player's disguise")
@Since("0.3.3")
@RequiredPlugins("LibsDisguises")
public class ExprYOffset extends WatcherPropertyExpression<MinecartWatcher, Long> {

    static {
        register(ExprYOffset.class, Long.class, "y [block] offset");
    }

    @Override
    protected Long convert(MinecartWatcher minecartWatcher) {
        return (long) minecartWatcher.getBlockOffset();
    }

    @Override
    protected String getPropertyName() {
        return "y block offset";
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }


    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull Changer.ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, RESET -> CollectionUtils.array(Long.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, MinecartWatcher minecartWatcher, Object[] delta, Changer.ChangeMode mode) {
        int i = minecartWatcher.getBlockOffset();
        int change = delta == null ? 0 : ((Long) delta[0]).intValue();
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
        minecartWatcher.setBlockOffset(i);
    }

}
