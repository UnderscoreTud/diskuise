package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.EndermanWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Disguise - Equipment")
@Description("Get or set the item in a disguise's equipment slot.")
@Examples({
        "set helmet slot of {_disguise} to iron helmet",
        "set chestplate slot of {_disguise} to iron chestplate",
        "set leggings slot of {_disguise} to iron leggings",
        "set boots slot of {_disguise} to iron boots",
        "set main hand slot of {_disguise} to iron sword",
        "set offhand slot of {_disguise} to shield"
})
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprEquipment extends WatcherPropertyExpression<FlagWatcher, ItemType> {

    static {
        register(ExprEquipment.class, ItemType.class,
                "(feet:(boot[s]|shoe[s])|legs:leg[ging][s]|" +
                        "chest:chestplate[s]|head:helm[et][s]|" +
                        "hand:[main[( |-)]]hand|off_hand:off[( |-)]hand) " +
                        "[(item|slot)]");
    }

    private EquipmentSlot equipmentSlot;

    @Override
    protected ItemType convert(FlagWatcher flagWatcher) {
        ItemStack itemStack = flagWatcher.getItemStack(equipmentSlot);
        if (flagWatcher instanceof EndermanWatcher endermanWatcher)
            itemStack = endermanWatcher.getItemInMainHand();
        return new ItemType(itemStack == null ? new ItemStack(Material.AIR) : itemStack);
    }

    @Override
    protected String getPropertyName() {
        return equipmentSlot.name().toLowerCase() + " item";
    }

    @Override
    public Class<? extends ItemType> getReturnType() {
        return ItemType.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        equipmentSlot = EquipmentSlot.valueOf(parseResult.tags.get(0).toUpperCase());
        setExpr((Expression<? extends Disguise>) exprs[0]);
        return true;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, REMOVE_ALL, DELETE -> CollectionUtils.array(ItemType.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, @Nullable Object[] delta, ChangeMode mode) {
        ItemStack itemStack = new ItemStack(Material.AIR);
        ItemStack current = flagWatcher.getItemStack(equipmentSlot);
        if (flagWatcher instanceof EndermanWatcher endermanWatcher)
            current = endermanWatcher.getItemInMainHand();
        if (current == null) current = new ItemStack(Material.AIR);
        if (mode != ChangeMode.DELETE) {
            if (delta[0] == null) return;
            ItemType itemType = (ItemType) delta[0];
            itemStack = switch (mode) {
                case SET -> itemType.getRandom();
                case ADD -> itemType.addTo(current);
                case REMOVE -> itemType.removeFrom(current);
                case REMOVE_ALL -> itemType.removeAll(current);
                default -> null;
            };
        }
        if (flagWatcher instanceof EndermanWatcher endermanWatcher && equipmentSlot == EquipmentSlot.HAND)
            endermanWatcher.setItemInMainHand(itemStack);
        else flagWatcher.setItemStack(equipmentSlot, itemStack);
    }
}
