package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.*;
import me.tud.diskuise.elements.entities.itemframe.BetterItemFrameWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Disguise - Item Stack")
@Description({"Set or get the item of a disguise.",
        "For Dropped Item Disguises, it returns the item of it.",
        "For Item Frame Disguises, it returns the item inside the frame.",
        "For throwable projectiles (eggs, snowballs, enderpearls, etc.), it returns the displayed item.",
        "Other disguises don't have items associated with them."})
@Examples("set the dropped item of {_dis} to diamond sword")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class ExprItemStack extends WatcherPropertyExpression<FlagWatcher, ItemType> {

    static {
        register(ExprItemStack.class, ItemType.class, "[dropped] item [stack]");
    }

    @Override
    protected ItemType convert(FlagWatcher flagWatcher) {
        if (flagWatcher instanceof DroppedItemWatcher droppedItemWatcher)
            return new ItemType(droppedItemWatcher.getItemStack());
        if (flagWatcher instanceof BetterItemFrameWatcher itemFrameWatcher)
            return new ItemType(itemFrameWatcher.getItem());
        if (flagWatcher instanceof ThrowableWatcher throwableWatcher)
            return new ItemType(throwableWatcher.getItemStack());
        if (flagWatcher instanceof EnderSignalWatcher enderSignalWatcher)
            return new ItemType(enderSignalWatcher.getItemStack());
        if (flagWatcher instanceof FireballWatcher fireballWatcher)
            fireballWatcher.getItemStack();
        if (flagWatcher instanceof SplashPotionWatcher splashPotionWatcher)
            splashPotionWatcher.getSplashPotion();
        if (flagWatcher instanceof FireworkWatcher fireworkWatcher)
            fireworkWatcher.getFirework();
        return null;
    }

    @Override
    protected String getPropertyName() {
        return "item stack";
    }

    @Override
    public Class<? extends ItemType> getReturnType() {
        return ItemType.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(ItemType.class) : null;
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, ChangeMode mode) {
        ItemStack itemStack = ((ItemType) delta[0]).getRandom();
        if (flagWatcher instanceof DroppedItemWatcher droppedItemWatcher)
            droppedItemWatcher.setItemStack(itemStack);
        else if (flagWatcher instanceof BetterItemFrameWatcher itemFrameWatcher)
            itemFrameWatcher.setItem(itemStack);
        else if (flagWatcher instanceof ThrowableWatcher throwableWatcher)
            throwableWatcher.setItemStack(itemStack);
        else if (flagWatcher instanceof EnderSignalWatcher enderSignalWatcher)
            enderSignalWatcher.setItemStack(itemStack);
        else if (flagWatcher instanceof FireballWatcher fireballWatcher)
            fireballWatcher.setItemStack(itemStack);
        else if (flagWatcher instanceof SplashPotionWatcher splashPotionWatcher)
            splashPotionWatcher.setSplashPotion(itemStack);
        else if (flagWatcher instanceof FireworkWatcher fireworkWatcher)
            fireworkWatcher.setFirework(itemStack);
    }
}
