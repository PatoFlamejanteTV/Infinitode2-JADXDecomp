package com.prineside.luaj.lib.jse;

import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import com.prineside.tdi2.utils.logging.TLog;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceLuaToJava.class */
public class CoerceLuaToJava {

    /* renamed from: a, reason: collision with root package name */
    static int f1607a;

    /* renamed from: b, reason: collision with root package name */
    static int f1608b;
    static int c;
    static int d;
    private static Map<Class<?>, Coercion> e;
    private static final ObjectMap<Class<?>, ObjectIntMap<Class<?>>> f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceLuaToJava$Coercion.class */
    public interface Coercion {
        int score(LuaValue luaValue);

        Object coerce(LuaValue luaValue);
    }

    static {
        TLog.forClass(CoerceLuaToJava.class);
        f1607a = 256;
        f1608b = 4096;
        c = 1048576;
        d = 16;
        e = new HashMap();
        f = new ObjectMap<>();
        BoolCoercion boolCoercion = new BoolCoercion();
        NumericCoercion numericCoercion = new NumericCoercion(0);
        NumericCoercion numericCoercion2 = new NumericCoercion(1);
        NumericCoercion numericCoercion3 = new NumericCoercion(2);
        NumericCoercion numericCoercion4 = new NumericCoercion(3);
        NumericCoercion numericCoercion5 = new NumericCoercion(4);
        NumericCoercion numericCoercion6 = new NumericCoercion(5);
        NumericCoercion numericCoercion7 = new NumericCoercion(6);
        StringCoercion stringCoercion = new StringCoercion(0);
        StringCoercion stringCoercion2 = new StringCoercion(1);
        e.put(Boolean.TYPE, boolCoercion);
        e.put(Boolean.class, boolCoercion);
        e.put(Byte.TYPE, numericCoercion);
        e.put(Byte.class, numericCoercion);
        e.put(Character.TYPE, numericCoercion2);
        e.put(Character.class, numericCoercion2);
        e.put(Short.TYPE, numericCoercion3);
        e.put(Short.class, numericCoercion3);
        e.put(Integer.TYPE, numericCoercion4);
        e.put(Integer.class, numericCoercion4);
        e.put(Long.TYPE, numericCoercion5);
        e.put(Long.class, numericCoercion5);
        e.put(Float.TYPE, numericCoercion6);
        e.put(Float.class, numericCoercion6);
        e.put(Double.TYPE, numericCoercion7);
        e.put(Double.class, numericCoercion7);
        e.put(String.class, stringCoercion);
        e.put(byte[].class, stringCoercion2);
    }

