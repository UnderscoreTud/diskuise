package me.tud.diskuise.elements.entities.armorstand;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.Util;

public class BetterArmorStandWatcher extends ArmorStandWatcher {

    public BetterArmorStandWatcher(Disguise disguise) {
        super(disguise);
    }

    private void setArmorStandFlag(int value, boolean isTrue) {
        byte b1 = (Byte)this.getData(MetaIndex.ARMORSTAND_META);
        Util.log(b1 + "");
        if (isTrue) {
            b1 = (byte) (b1 | value);
        } else {
            b1 = (byte) (b1 & ~value);
        }

        this.setData(MetaIndex.ARMORSTAND_META, b1);
        this.sendData(MetaIndex.ARMORSTAND_META);
    }

    @Override
    public void setShowArms(boolean showArms) {
        setArmorStandFlag(4, showArms);
    }

    @Override
    public void setSmall(boolean isSmall) {
        setArmorStandFlag(1, isSmall);
    }

    @Override
    public void setNoBasePlate(boolean noBasePlate) {
        setArmorStandFlag(8, noBasePlate);
    }

    @Override
    public void setMarker(boolean isMarker) {
        setArmorStandFlag(16, isMarker);
    }
}
