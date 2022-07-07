package me.tud.diskuise.elements.entities.bat.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BatWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Bat Disguise - Is Hanging")
@Description("Checks whether the bat disguise is hanging upside down")
@Examples("if player's disguise is hanging upside down:")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class CondIsHanging extends WatcherPropertyCondition<BatWatcher> {

    static {
        register(CondIsHanging.class, "hanging [upside[( |-)]down]");
    }

    @Override
    protected boolean check(BatWatcher batWatcher) {
        return batWatcher.isHanging();
    }

    @Override
    protected String getPropertyName() {
        return "hanging";
    }
}
