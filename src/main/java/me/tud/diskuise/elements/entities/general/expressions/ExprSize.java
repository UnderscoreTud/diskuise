package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.PhantomWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Disguise - Size")
@Description({
        "Set or get the size of a disguise.",
        "This only works on a phantom or slime disguise"})
@Examples("set the size of {_dis} to 25")
@Since("0.3.2")
@RequiredPlugins("LibsDisguises")
public class ExprSize extends WatcherPropertyExpression<FlagWatcher, Long> {

    static {
        register(ExprSize.class, Long.class, "size");
    }

    @Override
    protected Long convert(FlagWatcher flagWatcher) {
        if (flagWatcher instanceof SlimeWatcher slimeWatcher)
            return (long) slimeWatcher.getSize();
        else if (flagWatcher instanceof PhantomWatcher phantomWatcher)
            return (long) phantomWatcher.getSize();
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "size";
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, RESET -> CollectionUtils.array(Long.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, ChangeMode mode) {
        int i = convert(flagWatcher).intValue();
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
        if (flagWatcher instanceof SlimeWatcher slimeWatcher)
            slimeWatcher.setSize(i);
        else if (flagWatcher instanceof PhantomWatcher phantomWatcher)
            phantomWatcher.setSize(i);
    }
}
