package net.bytebuddy.utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant.class */
public interface JavaConstant {
    Object toDescription();

    TypeDescription getTypeDescription();

    <T> T accept(Visitor<T> visitor);

    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Visitor.class */
    public interface Visitor<T> {
        T onValue(Simple<?> simple);

        T onType(Simple<TypeDescription> simple);

        T onMethodType(MethodType methodType);

        T onMethodHandle(MethodHandle methodHandle);

        T onDynamic(Dynamic dynamic);

        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Visitor$NoOp.class */
        public enum NoOp implements Visitor<JavaConstant> {
            INSTANCE;

            @Override // net.bytebuddy.utility.JavaConstant.Visitor
            public final /* bridge */ /* synthetic */ JavaConstant onType(Simple simple) {
                return onType((Simple<TypeDescription>) simple);
            }

            @Override // net.bytebuddy.utility.JavaConstant.Visitor
            public final /* bridge */ /* synthetic */ JavaConstant onValue(Simple simple) {
                return onValue((Simple<?>) simple);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.utility.JavaConstant.Visitor
            public final JavaConstant onValue(Simple<?> simple) {
                return simple;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.utility.JavaConstant.Visitor
            public final JavaConstant onType(Simple<TypeDescription> simple) {
                return simple;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.utility.JavaConstant.Visitor
            public final JavaConstant onMethodType(MethodType methodType) {
                return methodType;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.utility.JavaConstant.Visitor
            public final JavaConstant onMethodHandle(MethodHandle methodHandle) {
                return methodHandle;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.utility.JavaConstant.Visitor
            public final JavaConstant onDynamic(Dynamic dynamic) {
                return dynamic;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple.class */
    public static abstract class Simple<T> implements JavaConstant {
        protected static final Dispatcher CONSTANT_DESC;
        protected static final Dispatcher.OfClassDesc CLASS_DESC;
        protected static final Dispatcher.OfMethodTypeDesc METHOD_TYPE_DESC;
        protected static final Dispatcher.OfMethodHandleDesc METHOD_HANDLE_DESC;
        protected static final Dispatcher.OfDirectMethodHandleDesc DIRECT_METHOD_HANDLE_DESC;
        protected static final Dispatcher.OfDirectMethodHandleDesc.ForKind DIRECT_METHOD_HANDLE_DESC_KIND;
        protected static final Dispatcher.OfDynamicConstantDesc DYNAMIC_CONSTANT_DESC;
        protected final T value;
        private final TypeDescription typeDescription;
        private static final boolean ACCESS_CONTROLLER;

        /* JADX INFO: Access modifiers changed from: protected */
        @JavaDispatcher.Proxied("java.lang.constant.ConstantDesc")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$Dispatcher.class */
        public interface Dispatcher {

            @JavaDispatcher.Proxied("java.lang.constant.ClassDesc")
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$Dispatcher$OfClassDesc.class */
            public interface OfClassDesc extends Dispatcher {
                @JavaDispatcher.IsStatic
                @JavaDispatcher.Proxied("ofDescriptor")
                Object ofDescriptor(String str);

                @JavaDispatcher.Proxied("descriptorString")
                String descriptorString(Object obj);
            }

            @JavaDispatcher.Proxied("java.lang.constant.DirectMethodHandleDesc")
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$Dispatcher$OfDirectMethodHandleDesc.class */
            public interface OfDirectMethodHandleDesc extends Dispatcher {

                @JavaDispatcher.Proxied("java.lang.constant.DirectMethodHandleDesc$Kind")
                /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$Dispatcher$OfDirectMethodHandleDesc$ForKind.class */
                public interface ForKind {
                    @JavaDispatcher.IsStatic
                    @JavaDispatcher.Proxied("valueOf")
                    Object valueOf(int i, boolean z);
                }

                @JavaDispatcher.Proxied("refKind")
                int refKind(Object obj);

                @JavaDispatcher.Proxied("methodName")
                String methodName(Object obj);

                @JavaDispatcher.Proxied("owner")
                Object owner(Object obj);

                @JavaDispatcher.Proxied("lookupDescriptor")
                String lookupDescriptor(Object obj);
            }

            @JavaDispatcher.Proxied("java.lang.constant.DynamicConstantDesc")
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$Dispatcher$OfDynamicConstantDesc.class */
            public interface OfDynamicConstantDesc extends Dispatcher {
                @JavaDispatcher.IsStatic
                @JavaDispatcher.Proxied("ofCanonical")
                Object ofCanonical(@JavaDispatcher.Proxied("java.lang.constant.DirectMethodHandleDesc") Object obj, String str, @JavaDispatcher.Proxied("java.lang.constant.ClassDesc") Object obj2, @JavaDispatcher.Proxied("java.lang.constant.ConstantDesc") Object[] objArr);

                @JavaDispatcher.Proxied("bootstrapArgs")
                Object[] bootstrapArgs(Object obj);

                @JavaDispatcher.Proxied("constantName")
                String constantName(Object obj);

                @JavaDispatcher.Proxied("constantType")
                Object constantType(Object obj);

                @JavaDispatcher.Proxied("bootstrapMethod")
                Object bootstrapMethod(Object obj);
            }

            @JavaDispatcher.Proxied("java.lang.constant.MethodHandleDesc")
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$Dispatcher$OfMethodHandleDesc.class */
            public interface OfMethodHandleDesc extends Dispatcher {
                @JavaDispatcher.IsStatic
                @JavaDispatcher.Proxied("of")
                Object of(@JavaDispatcher.Proxied("java.lang.constant.DirectMethodHandleDesc$Kind") Object obj, @JavaDispatcher.Proxied("java.lang.constant.ClassDesc") Object obj2, String str, String str2);

                @JavaDispatcher.Proxied("invocationType")
                Object invocationType(Object obj);
            }

            @JavaDispatcher.Proxied("java.lang.constant.MethodTypeDesc")
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$Dispatcher$OfMethodTypeDesc.class */
            public interface OfMethodTypeDesc extends Dispatcher {
                @JavaDispatcher.IsStatic
                @JavaDispatcher.Proxied("of")
                Object of(@JavaDispatcher.Proxied("java.lang.constant.ClassDesc") Object obj, @JavaDispatcher.Proxied("java.lang.constant.ClassDesc") Object[] objArr);

                @JavaDispatcher.IsStatic
                @JavaDispatcher.Proxied("ofDescriptor")
                Object ofDescriptor(String str);

                @JavaDispatcher.Proxied("returnType")
                Object returnType(Object obj);

                @JavaDispatcher.Proxied("parameterArray")
                Object[] parameterArray(Object obj);
            }

            @JavaDispatcher.Instance
            @JavaDispatcher.Proxied("isInstance")
            boolean isInstance(Object obj);

            @JavaDispatcher.Container
            @JavaDispatcher.Proxied("toArray")
            Object[] toArray(int i);
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
            CONSTANT_DESC = (Dispatcher) doPrivileged(JavaDispatcher.of(Dispatcher.class));
            CLASS_DESC = (Dispatcher.OfClassDesc) doPrivileged(JavaDispatcher.of(Dispatcher.OfClassDesc.class));
            METHOD_TYPE_DESC = (Dispatcher.OfMethodTypeDesc) doPrivileged(JavaDispatcher.of(Dispatcher.OfMethodTypeDesc.class));
            METHOD_HANDLE_DESC = (Dispatcher.OfMethodHandleDesc) doPrivileged(JavaDispatcher.of(Dispatcher.OfMethodHandleDesc.class));
            DIRECT_METHOD_HANDLE_DESC = (Dispatcher.OfDirectMethodHandleDesc) doPrivileged(JavaDispatcher.of(Dispatcher.OfDirectMethodHandleDesc.class));
            DIRECT_METHOD_HANDLE_DESC_KIND = (Dispatcher.OfDirectMethodHandleDesc.ForKind) doPrivileged(JavaDispatcher.of(Dispatcher.OfDirectMethodHandleDesc.ForKind.class));
            DYNAMIC_CONSTANT_DESC = (Dispatcher.OfDynamicConstantDesc) doPrivileged(JavaDispatcher.of(Dispatcher.OfDynamicConstantDesc.class));
        }

        protected Simple(T t, TypeDescription typeDescription) {
            this.value = t;
            this.typeDescription = typeDescription;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static JavaConstant ofLoaded(Object obj) {
            if (obj instanceof Integer) {
                return new OfTrivialValue((Integer) obj, TypeDescription.ForLoadedType.of(Integer.TYPE));
            }
            if (obj instanceof Long) {
                return new OfTrivialValue((Long) obj, TypeDescription.ForLoadedType.of(Long.TYPE));
            }
            if (obj instanceof Float) {
                return new OfTrivialValue((Float) obj, TypeDescription.ForLoadedType.of(Float.TYPE));
            }
            if (obj instanceof Double) {
                return new OfTrivialValue((Double) obj, TypeDescription.ForLoadedType.of(Double.TYPE));
            }
            if (obj instanceof String) {
                return new OfTrivialValue((String) obj, TypeDescription.ForLoadedType.of(String.class));
            }
            if (obj instanceof Class) {
                return of(TypeDescription.ForLoadedType.of((Class) obj));
            }
            if (JavaType.METHOD_HANDLE.isInstance(obj)) {
                return MethodHandle.ofLoaded(obj);
            }
            if (JavaType.METHOD_TYPE.isInstance(obj)) {
                return MethodType.ofLoaded(obj);
            }
            throw new IllegalArgumentException("Not a loaded Java constant value: " + obj);
        }

        public static JavaConstant ofDescription(Object obj, @MaybeNull ClassLoader classLoader) {
            return ofDescription(obj, ClassFileLocator.ForClassLoader.of(classLoader));
        }

        public static JavaConstant ofDescription(Object obj, ClassFileLocator classFileLocator) {
            return ofDescription(obj, TypePool.Default.WithLazyResolution.of(classFileLocator));
        }

        public static JavaConstant ofDescription(Object obj, TypePool typePool) {
            String className;
            String className2;
            String className3;
            TypeDescription resolve;
            String className4;
            String className5;
            String className6;
            String className7;
            if (obj instanceof Integer) {
                return new OfTrivialValue((Integer) obj, TypeDescription.ForLoadedType.of(Integer.TYPE));
            }
            if (obj instanceof Long) {
                return new OfTrivialValue((Long) obj, TypeDescription.ForLoadedType.of(Long.TYPE));
            }
            if (obj instanceof Float) {
                return new OfTrivialValue((Float) obj, TypeDescription.ForLoadedType.of(Float.TYPE));
            }
            if (obj instanceof Double) {
                return new OfTrivialValue((Double) obj, TypeDescription.ForLoadedType.of(Double.TYPE));
            }
            if (obj instanceof String) {
                return new OfTrivialValue((String) obj, TypeDescription.ForLoadedType.of(String.class));
            }
            if (CLASS_DESC.isInstance(obj)) {
                Type type = Type.getType(CLASS_DESC.descriptorString(obj));
                if (type.getSort() == 9) {
                    className7 = type.getInternalName().replace('/', '.');
                } else {
                    className7 = type.getClassName();
                }
                return OfTypeDescription.of(typePool.describe(className7).resolve());
            }
            if (METHOD_TYPE_DESC.isInstance(obj)) {
                Object[] parameterArray = METHOD_TYPE_DESC.parameterArray(obj);
                ArrayList arrayList = new ArrayList(parameterArray.length);
                for (Object obj2 : parameterArray) {
                    Type type2 = Type.getType(CLASS_DESC.descriptorString(obj2));
                    if (type2.getSort() == 9) {
                        className6 = type2.getInternalName().replace('/', '.');
                    } else {
                        className6 = type2.getClassName();
                    }
                    arrayList.add(typePool.describe(className6).resolve());
                }
                Type type3 = Type.getType(CLASS_DESC.descriptorString(METHOD_TYPE_DESC.returnType(obj)));
                if (type3.getSort() == 9) {
                    className5 = type3.getInternalName().replace('/', '.');
                } else {
                    className5 = type3.getClassName();
                }
                return MethodType.of(typePool.describe(className5).resolve(), arrayList);
            }
            if (DIRECT_METHOD_HANDLE_DESC.isInstance(obj)) {
                Object[] parameterArray2 = METHOD_TYPE_DESC.parameterArray(METHOD_HANDLE_DESC.invocationType(obj));
                ArrayList arrayList2 = new ArrayList(parameterArray2.length);
                for (Object obj3 : parameterArray2) {
                    Type type4 = Type.getType(CLASS_DESC.descriptorString(obj3));
                    if (type4.getSort() == 9) {
                        className4 = type4.getInternalName().replace('/', '.');
                    } else {
                        className4 = type4.getClassName();
                    }
                    arrayList2.add(typePool.describe(className4).resolve());
                }
                Type type5 = Type.getType(CLASS_DESC.descriptorString(METHOD_TYPE_DESC.returnType(METHOD_HANDLE_DESC.invocationType(obj))));
                MethodHandle.HandleType of = MethodHandle.HandleType.of(DIRECT_METHOD_HANDLE_DESC.refKind(obj));
                TypeDescription resolve2 = typePool.describe(Type.getType(CLASS_DESC.descriptorString(DIRECT_METHOD_HANDLE_DESC.owner(obj))).getClassName()).resolve();
                String methodName = DIRECT_METHOD_HANDLE_DESC.methodName(obj);
                if (DIRECT_METHOD_HANDLE_DESC.refKind(obj) == 8) {
                    resolve = TypeDescription.ForLoadedType.of(Void.TYPE);
                } else {
                    resolve = typePool.describe(type5.getSort() == 9 ? type5.getInternalName().replace('/', '.') : type5.getClassName()).resolve();
                }
                return new MethodHandle(of, resolve2, methodName, resolve, arrayList2);
            }
            if (DYNAMIC_CONSTANT_DESC.isInstance(obj)) {
                Type methodType = Type.getMethodType(DIRECT_METHOD_HANDLE_DESC.lookupDescriptor(DYNAMIC_CONSTANT_DESC.bootstrapMethod(obj)));
                ArrayList arrayList3 = new ArrayList(methodType.getArgumentTypes().length);
                for (Type type6 : methodType.getArgumentTypes()) {
                    if (type6.getSort() == 9) {
                        className3 = type6.getInternalName().replace('/', '.');
                    } else {
                        className3 = type6.getClassName();
                    }
                    arrayList3.add(typePool.describe(className3).resolve());
                }
                Object[] bootstrapArgs = DYNAMIC_CONSTANT_DESC.bootstrapArgs(obj);
                ArrayList arrayList4 = new ArrayList(bootstrapArgs.length);
                for (Object obj4 : bootstrapArgs) {
                    arrayList4.add(ofDescription(obj4, typePool));
                }
                Type type7 = Type.getType(CLASS_DESC.descriptorString(DYNAMIC_CONSTANT_DESC.constantType(obj)));
                String constantName = DYNAMIC_CONSTANT_DESC.constantName(obj);
                if (type7.getSort() == 9) {
                    className = type7.getInternalName().replace('/', '.');
                } else {
                    className = type7.getClassName();
                }
                TypeDescription resolve3 = typePool.describe(className).resolve();
                MethodHandle.HandleType of2 = MethodHandle.HandleType.of(DIRECT_METHOD_HANDLE_DESC.refKind(DYNAMIC_CONSTANT_DESC.bootstrapMethod(obj)));
                TypeDescription resolve4 = typePool.describe(Type.getType(CLASS_DESC.descriptorString(DIRECT_METHOD_HANDLE_DESC.owner(DYNAMIC_CONSTANT_DESC.bootstrapMethod(obj)))).getClassName()).resolve();
                String methodName2 = DIRECT_METHOD_HANDLE_DESC.methodName(DYNAMIC_CONSTANT_DESC.bootstrapMethod(obj));
                if (methodType.getReturnType().getSort() == 9) {
                    className2 = methodType.getReturnType().getInternalName().replace('/', '.');
                } else {
                    className2 = methodType.getReturnType().getClassName();
                }
                return new Dynamic(constantName, resolve3, new MethodHandle(of2, resolve4, methodName2, typePool.describe(className2).resolve(), arrayList3), arrayList4);
            }
            throw new IllegalArgumentException("Not a resolvable constant description or not expressible as a constant pool value: " + obj);
        }

        public static JavaConstant of(TypeDescription typeDescription) {
            if (typeDescription.isPrimitive()) {
                throw new IllegalArgumentException("A primitive type cannot be represented as a type constant: " + typeDescription);
            }
            return new OfTypeDescription(typeDescription);
        }

        public static JavaConstant wrap(Object obj) {
            if (obj instanceof JavaConstant) {
                return (JavaConstant) obj;
            }
            if (obj instanceof TypeDescription) {
                return of((TypeDescription) obj);
            }
            return ofLoaded(obj);
        }

        public static List<JavaConstant> wrap(List<?> list) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(wrap(it.next()));
            }
            return arrayList;
        }

        public T getValue() {
            return this.value;
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public TypeDescription getTypeDescription() {
            return this.typeDescription;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.value.equals(((Simple) obj).value);
        }

        public String toString() {
            return this.value.toString();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$OfTrivialValue.class */
        public static class OfTrivialValue<S> extends Simple<S> {
            protected OfTrivialValue(S s, TypeDescription typeDescription) {
                super(s, typeDescription);
            }

            @Override // net.bytebuddy.utility.JavaConstant
            public Object toDescription() {
                return this.value;
            }

            @Override // net.bytebuddy.utility.JavaConstant
            public <T> T accept(Visitor<T> visitor) {
                return visitor.onValue(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Simple$OfTypeDescription.class */
        public static class OfTypeDescription extends Simple<TypeDescription> {
            protected OfTypeDescription(TypeDescription typeDescription) {
                super(typeDescription, TypeDescription.ForLoadedType.of(Class.class));
            }

            @Override // net.bytebuddy.utility.JavaConstant
            public Object toDescription() {
                return CLASS_DESC.ofDescriptor(((TypeDescription) this.value).getDescriptor());
            }

            @Override // net.bytebuddy.utility.JavaConstant
            public <T> T accept(Visitor<T> visitor) {
                return visitor.onType(this);
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodType.class */
    public static class MethodType implements JavaConstant {
        private static final Dispatcher DISPATCHER;
        private final TypeDescription returnType;
        private final List<? extends TypeDescription> parameterTypes;
        private static final boolean ACCESS_CONTROLLER;

        /* JADX INFO: Access modifiers changed from: protected */
        @JavaDispatcher.Proxied("java.lang.invoke.MethodType")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodType$Dispatcher.class */
        public interface Dispatcher {
            @JavaDispatcher.Proxied("returnType")
            Class<?> returnType(Object obj);

            @JavaDispatcher.Proxied("parameterArray")
            Class<?>[] parameterArray(Object obj);
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
            DISPATCHER = (Dispatcher) doPrivileged(JavaDispatcher.of(Dispatcher.class));
        }

        protected MethodType(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static MethodType ofLoaded(Object obj) {
            if (!JavaType.METHOD_TYPE.isInstance(obj)) {
                throw new IllegalArgumentException("Expected method type object: " + obj);
            }
            return of(DISPATCHER.returnType(obj), DISPATCHER.parameterArray(obj));
        }

        public static MethodType of(Class<?> cls, Class<?>... clsArr) {
            return of(TypeDescription.ForLoadedType.of(cls), new TypeList.ForLoadedTypes(clsArr));
        }

        public static MethodType of(TypeDescription typeDescription, TypeDescription... typeDescriptionArr) {
            return new MethodType(typeDescription, Arrays.asList(typeDescriptionArr));
        }

        public static MethodType of(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            return new MethodType(typeDescription, list);
        }

        public static MethodType of(Method method) {
            return of(new MethodDescription.ForLoadedMethod(method));
        }

        public static MethodType of(Constructor<?> constructor) {
            return of(new MethodDescription.ForLoadedConstructor(constructor));
        }

        public static MethodType of(MethodDescription methodDescription) {
            return new MethodType(methodDescription.getReturnType().asErasure(), methodDescription.getParameters().asTypeList().asErasures());
        }

        public static MethodType ofSetter(Field field) {
            return ofSetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodType ofSetter(FieldDescription fieldDescription) {
            return new MethodType(TypeDescription.ForLoadedType.of(Void.TYPE), Collections.singletonList(fieldDescription.getType().asErasure()));
        }

        public static MethodType ofGetter(Field field) {
            return ofGetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodType ofGetter(FieldDescription fieldDescription) {
            return new MethodType(fieldDescription.getType().asErasure(), Collections.emptyList());
        }

        public static MethodType ofConstant(Object obj) {
            return ofConstant(obj.getClass());
        }

        public static MethodType ofConstant(Class<?> cls) {
            return ofConstant(TypeDescription.ForLoadedType.of(cls));
        }

        public static MethodType ofConstant(TypeDescription typeDescription) {
            return new MethodType(typeDescription, Collections.emptyList());
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public TypeList getParameterTypes() {
            return new TypeList.Explicit(this.parameterTypes);
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder("(");
            Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDescriptor());
            }
            return sb.append(')').append(this.returnType.getDescriptor()).toString();
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public Object toDescription() {
            Object[] array = Simple.CLASS_DESC.toArray(this.parameterTypes.size());
            for (int i = 0; i < this.parameterTypes.size(); i++) {
                array[i] = Simple.CLASS_DESC.ofDescriptor(this.parameterTypes.get(i).getDescriptor());
            }
            return Simple.METHOD_TYPE_DESC.of(Simple.CLASS_DESC.ofDescriptor(this.returnType.getDescriptor()), array);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public <T> T accept(Visitor<T> visitor) {
            return visitor.onMethodType(this);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public TypeDescription getTypeDescription() {
            return JavaType.METHOD_TYPE.getTypeStub();
        }

        public int hashCode() {
            return (this.returnType.hashCode() * 31) + this.parameterTypes.hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodType)) {
                return false;
            }
            MethodType methodType = (MethodType) obj;
            return this.parameterTypes.equals(methodType.parameterTypes) && this.returnType.equals(methodType.returnType);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(");
            boolean z = true;
            for (TypeDescription typeDescription : this.parameterTypes) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(typeDescription.getSimpleName());
            }
            return sb.append(')').append(this.returnType.getSimpleName()).toString();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodHandle.class */
    public static class MethodHandle implements JavaConstant {
        protected static final MethodHandleInfo METHOD_HANDLE_INFO;
        protected static final MethodType METHOD_TYPE;
        protected static final MethodHandles METHOD_HANDLES;
        protected static final MethodHandles.Lookup METHOD_HANDLES_LOOKUP;
        private final HandleType handleType;
        private final TypeDescription ownerType;
        private final String name;
        private final TypeDescription returnType;
        private final List<? extends TypeDescription> parameterTypes;
        private static final boolean ACCESS_CONTROLLER;

        /* JADX INFO: Access modifiers changed from: protected */
        @JavaDispatcher.Proxied("java.lang.invoke.MethodHandleInfo")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodHandle$MethodHandleInfo.class */
        public interface MethodHandleInfo {
            @JavaDispatcher.Proxied("getName")
            String getName(Object obj);

            @JavaDispatcher.Proxied("getDeclaringClass")
            Class<?> getDeclaringClass(Object obj);

            @JavaDispatcher.Proxied("getReferenceKind")
            int getReferenceKind(Object obj);

            @JavaDispatcher.Proxied("getMethodType")
            Object getMethodType(Object obj);

            @JavaDispatcher.IsConstructor
            @JavaDispatcher.Proxied("revealDirect")
            Object revealDirect(@JavaDispatcher.Proxied("java.lang.invoke.MethodHandle") Object obj);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @JavaDispatcher.Proxied("java.lang.invoke.MethodHandles")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodHandle$MethodHandles.class */
        public interface MethodHandles {

            @JavaDispatcher.Proxied("java.lang.invoke.MethodHandles$Lookup")
            /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodHandle$MethodHandles$Lookup.class */
            public interface Lookup {
                @JavaDispatcher.Proxied("lookupClass")
                Class<?> lookupClass(Object obj);

                @JavaDispatcher.Proxied("revealDirect")
                Object revealDirect(Object obj, @JavaDispatcher.Proxied("java.lang.invoke.MethodHandle") Object obj2);
            }

            @JavaDispatcher.IsStatic
            @JavaDispatcher.Proxied("publicLookup")
            Object publicLookup();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @JavaDispatcher.Proxied("java.lang.invoke.MethodType")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodHandle$MethodType.class */
        public interface MethodType {
            @JavaDispatcher.Proxied("returnType")
            Class<?> returnType(Object obj);

            @JavaDispatcher.Proxied("parameterArray")
            Class<?>[] parameterArray(Object obj);
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
            METHOD_HANDLE_INFO = (MethodHandleInfo) doPrivileged(JavaDispatcher.of(MethodHandleInfo.class));
            METHOD_TYPE = (MethodType) doPrivileged(JavaDispatcher.of(MethodType.class));
            METHOD_HANDLES = (MethodHandles) doPrivileged(JavaDispatcher.of(MethodHandles.class));
            METHOD_HANDLES_LOOKUP = (MethodHandles.Lookup) doPrivileged(JavaDispatcher.of(MethodHandles.Lookup.class));
        }

        protected MethodHandle(HandleType handleType, TypeDescription typeDescription, String str, TypeDescription typeDescription2, List<? extends TypeDescription> list) {
            this.handleType = handleType;
            this.ownerType = typeDescription;
            this.name = str;
            this.returnType = typeDescription2;
            this.parameterTypes = list;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static MethodHandle ofLoaded(Object obj) {
            return ofLoaded(obj, METHOD_HANDLES.publicLookup());
        }

        public static MethodHandle ofLoaded(Object obj, Object obj2) {
            Object revealDirect;
            if (!JavaType.METHOD_HANDLE.isInstance(obj)) {
                throw new IllegalArgumentException("Expected method handle object: " + obj);
            }
            if (!JavaType.METHOD_HANDLES_LOOKUP.isInstance(obj2)) {
                throw new IllegalArgumentException("Expected method handle lookup object: " + obj2);
            }
            if (ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V8).isAtMost(ClassFileVersion.JAVA_V7)) {
                revealDirect = METHOD_HANDLE_INFO.revealDirect(obj);
            } else {
                revealDirect = METHOD_HANDLES_LOOKUP.revealDirect(obj2, obj);
            }
            Object obj3 = revealDirect;
            Object methodType = METHOD_HANDLE_INFO.getMethodType(obj3);
            return new MethodHandle(HandleType.of(METHOD_HANDLE_INFO.getReferenceKind(obj3)), TypeDescription.ForLoadedType.of(METHOD_HANDLE_INFO.getDeclaringClass(obj3)), METHOD_HANDLE_INFO.getName(obj3), TypeDescription.ForLoadedType.of(METHOD_TYPE.returnType(methodType)), new TypeList.ForLoadedTypes(METHOD_TYPE.parameterArray(methodType)));
        }

        public static MethodHandle of(Method method) {
            return of(new MethodDescription.ForLoadedMethod(method));
        }

        public static MethodHandle of(Constructor<?> constructor) {
            return of(new MethodDescription.ForLoadedConstructor(constructor));
        }

        public static MethodHandle of(MethodDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.of(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), inDefinedShape.getReturnType().asErasure(), inDefinedShape.getParameters().asTypeList().asErasures());
        }

        public static MethodHandle ofSpecial(Method method, Class<?> cls) {
            return ofSpecial(new MethodDescription.ForLoadedMethod(method), TypeDescription.ForLoadedType.of(cls));
        }

        public static MethodHandle ofSpecial(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
            if (!inDefinedShape.isSpecializableFor(typeDescription)) {
                throw new IllegalArgumentException("Cannot specialize " + inDefinedShape + " for " + typeDescription);
            }
            return new MethodHandle(HandleType.ofSpecial(inDefinedShape), typeDescription, inDefinedShape.getInternalName(), inDefinedShape.getReturnType().asErasure(), inDefinedShape.getParameters().asTypeList().asErasures());
        }

        public static MethodHandle ofGetter(Field field) {
            return ofGetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodHandle ofGetter(FieldDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.ofGetter(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), inDefinedShape.getType().asErasure(), Collections.emptyList());
        }

        public static MethodHandle ofSetter(Field field) {
            return ofSetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodHandle ofSetter(FieldDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.ofSetter(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), TypeDescription.ForLoadedType.of(Void.TYPE), Collections.singletonList(inDefinedShape.getType().asErasure()));
        }

        public static Class<?> lookupType(Object obj) {
            return METHOD_HANDLES_LOOKUP.lookupClass(obj);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public Object toDescription() {
            return Simple.METHOD_HANDLE_DESC.of(Simple.DIRECT_METHOD_HANDLE_DESC_KIND.valueOf(this.handleType.getIdentifier(), this.ownerType.isInterface()), Simple.CLASS_DESC.ofDescriptor(this.ownerType.getDescriptor()), this.name, getDescriptor());
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public <T> T accept(Visitor<T> visitor) {
            return visitor.onMethodHandle(this);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public TypeDescription getTypeDescription() {
            return JavaType.METHOD_HANDLE.getTypeStub();
        }

        public HandleType getHandleType() {
            return this.handleType;
        }

        public TypeDescription getOwnerType() {
            return this.ownerType;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public TypeList getParameterTypes() {
            return new TypeList.Explicit(this.parameterTypes);
        }

        public String getDescriptor() {
            switch (this.handleType) {
                case GET_FIELD:
                case GET_STATIC_FIELD:
                    return this.returnType.getDescriptor();
                case PUT_FIELD:
                case PUT_STATIC_FIELD:
                    return this.parameterTypes.get(0).getDescriptor();
                default:
                    StringBuilder sb = new StringBuilder("(");
                    Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
                    while (it.hasNext()) {
                        sb.append(it.next().getDescriptor());
                    }
                    return sb.append(')').append(this.returnType.getDescriptor()).toString();
            }
        }

        public int hashCode() {
            return (((((((this.handleType.hashCode() * 31) + this.ownerType.hashCode()) * 31) + this.name.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodHandle)) {
                return false;
            }
            MethodHandle methodHandle = (MethodHandle) obj;
            return this.handleType == methodHandle.handleType && this.name.equals(methodHandle.name) && this.ownerType.equals(methodHandle.ownerType) && this.parameterTypes.equals(methodHandle.parameterTypes) && this.returnType.equals(methodHandle.returnType);
        }

        public String toString() {
            StringBuilder append = new StringBuilder().append(this.handleType.name()).append((!this.ownerType.isInterface() || this.handleType.isField() || this.handleType == HandleType.INVOKE_INTERFACE) ? "" : "@interface").append('/').append(this.ownerType.getSimpleName()).append("::").append(this.name).append('(');
            boolean z = true;
            for (TypeDescription typeDescription : this.parameterTypes) {
                if (z) {
                    z = false;
                } else {
                    append.append(',');
                }
                append.append(typeDescription.getSimpleName());
            }
            return append.append(')').append(this.returnType.getSimpleName()).toString();
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$MethodHandle$HandleType.class */
        public enum HandleType {
            INVOKE_VIRTUAL(5, false),
            INVOKE_STATIC(6, false),
            INVOKE_SPECIAL(7, false),
            INVOKE_INTERFACE(9, false),
            INVOKE_SPECIAL_CONSTRUCTOR(8, false),
            PUT_FIELD(3, true),
            GET_FIELD(1, true),
            PUT_STATIC_FIELD(4, true),
            GET_STATIC_FIELD(2, true);

            private final int identifier;
            private final boolean field;

            HandleType(int i, boolean z) {
                this.identifier = i;
                this.field = z;
            }

            protected static HandleType of(MethodDescription.InDefinedShape inDefinedShape) {
                if (inDefinedShape.isTypeInitializer()) {
                    throw new IllegalArgumentException("Cannot create handle of type initializer " + inDefinedShape);
                }
                if (inDefinedShape.isStatic()) {
                    return INVOKE_STATIC;
                }
                if (inDefinedShape.isConstructor()) {
                    return INVOKE_SPECIAL_CONSTRUCTOR;
                }
                if (inDefinedShape.isPrivate()) {
                    return INVOKE_SPECIAL;
                }
                if (inDefinedShape.getDeclaringType().isInterface()) {
                    return INVOKE_INTERFACE;
                }
                return INVOKE_VIRTUAL;
            }

            protected static HandleType of(int i) {
                for (HandleType handleType : values()) {
                    if (handleType.getIdentifier() == i) {
                        return handleType;
                    }
                }
                throw new IllegalArgumentException("Unknown handle type: " + i);
            }

            protected static HandleType ofSpecial(MethodDescription.InDefinedShape inDefinedShape) {
                if (inDefinedShape.isStatic() || inDefinedShape.isAbstract()) {
                    throw new IllegalArgumentException("Cannot invoke " + inDefinedShape + " via invokespecial");
                }
                return inDefinedShape.isConstructor() ? INVOKE_SPECIAL_CONSTRUCTOR : INVOKE_SPECIAL;
            }

            protected static HandleType ofGetter(FieldDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape.isStatic() ? GET_STATIC_FIELD : GET_FIELD;
            }

            protected static HandleType ofSetter(FieldDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape.isStatic() ? PUT_STATIC_FIELD : PUT_FIELD;
            }

            public final int getIdentifier() {
                return this.identifier;
            }

            public final boolean isField() {
                return this.field;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaConstant$Dynamic.class */
    public static class Dynamic implements JavaConstant {
        public static final String DEFAULT_NAME = "_";
        private final String name;
        private final TypeDescription typeDescription;
        private final MethodHandle bootstrap;
        private final List<JavaConstant> arguments;

        protected Dynamic(String str, TypeDescription typeDescription, MethodHandle methodHandle, List<JavaConstant> list) {
            this.name = str;
            this.typeDescription = typeDescription;
            this.bootstrap = methodHandle;
            this.arguments = list;
        }

        public static Dynamic ofNullConstant() {
            return new Dynamic(DEFAULT_NAME, TypeDescription.ForLoadedType.of(Object.class), new MethodHandle(MethodHandle.HandleType.INVOKE_STATIC, JavaType.CONSTANT_BOOTSTRAPS.getTypeStub(), "nullConstant", TypeDescription.ForLoadedType.of(Object.class), Arrays.asList(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Class.class))), Collections.emptyList());
        }

        public static JavaConstant ofPrimitiveType(Class<?> cls) {
            return ofPrimitiveType(TypeDescription.ForLoadedType.of(cls));
        }

        public static JavaConstant ofPrimitiveType(TypeDescription typeDescription) {
            if (!typeDescription.isPrimitive()) {
                throw new IllegalArgumentException("Not a primitive type: " + typeDescription);
            }
            return new Dynamic(typeDescription.getDescriptor(), TypeDescription.ForLoadedType.of(Class.class), new MethodHandle(MethodHandle.HandleType.INVOKE_STATIC, JavaType.CONSTANT_BOOTSTRAPS.getTypeStub(), "primitiveClass", TypeDescription.ForLoadedType.of(Class.class), Arrays.asList(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Class.class))), Collections.emptyList());
        }

        public static JavaConstant ofEnumeration(Enum<?> r4) {
            return ofEnumeration(new EnumerationDescription.ForLoadedEnumeration(r4));
        }

        public static JavaConstant ofEnumeration(EnumerationDescription enumerationDescription) {
            return new Dynamic(enumerationDescription.getValue(), enumerationDescription.getEnumerationType(), new MethodHandle(MethodHandle.HandleType.INVOKE_STATIC, JavaType.CONSTANT_BOOTSTRAPS.getTypeStub(), "enumConstant", TypeDescription.ForLoadedType.of(Enum.class), Arrays.asList(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Class.class))), Collections.emptyList());
        }

        public static Dynamic ofField(Field field) {
            return ofField(new FieldDescription.ForLoadedField(field));
        }

        public static Dynamic ofField(FieldDescription.InDefinedShape inDefinedShape) {
            boolean equals;
            List singletonList;
            if (!inDefinedShape.isStatic() || !inDefinedShape.isFinal()) {
                throw new IllegalArgumentException("Field must be static and final: " + inDefinedShape);
            }
            if (inDefinedShape.getType().isPrimitive()) {
                equals = inDefinedShape.getType().asErasure().asBoxed().equals(inDefinedShape.getType().asErasure());
            } else {
                equals = inDefinedShape.getDeclaringType().equals(inDefinedShape.getType().asErasure());
            }
            boolean z = equals;
            String internalName = inDefinedShape.getInternalName();
            TypeDescription asErasure = inDefinedShape.getType().asErasure();
            MethodHandle methodHandle = new MethodHandle(MethodHandle.HandleType.INVOKE_STATIC, JavaType.CONSTANT_BOOTSTRAPS.getTypeStub(), "getStaticFinal", TypeDescription.ForLoadedType.of(Object.class), z ? Arrays.asList(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Class.class)) : Arrays.asList(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Class.class), TypeDescription.ForLoadedType.of(Class.class)));
            if (z) {
                singletonList = Collections.emptyList();
            } else {
                singletonList = Collections.singletonList(Simple.of(inDefinedShape.getDeclaringType()));
            }
            return new Dynamic(internalName, asErasure, methodHandle, singletonList);
        }

        public static Dynamic ofInvocation(Method method, Object... objArr) {
            return ofInvocation(method, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic ofInvocation(Method method, List<?> list) {
            return ofInvocation(new MethodDescription.ForLoadedMethod(method), list);
        }

        public static Dynamic ofInvocation(Constructor<?> constructor, Object... objArr) {
            return ofInvocation(constructor, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic ofInvocation(Constructor<?> constructor, List<?> list) {
            return ofInvocation(new MethodDescription.ForLoadedConstructor(constructor), list);
        }

        public static Dynamic ofInvocation(MethodDescription.InDefinedShape inDefinedShape, Object... objArr) {
            return ofInvocation(inDefinedShape, (List<?>) Arrays.asList(objArr));
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0066, code lost:            if ((r15.getParameters().size() + ((r15.isStatic() || r15.isConstructor()) ? 0 : 1)) > (r16.size() + 1)) goto L27;     */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x00bf, code lost:            if (r15.isStatic() != false) goto L33;     */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x00c8, code lost:            if (r15.isConstructor() == false) goto L34;     */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x00de, code lost:            r0 = net.bytebuddy.utility.CompoundList.of(r15.getDeclaringType(), r15.getParameters().asTypeList().asErasures());     */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x00f7, code lost:            r17 = r0;     */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00fe, code lost:            if (r15.isVarArgs() == false) goto L38;     */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0101, code lost:            r17 = net.bytebuddy.utility.CompoundList.of(r17.subList(0, r17.size() - 1), java.util.Collections.nCopies((r16.size() - r17.size()) + 1, ((net.bytebuddy.description.type.TypeDescription) r17.get(r17.size() - 1)).getComponentType())).iterator();     */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x014b, code lost:            r0 = new java.util.ArrayList(r16.size() + 1);        r0.add(net.bytebuddy.utility.JavaConstant.MethodHandle.of(r15));        r0 = r16.iterator();     */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0175, code lost:            if (r0.hasNext() == false) goto L53;     */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0178, code lost:            r0 = net.bytebuddy.utility.JavaConstant.Simple.wrap(r0.next());     */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x019b, code lost:            if (r0.getTypeDescription().isAssignableTo((net.bytebuddy.description.type.TypeDescription) r17.next()) != false) goto L46;     */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x01bf, code lost:            r0.add(r0);     */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x01be, code lost:            throw new java.lang.IllegalArgumentException("Cannot assign " + r16 + " to " + r15);     */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x01d7, code lost:            if (r15.isConstructor() == false) goto L50;     */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x01da, code lost:            r3 = r15.getDeclaringType();     */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x023d, code lost:            return new net.bytebuddy.utility.JavaConstant.Dynamic(net.bytebuddy.utility.JavaConstant.Dynamic.DEFAULT_NAME, r3, new net.bytebuddy.utility.JavaConstant.MethodHandle(net.bytebuddy.utility.JavaConstant.MethodHandle.HandleType.INVOKE_STATIC, net.bytebuddy.utility.JavaType.CONSTANT_BOOTSTRAPS.getTypeStub(), "invoke", net.bytebuddy.description.type.TypeDescription.ForLoadedType.of(java.lang.Object.class), java.util.Arrays.asList(net.bytebuddy.utility.JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), net.bytebuddy.description.type.TypeDescription.ForLoadedType.of(java.lang.String.class), net.bytebuddy.description.type.TypeDescription.ForLoadedType.of(java.lang.Class.class), net.bytebuddy.utility.JavaType.METHOD_HANDLE.getTypeStub(), net.bytebuddy.description.type.TypeDescription.ArrayProjection.of(net.bytebuddy.description.type.TypeDescription.ForLoadedType.of(java.lang.Object.class)))), r0);     */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x01e3, code lost:            r3 = r15.getReturnType().asErasure();     */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0144, code lost:            r17 = r17.iterator();     */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00cb, code lost:            r0 = r15.getParameters().asTypeList().asErasures();     */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00b8, code lost:            throw new java.lang.IllegalArgumentException("Cannot assign " + r16 + " to " + r15);     */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0095, code lost:            if ((r15.getParameters().size() + ((r15.isStatic() || r15.isConstructor()) ? 0 : 1)) != r16.size()) goto L27;     */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static net.bytebuddy.utility.JavaConstant.Dynamic ofInvocation(net.bytebuddy.description.method.MethodDescription.InDefinedShape r15, java.util.List<?> r16) {
            /*
                Method dump skipped, instructions count: 574
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.utility.JavaConstant.Dynamic.ofInvocation(net.bytebuddy.description.method.MethodDescription$InDefinedShape, java.util.List):net.bytebuddy.utility.JavaConstant$Dynamic");
        }

        public static JavaConstant ofVarHandle(Field field) {
            return ofVarHandle(new FieldDescription.ForLoadedField(field));
        }

        public static JavaConstant ofVarHandle(FieldDescription.InDefinedShape inDefinedShape) {
            return new Dynamic(inDefinedShape.getInternalName(), JavaType.VAR_HANDLE.getTypeStub(), new MethodHandle(MethodHandle.HandleType.INVOKE_STATIC, JavaType.CONSTANT_BOOTSTRAPS.getTypeStub(), inDefinedShape.isStatic() ? "staticFieldVarHandle" : "fieldVarHandle", JavaType.VAR_HANDLE.getTypeStub(), Arrays.asList(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Class.class), TypeDescription.ForLoadedType.of(Class.class), TypeDescription.ForLoadedType.of(Class.class))), Arrays.asList(Simple.of(inDefinedShape.getDeclaringType()), Simple.of(inDefinedShape.getType().asErasure())));
        }

        public static JavaConstant ofArrayVarHandle(Class<?> cls) {
            return ofArrayVarHandle(TypeDescription.ForLoadedType.of(cls));
        }

        public static JavaConstant ofArrayVarHandle(TypeDescription typeDescription) {
            if (!typeDescription.isArray()) {
                throw new IllegalArgumentException("Not an array type: " + typeDescription);
            }
            return new Dynamic(DEFAULT_NAME, JavaType.VAR_HANDLE.getTypeStub(), new MethodHandle(MethodHandle.HandleType.INVOKE_STATIC, JavaType.CONSTANT_BOOTSTRAPS.getTypeStub(), "arrayVarHandle", JavaType.VAR_HANDLE.getTypeStub(), Arrays.asList(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub(), TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Class.class), TypeDescription.ForLoadedType.of(Class.class))), Collections.singletonList(Simple.of(typeDescription)));
        }

        public static Dynamic bootstrap(String str, Method method, Object... objArr) {
            return bootstrap(str, method, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic bootstrap(String str, Method method, List<?> list) {
            return bootstrap(str, new MethodDescription.ForLoadedMethod(method), list);
        }

        public static Dynamic bootstrap(String str, Constructor<?> constructor, Object... objArr) {
            return bootstrap(str, constructor, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic bootstrap(String str, Constructor<?> constructor, List<?> list) {
            return bootstrap(str, new MethodDescription.ForLoadedConstructor(constructor), list);
        }

        public static Dynamic bootstrap(String str, MethodDescription.InDefinedShape inDefinedShape, Object... objArr) {
            return bootstrap(str, inDefinedShape, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic bootstrap(String str, MethodDescription.InDefinedShape inDefinedShape, List<?> list) {
            TypeDescription asErasure;
            if (str.length() == 0 || str.contains(".")) {
                throw new IllegalArgumentException("Not a valid field name: " + str);
            }
            ArrayList arrayList = new ArrayList(list.size());
            ArrayList arrayList2 = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                JavaConstant wrap = Simple.wrap(it.next());
                arrayList.add(wrap);
                arrayList2.add(wrap.getTypeDescription());
            }
            if (!inDefinedShape.isConstantBootstrap(arrayList2)) {
                throw new IllegalArgumentException("Not a valid bootstrap method " + inDefinedShape + " for " + arrayList);
            }
            if (inDefinedShape.isConstructor()) {
                asErasure = inDefinedShape.getDeclaringType();
            } else {
                asErasure = inDefinedShape.getReturnType().asErasure();
            }
            return new Dynamic(str, asErasure, new MethodHandle(inDefinedShape.isConstructor() ? MethodHandle.HandleType.INVOKE_SPECIAL_CONSTRUCTOR : MethodHandle.HandleType.INVOKE_STATIC, inDefinedShape.getDeclaringType(), inDefinedShape.getInternalName(), inDefinedShape.getReturnType().asErasure(), inDefinedShape.getParameters().asTypeList().asErasures()), arrayList);
        }

        public String getName() {
            return this.name;
        }

        public MethodHandle getBootstrap() {
            return this.bootstrap;
        }

        public List<JavaConstant> getArguments() {
            return this.arguments;
        }

        public JavaConstant withType(Class<?> cls) {
            return withType(TypeDescription.ForLoadedType.of(cls));
        }

        public JavaConstant withType(TypeDescription typeDescription) {
            if (typeDescription.represents(Void.TYPE)) {
                throw new IllegalArgumentException("Constant value cannot represent void");
            }
            if (!getBootstrap().getName().equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME) ? !typeDescription.asBoxed().isInHierarchyWith(getTypeDescription().asBoxed()) : !getTypeDescription().isAssignableTo(typeDescription)) {
                throw new IllegalArgumentException(typeDescription + " is not compatible with bootstrapped type " + getTypeDescription());
            }
            return new Dynamic(getName(), typeDescription, getBootstrap(), getArguments());
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public Object toDescription() {
            Object[] array = Simple.CONSTANT_DESC.toArray(this.arguments.size());
            for (int i = 0; i < array.length; i++) {
                array[i] = this.arguments.get(i).toDescription();
            }
            return Simple.DYNAMIC_CONSTANT_DESC.ofCanonical(Simple.METHOD_HANDLE_DESC.of(Simple.DIRECT_METHOD_HANDLE_DESC_KIND.valueOf(this.bootstrap.getHandleType().getIdentifier(), this.bootstrap.getOwnerType().isInterface()), Simple.CLASS_DESC.ofDescriptor(this.bootstrap.getOwnerType().getDescriptor()), this.bootstrap.getName(), this.bootstrap.getDescriptor()), getName(), Simple.CLASS_DESC.ofDescriptor(this.typeDescription.getDescriptor()), array);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public <T> T accept(Visitor<T> visitor) {
            return visitor.onDynamic(this);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public TypeDescription getTypeDescription() {
            return this.typeDescription;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.typeDescription.hashCode()) * 31) + this.bootstrap.hashCode()) * 31) + this.arguments.hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Dynamic dynamic = (Dynamic) obj;
            if (this.name.equals(dynamic.name) && this.typeDescription.equals(dynamic.typeDescription) && this.bootstrap.equals(dynamic.bootstrap)) {
                return this.arguments.equals(dynamic.arguments);
            }
            return false;
        }

        public String toString() {
            StringBuilder append = new StringBuilder().append(this.bootstrap.getOwnerType().getSimpleName()).append("::").append(this.bootstrap.getName()).append('(').append(this.name.equals(DEFAULT_NAME) ? "" : this.name).append('/');
            boolean z = true;
            for (JavaConstant javaConstant : this.arguments) {
                if (z) {
                    z = false;
                } else {
                    append.append(',');
                }
                append.append(javaConstant.toString());
            }
            return append.append(')').append(this.typeDescription.getSimpleName()).toString();
        }
    }
}
