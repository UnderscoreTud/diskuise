package me.tud.diskuise.elements.entities.itemframe;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.disguisetypes.watchers.ItemFrameWatcher;

public class BetterItemFrameWatcher extends ItemFrameWatcher {

    public BetterItemFrameWatcher(Disguise disguise) {
        super(disguise);
    }

    @Override
    public void setRotation(int rotation) {
        setData(MetaIndex.ITEMFRAME_ROTATION, rotation % 8);
        sendData(MetaIndex.ITEMFRAME_ROTATION);
    }
}
