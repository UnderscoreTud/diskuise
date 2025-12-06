package me.tud.diskuise.elements.entities.endercrystal.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.github.retrooper.packetevents.util.Vector3i;
import me.libraryaddict.disguise.disguisetypes.watchers.EnderCrystalWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Ender Crystal Disguise - Beam Target")
@Description("Get or set the target location of an ender crystal disguise's beam")
@Examples("set beam target of player's disguise to {_loc}")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class ExprBeamTarget extends WatcherPropertyExpression<EnderCrystalWatcher, Location> {

    static {
        register(ExprBeamTarget.class, Location.class, "beam target");
    }

    @Override
    protected Location convert(EnderCrystalWatcher enderCrystalWatcher) {
        Vector3i blockPosition = enderCrystalWatcher.getBeamTarget();
        if (blockPosition == null)
            return null;
        return new Location(
            enderCrystalWatcher.getDisguise().getEntity().getWorld(),
            blockPosition.getX(), blockPosition.getY(), blockPosition.getZ()
        );
        //return blockPosition.toLocation(enderCrystalWatcher.getDisguise().getEntity().getWorld());
    }

    @Override
    protected String getPropertyName() {
        return "beam target";
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, DELETE, RESET -> CollectionUtils.array(Location.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, EnderCrystalWatcher enderCrystalWatcher, @Nullable Object[] delta, ChangeMode mode) {
        switch (mode) {
            case DELETE, RESET -> enderCrystalWatcher.setBeamTarget(null);
            case SET -> {
                Location location = (Location) delta[0];
                if (location == null) return;
				Vector3i vector = new Vector3i(location.getBlockX(), location.getBlockY(), location.getBlockZ());
                enderCrystalWatcher.setBeamTarget(vector);
            }
        }
    }
}
