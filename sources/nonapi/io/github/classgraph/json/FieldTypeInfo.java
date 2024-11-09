package nonapi.io.github.classgraph.json;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/FieldTypeInfo.class */
public class FieldTypeInfo {
    final Field field;
    private final Type fieldTypePartiallyResolved;
    private final boolean hasUnresolvedTypeVariables;
    private final boolean isTypeVariable;
    private final PrimitiveType primitiveType;
    private Constructor<?> constructorForFieldTypeWithSizeHint;
    private Constructor<?> defaultConstructorForFieldType;

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/FieldTypeInfo$PrimitiveType.class */
    private enum PrimitiveType {
        NON_PRIMITIVE,
        INTEGER,
        LONG,
        SHORT,
        DOUBLE,
        FLOAT,
        BOOLEAN,
        BYTE,
        CHARACTER,
        CLASS_REF
    }

    private static boolean hasTypeVariables(Type type) {
        if ((type instanceof TypeVariable) || (type instanceof GenericArrayType)) {
            return true;
        }
        if (type instanceof ParameterizedType) {
            for (Type type2 : ((ParameterizedType) type).getActualTypeArguments()) {
                if (hasTypeVariables(type2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public FieldTypeInfo(Field field, Type type, ClassFieldCache classFieldCache) {
        this.field = field;
        this.fieldTypePartiallyResolved = type;
        this.isTypeVariable = type instanceof TypeVariable;
        this.hasUnresolvedTypeVariables = this.isTypeVariable || hasTypeVariables(type);
        if (((type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray())) || this.isTypeVariable) {
            this.primitiveType = PrimitiveType.NON_PRIMITIVE;
            return;
        }
        Class<?> rawType = JSONUtils.getRawType(type);
        if (rawType == Integer.TYPE) {
            this.primitiveType = PrimitiveType.INTEGER;
        } else if (rawType == Long.TYPE) {
            this.primitiveType = PrimitiveType.LONG;
        } else if (rawType == Short.TYPE) {
            this.primitiveType = PrimitiveType.SHORT;
        } else if (rawType == Double.TYPE) {
            this.primitiveType = PrimitiveType.DOUBLE;
        } else if (rawType == Float.TYPE) {
            this.primitiveType = PrimitiveType.FLOAT;
        } else if (rawType == Boolean.TYPE) {
            this.primitiveType = PrimitiveType.BOOLEAN;
        } else if (rawType == Byte.TYPE) {
            this.primitiveType = PrimitiveType.BYTE;
        } else if (rawType == Character.TYPE) {
            this.primitiveType = PrimitiveType.CHARACTER;
        } else if (rawType == Class.class) {
            this.primitiveType = PrimitiveType.CLASS_REF;
        } else {
            this.primitiveType = PrimitiveType.NON_PRIMITIVE;
        }
        if (!JSONUtils.isBasicValueType(rawType)) {
            if (Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType)) {
                this.constructorForFieldTypeWithSizeHint = classFieldCache.getConstructorWithSizeHintForConcreteTypeOf(rawType);
            }
            if (this.constructorForFieldTypeWithSizeHint == null) {
                this.defaultConstructorForFieldType = classFieldCache.getDefaultConstructorForConcreteTypeOf(rawType);
            }
        }
    }

    public Constructor<?> getConstructorForFieldTypeWithSizeHint(Type type, ClassFieldCache classFieldCache) {
        if (!this.isTypeVariable) {
            return this.constructorForFieldTypeWithSizeHint;
        }
        Class<?> rawType = JSONUtils.getRawType(type);
        if (!Collection.class.isAssignableFrom(rawType) && !Map.class.isAssignableFrom(rawType)) {
            return null;
        }
        return classFieldCache.getConstructorWithSizeHintForConcreteTypeOf(rawType);
    }

    public Constructor<?> getDefaultConstructorForFieldType(Type type, ClassFieldCache classFieldCache) {
        if (!this.isTypeVariable) {
            return this.defaultConstructorForFieldType;
        }
        return classFieldCache.getDefaultConstructorForConcreteTypeOf(JSONUtils.getRawType(type));
    }

    public Type getFullyResolvedFieldType(TypeResolutions typeResolutions) {
        if (!this.hasUnresolvedTypeVariables) {
            return this.fieldTypePartiallyResolved;
        }
        return typeResolutions.resolveTypeVariables(this.fieldTypePartiallyResolved);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFieldValue(Object obj, Object obj2) {
        try {
            if (obj2 == null) {
                if (this.primitiveType != PrimitiveType.NON_PRIMITIVE) {
                    throw new IllegalArgumentException("Tried to set primitive-typed field " + this.field.getDeclaringClass().getName() + "." + this.field.getName() + " to null value");
                }
                this.field.set(obj, null);
                return;
            }
            switch (this.primitiveType) {
                case NON_PRIMITIVE:
                    this.field.set(obj, obj2);
                    return;
                case CLASS_REF:
                    if (!(obj2 instanceof Class)) {
                        throw new IllegalArgumentException("Expected value of type Class<?>; got " + obj2.getClass().getName());
                    }
                    this.field.set(obj, obj2);
                    return;
                case INTEGER:
                    if (!(obj2 instanceof Integer)) {
                        throw new IllegalArgumentException("Expected value of type Integer; got " + obj2.getClass().getName());
                    }
                    this.field.setInt(obj, ((Integer) obj2).intValue());
                    return;
                case LONG:
                    if (!(obj2 instanceof Long)) {
                        throw new IllegalArgumentException("Expected value of type Long; got " + obj2.getClass().getName());
                    }
                    this.field.setLong(obj, ((Long) obj2).longValue());
                    return;
                case SHORT:
                    if (!(obj2 instanceof Short)) {
                        throw new IllegalArgumentException("Expected value of type Short; got " + obj2.getClass().getName());
                    }
                    this.field.setShort(obj, ((Short) obj2).shortValue());
                    return;
                case DOUBLE:
                    if (!(obj2 instanceof Double)) {
                        throw new IllegalArgumentException("Expected value of type Double; got " + obj2.getClass().getName());
                    }
                    this.field.setDouble(obj, ((Double) obj2).doubleValue());
                    return;
                case FLOAT:
                    if (!(obj2 instanceof Float)) {
                        throw new IllegalArgumentException("Expected value of type Float; got " + obj2.getClass().getName());
                    }
                    this.field.setFloat(obj, ((Float) obj2).floatValue());
                    return;
                case BOOLEAN:
                    if (!(obj2 instanceof Boolean)) {
                        throw new IllegalArgumentException("Expected value of type Boolean; got " + obj2.getClass().getName());
                    }
                    this.field.setBoolean(obj, ((Boolean) obj2).booleanValue());
                    return;
                case BYTE:
                    if (!(obj2 instanceof Byte)) {
                        throw new IllegalArgumentException("Expected value of type Byte; got " + obj2.getClass().getName());
                    }
                    this.field.setByte(obj, ((Byte) obj2).byteValue());
                    return;
                case CHARACTER:
                    if (!(obj2 instanceof Character)) {
                        throw new IllegalArgumentException("Expected value of type Character; got " + obj2.getClass().getName());
                    }
                    this.field.setChar(obj, ((Character) obj2).charValue());
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not set field " + this.field.getDeclaringClass().getName() + "." + this.field.getName(), e);
        }
    }

    public String toString() {
        return this.fieldTypePartiallyResolved + SequenceUtils.SPACE + this.field.getDeclaringClass().getName() + "." + this.field.getDeclaringClass().getName();
    }
}
