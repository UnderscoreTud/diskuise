package me.tud.diskuise.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Is Disguised")
@Description("Checks if an entity is disguised")
@Examples({"if player is disguised:",
            "\tundisguise player"})
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsDisguised extends PropertyCondition<Entity> {

    static {
        register(CondIsDisguised.class, "[currently] dis(g|k)uised", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        return DisguiseAPI.isDisguised(entity);
    }

    @Override
    protected String getPropertyName() {
        return "disguised";
    }

}
