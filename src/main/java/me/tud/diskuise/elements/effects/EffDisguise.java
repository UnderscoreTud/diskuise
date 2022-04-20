package me.tud.diskuise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.lang.management.BufferPoolMXBean;
import java.security.InvalidParameterException;

@Name("Disguise Entity")
@Description("Disguises the specified entity into another entity")
@Examples({"disguise all players as a cow disguise",
        "set disguise of target entity to zombie",
        "set all players' disguise to player \"_tud\""})
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguise extends Effect {

    static {
        Skript.registerEffect(EffDisguise.class,
                "dis(g|k)uise %entities% (to|as) [(a|an)] %disguise%",
                "(set|change) %entities%'[s] dis(g|k)uise to %disguise%",
                "(set|change) dis(g|k)uise of %entities% to %disguise%",

                "dis(g|k)uise %entities% (to|as) [(a|an)] %entitydata%",
                "(set|change) %entities%'[s] dis(g|k)uise to %entitydata%",
                "(set|change) dis(g|k)uise of %entities% to %entitydata%",

                "dis(g|k)uise %entities% (to|as) [(a|an)] [player] %string%",
                "(set|change) %entities%'[s] dis(g|k)uise to [player] %string%",
                "(set|change) dis(g|k)uise of %entities% to [player] %string%");
    }

    Expression<Entity> entities;
    Expression<Disguise> disguise;
    Expression<EntityData<?>> entityData;
    Expression<String> string;
    int pattern;

    @Override
    protected void execute(Event e) {
        Entity[] entities = this.entities.getArray(e);
        Disguise disguise = null;

        if (pattern < 3) disguise = this.disguise.getSingle(e);
        else if (pattern < 6) {
            EntityData<?> entityData = this.entityData.getSingle(e);
            if (entityData == null) return;
            DisguiseType type = DisguiseType.getType(EntityUtils.toBukkitEntityType(entityData));
            try {
                disguise = new MobDisguise(type);
            } catch (InvalidParameterException ignore) {}
            try {
                disguise = new MiscDisguise(type);
            } catch (InvalidParameterException ignore) {}
        }
        else if (pattern < 9) {
            String string = this.string.getSingle(e);
            if (string == null) return;
            disguise = new PlayerDisguise(string);
        }

        if (disguise == null) return;
        for (Entity entity : entities) {
            DisguiseAPI.disguiseEntity(entity, disguise);
            disguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
            disguise.startDisguise();
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
        if (matchedPattern < 3) disguise = (Expression<Disguise>) exprs[1];
        else if (matchedPattern < 6) entityData = (Expression<EntityData<?>>) exprs[1];
        else if (matchedPattern < 9) string = (Expression<String>) exprs[1];
        pattern = matchedPattern;
        return true;
    }
}
