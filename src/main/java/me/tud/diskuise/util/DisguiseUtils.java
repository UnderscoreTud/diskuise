package me.tud.diskuise.util;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.*;
import me.tud.diskuise.Diskuise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class DisguiseUtils {

    private static Disguise lastCreatedDisguise = null;

    public static Disguise getDisguise(EntityType entityType) {
        return getDisguise(DisguiseType.getType(entityType));
    }

    public static Disguise getDisguise(DisguiseType disguiseType) {
        lastCreatedDisguise = disguiseType.isMob() ? new MobDisguise(disguiseType) : new MiscDisguise(disguiseType);
        return lastCreatedDisguise;
    }

    public static PlayerDisguise getDisguise(String name) {
        lastCreatedDisguise = new PlayerDisguise(name);
        return (PlayerDisguise) lastCreatedDisguise;
    }

    public static Disguise getLastCreatedDisguise() {
        return lastCreatedDisguise;
    }

    public static void setLastCreatedDisguise(Disguise lastCreatedDisguise) {
        DisguiseUtils.lastCreatedDisguise = lastCreatedDisguise;
    }

    private static final DisguiseMap DISGUISED_ENTITIES = new DisguiseMap();

    public static void disguise(Entity entity, Disguise disguise) {
        DisguiseAPI.disguiseToAll(entity, disguise);
        disguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        disguise.startDisguise();
        addEntity(entity, disguise);
    }
    public static void disguise(Entity entity, Disguise disguise, @Nullable Long timeToExpire) {
        if (timeToExpire == null) {
            disguise(entity, disguise);
            return;
        }
        DisguiseAPI.disguiseToAll(entity, disguise);
        disguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        disguise.startDisguise();
        addEntity(entity, disguise);
        Bukkit.getScheduler().runTaskLater(Diskuise.getInstance(), () -> {
            if (DisguiseAPI.getDisguise(entity) == disguise) undisguise(entity);
        }, timeToExpire);
    }
    public static void disguise(Entity entity, Disguise disguise, @Nullable Player... targets) {
        if (targets == null) {
            disguise(entity, disguise);
            return;
        }
        DisguiseAPI.disguiseToPlayers(entity, disguise, List.of(targets, entity));
        disguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        disguise.startDisguise();
        addEntity(entity, disguise);
    }
    public static void disguise(Entity entity, Disguise disguise, @Nullable Long timeToExpire, @Nullable Player... targets) {
        if (timeToExpire == null && targets == null) {
            disguise(entity, disguise);
            return;
        }
        else if (timeToExpire == null) {
            disguise(entity, disguise, targets);
            return;
        }
        else if (targets == null) {
            disguise(entity, disguise, timeToExpire);
            return;
        }
        DisguiseAPI.disguiseToPlayers(entity, disguise, List.of(targets, entity));
        disguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        disguise.startDisguise();
        addEntity(entity, disguise);
        Bukkit.getScheduler().runTaskLater(Diskuise.getInstance(), () -> {
            if (DisguiseAPI.getDisguise(entity) == disguise) undisguise(entity);
        }, timeToExpire);
    }

    public static void undisguise(Entity... entities) {
        for (Entity entity : entities) DisguiseAPI.undisguiseToAll(entity);
        removeEntities(entities);
    }

    public static DisguiseMap getDisguisedEntitiesMap() {
        return DISGUISED_ENTITIES;
    }
    public static Collection<Entity> getDisguisedEntities(Disguise disguise) {
        return DISGUISED_ENTITIES.get(disguise);
    }

    public static void addEntity(Entity entity, Disguise disguise) {
        DISGUISED_ENTITIES.put(disguise, entity);
    }
    public static void removeEntities(Entity... entities) {
        for (Entity value : entities) DISGUISED_ENTITIES.removeAll(value);
    }

    public static void update(Disguise disguise) {
        for (Entity entity : DISGUISED_ENTITIES.get(disguise)) {
            DisguiseAPI.disguiseEntity(entity, disguise);
        }
    }
}
