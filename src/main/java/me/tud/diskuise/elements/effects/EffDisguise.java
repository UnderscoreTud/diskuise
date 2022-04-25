package me.tud.diskuise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.*;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

@Name("Disguise Entity")
@Description("Disguises the specified entity into another entity")
@Examples({"disguise all players as a cow disguise",
        "set disguise of target entity to zombie",
        "set all players' disguise to \"_tud\""})
@Since("0.1")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguise extends Effect {

    static {
        Skript.registerEffect(EffDisguise.class,
                "dis(g|k)uise %entities% (to|as) [(a|an)] %disguise/entitydata/string%",
                "(set|change) %entities%'[s] dis(g|k)uise to %disguise/entitydata/string%",
                "(set|change) dis(g|k)uise of %entities% to %disguise/entitydata/string%");
    }

    Expression<Entity> entities;
    Expression<Object> object;

    @Override
    protected void execute(Event e) {
        Entity[] entities = this.entities.getArray(e);
        Object object = this.object.getSingle(e) != null ? this.object.getSingle(e) : EntityUtils.toSkriptEntityData(EntityType.DROPPED_ITEM);
        Disguise disguise = null;

        if (object instanceof Disguise) disguise = (Disguise) object;
        if (object instanceof EntityData) {
            EntityData<?> entityData = (EntityData<?>) object;
            DisguiseType type = DisguiseType.getType(EntityUtils.toBukkitEntityType(entityData));
            try {
                disguise = new MobDisguise(type);
            } catch (InvalidParameterException ignore) {}
            try {
                disguise = new MiscDisguise(type);
            } catch (InvalidParameterException ignore) {}
        }
        else if (object instanceof String) {
            String string = (String) object;
            disguise = new PlayerDisguise(string);
        }

        if (disguise == null) return;
        for (Entity entity : entities) {
            DisguiseUtil.disguise(entity, disguise);
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        object = (Expression<Object>) exprs[1];
        return true;
    }
}
