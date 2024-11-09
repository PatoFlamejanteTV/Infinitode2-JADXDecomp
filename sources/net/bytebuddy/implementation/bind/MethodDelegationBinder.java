package net.bytebuddy.implementation.bind;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder.class */
public interface MethodDelegationBinder {
    Record compile(MethodDescription methodDescription);

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$Record.class */
    public interface Record {
        MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, TerminationHandler terminationHandler, MethodInvoker methodInvoker, Assigner assigner);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$Record$Illegal.class */
        public enum Illegal implements Record {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.Record
            public final MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, TerminationHandler terminationHandler, MethodInvoker methodInvoker, Assigner assigner) {
                return MethodBinding.Illegal.INSTANCE;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$MethodInvoker.class */
    public interface MethodInvoker {
        StackManipulation invoke(MethodDescription methodDescription);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$MethodInvoker$Simple.class */
        public enum Simple implements MethodInvoker {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodInvoker
            public final StackManipulation invoke(MethodDescription methodDescription) {
                return MethodInvocation.invoke(methodDescription);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$MethodInvoker$Virtual.class */
        public static class Virtual implements MethodInvoker {
            private final TypeDescription typeDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Virtual) obj).typeDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.typeDescription.hashCode();
            }

            public Virtual(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodInvoker
            public StackManipulation invoke(MethodDescription methodDescription) {
                return MethodInvocation.invoke(methodDescription).virtual(this.typeDescription);
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$ParameterBinding.class */
    public interface ParameterBinding<T> extends StackManipulation {
        T getIdentificationToken();

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$ParameterBinding$Illegal.class */
        public enum Illegal implements ParameterBinding<Void> {
            INSTANCE;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding
            public final Void getIdentificationToken() {
                throw new IllegalStateException("An illegal binding does not define an identification token");
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final boolean isValid() {
                return false;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                throw new IllegalStateException("An illegal parameter binding must not be applied");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$ParameterBinding$Anonymous.class */
        public static class Anonymous implements ParameterBinding<Object> {

            @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
            private final Object anonymousToken = new Object();
            private final StackManipulation delegate;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.delegate.equals(((Anonymous) obj).delegate);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.delegate.hashCode();
            }

            public Anonymous(StackManipulation stackManipulation) {
                this.delegate = stackManipulation;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding
            public Object getIdentificationToken() {
                return this.anonymousToken;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return this.delegate.isValid();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                return this.delegate.apply(methodVisitor, context);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$ParameterBinding$Unique.class */
        public static class Unique<T> implements ParameterBinding<T> {
            private final T identificationToken;
            private final StackManipulation delegate;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.identificationToken.equals(((Unique) obj).identificationToken) && this.delegate.equals(((Unique) obj).delegate);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.identificationToken.hashCode()) * 31) + this.delegate.hashCode();
            }

            public Unique(StackManipulation stackManipulation, T t) {
                this.delegate = stackManipulation;
                this.identificationToken = t;
            }

            public static <S> Unique<S> of(StackManipulation stackManipulation, S s) {
                return new Unique<>(stackManipulation, s);
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding
            public T getIdentificationToken() {
                return this.identificationToken;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return this.delegate.isValid();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                return this.delegate.apply(methodVisitor, context);
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$MethodBinding.class */
    public interface MethodBinding extends StackManipulation {
        @MaybeNull
        Integer getTargetParameterIndex(Object obj);

        MethodDescription getTarget();

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$MethodBinding$Illegal.class */
        public enum Illegal implements MethodBinding {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
            public final Integer getTargetParameterIndex(Object obj) {
                throw new IllegalStateException("Method is not bound");
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
            public final MethodDescription getTarget() {
                throw new IllegalStateException("Method is not bound");
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final boolean isValid() {
                return false;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                throw new IllegalStateException("Cannot delegate to an unbound method");
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$MethodBinding$Builder.class */
        public static class Builder {
            private final MethodInvoker methodInvoker;
            private final MethodDescription candidate;
            private final List<StackManipulation> parameterStackManipulations;
            private final LinkedHashMap<Object, Integer> registeredTargetIndices = new LinkedHashMap<>();
            private int nextParameterIndex = 0;

            public Builder(MethodInvoker methodInvoker, MethodDescription methodDescription) {
                this.methodInvoker = methodInvoker;
                this.candidate = methodDescription;
                this.parameterStackManipulations = new ArrayList(methodDescription.getParameters().size());
            }

            public boolean append(ParameterBinding<?> parameterBinding) {
                this.parameterStackManipulations.add(parameterBinding);
                LinkedHashMap<Object, Integer> linkedHashMap = this.registeredTargetIndices;
                Object identificationToken = parameterBinding.getIdentificationToken();
                int i = this.nextParameterIndex;
                this.nextParameterIndex = i + 1;
                return linkedHashMap.put(identificationToken, Integer.valueOf(i)) == null;
            }

            public MethodBinding build(StackManipulation stackManipulation) {
                if (this.candidate.getParameters().size() != this.nextParameterIndex) {
                    throw new IllegalStateException("The number of parameters bound does not equal the target's number of parameters");
                }
                return new Build(this.candidate, this.registeredTargetIndices, this.methodInvoker.invoke(this.candidate), this.parameterStackManipulations, stackManipulation);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$MethodBinding$Builder$Build.class */
            protected static class Build implements MethodBinding {
                private final MethodDescription target;
                private final Map<?, Integer> registeredTargetIndices;
                private final StackManipulation methodInvocation;
                private final List<StackManipulation> parameterStackManipulations;
                private final StackManipulation terminatingStackManipulation;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.target.equals(((Build) obj).target) && this.registeredTargetIndices.equals(((Build) obj).registeredTargetIndices) && this.methodInvocation.equals(((Build) obj).methodInvocation) && this.parameterStackManipulations.equals(((Build) obj).parameterStackManipulations) && this.terminatingStackManipulation.equals(((Build) obj).terminatingStackManipulation);
                }

                public int hashCode() {
                    return (((((((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.registeredTargetIndices.hashCode()) * 31) + this.methodInvocation.hashCode()) * 31) + this.parameterStackManipulations.hashCode()) * 31) + this.terminatingStackManipulation.hashCode();
                }

                protected Build(MethodDescription methodDescription, Map<?, Integer> map, StackManipulation stackManipulation, List<StackManipulation> list, StackManipulation stackManipulation2) {
                    this.target = methodDescription;
                    this.registeredTargetIndices = new HashMap(map);
                    this.methodInvocation = stackManipulation;
                    this.parameterStackManipulations = new ArrayList(list);
                    this.terminatingStackManipulation = stackManipulation2;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public boolean isValid() {
                    boolean z = this.methodInvocation.isValid() && this.terminatingStackManipulation.isValid();
                    Iterator<StackManipulation> it = this.parameterStackManipulations.iterator();
                    while (z && it.hasNext()) {
                        z = it.next().isValid();
                    }
                    return z;
                }

                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
                @MaybeNull
                public Integer getTargetParameterIndex(Object obj) {
                    return this.registeredTargetIndices.get(obj);
                }

                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
                public MethodDescription getTarget() {
                    return this.target;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    return new StackManipulation.Compound((List<? extends StackManipulation>) CompoundList.of((List) this.parameterStackManipulations, Arrays.asList(this.methodInvocation, this.terminatingStackManipulation))).apply(methodVisitor, context);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$BindingResolver.class */
    public interface BindingResolver {
        MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$BindingResolver$Default.class */
        public enum Default implements BindingResolver {
            INSTANCE;

            private static final int ONLY = 0;
            private static final int LEFT = 0;
            private static final int RIGHT = 1;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver
            public final MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                return doResolve(ambiguityResolver, methodDescription, new ArrayList(list));
            }

            private MethodBinding doResolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                switch (list.size()) {
                    case 1:
                        return list.get(0);
                    case 2:
                        MethodBinding methodBinding = list.get(0);
                        MethodBinding methodBinding2 = list.get(1);
                        switch (ambiguityResolver.resolve(methodDescription, methodBinding, methodBinding2)) {
                            case LEFT:
                                return methodBinding;
                            case RIGHT:
                                return methodBinding2;
                            case AMBIGUOUS:
                            case UNKNOWN:
                                throw new IllegalArgumentException("Cannot resolve ambiguous delegation of " + methodDescription + " to " + methodBinding.getTarget() + " or " + methodBinding2.getTarget());
                            default:
                                throw new AssertionError();
                        }
                    default:
                        MethodBinding methodBinding3 = list.get(0);
                        MethodBinding methodBinding4 = list.get(1);
                        switch (ambiguityResolver.resolve(methodDescription, methodBinding3, methodBinding4)) {
                            case LEFT:
                                list.remove(1);
                                return doResolve(ambiguityResolver, methodDescription, list);
                            case RIGHT:
                                list.remove(0);
                                return doResolve(ambiguityResolver, methodDescription, list);
                            case AMBIGUOUS:
                            case UNKNOWN:
                                list.remove(1);
                                list.remove(0);
                                MethodBinding doResolve = doResolve(ambiguityResolver, methodDescription, list);
                                switch (ambiguityResolver.resolve(methodDescription, methodBinding3, doResolve).merge(ambiguityResolver.resolve(methodDescription, methodBinding4, doResolve))) {
                                    case LEFT:
                                    case AMBIGUOUS:
                                    case UNKNOWN:
                                        throw new IllegalArgumentException("Cannot resolve ambiguous delegation of " + methodDescription + " to " + methodBinding3.getTarget() + " or " + methodBinding4.getTarget());
                                    case RIGHT:
                                        return doResolve;
                                    default:
                                        throw new AssertionError();
                                }
                            default:
                                throw new IllegalStateException("Unexpected amount of targets: " + list.size());
                        }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$BindingResolver$Unique.class */
        public enum Unique implements BindingResolver {
            INSTANCE;

            private static final int ONLY = 0;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver
            public final MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                if (list.size() == 1) {
                    return list.get(0);
                }
                throw new IllegalStateException(methodDescription + " allowed for more than one binding: " + list);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$BindingResolver$StreamWriting.class */
        public static class StreamWriting implements BindingResolver {
            private final BindingResolver delegate;
            private final PrintStream printStream;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.delegate.equals(((StreamWriting) obj).delegate) && this.printStream.equals(((StreamWriting) obj).printStream);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.delegate.hashCode()) * 31) + this.printStream.hashCode();
            }

            public StreamWriting(BindingResolver bindingResolver, PrintStream printStream) {
                this.delegate = bindingResolver;
                this.printStream = printStream;
            }

            public static BindingResolver toSystemOut() {
                return toSystemOut(Default.INSTANCE);
            }

            public static BindingResolver toSystemOut(BindingResolver bindingResolver) {
                return new StreamWriting(bindingResolver, System.out);
            }

            public static BindingResolver toSystemError() {
                return toSystemError(Default.INSTANCE);
            }

            public static BindingResolver toSystemError(BindingResolver bindingResolver) {
                return new StreamWriting(bindingResolver, System.err);
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver
            public MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                MethodBinding resolve = this.delegate.resolve(ambiguityResolver, methodDescription, list);
                this.printStream.println("Binding " + methodDescription + " as delegation to " + resolve.getTarget());
                return resolve;
            }
        }
    }

    @SuppressFBWarnings(value = {"IC_SUPERCLASS_USES_SUBCLASS_DURING_INITIALIZATION"}, justification = "Safe initialization is implied.")
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$AmbiguityResolver.class */
    public interface AmbiguityResolver {
        public static final AmbiguityResolver DEFAULT = new Compound(BindingPriority.Resolver.INSTANCE, DeclaringTypeResolver.INSTANCE, ArgumentTypeResolver.INSTANCE, MethodNameEqualityResolver.INSTANCE, ParameterLengthResolver.INSTANCE);

        Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$AmbiguityResolver$Resolution.class */
        public enum Resolution {
            UNKNOWN(true),
            LEFT(false),
            RIGHT(false),
            AMBIGUOUS(true);

            private final boolean unresolved;

            Resolution(boolean z) {
                this.unresolved = z;
            }

            public final boolean isUnresolved() {
                return this.unresolved;
            }

            public final Resolution merge(Resolution resolution) {
                switch (this) {
                    case LEFT:
                    case RIGHT:
                        return (resolution == UNKNOWN || resolution == this) ? this : AMBIGUOUS;
                    case AMBIGUOUS:
                        return AMBIGUOUS;
                    case UNKNOWN:
                        return resolution;
                    default:
                        throw new AssertionError();
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$AmbiguityResolver$NoOp.class */
        public enum NoOp implements AmbiguityResolver {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
            public final Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2) {
                return Resolution.UNKNOWN;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$AmbiguityResolver$Directional.class */
        public enum Directional implements AmbiguityResolver {
            LEFT(true),
            RIGHT(false);

            private final boolean left;

            Directional(boolean z) {
                this.left = z;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
            public final Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2) {
                return this.left ? Resolution.LEFT : Resolution.RIGHT;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$AmbiguityResolver$Compound.class */
        public static class Compound implements AmbiguityResolver {
            private final List<AmbiguityResolver> ambiguityResolvers;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.ambiguityResolvers.equals(((Compound) obj).ambiguityResolvers);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.ambiguityResolvers.hashCode();
            }

            public Compound(AmbiguityResolver... ambiguityResolverArr) {
                this((List<? extends AmbiguityResolver>) Arrays.asList(ambiguityResolverArr));
            }

            public Compound(List<? extends AmbiguityResolver> list) {
                this.ambiguityResolvers = new ArrayList();
                for (AmbiguityResolver ambiguityResolver : list) {
                    if (ambiguityResolver instanceof Compound) {
                        this.ambiguityResolvers.addAll(((Compound) ambiguityResolver).ambiguityResolvers);
                    } else if (!(ambiguityResolver instanceof NoOp)) {
                        this.ambiguityResolvers.add(ambiguityResolver);
                    }
                }
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
            public Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2) {
                Resolution resolution = Resolution.UNKNOWN;
                Iterator<AmbiguityResolver> it = this.ambiguityResolvers.iterator();
                while (resolution.isUnresolved() && it.hasNext()) {
                    resolution = it.next().resolve(methodDescription, methodBinding, methodBinding2);
                }
                return resolution;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$TerminationHandler.class */
    public interface TerminationHandler {
        StackManipulation resolve(Assigner assigner, Assigner.Typing typing, MethodDescription methodDescription, MethodDescription methodDescription2);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$TerminationHandler$Default.class */
        public enum Default implements TerminationHandler {
            RETURNING { // from class: net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler.Default.1
                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler
                public final StackManipulation resolve(Assigner assigner, Assigner.Typing typing, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    TypeDescription.Generic returnType;
                    StackManipulation[] stackManipulationArr = new StackManipulation[2];
                    if (methodDescription2.isConstructor()) {
                        returnType = methodDescription2.getDeclaringType().asGenericType();
                    } else {
                        returnType = methodDescription2.getReturnType();
                    }
                    stackManipulationArr[0] = assigner.assign(returnType, methodDescription.getReturnType(), typing);
                    stackManipulationArr[1] = MethodReturn.of(methodDescription.getReturnType());
                    return new StackManipulation.Compound(stackManipulationArr);
                }
            },
            DROPPING { // from class: net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler.Default.2
                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler
                public final StackManipulation resolve(Assigner assigner, Assigner.Typing typing, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    TypeDefinition returnType;
                    if (methodDescription2.isConstructor()) {
                        returnType = methodDescription2.getDeclaringType();
                    } else {
                        returnType = methodDescription2.getReturnType();
                    }
                    return Removal.of(returnType);
                }
            };

            /* synthetic */ Default(byte b2) {
                this();
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/MethodDelegationBinder$Processor.class */
    public static class Processor implements Record {
        private final List<? extends Record> records;
        private final AmbiguityResolver ambiguityResolver;
        private final BindingResolver bindingResolver;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.records.equals(((Processor) obj).records) && this.ambiguityResolver.equals(((Processor) obj).ambiguityResolver) && this.bindingResolver.equals(((Processor) obj).bindingResolver);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.records.hashCode()) * 31) + this.ambiguityResolver.hashCode()) * 31) + this.bindingResolver.hashCode();
        }

        public Processor(List<? extends Record> list, AmbiguityResolver ambiguityResolver, BindingResolver bindingResolver) {
            this.records = list;
            this.ambiguityResolver = ambiguityResolver;
            this.bindingResolver = bindingResolver;
        }

        @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.Record
        public MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, TerminationHandler terminationHandler, MethodInvoker methodInvoker, Assigner assigner) {
            ArrayList arrayList = new ArrayList();
            Iterator<? extends Record> it = this.records.iterator();
            while (it.hasNext()) {
                MethodBinding bind = it.next().bind(target, methodDescription, terminationHandler, methodInvoker, assigner);
                if (bind.isValid()) {
                    arrayList.add(bind);
                }
            }
            if (arrayList.isEmpty()) {
                throw new IllegalArgumentException("None of " + this.records + " allows for delegation from " + methodDescription);
            }
            return this.bindingResolver.resolve(this.ambiguityResolver, methodDescription, arrayList);
        }
    }
}
