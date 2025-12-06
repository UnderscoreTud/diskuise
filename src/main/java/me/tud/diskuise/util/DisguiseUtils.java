package me.tud.diskuise.util;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.util.Color;
import ch.njol.util.coll.CollectionUtils;
import ch.njol.yggdrasil.Fields;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.*;
import me.libraryaddict.disguise.disguisetypes.watchers.*;
import me.libraryaddict.disguise.utilities.DisguiseUtilities;
import me.tud.diskuise.Diskuise;
import me.tud.diskuise.elements.entities.ageable.Age;
import me.tud.diskuise.elements.entities.ageable.AgeUtil;
import me.tud.diskuise.elements.entities.armorstand.BetterArmorStandWatcher;
import me.tud.diskuise.elements.entities.arrow.BetterTippedArrowWatcher;
import me.tud.diskuise.elements.entities.guardian.BetterGuardianWatcher;
import me.tud.diskuise.elements.entities.itemframe.BetterItemFrameWatcher;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.TreeSpecies;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.entity.MushroomCow.Variant;
import org.bukkit.entity.Rabbit.Type;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.*;

public class DisguiseUtils {

    private static final WeakHashMap<Disguise, Set<Entity>> DISGUISED_ENTITIES = new WeakHashMap<>();
    private static final String DEFAULT_PLAYER_NAME = "Steve";
    private static final HashMap<Entity, Disguise> ENTITY_LAST_DISGUISE = new HashMap<>();
    private static final TreeSpecies[] treeSpecies = TreeSpecies.values();
    private static final Parrot.Variant[] parrotVariants = Parrot.Variant.values();
    private static final RabbitType[] rabbitTypes = RabbitType.values();
    private static final TropicalFish.Pattern[] tropicalFishPatterns = TropicalFish.Pattern.values();
    private static Disguise lastCreatedDisguise = null;

