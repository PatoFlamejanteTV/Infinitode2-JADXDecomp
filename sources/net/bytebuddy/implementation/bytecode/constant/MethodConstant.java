package net.bytebuddy.implementation.bytecode.constant;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.PrivilegedMemberLookupAction;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant.class */
public abstract class MethodConstant extends StackManipulation.AbstractBase {

    @MaybeNull
    protected static final MethodDescription.InDefinedShape DO_PRIVILEGED = doPrivileged();
    protected final MethodDescription.InDefinedShape methodDescription;

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant$CanCache.class */
    public interface CanCache extends StackManipulation {
        StackManipulation cached();
    }

    protected abstract StackManipulation methodName();

    protected abstract MethodDescription.InDefinedShape accessorMethod();

    @MaybeNull
    @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
    private static MethodDescription.InDefinedShape doPrivileged() {
        MethodDescription.ForLoadedMethod forLoadedMethod;
        try {
            forLoadedMethod = new MethodDescription.ForLoadedMethod(Class.forName("java.security.AccessController").getMethod("doPrivileged", PrivilegedExceptionAction.class));
            try {
                if (!Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"))) {
                    forLoadedMethod = null;
                }
            } catch (SecurityException unused) {
            }
        } catch (Exception unused2) {
            forLoadedMethod = null;
        }
        return forLoadedMethod;
    }

    protected MethodConstant(MethodDescription.InDefinedShape inDefinedShape) {
        this.methodDescription = inDefinedShape;
    }

