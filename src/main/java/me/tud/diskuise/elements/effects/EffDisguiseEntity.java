package me.tud.diskuise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise Entity")
@Description("Disguises the specified entity into another entity")
@Examples({
        "disguise all players as a cow disguise",
        "disguise target entity to zombie",
        "disguise all players as \"_tud\""
})
@Since("0.1")
@RequiredPlugins("LibsDisguises")
public class EffDisguiseEntity extends Effect {

    static {
        Skript.registerEffect(EffDisguiseEntity.class,
                "disguise %entities% (to|as) %disguise/entitydata/string% [(for|to) %-players%] [for %-timespan%]",
                "set %entities%'[s] disguise to %disguise/entitydata/string% [(for|to) %-players%] [for %-timespan%]",
                "set disguise of %entities% to %disguise/entitydata/string% [(for|to) %-players%] [for %-timespan%]");
    }

    private Expression<Entity> entities;
    private Expression<?> expr;
    private Expression<Player> targets;
    private Expression<Timespan> timespan;

    @Override
    protected void execute(Event e) {
        Entity[] entities = this.entities.getArray(e);
        Object object = this.expr.getSingle(e);
        Player[] targets = null;
        Timespan timespan = null;
        if (this.targets != null) targets = this.targets.getArray(e);
        if (this.timespan != null) timespan = this.timespan.getSingle(e);
        Disguise disguise = null;

        if (object instanceof Disguise)
            disguise = (Disguise) object;
        else if (object instanceof EntityData<?> entityData)
            disguise = DisguiseUtils.createDisguise(entityData);
        else if (object instanceof String name)
            disguise = DisguiseUtils.createDisguise(name);

        if (disguise == null)
            return;
        long timeToExpire = -1;
        if (timespan != null)
            timeToExpire = timespan.getTicks_i();
        for (Entity entity : entities)
            DisguiseUtils.disguise(entity, disguise, timeToExpire, targets);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "disguise " + entities.toString(e, debug) + " as " + expr.toString(e, debug) + " to "
                + (targets == null ? "all players" : targets.toString(e, debug))
                + (timespan == null ? "" : " for " + timespan.toString(e, debug));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        expr = LiteralUtils.defendExpression(exprs[1]);
        targets = (Expression<Player>) exprs[2];
        timespan = (Expression<Timespan>) exprs[3];
        if (expr instanceof Literal<?> literal) {
            if (literal.getSingle() instanceof EntityData<?> entityData) {
                if (entityData.getSuperType().equals(EntityData.fromClass(Player.class))) {
                    Skript.error("Use a string to disguise as a player, e.g. 'disguise player as \"_tud\"");
                    return false;
                }
            }
        }
        return LiteralUtils.canInitSafely(expr);
    }
}
