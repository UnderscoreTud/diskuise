package me.tud.diskuise.elements.entities.itemframe.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.disguisetypes.watchers.ItemFrameWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Name("Item Frame Disguise - Rotation")
@Description("Set or get the rotation of an item frame disguise")
@Examples("set frame rotation of player's disguise to 5")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class ExprRotation extends WatcherPropertyExpression<ItemFrameWatcher, Number> {

    static {
        register(ExprRotation.class, Number.class, "frame rotation");
        try {
            setData = FlagWatcher.class.getDeclaredMethod("setData", MetaIndex.class, Object.class);
            sendData = FlagWatcher.class.getDeclaredMethod("sendData", MetaIndex[].class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static Method setData;
    private static Method sendData;

    @Override
    protected Number convert(ItemFrameWatcher itemFrameWatcher) {
        return itemFrameWatcher.getRotation();
    }

    @Override
    protected String getPropertyName() {
        return "frame rotation";
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setData.setAccessible(true);
        sendData.setAccessible(true);
        setExpr((Expression<? extends Disguise>) exprs[0]);
        return true;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(Number.class) : null;
    }

    @Override
    protected void change(Event e, ItemFrameWatcher itemFrameWatcher, Object[] delta, Changer.ChangeMode mode) {
        try {
            setData.invoke(itemFrameWatcher, MetaIndex.ITEMFRAME_ROTATION, ((Number) delta[0]).intValue() % 8);
            sendData.invoke(itemFrameWatcher, (Object) CollectionUtils.array(MetaIndex.ITEMFRAME_ROTATION));
        }
        catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}
