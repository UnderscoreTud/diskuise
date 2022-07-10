package me.tud.diskuise.elements.entities.endercrystal.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.EnderCrystalWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Ender Crystal Disguise - Has Bottom")
@Description("Checks whether an ender crystal disguise has the bottom shown")
@Examples("if player's disguise has a bottom")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class CondHasBottom extends WatcherPropertyCondition<EnderCrystalWatcher> {

    static {
        register(CondHasBottom.class, PropertyType.HAVE, "[a] bottom");
    }

    @Override
    protected boolean check(EnderCrystalWatcher enderCrystalWatcher) {
        return enderCrystalWatcher.isShowBottom();
    }

    @Override
    protected String getPropertyName() {
        return "bottom";
    }
}
