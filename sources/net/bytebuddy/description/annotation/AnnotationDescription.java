package net.bytebuddy.description.annotation;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.privilege.SetAccessibleAction;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription.class */
public interface AnnotationDescription {

    @AlwaysNull
    public static final Loadable<?> UNDEFINED = null;

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$Loadable.class */
    public interface Loadable<S extends Annotation> extends AnnotationDescription {
        S load();
    }

    AnnotationValue<?, ?> getValue(String str);

    AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape);

    TypeDescription getAnnotationType();

    <T extends Annotation> Loadable<T> prepare(Class<T> cls);

    RetentionPolicy getRetention();

    Set<ElementType> getElementTypes();

    boolean isSupportedOn(ElementType elementType);

    boolean isSupportedOn(String str);

    boolean isInherited();

    boolean isDocumented();

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$RenderingDispatcher.class */
    public enum RenderingDispatcher {
        LEGACY_VM,
        JAVA_14_CAPABLE_VM { // from class: net.bytebuddy.description.annotation.AnnotationDescription.RenderingDispatcher.1
            @Override // net.bytebuddy.description.annotation.AnnotationDescription.RenderingDispatcher
            public final void appendPrefix(StringBuilder sb, String str, int i) {
                if (i > 1 || !str.equals("value")) {
                    super.appendPrefix(sb, str, i);
                }
            }
        },
        JAVA_19_CAPABLE_VM { // from class: net.bytebuddy.description.annotation.AnnotationDescription.RenderingDispatcher.2
            @Override // net.bytebuddy.description.annotation.AnnotationDescription.RenderingDispatcher
            public final void appendPrefix(StringBuilder sb, String str, int i) {
                if (i > 1 || !str.equals("value")) {
                    super.appendPrefix(sb, str, i);
                }
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription.RenderingDispatcher
            public final void appendType(StringBuilder sb, TypeDescription typeDescription) {
                sb.append(typeDescription.getCanonicalName());
            }
        };

        public static final RenderingDispatcher CURRENT;

        /* synthetic */ RenderingDispatcher(byte b2) {
            this();
        }

        static {
            ClassFileVersion ofThisVm = ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V5);
            if (ofThisVm.isAtLeast(ClassFileVersion.JAVA_V19)) {
                CURRENT = JAVA_19_CAPABLE_VM;
            } else if (ofThisVm.isAtLeast(ClassFileVersion.JAVA_V14)) {
                CURRENT = JAVA_14_CAPABLE_VM;
            } else {
                CURRENT = LEGACY_VM;
            }
        }

        public void appendPrefix(StringBuilder sb, String str, int i) {
            sb.append(str).append('=');
        }

        public void appendType(StringBuilder sb, TypeDescription typeDescription) {
            sb.append(typeDescription.getName());
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$AnnotationInvocationHandler.class */
    public static class AnnotationInvocationHandler<T extends Annotation> implements InvocationHandler {
        private static final String HASH_CODE = "hashCode";
        private static final String EQUALS = "equals";
        private static final String TO_STRING = "toString";
        private static final String ANNOTATION_TYPE = "annotationType";
        private static final Object[] NO_ARGUMENT = new Object[0];
        private final Class<? extends Annotation> annotationType;
        private final LinkedHashMap<Method, AnnotationValue.Loaded<?>> values;
        private transient /* synthetic */ int hashCode;

        /* JADX WARN: Multi-variable type inference failed */
        protected AnnotationInvocationHandler(Class<T> cls, LinkedHashMap<Method, AnnotationValue.Loaded<?>> linkedHashMap) {
            this.annotationType = cls;
            this.values = linkedHashMap;
        }

        public static <S extends Annotation> S of(@MaybeNull ClassLoader classLoader, Class<S> cls, Map<String, ? extends AnnotationValue<?, ?>> map) {
            AnnotationValue asValue;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Method method : cls.getDeclaredMethods()) {
                AnnotationValue<?, ?> annotationValue = map.get(method.getName());
                if (annotationValue == null) {
                    Object defaultValue = method.getDefaultValue();
                    if (defaultValue == null) {
                        asValue = new AnnotationValue.ForMissingValue(new TypeDescription.ForLoadedType(method.getDeclaringClass()), method.getName());
                    } else {
                        asValue = ForLoadedAnnotation.asValue(defaultValue, method.getReturnType());
                    }
                    linkedHashMap.put(method, asValue.load(classLoader));
                } else {
                    linkedHashMap.put(method, annotationValue.filter(new MethodDescription.ForLoadedMethod(method)).load(classLoader));
                }
            }
            return (S) Proxy.newProxyInstance(classLoader, new Class[]{cls}, new AnnotationInvocationHandler(cls, linkedHashMap));
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, @MaybeNull Object[] objArr) {
            if (method.getDeclaringClass() != this.annotationType) {
                if (method.getName().equals(HASH_CODE)) {
                    return Integer.valueOf(hashCodeRepresentation());
                }
                if (method.getName().equals(EQUALS) && method.getParameterTypes().length == 1) {
                    return Boolean.valueOf(equalsRepresentation(obj, objArr[0]));
                }
                if (method.getName().equals(TO_STRING)) {
                    return toStringRepresentation();
                }
                if (method.getName().equals(ANNOTATION_TYPE)) {
                    return this.annotationType;
                }
                throw new IllegalStateException("Unexpected method: " + method);
            }
            return this.values.get(method).resolve();
        }

        protected String toStringRepresentation() {
            StringBuilder sb = new StringBuilder();
            sb.append('@');
            RenderingDispatcher.CURRENT.appendType(sb, TypeDescription.ForLoadedType.of(this.annotationType));
            sb.append('(');
            boolean z = true;
            for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                if (entry.getValue().getState().isDefined()) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    RenderingDispatcher.CURRENT.appendPrefix(sb, entry.getKey().getName(), this.values.entrySet().size());
                    sb.append(entry.getValue().toString());
                }
            }
            sb.append(')');
            return sb.toString();
        }

        private int hashCodeRepresentation() {
            int i = 0;
            for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                if (entry.getValue().getState().isDefined()) {
                    i += (127 * entry.getKey().getName().hashCode()) ^ entry.getValue().hashCode();
                }
            }
            return i;
        }

        private boolean equalsRepresentation(Object obj, Object obj2) {
            if (obj == obj2) {
                return true;
            }
            if (!this.annotationType.isInstance(obj2)) {
                return false;
            }
            if (Proxy.isProxyClass(obj2.getClass())) {
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(obj2);
                if (invocationHandler instanceof AnnotationInvocationHandler) {
                    return invocationHandler.equals(this);
                }
            }
            try {
                for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                    try {
                        if (!entry.getValue().represents(entry.getKey().invoke(obj2, NO_ARGUMENT))) {
                            return false;
                        }
                    } catch (RuntimeException unused) {
                        return false;
                    }
                }
                return true;
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not access annotation property", e);
            } catch (InvocationTargetException unused2) {
                return false;
            }
        }

        @CachedReturnPlugin.Enhance(HASH_CODE)
        public int hashCode() {
            int i;
            if (this.hashCode != 0) {
                i = 0;
            } else {
                int hashCode = (this.annotationType.hashCode() * 31) + this.values.hashCode();
                Iterator<Map.Entry<Method, AnnotationValue.Loaded<?>>> it = this.values.entrySet().iterator();
                while (it.hasNext()) {
                    hashCode = (hashCode * 31) + it.next().getValue().hashCode();
                }
                i = hashCode;
            }
            int i2 = i;
            if (i == 0) {
                i2 = this.hashCode;
            } else {
                this.hashCode = i2;
            }
            return i2;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnnotationInvocationHandler)) {
                return false;
            }
            AnnotationInvocationHandler annotationInvocationHandler = (AnnotationInvocationHandler) obj;
            if (!this.annotationType.equals(annotationInvocationHandler.annotationType)) {
                return false;
            }
            for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                if (!entry.getValue().equals(annotationInvocationHandler.values.get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$AbstractBase.class */
    public static abstract class AbstractBase implements AnnotationDescription {
        private static final Set<ElementType> DEFAULT_TARGET = new HashSet();
        private static final MethodDescription.InDefinedShape RETENTION_VALUE;
        private static final MethodDescription.InDefinedShape TARGET_VALUE;
        private transient /* synthetic */ int hashCode;

        static {
            for (ElementType elementType : ElementType.values()) {
                if (!elementType.name().equals("TYPE_PARAMETER")) {
                    DEFAULT_TARGET.add(elementType);
                }
            }
            RETENTION_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Retention.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
            TARGET_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Target.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public AnnotationValue<?, ?> getValue(String str) {
            MethodList filter = getAnnotationType().getDeclaredMethods().filter(ElementMatchers.named(str).and(ElementMatchers.takesArguments(0)).and(ElementMatchers.isPublic()).and(ElementMatchers.not(ElementMatchers.isStatic())));
            if (filter.size() == 1) {
                return getValue((MethodDescription.InDefinedShape) filter.getOnly());
            }
            throw new IllegalArgumentException("Unknown property of " + getAnnotationType() + ": " + str);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public RetentionPolicy getRetention() {
            Loadable ofType = getAnnotationType().getDeclaredAnnotations().ofType(Retention.class);
            return ofType == null ? RetentionPolicy.CLASS : (RetentionPolicy) ofType.getValue(RETENTION_VALUE).load(ClassLoadingStrategy.BOOTSTRAP_LOADER).resolve(RetentionPolicy.class);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public Set<ElementType> getElementTypes() {
            Loadable ofType = getAnnotationType().getDeclaredAnnotations().ofType(Target.class);
            if (ofType == null) {
                return Collections.unmodifiableSet(DEFAULT_TARGET);
            }
            return new HashSet(Arrays.asList((Object[]) ofType.getValue(TARGET_VALUE).load(ClassLoadingStrategy.BOOTSTRAP_LOADER).resolve(ElementType[].class)));
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public boolean isSupportedOn(ElementType elementType) {
            return isSupportedOn(elementType.name());
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public boolean isSupportedOn(String str) {
            Loadable ofType = getAnnotationType().getDeclaredAnnotations().ofType(Target.class);
            if (ofType == null) {
                if (str.equals("TYPE_USE")) {
                    return true;
                }
                Iterator<ElementType> it = DEFAULT_TARGET.iterator();
                while (it.hasNext()) {
                    if (it.next().name().equals(str)) {
                        return true;
                    }
                }
                return false;
            }
            for (EnumerationDescription enumerationDescription : (EnumerationDescription[]) ofType.getValue(TARGET_VALUE).resolve(EnumerationDescription[].class)) {
                if (enumerationDescription.getValue().equals(str)) {
                    return true;
                }
            }
            return false;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public boolean isInherited() {
            return getAnnotationType().getDeclaredAnnotations().isAnnotationPresent(Inherited.class);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public boolean isDocumented() {
            return getAnnotationType().getDeclaredAnnotations().isAnnotationPresent(Documented.class);
        }

        @CachedReturnPlugin.Enhance("hashCode")
        public int hashCode() {
            int i;
            if (this.hashCode != 0) {
                i = 0;
            } else {
                int i2 = 0;
                Iterator it = getAnnotationType().getDeclaredMethods().iterator();
                while (it.hasNext()) {
                    i2 += 31 * getValue((MethodDescription.InDefinedShape) it.next()).hashCode();
                }
                i = i2;
            }
            int i3 = i;
            if (i == 0) {
                i3 = this.hashCode;
            } else {
                this.hashCode = i3;
            }
            return i3;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnnotationDescription)) {
                return false;
            }
            AnnotationDescription annotationDescription = (AnnotationDescription) obj;
            TypeDescription annotationType = getAnnotationType();
            if (!annotationDescription.getAnnotationType().equals(annotationType)) {
                return false;
            }
            for (MethodDescription.InDefinedShape inDefinedShape : annotationType.getDeclaredMethods()) {
                if (!getValue(inDefinedShape).equals(annotationDescription.getValue(inDefinedShape))) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            TypeDescription annotationType = getAnnotationType();
            StringBuilder sb = new StringBuilder("@");
            RenderingDispatcher.CURRENT.appendType(sb, annotationType);
            sb.append('(');
            boolean z = true;
            for (MethodDescription.InDefinedShape inDefinedShape : annotationType.getDeclaredMethods()) {
                AnnotationValue<?, ?> value = getValue(inDefinedShape);
                if (value.getState() != AnnotationValue.State.UNDEFINED) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    RenderingDispatcher.CURRENT.appendPrefix(sb, inDefinedShape.getName(), annotationType.getDeclaredMethods().size());
                    sb.append(value);
                }
            }
            return sb.append(')').toString();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$ForLoadedAnnotation.class */
    public static class ForLoadedAnnotation<S extends Annotation> extends AbstractBase implements Loadable<S> {
        private static final Object[] NO_ARGUMENT;
        private final S annotation;
        private final Class<S> annotationType;
        private static final boolean ACCESS_CONTROLLER;

        static {
            try {
                Class.forName("java.security.AccessController", false, null);
                ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
            } catch (ClassNotFoundException unused) {
                ACCESS_CONTROLLER = false;
            } catch (SecurityException unused2) {
                ACCESS_CONTROLLER = true;
            }
            NO_ARGUMENT = new Object[0];
        }

        protected ForLoadedAnnotation(S s) {
            this(s, s.annotationType());
        }

        private ForLoadedAnnotation(S s, Class<S> cls) {
            this.annotation = s;
            this.annotationType = cls;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static <U extends Annotation> Loadable<U> of(U u) {
            return new ForLoadedAnnotation(u);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription.Loadable
        public S load() {
            return this.annotationType == this.annotation.annotationType() ? this.annotation : (S) AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, asValue(this.annotation));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v28, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object, java.lang.reflect.Method] */
        private static Map<String, AnnotationValue<?, ?>> asValue(Annotation annotation) {
            HashMap hashMap = new HashMap();
            Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
            int length = declaredMethods.length;
            for (int i = 0; i < length; i++) {
                InvocationTargetException invocationTargetException = declaredMethods[i];
                try {
                    invocationTargetException = hashMap.put(invocationTargetException.getName(), asValue(invocationTargetException.invoke(annotation, NO_ARGUMENT), invocationTargetException.getReturnType()));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + ((Object) invocationTargetException), e);
                } catch (InvocationTargetException e2) {
                    Throwable targetException = invocationTargetException.getTargetException();
                    if (targetException instanceof TypeNotPresentException) {
                        hashMap.put(invocationTargetException.getName(), new AnnotationValue.ForMissingType(((TypeNotPresentException) targetException).typeName()));
                    } else if (targetException instanceof EnumConstantNotPresentException) {
                        hashMap.put(invocationTargetException.getName(), new AnnotationValue.ForEnumerationDescription.WithUnknownConstant(new TypeDescription.ForLoadedType(((EnumConstantNotPresentException) targetException).enumType()), ((EnumConstantNotPresentException) targetException).constantName()));
                    } else if (targetException instanceof AnnotationTypeMismatchException) {
                        hashMap.put(invocationTargetException.getName(), new AnnotationValue.ForMismatchedType(new MethodDescription.ForLoadedMethod(((AnnotationTypeMismatchException) targetException).element()), ((AnnotationTypeMismatchException) targetException).foundType()));
                    } else if (!(targetException instanceof IncompleteAnnotationException)) {
                        throw new IllegalStateException("Cannot read " + ((Object) invocationTargetException), targetException);
                    }
                }
            }
            return hashMap;
        }

        public static AnnotationValue<?, ?> asValue(Object obj, Class<?> cls) {
            if (Enum.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForEnumerationDescription.of(new EnumerationDescription.ForLoadedEnumeration((Enum) obj));
            }
            if (Enum[].class.isAssignableFrom(cls)) {
                Enum[] enumArr = (Enum[]) obj;
                EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[enumArr.length];
                int i = 0;
                for (Enum r0 : enumArr) {
                    int i2 = i;
                    i++;
                    enumerationDescriptionArr[i2] = new EnumerationDescription.ForLoadedEnumeration(r0);
                }
                return AnnotationValue.ForDescriptionArray.of(TypeDescription.ForLoadedType.of(cls.getComponentType()), enumerationDescriptionArr);
            }
            if (Annotation.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForAnnotationDescription.of(TypeDescription.ForLoadedType.of(cls), asValue((Annotation) obj));
            }
            if (Annotation[].class.isAssignableFrom(cls)) {
                Annotation[] annotationArr = (Annotation[]) obj;
                AnnotationDescription[] annotationDescriptionArr = new AnnotationDescription[annotationArr.length];
                int i3 = 0;
                for (Annotation annotation : annotationArr) {
                    int i4 = i3;
                    i3++;
                    annotationDescriptionArr[i4] = new Latent(TypeDescription.ForLoadedType.of(cls.getComponentType()), asValue(annotation));
                }
                return AnnotationValue.ForDescriptionArray.of(TypeDescription.ForLoadedType.of(cls.getComponentType()), annotationDescriptionArr);
            }
            if (Class.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForTypeDescription.of(TypeDescription.ForLoadedType.of((Class) obj));
            }
            if (Class[].class.isAssignableFrom(cls)) {
                Class[] clsArr = (Class[]) obj;
                TypeDescription[] typeDescriptionArr = new TypeDescription[clsArr.length];
                int i5 = 0;
                for (Class cls2 : clsArr) {
                    int i6 = i5;
                    i5++;
                    typeDescriptionArr[i6] = TypeDescription.ForLoadedType.of(cls2);
                }
                return AnnotationValue.ForDescriptionArray.of(typeDescriptionArr);
            }
            return AnnotationValue.ForConstant.of(obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
        /* JADX WARN: Type inference failed for: r0v35, types: [net.bytebuddy.description.annotation.AnnotationValue<?, ?>, net.bytebuddy.description.annotation.AnnotationValue] */
        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should always be wrapped for clarity.")
        public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
            InvocationTargetException represents = inDefinedShape.getDeclaringType().represents(this.annotation.annotationType());
            if (represents == 0) {
                throw new IllegalArgumentException(inDefinedShape + " does not represent " + this.annotation.annotationType());
            }
            try {
                boolean isPublic = inDefinedShape.getDeclaringType().isPublic();
                Method loadedMethod = inDefinedShape instanceof MethodDescription.ForLoadedMethod ? ((MethodDescription.ForLoadedMethod) inDefinedShape).getLoadedMethod() : null;
                Method method = loadedMethod;
                if (loadedMethod == null || method.getDeclaringClass() != this.annotation.annotationType() || (!isPublic && !method.isAccessible())) {
                    method = this.annotation.annotationType().getMethod(inDefinedShape.getName(), new Class[0]);
                    if (!isPublic) {
                        doPrivileged(new SetAccessibleAction(method));
                    }
                }
                represents = asValue(method.invoke(this.annotation, NO_ARGUMENT), method.getReturnType()).filter(inDefinedShape);
                return represents;
            } catch (InvocationTargetException e) {
                Throwable targetException = represents.getTargetException();
                if (targetException instanceof TypeNotPresentException) {
                    return new AnnotationValue.ForMissingType(((TypeNotPresentException) targetException).typeName());
                }
                if (targetException instanceof EnumConstantNotPresentException) {
                    return new AnnotationValue.ForEnumerationDescription.WithUnknownConstant(new TypeDescription.ForLoadedType(((EnumConstantNotPresentException) targetException).enumType()), ((EnumConstantNotPresentException) targetException).constantName());
                }
                if (targetException instanceof AnnotationTypeMismatchException) {
                    return new AnnotationValue.ForMismatchedType(new MethodDescription.ForLoadedMethod(((AnnotationTypeMismatchException) targetException).element()), ((AnnotationTypeMismatchException) targetException).foundType());
                }
                if (!(targetException instanceof IncompleteAnnotationException)) {
                    throw new IllegalStateException("Error reading annotation property " + inDefinedShape, targetException);
                }
                return new AnnotationValue.ForMissingValue(new TypeDescription.ForLoadedType(((IncompleteAnnotationException) targetException).annotationType()), ((IncompleteAnnotationException) targetException).elementName());
            } catch (Exception e2) {
                throw new IllegalStateException("Cannot access annotation property " + inDefinedShape, e2);
            }
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
            if (this.annotation.annotationType().getName().equals(cls.getName())) {
                return cls == this.annotation.annotationType() ? this : new ForLoadedAnnotation(this.annotation, cls);
            }
            throw new IllegalArgumentException(cls + " does not represent " + this.annotation.annotationType());
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public TypeDescription getAnnotationType() {
            return TypeDescription.ForLoadedType.of(this.annotation.annotationType());
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$Latent.class */
    public static class Latent extends AbstractBase {
        private final TypeDescription annotationType;
        private final Map<String, ? extends AnnotationValue<?, ?>> annotationValues;

        /* JADX INFO: Access modifiers changed from: protected */
        public Latent(TypeDescription typeDescription, Map<String, ? extends AnnotationValue<?, ?>> map) {
            this.annotationType = typeDescription;
            this.annotationValues = map;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
            if (!inDefinedShape.getDeclaringType().equals(this.annotationType)) {
                throw new IllegalArgumentException("Not a property of " + this.annotationType + ": " + inDefinedShape);
            }
            AnnotationValue<?, ?> annotationValue = this.annotationValues.get(inDefinedShape.getName());
            if (annotationValue != null) {
                return annotationValue.filter(inDefinedShape);
            }
            AnnotationValue<?, ?> defaultValue = inDefinedShape.getDefaultValue();
            if (defaultValue != null) {
                return defaultValue;
            }
            return new AnnotationValue.ForMissingValue(this.annotationType, inDefinedShape.getName());
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public TypeDescription getAnnotationType() {
            return this.annotationType;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
            if (!this.annotationType.represents(cls)) {
                throw new IllegalArgumentException(cls + " does not represent " + this.annotationType);
            }
            return new Loadable<>(cls);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$Latent$Loadable.class */
        public class Loadable<S extends Annotation> extends AbstractBase implements Loadable<S> {
            private final Class<S> annotationType;

            protected Loadable(Class<S> cls) {
                this.annotationType = cls;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription.Loadable
            public S load() {
                return (S) AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, Latent.this.annotationValues);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription
            public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
                return Latent.this.getValue(inDefinedShape);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription
            public TypeDescription getAnnotationType() {
                return TypeDescription.ForLoadedType.of(this.annotationType);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription
            public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
                return Latent.this.prepare((Class) cls);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/description/annotation/AnnotationDescription$Builder.class */
    public static class Builder {
        private final TypeDescription annotationType;
        private final Map<String, AnnotationValue<?, ?>> annotationValues;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((Builder) obj).annotationType) && this.annotationValues.equals(((Builder) obj).annotationValues);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.annotationValues.hashCode();
        }

        protected Builder(TypeDescription typeDescription, Map<String, AnnotationValue<?, ?>> map) {
            this.annotationType = typeDescription;
            this.annotationValues = map;
        }

        public static Builder ofType(Class<? extends Annotation> cls) {
            return ofType(TypeDescription.ForLoadedType.of(cls));
        }

        public static Builder ofType(TypeDescription typeDescription) {
            if (!typeDescription.isAnnotation()) {
                throw new IllegalArgumentException("Not an annotation type: " + typeDescription);
            }
            return new Builder(typeDescription, Collections.emptyMap());
        }

        public Builder define(String str, AnnotationValue<?, ?> annotationValue) {
            MethodList filter = this.annotationType.getDeclaredMethods().filter(ElementMatchers.named(str));
            if (filter.isEmpty()) {
                throw new IllegalArgumentException(this.annotationType + " does not define a property named " + str);
            }
            HashMap hashMap = new HashMap(this.annotationValues);
            if (hashMap.put(((MethodDescription.InDefinedShape) filter.getOnly()).getName(), annotationValue) == null) {
                return new Builder(this.annotationType, hashMap);
            }
            throw new IllegalArgumentException("Property already defined: " + str);
        }

        public Builder define(String str, Enum<?> r8) {
            return define(str, new EnumerationDescription.ForLoadedEnumeration(r8));
        }

        public Builder define(String str, TypeDescription typeDescription, String str2) {
            return define(str, new EnumerationDescription.Latent(typeDescription, str2));
        }

        public Builder define(String str, EnumerationDescription enumerationDescription) {
            return define(str, AnnotationValue.ForEnumerationDescription.of(enumerationDescription));
        }

        public Builder define(String str, Annotation annotation) {
            return define(str, new ForLoadedAnnotation(annotation));
        }

        public Builder define(String str, AnnotationDescription annotationDescription) {
            return define(str, new AnnotationValue.ForAnnotationDescription(annotationDescription));
        }

        public Builder define(String str, Class<?> cls) {
            return define(str, TypeDescription.ForLoadedType.of(cls));
        }

        public Builder define(String str, TypeDescription typeDescription) {
            return define(str, AnnotationValue.ForTypeDescription.of(typeDescription));
        }

        public <T extends Enum<?>> Builder defineEnumerationArray(String str, Class<T> cls, T... tArr) {
            EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[tArr.length];
            int i = 0;
            for (T t : tArr) {
                int i2 = i;
                i++;
                enumerationDescriptionArr[i2] = new EnumerationDescription.ForLoadedEnumeration(t);
            }
            return defineEnumerationArray(str, TypeDescription.ForLoadedType.of(cls), enumerationDescriptionArr);
        }

        public Builder defineEnumerationArray(String str, TypeDescription typeDescription, String... strArr) {
            if (!typeDescription.isEnum()) {
                throw new IllegalArgumentException("Not an enumeration type: " + typeDescription);
            }
            EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                enumerationDescriptionArr[i] = new EnumerationDescription.Latent(typeDescription, strArr[i]);
            }
            return defineEnumerationArray(str, typeDescription, enumerationDescriptionArr);
        }

        public Builder defineEnumerationArray(String str, TypeDescription typeDescription, EnumerationDescription... enumerationDescriptionArr) {
            return define(str, AnnotationValue.ForDescriptionArray.of(typeDescription, enumerationDescriptionArr));
        }

        public <T extends Annotation> Builder defineAnnotationArray(String str, Class<T> cls, T... tArr) {
            return defineAnnotationArray(str, TypeDescription.ForLoadedType.of(cls), (AnnotationDescription[]) new AnnotationList.ForLoadedAnnotations(tArr).toArray(new AnnotationDescription[0]));
        }

        public Builder defineAnnotationArray(String str, TypeDescription typeDescription, AnnotationDescription... annotationDescriptionArr) {
            return define(str, AnnotationValue.ForDescriptionArray.of(typeDescription, annotationDescriptionArr));
        }

        public Builder defineTypeArray(String str, Class<?>... clsArr) {
            return defineTypeArray(str, (TypeDescription[]) new TypeList.ForLoadedTypes(clsArr).toArray(new TypeDescription[0]));
        }

        public Builder defineTypeArray(String str, TypeDescription... typeDescriptionArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForDescriptionArray.of(typeDescriptionArr));
        }

        public Builder define(String str, boolean z) {
            return define(str, AnnotationValue.ForConstant.of(z));
        }

        public Builder define(String str, byte b2) {
            return define(str, AnnotationValue.ForConstant.of(b2));
        }

        public Builder define(String str, char c) {
            return define(str, AnnotationValue.ForConstant.of(c));
        }

        public Builder define(String str, short s) {
            return define(str, AnnotationValue.ForConstant.of(s));
        }

        public Builder define(String str, int i) {
            return define(str, AnnotationValue.ForConstant.of(i));
        }

        public Builder define(String str, long j) {
            return define(str, AnnotationValue.ForConstant.of(j));
        }

        public Builder define(String str, float f) {
            return define(str, AnnotationValue.ForConstant.of(f));
        }

        public Builder define(String str, double d) {
            return define(str, AnnotationValue.ForConstant.of(d));
        }

        public Builder define(String str, String str2) {
            return define(str, AnnotationValue.ForConstant.of(str2));
        }

        public Builder defineArray(String str, boolean... zArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(zArr));
        }

        public Builder defineArray(String str, byte... bArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(bArr));
        }

        public Builder defineArray(String str, char... cArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(cArr));
        }

        public Builder defineArray(String str, short... sArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(sArr));
        }

        public Builder defineArray(String str, int... iArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(iArr));
        }

        public Builder defineArray(String str, long... jArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(jArr));
        }

        public Builder defineArray(String str, float... fArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(fArr));
        }

        public Builder defineArray(String str, double... dArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(dArr));
        }

        public Builder defineArray(String str, String... strArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(strArr));
        }

        public AnnotationDescription build() {
            for (MethodDescription.InDefinedShape inDefinedShape : this.annotationType.getDeclaredMethods()) {
                AnnotationValue<?, ?> annotationValue = this.annotationValues.get(inDefinedShape.getName());
                if (annotationValue == null && inDefinedShape.getDefaultValue() == null) {
                    throw new IllegalStateException("No value or default value defined for " + inDefinedShape.getName());
                }
                if (annotationValue != null && annotationValue.filter(inDefinedShape).getState() != AnnotationValue.State.RESOLVED) {
                    throw new IllegalStateException("Illegal annotation value for " + inDefinedShape + ": " + annotationValue);
                }
            }
            return new Latent(this.annotationType, this.annotationValues);
        }

        public AnnotationDescription build(boolean z) {
            return z ? build() : new Latent(this.annotationType, this.annotationValues);
        }
    }
}
