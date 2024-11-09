package net.bytebuddy.implementation.bind;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/ArgumentTypeResolver.class */
public enum ArgumentTypeResolver implements MethodDelegationBinder.AmbiguityResolver {
    INSTANCE;

    private static MethodDelegationBinder.AmbiguityResolver.Resolution resolveRivalBinding(TypeDescription typeDescription, int i, MethodDelegationBinder.MethodBinding methodBinding, int i2, MethodDelegationBinder.MethodBinding methodBinding2) {
        TypeDescription asErasure = ((ParameterDescription) methodBinding.getTarget().getParameters().get(i)).getType().asErasure();
        TypeDescription asErasure2 = ((ParameterDescription) methodBinding2.getTarget().getParameters().get(i2)).getType().asErasure();
        if (!asErasure.equals(asErasure2)) {
            if (asErasure.isPrimitive() && asErasure2.isPrimitive()) {
                return PrimitiveTypePrecedence.forPrimitive(asErasure).resolve(PrimitiveTypePrecedence.forPrimitive(asErasure2));
            }
            if (asErasure.isPrimitive()) {
                return typeDescription.isPrimitive() ? MethodDelegationBinder.AmbiguityResolver.Resolution.LEFT : MethodDelegationBinder.AmbiguityResolver.Resolution.RIGHT;
            }
            if (asErasure2.isPrimitive()) {
                return typeDescription.isPrimitive() ? MethodDelegationBinder.AmbiguityResolver.Resolution.RIGHT : MethodDelegationBinder.AmbiguityResolver.Resolution.LEFT;
            }
            if (asErasure.isAssignableFrom(asErasure2)) {
                return MethodDelegationBinder.AmbiguityResolver.Resolution.RIGHT;
            }
            if (asErasure2.isAssignableFrom(asErasure)) {
                return MethodDelegationBinder.AmbiguityResolver.Resolution.LEFT;
            }
            return MethodDelegationBinder.AmbiguityResolver.Resolution.AMBIGUOUS;
        }
        return MethodDelegationBinder.AmbiguityResolver.Resolution.UNKNOWN;
    }

    private static MethodDelegationBinder.AmbiguityResolver.Resolution resolveByScore(int i) {
        if (i == 0) {
            return MethodDelegationBinder.AmbiguityResolver.Resolution.AMBIGUOUS;
        }
        if (i > 0) {
            return MethodDelegationBinder.AmbiguityResolver.Resolution.LEFT;
        }
        return MethodDelegationBinder.AmbiguityResolver.Resolution.RIGHT;
    }

    @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
    public final MethodDelegationBinder.AmbiguityResolver.Resolution resolve(MethodDescription methodDescription, MethodDelegationBinder.MethodBinding methodBinding, MethodDelegationBinder.MethodBinding methodBinding2) {
        MethodDelegationBinder.AmbiguityResolver.Resolution resolution = MethodDelegationBinder.AmbiguityResolver.Resolution.UNKNOWN;
        ParameterList<?> parameters = methodDescription.getParameters();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < parameters.size(); i3++) {
            ParameterIndexToken parameterIndexToken = new ParameterIndexToken(i3);
            Integer targetParameterIndex = methodBinding.getTargetParameterIndex(parameterIndexToken);
            Integer targetParameterIndex2 = methodBinding2.getTargetParameterIndex(parameterIndexToken);
            if (targetParameterIndex != null && targetParameterIndex2 != null) {
                resolution = resolution.merge(resolveRivalBinding(((ParameterDescription) parameters.get(i3)).getType().asErasure(), targetParameterIndex.intValue(), methodBinding, targetParameterIndex2.intValue(), methodBinding2));
            } else if (targetParameterIndex != null) {
                i++;
            } else if (targetParameterIndex2 != null) {
                i2++;
            }
        }
        return resolution == MethodDelegationBinder.AmbiguityResolver.Resolution.UNKNOWN ? resolveByScore(i - i2) : resolution;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/ArgumentTypeResolver$PrimitiveTypePrecedence.class */
    public enum PrimitiveTypePrecedence {
        BOOLEAN(0),
        BYTE(1),
        SHORT(2),
        INTEGER(3),
        CHARACTER(4),
        LONG(5),
        FLOAT(6),
        DOUBLE(7);

        private final int score;

        PrimitiveTypePrecedence(int i) {
            this.score = i;
        }

        public static PrimitiveTypePrecedence forPrimitive(TypeDescription typeDescription) {
            if (typeDescription.represents(Boolean.TYPE)) {
                return BOOLEAN;
            }
            if (typeDescription.represents(Byte.TYPE)) {
                return BYTE;
            }
            if (typeDescription.represents(Short.TYPE)) {
                return SHORT;
            }
            if (typeDescription.represents(Integer.TYPE)) {
                return INTEGER;
            }
            if (typeDescription.represents(Character.TYPE)) {
                return CHARACTER;
            }
            if (typeDescription.represents(Long.TYPE)) {
                return LONG;
            }
            if (typeDescription.represents(Float.TYPE)) {
                return FLOAT;
            }
            if (typeDescription.represents(Double.TYPE)) {
                return DOUBLE;
            }
            throw new IllegalArgumentException("Not a non-void, primitive type " + typeDescription);
        }

        public final MethodDelegationBinder.AmbiguityResolver.Resolution resolve(PrimitiveTypePrecedence primitiveTypePrecedence) {
            if (this.score - primitiveTypePrecedence.score == 0) {
                return MethodDelegationBinder.AmbiguityResolver.Resolution.UNKNOWN;
            }
            if (this.score - primitiveTypePrecedence.score > 0) {
                return MethodDelegationBinder.AmbiguityResolver.Resolution.RIGHT;
            }
            return MethodDelegationBinder.AmbiguityResolver.Resolution.LEFT;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bind/ArgumentTypeResolver$ParameterIndexToken.class */
    public static class ParameterIndexToken {
        private final int parameterIndex;

        public ParameterIndexToken(int i) {
            this.parameterIndex = i;
        }

        public int hashCode() {
            return this.parameterIndex;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.parameterIndex == ((ParameterIndexToken) obj).parameterIndex;
        }
    }
}
