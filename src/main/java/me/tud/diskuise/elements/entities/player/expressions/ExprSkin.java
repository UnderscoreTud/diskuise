package me.tud.diskuise.elements.entities.player.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import com.github.retrooper.packetevents.protocol.player.UserProfile;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.libraryaddict.disguise.utilities.DisguiseUtilities;
import me.libraryaddict.disguise.utilities.SkinUtils;
import me.libraryaddict.disguise.utilities.SkinUtils.SkinCallback;
import me.libraryaddict.disguise.utilities.translations.LibsMsg;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Skin")
@Description("Set the displayed skin of a player disguise")
@Examples({
        "set skin of player's disguise to \"_tud\" parsed as offline player",
        "set skin of disguise {_disguise} to \"skin.png\"",
        "set skin of {_disguise} to \"skin\"",
        "set skin of target entity's disguise to \"https://crafatar.com/skins/2a50f585-2dd2-4043-a72b-6e20a6795a6d\"",
        "set player's disguise's skin to \"2a50f585-2dd2-4043-a72b-6e20a6795a6d\""
})
@Since("0.2-beta2, 0.3.3 (expression)")
@RequiredPlugins("LibsDisguises")
public class ExprSkin extends WatcherPropertyExpression<PlayerWatcher, String> {

    static {
        register(ExprSkin.class, String.class, "skin");
    }

    @Override
    protected String convert(PlayerWatcher playerWatcher) {
        return ((PlayerDisguise) playerWatcher.getDisguise()).getSkin();
    }

    @Override
    protected String getPropertyName() {
        return "skin";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(String.class, OfflinePlayer.class) : null;
    }

    @Override
    protected void change(Event e, PlayerWatcher playerWatcher, @Nullable Object[] delta, ChangeMode mode) {
        if (delta[0] instanceof OfflinePlayer offlinePlayer) {
            String name = offlinePlayer.getName();
            if (name == null)
                return;
            UserProfile profile = DisguiseUtilities.getUserProfile(name);
            if (profile == null)
                return;
            playerWatcher.setSkin(profile);
            return;
        }
        String skin = (String) delta[0];
        if (skin == null)
            return;
        SkinUtils.grabSkin(skin, new SkinCallback() {
            @Override
            public void onError(LibsMsg libsMsg, Object... objects) { }

            @Override
            public void onInfo(LibsMsg libsMsg, Object... objects) { }

			@Override
			public void onSuccess(UserProfile userProfile) {
				playerWatcher.setSkin(userProfile);
			}

        });
    }
}
