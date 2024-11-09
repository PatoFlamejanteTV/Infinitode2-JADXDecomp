package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/luaj/Prototype.class */
public final class Prototype implements KryoSerializable {
    public LuaValue[] k;
    public int[] code;
    public Prototype[] p;
    public int[] lineinfo;
    public LocVars[] locvars;
    public Upvaldesc[] upvalues;
    public LuaString source;
    public int linedefined;
    public int lastlinedefined;
    public int numparams;
    public int is_vararg;
    public int maxstacksize;

    /* renamed from: a, reason: collision with root package name */
    private static final Upvaldesc[] f1504a = new Upvaldesc[0];

    /* renamed from: b, reason: collision with root package name */
    private static final Prototype[] f1505b = new Prototype[0];

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, this.k);
        kryo.writeObjectOrNull(output, this.code, int[].class);
        kryo.writeClassAndObject(output, this.p);
        kryo.writeObjectOrNull(output, this.lineinfo, int[].class);
        kryo.writeClassAndObject(output, this.locvars);
        kryo.writeClassAndObject(output, this.upvalues);
        kryo.writeObjectOrNull(output, this.source, LuaString.class);
        output.writeInt(this.linedefined);
        output.writeInt(this.lastlinedefined);
        output.writeInt(this.numparams);
        output.writeInt(this.is_vararg);
        output.writeInt(this.maxstacksize);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.k = (LuaValue[]) kryo.readClassAndObject(input);
        this.code = (int[]) kryo.readObjectOrNull(input, int[].class);
        this.p = (Prototype[]) kryo.readClassAndObject(input);
        this.lineinfo = (int[]) kryo.readObjectOrNull(input, int[].class);
        this.locvars = (LocVars[]) kryo.readClassAndObject(input);
        this.upvalues = (Upvaldesc[]) kryo.readClassAndObject(input);
        this.source = (LuaString) kryo.readObjectOrNull(input, LuaString.class);
        this.linedefined = input.readInt();
        this.lastlinedefined = input.readInt();
        this.numparams = input.readInt();
        this.is_vararg = input.readInt();
        this.maxstacksize = input.readInt();
    }

    public final FPrototype toFixedProto() {
        FPrototype[] fPrototypeArr = new FPrototype[this.p.length];
        for (int i = 0; i < this.p.length; i++) {
            fPrototypeArr[i] = this.p[i].toFixedProto();
        }
        short[] sArr = new short[this.lineinfo.length];
        for (int i2 = 0; i2 < this.lineinfo.length; i2++) {
            sArr[i2] = (short) this.lineinfo[i2];
        }
        return new FPrototype(this.k, this.code, fPrototypeArr, sArr, this.locvars, this.upvalues, this.source, (short) this.linedefined, (short) this.lastlinedefined, (byte) this.numparams, this.is_vararg != 0, (byte) this.maxstacksize);
    }

    public Prototype() {
        this.p = f1505b;
        this.upvalues = f1504a;
    }

    public Prototype(int i) {
        this.p = f1505b;
        this.upvalues = new Upvaldesc[i];
    }

    public final String toString() {
        return this.source + ":" + this.linedefined + "-" + this.lastlinedefined;
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
