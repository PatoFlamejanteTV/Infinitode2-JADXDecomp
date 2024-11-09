package com.prineside.tdi2.ui.shared.luaWhitelistEditor;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.scene2d.utils.Drawable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/EField.class */
public final class EField extends TreeEntry {

    /* renamed from: b, reason: collision with root package name */
    private final Field f3774b;

    public EField(Field field, TreeEntry treeEntry) {
        super(treeEntry, field.getName() + "[#FFFFFF80]: " + field.getType().getSimpleName() + "[]");
        this.f3774b = field;
    }

    public final Field getField() {
        return this.f3774b;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void applyStateFromWhitelist(Whitelist whitelist) {
        if (whitelist.isFieldWhiteListed(this.f3774b)) {
            this.state = 1;
        } else if (whitelist.isFieldBlackListed(this.f3774b)) {
            this.state = 2;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final String getSortName() {
        return this.f3774b.getName() + ": " + this.f3774b.getType().getSimpleName();
    }

    public final String toString() {
        return "F:" + getName();
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    protected final boolean a() {
        return true;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final int getSortCategory() {
        if (Modifier.isStatic(this.f3774b.getModifiers())) {
            return 30;
        }
        return 31;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void gatherSaveData(Array<String> array) {
        switch (getState()) {
            case 1:
                array.add("+f:" + this.f3774b.getName());
                return;
            case 2:
                array.add("-f:" + this.f3774b.getName());
                return;
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final Drawable getEntryIcon() {
        if (Modifier.isStatic(this.f3774b.getModifiers())) {
            return Game.i.assetManager.getQuad("icons.classTree.static-field");
        }
        return Game.i.assetManager.getQuad("icons.classTree.field");
    }
}
