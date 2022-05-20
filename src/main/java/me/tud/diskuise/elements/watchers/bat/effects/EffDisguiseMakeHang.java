package me.tud.diskuise.elements.watchers.bat.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.BatWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Bat Disguise - Make Hang")
@Description("Sets if a bat disguise appears to be hanging upside down")
@Examples("make {disguise} hang upside down")
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakeHang extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakeHang.class,
                "make [dis(k|g)uise] %disguise% [1¦not] hang [upside[ ]down]");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        BatWatcher watcher;
        if (disguise.getWatcher() instanceof BatWatcher) watcher = (BatWatcher) disguise.getWatcher();
        else return;
        watcher.setHanging(bool);
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
        bool = parseResult.mark != 1;
        return true;
    }
}
