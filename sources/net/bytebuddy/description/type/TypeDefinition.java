package net.bytebuddy.description.type;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.nullability.UnknownNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/type/TypeDefinition.class */
public interface TypeDefinition extends Iterable<TypeDefinition>, ModifierReviewable.ForTypeDefinition, NamedElement {
    public static final String RAW_TYPES_PROPERTY = "net.bytebuddy.raw";

    TypeDescription.Generic asGenericType();

    TypeDescription asErasure();

    @MaybeNull
    TypeDescription.Generic getSuperClass();

    TypeList.Generic getInterfaces();

    FieldList<?> getDeclaredFields();

    MethodList<?> getDeclaredMethods();

    @MaybeNull
    TypeDefinition getComponentType();

    RecordComponentList<?> getRecordComponents();

    Sort getSort();

    String getTypeName();

    StackSize getStackSize();

    boolean isArray();

    boolean isRecord();

    boolean isPrimitive();

    boolean represents(Type type);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unexpected branching in enum static init block */
    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/TypeDefinition$Sort.class */
    public static final class Sort {
        public static final Sort NON_GENERIC;
        public static final Sort GENERIC_ARRAY;
        public static final Sort PARAMETERIZED;
        public static final Sort WILDCARD;
        public static final Sort VARIABLE;
        public static final Sort VARIABLE_SYMBOLIC;
        private static final AnnotatedType ANNOTATED_TYPE;
        private static final /* synthetic */ Sort[] $VALUES;
        private static final boolean ACCESS_CONTROLLER;

        @JavaDispatcher.Proxied("java.lang.reflect.AnnotatedType")
        /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/TypeDefinition$Sort$AnnotatedType.class */
        protected interface AnnotatedType {
            @JavaDispatcher.Instance
            @JavaDispatcher.Proxied("isInstance")
            boolean isInstance(AnnotatedElement annotatedElement);

            @JavaDispatcher.Proxied("getType")
            Type getType(AnnotatedElement annotatedElement);
        }

        public static Sort[] values() {
            return (Sort[]) $VALUES.clone();
        }

        public static Sort valueOf(String str) {
            return (Sort) Enum.valueOf(Sort.class, str);
        }

        private Sort(String str, int i) {
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
            NON_GENERIC = new Sort("NON_GENERIC", 0);
            GENERIC_ARRAY = new Sort("GENERIC_ARRAY", 1);
            PARAMETERIZED = new Sort("PARAMETERIZED", 2);
            WILDCARD = new Sort("WILDCARD", 3);
            VARIABLE = new Sort("VARIABLE", 4);
            VARIABLE_SYMBOLIC = new Sort("VARIABLE_SYMBOLIC", 5);
            $VALUES = new Sort[]{NON_GENERIC, GENERIC_ARRAY, PARAMETERIZED, WILDCARD, VARIABLE, VARIABLE_SYMBOLIC};
            ANNOTATED_TYPE = (AnnotatedType) doPrivileged(JavaDispatcher.of(AnnotatedType.class));
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        public static TypeDescription.Generic describe(Type type) {
            return describe(type, TypeDescription.Generic.AnnotationReader.NoOp.INSTANCE);
        }

        public static TypeDescription.Generic describeAnnotated(AnnotatedElement annotatedElement) {
            if (!ANNOTATED_TYPE.isInstance(annotatedElement)) {
                throw new IllegalArgumentException("Not an instance of AnnotatedType: " + annotatedElement);
            }
            return describe(ANNOTATED_TYPE.getType(annotatedElement), new TypeDescription.Generic.AnnotationReader.Delegator.Simple(annotatedElement));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static TypeDescription.Generic describe(Type type, TypeDescription.Generic.AnnotationReader annotationReader) {
            if (type instanceof Class) {
                return new TypeDescription.Generic.OfNonGenericType.ForLoadedType((Class) type, annotationReader);
            }
            if (type instanceof GenericArrayType) {
                return new TypeDescription.Generic.OfGenericArray.ForLoadedType((GenericArrayType) type, annotationReader);
            }
            if (type instanceof ParameterizedType) {
                return new TypeDescription.Generic.OfParameterizedType.ForLoadedType((ParameterizedType) type, annotationReader);
            }
            if (type instanceof TypeVariable) {
                return new TypeDescription.Generic.OfTypeVariable.ForLoadedType((TypeVariable) type, annotationReader);
            }
            if (type instanceof WildcardType) {
                return new TypeDescription.Generic.OfWildcardType.ForLoadedType((WildcardType) type, annotationReader);
            }
            throw new IllegalArgumentException("Unknown type: " + type);
        }

        public final boolean isNonGeneric() {
            return this == NON_GENERIC;
        }

        public final boolean isParameterized() {
            return this == PARAMETERIZED;
        }

        public final boolean isGenericArray() {
            return this == GENERIC_ARRAY;
        }

        public final boolean isWildcard() {
            return this == WILDCARD;
        }

        public final boolean isTypeVariable() {
            return this == VARIABLE || this == VARIABLE_SYMBOLIC;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/TypeDefinition$SuperClassIterator.class */
    public static class SuperClassIterator implements Iterator<TypeDefinition> {

        @UnknownNull
        private TypeDefinition nextClass;

        public SuperClassIterator(TypeDefinition typeDefinition) {
            this.nextClass = typeDefinition;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextClass != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public TypeDefinition next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of type hierarchy");
            }
            try {
                return this.nextClass;
            } finally {
                this.nextClass = this.nextClass.getSuperClass();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
