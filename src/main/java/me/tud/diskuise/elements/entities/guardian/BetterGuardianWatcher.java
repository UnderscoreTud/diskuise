package me.tud.diskuise.elements.entities.guardian;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.GuardianWatcher;
import org.bukkit.entity.Entity;

public class BetterGuardianWatcher extends GuardianWatcher {
    public BetterGuardianWatcher(Disguise disguise) {
        super(disguise);
    }

    private Entity entity = null;

    @Override
    public void setTarget(Entity entity) {
        super.setTarget(entity);
        this.entity = entity;
    }

    public Entity getTargetEntity() {
        return entity;
    }
}
