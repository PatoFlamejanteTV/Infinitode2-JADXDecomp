package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/luaj/Upvaldesc.class */
public final class Upvaldesc implements KryoSerializable {
    public LuaString name;
    public boolean instack;
    public short idx;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.name, LuaString.class);
        output.writeBoolean(this.instack);
        output.writeShort(this.idx);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.name = (LuaString) kryo.readObjectOrNull(input, LuaString.class);
        this.instack = input.readBoolean();
        this.idx = input.readShort();
    }

    private Upvaldesc() {
    }

    public Upvaldesc(LuaString luaString, boolean z, int i) {
        this.name = luaString;
        this.instack = z;
        this.idx = (short) i;
    }

    public final String toString() {
        return ((int) this.idx) + (this.instack ? " instack " : " closed ") + String.valueOf(this.name);
    }
}
