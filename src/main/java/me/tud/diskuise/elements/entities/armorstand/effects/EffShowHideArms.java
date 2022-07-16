package me.tud.diskuise.elements.entities.armorstand.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.elements.entities.armorstand.BetterArmorStandWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Armor Stand Disguise - Arms Visibility")
@Description("Set whether an armor stand disguise has its arms shown")
@Examples("show the arms of player's disguise")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class EffShowHideArms extends Effect {

    static {
        Skript.registerEffect(EffShowHideArms.class,
                "(:show|:hide) [the] arms of [dis(g|k)uise[s]] %disguises%",
                "(:show|:hide) [dis(g|k)uise[s]] %disguises%'[s] arms");
    }

    private Expression<Disguise> disguiseExpr;
    private boolean show;

    @Override
    protected void execute(Event e) {
        for (Disguise disguise : disguiseExpr.getArray(e))
            if (disguise.getWatcher() instanceof BetterArmorStandWatcher armorStandWatcher)
                armorStandWatcher.setShowArms(show);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return (show ? "show" : "hide") + " the arms of " + disguiseExpr.toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        show = parseResult.hasTag("show");
        disguiseExpr = (Expression<Disguise>) exprs[0];
        return true;
    }
}
