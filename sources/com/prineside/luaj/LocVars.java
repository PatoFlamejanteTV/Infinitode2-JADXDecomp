package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/luaj/LocVars.class */
public final class LocVars implements KryoSerializable {
    public LuaString varname;
    public int startpc;
    public int endpc;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.varname, LuaString.class);
        output.writeInt(this.startpc);
        output.writeInt(this.endpc);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.varname = (LuaString) kryo.readObjectOrNull(input, LuaString.class);
        this.startpc = input.readInt();
        this.endpc = input.readInt();
    }

    private LocVars() {
    }

    public LocVars(LuaString luaString, int i, int i2) {
        this.varname = luaString;
        this.startpc = i;
        this.endpc = i2;
    }

    public final String tojstring() {
        return this.varname + SequenceUtils.SPACE + this.startpc + "-" + this.endpc;
    }
}
