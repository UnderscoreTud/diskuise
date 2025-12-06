package me.tud.diskuise.elements.entities.arrow.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.coll.CollectionUtils;
import me.tud.diskuise.elements.entities.arrow.BetterTippedArrowWatcher;
import me.tud.diskuise.util.Util;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Arrow Disguise - Tipped Arrow Color")
@Description("Set or get the tip particle color of an arrow disguise")
@Examples("set tip color of player's disguise to blue")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprTippedArrowColor extends WatcherPropertyExpression<BetterTippedArrowWatcher, SkriptColor> {

    static {
        register(ExprTippedArrowColor.class, SkriptColor.class, "[arrow] tip color");
    }

    @Override
    protected SkriptColor convert(BetterTippedArrowWatcher betterTippedArrowWatcher) {
        Util.log(betterTippedArrowWatcher.getColor() + "");
        return SkriptColor.fromBukkitColor(betterTippedArrowWatcher.getColor());
    }

    @Override
    protected String getPropertyName() {
        return "tipped arrow color";
    }

    @Override
    public Class<? extends SkriptColor> getReturnType() {
        return SkriptColor.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, RESET, DELETE -> CollectionUtils.array(Color.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, BetterTippedArrowWatcher betterTippedArrowWatcher, @Nullable Object[] delta, ChangeMode mode) {
        if (delta == null) {
            betterTippedArrowWatcher.removeColor();
            return;
        }
        org.bukkit.Color color = ((SkriptColor) delta[0]).asBukkitColor();
        betterTippedArrowWatcher.setColor(color);
    }
}
