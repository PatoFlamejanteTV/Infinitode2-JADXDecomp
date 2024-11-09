package com.prineside.luaj.lib;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.Globals;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.debug.CallFrame;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/DebugLib.class */
public final class DebugLib extends TwoArgFunction implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    Globals f1576a;

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.f1576a, Globals.class);
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1576a = (Globals) kryo.readObjectOrNull(input, Globals.class);
    }

    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        this.f1576a = luaValue2.checkglobals();
        this.f1576a.setDebugLib(this);
        LuaTable luaTable = new LuaTable();
        luaTable.set("traceback", new traceback(this, (byte) 0));
        luaValue2.set("debug", luaTable);
        if (!luaValue2.get("package").isnil()) {
            luaValue2.get("package").get("loaded").set("debug", luaTable);
        }
        return luaTable;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/DebugLib$traceback.class */
    public static final class traceback extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private DebugLib f1577a;

        /* synthetic */ traceback(DebugLib debugLib, byte b2) {
            this(debugLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1577a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1577a = (DebugLib) kryo.readObject(input, DebugLib.class);
        }

        private traceback() {
        }

        private traceback(DebugLib debugLib) {
            this.f1577a = debugLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            String optjstring = varargs.optjstring(1, null);
            String traceback = this.f1577a.f1576a.getCallstack().traceback(varargs.optint(2, 1));
            return valueOf(optjstring != null ? optjstring + SequenceUtils.EOL + traceback : traceback);
        }
    }

    public final String traceback(int i) {
        return this.f1576a.getCallstack().traceback(i);
    }

    public final CallFrame getCallFrame(int i) {
        return this.f1576a.getCallstack().getCallFrame(i);
    }
}
