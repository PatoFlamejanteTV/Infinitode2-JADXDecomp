package nonapi.io.github.classgraph.json;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/TypeResolutions.class */
class TypeResolutions {
    private final TypeVariable<?>[] typeVariables;
    Type[] resolvedTypeArguments;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypeResolutions(ParameterizedType parameterizedType) {
        this.typeVariables = ((Class) parameterizedType.getRawType()).getTypeParameters();
        this.resolvedTypeArguments = parameterizedType.getActualTypeArguments();
        if (this.resolvedTypeArguments.length != this.typeVariables.length) {
            throw new IllegalArgumentException("Type parameter count mismatch");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Type resolveTypeVariables(Type type) {
        Type type2;
        if (type instanceof Class) {
            return type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type[] typeArr = null;
            for (int i = 0; i < actualTypeArguments.length; i++) {
                Type resolveTypeVariables = resolveTypeVariables(actualTypeArguments[i]);
                if (typeArr == null) {
                    if (!resolveTypeVariables.equals(actualTypeArguments[i])) {
                        typeArr = new Type[actualTypeArguments.length];
                        System.arraycopy(actualTypeArguments, 0, typeArr, 0, i);
                    }
                }
                typeArr[i] = resolveTypeVariables;
            }
            if (typeArr == null) {
                return type;
            }
            return new ParameterizedTypeImpl((Class) parameterizedType.getRawType(), typeArr, parameterizedType.getOwnerType());
        }
        if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            for (int i2 = 0; i2 < this.typeVariables.length; i2++) {
                if (this.typeVariables[i2].getName().equals(typeVariable.getName())) {
                    return this.resolvedTypeArguments[i2];
                }
            }
            return type;
        }
        if (type instanceof GenericArrayType) {
            int i3 = 0;
            Type type3 = type;
            while (true) {
                type2 = type3;
                if (!(type2 instanceof GenericArrayType)) {
                    break;
                }
                i3++;
                type3 = ((GenericArrayType) type2).getGenericComponentType();
            }
            Type resolveTypeVariables2 = resolveTypeVariables(type2);
            if (!(resolveTypeVariables2 instanceof Class)) {
                throw new IllegalArgumentException("Could not resolve generic array type " + type);
            }
            return Array.newInstance((Class<?>) resolveTypeVariables2, (int[]) Array.newInstance((Class<?>) Integer.TYPE, i3)).getClass();
        }
        if (type instanceof WildcardType) {
            throw new RuntimeException("WildcardType not yet supported: " + type);
        }
        throw new RuntimeException("Got unexpected type: " + type);
    }

    public String toString() {
        if (this.typeVariables.length == 0) {
            return "{ }";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < this.typeVariables.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.typeVariables[i]).append(" => ").append(this.resolvedTypeArguments[i]);
        }
        sb.append(" }");
        return sb.toString();
    }
}
