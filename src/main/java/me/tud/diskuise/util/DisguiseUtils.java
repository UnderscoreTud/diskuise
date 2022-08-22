package me.tud.diskuise.util;

import ch.njol.skript.entity.EntityData;
import ch.njol.yggdrasil.Fields;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.*;
import me.libraryaddict.disguise.disguisetypes.watchers.MushroomCowWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.VillagerWatcher;
import me.tud.diskuise.Diskuise;
import me.tud.diskuise.elements.entities.ageable.Age;
import me.tud.diskuise.elements.entities.ageable.AgeUtil;
import me.tud.diskuise.elements.entities.armorstand.BetterArmorStandWatcher;
import me.tud.diskuise.elements.entities.arrow.BetterTippedArrowWatcher;
import me.tud.diskuise.elements.entities.guardian.BetterGuardianWatcher;
import me.tud.diskuise.elements.entities.itemframe.BetterItemFrameWatcher;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MushroomCow.Variant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager.Profession;
import org.jetbrains.annotations.Nullable;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.*;

public class DisguiseUtils {

    private static final WeakHashMap<Disguise, Set<Entity>> DISGUISED_ENTITIES = new WeakHashMap<>();
    private static final HashMap<Entity, Disguise> ENTITY_LAST_DISGUISE = new HashMap<>();
    private static Disguise lastCreatedDisguise = null;

    public static Disguise createDisguise(EntityData<?> entityData) {
        if (entityData.getSuperType().equals(EntityData.fromClass(Player.class)))
            return null;
        lastCreatedDisguise = createDisguise(DisguiseType.getType(typeFromClass(entityData.getType())));
        modify : try {
            Fields fields = entityData.serialize();
            FlagWatcher watcher = lastCreatedDisguise.getWatcher();
            if (watcher instanceof MushroomCowWatcher mushroomCowWatcher) {
                Variant variant = fields.getAndRemoveObject("profession", Variant.class);
                if (variant == null) break modify;
                mushroomCowWatcher.setVariant(fields.getAndRemoveObject("variant", Variant.class));
            }
            else if (watcher instanceof VillagerWatcher villagerWatcher) {
                Profession profession = fields.getAndRemoveObject("profession", Profession.class);
                if (profession == null) break modify;
                villagerWatcher.setProfession(profession);
            }
        }
        catch (StreamCorruptedException | NotSerializableException e) {
            e.printStackTrace();
        }
        AgeUtil.setDisguiseAge(lastCreatedDisguise.getWatcher(), entityData.isBaby().isTrue() ? Age.BABY : Age.ADULT);
        setupDisguise(lastCreatedDisguise);
        return lastCreatedDisguise;
    }

    private static EntityType typeFromClass(Class<? extends Entity> entityClass) {
        for (EntityType type : EntityType.values()) {
            if (type.getEntityClass() == entityClass)
                return type;
        }
        return null;
    }

    public static Disguise createDisguise(DisguiseType disguiseType) {
        disguiseType.setWatcherClass(switch (disguiseType) {
            case ARROW -> BetterTippedArrowWatcher.class;
            case ARMOR_STAND -> BetterArmorStandWatcher.class;
            case GUARDIAN, ELDER_GUARDIAN -> BetterGuardianWatcher.class;
            case ITEM_FRAME, GLOW_ITEM_FRAME -> BetterItemFrameWatcher.class;
            default -> disguiseType.getWatcherClass();
        });
        lastCreatedDisguise = disguiseType.isMob() ? new MobDisguise(disguiseType) : new MiscDisguise(disguiseType);
        setupDisguise(lastCreatedDisguise);
        return lastCreatedDisguise;
    }

    public static PlayerDisguise createDisguise(String name) {
        lastCreatedDisguise = new PlayerDisguise(name);
        setupDisguise(lastCreatedDisguise);
        return (PlayerDisguise) lastCreatedDisguise;
    }

    public static void setupDisguise(Disguise disguise) {
        disguise.setNotifyBar(DisguiseConfig.NotifyBar.NONE);
        disguise.setSelfDisguiseVisible(true);
        disguise.setHearSelfDisguise(true);
        disguise.setHidePlayer(false);
        DISGUISED_ENTITIES.put(disguise, new HashSet<>());
    }

    public static Disguise getLastCreatedDisguise() {
        return lastCreatedDisguise;
    }

    public static void setLastCreatedDisguise(Disguise lastCreatedDisguise) {
        DisguiseUtils.lastCreatedDisguise = lastCreatedDisguise;
    }

    public static void disguise(Entity entity, Disguise disguise) {
        disguise(entity, disguise, null, null);
    }

    public static void disguise(Entity entity, Disguise disguise, @Nullable Long timeToExpire) {
        disguise(entity, disguise, timeToExpire, null);
    }

    public static void disguise(Entity entity, Disguise disguise, @Nullable Player[] targets) {
        disguise(entity, disguise, null, targets);
    }

    public static void disguise(Entity entity, Disguise disguise, @Nullable Long timeToExpire, @Nullable Player[] targets) {
        List<Player> viewers = new ArrayList<>();
        if (targets == null)
            viewers.addAll(Bukkit.getOnlinePlayers());
        else {
            for (Player target : targets) {
                if (target == null) continue;
                viewers.add(target);
            }
            if (entity instanceof Player player)
                viewers.add(player);
        }
        DisguiseAPI.disguiseToPlayers(entity, disguise, viewers);
        if (new HashSet<>(viewers).containsAll(Bukkit.getOnlinePlayers()))
            ENTITY_LAST_DISGUISE.remove(entity);
        addEntity(entity, disguise);
        if (timeToExpire != null) {
            Bukkit.getScheduler().runTaskLater(Diskuise.getInstance(), () -> {
                if (DisguiseAPI.getDisguise(entity) == disguise) undisguise(entity);
            }, timeToExpire);
        }
    }

    public static void undisguise(Entity... entities) {
        for (Entity entity : entities) DisguiseAPI.undisguiseToAll(entity);
        removeEntities(entities);
    }

    public static Collection<Entity> getDisguisedEntities(Disguise disguise) {
        return DISGUISED_ENTITIES.get(disguise);
    }

    public static Disguise getLastDisguise(Entity entity) {
        return ENTITY_LAST_DISGUISE.get(entity);
    }

    private static void addEntity(Entity entity, Disguise disguise) {
        DISGUISED_ENTITIES.putIfAbsent(disguise, new HashSet<>());
        DISGUISED_ENTITIES.get(disguise).add(entity);
        ENTITY_LAST_DISGUISE.put(entity, disguise);
    }

    private static void removeEntities(Entity... entities) {
        for (Entity entity : entities) {
            for (Disguise disguise : DISGUISED_ENTITIES.keySet()) DISGUISED_ENTITIES.get(disguise).remove(entity);
            ENTITY_LAST_DISGUISE.remove(entity);
        }
    }

    public static void update(Disguise disguise) {
        if (Diskuise.getInstance().getConfig().getBoolean("auto update disguises", false)) {
            DISGUISED_ENTITIES.putIfAbsent(disguise, new HashSet<>());
            for (Entity entity : DISGUISED_ENTITIES.get(disguise))
                DisguiseAPI.disguiseEntity(entity, disguise);
        }
    }
}
