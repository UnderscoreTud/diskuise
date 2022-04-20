package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Disguised Entities")
@Description("Returns all the disguised entities of a list of entities")
@Examples({"loop all disguised players:",
            "\tsend \"You are disguised as %loop-player's disguise type%!\" to loop-player"})
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class ExprAllDisguisedEntities extends SimpleExpression<Entity> {

    static {
        Skript.registerExpression(ExprAllDisguisedEntities.class, Entity.class, ExpressionType.PROPERTY,
                "[all [of]] [the] dis(g|k)uised %entities%");
    }

    Expression<Entity> entities;

    @Override
    protected @Nullable
    Entity[] get(Event e) {
        List<Entity> disguisedEntities = new ArrayList<>();
        for (Entity entity : entities.getAll(e)) if (DisguiseAPI.isDisguised(entity)) disguisedEntities.add(entity);
        return disguisedEntities.toArray(new Entity[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        return true;
    }
}
