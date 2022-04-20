package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise Type")
@Description("Returns the type of a disguise")
@Examples("broadcast type of player's disguise")
@Since("0.2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseType extends SimpleExpression<EntityType> {

    static {
        Skript.registerExpression(ExprDisguiseType.class, EntityType.class, ExpressionType.PROPERTY,
                "%disguise%'s [dis(g|k)uise[( |-)]]type",
                "[the] [dis(g|k)uise[( |-)]]type of %disguise%");
    }

    Expression<Disguise> disguise;

    @Override
    protected @Nullable
    EntityType[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        EntityType entityType = EntityType.parse(disguise.getType().getEntityType().toString().replace("_", " "));
        return new EntityType[]{entityType};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends EntityType> getReturnType() {
        return EntityType.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
