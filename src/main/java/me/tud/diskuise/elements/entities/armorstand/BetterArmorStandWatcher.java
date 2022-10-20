package me.tud.diskuise.elements.entities.armorstand;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;

public class BetterArmorStandWatcher extends ArmorStandWatcher {

    public BetterArmorStandWatcher(Disguise disguise) {
        super(disguise);
    }

    private void setArmorStandFlag(int value, boolean isTrue) {
        byte b1 = this.getData(MetaIndex.ARMORSTAND_META);
        if (isTrue) {
            b1 = (byte) (b1 | value);
        }
        else {
            b1 = (byte) (b1 & ~value);
        }

        this.setData(MetaIndex.ARMORSTAND_META, b1);
        this.sendData(MetaIndex.ARMORSTAND_META);
    }

    @Override
    public void setSmall(boolean isSmall) {
        setArmorStandFlag(1, isSmall);
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        setArmorStandFlag(1 << 1, noGravity);
    }

    @Override
    public void setShowArms(boolean showArms) {
        setArmorStandFlag(1 << 2, showArms);
    }

    @Override
    public void setNoBasePlate(boolean noBasePlate) {
        setArmorStandFlag(1 << 3, noBasePlate);
    }

    @Override
    public void setMarker(boolean isMarker) {
        setArmorStandFlag(1 << 4, isMarker);
    }
}
