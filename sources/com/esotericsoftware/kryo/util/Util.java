package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.serializers.ClosureSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.Generics;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/Util.class */
public class Util {
    public static final boolean isAndroid = "Dalvik".equals(System.getProperty("java.vm.name"));
    public static final boolean unsafe;
    public static final int maxArraySize = 2147483639;
    private static final Map<Class<?>, Class<?>> primitiveWrappers;

    static {
        boolean z = false;
        if ("false".equals(System.getProperty("kryo.unsafe"))) {
            if (Log.TRACE) {
                Log.trace("kryo", "Unsafe is disabled.");
            }
        } else {
            try {
                z = Class.forName("com.esotericsoftware.kryo.unsafe.UnsafeUtil", true, FieldSerializer.class.getClassLoader()).getField("unsafe").get(null) != null;
            } catch (Throwable th) {
                if (Log.TRACE) {
                    Log.trace("kryo", "Unsafe is unavailable.", th);
                }
            }
        }
        unsafe = z;
        HashMap hashMap = new HashMap();
        primitiveWrappers = hashMap;
        hashMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrappers.put(Byte.TYPE, Byte.class);
        primitiveWrappers.put(Character.TYPE, Character.class);
        primitiveWrappers.put(Double.TYPE, Double.class);
        primitiveWrappers.put(Float.TYPE, Float.class);
        primitiveWrappers.put(Integer.TYPE, Integer.class);
        primitiveWrappers.put(Long.TYPE, Long.class);
        primitiveWrappers.put(Short.TYPE, Short.class);
    }

    public static boolean isUnsafeAvailable() {
        return unsafe;
    }

