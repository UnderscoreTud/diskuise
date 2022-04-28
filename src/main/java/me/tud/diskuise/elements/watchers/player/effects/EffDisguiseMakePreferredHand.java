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
import org.bukkit.event.Event;
import org.bukkit.inventory.MainHand;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Make Preferred hand")
@Description("Sets the preferred hand of a player disguise")
@Examples("make player's disguise left handed")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakePreferredHand extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakePreferredHand.class,
                "make [dis(k|g)uise] %disguise% (1¦right|2¦left)[( |-)]handed");
    }

    Expression<Disguise> disguise;
    boolean isRightHanded;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        PlayerWatcher watcher;
        try {
            watcher = (PlayerWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        if (isRightHanded) watcher.setMainHand(MainHand.RIGHT);
        else watcher.setMainHand(MainHand.LEFT);
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
        isRightHanded = parseResult.mark == 1;
        return true;
    }
}
