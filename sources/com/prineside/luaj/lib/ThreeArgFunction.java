package com.prineside.luaj.lib;

import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/ThreeArgFunction.class */
public abstract class ThreeArgFunction extends LibFunction {
    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public abstract LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3);

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final LuaValue call() {
        LuaValue luaValue = NIL;
        return call(luaValue, luaValue, NIL);
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue) {
        LuaValue luaValue2 = NIL;
        return call(luaValue, luaValue2, luaValue2);
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        return call(luaValue, luaValue2, NIL);
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public Varargs invoke(Varargs varargs) {
        return call(varargs.arg1(), varargs.arg(2), varargs.arg(3));
    }
}
