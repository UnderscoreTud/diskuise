package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.SkriptUtils;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Disguise - Glowing Color")
@Description("Set or get the glow color of a disguise")
@Examples("set glowing color of disguise {dis} to red")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprGlowColor extends WatcherPropertyExpression<FlagWatcher, SkriptColor> {

    static {
        register(ExprGlowColor.class, SkriptColor.class, "glow[ing] color");
    }

    @Override
    protected SkriptColor convert(FlagWatcher flagWatcher) {
        return SkriptUtils.toSkriptColor(flagWatcher.getGlowColor());
    }

    @Override
    protected String getPropertyName() {
        return "glowing";
    }

    @Override
    public Class<? extends SkriptColor> getReturnType() {
        return SkriptColor.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(Color.class) : null;
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, Object[] delta, ChangeMode mode) {
        flagWatcher.setGlowColor(((SkriptColor) delta[0]).asChatColor());
    }
}
