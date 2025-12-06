package me.tud.diskuise.elements.entities.general.expressions;

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

@Name("Disguise - Block Data")
@Description({"Set or get the block data of a disguise.",
        "This expression only works on falling block and minecart disguise"})
@Examples("set the falling block of {_dis} to furnace[lit=true]")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class ExprBlockData extends WatcherPropertyExpression<FlagWatcher, BlockData> {

    static {
        register(ExprBlockData.class, BlockData.class, "block[ ]data");
    }

    @Override
    protected BlockData convert(FlagWatcher flagWatcher) {
        if (flagWatcher instanceof FallingBlockWatcher fallingBlockWatcher)
            return fallingBlockWatcher.getBlockData();
        if (flagWatcher instanceof MinecartWatcher minecartWatcher)
            return minecartWatcher.getBlockData();
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "block data";
    }

    @Override
    public Class<? extends BlockData> getReturnType() {
        return BlockData.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(BlockData.class) : null;
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, ChangeMode mode) {
        if (flagWatcher instanceof FallingBlockWatcher fallingBlockWatcher)
            fallingBlockWatcher.setBlockData((BlockData) delta[0]);
        else if (flagWatcher instanceof MinecartWatcher minecartWatcher)
            minecartWatcher.setBlockData((BlockData) delta[0]);
    }
}
