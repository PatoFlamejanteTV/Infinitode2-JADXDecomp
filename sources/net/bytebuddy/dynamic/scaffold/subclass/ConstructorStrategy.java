package net.bytebuddy.dynamic.scaffold.subclass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/subclass/ConstructorStrategy.class */
public interface ConstructorStrategy {
    List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription);

    MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/subclass/ConstructorStrategy$Default.class */
    public enum Default implements ConstructorStrategy {
        NO_CONSTRUCTORS { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.1
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                return Collections.emptyList();
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry;
            }
        },
        DEFAULT_CONSTRUCTOR { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.2
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if ((superClass == null ? new MethodList.Empty() : superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0)).and(ElementMatchers.isVisibleTo(typeDescription)))).size() == 1) {
                    return Collections.singletonList(new MethodDescription.Token(1));
                }
                throw new IllegalArgumentException(typeDescription.getSuperClass() + " declares no constructor that is visible to " + typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.3
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                return (superClass == null ? new MethodList.Empty() : superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.isVisibleTo(typeDescription)))).asTokenList(ElementMatchers.is(typeDescription));
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            public final MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS_PUBLIC { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.4
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                return (superClass == null ? new MethodList.Empty() : superClass.getDeclaredMethods().filter(ElementMatchers.isPublic().and(ElementMatchers.isConstructor()))).asTokenList(ElementMatchers.is(typeDescription));
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            public final MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS_OPENING { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.5
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                return (superClass == null ? new MethodList.Empty() : superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.isVisibleTo(typeDescription)))).asTokenList(ElementMatchers.is(typeDescription));
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            public final MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected final int resolveModifier(int i) {
                return 1;
            }
        };

        protected abstract List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription);

        protected abstract MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory);

        /* synthetic */ Default(byte b2) {
            this();
        }

        @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
        public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
            List<MethodDescription.Token> doExtractConstructors = doExtractConstructors(typeDescription);
            ArrayList arrayList = new ArrayList(doExtractConstructors.size());
            for (MethodDescription.Token token : doExtractConstructors) {
                arrayList.add(new MethodDescription.Token(token.getName(), resolveModifier(token.getModifiers()), token.getTypeVariableTokens(), token.getReturnType(), token.getParameterTokens(), token.getExceptionTypes(), token.getAnnotations(), token.getDefaultValue(), TypeDescription.Generic.UNDEFINED));
            }
            return arrayList;
        }

        protected int resolveModifier(int i) {
            return i;
        }

        @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
        public MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry) {
            return doInject(methodRegistry, MethodAttributeAppender.NoOp.INSTANCE);
        }

        public ConstructorStrategy with(MethodAttributeAppender.Factory factory) {
            return new WithMethodAttributeAppenderFactory(this, factory);
        }

        public ConstructorStrategy withInheritedAnnotations() {
            return new WithMethodAttributeAppenderFactory(this, MethodAttributeAppender.ForInstrumentedMethod.EXCLUDING_RECEIVER);
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/subclass/ConstructorStrategy$Default$WithMethodAttributeAppenderFactory.class */
        protected static class WithMethodAttributeAppenderFactory implements ConstructorStrategy {
            private final Default delegate;
            private final MethodAttributeAppender.Factory methodAttributeAppenderFactory;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithMethodAttributeAppenderFactory) obj).delegate) && this.methodAttributeAppenderFactory.equals(((WithMethodAttributeAppenderFactory) obj).methodAttributeAppenderFactory);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.delegate.hashCode()) * 31) + this.methodAttributeAppenderFactory.hashCode();
            }

            protected WithMethodAttributeAppenderFactory(Default r4, MethodAttributeAppender.Factory factory) {
                this.delegate = r4;
                this.methodAttributeAppenderFactory = factory;
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
            public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
                return this.delegate.extractConstructors(typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
            public MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry) {
                return this.delegate.doInject(methodRegistry, this.methodAttributeAppenderFactory);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/subclass/ConstructorStrategy$ForDefaultConstructor.class */
    public static class ForDefaultConstructor implements ConstructorStrategy {
        private final ElementMatcher<? super MethodDescription> elementMatcher;
        private final MethodAttributeAppender.Factory methodAttributeAppenderFactory;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.elementMatcher.equals(((ForDefaultConstructor) obj).elementMatcher) && this.methodAttributeAppenderFactory.equals(((ForDefaultConstructor) obj).methodAttributeAppenderFactory);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.elementMatcher.hashCode()) * 31) + this.methodAttributeAppenderFactory.hashCode();
        }

        public ForDefaultConstructor() {
            this(ElementMatchers.any());
        }

        public ForDefaultConstructor(ElementMatcher<? super MethodDescription> elementMatcher) {
            this(elementMatcher, MethodAttributeAppender.NoOp.INSTANCE);
        }

        public ForDefaultConstructor(MethodAttributeAppender.Factory factory) {
            this(ElementMatchers.any(), factory);
        }

        public ForDefaultConstructor(ElementMatcher<? super MethodDescription> elementMatcher, MethodAttributeAppender.Factory factory) {
            this.elementMatcher = elementMatcher;
            this.methodAttributeAppenderFactory = factory;
        }

        @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
        public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
            TypeDescription.Generic superClass = typeDescription.getSuperClass();
            if (superClass == null) {
                throw new IllegalArgumentException("Cannot extract constructors for " + typeDescription);
            }
            if (superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor()).isEmpty()) {
                throw new IllegalStateException("Cannot define default constructor for class without super class constructor");
            }
            return Collections.singletonList(new MethodDescription.Token(1));
        }

        @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
        public MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry) {
            TypeDescription.Generic superClass = typeDescription.getSuperClass();
            if (superClass == null) {
                throw new IllegalArgumentException("Cannot inject constructors for " + typeDescription);
            }
            MethodList filter = superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(this.elementMatcher));
            MethodList methodList = filter;
            if (filter.isEmpty()) {
                throw new IllegalStateException("No possible candidate for super constructor invocation in " + superClass);
            }
            if (!methodList.filter(ElementMatchers.takesArguments(0)).isEmpty()) {
                methodList = (MethodList) methodList.filter(ElementMatchers.takesArguments(0));
            } else if (methodList.size() > 1) {
                throw new IllegalStateException("More than one possible super constructor for constructor delegation: " + methodList);
            }
            MethodCall.WithoutSpecifiedTarget invoke = MethodCall.invoke((MethodDescription) methodList.getOnly());
            Iterator it = ((MethodDescription) methodList.getOnly()).getParameters().asTypeList().asErasures().iterator();
            while (it.hasNext()) {
                invoke = invoke.with(((TypeDescription) it.next()).getDefaultValue());
            }
            return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0))), new MethodRegistry.Handler.ForImplementation(invoke), this.methodAttributeAppenderFactory, Transformer.NoOp.make());
        }
    }
}
