package com.prineside.tdi2.ui.shared.luaWhitelistEditor;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/EClass.class */
public final class EClass extends TreeEntry {
    public Class<?> forClass;

    public EClass(Class<?> cls, TreeEntry treeEntry) {
        super(treeEntry, cls.getSimpleName());
        this.forClass = cls;
    }

    public final Class<?> getForClass() {
        return this.forClass;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void applyStateFromWhitelist(Whitelist whitelist) {
        if (whitelist.isClassBlackListed(this.forClass)) {
            this.state = 2;
        }
    }

    public final String toString() {
        return "C:" + getName();
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final int getSortCategory() {
        if (this.forClass.isInterface()) {
            return 10;
        }
        return 11;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final int getState() {
        if (this.state == 2) {
            return 2;
        }
        boolean z = true;
        boolean z2 = true;
        boolean z3 = true;
        int i = 0;
        while (true) {
            if (i >= this.children.size) {
                break;
            }
            int state = this.children.get(i).getState();
            if (state == 0) {
                z = false;
                break;
            }
            if (state != 1 && state != 4) {
                z2 = false;
            }
            if (state != 2 && state != 5) {
                z3 = false;
            }
            i++;
        }
        if (z) {
            if (z3) {
                return 5;
            }
            if (z2) {
                return 4;
            }
            return 3;
        }
        return 0;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void gatherSaveData(Array<String> array) {
        if (getState() == 2) {
            array.add("-c:" + this.f3777a);
            return;
        }
        Array<String> array2 = new Array<>();
        for (int i = 0; i < this.children.size; i++) {
            this.children.get(i).gatherSaveData(array2);
        }
        if (array2.size != 0) {
            array.add(">c:" + this.f3777a + "{");
            for (int i2 = 0; i2 < array2.size; i2++) {
                array.add(SequenceUtils.SPACE + array2.get(i2));
            }
            array.add("}");
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final Drawable getEntryIcon() {
        if (this.forClass.isInterface()) {
            return Game.i.assetManager.getQuad("icons.classTree.interface");
        }
        if (this.forClass.isEnum()) {
            return Game.i.assetManager.getQuad("icons.classTree.enum");
        }
        return Game.i.assetManager.getQuad("icons.classTree.class");
    }
}
