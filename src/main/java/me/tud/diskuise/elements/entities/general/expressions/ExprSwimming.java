package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Swimming")
@Description("Set or get whether the disguise is swimming")
@Examples("set swimming of disguise {dis} to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprSwimming extends WatcherPropertyExpression<FlagWatcher, Boolean> {

    static {
        register(ExprSwimming.class, Boolean.class, "swim[ming]");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return flagWatcher.isSwimming();
    }

    @Override
    protected String getPropertyName() {
        return "swimming";
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
        flagWatcher.setSwimming((boolean) delta[0]);
    }
}
