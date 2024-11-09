package com.prineside.tdi2.managers.script;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/ClassTreeLuaTable.class */
public class ClassTreeLuaTable extends LuaTable {
    @Override // com.prineside.luaj.LuaTable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeString(get(LuaString.valueOf("_classTreePath")).tojstring());
    }

    @Override // com.prineside.luaj.LuaTable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        Game.i.scriptManager.restoreClassTreePath(input.readString(), this);
    }

    protected ClassTreeLuaTable() {
    }

    public ClassTreeLuaTable(LuaValue luaValue) {
        loadFrom(luaValue);
    }

    public void loadFrom(LuaValue luaValue) {
        presize(luaValue.length(), 0);
        Varargs next = luaValue.next(LuaValue.NIL);
        while (true) {
            Varargs varargs = next;
            if (!varargs.arg1().isnil()) {
                LuaValue arg1 = varargs.arg1();
                LuaValue arg = varargs.arg(2);
                super.rawset(arg1, arg.istable() ? new ClassTreeLuaTable(arg) : arg);
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

    @Override // com.prineside.luaj.LuaValue
    public LuaValue tostring() {
        return LuaString.valueOf("table: classTree@" + get("_classTreePath").tojstring());
    }
}
