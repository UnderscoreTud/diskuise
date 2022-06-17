package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;

public abstract class DisguiseMakeEffect extends Effect {

    public static void register(final Class<? extends Effect> c, final String property) {
        Skript.registerEffect(c, "make [dis(g|k)uise] %disguises% " + property);
    }

    Expression<Disguise> expr;

    public Expression<Disguise> getExpr() {
        return expr;
    }

    public void setExpr(Expression<Disguise> expr) {
        this.expr = expr;
    }

    abstract protected void make(Event e, Disguise disguise);

    @Override
    protected void execute(Event e) {
        for (Disguise disguise : getExpr().getArray(e))
            make(e, disguise);
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<Disguise>) exprs[0]);
        return true;
    }
}
