package me.tud.diskuise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.*;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import org.bukkit.entity.Entity;
import java.util.ArrayList;
import java.util.List;

@Name("Disguise Entity")
@Description("Disguises the specified entity into another entity")
@Examples({"disguise all players as a cow disguise",
        "disguise target entity to zombie",
        "disguise all players as \"_tud\""})
@Since("0.1")
@RequiredPlugins("LibsDisguises")
public class EffDisguiseEntity extends Effect {

    static {
        Skript.registerEffect(EffDisguiseEntity.class,
                "dis(g|k)uise %entities% (to|as) [(a|an)] %disguise/entitydata/string% [(for|to) %-players%] [for %-timespan%]",
                "(set|change) %entities%'[s] dis(g|k)uise to %disguise/entitydata/string% [(for|to) %-players%] [for %-timespan%]",
                "(set|change) dis(g|k)uise of %entities% to %disguise/entitydata/string% [(for|to) %-players%] [for %-timespan%]");
    }

    private Expression<Entity> entities;
    private Expression<?> object;
    private Expression<Player> targets;
    private Expression<Timespan> timespan;

    @Override
    protected void execute(Event e) {
        Entity[] entities = this.entities.getArray(e);
        Object object = this.object.getSingle(e) != null ? this.object.getSingle(e) : EntityUtils.toSkriptEntityData(EntityType.DROPPED_ITEM);
        Player[] targets = new Player[0];
        Timespan timespan = null;
        if (this.targets != null) targets = this.targets.getArray(e);
        if (this.timespan != null) timespan = this.timespan.getSingle(e);
        Disguise disguise = null;

        if (object instanceof Disguise) disguise = (Disguise) object;
        else if (object instanceof EntityData<?> entityData) {
            DisguiseType type = DisguiseType.getType(EntityUtils.toBukkitEntityType(entityData));
            disguise = (type.isMob() ? new MobDisguise(type) : new MiscDisguise(type));
        }
        else if (object instanceof String string) disguise = new PlayerDisguise(string);

        if (disguise == null) return;
        Long timeToExpire = null;
        if (timespan != null) timeToExpire = timespan.getTicks_i();
        for (Entity entity : entities) {
            if (!(object instanceof Disguise)) DisguiseAPI.setViewDisguiseToggled(entity, true);
            if (targets.length == 0) DisguiseUtils.disguise(entity, disguise, timeToExpire);
            else DisguiseUtils.disguise(entity, disguise, timeToExpire, targets);
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "disguise " + entities.toString(e, debug) + " as " + object.toString(e, debug)
                + " to " + (targets == null ? "all players" : targets.toString(e, debug))
                + (timespan == null ? "" : " for " + timespan.toString(e, debug));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        object = exprs[1];
        targets = (Expression<Player>) exprs[2];
        timespan = (Expression<Timespan>) exprs[3];
        return true;
    }
}
