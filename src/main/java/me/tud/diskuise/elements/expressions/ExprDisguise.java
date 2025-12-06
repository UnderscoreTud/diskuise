package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.NoDoc;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.elements.sections.SecCreateDisguise;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@NoDoc
public class ExprDisguise extends SimpleExpression<Disguise> {

    static {
        Skript.registerExpression(ExprDisguise.class, Disguise.class, ExpressionType.SIMPLE, "[the] disguise");
    }

    private SecCreateDisguise section;

    @Override
    protected @Nullable Disguise[] get(Event e) {
        return new Disguise[]{section.getSectionDisguise()};
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
        return "disguise";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        section = getParser().getCurrentSection(SecCreateDisguise.class);
        return section != null;
    }
}
