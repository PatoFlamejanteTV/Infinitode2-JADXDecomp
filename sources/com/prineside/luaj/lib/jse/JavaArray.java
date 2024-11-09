package com.prineside.luaj.lib.jse;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaBoolean;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaValue;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaArray.class */
public final class JavaArray extends JavaInstance {
    public static final LuaValue LENGTH = valueOf("length");

    /* renamed from: b, reason: collision with root package name */
    private final byte f1615b;

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaArray$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<JavaArray> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, JavaArray javaArray) {
            kryo.writeClassAndObject(output, javaArray.m_instance);
            kryo.writeClassAndObject(output, javaArray.m_metatable);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public JavaArray read2(Kryo kryo, Input input, Class<? extends JavaArray> cls) {
            Object readClassAndObject = kryo.readClassAndObject(input);
            LuaValue luaValue = (LuaValue) kryo.readClassAndObject(input);
            JavaArray javaArray = new JavaArray(readClassAndObject);
            if (luaValue != null) {
                javaArray.setmetatable(luaValue);
            }
            return javaArray;
        }
    }

    public JavaArray(Object obj) {
        super(obj);
        Class<?> cls = obj.getClass();
        if (!cls.isArray()) {
            throw new IllegalArgumentException("Class is not an array: " + cls);
        }
        Class<?> componentType = cls.getComponentType();
        if (componentType == Float.TYPE) {
            this.f1615b = (byte) 0;
            return;
        }
        if (componentType == Integer.TYPE) {
            this.f1615b = (byte) 1;
            return;
        }
        if (componentType == Boolean.TYPE) {
            this.f1615b = (byte) 2;
            return;
        }
        if (componentType == Double.TYPE) {
            this.f1615b = (byte) 3;
            return;
        }
        if (componentType == Long.TYPE) {
            this.f1615b = (byte) 4;
            return;
        }
        if (componentType == Byte.TYPE) {
            this.f1615b = (byte) 5;
            return;
        }
        if (componentType == Short.TYPE) {
            this.f1615b = (byte) 6;
        } else if (componentType == Character.TYPE) {
            this.f1615b = (byte) 7;
        } else {
            this.f1615b = (byte) 8;
        }
    }

    @Override // com.prineside.luaj.lib.jse.JavaInstance, com.prineside.luaj.LuaValue
    public final LuaValue len() {
        switch (this.f1615b) {
            case 0:
                return CoerceJavaToLua.coerce(Integer.valueOf(((float[]) this.m_instance).length));
            case 1:
                return CoerceJavaToLua.coerce(Integer.valueOf(((int[]) this.m_instance).length));
            case 2:
                return CoerceJavaToLua.coerce(Integer.valueOf(((boolean[]) this.m_instance).length));
            case 3:
                return CoerceJavaToLua.coerce(Integer.valueOf(((double[]) this.m_instance).length));
            case 4:
                return CoerceJavaToLua.coerce(Integer.valueOf(((long[]) this.m_instance).length));
            case 5:
                return CoerceJavaToLua.coerce(Integer.valueOf(((byte[]) this.m_instance).length));
            case 6:
                return CoerceJavaToLua.coerce(Integer.valueOf(((short[]) this.m_instance).length));
            case 7:
                return CoerceJavaToLua.coerce(Integer.valueOf(((char[]) this.m_instance).length));
            default:
                return CoerceJavaToLua.coerce(Integer.valueOf(((Object[]) this.m_instance).length));
        }
    }

    @Override // com.prineside.luaj.lib.jse.JavaInstance, com.prineside.luaj.LuaUserdata, com.prineside.luaj.LuaValue
    public final void set(LuaValue luaValue, LuaValue luaValue2) {
        if (luaValue.isint()) {
            int checkint = luaValue.checkint() - 1;
            switch (this.f1615b) {
                case 0:
                    float[] fArr = (float[]) this.m_instance;
                    a(checkint, fArr.length);
                    fArr[checkint] = luaValue2.tofloat();
                    return;
                case 1:
                    int[] iArr = (int[]) this.m_instance;
                    a(checkint, iArr.length);
                    iArr[checkint] = luaValue2.toint();
                    return;
                case 2:
                    boolean[] zArr = (boolean[]) this.m_instance;
                    a(checkint, zArr.length);
                    zArr[checkint] = luaValue2.toboolean();
                    return;
                case 3:
                    double[] dArr = (double[]) this.m_instance;
                    a(checkint, dArr.length);
                    dArr[checkint] = luaValue2.todouble();
                    return;
                case 4:
                    long[] jArr = (long[]) this.m_instance;
                    a(checkint, jArr.length);
                    jArr[checkint] = luaValue2.tolong();
                    return;
                case 5:
                    byte[] bArr = (byte[]) this.m_instance;
                    a(checkint, bArr.length);
                    bArr[checkint] = luaValue2.tobyte();
                    return;
                case 6:
                    short[] sArr = (short[]) this.m_instance;
                    a(checkint, sArr.length);
                    sArr[checkint] = luaValue2.toshort();
                    return;
                case 7:
                    char[] cArr = (char[]) this.m_instance;
                    a(checkint, cArr.length);
                    cArr[checkint] = luaValue2.tochar();
                    return;
                default:
                    Object[] objArr = (Object[]) this.m_instance;
                    a(checkint, objArr.length);
                    switch (luaValue2.type()) {
                        case 5:
                        case 6:
                            objArr[checkint] = luaValue2;
                            return;
                        default:
                            objArr[checkint] = luaValue2.touserdata();
                            return;
                    }
            }
        }
        super.set(luaValue, luaValue2);
    }

    @Override // com.prineside.luaj.lib.jse.JavaInstance, com.prineside.luaj.LuaUserdata, com.prineside.luaj.LuaValue
    public final LuaValue get(LuaValue luaValue) {
        if (luaValue.isnumber()) {
            int i = luaValue.toint() - 1;
            switch (this.f1615b) {
                case 0:
                    a(i, ((float[]) this.m_instance).length);
                    return LuaNumber.valueOf(r0[i]);
                case 1:
                    int[] iArr = (int[]) this.m_instance;
                    a(i, iArr.length);
                    return LuaNumber.valueOf(iArr[i]);
                case 2:
                    boolean[] zArr = (boolean[]) this.m_instance;
                    a(i, zArr.length);
                    return LuaBoolean.valueOf(zArr[i]);
                case 3:
                    double[] dArr = (double[]) this.m_instance;
                    a(i, dArr.length);
                    return LuaNumber.valueOf(dArr[i]);
                case 4:
                    a(i, ((long[]) this.m_instance).length);
                    return LuaNumber.valueOf(r0[i]);
                case 5:
                    byte[] bArr = (byte[]) this.m_instance;
                    a(i, bArr.length);
                    return LuaNumber.valueOf((int) bArr[i]);
                case 6:
                    short[] sArr = (short[]) this.m_instance;
                    a(i, sArr.length);
                    return LuaNumber.valueOf((int) sArr[i]);
                case 7:
                    char[] cArr = (char[]) this.m_instance;
                    a(i, cArr.length);
                    return LuaNumber.valueOf((int) cArr[i]);
                default:
                    Object[] objArr = (Object[]) this.m_instance;
                    a(i, objArr.length);
                    return CoerceJavaToLua.coerce(objArr[i]);
            }
        }
        if (luaValue.equals(LENGTH)) {
            return len();
        }
        return super.get(luaValue);
    }

    private static void a(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new LuaError("Accessing index " + (i + 1) + " in array of length " + i2 + " (valid range is 1 <> " + i2 + ")");
        }
    }
}
