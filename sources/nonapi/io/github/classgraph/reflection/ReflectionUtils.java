package nonapi.io.github.classgraph.reflection;

import io.github.classgraph.ClassGraph;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/ReflectionUtils.class */
public final class ReflectionUtils {
    public ReflectionDriver reflectionDriver;
    private Class<?> accessControllerClass;
    private Class<?> privilegedActionClass;
    private Method accessControllerDoPrivileged;

    public ReflectionUtils() {
        if (ClassGraph.CIRCUMVENT_ENCAPSULATION == ClassGraph.CircumventEncapsulationMethod.NARCISSUS) {
            try {
                this.reflectionDriver = new NarcissusReflectionDriver();
            } catch (Throwable th) {
                System.err.println("Could not load Narcissus reflection driver: " + th);
            }
        } else if (ClassGraph.CIRCUMVENT_ENCAPSULATION == ClassGraph.CircumventEncapsulationMethod.JVM_DRIVER) {
            try {
                this.reflectionDriver = new JVMDriverReflectionDriver();
            } catch (Throwable th2) {
                System.err.println("Could not load JVM-Driver reflection driver: " + th2);
            }
        }
        if (this.reflectionDriver == null) {
            this.reflectionDriver = new StandardReflectionDriver();
        }
        try {
            this.accessControllerClass = this.reflectionDriver.findClass("java.security.AccessController");
            this.privilegedActionClass = this.reflectionDriver.findClass("java.security.PrivilegedAction");
            this.accessControllerDoPrivileged = this.reflectionDriver.findMethod(this.accessControllerClass, null, "doPrivileged", this.privilegedActionClass);
        } catch (Throwable unused) {
        }
    }

    public final Object getFieldVal(boolean z, Object obj, Field field) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        if (obj == null || field == null) {
            if (z) {
                throw new NullPointerException();
            }
            return null;
        }
        try {
            return this.reflectionDriver.getField(obj, field);
        } catch (Throwable th) {
            if (z) {
                throw new IllegalArgumentException("Can't read field " + obj.getClass().getName() + "." + field.getName(), th);
            }
            return null;
        }
    }

    public final Object getFieldVal(boolean z, Object obj, String str) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        if (obj == null || str == null) {
            if (z) {
                throw new NullPointerException();
            }
            return null;
        }
        try {
            return this.reflectionDriver.getField(obj, this.reflectionDriver.findInstanceField(obj, str));
        } catch (Throwable th) {
            if (z) {
                throw new IllegalArgumentException("Can't read field " + obj.getClass().getName() + "." + str, th);
            }
            return null;
        }
    }

    public final Object getStaticFieldVal(boolean z, Class<?> cls, String str) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        if (cls == null || str == null) {
            if (z) {
                throw new NullPointerException();
            }
            return null;
        }
        try {
            return this.reflectionDriver.getStaticField(this.reflectionDriver.findStaticField(cls, str));
        } catch (Throwable th) {
            if (z) {
                throw new IllegalArgumentException("Can't read field " + cls.getName() + "." + str, th);
            }
            return null;
        }
    }

    public final Object invokeMethod(boolean z, Object obj, String str) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        if (obj == null || str == null) {
            if (z) {
                throw new IllegalArgumentException("Unexpected null argument");
            }
            return null;
        }
        try {
            return this.reflectionDriver.invokeMethod(obj, this.reflectionDriver.findInstanceMethod(obj, str, new Class[0]), new Object[0]);
        } catch (Throwable th) {
            if (z) {
                throw new IllegalArgumentException("Method \"" + str + "\" could not be invoked", th);
            }
            return null;
        }
    }

    public final Object invokeMethod(boolean z, Object obj, String str, Class<?> cls, Object obj2) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        if (obj == null || str == null || cls == null) {
            if (z) {
                throw new IllegalArgumentException("Unexpected null argument");
            }
            return null;
        }
        try {
            return this.reflectionDriver.invokeMethod(obj, this.reflectionDriver.findInstanceMethod(obj, str, cls), obj2);
        } catch (Throwable th) {
            if (z) {
                throw new IllegalArgumentException("Method \"" + str + "\" could not be invoked", th);
            }
            return null;
        }
    }

    public final Object invokeStaticMethod(boolean z, Class<?> cls, String str) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        if (cls == null || str == null) {
            if (z) {
                throw new IllegalArgumentException("Unexpected null argument");
            }
            return null;
        }
        try {
            return this.reflectionDriver.invokeStaticMethod(this.reflectionDriver.findStaticMethod(cls, str, new Class[0]), new Object[0]);
        } catch (Throwable th) {
            if (z) {
                throw new IllegalArgumentException("Method \"" + str + "\" could not be invoked", th);
            }
            return null;
        }
    }

    public final Object invokeStaticMethod(boolean z, Class<?> cls, String str, Class<?> cls2, Object obj) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        if (cls == null || str == null || cls2 == null) {
            if (z) {
                throw new IllegalArgumentException("Unexpected null argument");
            }
            return null;
        }
        try {
            return this.reflectionDriver.invokeStaticMethod(this.reflectionDriver.findStaticMethod(cls, str, cls2), obj);
        } catch (Throwable th) {
            if (z) {
                throw new IllegalArgumentException("Fethod \"" + str + "\" could not be invoked", th);
            }
            return null;
        }
    }

    public final Class<?> classForNameOrNull(String str) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        try {
            return this.reflectionDriver.findClass(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final Method staticMethodForNameOrNull(String str, String str2) {
        if (this.reflectionDriver == null) {
            throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
        }
        try {
            return this.reflectionDriver.findStaticMethod(this.reflectionDriver.findClass(str), str2, new Class[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/ReflectionUtils$PrivilegedActionInvocationHandler.class */
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

    public final <T> T doPrivileged(Callable<T> callable) {
        if (this.accessControllerDoPrivileged != null) {
            return (T) this.accessControllerDoPrivileged.invoke(null, Proxy.newProxyInstance(this.privilegedActionClass.getClassLoader(), new Class[]{this.privilegedActionClass}, new PrivilegedActionInvocationHandler(callable)));
        }
        return callable.call();
    }
}