    public static CanCache of(MethodDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isTypeInitializer()) {
            return CanCacheIllegal.INSTANCE;
        }
        if (inDefinedShape.isConstructor()) {
            return new ForConstructor(inDefinedShape);
        }
        return new ForMethod(inDefinedShape);
    }

    public static CanCache ofPrivileged(MethodDescription.InDefinedShape inDefinedShape) {
        if (DO_PRIVILEGED == null) {
            return of(inDefinedShape);
        }
        if (inDefinedShape.isTypeInitializer()) {
            return CanCacheIllegal.INSTANCE;
        }
        if (inDefinedShape.isConstructor()) {
            return new ForConstructor(inDefinedShape).withPrivilegedLookup();
        }
        return new ForMethod(inDefinedShape).withPrivilegedLookup();
    }

    protected static List<StackManipulation> typeConstantsFor(List<TypeDescription> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<TypeDescription> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(ClassConstant.of(it.next()));
        }
        return arrayList;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        return new StackManipulation.Compound(ClassConstant.of(this.methodDescription.getDeclaringType()), methodName(), ArrayFactory.forType(TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Class.class)).withValues(typeConstantsFor(this.methodDescription.getParameters().asTypeList().asErasures())), MethodInvocation.invoke(accessorMethod())).apply(methodVisitor, context);
    }

    protected CanCache withPrivilegedLookup() {
        return new PrivilegedLookup(this.methodDescription, methodName());
    }

    public int hashCode() {
        return this.methodDescription.hashCode();
    }

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.methodDescription.equals(((MethodConstant) obj).methodDescription);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant$CanCacheIllegal.class */
    public enum CanCacheIllegal implements CanCache {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache
        public final StackManipulation cached() {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final boolean isValid() {
            return false;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public final StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackManipulation.Illegal.INSTANCE.apply(methodVisitor, context);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant$ForMethod.class */
    public static class ForMethod extends MethodConstant implements CanCache {
        private static final MethodDescription.InDefinedShape GET_METHOD;
        private static final MethodDescription.InDefinedShape GET_DECLARED_METHOD;

        static {
            try {
                GET_METHOD = new MethodDescription.ForLoadedMethod(Class.class.getMethod("getMethod", String.class, Class[].class));
                GET_DECLARED_METHOD = new MethodDescription.ForLoadedMethod(Class.class.getMethod("getDeclaredMethod", String.class, Class[].class));
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Could not locate method lookup", e);
            }
        }

        protected ForMethod(MethodDescription.InDefinedShape inDefinedShape) {
            super(inDefinedShape);
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected StackManipulation methodName() {
            return new TextConstant(this.methodDescription.getInternalName());
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected MethodDescription.InDefinedShape accessorMethod() {
            return this.methodDescription.isPublic() ? GET_METHOD : GET_DECLARED_METHOD;
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache
        public StackManipulation cached() {
            return new CachedMethod(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant$ForConstructor.class */
    public static class ForConstructor extends MethodConstant implements CanCache {
        private static final MethodDescription.InDefinedShape GET_CONSTRUCTOR;
        private static final MethodDescription.InDefinedShape GET_DECLARED_CONSTRUCTOR;

        static {
            try {
                GET_CONSTRUCTOR = new MethodDescription.ForLoadedMethod(Class.class.getMethod("getConstructor", Class[].class));
                GET_DECLARED_CONSTRUCTOR = new MethodDescription.ForLoadedMethod(Class.class.getMethod(TypeProxy.SilentConstruction.Appender.GET_DECLARED_CONSTRUCTOR_METHOD_NAME, Class[].class));
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Could not locate Class::getDeclaredConstructor", e);
            }
        }

        protected ForConstructor(MethodDescription.InDefinedShape inDefinedShape) {
            super(inDefinedShape);
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected StackManipulation methodName() {
            return StackManipulation.Trivial.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected MethodDescription.InDefinedShape accessorMethod() {
            return this.methodDescription.isPublic() ? GET_CONSTRUCTOR : GET_DECLARED_CONSTRUCTOR;
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache
        public StackManipulation cached() {
            return new CachedConstructor(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant$PrivilegedLookup.class */
    public static class PrivilegedLookup implements StackManipulation, CanCache {
        private final MethodDescription.InDefinedShape methodDescription;
        private final StackManipulation methodName;

        protected PrivilegedLookup(MethodDescription.InDefinedShape inDefinedShape, StackManipulation stackManipulation) {
            this.methodDescription = inDefinedShape;
            this.methodName = stackManipulation;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.methodName.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            if (MethodConstant.DO_PRIVILEGED == null) {
                throw new IllegalStateException("Privileged method invocation is not supported on the current VM");
            }
            TypeDescription register = context.register(PrivilegedMemberLookupAction.of(this.methodDescription));
            StackManipulation[] stackManipulationArr = new StackManipulation[8];
            stackManipulationArr[0] = TypeCreation.of(register);
            stackManipulationArr[1] = Duplication.SINGLE;
            stackManipulationArr[2] = ClassConstant.of(this.methodDescription.getDeclaringType());
            stackManipulationArr[3] = this.methodName;
            stackManipulationArr[4] = ArrayFactory.forType(TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Class.class)).withValues(MethodConstant.typeConstantsFor(this.methodDescription.getParameters().asTypeList().asErasures()));
            stackManipulationArr[5] = MethodInvocation.invoke((MethodDescription.InDefinedShape) register.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly());
            stackManipulationArr[6] = MethodInvocation.invoke(MethodConstant.DO_PRIVILEGED);
            stackManipulationArr[7] = TypeCasting.to(TypeDescription.ForLoadedType.of(this.methodDescription.isConstructor() ? Constructor.class : Method.class));
            return new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context);
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache
        public StackManipulation cached() {
            return this.methodDescription.isConstructor() ? new CachedConstructor(this) : new CachedMethod(this);
        }

        public int hashCode() {
            return this.methodDescription.hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.methodDescription.equals(((PrivilegedLookup) obj).methodDescription);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant$CachedMethod.class */
    protected static class CachedMethod implements StackManipulation {
        private static final TypeDescription METHOD_TYPE = TypeDescription.ForLoadedType.of(Method.class);
        private final StackManipulation methodConstant;

        protected CachedMethod(StackManipulation stackManipulation) {
            this.methodConstant = stackManipulation;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.methodConstant.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return FieldAccess.forField(context.cache(this.methodConstant, METHOD_TYPE)).read().apply(methodVisitor, context);
        }

        public int hashCode() {
            return this.methodConstant.hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.methodConstant.equals(((CachedMethod) obj).methodConstant);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/constant/MethodConstant$CachedConstructor.class */
    protected static class CachedConstructor implements StackManipulation {
        private static final TypeDescription CONSTRUCTOR_TYPE = TypeDescription.ForLoadedType.of(Constructor.class);
        private final StackManipulation constructorConstant;

        protected CachedConstructor(StackManipulation stackManipulation) {
            this.constructorConstant = stackManipulation;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.constructorConstant.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return FieldAccess.forField(context.cache(this.constructorConstant, CONSTRUCTOR_TYPE)).read().apply(methodVisitor, context);
        }

        public int hashCode() {
            return this.constructorConstant.hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.constructorConstant.equals(((CachedConstructor) obj).constructorConstant);
        }
    }
}
