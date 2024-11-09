package com.prineside.luaj.lib.jse;

import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua.class */
public class CoerceJavaToLua {

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<Class<?>, Coercion> f1605a = new ConcurrentHashMap<>();
    public static final Coercion instanceCoercion;
    public static final Coercion arrayCoercion;

    /* renamed from: b, reason: collision with root package name */
    private static Coercion f1606b;

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$Coercion.class */
    public interface Coercion {
        LuaValue coerce(Object obj);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$BoolCoercion.class */
    private static final class BoolCoercion implements Coercion {
        private BoolCoercion() {
        }

        /* synthetic */ BoolCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return ((Boolean) obj).booleanValue() ? LuaValue.TRUE : LuaValue.FALSE;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$IntCoercion.class */
    private static final class IntCoercion implements Coercion {
        private IntCoercion() {
        }

        /* synthetic */ IntCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return LuaNumber.valueOf(((Number) obj).intValue());
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$CharCoercion.class */
    private static final class CharCoercion implements Coercion {
        private CharCoercion() {
        }

        /* synthetic */ CharCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return LuaNumber.valueOf((int) ((Character) obj).charValue());
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$DoubleCoercion.class */
    private static final class DoubleCoercion implements Coercion {
        private DoubleCoercion() {
        }

        /* synthetic */ DoubleCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return LuaNumber.valueOf(((Number) obj).doubleValue());
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$StringCoercion.class */
    private static final class StringCoercion implements Coercion {
        private StringCoercion() {
        }

        /* synthetic */ StringCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return LuaString.valueOf(obj.toString());
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$BytesCoercion.class */
    private static final class BytesCoercion implements Coercion {
        private BytesCoercion() {
        }

        /* synthetic */ BytesCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return LuaValue.valueOf((byte[]) obj);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$ClassCoercion.class */
    private static final class ClassCoercion implements Coercion {
        private ClassCoercion() {
        }

        /* synthetic */ ClassCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return JavaClass.forClass((Class) obj);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$InstanceCoercion.class */
    public static final class InstanceCoercion implements Coercion {
        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return new JavaInstance(obj);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$ArrayCoercion.class */
    public static final class ArrayCoercion implements Coercion {
        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return new JavaArray(obj);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/CoerceJavaToLua$LuaCoercion.class */
    private static final class LuaCoercion implements Coercion {
        private LuaCoercion() {
        }

        /* synthetic */ LuaCoercion(byte b2) {
            this();
        }

        @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
        public final LuaValue coerce(Object obj) {
            return (LuaValue) obj;
        }
    }

    static {
        BoolCoercion boolCoercion = new BoolCoercion((byte) 0);
        IntCoercion intCoercion = new IntCoercion((byte) 0);
        CharCoercion charCoercion = new CharCoercion((byte) 0);
        DoubleCoercion doubleCoercion = new DoubleCoercion((byte) 0);
        StringCoercion stringCoercion = new StringCoercion((byte) 0);
        BytesCoercion bytesCoercion = new BytesCoercion((byte) 0);
        ClassCoercion classCoercion = new ClassCoercion((byte) 0);
        f1605a.put(Boolean.class, boolCoercion);
        f1605a.put(Byte.class, intCoercion);
        f1605a.put(Character.class, charCoercion);
        f1605a.put(Short.class, intCoercion);
        f1605a.put(Integer.class, intCoercion);
        f1605a.put(Long.class, doubleCoercion);
        f1605a.put(Float.class, doubleCoercion);
        f1605a.put(Double.class, doubleCoercion);
        f1605a.put(String.class, stringCoercion);
        f1605a.put(byte[].class, bytesCoercion);
        f1605a.put(Class.class, classCoercion);
        instanceCoercion = new InstanceCoercion();
        arrayCoercion = new ArrayCoercion();
        f1606b = new LuaCoercion((byte) 0);
    }

    public static LuaValue coerce(Object obj) {
        if (obj == null) {
            return LuaValue.NIL;
        }
        Class<?> cls = obj.getClass();
        if (!cls.isArray()) {
            Coercion coercion = f1605a.get(cls);
            Coercion coercion2 = coercion;
            if (coercion == null) {
                coercion2 = obj instanceof LuaValue ? f1606b : instanceCoercion;
                f1605a.put(cls, coercion2);
            }
            return coercion2.coerce(obj);
        }
        return arrayCoercion.coerce(obj);
    }
}
