package me.tud.diskuise.elements.entities.armorstand;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;

public class BetterArmorStandWatcher extends ArmorStandWatcher {

    public BetterArmorStandWatcher(Disguise disguise) {
        super(disguise);
    }

    @Override
    public void setShowArms(boolean showArms) {
        this.setData(MetaIndex.ARMORSTAND_META, (byte) (showArms ? 6 : 2));
        this.sendData(MetaIndex.ARMORSTAND_META);
    }
}
