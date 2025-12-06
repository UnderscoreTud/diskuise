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

@Name("View Self Of Disguise")
@Description("Get or set whether a player sees their own disguise")
@Examples("set view self of {_disguise} to false")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprViewSelfOfDisguise extends DisguisePropertyExpression<Boolean> {

    static {
        register(ExprViewSelfOfDisguise.class, Boolean.class, "view[ing] self");
    }

    @Override
    protected String getPropertyName() {
        return "view self";
    }

    @Override
    public @Nullable Boolean convert(Disguise disguise) {
        return disguise.isSelfDisguiseVisible();
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
        assert delta[0] != null;
        for (Disguise disguise : getExpr().getArray(e)) {
            disguise.setSelfDisguiseVisible((boolean) delta[0]);
            DisguiseUtils.update(disguise);
        }
    }
}
