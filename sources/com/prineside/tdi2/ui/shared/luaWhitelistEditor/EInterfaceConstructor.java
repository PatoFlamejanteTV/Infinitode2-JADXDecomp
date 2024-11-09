package com.prineside.tdi2.ui.shared.luaWhitelistEditor;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/EInterfaceConstructor.class */
public final class EInterfaceConstructor extends TreeEntry {
    public EInterfaceConstructor(TreeEntry treeEntry) {
        super(treeEntry, "");
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final String getName() {
        return ((EClass) this.parent).forClass.getSimpleName() + "()[#FFFFFF80] - interface proxying[]";
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void applyStateFromWhitelist(Whitelist whitelist) {
        if (whitelist.isInterfaceProxyWhiteListed(((EClass) this.parent).forClass)) {
            this.state = 1;
        } else if (whitelist.isInterfaceProxyBlackListed(((EClass) this.parent).forClass)) {
            this.state = 2;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void gatherSaveData(Array<String> array) {
        switch (getState()) {
            case 1:
                array.add("+z");
                return;
            case 2:
                array.add("-z");
                return;
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final int getSortCategory() {
        return 20;
    }

    public final String toString() {
        return "ICtor:" + getName();
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final Drawable getEntryIcon() {
        return Game.i.assetManager.getQuad("icons.classTree.interface-constructor");
    }
}
