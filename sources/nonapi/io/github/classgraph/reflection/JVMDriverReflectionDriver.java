package nonapi.io.github.classgraph.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/JVMDriverReflectionDriver.class */
class JVMDriverReflectionDriver extends ReflectionDriver {
    private Object driver;
    private final Method getDeclaredMethods;
    private final Method getDeclaredConstructors;
    private final Method getDeclaredFields;
    private final Method getField;
    private final Method setField;
    private final Method invokeMethod;
    private final Method setAccessibleMethod;
    private ClassFinder classFinder;

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/JVMDriverReflectionDriver$ClassFinder.class */
    private interface ClassFinder {
        Class<?> findClass(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JVMDriverReflectionDriver() {
        StandardReflectionDriver standardReflectionDriver = new StandardReflectionDriver();
        Constructor[] declaredConstructors = standardReflectionDriver.getDeclaredConstructors(standardReflectionDriver.findClass("io.github.toolfactory.jvm.DefaultDriver"));
        int length = declaredConstructors.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor constructor = declaredConstructors[i];
            if (constructor.getParameterTypes().length != 0) {
                i++;
            } else {
                this.driver = constructor.newInstance(new Object[0]);
                break;
            }
        }
        if (this.driver == null) {
            throw new IllegalArgumentException("Could not instantiate jvm.DefaultDriver");
        }
        this.getDeclaredMethods = standardReflectionDriver.findInstanceMethod(this.driver, "getDeclaredMethods", Class.class);
        this.getDeclaredConstructors = standardReflectionDriver.findInstanceMethod(this.driver, "getDeclaredConstructors", Class.class);
        this.getDeclaredFields = standardReflectionDriver.findInstanceMethod(this.driver, "getDeclaredFields", Class.class);
        this.getField = standardReflectionDriver.findInstanceMethod(this.driver, "getFieldValue", Object.class, Field.class);
        this.setField = standardReflectionDriver.findInstanceMethod(this.driver, "setFieldValue", Object.class, Field.class, Object.class);
        this.invokeMethod = standardReflectionDriver.findInstanceMethod(this.driver, "invoke", Object.class, Method.class, Object[].class);
        this.setAccessibleMethod = standardReflectionDriver.findInstanceMethod(this.driver, "setAccessible", AccessibleObject.class, Boolean.TYPE);
        try {
            final Method findStaticMethod = findStaticMethod(Class.class, "forName0", String.class, Boolean.TYPE, ClassLoader.class);
            this.classFinder = new ClassFinder() { // from class: nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.1
                @Override // nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.ClassFinder
                public Class<?> findClass(String str) {
                    return (Class) findStaticMethod.invoke(null, str, Boolean.TRUE, Thread.currentThread().getContextClassLoader());
                }
            };
        } catch (Throwable unused) {
        }
        if (this.classFinder == null) {
            try {
                final Method findStaticMethod2 = findStaticMethod(Class.class, "forName0", String.class, Boolean.TYPE, ClassLoader.class, Class.class);
                this.classFinder = new ClassFinder() { // from class: nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.2
                    @Override // nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.ClassFinder
                    public Class<?> findClass(String str) {
                        return (Class) findStaticMethod2.invoke(null, str, Boolean.TRUE, Thread.currentThread().getContextClassLoader(), JVMDriverReflectionDriver.class);
                    }
                };
            } catch (Throwable unused2) {
            }
        }
        if (this.classFinder == null) {
            try {
                final Method findStaticMethod3 = findStaticMethod(Class.class, "forNameImpl", String.class, Boolean.TYPE, ClassLoader.class);
                this.classFinder = new ClassFinder() { // from class: nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.3
                    @Override // nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.ClassFinder
                    public Class<?> findClass(String str) {
                        return (Class) findStaticMethod3.invoke(null, str, Boolean.TRUE, Thread.currentThread().getContextClassLoader());
                    }
                };
            } catch (Throwable unused3) {
            }
        }
        if (this.classFinder == null) {
            final Method findStaticMethod4 = findStaticMethod(Class.class, "forName", String.class);
            this.classFinder = new ClassFinder() { // from class: nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.4
                @Override // nonapi.io.github.classgraph.reflection.JVMDriverReflectionDriver.ClassFinder
                public Class<?> findClass(String str) {
                    return (Class) findStaticMethod4.invoke(null, str);
                }
            };
        }
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public boolean makeAccessible(Object obj, AccessibleObject accessibleObject) {
        try {
            this.setAccessibleMethod.invoke(this.driver, accessibleObject, Boolean.TRUE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Class<?> findClass(String str) {
        return this.classFinder.findClass(str);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    Method[] getDeclaredMethods(Class<?> cls) {
        return (Method[]) this.getDeclaredMethods.invoke(this.driver, cls);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    <T> Constructor<T>[] getDeclaredConstructors(Class<T> cls) {
        return (Constructor[]) this.getDeclaredConstructors.invoke(this.driver, cls);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    Field[] getDeclaredFields(Class<?> cls) {
        return (Field[]) this.getDeclaredFields.invoke(this.driver, cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object getField(Object obj, Field field) {
        return this.getField.invoke(this.driver, obj, field);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    void setField(Object obj, Field field, Object obj2) {
        this.setField.invoke(this.driver, obj, field, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object getStaticField(Field field) {
        return this.getField.invoke(this.driver, null, field);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    void setStaticField(Field field, Object obj) {
        this.setField.invoke(this.driver, null, field, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object invokeMethod(Object obj, Method method, Object... objArr) {
        return this.invokeMethod.invoke(this.driver, obj, method, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object invokeStaticMethod(Method method, Object... objArr) {
        return this.invokeMethod.invoke(this.driver, null, method, objArr);
    }
}
