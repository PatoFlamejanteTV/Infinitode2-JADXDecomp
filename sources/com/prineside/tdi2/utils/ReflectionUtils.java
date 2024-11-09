package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IdentityMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.WeakHashMap;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ReflectionUtils.class */
public final class ReflectionUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3897a = TLog.forClass(ReflectionUtils.class);

    /* renamed from: b, reason: collision with root package name */
    private static final IdentityMap<Class<?>, Constructor<?>> f3898b = new IdentityMap<>();
    private static final ObjectMap<Class<?>, Array<Field>> c = new ObjectMap<>();
    private static final ObjectMap<Field, EnumKeyArray> d = new ObjectMap<>();
    private static final WeakHashMap<String, Class<?>> e = new WeakHashMap<>();
    public static final Comparator<Field> FIELD_COMPARATOR = (field, field2) -> {
        return field.getName().compareTo(field2.getName());
    };
    public static final Comparator<Constructor<?>> CONSTRUCTOR_COMPARATOR = (constructor, constructor2) -> {
        int compareTo = constructor.getName().compareTo(constructor2.getName());
        if (compareTo == 0) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Class<?>[] parameterTypes2 = constructor2.getParameterTypes();
            int compare = Integer.compare(parameterTypes.length, parameterTypes2.length);
            if (compare == 0) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> cls = parameterTypes[i];
                    Class<?> cls2 = parameterTypes2[i];
                    String simpleName = cls.getSimpleName();
                    String simpleName2 = cls2.getSimpleName();
                    int compare2 = Integer.compare(simpleName.length(), simpleName2.length());
                    if (compare2 != 0) {
                        return compare2;
                    }
                    for (int i2 = 0; i2 < simpleName.length(); i2++) {
                        int compare3 = Integer.compare(simpleName.charAt(i2), simpleName2.charAt(i2));
                        if (compare3 != 0) {
                            return compare3;
                        }
                    }
                }
                return 0;
            }
            return compare;
        }
        return compareTo;
    };
    public static final Comparator<Method> METHOD_COMPARATOR = (method, method2) -> {
        int compareTo = method.getName().compareTo(method2.getName());
        if (compareTo == 0) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?>[] parameterTypes2 = method2.getParameterTypes();
            int compare = Integer.compare(parameterTypes.length, parameterTypes2.length);
            if (compare == 0) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> cls = parameterTypes[i];
                    Class<?> cls2 = parameterTypes2[i];
                    String simpleName = cls.getSimpleName();
                    String simpleName2 = cls2.getSimpleName();
                    int compare2 = Integer.compare(simpleName.length(), simpleName2.length());
                    if (compare2 != 0) {
                        return compare2;
                    }
                    for (int i2 = 0; i2 < simpleName.length(); i2++) {
                        int compare3 = Integer.compare(simpleName.charAt(i2), simpleName2.charAt(i2));
                        if (compare3 != 0) {
                            return compare3;
                        }
                    }
                }
                return 0;
            }
            return compare;
        }
        return compareTo;
    };

    public static synchronized <T> T newObject(Class<T> cls) {
        Constructor<T> constructor = (Constructor) f3898b.get(cls);
        Constructor<T> constructor2 = constructor;
        if (constructor == null) {
            try {
                constructor2 = cls.getConstructor(new Class[0]);
                f3898b.put(cls, constructor2);
            } catch (NoSuchMethodException unused) {
                throw new IllegalArgumentException(cls + " has no simple (no-arg) constructor");
            }
        }
        try {
            return constructor2.newInstance(new Object[0]);
        } catch (Exception e2) {
            throw new IllegalStateException("Can not create new instance of " + cls, e2);
        }
    }

    public static synchronized Array<Field> getAllSerializableFields(Class<?> cls) {
        Array<Field> array = c.get(cls, null);
        if (array != null) {
            return array;
        }
        Array<Field> array2 = new Array<>(Field.class);
        while (cls != null && cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                if (!isFieldIgnoredBySerialization(field) && !array2.contains(field, true)) {
                    try {
                        field.setAccessible(true);
                    } catch (Exception unused) {
                    }
                    array2.add(field);
                }
            }
            cls = cls.getSuperclass();
        }
        Threads.sortGdxArray(array2, FIELD_COMPARATOR);
        c.put(cls, array2);
        return array2;
    }

    public static boolean isFieldIgnoredBySerialization(Field field) {
        return Modifier.isStatic(field.getModifiers()) || field.isAnnotationPresent(NAGS.class) || field.getType().isAnnotationPresent(NAGS.class);
    }

    public static synchronized EnumKeyArray getEnumKeyArrayFieldAnnotationCached(Field field) {
        if (field.isAnnotationPresent(EnumKeyArray.class)) {
            if (d.containsKey(field)) {
                return d.get(field);
            }
            EnumKeyArray enumKeyArray = (EnumKeyArray) field.getAnnotation(EnumKeyArray.class);
            d.put(field, enumKeyArray);
            return enumKeyArray;
        }
        return null;
    }

    public static synchronized Class<?> getClassByName(String str) {
        Class<?> cls = e.get(str);
        if (cls == null) {
            Class<?> cls2 = null;
            boolean z = -1;
            switch (str.hashCode()) {
                case -1325958191:
                    if (str.equals("double")) {
                        z = 6;
                        break;
                    }
                    break;
                case 104431:
                    if (str.equals("int")) {
                        z = false;
                        break;
                    }
                    break;
                case 3039496:
                    if (str.equals("byte")) {
                        z = 3;
                        break;
                    }
                    break;
                case 3052374:
                    if (str.equals("char")) {
                        z = 4;
                        break;
                    }
                    break;
                case 3327612:
                    if (str.equals("long")) {
                        z = 2;
                        break;
                    }
                    break;
                case 64711720:
                    if (str.equals("boolean")) {
                        z = 7;
                        break;
                    }
                    break;
                case 97526364:
                    if (str.equals("float")) {
                        z = 5;
                        break;
                    }
                    break;
                case 109413500:
                    if (str.equals("short")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    cls2 = Integer.TYPE;
                    break;
                case true:
                    cls2 = Short.TYPE;
                    break;
                case true:
                    cls2 = Long.TYPE;
                    break;
                case true:
                    cls2 = Byte.TYPE;
                    break;
                case true:
                    cls2 = Character.TYPE;
                    break;
                case true:
                    cls2 = Float.TYPE;
                    break;
                case true:
                    cls2 = Double.TYPE;
                    break;
                case true:
                    cls2 = Boolean.TYPE;
                    break;
            }
            if (cls2 != null) {
                e.put(str, cls2);
                return cls2;
            }
            try {
                Class<?> cls3 = Class.forName(str);
                e.put(str, cls3);
                return cls3;
            } catch (ClassNotFoundException unused) {
                e.put(str, UnknownClass.class);
                return null;
            }
        }
        if (cls == UnknownClass.class) {
            return null;
        }
        return cls;
    }

    public static boolean isFirstOverridesSecond(Class<?> cls, Class<?> cls2) {
        if (cls2 == Object.class) {
            return true;
        }
        if (cls.isInterface() && !cls2.isInterface()) {
            return false;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        while (true) {
            Class<? super Object> cls3 = superclass;
            if (cls3 != null) {
                if (cls2 == cls3) {
                    return true;
                }
                superclass = cls3.getSuperclass();
            } else {
                return cls2.isAssignableFrom(cls);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ReflectionUtils$LuaRelated.class */
    public static final class LuaRelated {
        private static void a(Class<?> cls, int i, StringBuilder stringBuilder, int i2) {
            String simpleName;
            int i3 = 0;
            while (cls.isArray()) {
                i3++;
                cls = cls.getComponentType();
            }
            stringBuilder.append('_');
            if (i != 1) {
                stringBuilder.append(i);
            }
            if (cls == Integer.TYPE) {
                simpleName = FlexmarkHtmlConverter.I_NODE;
            } else if (cls == Float.TYPE) {
                simpleName = "f";
            } else if (cls == Double.TYPE) {
                simpleName = "d";
            } else if (cls == Long.TYPE) {
                simpleName = "l";
            } else if (cls == Character.TYPE) {
                simpleName = "c";
            } else if (cls == Byte.TYPE) {
                simpleName = "byte";
            } else if (cls == Boolean.TYPE) {
                simpleName = FlexmarkHtmlConverter.B_NODE;
            } else if (cls == Short.TYPE) {
                simpleName = "s";
            } else if (i2 <= 1) {
                StringBuilder stringBuilder2 = new StringBuilder();
                simpleName = cls.getSimpleName();
                for (int i4 = 0; i4 < simpleName.length(); i4++) {
                    char charAt = simpleName.charAt(i4);
                    if (Character.isUpperCase(charAt) || Character.isDigit(charAt)) {
                        stringBuilder2.append(charAt);
                    }
                }
                if (stringBuilder2.length != 0) {
                    simpleName = stringBuilder2.toString();
                }
            } else {
                simpleName = cls.getSimpleName();
            }
            stringBuilder.append(simpleName);
            for (int i5 = 0; i5 < i3; i5++) {
                stringBuilder.append("Arr");
            }
        }

        public static Array<String> generateOverloadSuffixForTypeArray(Array<ObjectPair<Object, Array<Class<?>>>> array, int i) {
            Array<String> array2 = new Array<>(true, array.size, String.class);
            for (int i2 = 0; i2 < array.size; i2++) {
                array.get(i2);
                Array<Class<?>> array3 = array.get(i2).second;
                if (array3.size == 0) {
                    array2.add("");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    Class<?> cls = null;
                    int i3 = 0;
                    Array.ArrayIterator<Class<?>> it = array3.iterator();
                    while (it.hasNext()) {
                        Class<?> next = it.next();
                        if (cls == next) {
                            i3++;
                        } else {
                            if (i3 != 0) {
                                a(cls, i3, stringBuilder, i);
                            }
                            i3 = 1;
                            cls = next;
                        }
                    }
                    if (i3 != 0) {
                        a(cls, i3, stringBuilder, i);
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (array2.contains(stringBuilder2, false)) {
                        return generateOverloadSuffixForTypeArray(array, i + 1);
                    }
                    array2.add(stringBuilder2);
                }
            }
            return array2;
        }

        public static Array<String> generateOverloadSuffixForMethods(Array<Method> array) {
            Array array2 = new Array(true, array.size, ObjectPair.class);
            for (int i = 0; i < array.size; i++) {
                Method method = array.get(i);
                array2.add(new ObjectPair(method, new Array(method.getParameterTypes())));
            }
            return generateOverloadSuffixForTypeArray(array2, 1);
        }

        public static Array<String> generateOverloadSuffixForConstructors(Array<Constructor<?>> array) {
            Array array2 = new Array(true, array.size, ObjectPair.class);
            for (int i = 0; i < array.size; i++) {
                Constructor<?> constructor = array.get(i);
                array2.add(new ObjectPair(constructor, new Array(constructor.getParameterTypes())));
            }
            return generateOverloadSuffixForTypeArray(array2, 1);
        }

        public static Array<Class<?>> filterClasses(HashSet<Class<?>> hashSet, ObjectConsumer<ObjectPair<Class<?>, String>> objectConsumer) {
            Array<Class<?>> array = new Array<>(true, 1, Class.class);
            Iterator<Class<?>> it = hashSet.iterator();
            while (it.hasNext()) {
                Class<?> next = it.next();
                try {
                    if (next.isAnonymousClass()) {
                        if (objectConsumer != null) {
                            objectConsumer.accept(new ObjectPair<>(next, "anonymous"));
                        }
                    } else if (!Modifier.isPublic(next.getModifiers())) {
                        if (objectConsumer != null) {
                            objectConsumer.accept(new ObjectPair<>(next, "not public"));
                        }
                    } else if (next.isMemberClass() && next.getSimpleName().startsWith(JavaConstant.Dynamic.DEFAULT_NAME)) {
                        if (objectConsumer != null) {
                            objectConsumer.accept(new ObjectPair<>(next, "member class starting with underscore"));
                        }
                    } else {
                        array.add(next);
                    }
                } catch (Throwable unused) {
                }
            }
            Threads.sortGdxArray(array, (cls, cls2) -> {
                return cls.getName().compareTo(cls2.getName());
            });
            return array;
        }

        public static Array<Field> gatherFields(Class<?> cls) {
            Array<Field> array = new Array<>(true, 1, Field.class);
            try {
                for (Field field : cls.getDeclaredFields()) {
                    if (Modifier.isPublic(field.getModifiers()) && !field.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(field.getType().getModifiers())) {
                        array.add(field);
                    }
                }
            } catch (Throwable unused) {
            }
            array.sort(ReflectionUtils.FIELD_COMPARATOR);
            return array;
        }

        public static Array<Constructor<?>> gatherConstructors(Class<?> cls) {
            int i;
            Array<Constructor<?>> array = new Array<>(true, 1, Constructor.class);
            if (Modifier.isAbstract(cls.getModifiers())) {
                return array;
            }
            if (cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
                return array;
            }
            try {
                for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
                    if (Modifier.isPublic(constructor.getModifiers())) {
                        Class<?>[] parameterTypes = constructor.getParameterTypes();
                        int length = parameterTypes.length;
                        while (true) {
                            if (i < length) {
                                i = Modifier.isPublic(parameterTypes[i].getModifiers()) ? i + 1 : 0;
                            } else if (!constructor.isAnnotationPresent(Deprecated.class)) {
                                array.add(constructor);
                            }
                        }
                    }
                }
                array.sort(ReflectionUtils.CONSTRUCTOR_COMPARATOR);
                return array;
            } catch (Throwable unused) {
                return array;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Array<Method> gatherMethods(Class<?> cls) {
            int i;
            DelayedRemovalArray delayedRemovalArray = new DelayedRemovalArray(true, 1, Method.class);
            for (Method method : cls.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers())) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    int length = parameterTypes.length;
                    while (true) {
                        if (i < length) {
                            i = Modifier.isPublic(parameterTypes[i].getModifiers()) ? i + 1 : 0;
                        } else if (!method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getReturnType().getModifiers()) && !method.getName().equals("finalize")) {
                            delayedRemovalArray.add(method);
                        }
                    }
                }
            }
            delayedRemovalArray.begin();
            for (int i2 = delayedRemovalArray.size - 1; i2 >= 0; i2--) {
                Method method2 = (Method) delayedRemovalArray.get(i2);
                int i3 = 0;
                while (true) {
                    if (i3 >= delayedRemovalArray.size) {
                        break;
                    }
                    if (i2 != i3) {
                        Method method3 = (Method) delayedRemovalArray.get(i3);
                        if (method3 == method2) {
                            delayedRemovalArray.removeIndex(i2);
                            break;
                        }
                        if (method3.getName().equals(method2.getName())) {
                            Class<?>[] parameterTypes2 = method2.getParameterTypes();
                            Class<?>[] parameterTypes3 = method3.getParameterTypes();
                            if (parameterTypes2.length == parameterTypes3.length) {
                                boolean z = true;
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= parameterTypes2.length) {
                                        break;
                                    }
                                    if (parameterTypes2[i4] == parameterTypes3[i4]) {
                                        i4++;
                                    } else {
                                        z = false;
                                        break;
                                    }
                                }
                                if (z) {
                                    Class<?> returnType = method2.getReturnType();
                                    Class<?> returnType2 = method3.getReturnType();
                                    if (returnType.isAssignableFrom(returnType2)) {
                                        delayedRemovalArray.removeIndex(i2);
                                    } else if (returnType2.isAssignableFrom(returnType)) {
                                        delayedRemovalArray.removeIndex(i3);
                                    } else if (returnType.isInterface() && !returnType2.isInterface()) {
                                        delayedRemovalArray.removeIndex(i3);
                                    } else if (returnType.isInterface() || !returnType2.isInterface()) {
                                        ReflectionUtils.f3897a.i("/!\\ Same method signatures (" + method2 + ") in " + cls + ", return types:", new Object[0]);
                                        ReflectionUtils.f3897a.i("     A: " + method2.getReturnType(), new Object[0]);
                                        ReflectionUtils.f3897a.i("     B: " + method3.getReturnType(), new Object[0]);
                                    } else {
                                        delayedRemovalArray.removeIndex(i2);
                                    }
                                }
                            }
                        }
                    }
                    i3++;
                }
            }
            delayedRemovalArray.end();
            Threads.sortGdxArray(delayedRemovalArray, ReflectionUtils.METHOD_COMPARATOR);
            return delayedRemovalArray;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ReflectionUtils$UnknownClass.class */
    private static final class UnknownClass {
        private UnknownClass() {
        }
    }
}
