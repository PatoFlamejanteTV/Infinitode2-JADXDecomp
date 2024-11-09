package nonapi.io.github.classgraph.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import nonapi.io.github.classgraph.concurrency.SingletonMap;
import nonapi.io.github.classgraph.utils.LogNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/ReflectionDriver.class */
public abstract class ReflectionDriver {
    private final SingletonMap<Class<?>, ClassMemberCache, Exception> classToClassMemberCache = new SingletonMap<Class<?>, ClassMemberCache, Exception>() { // from class: nonapi.io.github.classgraph.reflection.ReflectionDriver.1
        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap
        public ClassMemberCache newInstance(Class<?> cls, LogNode logNode) {
            return new ClassMemberCache(cls);
        }
    };
    private static Method isAccessibleMethod;
    private static Method canAccessMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Class<?> findClass(String str);

    abstract Method[] getDeclaredMethods(Class<?> cls);

    abstract <T> Constructor<T>[] getDeclaredConstructors(Class<T> cls);

    abstract Field[] getDeclaredFields(Class<?> cls);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object getField(Object obj, Field field);

    abstract void setField(Object obj, Field field, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object getStaticField(Field field);

    abstract void setStaticField(Field field, Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object invokeMethod(Object obj, Method method, Object... objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object invokeStaticMethod(Method method, Object... objArr);

    abstract boolean makeAccessible(Object obj, AccessibleObject accessibleObject);

    static {
        try {
            isAccessibleMethod = AccessibleObject.class.getDeclaredMethod("isAccessible", new Class[0]);
        } catch (Throwable unused) {
        }
        try {
            canAccessMethod = AccessibleObject.class.getDeclaredMethod("canAccess", Object.class);
        } catch (Throwable unused2) {
        }
    }

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/ReflectionDriver$ClassMemberCache.class */
    public class ClassMemberCache {
        private final Map<String, List<Method>> methodNameToMethods;
        private final Map<String, Field> fieldNameToField;

        private ClassMemberCache(Class<?> cls) {
            this.methodNameToMethods = new HashMap();
            this.fieldNameToField = new HashMap();
            HashSet hashSet = new HashSet();
            LinkedList linkedList = new LinkedList();
            Class<?> cls2 = cls;
            while (true) {
                Class<?> cls3 = cls2;
                if (cls3 == null) {
                    break;
                }
                try {
                    for (Method method : ReflectionDriver.this.getDeclaredMethods(cls3)) {
                        cacheMethod(method);
                    }
                    for (Field field : ReflectionDriver.this.getDeclaredFields(cls3)) {
                        cacheField(field);
                    }
                    if (cls3.isInterface() && hashSet.add(cls3)) {
                        linkedList.add(cls3);
                    }
                    for (Class<?> cls4 : cls3.getInterfaces()) {
                        if (hashSet.add(cls4)) {
                            linkedList.add(cls4);
                        }
                    }
                } catch (Exception unused) {
                }
                cls2 = cls3.getSuperclass();
            }
            while (!linkedList.isEmpty()) {
                Class<?> cls5 = (Class) linkedList.remove();
                try {
                    for (Method method2 : ReflectionDriver.this.getDeclaredMethods(cls5)) {
                        cacheMethod(method2);
                    }
                } catch (Exception unused2) {
                }
                for (Class<?> cls6 : cls5.getInterfaces()) {
                    if (hashSet.add(cls6)) {
                        linkedList.add(cls6);
                    }
                }
            }
        }

        private void cacheMethod(Method method) {
            List<Method> list = this.methodNameToMethods.get(method.getName());
            List<Method> list2 = list;
            if (list == null) {
                Map<String, List<Method>> map = this.methodNameToMethods;
                String name = method.getName();
                ArrayList arrayList = new ArrayList();
                list2 = arrayList;
                map.put(name, arrayList);
            }
            list2.add(method);
        }

        private void cacheField(Field field) {
            if (!this.fieldNameToField.containsKey(field.getName())) {
                this.fieldNameToField.put(field.getName(), field);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAccessible(Object obj, AccessibleObject accessibleObject) {
        if (canAccessMethod != null) {
            try {
                return ((Boolean) canAccessMethod.invoke(accessibleObject, obj)).booleanValue();
            } catch (Throwable unused) {
            }
        }
        if (isAccessibleMethod != null) {
            try {
                return ((Boolean) isAccessibleMethod.invoke(accessibleObject, new Object[0])).booleanValue();
            } catch (Throwable unused2) {
                return false;
            }
        }
        return false;
    }

    protected Field findField(Class<?> cls, Object obj, String str) {
        Field field = (Field) this.classToClassMemberCache.get(cls, null).fieldNameToField.get(str);
        if (field != null) {
            if (!isAccessible(obj, field)) {
                makeAccessible(obj, field);
            }
            return field;
        }
        throw new NoSuchFieldException("Could not find field " + cls.getName() + "." + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Field findStaticField(Class<?> cls, String str) {
        return findField(cls, null, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Field findInstanceField(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("obj cannot be null");
        }
        return findField(obj.getClass(), obj, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Method findMethod(Class<?> cls, Object obj, String str, Class<?>... clsArr) {
        List<Method> list = (List) this.classToClassMemberCache.get(cls, null).methodNameToMethods.get(str);
        if (list != null) {
            boolean z = false;
            for (Method method : list) {
                if (Arrays.equals(method.getParameterTypes(), clsArr)) {
                    z = true;
                    if (isAccessible(obj, method)) {
                        return method;
                    }
                }
            }
            if (z) {
                for (Method method2 : list) {
                    if (Arrays.equals(method2.getParameterTypes(), clsArr) && makeAccessible(obj, method2)) {
                        return method2;
                    }
                }
            }
            throw new NoSuchMethodException("Could not make method accessible: " + cls.getName() + "." + str);
        }
        throw new NoSuchMethodException("Could not find method " + cls.getName() + "." + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Method findStaticMethod(Class<?> cls, String str, Class<?>... clsArr) {
        return findMethod(cls, null, str, clsArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Method findInstanceMethod(Object obj, String str, Class<?>... clsArr) {
        if (obj == null) {
            throw new IllegalArgumentException("obj cannot be null");
        }
        return findMethod(obj.getClass(), obj, str, clsArr);
    }
}
