package me.tud.diskuise.elements.entities.fallingblock.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Falling Block Disguise - Block")
@Description({"Set or get the block of a falling block disguise.",
        "NOTE: This expression also supports block data."})
@Examples("set the falling block of {_dis} to diamond block")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class ExprBlock extends WatcherPropertyExpression<FallingBlockWatcher, ItemStack> {

    static {
        register(ExprBlock.class, ItemStack.class, "[falling] block [type]");
    }

    @Override
    protected ItemStack convert(FallingBlockWatcher fallingBlockWatcher) {
        return fallingBlockWatcher.getBlock();
    }

    @Override
    protected String getPropertyName() {
        return "block";
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(ItemStack.class, BlockData.class) : null;
    }

    @Override
    protected void change(Event e, FallingBlockWatcher fallingBlockWatcher, Object[] delta, Changer.ChangeMode mode) {
        if (delta[0] instanceof ItemStack itemStack)
            fallingBlockWatcher.setBlock(itemStack);
        else if (delta[0] instanceof BlockData blockData)
            fallingBlockWatcher.setBlockData(blockData);
    }
}
