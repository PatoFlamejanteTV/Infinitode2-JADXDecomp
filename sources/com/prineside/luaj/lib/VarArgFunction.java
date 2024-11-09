package com.prineside.luaj.lib;

import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/VarArgFunction.class */
public abstract class VarArgFunction extends LibFunction {
    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call() {
        return invoke(NONE).arg1();
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue) {
        return invoke(luaValue).arg1();
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        return invoke(varargsOf(luaValue, luaValue2)).arg1();
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return invoke(LuaValue.varargsOf(luaValue, luaValue2, luaValue3)).arg1();
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public Varargs invoke(Varargs varargs) {
        return onInvoke(varargs).eval();
    }

    @Override // com.prineside.luaj.LuaValue
    public Varargs onInvoke(Varargs varargs) {
        return invoke(varargs);
    }
}
