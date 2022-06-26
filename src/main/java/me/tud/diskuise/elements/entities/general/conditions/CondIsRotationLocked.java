package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Rotation Locked")
@Description("Checks whether the disguise's yaw or pitch axis are locked'")
@Examples("if player's disguise is pitch locked:")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class CondIsRotationLocked extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsRotationLocked.class, "(:pitch|:yaw) locked");
    }

    private boolean useYaw = false;

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        if (useYaw) flagWatcher.isYawLocked();
        return flagWatcher.isPitchLocked();
    }

    @Override
    protected String getPropertyName() {
        return (useYaw ? "yaw" : "pitch") + " locked";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        useYaw = parseResult.hasTag("yaw");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }
}
