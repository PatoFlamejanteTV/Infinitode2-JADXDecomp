package net.bytebuddy.description;

import net.bytebuddy.description.modifier.EnumerationState;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.FieldPersistence;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.MethodStrictness;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.ParameterManifestation;
import net.bytebuddy.description.modifier.ProvisioningState;
import net.bytebuddy.description.modifier.SynchronizationState;
import net.bytebuddy.description.modifier.SyntheticState;
import net.bytebuddy.description.modifier.TypeManifestation;
import net.bytebuddy.description.modifier.Visibility;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable.class */
public interface ModifierReviewable {
    public static final int EMPTY_MASK = 0;

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$ForFieldDescription.class */
    public interface ForFieldDescription extends OfEnumeration {
        boolean isVolatile();

        boolean isTransient();

        FieldManifestation getFieldManifestation();

        FieldPersistence getFieldPersistence();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$ForMethodDescription.class */
    public interface ForMethodDescription extends OfAbstraction {
        boolean isSynchronized();

        boolean isVarArgs();

        boolean isNative();

        boolean isBridge();

        boolean isStrict();

        SynchronizationState getSynchronizationState();

        MethodStrictness getMethodStrictness();

        MethodManifestation getMethodManifestation();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$ForParameterDescription.class */
    public interface ForParameterDescription extends ModifierReviewable {
        boolean isMandated();

        ParameterManifestation getParameterManifestation();

        ProvisioningState getProvisioningState();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$ForTypeDefinition.class */
    public interface ForTypeDefinition extends OfAbstraction, OfEnumeration {
        boolean isInterface();

        boolean isAnnotation();

        TypeManifestation getTypeManifestation();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$OfAbstraction.class */
    public interface OfAbstraction extends OfByteCodeElement {
        boolean isAbstract();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$OfByteCodeElement.class */
    public interface OfByteCodeElement extends ModifierReviewable {
        boolean isPublic();

        boolean isProtected();

        boolean isPackagePrivate();

        boolean isPrivate();

        boolean isStatic();

        boolean isDeprecated();

        Ownership getOwnership();

        Visibility getVisibility();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$OfEnumeration.class */
    public interface OfEnumeration extends OfByteCodeElement {
        boolean isEnum();

        EnumerationState getEnumerationState();
    }

    int getModifiers();

    boolean isFinal();

    boolean isSynthetic();

