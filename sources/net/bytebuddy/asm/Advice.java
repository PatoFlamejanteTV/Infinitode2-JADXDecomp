package net.bytebuddy.asm;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.RepeatedAnnotationPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.bytecode.Addition;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.Throw;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.SerializedConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;
import net.bytebuddy.utility.visitor.LineNumberPrependingMethodVisitor;
import net.bytebuddy.utility.visitor.StackAwareMethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice.class */
public class Advice implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper, Implementation {

    @AlwaysNull
    private static final ClassReader UNDEFINED = null;
    private static final MethodDescription.InDefinedShape SKIP_ON;
    private static final MethodDescription.InDefinedShape PREPEND_LINE_NUMBER;
    private static final MethodDescription.InDefinedShape INLINE_ENTER;
    private static final MethodDescription.InDefinedShape SUPPRESS_ENTER;
    private static final MethodDescription.InDefinedShape REPEAT_ON;
    private static final MethodDescription.InDefinedShape ON_THROWABLE;
    private static final MethodDescription.InDefinedShape BACKUP_ARGUMENTS;
    private static final MethodDescription.InDefinedShape INLINE_EXIT;
    private static final MethodDescription.InDefinedShape SUPPRESS_EXIT;
    private final Dispatcher.Resolved.ForMethodEnter methodEnter;
    private final Dispatcher.Resolved.ForMethodExit methodExit;
    private final Assigner assigner;
    private final ExceptionHandler exceptionHandler;
    private final Implementation delegate;

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AllArguments.class */
    public @interface AllArguments {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;

        boolean nullIfEmpty() default false;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Argument.class */
    public @interface Argument {
        int value();

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;

        boolean optional() default false;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Enter.class */
    public @interface Enter {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Exit.class */
    public @interface Exit {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$FieldValue.class */
    public @interface FieldValue {
        String value() default "";

        Class<?> declaringType() default void.class;

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Local.class */
    public @interface Local {
        String value();
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OnMethodEnter.class */
    public @interface OnMethodEnter {
        Class<?> skipOn() default void.class;

        boolean prependLineNumber() default true;

        boolean inline() default true;

        Class<? extends Throwable> suppress() default NoExceptionHandler.class;
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OnMethodExit.class */
    public @interface OnMethodExit {
        Class<?> repeatOn() default void.class;

        Class<? extends Throwable> onThrowable() default NoExceptionHandler.class;

        boolean backupArguments() default true;

        boolean inline() default true;

        Class<? extends Throwable> suppress() default NoExceptionHandler.class;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Origin.class */
    public @interface Origin {
        public static final String DEFAULT = "";

        String value() default "";
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Return.class */
    public @interface Return {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StubValue.class */
    public @interface StubValue {
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$This.class */
    public @interface This {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;

        boolean optional() default false;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Thrown.class */
    public @interface Thrown {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.DYNAMIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Unused.class */
    public @interface Unused {
    }

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.methodEnter.equals(((Advice) obj).methodEnter) && this.methodExit.equals(((Advice) obj).methodExit) && this.assigner.equals(((Advice) obj).assigner) && this.exceptionHandler.equals(((Advice) obj).exceptionHandler) && this.delegate.equals(((Advice) obj).delegate);
    }

    public int hashCode() {
        return (((((((((getClass().hashCode() * 31) + this.methodEnter.hashCode()) * 31) + this.methodExit.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.exceptionHandler.hashCode()) * 31) + this.delegate.hashCode();
    }

    static {
        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(OnMethodEnter.class).getDeclaredMethods();
        SKIP_ON = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("skipOn")).getOnly();
        PREPEND_LINE_NUMBER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("prependLineNumber")).getOnly();
        INLINE_ENTER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("inline")).getOnly();
        SUPPRESS_ENTER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("suppress")).getOnly();
        MethodList<MethodDescription.InDefinedShape> declaredMethods2 = TypeDescription.ForLoadedType.of(OnMethodExit.class).getDeclaredMethods();
        REPEAT_ON = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("repeatOn")).getOnly();
        ON_THROWABLE = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("onThrowable")).getOnly();
        BACKUP_ARGUMENTS = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("backupArguments")).getOnly();
        INLINE_EXIT = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("inline")).getOnly();
        SUPPRESS_EXIT = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("suppress")).getOnly();
    }

    protected Advice(Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit) {
        this(forMethodEnter, forMethodExit, Assigner.DEFAULT, ExceptionHandler.Default.SUPPRESSING, SuperMethodCall.INSTANCE);
    }

    private Advice(Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, Assigner assigner, ExceptionHandler exceptionHandler, Implementation implementation) {
        this.methodEnter = forMethodEnter;
        this.methodExit = forMethodExit;
        this.assigner = assigner;
        this.exceptionHandler = exceptionHandler;
        this.delegate = implementation;
    }

    public static Advice to(Class<?> cls) {
        return to(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public static Advice to(Class<?> cls, ClassFileLocator classFileLocator) {
        return to(TypeDescription.ForLoadedType.of(cls), classFileLocator);
    }

    public static Advice to(TypeDescription typeDescription) {
        return to(typeDescription, ClassFileLocator.NoOp.INSTANCE);
    }

    public static Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return to(typeDescription, PostProcessor.NoOp.INSTANCE, classFileLocator, Collections.emptyList(), Delegator.ForStaticInvocation.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v28, types: [net.bytebuddy.asm.Advice$Dispatcher$Unresolved] */
    protected static Advice to(TypeDescription typeDescription, PostProcessor.Factory factory, ClassFileLocator classFileLocator, List<? extends OffsetMapping.Factory<?>> list, Delegator delegator) {
        Dispatcher.Unresolved unresolved = Dispatcher.Inactive.INSTANCE;
        Dispatcher.Unresolved unresolved2 = Dispatcher.Inactive.INSTANCE;
        for (MethodDescription.InDefinedShape inDefinedShape : typeDescription.getDeclaredMethods()) {
            unresolved = locate(OnMethodEnter.class, INLINE_ENTER, unresolved, inDefinedShape, delegator);
            unresolved2 = locate(OnMethodExit.class, INLINE_EXIT, unresolved2, inDefinedShape, delegator);
        }
        if (!unresolved.isAlive() && !unresolved2.isAlive()) {
            throw new IllegalArgumentException("No advice defined by " + typeDescription);
        }
        try {
            ClassReader of = (unresolved.isBinary() || unresolved2.isBinary()) ? OpenedClassReader.of(classFileLocator.locate(typeDescription.getName()).resolve()) : UNDEFINED;
            return new Advice(unresolved.asMethodEnter(list, of, unresolved2, factory), unresolved2.asMethodExit(list, of, unresolved, factory));
        } catch (IOException e) {
            throw new IllegalStateException("Error reading class file of " + typeDescription, e);
        }
    }

    public static Advice to(Class<?> cls, Class<?> cls2) {
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = cls2.getClassLoader();
        return to(cls, cls2, classLoader == classLoader2 ? ClassFileLocator.ForClassLoader.of(classLoader) : new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader), ClassFileLocator.ForClassLoader.of(classLoader2)));
    }

    public static Advice to(Class<?> cls, Class<?> cls2, ClassFileLocator classFileLocator) {
        return to(TypeDescription.ForLoadedType.of(cls), TypeDescription.ForLoadedType.of(cls2), classFileLocator);
    }

    public static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2) {
        return to(typeDescription, typeDescription2, ClassFileLocator.NoOp.INSTANCE);
    }

    public static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator) {
        return to(typeDescription, typeDescription2, PostProcessor.NoOp.INSTANCE, classFileLocator, Collections.emptyList(), Delegator.ForStaticInvocation.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v29, types: [net.bytebuddy.asm.Advice$Dispatcher$Unresolved] */
    protected static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, PostProcessor.Factory factory, ClassFileLocator classFileLocator, List<? extends OffsetMapping.Factory<?>> list, Delegator delegator) {
        Dispatcher.Unresolved unresolved = Dispatcher.Inactive.INSTANCE;
        Dispatcher.Unresolved unresolved2 = Dispatcher.Inactive.INSTANCE;
        Iterator it = typeDescription.getDeclaredMethods().iterator();
        while (it.hasNext()) {
            unresolved = locate(OnMethodEnter.class, INLINE_ENTER, unresolved, (MethodDescription.InDefinedShape) it.next(), delegator);
        }
        if (!unresolved.isAlive()) {
            throw new IllegalArgumentException("No enter advice defined by " + typeDescription);
        }
        Iterator it2 = typeDescription2.getDeclaredMethods().iterator();
        while (it2.hasNext()) {
            unresolved2 = locate(OnMethodExit.class, INLINE_EXIT, unresolved2, (MethodDescription.InDefinedShape) it2.next(), delegator);
        }
        if (!unresolved2.isAlive()) {
            throw new IllegalArgumentException("No exit advice defined by " + typeDescription2);
        }
        try {
            return new Advice(unresolved.asMethodEnter(list, unresolved.isBinary() ? OpenedClassReader.of(classFileLocator.locate(typeDescription.getName()).resolve()) : UNDEFINED, unresolved2, factory), unresolved2.asMethodExit(list, unresolved2.isBinary() ? OpenedClassReader.of(classFileLocator.locate(typeDescription2.getName()).resolve()) : UNDEFINED, unresolved, factory));
        } catch (IOException e) {
            throw new IllegalStateException("Error reading class file of " + typeDescription + " or " + typeDescription2, e);
        }
    }

    private static Dispatcher.Unresolved locate(Class<? extends Annotation> cls, MethodDescription.InDefinedShape inDefinedShape, Dispatcher.Unresolved unresolved, MethodDescription.InDefinedShape inDefinedShape2, Delegator delegator) {
        AnnotationDescription.Loadable ofType = inDefinedShape2.getDeclaredAnnotations().ofType(cls);
        if (ofType == null) {
            return unresolved;
        }
        if (unresolved.isAlive()) {
            throw new IllegalStateException("Duplicate advice for " + unresolved + " and " + inDefinedShape2);
        }
        if (inDefinedShape2.isStatic()) {
            return ((Boolean) ofType.getValue(inDefinedShape).resolve(Boolean.class)).booleanValue() ? new Dispatcher.Inlining(inDefinedShape2) : new Dispatcher.Delegating(inDefinedShape2, delegator);
        }
        throw new IllegalStateException("Advice for " + inDefinedShape2 + " is not static");
    }

    public static WithCustomMapping withCustomMapping() {
        return new WithCustomMapping();
    }

    public AsmVisitorWrapper.ForDeclaredMethods on(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new AsmVisitorWrapper.ForDeclaredMethods().invokable(elementMatcher, this);
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
    public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
        return (methodDescription.isAbstract() || methodDescription.isNative()) ? methodVisitor : doWrap(typeDescription, methodDescription, methodVisitor, context, i, i2);
    }

    protected MethodVisitor doWrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, int i, int i2) {
        if (this.methodEnter.isPrependLineNumber()) {
            methodVisitor = new LineNumberPrependingMethodVisitor(methodVisitor);
        }
        if (!this.methodExit.isAlive()) {
            return new AdviceVisitor.WithoutExitAdvice(methodVisitor, context, this.assigner, this.exceptionHandler.resolve(methodDescription, typeDescription), typeDescription, methodDescription, this.methodEnter, i, i2);
        }
        if (this.methodExit.getThrowable().represents(NoExceptionHandler.class)) {
            return new AdviceVisitor.WithExitAdvice.WithoutExceptionHandling(methodVisitor, context, this.assigner, this.exceptionHandler.resolve(methodDescription, typeDescription), typeDescription, methodDescription, this.methodEnter, this.methodExit, i, i2);
        }
        if (methodDescription.isConstructor()) {
            throw new IllegalStateException("Cannot catch exception during constructor call for " + methodDescription);
        }
        return new AdviceVisitor.WithExitAdvice.WithExceptionHandling(methodVisitor, context, this.assigner, this.exceptionHandler.resolve(methodDescription, typeDescription), typeDescription, methodDescription, this.methodEnter, this.methodExit, i, i2, this.methodExit.getThrowable());
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return this.delegate.prepare(instrumentedType);
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(this, target, this.delegate.appender(target));
    }

    public Advice withAssigner(Assigner assigner) {
        return new Advice(this.methodEnter, this.methodExit, assigner, this.exceptionHandler, this.delegate);
    }

    public Advice withExceptionPrinting() {
        return withExceptionHandler(ExceptionHandler.Default.PRINTING);
    }

    public Advice withExceptionHandler(StackManipulation stackManipulation) {
        return withExceptionHandler(new ExceptionHandler.Simple(stackManipulation));
    }

    public Advice withExceptionHandler(ExceptionHandler exceptionHandler) {
        return new Advice(this.methodEnter, this.methodExit, this.assigner, exceptionHandler, this.delegate);
    }

    public Implementation wrap(Implementation implementation) {
        return new Advice(this.methodEnter, this.methodExit, this.assigner, this.exceptionHandler, implementation);
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping.class */
    public interface OffsetMapping {
        Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target.class */
        public interface Target {
            StackManipulation resolveRead();

            StackManipulation resolveWrite();

            StackManipulation resolveIncrement(int i);

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$AbstractReadOnlyAdapter.class */
            public static abstract class AbstractReadOnlyAdapter implements Target {
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveWrite() {
                    throw new IllegalStateException("Cannot write to read-only value");
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot write to read-only value");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForDefaultValue.class */
            public static abstract class ForDefaultValue implements Target {
                protected final TypeDefinition typeDefinition;
                protected final StackManipulation readAssignment;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDefinition.equals(((ForDefaultValue) obj).typeDefinition) && this.readAssignment.equals(((ForDefaultValue) obj).readAssignment);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.typeDefinition.hashCode()) * 31) + this.readAssignment.hashCode();
                }

                protected ForDefaultValue(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                    this.typeDefinition = typeDefinition;
                    this.readAssignment = stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return new StackManipulation.Compound(DefaultValue.of(this.typeDefinition), this.readAssignment);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForDefaultValue$ReadOnly.class */
                public static class ReadOnly extends ForDefaultValue {
                    public ReadOnly(TypeDefinition typeDefinition) {
                        this(typeDefinition, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                        super(typeDefinition, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only default value");
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only default value");
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForDefaultValue$ReadWrite.class */
                public static class ReadWrite extends ForDefaultValue {
                    public ReadWrite(TypeDefinition typeDefinition) {
                        this(typeDefinition, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                        super(typeDefinition, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        return Removal.of(this.typeDefinition);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        return StackManipulation.Trivial.INSTANCE;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForVariable.class */
            public static abstract class ForVariable implements Target {
                protected final TypeDefinition typeDefinition;
                protected final int offset;
                protected final StackManipulation readAssignment;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.offset == ((ForVariable) obj).offset && this.typeDefinition.equals(((ForVariable) obj).typeDefinition) && this.readAssignment.equals(((ForVariable) obj).readAssignment);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.typeDefinition.hashCode()) * 31) + this.offset) * 31) + this.readAssignment.hashCode();
                }

                protected ForVariable(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation) {
                    this.typeDefinition = typeDefinition;
                    this.offset = i;
                    this.readAssignment = stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return new StackManipulation.Compound(MethodVariableAccess.of(this.typeDefinition).loadFrom(this.offset), this.readAssignment);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForVariable$ReadOnly.class */
                public static class ReadOnly extends ForVariable {
                    public ReadOnly(TypeDefinition typeDefinition, int i) {
                        this(typeDefinition, i, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation) {
                        super(typeDefinition, i, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only parameter " + this.typeDefinition + " at " + this.offset);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only variable " + this.typeDefinition + " at " + this.offset);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForVariable$ReadWrite.class */
                public static class ReadWrite extends ForVariable {
                    private final StackManipulation writeAssignment;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForVariable
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.writeAssignment.equals(((ReadWrite) obj).writeAssignment);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForVariable
                    public int hashCode() {
                        return (super.hashCode() * 31) + this.writeAssignment.hashCode();
                    }

                    /* JADX WARN: Illegal instructions before constructor call */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public ReadWrite(net.bytebuddy.description.type.TypeDefinition r7, int r8) {
                        /*
                            r6 = this;
                            r0 = r6
                            r1 = r7
                            r2 = r8
                            net.bytebuddy.implementation.bytecode.StackManipulation$Trivial r3 = net.bytebuddy.implementation.bytecode.StackManipulation.Trivial.INSTANCE
                            r4 = r3
                            r0.<init>(r1, r2, r3, r4)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.OffsetMapping.Target.ForVariable.ReadWrite.<init>(net.bytebuddy.description.type.TypeDefinition, int):void");
                    }

                    public ReadWrite(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        super(typeDefinition, i, stackManipulation);
                        this.writeAssignment = stackManipulation2;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        return new StackManipulation.Compound(this.writeAssignment, MethodVariableAccess.of(this.typeDefinition).storeAt(this.offset));
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        return this.typeDefinition.represents(Integer.TYPE) ? MethodVariableAccess.of(this.typeDefinition).increment(this.offset, i) : new StackManipulation.Compound(resolveRead(), IntegerConstant.forValue(1), Addition.INTEGER, resolveWrite());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForArray.class */
            public static abstract class ForArray implements Target {
                protected final TypeDescription.Generic target;
                protected final List<? extends StackManipulation> valueReads;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.target.equals(((ForArray) obj).target) && this.valueReads.equals(((ForArray) obj).valueReads);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.valueReads.hashCode();
                }

                protected ForArray(TypeDescription.Generic generic, List<? extends StackManipulation> list) {
                    this.target = generic;
                    this.valueReads = list;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return ArrayFactory.forType(this.target).withValues(this.valueReads);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot increment read-only array value");
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForArray$ReadOnly.class */
                public static class ReadOnly extends ForArray {
                    public ReadOnly(TypeDescription.Generic generic, List<? extends StackManipulation> list) {
                        super(generic, list);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only array value");
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForArray$ReadWrite.class */
                public static class ReadWrite extends ForArray {
                    private final List<? extends StackManipulation> valueWrites;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.valueWrites.equals(((ReadWrite) obj).valueWrites);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray
                    public int hashCode() {
                        return (super.hashCode() * 31) + this.valueWrites.hashCode();
                    }

                    public ReadWrite(TypeDescription.Generic generic, List<? extends StackManipulation> list, List<? extends StackManipulation> list2) {
                        super(generic, list);
                        this.valueWrites = list2;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        return new StackManipulation.Compound(ArrayAccess.of(this.target).forEach(this.valueWrites), Removal.SINGLE);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForField.class */
            public static abstract class ForField implements Target {
                protected final FieldDescription fieldDescription;
                protected final StackManipulation readAssignment;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForField) obj).fieldDescription) && this.readAssignment.equals(((ForField) obj).readAssignment);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.fieldDescription.hashCode()) * 31) + this.readAssignment.hashCode();
                }

                protected ForField(FieldDescription fieldDescription, StackManipulation stackManipulation) {
                    this.fieldDescription = fieldDescription;
                    this.readAssignment = stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    stackManipulationArr[0] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    stackManipulationArr[2] = this.readAssignment;
                    return new StackManipulation.Compound(stackManipulationArr);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForField$ReadOnly.class */
                public static class ReadOnly extends ForField {
                    public ReadOnly(FieldDescription fieldDescription) {
                        this(fieldDescription, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(FieldDescription fieldDescription, StackManipulation stackManipulation) {
                        super(fieldDescription, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only field value");
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only field value");
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForField$WriteOnly.class */
                public static class WriteOnly implements Target {
                    private final FieldDescription fieldDescription;
                    private final StackManipulation writeAssignment;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((WriteOnly) obj).fieldDescription) && this.writeAssignment.equals(((WriteOnly) obj).writeAssignment);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.fieldDescription.hashCode()) * 31) + this.writeAssignment.hashCode();
                    }

                    protected WriteOnly(FieldDescription fieldDescription, StackManipulation stackManipulation) {
                        this.fieldDescription = fieldDescription;
                        this.writeAssignment = stackManipulation;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveRead() {
                        throw new IllegalStateException("Cannot read write-only field value");
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        StackManipulation compound;
                        if (this.fieldDescription.isStatic()) {
                            compound = StackManipulation.Trivial.INSTANCE;
                        } else {
                            compound = new StackManipulation.Compound(MethodVariableAccess.loadThis(), Duplication.SINGLE.flipOver(this.fieldDescription.getType()), Removal.SINGLE);
                        }
                        return new StackManipulation.Compound(this.writeAssignment, compound, FieldAccess.forField(this.fieldDescription).write());
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot increment write-only field value");
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForField$ReadWrite.class */
                public static class ReadWrite extends ForField {
                    private final StackManipulation writeAssignment;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.writeAssignment.equals(((ReadWrite) obj).writeAssignment);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField
                    public int hashCode() {
                        return (super.hashCode() * 31) + this.writeAssignment.hashCode();
                    }

                    /* JADX WARN: Illegal instructions before constructor call */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public ReadWrite(net.bytebuddy.description.field.FieldDescription r6) {
                        /*
                            r5 = this;
                            r0 = r5
                            r1 = r6
                            net.bytebuddy.implementation.bytecode.StackManipulation$Trivial r2 = net.bytebuddy.implementation.bytecode.StackManipulation.Trivial.INSTANCE
                            r3 = r2
                            r0.<init>(r1, r2, r3)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField.ReadWrite.<init>(net.bytebuddy.description.field.FieldDescription):void");
                    }

                    public ReadWrite(FieldDescription fieldDescription, StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        super(fieldDescription, stackManipulation);
                        this.writeAssignment = stackManipulation2;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        StackManipulation compound;
                        if (this.fieldDescription.isStatic()) {
                            compound = StackManipulation.Trivial.INSTANCE;
                        } else {
                            compound = new StackManipulation.Compound(MethodVariableAccess.loadThis(), Duplication.SINGLE.flipOver(this.fieldDescription.getType()), Removal.SINGLE);
                        }
                        return new StackManipulation.Compound(this.writeAssignment, compound, FieldAccess.forField(this.fieldDescription).write());
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        return new StackManipulation.Compound(resolveRead(), IntegerConstant.forValue(i), Addition.INTEGER, resolveWrite());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForStackManipulation.class */
            public static class ForStackManipulation implements Target {
                private final StackManipulation stackManipulation;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.stackManipulation.equals(((ForStackManipulation) obj).stackManipulation);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.stackManipulation.hashCode();
                }

                public ForStackManipulation(StackManipulation stackManipulation) {
                    this.stackManipulation = stackManipulation;
                }

                public static Target of(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForStackManipulation(MethodConstant.of(inDefinedShape));
                }

                public static Target of(TypeDescription typeDescription) {
                    return new ForStackManipulation(ClassConstant.of(typeDescription));
                }

                public static Target of(Object obj) {
                    if (obj == null) {
                        return new ForStackManipulation(NullConstant.INSTANCE);
                    }
                    if (obj instanceof Boolean) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Boolean) obj).booleanValue()));
                    }
                    if (obj instanceof Byte) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Byte) obj).byteValue()));
                    }
                    if (obj instanceof Short) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Short) obj).shortValue()));
                    }
                    if (obj instanceof Character) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Character) obj).charValue()));
                    }
                    if (obj instanceof Integer) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Integer) obj).intValue()));
                    }
                    if (obj instanceof Long) {
                        return new ForStackManipulation(LongConstant.forValue(((Long) obj).longValue()));
                    }
                    if (obj instanceof Float) {
                        return new ForStackManipulation(FloatConstant.forValue(((Float) obj).floatValue()));
                    }
                    if (obj instanceof Double) {
                        return new ForStackManipulation(DoubleConstant.forValue(((Double) obj).doubleValue()));
                    }
                    if (obj instanceof String) {
                        return new ForStackManipulation(new TextConstant((String) obj));
                    }
                    if (obj instanceof Enum) {
                        return new ForStackManipulation(FieldAccess.forEnumeration(new EnumerationDescription.ForLoadedEnumeration((Enum) obj)));
                    }
                    if (obj instanceof EnumerationDescription) {
                        return new ForStackManipulation(FieldAccess.forEnumeration((EnumerationDescription) obj));
                    }
                    if (obj instanceof Class) {
                        return new ForStackManipulation(ClassConstant.of(TypeDescription.ForLoadedType.of((Class) obj)));
                    }
                    if (obj instanceof TypeDescription) {
                        return new ForStackManipulation(ClassConstant.of((TypeDescription) obj));
                    }
                    if (JavaType.METHOD_HANDLE.isInstance(obj)) {
                        return new ForStackManipulation(new JavaConstantValue(JavaConstant.MethodHandle.ofLoaded(obj)));
                    }
                    if (JavaType.METHOD_TYPE.isInstance(obj)) {
                        return new ForStackManipulation(new JavaConstantValue(JavaConstant.MethodType.ofLoaded(obj)));
                    }
                    if (obj instanceof JavaConstant) {
                        return new ForStackManipulation(new JavaConstantValue((JavaConstant) obj));
                    }
                    throw new IllegalArgumentException("Not a constant value: " + obj);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return this.stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveWrite() {
                    throw new IllegalStateException("Cannot write to constant value: " + this.stackManipulation);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot write to constant value: " + this.stackManipulation);
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Target$ForStackManipulation$Writable.class */
                public static class Writable implements Target {
                    private final StackManipulation read;
                    private final StackManipulation write;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.read.equals(((Writable) obj).read) && this.write.equals(((Writable) obj).write);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.read.hashCode()) * 31) + this.write.hashCode();
                    }

