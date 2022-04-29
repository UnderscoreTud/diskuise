package me.tud.diskuise.utils;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class DisguiseUtil {

    private static final DisguiseMap DISGUISED_ENTITIES = new DisguiseMap();

    public static void disguise(Entity entity, Disguise disguise) {
        DisguiseAPI.disguiseEntity(entity, disguise);
        disguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        disguise.startDisguise();
        addEntity(entity, disguise);
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
