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

@Name("Armor Stand Disguise - Base Plate Visibility")
@Description("Set whether an armor stand disguise has its base plate shown")
@Examples("hide base plate of player's disguise")
@Since("0.3.1")
@RequiredPlugins("LibsDisguises")
public class EffShowHideBasePlate extends Effect {

    static {
        Skript.registerEffect(EffShowHideBasePlate.class,
                "(:show|:hide) [the] base[ ]plate of [disguise[s]] %disguises%",
                "(:show|:hide) [disguise[s]] %disguises%'[s] base[ ]plate");
    }

    private Expression<Disguise> disguiseExpr;
    private boolean show = false;

    @Override
    protected void execute(Event e) {
        for (Disguise disguise : disguiseExpr.getArray(e))
            if (disguise.getWatcher() instanceof BetterArmorStandWatcher armorStandWatcher)
                armorStandWatcher.setNoBasePlate(!show);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return (show ? "show" : "hide") + " the base plate of " + disguiseExpr.toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        show = parseResult.hasTag("show");
        disguiseExpr = (Expression<Disguise>) exprs[0];
        return true;
    }
}
