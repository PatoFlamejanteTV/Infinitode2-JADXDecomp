package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaBoolean.class */
public final class LuaBoolean extends LuaValue {

    /* renamed from: a, reason: collision with root package name */
    static final LuaBoolean f1476a = new LuaBoolean(true);

    /* renamed from: b, reason: collision with root package name */
    static final LuaBoolean f1477b = new LuaBoolean(false);
    public final boolean v;

    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaBoolean$Serializer.class */
    public static class Serializer extends SingletonSerializer<LuaBoolean> {
        public Serializer() {
            setImmutable(true);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends LuaBoolean>) cls);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, LuaBoolean luaBoolean) {
            output.writeBoolean(luaBoolean.v);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public LuaBoolean read2(Kryo kryo, Input input, Class<? extends LuaBoolean> cls) {
            return input.readBoolean() ? LuaBoolean.f1476a : LuaBoolean.f1477b;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public LuaBoolean read() {
            throw new IllegalStateException("Unused");
        }
    }

    private LuaBoolean(boolean z) {
        this.v = z;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int type() {
        return 1;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String typename() {
        return "boolean";
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isboolean() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue not() {
        return this.v ? FALSE : LuaValue.TRUE;
    }

    public final boolean booleanValue() {
        return this.v;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean toboolean() {
        return this.v;
    }

    @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public final String tojstring() {
        return this.v ? "true" : "false";
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean optboolean(boolean z) {
        return this.v;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean checkboolean() {
        return this.v;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue getmetatable() {
        return null;
    }
}
