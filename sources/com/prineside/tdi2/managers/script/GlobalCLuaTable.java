package com.prineside.tdi2.managers.script;

import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/GlobalCLuaTable.class */
public final class GlobalCLuaTable extends LuaTable {

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/GlobalCLuaTable$Serializer.class */
    public static class Serializer extends SingletonSerializer<GlobalCLuaTable> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public GlobalCLuaTable read() {
            return Game.i.scriptManager.getGlobalCTable();
        }
    }

    public GlobalCLuaTable(LuaValue luaValue) {
        loadFrom(luaValue);
    }

    public final void loadFrom(LuaValue luaValue) {
        presize(luaValue.length(), 0);
        Varargs next = luaValue.next(LuaValue.NIL);
        while (true) {
            Varargs varargs = next;
            if (!varargs.arg1().isnil()) {
                LuaValue arg1 = varargs.arg1();
                LuaValue arg = varargs.arg(2);
                super.rawset(arg1, arg.istable() ? new GlobalCLuaTable(arg) : arg);
                next = luaValue.next(varargs.arg1());
            } else {
                return;
            }
        }
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public final LuaValue setmetatable(LuaValue luaValue) {
        return error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public final void set(int i, LuaValue luaValue) {
        error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public final void rawset(int i, LuaValue luaValue) {
        error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public final void rawset(LuaValue luaValue, LuaValue luaValue2) {
        error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable
    public final LuaValue remove(int i) {
        return error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue tostring() {
        return LuaString.valueOf("table: globalClassAliases");
    }
}
