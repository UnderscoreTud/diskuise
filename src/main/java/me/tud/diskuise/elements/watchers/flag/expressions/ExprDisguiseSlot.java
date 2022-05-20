package me.tud.diskuise.elements.watchers.flag.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Item in slot")
@Description("Get or set the item in one of a disguise's slots")
@Examples("broadcast chestplate slot of {disguise}")
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseSlot extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprDisguiseSlot.class, ItemStack.class, ExpressionType.PROPERTY,
                "[the] (1¦helmet|2¦chestplate|3¦leggings|4¦boots|5¦[main[( |-)]]hand|6¦off[( |-)]hand) [slot] [item] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s (1¦helmet|2¦chestplate|3¦leggings|4¦boots) [slot] [item]");
    }

    Expression<Disguise> disguise;
    int mark = 0;

    @Override
    protected @Nullable
    ItemStack[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        EquipmentSlot slot = null;
        switch (mark) {
            case 1 -> slot = EquipmentSlot.HEAD;
            case 2 -> slot = EquipmentSlot.CHEST;
            case 3 -> slot = EquipmentSlot.LEGS;
            case 4 -> slot = EquipmentSlot.FEET;
            case 5 -> slot = EquipmentSlot.HAND;
            case 6 -> slot = EquipmentSlot.OFF_HAND;
        }
        return new ItemStack[]{disguise.getWatcher() != null ?
                disguise.getWatcher().getItemStack(slot) : null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        mark = parseResult.mark;
        return true;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        switch (mode) {
            case SET, DELETE, REMOVE_ALL, RESET -> { return CollectionUtils.array(ItemStack.class); }
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        FlagWatcher watcher = disguise.getWatcher();
        ItemStack itemStack = new ItemStack(Material.AIR, 1);
        try {
            itemStack = (ItemStack) delta[0];
        } catch (NullPointerException ignore) {}
        EquipmentSlot slot = null;
        switch (mark) {
            case 1 -> slot = EquipmentSlot.HEAD;
            case 2 -> slot = EquipmentSlot.CHEST;
            case 3 -> slot = EquipmentSlot.LEGS;
            case 4 -> slot = EquipmentSlot.FEET;
            case 5 -> slot = EquipmentSlot.HAND;
            case 6 -> slot = EquipmentSlot.OFF_HAND;
        }
        watcher.setItemStack(slot, itemStack);
        DisguiseUtil.update(disguise);
    }
}
