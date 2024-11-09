package net.bytebuddy.implementation.bytecode.assign.reference;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.utility.QueueFactory;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner.class */
public enum GenericTypeAwareAssigner implements Assigner {
    INSTANCE;

    @Override // net.bytebuddy.implementation.bytecode.assign.Assigner
    public final StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing) {
        if (generic.isPrimitive() || generic2.isPrimitive()) {
            return generic.equals(generic2) ? StackManipulation.Trivial.INSTANCE : StackManipulation.Illegal.INSTANCE;
        }
        if (((Boolean) generic.accept(new IsAssignableToVisitor(generic2))).booleanValue()) {
            return StackManipulation.Trivial.INSTANCE;
        }
        if (typing.isDynamic()) {
            return generic.asErasure().isAssignableTo(generic2.asErasure()) ? StackManipulation.Trivial.INSTANCE : TypeCasting.to(generic2);
        }
        return StackManipulation.Illegal.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner$IsAssignableToVisitor.class */
    public static class IsAssignableToVisitor implements TypeDescription.Generic.Visitor<Boolean> {
        private final TypeDescription.Generic typeDescription;
        private final boolean polymorphic;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.polymorphic == ((IsAssignableToVisitor) obj).polymorphic && this.typeDescription.equals(((IsAssignableToVisitor) obj).typeDescription);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.typeDescription.hashCode()) * 31) + (this.polymorphic ? 1 : 0);
        }

        public IsAssignableToVisitor(TypeDescription.Generic generic) {
            this(generic, true);
        }

        protected IsAssignableToVisitor(TypeDescription.Generic generic, boolean z) {
            this.typeDescription = generic;
            this.polymorphic = z;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public Boolean onGenericArray(TypeDescription.Generic generic) {
            return (Boolean) this.typeDescription.accept(new OfGenericArray(generic, this.polymorphic));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public Boolean onWildcard(TypeDescription.Generic generic) {
            return (Boolean) this.typeDescription.accept(new OfWildcard(generic));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public Boolean onParameterizedType(TypeDescription.Generic generic) {
            return (Boolean) this.typeDescription.accept(new OfParameterizedType(generic, this.polymorphic));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public Boolean onTypeVariable(TypeDescription.Generic generic) {
            if (generic.getTypeVariableSource().isInferrable()) {
                throw new UnsupportedOperationException("Assignability checks for type variables declared by methods are not currently supported");
            }
            if (generic.equals(this.typeDescription)) {
                return Boolean.TRUE;
            }
            if (this.polymorphic) {
                Queue make = QueueFactory.make(generic.getUpperBounds());
                while (!make.isEmpty()) {
                    TypeDescription.Generic generic2 = (TypeDescription.Generic) make.remove();
                    if (((Boolean) generic2.accept(new IsAssignableToVisitor(this.typeDescription))).booleanValue()) {
                        return Boolean.TRUE;
                    }
                    if (generic2.getSort().isTypeVariable()) {
                        make.addAll(generic2.getUpperBounds());
                    }
                }
                return Boolean.FALSE;
            }
            return Boolean.FALSE;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public Boolean onNonGenericType(TypeDescription.Generic generic) {
            return (Boolean) this.typeDescription.accept(new OfNonGenericType(generic, this.polymorphic));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner$IsAssignableToVisitor$OfManifestType.class */
        public static abstract class OfManifestType implements TypeDescription.Generic.Visitor<Boolean> {
            protected final TypeDescription.Generic typeDescription;
            protected final boolean polymorphic;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.polymorphic == ((OfManifestType) obj).polymorphic && this.typeDescription.equals(((OfManifestType) obj).typeDescription);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.typeDescription.hashCode()) * 31) + (this.polymorphic ? 1 : 0);
            }

            protected OfManifestType(TypeDescription.Generic generic, boolean z) {
                this.typeDescription = generic;
                this.polymorphic = z;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onWildcard(TypeDescription.Generic generic) {
                Iterator it = generic.getUpperBounds().iterator();
                while (it.hasNext()) {
                    if (!((Boolean) this.typeDescription.accept(new IsAssignableToVisitor((TypeDescription.Generic) it.next()))).booleanValue()) {
                        return Boolean.FALSE;
                    }
                }
                Iterator it2 = generic.getLowerBounds().iterator();
                while (it2.hasNext()) {
                    if (!((Boolean) ((TypeDescription.Generic) it2.next()).accept(new IsAssignableToVisitor(this.typeDescription))).booleanValue()) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onTypeVariable(TypeDescription.Generic generic) {
                if (generic.getTypeVariableSource().isInferrable()) {
                    throw new UnsupportedOperationException("Assignability checks for type variables declared by methods arel not currently supported");
                }
                return Boolean.FALSE;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner$IsAssignableToVisitor$OfSimpleType.class */
        protected static abstract class OfSimpleType extends OfManifestType {
            protected OfSimpleType(TypeDescription.Generic generic, boolean z) {
                super(generic, z);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onParameterizedType(TypeDescription.Generic generic) {
                Queue make = QueueFactory.make(Collections.singleton(this.typeDescription));
                HashSet hashSet = new HashSet(Collections.singleton(this.typeDescription.asErasure()));
                do {
                    TypeDescription.Generic generic2 = (TypeDescription.Generic) make.remove();
                    if (generic2.asErasure().equals(generic.asErasure())) {
                        if (generic2.getSort().isNonGeneric()) {
                            return Boolean.TRUE;
                        }
                        TypeList.Generic typeArguments = generic2.getTypeArguments();
                        TypeList.Generic typeArguments2 = generic.getTypeArguments();
                        int size = typeArguments2.size();
                        if (typeArguments.size() != size) {
                            return Boolean.FALSE;
                        }
                        for (int i = 0; i < size; i++) {
                            if (!((Boolean) ((TypeDescription.Generic) typeArguments.get(i)).accept(new IsAssignableToVisitor((TypeDescription.Generic) typeArguments2.get(i), false))).booleanValue()) {
                                return Boolean.FALSE;
                            }
                        }
                        TypeDescription.Generic ownerType = generic.getOwnerType();
                        return Boolean.valueOf(ownerType == null || ((Boolean) ownerType.accept(new IsAssignableToVisitor(ownerType))).booleanValue());
                    }
                    if (this.polymorphic) {
                        TypeDescription.Generic superClass = generic2.getSuperClass();
                        if (superClass != null && hashSet.add(superClass.asErasure())) {
                            make.add(superClass);
                        }
                        for (TypeDescription.Generic generic3 : generic2.getInterfaces()) {
                            if (hashSet.add(generic3.asErasure())) {
                                make.add(generic3);
                            }
                        }
                    }
                } while (!make.isEmpty());
                return Boolean.FALSE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onNonGenericType(TypeDescription.Generic generic) {
                boolean equals;
                if (this.polymorphic) {
                    equals = this.typeDescription.asErasure().isAssignableTo(generic.asErasure());
                } else {
                    equals = this.typeDescription.asErasure().equals(generic.asErasure());
                }
                return Boolean.valueOf(equals);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner$IsAssignableToVisitor$OfGenericArray.class */
        public static class OfGenericArray extends OfManifestType {
            protected OfGenericArray(TypeDescription.Generic generic, boolean z) {
                super(generic, z);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
            public Boolean onGenericArray(TypeDescription.Generic generic) {
                TypeDescription.Generic generic2;
                TypeDescription.Generic componentType = this.typeDescription.getComponentType();
                TypeDescription.Generic componentType2 = generic.getComponentType();
                while (true) {
                    generic2 = componentType2;
                    if (!componentType.getSort().isGenericArray() || !generic2.getSort().isGenericArray()) {
                        break;
                    }
                    componentType = componentType.getComponentType();
                    componentType2 = generic2.getComponentType();
                }
                return Boolean.valueOf((componentType.getSort().isGenericArray() || generic2.getSort().isGenericArray() || !((Boolean) componentType.accept(new IsAssignableToVisitor(generic2))).booleanValue()) ? false : true);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onParameterizedType(TypeDescription.Generic generic) {
                return Boolean.FALSE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onNonGenericType(TypeDescription.Generic generic) {
                boolean equals;
                if (this.polymorphic) {
                    equals = this.typeDescription.asErasure().isAssignableTo(generic.asErasure());
                } else {
                    equals = this.typeDescription.asErasure().equals(generic.asErasure());
                }
                return Boolean.valueOf(equals);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner$IsAssignableToVisitor$OfWildcard.class */
        public static class OfWildcard implements TypeDescription.Generic.Visitor<Boolean> {
            private final TypeDescription.Generic wildcard;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.wildcard.equals(((OfWildcard) obj).wildcard);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.wildcard.hashCode();
            }

            protected OfWildcard(TypeDescription.Generic generic) {
                this.wildcard = generic;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onGenericArray(TypeDescription.Generic generic) {
                return Boolean.FALSE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onWildcard(TypeDescription.Generic generic) {
                boolean z = false;
                boolean z2 = false;
                for (TypeDescription.Generic generic2 : generic.getUpperBounds()) {
                    Iterator it = this.wildcard.getUpperBounds().iterator();
                    while (it.hasNext()) {
                        if (!((Boolean) ((TypeDescription.Generic) it.next()).accept(new IsAssignableToVisitor(generic2))).booleanValue()) {
                            return Boolean.FALSE;
                        }
                    }
                    z = z || !generic2.represents(Object.class);
                }
                for (TypeDescription.Generic generic3 : generic.getLowerBounds()) {
                    Iterator it2 = this.wildcard.getLowerBounds().iterator();
                    while (it2.hasNext()) {
                        if (!((Boolean) generic3.accept(new IsAssignableToVisitor((TypeDescription.Generic) it2.next()))).booleanValue()) {
                            return Boolean.FALSE;
                        }
                    }
                    z2 = true;
                }
                if (z) {
                    return Boolean.valueOf(this.wildcard.getLowerBounds().isEmpty());
                }
                if (z2) {
                    TypeList.Generic upperBounds = this.wildcard.getUpperBounds();
                    return Boolean.valueOf(upperBounds.size() == 0 || (upperBounds.size() == 1 && upperBounds.getOnly().represents(Object.class)));
                }
                return Boolean.TRUE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onParameterizedType(TypeDescription.Generic generic) {
                return Boolean.FALSE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onTypeVariable(TypeDescription.Generic generic) {
                return Boolean.FALSE;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onNonGenericType(TypeDescription.Generic generic) {
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner$IsAssignableToVisitor$OfParameterizedType.class */
        public static class OfParameterizedType extends OfSimpleType {
            protected OfParameterizedType(TypeDescription.Generic generic, boolean z) {
                super(generic, z);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onGenericArray(TypeDescription.Generic generic) {
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/GenericTypeAwareAssigner$IsAssignableToVisitor$OfNonGenericType.class */
        public static class OfNonGenericType extends OfSimpleType {
            protected OfNonGenericType(TypeDescription.Generic generic, boolean z) {
                super(generic, z);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
            public Boolean onGenericArray(TypeDescription.Generic generic) {
                boolean equals;
                if (this.polymorphic) {
                    equals = this.typeDescription.asErasure().isAssignableTo(generic.asErasure());
                } else {
                    equals = this.typeDescription.asErasure().equals(generic.asErasure());
                }
                return Boolean.valueOf(equals);
            }
        }
    }
}
