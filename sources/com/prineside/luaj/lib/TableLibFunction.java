package com.prineside.luaj.lib;

import com.prineside.luaj.LuaValue;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLibFunction.class */
class TableLibFunction extends LibFunction {
    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call() {
        return argerror(1, "table expected, got no value");
    }
}