    SyntheticState getSyntheticState();

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/ModifierReviewable$AbstractBase.class */
    public static abstract class AbstractBase implements ForFieldDescription, ForMethodDescription, ForParameterDescription, ForTypeDefinition {
        @Override // net.bytebuddy.description.ModifierReviewable.OfAbstraction
        public boolean isAbstract() {
            return matchesMask(1024);
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public boolean isFinal() {
            return matchesMask(16);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public boolean isStatic() {
            return matchesMask(8);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public boolean isPublic() {
            return matchesMask(1);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public boolean isProtected() {
            return matchesMask(4);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public boolean isPackagePrivate() {
            return (isPublic() || isProtected() || isPrivate()) ? false : true;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public boolean isPrivate() {
            return matchesMask(2);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public boolean isNative() {
            return matchesMask(256);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public boolean isSynchronized() {
            return matchesMask(32);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public boolean isStrict() {
            return matchesMask(2048);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForParameterDescription
        public boolean isMandated() {
            return matchesMask(32768);
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public boolean isSynthetic() {
            return matchesMask(4096);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public boolean isBridge() {
            return matchesMask(64);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public boolean isDeprecated() {
            return matchesMask(131072);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForTypeDefinition
        public boolean isAnnotation() {
            return matchesMask(8192);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfEnumeration
        public boolean isEnum() {
            return matchesMask(16384);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForTypeDefinition
        public boolean isInterface() {
            return matchesMask(512);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForFieldDescription
        public boolean isTransient() {
            return matchesMask(128);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForFieldDescription
        public boolean isVolatile() {
            return matchesMask(64);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public boolean isVarArgs() {
            return matchesMask(128);
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public SyntheticState getSyntheticState() {
            return isSynthetic() ? SyntheticState.SYNTHETIC : SyntheticState.PLAIN;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public Visibility getVisibility() {
            int modifiers = getModifiers();
            switch (modifiers & 7) {
                case 0:
                    return Visibility.PACKAGE_PRIVATE;
                case 1:
                    return Visibility.PUBLIC;
                case 2:
                    return Visibility.PRIVATE;
                case 3:
                default:
                    throw new IllegalStateException("Unexpected modifiers: " + modifiers);
                case 4:
                    return Visibility.PROTECTED;
            }
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfByteCodeElement
        public Ownership getOwnership() {
            return isStatic() ? Ownership.STATIC : Ownership.MEMBER;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.OfEnumeration
        public EnumerationState getEnumerationState() {
            return isEnum() ? EnumerationState.ENUMERATION : EnumerationState.PLAIN;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForTypeDefinition
        public TypeManifestation getTypeManifestation() {
            int modifiers = getModifiers();
            switch (modifiers & 9744) {
                case 0:
                    return TypeManifestation.PLAIN;
                case 16:
                    return TypeManifestation.FINAL;
                case 1024:
                    return TypeManifestation.ABSTRACT;
                case 1536:
                    return TypeManifestation.INTERFACE;
                case 9728:
                    return TypeManifestation.ANNOTATION;
                default:
                    throw new IllegalStateException("Unexpected modifiers: " + modifiers);
            }
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForFieldDescription
        public FieldManifestation getFieldManifestation() {
            int modifiers = getModifiers();
            switch (modifiers & 80) {
                case 0:
                    return FieldManifestation.PLAIN;
                case 16:
                    return FieldManifestation.FINAL;
                case 64:
                    return FieldManifestation.VOLATILE;
                default:
                    throw new IllegalStateException("Unexpected modifiers: " + modifiers);
            }
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForFieldDescription
        public FieldPersistence getFieldPersistence() {
            int modifiers = getModifiers();
            switch (modifiers & 128) {
                case 0:
                    return FieldPersistence.PLAIN;
                case 128:
                    return FieldPersistence.TRANSIENT;
                default:
                    throw new IllegalStateException("Unexpected modifiers: " + modifiers);
            }
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public SynchronizationState getSynchronizationState() {
            return isSynchronized() ? SynchronizationState.SYNCHRONIZED : SynchronizationState.PLAIN;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public MethodManifestation getMethodManifestation() {
            int modifiers = getModifiers();
            switch (modifiers & 1360) {
                case 0:
                    return MethodManifestation.PLAIN;
                case 16:
                    return MethodManifestation.FINAL;
                case 64:
                    return MethodManifestation.BRIDGE;
                case 80:
                    return MethodManifestation.FINAL_BRIDGE;
                case 256:
                    return MethodManifestation.NATIVE;
                case 272:
                    return MethodManifestation.FINAL_NATIVE;
                case 1024:
                    return MethodManifestation.ABSTRACT;
                default:
                    throw new IllegalStateException("Unexpected modifiers: " + modifiers);
            }
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public MethodStrictness getMethodStrictness() {
            return isStrict() ? MethodStrictness.STRICT : MethodStrictness.PLAIN;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForParameterDescription
        public ParameterManifestation getParameterManifestation() {
            return isFinal() ? ParameterManifestation.FINAL : ParameterManifestation.PLAIN;
        }

        @Override // net.bytebuddy.description.ModifierReviewable.ForParameterDescription
        public ProvisioningState getProvisioningState() {
            return isMandated() ? ProvisioningState.MANDATED : ProvisioningState.PLAIN;
        }

        private boolean matchesMask(int i) {
            return (getModifiers() & i) == i;
        }
    }
}