                    public Writable(StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        this.read = stackManipulation;
                        this.write = stackManipulation2;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveRead() {
                        return this.read;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        return this.write;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot increment mutable constant value: " + this.write);
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Factory.class */
        public interface Factory<T extends Annotation> {
            Class<T> getAnnotationType();

            OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType);

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Factory$AdviceType.class */
            public enum AdviceType {
                DELEGATION(true),
                INLINING(false);

                private final boolean delegation;

                AdviceType(boolean z) {
                    this.delegation = z;
                }

                public final boolean isDelegation() {
                    return this.delegation;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Factory$Simple.class */
            public static class Simple<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final OffsetMapping offsetMapping;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((Simple) obj).annotationType) && this.offsetMapping.equals(((Simple) obj).offsetMapping);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.offsetMapping.hashCode();
                }

                public Simple(Class<T> cls, OffsetMapping offsetMapping) {
                    this.annotationType = cls;
                    this.offsetMapping = offsetMapping;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType) {
                    return this.offsetMapping;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Factory$Illegal.class */
            public static class Illegal<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((Illegal) obj).annotationType);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.annotationType.hashCode();
                }

                public Illegal(Class<T> cls) {
                    this.annotationType = cls;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType) {
                    throw new IllegalStateException("Usage of " + this.annotationType + " is not allowed on " + inDefinedShape);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$Sort.class */
        public enum Sort {
            ENTER { // from class: net.bytebuddy.asm.Advice.OffsetMapping.Sort.1
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Sort
                public final boolean isPremature(MethodDescription methodDescription) {
                    return methodDescription.isConstructor();
                }
            },
            EXIT { // from class: net.bytebuddy.asm.Advice.OffsetMapping.Sort.2
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Sort
                public final boolean isPremature(MethodDescription methodDescription) {
                    return false;
                }
            };

            public abstract boolean isPremature(MethodDescription methodDescription);

            /* synthetic */ Sort(byte b2) {
                this();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForArgument.class */
        public static abstract class ForArgument implements OffsetMapping {
            protected final TypeDescription.Generic target;
            protected final boolean readOnly;
            private final Assigner.Typing typing;

            protected abstract ParameterDescription resolve(MethodDescription methodDescription);

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForArgument) obj).readOnly && this.typing.equals(((ForArgument) obj).typing) && this.target.equals(((ForArgument) obj).target);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForArgument(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                ParameterDescription resolve = resolve(methodDescription);
                StackManipulation assign = assigner.assign(resolve.getType(), this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + resolve + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(resolve.getType(), argumentHandler.argument(resolve.getOffset()), assign);
                }
                StackManipulation assign2 = assigner.assign(this.target, resolve.getType(), this.typing);
                if (assign2.isValid()) {
                    return new Target.ForVariable.ReadWrite(resolve.getType(), argumentHandler.argument(resolve.getOffset()), assign, assign2);
                }
                throw new IllegalStateException("Cannot assign " + resolve + " to " + this.target);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForArgument$Unresolved.class */
            public static class Unresolved extends ForArgument {
                private final int index;
                private final boolean optional;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Unresolved) obj).index && this.optional == ((Unresolved) obj).optional;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public int hashCode() {
                    return (((super.hashCode() * 31) + this.index) * 31) + (this.optional ? 1 : 0);
                }

                protected Unresolved(TypeDescription.Generic generic, AnnotationDescription.Loadable<Argument> loadable) {
                    this(generic, ((Boolean) loadable.getValue(Factory.ARGUMENT_READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(Factory.ARGUMENT_TYPING).load(Argument.class.getClassLoader()).resolve(Assigner.Typing.class), ((Integer) loadable.getValue(Factory.ARGUMENT_VALUE).resolve(Integer.class)).intValue(), ((Boolean) loadable.getValue(Factory.ARGUMENT_OPTIONAL).resolve(Boolean.class)).booleanValue());
                }

                protected Unresolved(ParameterDescription parameterDescription) {
                    this(parameterDescription.getType(), true, Assigner.Typing.STATIC, parameterDescription.getIndex());
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, int i) {
                    this(generic, z, typing, i, false);
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, int i, boolean z2) {
                    super(generic, z, typing);
                    this.index = i;
                    this.optional = z2;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                protected ParameterDescription resolve(MethodDescription methodDescription) {
                    ParameterList<?> parameters = methodDescription.getParameters();
                    if (parameters.size() <= this.index) {
                        throw new IllegalStateException(methodDescription + " does not define an index " + this.index);
                    }
                    return (ParameterDescription) parameters.get(this.index);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument, net.bytebuddy.asm.Advice.OffsetMapping
                public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                    if (!this.optional || methodDescription.getParameters().size() > this.index) {
                        return super.resolve(typeDescription, methodDescription, assigner, argumentHandler, sort);
                    }
                    return this.readOnly ? new Target.ForDefaultValue.ReadOnly(this.target) : new Target.ForDefaultValue.ReadWrite(this.target);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForArgument$Unresolved$Factory.class */
                protected enum Factory implements Factory<Argument> {
                    INSTANCE;

                    private static final MethodDescription.InDefinedShape ARGUMENT_VALUE;
                    private static final MethodDescription.InDefinedShape ARGUMENT_READ_ONLY;
                    private static final MethodDescription.InDefinedShape ARGUMENT_TYPING;
                    private static final MethodDescription.InDefinedShape ARGUMENT_OPTIONAL;

                    static {
                        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Argument.class).getDeclaredMethods();
                        ARGUMENT_VALUE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("value")).getOnly();
                        ARGUMENT_READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                        ARGUMENT_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                        ARGUMENT_OPTIONAL = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("optional")).getOnly();
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public final Class<Argument> getAnnotationType() {
                        return Argument.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Argument> loadable, Factory.AdviceType adviceType) {
                        if (adviceType.isDelegation() && !((Boolean) loadable.getValue(ARGUMENT_READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                            throw new IllegalStateException("Cannot define writable field access for " + inDefinedShape + " when using delegation");
                        }
                        return new Unresolved(inDefinedShape.getType(), loadable);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForArgument$Resolved.class */
            public static class Resolved extends ForArgument {
                private final ParameterDescription parameterDescription;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.parameterDescription.equals(((Resolved) obj).parameterDescription);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public int hashCode() {
                    return (super.hashCode() * 31) + this.parameterDescription.hashCode();
                }

                public Resolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, ParameterDescription parameterDescription) {
                    super(generic, z, typing);
                    this.parameterDescription = parameterDescription;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                protected ParameterDescription resolve(MethodDescription methodDescription) {
                    if (!this.parameterDescription.getDeclaringMethod().equals(methodDescription)) {
                        throw new IllegalStateException(this.parameterDescription + " is not a parameter of " + methodDescription);
                    }
                    return this.parameterDescription;
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForArgument$Resolved$Factory.class */
                public static class Factory<T extends Annotation> implements Factory<T> {
                    private final Class<T> annotationType;
                    private final ParameterDescription parameterDescription;
                    private final boolean readOnly;
                    private final Assigner.Typing typing;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.readOnly == ((Factory) obj).readOnly && this.typing.equals(((Factory) obj).typing) && this.annotationType.equals(((Factory) obj).annotationType) && this.parameterDescription.equals(((Factory) obj).parameterDescription);
                    }

                    public int hashCode() {
                        return (((((((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.parameterDescription.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
                    }

                    public Factory(Class<T> cls, ParameterDescription parameterDescription) {
                        this(cls, parameterDescription, true, Assigner.Typing.STATIC);
                    }

                    public Factory(Class<T> cls, ParameterDescription parameterDescription, boolean z, Assigner.Typing typing) {
                        this.annotationType = cls;
                        this.parameterDescription = parameterDescription;
                        this.readOnly = z;
                        this.typing = typing;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public Class<T> getAnnotationType() {
                        return this.annotationType;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                        return new Resolved(inDefinedShape.getType(), this.readOnly, this.typing, this.parameterDescription);
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForThisReference.class */
        public static class ForThisReference implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final boolean readOnly;
            private final Assigner.Typing typing;
            private final boolean optional;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForThisReference) obj).readOnly && this.optional == ((ForThisReference) obj).optional && this.typing.equals(((ForThisReference) obj).typing) && this.target.equals(((ForThisReference) obj).target);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode()) * 31) + (this.optional ? 1 : 0);
            }

            protected ForThisReference(TypeDescription.Generic generic, AnnotationDescription.Loadable<This> loadable) {
                this(generic, ((Boolean) loadable.getValue(Factory.THIS_READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(Factory.THIS_TYPING).load(This.class.getClassLoader()).resolve(Assigner.Typing.class), ((Boolean) loadable.getValue(Factory.THIS_OPTIONAL).resolve(Boolean.class)).booleanValue());
            }

            public ForThisReference(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, boolean z2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
                this.optional = z2;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                if (methodDescription.isStatic() || sort.isPremature(methodDescription)) {
                    if (this.optional) {
                        return this.readOnly ? new Target.ForDefaultValue.ReadOnly(typeDescription) : new Target.ForDefaultValue.ReadWrite(typeDescription);
                    }
                    throw new IllegalStateException("Cannot map this reference for static method or constructor start: " + methodDescription);
                }
                StackManipulation assign = assigner.assign(typeDescription.asGenericType(), this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + typeDescription + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(typeDescription.asGenericType(), argumentHandler.argument(0), assign);
                }
                StackManipulation assign2 = assigner.assign(this.target, typeDescription.asGenericType(), this.typing);
                if (assign2.isValid()) {
                    return new Target.ForVariable.ReadWrite(typeDescription.asGenericType(), argumentHandler.argument(0), assign, assign2);
                }
                throw new IllegalStateException("Cannot assign " + this.target + " to " + typeDescription);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForThisReference$Factory.class */
            protected enum Factory implements Factory<This> {
                INSTANCE;

                private static final MethodDescription.InDefinedShape THIS_READ_ONLY;
                private static final MethodDescription.InDefinedShape THIS_TYPING;
                private static final MethodDescription.InDefinedShape THIS_OPTIONAL;

                static {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(This.class).getDeclaredMethods();
                    THIS_READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                    THIS_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    THIS_OPTIONAL = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("optional")).getOnly();
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final Class<This> getAnnotationType() {
                    return This.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<This> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Boolean) loadable.getValue(THIS_READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                        throw new IllegalStateException("Cannot write to this reference for " + inDefinedShape + " in read-only context");
                    }
                    return new ForThisReference(inDefinedShape.getType(), loadable);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForAllArguments.class */
        public static class ForAllArguments implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final boolean readOnly;
            private final Assigner.Typing typing;
            private final boolean nullIfEmpty;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForAllArguments) obj).readOnly && this.nullIfEmpty == ((ForAllArguments) obj).nullIfEmpty && this.typing.equals(((ForAllArguments) obj).typing) && this.target.equals(((ForAllArguments) obj).target);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode()) * 31) + (this.nullIfEmpty ? 1 : 0);
            }

            protected ForAllArguments(TypeDescription.Generic generic, AnnotationDescription.Loadable<AllArguments> loadable) {
                this(generic, ((Boolean) loadable.getValue(Factory.ALL_ARGUMENTS_READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(Factory.ALL_ARGUMENTS_TYPING).load(AllArguments.class.getClassLoader()).resolve(Assigner.Typing.class), ((Boolean) loadable.getValue(Factory.ALL_ARGUMENTS_NULL_IF_EMPTY).resolve(Boolean.class)).booleanValue());
            }

            public ForAllArguments(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, boolean z2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
                this.nullIfEmpty = z2;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                if (this.nullIfEmpty && methodDescription.getParameters().isEmpty()) {
                    return this.readOnly ? new Target.ForStackManipulation(NullConstant.INSTANCE) : new Target.ForStackManipulation.Writable(NullConstant.INSTANCE, Removal.SINGLE);
                }
                ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                Iterator it = methodDescription.getParameters().iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription = (ParameterDescription) it.next();
                    StackManipulation assign = assigner.assign(parameterDescription.getType(), this.target, this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + parameterDescription + " to " + this.target);
                    }
                    arrayList.add(new StackManipulation.Compound(MethodVariableAccess.of(parameterDescription.getType()).loadFrom(argumentHandler.argument(parameterDescription.getOffset())), assign));
                }
                if (this.readOnly) {
                    return new Target.ForArray.ReadOnly(this.target, arrayList);
                }
                ArrayList arrayList2 = new ArrayList(methodDescription.getParameters().size());
                Iterator it2 = methodDescription.getParameters().iterator();
                while (it2.hasNext()) {
                    ParameterDescription parameterDescription2 = (ParameterDescription) it2.next();
                    StackManipulation assign2 = assigner.assign(this.target, parameterDescription2.getType(), this.typing);
                    if (!assign2.isValid()) {
                        throw new IllegalStateException("Cannot assign " + this.target + " to " + parameterDescription2);
                    }
                    arrayList2.add(new StackManipulation.Compound(assign2, MethodVariableAccess.of(parameterDescription2.getType()).storeAt(argumentHandler.argument(parameterDescription2.getOffset()))));
                }
                return new Target.ForArray.ReadWrite(this.target, arrayList, arrayList2);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForAllArguments$Factory.class */
            protected enum Factory implements Factory<AllArguments> {
                INSTANCE;

                private static final MethodDescription.InDefinedShape ALL_ARGUMENTS_READ_ONLY;
                private static final MethodDescription.InDefinedShape ALL_ARGUMENTS_TYPING;
                private static final MethodDescription.InDefinedShape ALL_ARGUMENTS_NULL_IF_EMPTY;

                static {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(AllArguments.class).getDeclaredMethods();
                    ALL_ARGUMENTS_READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                    ALL_ARGUMENTS_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    ALL_ARGUMENTS_NULL_IF_EMPTY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("nullIfEmpty")).getOnly();
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final Class<AllArguments> getAnnotationType() {
                    return AllArguments.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
                public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<AllArguments> loadable, Factory.AdviceType adviceType) {
                    TypeDescription.Generic componentType;
                    if (!inDefinedShape.getType().represents(Object.class) && !inDefinedShape.getType().isArray()) {
                        throw new IllegalStateException("Cannot use AllArguments annotation on a non-array type");
                    }
                    if (adviceType.isDelegation() && !((Boolean) loadable.getValue(ALL_ARGUMENTS_READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                        throw new IllegalStateException("Cannot define writable field access for " + inDefinedShape);
                    }
                    if (inDefinedShape.getType().represents(Object.class)) {
                        componentType = TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Object.class);
                    } else {
                        componentType = inDefinedShape.getType().getComponentType();
                    }
                    return new ForAllArguments(componentType, loadable);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForInstrumentedType.class */
        public enum ForInstrumentedType implements OffsetMapping {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public final Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                return Target.ForStackManipulation.of(typeDescription);
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForInstrumentedMethod.class */
        public enum ForInstrumentedMethod implements OffsetMapping {
            METHOD { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.1
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final boolean isRepresentable(MethodDescription methodDescription) {
                    return methodDescription.isMethod();
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final Target resolve(MethodDescription.InDefinedShape inDefinedShape) {
                    return Target.ForStackManipulation.of(inDefinedShape);
                }
            },
            CONSTRUCTOR { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.2
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final boolean isRepresentable(MethodDescription methodDescription) {
                    return methodDescription.isConstructor();
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final Target resolve(MethodDescription.InDefinedShape inDefinedShape) {
                    return Target.ForStackManipulation.of(inDefinedShape);
                }
            },
            EXECUTABLE { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.3
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final boolean isRepresentable(MethodDescription methodDescription) {
                    return true;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final Target resolve(MethodDescription.InDefinedShape inDefinedShape) {
                    return Target.ForStackManipulation.of(inDefinedShape);
                }
            },
            METHOD_HANDLE { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.4
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final boolean isRepresentable(MethodDescription methodDescription) {
                    return true;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final Target resolve(MethodDescription.InDefinedShape inDefinedShape) {
                    return new Target.ForStackManipulation(new JavaConstantValue(JavaConstant.MethodHandle.of(inDefinedShape)));
                }
            },
            METHOD_TYPE { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.5
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final boolean isRepresentable(MethodDescription methodDescription) {
                    return true;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected final Target resolve(MethodDescription.InDefinedShape inDefinedShape) {
                    return new Target.ForStackManipulation(new JavaConstantValue(JavaConstant.MethodType.of(inDefinedShape)));
                }
            };

            protected abstract boolean isRepresentable(MethodDescription methodDescription);

            protected abstract Target resolve(MethodDescription.InDefinedShape inDefinedShape);

            /* synthetic */ ForInstrumentedMethod(byte b2) {
                this();
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                if (!isRepresentable(methodDescription)) {
                    throw new IllegalStateException("Cannot represent " + methodDescription + " as the specified constant");
                }
                return resolve(methodDescription.asDefined());
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForField.class */
        public static abstract class ForField implements OffsetMapping {
            private static final MethodDescription.InDefinedShape VALUE;
            private static final MethodDescription.InDefinedShape DECLARING_TYPE;
            private static final MethodDescription.InDefinedShape READ_ONLY;
            private static final MethodDescription.InDefinedShape TYPING;
            private final TypeDescription.Generic target;
            private final boolean readOnly;
            private final Assigner.Typing typing;

            protected abstract FieldDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription);

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForField) obj).readOnly && this.typing.equals(((ForField) obj).typing) && this.target.equals(((ForField) obj).target);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            static {
                MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(FieldValue.class).getDeclaredMethods();
                VALUE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("value")).getOnly();
                DECLARING_TYPE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("declaringType")).getOnly();
                READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
            }

            public ForField(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                FieldDescription resolve = resolve(typeDescription, methodDescription);
                if (!resolve.isStatic() && methodDescription.isStatic()) {
                    throw new IllegalStateException("Cannot read non-static field " + resolve + " from static method " + methodDescription);
                }
                if (sort.isPremature(methodDescription) && !resolve.isStatic()) {
                    if (this.readOnly) {
                        throw new IllegalStateException("Cannot assign " + resolve + " to " + this.target);
                    }
                    StackManipulation assign = assigner.assign(this.target, resolve.getType(), this.typing);
                    if (assign.isValid()) {
                        return new Target.ForField.WriteOnly(resolve.asDefined(), assign);
                    }
                    throw new IllegalStateException("Cannot assign " + this.target + " to " + resolve);
                }
                StackManipulation assign2 = assigner.assign(resolve.getType(), this.target, this.typing);
                if (!assign2.isValid()) {
                    throw new IllegalStateException("Cannot assign " + resolve + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForField.ReadOnly(resolve, assign2);
                }
                StackManipulation assign3 = assigner.assign(this.target, resolve.getType(), this.typing);
                if (assign3.isValid()) {
                    return new Target.ForField.ReadWrite(resolve.asDefined(), assign2, assign3);
                }
                throw new IllegalStateException("Cannot assign " + this.target + " to " + resolve);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForField$Unresolved.class */
            public static abstract class Unresolved extends ForField {
                protected static final String BEAN_PROPERTY = "";
                private final String name;

                protected abstract FieldLocator fieldLocator(TypeDescription typeDescription);

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.name.equals(((Unresolved) obj).name);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public int hashCode() {
                    return (super.hashCode() * 31) + this.name.hashCode();
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str) {
                    super(generic, z, typing);
                    this.name = str;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                protected FieldDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                    FieldLocator.Resolution locate;
                    FieldLocator fieldLocator = fieldLocator(typeDescription);
                    if (this.name.equals("")) {
                        locate = resolveAccessor(fieldLocator, methodDescription);
                    } else {
                        locate = fieldLocator.locate(this.name);
                    }
                    FieldLocator.Resolution resolution = locate;
                    if (!locate.isResolved()) {
                        throw new IllegalStateException("Cannot locate field named " + this.name + " for " + typeDescription);
                    }
                    return resolution.getField();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static FieldLocator.Resolution resolveAccessor(FieldLocator fieldLocator, MethodDescription methodDescription) {
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

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForField$Unresolved$WithImplicitType.class */
                public static class WithImplicitType extends Unresolved {
                    protected WithImplicitType(TypeDescription.Generic generic, AnnotationDescription.Loadable<FieldValue> loadable) {
                        this(generic, ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(ForField.TYPING).load(Assigner.Typing.class.getClassLoader()).resolve(Assigner.Typing.class), (String) loadable.getValue(ForField.VALUE).resolve(String.class));
                    }

                    public WithImplicitType(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str) {
                        super(generic, z, typing, str);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved
                    protected FieldLocator fieldLocator(TypeDescription typeDescription) {
                        return new FieldLocator.ForClassHierarchy(typeDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForField$Unresolved$WithExplicitType.class */
                public static class WithExplicitType extends Unresolved {
                    private final TypeDescription declaringType;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved, net.bytebuddy.asm.Advice.OffsetMapping.ForField
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.declaringType.equals(((WithExplicitType) obj).declaringType);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved, net.bytebuddy.asm.Advice.OffsetMapping.ForField
                    public int hashCode() {
                        return (super.hashCode() * 31) + this.declaringType.hashCode();
                    }

                    protected WithExplicitType(TypeDescription.Generic generic, AnnotationDescription.Loadable<FieldValue> loadable, TypeDescription typeDescription) {
                        this(generic, ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(ForField.TYPING).load(Assigner.Typing.class.getClassLoader()).resolve(Assigner.Typing.class), (String) loadable.getValue(ForField.VALUE).resolve(String.class), typeDescription);
                    }

                    public WithExplicitType(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str, TypeDescription typeDescription) {
                        super(generic, z, typing, str);
                        this.declaringType = typeDescription;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved
                    protected FieldLocator fieldLocator(TypeDescription typeDescription) {
                        if (!this.declaringType.represents(TargetType.class) && !typeDescription.isAssignableTo(this.declaringType)) {
                            throw new IllegalStateException(this.declaringType + " is no super type of " + typeDescription);
                        }
                        return new FieldLocator.ForExactType(TargetType.resolve(this.declaringType, typeDescription));
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForField$Unresolved$Factory.class */
                protected enum Factory implements Factory<FieldValue> {
                    INSTANCE;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public final Class<FieldValue> getAnnotationType() {
                        return FieldValue.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<FieldValue> loadable, Factory.AdviceType adviceType) {
                        if (adviceType.isDelegation() && !((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                            throw new IllegalStateException("Cannot write to field for " + inDefinedShape + " in read-only context");
                        }
                        TypeDescription typeDescription = (TypeDescription) loadable.getValue(ForField.DECLARING_TYPE).resolve(TypeDescription.class);
                        if (!typeDescription.represents(Void.TYPE)) {
                            return new WithExplicitType(inDefinedShape.getType(), loadable, typeDescription);
                        }
                        return new WithImplicitType(inDefinedShape.getType(), loadable);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForField$Resolved.class */
            public static class Resolved extends ForField {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Resolved) obj).fieldDescription);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public int hashCode() {
                    return (super.hashCode() * 31) + this.fieldDescription.hashCode();
                }

                public Resolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, FieldDescription fieldDescription) {
                    super(generic, z, typing);
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming declaring type for type member.")
                protected FieldDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                    if (!this.fieldDescription.isStatic() && !this.fieldDescription.getDeclaringType().asErasure().isAssignableFrom(typeDescription)) {
                        throw new IllegalStateException(this.fieldDescription + " is no member of " + typeDescription);
                    }
                    if (!this.fieldDescription.isVisibleTo(typeDescription)) {
                        throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
                    }
                    return this.fieldDescription;
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForField$Resolved$Factory.class */
                public static class Factory<T extends Annotation> implements Factory<T> {
                    private final Class<T> annotationType;
                    private final FieldDescription fieldDescription;
                    private final boolean readOnly;
                    private final Assigner.Typing typing;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.readOnly == ((Factory) obj).readOnly && this.typing.equals(((Factory) obj).typing) && this.annotationType.equals(((Factory) obj).annotationType) && this.fieldDescription.equals(((Factory) obj).fieldDescription);
                    }

                    public int hashCode() {
                        return (((((((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.fieldDescription.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
                    }

                    public Factory(Class<T> cls, FieldDescription fieldDescription) {
                        this(cls, fieldDescription, true, Assigner.Typing.STATIC);
                    }

                    public Factory(Class<T> cls, FieldDescription fieldDescription, boolean z, Assigner.Typing typing) {
                        this.annotationType = cls;
                        this.fieldDescription = fieldDescription;
                        this.readOnly = z;
                        this.typing = typing;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public Class<T> getAnnotationType() {
                        return this.annotationType;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                        return new Resolved(inDefinedShape.getType(), this.readOnly, this.typing, this.fieldDescription);
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin.class */
        public static class ForOrigin implements OffsetMapping {
            private static final char DELIMITER = '#';
            private static final char ESCAPE = '\\';
            private final List<Renderer> renderers;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.renderers.equals(((ForOrigin) obj).renderers);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.renderers.hashCode();
            }

            public ForOrigin(List<Renderer> list) {
                this.renderers = list;
            }

            public static OffsetMapping parse(String str) {
                int i;
                int i2;
                if (str.equals("")) {
                    return new ForOrigin(Collections.singletonList(Renderer.ForStringRepresentation.INSTANCE));
                }
                ArrayList arrayList = new ArrayList(str.length());
                int i3 = 0;
                int indexOf = str.indexOf(35);
                while (true) {
                    int i4 = indexOf;
                    if (i4 != -1) {
                        if (i4 != 0 && str.charAt(i4 - 1) == '\\' && (i4 == 1 || str.charAt(i4 - 2) != '\\')) {
                            arrayList.add(new Renderer.ForConstantValue(str.substring(i3, Math.max(0, i4 - 1)) + '#'));
                            i = i4;
                            i2 = 1;
                        } else {
                            if (str.length() == i4 + 1) {
                                throw new IllegalStateException("Missing sort descriptor for " + str + " at index " + i4);
                            }
                            arrayList.add(new Renderer.ForConstantValue(str.substring(i3, i4).replace("\\\\", "\\")));
                            switch (str.charAt(i4 + 1)) {
                                case 'd':
                                    arrayList.add(Renderer.ForDescriptor.INSTANCE);
                                    break;
                                case 'e':
                                case 'f':
                                case 'g':
                                case 'h':
                                case 'i':
                                case 'j':
                                case 'k':
                                case 'l':
                                case 'n':
                                case 'o':
                                case 'q':
                                default:
                                    throw new IllegalStateException("Illegal sort descriptor " + str.charAt(i4 + 1) + " for " + str);
                                case 'm':
                                    arrayList.add(Renderer.ForMethodName.INSTANCE);
                                    break;
                                case 'p':
                                    arrayList.add(Renderer.ForPropertyName.INSTANCE);
                                    break;
                                case 'r':
                                    arrayList.add(Renderer.ForReturnTypeName.INSTANCE);
                                    break;
                                case 's':
                                    arrayList.add(Renderer.ForJavaSignature.INSTANCE);
                                    break;
                                case 't':
                                    arrayList.add(Renderer.ForTypeName.INSTANCE);
                                    break;
                            }
                            i = i4;
                            i2 = 2;
                        }
                        i3 = i + i2;
                        indexOf = str.indexOf(35, i3);
                    } else {
                        arrayList.add(new Renderer.ForConstantValue(str.substring(i3)));
                        return new ForOrigin(arrayList);
                    }
                }
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StringBuilder sb = new StringBuilder();
                Iterator<Renderer> it = this.renderers.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().apply(typeDescription, methodDescription));
                }
                return Target.ForStackManipulation.of(sb.toString());
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer.class */
            public interface Renderer {
                String apply(TypeDescription typeDescription, MethodDescription methodDescription);

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForMethodName.class */
                public enum ForMethodName implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 'm';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public final String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getInternalName();
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForTypeName.class */
                public enum ForTypeName implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 't';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public final String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return typeDescription.getName();
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForDescriptor.class */
                public enum ForDescriptor implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 'd';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public final String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getDescriptor();
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForJavaSignature.class */
                public enum ForJavaSignature implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 's';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public final String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        StringBuilder sb = new StringBuilder("(");
                        boolean z = false;
                        for (TypeDescription typeDescription2 : methodDescription.getParameters().asTypeList().asErasures()) {
                            if (z) {
                                sb.append(',');
                            } else {
                                z = true;
                            }
                            sb.append(typeDescription2.getName());
                        }
                        return sb.append(')').toString();
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForReturnTypeName.class */
                public enum ForReturnTypeName implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 'r';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public final String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getReturnType().asErasure().getName();
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForStringRepresentation.class */
                public enum ForStringRepresentation implements Renderer {
                    INSTANCE;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public final String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.toString();
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForConstantValue.class */
                public static class ForConstantValue implements Renderer {
                    private final String value;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.value.equals(((ForConstantValue) obj).value);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.value.hashCode();
                    }

                    public ForConstantValue(String str) {
                        this.value = str;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return this.value;
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Renderer$ForPropertyName.class */
                public enum ForPropertyName implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 'p';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public final String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return FieldAccessor.FieldNameExtractor.ForBeanProperty.INSTANCE.resolve(methodDescription);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForOrigin$Factory.class */
            protected enum Factory implements Factory<Origin> {
                INSTANCE;

                private static final MethodDescription.InDefinedShape ORIGIN_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Origin.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final Class<Origin> getAnnotationType() {
                    return Origin.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Origin> loadable, Factory.AdviceType adviceType) {
                    if (inDefinedShape.getType().asErasure().represents(Class.class)) {
                        return ForInstrumentedType.INSTANCE;
                    }
                    if (inDefinedShape.getType().asErasure().represents(Method.class)) {
                        return ForInstrumentedMethod.METHOD;
                    }
                    if (inDefinedShape.getType().asErasure().represents(Constructor.class)) {
                        return ForInstrumentedMethod.CONSTRUCTOR;
                    }
                    if (JavaType.EXECUTABLE.getTypeStub().equals(inDefinedShape.getType().asErasure())) {
                        return ForInstrumentedMethod.EXECUTABLE;
                    }
                    if (JavaType.METHOD_HANDLE.getTypeStub().equals(inDefinedShape.getType().asErasure())) {
                        return ForInstrumentedMethod.METHOD_HANDLE;
                    }
                    if (JavaType.METHOD_TYPE.getTypeStub().equals(inDefinedShape.getType().asErasure())) {
                        return ForInstrumentedMethod.METHOD_TYPE;
                    }
                    if (JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().equals(inDefinedShape.getType().asErasure())) {
                        return new ForStackManipulation(MethodInvocation.lookup(), JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().asGenericType(), inDefinedShape.getType(), Assigner.Typing.STATIC);
                    }
                    if (inDefinedShape.getType().asErasure().isAssignableFrom(String.class)) {
                        return ForOrigin.parse((String) loadable.getValue(ORIGIN_VALUE).resolve(String.class));
                    }
                    throw new IllegalStateException("Non-supported type " + inDefinedShape.getType() + " for @Origin annotation");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForUnusedValue.class */
        public static class ForUnusedValue implements OffsetMapping {
            private final TypeDefinition target;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.target.equals(((ForUnusedValue) obj).target);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.target.hashCode();
            }

            public ForUnusedValue(TypeDefinition typeDefinition) {
                this.target = typeDefinition;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                return new Target.ForDefaultValue.ReadWrite(this.target);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForUnusedValue$Factory.class */
            protected enum Factory implements Factory<Unused> {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final Class<Unused> getAnnotationType() {
                    return Unused.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Unused> loadable, Factory.AdviceType adviceType) {
                    return new ForUnusedValue(inDefinedShape.getType());
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForStubValue.class */
        public enum ForStubValue implements OffsetMapping, Factory<StubValue> {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public final Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                return new Target.ForDefaultValue.ReadOnly(methodDescription.getReturnType(), assigner.assign(methodDescription.getReturnType(), TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Object.class), Assigner.Typing.DYNAMIC));
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
            public final Class<StubValue> getAnnotationType() {
                return StubValue.class;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
            public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<StubValue> loadable, Factory.AdviceType adviceType) {
                if (!inDefinedShape.getType().represents(Object.class)) {
                    throw new IllegalStateException("Cannot use StubValue on non-Object parameter type " + inDefinedShape);
                }
                return this;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForEnterValue.class */
        public static class ForEnterValue implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final TypeDescription.Generic enterType;
            private final boolean readOnly;
            private final Assigner.Typing typing;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForEnterValue) obj).readOnly && this.typing.equals(((ForEnterValue) obj).typing) && this.target.equals(((ForEnterValue) obj).target) && this.enterType.equals(((ForEnterValue) obj).enterType);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.enterType.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForEnterValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, AnnotationDescription.Loadable<Enter> loadable) {
                this(generic, generic2, ((Boolean) loadable.getValue(Factory.ENTER_READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(Factory.ENTER_TYPING).load(Enter.class.getClassLoader()).resolve(Assigner.Typing.class));
            }

            public ForEnterValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.enterType = generic2;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.enterType, this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.enterType + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(this.target, argumentHandler.enter(), assign);
                }
                StackManipulation assign2 = assigner.assign(this.target, this.enterType, this.typing);
                if (assign2.isValid()) {
                    return new Target.ForVariable.ReadWrite(this.target, argumentHandler.enter(), assign, assign2);
                }
                throw new IllegalStateException("Cannot assign " + this.target + " to " + this.enterType);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForEnterValue$Factory.class */
            protected static class Factory implements Factory<Enter> {
                private static final MethodDescription.InDefinedShape ENTER_READ_ONLY;
                private static final MethodDescription.InDefinedShape ENTER_TYPING;
                private final TypeDefinition enterType;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.enterType.equals(((Factory) obj).enterType);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.enterType.hashCode();
                }

                static {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Enter.class).getDeclaredMethods();
                    ENTER_READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                    ENTER_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                }

                protected Factory(TypeDefinition typeDefinition) {
                    this.enterType = typeDefinition;
                }

                protected static Factory<Enter> of(TypeDefinition typeDefinition) {
                    return typeDefinition.represents(Void.TYPE) ? new Factory.Illegal(Enter.class) : new Factory(typeDefinition);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Enter> getAnnotationType() {
                    return Enter.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Enter> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Boolean) loadable.getValue(ENTER_READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                        throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                    }
                    return new ForEnterValue(inDefinedShape.getType(), this.enterType.asGenericType(), loadable);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForExitValue.class */
        public static class ForExitValue implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final TypeDescription.Generic exitType;
            private final boolean readOnly;
            private final Assigner.Typing typing;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForExitValue) obj).readOnly && this.typing.equals(((ForExitValue) obj).typing) && this.target.equals(((ForExitValue) obj).target) && this.exitType.equals(((ForExitValue) obj).exitType);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.exitType.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForExitValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, AnnotationDescription.Loadable<Exit> loadable) {
                this(generic, generic2, ((Boolean) loadable.getValue(Factory.EXIT_READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(Factory.EXIT_TYPING).load(Exit.class.getClassLoader()).resolve(Assigner.Typing.class));
            }

            public ForExitValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.exitType = generic2;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.exitType, this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.exitType + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(this.target, argumentHandler.exit(), assign);
                }
                StackManipulation assign2 = assigner.assign(this.target, this.exitType, this.typing);
                if (assign2.isValid()) {
                    return new Target.ForVariable.ReadWrite(this.target, argumentHandler.exit(), assign, assign2);
                }
                throw new IllegalStateException("Cannot assign " + this.target + " to " + this.exitType);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForExitValue$Factory.class */
            protected static class Factory implements Factory<Exit> {
                private static final MethodDescription.InDefinedShape EXIT_READ_ONLY;
                private static final MethodDescription.InDefinedShape EXIT_TYPING;
                private final TypeDefinition exitType;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.exitType.equals(((Factory) obj).exitType);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.exitType.hashCode();
                }

                static {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Exit.class).getDeclaredMethods();
                    EXIT_READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                    EXIT_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                }

                protected Factory(TypeDefinition typeDefinition) {
                    this.exitType = typeDefinition;
                }

                protected static Factory<Exit> of(TypeDefinition typeDefinition) {
                    return typeDefinition.represents(Void.TYPE) ? new Factory.Illegal(Exit.class) : new Factory(typeDefinition);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Exit> getAnnotationType() {
                    return Exit.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Exit> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Boolean) loadable.getValue(EXIT_READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                        throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                    }
                    return new ForExitValue(inDefinedShape.getType(), this.exitType.asGenericType(), loadable);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForLocalValue.class */
        public static class ForLocalValue implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final TypeDescription.Generic localType;
            private final String name;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.name.equals(((ForLocalValue) obj).name) && this.target.equals(((ForLocalValue) obj).target) && this.localType.equals(((ForLocalValue) obj).localType);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.localType.hashCode()) * 31) + this.name.hashCode();
            }

            public ForLocalValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, String str) {
                this.target = generic;
                this.localType = generic2;
                this.name = str;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.localType, this.target, Assigner.Typing.STATIC);
                StackManipulation assign2 = assigner.assign(this.target, this.localType, Assigner.Typing.STATIC);
                if (!assign.isValid() || !assign2.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.localType + " to " + this.target);
                }
                return new Target.ForVariable.ReadWrite(this.target, argumentHandler.named(this.name), assign, assign2);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForLocalValue$Factory.class */
            protected static class Factory implements Factory<Local> {
                protected static final MethodDescription.InDefinedShape LOCAL_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Local.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
                private final Map<String, TypeDefinition> namedTypes;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.namedTypes.equals(((Factory) obj).namedTypes);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.namedTypes.hashCode();
                }

                protected Factory(Map<String, TypeDefinition> map) {
                    this.namedTypes = map;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Local> getAnnotationType() {
                    return Local.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Local> loadable, Factory.AdviceType adviceType) {
                    String str = (String) loadable.getValue(LOCAL_VALUE).resolve(String.class);
                    TypeDefinition typeDefinition = this.namedTypes.get(str);
                    if (typeDefinition == null) {
                        throw new IllegalStateException("Named local variable is unknown: " + str);
                    }
                    return new ForLocalValue(inDefinedShape.getType(), typeDefinition.asGenericType(), str);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForReturnValue.class */
        public static class ForReturnValue implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final boolean readOnly;
            private final Assigner.Typing typing;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForReturnValue) obj).readOnly && this.typing.equals(((ForReturnValue) obj).typing) && this.target.equals(((ForReturnValue) obj).target);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForReturnValue(TypeDescription.Generic generic, AnnotationDescription.Loadable<Return> loadable) {
                this(generic, ((Boolean) loadable.getValue(Factory.RETURN_READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(Factory.RETURN_TYPING).load(Return.class.getClassLoader()).resolve(Assigner.Typing.class));
            }

            public ForReturnValue(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(methodDescription.getReturnType(), this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + methodDescription.getReturnType() + " to " + this.target);
                }
                if (this.readOnly) {
                    return methodDescription.getReturnType().represents(Void.TYPE) ? new Target.ForDefaultValue.ReadOnly(this.target) : new Target.ForVariable.ReadOnly(methodDescription.getReturnType(), argumentHandler.returned(), assign);
                }
                StackManipulation assign2 = assigner.assign(this.target, methodDescription.getReturnType(), this.typing);
                if (assign2.isValid()) {
                    return methodDescription.getReturnType().represents(Void.TYPE) ? new Target.ForDefaultValue.ReadWrite(this.target) : new Target.ForVariable.ReadWrite(methodDescription.getReturnType(), argumentHandler.returned(), assign, assign2);
                }
                throw new IllegalStateException("Cannot assign " + this.target + " to " + methodDescription.getReturnType());
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForReturnValue$Factory.class */
            protected enum Factory implements Factory<Return> {
                INSTANCE;

                private static final MethodDescription.InDefinedShape RETURN_READ_ONLY;
                private static final MethodDescription.InDefinedShape RETURN_TYPING;

                static {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Return.class).getDeclaredMethods();
                    RETURN_READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                    RETURN_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final Class<Return> getAnnotationType() {
                    return Return.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Return> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Boolean) loadable.getValue(RETURN_READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                        throw new IllegalStateException("Cannot write return value for " + inDefinedShape + " in read-only context");
                    }
                    return new ForReturnValue(inDefinedShape.getType(), loadable);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForThrowable.class */
        public static class ForThrowable implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final boolean readOnly;
            private final Assigner.Typing typing;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readOnly == ((ForThrowable) obj).readOnly && this.typing.equals(((ForThrowable) obj).typing) && this.target.equals(((ForThrowable) obj).target);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForThrowable(TypeDescription.Generic generic, AnnotationDescription.Loadable<Thrown> loadable) {
                this(generic, ((Boolean) loadable.getValue(Factory.THROWN_READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(Factory.THROWN_TYPING).load(Thrown.class.getClassLoader()).resolve(Assigner.Typing.class));
            }

            public ForThrowable(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(TypeDescription.ForLoadedType.of(Throwable.class).asGenericType(), this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign Throwable to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(TypeDescription.ForLoadedType.of(Throwable.class), argumentHandler.thrown(), assign);
                }
                StackManipulation assign2 = assigner.assign(this.target, TypeDescription.ForLoadedType.of(Throwable.class).asGenericType(), this.typing);
                if (assign2.isValid()) {
                    return new Target.ForVariable.ReadWrite(TypeDescription.ForLoadedType.of(Throwable.class), argumentHandler.thrown(), assign, assign2);
                }
                throw new IllegalStateException("Cannot assign " + this.target + " to Throwable");
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForThrowable$Factory.class */
            protected enum Factory implements Factory<Thrown> {
                INSTANCE;

                private static final MethodDescription.InDefinedShape THROWN_READ_ONLY;
                private static final MethodDescription.InDefinedShape THROWN_TYPING;

                static {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Thrown.class).getDeclaredMethods();
                    THROWN_READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                    THROWN_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                }

                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                protected static Factory<?> of(MethodDescription.InDefinedShape inDefinedShape) {
                    return ((TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class)).represents(NoExceptionHandler.class) ? new Factory.Illegal(Thrown.class) : INSTANCE;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final Class<Thrown> getAnnotationType() {
                    return Thrown.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public final OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Thrown> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Boolean) loadable.getValue(THROWN_READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                        throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                    }
                    return new ForThrowable(inDefinedShape.getType(), loadable);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForStackManipulation.class */
        public static class ForStackManipulation implements OffsetMapping {
            private final StackManipulation stackManipulation;
            private final TypeDescription.Generic typeDescription;
            private final TypeDescription.Generic targetType;
            private final Assigner.Typing typing;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typing.equals(((ForStackManipulation) obj).typing) && this.stackManipulation.equals(((ForStackManipulation) obj).stackManipulation) && this.typeDescription.equals(((ForStackManipulation) obj).typeDescription) && this.targetType.equals(((ForStackManipulation) obj).targetType);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.stackManipulation.hashCode()) * 31) + this.typeDescription.hashCode()) * 31) + this.targetType.hashCode()) * 31) + this.typing.hashCode();
            }

            public ForStackManipulation(StackManipulation stackManipulation, TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing) {
                this.stackManipulation = stackManipulation;
                this.typeDescription = generic;
                this.targetType = generic2;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.typeDescription, this.targetType, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.typeDescription + " to " + this.targetType);
                }
                return new Target.ForStackManipulation(new StackManipulation.Compound(this.stackManipulation, assign));
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForStackManipulation$Factory.class */
            public static class Factory<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final StackManipulation stackManipulation;
                private final TypeDescription.Generic typeDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((Factory) obj).annotationType) && this.stackManipulation.equals(((Factory) obj).stackManipulation) && this.typeDescription.equals(((Factory) obj).typeDescription);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.stackManipulation.hashCode()) * 31) + this.typeDescription.hashCode();
                }

                public Factory(Class<T> cls, TypeDescription typeDescription) {
                    this(cls, ClassConstant.of(typeDescription), TypeDescription.ForLoadedType.of(Class.class).asGenericType());
                }

                public Factory(Class<T> cls, EnumerationDescription enumerationDescription) {
                    this(cls, FieldAccess.forEnumeration(enumerationDescription), enumerationDescription.getEnumerationType().asGenericType());
                }

                public Factory(Class<T> cls, StackManipulation stackManipulation, TypeDescription.Generic generic) {
                    this.annotationType = cls;
                    this.stackManipulation = stackManipulation;
                    this.typeDescription = generic;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, @MaybeNull Object obj) {
                    StackManipulation javaConstantValue;
                    TypeDescription typeDescription;
                    if (obj == null) {
                        return new OfDefaultValue(cls);
                    }
                    if (obj instanceof Boolean) {
                        javaConstantValue = IntegerConstant.forValue(((Boolean) obj).booleanValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Boolean.TYPE);
                    } else if (obj instanceof Byte) {
                        javaConstantValue = IntegerConstant.forValue(((Byte) obj).byteValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Byte.TYPE);
                    } else if (obj instanceof Short) {
                        javaConstantValue = IntegerConstant.forValue(((Short) obj).shortValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Short.TYPE);
                    } else if (obj instanceof Character) {
                        javaConstantValue = IntegerConstant.forValue(((Character) obj).charValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Character.TYPE);
                    } else if (obj instanceof Integer) {
                        javaConstantValue = IntegerConstant.forValue(((Integer) obj).intValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Integer.TYPE);
                    } else if (obj instanceof Long) {
                        javaConstantValue = LongConstant.forValue(((Long) obj).longValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Long.TYPE);
                    } else if (obj instanceof Float) {
                        javaConstantValue = FloatConstant.forValue(((Float) obj).floatValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Float.TYPE);
                    } else if (obj instanceof Double) {
                        javaConstantValue = DoubleConstant.forValue(((Double) obj).doubleValue());
                        typeDescription = TypeDescription.ForLoadedType.of(Double.TYPE);
                    } else if (obj instanceof String) {
                        javaConstantValue = new TextConstant((String) obj);
                        typeDescription = TypeDescription.ForLoadedType.of(String.class);
                    } else if (obj instanceof Class) {
                        javaConstantValue = ClassConstant.of(TypeDescription.ForLoadedType.of((Class) obj));
                        typeDescription = TypeDescription.ForLoadedType.of(Class.class);
                    } else if (obj instanceof TypeDescription) {
                        javaConstantValue = ClassConstant.of((TypeDescription) obj);
                        typeDescription = TypeDescription.ForLoadedType.of(Class.class);
                    } else if (obj instanceof Enum) {
                        javaConstantValue = FieldAccess.forEnumeration(new EnumerationDescription.ForLoadedEnumeration((Enum) obj));
                        typeDescription = TypeDescription.ForLoadedType.of(((Enum) obj).getDeclaringClass());
                    } else if (obj instanceof EnumerationDescription) {
                        javaConstantValue = FieldAccess.forEnumeration((EnumerationDescription) obj);
                        typeDescription = ((EnumerationDescription) obj).getEnumerationType();
                    } else if (JavaType.METHOD_HANDLE.isInstance(obj)) {
                        JavaConstant.MethodHandle ofLoaded = JavaConstant.MethodHandle.ofLoaded(obj);
                        javaConstantValue = new JavaConstantValue(ofLoaded);
                        typeDescription = ofLoaded.getTypeDescription();
                    } else if (JavaType.METHOD_TYPE.isInstance(obj)) {
                        JavaConstant.MethodType ofLoaded2 = JavaConstant.MethodType.ofLoaded(obj);
                        javaConstantValue = new JavaConstantValue(ofLoaded2);
                        typeDescription = ofLoaded2.getTypeDescription();
                    } else if (obj instanceof JavaConstant) {
                        javaConstantValue = new JavaConstantValue((JavaConstant) obj);
                        typeDescription = ((JavaConstant) obj).getTypeDescription();
                    } else {
                        throw new IllegalStateException("Not a constant value: " + obj);
                    }
                    return new Factory(cls, javaConstantValue, typeDescription.asGenericType());
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForStackManipulation(this.stackManipulation, this.typeDescription, inDefinedShape.getType(), Assigner.Typing.STATIC);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForStackManipulation$OfDefaultValue.class */
            public static class OfDefaultValue<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((OfDefaultValue) obj).annotationType);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.annotationType.hashCode();
                }

                public OfDefaultValue(Class<T> cls) {
                    this.annotationType = cls;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForStackManipulation(DefaultValue.of(inDefinedShape.getType()), inDefinedShape.getType(), inDefinedShape.getType(), Assigner.Typing.STATIC);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForStackManipulation$OfAnnotationProperty.class */
            public static class OfAnnotationProperty<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final MethodDescription.InDefinedShape property;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((OfAnnotationProperty) obj).annotationType) && this.property.equals(((OfAnnotationProperty) obj).property);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.property.hashCode();
                }

                protected OfAnnotationProperty(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape) {
                    this.annotationType = cls;
                    this.property = inDefinedShape;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, String str) {
                    if (!cls.isAnnotation()) {
                        throw new IllegalArgumentException("Not an annotation type: " + cls);
                    }
                    try {
                        return new OfAnnotationProperty(cls, new MethodDescription.ForLoadedMethod(cls.getMethod(str, new Class[0])));
                    } catch (NoSuchMethodException e) {
                        throw new IllegalArgumentException("Cannot find a property " + str + " on " + cls, e);
                    }
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    Factory of;
                    Object resolve = loadable.getValue(this.property).resolve();
                    if (resolve instanceof TypeDescription) {
                        of = new Factory(this.annotationType, (TypeDescription) resolve);
                    } else if (resolve instanceof EnumerationDescription) {
                        of = new Factory(this.annotationType, (EnumerationDescription) resolve);
                    } else if (!(resolve instanceof AnnotationDescription)) {
                        of = Factory.of(this.annotationType, resolve);
                    } else {
                        throw new IllegalStateException("Cannot bind annotation as fixed value for " + this.property);
                    }
                    return of.make(inDefinedShape, loadable, adviceType);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForStackManipulation$OfDynamicInvocation.class */
            public static class OfDynamicInvocation<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final MethodDescription.InDefinedShape bootstrapMethod;
                private final List<? extends JavaConstant> arguments;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((OfDynamicInvocation) obj).annotationType) && this.bootstrapMethod.equals(((OfDynamicInvocation) obj).bootstrapMethod) && this.arguments.equals(((OfDynamicInvocation) obj).arguments);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.bootstrapMethod.hashCode()) * 31) + this.arguments.hashCode();
                }

                public OfDynamicInvocation(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape, List<? extends JavaConstant> list) {
                    this.annotationType = cls;
                    this.bootstrapMethod = inDefinedShape;
                    this.arguments = list;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    if (!inDefinedShape.getType().isInterface()) {
                        throw new IllegalArgumentException(inDefinedShape.getType() + " is not an interface");
                    }
                    if (!inDefinedShape.getType().getInterfaces().isEmpty()) {
                        throw new IllegalArgumentException(inDefinedShape.getType() + " must not extend other interfaces");
                    }
                    if (!inDefinedShape.getType().isPublic()) {
                        throw new IllegalArgumentException(inDefinedShape.getType() + " is mot public");
                    }
                    MethodList filter = inDefinedShape.getType().getDeclaredMethods().filter(ElementMatchers.isAbstract());
                    if (filter.size() != 1) {
                        throw new IllegalArgumentException(inDefinedShape.getType() + " must declare exactly one abstract method");
                    }
                    return new ForStackManipulation(MethodInvocation.invoke(this.bootstrapMethod).dynamic(((MethodDescription) filter.getOnly()).getInternalName(), inDefinedShape.getType().asErasure(), Collections.emptyList(), this.arguments), inDefinedShape.getType(), inDefinedShape.getType(), Assigner.Typing.STATIC);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForSerializedValue.class */
        public static class ForSerializedValue implements OffsetMapping {
            private final TypeDescription.Generic target;
            private final TypeDescription typeDescription;
            private final StackManipulation deserialization;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.target.equals(((ForSerializedValue) obj).target) && this.typeDescription.equals(((ForSerializedValue) obj).typeDescription) && this.deserialization.equals(((ForSerializedValue) obj).deserialization);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.typeDescription.hashCode()) * 31) + this.deserialization.hashCode();
            }

            public ForSerializedValue(TypeDescription.Generic generic, TypeDescription typeDescription, StackManipulation stackManipulation) {
                this.target = generic;
                this.typeDescription = typeDescription;
                this.deserialization = stackManipulation;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.typeDescription.asGenericType(), this.target, Assigner.Typing.DYNAMIC);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.typeDescription + " to " + this.target);
                }
                return new Target.ForStackManipulation(new StackManipulation.Compound(this.deserialization, assign));
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OffsetMapping$ForSerializedValue$Factory.class */
            public static class Factory<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final TypeDescription typeDescription;
                private final StackManipulation deserialization;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((Factory) obj).annotationType) && this.typeDescription.equals(((Factory) obj).typeDescription) && this.deserialization.equals(((Factory) obj).deserialization);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.annotationType.hashCode()) * 31) + this.typeDescription.hashCode()) * 31) + this.deserialization.hashCode();
                }

                protected Factory(Class<T> cls, TypeDescription typeDescription, StackManipulation stackManipulation) {
                    this.annotationType = cls;
                    this.typeDescription = typeDescription;
                    this.deserialization = stackManipulation;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, Serializable serializable, Class<?> cls2) {
                    if (!cls2.isInstance(serializable)) {
                        throw new IllegalArgumentException(serializable + " is no instance of " + cls2);
                    }
                    return new Factory(cls, TypeDescription.ForLoadedType.of(cls2), SerializedConstant.of(serializable));
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForSerializedValue(inDefinedShape.getType(), this.typeDescription, this.deserialization);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler.class */
    public interface ArgumentHandler {
        public static final int THIS_REFERENCE = 0;

        int argument(int i);

        int exit();

        int enter();

        int named(String str);

        int returned();

        int thrown();

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForInstrumentedMethod.class */
        public interface ForInstrumentedMethod extends ArgumentHandler {
            int variable(int i);

            int prepare(MethodVisitor methodVisitor);

            ForAdvice bindEnter(MethodDescription methodDescription);

            ForAdvice bindExit(MethodDescription methodDescription, boolean z);

            boolean isCopyingArguments();

            List<TypeDescription> getNamedTypes();

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForInstrumentedMethod$Default.class */
            public static abstract class Default implements ForInstrumentedMethod {
                protected final MethodDescription instrumentedMethod;
                protected final TypeDefinition exitType;
                protected final SortedMap<String, TypeDefinition> namedTypes;
                protected final TypeDefinition enterType;

                protected Default(MethodDescription methodDescription, TypeDefinition typeDefinition, SortedMap<String, TypeDefinition> sortedMap, TypeDefinition typeDefinition2) {
                    this.instrumentedMethod = methodDescription;
                    this.namedTypes = sortedMap;
                    this.exitType = typeDefinition;
                    this.enterType = typeDefinition2;
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int exit() {
                    return this.instrumentedMethod.getStackSize();
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int named(String str) {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.headMap(str).values());
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int enter() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values());
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int returned() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize();
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int thrown() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize() + this.instrumentedMethod.getReturnType().getStackSize().getSize();
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                public ForAdvice bindEnter(MethodDescription methodDescription) {
                    return new ForAdvice.Default.ForMethodEnter(this.instrumentedMethod, methodDescription, this.exitType, this.namedTypes);
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                public ForAdvice bindExit(MethodDescription methodDescription, boolean z) {
                    return new ForAdvice.Default.ForMethodExit(this.instrumentedMethod, methodDescription, this.exitType, this.namedTypes, this.enterType, z ? StackSize.ZERO : StackSize.SINGLE);
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                public List<TypeDescription> getNamedTypes() {
                    ArrayList arrayList = new ArrayList(this.namedTypes.size());
                    Iterator<TypeDefinition> it = this.namedTypes.values().iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().asErasure());
                    }
                    return arrayList;
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForInstrumentedMethod$Default$Simple.class */
                protected static class Simple extends Default {
                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass();
                    }

                    public int hashCode() {
                        return getClass().hashCode();
                    }

                    protected Simple(MethodDescription methodDescription, TypeDefinition typeDefinition, SortedMap<String, TypeDefinition> sortedMap, TypeDefinition typeDefinition2) {
                        super(methodDescription, typeDefinition, sortedMap, typeDefinition2);
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                    public int argument(int i) {
                        return i < this.instrumentedMethod.getStackSize() ? i : i + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize();
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                    public int variable(int i) {
                        if (i < (this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size()) {
                            return i;
                        }
                        return i + (this.exitType.represents(Void.TYPE) ? 0 : 1) + this.namedTypes.size() + (this.enterType.represents(Void.TYPE) ? 0 : 1);
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                    public boolean isCopyingArguments() {
                        return false;
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                    public int prepare(MethodVisitor methodVisitor) {
                        return 0;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForInstrumentedMethod$Default$Copying.class */
                protected static class Copying extends Default {
                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass();
                    }

                    public int hashCode() {
                        return getClass().hashCode();
                    }

                    protected Copying(MethodDescription methodDescription, TypeDefinition typeDefinition, SortedMap<String, TypeDefinition> sortedMap, TypeDefinition typeDefinition2) {
                        super(methodDescription, typeDefinition, sortedMap, typeDefinition2);
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                    public int argument(int i) {
                        return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize() + i;
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                    public int variable(int i) {
                        return (this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size() + (this.exitType.represents(Void.TYPE) ? 0 : 1) + this.namedTypes.size() + (this.enterType.represents(Void.TYPE) ? 0 : 1) + i;
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                    public boolean isCopyingArguments() {
                        return true;
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForInstrumentedMethod
                    public int prepare(MethodVisitor methodVisitor) {
                        StackSize stackSize;
                        if (!this.instrumentedMethod.isStatic()) {
                            methodVisitor.visitVarInsn(25, 0);
                            methodVisitor.visitVarInsn(58, this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize());
                            stackSize = StackSize.SINGLE;
                        } else {
                            stackSize = StackSize.ZERO;
                        }
                        Iterator it = this.instrumentedMethod.getParameters().iterator();
                        while (it.hasNext()) {
                            ParameterDescription parameterDescription = (ParameterDescription) it.next();
                            Type type = Type.getType(parameterDescription.getType().asErasure().getDescriptor());
                            methodVisitor.visitVarInsn(type.getOpcode(21), parameterDescription.getOffset());
                            methodVisitor.visitVarInsn(type.getOpcode(54), this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize() + parameterDescription.getOffset());
                            stackSize = stackSize.maximum(parameterDescription.getType().getStackSize());
                        }
                        return stackSize.getSize();
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForAdvice.class */
        public interface ForAdvice extends ArgumentHandler {
            int mapped(int i);

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForAdvice$Default.class */
            public static abstract class Default implements ForAdvice {
                protected final MethodDescription instrumentedMethod;
                protected final MethodDescription adviceMethod;
                protected final TypeDefinition exitType;
                protected final SortedMap<String, TypeDefinition> namedTypes;

                protected Default(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDefinition typeDefinition, SortedMap<String, TypeDefinition> sortedMap) {
                    this.instrumentedMethod = methodDescription;
                    this.adviceMethod = methodDescription2;
                    this.exitType = typeDefinition;
                    this.namedTypes = sortedMap;
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int argument(int i) {
                    return i;
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int exit() {
                    return this.instrumentedMethod.getStackSize();
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int named(String str) {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.headMap(str).values());
                }

                @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                public int enter() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values());
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForAdvice$Default$ForMethodEnter.class */
                protected static class ForMethodEnter extends Default {
                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass();
                    }

                    public int hashCode() {
                        return getClass().hashCode();
                    }

                    protected ForMethodEnter(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDefinition typeDefinition, SortedMap<String, TypeDefinition> sortedMap) {
                        super(methodDescription, methodDescription2, typeDefinition, sortedMap);
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                    public int returned() {
                        throw new IllegalStateException("Cannot resolve the return value offset during enter advice");
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                    public int thrown() {
                        throw new IllegalStateException("Cannot resolve the thrown value offset during enter advice");
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForAdvice
                    public int mapped(int i) {
                        return (((this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize()) + StackSize.of(this.namedTypes.values())) - this.adviceMethod.getStackSize()) + i;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$ForAdvice$Default$ForMethodExit.class */
                protected static class ForMethodExit extends Default {
                    private final TypeDefinition enterType;
                    private final StackSize throwableSize;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.throwableSize.equals(((ForMethodExit) obj).throwableSize) && this.enterType.equals(((ForMethodExit) obj).enterType);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.enterType.hashCode()) * 31) + this.throwableSize.hashCode();
                    }

                    protected ForMethodExit(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDefinition typeDefinition, SortedMap<String, TypeDefinition> sortedMap, TypeDefinition typeDefinition2, StackSize stackSize) {
                        super(methodDescription, methodDescription2, typeDefinition, sortedMap);
                        this.enterType = typeDefinition2;
                        this.throwableSize = stackSize;
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                    public int returned() {
                        return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize();
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler
                    public int thrown() {
                        return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of(this.namedTypes.values()) + this.enterType.getStackSize().getSize() + this.instrumentedMethod.getReturnType().getStackSize().getSize();
                    }

                    @Override // net.bytebuddy.asm.Advice.ArgumentHandler.ForAdvice
                    public int mapped(int i) {
                        return ((((((this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize()) + StackSize.of(this.namedTypes.values())) + this.enterType.getStackSize().getSize()) + this.instrumentedMethod.getReturnType().getStackSize().getSize()) + this.throwableSize.getSize()) - this.adviceMethod.getStackSize()) + i;
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ArgumentHandler$Factory.class */
        public enum Factory {
            SIMPLE { // from class: net.bytebuddy.asm.Advice.ArgumentHandler.Factory.1
                @Override // net.bytebuddy.asm.Advice.ArgumentHandler.Factory
                protected final ForInstrumentedMethod resolve(MethodDescription methodDescription, TypeDefinition typeDefinition, TypeDefinition typeDefinition2, SortedMap<String, TypeDefinition> sortedMap) {
                    return new ForInstrumentedMethod.Default.Simple(methodDescription, typeDefinition2, sortedMap, typeDefinition);
                }
            },
            COPYING { // from class: net.bytebuddy.asm.Advice.ArgumentHandler.Factory.2
                @Override // net.bytebuddy.asm.Advice.ArgumentHandler.Factory
                protected final ForInstrumentedMethod resolve(MethodDescription methodDescription, TypeDefinition typeDefinition, TypeDefinition typeDefinition2, SortedMap<String, TypeDefinition> sortedMap) {
                    return new ForInstrumentedMethod.Default.Copying(methodDescription, typeDefinition2, sortedMap, typeDefinition);
                }
            };

            protected abstract ForInstrumentedMethod resolve(MethodDescription methodDescription, TypeDefinition typeDefinition, TypeDefinition typeDefinition2, SortedMap<String, TypeDefinition> sortedMap);

            /* synthetic */ Factory(byte b2) {
                this();
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$PostProcessor.class */
    public interface PostProcessor {
        StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, StackMapFrameHandler.ForPostProcessor forPostProcessor, StackManipulation stackManipulation);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$PostProcessor$Factory.class */
        public interface Factory {
            PostProcessor make(MethodDescription.InDefinedShape inDefinedShape, boolean z);

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$PostProcessor$Factory$Compound.class */
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

                public Compound(Factory... factoryArr) {
                    this((List<? extends Factory>) Arrays.asList(factoryArr));
                }

                public Compound(List<? extends Factory> list) {
                    this.factories = new ArrayList();
                    for (Factory factory : list) {
                        if (factory instanceof Compound) {
                            this.factories.addAll(((Compound) factory).factories);
                        } else if (!(factory instanceof NoOp)) {
                            this.factories.add(factory);
                        }
                    }
                }

                @Override // net.bytebuddy.asm.Advice.PostProcessor.Factory
                public PostProcessor make(MethodDescription.InDefinedShape inDefinedShape, boolean z) {
                    ArrayList arrayList = new ArrayList(this.factories.size());
                    Iterator<Factory> it = this.factories.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().make(inDefinedShape, z));
                    }
                    return new Compound(arrayList);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$PostProcessor$NoOp.class */
        public enum NoOp implements PostProcessor, Factory {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.PostProcessor
            public final StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, StackMapFrameHandler.ForPostProcessor forPostProcessor, StackManipulation stackManipulation) {
                return StackManipulation.Trivial.INSTANCE;
            }

            @Override // net.bytebuddy.asm.Advice.PostProcessor.Factory
            public final PostProcessor make(MethodDescription.InDefinedShape inDefinedShape, boolean z) {
                return this;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$PostProcessor$Compound.class */
        public static class Compound implements PostProcessor {
            private final List<PostProcessor> postProcessors;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.postProcessors.equals(((Compound) obj).postProcessors);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.postProcessors.hashCode();
            }

            protected Compound(List<PostProcessor> list) {
                this.postProcessors = list;
            }

            @Override // net.bytebuddy.asm.Advice.PostProcessor
            public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, StackMapFrameHandler.ForPostProcessor forPostProcessor, StackManipulation stackManipulation) {
                ArrayList arrayList = new ArrayList(this.postProcessors.size());
                Iterator<PostProcessor> it = this.postProcessors.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().resolve(typeDescription, methodDescription, assigner, argumentHandler, forPostProcessor, stackManipulation));
                }
                return new StackManipulation.Compound(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Delegator.class */
    public interface Delegator {
        void apply(MethodVisitor methodVisitor, MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodDescription methodDescription, boolean z);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Delegator$ForStaticInvocation.class */
        public enum ForStaticInvocation implements Delegator {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.Delegator
            public final void apply(MethodVisitor methodVisitor, MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodDescription methodDescription, boolean z) {
                methodVisitor.visitMethodInsn(184, inDefinedShape.getDeclaringType().getInternalName(), inDefinedShape.getInternalName(), inDefinedShape.getDescriptor(), false);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Delegator$ForDynamicInvocation.class */
        public static class ForDynamicInvocation implements Delegator {
            private final MethodDescription.InDefinedShape bootstrapMethod;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.bootstrapMethod.equals(((ForDynamicInvocation) obj).bootstrapMethod);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.bootstrapMethod.hashCode();
            }

            protected ForDynamicInvocation(MethodDescription.InDefinedShape inDefinedShape) {
                this.bootstrapMethod = inDefinedShape;
            }

            protected static Delegator of(MethodDescription.InDefinedShape inDefinedShape) {
                if (!inDefinedShape.isInvokeBootstrap()) {
                    throw new IllegalArgumentException("Not a suitable bootstrap target: " + inDefinedShape);
                }
                return new ForDynamicInvocation(inDefinedShape);
            }

            @Override // net.bytebuddy.asm.Advice.Delegator
            public void apply(MethodVisitor methodVisitor, MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodDescription methodDescription, boolean z) {
                Object[] objArr;
                if (methodDescription.isTypeInitializer()) {
                    if (!this.bootstrapMethod.isInvokeBootstrap(Arrays.asList(TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Integer.TYPE), TypeDescription.ForLoadedType.of(Class.class), TypeDescription.ForLoadedType.of(String.class)))) {
                        throw new IllegalArgumentException(this.bootstrapMethod + " is not accepting advice bootstrap arguments");
                    }
                    Object[] objArr2 = new Object[4];
                    objArr2[0] = inDefinedShape.getDeclaringType().getName();
                    objArr2[1] = Integer.valueOf(z ? 1 : 0);
                    objArr2[2] = Type.getType(typeDescription.getDescriptor());
                    objArr2[3] = methodDescription.getInternalName();
                    objArr = objArr2;
                } else {
                    if (!this.bootstrapMethod.isInvokeBootstrap(Arrays.asList(TypeDescription.ForLoadedType.of(String.class), TypeDescription.ForLoadedType.of(Integer.TYPE), TypeDescription.ForLoadedType.of(Class.class), TypeDescription.ForLoadedType.of(String.class), JavaType.METHOD_HANDLE.getTypeStub()))) {
                        throw new IllegalArgumentException(this.bootstrapMethod + " is not accepting advice bootstrap arguments");
                    }
                    Object[] objArr3 = new Object[5];
                    objArr3[0] = inDefinedShape.getDeclaringType().getName();
                    objArr3[1] = Integer.valueOf(z ? 1 : 0);
                    objArr3[2] = Type.getType(typeDescription.getDescriptor());
                    objArr3[3] = methodDescription.getInternalName();
                    objArr3[4] = JavaConstant.MethodHandle.of(methodDescription.asDefined()).accept(JavaConstantValue.Visitor.INSTANCE);
                    objArr = objArr3;
                }
                methodVisitor.visitInvokeDynamicInsn(inDefinedShape.getInternalName(), inDefinedShape.getDescriptor(), new Handle(this.bootstrapMethod.isConstructor() ? 8 : 6, this.bootstrapMethod.getDeclaringType().getInternalName(), this.bootstrapMethod.getInternalName(), this.bootstrapMethod.getDescriptor(), false), objArr);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler.class */
    public interface MethodSizeHandler {
        public static final int UNDEFINED_SIZE = 32767;

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler$ForAdvice.class */
        public interface ForAdvice extends MethodSizeHandler {
            void requireStackSizePadding(int i);

            void requireLocalVariableLengthPadding(int i);

            void recordMaxima(int i, int i2);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler$ForInstrumentedMethod.class */
        public interface ForInstrumentedMethod extends MethodSizeHandler {
            ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape);

            ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape);

            int compoundStackSize(int i);

            int compoundLocalVariableLength(int i);
        }

        void requireStackSize(int i);

        void requireLocalVariableLength(int i);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler$NoOp.class */
        public enum NoOp implements ForAdvice, ForInstrumentedMethod {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public final ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public final ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public final int compoundStackSize(int i) {
                return 32767;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public final int compoundLocalVariableLength(int i) {
                return 32767;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
            public final void requireStackSize(int i) {
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
            public final void requireLocalVariableLength(int i) {
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
            public final void requireStackSizePadding(int i) {
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
            public final void requireLocalVariableLengthPadding(int i) {
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
            public final void recordMaxima(int i, int i2) {
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler$Default.class */
        public static abstract class Default implements ForInstrumentedMethod {
            protected final MethodDescription instrumentedMethod;
            protected final List<? extends TypeDescription> initialTypes;
            protected final List<? extends TypeDescription> preMethodTypes;
            protected final List<? extends TypeDescription> postMethodTypes;
            protected int stackSize;
            protected int localVariableLength;

            protected Default(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3) {
                this.instrumentedMethod = methodDescription;
                this.initialTypes = list;
                this.preMethodTypes = list2;
                this.postMethodTypes = list3;
            }

            protected static ForInstrumentedMethod of(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, boolean z, int i) {
                if ((i & 3) != 0) {
                    return NoOp.INSTANCE;
                }
                if (z) {
                    return new WithCopiedArguments(methodDescription, list, list2, list3);
                }
                return new WithRetainedArguments(methodDescription, list, list2, list3);
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return new ForAdvice(inDefinedShape, this.instrumentedMethod.getStackSize() + StackSize.of(this.initialTypes));
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
            public void requireStackSize(int i) {
                this.stackSize = Math.max(this.stackSize, i);
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
            public void requireLocalVariableLength(int i) {
                this.localVariableLength = Math.max(this.localVariableLength, i);
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public int compoundStackSize(int i) {
                return Math.max(this.stackSize, i);
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public int compoundLocalVariableLength(int i) {
                return Math.max(this.localVariableLength, i + StackSize.of(this.postMethodTypes) + StackSize.of(this.initialTypes) + StackSize.of(this.preMethodTypes));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler$Default$WithRetainedArguments.class */
            public static class WithRetainedArguments extends Default {
                protected WithRetainedArguments(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3) {
                    super(methodDescription, list, list2, list3);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForAdvice(inDefinedShape, this.instrumentedMethod.getStackSize() + StackSize.of(this.postMethodTypes) + StackSize.of(this.initialTypes) + StackSize.of(this.preMethodTypes));
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.Default, net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
                public int compoundLocalVariableLength(int i) {
                    return Math.max(this.localVariableLength, i + StackSize.of(this.postMethodTypes) + StackSize.of(this.initialTypes) + StackSize.of(this.preMethodTypes));
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler$Default$WithCopiedArguments.class */
            public static class WithCopiedArguments extends Default {
                protected WithCopiedArguments(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3) {
                    super(methodDescription, list, list2, list3);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForAdvice(inDefinedShape, (2 * this.instrumentedMethod.getStackSize()) + StackSize.of(this.initialTypes) + StackSize.of(this.preMethodTypes) + StackSize.of(this.postMethodTypes));
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.Default, net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
                public int compoundLocalVariableLength(int i) {
                    return Math.max(this.localVariableLength, i + this.instrumentedMethod.getStackSize() + StackSize.of(this.postMethodTypes) + StackSize.of(this.initialTypes) + StackSize.of(this.preMethodTypes));
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$MethodSizeHandler$Default$ForAdvice.class */
            protected class ForAdvice implements ForAdvice {
                private final MethodDescription.InDefinedShape adviceMethod;
                private final int baseLocalVariableLength;
                private int stackSizePadding;
                private int localVariableLengthPadding;

                protected ForAdvice(MethodDescription.InDefinedShape inDefinedShape, int i) {
                    this.adviceMethod = inDefinedShape;
                    this.baseLocalVariableLength = i;
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
                public void requireStackSize(int i) {
                    Default.this.requireStackSize(i);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
                public void requireLocalVariableLength(int i) {
                    Default.this.requireLocalVariableLength(i);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
                public void requireStackSizePadding(int i) {
                    this.stackSizePadding = Math.max(this.stackSizePadding, i);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
                public void requireLocalVariableLengthPadding(int i) {
                    this.localVariableLengthPadding = Math.max(this.localVariableLengthPadding, i);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
                public void recordMaxima(int i, int i2) {
                    Default.this.requireStackSize(i + this.stackSizePadding);
                    Default.this.requireLocalVariableLength((i2 - this.adviceMethod.getStackSize()) + this.baseLocalVariableLength + this.localVariableLengthPadding);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler.class */
    public interface StackMapFrameHandler {

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$ForAdvice.class */
        public interface ForAdvice extends StackMapFrameHandler, ForPostProcessor {
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$ForInstrumentedMethod.class */
        public interface ForInstrumentedMethod extends StackMapFrameHandler {
            ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape);

            ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape);

            int getReaderHint();

            void injectInitializationFrame(MethodVisitor methodVisitor);

            void injectStartFrame(MethodVisitor methodVisitor);

            void injectPostCompletionFrame(MethodVisitor methodVisitor);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$ForPostProcessor.class */
        public interface ForPostProcessor {
            void injectIntermediateFrame(MethodVisitor methodVisitor, List<? extends TypeDescription> list);
        }

        void translateFrame(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2);

        void injectReturnFrame(MethodVisitor methodVisitor);

        void injectExceptionFrame(MethodVisitor methodVisitor);

        void injectCompletionFrame(MethodVisitor methodVisitor);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$NoOp.class */
        public enum NoOp implements ForAdvice, ForInstrumentedMethod {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public final ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public final ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public final int getReaderHint() {
                return 4;
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public final void translateFrame(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public final void injectReturnFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public final void injectExceptionFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public final void injectCompletionFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public final void injectInitializationFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public final void injectStartFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public final void injectPostCompletionFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForPostProcessor
            public final void injectIntermediateFrame(MethodVisitor methodVisitor, List<? extends TypeDescription> list) {
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default.class */
        public static abstract class Default implements ForInstrumentedMethod {
            protected static final Object[] EMPTY = new Object[0];
            protected final TypeDescription instrumentedType;
            protected final MethodDescription instrumentedMethod;
            protected final List<? extends TypeDescription> initialTypes;
            protected final List<? extends TypeDescription> latentTypes;
            protected final List<? extends TypeDescription> preMethodTypes;
            protected final List<? extends TypeDescription> postMethodTypes;
            protected final boolean expandFrames;
            protected int currentFrameDivergence;

            protected Default(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, List<? extends TypeDescription> list4, boolean z) {
                this.instrumentedType = typeDescription;
                this.instrumentedMethod = methodDescription;
                this.initialTypes = list;
                this.latentTypes = list2;
                this.preMethodTypes = list3;
                this.postMethodTypes = list4;
                this.expandFrames = z;
            }

            protected static ForInstrumentedMethod of(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, List<? extends TypeDescription> list4, boolean z, boolean z2, ClassFileVersion classFileVersion, int i, int i2) {
                if ((i & 2) != 0 || classFileVersion.isLessThan(ClassFileVersion.JAVA_V6)) {
                    return NoOp.INSTANCE;
                }
                if (!z && list.isEmpty()) {
                    return new Trivial(typeDescription, methodDescription, list2, (i2 & 8) != 0);
                }
                if (z2) {
                    return new WithPreservedArguments.WithArgumentCopy(typeDescription, methodDescription, list, list2, list3, list4, (i2 & 8) != 0);
                }
                return new WithPreservedArguments.WithoutArgumentCopy(typeDescription, methodDescription, list, list2, list3, list4, (i2 & 8) != 0, !methodDescription.isConstructor());
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return new ForAdvice(inDefinedShape, this.initialTypes, this.latentTypes, this.preMethodTypes, TranslationMode.ENTER, this.instrumentedMethod.isConstructor() ? Initialization.UNITIALIZED : Initialization.INITIALIZED);
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public int getReaderHint() {
                return this.expandFrames ? 8 : 0;
            }

            protected void translateFrame(MethodVisitor methodVisitor, TranslationMode translationMode, MethodDescription methodDescription, List<? extends TypeDescription> list, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                int i4;
                switch (i) {
                    case -1:
                    case 0:
                        if (methodDescription.getParameters().size() + (methodDescription.isStatic() ? 0 : 1) > i2) {
                            throw new IllegalStateException("Inconsistent frame length for " + methodDescription + ": " + i2);
                        }
                        if (methodDescription.isStatic()) {
                            i4 = 0;
                        } else {
                            if (!translationMode.isPossibleThisFrameValue(this.instrumentedType, this.instrumentedMethod, objArr[0])) {
                                throw new IllegalStateException(methodDescription + " is inconsistent for 'this' reference: " + objArr[0]);
                            }
                            i4 = 1;
                        }
                        for (int i5 = 0; i5 < methodDescription.getParameters().size(); i5++) {
                            if (!Initialization.INITIALIZED.toFrame(((ParameterDescription) methodDescription.getParameters().get(i5)).getType().asErasure()).equals(objArr[i5 + i4])) {
                                throw new IllegalStateException(methodDescription + " is inconsistent at " + i5 + ": " + objArr[i5 + i4]);
                            }
                        }
                        Object[] objArr3 = new Object[((i2 - (methodDescription.isStatic() ? 0 : 1)) - methodDescription.getParameters().size()) + (this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size() + list.size()];
                        int copy = translationMode.copy(this.instrumentedType, this.instrumentedMethod, methodDescription, objArr, objArr3);
                        Iterator<? extends TypeDescription> it = list.iterator();
                        while (it.hasNext()) {
                            int i6 = copy;
                            copy++;
                            objArr3[i6] = Initialization.INITIALIZED.toFrame(it.next());
                        }
                        System.arraycopy(objArr, methodDescription.getParameters().size() + (methodDescription.isStatic() ? 0 : 1), objArr3, copy, objArr3.length - copy);
                        i2 = objArr3.length;
                        objArr = objArr3;
                        this.currentFrameDivergence = objArr3.length - copy;
                        break;
                    case 1:
                        this.currentFrameDivergence += i2;
                        break;
                    case 2:
                        this.currentFrameDivergence -= i2;
                        if (this.currentFrameDivergence < 0) {
                            throw new IllegalStateException(methodDescription + " dropped " + Math.abs(this.currentFrameDivergence) + " implicit frames");
                        }
                        break;
                    case 3:
                    case 4:
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected frame type: " + i);
                }
                methodVisitor.visitFrame(i, i2, objArr, i3, objArr2);
            }

            protected void injectFullFrame(MethodVisitor methodVisitor, Initialization initialization, List<? extends TypeDescription> list, List<? extends TypeDescription> list2) {
                Object[] objArr = new Object[this.instrumentedMethod.getParameters().size() + (this.instrumentedMethod.isStatic() ? 0 : 1) + list.size()];
                int i = 0;
                if (!this.instrumentedMethod.isStatic()) {
                    i = 0 + 1;
                    objArr[0] = initialization.toFrame(this.instrumentedType);
                }
                Iterator it = this.instrumentedMethod.getParameters().asTypeList().asErasures().iterator();
                while (it.hasNext()) {
                    int i2 = i;
                    i++;
                    objArr[i2] = Initialization.INITIALIZED.toFrame((TypeDescription) it.next());
                }
                Iterator<? extends TypeDescription> it2 = list.iterator();
                while (it2.hasNext()) {
                    int i3 = i;
                    i++;
                    objArr[i3] = Initialization.INITIALIZED.toFrame(it2.next());
                }
                int i4 = 0;
                Object[] objArr2 = new Object[list2.size()];
                Iterator<? extends TypeDescription> it3 = list2.iterator();
                while (it3.hasNext()) {
                    int i5 = i4;
                    i4++;
                    objArr2[i5] = Initialization.INITIALIZED.toFrame(it3.next());
                }
                methodVisitor.visitFrame(this.expandFrames ? -1 : 0, objArr.length, objArr, objArr2.length, objArr2);
                this.currentFrameDivergence = 0;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default$TranslationMode.class */
            public enum TranslationMode {
                COPY { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode.1
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected final int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int size = methodDescription.getParameters().size() + (methodDescription.isStatic() ? 0 : 1);
                        System.arraycopy(objArr, 0, objArr2, 0, size);
                        return size;
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected final boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        return (methodDescription.isConstructor() && Opcodes.UNINITIALIZED_THIS.equals(obj)) || Initialization.INITIALIZED.toFrame(typeDescription).equals(obj);
                    }
                },
                ENTER { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode.2
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected final int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int i = 0;
                        if (!methodDescription.isStatic()) {
                            i = 0 + 1;
                            objArr2[0] = methodDescription.isConstructor() ? Opcodes.UNINITIALIZED_THIS : Initialization.INITIALIZED.toFrame(typeDescription);
                        }
                        Iterator it = methodDescription.getParameters().asTypeList().asErasures().iterator();
                        while (it.hasNext()) {
                            int i2 = i;
                            i++;
                            objArr2[i2] = Initialization.INITIALIZED.toFrame((TypeDescription) it.next());
                        }
                        return i;
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected final boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        if (methodDescription.isConstructor()) {
                            return Opcodes.UNINITIALIZED_THIS.equals(obj);
                        }
                        return Initialization.INITIALIZED.toFrame(typeDescription).equals(obj);
                    }
                },
                EXIT { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode.3
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected final int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int i = 0;
                        if (!methodDescription.isStatic()) {
                            i = 0 + 1;
                            objArr2[0] = Initialization.INITIALIZED.toFrame(typeDescription);
                        }
                        Iterator it = methodDescription.getParameters().asTypeList().asErasures().iterator();
                        while (it.hasNext()) {
                            int i2 = i;
                            i++;
                            objArr2[i2] = Initialization.INITIALIZED.toFrame((TypeDescription) it.next());
                        }
                        return i;
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected final boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        return Initialization.INITIALIZED.toFrame(typeDescription).equals(obj);
                    }
                };

                protected abstract int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2);

                protected abstract boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj);

                /* synthetic */ TranslationMode(byte b2) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default$Initialization.class */
            public enum Initialization {
                UNITIALIZED { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.Initialization.1
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.Initialization
                    protected final Object toFrame(TypeDescription typeDescription) {
                        if (typeDescription.isPrimitive()) {
                            throw new IllegalArgumentException("Cannot assume primitive uninitialized value: " + typeDescription);
                        }
                        return Opcodes.UNINITIALIZED_THIS;
                    }
                },
                INITIALIZED { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.Initialization.2
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.Initialization
                    protected final Object toFrame(TypeDescription typeDescription) {
                        if (typeDescription.represents(Boolean.TYPE) || typeDescription.represents(Byte.TYPE) || typeDescription.represents(Short.TYPE) || typeDescription.represents(Character.TYPE) || typeDescription.represents(Integer.TYPE)) {
                            return Opcodes.INTEGER;
                        }
                        if (typeDescription.represents(Long.TYPE)) {
                            return Opcodes.LONG;
                        }
                        if (typeDescription.represents(Float.TYPE)) {
                            return Opcodes.FLOAT;
                        }
                        if (typeDescription.represents(Double.TYPE)) {
                            return Opcodes.DOUBLE;
                        }
                        return typeDescription.getInternalName();
                    }
                };

                protected abstract Object toFrame(TypeDescription typeDescription);

                /* synthetic */ Initialization(byte b2) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default$Trivial.class */
            public static class Trivial extends Default {
                protected Trivial(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, boolean z) {
                    super(typeDescription, methodDescription, Collections.emptyList(), list, Collections.emptyList(), Collections.emptyList(), z);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void translateFrame(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                    methodVisitor.visitFrame(i, i2, objArr, i3, objArr2);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    throw new IllegalStateException("Did not expect exit advice " + inDefinedShape + " for " + this.instrumentedMethod);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectReturnFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect return frame for " + this.instrumentedMethod);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectExceptionFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect exception frame for " + this.instrumentedMethod);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectCompletionFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect completion frame for " + this.instrumentedMethod);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                public void injectPostCompletionFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect post completion frame for " + this.instrumentedMethod);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                public void injectInitializationFrame(MethodVisitor methodVisitor) {
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                public void injectStartFrame(MethodVisitor methodVisitor) {
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default$WithPreservedArguments.class */
            protected static abstract class WithPreservedArguments extends Default {
                protected boolean allowCompactCompletionFrame;

                protected WithPreservedArguments(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, List<? extends TypeDescription> list4, boolean z, boolean z2) {
                    super(typeDescription, methodDescription, list, list2, list3, list4, z);
                    this.allowCompactCompletionFrame = z2;
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default
                @SuppressFBWarnings(value = {"RC_REF_COMPARISON_BAD_PRACTICE"}, justification = "ASM models frames by reference identity.")
                protected void translateFrame(MethodVisitor methodVisitor, TranslationMode translationMode, MethodDescription methodDescription, List<? extends TypeDescription> list, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                    if (i == 0 && i2 > 0 && objArr[0] != Opcodes.UNINITIALIZED_THIS) {
                        this.allowCompactCompletionFrame = true;
                    }
                    super.translateFrame(methodVisitor, translationMode, methodDescription, list, i, i2, objArr, i3, objArr2);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForAdvice(inDefinedShape, CompoundList.of(this.initialTypes, this.preMethodTypes, this.postMethodTypes), Collections.emptyList(), Collections.emptyList(), TranslationMode.EXIT, Initialization.INITIALIZED);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectReturnFrame(MethodVisitor methodVisitor) {
                    List<? extends TypeDescription> singletonList;
                    if (!this.expandFrames && this.currentFrameDivergence == 0) {
                        if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                            int length = EMPTY.length;
                            Object[] objArr = EMPTY;
                            methodVisitor.visitFrame(3, length, objArr, objArr.length, EMPTY);
                            return;
                        }
                        methodVisitor.visitFrame(4, EMPTY.length, EMPTY, 1, new Object[]{Initialization.INITIALIZED.toFrame(this.instrumentedMethod.getReturnType().asErasure())});
                        return;
                    }
                    Initialization initialization = Initialization.INITIALIZED;
                    List<? extends TypeDescription> of = CompoundList.of((List) this.initialTypes, (List) this.preMethodTypes);
                    if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        singletonList = Collections.emptyList();
                    } else {
                        singletonList = Collections.singletonList(this.instrumentedMethod.getReturnType().asErasure());
                    }
                    injectFullFrame(methodVisitor, initialization, of, singletonList);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectExceptionFrame(MethodVisitor methodVisitor) {
                    if (!this.expandFrames && this.currentFrameDivergence == 0) {
                        methodVisitor.visitFrame(4, EMPTY.length, EMPTY, 1, new Object[]{Type.getInternalName(Throwable.class)});
                    } else {
                        injectFullFrame(methodVisitor, Initialization.INITIALIZED, CompoundList.of((List) this.initialTypes, (List) this.preMethodTypes), Collections.singletonList(TypeDescription.ForLoadedType.of(Throwable.class)));
                    }
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectCompletionFrame(MethodVisitor methodVisitor) {
                    if (this.allowCompactCompletionFrame && !this.expandFrames && this.currentFrameDivergence == 0 && this.postMethodTypes.size() < 4) {
                        if (this.postMethodTypes.isEmpty()) {
                            int length = EMPTY.length;
                            Object[] objArr = EMPTY;
                            methodVisitor.visitFrame(3, length, objArr, objArr.length, EMPTY);
                            return;
                        }
                        Object[] objArr2 = new Object[this.postMethodTypes.size()];
                        int i = 0;
                        Iterator<? extends TypeDescription> it = this.postMethodTypes.iterator();
                        while (it.hasNext()) {
                            int i2 = i;
                            i++;
                            objArr2[i2] = Initialization.INITIALIZED.toFrame(it.next());
                        }
                        methodVisitor.visitFrame(1, objArr2.length, objArr2, EMPTY.length, EMPTY);
                        return;
                    }
                    injectFullFrame(methodVisitor, Initialization.INITIALIZED, CompoundList.of(this.initialTypes, this.preMethodTypes, this.postMethodTypes), Collections.emptyList());
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                public void injectPostCompletionFrame(MethodVisitor methodVisitor) {
                    if (!this.expandFrames && this.currentFrameDivergence == 0) {
                        int length = EMPTY.length;
                        Object[] objArr = EMPTY;
                        methodVisitor.visitFrame(3, length, objArr, objArr.length, EMPTY);
                        return;
                    }
                    injectFullFrame(methodVisitor, Initialization.INITIALIZED, CompoundList.of(this.initialTypes, this.preMethodTypes, this.postMethodTypes), Collections.emptyList());
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                public void injectInitializationFrame(MethodVisitor methodVisitor) {
                    if (!this.initialTypes.isEmpty()) {
                        if (!this.expandFrames && this.initialTypes.size() < 4) {
                            Object[] objArr = new Object[this.initialTypes.size()];
                            int i = 0;
                            Iterator<? extends TypeDescription> it = this.initialTypes.iterator();
                            while (it.hasNext()) {
                                int i2 = i;
                                i++;
                                objArr[i2] = Initialization.INITIALIZED.toFrame(it.next());
                            }
                            methodVisitor.visitFrame(1, objArr.length, objArr, EMPTY.length, EMPTY);
                            return;
                        }
                        Object[] objArr2 = new Object[(this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size() + this.initialTypes.size()];
                        int i3 = 0;
                        if (this.instrumentedMethod.isConstructor()) {
                            i3 = 0 + 1;
                            objArr2[0] = Opcodes.UNINITIALIZED_THIS;
                        } else if (!this.instrumentedMethod.isStatic()) {
                            i3 = 0 + 1;
                            objArr2[0] = Initialization.INITIALIZED.toFrame(this.instrumentedType);
                        }
                        Iterator it2 = this.instrumentedMethod.getParameters().asTypeList().asErasures().iterator();
                        while (it2.hasNext()) {
                            int i4 = i3;
                            i3++;
                            objArr2[i4] = Initialization.INITIALIZED.toFrame((TypeDescription) it2.next());
                        }
                        Iterator<? extends TypeDescription> it3 = this.initialTypes.iterator();
                        while (it3.hasNext()) {
                            int i5 = i3;
                            i3++;
                            objArr2[i5] = Initialization.INITIALIZED.toFrame(it3.next());
                        }
                        methodVisitor.visitFrame(this.expandFrames ? -1 : 0, objArr2.length, objArr2, EMPTY.length, EMPTY);
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default$WithPreservedArguments$WithoutArgumentCopy.class */
                public static class WithoutArgumentCopy extends WithPreservedArguments {
                    protected WithoutArgumentCopy(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, List<? extends TypeDescription> list4, boolean z, boolean z2) {
                        super(typeDescription, methodDescription, list, list2, list3, list4, z, z2);
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                    public void injectStartFrame(MethodVisitor methodVisitor) {
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                    public void translateFrame(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                        translateFrame(methodVisitor, TranslationMode.COPY, this.instrumentedMethod, CompoundList.of((List) this.initialTypes, (List) this.preMethodTypes), i, i2, objArr, i3, objArr2);
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default$WithPreservedArguments$WithArgumentCopy.class */
                public static class WithArgumentCopy extends WithPreservedArguments {
                    protected WithArgumentCopy(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, List<? extends TypeDescription> list4, boolean z) {
                        super(typeDescription, methodDescription, list, list2, list3, list4, z, true);
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
                    public void injectStartFrame(MethodVisitor methodVisitor) {
                        if (!this.instrumentedMethod.isStatic() || !this.instrumentedMethod.getParameters().isEmpty()) {
                            if (!this.expandFrames) {
                                if ((this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size() < 4) {
                                    Object[] objArr = new Object[(this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size()];
                                    int i = 0;
                                    if (this.instrumentedMethod.isConstructor()) {
                                        i = 0 + 1;
                                        objArr[0] = Opcodes.UNINITIALIZED_THIS;
                                    } else if (!this.instrumentedMethod.isStatic()) {
                                        i = 0 + 1;
                                        objArr[0] = Initialization.INITIALIZED.toFrame(this.instrumentedType);
                                    }
                                    Iterator it = this.instrumentedMethod.getParameters().asTypeList().asErasures().iterator();
                                    while (it.hasNext()) {
                                        int i2 = i;
                                        i++;
                                        objArr[i2] = Initialization.INITIALIZED.toFrame((TypeDescription) it.next());
                                    }
                                    methodVisitor.visitFrame(1, objArr.length, objArr, EMPTY.length, EMPTY);
                                }
                            }
                            Object[] objArr2 = new Object[(this.instrumentedMethod.isStatic() ? 0 : 2) + (this.instrumentedMethod.getParameters().size() << 1) + this.initialTypes.size() + this.preMethodTypes.size()];
                            int i3 = 0;
                            if (this.instrumentedMethod.isConstructor()) {
                                i3 = 0 + 1;
                                objArr2[0] = Opcodes.UNINITIALIZED_THIS;
                            } else if (!this.instrumentedMethod.isStatic()) {
                                i3 = 0 + 1;
                                objArr2[0] = Initialization.INITIALIZED.toFrame(this.instrumentedType);
                            }
                            Iterator it2 = this.instrumentedMethod.getParameters().asTypeList().asErasures().iterator();
                            while (it2.hasNext()) {
                                int i4 = i3;
                                i3++;
                                objArr2[i4] = Initialization.INITIALIZED.toFrame((TypeDescription) it2.next());
                            }
                            Iterator<? extends TypeDescription> it3 = this.initialTypes.iterator();
                            while (it3.hasNext()) {
                                int i5 = i3;
                                i3++;
                                objArr2[i5] = Initialization.INITIALIZED.toFrame(it3.next());
                            }
                            Iterator<? extends TypeDescription> it4 = this.preMethodTypes.iterator();
                            while (it4.hasNext()) {
                                int i6 = i3;
                                i3++;
                                objArr2[i6] = Initialization.INITIALIZED.toFrame(it4.next());
                            }
                            if (this.instrumentedMethod.isConstructor()) {
                                int i7 = i3;
                                i3++;
                                objArr2[i7] = Opcodes.UNINITIALIZED_THIS;
                            } else if (!this.instrumentedMethod.isStatic()) {
                                int i8 = i3;
                                i3++;
                                objArr2[i8] = Initialization.INITIALIZED.toFrame(this.instrumentedType);
                            }
                            Iterator it5 = this.instrumentedMethod.getParameters().asTypeList().asErasures().iterator();
                            while (it5.hasNext()) {
                                int i9 = i3;
                                i3++;
                                objArr2[i9] = Initialization.INITIALIZED.toFrame((TypeDescription) it5.next());
                            }
                            methodVisitor.visitFrame(this.expandFrames ? -1 : 0, objArr2.length, objArr2, EMPTY.length, EMPTY);
                        }
                        this.currentFrameDivergence = (this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size();
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                    @SuppressFBWarnings(value = {"RC_REF_COMPARISON_BAD_PRACTICE"}, justification = "ASM models frames by reference identity.")
                    public void translateFrame(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                        switch (i) {
                            case -1:
                            case 0:
                                Object[] objArr3 = new Object[i2 + (this.instrumentedMethod.isStatic() ? 0 : 1) + this.instrumentedMethod.getParameters().size() + this.initialTypes.size() + this.preMethodTypes.size()];
                                int i4 = 0;
                                if (this.instrumentedMethod.isConstructor()) {
                                    Initialization initialization = Initialization.INITIALIZED;
                                    int i5 = 0;
                                    while (true) {
                                        if (i5 < i2) {
                                            if (objArr[i5] != Opcodes.UNINITIALIZED_THIS) {
                                                i5++;
                                            } else {
                                                initialization = Initialization.UNITIALIZED;
                                            }
                                        }
                                    }
                                    i4 = 0 + 1;
                                    objArr3[0] = initialization.toFrame(this.instrumentedType);
                                } else if (!this.instrumentedMethod.isStatic()) {
                                    i4 = 0 + 1;
                                    objArr3[0] = Initialization.INITIALIZED.toFrame(this.instrumentedType);
                                }
                                Iterator it = this.instrumentedMethod.getParameters().asTypeList().asErasures().iterator();
                                while (it.hasNext()) {
                                    int i6 = i4;
                                    i4++;
                                    objArr3[i6] = Initialization.INITIALIZED.toFrame((TypeDescription) it.next());
                                }
                                Iterator<? extends TypeDescription> it2 = this.initialTypes.iterator();
                                while (it2.hasNext()) {
                                    int i7 = i4;
                                    i4++;
                                    objArr3[i7] = Initialization.INITIALIZED.toFrame(it2.next());
                                }
                                Iterator<? extends TypeDescription> it3 = this.preMethodTypes.iterator();
                                while (it3.hasNext()) {
                                    int i8 = i4;
                                    i4++;
                                    objArr3[i8] = Initialization.INITIALIZED.toFrame(it3.next());
                                }
                                if (i2 > 0) {
                                    System.arraycopy(objArr, 0, objArr3, i4, i2);
                                }
                                i2 = objArr3.length;
                                objArr = objArr3;
                                this.currentFrameDivergence = i2;
                                break;
                            case 1:
                                this.currentFrameDivergence += i2;
                                break;
                            case 2:
                                this.currentFrameDivergence -= i2;
                                break;
                            case 3:
                            case 4:
                                break;
                            default:
                                throw new IllegalArgumentException("Unexpected frame type: " + i);
                        }
                        methodVisitor.visitFrame(i, i2, objArr, i3, objArr2);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$StackMapFrameHandler$Default$ForAdvice.class */
            protected class ForAdvice implements ForAdvice {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final List<? extends TypeDescription> startTypes;
                private final List<? extends TypeDescription> intermediateTypes;
                protected final List<? extends TypeDescription> endTypes;
                protected final TranslationMode translationMode;
                private final Initialization initialization;
                private boolean intermedate = false;

                protected ForAdvice(MethodDescription.InDefinedShape inDefinedShape, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, TranslationMode translationMode, Initialization initialization) {
                    this.adviceMethod = inDefinedShape;
                    this.startTypes = list;
                    this.intermediateTypes = list2;
                    this.endTypes = list3;
                    this.translationMode = translationMode;
                    this.initialization = initialization;
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void translateFrame(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                    Default.this.translateFrame(methodVisitor, this.translationMode, this.adviceMethod, this.startTypes, i, i2, objArr, i3, objArr2);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectReturnFrame(MethodVisitor methodVisitor) {
                    List<? extends TypeDescription> singletonList;
                    if (!Default.this.expandFrames && Default.this.currentFrameDivergence == 0) {
                        if (this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            int length = Default.EMPTY.length;
                            Object[] objArr = Default.EMPTY;
                            methodVisitor.visitFrame(3, length, objArr, objArr.length, Default.EMPTY);
                            return;
                        }
                        methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Initialization.INITIALIZED.toFrame(this.adviceMethod.getReturnType().asErasure())});
                        return;
                    }
                    Default r0 = Default.this;
                    Initialization initialization = this.initialization;
                    List<? extends TypeDescription> list = this.startTypes;
                    if (this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                        singletonList = Collections.emptyList();
                    } else {
                        singletonList = Collections.singletonList(this.adviceMethod.getReturnType().asErasure());
                    }
                    r0.injectFullFrame(methodVisitor, initialization, list, singletonList);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectExceptionFrame(MethodVisitor methodVisitor) {
                    if (!Default.this.expandFrames && Default.this.currentFrameDivergence == 0) {
                        methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Type.getInternalName(Throwable.class)});
                    } else {
                        Default.this.injectFullFrame(methodVisitor, this.initialization, this.startTypes, Collections.singletonList(TypeDescription.ForLoadedType.of(Throwable.class)));
                    }
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectCompletionFrame(MethodVisitor methodVisitor) {
                    if (!Default.this.expandFrames) {
                        if (Default.this.currentFrameDivergence == 0 && (this.intermedate || this.endTypes.size() < 4)) {
                            if (this.intermedate || this.endTypes.isEmpty()) {
                                int length = Default.EMPTY.length;
                                Object[] objArr = Default.EMPTY;
                                methodVisitor.visitFrame(3, length, objArr, objArr.length, Default.EMPTY);
                                return;
                            }
                            Object[] objArr2 = new Object[this.endTypes.size()];
                            int i = 0;
                            Iterator<? extends TypeDescription> it = this.endTypes.iterator();
                            while (it.hasNext()) {
                                int i2 = i;
                                i++;
                                objArr2[i2] = Initialization.INITIALIZED.toFrame(it.next());
                            }
                            methodVisitor.visitFrame(1, objArr2.length, objArr2, Default.EMPTY.length, Default.EMPTY);
                            return;
                        }
                        if (Default.this.currentFrameDivergence < 3 && this.endTypes.isEmpty()) {
                            int i3 = Default.this.currentFrameDivergence;
                            Object[] objArr3 = Default.EMPTY;
                            methodVisitor.visitFrame(2, i3, objArr3, objArr3.length, Default.EMPTY);
                            Default.this.currentFrameDivergence = 0;
                            return;
                        }
                    }
                    Default.this.injectFullFrame(methodVisitor, this.initialization, CompoundList.of((List) this.startTypes, (List) this.endTypes), Collections.emptyList());
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForPostProcessor
                public void injectIntermediateFrame(MethodVisitor methodVisitor, List<? extends TypeDescription> list) {
                    if (!Default.this.expandFrames) {
                        if (this.intermedate && list.size() < 2) {
                            if (list.isEmpty()) {
                                int length = Default.EMPTY.length;
                                Object[] objArr = Default.EMPTY;
                                methodVisitor.visitFrame(3, length, objArr, objArr.length, Default.EMPTY);
                            } else {
                                methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Initialization.INITIALIZED.toFrame(list.get(0))});
                            }
                        } else if (Default.this.currentFrameDivergence == 0 && this.intermediateTypes.size() < 4 && (list.isEmpty() || (list.size() < 2 && this.intermediateTypes.isEmpty()))) {
                            if (this.intermediateTypes.isEmpty()) {
                                if (list.isEmpty()) {
                                    int length2 = Default.EMPTY.length;
                                    Object[] objArr2 = Default.EMPTY;
                                    methodVisitor.visitFrame(3, length2, objArr2, objArr2.length, Default.EMPTY);
                                } else {
                                    methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Initialization.INITIALIZED.toFrame(list.get(0))});
                                }
                            } else {
                                Object[] objArr3 = new Object[this.intermediateTypes.size()];
                                int i = 0;
                                Iterator<? extends TypeDescription> it = this.intermediateTypes.iterator();
                                while (it.hasNext()) {
                                    int i2 = i;
                                    i++;
                                    objArr3[i2] = Initialization.INITIALIZED.toFrame(it.next());
                                }
                                methodVisitor.visitFrame(1, objArr3.length, objArr3, Default.EMPTY.length, Default.EMPTY);
                            }
                        } else if (Default.this.currentFrameDivergence < 3 && this.intermediateTypes.isEmpty() && list.isEmpty()) {
                            int i3 = Default.this.currentFrameDivergence;
                            Object[] objArr4 = Default.EMPTY;
                            methodVisitor.visitFrame(2, i3, objArr4, objArr4.length, Default.EMPTY);
                        }
                        Default.this.currentFrameDivergence = this.intermediateTypes.size() - this.endTypes.size();
                        this.intermedate = true;
                    }
                    Default.this.injectFullFrame(methodVisitor, this.initialization, CompoundList.of((List) this.startTypes, (List) this.intermediateTypes), list);
                    Default.this.currentFrameDivergence = this.intermediateTypes.size() - this.endTypes.size();
                    this.intermedate = true;
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ExceptionHandler.class */
    public interface ExceptionHandler {
        StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription);

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ExceptionHandler$Default.class */
        public enum Default implements ExceptionHandler {
            SUPPRESSING { // from class: net.bytebuddy.asm.Advice.ExceptionHandler.Default.1
                @Override // net.bytebuddy.asm.Advice.ExceptionHandler
                public final StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription) {
                    return Removal.SINGLE;
                }
            },
            PRINTING { // from class: net.bytebuddy.asm.Advice.ExceptionHandler.Default.2
                @Override // net.bytebuddy.asm.Advice.ExceptionHandler
                public final StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription) {
                    try {
                        return MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Throwable.class.getMethod("printStackTrace", new Class[0])));
                    } catch (NoSuchMethodException unused) {
                        throw new IllegalStateException("Cannot locate Throwable::printStackTrace");
                    }
                }
            },
            RETHROWING { // from class: net.bytebuddy.asm.Advice.ExceptionHandler.Default.3
                @Override // net.bytebuddy.asm.Advice.ExceptionHandler
                public final StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription) {
                    return Throw.INSTANCE;
                }
            };

            /* synthetic */ Default(byte b2) {
                this();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$ExceptionHandler$Simple.class */
        public static class Simple implements ExceptionHandler {
            private final StackManipulation stackManipulation;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.stackManipulation.equals(((Simple) obj).stackManipulation);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.stackManipulation.hashCode();
            }

            public Simple(StackManipulation stackManipulation) {
                this.stackManipulation = stackManipulation;
            }

            @Override // net.bytebuddy.asm.Advice.ExceptionHandler
            public StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription) {
                return this.stackManipulation;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher.class */
    public interface Dispatcher {

        @AlwaysNull
        public static final MethodVisitor IGNORE_METHOD = null;

        @AlwaysNull
        public static final AnnotationVisitor IGNORE_ANNOTATION = null;

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Bound.class */
        public interface Bound {
            void prepare();

            void initialize();

            void apply();
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Unresolved.class */
        public interface Unresolved extends Dispatcher {
            boolean isBinary();

            Map<String, TypeDefinition> getNamedTypes();

            Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory);

            Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory);
        }

        boolean isAlive();

        TypeDefinition getAdviceType();

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$SuppressionHandler.class */
        public interface SuppressionHandler {

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$SuppressionHandler$Bound.class */
            public interface Bound {
                void onPrepare(MethodVisitor methodVisitor);

                void onStart(MethodVisitor methodVisitor);

                void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition);

                void onEndWithSkip(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition);
            }

            Bound bind(StackManipulation stackManipulation);

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$SuppressionHandler$NoOp.class */
            public enum NoOp implements SuppressionHandler, Bound {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler
                public final Bound bind(StackManipulation stackManipulation) {
                    return this;
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public final void onPrepare(MethodVisitor methodVisitor) {
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public final void onStart(MethodVisitor methodVisitor) {
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public final void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public final void onEndWithSkip(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$SuppressionHandler$Suppressing.class */
            public static class Suppressing implements SuppressionHandler {
                private final TypeDescription suppressedType;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.suppressedType.equals(((Suppressing) obj).suppressedType);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.suppressedType.hashCode();
                }

                protected Suppressing(TypeDescription typeDescription) {
                    this.suppressedType = typeDescription;
                }

                protected static SuppressionHandler of(TypeDescription typeDescription) {
                    return typeDescription.represents(NoExceptionHandler.class) ? NoOp.INSTANCE : new Suppressing(typeDescription);
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler
                public Bound bind(StackManipulation stackManipulation) {
                    return new Bound(this.suppressedType, stackManipulation);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$SuppressionHandler$Suppressing$Bound.class */
                protected static class Bound implements Bound {
                    private final TypeDescription suppressedType;
                    private final StackManipulation exceptionHandler;
                    private final Label startOfMethod = new Label();
                    private final Label endOfMethod = new Label();

                    protected Bound(TypeDescription typeDescription, StackManipulation stackManipulation) {
                        this.suppressedType = typeDescription;
                        this.exceptionHandler = stackManipulation;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onPrepare(MethodVisitor methodVisitor) {
                        methodVisitor.visitTryCatchBlock(this.startOfMethod, this.endOfMethod, this.endOfMethod, this.suppressedType.getInternalName());
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onStart(MethodVisitor methodVisitor) {
                        methodVisitor.visitLabel(this.startOfMethod);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                        methodVisitor.visitLabel(this.endOfMethod);
                        forAdvice2.injectExceptionFrame(methodVisitor);
                        forAdvice.requireStackSize(1 + this.exceptionHandler.apply(methodVisitor, context).getMaximalSize());
                        if (typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Byte.TYPE) || typeDefinition.represents(Short.TYPE) || typeDefinition.represents(Character.TYPE) || typeDefinition.represents(Integer.TYPE)) {
                            methodVisitor.visitInsn(3);
                            return;
                        }
                        if (typeDefinition.represents(Long.TYPE)) {
                            methodVisitor.visitInsn(9);
                            return;
                        }
                        if (typeDefinition.represents(Float.TYPE)) {
                            methodVisitor.visitInsn(11);
                        } else if (typeDefinition.represents(Double.TYPE)) {
                            methodVisitor.visitInsn(14);
                        } else if (!typeDefinition.represents(Void.TYPE)) {
                            methodVisitor.visitInsn(1);
                        }
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onEndWithSkip(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                        Label label = new Label();
                        methodVisitor.visitJumpInsn(167, label);
                        onEnd(methodVisitor, context, forAdvice, forAdvice2, typeDefinition);
                        methodVisitor.visitLabel(label);
                        forAdvice2.injectReturnFrame(methodVisitor);
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler.class */
        public interface RelocationHandler {

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$Bound.class */
            public interface Bound {
                public static final int NO_REQUIRED_SIZE = 0;

                int apply(MethodVisitor methodVisitor, int i);
            }

            Bound bind(MethodDescription methodDescription, Relocation relocation);

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$Relocation.class */
            public interface Relocation {
                void apply(MethodVisitor methodVisitor);

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$Relocation$ForLabel.class */
                public static class ForLabel implements Relocation {
                    private final Label label;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.label.equals(((ForLabel) obj).label);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.label.hashCode();
                    }

                    public ForLabel(Label label) {
                        this.label = label;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.Relocation
                    public void apply(MethodVisitor methodVisitor) {
                        methodVisitor.visitJumpInsn(167, this.label);
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$Disabled.class */
            public enum Disabled implements RelocationHandler, Bound {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler
                public final Bound bind(MethodDescription methodDescription, Relocation relocation) {
                    return this;
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.Bound
                public final int apply(MethodVisitor methodVisitor, int i) {
                    return 0;
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$ForValue.class */
            public enum ForValue implements RelocationHandler {
                INTEGER(21, 154, 153, 0) { // from class: net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue.1
                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue
                    protected final void convertValue(MethodVisitor methodVisitor) {
                    }
                },
                LONG(22, 154, 153, 0) { // from class: net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue.2
                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue
                    protected final void convertValue(MethodVisitor methodVisitor) {
                        methodVisitor.visitInsn(136);
                    }
                },
                FLOAT(23, 154, 153, 2) { // from class: net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue.3
                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue
                    protected final void convertValue(MethodVisitor methodVisitor) {
                        methodVisitor.visitInsn(11);
                        methodVisitor.visitInsn(149);
                    }
                },
                DOUBLE(24, 154, 153, 4) { // from class: net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue.4
                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue
                    protected final void convertValue(MethodVisitor methodVisitor) {
                        methodVisitor.visitInsn(14);
                        methodVisitor.visitInsn(151);
                    }
                },
                REFERENCE(25, 199, 198, 0) { // from class: net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue.5
                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.ForValue
                    protected final void convertValue(MethodVisitor methodVisitor) {
                    }
                };

                private final int load;
                private final int defaultJump;
                private final int nonDefaultJump;
                private final int requiredSize;

                protected abstract void convertValue(MethodVisitor methodVisitor);

                /* synthetic */ ForValue(int i, int i2, int i3, int i4, byte b2) {
                    this(i, i2, i3, i4);
                }

                ForValue(int i, int i2, int i3, int i4) {
                    this.load = i;
                    this.defaultJump = i2;
                    this.nonDefaultJump = i3;
                    this.requiredSize = i4;
                }

                protected static RelocationHandler of(TypeDefinition typeDefinition, boolean z) {
                    ForValue forValue;
                    if (typeDefinition.represents(Long.TYPE)) {
                        forValue = LONG;
                    } else if (typeDefinition.represents(Float.TYPE)) {
                        forValue = FLOAT;
                    } else if (typeDefinition.represents(Double.TYPE)) {
                        forValue = DOUBLE;
                    } else {
                        if (typeDefinition.represents(Void.TYPE)) {
                            throw new IllegalStateException("Cannot skip on default value for void return type");
                        }
                        if (typeDefinition.isPrimitive()) {
                            forValue = INTEGER;
                        } else {
                            forValue = REFERENCE;
                        }
                    }
                    if (!z) {
                        return forValue;
                    }
                    ForValue forValue2 = forValue;
                    forValue2.getClass();
                    return new Inverted();
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler
                public Bound bind(MethodDescription methodDescription, Relocation relocation) {
                    return new Bound(methodDescription, relocation, false);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$ForValue$Inverted.class */
                public class Inverted implements RelocationHandler {
                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && ForValue.this.equals(ForValue.this);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + ForValue.this.hashCode();
                    }

                    protected Inverted() {
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler
                    public Bound bind(MethodDescription methodDescription, Relocation relocation) {
                        return new Bound(methodDescription, relocation, true);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$ForValue$Bound.class */
                protected class Bound implements Bound {
                    private final MethodDescription instrumentedMethod;
                    private final Relocation relocation;
                    private final boolean inverted;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.inverted == ((Bound) obj).inverted && ForValue.this.equals(ForValue.this) && this.instrumentedMethod.equals(((Bound) obj).instrumentedMethod) && this.relocation.equals(((Bound) obj).relocation);
                    }

                    public int hashCode() {
                        return (((((((getClass().hashCode() * 31) + this.instrumentedMethod.hashCode()) * 31) + this.relocation.hashCode()) * 31) + (this.inverted ? 1 : 0)) * 31) + ForValue.this.hashCode();
                    }

                    protected Bound(MethodDescription methodDescription, Relocation relocation, boolean z) {
                        this.instrumentedMethod = methodDescription;
                        this.relocation = relocation;
                        this.inverted = z;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.Bound
                    public int apply(MethodVisitor methodVisitor, int i) {
                        if (this.instrumentedMethod.isConstructor()) {
                            throw new IllegalStateException("Cannot skip code execution from constructor: " + this.instrumentedMethod);
                        }
                        methodVisitor.visitVarInsn(ForValue.this.load, i);
                        ForValue.this.convertValue(methodVisitor);
                        Label label = new Label();
                        methodVisitor.visitJumpInsn(this.inverted ? ForValue.this.nonDefaultJump : ForValue.this.defaultJump, label);
                        this.relocation.apply(methodVisitor);
                        methodVisitor.visitLabel(label);
                        return ForValue.this.requiredSize;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$ForType.class */
            public static class ForType implements RelocationHandler {
                private final TypeDescription typeDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForType) obj).typeDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.typeDescription.hashCode();
                }

                protected ForType(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                protected static RelocationHandler of(TypeDescription typeDescription, TypeDefinition typeDefinition) {
                    if (typeDescription.represents(Void.TYPE)) {
                        return Disabled.INSTANCE;
                    }
                    if (typeDescription.represents(OnDefaultValue.class)) {
                        return ForValue.of(typeDefinition, false);
                    }
                    if (typeDescription.represents(OnNonDefaultValue.class)) {
                        return ForValue.of(typeDefinition, true);
                    }
                    if (typeDescription.isPrimitive() || typeDefinition.isPrimitive()) {
                        throw new IllegalStateException("Cannot skip method by instance type for primitive return type " + typeDefinition);
                    }
                    return new ForType(typeDescription);
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler
                public Bound bind(MethodDescription methodDescription, Relocation relocation) {
                    return new Bound(methodDescription, relocation);
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$RelocationHandler$ForType$Bound.class */
                protected class Bound implements Bound {
                    private final MethodDescription instrumentedMethod;
                    private final Relocation relocation;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.instrumentedMethod.equals(((Bound) obj).instrumentedMethod) && this.relocation.equals(((Bound) obj).relocation) && ForType.this.equals(ForType.this);
                    }

                    public int hashCode() {
                        return (((((getClass().hashCode() * 31) + this.instrumentedMethod.hashCode()) * 31) + this.relocation.hashCode()) * 31) + ForType.this.hashCode();
                    }

                    protected Bound(MethodDescription methodDescription, Relocation relocation) {
                        this.instrumentedMethod = methodDescription;
                        this.relocation = relocation;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.Bound
                    public int apply(MethodVisitor methodVisitor, int i) {
                        if (this.instrumentedMethod.isConstructor()) {
                            throw new IllegalStateException("Cannot skip code execution from constructor: " + this.instrumentedMethod);
                        }
                        methodVisitor.visitVarInsn(25, i);
                        methodVisitor.visitTypeInsn(193, ForType.this.typeDescription.getInternalName());
                        Label label = new Label();
                        methodVisitor.visitJumpInsn(153, label);
                        this.relocation.apply(methodVisitor);
                        methodVisitor.visitLabel(label);
                        return 0;
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Resolved.class */
        public interface Resolved extends Dispatcher {

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Resolved$ForMethodEnter.class */
            public interface ForMethodEnter extends Resolved {
                boolean isPrependLineNumber();

                TypeDefinition getActualAdviceType();
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Resolved$ForMethodExit.class */
            public interface ForMethodExit extends Resolved {
                TypeDescription getThrowable();

                ArgumentHandler.Factory getArgumentHandlerFactory();
            }

            Map<String, TypeDefinition> getNamedTypes();

            Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation);

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Resolved$AbstractBase.class */
            public static abstract class AbstractBase implements Resolved {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final PostProcessor postProcessor;
                protected final Map<Integer, OffsetMapping> offsetMappings;
                protected final SuppressionHandler suppressionHandler;
                protected final RelocationHandler relocationHandler;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.adviceMethod.equals(((AbstractBase) obj).adviceMethod) && this.postProcessor.equals(((AbstractBase) obj).postProcessor) && this.offsetMappings.equals(((AbstractBase) obj).offsetMappings) && this.suppressionHandler.equals(((AbstractBase) obj).suppressionHandler) && this.relocationHandler.equals(((AbstractBase) obj).relocationHandler);
                }

                public int hashCode() {
                    return (((((((((getClass().hashCode() * 31) + this.adviceMethod.hashCode()) * 31) + this.postProcessor.hashCode()) * 31) + this.offsetMappings.hashCode()) * 31) + this.suppressionHandler.hashCode()) * 31) + this.relocationHandler.hashCode();
                }

                protected AbstractBase(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, List<? extends OffsetMapping.Factory<?>> list, TypeDescription typeDescription, TypeDescription typeDescription2, OffsetMapping.Factory.AdviceType adviceType) {
                    this.adviceMethod = inDefinedShape;
                    this.postProcessor = postProcessor;
                    HashMap hashMap = new HashMap();
                    for (OffsetMapping.Factory<?> factory : list) {
                        hashMap.put(TypeDescription.ForLoadedType.of(factory.getAnnotationType()), factory);
                    }
                    this.offsetMappings = new LinkedHashMap();
                    for (ParameterDescription.InDefinedShape inDefinedShape2 : inDefinedShape.getParameters()) {
                        OffsetMapping offsetMapping = null;
                        for (AnnotationDescription annotationDescription : inDefinedShape2.getDeclaredAnnotations()) {
                            OffsetMapping.Factory factory2 = (OffsetMapping.Factory) hashMap.get(annotationDescription.getAnnotationType());
                            if (factory2 != null) {
                                OffsetMapping make = factory2.make(inDefinedShape2, annotationDescription.prepare(factory2.getAnnotationType()), adviceType);
                                if (offsetMapping == null) {
                                    offsetMapping = make;
                                } else {
                                    throw new IllegalStateException(inDefinedShape2 + " is bound to both " + make + " and " + offsetMapping);
                                }
                            }
                        }
                        this.offsetMappings.put(Integer.valueOf(inDefinedShape2.getOffset()), offsetMapping == null ? new OffsetMapping.ForArgument.Unresolved(inDefinedShape2) : offsetMapping);
                    }
                    this.suppressionHandler = SuppressionHandler.Suppressing.of(typeDescription);
                    this.relocationHandler = RelocationHandler.ForType.of(typeDescription2, inDefinedShape.getReturnType());
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher
                public boolean isAlive() {
                    return true;
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inactive.class */
        public enum Inactive implements Bound, Resolved.ForMethodEnter, Resolved.ForMethodExit, Unresolved {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public final boolean isAlive() {
                return false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public final boolean isBinary() {
                return false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public final TypeDescription getAdviceType() {
                return TypeDescription.ForLoadedType.of(Void.TYPE);
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
            public final boolean isPrependLineNumber() {
                return false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
            public final TypeDefinition getActualAdviceType() {
                return TypeDescription.ForLoadedType.of(Void.TYPE);
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
            public final Map<String, TypeDefinition> getNamedTypes() {
                return Collections.emptyMap();
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
            public final TypeDescription getThrowable() {
                return NoExceptionHandler.DESCRIPTION;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
            public final ArgumentHandler.Factory getArgumentHandlerFactory() {
                return ArgumentHandler.Factory.SIMPLE;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public final Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public final Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
            public final void prepare() {
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
            public final void initialize() {
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
            public final void apply() {
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
            public final Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                return this;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining.class */
        public static class Inlining implements Unresolved {
            protected final MethodDescription.InDefinedShape adviceMethod;
            private final Map<String, TypeDefinition> namedTypes = new HashMap();

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.adviceMethod.equals(((Inlining) obj).adviceMethod) && this.namedTypes.equals(((Inlining) obj).namedTypes);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.adviceMethod.hashCode()) * 31) + this.namedTypes.hashCode();
            }

            protected Inlining(MethodDescription.InDefinedShape inDefinedShape) {
                String str;
                TypeDefinition put;
                this.adviceMethod = inDefinedShape;
                for (ParameterDescription parameterDescription : inDefinedShape.getParameters()) {
                    AnnotationDescription.Loadable ofType = parameterDescription.getDeclaredAnnotations().ofType(Local.class);
                    if (ofType != null && (put = this.namedTypes.put((str = (String) ofType.getValue(OffsetMapping.ForLocalValue.Factory.LOCAL_VALUE).resolve(String.class)), parameterDescription.getType())) != null && !put.equals(parameterDescription.getType())) {
                        throw new IllegalStateException("Local variable for " + str + " is defined with inconsistent types");
                    }
                }
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public boolean isAlive() {
                return true;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public boolean isBinary() {
                return true;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public TypeDescription getAdviceType() {
                return this.adviceMethod.getReturnType().asErasure();
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Map<String, TypeDefinition> getNamedTypes() {
                return this.namedTypes;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory) {
                if (classReader == null) {
                    throw new IllegalStateException("Class reader not expected null");
                }
                return Resolved.ForMethodEnter.of(this.adviceMethod, factory.make(this.adviceMethod, false), this.namedTypes, list, unresolved.getAdviceType(), classReader, unresolved.isAlive());
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory) {
                HashMap hashMap = new HashMap(unresolved.getNamedTypes());
                HashMap hashMap2 = new HashMap();
                for (Map.Entry<String, TypeDefinition> entry : this.namedTypes.entrySet()) {
                    TypeDefinition typeDefinition = (TypeDefinition) hashMap.get(entry.getKey());
                    TypeDefinition typeDefinition2 = (TypeDefinition) hashMap2.get(entry.getKey());
                    if (typeDefinition == null && typeDefinition2 == null) {
                        hashMap.put(entry.getKey(), entry.getValue());
                        hashMap2.put(entry.getKey(), entry.getValue());
                    } else {
                        if (!(typeDefinition == null ? typeDefinition2 : typeDefinition).equals(entry.getValue())) {
                            throw new IllegalStateException("Local variable for " + entry.getKey() + " is defined with inconsistent types");
                        }
                    }
                }
                return Resolved.ForMethodExit.of(this.adviceMethod, factory.make(this.adviceMethod, true), hashMap, hashMap2, list, classReader, unresolved.getAdviceType());
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved.class */
            protected static abstract class Resolved extends Resolved.AbstractBase {
                protected final ClassReader classReader;

                protected abstract Map<Integer, TypeDefinition> resolveInitializationTypes(ArgumentHandler argumentHandler);

                protected abstract MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation);

                protected Resolved(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, List<? extends OffsetMapping.Factory<?>> list, TypeDescription typeDescription, TypeDescription typeDescription2, ClassReader classReader) {
                    super(inDefinedShape, postProcessor, list, typeDescription, typeDescription2, OffsetMapping.Factory.AdviceType.INLINING);
                    this.classReader = classReader;
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$AdviceMethodInliner.class */
                protected class AdviceMethodInliner extends ClassVisitor implements Bound {
                    protected final TypeDescription instrumentedType;
                    protected final MethodDescription instrumentedMethod;
                    protected final MethodVisitor methodVisitor;
                    protected final Implementation.Context implementationContext;
                    protected final Assigner assigner;
                    protected final ArgumentHandler.ForInstrumentedMethod argumentHandler;
                    protected final MethodSizeHandler.ForInstrumentedMethod methodSizeHandler;
                    protected final StackMapFrameHandler.ForInstrumentedMethod stackMapFrameHandler;
                    protected final SuppressionHandler.Bound suppressionHandler;
                    protected final RelocationHandler.Bound relocationHandler;
                    protected final StackManipulation exceptionHandler;
                    protected final ClassReader classReader;
                    protected final List<Label> labels;

                    protected AdviceMethodInliner(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation, ClassReader classReader) {
                        super(OpenedClassReader.ASM_API);
                        this.instrumentedType = typeDescription;
                        this.instrumentedMethod = methodDescription;
                        this.methodVisitor = methodVisitor;
                        this.implementationContext = context;
                        this.assigner = assigner;
                        this.argumentHandler = forInstrumentedMethod;
                        this.methodSizeHandler = forInstrumentedMethod2;
                        this.stackMapFrameHandler = forInstrumentedMethod3;
                        this.suppressionHandler = bound;
                        this.relocationHandler = bound2;
                        this.exceptionHandler = stackManipulation;
                        this.classReader = classReader;
                        this.labels = new ArrayList();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                    public void prepare() {
                        this.classReader.accept(new ExceptionTableExtractor(), 6);
                        this.suppressionHandler.onPrepare(this.methodVisitor);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                    public void initialize() {
                        for (Map.Entry<Integer, TypeDefinition> entry : Resolved.this.resolveInitializationTypes(this.argumentHandler).entrySet()) {
                            if (entry.getValue().represents(Boolean.TYPE) || entry.getValue().represents(Byte.TYPE) || entry.getValue().represents(Short.TYPE) || entry.getValue().represents(Character.TYPE) || entry.getValue().represents(Integer.TYPE)) {
                                this.methodVisitor.visitInsn(3);
                                this.methodVisitor.visitVarInsn(54, entry.getKey().intValue());
                            } else if (entry.getValue().represents(Long.TYPE)) {
                                this.methodVisitor.visitInsn(9);
                                this.methodVisitor.visitVarInsn(55, entry.getKey().intValue());
                            } else if (entry.getValue().represents(Float.TYPE)) {
                                this.methodVisitor.visitInsn(11);
                                this.methodVisitor.visitVarInsn(56, entry.getKey().intValue());
                            } else if (entry.getValue().represents(Double.TYPE)) {
                                this.methodVisitor.visitInsn(14);
                                this.methodVisitor.visitVarInsn(57, entry.getKey().intValue());
                            } else {
                                this.methodVisitor.visitInsn(1);
                                this.methodVisitor.visitVarInsn(58, entry.getKey().intValue());
                            }
                            this.methodSizeHandler.requireStackSize(entry.getValue().getStackSize().getSize());
                        }
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                    public void apply() {
                        this.classReader.accept(this, 2 | this.stackMapFrameHandler.getReaderHint());
                    }

                    @Override // net.bytebuddy.jar.asm.ClassVisitor
                    @MaybeNull
                    public MethodVisitor visitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
                        return (Resolved.this.adviceMethod.getInternalName().equals(str) && Resolved.this.adviceMethod.getDescriptor().equals(str2)) ? new ExceptionTableSubstitutor(Resolved.this.apply(this.methodVisitor, this.implementationContext, this.assigner, this.argumentHandler, this.methodSizeHandler, this.stackMapFrameHandler, this.instrumentedType, this.instrumentedMethod, this.suppressionHandler, this.relocationHandler, this.exceptionHandler)) : Dispatcher.IGNORE_METHOD;
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$AdviceMethodInliner$ExceptionTableExtractor.class */
                    protected class ExceptionTableExtractor extends ClassVisitor {
                        protected ExceptionTableExtractor() {
                            super(OpenedClassReader.ASM_API);
                        }

                        @Override // net.bytebuddy.jar.asm.ClassVisitor
                        @MaybeNull
                        public MethodVisitor visitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
                            return (Resolved.this.adviceMethod.getInternalName().equals(str) && Resolved.this.adviceMethod.getDescriptor().equals(str2)) ? new ExceptionTableCollector(AdviceMethodInliner.this.methodVisitor) : Dispatcher.IGNORE_METHOD;
                        }
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$AdviceMethodInliner$ExceptionTableCollector.class */
                    protected class ExceptionTableCollector extends MethodVisitor {
                        private final MethodVisitor methodVisitor;

                        protected ExceptionTableCollector(MethodVisitor methodVisitor) {
                            super(OpenedClassReader.ASM_API);
                            this.methodVisitor = methodVisitor;
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitTryCatchBlock(Label label, Label label2, Label label3, @MaybeNull String str) {
                            this.methodVisitor.visitTryCatchBlock(label, label2, label3, str);
                            AdviceMethodInliner.this.labels.addAll(Arrays.asList(label, label2, label3));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitTryCatchAnnotation(int i, @MaybeNull TypePath typePath, String str, boolean z) {
                            return this.methodVisitor.visitTryCatchAnnotation(i, typePath, str, z);
                        }
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$AdviceMethodInliner$ExceptionTableSubstitutor.class */
                    protected class ExceptionTableSubstitutor extends MethodVisitor {
                        private final Map<Label, Label> substitutions;
                        private int index;

                        protected ExceptionTableSubstitutor(MethodVisitor methodVisitor) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                            this.substitutions = new IdentityHashMap();
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
                            Map<Label, Label> map = this.substitutions;
                            List<Label> list = AdviceMethodInliner.this.labels;
                            int i = this.index;
                            this.index = i + 1;
                            map.put(label, list.get(i));
                            Map<Label, Label> map2 = this.substitutions;
                            List<Label> list2 = AdviceMethodInliner.this.labels;
                            int i2 = this.index;
                            this.index = i2 + 1;
                            map2.put(label2, list2.get(i2));
                            List<Label> list3 = AdviceMethodInliner.this.labels;
                            int i3 = this.index;
                            this.index = i3 + 1;
                            Label label4 = list3.get(i3);
                            this.substitutions.put(label3, label4);
                            ((CodeTranslationVisitor) this.mv).propagateHandler(label4);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitTryCatchAnnotation(int i, @MaybeNull TypePath typePath, String str, boolean z) {
                            return Dispatcher.IGNORE_ANNOTATION;
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitLabel(Label label) {
                            super.visitLabel(resolve(label));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitJumpInsn(int i, Label label) {
                            super.visitJumpInsn(i, resolve(label));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
                            super.visitTableSwitchInsn(i, i2, label, resolve(labelArr));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
                            super.visitLookupSwitchInsn(resolve(label), iArr, resolve(labelArr));
                        }

                        private Label[] resolve(Label[] labelArr) {
                            Label[] labelArr2 = new Label[labelArr.length];
                            int i = 0;
                            for (Label label : labelArr) {
                                int i2 = i;
                                i++;
                                labelArr2[i2] = resolve(label);
                            }
                            return labelArr2;
                        }

                        private Label resolve(Label label) {
                            Label label2 = this.substitutions.get(label);
                            return label2 == null ? label : label2;
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$ForMethodEnter.class */
                protected static abstract class ForMethodEnter extends Resolved implements Resolved.ForMethodEnter {
                    private final Map<String, TypeDefinition> namedTypes;
                    private final boolean prependLineNumber;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.prependLineNumber == ((ForMethodEnter) obj).prependLineNumber && this.namedTypes.equals(((ForMethodEnter) obj).namedTypes);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.namedTypes.hashCode()) * 31) + (this.prependLineNumber ? 1 : 0);
                    }

                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                    protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, ClassReader classReader) {
                        super(inDefinedShape, postProcessor, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, OffsetMapping.ForThrowable.Factory.INSTANCE, OffsetMapping.ForExitValue.Factory.of(typeDefinition), new OffsetMapping.ForLocalValue.Factory(map), new OffsetMapping.Factory.Illegal(Thrown.class), new OffsetMapping.Factory.Illegal(Enter.class), new OffsetMapping.Factory.Illegal(Return.class)), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SUPPRESS_ENTER).resolve(TypeDescription.class), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SKIP_ON).resolve(TypeDescription.class), classReader);
                        this.namedTypes = map;
                        this.prependLineNumber = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.PREPEND_LINE_NUMBER).resolve(Boolean.class)).booleanValue();
                    }

                    protected static Resolved.ForMethodEnter of(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, ClassReader classReader, boolean z) {
                        return z ? new WithRetainedEnterType(inDefinedShape, postProcessor, map, list, typeDefinition, classReader) : new WithDiscardedEnterType(inDefinedShape, postProcessor, map, list, typeDefinition, classReader);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    protected Map<Integer, TypeDefinition> resolveInitializationTypes(ArgumentHandler argumentHandler) {
                        TreeMap treeMap = new TreeMap();
                        for (Map.Entry<String, TypeDefinition> entry : this.namedTypes.entrySet()) {
                            treeMap.put(Integer.valueOf(argumentHandler.named(entry.getKey())), entry.getValue());
                        }
                        return treeMap;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        return new AdviceMethodInliner(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, forInstrumentedMethod3, this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription, relocation), stackManipulation, this.classReader);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public boolean isPrependLineNumber() {
                        return this.prependLineNumber;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public TypeDefinition getActualAdviceType() {
                        return this.adviceMethod.getReturnType();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public Map<String, TypeDefinition> getNamedTypes() {
                        return this.namedTypes;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    protected MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                        return doApply(methodVisitor, context, assigner, forInstrumentedMethod.bindEnter(this.adviceMethod), forInstrumentedMethod2.bindEnter(this.adviceMethod), forInstrumentedMethod3.bindEnter(this.adviceMethod), typeDescription, methodDescription, bound, bound2, stackManipulation);
                    }

                    protected MethodVisitor doApply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                        HashMap hashMap = new HashMap();
                        for (Map.Entry<Integer, OffsetMapping> entry : this.offsetMappings.entrySet()) {
                            hashMap.put(entry.getKey(), entry.getValue().resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.ENTER));
                        }
                        return new CodeTranslationVisitor(methodVisitor, context, forAdvice, forAdvice2, forAdvice3, typeDescription, methodDescription, assigner, this.adviceMethod, hashMap, bound, bound2, stackManipulation, this.postProcessor, false);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$ForMethodEnter$WithRetainedEnterType.class */
                    public static class WithRetainedEnterType extends ForMethodEnter {
                        protected WithRetainedEnterType(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, ClassReader classReader) {
                            super(inDefinedShape, postProcessor, map, list, typeDefinition, classReader);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher
                        public TypeDefinition getAdviceType() {
                            return this.adviceMethod.getReturnType();
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$ForMethodEnter$WithDiscardedEnterType.class */
                    public static class WithDiscardedEnterType extends ForMethodEnter {
                        protected WithDiscardedEnterType(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, ClassReader classReader) {
                            super(inDefinedShape, postProcessor, map, list, typeDefinition, classReader);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher
                        public TypeDefinition getAdviceType() {
                            return TypeDescription.ForLoadedType.of(Void.TYPE);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodEnter
                        protected MethodVisitor doApply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                            forAdvice2.requireLocalVariableLengthPadding(this.adviceMethod.getReturnType().getStackSize().getSize());
                            return super.doApply(methodVisitor, context, assigner, forAdvice, forAdvice2, forAdvice3, typeDescription, methodDescription, bound, bound2, stackManipulation);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$ForMethodExit.class */
                protected static abstract class ForMethodExit extends Resolved implements Resolved.ForMethodExit {
                    private final Map<String, TypeDefinition> uninitializedNamedTypes;
                    private final boolean backupArguments;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.backupArguments == ((ForMethodExit) obj).backupArguments && this.uninitializedNamedTypes.equals(((ForMethodExit) obj).uninitializedNamedTypes);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.uninitializedNamedTypes.hashCode()) * 31) + (this.backupArguments ? 1 : 0);
                    }

                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                    protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, Map<String, TypeDefinition> map2, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                        super(inDefinedShape, postProcessor, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, OffsetMapping.ForEnterValue.Factory.of(typeDefinition), OffsetMapping.ForExitValue.Factory.of(inDefinedShape.getReturnType()), new OffsetMapping.ForLocalValue.Factory(map), OffsetMapping.ForReturnValue.Factory.INSTANCE, OffsetMapping.ForThrowable.Factory.of(inDefinedShape)), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.SUPPRESS_EXIT).resolve(TypeDescription.class), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.REPEAT_ON).resolve(TypeDescription.class), classReader);
                        this.uninitializedNamedTypes = map2;
                        this.backupArguments = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.BACKUP_ARGUMENTS).resolve(Boolean.class)).booleanValue();
                    }

                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                    protected static Resolved.ForMethodExit of(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, Map<String, TypeDefinition> map2, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                        TypeDescription typeDescription = (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class);
                        return typeDescription.represents(NoExceptionHandler.class) ? new WithoutExceptionHandler(inDefinedShape, postProcessor, map, map2, list, classReader, typeDefinition) : new WithExceptionHandler(inDefinedShape, postProcessor, map, map2, list, classReader, typeDefinition, typeDescription);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public Map<String, TypeDefinition> getNamedTypes() {
                        return this.uninitializedNamedTypes;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    protected Map<Integer, TypeDefinition> resolveInitializationTypes(ArgumentHandler argumentHandler) {
                        TreeMap treeMap = new TreeMap();
                        for (Map.Entry<String, TypeDefinition> entry : this.uninitializedNamedTypes.entrySet()) {
                            treeMap.put(Integer.valueOf(argumentHandler.named(entry.getKey())), entry.getValue());
                        }
                        if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            treeMap.put(Integer.valueOf(argumentHandler.exit()), this.adviceMethod.getReturnType());
                        }
                        return treeMap;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    protected MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                        return doApply(methodVisitor, context, assigner, forInstrumentedMethod.bindExit(this.adviceMethod, getThrowable().represents(NoExceptionHandler.class)), forInstrumentedMethod2.bindExit(this.adviceMethod), forInstrumentedMethod3.bindExit(this.adviceMethod), typeDescription, methodDescription, bound, bound2, stackManipulation);
                    }

                    private MethodVisitor doApply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                        HashMap hashMap = new HashMap();
                        for (Map.Entry<Integer, OffsetMapping> entry : this.offsetMappings.entrySet()) {
                            hashMap.put(entry.getKey(), entry.getValue().resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.EXIT));
                        }
                        return new CodeTranslationVisitor(methodVisitor, context, forAdvice, forAdvice2, forAdvice3, typeDescription, methodDescription, assigner, this.adviceMethod, hashMap, bound, bound2, stackManipulation, this.postProcessor, true);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                    public ArgumentHandler.Factory getArgumentHandlerFactory() {
                        return this.backupArguments ? ArgumentHandler.Factory.COPYING : ArgumentHandler.Factory.SIMPLE;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher
                    public TypeDefinition getAdviceType() {
                        return this.adviceMethod.getReturnType();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        return new AdviceMethodInliner(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, forInstrumentedMethod3, this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription, relocation), stackManipulation, this.classReader);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$ForMethodExit$WithExceptionHandler.class */
                    public static class WithExceptionHandler extends ForMethodExit {
                        private final TypeDescription throwable;

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                        public boolean equals(@MaybeNull Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.throwable.equals(((WithExceptionHandler) obj).throwable);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                        public int hashCode() {
                            return (super.hashCode() * 31) + this.throwable.hashCode();
                        }

                        protected WithExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, Map<String, TypeDefinition> map2, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition, TypeDescription typeDescription) {
                            super(inDefinedShape, postProcessor, map, map2, list, classReader, typeDefinition);
                            this.throwable = typeDescription;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return this.throwable;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$Resolved$ForMethodExit$WithoutExceptionHandler.class */
                    public static class WithoutExceptionHandler extends ForMethodExit {
                        protected WithoutExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, Map<String, TypeDefinition> map2, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                            super(inDefinedShape, postProcessor, map, map2, list, classReader, typeDefinition);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return NoExceptionHandler.DESCRIPTION;
                        }
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Inlining$CodeTranslationVisitor.class */
            public static class CodeTranslationVisitor extends MethodVisitor {
                protected final MethodVisitor methodVisitor;
                protected final Implementation.Context implementationContext;
                protected final ArgumentHandler.ForAdvice argumentHandler;
                protected final MethodSizeHandler.ForAdvice methodSizeHandler;
                protected final StackMapFrameHandler.ForAdvice stackMapFrameHandler;
                private final TypeDescription instrumentedType;
                private final MethodDescription instrumentedMethod;
                private final Assigner assigner;
                protected final MethodDescription.InDefinedShape adviceMethod;
                private final Map<Integer, OffsetMapping.Target> offsetMappings;
                private final SuppressionHandler.Bound suppressionHandler;
                private final RelocationHandler.Bound relocationHandler;
                private final StackManipulation exceptionHandler;
                private final PostProcessor postProcessor;
                private final boolean exit;
                protected final Label endOfMethod;

                protected CodeTranslationVisitor(MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, MethodDescription.InDefinedShape inDefinedShape, Map<Integer, OffsetMapping.Target> map, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation, PostProcessor postProcessor, boolean z) {
                    super(OpenedClassReader.ASM_API, StackAwareMethodVisitor.of(methodVisitor, methodDescription));
                    this.methodVisitor = methodVisitor;
                    this.implementationContext = context;
                    this.argumentHandler = forAdvice;
                    this.methodSizeHandler = forAdvice2;
                    this.stackMapFrameHandler = forAdvice3;
                    this.instrumentedType = typeDescription;
                    this.instrumentedMethod = methodDescription;
                    this.assigner = assigner;
                    this.adviceMethod = inDefinedShape;
                    this.offsetMappings = map;
                    this.suppressionHandler = bound;
                    this.relocationHandler = bound2;
                    this.exceptionHandler = stackManipulation;
                    this.postProcessor = postProcessor;
                    this.exit = z;
                    this.endOfMethod = new Label();
                }

                protected void propagateHandler(Label label) {
                    ((StackAwareMethodVisitor) this.mv).register(label, Collections.singletonList(StackSize.SINGLE));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitParameter(String str, int i) {
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitAnnotableParameterCount(int i, boolean z) {
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                @MaybeNull
                public AnnotationVisitor visitAnnotationDefault() {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                @MaybeNull
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                @MaybeNull
                public AnnotationVisitor visitTypeAnnotation(int i, @MaybeNull TypePath typePath, String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                @MaybeNull
                public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitAttribute(Attribute attribute) {
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitCode() {
                    this.suppressionHandler.onStart(this.methodVisitor);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitFrame(int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                    this.stackMapFrameHandler.translateFrame(this.methodVisitor, i, i2, objArr, i3, objArr2);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitVarInsn(int i, int i2) {
                    StackManipulation resolveWrite;
                    StackSize stackSize;
                    OffsetMapping.Target target = this.offsetMappings.get(Integer.valueOf(i2));
                    if (target != null) {
                        switch (i) {
                            case 21:
                            case 23:
                            case 25:
                                resolveWrite = target.resolveRead();
                                stackSize = StackSize.SINGLE;
                                break;
                            case 22:
                            case 24:
                                resolveWrite = target.resolveRead();
                                stackSize = StackSize.DOUBLE;
                                break;
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            default:
                                throw new IllegalStateException("Unexpected opcode: " + i);
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                                resolveWrite = target.resolveWrite();
                                stackSize = StackSize.ZERO;
                                break;
                        }
                        this.methodSizeHandler.requireStackSizePadding(resolveWrite.apply(this.mv, this.implementationContext).getMaximalSize() - stackSize.getSize());
                        return;
                    }
                    this.mv.visitVarInsn(i, this.argumentHandler.mapped(i2));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitIincInsn(int i, int i2) {
                    OffsetMapping.Target target = this.offsetMappings.get(Integer.valueOf(i));
                    if (target != null) {
                        this.methodSizeHandler.requireStackSizePadding(target.resolveIncrement(i2).apply(this.mv, this.implementationContext).getMaximalSize());
                    } else {
                        this.mv.visitIincInsn(this.argumentHandler.mapped(i), i2);
                    }
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitInsn(int i) {
                    switch (i) {
                        case 172:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(54, 21, StackSize.SINGLE));
                            break;
                        case 173:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(55, 22, StackSize.DOUBLE));
                            break;
                        case 174:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(56, 23, StackSize.SINGLE));
                            break;
                        case 175:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(57, 24, StackSize.DOUBLE));
                            break;
                        case 176:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(58, 25, StackSize.SINGLE));
                            break;
                        case 177:
                            ((StackAwareMethodVisitor) this.mv).drainStack();
                            break;
                        default:
                            this.mv.visitInsn(i);
                            return;
                    }
                    this.mv.visitJumpInsn(167, this.endOfMethod);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitEnd() {
                    this.suppressionHandler.onEnd(this.methodVisitor, this.implementationContext, this.methodSizeHandler, this.stackMapFrameHandler, this.adviceMethod.getReturnType());
                    this.methodVisitor.visitLabel(this.endOfMethod);
                    if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(54, this.exit ? this.argumentHandler.exit() : this.argumentHandler.enter());
                    } else if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(55, this.exit ? this.argumentHandler.exit() : this.argumentHandler.enter());
                    } else if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(56, this.exit ? this.argumentHandler.exit() : this.argumentHandler.enter());
                    } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(57, this.exit ? this.argumentHandler.exit() : this.argumentHandler.enter());
                    } else if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(58, this.exit ? this.argumentHandler.exit() : this.argumentHandler.enter());
                    }
                    this.methodSizeHandler.requireStackSize(this.postProcessor.resolve(this.instrumentedType, this.instrumentedMethod, this.assigner, this.argumentHandler, this.stackMapFrameHandler, this.exceptionHandler).apply(this.methodVisitor, this.implementationContext).getMaximalSize());
                    this.methodSizeHandler.requireStackSize(this.relocationHandler.apply(this.methodVisitor, this.exit ? this.argumentHandler.exit() : this.argumentHandler.enter()));
                    this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitMaxs(int i, int i2) {
                    this.methodSizeHandler.recordMaxima(i, i2);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating.class */
        public static class Delegating implements Unresolved {
            protected final MethodDescription.InDefinedShape adviceMethod;
            protected final Delegator delegator;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.adviceMethod.equals(((Delegating) obj).adviceMethod) && this.delegator.equals(((Delegating) obj).delegator);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.adviceMethod.hashCode()) * 31) + this.delegator.hashCode();
            }

            protected Delegating(MethodDescription.InDefinedShape inDefinedShape, Delegator delegator) {
                this.adviceMethod = inDefinedShape;
                this.delegator = delegator;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public boolean isAlive() {
                return true;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public boolean isBinary() {
                return false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public TypeDescription getAdviceType() {
                return this.adviceMethod.getReturnType().asErasure();
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Map<String, TypeDefinition> getNamedTypes() {
                return Collections.emptyMap();
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory) {
                return Resolved.ForMethodEnter.of(this.adviceMethod, factory.make(this.adviceMethod, false), this.delegator, list, unresolved.getAdviceType(), unresolved.isAlive());
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, @MaybeNull ClassReader classReader, Unresolved unresolved, PostProcessor.Factory factory) {
                Map<String, TypeDefinition> namedTypes = unresolved.getNamedTypes();
                for (ParameterDescription parameterDescription : this.adviceMethod.getParameters()) {
                    AnnotationDescription.Loadable ofType = parameterDescription.getDeclaredAnnotations().ofType(Local.class);
                    if (ofType != null) {
                        String str = (String) ofType.getValue(OffsetMapping.ForLocalValue.Factory.LOCAL_VALUE).resolve(String.class);
                        TypeDefinition typeDefinition = namedTypes.get(str);
                        if (typeDefinition == null) {
                            throw new IllegalStateException(this.adviceMethod + " attempts use of undeclared local variable " + str);
                        }
                        if (!typeDefinition.equals(parameterDescription.getType())) {
                            throw new IllegalStateException(this.adviceMethod + " does not read variable " + str + " as " + typeDefinition);
                        }
                    }
                }
                return Resolved.ForMethodExit.of(this.adviceMethod, factory.make(this.adviceMethod, true), this.delegator, namedTypes, list, unresolved.getAdviceType());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved.class */
            public static abstract class Resolved extends Resolved.AbstractBase {
                protected final Delegator delegator;

                protected abstract Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation);

                protected Resolved(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, List<? extends OffsetMapping.Factory<?>> list, TypeDescription typeDescription, TypeDescription typeDescription2, Delegator delegator) {
                    super(inDefinedShape, postProcessor, list, typeDescription, typeDescription2, OffsetMapping.Factory.AdviceType.DELEGATION);
                    this.delegator = delegator;
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                public Map<String, TypeDefinition> getNamedTypes() {
                    return Collections.emptyMap();
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                public Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                    if (!this.adviceMethod.isVisibleTo(typeDescription)) {
                        throw new IllegalStateException(this.adviceMethod + " is not visible to " + methodDescription.getDeclaringType());
                    }
                    return resolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, forInstrumentedMethod3, stackManipulation, relocation);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$AdviceMethodWriter.class */
                protected static abstract class AdviceMethodWriter implements Bound {
                    protected final MethodDescription.InDefinedShape adviceMethod;
                    private final TypeDescription instrumentedType;
                    private final MethodDescription instrumentedMethod;
                    private final Assigner assigner;
                    private final List<OffsetMapping.Target> offsetMappings;
                    protected final MethodVisitor methodVisitor;
                    protected final Implementation.Context implementationContext;
                    protected final ArgumentHandler.ForAdvice argumentHandler;
                    protected final MethodSizeHandler.ForAdvice methodSizeHandler;
                    protected final StackMapFrameHandler.ForAdvice stackMapFrameHandler;
                    private final SuppressionHandler.Bound suppressionHandler;
                    private final RelocationHandler.Bound relocationHandler;
                    private final StackManipulation exceptionHandler;
                    private final PostProcessor postProcessor;
                    private final Delegator delegator;

                    protected abstract boolean isExitAdvice();

                    protected AdviceMethodWriter(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, PostProcessor postProcessor, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation, Delegator delegator) {
                        this.adviceMethod = inDefinedShape;
                        this.instrumentedType = typeDescription;
                        this.instrumentedMethod = methodDescription;
                        this.assigner = assigner;
                        this.postProcessor = postProcessor;
                        this.offsetMappings = list;
                        this.methodVisitor = methodVisitor;
                        this.implementationContext = context;
                        this.argumentHandler = forAdvice;
                        this.methodSizeHandler = forAdvice2;
                        this.stackMapFrameHandler = forAdvice3;
                        this.suppressionHandler = bound;
                        this.relocationHandler = bound2;
                        this.exceptionHandler = stackManipulation;
                        this.delegator = delegator;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                    public void prepare() {
                        this.suppressionHandler.onPrepare(this.methodVisitor);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                    public void apply() {
                        this.suppressionHandler.onStart(this.methodVisitor);
                        int i = 0;
                        int i2 = 0;
                        int i3 = 0;
                        for (OffsetMapping.Target target : this.offsetMappings) {
                            int i4 = i;
                            i++;
                            i2 += ((ParameterDescription.InDefinedShape) this.adviceMethod.getParameters().get(i4)).getType().getStackSize().getSize();
                            i3 = Math.max(i3, i2 + target.resolveRead().apply(this.methodVisitor, this.implementationContext).getMaximalSize());
                        }
                        this.delegator.apply(this.methodVisitor, this.adviceMethod, this.instrumentedType, this.instrumentedMethod, isExitAdvice());
                        this.suppressionHandler.onEndWithSkip(this.methodVisitor, this.implementationContext, this.methodSizeHandler, this.stackMapFrameHandler, this.adviceMethod.getReturnType());
                        if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                            this.methodVisitor.visitVarInsn(54, isExitAdvice() ? this.argumentHandler.exit() : this.argumentHandler.enter());
                        } else if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                            this.methodVisitor.visitVarInsn(55, isExitAdvice() ? this.argumentHandler.exit() : this.argumentHandler.enter());
                        } else if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                            this.methodVisitor.visitVarInsn(56, isExitAdvice() ? this.argumentHandler.exit() : this.argumentHandler.enter());
                        } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                            this.methodVisitor.visitVarInsn(57, isExitAdvice() ? this.argumentHandler.exit() : this.argumentHandler.enter());
                        } else if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            this.methodVisitor.visitVarInsn(58, isExitAdvice() ? this.argumentHandler.exit() : this.argumentHandler.enter());
                        }
                        this.methodSizeHandler.requireStackSize(this.postProcessor.resolve(this.instrumentedType, this.instrumentedMethod, this.assigner, this.argumentHandler, this.stackMapFrameHandler, this.exceptionHandler).apply(this.methodVisitor, this.implementationContext).getMaximalSize());
                        this.methodSizeHandler.requireStackSize(this.relocationHandler.apply(this.methodVisitor, isExitAdvice() ? this.argumentHandler.exit() : this.argumentHandler.enter()));
                        this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor);
                        this.methodSizeHandler.requireStackSize(Math.max(i3, this.adviceMethod.getReturnType().getStackSize().getSize()));
                        this.methodSizeHandler.requireLocalVariableLength(this.instrumentedMethod.getStackSize() + this.adviceMethod.getReturnType().getStackSize().getSize());
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$AdviceMethodWriter$ForMethodEnter.class */
                    public static class ForMethodEnter extends AdviceMethodWriter {
                        protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, PostProcessor postProcessor, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation, Delegator delegator) {
                            super(inDefinedShape, typeDescription, methodDescription, assigner, postProcessor, list, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2, stackManipulation, delegator);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                        public void initialize() {
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.AdviceMethodWriter
                        protected boolean isExitAdvice() {
                            return false;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$AdviceMethodWriter$ForMethodExit.class */
                    public static class ForMethodExit extends AdviceMethodWriter {
                        protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, PostProcessor postProcessor, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation, Delegator delegator) {
                            super(inDefinedShape, typeDescription, methodDescription, assigner, postProcessor, list, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2, stackManipulation, delegator);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                        public void initialize() {
                            if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                                this.methodVisitor.visitInsn(3);
                                this.methodVisitor.visitVarInsn(54, this.argumentHandler.exit());
                            } else if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                                this.methodVisitor.visitInsn(9);
                                this.methodVisitor.visitVarInsn(55, this.argumentHandler.exit());
                            } else if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                                this.methodVisitor.visitInsn(11);
                                this.methodVisitor.visitVarInsn(56, this.argumentHandler.exit());
                            } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                                this.methodVisitor.visitInsn(14);
                                this.methodVisitor.visitVarInsn(57, this.argumentHandler.exit());
                            } else if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                                this.methodVisitor.visitInsn(1);
                                this.methodVisitor.visitVarInsn(58, this.argumentHandler.exit());
                            }
                            this.methodSizeHandler.requireStackSize(this.adviceMethod.getReturnType().getStackSize().getSize());
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.AdviceMethodWriter
                        protected boolean isExitAdvice() {
                            return true;
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$ForMethodEnter.class */
                protected static abstract class ForMethodEnter extends Resolved implements Resolved.ForMethodEnter {
                    private final boolean prependLineNumber;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.prependLineNumber == ((ForMethodEnter) obj).prependLineNumber;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public int hashCode() {
                        return (super.hashCode() * 31) + (this.prependLineNumber ? 1 : 0);
                    }

                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                    protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, Delegator delegator) {
                        super(inDefinedShape, postProcessor, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, OffsetMapping.ForExitValue.Factory.of(typeDefinition), new OffsetMapping.Factory.Illegal(Thrown.class), new OffsetMapping.Factory.Illegal(Enter.class), new OffsetMapping.Factory.Illegal(Local.class), new OffsetMapping.Factory.Illegal(Return.class)), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SUPPRESS_ENTER).resolve(TypeDescription.class), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SKIP_ON).resolve(TypeDescription.class), delegator);
                        this.prependLineNumber = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.PREPEND_LINE_NUMBER).resolve(Boolean.class)).booleanValue();
                    }

                    protected static Resolved.ForMethodEnter of(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Delegator delegator, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, boolean z) {
                        return z ? new WithRetainedEnterType(inDefinedShape, postProcessor, list, typeDefinition, delegator) : new WithDiscardedEnterType(inDefinedShape, postProcessor, list, typeDefinition, delegator);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public boolean isPrependLineNumber() {
                        return this.prependLineNumber;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public TypeDefinition getActualAdviceType() {
                        return this.adviceMethod.getReturnType();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    protected Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        return doResolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod.bindEnter(this.adviceMethod), forInstrumentedMethod2.bindEnter(this.adviceMethod), forInstrumentedMethod3.bindEnter(this.adviceMethod), this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription, relocation), stackManipulation);
                    }

                    protected Bound doResolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                        ArrayList arrayList = new ArrayList(this.offsetMappings.size());
                        Iterator<OffsetMapping> it = this.offsetMappings.values().iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.ENTER));
                        }
                        return new AdviceMethodWriter.ForMethodEnter(this.adviceMethod, typeDescription, methodDescription, assigner, this.postProcessor, arrayList, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2, stackManipulation, this.delegator);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$ForMethodEnter$WithRetainedEnterType.class */
                    public static class WithRetainedEnterType extends ForMethodEnter {
                        protected WithRetainedEnterType(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, Delegator delegator) {
                            super(inDefinedShape, postProcessor, list, typeDefinition, delegator);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher
                        public TypeDefinition getAdviceType() {
                            return this.adviceMethod.getReturnType();
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$ForMethodEnter$WithDiscardedEnterType.class */
                    public static class WithDiscardedEnterType extends ForMethodEnter {
                        protected WithDiscardedEnterType(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, Delegator delegator) {
                            super(inDefinedShape, postProcessor, list, typeDefinition, delegator);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher
                        public TypeDefinition getAdviceType() {
                            return TypeDescription.ForLoadedType.of(Void.TYPE);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodEnter
                        protected Bound doResolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                            forAdvice2.requireLocalVariableLengthPadding(this.adviceMethod.getReturnType().getStackSize().getSize());
                            return super.doResolve(typeDescription, methodDescription, methodVisitor, context, assigner, forAdvice, forAdvice2, forAdvice3, bound, bound2, stackManipulation);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$ForMethodExit.class */
                protected static abstract class ForMethodExit extends Resolved implements Resolved.ForMethodExit {
                    private final boolean backupArguments;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public boolean equals(@MaybeNull Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.backupArguments == ((ForMethodExit) obj).backupArguments;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                    public int hashCode() {
                        return (super.hashCode() * 31) + (this.backupArguments ? 1 : 0);
                    }

                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                    protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, Delegator delegator) {
                        super(inDefinedShape, postProcessor, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, OffsetMapping.ForEnterValue.Factory.of(typeDefinition), OffsetMapping.ForExitValue.Factory.of(inDefinedShape.getReturnType()), new OffsetMapping.ForLocalValue.Factory(map), OffsetMapping.ForReturnValue.Factory.INSTANCE, OffsetMapping.ForThrowable.Factory.of(inDefinedShape)), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.SUPPRESS_EXIT).resolve(TypeDescription.class), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.REPEAT_ON).resolve(TypeDescription.class), delegator);
                        this.backupArguments = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.BACKUP_ARGUMENTS).resolve(Boolean.class)).booleanValue();
                    }

                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                    protected static Resolved.ForMethodExit of(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Delegator delegator, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                        TypeDescription typeDescription = (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class);
                        return typeDescription.represents(NoExceptionHandler.class) ? new WithoutExceptionHandler(inDefinedShape, postProcessor, map, list, typeDefinition, delegator) : new WithExceptionHandler(inDefinedShape, postProcessor, map, list, typeDefinition, typeDescription, delegator);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    protected Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        return doResolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod.bindExit(this.adviceMethod, getThrowable().represents(NoExceptionHandler.class)), forInstrumentedMethod2.bindExit(this.adviceMethod), forInstrumentedMethod3.bindExit(this.adviceMethod), this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription, relocation), stackManipulation);
                    }

                    private Bound doResolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, StackManipulation stackManipulation) {
                        ArrayList arrayList = new ArrayList(this.offsetMappings.size());
                        Iterator<OffsetMapping> it = this.offsetMappings.values().iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.EXIT));
                        }
                        return new AdviceMethodWriter.ForMethodExit(this.adviceMethod, typeDescription, methodDescription, assigner, this.postProcessor, arrayList, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2, stackManipulation, this.delegator);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                    public ArgumentHandler.Factory getArgumentHandlerFactory() {
                        return this.backupArguments ? ArgumentHandler.Factory.COPYING : ArgumentHandler.Factory.SIMPLE;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher
                    public TypeDefinition getAdviceType() {
                        return this.adviceMethod.getReturnType();
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$ForMethodExit$WithExceptionHandler.class */
                    public static class WithExceptionHandler extends ForMethodExit {
                        private final TypeDescription throwable;

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                        public boolean equals(@MaybeNull Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.throwable.equals(((WithExceptionHandler) obj).throwable);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Resolved.AbstractBase
                        public int hashCode() {
                            return (super.hashCode() * 31) + this.throwable.hashCode();
                        }

                        protected WithExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, TypeDescription typeDescription, Delegator delegator) {
                            super(inDefinedShape, postProcessor, map, list, typeDefinition, delegator);
                            this.throwable = typeDescription;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return this.throwable;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Dispatcher$Delegating$Resolved$ForMethodExit$WithoutExceptionHandler.class */
                    public static class WithoutExceptionHandler extends ForMethodExit {
                        protected WithoutExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, PostProcessor postProcessor, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, Delegator delegator) {
                            super(inDefinedShape, postProcessor, map, list, typeDefinition, delegator);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return NoExceptionHandler.DESCRIPTION;
                        }
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AdviceVisitor.class */
    protected static abstract class AdviceVisitor extends ExceptionTableSensitiveMethodVisitor implements Dispatcher.RelocationHandler.Relocation {
        private static final int THIS_VARIABLE_INDEX = 0;
        private static final String THIS_VARIABLE_NAME = "this";
        protected final MethodDescription instrumentedMethod;
        private final Label preparationStart;
        private final Dispatcher.Bound methodEnter;
        protected final Dispatcher.Bound methodExit;
        protected final ArgumentHandler.ForInstrumentedMethod argumentHandler;
        protected final MethodSizeHandler.ForInstrumentedMethod methodSizeHandler;
        protected final StackMapFrameHandler.ForInstrumentedMethod stackMapFrameHandler;

        protected abstract void onUserPrepare();

        protected abstract void onUserStart();

        protected abstract void onUserEnd();

        protected AdviceVisitor(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, List<? extends TypeDescription> list, int i, int i2) {
            super(OpenedClassReader.ASM_API, methodVisitor);
            List singletonList;
            List singletonList2;
            List singletonList3;
            this.instrumentedMethod = methodDescription;
            this.preparationStart = new Label();
            TreeMap treeMap = new TreeMap();
            treeMap.putAll(forMethodEnter.getNamedTypes());
            treeMap.putAll(forMethodExit.getNamedTypes());
            this.argumentHandler = forMethodExit.getArgumentHandlerFactory().resolve(methodDescription, forMethodEnter.getAdviceType(), forMethodExit.getAdviceType(), treeMap);
            if (forMethodExit.getAdviceType().represents(Void.TYPE)) {
                singletonList = Collections.emptyList();
            } else {
                singletonList = Collections.singletonList(forMethodExit.getAdviceType().asErasure());
            }
            List of = CompoundList.of(singletonList, (List) this.argumentHandler.getNamedTypes());
            if (forMethodEnter.getActualAdviceType().represents(Void.TYPE)) {
                singletonList2 = Collections.emptyList();
            } else {
                singletonList2 = Collections.singletonList(forMethodEnter.getActualAdviceType().asErasure());
            }
            List list2 = singletonList2;
            if (forMethodEnter.getAdviceType().represents(Void.TYPE)) {
                singletonList3 = Collections.emptyList();
            } else {
                singletonList3 = Collections.singletonList(forMethodEnter.getAdviceType().asErasure());
            }
            List list3 = singletonList3;
            this.methodSizeHandler = MethodSizeHandler.Default.of(methodDescription, of, list3, list, this.argumentHandler.isCopyingArguments(), i);
            this.stackMapFrameHandler = StackMapFrameHandler.Default.of(typeDescription, methodDescription, of, list2, list3, list, forMethodExit.isAlive(), this.argumentHandler.isCopyingArguments(), context.getClassFileVersion(), i, i2);
            this.methodEnter = forMethodEnter.bind(typeDescription, methodDescription, methodVisitor, context, assigner, this.argumentHandler, this.methodSizeHandler, this.stackMapFrameHandler, stackManipulation, this);
            this.methodExit = forMethodExit.bind(typeDescription, methodDescription, methodVisitor, context, assigner, this.argumentHandler, this.methodSizeHandler, this.stackMapFrameHandler, stackManipulation, new Dispatcher.RelocationHandler.Relocation.ForLabel(this.preparationStart));
        }

        @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
        protected void onAfterExceptionTable() {
            this.methodEnter.prepare();
            onUserPrepare();
            this.methodExit.prepare();
            this.methodEnter.initialize();
            this.methodExit.initialize();
            this.stackMapFrameHandler.injectInitializationFrame(this.mv);
            this.methodEnter.apply();
            this.mv.visitLabel(this.preparationStart);
            this.methodSizeHandler.requireStackSize(this.argumentHandler.prepare(this.mv));
            this.stackMapFrameHandler.injectStartFrame(this.mv);
            this.mv.visitInsn(0);
            onUserStart();
        }

        @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
        protected void onVisitVarInsn(int i, int i2) {
            this.mv.visitVarInsn(i, this.argumentHandler.argument(i2));
        }

        @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
        protected void onVisitIincInsn(int i, int i2) {
            this.mv.visitIincInsn(this.argumentHandler.argument(i), i2);
        }

        @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
        public void onVisitFrame(int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
            this.stackMapFrameHandler.translateFrame(this.mv, i, i2, objArr, i3, objArr2);
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitMaxs(int i, int i2) {
            onUserEnd();
            this.mv.visitMaxs(this.methodSizeHandler.compoundStackSize(i), this.methodSizeHandler.compoundLocalVariableLength(i2));
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
            this.mv.visitLocalVariable(str, str2, str3, label, label2, (i == 0 && THIS_VARIABLE_NAME.equals(str)) ? i : this.argumentHandler.variable(i));
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = this.argumentHandler.variable(iArr[i2]);
            }
            return this.mv.visitLocalVariableAnnotation(i, typePath, labelArr, labelArr2, iArr2, str, z);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AdviceVisitor$WithoutExitAdvice.class */
        public static class WithoutExitAdvice extends AdviceVisitor {
            protected WithoutExitAdvice(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, int i, int i2) {
                super(methodVisitor, context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, Dispatcher.Inactive.INSTANCE, Collections.emptyList(), i, i2);
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.Relocation
            public void apply(MethodVisitor methodVisitor) {
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    methodVisitor.visitInsn(3);
                    methodVisitor.visitInsn(172);
                    return;
                }
                if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    methodVisitor.visitInsn(9);
                    methodVisitor.visitInsn(173);
                    return;
                }
                if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    methodVisitor.visitInsn(11);
                    methodVisitor.visitInsn(174);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    methodVisitor.visitInsn(14);
                    methodVisitor.visitInsn(175);
                } else if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    methodVisitor.visitInsn(177);
                } else {
                    methodVisitor.visitInsn(1);
                    methodVisitor.visitInsn(176);
                }
            }

            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserPrepare() {
            }

            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserStart() {
            }

            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserEnd() {
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AdviceVisitor$WithExitAdvice.class */
        protected static abstract class WithExitAdvice extends AdviceVisitor {
            protected final Label returnHandler;

            protected abstract void onUserReturn();

            protected abstract void onExitAdviceReturn();

            protected WithExitAdvice(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, List<? extends TypeDescription> list, int i, int i2) {
                super(StackAwareMethodVisitor.of(methodVisitor, methodDescription), context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, forMethodExit, list, i, i2);
                this.returnHandler = new Label();
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.RelocationHandler.Relocation
            public void apply(MethodVisitor methodVisitor) {
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    methodVisitor.visitInsn(3);
                } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    methodVisitor.visitInsn(9);
                } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    methodVisitor.visitInsn(11);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    methodVisitor.visitInsn(14);
                } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    methodVisitor.visitInsn(1);
                }
                methodVisitor.visitJumpInsn(167, this.returnHandler);
            }

            @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
            protected void onVisitInsn(int i) {
                switch (i) {
                    case 172:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(54, 21, StackSize.SINGLE));
                        break;
                    case 173:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(55, 22, StackSize.DOUBLE));
                        break;
                    case 174:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(56, 23, StackSize.SINGLE));
                        break;
                    case 175:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(57, 24, StackSize.DOUBLE));
                        break;
                    case 176:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(58, 25, StackSize.SINGLE));
                        break;
                    case 177:
                        ((StackAwareMethodVisitor) this.mv).drainStack();
                        break;
                    default:
                        this.mv.visitInsn(i);
                        return;
                }
                this.mv.visitJumpInsn(167, this.returnHandler);
            }

            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserEnd() {
                this.mv.visitLabel(this.returnHandler);
                onUserReturn();
                this.stackMapFrameHandler.injectCompletionFrame(this.mv);
                this.methodExit.apply();
                onExitAdviceReturn();
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    this.mv.visitVarInsn(21, this.argumentHandler.returned());
                    this.mv.visitInsn(172);
                } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    this.mv.visitVarInsn(22, this.argumentHandler.returned());
                    this.mv.visitInsn(173);
                } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    this.mv.visitVarInsn(23, this.argumentHandler.returned());
                    this.mv.visitInsn(174);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    this.mv.visitVarInsn(24, this.argumentHandler.returned());
                    this.mv.visitInsn(175);
                } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    this.mv.visitVarInsn(25, this.argumentHandler.returned());
                    this.mv.visitInsn(176);
                } else {
                    this.mv.visitInsn(177);
                }
                this.methodSizeHandler.requireStackSize(this.instrumentedMethod.getReturnType().getStackSize().getSize());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AdviceVisitor$WithExitAdvice$WithoutExceptionHandling.class */
            public static class WithoutExceptionHandling extends WithExitAdvice {
                /* JADX WARN: Illegal instructions before constructor call */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                protected WithoutExceptionHandling(net.bytebuddy.jar.asm.MethodVisitor r14, net.bytebuddy.implementation.Implementation.Context r15, net.bytebuddy.implementation.bytecode.assign.Assigner r16, net.bytebuddy.implementation.bytecode.StackManipulation r17, net.bytebuddy.description.type.TypeDescription r18, net.bytebuddy.description.method.MethodDescription r19, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter r20, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit r21, int r22, int r23) {
                    /*
                        r13 = this;
                        r0 = r13
                        r1 = r14
                        r2 = r15
                        r3 = r16
                        r4 = r17
                        r5 = r18
                        r6 = r19
                        r7 = r20
                        r8 = r21
                        r9 = r19
                        net.bytebuddy.description.type.TypeDescription$Generic r9 = r9.getReturnType()
                        java.lang.Class r10 = java.lang.Void.TYPE
                        boolean r9 = r9.represents(r10)
                        if (r9 == 0) goto L26
                        java.util.List r9 = java.util.Collections.emptyList()
                        goto L35
                    L26:
                        r9 = r19
                        net.bytebuddy.description.type.TypeDescription$Generic r9 = r9.getReturnType()
                        net.bytebuddy.description.type.TypeDescription r9 = r9.asErasure()
                        java.util.List r9 = java.util.Collections.singletonList(r9)
                    L35:
                        r10 = r22
                        r11 = r23
                        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice.WithoutExceptionHandling.<init>(net.bytebuddy.jar.asm.MethodVisitor, net.bytebuddy.implementation.Implementation$Context, net.bytebuddy.implementation.bytecode.assign.Assigner, net.bytebuddy.implementation.bytecode.StackManipulation, net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.method.MethodDescription, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodEnter, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodExit, int, int):void");
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserPrepare() {
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserStart() {
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onUserReturn() {
                    if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.mv);
                        this.mv.visitVarInsn(54, this.argumentHandler.returned());
                        return;
                    }
                    if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.mv);
                        this.mv.visitVarInsn(55, this.argumentHandler.returned());
                        return;
                    }
                    if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.mv);
                        this.mv.visitVarInsn(56, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.mv);
                        this.mv.visitVarInsn(57, this.argumentHandler.returned());
                    } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.mv);
                        this.mv.visitVarInsn(58, this.argumentHandler.returned());
                    }
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onExitAdviceReturn() {
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AdviceVisitor$WithExitAdvice$WithExceptionHandling.class */
            public static class WithExceptionHandling extends WithExitAdvice {
                private final TypeDescription throwable;
                private final Label exceptionHandler;
                protected final Label userStart;

                protected WithExceptionHandling(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, int i, int i2, TypeDescription typeDescription2) {
                    super(methodVisitor, context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, forMethodExit, methodDescription.getReturnType().represents(Void.TYPE) ? Collections.singletonList(TypeDescription.ForLoadedType.of(Throwable.class)) : Arrays.asList(methodDescription.getReturnType().asErasure(), TypeDescription.ForLoadedType.of(Throwable.class)), i, i2);
                    this.throwable = typeDescription2;
                    this.exceptionHandler = new Label();
                    this.userStart = new Label();
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserPrepare() {
                    this.mv.visitTryCatchBlock(this.userStart, this.returnHandler, this.exceptionHandler, this.throwable.getInternalName());
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserStart() {
                    this.mv.visitLabel(this.userStart);
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onUserReturn() {
                    this.stackMapFrameHandler.injectReturnFrame(this.mv);
                    if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                        this.mv.visitVarInsn(54, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                        this.mv.visitVarInsn(55, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                        this.mv.visitVarInsn(56, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                        this.mv.visitVarInsn(57, this.argumentHandler.returned());
                    } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        this.mv.visitVarInsn(58, this.argumentHandler.returned());
                    }
                    this.mv.visitInsn(1);
                    this.mv.visitVarInsn(58, this.argumentHandler.thrown());
                    Label label = new Label();
                    this.mv.visitJumpInsn(167, label);
                    this.mv.visitLabel(this.exceptionHandler);
                    this.stackMapFrameHandler.injectExceptionFrame(this.mv);
                    this.mv.visitVarInsn(58, this.argumentHandler.thrown());
                    if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                        this.mv.visitInsn(3);
                        this.mv.visitVarInsn(54, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                        this.mv.visitInsn(9);
                        this.mv.visitVarInsn(55, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                        this.mv.visitInsn(11);
                        this.mv.visitVarInsn(56, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                        this.mv.visitInsn(14);
                        this.mv.visitVarInsn(57, this.argumentHandler.returned());
                    } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        this.mv.visitInsn(1);
                        this.mv.visitVarInsn(58, this.argumentHandler.returned());
                    }
                    this.mv.visitLabel(label);
                    this.methodSizeHandler.requireStackSize(StackSize.SINGLE.getSize());
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onExitAdviceReturn() {
                    this.mv.visitVarInsn(25, this.argumentHandler.thrown());
                    Label label = new Label();
                    this.mv.visitJumpInsn(198, label);
                    this.mv.visitVarInsn(25, this.argumentHandler.thrown());
                    this.mv.visitInsn(191);
                    this.mv.visitLabel(label);
                    this.stackMapFrameHandler.injectPostCompletionFrame(this.mv);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Appender.class */
    protected static class Appender implements ByteCodeAppender {
        private final Advice advice;
        private final Implementation.Target implementationTarget;
        private final ByteCodeAppender delegate;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.advice.equals(((Appender) obj).advice) && this.implementationTarget.equals(((Appender) obj).implementationTarget) && this.delegate.equals(((Appender) obj).delegate);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.advice.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + this.delegate.hashCode();
        }

        protected Appender(Advice advice, Implementation.Target target, ByteCodeAppender byteCodeAppender) {
            this.advice = advice;
            this.implementationTarget = target;
            this.delegate = byteCodeAppender;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            EmulatingMethodVisitor emulatingMethodVisitor = new EmulatingMethodVisitor(methodVisitor, this.delegate);
            return emulatingMethodVisitor.resolve(this.advice.doWrap(this.implementationTarget.getInstrumentedType(), methodDescription, emulatingMethodVisitor, context, 0, 0), context, methodDescription);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$Appender$EmulatingMethodVisitor.class */
        protected static class EmulatingMethodVisitor extends MethodVisitor {
            private final ByteCodeAppender delegate;
            private int stackSize;
            private int localVariableLength;

            protected EmulatingMethodVisitor(MethodVisitor methodVisitor, ByteCodeAppender byteCodeAppender) {
                super(OpenedClassReader.ASM_API, methodVisitor);
                this.delegate = byteCodeAppender;
            }

            protected ByteCodeAppender.Size resolve(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                methodVisitor.visitCode();
                ByteCodeAppender.Size apply = this.delegate.apply(methodVisitor, context, methodDescription);
                methodVisitor.visitMaxs(apply.getOperandStackSize(), apply.getLocalVariableSize());
                methodVisitor.visitEnd();
                return new ByteCodeAppender.Size(this.stackSize, this.localVariableLength);
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitCode() {
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitMaxs(int i, int i2) {
                this.stackSize = i;
                this.localVariableLength = i2;
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitEnd() {
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned.class */
    public static abstract class AssignReturned implements PostProcessor {
        public static final int NO_INDEX = -1;
        protected final TypeDescription.Generic type;
        protected final ExceptionHandler.Factory exceptionHandlerFactory;
        protected final boolean exit;
        protected final boolean skipOnDefaultValue;

        @Target({ElementType.METHOD})
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$AsScalar.class */
        public @interface AsScalar {
            boolean skipOnDefaultValue() default true;
        }

        protected abstract TypeDescription.Generic getType();

        protected abstract Collection<Handler> getHandlers();

        protected abstract StackManipulation toLoadInstruction(Handler handler, int i);

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.exit == ((AssignReturned) obj).exit && this.skipOnDefaultValue == ((AssignReturned) obj).skipOnDefaultValue && this.type.equals(((AssignReturned) obj).type) && this.exceptionHandlerFactory.equals(((AssignReturned) obj).exceptionHandlerFactory);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.type.hashCode()) * 31) + this.exceptionHandlerFactory.hashCode()) * 31) + (this.exit ? 1 : 0)) * 31) + (this.skipOnDefaultValue ? 1 : 0);
        }

        protected AssignReturned(TypeDescription.Generic generic, ExceptionHandler.Factory factory, boolean z, boolean z2) {
            this.type = generic;
            this.exceptionHandlerFactory = factory;
            this.exit = z;
            this.skipOnDefaultValue = z2;
        }

        @Override // net.bytebuddy.asm.Advice.PostProcessor
        public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, StackMapFrameHandler.ForPostProcessor forPostProcessor, StackManipulation stackManipulation) {
            ArrayList arrayList = new ArrayList(getHandlers().size());
            for (Handler handler : getHandlers()) {
                arrayList.add(handler.resolve(typeDescription, methodDescription, assigner, argumentHandler, getType(), toLoadInstruction(handler, this.exit ? argumentHandler.exit() : argumentHandler.enter())));
            }
            StackManipulation wrap = this.exceptionHandlerFactory.wrap(new StackManipulation.Compound(arrayList), stackManipulation, forPostProcessor);
            if (this.skipOnDefaultValue) {
                return DefaultValueSkip.of(wrap, forPostProcessor, this.exit ? argumentHandler.exit() : argumentHandler.enter(), this.type);
            }
            return wrap;
        }

        @Target({ElementType.METHOD})
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToArguments.class */
        public @interface ToArguments {

            @Target({})
            @Repeatable(ToArguments.class)
            @RepeatedAnnotationPlugin.Enhance(ToArguments.class)
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToArguments$ToArgument.class */
            public @interface ToArgument {
                int value();

                int index() default -1;

                Assigner.Typing typing() default Assigner.Typing.STATIC;
            }

            ToArgument[] value();

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToArguments$Handler.class */
            public static class Handler implements Handler {
                private final int value;
                private final int index;
                private final Assigner.Typing typing;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((Handler) obj).value && this.index == ((Handler) obj).index && this.typing.equals(((Handler) obj).typing);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.value) * 31) + this.index) * 31) + this.typing.hashCode();
                }

                protected Handler(int i, int i2, Assigner.Typing typing) {
                    this.value = i;
                    this.index = i2;
                    this.typing = typing;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, TypeDescription.Generic generic, StackManipulation stackManipulation) {
                    if (methodDescription.getParameters().size() < this.value) {
                        throw new IllegalStateException(methodDescription + " declares less then " + this.value + " parameters");
                    }
                    StackManipulation assign = assigner.assign(generic, ((ParameterDescription) methodDescription.getParameters().get(this.value)).getType(), this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + generic + " to " + ((ParameterDescription) methodDescription.getParameters().get(this.value)).getType());
                    }
                    return new StackManipulation.Compound(stackManipulation, assign, MethodVariableAccess.of(((ParameterDescription) methodDescription.getParameters().get(this.value)).getType()).storeAt(argumentHandler.argument(((ParameterDescription) methodDescription.getParameters().get(this.value)).getOffset())));
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToArguments$Handler$Factory.class */
                public enum Factory implements Handler.Factory<ToArguments> {
                    INSTANCE;

                    private static final MethodDescription.InDefinedShape TO_ARGUMENTS_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(ToArguments.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
                    private static final MethodDescription.InDefinedShape TO_ARGUMENT_VALUE;
                    private static final MethodDescription.InDefinedShape TO_ARGUMENT_INDEX;
                    private static final MethodDescription.InDefinedShape TO_ARGUMENT_TYPING;

                    static {
                        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(ToArgument.class).getDeclaredMethods();
                        TO_ARGUMENT_VALUE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("value")).getOnly();
                        TO_ARGUMENT_INDEX = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("index")).getOnly();
                        TO_ARGUMENT_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final Class<ToArguments> getAnnotationType() {
                        return ToArguments.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends ToArguments> loadable) {
                        ArrayList arrayList = new ArrayList();
                        for (AnnotationDescription annotationDescription : (AnnotationDescription[]) loadable.getValue(TO_ARGUMENTS_VALUE).resolve(AnnotationDescription[].class)) {
                            int intValue = ((Integer) annotationDescription.getValue(TO_ARGUMENT_VALUE).resolve(Integer.class)).intValue();
                            if (intValue < 0) {
                                throw new IllegalStateException("An argument cannot have a negative index for " + inDefinedShape);
                            }
                            arrayList.add(new Handler(intValue, ((Integer) annotationDescription.getValue(TO_ARGUMENT_INDEX).resolve(Integer.class)).intValue(), (Assigner.Typing) annotationDescription.getValue(TO_ARGUMENT_TYPING).load(ToArguments.class.getClassLoader()).resolve(Assigner.Typing.class)));
                        }
                        return arrayList;
                    }
                }
            }
        }

        @Target({ElementType.METHOD})
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToAllArguments.class */
        public @interface ToAllArguments {
            int index() default -1;

            Assigner.Typing typing() default Assigner.Typing.STATIC;

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToAllArguments$Handler.class */
            public static class Handler implements Handler {
                private final int index;
                private final Assigner.Typing typing;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Handler) obj).index && this.typing.equals(((Handler) obj).typing);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.index) * 31) + this.typing.hashCode();
                }

                protected Handler(int i, Assigner.Typing typing) {
                    this.index = i;
                    this.typing = typing;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
                public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, TypeDescription.Generic generic, StackManipulation stackManipulation) {
                    ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                    if (!generic.isArray()) {
                        StackManipulation assign = assigner.assign(generic, TypeDefinition.Sort.describe(Object[].class), this.typing);
                        if (!assign.isValid()) {
                            throw new IllegalStateException("Cannot assign " + generic + " to " + Object[].class);
                        }
                        generic = TypeDefinition.Sort.describe(Object[].class);
                        stackManipulation = new StackManipulation.Compound(stackManipulation, assign);
                    }
                    Iterator it = methodDescription.getParameters().iterator();
                    while (it.hasNext()) {
                        ParameterDescription parameterDescription = (ParameterDescription) it.next();
                        StackManipulation assign2 = assigner.assign(generic.getComponentType(), parameterDescription.getType(), this.typing);
                        if (!assign2.isValid()) {
                            throw new IllegalStateException("Cannot assign " + generic.getComponentType() + " to " + parameterDescription);
                        }
                        arrayList.add(new StackManipulation.Compound(assign2, MethodVariableAccess.of(parameterDescription.getType()).storeAt(argumentHandler.argument(parameterDescription.getOffset()))));
                    }
                    return new StackManipulation.Compound(stackManipulation, ArrayAccess.of(generic.getComponentType()).forEach(arrayList), Removal.SINGLE);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToAllArguments$Handler$Factory.class */
                public enum Factory implements Handler.Factory<ToAllArguments> {
                    INSTANCE;

                    private static final MethodDescription.InDefinedShape TO_ALL_ARGUMENTS_INDEX;
                    private static final MethodDescription.InDefinedShape TO_ALL_ARGUMENTS_TYPING;

                    static {
                        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(ToAllArguments.class).getDeclaredMethods();
                        TO_ALL_ARGUMENTS_INDEX = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("index")).getOnly();
                        TO_ALL_ARGUMENTS_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final Class<ToAllArguments> getAnnotationType() {
                        return ToAllArguments.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends ToAllArguments> loadable) {
                        return Collections.singletonList(new Handler(((Integer) loadable.getValue(TO_ALL_ARGUMENTS_INDEX).resolve(Integer.class)).intValue(), (Assigner.Typing) loadable.getValue(TO_ALL_ARGUMENTS_TYPING).load(ToAllArguments.class.getClassLoader()).resolve(Assigner.Typing.class)));
                    }
                }
            }
        }

        @Target({ElementType.METHOD})
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToThis.class */
        public @interface ToThis {
            int index() default -1;

            Assigner.Typing typing() default Assigner.Typing.STATIC;

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToThis$Handler.class */
            public static class Handler implements Handler {
                private final int index;
                private final Assigner.Typing typing;
                private final boolean exit;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Handler) obj).index && this.exit == ((Handler) obj).exit && this.typing.equals(((Handler) obj).typing);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.index) * 31) + this.typing.hashCode()) * 31) + (this.exit ? 1 : 0);
                }

                protected Handler(int i, Assigner.Typing typing, boolean z) {
                    this.index = i;
                    this.typing = typing;
                    this.exit = z;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, TypeDescription.Generic generic, StackManipulation stackManipulation) {
                    if (methodDescription.isStatic()) {
                        throw new IllegalStateException("Cannot assign this reference for static method " + methodDescription);
                    }
                    if (!this.exit && methodDescription.isConstructor()) {
                        throw new IllegalStateException("Cannot assign this reference in constructor prior to initialization for " + methodDescription);
                    }
                    StackManipulation assign = assigner.assign(generic, typeDescription.asGenericType(), this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + generic + " to " + typeDescription);
                    }
                    return new StackManipulation.Compound(stackManipulation, assign, MethodVariableAccess.REFERENCE.storeAt(argumentHandler.argument(0)));
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToThis$Handler$Factory.class */
                public enum Factory implements Handler.Factory<ToThis> {
                    INSTANCE;

                    private static final MethodDescription.InDefinedShape TO_THIS_INDEX;
                    private static final MethodDescription.InDefinedShape TO_THIS_TYPING;

                    static {
                        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(ToThis.class).getDeclaredMethods();
                        TO_THIS_INDEX = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("index")).getOnly();
                        TO_THIS_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final Class<ToThis> getAnnotationType() {
                        return ToThis.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends ToThis> loadable) {
                        return Collections.singletonList(new Handler(((Integer) loadable.getValue(TO_THIS_INDEX).resolve(Integer.class)).intValue(), (Assigner.Typing) loadable.getValue(TO_THIS_TYPING).load(ToThis.class.getClassLoader()).resolve(Assigner.Typing.class), z));
                    }
                }
            }
        }

        @Target({ElementType.METHOD})
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToFields.class */
        public @interface ToFields {

            @Target({})
            @Repeatable(ToFields.class)
            @RepeatedAnnotationPlugin.Enhance(ToFields.class)
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToFields$ToField.class */
            public @interface ToField {
                String value() default "";

                Class<?> declaringType() default void.class;

                int index() default -1;

                Assigner.Typing typing() default Assigner.Typing.STATIC;
            }

            ToField[] value();

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToFields$Handler.class */
            public static class Handler implements Handler {
                private final int index;
                private final String name;
                private final TypeDescription declaringType;
                private final Assigner.Typing typing;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Handler) obj).index && this.typing.equals(((Handler) obj).typing) && this.name.equals(((Handler) obj).name) && this.declaringType.equals(((Handler) obj).declaringType);
                }

                public int hashCode() {
                    return (((((((getClass().hashCode() * 31) + this.index) * 31) + this.name.hashCode()) * 31) + this.declaringType.hashCode()) * 31) + this.typing.hashCode();
                }

                protected Handler(int i, String str, TypeDescription typeDescription, Assigner.Typing typing) {
                    this.index = i;
                    this.name = str;
                    this.declaringType = typeDescription;
                    this.typing = typing;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
                public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, TypeDescription.Generic generic, StackManipulation stackManipulation) {
                    FieldLocator.Resolution locate;
                    StackManipulation loadThis;
                    FieldLocator forClassHierarchy = this.declaringType.represents(Void.TYPE) ? new FieldLocator.ForClassHierarchy(typeDescription) : new FieldLocator.ForExactType(this.declaringType);
                    if (this.name.equals("")) {
                        locate = OffsetMapping.ForField.Unresolved.resolveAccessor(forClassHierarchy, methodDescription);
                    } else {
                        locate = forClassHierarchy.locate(this.name);
                    }
                    FieldLocator.Resolution resolution = locate;
                    if (!locate.isResolved()) {
                        throw new IllegalStateException("Cannot resolve field " + this.name + " for " + typeDescription);
                    }
                    if (!resolution.getField().isVisibleTo(typeDescription)) {
                        throw new IllegalStateException(resolution.getField() + " is not visible to " + typeDescription);
                    }
                    if (resolution.getField().isStatic()) {
                        loadThis = StackManipulation.Trivial.INSTANCE;
                    } else {
                        if (methodDescription.isStatic()) {
                            throw new IllegalStateException("Cannot access member field " + resolution.getField() + " from static " + methodDescription);
                        }
                        if (!typeDescription.isAssignableTo(resolution.getField().getDeclaringType().asErasure())) {
                            throw new IllegalStateException(typeDescription + " does not define " + resolution.getField());
                        }
                        loadThis = MethodVariableAccess.loadThis();
                    }
                    StackManipulation assign = assigner.assign(generic, resolution.getField().getType(), this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + generic + " to " + resolution.getField());
                    }
                    return new StackManipulation.Compound(loadThis, stackManipulation, assign, FieldAccess.forField(resolution.getField()).write());
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToFields$Handler$Factory.class */
                public enum Factory implements Handler.Factory<ToFields> {
                    INSTANCE;

                    private static final MethodDescription.InDefinedShape TO_FIELDS_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(ToFields.class).getDeclaredMethods().filter(ElementMatchers.named("value")).getOnly();
                    private static final MethodDescription.InDefinedShape TO_FIELD_VALUE;
                    private static final MethodDescription.InDefinedShape TO_FIELD_INDEX;
                    private static final MethodDescription.InDefinedShape TO_FIELD_DECLARING_TYPE;
                    private static final MethodDescription.InDefinedShape TO_FIELD_TYPING;

                    static {
                        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(ToField.class).getDeclaredMethods();
                        TO_FIELD_VALUE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("value")).getOnly();
                        TO_FIELD_INDEX = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("index")).getOnly();
                        TO_FIELD_DECLARING_TYPE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("declaringType")).getOnly();
                        TO_FIELD_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final Class<ToFields> getAnnotationType() {
                        return ToFields.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends ToFields> loadable) {
                        ArrayList arrayList = new ArrayList();
                        for (AnnotationDescription annotationDescription : (AnnotationDescription[]) loadable.getValue(TO_FIELDS_VALUE).resolve(AnnotationDescription[].class)) {
                            arrayList.add(new Handler(((Integer) annotationDescription.getValue(TO_FIELD_INDEX).resolve(Integer.class)).intValue(), (String) annotationDescription.getValue(TO_FIELD_VALUE).resolve(String.class), (TypeDescription) annotationDescription.getValue(TO_FIELD_DECLARING_TYPE).resolve(TypeDescription.class), (Assigner.Typing) annotationDescription.getValue(TO_FIELD_TYPING).load(ToFields.class.getClassLoader()).resolve(Assigner.Typing.class)));
                        }
                        return arrayList;
                    }
                }
            }
        }

        @Target({ElementType.METHOD})
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToReturned.class */
        public @interface ToReturned {
            int index() default -1;

            Assigner.Typing typing() default Assigner.Typing.STATIC;

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToReturned$Handler.class */
            public static class Handler implements Handler {
                private final int index;
                private final Assigner.Typing typing;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Handler) obj).index && this.typing.equals(((Handler) obj).typing);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.index) * 31) + this.typing.hashCode();
                }

                protected Handler(int i, Assigner.Typing typing) {
                    this.index = i;
                    this.typing = typing;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, TypeDescription.Generic generic, StackManipulation stackManipulation) {
                    if (methodDescription.getReturnType().represents(Void.TYPE)) {
                        return StackManipulation.Trivial.INSTANCE;
                    }
                    StackManipulation assign = assigner.assign(generic, methodDescription.getReturnType(), this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + generic + " to " + methodDescription.getReturnType());
                    }
                    return new StackManipulation.Compound(stackManipulation, assign, MethodVariableAccess.of(methodDescription.getReturnType()).storeAt(argumentHandler.returned()));
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToReturned$Handler$Factory.class */
                public enum Factory implements Handler.Factory<ToReturned> {
                    INSTANCE;

                    private static final MethodDescription.InDefinedShape TO_RETURNED_INDEX;
                    private static final MethodDescription.InDefinedShape TO_RETURNED_TYPING;

                    static {
                        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(ToReturned.class).getDeclaredMethods();
                        TO_RETURNED_INDEX = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("index")).getOnly();
                        TO_RETURNED_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final Class<ToReturned> getAnnotationType() {
                        return ToReturned.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends ToReturned> loadable) {
                        if (!z) {
                            throw new IllegalStateException("Cannot write returned value from enter advice " + inDefinedShape);
                        }
                        return Collections.singletonList(new Handler(((Integer) loadable.getValue(TO_RETURNED_INDEX).resolve(Integer.class)).intValue(), (Assigner.Typing) loadable.getValue(TO_RETURNED_TYPING).load(ToReturned.class.getClassLoader()).resolve(Assigner.Typing.class)));
                    }
                }
            }
        }

        @Target({ElementType.METHOD})
        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToThrown.class */
        public @interface ToThrown {
            int index() default -1;

            Assigner.Typing typing() default Assigner.Typing.STATIC;

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToThrown$Handler.class */
            public static class Handler implements Handler {
                private final int index;
                private final Assigner.Typing typing;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Handler) obj).index && this.typing.equals(((Handler) obj).typing);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.index) * 31) + this.typing.hashCode();
                }

                protected Handler(int i, Assigner.Typing typing) {
                    this.index = i;
                    this.typing = typing;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler
                public StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, TypeDescription.Generic generic, StackManipulation stackManipulation) {
                    if (methodDescription.getReturnType().represents(Void.TYPE)) {
                        return StackManipulation.Trivial.INSTANCE;
                    }
                    StackManipulation assign = assigner.assign(generic, TypeDefinition.Sort.describe(Throwable.class), this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + generic + " to " + Throwable.class.getName());
                    }
                    return new StackManipulation.Compound(stackManipulation, assign, MethodVariableAccess.REFERENCE.storeAt(argumentHandler.thrown()));
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ToThrown$Handler$Factory.class */
                public enum Factory implements Handler.Factory<ToThrown> {
                    INSTANCE;

                    private static final MethodDescription.InDefinedShape TO_THROWN_INDEX;
                    private static final MethodDescription.InDefinedShape TO_THROWN_TYPING;

                    static {
                        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(ToThrown.class).getDeclaredMethods();
                        TO_THROWN_INDEX = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("index")).getOnly();
                        TO_THROWN_TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public final Class<ToThrown> getAnnotationType() {
                        return ToThrown.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming annotation for exit advice.")
                    public final List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends ToThrown> loadable) {
                        if (z) {
                            if (((TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class)).represents(NoExceptionHandler.class)) {
                                throw new IllegalStateException("Cannot assign thrown value for non-catching exit advice " + inDefinedShape);
                            }
                            return Collections.singletonList(new Handler(((Integer) loadable.getValue(TO_THROWN_INDEX).resolve(Integer.class)).intValue(), (Assigner.Typing) loadable.getValue(TO_THROWN_TYPING).load(ToThrown.class.getClassLoader()).resolve(Assigner.Typing.class)));
                        }
                        throw new IllegalStateException("Cannot assign thrown value from enter advice " + inDefinedShape);
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ForArray.class */
        protected static class ForArray extends AssignReturned {
            private final Map<Handler, Integer> handlers;

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.handlers.equals(((ForArray) obj).handlers);
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            public int hashCode() {
                return (super.hashCode() * 31) + this.handlers.hashCode();
            }

            protected ForArray(TypeDescription.Generic generic, ExceptionHandler.Factory factory, boolean z, Collection<List<Handler>> collection) {
                super(generic, factory, z, true);
                this.handlers = new LinkedHashMap();
                Iterator<List<Handler>> it = collection.iterator();
                while (it.hasNext()) {
                    for (Handler handler : it.next()) {
                        int index = handler.getIndex();
                        if (index >= 0) {
                            this.handlers.put(handler, Integer.valueOf(index));
                        } else {
                            throw new IllegalStateException("Handler on array requires positive index for " + handler);
                        }
                    }
                }
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
            protected TypeDescription.Generic getType() {
                return this.type.getComponentType();
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            protected Collection<Handler> getHandlers() {
                return this.handlers.keySet();
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
            protected StackManipulation toLoadInstruction(Handler handler, int i) {
                return new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(i), IntegerConstant.forValue(this.handlers.get(handler).intValue()), ArrayAccess.of(this.type.getComponentType()).load());
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ForScalar.class */
        protected static class ForScalar extends AssignReturned {
            private final List<Handler> handlers;

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.handlers.equals(((ForScalar) obj).handlers);
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            public int hashCode() {
                return (super.hashCode() * 31) + this.handlers.hashCode();
            }

            protected ForScalar(TypeDescription.Generic generic, ExceptionHandler.Factory factory, boolean z, boolean z2, Collection<List<Handler>> collection) {
                super(generic, factory, z, z2);
                this.handlers = new ArrayList();
                Iterator<List<Handler>> it = collection.iterator();
                while (it.hasNext()) {
                    for (Handler handler : it.next()) {
                        if (handler.getIndex() < 0) {
                            this.handlers.add(handler);
                        } else {
                            throw new IllegalStateException("Handler on array requires negative index for " + handler);
                        }
                    }
                }
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            protected TypeDescription.Generic getType() {
                return this.type;
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            protected Collection<Handler> getHandlers() {
                return this.handlers;
            }

            @Override // net.bytebuddy.asm.Advice.AssignReturned
            protected StackManipulation toLoadInstruction(Handler handler, int i) {
                return MethodVariableAccess.of(this.type).loadFrom(i);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$DefaultValueSkip.class */
        protected static class DefaultValueSkip implements StackManipulation {
            private final StackManipulation stackManipulation;
            private final StackMapFrameHandler.ForPostProcessor stackMapFrameHandler;
            private final int offset;
            private final Dispatcher dispatcher;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.offset == ((DefaultValueSkip) obj).offset && this.dispatcher.equals(((DefaultValueSkip) obj).dispatcher) && this.stackManipulation.equals(((DefaultValueSkip) obj).stackManipulation) && this.stackMapFrameHandler.equals(((DefaultValueSkip) obj).stackMapFrameHandler);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.stackManipulation.hashCode()) * 31) + this.stackMapFrameHandler.hashCode()) * 31) + this.offset) * 31) + this.dispatcher.hashCode();
            }

            protected DefaultValueSkip(StackManipulation stackManipulation, StackMapFrameHandler.ForPostProcessor forPostProcessor, int i, Dispatcher dispatcher) {
                this.stackManipulation = stackManipulation;
                this.stackMapFrameHandler = forPostProcessor;
                this.offset = i;
                this.dispatcher = dispatcher;
            }

            protected static StackManipulation of(StackManipulation stackManipulation, StackMapFrameHandler.ForPostProcessor forPostProcessor, int i, TypeDefinition typeDefinition) {
                Dispatcher dispatcher;
                if (typeDefinition.isPrimitive()) {
                    if (typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Byte.TYPE) || typeDefinition.represents(Short.TYPE) || typeDefinition.represents(Character.TYPE) || typeDefinition.represents(Integer.TYPE)) {
                        dispatcher = Dispatcher.INTEGER;
                    } else if (typeDefinition.represents(Long.TYPE)) {
                        dispatcher = Dispatcher.LONG;
                    } else if (typeDefinition.represents(Float.TYPE)) {
                        dispatcher = Dispatcher.FLOAT;
                    } else if (typeDefinition.represents(Double.TYPE)) {
                        dispatcher = Dispatcher.DOUBLE;
                    } else {
                        throw new IllegalArgumentException("Cannot apply skip for " + typeDefinition);
                    }
                } else {
                    dispatcher = Dispatcher.REFERENCE;
                }
                return new DefaultValueSkip(stackManipulation, forPostProcessor, i, dispatcher);
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return this.stackManipulation.isValid();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                Label label = new Label();
                StackManipulation.Size aggregate = this.dispatcher.apply(methodVisitor, this.offset, label).aggregate(this.stackManipulation.apply(methodVisitor, context));
                methodVisitor.visitLabel(label);
                this.stackMapFrameHandler.injectIntermediateFrame(methodVisitor, Collections.emptyList());
                methodVisitor.visitInsn(0);
                return aggregate;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$DefaultValueSkip$Dispatcher.class */
            public enum Dispatcher {
                INTEGER { // from class: net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher.1
                    @Override // net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher
                    protected final StackManipulation.Size apply(MethodVisitor methodVisitor, int i, Label label) {
                        methodVisitor.visitVarInsn(21, i);
                        methodVisitor.visitJumpInsn(153, label);
                        return new StackManipulation.Size(0, 1);
                    }
                },
                LONG { // from class: net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher.2
                    @Override // net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher
                    protected final StackManipulation.Size apply(MethodVisitor methodVisitor, int i, Label label) {
                        methodVisitor.visitVarInsn(22, i);
                        methodVisitor.visitInsn(9);
                        methodVisitor.visitInsn(148);
                        methodVisitor.visitJumpInsn(153, label);
                        return new StackManipulation.Size(0, 4);
                    }
                },
                FLOAT { // from class: net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher.3
                    @Override // net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher
                    protected final StackManipulation.Size apply(MethodVisitor methodVisitor, int i, Label label) {
                        methodVisitor.visitVarInsn(23, i);
                        methodVisitor.visitInsn(11);
                        methodVisitor.visitInsn(149);
                        methodVisitor.visitJumpInsn(153, label);
                        return new StackManipulation.Size(0, 2);
                    }
                },
                DOUBLE { // from class: net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher.4
                    @Override // net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher
                    protected final StackManipulation.Size apply(MethodVisitor methodVisitor, int i, Label label) {
                        methodVisitor.visitVarInsn(24, i);
                        methodVisitor.visitInsn(14);
                        methodVisitor.visitInsn(151);
                        methodVisitor.visitJumpInsn(153, label);
                        return new StackManipulation.Size(0, 4);
                    }
                },
                REFERENCE { // from class: net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher.5
                    @Override // net.bytebuddy.asm.Advice.AssignReturned.DefaultValueSkip.Dispatcher
                    protected final StackManipulation.Size apply(MethodVisitor methodVisitor, int i, Label label) {
                        methodVisitor.visitVarInsn(25, i);
                        methodVisitor.visitJumpInsn(198, label);
                        return new StackManipulation.Size(0, 2);
                    }
                };

                protected abstract StackManipulation.Size apply(MethodVisitor methodVisitor, int i, Label label);

                /* synthetic */ Dispatcher(byte b2) {
                    this();
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ExceptionHandler.class */
        protected static class ExceptionHandler implements StackManipulation {
            private final StackManipulation stackManipulation;
            private final StackManipulation exceptionHandler;
            private final TypeDescription exceptionType;
            private final StackMapFrameHandler.ForPostProcessor stackMapFrameHandler;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.stackManipulation.equals(((ExceptionHandler) obj).stackManipulation) && this.exceptionHandler.equals(((ExceptionHandler) obj).exceptionHandler) && this.exceptionType.equals(((ExceptionHandler) obj).exceptionType) && this.stackMapFrameHandler.equals(((ExceptionHandler) obj).stackMapFrameHandler);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.stackManipulation.hashCode()) * 31) + this.exceptionHandler.hashCode()) * 31) + this.exceptionType.hashCode()) * 31) + this.stackMapFrameHandler.hashCode();
            }

            protected ExceptionHandler(StackManipulation stackManipulation, StackManipulation stackManipulation2, TypeDescription typeDescription, StackMapFrameHandler.ForPostProcessor forPostProcessor) {
                this.stackManipulation = stackManipulation;
                this.exceptionHandler = stackManipulation2;
                this.exceptionType = typeDescription;
                this.stackMapFrameHandler = forPostProcessor;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return this.stackManipulation.isValid() && this.exceptionHandler.isValid();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                Label label = new Label();
                Label label2 = new Label();
                Label label3 = new Label();
                methodVisitor.visitTryCatchBlock(label, label2, label2, this.exceptionType.getInternalName());
                methodVisitor.visitLabel(label);
                StackManipulation.Size apply = this.stackManipulation.apply(methodVisitor, context);
                methodVisitor.visitJumpInsn(167, label3);
                methodVisitor.visitLabel(label2);
                this.stackMapFrameHandler.injectIntermediateFrame(methodVisitor, Collections.singletonList(this.exceptionType));
                StackManipulation.Size aggregate = this.exceptionHandler.apply(methodVisitor, context).aggregate(apply);
                methodVisitor.visitLabel(label3);
                this.stackMapFrameHandler.injectIntermediateFrame(methodVisitor, Collections.emptyList());
                return aggregate;
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ExceptionHandler$Factory.class */
            public interface Factory {
                StackManipulation wrap(StackManipulation stackManipulation, StackManipulation stackManipulation2, StackMapFrameHandler.ForPostProcessor forPostProcessor);

                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ExceptionHandler$Factory$NoOp.class */
                public enum NoOp implements Factory {
                    INSTANCE;

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.ExceptionHandler.Factory
                    public final StackManipulation wrap(StackManipulation stackManipulation, StackManipulation stackManipulation2, StackMapFrameHandler.ForPostProcessor forPostProcessor) {
                        return stackManipulation;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$ExceptionHandler$Factory$Enabled.class */
                public static class Enabled implements Factory {
                    private final TypeDescription exceptionType;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.exceptionType.equals(((Enabled) obj).exceptionType);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.exceptionType.hashCode();
                    }

                    protected Enabled(TypeDescription typeDescription) {
                        this.exceptionType = typeDescription;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.ExceptionHandler.Factory
                    public StackManipulation wrap(StackManipulation stackManipulation, StackManipulation stackManipulation2, StackMapFrameHandler.ForPostProcessor forPostProcessor) {
                        return new ExceptionHandler(stackManipulation, stackManipulation2, this.exceptionType, forPostProcessor);
                    }
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$Handler.class */
        public interface Handler {
            int getIndex();

            StackManipulation resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, TypeDescription.Generic generic, StackManipulation stackManipulation);

            /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$Handler$Factory.class */
            public interface Factory<T extends Annotation> {
                Class<T> getAnnotationType();

                List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends T> loadable);

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$Handler$Factory$Simple.class */
                public static class Simple<S extends Annotation> implements Factory<S> {
                    private final Class<S> type;
                    private final List<Handler> handlers;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.type.equals(((Simple) obj).type) && this.handlers.equals(((Simple) obj).handlers);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.type.hashCode()) * 31) + this.handlers.hashCode();
                    }

                    public Simple(Class<S> cls, List<Handler> list) {
                        this.type = cls;
                        this.handlers = list;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public Class<S> getAnnotationType() {
                        return this.type;
                    }

                    @Override // net.bytebuddy.asm.Advice.AssignReturned.Handler.Factory
                    public List<Handler> make(MethodDescription.InDefinedShape inDefinedShape, boolean z, AnnotationDescription.Loadable<? extends S> loadable) {
                        return this.handlers;
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$AssignReturned$Factory.class */
        public static class Factory implements PostProcessor.Factory {
            private static final MethodDescription.InDefinedShape SKIP_ON_DEFAULT_VALUE = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(AsScalar.class).getDeclaredMethods().filter(ElementMatchers.named("skipOnDefaultValue")).getOnly();
            private final List<? extends Handler.Factory<?>> factories;
            private final ExceptionHandler.Factory exceptionHandlerFactory;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.factories.equals(((Factory) obj).factories) && this.exceptionHandlerFactory.equals(((Factory) obj).exceptionHandlerFactory);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.factories.hashCode()) * 31) + this.exceptionHandlerFactory.hashCode();
            }

            public Factory() {
                this(Arrays.asList(ToArguments.Handler.Factory.INSTANCE, ToAllArguments.Handler.Factory.INSTANCE, ToThis.Handler.Factory.INSTANCE, ToFields.Handler.Factory.INSTANCE, ToReturned.Handler.Factory.INSTANCE, ToThrown.Handler.Factory.INSTANCE), ExceptionHandler.Factory.NoOp.INSTANCE);
            }

            protected Factory(List<? extends Handler.Factory<?>> list, ExceptionHandler.Factory factory) {
                this.factories = list;
                this.exceptionHandlerFactory = factory;
            }

            public Factory with(Class<? extends Annotation> cls, Handler... handlerArr) {
                return with(cls, Arrays.asList(handlerArr));
            }

            public Factory with(Class<? extends Annotation> cls, List<Handler> list) {
                return with(new Handler.Factory.Simple(cls, list));
            }

            public Factory with(Handler.Factory<?> factory) {
                return new Factory(CompoundList.of(this.factories, factory), this.exceptionHandlerFactory);
            }

            public PostProcessor.Factory withSuppressed(Class<? extends Throwable> cls) {
                return withSuppressed(TypeDescription.ForLoadedType.of(cls));
            }

            public PostProcessor.Factory withSuppressed(TypeDescription typeDescription) {
                if (!typeDescription.isAssignableTo(Throwable.class)) {
                    throw new IllegalArgumentException(typeDescription + " is not a throwable type");
                }
                return new Factory(this.factories, new ExceptionHandler.Factory.Enabled(typeDescription));
            }

            @Override // net.bytebuddy.asm.Advice.PostProcessor.Factory
            public PostProcessor make(MethodDescription.InDefinedShape inDefinedShape, boolean z) {
                if (inDefinedShape.getReturnType().represents(Void.TYPE)) {
                    return PostProcessor.NoOp.INSTANCE;
                }
                HashMap hashMap = new HashMap();
                for (Handler.Factory<?> factory : this.factories) {
                    if (hashMap.put(factory.getAnnotationType().getName(), factory) != null) {
                        throw new IllegalStateException("Duplicate registration of handler for " + factory.getAnnotationType());
                    }
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                boolean z2 = false;
                boolean z3 = true;
                for (AnnotationDescription annotationDescription : inDefinedShape.getDeclaredAnnotations()) {
                    if (annotationDescription.getAnnotationType().represents(AsScalar.class)) {
                        z2 = true;
                        z3 = ((Boolean) annotationDescription.getValue(SKIP_ON_DEFAULT_VALUE).resolve(Boolean.class)).booleanValue();
                    } else {
                        Handler.Factory factory2 = (Handler.Factory) hashMap.get(annotationDescription.getAnnotationType().getName());
                        if (factory2 != null && linkedHashMap.put(factory2.getAnnotationType(), factory2.make(inDefinedShape, z, annotationDescription.prepare(factory2.getAnnotationType()))) != null) {
                            throw new IllegalStateException("Duplicate handler registration for " + annotationDescription.getAnnotationType());
                        }
                    }
                }
                if (linkedHashMap.isEmpty()) {
                    return PostProcessor.NoOp.INSTANCE;
                }
                if (!z2 && inDefinedShape.getReturnType().isArray()) {
                    return new ForArray(inDefinedShape.getReturnType(), this.exceptionHandlerFactory, z, linkedHashMap.values());
                }
                return new ForScalar(inDefinedShape.getReturnType(), this.exceptionHandlerFactory, z, z3, linkedHashMap.values());
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$WithCustomMapping.class */
    public static class WithCustomMapping {
        private final PostProcessor.Factory postProcessorFactory;
        private final Delegator delegator;
        private final Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> offsetMappings;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.postProcessorFactory.equals(((WithCustomMapping) obj).postProcessorFactory) && this.delegator.equals(((WithCustomMapping) obj).delegator) && this.offsetMappings.equals(((WithCustomMapping) obj).offsetMappings);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.postProcessorFactory.hashCode()) * 31) + this.delegator.hashCode()) * 31) + this.offsetMappings.hashCode();
        }

        protected WithCustomMapping() {
            this(PostProcessor.NoOp.INSTANCE, Collections.emptyMap(), Delegator.ForStaticInvocation.INSTANCE);
        }

        protected WithCustomMapping(PostProcessor.Factory factory, Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> map, Delegator delegator) {
            this.postProcessorFactory = factory;
            this.offsetMappings = map;
            this.delegator = delegator;
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, @MaybeNull Object obj) {
            return bind(OffsetMapping.ForStackManipulation.Factory.of(cls, obj));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Field field) {
            return bind((Class) cls, (FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, FieldDescription fieldDescription) {
            return bind(new OffsetMapping.ForField.Resolved.Factory(cls, fieldDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Method method, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("A parameter cannot be negative: " + i);
            }
            if (method.getParameterTypes().length <= i) {
                throw new IllegalArgumentException(method + " does not declare a parameter with index " + i);
            }
            return bind((Class) cls, (ParameterDescription) new MethodDescription.ForLoadedMethod(method).getParameters().get(i));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Constructor<?> constructor, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("A parameter cannot be negative: " + i);
            }
            if (constructor.getParameterTypes().length <= i) {
                throw new IllegalArgumentException(constructor + " does not declare a parameter with index " + i);
            }
            return bind((Class) cls, (ParameterDescription) new MethodDescription.ForLoadedConstructor(constructor).getParameters().get(i));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, ParameterDescription parameterDescription) {
            return bind(new OffsetMapping.ForArgument.Resolved.Factory(cls, parameterDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Class<?> cls2) {
            return bind((Class) cls, TypeDescription.ForLoadedType.of(cls2));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, TypeDescription typeDescription) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, typeDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Enum<?> r8) {
            return bind((Class) cls, (EnumerationDescription) new EnumerationDescription.ForLoadedEnumeration(r8));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, EnumerationDescription enumerationDescription) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, enumerationDescription));
        }

        public <T extends Annotation> WithCustomMapping bindSerialized(Class<T> cls, Serializable serializable) {
            return bindSerialized(cls, serializable, serializable.getClass());
        }

        public <T extends Annotation, S extends Serializable> WithCustomMapping bindSerialized(Class<T> cls, S s, Class<? super S> cls2) {
            return bind(OffsetMapping.ForSerializedValue.Factory.of(cls, s, cls2));
        }

        public <T extends Annotation> WithCustomMapping bindProperty(Class<T> cls, String str) {
            return bind(OffsetMapping.ForStackManipulation.OfAnnotationProperty.of(cls, str));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, JavaConstant javaConstant) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, new JavaConstantValue(javaConstant), javaConstant.getTypeDescription().asGenericType()));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, StackManipulation stackManipulation, java.lang.reflect.Type type) {
            return bind(cls, stackManipulation, TypeDefinition.Sort.describe(type));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, StackManipulation stackManipulation, TypeDescription.Generic generic) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, stackManipulation, generic));
        }

        public <T extends Annotation> WithCustomMapping bindLambda(Class<T> cls, Constructor<?> constructor, Class<?> cls2) {
            return bindLambda(cls, new MethodDescription.ForLoadedConstructor(constructor), TypeDescription.ForLoadedType.of(cls2));
        }

        public <T extends Annotation> WithCustomMapping bindLambda(Class<T> cls, Constructor<?> constructor, Class<?> cls2, MethodGraph.Compiler compiler) {
            return bindLambda(cls, new MethodDescription.ForLoadedConstructor(constructor), TypeDescription.ForLoadedType.of(cls2), compiler);
        }

        public <T extends Annotation> WithCustomMapping bindLambda(Class<T> cls, Method method, Class<?> cls2) {
            return bindLambda(cls, new MethodDescription.ForLoadedMethod(method), TypeDescription.ForLoadedType.of(cls2));
        }

        public <T extends Annotation> WithCustomMapping bindLambda(Class<T> cls, Method method, Class<?> cls2, MethodGraph.Compiler compiler) {
            return bindLambda(cls, new MethodDescription.ForLoadedMethod(method), TypeDescription.ForLoadedType.of(cls2), compiler);
        }

        public <T extends Annotation> WithCustomMapping bindLambda(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
            return bindLambda(cls, inDefinedShape, typeDescription, MethodGraph.Compiler.DEFAULT);
        }

        public <T extends Annotation> WithCustomMapping bindLambda(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodGraph.Compiler compiler) {
            if (!typeDescription.isInterface()) {
                throw new IllegalArgumentException(typeDescription + " is not an interface type");
            }
            MethodList filter = compiler.compile((TypeDefinition) typeDescription).listNodes().asMethodList().filter(ElementMatchers.isAbstract());
            if (filter.size() != 1) {
                throw new IllegalArgumentException(typeDescription + " does not define exactly one abstract method: " + filter);
            }
            return bindDynamic(cls, new MethodDescription.Latent(new TypeDescription.Latent("java.lang.invoke.LambdaMetafactory", 1, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Object.class), new TypeDescription.Generic[0]), "metafactory", 9, Collections.emptyList(), JavaType.CALL_SITE.getTypeStub().asGenericType(), Arrays.asList(new ParameterDescription.Token(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().asGenericType()), new ParameterDescription.Token(TypeDescription.ForLoadedType.of(String.class).asGenericType()), new ParameterDescription.Token(JavaType.METHOD_TYPE.getTypeStub().asGenericType()), new ParameterDescription.Token(JavaType.METHOD_TYPE.getTypeStub().asGenericType()), new ParameterDescription.Token(JavaType.METHOD_HANDLE.getTypeStub().asGenericType()), new ParameterDescription.Token(JavaType.METHOD_TYPE.getTypeStub().asGenericType())), Collections.emptyList(), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED), JavaConstant.MethodType.of((MethodDescription) filter.asDefined().getOnly()), JavaConstant.MethodHandle.of(inDefinedShape), JavaConstant.MethodType.of((MethodDescription) filter.asDefined().getOnly()));
        }

        public <T extends Annotation> WithCustomMapping bindDynamic(Class<T> cls, Method method, Object... objArr) {
            return bindDynamic(cls, method, Arrays.asList(objArr));
        }

        public <T extends Annotation> WithCustomMapping bindDynamic(Class<T> cls, Method method, List<?> list) {
            return bindDynamic(cls, new MethodDescription.ForLoadedMethod(method), list);
        }

        public <T extends Annotation> WithCustomMapping bindDynamic(Class<T> cls, Constructor<?> constructor, Object... objArr) {
            return bindDynamic(cls, constructor, Arrays.asList(objArr));
        }

        public <T extends Annotation> WithCustomMapping bindDynamic(Class<T> cls, Constructor<?> constructor, List<?> list) {
            return bindDynamic(cls, new MethodDescription.ForLoadedConstructor(constructor), list);
        }

        public <T extends Annotation> WithCustomMapping bindDynamic(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape, Object... objArr) {
            return bindDynamic(cls, inDefinedShape, Arrays.asList(objArr));
        }

        public <T extends Annotation> WithCustomMapping bindDynamic(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape, List<?> list) {
            List<JavaConstant> wrap = JavaConstant.Simple.wrap(list);
            if (!inDefinedShape.isInvokeBootstrap(TypeList.Explicit.of((List<? extends JavaConstant>) wrap))) {
                throw new IllegalArgumentException("Not a valid bootstrap method " + inDefinedShape + " for " + wrap);
            }
            return bind(new OffsetMapping.ForStackManipulation.OfDynamicInvocation(cls, inDefinedShape, wrap));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, OffsetMapping offsetMapping) {
            return bind(new OffsetMapping.Factory.Simple(cls, offsetMapping));
        }

        public WithCustomMapping bind(OffsetMapping.Factory<?> factory) {
            HashMap hashMap = new HashMap(this.offsetMappings);
            if (!factory.getAnnotationType().isAnnotation()) {
                throw new IllegalArgumentException("Not an annotation type: " + factory.getAnnotationType());
            }
            if (hashMap.put(factory.getAnnotationType(), factory) != null) {
                throw new IllegalArgumentException("Annotation type already mapped: " + factory.getAnnotationType());
            }
            return new WithCustomMapping(this.postProcessorFactory, hashMap, this.delegator);
        }

        public WithCustomMapping bootstrap(Constructor<?> constructor) {
            return bootstrap(new MethodDescription.ForLoadedConstructor(constructor));
        }

        public WithCustomMapping bootstrap(Method method) {
            return bootstrap(new MethodDescription.ForLoadedMethod(method));
        }

        public WithCustomMapping bootstrap(MethodDescription.InDefinedShape inDefinedShape) {
            return new WithCustomMapping(this.postProcessorFactory, this.offsetMappings, Delegator.ForDynamicInvocation.of(inDefinedShape));
        }

        public WithCustomMapping with(PostProcessor.Factory factory) {
            return new WithCustomMapping(new PostProcessor.Factory.Compound(this.postProcessorFactory, factory), this.offsetMappings, this.delegator);
        }

        public Advice to(Class<?> cls) {
            return to(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
        }

        public Advice to(Class<?> cls, ClassFileLocator classFileLocator) {
            return to(TypeDescription.ForLoadedType.of(cls), classFileLocator);
        }

        public Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            return Advice.to(typeDescription, this.postProcessorFactory, classFileLocator, new ArrayList(this.offsetMappings.values()), this.delegator);
        }

        public Advice to(Class<?> cls, Class<?> cls2) {
            ClassLoader classLoader = cls.getClassLoader();
            ClassLoader classLoader2 = cls2.getClassLoader();
            return to(cls, cls2, classLoader == classLoader2 ? ClassFileLocator.ForClassLoader.of(classLoader) : new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader), ClassFileLocator.ForClassLoader.of(classLoader2)));
        }

        public Advice to(Class<?> cls, Class<?> cls2, ClassFileLocator classFileLocator) {
            return to(TypeDescription.ForLoadedType.of(cls), TypeDescription.ForLoadedType.of(cls2), classFileLocator);
        }

        public Advice to(TypeDescription typeDescription, TypeDescription typeDescription2) {
            return to(typeDescription, typeDescription2, ClassFileLocator.NoOp.INSTANCE);
        }

        public Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator) {
            return Advice.to(typeDescription, typeDescription2, this.postProcessorFactory, classFileLocator, new ArrayList(this.offsetMappings.values()), this.delegator);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$NoExceptionHandler.class */
    private static class NoExceptionHandler extends Throwable {
        private static final long serialVersionUID = 1;
        private static final TypeDescription DESCRIPTION = TypeDescription.ForLoadedType.of(NoExceptionHandler.class);

        private NoExceptionHandler() {
            throw new UnsupportedOperationException("This class only serves as a marker type and should not be instantiated");
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OnDefaultValue.class */
    public static final class OnDefaultValue {
        private OnDefaultValue() {
            throw new UnsupportedOperationException("This class only serves as a marker type and should not be instantiated");
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/Advice$OnNonDefaultValue.class */
    public static final class OnNonDefaultValue {
        private OnNonDefaultValue() {
            throw new UnsupportedOperationException("This class only serves as a marker type and should not be instantiated");
        }
    }
}
