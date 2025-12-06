package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Disguise - Custom Name")
@Description("Set or get the custom name of a disguise")
@Examples("set {_name} to custom name of player's disguise")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprCustomName extends WatcherPropertyExpression<FlagWatcher, String> {

    static {
        register(ExprCustomName.class, String.class, "[custom[ ]]name");
    }

    @Override
    protected String convert(FlagWatcher flagWatcher) {
        return flagWatcher.getCustomName();
    }

    @Override
    protected String getPropertyName() {
        return "custom name";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(String.class) : null;
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, ChangeMode mode) {
        flagWatcher.setCustomName((String) delta[0]);
    }
}
