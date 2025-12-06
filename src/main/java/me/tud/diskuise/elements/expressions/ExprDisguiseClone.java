package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.jetbrains.annotations.Nullable;

@Name("Clone Disguise")
@Description("Clones an already existing disguise")
@Examples("set {anotherDisguise} to clone of {disguise}")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprDisguiseClone extends SimplePropertyExpression<Disguise, Disguise> {

    static {
        Skript.registerExpression(ExprDisguiseClone.class, Disguise.class, ExpressionType.PROPERTY,
                "[the] clone of %disguise% [disguise]",
                "cloned %disguise% [disguise]");
    }

    @Override
    protected String getPropertyName() {
        return "clone";
    }

    @Override
    public @Nullable Disguise convert(Disguise disguise) {
        return disguise.clone();
    }

    @Override
    public Class<? extends Disguise> getReturnType() {
        return Disguise.class;
    }
}
