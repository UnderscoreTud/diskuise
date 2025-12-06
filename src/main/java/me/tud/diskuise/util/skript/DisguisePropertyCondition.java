package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;

public abstract class DisguisePropertyCondition extends PropertyCondition<Disguise> {

    public static void register(final Class<? extends Condition> c, final String property) {
        register(c, PropertyType.BE, property);
    }

    public static void register(final Class<? extends Condition> c, final PropertyType propertyType, final String property) {
        switch (propertyType) {
            case BE:
                Skript.registerCondition(c,
                        "[disguise[s]] %disguises% (is|are) " + property,
                        "[disguise[s]] %disguises% (isn't|is not|aren't|are not) " + property);
                break;
            case CAN:
                Skript.registerCondition(c,
                        "[disguise[s]] %disguises% can " + property,
                        "[disguise[s]] %disguises% (can't|cannot|can not) " + property);
                break;
            case HAVE:
                Skript.registerCondition(c,
                        "[disguise[s]] %disguises% (has|have) " + property,
                        "[disguise[s]] %disguises% (doesn't|does not|do not|don't) have " + property);
                break;
            default:
                assert false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<? extends Disguise>) exprs[0]);
        setNegated(matchedPattern == 1);
        return true;
    }
}
