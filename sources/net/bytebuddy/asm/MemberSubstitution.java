package net.bytebuddy.asm;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.visitor.LocalVariableAwareMethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution.class */
public class MemberSubstitution implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
    private final MethodGraph.Compiler methodGraphCompiler;
    private final boolean strict;
    private final TypePoolResolver typePoolResolver;
    private final Replacement.Factory replacementFactory;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.strict == ((MemberSubstitution) obj).strict && this.methodGraphCompiler.equals(((MemberSubstitution) obj).methodGraphCompiler) && this.typePoolResolver.equals(((MemberSubstitution) obj).typePoolResolver) && this.replacementFactory.equals(((MemberSubstitution) obj).replacementFactory);
    }

    public int hashCode() {
        return (((((((getClass().hashCode() * 31) + this.methodGraphCompiler.hashCode()) * 31) + (this.strict ? 1 : 0)) * 31) + this.typePoolResolver.hashCode()) * 31) + this.replacementFactory.hashCode();
    }

    protected MemberSubstitution(boolean z) {
        this(MethodGraph.Compiler.DEFAULT, TypePoolResolver.OfImplicitPool.INSTANCE, z, Replacement.NoOp.INSTANCE);
    }

    protected MemberSubstitution(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory) {
        this.methodGraphCompiler = compiler;
        this.typePoolResolver = typePoolResolver;
        this.strict = z;
        this.replacementFactory = factory;
    }

    public static MemberSubstitution strict() {
        return new MemberSubstitution(true);
    }

    public static MemberSubstitution relaxed() {
        return new MemberSubstitution(false);
    }

    public WithoutSpecification element(ElementMatcher<? super ByteCodeElement> elementMatcher) {
        return new WithoutSpecification.ForMatchedByteCodeElement(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public WithoutSpecification.ForMatchedField field(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new WithoutSpecification.ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public WithoutSpecification.ForMatchedMethod method(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new WithoutSpecification.ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public WithoutSpecification constructor(ElementMatcher<? super MethodDescription> elementMatcher) {
        return invokable(ElementMatchers.isConstructor().and(elementMatcher));
    }

    public WithoutSpecification invokable(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new WithoutSpecification.ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public MemberSubstitution with(MethodGraph.Compiler compiler) {
        return new MemberSubstitution(compiler, this.typePoolResolver, this.strict, this.replacementFactory);
    }

    public MemberSubstitution with(TypePoolResolver typePoolResolver) {
        return new MemberSubstitution(this.methodGraphCompiler, typePoolResolver, this.strict, this.replacementFactory);
    }

    public AsmVisitorWrapper.ForDeclaredMethods on(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new AsmVisitorWrapper.ForDeclaredMethods().invokable(elementMatcher, this);
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
    public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
        TypePool resolve = this.typePoolResolver.resolve(typeDescription, methodDescription, typePool);
        return new SubstitutingMethodVisitor(methodVisitor, typeDescription, methodDescription, this.methodGraphCompiler, this.strict, this.replacementFactory.make(typeDescription, methodDescription, resolve), context, resolve, context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V11));
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$WithoutSpecification.class */
    public static abstract class WithoutSpecification {
        protected final MethodGraph.Compiler methodGraphCompiler;
        protected final TypePoolResolver typePoolResolver;
        protected final boolean strict;
        protected final Replacement.Factory replacementFactory;

        public abstract MemberSubstitution replaceWith(Substitution.Factory factory);

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.strict == ((WithoutSpecification) obj).strict && this.methodGraphCompiler.equals(((WithoutSpecification) obj).methodGraphCompiler) && this.typePoolResolver.equals(((WithoutSpecification) obj).typePoolResolver) && this.replacementFactory.equals(((WithoutSpecification) obj).replacementFactory);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.typePoolResolver.hashCode()) * 31) + (this.strict ? 1 : 0)) * 31) + this.replacementFactory.hashCode();
        }

        protected WithoutSpecification(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory) {
            this.methodGraphCompiler = compiler;
            this.typePoolResolver = typePoolResolver;
            this.strict = z;
            this.replacementFactory = factory;
        }

        public MemberSubstitution stub() {
            return replaceWith(Substitution.Stubbing.INSTANCE);
        }

        public MemberSubstitution replaceWith(Field field) {
            return replaceWith(new FieldDescription.ForLoadedField(field));
        }

        public MemberSubstitution replaceWith(FieldDescription fieldDescription) {
            return replaceWith(new Substitution.ForFieldAccess.OfGivenField(fieldDescription));
        }

        public MemberSubstitution replaceWithField(ElementMatcher<? super FieldDescription> elementMatcher) {
            return replaceWith(new Substitution.ForFieldAccess.OfMatchedField(elementMatcher));
        }

        public MemberSubstitution replaceWith(Method method) {
            return replaceWith(new MethodDescription.ForLoadedMethod(method));
        }

        public MemberSubstitution replaceWith(MethodDescription methodDescription) {
            if (!methodDescription.isMethod()) {
                throw new IllegalArgumentException("Cannot use " + methodDescription + " as a replacement");
            }
            return replaceWith(new Substitution.ForMethodInvocation.OfGivenMethod(methodDescription));
        }

        public MemberSubstitution replaceWithMethod(ElementMatcher<? super MethodDescription> elementMatcher) {
            return replaceWithMethod(elementMatcher, this.methodGraphCompiler);
        }

        public MemberSubstitution replaceWithMethod(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
            return replaceWith(new Substitution.ForMethodInvocation.OfMatchedMethod(elementMatcher, compiler));
        }

        public MemberSubstitution replaceWithInstrumentedMethod() {
            return replaceWith(Substitution.ForMethodInvocation.OfInstrumentedMethod.INSTANCE);
        }

        public MemberSubstitution replaceWithChain(Substitution.Chain.Step.Factory... factoryArr) {
            return replaceWithChain(Arrays.asList(factoryArr));
        }

        public MemberSubstitution replaceWithChain(List<? extends Substitution.Chain.Step.Factory> list) {
            return replaceWith(Substitution.Chain.withDefaultAssigner().executing(list));
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$WithoutSpecification$ForMatchedByteCodeElement.class */
        protected static class ForMatchedByteCodeElement extends WithoutSpecification {
            private final ElementMatcher<? super ByteCodeElement> matcher;

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.matcher.equals(((ForMatchedByteCodeElement) obj).matcher);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public int hashCode() {
                return (super.hashCode() * 31) + this.matcher.hashCode();
            }

            protected ForMatchedByteCodeElement(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super ByteCodeElement> elementMatcher) {
                super(compiler, typePoolResolver, z, factory);
                this.matcher = elementMatcher;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public MemberSubstitution replaceWith(Substitution.Factory factory) {
                return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Replacement.Factory.Compound(this.replacementFactory, Replacement.ForElementMatchers.Factory.of(this.matcher, factory)));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$WithoutSpecification$ForMatchedField.class */
        public static class ForMatchedField extends WithoutSpecification {
            private final ElementMatcher<? super FieldDescription.InDefinedShape> matcher;
            private final boolean matchRead;
            private final boolean matchWrite;

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.matchRead == ((ForMatchedField) obj).matchRead && this.matchWrite == ((ForMatchedField) obj).matchWrite && this.matcher.equals(((ForMatchedField) obj).matcher);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public int hashCode() {
                return (((((super.hashCode() * 31) + this.matcher.hashCode()) * 31) + (this.matchRead ? 1 : 0)) * 31) + (this.matchWrite ? 1 : 0);
            }

            protected ForMatchedField(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
                this(compiler, typePoolResolver, z, factory, elementMatcher, true, true);
            }

            protected ForMatchedField(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, boolean z2, boolean z3) {
                super(compiler, typePoolResolver, z, factory);
                this.matcher = elementMatcher;
                this.matchRead = z2;
                this.matchWrite = z3;
            }

            public WithoutSpecification onRead() {
                return new ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, this.matcher, true, false);
            }

            public WithoutSpecification onWrite() {
                return new ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, this.matcher, false, true);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public MemberSubstitution replaceWith(Substitution.Factory factory) {
                return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Replacement.Factory.Compound(this.replacementFactory, Replacement.ForElementMatchers.Factory.ofField(this.matcher, this.matchRead, this.matchWrite, factory)));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$WithoutSpecification$ForMatchedMethod.class */
        public static class ForMatchedMethod extends WithoutSpecification {
            private final ElementMatcher<? super MethodDescription> matcher;
            private final boolean includeVirtualCalls;
            private final boolean includeSuperCalls;

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.includeVirtualCalls == ((ForMatchedMethod) obj).includeVirtualCalls && this.includeSuperCalls == ((ForMatchedMethod) obj).includeSuperCalls && this.matcher.equals(((ForMatchedMethod) obj).matcher);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public int hashCode() {
                return (((((super.hashCode() * 31) + this.matcher.hashCode()) * 31) + (this.includeVirtualCalls ? 1 : 0)) * 31) + (this.includeSuperCalls ? 1 : 0);
            }

            protected ForMatchedMethod(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super MethodDescription> elementMatcher) {
                this(compiler, typePoolResolver, z, factory, elementMatcher, true, true);
            }

            protected ForMatchedMethod(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super MethodDescription> elementMatcher, boolean z2, boolean z3) {
                super(compiler, typePoolResolver, z, factory);
                this.matcher = elementMatcher;
                this.includeVirtualCalls = z2;
                this.includeSuperCalls = z3;
            }

            public WithoutSpecification onVirtualCall() {
                return new ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, ElementMatchers.isVirtual().and(this.matcher), true, false);
            }

            public WithoutSpecification onSuperCall() {
                return new ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, ElementMatchers.isVirtual().and(this.matcher), false, true);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public MemberSubstitution replaceWith(Substitution.Factory factory) {
                return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Replacement.Factory.Compound(this.replacementFactory, Replacement.ForElementMatchers.Factory.ofMethod(this.matcher, this.includeVirtualCalls, this.includeSuperCalls, factory)));
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$TypePoolResolver.class */
    public interface TypePoolResolver {
        TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$TypePoolResolver$OfImplicitPool.class */
        public enum OfImplicitPool implements TypePoolResolver {
            INSTANCE;

            @Override // net.bytebuddy.asm.MemberSubstitution.TypePoolResolver
            public final TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return typePool;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$TypePoolResolver$ForExplicitPool.class */
        public static class ForExplicitPool implements TypePoolResolver {
            private final TypePool typePool;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typePool.equals(((ForExplicitPool) obj).typePool);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.typePool.hashCode();
            }

            public ForExplicitPool(TypePool typePool) {
                this.typePool = typePool;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.TypePoolResolver
            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return this.typePool;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$TypePoolResolver$ForClassFileLocator.class */
        public static class ForClassFileLocator implements TypePoolResolver {
            private final ClassFileLocator classFileLocator;
            private final TypePool.Default.ReaderMode readerMode;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readerMode.equals(((ForClassFileLocator) obj).readerMode) && this.classFileLocator.equals(((ForClassFileLocator) obj).classFileLocator);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.classFileLocator.hashCode()) * 31) + this.readerMode.hashCode();
            }

            public ForClassFileLocator(ClassFileLocator classFileLocator) {
                this(classFileLocator, TypePool.Default.ReaderMode.FAST);
            }

            public ForClassFileLocator(ClassFileLocator classFileLocator, TypePool.Default.ReaderMode readerMode) {
                this.classFileLocator = classFileLocator;
                this.readerMode = readerMode;
            }

            public static TypePoolResolver of(@MaybeNull ClassLoader classLoader) {
                return new ForClassFileLocator(ClassFileLocator.ForClassLoader.of(classLoader));
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.TypePoolResolver
            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return new TypePool.Default(new TypePool.CacheProvider.Simple(), this.classFileLocator, this.readerMode, typePool);
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution.class */
    public interface Substitution {

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Factory.class */
        public interface Factory {
            Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool);
        }

        StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2, int i);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Stubbing.class */
        public enum Stubbing implements Substitution, Factory {
            INSTANCE;

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Factory
            public final Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return this;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public final StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2, int i) {
                ArrayList arrayList = new ArrayList(generic.size());
                for (int size = generic.size() - 1; size >= 0; size--) {
                    arrayList.add(Removal.of((TypeDefinition) generic.get(size)));
                }
                return new StackManipulation.Compound((List<? extends StackManipulation>) CompoundList.of(arrayList, DefaultValue.of(generic2.asErasure())));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForFieldAccess.class */
        public static class ForFieldAccess implements Substitution {
            private final TypeDescription instrumentedType;
            private final FieldResolver fieldResolver;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForFieldAccess) obj).instrumentedType) && this.fieldResolver.equals(((ForFieldAccess) obj).fieldResolver);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.fieldResolver.hashCode();
            }

            public ForFieldAccess(TypeDescription typeDescription, FieldResolver fieldResolver) {
                this.instrumentedType = typeDescription;
                this.fieldResolver = fieldResolver;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming declaring type for type member.")
            public StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2, int i) {
                FieldDescription resolve = this.fieldResolver.resolve(typeDescription, byteCodeElement, generic, generic2);
                if (!resolve.isAccessibleTo(this.instrumentedType)) {
                    throw new IllegalStateException(this.instrumentedType + " cannot access " + resolve);
                }
                if (generic2.represents(Void.TYPE)) {
                    if (generic.size() != (resolve.isStatic() ? 1 : 2)) {
                        throw new IllegalStateException("Cannot set " + resolve + " with " + generic);
                    }
                    if (resolve.isStatic() || ((TypeDescription.Generic) generic.get(0)).asErasure().isAssignableTo(resolve.getDeclaringType().asErasure())) {
                        if (!((TypeDescription.Generic) generic.get(resolve.isStatic() ? 0 : 1)).asErasure().isAssignableTo(resolve.getType().asErasure())) {
                            throw new IllegalStateException("Cannot set " + resolve + " to " + generic.get(resolve.isStatic() ? 0 : 1));
                        }
                        return FieldAccess.forField(resolve).write();
                    }
                    throw new IllegalStateException("Cannot set " + resolve + " on " + generic.get(0));
                }
                if (generic.size() != (resolve.isStatic() ? 0 : 1)) {
                    throw new IllegalStateException("Cannot set " + resolve + " with " + generic);
                }
                if (!resolve.isStatic() && !((TypeDescription.Generic) generic.get(0)).asErasure().isAssignableTo(resolve.getDeclaringType().asErasure())) {
                    throw new IllegalStateException("Cannot get " + resolve + " on " + generic.get(0));
                }
                if (!resolve.getType().asErasure().isAssignableTo(generic2.asErasure())) {
                    throw new IllegalStateException("Cannot get " + resolve + " as " + generic2);
                }
                return FieldAccess.forField(resolve).read();
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForFieldAccess$FieldResolver.class */
            public interface FieldResolver {
                FieldDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2);

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForFieldAccess$FieldResolver$Simple.class */
                public static class Simple implements FieldResolver {
                    private final FieldDescription fieldDescription;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Simple) obj).fieldDescription);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
                    }

                    public Simple(FieldDescription fieldDescription) {
                        this.fieldDescription = fieldDescription;
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.ForFieldAccess.FieldResolver
                    public FieldDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        return this.fieldDescription;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForFieldAccess$FieldResolver$ForElementMatcher.class */
                public static class ForElementMatcher implements FieldResolver {
                    private final TypeDescription instrumentedType;
                    private final ElementMatcher<? super FieldDescription> matcher;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForElementMatcher) obj).instrumentedType) && this.matcher.equals(((ForElementMatcher) obj).matcher);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.matcher.hashCode();
                    }

                    protected ForElementMatcher(TypeDescription typeDescription, ElementMatcher<? super FieldDescription> elementMatcher) {
                        this.instrumentedType = typeDescription;
                        this.matcher = elementMatcher;
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.ForFieldAccess.FieldResolver
                    public FieldDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        TypeDescription.Generic superClass;
                        if (generic.isEmpty()) {
                            throw new IllegalStateException("Cannot substitute parameterless instruction with " + byteCodeElement);
                        }
                        if (((TypeDescription.Generic) generic.get(0)).isPrimitive() || ((TypeDescription.Generic) generic.get(0)).isArray()) {
                            throw new IllegalStateException("Cannot access field on primitive or array type for " + byteCodeElement);
                        }
                        TypeDefinition typeDefinition = (TypeDefinition) ((TypeDescription.Generic) generic.get(0)).accept(new TypeDescription.Generic.Visitor.Substitutor.ForReplacement(this.instrumentedType));
                        do {
                            FieldList filter = typeDefinition.getDeclaredFields().filter(ElementMatchers.not(ElementMatchers.isStatic()).and(ElementMatchers.isVisibleTo(this.instrumentedType)).and(this.matcher));
                            if (filter.size() == 1) {
                                return (FieldDescription) filter.getOnly();
                            }
                            if (filter.size() > 1) {
                                throw new IllegalStateException("Ambiguous field location of " + filter);
                            }
                            superClass = typeDefinition.getSuperClass();
                            typeDefinition = superClass;
                        } while (superClass != null);
                        throw new IllegalStateException("Cannot locate field matching " + this.matcher + " on " + typeDescription);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForFieldAccess$OfGivenField.class */
            public static class OfGivenField implements Factory {
                private final FieldDescription fieldDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((OfGivenField) obj).fieldDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
                }

                public OfGivenField(FieldDescription fieldDescription) {
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Factory
                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForFieldAccess(typeDescription, new FieldResolver.Simple(this.fieldDescription));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForFieldAccess$OfMatchedField.class */
            public static class OfMatchedField implements Factory {
                private final ElementMatcher<? super FieldDescription> matcher;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((OfMatchedField) obj).matcher);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.matcher.hashCode();
                }

                public OfMatchedField(ElementMatcher<? super FieldDescription> elementMatcher) {
                    this.matcher = elementMatcher;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Factory
                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForFieldAccess(typeDescription, new FieldResolver.ForElementMatcher(typeDescription, this.matcher));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForMethodInvocation.class */
        public static class ForMethodInvocation implements Substitution {
            private static final int THIS_REFERENCE = 0;
            private final TypeDescription instrumentedType;
            private final MethodResolver methodResolver;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForMethodInvocation) obj).instrumentedType) && this.methodResolver.equals(((ForMethodInvocation) obj).methodResolver);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.methodResolver.hashCode();
            }

            public ForMethodInvocation(TypeDescription typeDescription, MethodResolver methodResolver) {
                this.instrumentedType = typeDescription;
                this.methodResolver = methodResolver;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2, int i) {
                TypeList.Generic explicit;
                MethodDescription resolve = this.methodResolver.resolve(typeDescription, byteCodeElement, generic, generic2);
                if (!resolve.isAccessibleTo(this.instrumentedType)) {
                    throw new IllegalStateException(this.instrumentedType + " cannot access " + resolve);
                }
                if (!resolve.isStatic()) {
                    explicit = new TypeList.Generic.Explicit((List<? extends TypeDefinition>) CompoundList.of(resolve.getDeclaringType(), resolve.getParameters().asTypeList()));
                } else {
                    explicit = resolve.getParameters().asTypeList();
                }
                TypeList.Generic generic3 = explicit;
                if (!resolve.getReturnType().asErasure().isAssignableTo(generic2.asErasure())) {
                    throw new IllegalStateException("Cannot assign return value of " + resolve + " to " + generic2);
                }
                if (generic3.size() != generic.size()) {
                    throw new IllegalStateException("Cannot invoke " + resolve + " on " + generic.size() + " parameters");
                }
                for (int i2 = 0; i2 < generic3.size(); i2++) {
                    if (!((TypeDescription.Generic) generic.get(i2)).asErasure().isAssignableTo(((TypeDescription.Generic) generic3.get(i2)).asErasure())) {
                        throw new IllegalStateException("Cannot invoke " + resolve + " on parameter " + i2 + " of type " + generic.get(i2));
                    }
                }
                if (resolve.isVirtual()) {
                    return MethodInvocation.invoke(resolve).virtual(((TypeDescription.Generic) generic3.get(0)).asErasure());
                }
                return MethodInvocation.invoke(resolve);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForMethodInvocation$MethodResolver.class */
            public interface MethodResolver {
                MethodDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2);

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForMethodInvocation$MethodResolver$Simple.class */
                public static class Simple implements MethodResolver {
                    private final MethodDescription methodDescription;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((Simple) obj).methodDescription);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.methodDescription.hashCode();
                    }

                    public Simple(MethodDescription methodDescription) {
                        this.methodDescription = methodDescription;
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.ForMethodInvocation.MethodResolver
                    public MethodDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        return this.methodDescription;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForMethodInvocation$MethodResolver$Matching.class */
                public static class Matching implements MethodResolver {
                    private final TypeDescription instrumentedType;
                    private final MethodGraph.Compiler methodGraphCompiler;
                    private final ElementMatcher<? super MethodDescription> matcher;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Matching) obj).instrumentedType) && this.methodGraphCompiler.equals(((Matching) obj).methodGraphCompiler) && this.matcher.equals(((Matching) obj).matcher);
                    }

                    public int hashCode() {
                        return (((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.matcher.hashCode();
                    }

                    public Matching(TypeDescription typeDescription, MethodGraph.Compiler compiler, ElementMatcher<? super MethodDescription> elementMatcher) {
                        this.instrumentedType = typeDescription;
                        this.methodGraphCompiler = compiler;
                        this.matcher = elementMatcher;
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.ForMethodInvocation.MethodResolver
                    public MethodDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        if (generic.isEmpty()) {
                            throw new IllegalStateException("Cannot substitute parameterless instruction with " + byteCodeElement);
                        }
                        if (((TypeDescription.Generic) generic.get(0)).isPrimitive() || ((TypeDescription.Generic) generic.get(0)).isArray()) {
                            throw new IllegalStateException("Cannot invoke method on primitive or array type for " + byteCodeElement);
                        }
                        TypeDefinition typeDefinition = (TypeDefinition) ((TypeDescription.Generic) generic.get(0)).accept(new TypeDescription.Generic.Visitor.Substitutor.ForReplacement(this.instrumentedType));
                        List of = CompoundList.of(this.methodGraphCompiler.compile(typeDefinition, this.instrumentedType).listNodes().asMethodList().filter(this.matcher), typeDefinition.getDeclaredMethods().filter(ElementMatchers.isPrivate().and(ElementMatchers.isVisibleTo(this.instrumentedType)).and(this.matcher)));
                        if (of.size() == 1) {
                            return (MethodDescription) of.get(0);
                        }
                        throw new IllegalStateException("Not exactly one method that matches " + this.matcher + ": " + of);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForMethodInvocation$OfInstrumentedMethod.class */
            enum OfInstrumentedMethod implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Factory
                public final Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForMethodInvocation(typeDescription, new MethodResolver.Simple(methodDescription));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForMethodInvocation$OfGivenMethod.class */
            public static class OfGivenMethod implements Factory {
                private final MethodDescription methodDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((OfGivenMethod) obj).methodDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.methodDescription.hashCode();
                }

                public OfGivenMethod(MethodDescription methodDescription) {
                    this.methodDescription = methodDescription;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Factory
                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForMethodInvocation(typeDescription, new MethodResolver.Simple(this.methodDescription));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$ForMethodInvocation$OfMatchedMethod.class */
            public static class OfMatchedMethod implements Factory {
                private final ElementMatcher<? super MethodDescription> matcher;
                private final MethodGraph.Compiler methodGraphCompiler;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((OfMatchedMethod) obj).matcher) && this.methodGraphCompiler.equals(((OfMatchedMethod) obj).methodGraphCompiler);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + this.methodGraphCompiler.hashCode();
                }

                public OfMatchedMethod(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
                    this.matcher = elementMatcher;
                    this.methodGraphCompiler = compiler;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Factory
                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForMethodInvocation(typeDescription, new MethodResolver.Matching(typeDescription, this.methodGraphCompiler, this.matcher));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Chain.class */
        public static class Chain implements Substitution {
            private final Assigner assigner;
            private final Assigner.Typing typing;
            private final List<Step> steps;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typing.equals(((Chain) obj).typing) && this.assigner.equals(((Chain) obj).assigner) && this.steps.equals(((Chain) obj).steps);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.assigner.hashCode()) * 31) + this.typing.hashCode()) * 31) + this.steps.hashCode();
            }

            protected Chain(Assigner assigner, Assigner.Typing typing, List<Step> list) {
                this.assigner = assigner;
                this.typing = typing;
                this.steps = list;
            }

            public static Factory withDefaultAssigner() {
                return with(Assigner.DEFAULT, Assigner.Typing.STATIC);
            }

            public static Factory with(Assigner assigner, Assigner.Typing typing) {
                return new Factory(assigner, typing, Collections.emptyList());
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2, int i) {
                ArrayList arrayList = new ArrayList(1 + generic.size() + (this.steps.size() << 1) + (generic2.represents(Void.TYPE) ? 0 : 2));
                HashMap hashMap = new HashMap();
                for (int size = generic.size() - 1; size >= 0; size--) {
                    arrayList.add(MethodVariableAccess.of((TypeDefinition) generic.get(size)).storeAt(i));
                    hashMap.put(Integer.valueOf(size), Integer.valueOf(i));
                    i += ((TypeDescription.Generic) generic.get(size)).getStackSize().getSize();
                }
                arrayList.add(DefaultValue.of(generic2));
                TypeDescription.Generic generic3 = generic2;
                Iterator<Step> it = this.steps.iterator();
                while (it.hasNext()) {
                    Step.Resolution resolve = it.next().resolve(typeDescription, byteCodeElement, generic, generic3, hashMap, i);
                    arrayList.add(resolve.getStackManipulation());
                    generic3 = resolve.getResultType();
                }
                arrayList.add(this.assigner.assign(generic3, generic2, this.typing));
                return new StackManipulation.Compound(arrayList);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Chain$Step.class */
            public interface Step {

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Chain$Step$Factory.class */
                public interface Factory {
                    Step make(Assigner assigner, Assigner.Typing typing, TypeDescription typeDescription, MethodDescription methodDescription);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Chain$Step$Resolution.class */
                public interface Resolution {
                    StackManipulation getStackManipulation();

                    TypeDescription.Generic getResultType();
                }

                Resolution resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2, Map<Integer, Integer> map, int i);

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Chain$Step$Simple.class */
                public static class Simple implements Step, Factory, Resolution {
                    private final StackManipulation stackManipulation;
                    private final TypeDescription.Generic resultType;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.stackManipulation.equals(((Simple) obj).stackManipulation) && this.resultType.equals(((Simple) obj).resultType);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.stackManipulation.hashCode()) * 31) + this.resultType.hashCode();
                    }

                    public Simple(StackManipulation stackManipulation, TypeDescription.Generic generic) {
                        this.stackManipulation = stackManipulation;
                        this.resultType = generic;
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.Factory
                    public Step make(Assigner assigner, Assigner.Typing typing, TypeDescription typeDescription, MethodDescription methodDescription) {
                        return this;
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step
                    public Resolution resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2, Map<Integer, Integer> map, int i) {
                        return typeDescription.represents(Void.TYPE) ? this : new Simple(new StackManipulation.Compound(Removal.of(typeDescription), this.stackManipulation), this.resultType);
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.Resolution
                    public StackManipulation getStackManipulation() {
                        return this.stackManipulation;
                    }

                    @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.Resolution
                    public TypeDescription.Generic getResultType() {
                        return this.resultType;
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Substitution$Chain$Factory.class */
            public static class Factory implements Factory {
                private final Assigner assigner;
                private final Assigner.Typing typing;
                private final List<Step.Factory> steps;

                protected Factory(Assigner assigner, Assigner.Typing typing, List<Step.Factory> list) {
                    this.assigner = assigner;
                    this.typing = typing;
                    this.steps = list;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Factory
                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    if (this.steps.isEmpty()) {
                        return Stubbing.INSTANCE;
                    }
                    ArrayList arrayList = new ArrayList(this.steps.size());
                    Iterator<Step.Factory> it = this.steps.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().make(this.assigner, this.typing, typeDescription, methodDescription));
                    }
                    return new Chain(this.assigner, this.typing, arrayList);
                }

                public Factory executing(Step.Factory... factoryArr) {
                    return executing(Arrays.asList(factoryArr));
                }

                public Factory executing(List<? extends Step.Factory> list) {
                    return new Factory(this.assigner, this.typing, CompoundList.of((List) this.steps, (List) list));
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement.class */
    protected interface Replacement {
        Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, FieldDescription.InDefinedShape inDefinedShape, boolean z);

        Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, TypeDescription typeDescription2, MethodDescription methodDescription2, InvocationType invocationType);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$Binding.class */
        public interface Binding {
            boolean isBound();

            StackManipulation make(TypeList.Generic generic, TypeDescription.Generic generic2, int i);

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$Binding$Unresolved.class */
            public enum Unresolved implements Binding {
                INSTANCE;

                @Override // net.bytebuddy.asm.MemberSubstitution.Replacement.Binding
                public final boolean isBound() {
                    return false;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Replacement.Binding
                public final StackManipulation make(TypeList.Generic generic, TypeDescription.Generic generic2, int i) {
                    throw new IllegalStateException("Cannot resolve unresolved binding");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$Binding$Resolved.class */
            public static class Resolved implements Binding {
                private final TypeDescription targetType;
                private final ByteCodeElement target;
                private final Substitution substitution;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.targetType.equals(((Resolved) obj).targetType) && this.target.equals(((Resolved) obj).target) && this.substitution.equals(((Resolved) obj).substitution);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.targetType.hashCode()) * 31) + this.target.hashCode()) * 31) + this.substitution.hashCode();
                }

                protected Resolved(TypeDescription typeDescription, ByteCodeElement byteCodeElement, Substitution substitution) {
                    this.targetType = typeDescription;
                    this.target = byteCodeElement;
                    this.substitution = substitution;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Replacement.Binding
                public boolean isBound() {
                    return true;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Replacement.Binding
                public StackManipulation make(TypeList.Generic generic, TypeDescription.Generic generic2, int i) {
                    return this.substitution.resolve(this.targetType, this.target, generic, generic2, i);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$Factory.class */
        public interface Factory {
            Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool);

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$Factory$Compound.class */
            public static class Compound implements Factory {
                private final List<Factory> factories;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.factories.equals(((Compound) obj).factories);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.factories.hashCode();
                }

                protected Compound(Factory... factoryArr) {
                    this((List<? extends Factory>) Arrays.asList(factoryArr));
                }

                protected Compound(List<? extends Factory> list) {
                    this.factories = new ArrayList();
                    for (Factory factory : list) {
                        if (factory instanceof Compound) {
                            this.factories.addAll(((Compound) factory).factories);
                        } else if (!(factory instanceof NoOp)) {
                            this.factories.add(factory);
                        }
                    }
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Replacement.Factory
                public Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<Factory> it = this.factories.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().make(typeDescription, methodDescription, typePool));
                    }
                    return new ForFirstBinding(arrayList);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$InvocationType.class */
        public enum InvocationType {
            VIRTUAL,
            SUPER,
            OTHER;

            protected static InvocationType of(int i, MethodDescription methodDescription) {
                switch (i) {
                    case 182:
                    case 185:
                        return VIRTUAL;
                    case 183:
                        return methodDescription.isVirtual() ? SUPER : OTHER;
                    case 184:
                    default:
                        return OTHER;
                }
            }

            protected final boolean matches(boolean z, boolean z2) {
                switch (this) {
                    case VIRTUAL:
                        return z;
                    case SUPER:
                        return z2;
                    default:
                        return true;
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$NoOp.class */
        public enum NoOp implements Replacement, Factory {
            INSTANCE;

            @Override // net.bytebuddy.asm.MemberSubstitution.Replacement.Factory
            public final Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return this;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Replacement
            public final Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                return Binding.Unresolved.INSTANCE;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Replacement
            public final Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, TypeDescription typeDescription2, MethodDescription methodDescription2, InvocationType invocationType) {
                return Binding.Unresolved.INSTANCE;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$ForElementMatchers.class */
        public static class ForElementMatchers implements Replacement {
            private final ElementMatcher<? super FieldDescription.InDefinedShape> fieldMatcher;
            private final ElementMatcher<? super MethodDescription> methodMatcher;
            private final boolean matchFieldRead;
            private final boolean matchFieldWrite;
            private final boolean includeVirtualCalls;
            private final boolean includeSuperCalls;
            private final Substitution substitution;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.matchFieldRead == ((ForElementMatchers) obj).matchFieldRead && this.matchFieldWrite == ((ForElementMatchers) obj).matchFieldWrite && this.includeVirtualCalls == ((ForElementMatchers) obj).includeVirtualCalls && this.includeSuperCalls == ((ForElementMatchers) obj).includeSuperCalls && this.fieldMatcher.equals(((ForElementMatchers) obj).fieldMatcher) && this.methodMatcher.equals(((ForElementMatchers) obj).methodMatcher) && this.substitution.equals(((ForElementMatchers) obj).substitution);
            }

            public int hashCode() {
                return (((((((((((((getClass().hashCode() * 31) + this.fieldMatcher.hashCode()) * 31) + this.methodMatcher.hashCode()) * 31) + (this.matchFieldRead ? 1 : 0)) * 31) + (this.matchFieldWrite ? 1 : 0)) * 31) + (this.includeVirtualCalls ? 1 : 0)) * 31) + (this.includeSuperCalls ? 1 : 0)) * 31) + this.substitution.hashCode();
            }

            protected ForElementMatchers(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, ElementMatcher<? super MethodDescription> elementMatcher2, boolean z, boolean z2, boolean z3, boolean z4, Substitution substitution) {
                this.fieldMatcher = elementMatcher;
                this.methodMatcher = elementMatcher2;
                this.matchFieldRead = z;
                this.matchFieldWrite = z2;
                this.includeVirtualCalls = z3;
                this.includeSuperCalls = z4;
                this.substitution = substitution;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Replacement
            public Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                if (!z ? this.matchFieldRead : this.matchFieldWrite) {
                    if (this.fieldMatcher.matches(inDefinedShape)) {
                        return new Binding.Resolved(inDefinedShape.getDeclaringType(), inDefinedShape, this.substitution);
                    }
                }
                return Binding.Unresolved.INSTANCE;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Replacement
            public Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, TypeDescription typeDescription2, MethodDescription methodDescription2, InvocationType invocationType) {
                return (invocationType.matches(this.includeVirtualCalls, this.includeSuperCalls) && this.methodMatcher.matches(methodDescription2)) ? new Binding.Resolved(typeDescription2, methodDescription2, this.substitution) : Binding.Unresolved.INSTANCE;
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$ForElementMatchers$Factory.class */
            protected static class Factory implements Factory {
                private final ElementMatcher<? super FieldDescription.InDefinedShape> fieldMatcher;
                private final ElementMatcher<? super MethodDescription> methodMatcher;
                private final boolean matchFieldRead;
                private final boolean matchFieldWrite;
                private final boolean includeVirtualCalls;
                private final boolean includeSuperCalls;
                private final Substitution.Factory substitutionFactory;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matchFieldRead == ((Factory) obj).matchFieldRead && this.matchFieldWrite == ((Factory) obj).matchFieldWrite && this.includeVirtualCalls == ((Factory) obj).includeVirtualCalls && this.includeSuperCalls == ((Factory) obj).includeSuperCalls && this.fieldMatcher.equals(((Factory) obj).fieldMatcher) && this.methodMatcher.equals(((Factory) obj).methodMatcher) && this.substitutionFactory.equals(((Factory) obj).substitutionFactory);
                }

                public int hashCode() {
                    return (((((((((((((getClass().hashCode() * 31) + this.fieldMatcher.hashCode()) * 31) + this.methodMatcher.hashCode()) * 31) + (this.matchFieldRead ? 1 : 0)) * 31) + (this.matchFieldWrite ? 1 : 0)) * 31) + (this.includeVirtualCalls ? 1 : 0)) * 31) + (this.includeSuperCalls ? 1 : 0)) * 31) + this.substitutionFactory.hashCode();
                }

                protected Factory(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, ElementMatcher<? super MethodDescription> elementMatcher2, boolean z, boolean z2, boolean z3, boolean z4, Substitution.Factory factory) {
                    this.fieldMatcher = elementMatcher;
                    this.methodMatcher = elementMatcher2;
                    this.matchFieldRead = z;
                    this.matchFieldWrite = z2;
                    this.includeVirtualCalls = z3;
                    this.includeSuperCalls = z4;
                    this.substitutionFactory = factory;
                }

                protected static Factory of(ElementMatcher<? super ByteCodeElement> elementMatcher, Substitution.Factory factory) {
                    return new Factory(elementMatcher, elementMatcher, true, true, true, true, factory);
                }

                protected static Factory ofField(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, boolean z, boolean z2, Substitution.Factory factory) {
                    return new Factory(elementMatcher, ElementMatchers.none(), z, z2, false, false, factory);
                }

                protected static Factory ofMethod(ElementMatcher<? super MethodDescription> elementMatcher, boolean z, boolean z2, Substitution.Factory factory) {
                    return new Factory(ElementMatchers.none(), elementMatcher, false, false, z, z2, factory);
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Replacement.Factory
                public Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForElementMatchers(this.fieldMatcher, this.methodMatcher, this.matchFieldRead, this.matchFieldWrite, this.includeVirtualCalls, this.includeSuperCalls, this.substitutionFactory.make(typeDescription, methodDescription, typePool));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$Replacement$ForFirstBinding.class */
        public static class ForFirstBinding implements Replacement {
            private final List<? extends Replacement> replacements;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.replacements.equals(((ForFirstBinding) obj).replacements);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.replacements.hashCode();
            }

            protected ForFirstBinding(List<? extends Replacement> list) {
                this.replacements = list;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Replacement
            public Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                Iterator<? extends Replacement> it = this.replacements.iterator();
                while (it.hasNext()) {
                    Binding bind = it.next().bind(typeDescription, methodDescription, inDefinedShape, z);
                    if (bind.isBound()) {
                        return bind;
                    }
                }
                return Binding.Unresolved.INSTANCE;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Replacement
            public Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, TypeDescription typeDescription2, MethodDescription methodDescription2, InvocationType invocationType) {
                Iterator<? extends Replacement> it = this.replacements.iterator();
                while (it.hasNext()) {
                    Binding bind = it.next().bind(typeDescription, methodDescription, typeDescription2, methodDescription2, invocationType);
                    if (bind.isBound()) {
                        return bind;
                    }
                }
                return Binding.Unresolved.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$SubstitutingMethodVisitor.class */
    public static class SubstitutingMethodVisitor extends LocalVariableAwareMethodVisitor {
        private final TypeDescription instrumentedType;
        private final MethodDescription instrumentedMethod;
        private final MethodGraph.Compiler methodGraphCompiler;
        private final boolean strict;
        private final Replacement replacement;
        private final Implementation.Context implementationContext;
        private final TypePool typePool;
        private final boolean virtualPrivateCalls;
        private int stackSizeBuffer;
        private int localVariableExtension;

        protected SubstitutingMethodVisitor(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodDescription methodDescription, MethodGraph.Compiler compiler, boolean z, Replacement replacement, Implementation.Context context, TypePool typePool, boolean z2) {
            super(methodVisitor, methodDescription);
            this.instrumentedType = typeDescription;
            this.instrumentedMethod = methodDescription;
            this.methodGraphCompiler = compiler;
            this.strict = z;
            this.replacement = replacement;
            this.implementationContext = context;
            this.typePool = typePool;
            this.virtualPrivateCalls = z2;
            this.stackSizeBuffer = 0;
            this.localVariableExtension = 0;
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitFieldInsn(int i, String str, String str2, String str3) {
            ElementMatcher.Junction failSafe;
            TypeList.Generic empty;
            TypeDescription.Generic type;
            TypePool.Resolution describe = this.typePool.describe(str.replace('/', '.'));
            if (describe.isResolved()) {
                FieldList<FieldDescription.InDefinedShape> declaredFields = describe.resolve().getDeclaredFields();
                if (this.strict) {
                    failSafe = ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3));
                } else {
                    failSafe = ElementMatchers.failSafe(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                }
                FieldList filter = declaredFields.filter(failSafe);
                if (!filter.isEmpty()) {
                    Replacement.Binding bind = this.replacement.bind(this.instrumentedType, this.instrumentedMethod, (FieldDescription.InDefinedShape) filter.getOnly(), i == 181 || i == 179);
                    if (bind.isBound()) {
                        switch (i) {
                            case 178:
                                empty = new TypeList.Generic.Empty();
                                type = ((FieldDescription.InDefinedShape) filter.getOnly()).getType();
                                break;
                            case 179:
                                empty = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) filter.getOnly()).getType());
                                type = TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE);
                                break;
                            case 180:
                                empty = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) filter.getOnly()).getDeclaringType());
                                type = ((FieldDescription.InDefinedShape) filter.getOnly()).getType();
                                break;
                            case 181:
                                empty = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) filter.getOnly()).getDeclaringType(), ((FieldDescription.InDefinedShape) filter.getOnly()).getType());
                                type = TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE);
                                break;
                            default:
                                throw new IllegalStateException("Unexpected opcode: " + i);
                        }
                        this.stackSizeBuffer = Math.max(this.stackSizeBuffer, bind.make(empty, type, getFreeOffset()).apply(new LocalVariableTracingMethodVisitor(this, this.mv, (byte) 0), this.implementationContext).getMaximalSize() - type.getStackSize().getSize());
                        return;
                    }
                } else if (this.strict) {
                    throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + "." + str2 + str3 + " using " + this.typePool);
                }
            } else if (this.strict) {
                throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + " using " + this.typePool);
            }
            super.visitFieldInsn(i, str, str2, str3);
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
            ElementMatcher.Junction failSafe;
            MethodList filter;
            ElementMatcher.Junction failSafe2;
            ElementMatcher.Junction failSafe3;
            ElementMatcher.Junction failSafe4;
            TypeList.Generic asTypeList;
            TypeDescription.Generic returnType;
            ElementMatcher.Junction failSafe5;
            TypePool.Resolution describe = this.typePool.describe(str.replace('/', '.'));
            if (describe.isResolved()) {
                if (i == 183 && str2.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = describe.resolve().getDeclaredMethods();
                    if (this.strict) {
                        failSafe5 = ElementMatchers.isConstructor().and(ElementMatchers.hasDescriptor(str3));
                    } else {
                        failSafe5 = ElementMatchers.failSafe(ElementMatchers.isConstructor().and(ElementMatchers.hasDescriptor(str3)));
                    }
                    filter = declaredMethods.filter(failSafe5);
                } else if (i == 184 || i == 183) {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods2 = describe.resolve().getDeclaredMethods();
                    if (this.strict) {
                        failSafe = ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3));
                    } else {
                        failSafe = ElementMatchers.failSafe(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                    }
                    filter = declaredMethods2.filter(failSafe);
                } else if (!this.virtualPrivateCalls) {
                    MethodList<?> asMethodList = this.methodGraphCompiler.compile((TypeDefinition) describe.resolve(), this.instrumentedType).listNodes().asMethodList();
                    if (this.strict) {
                        failSafe2 = ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3));
                    } else {
                        failSafe2 = ElementMatchers.failSafe(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                    }
                    filter = (MethodList) asMethodList.filter(failSafe2);
                } else {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods3 = describe.resolve().getDeclaredMethods();
                    if (this.strict) {
                        failSafe3 = ElementMatchers.isPrivate().and(ElementMatchers.not(ElementMatchers.isStatic())).and(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                    } else {
                        failSafe3 = ElementMatchers.failSafe(ElementMatchers.isPrivate().and(ElementMatchers.not(ElementMatchers.isStatic())).and(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3))));
                    }
                    MethodList filter2 = declaredMethods3.filter(failSafe3);
                    filter = filter2;
                    if (filter2.isEmpty()) {
                        MethodList<?> asMethodList2 = this.methodGraphCompiler.compile((TypeDefinition) describe.resolve(), this.instrumentedType).listNodes().asMethodList();
                        if (this.strict) {
                            failSafe4 = ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3));
                        } else {
                            failSafe4 = ElementMatchers.failSafe(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                        }
                        filter = (MethodList) asMethodList2.filter(failSafe4);
                    }
                }
                if (!filter.isEmpty()) {
                    Replacement.Binding bind = this.replacement.bind(this.instrumentedType, this.instrumentedMethod, describe.resolve(), (MethodDescription) filter.getOnly(), Replacement.InvocationType.of(i, (MethodDescription) filter.getOnly()));
                    if (bind.isBound()) {
                        int i2 = this.stackSizeBuffer;
                        if (((MethodDescription) filter.getOnly()).isStatic() || ((MethodDescription) filter.getOnly()).isConstructor()) {
                            asTypeList = ((MethodDescription) filter.getOnly()).getParameters().asTypeList();
                        } else {
                            asTypeList = new TypeList.Generic.Explicit((List<? extends TypeDefinition>) CompoundList.of(describe.resolve(), ((MethodDescription) filter.getOnly()).getParameters().asTypeList()));
                        }
                        if (((MethodDescription) filter.getOnly()).isConstructor()) {
                            returnType = ((MethodDescription) filter.getOnly()).getDeclaringType().asGenericType();
                        } else {
                            returnType = ((MethodDescription) filter.getOnly()).getReturnType();
                        }
                        this.stackSizeBuffer = Math.max(i2, bind.make(asTypeList, returnType, getFreeOffset()).apply(new LocalVariableTracingMethodVisitor(this, this.mv, (byte) 0), this.implementationContext).getMaximalSize() - (((MethodDescription) filter.getOnly()).isConstructor() ? StackSize.SINGLE : ((MethodDescription) filter.getOnly()).getReturnType().getStackSize()).getSize());
                        if (((MethodDescription) filter.getOnly()).isConstructor()) {
                            this.stackSizeBuffer = Math.max(this.stackSizeBuffer, new StackManipulation.Compound(Duplication.SINGLE.flipOver(TypeDescription.ForLoadedType.of(Object.class)), Removal.SINGLE, Removal.SINGLE, Duplication.SINGLE.flipOver(TypeDescription.ForLoadedType.of(Object.class)), Removal.SINGLE, Removal.SINGLE).apply(this.mv, this.implementationContext).getMaximalSize() + StackSize.SINGLE.getSize());
                            return;
                        }
                        return;
                    }
                } else if (this.strict) {
                    throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + "." + str2 + str3 + " using " + this.typePool);
                }
            } else if (this.strict) {
                throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + " using " + this.typePool);
            }
            super.visitMethodInsn(i, str, str2, str3, z);
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitMaxs(int i, int i2) {
            super.visitMaxs(i + this.stackSizeBuffer, Math.max(this.localVariableExtension, i2));
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberSubstitution$SubstitutingMethodVisitor$LocalVariableTracingMethodVisitor.class */
        private class LocalVariableTracingMethodVisitor extends MethodVisitor {
            /* synthetic */ LocalVariableTracingMethodVisitor(SubstitutingMethodVisitor substitutingMethodVisitor, MethodVisitor methodVisitor, byte b2) {
                this(methodVisitor);
            }

            private LocalVariableTracingMethodVisitor(MethodVisitor methodVisitor) {
                super(OpenedClassReader.ASM_API, methodVisitor);
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            @SuppressFBWarnings(value = {"SF_SWITCH_NO_DEFAULT"}, justification = "No action required on default option.")
            public void visitVarInsn(int i, int i2) {
                switch (i) {
                    case 54:
                    case 56:
                    case 58:
                        SubstitutingMethodVisitor.this.localVariableExtension = Math.max(SubstitutingMethodVisitor.this.localVariableExtension, i2 + 1);
                        break;
                    case 55:
                    case 57:
                        SubstitutingMethodVisitor.this.localVariableExtension = Math.max(SubstitutingMethodVisitor.this.localVariableExtension, i2 + 2);
                        break;
                }
                super.visitVarInsn(i, i2);
            }
        }
    }
}