    public static Object coerce(LuaValue luaValue, Class cls) {
        return a(cls).coerce(luaValue);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceLuaToJava$BoolCoercion.class */
    static final class BoolCoercion implements Coercion {
        BoolCoercion() {
        }

        public final String toString() {
            return "BoolCoercion()";
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final int score(LuaValue luaValue) {
            if (luaValue.type() == 1) {
                return 0;
            }
            return 1;
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final Object coerce(LuaValue luaValue) {
            return luaValue.toboolean() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceLuaToJava$NumericCoercion.class */
    static final class NumericCoercion implements Coercion {

        /* renamed from: a, reason: collision with root package name */
        private static String[] f1611a = {"byte", "char", "short", "int", "long", "float", "double"};

        /* renamed from: b, reason: collision with root package name */
        private int f1612b;

        public final String toString() {
            return "NumericCoercion(" + f1611a[this.f1612b] + ")";
        }

        NumericCoercion(int i) {
            this.f1612b = i;
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final int score(LuaValue luaValue) {
            int i = 0;
            if (luaValue.type() == 4) {
                LuaValue luaValue2 = luaValue.tonumber();
                luaValue = luaValue2;
                if (luaValue2.isnil()) {
                    return CoerceLuaToJava.c;
                }
                i = 64;
            }
            if (luaValue.isint()) {
                switch (this.f1612b) {
                    case 0:
                        int i2 = luaValue.toint();
                        return i + (i2 == ((byte) i2) ? 0 : CoerceLuaToJava.f1608b);
                    case 1:
                        int i3 = luaValue.toint();
                        return i + (i3 == ((byte) i3) ? 1 : i3 == ((char) i3) ? 0 : CoerceLuaToJava.f1608b);
                    case 2:
                        int i4 = luaValue.toint();
                        return i + (i4 == ((byte) i4) ? 1 : i4 == ((short) i4) ? 0 : CoerceLuaToJava.f1608b);
                    case 3:
                        int i5 = luaValue.toint();
                        return i + (i5 == ((byte) i5) ? 2 : (i5 == ((char) i5) || i5 == ((short) i5)) ? 1 : 0);
                    case 4:
                        return i + 16;
                    case 5:
                        return i + 16;
                    case 6:
                        return i + 32;
                    default:
                        return CoerceLuaToJava.f1608b;
                }
            }
            if (luaValue.isnumber()) {
                switch (this.f1612b) {
                    case 4:
                        int i6 = i;
                        return i6 + (i6 == ((double) ((long) luaValue.todouble())) ? 0 : CoerceLuaToJava.f1608b);
                    case 5:
                        int i7 = i;
                        return i7 + (i7 == ((double) ((float) luaValue.todouble())) ? 0 : CoerceLuaToJava.f1608b);
                    case 6:
                        double d = luaValue.todouble();
                        int i8 = i;
                        return i8 + ((i8 == ((double) ((long) d)) || d == ((double) ((float) i8))) ? 16 : 0);
                    default:
                        return CoerceLuaToJava.f1608b;
                }
            }
            return CoerceLuaToJava.c;
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final Object coerce(LuaValue luaValue) {
            switch (this.f1612b) {
                case 0:
                    return Byte.valueOf((byte) luaValue.toint());
                case 1:
                    return Character.valueOf((char) luaValue.toint());
                case 2:
                    return Short.valueOf((short) luaValue.toint());
                case 3:
                    return Integer.valueOf(luaValue.toint());
                case 4:
                    return Long.valueOf((long) luaValue.todouble());
                case 5:
                    return Float.valueOf((float) luaValue.todouble());
                case 6:
                    return Double.valueOf(luaValue.todouble());
                default:
                    return null;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceLuaToJava$StringCoercion.class */
    static final class StringCoercion implements Coercion {
        public static final int TARGET_TYPE_STRING = 0;
        public static final int TARGET_TYPE_BYTES = 1;

        /* renamed from: a, reason: collision with root package name */
        private int f1614a;

        public StringCoercion(int i) {
            this.f1614a = i;
        }

        public final String toString() {
            return "StringCoercion(" + (this.f1614a == 0 ? "String" : "byte[]") + ")";
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final int score(LuaValue luaValue) {
            switch (luaValue.type()) {
                case 0:
                    return CoerceLuaToJava.f1607a;
                case 4:
                    if (luaValue.checkstring().isValidUtf8()) {
                        return this.f1614a == 0 ? 0 : 1;
                    }
                    if (this.f1614a == 1) {
                        return 0;
                    }
                    return CoerceLuaToJava.f1608b;
                default:
                    return this.f1614a == 0 ? CoerceLuaToJava.f1608b : CoerceLuaToJava.c;
            }
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final Object coerce(LuaValue luaValue) {
            if (luaValue.isnil()) {
                return null;
            }
            if (this.f1614a == 0) {
                return luaValue.tojstring();
            }
            LuaString checkstring = luaValue.checkstring();
            byte[] bArr = new byte[checkstring.m_length];
            checkstring.copyInto(0, bArr, 0, bArr.length);
            return bArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceLuaToJava$ArrayCoercion.class */
    public static final class ArrayCoercion implements Coercion {

        /* renamed from: a, reason: collision with root package name */
        final Class f1609a;

        /* renamed from: b, reason: collision with root package name */
        final Coercion f1610b;

        public ArrayCoercion(Class cls) {
            this.f1609a = cls;
            this.f1610b = CoerceLuaToJava.a(cls);
        }

        public final String toString() {
            return "ArrayCoercion(" + this.f1609a.getName() + ")";
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final int score(LuaValue luaValue) {
            switch (luaValue.type()) {
                case 0:
                    return CoerceLuaToJava.f1607a;
                case 5:
                    if (luaValue.length() == 0) {
                        return 0;
                    }
                    return this.f1610b.score(luaValue.get(1));
                case 7:
                    return CoerceLuaToJava.a(this.f1609a, luaValue.touserdata().getClass().getComponentType());
                default:
                    return CoerceLuaToJava.c;
            }
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final Object coerce(LuaValue luaValue) {
            switch (luaValue.type()) {
                case 5:
                    int length = luaValue.length();
                    Object newInstance = Array.newInstance((Class<?>) this.f1609a, length);
                    for (int i = 0; i < length; i++) {
                        Array.set(newInstance, i, this.f1610b.coerce(luaValue.get(i + 1)));
                    }
                    return newInstance;
                case 7:
                    return luaValue.touserdata();
                default:
                    return null;
            }
        }
    }

    static synchronized int a(Class<?> cls, Class<?> cls2) {
        int i;
        if (cls2 == null) {
            return c;
        }
        if (cls == cls2) {
            return 0;
        }
        ObjectIntMap<Class<?>> objectIntMap = f.get(cls);
        ObjectIntMap<Class<?>> objectIntMap2 = objectIntMap;
        if (objectIntMap != null && (i = objectIntMap2.get(cls2, -1)) != -1) {
            return i;
        }
        int min = Math.min(c, a(cls, cls2.getSuperclass()) + 1);
        for (Class<?> cls3 : cls2.getInterfaces()) {
            min = Math.min(min, a(cls, cls3) + 1);
        }
        if (objectIntMap2 == null) {
            objectIntMap2 = new ObjectIntMap<>();
            f.put(cls, objectIntMap2);
        }
        objectIntMap2.put(cls2, min);
        return min;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceLuaToJava$ObjectCoercion.class */
    public static final class ObjectCoercion implements Coercion {

        /* renamed from: a, reason: collision with root package name */
        private Class f1613a;

        ObjectCoercion(Class cls) {
            this.f1613a = cls;
        }

        public final String toString() {
            return "ObjectCoercion(" + this.f1613a.getName() + ")";
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final int score(LuaValue luaValue) {
            switch (luaValue.type()) {
                case 0:
                    return CoerceLuaToJava.f1607a;
                case 1:
                    return CoerceLuaToJava.a(this.f1613a, Boolean.class);
                case 2:
                case 5:
                case 6:
                default:
                    return CoerceLuaToJava.a(this.f1613a, luaValue.getClass());
                case 3:
                    return CoerceLuaToJava.a(this.f1613a, luaValue.isint() ? Integer.class : Double.class);
                case 4:
                    return CoerceLuaToJava.a(this.f1613a, String.class);
                case 7:
                    return CoerceLuaToJava.a(this.f1613a, luaValue.touserdata().getClass());
            }
        }

        @Override // com.prineside.luaj.lib.jse.CoerceLuaToJava.Coercion
        public final Object coerce(LuaValue luaValue) {
            switch (luaValue.type()) {
                case 0:
                    return null;
                case 1:
                    return luaValue.toboolean() ? Boolean.TRUE : Boolean.FALSE;
                case 2:
                case 5:
                case 6:
                default:
                    return luaValue;
                case 3:
                    if (luaValue.isint()) {
                        return Integer.valueOf(luaValue.toint());
                    }
                    return Double.valueOf(luaValue.todouble());
                case 4:
                    return luaValue.tojstring();
                case 7:
                    return luaValue.optuserdata(this.f1613a, (Object) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Coercion a(Class<?> cls) {
        Coercion objectCoercion;
        Coercion coercion = e.get(cls);
        if (coercion != null) {
            return coercion;
        }
        synchronized (e) {
            if (cls.isArray()) {
                objectCoercion = new ArrayCoercion(cls.getComponentType());
            } else {
                objectCoercion = new ObjectCoercion(cls);
            }
            e.put(cls, objectCoercion);
        }
        return objectCoercion;
    }
}
