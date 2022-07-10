package me.tud.diskuise.elements.entities.fallingblock.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.FallingBlockWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Falling Block Disguise - Block Data")
@Description("Set or get the block data of a falling block disguise.")
@Examples("set the falling block of {_dis} to furnace[lit=true]")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class ExprBlockData extends WatcherPropertyExpression<FallingBlockWatcher, BlockData> {

    static {
        register(ExprBlockData.class, BlockData.class, "[falling] block[ ]data");
    }

    @Override
    protected BlockData convert(FallingBlockWatcher fallingBlockWatcher) {
        return fallingBlockWatcher.getBlockData();
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
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(BlockData.class) : null;
    }

    @Override
    protected void change(Event e, FallingBlockWatcher fallingBlockWatcher, Object[] delta, Changer.ChangeMode mode) {
        fallingBlockWatcher.setBlockData((BlockData) delta[0]);
    }
}
