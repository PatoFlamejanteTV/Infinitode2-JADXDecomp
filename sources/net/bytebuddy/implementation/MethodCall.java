package net.bytebuddy.implementation;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall.class */
public class MethodCall implements Implementation.Composable {
    protected final MethodLocator.Factory methodLocator;
    protected final TargetHandler.Factory targetHandler;
    protected final List<ArgumentLoader.Factory> argumentLoaders;
    protected final MethodInvoker.Factory methodInvoker;
    protected final TerminationHandler.Factory terminationHandler;
    protected final Assigner assigner;
    protected final Assigner.Typing typing;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.typing.equals(((MethodCall) obj).typing) && this.methodLocator.equals(((MethodCall) obj).methodLocator) && this.targetHandler.equals(((MethodCall) obj).targetHandler) && this.argumentLoaders.equals(((MethodCall) obj).argumentLoaders) && this.methodInvoker.equals(((MethodCall) obj).methodInvoker) && this.terminationHandler.equals(((MethodCall) obj).terminationHandler) && this.assigner.equals(((MethodCall) obj).assigner);
    }

    public int hashCode() {
        return (((((((((((((getClass().hashCode() * 31) + this.methodLocator.hashCode()) * 31) + this.targetHandler.hashCode()) * 31) + this.argumentLoaders.hashCode()) * 31) + this.methodInvoker.hashCode()) * 31) + this.terminationHandler.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.typing.hashCode();
    }

    protected MethodCall(MethodLocator.Factory factory, TargetHandler.Factory factory2, List<ArgumentLoader.Factory> list, MethodInvoker.Factory factory3, TerminationHandler.Factory factory4, Assigner assigner, Assigner.Typing typing) {
        this.methodLocator = factory;
        this.targetHandler = factory2;
        this.argumentLoaders = list;
        this.methodInvoker = factory3;
        this.terminationHandler = factory4;
        this.assigner = assigner;
        this.typing = typing;
    }

    public static WithoutSpecifiedTarget invoke(Method method) {
        return invoke(new MethodDescription.ForLoadedMethod(method));
    }

    public static WithoutSpecifiedTarget invoke(Constructor<?> constructor) {
        return invoke(new MethodDescription.ForLoadedConstructor(constructor));
    }

    public static WithoutSpecifiedTarget invoke(MethodDescription methodDescription) {
        return invoke(new MethodLocator.ForExplicitMethod(methodDescription));
    }

    public static WithoutSpecifiedTarget invoke(ElementMatcher<? super MethodDescription> elementMatcher) {
        return invoke(elementMatcher, MethodGraph.Compiler.DEFAULT);
    }

    public static WithoutSpecifiedTarget invoke(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
        return invoke(new MethodLocator.ForElementMatcher.Factory(elementMatcher, compiler));
    }

    public static WithoutSpecifiedTarget invoke(MethodLocator.Factory factory) {
        return new WithoutSpecifiedTarget(factory);
    }

    public static WithoutSpecifiedTarget invokeSelf() {
        return new WithoutSpecifiedTarget(MethodLocator.ForInstrumentedMethod.INSTANCE);
    }

    public static MethodCall invokeSuper() {
        return invokeSelf().onSuper();
    }

    public static Implementation.Composable call(Callable<?> callable) {
        try {
            return invoke(Callable.class.getMethod("call", new Class[0])).on((WithoutSpecifiedTarget) callable, (Class<? super WithoutSpecifiedTarget>) Callable.class).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Callable::call method", e);
        }
    }

    public static Implementation.Composable run(Runnable runnable) {
        try {
            return invoke(Runnable.class.getMethod("run", new Class[0])).on((WithoutSpecifiedTarget) runnable, (Class<? super WithoutSpecifiedTarget>) Runnable.class).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Runnable::run method", e);
        }
    }

    public static MethodCall construct(Constructor<?> constructor) {
        return construct(new MethodDescription.ForLoadedConstructor(constructor));
    }

    public static MethodCall construct(MethodDescription methodDescription) {
        if (!methodDescription.isConstructor()) {
            throw new IllegalArgumentException("Not a constructor: " + methodDescription);
        }
        return new MethodCall(new MethodLocator.ForExplicitMethod(methodDescription), TargetHandler.ForConstructingInvocation.Factory.INSTANCE, Collections.emptyList(), MethodInvoker.ForContextualInvocation.Factory.INSTANCE, TerminationHandler.Simple.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
    }

    public MethodCall with(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(ArgumentLoader.ForStackManipulation.of(obj));
        }
        return with(arrayList);
    }

    public MethodCall with(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription typeDescription : typeDescriptionArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(ClassConstant.of(typeDescription), Class.class));
        }
        return with(arrayList);
    }

    public MethodCall with(EnumerationDescription... enumerationDescriptionArr) {
        ArrayList arrayList = new ArrayList(enumerationDescriptionArr.length);
        for (EnumerationDescription enumerationDescription : enumerationDescriptionArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(FieldAccess.forEnumeration(enumerationDescription), enumerationDescription.getEnumerationType()));
        }
        return with(arrayList);
    }

    public MethodCall with(JavaConstant... javaConstantArr) {
        ArrayList arrayList = new ArrayList(javaConstantArr.length);
        for (JavaConstant javaConstant : javaConstantArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(new JavaConstantValue(javaConstant), javaConstant.getTypeDescription()));
        }
        return with(arrayList);
    }

    public MethodCall withReference(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            arrayList.add(obj == null ? ArgumentLoader.ForNullConstant.INSTANCE : new ArgumentLoader.ForInstance.Factory(obj));
        }
        return with(arrayList);
    }

    public MethodCall withArgument(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            if (i < 0) {
                throw new IllegalArgumentException("Negative index: " + i);
            }
            arrayList.add(new ArgumentLoader.ForMethodParameter.Factory(i));
        }
        return with(arrayList);
    }

    public MethodCall withAllArguments() {
        return with(ArgumentLoader.ForMethodParameter.OfInstrumentedMethod.INSTANCE);
    }

    public MethodCall withArgumentArray() {
        return with(ArgumentLoader.ForMethodParameterArray.ForInstrumentedMethod.INSTANCE);
    }

    public MethodCall withArgumentArrayElements(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
        }
        return with(new ArgumentLoader.ForMethodParameterArrayElement.OfInvokedMethod(i));
    }

    public MethodCall withArgumentArrayElements(int i, int i2) {
        return withArgumentArrayElements(i, 0, i2);
    }

    public MethodCall withArgumentArrayElements(int i, int i2, int i3) {
        if (i < 0) {
            throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("An array index cannot be negative: " + i2);
        }
        if (i3 == 0) {
            return this;
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("Size cannot be negative: " + i3);
        }
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            arrayList.add(new ArgumentLoader.ForMethodParameterArrayElement.OfParameter(i, i2 + i4));
        }
        return with(arrayList);
    }

    public MethodCall withThis() {
        return with(ArgumentLoader.ForThisReference.Factory.INSTANCE);
    }

    public MethodCall withOwnType() {
        return with(ArgumentLoader.ForInstrumentedType.Factory.INSTANCE);
    }

    public MethodCall withField(String... strArr) {
        return withField(FieldLocator.ForClassHierarchy.Factory.INSTANCE, strArr);
    }

    public MethodCall withField(FieldLocator.Factory factory, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(new ArgumentLoader.ForField.Factory(str, factory));
        }
        return with(arrayList);
    }

    public MethodCall withMethodCall(MethodCall methodCall) {
        return with(new ArgumentLoader.ForMethodCall.Factory(methodCall));
    }

    public MethodCall with(StackManipulation stackManipulation, Type type) {
        return with(stackManipulation, TypeDefinition.Sort.describe(type));
    }

    public MethodCall with(StackManipulation stackManipulation, TypeDefinition typeDefinition) {
        return with(new ArgumentLoader.ForStackManipulation(stackManipulation, typeDefinition));
    }

    public MethodCall with(ArgumentLoader.Factory... factoryArr) {
        return with(Arrays.asList(factoryArr));
    }

    public MethodCall with(List<? extends ArgumentLoader.Factory> list) {
        return new MethodCall(this.methodLocator, this.targetHandler, CompoundList.of((List) this.argumentLoaders, (List) list), this.methodInvoker, this.terminationHandler, this.assigner, this.typing);
    }

    public FieldSetting setsField(Field field) {
        return setsField(new FieldDescription.ForLoadedField(field));
    }

    public FieldSetting setsField(FieldDescription fieldDescription) {
        return new FieldSetting(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, new TerminationHandler.FieldSetting.Explicit(fieldDescription), this.assigner, this.typing));
    }

    public FieldSetting setsField(ElementMatcher<? super FieldDescription> elementMatcher) {
        return new FieldSetting(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, new TerminationHandler.FieldSetting.Implicit(elementMatcher), this.assigner, this.typing));
    }

    public Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
        return new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, this.terminationHandler, assigner, typing);
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, TerminationHandler.Simple.DROPPING, this.assigner, this.typing), implementation);
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public Implementation.Composable andThen(Implementation.Composable composable) {
        return new Implementation.Compound.Composable(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, TerminationHandler.Simple.DROPPING, this.assigner, this.typing), composable);
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        Iterator<ArgumentLoader.Factory> it = this.argumentLoaders.iterator();
        while (it.hasNext()) {
            instrumentedType = it.next().prepare(instrumentedType);
        }
        return this.targetHandler.prepare(instrumentedType);
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(target, this.terminationHandler.make(target.getInstrumentedType()));
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodLocator.class */
    public interface MethodLocator {

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodLocator$Factory.class */
        public interface Factory {
            MethodLocator make(TypeDescription typeDescription);
        }

        MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodLocator$ForInstrumentedMethod.class */
        public enum ForInstrumentedMethod implements MethodLocator, Factory {
            INSTANCE;

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator.Factory
            public final MethodLocator make(TypeDescription typeDescription) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator
            public final MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                return methodDescription;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodLocator$ForExplicitMethod.class */
        public static class ForExplicitMethod implements MethodLocator, Factory {
            private final MethodDescription methodDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForExplicitMethod) obj).methodDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.methodDescription.hashCode();
            }

            protected ForExplicitMethod(MethodDescription methodDescription) {
                this.methodDescription = methodDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator.Factory
            public MethodLocator make(TypeDescription typeDescription) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator
            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                return this.methodDescription;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodLocator$ForElementMatcher.class */
        public static class ForElementMatcher implements MethodLocator {
            private final TypeDescription instrumentedType;
            private final ElementMatcher<? super MethodDescription> matcher;
            private final MethodGraph.Compiler methodGraphCompiler;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForElementMatcher) obj).instrumentedType) && this.matcher.equals(((ForElementMatcher) obj).matcher) && this.methodGraphCompiler.equals(((ForElementMatcher) obj).methodGraphCompiler);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.matcher.hashCode()) * 31) + this.methodGraphCompiler.hashCode();
            }

            protected ForElementMatcher(TypeDescription typeDescription, ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
                this.instrumentedType = typeDescription;
                this.matcher = elementMatcher;
                this.methodGraphCompiler = compiler;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodLocator
            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                List filter;
                TypeDescription.Generic superClass = this.instrumentedType.getSuperClass();
                if (superClass == null) {
                    filter = Collections.emptyList();
                } else {
                    filter = superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(this.matcher));
                }
                List of = CompoundList.of(filter, this.instrumentedType.getDeclaredMethods().filter(ElementMatchers.not(ElementMatchers.isVirtual()).and(this.matcher)), this.methodGraphCompiler.compile((TypeDefinition) typeDescription, this.instrumentedType).listNodes().asMethodList().filter(this.matcher));
                if (of.size() == 1) {
                    return (MethodDescription) of.get(0);
                }
                throw new IllegalStateException(this.instrumentedType + " does not define exactly one virtual method or constructor for " + this.matcher + " but contained " + of.size() + " candidates: " + of);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodLocator$ForElementMatcher$Factory.class */
            public static class Factory implements Factory {
                private final ElementMatcher<? super MethodDescription> matcher;
                private final MethodGraph.Compiler methodGraphCompiler;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Factory) obj).matcher) && this.methodGraphCompiler.equals(((Factory) obj).methodGraphCompiler);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + this.methodGraphCompiler.hashCode();
                }

                public Factory(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
                    this.matcher = elementMatcher;
                    this.methodGraphCompiler = compiler;
                }

                @Override // net.bytebuddy.implementation.MethodCall.MethodLocator.Factory
                public MethodLocator make(TypeDescription typeDescription) {
                    return new ForElementMatcher(typeDescription, this.matcher, this.methodGraphCompiler);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader.class */
    public interface ArgumentLoader {

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ArgumentProvider.class */
        public interface ArgumentProvider {
            List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$Factory.class */
        public interface Factory extends InstrumentedType.Prepareable {
            ArgumentProvider make(Implementation.Target target);
        }

        StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForNullConstant.class */
        public enum ForNullConstant implements ArgumentLoader, ArgumentProvider, Factory {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
            public final ArgumentProvider make(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
            public final List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public final StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                if (parameterDescription.getType().isPrimitive()) {
                    throw new IllegalStateException("Cannot assign null to " + parameterDescription);
                }
                return NullConstant.INSTANCE;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForThisReference.class */
        public static class ForThisReference implements ArgumentLoader, ArgumentProvider {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForThisReference) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            public ForThisReference(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                if (methodDescription.isStatic()) {
                    throw new IllegalStateException(methodDescription + " is static and cannot supply an invoker instance");
                }
                return Collections.singletonList(this);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.loadThis(), assigner.assign(this.instrumentedType.asGenericType(), parameterDescription.getType(), typing));
                if (!compound.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.instrumentedType + " to " + parameterDescription);
                }
                return compound;
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForThisReference$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public final ArgumentProvider make(Implementation.Target target) {
                    return new ForThisReference(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForInstrumentedType.class */
        public static class ForInstrumentedType implements ArgumentLoader, ArgumentProvider {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForInstrumentedType) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            public ForInstrumentedType(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(ClassConstant.of(this.instrumentedType), assigner.assign(TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Class.class), parameterDescription.getType(), typing));
                if (!compound.isValid()) {
                    throw new IllegalStateException("Cannot assign Class value to " + parameterDescription);
                }
                return compound;
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForInstrumentedType$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public final ArgumentProvider make(Implementation.Target target) {
                    return new ForInstrumentedType(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameter.class */
        public static class ForMethodParameter implements ArgumentLoader {
            private final int index;
            private final MethodDescription instrumentedMethod;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.index == ((ForMethodParameter) obj).index && this.instrumentedMethod.equals(((ForMethodParameter) obj).instrumentedMethod);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.index) * 31) + this.instrumentedMethod.hashCode();
            }

            public ForMethodParameter(int i, MethodDescription methodDescription) {
                this.index = i;
                this.instrumentedMethod = methodDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                ParameterDescription parameterDescription2 = (ParameterDescription) this.instrumentedMethod.getParameters().get(this.index);
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription2), assigner.assign(parameterDescription2.getType(), parameterDescription.getType(), typing));
                if (!compound.isValid()) {
                    throw new IllegalStateException("Cannot assign " + parameterDescription2 + " to " + parameterDescription + " for " + this.instrumentedMethod);
                }
                return compound;
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameter$OfInstrumentedMethod.class */
            protected enum OfInstrumentedMethod implements ArgumentProvider, Factory {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public final ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
                public final List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                    Iterator it = methodDescription.getParameters().iterator();
                    while (it.hasNext()) {
                        arrayList.add(new ForMethodParameter(((ParameterDescription) it.next()).getIndex(), methodDescription));
                    }
                    return arrayList;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameter$Factory.class */
            public static class Factory implements ArgumentProvider, Factory {
                private final int index;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Factory) obj).index;
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.index;
                }

                public Factory(int i) {
                    this.index = i;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (this.index >= methodDescription.getParameters().size()) {
                        throw new IllegalStateException(methodDescription + " does not have a parameter with index " + this.index + ", " + methodDescription.getParameters().size() + " defined");
                    }
                    return Collections.singletonList(new ForMethodParameter(this.index, methodDescription));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameterArray.class */
        public static class ForMethodParameterArray implements ArgumentLoader {
            private final ParameterList<?> parameters;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.parameters.equals(((ForMethodParameterArray) obj).parameters);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.parameters.hashCode();
            }

            public ForMethodParameterArray(ParameterList<?> parameterList) {
                this.parameters = parameterList;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                TypeDescription.Generic componentType;
                if (parameterDescription.getType().represents(Object.class)) {
                    componentType = TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Object.class);
                } else if (parameterDescription.getType().isArray()) {
                    componentType = parameterDescription.getType().getComponentType();
                } else {
                    throw new IllegalStateException("Cannot set method parameter array for non-array type: " + parameterDescription);
                }
                ArrayList arrayList = new ArrayList(this.parameters.size());
                Iterator it = this.parameters.iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription2 = (ParameterDescription) it.next();
                    StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription2), assigner.assign(parameterDescription2.getType(), componentType, typing));
                    if (compound.isValid()) {
                        arrayList.add(compound);
                    } else {
                        throw new IllegalStateException("Cannot assign " + parameterDescription2 + " to " + componentType);
                    }
                }
                return new StackManipulation.Compound(ArrayFactory.forType(componentType).withValues(arrayList));
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameterArray$ForInstrumentedMethod.class */
            public enum ForInstrumentedMethod implements ArgumentProvider, Factory {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public final ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
                public final List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    return Collections.singletonList(new ForMethodParameterArray(methodDescription.getParameters()));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameterArrayElement.class */
        public static class ForMethodParameterArrayElement implements ArgumentLoader {
            private final ParameterDescription parameterDescription;
            private final int index;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.index == ((ForMethodParameterArrayElement) obj).index && this.parameterDescription.equals(((ForMethodParameterArrayElement) obj).parameterDescription);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.parameterDescription.hashCode()) * 31) + this.index;
            }

            public ForMethodParameterArrayElement(ParameterDescription parameterDescription, int i) {
                this.parameterDescription = parameterDescription;
                this.index = i;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(this.parameterDescription), IntegerConstant.forValue(this.index), ArrayAccess.of(this.parameterDescription.getType().getComponentType()).load(), assigner.assign(this.parameterDescription.getType().getComponentType(), parameterDescription.getType(), typing));
                if (!compound.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.parameterDescription.getType().getComponentType() + " to " + parameterDescription);
                }
                return compound;
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameterArrayElement$OfParameter.class */
            public static class OfParameter implements ArgumentProvider, Factory {
                private final int index;
                private final int arrayIndex;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((OfParameter) obj).index && this.arrayIndex == ((OfParameter) obj).arrayIndex;
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.index) * 31) + this.arrayIndex;
                }

                public OfParameter(int i, int i2) {
                    this.index = i;
                    this.arrayIndex = i2;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (methodDescription.getParameters().size() <= this.index) {
                        throw new IllegalStateException(methodDescription + " does not declare a parameter with index " + this.index + ", " + methodDescription.getParameters().size() + " defined");
                    }
                    if (!((ParameterDescription) methodDescription.getParameters().get(this.index)).getType().isArray()) {
                        throw new IllegalStateException("Cannot access an item from non-array parameter " + methodDescription.getParameters().get(this.index) + " at index " + this.index);
                    }
                    return Collections.singletonList(new ForMethodParameterArrayElement((ParameterDescription) methodDescription.getParameters().get(this.index), this.arrayIndex));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodParameterArrayElement$OfInvokedMethod.class */
            public static class OfInvokedMethod implements ArgumentProvider, Factory {
                private final int index;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((OfInvokedMethod) obj).index;
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.index;
                }

                public OfInvokedMethod(int i) {
                    this.index = i;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (methodDescription.getParameters().size() <= this.index) {
                        throw new IllegalStateException(methodDescription + " does not declare a parameter with index " + this.index + ", " + methodDescription.getParameters().size() + " defined");
                    }
                    if (!((ParameterDescription) methodDescription.getParameters().get(this.index)).getType().isArray()) {
                        throw new IllegalStateException("Cannot access an item from non-array parameter " + methodDescription.getParameters().get(this.index) + " at index " + this.index);
                    }
                    ArrayList arrayList = new ArrayList(methodDescription2.getParameters().size());
                    for (int i = 0; i < methodDescription2.getParameters().size(); i++) {
                        arrayList.add(new ForMethodParameterArrayElement((ParameterDescription) methodDescription.getParameters().get(this.index), i));
                    }
                    return arrayList;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForInstance.class */
        public static class ForInstance implements ArgumentLoader, ArgumentProvider {
            private final FieldDescription fieldDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForInstance) obj).fieldDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
            }

            public ForInstance(FieldDescription fieldDescription) {
                this.fieldDescription = fieldDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(FieldAccess.forField(this.fieldDescription).read(), assigner.assign(this.fieldDescription.getType(), parameterDescription.getType(), typing));
                if (!compound.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.fieldDescription.getType() + " to " + parameterDescription);
                }
                return compound;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForInstance$Factory.class */
            public static class Factory implements Factory {
                private static final String FIELD_PREFIX = "methodCall";
                private final Object value;

                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
                private final String name;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value.equals(((Factory) obj).value);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.value.hashCode();
                }

                public Factory(Object obj) {
                    this.value = obj;
                    this.name = "methodCall$" + RandomString.hashOf(obj);
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withAuxiliaryField(new FieldDescription.Token(this.name, 4105, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(this.value.getClass())), this.value);
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public ArgumentProvider make(Implementation.Target target) {
                    return new ForInstance((FieldDescription) target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named(this.name)).getOnly());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForField.class */
        public static class ForField implements ArgumentLoader {
            private final FieldDescription fieldDescription;
            private final MethodDescription instrumentedMethod;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForField) obj).fieldDescription) && this.instrumentedMethod.equals(((ForField) obj).instrumentedMethod);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.fieldDescription.hashCode()) * 31) + this.instrumentedMethod.hashCode();
            }

            public ForField(FieldDescription fieldDescription, MethodDescription methodDescription) {
                this.fieldDescription = fieldDescription;
                this.instrumentedMethod = methodDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                if (!this.fieldDescription.isStatic() && this.instrumentedMethod.isStatic()) {
                    throw new IllegalStateException("Cannot access non-static " + this.fieldDescription + " from " + this.instrumentedMethod);
                }
                StackManipulation[] stackManipulationArr = new StackManipulation[3];
                stackManipulationArr[0] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                stackManipulationArr[2] = assigner.assign(this.fieldDescription.getType(), parameterDescription.getType(), typing);
                StackManipulation.Compound compound = new StackManipulation.Compound(stackManipulationArr);
                if (!compound.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.fieldDescription + " to " + parameterDescription);
                }
                return compound;
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForField$ArgumentProvider.class */
            protected static class ArgumentProvider implements ArgumentProvider {
                private final FieldDescription fieldDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ArgumentProvider) obj).fieldDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
                }

                protected ArgumentProvider(FieldDescription fieldDescription) {
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    return Collections.singletonList(new ForField(this.fieldDescription, methodDescription));
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForField$Factory.class */
            public static class Factory implements Factory {
                private final String name;
                private final FieldLocator.Factory fieldLocatorFactory;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.name.equals(((Factory) obj).name) && this.fieldLocatorFactory.equals(((Factory) obj).fieldLocatorFactory);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.name.hashCode()) * 31) + this.fieldLocatorFactory.hashCode();
                }

                public Factory(String str, FieldLocator.Factory factory) {
                    this.name = str;
                    this.fieldLocatorFactory = factory;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public ArgumentProvider make(Implementation.Target target) {
                    FieldLocator.Resolution locate = this.fieldLocatorFactory.make(target.getInstrumentedType()).locate(this.name);
                    if (!locate.isResolved()) {
                        throw new IllegalStateException("Could not locate field '" + this.name + "' on " + target.getInstrumentedType());
                    }
                    return new ArgumentProvider(locate.getField());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodCall.class */
        public static class ForMethodCall implements ArgumentLoader {
            private final Appender appender;
            private final MethodDescription methodDescription;
            private final MethodDescription instrumentedMethod;
            private final TargetHandler.Resolved targetHandler;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.appender.equals(((ForMethodCall) obj).appender) && this.methodDescription.equals(((ForMethodCall) obj).methodDescription) && this.instrumentedMethod.equals(((ForMethodCall) obj).instrumentedMethod) && this.targetHandler.equals(((ForMethodCall) obj).targetHandler);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.appender.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.instrumentedMethod.hashCode()) * 31) + this.targetHandler.hashCode();
            }

            public ForMethodCall(Appender appender, MethodDescription methodDescription, MethodDescription methodDescription2, TargetHandler.Resolved resolved) {
                this.appender = appender;
                this.methodDescription = methodDescription;
                this.instrumentedMethod = methodDescription2;
                this.targetHandler = resolved;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                TypeDescription.Generic returnType;
                StackManipulation[] stackManipulationArr = new StackManipulation[2];
                stackManipulationArr[0] = this.appender.toStackManipulation(this.instrumentedMethod, this.methodDescription, this.targetHandler);
                if (this.methodDescription.isConstructor()) {
                    returnType = this.methodDescription.getDeclaringType().asGenericType();
                } else {
                    returnType = this.methodDescription.getReturnType();
                }
                stackManipulationArr[1] = assigner.assign(returnType, parameterDescription.getType(), typing);
                StackManipulation.Compound compound = new StackManipulation.Compound(stackManipulationArr);
                if (!compound.isValid()) {
                    throw new IllegalStateException("Cannot assign return type of " + this.methodDescription + " to " + parameterDescription);
                }
                return compound;
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodCall$ArgumentProvider.class */
            protected static class ArgumentProvider implements ArgumentProvider {
                private final Appender appender;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.appender.equals(((ArgumentProvider) obj).appender);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.appender.hashCode();
                }

                protected ArgumentProvider(Appender appender) {
                    this.appender = appender;
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    TargetHandler.Resolved resolve = this.appender.targetHandler.resolve(methodDescription);
                    return Collections.singletonList(new ForMethodCall(this.appender, this.appender.toInvokedMethod(methodDescription, resolve), methodDescription, resolve));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForMethodCall$Factory.class */
            protected static class Factory implements Factory {
                private final MethodCall methodCall;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodCall.equals(((Factory) obj).methodCall);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.methodCall.hashCode();
                }

                public Factory(MethodCall methodCall) {
                    this.methodCall = methodCall;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return this.methodCall.prepare(instrumentedType);
                }

                @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
                public ArgumentProvider make(Implementation.Target target) {
                    MethodCall methodCall = this.methodCall;
                    methodCall.getClass();
                    return new ArgumentProvider(new Appender(target, TerminationHandler.Simple.IGNORING));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$ArgumentLoader$ForStackManipulation.class */
        public static class ForStackManipulation implements ArgumentLoader, ArgumentProvider, Factory {
            private final StackManipulation stackManipulation;
            private final TypeDefinition typeDefinition;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.stackManipulation.equals(((ForStackManipulation) obj).stackManipulation) && this.typeDefinition.equals(((ForStackManipulation) obj).typeDefinition);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.stackManipulation.hashCode()) * 31) + this.typeDefinition.hashCode();
            }

            public ForStackManipulation(StackManipulation stackManipulation, Type type) {
                this(stackManipulation, TypeDefinition.Sort.describe(type));
            }

            public ForStackManipulation(StackManipulation stackManipulation, TypeDefinition typeDefinition) {
                this.stackManipulation = stackManipulation;
                this.typeDefinition = typeDefinition;
            }

            public static Factory of(@MaybeNull Object obj) {
                if (obj == null) {
                    return ForNullConstant.INSTANCE;
                }
                if (obj instanceof Boolean) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Boolean) obj).booleanValue()), Boolean.TYPE);
                }
                if (obj instanceof Byte) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Byte) obj).byteValue()), Byte.TYPE);
                }
                if (obj instanceof Short) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Short) obj).shortValue()), Short.TYPE);
                }
                if (obj instanceof Character) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Character) obj).charValue()), Character.TYPE);
                }
                if (obj instanceof Integer) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Integer) obj).intValue()), Integer.TYPE);
                }
                if (obj instanceof Long) {
                    return new ForStackManipulation(LongConstant.forValue(((Long) obj).longValue()), Long.TYPE);
                }
                if (obj instanceof Float) {
                    return new ForStackManipulation(FloatConstant.forValue(((Float) obj).floatValue()), Float.TYPE);
                }
                if (obj instanceof Double) {
                    return new ForStackManipulation(DoubleConstant.forValue(((Double) obj).doubleValue()), Double.TYPE);
                }
                if (obj instanceof String) {
                    return new ForStackManipulation(new TextConstant((String) obj), String.class);
                }
                if (obj instanceof Class) {
                    return new ForStackManipulation(ClassConstant.of(TypeDescription.ForLoadedType.of((Class) obj)), Class.class);
                }
                if (obj instanceof TypeDescription) {
                    return new ForStackManipulation(ClassConstant.of((TypeDescription) obj), Class.class);
                }
                if (obj instanceof Enum) {
                    EnumerationDescription.ForLoadedEnumeration forLoadedEnumeration = new EnumerationDescription.ForLoadedEnumeration((Enum) obj);
                    return new ForStackManipulation(FieldAccess.forEnumeration(forLoadedEnumeration), forLoadedEnumeration.getEnumerationType());
                }
                if (obj instanceof EnumerationDescription) {
                    return new ForStackManipulation(FieldAccess.forEnumeration((EnumerationDescription) obj), ((EnumerationDescription) obj).getEnumerationType());
                }
                if (JavaType.METHOD_HANDLE.isInstance(obj)) {
                    return new ForStackManipulation(new JavaConstantValue(JavaConstant.MethodHandle.ofLoaded(obj)), JavaType.METHOD_HANDLE.getTypeStub());
                }
                if (JavaType.METHOD_TYPE.isInstance(obj)) {
                    return new ForStackManipulation(new JavaConstantValue(JavaConstant.MethodType.ofLoaded(obj)), JavaType.METHOD_TYPE.getTypeStub());
                }
                if (obj instanceof JavaConstant) {
                    return new ForStackManipulation(new JavaConstantValue((JavaConstant) obj), ((JavaConstant) obj).getTypeDescription());
                }
                return new ForInstance.Factory(obj);
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.Factory
            public ArgumentProvider make(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader.ArgumentProvider
            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            @Override // net.bytebuddy.implementation.MethodCall.ArgumentLoader
            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation assign = assigner.assign(this.typeDefinition.asGenericType(), parameterDescription.getType(), typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + parameterDescription + " to " + this.typeDefinition);
                }
                return new StackManipulation.Compound(this.stackManipulation, assign);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler.class */
    public interface TargetHandler {

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$Factory.class */
        public interface Factory extends InstrumentedType.Prepareable {
            TargetHandler make(Implementation.Target target);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$Resolved.class */
        public interface Resolved {
            TypeDescription getTypeDescription();

            StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing);
        }

        Resolved resolve(MethodDescription methodDescription);

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$Simple.class */
        public static class Simple implements TargetHandler, Factory, Resolved {
            private final TypeDescription typeDescription;
            private final StackManipulation stackManipulation;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Simple) obj).typeDescription) && this.stackManipulation.equals(((Simple) obj).stackManipulation);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.typeDescription.hashCode()) * 31) + this.stackManipulation.hashCode();
            }

            protected Simple(TypeDescription typeDescription, StackManipulation stackManipulation) {
                this.typeDescription = typeDescription;
                this.stackManipulation = stackManipulation;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Factory
            public TargetHandler make(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public Resolved resolve(MethodDescription methodDescription) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public TypeDescription getTypeDescription() {
                return this.typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                return this.stackManipulation;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForSelfOrStaticInvocation.class */
        public static class ForSelfOrStaticInvocation implements TargetHandler {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForSelfOrStaticInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            protected ForSelfOrStaticInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public Resolved resolve(MethodDescription methodDescription) {
                return new Resolved(this.instrumentedType, methodDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForSelfOrStaticInvocation$Resolved.class */
            protected static class Resolved implements Resolved {
                private final TypeDescription instrumentedType;
                private final MethodDescription instrumentedMethod;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Resolved) obj).instrumentedType) && this.instrumentedMethod.equals(((Resolved) obj).instrumentedMethod);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.instrumentedMethod.hashCode();
                }

                protected Resolved(TypeDescription typeDescription, MethodDescription methodDescription) {
                    this.instrumentedType = typeDescription;
                    this.instrumentedMethod = methodDescription;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
                public TypeDescription getTypeDescription() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming declaring type for type member.")
                public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    if (this.instrumentedMethod.isStatic() && !methodDescription.isStatic() && !methodDescription.isConstructor()) {
                        throw new IllegalStateException("Cannot invoke " + methodDescription + " from " + this.instrumentedMethod);
                    }
                    if (methodDescription.isConstructor() && (!this.instrumentedMethod.isConstructor() || (!this.instrumentedType.equals(methodDescription.getDeclaringType().asErasure()) && (this.instrumentedType.getSuperClass() == null || !this.instrumentedType.getSuperClass().asErasure().equals(methodDescription.getDeclaringType().asErasure()))))) {
                        throw new IllegalStateException("Cannot invoke " + methodDescription + " from " + this.instrumentedMethod + " in " + this.instrumentedType);
                    }
                    StackManipulation[] stackManipulationArr = new StackManipulation[2];
                    stackManipulationArr[0] = methodDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                    stackManipulationArr[1] = methodDescription.isConstructor() ? Duplication.SINGLE : StackManipulation.Trivial.INSTANCE;
                    return new StackManipulation.Compound(stackManipulationArr);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForSelfOrStaticInvocation$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Factory
                public final TargetHandler make(Implementation.Target target) {
                    return new ForSelfOrStaticInvocation(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForConstructingInvocation.class */
        public static class ForConstructingInvocation implements TargetHandler, Resolved {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForConstructingInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            protected ForConstructingInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public Resolved resolve(MethodDescription methodDescription) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public TypeDescription getTypeDescription() {
                return this.instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                return new StackManipulation.Compound(TypeCreation.of(methodDescription.getDeclaringType().asErasure()), Duplication.SINGLE);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForConstructingInvocation$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Factory
                public final TargetHandler make(Implementation.Target target) {
                    return new ForConstructingInvocation(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForValue.class */
        public static class ForValue implements TargetHandler, Resolved {
            private final FieldDescription.InDefinedShape fieldDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForValue) obj).fieldDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
            }

            protected ForValue(FieldDescription.InDefinedShape inDefinedShape) {
                this.fieldDescription = inDefinedShape;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public Resolved resolve(MethodDescription methodDescription) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public TypeDescription getTypeDescription() {
                return this.fieldDescription.getType().asErasure();
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation assign = assigner.assign(this.fieldDescription.getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.fieldDescription);
                }
                return new StackManipulation.Compound(FieldAccess.forField(this.fieldDescription).read(), assign);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForValue$Factory.class */
            public static class Factory implements Factory {
                private static final String FIELD_PREFIX = "invocationTarget";
                private final Object target;
                private final TypeDescription.Generic fieldType;

                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
                private final String name;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.target.equals(((Factory) obj).target) && this.fieldType.equals(((Factory) obj).fieldType);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.fieldType.hashCode();
                }

                protected Factory(Object obj, TypeDescription.Generic generic) {
                    this.target = obj;
                    this.fieldType = generic;
                    this.name = "invocationTarget$" + RandomString.hashOf(obj);
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withAuxiliaryField(new FieldDescription.Token(this.name, 4169, this.fieldType), this.target);
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Factory
                public TargetHandler make(Implementation.Target target) {
                    return new ForValue((FieldDescription.InDefinedShape) target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named(this.name)).getOnly());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForField.class */
        public static class ForField implements TargetHandler, Resolved {
            private final FieldDescription fieldDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForField) obj).fieldDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
            }

            protected ForField(FieldDescription fieldDescription) {
                this.fieldDescription = fieldDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public Resolved resolve(MethodDescription methodDescription) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public TypeDescription getTypeDescription() {
                return this.fieldDescription.getType().asErasure();
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                if (!methodDescription.isMethod() || !methodDescription.isVirtual() || !methodDescription.isVisibleTo(this.fieldDescription.getType().asErasure())) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.fieldDescription);
                }
                StackManipulation assign = assigner.assign(this.fieldDescription.getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.fieldDescription);
                }
                StackManipulation[] stackManipulationArr = new StackManipulation[3];
                stackManipulationArr[0] = (methodDescription.isStatic() || this.fieldDescription.isStatic()) ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                stackManipulationArr[2] = assign;
                return new StackManipulation.Compound(stackManipulationArr);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForField$Location.class */
            public interface Location {
                FieldDescription resolve(TypeDescription typeDescription);

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForField$Location$ForImplicitField.class */
                public static class ForImplicitField implements Location {
                    private final String name;
                    private final FieldLocator.Factory fieldLocatorFactory;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.name.equals(((ForImplicitField) obj).name) && this.fieldLocatorFactory.equals(((ForImplicitField) obj).fieldLocatorFactory);
                    }

                    public int hashCode() {
                        return (((getClass().hashCode() * 31) + this.name.hashCode()) * 31) + this.fieldLocatorFactory.hashCode();
                    }

                    protected ForImplicitField(String str, FieldLocator.Factory factory) {
                        this.name = str;
                        this.fieldLocatorFactory = factory;
                    }

                    @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.ForField.Location
                    public FieldDescription resolve(TypeDescription typeDescription) {
                        FieldLocator.Resolution locate = this.fieldLocatorFactory.make(typeDescription).locate(this.name);
                        if (!locate.isResolved()) {
                            throw new IllegalStateException("Could not locate field name " + this.name + " on " + typeDescription);
                        }
                        return locate.getField();
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForField$Location$ForExplicitField.class */
                public static class ForExplicitField implements Location {
                    private final FieldDescription fieldDescription;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForExplicitField) obj).fieldDescription);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
                    }

                    protected ForExplicitField(FieldDescription fieldDescription) {
                        this.fieldDescription = fieldDescription;
                    }

                    @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.ForField.Location
                    public FieldDescription resolve(TypeDescription typeDescription) {
                        return this.fieldDescription;
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForField$Factory.class */
            public static class Factory implements Factory {
                private final Location location;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.location.equals(((Factory) obj).location);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.location.hashCode();
                }

                protected Factory(Location location) {
                    this.location = location;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Factory
                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming declaring type for type member.")
                public TargetHandler make(Implementation.Target target) {
                    FieldDescription resolve = this.location.resolve(target.getInstrumentedType());
                    if (resolve.isStatic() || target.getInstrumentedType().isAssignableTo(resolve.getDeclaringType().asErasure())) {
                        return new ForField(resolve);
                    }
                    throw new IllegalStateException("Cannot access " + resolve + " from " + target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForMethodParameter.class */
        public static class ForMethodParameter implements TargetHandler, Factory {
            private final int index;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.index == ((ForMethodParameter) obj).index;
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.index;
            }

            protected ForMethodParameter(int i) {
                this.index = i;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Factory
            public TargetHandler make(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public Resolved resolve(MethodDescription methodDescription) {
                if (this.index >= methodDescription.getParameters().size()) {
                    throw new IllegalArgumentException(methodDescription + " does not have a parameter with index " + this.index);
                }
                return new Resolved((ParameterDescription) methodDescription.getParameters().get(this.index));
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForMethodParameter$Resolved.class */
            protected static class Resolved implements Resolved {
                private final ParameterDescription parameterDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.parameterDescription.equals(((Resolved) obj).parameterDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.parameterDescription.hashCode();
                }

                protected Resolved(ParameterDescription parameterDescription) {
                    this.parameterDescription = parameterDescription;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
                public TypeDescription getTypeDescription() {
                    return this.parameterDescription.getType().asErasure();
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
                public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    StackManipulation assign = assigner.assign(this.parameterDescription.getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.parameterDescription.getType());
                    }
                    return new StackManipulation.Compound(MethodVariableAccess.load(this.parameterDescription), assign);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForMethodCall.class */
        public static class ForMethodCall implements TargetHandler {
            private final Appender appender;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.appender.equals(((ForMethodCall) obj).appender);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.appender.hashCode();
            }

            protected ForMethodCall(Appender appender) {
                this.appender = appender;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TargetHandler
            public Resolved resolve(MethodDescription methodDescription) {
                Resolved resolve = this.appender.targetHandler.resolve(methodDescription);
                return new Resolved(this.appender, this.appender.toInvokedMethod(methodDescription, resolve), methodDescription, resolve);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForMethodCall$Resolved.class */
            protected static class Resolved implements Resolved {
                private final Appender appender;
                private final MethodDescription methodDescription;
                private final MethodDescription instrumentedMethod;
                private final Resolved targetHandler;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.appender.equals(((Resolved) obj).appender) && this.methodDescription.equals(((Resolved) obj).methodDescription) && this.instrumentedMethod.equals(((Resolved) obj).instrumentedMethod) && this.targetHandler.equals(((Resolved) obj).targetHandler);
                }

                public int hashCode() {
                    return (((((((getClass().hashCode() * 31) + this.appender.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.instrumentedMethod.hashCode()) * 31) + this.targetHandler.hashCode();
                }

                protected Resolved(Appender appender, MethodDescription methodDescription, MethodDescription methodDescription2, Resolved resolved) {
                    this.appender = appender;
                    this.methodDescription = methodDescription;
                    this.instrumentedMethod = methodDescription2;
                    this.targetHandler = resolved;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
                public TypeDescription getTypeDescription() {
                    if (this.methodDescription.isConstructor()) {
                        return this.methodDescription.getDeclaringType().asErasure();
                    }
                    return this.methodDescription.getReturnType().asErasure();
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Resolved
                public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    TypeDescription.Generic returnType;
                    TypeDefinition returnType2;
                    if (this.methodDescription.isConstructor()) {
                        returnType = this.methodDescription.getDeclaringType().asGenericType();
                    } else {
                        returnType = this.methodDescription.getReturnType();
                    }
                    StackManipulation assign = assigner.assign(returnType, methodDescription.getDeclaringType().asGenericType(), typing);
                    if (!assign.isValid()) {
                        StringBuilder append = new StringBuilder("Cannot invoke ").append(methodDescription).append(" on ");
                        if (this.methodDescription.isConstructor()) {
                            returnType2 = this.methodDescription.getDeclaringType();
                        } else {
                            returnType2 = this.methodDescription.getReturnType();
                        }
                        throw new IllegalStateException(append.append(returnType2).toString());
                    }
                    return new StackManipulation.Compound(this.appender.toStackManipulation(this.instrumentedMethod, this.methodDescription, this.targetHandler), assign);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TargetHandler$ForMethodCall$Factory.class */
            protected static class Factory implements Factory {
                private final MethodCall methodCall;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodCall.equals(((Factory) obj).methodCall);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.methodCall.hashCode();
                }

                public Factory(MethodCall methodCall) {
                    this.methodCall = methodCall;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return this.methodCall.prepare(instrumentedType);
                }

                @Override // net.bytebuddy.implementation.MethodCall.TargetHandler.Factory
                public TargetHandler make(Implementation.Target target) {
                    MethodCall methodCall = this.methodCall;
                    methodCall.getClass();
                    return new ForMethodCall(new Appender(target, TerminationHandler.Simple.IGNORING));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker.class */
    public interface MethodInvoker {

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$Factory.class */
        public interface Factory {
            MethodInvoker make(TypeDescription typeDescription);
        }

        StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target);

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForContextualInvocation.class */
        public static class ForContextualInvocation implements MethodInvoker {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForContextualInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            protected ForContextualInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (methodDescription.isVirtual() && !methodDescription.isInvokableOn(this.instrumentedType)) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.instrumentedType);
                }
                if (methodDescription.isVirtual()) {
                    return MethodInvocation.invoke(methodDescription).virtual(this.instrumentedType);
                }
                return MethodInvocation.invoke(methodDescription);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForContextualInvocation$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker.Factory
                public final MethodInvoker make(TypeDescription typeDescription) {
                    return new ForContextualInvocation(typeDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForVirtualInvocation.class */
        public static class ForVirtualInvocation implements MethodInvoker {
            private final TypeDescription typeDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForVirtualInvocation) obj).typeDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.typeDescription.hashCode();
            }

            protected ForVirtualInvocation(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (!methodDescription.isInvokableOn(this.typeDescription)) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.typeDescription);
                }
                return MethodInvocation.invoke(methodDescription).virtual(this.typeDescription);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForVirtualInvocation$WithImplicitType.class */
            public enum WithImplicitType implements MethodInvoker, Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker.Factory
                public final MethodInvoker make(TypeDescription typeDescription) {
                    return this;
                }

                @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
                public final StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                    if (!methodDescription.isAccessibleTo(target.getInstrumentedType()) || !methodDescription.isVirtual()) {
                        throw new IllegalStateException("Cannot invoke " + methodDescription + " virtually");
                    }
                    return MethodInvocation.invoke(methodDescription);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForVirtualInvocation$Factory.class */
            public static class Factory implements Factory {
                private final TypeDescription typeDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Factory) obj).typeDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.typeDescription.hashCode();
                }

                protected Factory(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker.Factory
                public MethodInvoker make(TypeDescription typeDescription) {
                    if (!this.typeDescription.asErasure().isAccessibleTo(typeDescription)) {
                        throw new IllegalStateException(this.typeDescription + " is not accessible to " + typeDescription);
                    }
                    return new ForVirtualInvocation(this.typeDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForSuperMethodInvocation.class */
        public static class ForSuperMethodInvocation implements MethodInvoker {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForSuperMethodInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            protected ForSuperMethodInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (!methodDescription.isInvokableOn(target.getOriginType().asErasure())) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " as super method of " + this.instrumentedType);
                }
                Implementation.SpecialMethodInvocation withCheckedCompatibilityTo = target.invokeDominant(methodDescription.asSignatureToken()).withCheckedCompatibilityTo(methodDescription.asTypeToken());
                if (!withCheckedCompatibilityTo.isValid()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " as a super method");
                }
                return withCheckedCompatibilityTo;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForSuperMethodInvocation$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker.Factory
                public final MethodInvoker make(TypeDescription typeDescription) {
                    if (typeDescription.getSuperClass() == null) {
                        throw new IllegalStateException("Cannot invoke super method for " + typeDescription);
                    }
                    return new ForSuperMethodInvocation(typeDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForDefaultMethodInvocation.class */
        public static class ForDefaultMethodInvocation implements MethodInvoker {
            private final TypeDescription instrumentedType;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForDefaultMethodInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
            }

            protected ForDefaultMethodInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker
            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (!methodDescription.isInvokableOn(this.instrumentedType)) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " as default method of " + this.instrumentedType);
                }
                Implementation.SpecialMethodInvocation withCheckedCompatibilityTo = target.invokeDefault(methodDescription.asSignatureToken(), methodDescription.getDeclaringType().asErasure()).withCheckedCompatibilityTo(methodDescription.asTypeToken());
                if (!withCheckedCompatibilityTo.isValid()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.instrumentedType);
                }
                return withCheckedCompatibilityTo;
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$MethodInvoker$ForDefaultMethodInvocation$Factory.class */
            enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.MethodCall.MethodInvoker.Factory
                public final MethodInvoker make(TypeDescription typeDescription) {
                    return new ForDefaultMethodInvocation(typeDescription);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TerminationHandler.class */
    public interface TerminationHandler {

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TerminationHandler$Factory.class */
        public interface Factory {
            TerminationHandler make(TypeDescription typeDescription);
        }

        StackManipulation prepare();

        StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TerminationHandler$Simple.class */
        public enum Simple implements TerminationHandler, Factory {
            RETURNING { // from class: net.bytebuddy.implementation.MethodCall.TerminationHandler.Simple.1
                @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
                public final StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                    TypeDescription.Generic returnType;
                    if (methodDescription.isConstructor()) {
                        returnType = methodDescription.getDeclaringType().asGenericType();
                    } else {
                        returnType = methodDescription.getReturnType();
                    }
                    StackManipulation assign = assigner.assign(returnType, methodDescription2.getReturnType(), typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot return " + methodDescription.getReturnType() + " from " + methodDescription2);
                    }
                    return new StackManipulation.Compound(assign, MethodReturn.of(methodDescription2.getReturnType()));
                }
            },
            DROPPING { // from class: net.bytebuddy.implementation.MethodCall.TerminationHandler.Simple.2
                @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
                public final StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                    TypeDefinition returnType;
                    if (methodDescription.isConstructor()) {
                        returnType = methodDescription.getDeclaringType();
                    } else {
                        returnType = methodDescription.getReturnType();
                    }
                    return Removal.of(returnType);
                }
            },
            IGNORING { // from class: net.bytebuddy.implementation.MethodCall.TerminationHandler.Simple.3
                @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
                public final StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                    return StackManipulation.Trivial.INSTANCE;
                }
            };

            /* synthetic */ Simple(byte b2) {
                this();
            }

            @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler.Factory
            public TerminationHandler make(TypeDescription typeDescription) {
                return this;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
            public StackManipulation prepare() {
                return StackManipulation.Trivial.INSTANCE;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TerminationHandler$FieldSetting.class */
        public static class FieldSetting implements TerminationHandler {
            private final FieldDescription fieldDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((FieldSetting) obj).fieldDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
            }

            protected FieldSetting(FieldDescription fieldDescription) {
                this.fieldDescription = fieldDescription;
            }

            @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
            public StackManipulation prepare() {
                return this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
            }

            @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler
            public StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                TypeDescription.Generic returnType;
                if (methodDescription.isConstructor()) {
                    returnType = methodDescription.getDeclaringType().asGenericType();
                } else {
                    returnType = methodDescription.getReturnType();
                }
                StackManipulation assign = assigner.assign(returnType, this.fieldDescription.getType(), typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign result of " + methodDescription + " to " + this.fieldDescription);
                }
                return new StackManipulation.Compound(assign, FieldAccess.forField(this.fieldDescription).write());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TerminationHandler$FieldSetting$Explicit.class */
            public static class Explicit implements Factory {
                private final FieldDescription fieldDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Explicit) obj).fieldDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
                }

                protected Explicit(FieldDescription fieldDescription) {
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler.Factory
                @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming declaring type for type member.")
                public TerminationHandler make(TypeDescription typeDescription) {
                    if (!this.fieldDescription.isStatic() && !typeDescription.isAssignableTo(this.fieldDescription.getDeclaringType().asErasure())) {
                        throw new IllegalStateException("Cannot set " + this.fieldDescription + " from " + typeDescription);
                    }
                    if (!this.fieldDescription.isVisibleTo(typeDescription)) {
                        throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
                    }
                    return new FieldSetting(this.fieldDescription);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$TerminationHandler$FieldSetting$Implicit.class */
            protected static class Implicit implements Factory {
                private final ElementMatcher<? super FieldDescription> matcher;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Implicit) obj).matcher);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.matcher.hashCode();
                }

                protected Implicit(ElementMatcher<? super FieldDescription> elementMatcher) {
                    this.matcher = elementMatcher;
                }

                @Override // net.bytebuddy.implementation.MethodCall.TerminationHandler.Factory
                public TerminationHandler make(TypeDescription typeDescription) {
                    TypeDescription.Generic superClass;
                    TypeDescription typeDescription2 = typeDescription;
                    do {
                        FieldList filter = typeDescription2.getDeclaredFields().filter(ElementMatchers.isVisibleTo(typeDescription).and(this.matcher));
                        if (filter.size() == 1) {
                            return new FieldSetting((FieldDescription) filter.getOnly());
                        }
                        if (filter.size() == 2) {
                            throw new IllegalStateException(this.matcher + " is ambiguous and resolved: " + filter);
                        }
                        superClass = typeDescription2.getSuperClass();
                        typeDescription2 = superClass;
                    } while (superClass != null);
                    throw new IllegalStateException(this.matcher + " does not locate any accessible fields for " + typeDescription);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$WithoutSpecifiedTarget.class */
    public static class WithoutSpecifiedTarget extends MethodCall {
        protected WithoutSpecifiedTarget(MethodLocator.Factory factory) {
            super(factory, TargetHandler.ForSelfOrStaticInvocation.Factory.INSTANCE, Collections.emptyList(), MethodInvoker.ForContextualInvocation.Factory.INSTANCE, TerminationHandler.Simple.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        public MethodCall on(Object obj) {
            return on((WithoutSpecifiedTarget) obj, (Class<? super WithoutSpecifiedTarget>) obj.getClass());
        }

        public <T> MethodCall on(T t, Class<? super T> cls) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForValue.Factory(t, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(cls)), this.argumentLoaders, new MethodInvoker.ForVirtualInvocation.Factory(TypeDescription.ForLoadedType.of(cls)), this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall on(StackManipulation stackManipulation, Class<?> cls) {
            return on(stackManipulation, TypeDescription.ForLoadedType.of(cls));
        }

        public MethodCall on(StackManipulation stackManipulation, TypeDescription typeDescription) {
            return new MethodCall(this.methodLocator, new TargetHandler.Simple(typeDescription, stackManipulation), this.argumentLoaders, new MethodInvoker.ForVirtualInvocation.Factory(typeDescription), this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onArgument(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("An argument index cannot be negative: " + i);
            }
            return new MethodCall(this.methodLocator, new TargetHandler.ForMethodParameter(i), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onField(String str) {
            return onField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
        }

        public MethodCall onField(String str, FieldLocator.Factory factory) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForField.Factory(new TargetHandler.ForField.Location.ForImplicitField(str, factory)), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onField(Field field) {
            return onField(new FieldDescription.ForLoadedField(field));
        }

        public MethodCall onField(FieldDescription fieldDescription) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForField.Factory(new TargetHandler.ForField.Location.ForExplicitField(fieldDescription)), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onMethodCall(MethodCall methodCall) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForMethodCall.Factory(methodCall), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onSuper() {
            return new MethodCall(this.methodLocator, TargetHandler.ForSelfOrStaticInvocation.Factory.INSTANCE, this.argumentLoaders, MethodInvoker.ForSuperMethodInvocation.Factory.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onDefault() {
            return new MethodCall(this.methodLocator, TargetHandler.ForSelfOrStaticInvocation.Factory.INSTANCE, this.argumentLoaders, MethodInvoker.ForDefaultMethodInvocation.Factory.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$FieldSetting.class */
    public static class FieldSetting implements Implementation.Composable {
        private final MethodCall methodCall;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.methodCall.equals(((FieldSetting) obj).methodCall);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.methodCall.hashCode();
        }

        protected FieldSetting(MethodCall methodCall) {
            this.methodCall = methodCall;
        }

        public Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new FieldSetting((MethodCall) this.methodCall.withAssigner(assigner, typing));
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return this.methodCall.prepare(instrumentedType);
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new ByteCodeAppender.Compound(this.methodCall.appender(target), Appender.INSTANCE);
        }

        @Override // net.bytebuddy.implementation.Implementation.Composable
        public Implementation andThen(Implementation implementation) {
            return new Implementation.Compound(this.methodCall, implementation);
        }

        @Override // net.bytebuddy.implementation.Implementation.Composable
        public Implementation.Composable andThen(Implementation.Composable composable) {
            return new Implementation.Compound.Composable(this.methodCall, composable);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$FieldSetting$Appender.class */
        protected enum Appender implements ByteCodeAppender {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public final ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                if (!methodDescription.getReturnType().represents(Void.TYPE)) {
                    throw new IllegalStateException("Instrumented method " + methodDescription + " does not return void for field setting method call");
                }
                return new ByteCodeAppender.Size(MethodReturn.VOID.apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/MethodCall$Appender.class */
    public class Appender implements ByteCodeAppender {
        private final Implementation.Target implementationTarget;
        private final MethodLocator methodLocator;
        private final List<ArgumentLoader.ArgumentProvider> argumentProviders;
        private final MethodInvoker methodInvoker;
        private final TargetHandler targetHandler;
        private final TerminationHandler terminationHandler;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.implementationTarget.equals(((Appender) obj).implementationTarget) && this.methodLocator.equals(((Appender) obj).methodLocator) && this.argumentProviders.equals(((Appender) obj).argumentProviders) && this.methodInvoker.equals(((Appender) obj).methodInvoker) && this.targetHandler.equals(((Appender) obj).targetHandler) && this.terminationHandler.equals(((Appender) obj).terminationHandler) && MethodCall.this.equals(MethodCall.this);
        }

        public int hashCode() {
            return (((((((((((((getClass().hashCode() * 31) + this.implementationTarget.hashCode()) * 31) + this.methodLocator.hashCode()) * 31) + this.argumentProviders.hashCode()) * 31) + this.methodInvoker.hashCode()) * 31) + this.targetHandler.hashCode()) * 31) + this.terminationHandler.hashCode()) * 31) + MethodCall.this.hashCode();
        }

        protected Appender(Implementation.Target target, TerminationHandler terminationHandler) {
            this.implementationTarget = target;
            this.methodLocator = MethodCall.this.methodLocator.make(target.getInstrumentedType());
            this.argumentProviders = new ArrayList(MethodCall.this.argumentLoaders.size());
            Iterator<ArgumentLoader.Factory> it = MethodCall.this.argumentLoaders.iterator();
            while (it.hasNext()) {
                this.argumentProviders.add(it.next().make(target));
            }
            this.methodInvoker = MethodCall.this.methodInvoker.make(target.getInstrumentedType());
            this.targetHandler = MethodCall.this.targetHandler.make(target);
            this.terminationHandler = terminationHandler;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            TargetHandler.Resolved resolve = this.targetHandler.resolve(methodDescription);
            return new ByteCodeAppender.Size(new StackManipulation.Compound(this.terminationHandler.prepare(), toStackManipulation(methodDescription, toInvokedMethod(methodDescription, resolve), resolve)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }

        protected MethodDescription toInvokedMethod(MethodDescription methodDescription, TargetHandler.Resolved resolved) {
            return this.methodLocator.resolve(resolved.getTypeDescription(), methodDescription);
        }

        protected StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, TargetHandler.Resolved resolved) {
            ArrayList arrayList = new ArrayList();
            Iterator<ArgumentLoader.ArgumentProvider> it = this.argumentProviders.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().resolve(methodDescription, methodDescription2));
            }
            ParameterList<?> parameters = methodDescription2.getParameters();
            if (parameters.size() != arrayList.size()) {
                throw new IllegalStateException(methodDescription2 + " does not accept " + arrayList.size() + " arguments");
            }
            Iterator it2 = parameters.iterator();
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                arrayList2.add(((ArgumentLoader) it3.next()).toStackManipulation((ParameterDescription) it2.next(), MethodCall.this.assigner, MethodCall.this.typing));
            }
            return new StackManipulation.Compound(resolved.toStackManipulation(methodDescription2, MethodCall.this.assigner, MethodCall.this.typing), new StackManipulation.Compound(arrayList2), this.methodInvoker.toStackManipulation(methodDescription2, this.implementationTarget), this.terminationHandler.toStackManipulation(methodDescription2, methodDescription, MethodCall.this.assigner, MethodCall.this.typing));
        }
    }
}
