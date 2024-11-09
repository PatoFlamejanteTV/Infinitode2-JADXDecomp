package com.prineside.luaj;

import com.prineside.luaj.LuaTable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/prineside/luaj/NonTableMetatable.class */
public class NonTableMetatable implements Metatable {

    /* renamed from: a, reason: collision with root package name */
    private final LuaValue f1503a;

    public NonTableMetatable(LuaValue luaValue) {
        this.f1503a = luaValue;
    }

    @Override // com.prineside.luaj.Metatable
    public boolean useWeakKeys() {
        return false;
    }

    @Override // com.prineside.luaj.Metatable
    public boolean useWeakValues() {
        return false;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue toLuaValue() {
        return this.f1503a;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaTable.Slot entry(LuaValue luaValue, LuaValue luaValue2) {
        return LuaTable.a(luaValue, luaValue2);
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue wrap(LuaValue luaValue) {
        return luaValue;
    }

    @Override // com.prineside.luaj.Metatable
    public LuaValue arrayget(LuaValue[] luaValueArr, int i) {
        return luaValueArr[i];
    }
}
