package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 1, serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/luaj/FPrototype.class */
public final class FPrototype {
    public final LuaValue[] k;
    public final int[] code;
    public final FPrototype[] p;
    public final short[] lineinfo;
    public final LocVars[] locvars;
    public final Upvaldesc[] upvalues;
    public final LuaString source;
    public final short linedefined;
    public final short lastlinedefined;
    public final byte numparams;
    public final boolean is_vararg;
    public final byte maxstacksize;

    static {
        Upvaldesc[] upvaldescArr = new Upvaldesc[0];
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/FPrototype$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<FPrototype> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, FPrototype fPrototype) {
            LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, fPrototype.k);
            kryo.writeObjectOrNull(output, fPrototype.code, int[].class);
            kryo.writeClassAndObject(output, fPrototype.p);
            kryo.writeObjectOrNull(output, fPrototype.lineinfo, short[].class);
            kryo.writeClassAndObject(output, fPrototype.locvars);
            kryo.writeClassAndObject(output, fPrototype.upvalues);
            kryo.writeObjectOrNull(output, fPrototype.source, LuaString.class);
            output.writeShort(fPrototype.linedefined);
            output.writeShort(fPrototype.lastlinedefined);
            output.writeByte(fPrototype.numparams);
            output.writeBoolean(fPrototype.is_vararg);
            output.writeByte(fPrototype.maxstacksize);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public FPrototype read2(Kryo kryo, Input input, Class<? extends FPrototype> cls) {
            return new FPrototype((LuaValue[]) kryo.readClassAndObject(input), (int[]) kryo.readObjectOrNull(input, int[].class), (FPrototype[]) kryo.readClassAndObject(input), (short[]) kryo.readObjectOrNull(input, short[].class), (LocVars[]) kryo.readClassAndObject(input), (Upvaldesc[]) kryo.readClassAndObject(input), (LuaString) kryo.readObjectOrNull(input, LuaString.class), input.readShort(), input.readShort(), input.readByte(), input.readBoolean(), input.readByte());
        }
    }

    public FPrototype(LuaValue[] luaValueArr, int[] iArr, FPrototype[] fPrototypeArr, short[] sArr, LocVars[] locVarsArr, Upvaldesc[] upvaldescArr, LuaString luaString, short s, short s2, byte b2, boolean z, byte b3) {
        this.k = luaValueArr;
        this.code = iArr;
        this.p = fPrototypeArr;
        this.lineinfo = sArr;
        this.locvars = locVarsArr;
        this.upvalues = upvaldescArr;
        this.source = luaString;
        this.linedefined = s;
        this.lastlinedefined = s2;
        this.numparams = b2;
        this.is_vararg = z;
        this.maxstacksize = b3;
    }

    public final String toString() {
        return this.source + ":" + ((int) this.linedefined) + "-" + ((int) this.lastlinedefined);
    }

    public final LuaString getlocalname(int i, int i2) {
        for (int i3 = 0; i3 < this.locvars.length && this.locvars[i3].startpc <= i2; i3++) {
            if (i2 < this.locvars[i3].endpc) {
                i--;
                if (i == 0) {
                    return this.locvars[i3].varname;
                }
            }
        }
        return null;
    }

    public final String shortsource() {
        if (this.source == null) {
            return "no source";
        }
        String str = this.source.tojstring();
        String str2 = str;
        if (str.startsWith("@") || str2.startsWith("=")) {
            str2 = str2.substring(1);
        } else if (str2.startsWith("\u001b")) {
            str2 = LoadState.SOURCE_BINARY_STRING;
        }
        return str2;
    }
}
