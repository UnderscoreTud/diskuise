package me.tud.diskuise.elements.watchers.itemframe.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.IronGolemWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.ItemFrameWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Item Frame Disguise - Item")
@Description("Set or get the item of an item frame disguise")
@Examples("set the frame item of {_dis} to stick of unbreaking 1")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseItem extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprDisguiseItem.class, ItemStack.class, ExpressionType.PROPERTY,
                "[the] [item[( |-)]]frame item [stack] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [item[( |-)]]frame item [stack]");
    }

    Expression<Disguise> disguise;

    @Override
    protected ItemStack[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        return new ItemStack[]{disguise.getWatcher() instanceof ItemFrameWatcher ?
                ((ItemFrameWatcher) disguise.getWatcher()).getItem() : null};
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
        if (mode == Changer.ChangeMode.SET) return CollectionUtils.array(ItemStack.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        ItemFrameWatcher watcher;
        if (disguise.getWatcher() instanceof ItemFrameWatcher) watcher = (ItemFrameWatcher) disguise.getWatcher();
        else return;

        ItemStack itemStack = (ItemStack) delta[0];
        watcher.setItem(itemStack);

        DisguiseUtil.update(disguise);
    }
}
