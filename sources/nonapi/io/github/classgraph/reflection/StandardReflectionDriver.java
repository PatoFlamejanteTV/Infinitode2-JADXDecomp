package nonapi.io.github.classgraph.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/StandardReflectionDriver.class */
public class StandardReflectionDriver extends ReflectionDriver {
    private static Method setAccessibleMethod;
    private static Method trySetAccessibleMethod;
    private static Class<?> accessControllerClass;
    private static Class<?> privilegedActionClass;
    private static Method accessControllerDoPrivileged;

    static {
        try {
            setAccessibleMethod = AccessibleObject.class.getDeclaredMethod("setAccessible", Boolean.TYPE);
        } catch (Throwable unused) {
        }
        try {
            trySetAccessibleMethod = AccessibleObject.class.getDeclaredMethod("trySetAccessible", new Class[0]);
        } catch (Throwable unused2) {
        }
        try {
            accessControllerClass = Class.forName("java.security.AccessController");
            privilegedActionClass = Class.forName("java.security.PrivilegedAction");
            accessControllerDoPrivileged = accessControllerClass.getMethod("doPrivileged", privilegedActionClass);
        } catch (Throwable unused3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/StandardReflectionDriver$PrivilegedActionInvocationHandler.class */
    public class PrivilegedActionInvocationHandler<T> implements InvocationHandler {
        private final Callable<T> callable;

        public PrivilegedActionInvocationHandler(Callable<T> callable) {
            this.callable = callable;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            return this.callable.call();
        }
    }

    private <T> T doPrivileged(Callable<T> callable) {
        if (accessControllerDoPrivileged != null) {
            return (T) accessControllerDoPrivileged.invoke(null, Proxy.newProxyInstance(privilegedActionClass.getClassLoader(), new Class[]{privilegedActionClass}, new PrivilegedActionInvocationHandler(callable)));
        }
        return callable.call();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean tryMakeAccessible(AccessibleObject accessibleObject) {
        if (trySetAccessibleMethod != null) {
            try {
                return ((Boolean) trySetAccessibleMethod.invoke(accessibleObject, new Object[0])).booleanValue();
            } catch (Throwable unused) {
            }
        }
        if (setAccessibleMethod != null) {
            try {
                setAccessibleMethod.invoke(accessibleObject, Boolean.TRUE);
                return true;
            } catch (Throwable unused2) {
                return false;
            }
        }
        return false;
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public boolean makeAccessible(Object obj, final AccessibleObject accessibleObject) {
        if (isAccessible(obj, accessibleObject)) {
            return true;
        }
        try {
            return ((Boolean) doPrivileged(new Callable<Boolean>() { // from class: nonapi.io.github.classgraph.reflection.StandardReflectionDriver.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Boolean call() {
                    return Boolean.valueOf(StandardReflectionDriver.tryMakeAccessible(accessibleObject));
                }
            })).booleanValue();
        } catch (Throwable unused) {
            return tryMakeAccessible(accessibleObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Class<?> findClass(String str) {
        return Class.forName(str);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    Method[] getDeclaredMethods(Class<?> cls) {
        return cls.getDeclaredMethods();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public <T> Constructor<T>[] getDeclaredConstructors(Class<T> cls) {
        return cls.getDeclaredConstructors();
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    Field[] getDeclaredFields(Class<?> cls) {
        return cls.getDeclaredFields();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object getField(Object obj, Field field) {
        makeAccessible(obj, field);
        return field.get(obj);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    void setField(Object obj, Field field, Object obj2) {
        makeAccessible(obj, field);
        field.set(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object getStaticField(Field field) {
        makeAccessible(null, field);
        return field.get(null);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    void setStaticField(Field field, Object obj) {
        makeAccessible(null, field);
        field.set(null, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object invokeMethod(Object obj, Method method, Object... objArr) {
        makeAccessible(obj, method);
        return method.invoke(obj, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object invokeStaticMethod(Method method, Object... objArr) {
        makeAccessible(null, method);
        return method.invoke(null, objArr);
    }
}
