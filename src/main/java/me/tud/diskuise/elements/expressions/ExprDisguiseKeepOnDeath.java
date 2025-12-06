package me.tud.diskuise.elements.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import me.tud.diskuise.util.skript.DisguisePropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Keep On Death")
@Description("Get or set if a player keeps their disguise on death")
@Examples("set keep on death of {_disguise} to true")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class ExprDisguiseKeepOnDeath extends DisguisePropertyExpression<Boolean> {

    static {
        register(ExprDisguiseKeepOnDeath.class, Boolean.class, "keep on (death|dying)");
    }

    @Override
    protected String getPropertyName() {
        return "keep on death";
    }

    @Override
    public @Nullable Boolean convert(Disguise disguise) {
        return disguise.isKeepDisguiseOnPlayerDeath();
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(Boolean.class) : null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
        if (delta[0] == null) return;
        for (Disguise disguise : getExpr().getArray(e)) {
            disguise.setKeepDisguiseOnPlayerDeath((boolean) delta[0]);
            DisguiseUtils.update(disguise);
        }
    }
}
