package me.tud.diskuise.elements.entities.player.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Set Skin")
@Description("Set the displayed skin of a player disguise")
@Examples("set skin of player's disguise to \"_tud\"")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class EffSetSkin extends Effect {

    static {
        Skript.registerEffect(EffSetSkin.class,
                "set [the] skin of [dis(g|k)uise[s]] %disguises% to %string/offlineplayer%",
                "set [dis(g|k)uise[s]] %disguises%'[s] skin to %string/offlineplayer%");
    }

    private Expression<Disguise> disguiseExpr;
    private Expression<?> skinExpr;

    @Override
    protected void execute(Event e) {
        Disguise disguise = disguiseExpr.getSingle(e);
        String skin;
        if (skinExpr.getSingle(e) instanceof OfflinePlayer offlinePlayer)
            skin = offlinePlayer.getName();
        else skin = (String) skinExpr.getSingle(e);
        if (disguise == null || !(disguise.getWatcher() instanceof PlayerWatcher) || skin == null) return;
        ((PlayerWatcher) disguise.getWatcher()).setSkin(skin);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "set the skin of " + disguiseExpr.toString(e, debug) + " to " + skinExpr.toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguiseExpr = (Expression<Disguise>) exprs[0];
        skinExpr = exprs[1];
        return true;
    }
}