    public static boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception unused) {
            Log.debug("kryo", "Class not available: " + str);
            return false;
        }
    }

    public static Class getWrapperClass(Class cls) {
        return cls == Integer.TYPE ? Integer.class : cls == Float.TYPE ? Float.class : cls == Boolean.TYPE ? Boolean.class : cls == Byte.TYPE ? Byte.class : cls == Long.TYPE ? Long.class : cls == Character.TYPE ? Character.class : cls == Double.TYPE ? Double.class : cls == Short.TYPE ? Short.class : cls;
    }

    public static Class getPrimitiveClass(Class cls) {
        return cls == Integer.class ? Integer.TYPE : cls == Float.class ? Float.TYPE : cls == Boolean.class ? Boolean.TYPE : cls == Byte.class ? Byte.TYPE : cls == Long.class ? Long.TYPE : cls == Character.class ? Character.TYPE : cls == Double.class ? Double.TYPE : cls == Short.class ? Short.TYPE : cls == Void.class ? Void.TYPE : cls;
    }

    public static Class getArrayType(Class cls) {
        return cls == String.class ? String[].class : cls == Integer.class ? Integer[].class : cls == Float.class ? Float[].class : cls == Boolean.class ? Boolean[].class : cls == Byte.class ? Byte[].class : cls == Long.class ? Long[].class : cls == Character.class ? Character[].class : cls == Double.class ? Double[].class : cls == Short.class ? Short[].class : Array.newInstance((Class<?>) cls, 0).getClass();
    }

    public static boolean isWrapperClass(Class cls) {
        return cls == Integer.class || cls == Float.class || cls == Boolean.class || cls == Byte.class || cls == Long.class || cls == Character.class || cls == Double.class || cls == Short.class;
    }

    public static boolean isEnum(Class cls) {
        return Enum.class.isAssignableFrom(cls) && cls != Enum.class;
    }

    public static void log(String str, Object obj, int i) {
        if (obj == null) {
            if (Log.TRACE) {
                Log.trace("kryo", str + ": null" + pos(i));
                return;
            }
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls.isPrimitive() || isWrapperClass(cls) || cls == String.class) {
            if (Log.TRACE) {
                Log.trace("kryo", str + ": " + string(obj) + pos(i));
                return;
            }
            return;
        }
        Log.debug("kryo", str + ": " + string(obj) + pos(i));
    }

    public static String pos(int i) {
        return i == -1 ? "" : " [" + i + "]";
    }

    public static String string(Object obj) {
        if (obj == null) {
            return "null";
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            return className(cls);
        }
        String className = Log.TRACE ? className(cls) : cls.getSimpleName();
        try {
            if (cls.getMethod("toString", new Class[0]).getDeclaringClass() == Object.class) {
                return className;
            }
        } catch (Exception unused) {
        }
        try {
            String str = String.valueOf(obj) + " (" + className + ")";
            return str.length() > 97 ? str.substring(0, 97) + "..." : str;
        } catch (Throwable th) {
            return className + " (toString exception: " + th + ")";
        }
    }

    public static String className(Class cls) {
        if (cls == null) {
            return "null";
        }
        if (cls.isArray()) {
            Class elementClass = getElementClass(cls);
            StringBuilder sb = new StringBuilder(16);
            int dimensionCount = getDimensionCount(cls);
            for (int i = 0; i < dimensionCount; i++) {
                sb.append("[]");
            }
            return className(elementClass) + ((Object) sb);
        }
        if (cls.isPrimitive() || cls == Object.class || cls == Boolean.class || cls == Byte.class || cls == Character.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class) {
            return cls.getSimpleName();
        }
        return cls.getName();
    }

    public static String classNames(Class[] clsArr) {
        StringBuilder sb = new StringBuilder(32);
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(className(clsArr[i]));
        }
        return sb.toString();
    }

    public static String canonicalName(Class cls) {
        if (cls == null) {
            return "null";
        }
        String canonicalName = cls.getCanonicalName();
        return canonicalName != null ? canonicalName : className(cls);
    }

    public static String simpleName(Type type) {
        return type instanceof Class ? ((Class) type).getSimpleName() : type.toString();
    }

    public static String simpleName(Class cls, Generics.GenericType genericType) {
        StringBuilder sb = new StringBuilder(32);
        sb.append((cls.isArray() ? getElementClass(cls) : cls).getSimpleName());
        if (genericType.arguments != null) {
            sb.append('<');
            int length = genericType.arguments.length;
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(genericType.arguments[i].toString());
            }
            sb.append('>');
        }
        if (cls.isArray()) {
            int dimensionCount = getDimensionCount(cls);
            for (int i2 = 0; i2 < dimensionCount; i2++) {
                sb.append("[]");
            }
        }
        return sb.toString();
    }

    public static int getDimensionCount(Class cls) {
        int i = 0;
        Class<?> componentType = cls.getComponentType();
        while (true) {
            Class<?> cls2 = componentType;
            if (cls2 != null) {
                i++;
                componentType = cls2.getComponentType();
            } else {
                return i;
            }
        }
    }

    public static Class getElementClass(Class cls) {
        Class cls2 = cls;
        while (true) {
            Class cls3 = cls2;
            if (cls3.getComponentType() != null) {
                cls2 = cls3.getComponentType();
            } else {
                return cls3;
            }
        }
    }

    public static boolean isAssignableTo(Class<?> cls, Class<?> cls2) {
        if (cls2 == Object.class || cls2.isAssignableFrom(cls)) {
            return true;
        }
        if (cls.isPrimitive()) {
            return isPrimitiveWrapperOf(cls2, cls) || cls2.isAssignableFrom(getPrimitiveWrapper(cls));
        }
        if (cls2.isPrimitive()) {
            return isPrimitiveWrapperOf(cls, cls2);
        }
        if (cls == ClosureSerializer.Closure.class) {
            return cls2.isInterface();
        }
        return false;
    }

    private static boolean isPrimitiveWrapperOf(Class<?> cls, Class<?> cls2) {
        return getPrimitiveWrapper(cls2) == cls;
    }

    private static Class<?> getPrimitiveWrapper(Class<?> cls) {
        if (cls.isPrimitive()) {
            return primitiveWrappers.get(cls);
        }
        throw new IllegalArgumentException("Argument has to be primitive type");
    }

    public static boolean isAscii(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

    public static <T extends SerializerFactory> T newFactory(Class<T> cls, Class<? extends Serializer> cls2) {
        try {
            if (cls2 != null) {
                try {
                    return cls.getConstructor(Class.class).newInstance(cls2);
                } catch (NoSuchMethodException unused) {
                }
            }
            return cls.newInstance();
        } catch (Exception e) {
            if (cls2 == null) {
                throw new IllegalArgumentException("Unable to create serializer factory: " + cls.getName(), e);
            }
            throw new IllegalArgumentException("Unable to create serializer factory \"" + cls.getName() + "\" for serializer class: " + className(cls2), e);
        }
    }
}
