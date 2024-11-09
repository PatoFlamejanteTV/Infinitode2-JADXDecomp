package nonapi.io.github.classgraph.json;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.Callable;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONUtils.class */
public final class JSONUtils {
    private static Method isAccessibleMethod;
    private static Method setAccessibleMethod;
    private static Method trySetAccessibleMethod;
    static final String ID_KEY = "__ID";
    static final String ID_PREFIX = "[#";
    static final String ID_SUFFIX = "]";
    private static final String[] JSON_CHAR_REPLACEMENTS;
    private static final String[] INDENT_LEVELS;

    static {
        try {
            isAccessibleMethod = AccessibleObject.class.getDeclaredMethod("isAccessible", new Class[0]);
        } catch (Throwable unused) {
        }
        try {
            setAccessibleMethod = AccessibleObject.class.getDeclaredMethod("setAccessible", Boolean.TYPE);
        } catch (Throwable unused2) {
        }
        try {
            trySetAccessibleMethod = AccessibleObject.class.getDeclaredMethod("trySetAccessible", new Class[0]);
        } catch (Throwable unused3) {
        }
        JSON_CHAR_REPLACEMENTS = new String[256];
        int i = 0;
        while (i < 256) {
            if (i == 32) {
                i = 127;
            }
            int i2 = i >> 4;
            char c = i2 <= 9 ? (char) (i2 + 48) : (char) ((i2 + 65) - 10);
            int i3 = i & 15;
            JSON_CHAR_REPLACEMENTS[i] = "\\u00" + c + ((char) (i3 <= 9 ? i3 + 48 : (i3 + 65) - 10));
            i++;
        }
        JSON_CHAR_REPLACEMENTS[34] = "\\\"";
        JSON_CHAR_REPLACEMENTS[92] = "\\\\";
        JSON_CHAR_REPLACEMENTS[10] = "\\n";
        JSON_CHAR_REPLACEMENTS[13] = "\\r";
        JSON_CHAR_REPLACEMENTS[9] = "\\t";
        JSON_CHAR_REPLACEMENTS[8] = "\\b";
        JSON_CHAR_REPLACEMENTS[12] = "\\f";
        INDENT_LEVELS = new String[17];
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < INDENT_LEVELS.length; i4++) {
            INDENT_LEVELS[i4] = sb.toString();
            sb.append(' ');
        }
    }

    private static boolean isAccessible(AccessibleObject accessibleObject) {
        if (isAccessibleMethod != null) {
            try {
                if (((Boolean) isAccessibleMethod.invoke(accessibleObject, new Object[0])).booleanValue()) {
                    return true;
                }
                return false;
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean tryMakeAccessible(AccessibleObject accessibleObject) {
        if (setAccessibleMethod != null) {
            try {
                setAccessibleMethod.invoke(accessibleObject, Boolean.TRUE);
                return true;
            } catch (Throwable unused) {
            }
        }
        if (trySetAccessibleMethod != null) {
            try {
                if (((Boolean) trySetAccessibleMethod.invoke(accessibleObject, new Object[0])).booleanValue()) {
                    return true;
                }
                return false;
            } catch (Throwable unused2) {
                return false;
            }
        }
        return false;
    }

    public static boolean makeAccessible(final AccessibleObject accessibleObject, ReflectionUtils reflectionUtils) {
        if (isAccessible(accessibleObject) || tryMakeAccessible(accessibleObject)) {
            return true;
        }
        try {
            return ((Boolean) reflectionUtils.doPrivileged(new Callable<Boolean>() { // from class: nonapi.io.github.classgraph.json.JSONUtils.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public final Boolean call() {
                    return Boolean.valueOf(JSONUtils.tryMakeAccessible(accessibleObject));
                }
            })).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    private JSONUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void escapeJSONString(String str, StringBuilder sb) {
        if (str == null) {
            return;
        }
        boolean z = false;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt > 255 || JSON_CHAR_REPLACEMENTS[charAt] != null) {
                z = true;
                break;
            }
        }
        if (!z) {
            sb.append(str);
            return;
        }
        int length2 = str.length();
        for (int i2 = 0; i2 < length2; i2++) {
            char charAt2 = str.charAt(i2);
            if (charAt2 <= 255) {
                String str2 = JSON_CHAR_REPLACEMENTS[charAt2];
                if (str2 == null) {
                    sb.append(charAt2);
                } else {
                    sb.append(str2);
                }
            } else {
                sb.append("\\u");
                int i3 = (charAt2 & 61440) >> 12;
                sb.append(i3 <= 9 ? (char) (i3 + 48) : (char) ((i3 + 65) - 10));
                int i4 = (charAt2 & 3840) >> 8;
                sb.append(i4 <= 9 ? (char) (i4 + 48) : (char) ((i4 + 65) - 10));
                int i5 = (charAt2 & 240) >> 4;
                sb.append(i5 <= 9 ? (char) (i5 + 48) : (char) ((i5 + 65) - 10));
                int i6 = charAt2 & 15;
                sb.append(i6 <= 9 ? (char) (i6 + 48) : (char) ((i6 + 65) - 10));
            }
        }
    }

    public static String escapeJSONString(String str) {
        StringBuilder sb = new StringBuilder(str.length() << 1);
        escapeJSONString(str, sb);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void indent(int i, int i2, StringBuilder sb) {
        int length = INDENT_LEVELS.length - 1;
        int i3 = i * i2;
        while (true) {
            int i4 = i3;
            if (i4 > 0) {
                int min = Math.min(i4, length);
                sb.append(INDENT_LEVELS[min]);
                i3 = i4 - min;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getFieldValue(Object obj, Field field) {
        Class<?> type = field.getType();
        if (type == Integer.TYPE) {
            return Integer.valueOf(field.getInt(obj));
        }
        if (type == Long.TYPE) {
            return Long.valueOf(field.getLong(obj));
        }
        if (type == Short.TYPE) {
            return Short.valueOf(field.getShort(obj));
        }
        if (type == Double.TYPE) {
            return Double.valueOf(field.getDouble(obj));
        }
        if (type == Float.TYPE) {
            return Float.valueOf(field.getFloat(obj));
        }
        if (type == Boolean.TYPE) {
            return Boolean.valueOf(field.getBoolean(obj));
        }
        if (type == Byte.TYPE) {
            return Byte.valueOf(field.getByte(obj));
        }
        if (type == Character.TYPE) {
            return Character.valueOf(field.getChar(obj));
        }
        return field.get(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isBasicValueType(Class<?> cls) {
        return cls == String.class || cls == Integer.class || cls == Integer.TYPE || cls == Long.class || cls == Long.TYPE || cls == Short.class || cls == Short.TYPE || cls == Float.class || cls == Float.TYPE || cls == Double.class || cls == Double.TYPE || cls == Byte.class || cls == Byte.TYPE || cls == Character.class || cls == Character.TYPE || cls == Boolean.class || cls == Boolean.TYPE || cls.isEnum() || cls == Class.class;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isBasicValueType(Type type) {
        if (type instanceof Class) {
            return isBasicValueType((Class<?>) type);
        }
        if (type instanceof ParameterizedType) {
            return isBasicValueType(((ParameterizedType) type).getRawType());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isBasicValueType(Object obj) {
        return obj == null || (obj instanceof String) || (obj instanceof Integer) || (obj instanceof Boolean) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short) || (obj instanceof Byte) || (obj instanceof Character) || obj.getClass().isEnum() || (obj instanceof Class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isCollectionOrArray(Object obj) {
        Class<?> cls = obj.getClass();
        return Collection.class.isAssignableFrom(cls) || cls.isArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        throw new IllegalArgumentException("Illegal type: " + type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean fieldIsSerializable(Field field, boolean z, ReflectionUtils reflectionUtils) {
        int modifiers = field.getModifiers();
        if ((!z || Modifier.isPublic(modifiers)) && !Modifier.isTransient(modifiers) && !Modifier.isFinal(modifiers) && (modifiers & 4096) == 0) {
            return makeAccessible(field, reflectionUtils);
        }
        return false;
    }
}
