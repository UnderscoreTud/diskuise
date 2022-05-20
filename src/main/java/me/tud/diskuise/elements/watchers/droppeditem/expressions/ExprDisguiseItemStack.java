package me.tud.diskuise.elements.watchers.droppeditem.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.DroppedItemWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Dropped Item Disguise - Item Stack")
@Description("Set or get the item of a dropped item disguise")
@Examples("set the dropped item of {_dis} to diamond sword")
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseItemStack extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprDisguiseItemStack.class, ItemStack.class, ExpressionType.PROPERTY,
                "[the] [dropped] item [stack] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [dropped] item [stack]");
    }

    Expression<Disguise> disguise;

    @Override
    protected ItemStack[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        return new ItemStack[]{disguise.getWatcher() instanceof DroppedItemWatcher ?
                ((DroppedItemWatcher) disguise.getWatcher()).getItemStack() : null};
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
        DroppedItemWatcher watcher;
        if (disguise.getWatcher() instanceof DroppedItemWatcher) watcher = (DroppedItemWatcher) disguise.getWatcher();
        else return;

        ItemStack itemStack = (ItemStack) delta[0];
        watcher.setItemStack(itemStack);

        DisguiseUtil.update(disguise);
    }
}
