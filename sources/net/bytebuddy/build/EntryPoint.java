package net.bytebuddy.build;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/build/EntryPoint.class */
public interface EntryPoint {
    ByteBuddy byteBuddy(ClassFileVersion classFileVersion);

    DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer);

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/EntryPoint$Default.class */
    public enum Default implements EntryPoint {
        REBASE { // from class: net.bytebuddy.build.EntryPoint.Default.1
            @Override // net.bytebuddy.build.EntryPoint
            public final ByteBuddy byteBuddy(ClassFileVersion classFileVersion) {
                return new ByteBuddy(classFileVersion);
            }

            @Override // net.bytebuddy.build.EntryPoint
            public final DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return byteBuddy.rebase(typeDescription, classFileLocator, methodNameTransformer);
            }
        },
        REDEFINE { // from class: net.bytebuddy.build.EntryPoint.Default.2
            @Override // net.bytebuddy.build.EntryPoint
            public final ByteBuddy byteBuddy(ClassFileVersion classFileVersion) {
                return new ByteBuddy(classFileVersion);
            }

            @Override // net.bytebuddy.build.EntryPoint
            public final DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return byteBuddy.redefine(typeDescription, classFileLocator);
            }
        },
        REDEFINE_LOCAL { // from class: net.bytebuddy.build.EntryPoint.Default.3
            @Override // net.bytebuddy.build.EntryPoint
            public final ByteBuddy byteBuddy(ClassFileVersion classFileVersion) {
                return new ByteBuddy(classFileVersion).with(Implementation.Context.Disabled.Factory.INSTANCE);
            }

            @Override // net.bytebuddy.build.EntryPoint
            public final DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return byteBuddy.redefine(typeDescription, classFileLocator).ignoreAlso(ElementMatchers.not(ElementMatchers.isDeclaredBy(typeDescription)));
            }
        },
        DECORATE { // from class: net.bytebuddy.build.EntryPoint.Default.4
            @Override // net.bytebuddy.build.EntryPoint
            public final ByteBuddy byteBuddy(ClassFileVersion classFileVersion) {
                return new ByteBuddy(classFileVersion).with(MethodGraph.Compiler.ForDeclaredMethods.INSTANCE).with(Implementation.Context.Disabled.Factory.INSTANCE);
            }

            @Override // net.bytebuddy.build.EntryPoint
            public final DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return byteBuddy.decorate(typeDescription, classFileLocator);
            }
        };

        /* synthetic */ Default(byte b2) {
            this();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/EntryPoint$Unvalidated.class */
    public static class Unvalidated implements EntryPoint {
        private final EntryPoint delegate;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.delegate.equals(((Unvalidated) obj).delegate);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.delegate.hashCode();
        }

        public Unvalidated(EntryPoint entryPoint) {
            this.delegate = entryPoint;
        }

        @Override // net.bytebuddy.build.EntryPoint
        public ByteBuddy byteBuddy(ClassFileVersion classFileVersion) {
            return this.delegate.byteBuddy(classFileVersion).with(TypeValidation.DISABLED);
        }

        @Override // net.bytebuddy.build.EntryPoint
        public DynamicType.Builder<?> transform(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
            return this.delegate.transform(typeDescription, byteBuddy, classFileLocator, methodNameTransformer);
        }
    }
}
