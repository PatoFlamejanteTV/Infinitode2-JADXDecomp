package net.bytebuddy.utility.dispatcher;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.utility.GraalImageCode;
import net.bytebuddy.utility.Invoker;
import net.bytebuddy.utility.MethodComparator;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher.class */
public class JavaDispatcher<T> implements PrivilegedAction<T> {
    public static final String GENERATE_PROPERTY = "net.bytebuddy.generate";
    private static final boolean GENERATE;
    private static final DynamicClassLoader.Resolver RESOLVER;
    private static final Invoker INVOKER;
    private final Class<T> proxy;

    @MaybeNull
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
    private final ClassLoader classLoader;
    private final boolean generate;
    private static final boolean ACCESS_CONTROLLER;

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Container.class */
    public @interface Container {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Defaults.class */
    public @interface Defaults {
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Instance.class */
    public @interface Instance {
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$IsConstructor.class */
    public @interface IsConstructor {
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$IsStatic.class */
    public @interface IsStatic {
    }

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Proxied.class */
    public @interface Proxied {
        String value();
    }

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || this.generate != ((JavaDispatcher) obj).generate || !this.proxy.equals(((JavaDispatcher) obj).proxy)) {
            return false;
        }
        ClassLoader classLoader = this.classLoader;
        ClassLoader classLoader2 = ((JavaDispatcher) obj).classLoader;
        return classLoader2 != null ? classLoader != null && classLoader.equals(classLoader2) : classLoader == null;
    }

    public int hashCode() {
        int hashCode = ((getClass().hashCode() * 31) + this.proxy.hashCode()) * 31;
        ClassLoader classLoader = this.classLoader;
        if (classLoader != null) {
            hashCode += classLoader.hashCode();
        }
        return (hashCode * 31) + (this.generate ? 1 : 0);
    }

    static {
        try {
            Class.forName("java.security.AccessController", false, null);
            ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
        } catch (ClassNotFoundException unused) {
            ACCESS_CONTROLLER = false;
        } catch (SecurityException unused2) {
            ACCESS_CONTROLLER = true;
        }
        GENERATE = Boolean.parseBoolean((String) doPrivileged(new GetSystemPropertyAction(GENERATE_PROPERTY)));
        RESOLVER = (DynamicClassLoader.Resolver) doPrivileged(DynamicClassLoader.Resolver.CreationAction.INSTANCE);
        INVOKER = (Invoker) doPrivileged(new InvokerCreationAction((byte) 0));
    }

    protected JavaDispatcher(Class<T> cls, @MaybeNull ClassLoader classLoader, boolean z) {
        this.proxy = cls;
        this.classLoader = classLoader;
        this.generate = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AccessControllerPlugin.Enhance
    public static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    public static <T> PrivilegedAction<T> of(Class<T> cls) {
        return of(cls, null);
    }

    protected static <T> PrivilegedAction<T> of(Class<T> cls, @MaybeNull ClassLoader classLoader) {
        return of(cls, classLoader, GENERATE);
    }

    protected static <T> PrivilegedAction<T> of(Class<T> cls, @MaybeNull ClassLoader classLoader, boolean z) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("Expected an interface instead of " + cls);
        }
        if (!cls.isAnnotationPresent(Proxied.class)) {
            throw new IllegalArgumentException("Expected " + cls.getName() + " to be annotated with " + Proxied.class.getName());
        }
        if (((Proxied) cls.getAnnotation(Proxied.class)).value().startsWith("java.security.")) {
            throw new IllegalArgumentException("Classes related to Java security cannot be proxied: " + cls.getName());
        }
        return new JavaDispatcher(cls, classLoader, z);
    }

