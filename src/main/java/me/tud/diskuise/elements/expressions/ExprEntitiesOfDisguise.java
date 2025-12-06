package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Entities Of Disguise")
@Description("Returns the entities of a disguise")
@Examples("set {_list::*} to entities of disguise {_dis}")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class ExprEntitiesOfDisguise extends PropertyExpression<Disguise, Entity> {

    static {
        Skript.registerExpression(ExprEntitiesOfDisguise.class, Entity.class, ExpressionType.PROPERTY,
                "[(all [of the]|the)] [disguised] entities of [disguise] %disguises%",
                "[disguise] %disguises%'[s] entities");
    }

    @Override
    protected Entity[] get(Event e, Disguise[] source) {
        List<Entity> entities = new ArrayList<>();
        for (Disguise disguise : source)
            entities.addAll(DisguiseUtils.getDisguisedEntities(disguise));
        return entities.toArray(new Entity[0]);
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "entities of " + getExpr().toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<? extends Disguise>) exprs[0]);
        return true;
    }
}
