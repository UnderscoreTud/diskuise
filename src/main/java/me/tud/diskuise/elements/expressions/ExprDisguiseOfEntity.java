package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Disguise Of Entity")
@Description("Returns the disguise of an entity")
@Examples("broadcast player's disguise")
@Since("0.1")
@RequiredPlugins("LibsDisguises")
public class ExprDisguiseOfEntity extends SimpleExpression<Disguise> {

    static {
        Skript.registerExpression(ExprDisguiseOfEntity.class, Disguise.class, ExpressionType.COMBINED,
                "disguise[s] of %entities% [for %-players%]",
                "%entities%'[s] disguise[s] [for %-players%]");
    }

    private Expression<Entity> entityExpr;
    private Expression<Player> playerExpr;

    @Override
    protected @Nullable Disguise[] get(Event e) {
        List<Disguise> disguises = new ArrayList<>();
        if (playerExpr == null) {
            for (Entity entity : entityExpr.getArray(e))
                disguises.add(DisguiseUtils.getLastDisguise(entity));
        }
        else {
            for (Player player : playerExpr.getArray(e))
                for (Entity entity : entityExpr.getArray(e))
                    disguises.add(DisguiseAPI.getDisguise(player, entity));
        }
        return disguises.toArray(new Disguise[0]);
    }

    @Override
    public boolean isSingle() {
        return entityExpr.isSingle() && (playerExpr == null || playerExpr.isSingle());
    }

    @Override
    public Class<? extends Disguise> getReturnType() {
        return Disguise.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the disguise of " + entityExpr.toString(e, debug)
                + (playerExpr != null ? " for " + playerExpr.toString(e, debug) : "");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entityExpr = (Expression<Entity>) exprs[0];
        playerExpr = (Expression<Player>) exprs[1];
        return true;
    }
}
