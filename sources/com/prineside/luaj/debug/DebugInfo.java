package com.prineside.luaj.debug;

import com.prineside.luaj.FPrototype;
import com.prineside.luaj.LuaFunction;

/* loaded from: infinitode-2.jar:com/prineside/luaj/debug/DebugInfo.class */
public final class DebugInfo {

    /* renamed from: a, reason: collision with root package name */
    String f1562a;

    /* renamed from: b, reason: collision with root package name */
    String f1563b;
    private int c;

    public final void funcinfo(LuaFunction luaFunction) {
        if (luaFunction.isclosure()) {
            FPrototype fPrototype = luaFunction.checkclosure().p;
            if (fPrototype.source != null) {
                fPrototype.source.tojstring();
            }
            this.c = fPrototype.linedefined;
            fPrototype.shortsource();
            return;
        }
        this.c = -1;
        luaFunction.name();
    }
}
