package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/luaj/UpValue.class */
public final class UpValue implements KryoSerializable {

    /* renamed from: b, reason: collision with root package name */
    private LuaValue[] f1508b;

    /* renamed from: a, reason: collision with root package name */
    int f1509a;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, this.f1508b);
        output.writeInt(this.f1509a);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f1508b = (LuaValue[]) kryo.readClassAndObject(input);
        this.f1509a = input.readInt();
    }

    private UpValue() {
    }

    public UpValue(LuaValue[] luaValueArr, int i) {
        this.f1508b = luaValueArr;
        this.f1509a = i;
    }

    public final String toString() {
        return this.f1509a + "/" + (this.f1508b != null ? this.f1508b.length + SequenceUtils.SPACE + this.f1508b[this.f1509a] : "noArr");
    }

    public final String tojstring() {
        return this.f1508b[this.f1509a].tojstring();
    }

    public final LuaValue getValue() {
        return this.f1508b[this.f1509a];
    }

    public final void setValue(LuaValue luaValue) {
        this.f1508b[this.f1509a] = luaValue;
    }

    public final void close() {
        LuaValue[] luaValueArr = this.f1508b;
        this.f1508b = new LuaValue[]{luaValueArr[this.f1509a]};
        luaValueArr[this.f1509a] = null;
        this.f1509a = 0;
    }
}
