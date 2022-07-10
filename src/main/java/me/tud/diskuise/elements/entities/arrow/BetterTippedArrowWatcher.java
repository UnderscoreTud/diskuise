package me.tud.diskuise.elements.entities.arrow;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.disguisetypes.watchers.TippedArrowWatcher;

public class BetterTippedArrowWatcher extends TippedArrowWatcher {

    public BetterTippedArrowWatcher(Disguise disguise) {
        super(disguise);
    }

    public void removeColor() {
        setData(MetaIndex.TIPPED_ARROW_COLOR, -1);
        sendData(MetaIndex.TIPPED_ARROW_COLOR);
    }

}