    public static Disguise createDisguise(EntityData<?> entityData) {
        if (entityData.getSuperType().equals(EntityData.fromClass(Player.class)))
            return null;
        EntityType entityType = typeFromClass(entityData.getType());
        if (entityType == null) return null;
        lastCreatedDisguise = createDisguise(DisguiseType.getType(entityType));
        modify: try {
            Fields fields = entityData.serialize();
            FlagWatcher watcher = lastCreatedDisguise.getWatcher();
            if (watcher instanceof AxolotlWatcher axolotlWatcher) {
                Axolotl.Variant variant = fields.getAndRemoveObject("variant", Axolotl.Variant.class);
                if (variant == null) break modify;
                axolotlWatcher.setVariant(variant);
            }
            else if (watcher instanceof BeeWatcher beeWatcher) {
                int nectar = fields.getAndRemovePrimitive("nectar", int.class);
                int angry = fields.getAndRemovePrimitive("angry", int.class);
                if (nectar != 0)
                    beeWatcher.setHasNectar(nectar == 1);
                beeWatcher.setBeeAnger(angry);
            }
            else if (watcher instanceof BoatWatcher boatWatcher) {
                int matchedPattern = fields.getAndRemovePrimitive("matchedPattern", int.class);
                if (matchedPattern == 0) matchedPattern = 2;
                matchedPattern -= 2;
                TreeSpecies tree = matchedPattern == -1 ? CollectionUtils.getRandom(treeSpecies) : treeSpecies[matchedPattern];
                boatWatcher.setBoatType(tree);
            }
            else if (watcher instanceof CatWatcher catWatcher) {
                Cat.Type catType = fields.getAndRemoveObject("race", Cat.Type.class);
                if (catType == null) break modify;
                catWatcher.setType(catType);
            }
            else if (watcher instanceof CreeperWatcher creeperWatcher) {
                int powered = fields.getAndRemovePrimitive("powered", int.class);
                creeperWatcher.setPowered(powered == 1);
            }
            else if (watcher instanceof DroppedItemWatcher
                    || watcher instanceof EndermanWatcher
                    || watcher instanceof FallingBlockWatcher
                    || watcher instanceof SplashPotionWatcher) {
                ItemType[] itemTypes = fields.getAndRemoveObject(watcher instanceof EndermanWatcher ? "hand" : "types", ItemType[].class);
                if (itemTypes == null) break modify;
                ItemType itemType = CollectionUtils.getRandom(itemTypes);
                if (itemType == null) break modify;
                ItemStack itemStack = itemType.getRandom();
                if (watcher instanceof DroppedItemWatcher droppedItemWatcher)
                    droppedItemWatcher.setItemStack(itemStack);
                else if (watcher instanceof EndermanWatcher endermanWatcher)
                    endermanWatcher.setItemInMainHand(itemStack);
                else if (watcher instanceof FallingBlockWatcher fallingBlockWatcher)
                    fallingBlockWatcher.setBlock(itemStack);
                else ((SplashPotionWatcher) watcher).setSplashPotion(itemStack);
            }
            else if (watcher instanceof FoxWatcher foxWatcher) {
                Fox.Type type = fields.getAndRemoveObject("type", Fox.Type.class);
                if (type == null) break modify;
                foxWatcher.setType(type);
            }
            else if (watcher instanceof GoatWatcher goatWatcher) {
                int i = fields.getAndRemovePrimitive("screaming", int.class);
                if (i == 0) i = new Random().nextInt(1, 2);
                goatWatcher.setScreaming(i == 1);
            }
            else if (watcher instanceof LlamaWatcher llamaWatcher) {
                Llama.Color color = fields.getAndRemoveObject("color", Llama.Color.class);
                if (color == null) break modify;
                llamaWatcher.setColor(color);
            }
            else if (watcher instanceof MushroomCowWatcher mushroomCowWatcher) {
                Variant variant = fields.getAndRemoveObject("variant", Variant.class);
                if (variant == null) break modify;
                mushroomCowWatcher.setVariant(variant);
            }
            else if (watcher instanceof PandaWatcher pandaWatcher) {
                Panda.Gene mainGene = fields.getAndRemoveObject("mainGene", Panda.Gene.class);
                Panda.Gene hiddenGene = fields.getAndRemoveObject("hiddenGene", Panda.Gene.class);
                if (mainGene != null) pandaWatcher.setMainGene(mainGene);
                if (hiddenGene != null) pandaWatcher.setHiddenGene(hiddenGene);
            }
            else if (watcher instanceof ParrotWatcher parrotWatcher) {
                int i = fields.getAndRemovePrimitive("variant", int.class);
                Parrot.Variant variant = i == -1 ? CollectionUtils.getRandom(parrotVariants) : parrotVariants[i];
                parrotWatcher.setVariant(variant);
            }
            else if (watcher instanceof PigWatcher pigWatcher) {
                int i = fields.getAndRemovePrimitive("saddled", int.class);
                pigWatcher.setSaddled(i == 1);
            }
            else if (watcher instanceof RabbitWatcher rabbitWatcher) {
                int i = fields.getAndRemovePrimitive("type", int.class);
				Type type = Optional.ofNullable(RabbitType.getType(i)).orElse(CollectionUtils.getRandom(rabbitTypes).getType());
				rabbitWatcher.setType(type);
            }
            else if (watcher instanceof SheepWatcher sheepWatcher) {
                Color[] colors = fields.getAndRemoveObject("colors", Color[].class);
                if (colors != null) {
                    Color color = CollectionUtils.getRandom(colors);
                    if (color != null)
                        sheepWatcher.setColor(color.asDyeColor());
                }
                boolean sheared = fields.getAndRemovePrimitive("sheared", int.class) == 1;
                sheepWatcher.setSheared(sheared);
            }
            else if (watcher instanceof TropicalFishWatcher tropicalFishWatcher) {
                int matchedPattern = fields.getAndRemovePrimitive("matchedPattern", int.class);
                TropicalFish.Pattern pattern = matchedPattern == 0 ? CollectionUtils.getRandom(tropicalFishPatterns) : tropicalFishPatterns[matchedPattern - 1];
                if (pattern != null)
                    tropicalFishWatcher.setPattern(pattern);
                DyeColor patternColor = fields.getAndRemoveObject("patternColor", DyeColor.class);
                if (patternColor != null)
                    tropicalFishWatcher.setPatternColor(patternColor);
                DyeColor bodyColor = fields.getAndRemoveObject("bodyColor", DyeColor.class);
                if (bodyColor != null)
                    tropicalFishWatcher.setBodyColor(bodyColor);
            }
            else if (watcher instanceof VillagerWatcher || watcher instanceof ZombieVillagerWatcher) {
                Profession profession = fields.getAndRemoveObject("profession", Profession.class);
                if (profession == null) break modify;
                if (watcher instanceof VillagerWatcher villagerWatcher)
                    villagerWatcher.setProfession(profession);
                else ((ZombieVillagerWatcher) watcher).setProfession(profession);
            }
            else if (watcher instanceof WolfWatcher wolfWatcher) {
                int angry = fields.getAndRemovePrimitive("angry", int.class);
                wolfWatcher.setAnger(angry * -1);
                wolfWatcher.setTamed(fields.getAndRemovePrimitive("tamed", int.class) == 1);
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
        if (entityClass == AbstractHorse.class)
            entityClass = Horse.class;
        else if (entityClass == Minecart.class)
            entityClass = RideableMinecart.class;

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
        if (disguiseType.isPlayer())
            return createDisguise(DEFAULT_PLAYER_NAME);
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

    public static void disguise(Entity entity, Disguise disguise) {
        disguise(entity, disguise, -1, null);
    }

    public static void disguise(Entity entity, Disguise disguise, long timeToExpire) {
        disguise(entity, disguise, timeToExpire, null);
    }

    public static void disguise(Entity entity, Disguise disguise, @Nullable Player[] targets) {
        disguise(entity, disguise, -1, targets);
    }

    public static void disguise(Entity entity, Disguise disguise, long timeToExpire, @Nullable Player[] targets) {
        FileConfiguration diskuiseConfig = Diskuise.getInstance().getConfig();
        if (diskuiseConfig.getBoolean("disguise with libs config", false)) {
            if (!(entity instanceof Player player)) return;
            if (DisguiseConfig.isNameOfPlayerShownAboveDisguise()) {
                FlagWatcher watcher = disguise.getWatcher();
                if (watcher instanceof LivingWatcher) {
                    watcher.setCustomName(DisguiseUtilities.getDisplayName(player.getName()));
                    watcher.setCustomNameVisible(DisguiseConfig.isNameAboveHeadAlwaysVisible());
                }
            }
        }
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
        if (timeToExpire > -1) {
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
