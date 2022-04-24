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
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Disguised Entities")
@Description("Returns all the disguised entities of a list of entities")
@Examples({"loop all disguised players:",
            "\tsend \"You are disguised as %loop-player's disguise type%!\" to loop-player"})
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class ExprEntitiesOfDisguise extends SimpleExpression<Entity> {

    static {
        Skript.registerExpression(ExprEntitiesOfDisguise.class, Entity.class, ExpressionType.PROPERTY,
                "[all [of]] [the] [dis(g|k)uise[d]] entit(y|ies) of [dis(g|k)uise] %disguise%");
    }

    Expression<Disguise> disguise;

    @Override
    protected @Nullable
    Entity[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        return DisguiseUtil.getDisguisedEntities(disguise).toArray(new Entity[0]);
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
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
