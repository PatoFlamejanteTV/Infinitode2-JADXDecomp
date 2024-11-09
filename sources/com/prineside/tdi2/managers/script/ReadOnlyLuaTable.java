package com.prineside.tdi2.managers.script;

import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/ReadOnlyLuaTable.class */
public class ReadOnlyLuaTable extends LuaTable {
    protected ReadOnlyLuaTable() {
    }

    public ReadOnlyLuaTable(LuaValue luaValue) {
        presize(luaValue.length(), 0);
        Varargs next = luaValue.next(LuaValue.NIL);
        while (true) {
            Varargs varargs = next;
            if (!varargs.arg1().isnil()) {
                LuaValue arg1 = varargs.arg1();
                LuaValue arg = varargs.arg(2);
                super.rawset(arg1, arg.istable() ? new ReadOnlyLuaTable(arg) : arg);
                next = luaValue.next(varargs.arg1());
            } else {
                return;
            }
        }
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public LuaValue setmetatable(LuaValue luaValue) {
        return error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public void set(int i, LuaValue luaValue) {
        error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public void rawset(int i, LuaValue luaValue) {
        error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable, com.prineside.luaj.LuaValue
    public void rawset(LuaValue luaValue, LuaValue luaValue2) {
        error("table is read-only");
    }

    @Override // com.prineside.luaj.LuaTable
    public LuaValue remove(int i) {
        return error("table is read-only");
    }
}
