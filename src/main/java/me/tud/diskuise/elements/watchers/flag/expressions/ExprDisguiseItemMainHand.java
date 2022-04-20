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
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Item in main hand")
@Description("Get or set the item in a disguise's main hand")
@Examples("broadcast main hand item of player's disguise")
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseItemMainHand extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprDisguiseItemMainHand.class, ItemStack.class, ExpressionType.PROPERTY,
                "[the] [main[( |-)]]hand [item] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s main[( |-)]hand [item]");
    }

    Expression<Disguise> disguise;

    @Override
    protected @Nullable
    ItemStack[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        if (!disguise.isMobDisguise()) return null;
        FlagWatcher watcher = disguise.getWatcher();
        return new ItemStack[]{(watcher.getItemInMainHand() != null ? watcher.getItemInMainHand() : new ItemStack(Material.AIR))};
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
        return true;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode changeMode) {
        if (
                changeMode != Changer.ChangeMode.SET &&
                changeMode != Changer.ChangeMode.DELETE &&
                changeMode != Changer.ChangeMode.REMOVE_ALL &&
                changeMode != Changer.ChangeMode.RESET) return null;
        return CollectionUtils.array(ItemStack.class);
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode changeMode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        if (!disguise.isMobDisguise()) return;
        FlagWatcher watcher = disguise.getWatcher();
        if (changeMode != Changer.ChangeMode.SET) {
            watcher.setItemInMainHand(new ItemStack(Material.AIR));
            return;
        }
        watcher.setItemInMainHand((ItemStack) delta[0]);
    }
}
