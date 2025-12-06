package me.tud.diskuise.elements.entities.ageable.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.elements.entities.ageable.Age;
import me.tud.diskuise.elements.entities.ageable.AgeUtil;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Ageable Disguise - Age")
@Description("Set or get the age of a disguise")
@Examples("set age of disguise {dis} to adult")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprAge extends WatcherPropertyExpression<FlagWatcher, Age> {

    static {
        register(ExprAge.class, Age.class, "age");
    }

    @Override
    protected Age convert(FlagWatcher flagWatcher) {
        return AgeUtil.getDisguiseAge(flagWatcher);
    }

    @Override
    protected String getPropertyName() {
        return "age";
    }

    @Override
    public Class<? extends Age> getReturnType() {
        return Age.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(Age.class) : null;
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, ChangeMode mode) {
        AgeUtil.setDisguiseAge(flagWatcher, (Age) delta[0]);
    }
}
