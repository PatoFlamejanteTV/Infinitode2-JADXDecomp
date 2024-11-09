package net.bytebuddy.description;

import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/TypeVariableSource.class */
public interface TypeVariableSource extends ModifierReviewable.OfAbstraction {

    @AlwaysNull
    public static final TypeVariableSource UNDEFINED = null;

    TypeList.Generic getTypeVariables();

    @MaybeNull
    TypeVariableSource getEnclosingSource();

    boolean isInferrable();

    @MaybeNull
    TypeDescription.Generic findVariable(String str);

    TypeDescription.Generic findExpectedVariable(String str);

    <T> T accept(Visitor<T> visitor);

    boolean isGenerified();

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/TypeVariableSource$Visitor.class */
    public interface Visitor<T> {
        T onType(TypeDescription typeDescription);

        T onMethod(MethodDescription.InDefinedShape inDefinedShape);

        /* loaded from: infinitode-2.jar:net/bytebuddy/description/TypeVariableSource$Visitor$NoOp.class */
        public enum NoOp implements Visitor<TypeVariableSource> {
            INSTANCE;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.TypeVariableSource.Visitor
            public final TypeVariableSource onType(TypeDescription typeDescription) {
                return typeDescription;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // net.bytebuddy.description.TypeVariableSource.Visitor
            public final TypeVariableSource onMethod(MethodDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/TypeVariableSource$AbstractBase.class */
    public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements TypeVariableSource {
        protected abstract String toSafeString();

        @Override // net.bytebuddy.description.TypeVariableSource
        @MaybeNull
        public TypeDescription.Generic findVariable(String str) {
            TypeList.Generic filter = getTypeVariables().filter(ElementMatchers.named(str));
            if (filter.isEmpty()) {
                TypeVariableSource enclosingSource = getEnclosingSource();
                return enclosingSource == null ? TypeDescription.Generic.UNDEFINED : enclosingSource.findVariable(str);
            }
            return filter.getOnly();
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeDescription.Generic findExpectedVariable(String str) {
            TypeDescription.Generic findVariable = findVariable(str);
            if (findVariable == null) {
                throw new IllegalArgumentException("Cannot resolve " + str + " from " + toSafeString());
            }
            return findVariable;
        }
    }
}
