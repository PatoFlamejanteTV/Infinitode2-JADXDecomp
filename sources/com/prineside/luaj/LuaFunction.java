package com.prineside.luaj;

/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaFunction.class */
public abstract class LuaFunction extends LuaValue {
    @Override // com.prineside.luaj.LuaValue
    public final int type() {
        return 6;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String typename() {
        return "function";
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isfunction() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaFunction checkfunction() {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaFunction optfunction(LuaFunction luaFunction) {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue getmetatable() {
        return null;
    }

    @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public String tojstring() {
        return "function: " + classnamestub();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString strvalue() {
        return valueOf(tojstring());
    }

    public final String classnamestub() {
        String name = getClass().getName();
        int max = Math.max(name.lastIndexOf(46), name.lastIndexOf(36)) + 1;
        if (name.charAt(max) == '_') {
            max++;
        }
        return name.substring(max);
    }

    public String name() {
        return classnamestub();
    }
}
