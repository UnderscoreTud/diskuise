package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.MinecartWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Disguise - Block")
@Description({"Set or get the block of a disguise.",
        "This expression only works on falling block and minecart disguise",
        "NOTE: This expression also supports block data."})
@Examples("set the block of {_dis} to diamond block")
@Since("0.2-beta1, 0.3.3 (minecart)")
@RequiredPlugins("LibsDisguises")
public class ExprBlock extends WatcherPropertyExpression<FlagWatcher, ItemType> {

    static {
        register(ExprBlock.class, ItemType.class, "block [type]");
    }

    @Override
    protected ItemType convert(FlagWatcher flagWatcher) {
        if (flagWatcher instanceof FallingBlockWatcher fallingBlockWatcher)
            return new ItemType(fallingBlockWatcher.getBlock());
        if (flagWatcher instanceof MinecartWatcher minecartWatcher)
            return new ItemType(minecartWatcher.getBlockInCart());
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "block";
    }

    @Override
    public Class<? extends ItemType> getReturnType() {
        return ItemType.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(ItemType.class, BlockData.class) : null;
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, ChangeMode mode) {
        if (delta[0] instanceof ItemType itemType) {
            if (flagWatcher instanceof FallingBlockWatcher fallingBlockWatcher)
                fallingBlockWatcher.setBlock(itemType.getRandom());
            else if (flagWatcher instanceof MinecartWatcher minecartWatcher)
                minecartWatcher.setBlockInCart(itemType.getRandom());
        }
        else if (delta[0] instanceof BlockData blockData) {
            if (flagWatcher instanceof FallingBlockWatcher fallingBlockWatcher)
                fallingBlockWatcher.setBlockData(blockData);
            else if (flagWatcher instanceof MinecartWatcher minecartWatcher)
                minecartWatcher.setBlockData(blockData);
        }
    }
}
