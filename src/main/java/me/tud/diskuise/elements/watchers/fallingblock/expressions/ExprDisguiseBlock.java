package me.tud.diskuise.elements.watchers.fallingblock.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Falling Block Disguise - Block")
@Description("Set or get the block of a falling block disguise (Supports block data)")
@Examples("set the falling block of {_dis} to diamond block")
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseBlock extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprDisguiseBlock.class, ItemStack.class, ExpressionType.PROPERTY,
                "[the] [falling] block [type] of [dis(k|g)uise] %disguise%");
    }

    Expression<Disguise> disguise;

    @Override
    protected ItemStack[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        FallingBlockWatcher watcher;
        try {
            watcher = (FallingBlockWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        return new ItemStack[]{watcher.getBlock()};
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
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return CollectionUtils.array(ItemStack.class, BlockData.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        FallingBlockWatcher watcher;
        try {
            watcher = (FallingBlockWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }

        if (delta[0] instanceof ItemStack) {
            ItemStack block = (ItemStack) delta[0];
            if (!block.getType().isBlock()) return;
            watcher.setBlock(block);
        }

        else if (delta[0] instanceof BlockData) {
            BlockData blockData = (BlockData) delta[0];
            watcher.setBlockData(blockData);
        }

        else return;

        DisguiseUtil.update(disguise);
    }
}
