package com.prineside.luaj;

import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaNil.class */
public class LuaNil extends LuaValue {

    /* renamed from: a, reason: collision with root package name */
    static final LuaNil f1482a = new LuaNil();

    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaNil$Serializer.class */
    public static class Serializer extends SingletonSerializer<LuaNil> {
        public Serializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public LuaNil read() {
            return LuaNil.f1482a;
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final int type() {
        return 0;
    }

    @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public final String toString() {
        return "nil";
    }

    @Override // com.prineside.luaj.LuaValue
    public final String typename() {
        return "nil";
    }

    @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public String tojstring() {
        return "nil";
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue not() {
        return LuaValue.TRUE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean toboolean() {
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isnil() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue getmetatable() {
        return null;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean equals(Object obj) {
        return obj instanceof LuaNil;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue checknotnil() {
        return a("value");
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isvalidkey() {
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean optboolean(boolean z) {
        return z;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaClosure optclosure(LuaClosure luaClosure) {
        return luaClosure;
    }

    @Override // com.prineside.luaj.LuaValue
    public final double optdouble(double d) {
        return d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaFunction optfunction(LuaFunction luaFunction) {
        return luaFunction;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int optint(int i) {
        return i;
    }

    @Override // com.prineside.luaj.LuaValue
    public final long optlong(long j) {
        return j;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaNumber optnumber(LuaNumber luaNumber) {
        return luaNumber;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaTable opttable(LuaTable luaTable) {
        return luaTable;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String optjstring(String str) {
        return str;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString optstring(LuaString luaString) {
        return luaString;
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object optuserdata(Object obj) {
        return obj;
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object optuserdata(Class cls, Object obj) {
        return obj;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue optvalue(LuaValue luaValue) {
        return luaValue;
    }
}
