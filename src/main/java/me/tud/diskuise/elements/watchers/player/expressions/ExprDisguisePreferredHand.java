package me.tud.diskuise.elements.watchers.player.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Preferred hand")
@Description("Get a player disguise's preferred hand (as a string)")
@Examples("broadcast the preferred hand of player's disguise")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguisePreferredHand extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprDisguisePreferredHand.class, String.class, ExpressionType.PROPERTY,
                "[the] preferred hand of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s preferred hand");
    }

    Expression<Disguise> disguise;

    @Override
    protected String[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        String preferredHand = disguise.getWatcher() instanceof PlayerWatcher ?
                ((PlayerWatcher) disguise.getWatcher()).getMainHand().name().toLowerCase() : null;
        return new String[]{preferredHand};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
}