    /* JADX WARN: Type inference failed for: r0v273, types: [java.lang.Object, java.lang.reflect.InvocationTargetException] */
    @Override // java.security.PrivilegedAction
    public T run() {
        Method[] methods;
        Object of;
        Method[] methods2;
        Object of2;
        Object of3;
        int i;
        ?? invoke;
        try {
            Object invoke2 = System.class.getMethod("getSecurityManager", new Class[0]).invoke(null, new Object[0]);
            if (invoke2 != null) {
                invoke = Class.forName("java.lang.SecurityManager").getMethod("checkPermission", Permission.class).invoke(invoke2, new RuntimePermission("net.bytebuddy.createJavaDispatcher"));
            }
        } catch (ClassNotFoundException unused) {
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to access security manager", e);
        } catch (NoSuchMethodException unused2) {
        } catch (InvocationTargetException e2) {
            Throwable targetException = invoke.getTargetException();
            if (!(targetException instanceof RuntimeException)) {
                throw new IllegalStateException("Failed to assert access rights using security manager", targetException);
            }
            throw ((RuntimeException) targetException);
        }
        Map linkedHashMap = this.generate ? new LinkedHashMap() : new HashMap();
        boolean isAnnotationPresent = this.proxy.isAnnotationPresent(Defaults.class);
        String value = ((Proxied) this.proxy.getAnnotation(Proxied.class)).value();
        try {
            Class<?> cls = Class.forName(value, false, this.classLoader);
            boolean z = this.generate;
            boolean z2 = z;
            if (z) {
                methods2 = (Method[]) GraalImageCode.getCurrent().sorted(this.proxy.getMethods(), MethodComparator.INSTANCE);
            } else {
                methods2 = this.proxy.getMethods();
            }
            Method[] methodArr = methods2;
            int length = methods2.length;
            for (int i2 = 0; i2 < length; i2++) {
                Method method = methodArr[i2];
                if (method.getDeclaringClass() != Object.class) {
                    if (method.isAnnotationPresent(Instance.class)) {
                        if (method.getParameterTypes().length != 1 || !method.getParameterTypes()[0].isAssignableFrom(cls)) {
                            throw new IllegalStateException("Instance check requires a single regular-typed argument: " + method);
                        }
                        if (method.getReturnType() != Boolean.TYPE) {
                            throw new IllegalStateException("Instance check requires a boolean return type: " + method);
                        }
                        linkedHashMap.put(method, new Dispatcher.ForInstanceCheck(cls));
                    } else if (method.isAnnotationPresent(Container.class)) {
                        if (method.getParameterTypes().length != 1 || method.getParameterTypes()[0] != Integer.TYPE) {
                            throw new IllegalStateException("Container creation requires a single int-typed argument: " + method);
                        }
                        if (!method.getReturnType().isArray() || !method.getReturnType().getComponentType().isAssignableFrom(cls)) {
                            throw new IllegalStateException("Container creation requires an assignable array as return value: " + method);
                        }
                        linkedHashMap.put(method, new Dispatcher.ForContainerCreation(cls));
                    } else {
                        if (cls.getName().equals("java.lang.invoke.MethodHandles") && method.getName().equals("lookup")) {
                            throw new UnsupportedOperationException("Cannot resolve Byte Buddy lookup via dispatcher");
                        }
                        try {
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            if (method.isAnnotationPresent(IsStatic.class) || method.isAnnotationPresent(IsConstructor.class)) {
                                i = 0;
                            } else {
                                i = 1;
                                if (parameterTypes.length == 0) {
                                    throw new IllegalStateException("Expected self type: " + method);
                                }
                                if (!parameterTypes[0].isAssignableFrom(cls)) {
                                    throw new IllegalStateException("Cannot assign self type: " + cls + " on " + method);
                                }
                                Class<?>[] clsArr = new Class[parameterTypes.length - 1];
                                System.arraycopy(parameterTypes, 1, clsArr, 0, clsArr.length);
                                parameterTypes = clsArr;
                            }
                            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                            for (int i3 = 0; i3 < parameterTypes.length; i3++) {
                                Annotation[] annotationArr = parameterAnnotations[i3 + i];
                                int length2 = annotationArr.length;
                                int i4 = 0;
                                while (true) {
                                    if (i4 < length2) {
                                        Annotation annotation = annotationArr[i4];
                                        if (!(annotation instanceof Proxied)) {
                                            i4++;
                                        } else {
                                            int i5 = 0;
                                            while (parameterTypes[i3].isArray()) {
                                                i5++;
                                                parameterTypes[i3] = parameterTypes[i3].getComponentType();
                                            }
                                            if (i5 > 0) {
                                                if (!parameterTypes[i3].isPrimitive()) {
                                                    if (!parameterTypes[i3].isAssignableFrom(Class.forName(((Proxied) annotation).value(), false, this.classLoader))) {
                                                        throw new IllegalStateException("Cannot resolve to component type: " + ((Proxied) annotation).value() + " at " + i3 + " of " + method);
                                                    }
                                                    StringBuilder sb = new StringBuilder();
                                                    while (true) {
                                                        int i6 = i5;
                                                        i5--;
                                                        if (i6 <= 0) {
                                                            break;
                                                        }
                                                        sb.append('[');
                                                    }
                                                    parameterTypes[i3] = Class.forName(sb.append('L').append(((Proxied) annotation).value()).append(';').toString(), false, this.classLoader);
                                                } else {
                                                    throw new IllegalStateException("Primitive values are not supposed to be proxied: " + i3 + " of " + method);
                                                }
                                            } else {
                                                Class<?> cls2 = Class.forName(((Proxied) annotation).value(), false, this.classLoader);
                                                if (!parameterTypes[i3].isAssignableFrom(cls2)) {
                                                    throw new IllegalStateException("Cannot resolve to type: " + cls2.getName() + " at " + i3 + " of " + method);
                                                }
                                                parameterTypes[i3] = cls2;
                                            }
                                        }
                                    }
                                }
                            }
                            if (method.isAnnotationPresent(IsConstructor.class)) {
                                Constructor<?> constructor = cls.getConstructor(parameterTypes);
                                if (!method.getReturnType().isAssignableFrom(cls)) {
                                    throw new IllegalStateException("Cannot assign " + constructor.getDeclaringClass().getName() + " to " + method);
                                }
                                if ((constructor.getModifiers() & 1) == 0 || (cls.getModifiers() & 1) == 0) {
                                    constructor.setAccessible(true);
                                    z2 = false;
                                }
                                linkedHashMap.put(method, new Dispatcher.ForConstructor(constructor));
                            } else {
                                Proxied proxied = (Proxied) method.getAnnotation(Proxied.class);
                                Method method2 = cls.getMethod(proxied == null ? method.getName() : proxied.value(), parameterTypes);
                                if (!method.getReturnType().isAssignableFrom(method2.getReturnType())) {
                                    throw new IllegalStateException("Cannot assign " + method2.getReturnType().getName() + " to " + method);
                                }
                                for (Class<?> cls3 : method2.getExceptionTypes()) {
                                    if (!RuntimeException.class.isAssignableFrom(cls3) && !Error.class.isAssignableFrom(cls3)) {
                                        for (Class<?> cls4 : method.getExceptionTypes()) {
                                            int i7 = cls4.isAssignableFrom(cls3) ? 0 : i7 + 1;
                                        }
                                        throw new IllegalStateException("Resolved method for " + method + " throws undeclared checked exception " + cls3.getName());
                                    }
                                }
                                if ((method2.getModifiers() & 1) == 0 || (method2.getDeclaringClass().getModifiers() & 1) == 0) {
                                    method2.setAccessible(true);
                                    z2 = false;
                                }
                                if (Modifier.isStatic(method2.getModifiers())) {
                                    if (!method.isAnnotationPresent(IsStatic.class)) {
                                        throw new IllegalStateException("Resolved method for " + method + " was expected to be static: " + method2);
                                    }
                                    linkedHashMap.put(method, new Dispatcher.ForStaticMethod(method2));
                                } else {
                                    if (method.isAnnotationPresent(IsStatic.class)) {
                                        throw new IllegalStateException("Resolved method for " + method + " was expected to be virtual: " + method2);
                                    }
                                    linkedHashMap.put(method, new Dispatcher.ForNonStaticMethod(method2));
                                }
                            }
                        } catch (ClassNotFoundException e3) {
                            if (isAnnotationPresent || method.isAnnotationPresent(Defaults.class)) {
                                of3 = Dispatcher.ForDefaultValue.of(method.getReturnType());
                            } else {
                                of3 = new Dispatcher.ForUnresolvedMethod("Class not available on current VM: " + e3.getMessage());
                            }
                            linkedHashMap.put(method, of3);
                        } catch (NoSuchMethodException e4) {
                            if (isAnnotationPresent || method.isAnnotationPresent(Defaults.class)) {
                                of2 = Dispatcher.ForDefaultValue.of(method.getReturnType());
                            } else {
                                of2 = new Dispatcher.ForUnresolvedMethod("Method not available on current VM: " + e4.getMessage());
                            }
                            linkedHashMap.put(method, of2);
                        } catch (Throwable th) {
                            linkedHashMap.put(method, new Dispatcher.ForUnresolvedMethod("Unexpected error: " + th.getMessage()));
                        }
                    }
                }
            }
            if (z2) {
                return (T) DynamicClassLoader.proxy(this.proxy, linkedHashMap);
            }
            return (T) Proxy.newProxyInstance(this.proxy.getClassLoader(), new Class[]{this.proxy}, new ProxiedInvocationHandler(cls.getName(), linkedHashMap));
        } catch (ClassNotFoundException e5) {
            if (this.generate) {
                methods = (Method[]) GraalImageCode.getCurrent().sorted(this.proxy.getMethods(), MethodComparator.INSTANCE);
            } else {
                methods = this.proxy.getMethods();
            }
            Method[] methodArr2 = methods;
            int length3 = methods.length;
            for (int i8 = 0; i8 < length3; i8++) {
                Method method3 = methodArr2[i8];
                if (method3.getDeclaringClass() != Object.class) {
                    if (!method3.isAnnotationPresent(Instance.class)) {
                        if (isAnnotationPresent || method3.isAnnotationPresent(Defaults.class)) {
                            of = Dispatcher.ForDefaultValue.of(method3.getReturnType());
                        } else {
                            of = new Dispatcher.ForUnresolvedMethod("Type not available on current VM: " + e5.getMessage());
                        }
                        linkedHashMap.put(method3, of);
                    } else {
                        if (method3.getParameterTypes().length != 1 || method3.getParameterTypes()[0].isPrimitive() || method3.getParameterTypes()[0].isArray()) {
                            throw new IllegalStateException("Instance check requires a single regular-typed argument: " + method3);
                        }
                        if (method3.getReturnType() != Boolean.TYPE) {
                            throw new IllegalStateException("Instance check requires a boolean return type: " + method3);
                        }
                        linkedHashMap.put(method3, Dispatcher.ForDefaultValue.BOOLEAN);
                    }
                }
            }
            return this.generate ? (T) DynamicClassLoader.proxy(this.proxy, linkedHashMap) : (T) Proxy.newProxyInstance(this.proxy.getClassLoader(), new Class[]{this.proxy}, new ProxiedInvocationHandler(value, linkedHashMap));
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$InvokerCreationAction.class */
    private static class InvokerCreationAction implements PrivilegedAction<Invoker> {
        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return getClass().hashCode();
        }

        private InvokerCreationAction() {
        }

        /* synthetic */ InvokerCreationAction(byte b2) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Invoker run() {
            return DynamicClassLoader.invoker();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$DirectInvoker.class */
    public static class DirectInvoker implements Invoker {
        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return getClass().hashCode();
        }

        private DirectInvoker() {
        }

        /* synthetic */ DirectInvoker(byte b2) {
            this();
        }

        @Override // net.bytebuddy.utility.Invoker
        public Object newInstance(Constructor<?> constructor, Object[] objArr) {
            return constructor.newInstance(objArr);
        }

        @Override // net.bytebuddy.utility.Invoker
        public Object invoke(Method method, @MaybeNull Object obj, @MaybeNull Object[] objArr) {
            return method.invoke(obj, objArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher.class */
    public interface Dispatcher {
        @MaybeNull
        Object invoke(Object[] objArr);

        int apply(MethodVisitor methodVisitor, Method method);

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForInstanceCheck.class */
        public static class ForInstanceCheck implements Dispatcher {
            private final Class<?> target;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.target.equals(((ForInstanceCheck) obj).target);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.target.hashCode();
            }

            protected ForInstanceCheck(Class<?> cls) {
                this.target = cls;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public Object invoke(Object[] objArr) {
                return Boolean.valueOf(this.target.isInstance(objArr[0]));
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public int apply(MethodVisitor methodVisitor, Method method) {
                methodVisitor.visitVarInsn(25, 1);
                methodVisitor.visitTypeInsn(193, Type.getInternalName(this.target));
                methodVisitor.visitInsn(172);
                return 1;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForContainerCreation.class */
        public static class ForContainerCreation implements Dispatcher {
            private final Class<?> target;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.target.equals(((ForContainerCreation) obj).target);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.target.hashCode();
            }

            protected ForContainerCreation(Class<?> cls) {
                this.target = cls;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public Object invoke(Object[] objArr) {
                return Array.newInstance(this.target, ((Integer) objArr[0]).intValue());
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public int apply(MethodVisitor methodVisitor, Method method) {
                methodVisitor.visitVarInsn(21, 1);
                methodVisitor.visitTypeInsn(189, Type.getInternalName(this.target));
                methodVisitor.visitInsn(176);
                return 1;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForDefaultValue.class */
        public enum ForDefaultValue implements Dispatcher {
            VOID(null, 0, 177, 0),
            BOOLEAN(Boolean.FALSE, 3, 172, 1),
            BOOLEAN_REVERSE(Boolean.TRUE, 4, 172, 1),
            BYTE((byte) 0, 3, 172, 1),
            SHORT((short) 0, 3, 172, 1),
            CHARACTER((char) 0, 3, 172, 1),
            INTEGER(0, 3, 172, 1),
            LONG(0L, 9, 173, 2),
            FLOAT(Float.valueOf(0.0f), 11, 174, 1),
            DOUBLE(Double.valueOf(0.0d), 14, 175, 2),
            REFERENCE(null, 1, 176, 1);


            @MaybeNull
            private final Object value;
            private final int load;
            private final int returned;
            private final int size;

            ForDefaultValue(@MaybeNull Object obj, int i, int i2, int i3) {
                this.value = obj;
                this.load = i;
                this.returned = i2;
                this.size = i3;
            }

            protected static Dispatcher of(Class<?> cls) {
                if (cls == Void.TYPE) {
                    return VOID;
                }
                if (cls == Boolean.TYPE) {
                    return BOOLEAN;
                }
                if (cls == Byte.TYPE) {
                    return BYTE;
                }
                if (cls == Short.TYPE) {
                    return SHORT;
                }
                if (cls == Character.TYPE) {
                    return CHARACTER;
                }
                if (cls == Integer.TYPE) {
                    return INTEGER;
                }
                if (cls == Long.TYPE) {
                    return LONG;
                }
                if (cls == Float.TYPE) {
                    return FLOAT;
                }
                if (cls == Double.TYPE) {
                    return DOUBLE;
                }
                if (cls.isArray()) {
                    if (cls.getComponentType() == Boolean.TYPE) {
                        return OfPrimitiveArray.BOOLEAN;
                    }
                    if (cls.getComponentType() == Byte.TYPE) {
                        return OfPrimitiveArray.BYTE;
                    }
                    if (cls.getComponentType() == Short.TYPE) {
                        return OfPrimitiveArray.SHORT;
                    }
                    if (cls.getComponentType() == Character.TYPE) {
                        return OfPrimitiveArray.CHARACTER;
                    }
                    if (cls.getComponentType() == Integer.TYPE) {
                        return OfPrimitiveArray.INTEGER;
                    }
                    if (cls.getComponentType() == Long.TYPE) {
                        return OfPrimitiveArray.LONG;
                    }
                    if (cls.getComponentType() == Float.TYPE) {
                        return OfPrimitiveArray.FLOAT;
                    }
                    if (cls.getComponentType() == Double.TYPE) {
                        return OfPrimitiveArray.DOUBLE;
                    }
                    return OfNonPrimitiveArray.of(cls.getComponentType());
                }
                return REFERENCE;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            @MaybeNull
            public final Object invoke(Object[] objArr) {
                return this.value;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public final int apply(MethodVisitor methodVisitor, Method method) {
                if (this.load != 0) {
                    methodVisitor.visitInsn(this.load);
                }
                methodVisitor.visitInsn(this.returned);
                return this.size;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForDefaultValue$OfPrimitiveArray.class */
            public enum OfPrimitiveArray implements Dispatcher {
                BOOLEAN(new boolean[0], 4),
                BYTE(new byte[0], 8),
                SHORT(new short[0], 9),
                CHARACTER(new char[0], 5),
                INTEGER(new int[0], 10),
                LONG(new long[0], 11),
                FLOAT(new float[0], 6),
                DOUBLE(new double[0], 7);

                private final Object value;
                private final int operand;

                OfPrimitiveArray(Object obj, int i) {
                    this.value = obj;
                    this.operand = i;
                }

                @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
                public final Object invoke(Object[] objArr) {
                    return this.value;
                }

                @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
                public final int apply(MethodVisitor methodVisitor, Method method) {
                    methodVisitor.visitInsn(3);
                    methodVisitor.visitIntInsn(188, this.operand);
                    methodVisitor.visitInsn(176);
                    return 1;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForDefaultValue$OfNonPrimitiveArray.class */
            public static class OfNonPrimitiveArray implements Dispatcher {

                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
                private final Object value;
                private final Class<?> componentType;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.componentType.equals(((OfNonPrimitiveArray) obj).componentType);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.componentType.hashCode();
                }

                protected OfNonPrimitiveArray(Object obj, Class<?> cls) {
                    this.value = obj;
                    this.componentType = cls;
                }

                protected static Dispatcher of(Class<?> cls) {
                    return new OfNonPrimitiveArray(Array.newInstance(cls, 0), cls);
                }

                @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
                public Object invoke(Object[] objArr) {
                    return this.value;
                }

                @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
                public int apply(MethodVisitor methodVisitor, Method method) {
                    methodVisitor.visitInsn(3);
                    methodVisitor.visitTypeInsn(189, Type.getInternalName(this.componentType));
                    methodVisitor.visitInsn(176);
                    return 1;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForConstructor.class */
        public static class ForConstructor implements Dispatcher {
            private final Constructor<?> constructor;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.constructor.equals(((ForConstructor) obj).constructor);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.constructor.hashCode();
            }

            protected ForConstructor(Constructor<?> constructor) {
                this.constructor = constructor;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public Object invoke(Object[] objArr) {
                return JavaDispatcher.INVOKER.newInstance(this.constructor, objArr);
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public int apply(MethodVisitor methodVisitor, Method method) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?>[] parameterTypes2 = this.constructor.getParameterTypes();
                methodVisitor.visitTypeInsn(187, Type.getInternalName(this.constructor.getDeclaringClass()));
                methodVisitor.visitInsn(89);
                int i = 1;
                for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                    Type type = Type.getType(parameterTypes[i2]);
                    methodVisitor.visitVarInsn(type.getOpcode(21), i);
                    if (parameterTypes[i2] != parameterTypes2[i2]) {
                        methodVisitor.visitTypeInsn(192, Type.getInternalName(parameterTypes2[i2]));
                    }
                    i += type.getSize();
                }
                methodVisitor.visitMethodInsn(183, Type.getInternalName(this.constructor.getDeclaringClass()), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getConstructorDescriptor(this.constructor), false);
                methodVisitor.visitInsn(176);
                return i + 1;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForStaticMethod.class */
        public static class ForStaticMethod implements Dispatcher {
            private final Method method;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.method.equals(((ForStaticMethod) obj).method);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.method.hashCode();
            }

            protected ForStaticMethod(Method method) {
                this.method = method;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            @MaybeNull
            public Object invoke(Object[] objArr) {
                return JavaDispatcher.INVOKER.invoke(this.method, null, objArr);
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public int apply(MethodVisitor methodVisitor, Method method) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?>[] parameterTypes2 = this.method.getParameterTypes();
                int i = 1;
                for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                    Type type = Type.getType(parameterTypes[i2]);
                    methodVisitor.visitVarInsn(type.getOpcode(21), i);
                    if (parameterTypes[i2] != parameterTypes2[i2]) {
                        methodVisitor.visitTypeInsn(192, Type.getInternalName(parameterTypes2[i2]));
                    }
                    i += type.getSize();
                }
                methodVisitor.visitMethodInsn(184, Type.getInternalName(this.method.getDeclaringClass()), this.method.getName(), Type.getMethodDescriptor(this.method), this.method.getDeclaringClass().isInterface());
                methodVisitor.visitInsn(Type.getReturnType(this.method).getOpcode(172));
                return Math.max(i - 1, Type.getReturnType(this.method).getSize());
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForNonStaticMethod.class */
        public static class ForNonStaticMethod implements Dispatcher {
            private static final Object[] NO_ARGUMENTS = new Object[0];
            private final Method method;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.method.equals(((ForNonStaticMethod) obj).method);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.method.hashCode();
            }

            protected ForNonStaticMethod(Method method) {
                this.method = method;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public Object invoke(Object[] objArr) {
                Object[] objArr2;
                if (objArr.length == 1) {
                    objArr2 = NO_ARGUMENTS;
                } else {
                    objArr2 = new Object[objArr.length - 1];
                    System.arraycopy(objArr, 1, objArr2, 0, objArr2.length);
                }
                return JavaDispatcher.INVOKER.invoke(this.method, objArr[0], objArr2);
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public int apply(MethodVisitor methodVisitor, Method method) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?>[] parameterTypes2 = this.method.getParameterTypes();
                int i = 1;
                int i2 = 0;
                while (i2 < parameterTypes.length) {
                    Type type = Type.getType(parameterTypes[i2]);
                    methodVisitor.visitVarInsn(type.getOpcode(21), i);
                    if (parameterTypes[i2] != (i2 == 0 ? this.method.getDeclaringClass() : parameterTypes2[i2 - 1])) {
                        methodVisitor.visitTypeInsn(192, Type.getInternalName(i2 == 0 ? this.method.getDeclaringClass() : parameterTypes2[i2 - 1]));
                    }
                    i += type.getSize();
                    i2++;
                }
                methodVisitor.visitMethodInsn(this.method.getDeclaringClass().isInterface() ? 185 : 182, Type.getInternalName(this.method.getDeclaringClass()), this.method.getName(), Type.getMethodDescriptor(this.method), this.method.getDeclaringClass().isInterface());
                methodVisitor.visitInsn(Type.getReturnType(this.method).getOpcode(172));
                return Math.max(i - 1, Type.getReturnType(this.method).getSize());
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$Dispatcher$ForUnresolvedMethod.class */
        public static class ForUnresolvedMethod implements Dispatcher {
            private final String message;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.message.equals(((ForUnresolvedMethod) obj).message);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.message.hashCode();
            }

            protected ForUnresolvedMethod(String str) {
                this.message = str;
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public Object invoke(Object[] objArr) {
                throw new IllegalStateException("Could not invoke proxy: " + this.message);
            }

            @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.Dispatcher
            public int apply(MethodVisitor methodVisitor, Method method) {
                methodVisitor.visitTypeInsn(187, Type.getInternalName(IllegalStateException.class));
                methodVisitor.visitInsn(89);
                methodVisitor.visitLdcInsn(this.message);
                methodVisitor.visitMethodInsn(183, Type.getInternalName(IllegalStateException.class), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType((Class<?>) String.class)), false);
                methodVisitor.visitInsn(191);
                return 3;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$ProxiedInvocationHandler.class */
    protected static class ProxiedInvocationHandler implements InvocationHandler {
        private static final Object[] NO_ARGUMENTS = new Object[0];
        private final String name;
        private final Map<Method, Dispatcher> targets;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.name.equals(((ProxiedInvocationHandler) obj).name) && this.targets.equals(((ProxiedInvocationHandler) obj).targets);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.name.hashCode()) * 31) + this.targets.hashCode();
        }

        protected ProxiedInvocationHandler(String str, Map<Method, Dispatcher> map) {
            this.name = str;
            this.targets = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.reflect.InvocationTargetException] */
        /* JADX WARN: Type inference failed for: r0v5, types: [net.bytebuddy.utility.dispatcher.JavaDispatcher$Dispatcher] */
        @Override // java.lang.reflect.InvocationHandler
        @MaybeNull
        public Object invoke(Object obj, Method method, @MaybeNull Object[] objArr) {
            if (method.getDeclaringClass() == Object.class) {
                if (method.getName().equals("hashCode")) {
                    return Integer.valueOf(hashCode());
                }
                if (method.getName().equals("equals")) {
                    return Boolean.valueOf(objArr[0] != null && Proxy.isProxyClass(objArr[0].getClass()) && Proxy.getInvocationHandler(objArr[0]).equals(this));
                }
                if (method.getName().equals("toString")) {
                    return "Call proxy for " + this.name;
                }
                throw new IllegalStateException("Unexpected object method: " + method);
            }
            Dispatcher dispatcher = this.targets.get(method);
            try {
                dispatcher = dispatcher;
                try {
                    if (dispatcher == 0) {
                        throw new IllegalStateException("No proxy target found for " + method);
                    }
                    return dispatcher.invoke(objArr == null ? NO_ARGUMENTS : objArr);
                } catch (InvocationTargetException e) {
                    throw dispatcher.getTargetException();
                }
            } catch (Error e2) {
                throw dispatcher;
            } catch (RuntimeException e3) {
                throw dispatcher;
            } catch (Throwable th) {
                for (Class<?> cls : method.getExceptionTypes()) {
                    if (cls.isInstance(th)) {
                        throw th;
                    }
                }
                throw new IllegalStateException("Failed to invoke proxy for " + method, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$DynamicClassLoader.class */
    public static class DynamicClassLoader extends ClassLoader {

        @MaybeNull
        private static final String DUMP_FOLDER;
        private static final Class<?>[] NO_PARAMETER = new Class[0];
        private static final Object[] NO_ARGUMENT = new Object[0];

        static {
            String str;
            try {
                str = (String) JavaDispatcher.doPrivileged(new GetSystemPropertyAction(TypeWriter.DUMP_PROPERTY));
            } catch (Throwable unused) {
                str = null;
            }
            DUMP_FOLDER = str;
        }

        protected DynamicClassLoader(Class<?> cls) {
            super(cls.getClassLoader());
            JavaDispatcher.RESOLVER.accept(this, cls);
        }

        @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION", "DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED"}, justification = "Expected internal invocation.")
        protected static Object proxy(Class<?> cls, Map<Method, Dispatcher> map) {
            ClassWriter classWriter = new ClassWriter(0);
            classWriter.visit(ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V5).getMinorMajorVersion(), 1, Type.getInternalName(cls) + "$Proxy", null, Type.getInternalName(Object.class), new String[]{Type.getInternalName(cls)});
            for (Map.Entry<Method, Dispatcher> entry : map.entrySet()) {
                Class<?>[] exceptionTypes = entry.getKey().getExceptionTypes();
                String[] strArr = new String[exceptionTypes.length];
                for (int i = 0; i < exceptionTypes.length; i++) {
                    strArr[i] = Type.getInternalName(exceptionTypes[i]);
                }
                MethodVisitor visitMethod = classWriter.visitMethod(1, entry.getKey().getName(), Type.getMethodDescriptor(entry.getKey()), null, strArr);
                visitMethod.visitCode();
                int i2 = (entry.getKey().getModifiers() & 8) == 0 ? 1 : 0;
                for (Class<?> cls2 : entry.getKey().getParameterTypes()) {
                    i2 += Type.getType(cls2).getSize();
                }
                visitMethod.visitMaxs(entry.getValue().apply(visitMethod, entry.getKey()), i2);
                visitMethod.visitEnd();
            }
            MethodVisitor visitMethod2 = classWriter.visitMethod(1, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, new Type[0]), null, null);
            visitMethod2.visitCode();
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitMethodInsn(183, Type.getInternalName(Object.class), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, new Type[0]), false);
            visitMethod2.visitInsn(177);
            visitMethod2.visitMaxs(1, 1);
            visitMethod2.visitEnd();
            classWriter.visitEnd();
            byte[] byteArray = classWriter.toByteArray();
            if (DUMP_FOLDER != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(DUMP_FOLDER, cls.getName() + "$Proxy.class"));
                    try {
                        fileOutputStream.write(byteArray);
                    } finally {
                        fileOutputStream.close();
                    }
                } catch (Throwable unused) {
                }
            }
            try {
                return new DynamicClassLoader(cls).defineClass(cls.getName() + "$Proxy", byteArray, 0, byteArray.length, JavaDispatcher.class.getProtectionDomain()).getConstructor(NO_PARAMETER).newInstance(NO_ARGUMENT);
            } catch (Exception e) {
                throw new IllegalStateException("Failed to create proxy for " + cls.getName(), e);
            }
        }

        @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION", "DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED"}, justification = "Expected internal invocation.")
        protected static Invoker invoker() {
            ClassWriter classWriter = new ClassWriter(0);
            classWriter.visit(ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V5).getMinorMajorVersion(), 1, Type.getInternalName(Invoker.class) + "$Dispatcher", null, Type.getInternalName(Object.class), new String[]{Type.getInternalName(Invoker.class)});
            for (Method method : (Method[]) GraalImageCode.getCurrent().sorted(Invoker.class.getMethods(), MethodComparator.INSTANCE)) {
                Class<?>[] exceptionTypes = method.getExceptionTypes();
                String[] strArr = new String[exceptionTypes.length];
                for (int i = 0; i < exceptionTypes.length; i++) {
                    strArr[i] = Type.getInternalName(exceptionTypes[i]);
                }
                MethodVisitor visitMethod = classWriter.visitMethod(1, method.getName(), Type.getMethodDescriptor(method), null, strArr);
                visitMethod.visitCode();
                int i2 = 1;
                Type[] typeArr = new Type[method.getParameterTypes().length - 1];
                for (int i3 = 0; i3 < method.getParameterTypes().length; i3++) {
                    Type type = Type.getType(method.getParameterTypes()[i3]);
                    if (i3 > 0) {
                        typeArr[i3 - 1] = type;
                    }
                    visitMethod.visitVarInsn(type.getOpcode(21), i2);
                    i2 += type.getSize();
                }
                visitMethod.visitMethodInsn(182, Type.getInternalName(method.getParameterTypes()[0]), method.getName(), Type.getMethodDescriptor(Type.getReturnType(method), typeArr), false);
                visitMethod.visitInsn(Type.getReturnType(method).getOpcode(172));
                visitMethod.visitMaxs(Math.max(i2 - 1, Type.getReturnType(method).getSize()), i2);
                visitMethod.visitEnd();
            }
            MethodVisitor visitMethod2 = classWriter.visitMethod(1, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, new Type[0]), null, null);
            visitMethod2.visitCode();
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitMethodInsn(183, Type.getInternalName(Object.class), MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, new Type[0]), false);
            visitMethod2.visitInsn(177);
            visitMethod2.visitMaxs(1, 1);
            visitMethod2.visitEnd();
            classWriter.visitEnd();
            byte[] byteArray = classWriter.toByteArray();
            try {
                String property = System.getProperty(TypeWriter.DUMP_PROPERTY);
                if (property != null) {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(property, Invoker.class.getName() + "$Dispatcher.class"));
                    try {
                        fileOutputStream.write(byteArray);
                    } finally {
                        fileOutputStream.close();
                    }
                }
            } catch (Throwable unused) {
            }
            try {
                return (Invoker) new DynamicClassLoader(Invoker.class).defineClass(Invoker.class.getName() + "$Dispatcher", byteArray, 0, byteArray.length, JavaDispatcher.class.getProtectionDomain()).getConstructor(NO_PARAMETER).newInstance(NO_ARGUMENT);
            } catch (UnsupportedOperationException unused2) {
                return new DirectInvoker((byte) 0);
            } catch (Exception e) {
                throw new IllegalStateException("Failed to create invoker for " + Invoker.class.getName(), e);
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$DynamicClassLoader$Resolver.class */
        protected interface Resolver {
            void accept(@MaybeNull ClassLoader classLoader, Class<?> cls);

            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$DynamicClassLoader$Resolver$CreationAction.class */
            public enum CreationAction implements PrivilegedAction<Resolver> {
                INSTANCE;

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
                public final Resolver run() {
                    try {
                        Class<?> cls = Class.forName("java.lang.Module", false, null);
                        return new ForModuleSystem(Class.class.getMethod("getModule", new Class[0]), cls.getMethod("isExported", String.class), cls.getMethod("addExports", String.class, cls), ClassLoader.class.getMethod("getUnnamedModule", new Class[0]));
                    } catch (Exception unused) {
                        return NoOp.INSTANCE;
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$DynamicClassLoader$Resolver$NoOp.class */
            public enum NoOp implements Resolver {
                INSTANCE;

                @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.DynamicClassLoader.Resolver
                public final void accept(@MaybeNull ClassLoader classLoader, Class<?> cls) {
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/dispatcher/JavaDispatcher$DynamicClassLoader$Resolver$ForModuleSystem.class */
            public static class ForModuleSystem implements Resolver {
                private final Method getModule;
                private final Method isExported;
                private final Method addExports;
                private final Method getUnnamedModule;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.getModule.equals(((ForModuleSystem) obj).getModule) && this.isExported.equals(((ForModuleSystem) obj).isExported) && this.addExports.equals(((ForModuleSystem) obj).addExports) && this.getUnnamedModule.equals(((ForModuleSystem) obj).getUnnamedModule);
                }

                public int hashCode() {
                    return (((((((getClass().hashCode() * 31) + this.getModule.hashCode()) * 31) + this.isExported.hashCode()) * 31) + this.addExports.hashCode()) * 31) + this.getUnnamedModule.hashCode();
                }

                protected ForModuleSystem(Method method, Method method2, Method method3, Method method4) {
                    this.getModule = method;
                    this.isExported = method2;
                    this.addExports = method3;
                    this.getUnnamedModule = method4;
                }

                @Override // net.bytebuddy.utility.dispatcher.JavaDispatcher.DynamicClassLoader.Resolver
                @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should always be wrapped for clarity.")
                public void accept(@MaybeNull ClassLoader classLoader, Class<?> cls) {
                    Package r0 = cls.getPackage();
                    if (r0 != null) {
                        try {
                            Object invoke = this.getModule.invoke(cls, new Object[0]);
                            if (!((Boolean) this.isExported.invoke(invoke, r0.getName())).booleanValue()) {
                                this.addExports.invoke(invoke, r0.getName(), this.getUnnamedModule.invoke(classLoader, new Object[0]));
                            }
                        } catch (Exception e) {
                            throw new IllegalStateException("Failed to adjust module graph for dispatcher", e);
                        }
                    }
                }
            }
        }
    }
}
