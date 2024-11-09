package net.bytebuddy.implementation.bind.annotation;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.Default;
import net.bytebuddy.implementation.bind.annotation.DefaultCall;
import net.bytebuddy.implementation.bind.annotation.DefaultMethod;
import net.bytebuddy.implementation.bind.annotation.Empty;
import net.bytebuddy.implementation.bind.annotation.FieldValue;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.StubValue;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder.class */
public class TargetMethodAnnotationDrivenBinder implements MethodDelegationBinder {
    private final DelegationProcessor delegationProcessor;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.delegationProcessor.equals(((TargetMethodAnnotationDrivenBinder) obj).delegationProcessor);
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.delegationProcessor.hashCode();
    }

    protected TargetMethodAnnotationDrivenBinder(DelegationProcessor delegationProcessor) {
        this.delegationProcessor = delegationProcessor;
    }

    public static MethodDelegationBinder of(List<? extends ParameterBinder<?>> list) {
        return new TargetMethodAnnotationDrivenBinder(DelegationProcessor.of(list));
    }

    @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder
    public MethodDelegationBinder.Record compile(MethodDescription methodDescription) {
        if (IgnoreForBinding.Verifier.check(methodDescription)) {
            return MethodDelegationBinder.Record.Illegal.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
        Iterator it = methodDescription.getParameters().iterator();
        while (it.hasNext()) {
            arrayList.add(this.delegationProcessor.prepare((ParameterDescription) it.next()));
        }
        return new Record(methodDescription, arrayList, RuntimeType.Verifier.check(methodDescription));
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$Record.class */
    protected static class Record implements MethodDelegationBinder.Record {
        private final MethodDescription candidate;
        private final List<DelegationProcessor.Handler> handlers;
        private final Assigner.Typing typing;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typing.equals(((Record) obj).typing) && this.candidate.equals(((Record) obj).candidate) && this.handlers.equals(((Record) obj).handlers);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.candidate.hashCode()) * 31) + this.handlers.hashCode()) * 31) + this.typing.hashCode();
        }

        protected Record(MethodDescription methodDescription, List<DelegationProcessor.Handler> list, Assigner.Typing typing) {
            this.candidate = methodDescription;
            this.handlers = list;
            this.typing = typing;
        }

        @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.Record
        public MethodDelegationBinder.MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, MethodDelegationBinder.TerminationHandler terminationHandler, MethodDelegationBinder.MethodInvoker methodInvoker, Assigner assigner) {
            if (!this.candidate.isAccessibleTo(target.getInstrumentedType())) {
                return MethodDelegationBinder.MethodBinding.Illegal.INSTANCE;
            }
            StackManipulation resolve = terminationHandler.resolve(assigner, this.typing, methodDescription, this.candidate);
            if (!resolve.isValid()) {
                return MethodDelegationBinder.MethodBinding.Illegal.INSTANCE;
            }
            MethodDelegationBinder.MethodBinding.Builder builder = new MethodDelegationBinder.MethodBinding.Builder(methodInvoker, this.candidate);
            Iterator<DelegationProcessor.Handler> it = this.handlers.iterator();
            while (it.hasNext()) {
                MethodDelegationBinder.ParameterBinding<?> bind = it.next().bind(methodDescription, target, assigner);
                if (!bind.isValid() || !builder.append(bind)) {
                    return MethodDelegationBinder.MethodBinding.Illegal.INSTANCE;
                }
            }
            return builder.build(resolve);
        }

        public String toString() {
            return this.candidate.toString();
        }
    }

    @SuppressFBWarnings(value = {"IC_SUPERCLASS_USES_SUBCLASS_DURING_INITIALIZATION"}, justification = "Safe initialization is implied.")
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$ParameterBinder.class */
    public interface ParameterBinder<T extends Annotation> {
        public static final List<ParameterBinder<?>> DEFAULTS = Collections.unmodifiableList(Arrays.asList(Argument.Binder.INSTANCE, AllArguments.Binder.INSTANCE, Origin.Binder.INSTANCE, This.Binder.INSTANCE, Super.Binder.INSTANCE, Default.Binder.INSTANCE, SuperCall.Binder.INSTANCE, DefaultCall.Binder.INSTANCE, SuperMethod.Binder.INSTANCE, DefaultMethod.Binder.INSTANCE, FieldValue.Binder.INSTANCE, StubValue.Binder.INSTANCE, Empty.Binder.INSTANCE));

        Class<T> getHandledType();

        MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<T> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$ParameterBinder$ForFixedValue.class */
        public static abstract class ForFixedValue<S extends Annotation> implements ParameterBinder<S> {
            @MaybeNull
            protected abstract Object bind(AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription);

            @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
            public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
                Object javaConstantValue;
                TypeDescription typeDescription;
                Object bind = bind(loadable, methodDescription, parameterDescription);
                if (bind == null) {
                    return new MethodDelegationBinder.ParameterBinding.Anonymous(DefaultValue.of(parameterDescription.getType()));
                }
                if (bind instanceof Boolean) {
                    javaConstantValue = IntegerConstant.forValue(((Boolean) bind).booleanValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Boolean.TYPE);
                } else if (bind instanceof Byte) {
                    javaConstantValue = IntegerConstant.forValue(((Byte) bind).byteValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Byte.TYPE);
                } else if (bind instanceof Short) {
                    javaConstantValue = IntegerConstant.forValue(((Short) bind).shortValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Short.TYPE);
                } else if (bind instanceof Character) {
                    javaConstantValue = IntegerConstant.forValue(((Character) bind).charValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Character.TYPE);
                } else if (bind instanceof Integer) {
                    javaConstantValue = IntegerConstant.forValue(((Integer) bind).intValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Integer.TYPE);
                } else if (bind instanceof Long) {
                    javaConstantValue = LongConstant.forValue(((Long) bind).longValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Long.TYPE);
                } else if (bind instanceof Float) {
                    javaConstantValue = FloatConstant.forValue(((Float) bind).floatValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Float.TYPE);
                } else if (bind instanceof Double) {
                    javaConstantValue = DoubleConstant.forValue(((Double) bind).doubleValue());
                    typeDescription = TypeDescription.ForLoadedType.of(Double.TYPE);
                } else if (bind instanceof String) {
                    javaConstantValue = new TextConstant((String) bind);
                    typeDescription = TypeDescription.ForLoadedType.of(String.class);
                } else if (bind instanceof Class) {
                    javaConstantValue = ClassConstant.of(TypeDescription.ForLoadedType.of((Class) bind));
                    typeDescription = TypeDescription.ForLoadedType.of(Class.class);
                } else if (bind instanceof TypeDescription) {
                    javaConstantValue = ClassConstant.of((TypeDescription) bind);
                    typeDescription = TypeDescription.ForLoadedType.of(Class.class);
                } else if (bind instanceof Enum) {
                    javaConstantValue = FieldAccess.forEnumeration(new EnumerationDescription.ForLoadedEnumeration((Enum) bind));
                    typeDescription = TypeDescription.ForLoadedType.of(((Enum) bind).getDeclaringClass());
                } else if (!(bind instanceof EnumerationDescription)) {
                    if (JavaType.METHOD_HANDLE.isInstance(bind)) {
                        javaConstantValue = new JavaConstantValue(JavaConstant.MethodHandle.ofLoaded(bind));
                        typeDescription = JavaType.METHOD_HANDLE.getTypeStub();
                    } else if (!(bind instanceof JavaConstant.MethodHandle)) {
                        if (JavaType.METHOD_TYPE.isInstance(bind)) {
                            javaConstantValue = new JavaConstantValue(JavaConstant.MethodType.ofLoaded(bind));
                            typeDescription = JavaType.METHOD_HANDLE.getTypeStub();
                        } else if (bind instanceof JavaConstant) {
                            javaConstantValue = new JavaConstantValue((JavaConstant) bind);
                            typeDescription = ((JavaConstant) bind).getTypeDescription();
                        } else {
                            throw new IllegalStateException("Not able to save in class's constant pool: " + bind);
                        }
                    } else {
                        javaConstantValue = new JavaConstantValue((JavaConstant.MethodHandle) bind);
                        typeDescription = JavaType.METHOD_HANDLE.getTypeStub();
                    }
                } else {
                    javaConstantValue = FieldAccess.forEnumeration((EnumerationDescription) bind);
                    typeDescription = ((EnumerationDescription) bind).getEnumerationType();
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new StackManipulation.Compound(javaConstantValue, assigner.assign(typeDescription.asGenericType(), parameterDescription.getType(), typing)));
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$ParameterBinder$ForFixedValue$OfConstant.class */
            public static class OfConstant<U extends Annotation> extends ForFixedValue<U> {
                private final Class<U> type;

                @MaybeNull
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                private final Object value;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass() || !this.type.equals(((OfConstant) obj).type)) {
                        return false;
                    }
                    Object obj2 = this.value;
                    Object obj3 = ((OfConstant) obj).value;
                    return obj3 != null ? obj2 != null && obj2.equals(obj3) : obj2 == null;
                }

                public int hashCode() {
                    int hashCode = ((getClass().hashCode() * 31) + this.type.hashCode()) * 31;
                    Object obj = this.value;
                    return obj != null ? hashCode + obj.hashCode() : hashCode;
                }

                protected OfConstant(Class<U> cls, @MaybeNull Object obj) {
                    this.type = cls;
                    this.value = obj;
                }

                public static <V extends Annotation> ParameterBinder<V> of(Class<V> cls, @MaybeNull Object obj) {
                    return new OfConstant(cls, obj);
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
                public Class<U> getHandledType() {
                    return this.type;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFixedValue
                @MaybeNull
                protected Object bind(AnnotationDescription.Loadable<U> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription) {
                    return this.value;
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$ParameterBinder$ForFieldBinding.class */
        public static abstract class ForFieldBinding<S extends Annotation> implements ParameterBinder<S> {
            protected static final String BEAN_PROPERTY = "";

            protected abstract String fieldName(AnnotationDescription.Loadable<S> loadable);

            protected abstract TypeDescription declaringType(AnnotationDescription.Loadable<S> loadable);

            protected abstract MethodDelegationBinder.ParameterBinding<?> bind(FieldDescription fieldDescription, AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner);

            private static FieldLocator.Resolution resolveAccessor(FieldLocator fieldLocator, MethodDescription methodDescription) {
                String substring;
                if (ElementMatchers.isSetter().matches(methodDescription)) {
                    substring = methodDescription.getInternalName().substring(3);
                } else if (ElementMatchers.isGetter().matches(methodDescription)) {
                    substring = methodDescription.getInternalName().substring(methodDescription.getInternalName().startsWith("is") ? 2 : 3);
                } else {
                    return FieldLocator.Resolution.Illegal.INSTANCE;
                }
                return fieldLocator.locate(Character.toLowerCase(substring.charAt(0)) + substring.substring(1));
            }

            @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
            public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
                FieldLocator forExactType;
                FieldLocator.Resolution locate;
                if (!declaringType(loadable).represents(Void.TYPE)) {
                    if (declaringType(loadable).isPrimitive() || declaringType(loadable).isArray()) {
                        throw new IllegalStateException("A primitive type or array type cannot declare a field: " + methodDescription);
                    }
                    if (!target.getInstrumentedType().isAssignableTo(declaringType(loadable))) {
                        return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
                    }
                }
                if (declaringType(loadable).represents(Void.TYPE)) {
                    forExactType = new FieldLocator.ForClassHierarchy(target.getInstrumentedType());
                } else {
                    forExactType = new FieldLocator.ForExactType(declaringType(loadable), target.getInstrumentedType());
                }
                FieldLocator fieldLocator = forExactType;
                if (fieldName(loadable).equals("")) {
                    locate = resolveAccessor(fieldLocator, methodDescription);
                } else {
                    locate = fieldLocator.locate(fieldName(loadable));
                }
                FieldLocator.Resolution resolution = locate;
                return (!locate.isResolved() || (methodDescription.isStatic() && !resolution.getField().isStatic())) ? MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE : bind(resolution.getField(), loadable, methodDescription, parameterDescription, target, assigner);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$DelegationProcessor.class */
    protected static class DelegationProcessor {
        private final Map<? extends TypeDescription, ? extends ParameterBinder<?>> parameterBinders;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.parameterBinders.equals(((DelegationProcessor) obj).parameterBinders);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.parameterBinders.hashCode();
        }

        protected DelegationProcessor(Map<? extends TypeDescription, ? extends ParameterBinder<?>> map) {
            this.parameterBinders = map;
        }

        protected static DelegationProcessor of(List<? extends ParameterBinder<?>> list) {
            HashMap hashMap = new HashMap();
            for (ParameterBinder<?> parameterBinder : list) {
                if (hashMap.put(TypeDescription.ForLoadedType.of(parameterBinder.getHandledType()), parameterBinder) != null) {
                    throw new IllegalArgumentException("Attempt to bind two handlers to " + parameterBinder.getHandledType());
                }
            }
            return new DelegationProcessor(hashMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v18, types: [net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder$DelegationProcessor$Handler] */
        protected Handler prepare(ParameterDescription parameterDescription) {
            Assigner.Typing check = RuntimeType.Verifier.check(parameterDescription);
            Handler.Unbound unbound = new Handler.Unbound(parameterDescription, check);
            for (AnnotationDescription annotationDescription : parameterDescription.getDeclaredAnnotations()) {
                ParameterBinder<?> parameterBinder = this.parameterBinders.get(annotationDescription.getAnnotationType());
                if (parameterBinder != null && unbound.isBound()) {
                    throw new IllegalStateException("Ambiguous binding for parameter annotated with two handled annotation types");
                }
                if (parameterBinder != null) {
                    unbound = Handler.Bound.of(parameterDescription, parameterBinder, annotationDescription, check);
                }
            }
            return unbound;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$DelegationProcessor$Handler.class */
        public interface Handler {
            boolean isBound();

            MethodDelegationBinder.ParameterBinding<?> bind(MethodDescription methodDescription, Implementation.Target target, Assigner assigner);

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$DelegationProcessor$Handler$Unbound.class */
            public static class Unbound implements Handler {
                private final ParameterDescription target;
                private final Assigner.Typing typing;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typing.equals(((Unbound) obj).typing) && this.target.equals(((Unbound) obj).target);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.typing.hashCode();
                }

                protected Unbound(ParameterDescription parameterDescription, Assigner.Typing typing) {
                    this.target = parameterDescription;
                    this.typing = typing;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public boolean isBound() {
                    return false;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public MethodDelegationBinder.ParameterBinding<?> bind(MethodDescription methodDescription, Implementation.Target target, Assigner assigner) {
                    return Argument.Binder.INSTANCE.bind(AnnotationDescription.ForLoadedAnnotation.of(new DefaultArgument(this.target.getIndex())), methodDescription, this.target, target, assigner, this.typing);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$DelegationProcessor$Handler$Unbound$DefaultArgument.class */
                protected static class DefaultArgument implements Argument {
                    private static final String VALUE = "value";
                    private static final String BINDING_MECHANIC = "bindingMechanic";
                    private final int parameterIndex;

                    protected DefaultArgument(int i) {
                        this.parameterIndex = i;
                    }

                    @Override // net.bytebuddy.implementation.bind.annotation.Argument
                    public int value() {
                        return this.parameterIndex;
                    }

                    @Override // net.bytebuddy.implementation.bind.annotation.Argument
                    public Argument.BindingMechanic bindingMechanic() {
                        return Argument.BindingMechanic.UNIQUE;
                    }

                    @Override // java.lang.annotation.Annotation
                    public Class<Argument> annotationType() {
                        return Argument.class;
                    }

                    @Override // java.lang.annotation.Annotation
                    public int hashCode() {
                        return ((127 * BINDING_MECHANIC.hashCode()) ^ Argument.BindingMechanic.UNIQUE.hashCode()) + ((127 * VALUE.hashCode()) ^ this.parameterIndex);
                    }

                    @Override // java.lang.annotation.Annotation
                    public boolean equals(@MaybeNull Object obj) {
                        if (this != obj) {
                            return (obj instanceof Argument) && this.parameterIndex == ((Argument) obj).value();
                        }
                        return true;
                    }

                    @Override // java.lang.annotation.Annotation
                    public String toString() {
                        return "@" + Argument.class.getName() + "(bindingMechanic=" + Argument.BindingMechanic.UNIQUE + ", value=" + this.parameterIndex + ")";
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/annotation/TargetMethodAnnotationDrivenBinder$DelegationProcessor$Handler$Bound.class */
            public static class Bound<T extends Annotation> implements Handler {
                private final ParameterDescription target;
                private final ParameterBinder<T> parameterBinder;
                private final AnnotationDescription.Loadable<T> annotation;
                private final Assigner.Typing typing;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typing.equals(((Bound) obj).typing) && this.target.equals(((Bound) obj).target) && this.parameterBinder.equals(((Bound) obj).parameterBinder) && this.annotation.equals(((Bound) obj).annotation);
                }

                public int hashCode() {
                    return (((((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.parameterBinder.hashCode()) * 31) + this.annotation.hashCode()) * 31) + this.typing.hashCode();
                }

                protected Bound(ParameterDescription parameterDescription, ParameterBinder<T> parameterBinder, AnnotationDescription.Loadable<T> loadable, Assigner.Typing typing) {
                    this.target = parameterDescription;
                    this.parameterBinder = parameterBinder;
                    this.annotation = loadable;
                    this.typing = typing;
                }

                protected static Handler of(ParameterDescription parameterDescription, ParameterBinder<?> parameterBinder, AnnotationDescription annotationDescription, Assigner.Typing typing) {
                    return new Bound(parameterDescription, parameterBinder, annotationDescription.prepare(parameterBinder.getHandledType()), typing);
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public boolean isBound() {
                    return true;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public MethodDelegationBinder.ParameterBinding<?> bind(MethodDescription methodDescription, Implementation.Target target, Assigner assigner) {
                    return this.parameterBinder.bind(this.annotation, methodDescription, this.target, target, assigner, this.typing);
                }
            }
        }
    }
}
