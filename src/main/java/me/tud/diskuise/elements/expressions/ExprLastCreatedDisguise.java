package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Last Created Disguise")
@Description("Returns the last created disguise")
@Examples("make last created disguise glow")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class ExprLastCreatedDisguise extends SimpleExpression<Disguise> {

    static {
        Skript.registerExpression(ExprLastCreatedDisguise.class, Disguise.class, ExpressionType.SIMPLE, "[the] [last[ly]] created disguise");
    }

    @Override
    protected @Nullable Disguise[] get(Event e) {
        return new Disguise[]{DisguiseUtils.getLastCreatedDisguise()};
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
        return "the last created disguise";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
