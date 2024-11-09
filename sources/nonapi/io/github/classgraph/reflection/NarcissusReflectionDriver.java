package nonapi.io.github.classgraph.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/reflection/NarcissusReflectionDriver.class */
class NarcissusReflectionDriver extends ReflectionDriver {
    private final Class<?> narcissusClass;
    private final Method getDeclaredMethods;
    private final Method findClass;
    private final Method getDeclaredConstructors;
    private final Method getDeclaredFields;
    private final Method getField;
    private final Method setField;
    private final Method getStaticField;
    private final Method setStaticField;
    private final Method invokeMethod;
    private final Method invokeStaticMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NarcissusReflectionDriver() {
        StandardReflectionDriver standardReflectionDriver = new StandardReflectionDriver();
        this.narcissusClass = standardReflectionDriver.findClass("io.github.toolfactory.narcissus.Narcissus");
        if (!((Boolean) standardReflectionDriver.getStaticField(standardReflectionDriver.findStaticField(this.narcissusClass, "libraryLoaded"))).booleanValue()) {
            throw new IllegalArgumentException("Could not load Narcissus native library");
        }
        this.findClass = standardReflectionDriver.findStaticMethod(this.narcissusClass, "findClass", String.class);
        this.getDeclaredMethods = standardReflectionDriver.findStaticMethod(this.narcissusClass, "getDeclaredMethods", Class.class);
        this.getDeclaredConstructors = standardReflectionDriver.findStaticMethod(this.narcissusClass, "getDeclaredConstructors", Class.class);
        this.getDeclaredFields = standardReflectionDriver.findStaticMethod(this.narcissusClass, "getDeclaredFields", Class.class);
        this.getField = standardReflectionDriver.findStaticMethod(this.narcissusClass, "getField", Object.class, Field.class);
        this.setField = standardReflectionDriver.findStaticMethod(this.narcissusClass, "setField", Object.class, Field.class, Object.class);
        this.getStaticField = standardReflectionDriver.findStaticMethod(this.narcissusClass, "getStaticField", Field.class);
        this.setStaticField = standardReflectionDriver.findStaticMethod(this.narcissusClass, "setStaticField", Field.class, Object.class);
        this.invokeMethod = standardReflectionDriver.findStaticMethod(this.narcissusClass, "invokeMethod", Object.class, Method.class, Object[].class);
        this.invokeStaticMethod = standardReflectionDriver.findStaticMethod(this.narcissusClass, "invokeStaticMethod", Method.class, Object[].class);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public boolean isAccessible(Object obj, AccessibleObject accessibleObject) {
        return true;
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public boolean makeAccessible(Object obj, AccessibleObject accessibleObject) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Class<?> findClass(String str) {
        return (Class) this.findClass.invoke(null, str);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    Method[] getDeclaredMethods(Class<?> cls) {
        return (Method[]) this.getDeclaredMethods.invoke(null, cls);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    <T> Constructor<T>[] getDeclaredConstructors(Class<T> cls) {
        return (Constructor[]) this.getDeclaredConstructors.invoke(null, cls);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    Field[] getDeclaredFields(Class<?> cls) {
        return (Field[]) this.getDeclaredFields.invoke(null, cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object getField(Object obj, Field field) {
        return this.getField.invoke(null, obj, field);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    void setField(Object obj, Field field, Object obj2) {
        this.setField.invoke(null, obj, field, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object getStaticField(Field field) {
        return this.getStaticField.invoke(null, field);
    }

    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    void setStaticField(Field field, Object obj) {
        this.setStaticField.invoke(null, field, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object invokeMethod(Object obj, Method method, Object... objArr) {
        return this.invokeMethod.invoke(null, obj, method, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // nonapi.io.github.classgraph.reflection.ReflectionDriver
    public Object invokeStaticMethod(Method method, Object... objArr) {
        return this.invokeStaticMethod.invoke(null, method, objArr);
    }
}
