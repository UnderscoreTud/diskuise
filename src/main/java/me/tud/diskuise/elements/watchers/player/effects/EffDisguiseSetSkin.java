package me.tud.diskuise.elements.watchers.player.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Set skin")
@Description("Sets the skin of a player disguise")
@Examples("set skin of player's disguise to \"_tud\"")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseSetSkin extends Effect {

    static {
        Skript.registerEffect(EffDisguiseSetSkin.class,
                "(set|change) [(appeared|displayed)] skin of [dis(k|g)uise] %disguise% to %string/offlineplayer%");
    }

    Expression<Disguise> disguise;
    Expression<Object> object;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        Object object = this.object.getSingle(e);
        if (disguise == null) return;
        PlayerWatcher watcher;
        if (disguise.getWatcher() instanceof PlayerWatcher) watcher = (PlayerWatcher) disguise.getWatcher();
        else return;
        String skin;
        if (object instanceof OfflinePlayer) skin = ((OfflinePlayer) object).getName();
        else skin = (String) object;
        watcher.setSkin(skin);
        DisguiseUtil.update(disguise);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        object = (Expression<Object>) exprs[1];
        return true;
    }
}
