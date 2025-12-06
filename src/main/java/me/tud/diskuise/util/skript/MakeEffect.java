package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public abstract class MakeEffect<T> extends Effect {

    public static void register(final Class<? extends Effect> c, final String property, final String fromType) {
        Skript.registerEffect(c, "make %" + fromType + "% " + property);
    }

    private Expression<? extends T> expr;
    private boolean negated = false;

    public Expression<? extends T> getExpr() {
        return expr;
    }

    public void setExpr(Expression<? extends T> expr) {
        this.expr = expr;
    }

    public boolean isNegated() {
        return negated;
    }

    public void setNegated(boolean negated) {
        this.negated = negated;
    }

    abstract protected String getProperty();

    abstract protected void make(Event e, T t, boolean state);

    @Override
    protected void execute(Event e) {
        for (T t : getExpr().getArray(e))
            make(e, t, !isNegated());
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "make " + getExpr().toString(e, debug) + (isNegated() ? " not " : " ") + getProperty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<? extends T>) exprs[0]);
        negated = parseResult.hasTag("not");
        return true;
    }
}
