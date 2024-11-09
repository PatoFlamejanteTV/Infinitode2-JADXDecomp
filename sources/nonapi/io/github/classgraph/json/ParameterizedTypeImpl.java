package nonapi.io.github.classgraph.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/ParameterizedTypeImpl.class */
class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Class<?> rawType;
    private final Type ownerType;
    public static final Type MAP_OF_UNKNOWN_TYPE = new ParameterizedTypeImpl(Map.class, new Type[]{Object.class, Object.class}, null);
    public static final Type LIST_OF_UNKNOWN_TYPE = new ParameterizedTypeImpl(List.class, new Type[]{Object.class}, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParameterizedTypeImpl(Class<?> cls, Type[] typeArr, Type type) {
        this.actualTypeArguments = typeArr;
        this.rawType = cls;
        this.ownerType = type != null ? type : cls.getDeclaringClass();
        if (cls.getTypeParameters().length != typeArr.length) {
            throw new IllegalArgumentException("Argument length mismatch");
        }
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return (Type[]) this.actualTypeArguments.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public Class<?> getRawType() {
        return this.rawType;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.ownerType;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType parameterizedType = (ParameterizedType) obj;
        return Objects.equals(this.ownerType, parameterizedType.getOwnerType()) && Objects.equals(this.rawType, parameterizedType.getRawType()) && Arrays.equals(this.actualTypeArguments, parameterizedType.getActualTypeArguments());
    }

    public int hashCode() {
        return (Arrays.hashCode(this.actualTypeArguments) ^ Objects.hashCode(this.ownerType)) ^ Objects.hashCode(this.rawType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.ownerType == null) {
            sb.append(this.rawType.getName());
        } else {
            if (this.ownerType instanceof Class) {
                sb.append(((Class) this.ownerType).getName());
            } else {
                sb.append(this.ownerType);
            }
            sb.append('$');
            if (this.ownerType instanceof ParameterizedTypeImpl) {
                sb.append(this.rawType.getName().replace(((ParameterizedTypeImpl) this.ownerType).rawType.getName() + "$", ""));
            } else {
                sb.append(this.rawType.getSimpleName());
            }
        }
        if (this.actualTypeArguments != null && this.actualTypeArguments.length > 0) {
            sb.append('<');
            boolean z = true;
            for (Type type : this.actualTypeArguments) {
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                sb.append(type.toString());
            }
            sb.append('>');
        }
        return sb.toString();
    }
}
