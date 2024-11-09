package com.prineside.tdi2.ui.shared.luaWhitelistEditor;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/EMethod.class */
public final class EMethod extends TreeEntry {

    /* renamed from: b, reason: collision with root package name */
    private final Method f3775b;

    public EMethod(Method method, TreeEntry treeEntry) {
        super(treeEntry, "");
        this.f3775b = method;
    }

    public final Method getMethod() {
        return this.f3775b;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final void applyStateFromWhitelist(Whitelist whitelist) {
        if (whitelist.isMethodBlackListed(this.f3775b)) {
            this.state = 2;
        } else if (whitelist.isMethodWhiteListedInDeclaringClass(this.f3775b)) {
            this.state = 1;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final int getSortCategory() {
        if (Modifier.isStatic(this.f3775b.getModifiers())) {
            return 40;
        }
        return 41;
    }

    public final String getMethodSignature() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f3775b.getName()).append(":");
        for (Parameter parameter : this.f3775b.getParameters()) {
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
                array.add("+m:" + getMethodSignature());
                return;
            case 2:
                array.add("-m:" + getMethodSignature());
                return;
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    protected final boolean a() {
        return true;
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f3775b.getName()).append("(");
        int i = 0;
        for (Parameter parameter : this.f3775b.getParameters()) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(parameter.getName()).append("[").append(LuajavaWhitelistEditor.TYPE_COLOR).append("]: ").append(parameter.getType().getSimpleName()).append("[]");
            i++;
        }
        stringBuilder.append(")[").append(LuajavaWhitelistEditor.TYPE_COLOR).append("]: ").append(this.f3775b.getReturnType().getSimpleName()).append("[]");
        return stringBuilder.toString();
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final String getSortName() {
        return getMethodSignature();
    }

    public final String toString() {
        return "M:" + getName();
    }

    @Override // com.prineside.tdi2.ui.shared.luaWhitelistEditor.TreeEntry
    public final Drawable getEntryIcon() {
        if (Modifier.isStatic(this.f3775b.getModifiers())) {
            return Game.i.assetManager.getQuad("icons.classTree.static-method");
        }
        return Game.i.assetManager.getQuad("icons.classTree.method");
    }
}
