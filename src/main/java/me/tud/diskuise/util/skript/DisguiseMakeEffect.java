package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import me.libraryaddict.disguise.disguisetypes.Disguise;

public abstract class DisguiseMakeEffect extends MakeEffect<Disguise> {

    public static void register(final Class<? extends Effect> c, final String property) {
        Skript.registerEffect(c, "make [disguise[s]] %disguises% " + property);
    }

}
