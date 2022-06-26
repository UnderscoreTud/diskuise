package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Custom Name Visibility")
@Description("Set or get whether the custom name of a disguise is visible")
@Examples("set custom name visibility of disguise {dis} to true")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprCustomNameVisibility extends WatcherPropertyExpression<FlagWatcher, Boolean> {

    static {
        register(ExprCustomNameVisibility.class, Boolean.class, "[custom[ ]]name visibility");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return flagWatcher.isCustomNameVisible();
    }

    @Override
    protected String getPropertyName() {
        return "custom name visibility";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(Boolean.class) : null;
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, Changer.ChangeMode mode) {
        flagWatcher.setCustomNameVisible((boolean) delta[0]);
    }
}
