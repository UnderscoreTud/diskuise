package me.tud.diskuise.elements.entities.droppeditem.expressions;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.DroppedItemWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Dropped Item Disguise - Item Stack")
@Description("Set or get the item of a dropped item disguise.")
@Examples("set the dropped item of {_dis} to diamond sword")
@Since("0.2-beta1")
@RequiredPlugins("LibsDisguises")
public class ExprItemStack extends WatcherPropertyExpression<DroppedItemWatcher, ItemType> {

    static {
        register(ExprItemStack.class, ItemType.class, "[dropped] item [stack]");
    }

    @Override
    protected ItemType convert(DroppedItemWatcher droppedItemWatcher) {
        return new ItemType(droppedItemWatcher.getItemStack());
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
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(ItemType.class) : null;
    }

    @Override
    protected void change(Event e, DroppedItemWatcher droppedItemWatcher, Object[] delta, Changer.ChangeMode mode) {
        droppedItemWatcher.setItemStack(((ItemType) delta[0]).getRandom());
    }
}
