package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise of Entity")
@Description("Returns the disguise of an entity")
@Examples("broadcast player's disguise")
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguise extends SimpleExpression<Disguise> {

    static {
        Skript.registerExpression(ExprDisguise.class, Disguise.class, ExpressionType.PROPERTY,
                "%entity%'s dis(g|k)uise",
                "[the] dis(g|k)uise of %entity%");
    }

    Expression<Entity> entity;

    @Override
    protected @Nullable
    Disguise[] get(Event e) {
        Entity entity = this.entity.getSingle(e);
        return new Disguise[]{DisguiseAPI.getDisguise(entity)};
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
        entity = (Expression<Entity>) exprs[0];
        return true;
    }
}
