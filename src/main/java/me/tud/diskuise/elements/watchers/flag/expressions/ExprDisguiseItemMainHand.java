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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Item in main hand")
@Description("Get or set the item in a disguise's main hand")
@Examples("broadcast main hand item of player's disguise")
@Since("0.2-beta0")
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
        return new ItemStack[]{disguise.getWatcher() != null ?
                disguise.getWatcher().getItemInMainHand() : null};
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
        if (!disguise.isMobDisguise()) return;
        FlagWatcher watcher = disguise.getWatcher();
        switch (mode) {
            case SET -> watcher.setItemInMainHand((ItemStack) delta[0]);
            case DELETE, REMOVE_ALL, RESET -> watcher.setItemInMainHand(new ItemStack(Material.AIR));
        }
        DisguiseUtil.update(disguise);
    }
}
