package com.prineside.luaj;

import com.prineside.luaj.LuaTable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/prineside/luaj/Metatable.class */
public interface Metatable {
    boolean useWeakKeys();

    boolean useWeakValues();

    LuaValue toLuaValue();

    LuaTable.Slot entry(LuaValue luaValue, LuaValue luaValue2);

    LuaValue wrap(LuaValue luaValue);

    LuaValue arrayget(LuaValue[] luaValueArr, int i);
}
