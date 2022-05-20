package me.tud.diskuise.elements.watchers.player.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Parrot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Parrot variant")
@Description({"Set or get the variant of a parrot on a player disguise's shoulder", "The only available colors are:", "red", "green", "blue", "aqua", "gray"})
@Examples("set left shoulder parrot variant of disguise {dis} to red")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseParrotVariant extends SimpleExpression<SkriptColor> {

    static {
        Skript.registerExpression(ExprDisguiseParrotVariant.class, SkriptColor.class, ExpressionType.PROPERTY,
                "[the] (1¦right|2¦left) [shoulder] parrot['s] (variant|color) [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s (1¦right|2¦left) [shoulder] parrot['s] (variant|color) [(value|option|state)]");
    }

    Expression<Disguise> disguise;
    boolean isRight = true;

    @Override
    protected SkriptColor[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        PlayerWatcher watcher;
        if (disguise.getWatcher() instanceof PlayerWatcher) watcher = (PlayerWatcher) disguise.getWatcher();
        else return null;
        if (isRight && !watcher.isRightShoulderHasParrot() ||
                !isRight && !watcher.isLeftShoulderHasParrot()) return null;
        Parrot.Variant variant = (isRight ? watcher.getRightShoulderParrot() : watcher.getLeftShoulderParrot());
        SkriptColor color = null;
        switch (variant) {
            case RED -> color = SkriptColor.DARK_RED;
            case GREEN -> color = SkriptColor.DARK_GREEN;
            case BLUE -> color = SkriptColor.DARK_BLUE;
            case CYAN -> color = SkriptColor.DARK_CYAN;
            case GRAY -> color = SkriptColor.DARK_GREY;
        }
        return new SkriptColor[]{color};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends SkriptColor> getReturnType() {
        return SkriptColor.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        if (parseResult.mark == 2) isRight = false;
        return true;
    }

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return CollectionUtils.array(Color.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        PlayerWatcher watcher;
        if (disguise.getWatcher() instanceof PlayerWatcher) watcher = (PlayerWatcher) disguise.getWatcher();
        else return;
        assert delta[0] != null;
        ChatColor color = ((SkriptColor) delta[0]).asChatColor();
        Parrot.Variant variant = null;
        switch (color.name()) {
            case "DARK_RED" -> variant = Parrot.Variant.RED;
            case "DARK_BLUE" -> variant = Parrot.Variant.BLUE;
            case "DARK_GREEN" -> variant = Parrot.Variant.GREEN;
            case "DARK_AQUA" -> variant = Parrot.Variant.CYAN;
            case "GRAY" -> variant = Parrot.Variant.GRAY;
        }
        if (variant == null) return;
        if (isRight) watcher.setRightShoulderParrot(variant);
        else watcher.setLeftShoulderParrot(variant);
        DisguiseUtil.update(disguise);
    }
}
