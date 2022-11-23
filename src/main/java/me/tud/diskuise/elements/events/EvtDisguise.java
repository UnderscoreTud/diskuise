package me.tud.diskuise.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.events.DisguiseEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EvtDisguise extends SkriptEvent {

    static {
        Skript.registerEvent("On Disguise", EvtDisguise.class, DisguiseEvent.class,
                        "[entity] disguise [(of|for) %-entitydatas%]")
                .description("This event gets called when an entity disguises"
                        , "(Cancellable)")
                .examples("on entity disguise:",
                        "\tbroadcast \"%event-entity% disguised as %type of event-disguise%!\"")
                .since("0.2-beta0")
                .requiredPlugins("LibsDisguises");
        EventValues.registerEventValue(DisguiseEvent.class, Entity.class, new Getter<>() {
            @Override
            public @Nullable Entity get(DisguiseEvent arg) {
                return arg.getDisguised();
            }
        }, 0);
        EventValues.registerEventValue(DisguiseEvent.class, Disguise.class, new Getter<>() {
            @Override
            public @Nullable Disguise get(DisguiseEvent arg) {
                return arg.getDisguise();
            }
        }, 0);
    }

    private Literal<EntityData<?>> entities;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        entities = (Literal<EntityData<?>>) args[0];
        return true;
    }

    @Override
    public boolean check(Event e) {
        if (entities == null) return true;
        Entity entity = ((DisguiseEvent) e).getEntity();
        return entities.check(e, o -> o.isInstance(entity));
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "disguise" + (entities == null ? "" : " of " + entities.toString(e, debug));
    }
}
