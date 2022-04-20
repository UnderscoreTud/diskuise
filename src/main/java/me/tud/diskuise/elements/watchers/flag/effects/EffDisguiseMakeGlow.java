package me.tud.diskuise.elements.watchers.flag.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Make Glow")
@Description("Sets if a disguise appears to be glowing")
@Examples("make {dis} glow red")
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakeGlow extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakeGlow.class,
                "make [dis(k|g)uise] %disguise% [1¦not] glow[(s|ing)] [%-color%]");
    }

    Expression<Disguise> disguise;
    Expression<Color> color;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        SkriptColor color = this.color != null ? (SkriptColor) this.color.getSingle(e) : null;
        if (disguise == null) return;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return;
        if (color != null && !bool) {
            if (color.asChatColor() == watcher.getGlowColor()) watcher.setGlowing(false);
            return;
        }
        watcher.setGlowing(bool);
        if (!bool) return;
        if (color == null) return;
        watcher.setGlowColor(color.asChatColor());
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        if (exprs.length == 2) color = (Expression<Color>) exprs[1];
        bool = parseResult.mark != 1;
        return true;
    }
}
