package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Make Glow")
@Description("Sets whether the disguise is glowing")
@Examples({
        "make player's disguise glow",
        "make {_disguise} glow red"
})
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeGlow extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeGlow.class, "[:not] glow [%-color%]");
    }

    private Expression<SkriptColor> skriptColorExpr;

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean state) {
        ChatColor chatColor = null;
        if (skriptColorExpr != null) {
            SkriptColor skriptColor = skriptColorExpr.getSingle(e);
            assert skriptColor != null;
            chatColor = skriptColor.asChatColor();
        }
        if (!isNegated()) {
            flagWatcher.setGlowing(true);
            if (chatColor != null) flagWatcher.setGlowColor(chatColor);
        }
        else {
            if (chatColor == null) flagWatcher.setGlowing(false);
            else if (flagWatcher.getGlowColor().equals(chatColor)) flagWatcher.setGlowing(false);
        }
    }

    @Override
    protected String getProperty() {
        return null;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "make " + getExpr().toString(e, debug) + (isNegated() ? " not " : " ") + "glow" + ((skriptColorExpr == null) ? "" : " " + skriptColorExpr.toString(e, debug));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        skriptColorExpr = (Expression<SkriptColor>) exprs[1];
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }
}
