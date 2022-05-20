package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Clone disguise")
@Description("Clones an already existing disguise")
@Examples({"set {anotherDisguise} to clone of {disguise}"})
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseClone extends SimpleExpression<Disguise> {

    static {
        Skript.registerExpression(ExprDisguiseClone.class, Disguise.class, ExpressionType.PROPERTY,
                "clone[d] [of] %disguise% [dis(g|k)uise]");
    }

    Expression<Disguise> toClone;

    @Override
    protected @Nullable
    Disguise[] get(Event e) {
        Disguise toClone = this.toClone.getSingle(e);
        if (toClone == null) return null;
        Disguise disguise = toClone.clone();
        return new Disguise[]{disguise};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Disguise> getReturnType() {
        return Disguise.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        toClone = (Expression<Disguise>) exprs[0];
        return true;
    }
}
