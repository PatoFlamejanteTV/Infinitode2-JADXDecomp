package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaUserdata.class */
public class LuaUserdata extends LuaValue implements KryoSerializable {
    public Object m_instance;
    public LuaValue m_metatable;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.m_instance);
        LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, this.m_metatable);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.m_instance = kryo.readClassAndObject(input);
        if (this.m_instance == null) {
            throw new IllegalArgumentException("m_instance is nil after deserialization");
        }
        this.m_metatable = (LuaValue) kryo.readClassAndObject(input);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LuaUserdata() {
    }

    public LuaUserdata(Object obj) {
        if (obj == null) {
            throw new IllegalStateException("obj is null");
        }
        this.m_instance = obj;
    }

    public LuaUserdata(Object obj, LuaValue luaValue) {
        if (obj == null) {
            throw new IllegalStateException("obj is null");
        }
        this.m_instance = obj;
        this.m_metatable = luaValue;
    }

    @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public String tojstring() {
        Class<?> cls = this.m_instance.getClass();
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            if (componentType == Float.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((float[]) this.m_instance);
            }
            if (componentType == Integer.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((int[]) this.m_instance);
            }
            if (componentType == Boolean.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((boolean[]) this.m_instance);
            }
            if (componentType == Double.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((double[]) this.m_instance);
            }
            if (componentType == Long.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((long[]) this.m_instance);
            }
            if (componentType == Character.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((char[]) this.m_instance);
            }
            if (componentType == Byte.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((byte[]) this.m_instance);
            }
            if (componentType == Short.TYPE) {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((short[]) this.m_instance);
            }
            try {
                return this.m_instance + SequenceUtils.SPACE + Arrays.toString((Object[]) this.m_instance);
            } catch (Exception unused) {
                return this.m_instance.toString();
            }
        }
        return this.m_instance.toString();
    }

    @Override // com.prineside.luaj.LuaValue
    public final int type() {
        return 7;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String typename() {
        return "userdata";
    }

    public final int hashCode() {
        return this.m_instance.hashCode();
    }

    public final Object userdata() {
        return this.m_instance;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isuserdata() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isuserdata(Class cls) {
        return cls.isAssignableFrom(this.m_instance.getClass());
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object touserdata() {
        return this.m_instance;
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object touserdata(Class cls) {
        if (cls.isAssignableFrom(this.m_instance.getClass())) {
            return this.m_instance;
        }
        return null;
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object optuserdata(Object obj) {
        return this.m_instance;
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object optuserdata(Class cls, Object obj) {
        if (!cls.isAssignableFrom(this.m_instance.getClass())) {
            b(cls.getName());
        }
        return this.m_instance;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue getmetatable() {
        return this.m_metatable;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue setmetatable(LuaValue luaValue) {
        this.m_metatable = luaValue;
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object checkuserdata() {
        return this.m_instance;
    }

    @Override // com.prineside.luaj.LuaValue
    public final Object checkuserdata(Class cls) {
        Class<?> cls2 = this.m_instance.getClass();
        if (cls2 == cls || cls.isAssignableFrom(cls2)) {
            return this.m_instance;
        }
        return b(cls.getName());
    }

    @Override // com.prineside.luaj.LuaValue
    public LuaValue get(LuaValue luaValue) {
        return this.m_metatable != null ? c(this, luaValue) : NIL;
    }

    @Override // com.prineside.luaj.LuaValue
    public void set(LuaValue luaValue, LuaValue luaValue2) {
        if (this.m_metatable == null || !a(this, luaValue, luaValue2)) {
            error("cannot set '" + luaValue + "' for userdata " + this);
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LuaUserdata)) {
            return false;
        }
        return this.m_instance.equals(((LuaUserdata) obj).m_instance);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue eq(LuaValue luaValue) {
        return eq_b(luaValue) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean eq_b(LuaValue luaValue) {
        LuaValue luaValue2;
        if (luaValue.raweq(this)) {
            return true;
        }
        return this.m_metatable != null && luaValue.isuserdata() && (luaValue2 = luaValue.getmetatable()) != null && LuaValue.eqmtcall(this, this.m_metatable, luaValue, luaValue2);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean raweq(LuaValue luaValue) {
        return luaValue.raweq(this);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean raweq(LuaUserdata luaUserdata) {
        if (this != luaUserdata) {
            return this.m_metatable == luaUserdata.m_metatable && this.m_instance.equals(luaUserdata.m_instance);
        }
        return true;
    }

    public final boolean eqmt(LuaValue luaValue) {
        if (this.m_metatable == null || !luaValue.isuserdata()) {
            return false;
        }
        return LuaValue.eqmtcall(this, this.m_metatable, luaValue, luaValue.getmetatable());
    }
}
