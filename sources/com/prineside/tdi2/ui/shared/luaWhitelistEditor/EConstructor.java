package com.prineside.tdi2.ui.shared.luaWhitelistEditor;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/EConstructor.class */
public final class EConstructor extends TreeEntry {

    /* renamed from: b, reason: collision with root package name */
    private final Constructor<?> f3773b;

    public EConstructor(TreeEntry treeEntry, Constructor<?> constructor) {
        super(treeEntry, "");
        this.f3773b = constructor;
    }

    public final Constructor<?> getConstructor() {
        return this.f3773b;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void applyStateFromWhitelist(Whitelist whitelist) {
        if (whitelist.isConstructorWhiteListed(this.f3773b)) {
            this.state = 1;
        } else if (whitelist.isConstructorBlackListed(this.f3773b)) {
            this.state = 2;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    protected final boolean a() {
        return true;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((EClass) this.parent).getName());
        stringBuilder.append("(");
        int i = 0;
        for (Parameter parameter : this.f3773b.getParameters()) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(parameter.getName()).append("[").append(LuajavaWhitelistEditor.TYPE_COLOR).append("]: ").append(parameter.getType().getSimpleName()).append("[]");
            i++;
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final String getCtorSignature() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Parameter parameter : this.f3773b.getParameters()) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(parameter.getType().getSimpleName());
            i++;
        }
        return stringBuilder.toString();
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void gatherSaveData(Array<String> array) {
        switch (getState()) {
            case 1:
                array.add("+x:" + getCtorSignature());
                return;
            case 2:
                array.add("-x:" + getCtorSignature());
                return;
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final int getSortCategory() {
        if (this.f3773b.getParameters().length == 0) {
            return 20;
        }
        return 21;
    }

    public final String toString() {
        return "Ctor:" + getName();
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final Drawable getEntryIcon() {
        return Game.i.assetManager.getQuad("icons.classTree.constructor");
    }
}
