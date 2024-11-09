package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.Generics;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/DefaultGenerics.class */
public final class DefaultGenerics implements Generics {
    private final Kryo kryo;
    private int genericTypesSize;
    private int argumentsSize;
    private Generics.GenericType[] genericTypes = new Generics.GenericType[16];
    private int[] depths = new int[16];
    private Type[] arguments = new Type[16];

    public DefaultGenerics(Kryo kryo) {
        this.kryo = kryo;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final void pushGenericType(Generics.GenericType genericType) {
        int i = this.genericTypesSize;
        if (i + 1 == this.genericTypes.length) {
            Generics.GenericType[] genericTypeArr = new Generics.GenericType[this.genericTypes.length << 1];
            System.arraycopy(this.genericTypes, 0, genericTypeArr, 0, i);
            this.genericTypes = genericTypeArr;
            int[] iArr = new int[this.depths.length << 1];
            System.arraycopy(this.depths, 0, iArr, 0, i);
            this.depths = iArr;
        }
        this.genericTypesSize = i + 1;
        this.genericTypes[i] = genericType;
        this.depths[i] = this.kryo.getDepth();
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final void popGenericType() {
        int i = this.genericTypesSize;
        if (i == 0) {
            return;
        }
        int i2 = i - 1;
        if (this.depths[i2] < this.kryo.getDepth()) {
            return;
        }
        this.genericTypes[i2] = null;
        this.genericTypesSize = i2;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final Generics.GenericType[] nextGenericTypes() {
        int i = this.genericTypesSize;
        if (i > 0) {
            int i2 = i - 1;
            Generics.GenericType genericType = this.genericTypes[i2];
            if (genericType.arguments != null && this.depths[i2] == this.kryo.getDepth() - 1) {
                pushGenericType(genericType.arguments[genericType.arguments.length - 1]);
                return genericType.arguments;
            }
            return null;
        }
        return null;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final Class nextGenericClass() {
        Generics.GenericType[] nextGenericTypes = nextGenericTypes();
        if (nextGenericTypes == null) {
            return null;
        }
        return nextGenericTypes[0].resolve(this);
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final int pushTypeVariables(Generics.GenericsHierarchy genericsHierarchy, Generics.GenericType[] genericTypeArr) {
        if (genericsHierarchy.total == 0 || genericsHierarchy.rootTotal > genericTypeArr.length || genericTypeArr.length > genericsHierarchy.counts.length) {
            return 0;
        }
        int i = this.argumentsSize;
        int i2 = i + genericsHierarchy.total;
        if (i2 > this.arguments.length) {
            Type[] typeArr = new Type[Math.max(i2, this.arguments.length << 1)];
            System.arraycopy(this.arguments, 0, typeArr, 0, i);
            this.arguments = typeArr;
        }
        int[] iArr = genericsHierarchy.counts;
        TypeVariable[] typeVariableArr = genericsHierarchy.parameters;
        int i3 = 0;
        int length = genericTypeArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            Generics.GenericType genericType = genericTypeArr[i4];
            Class resolve = genericType.resolve(this);
            if (resolve != null) {
                int i5 = iArr[i4];
                if (genericType == null) {
                    i3 += i5;
                } else {
                    int i6 = i3 + i5;
                    while (i3 < i6) {
                        this.arguments[this.argumentsSize] = typeVariableArr[i3];
                        this.arguments[this.argumentsSize + 1] = resolve;
                        this.argumentsSize += 2;
                        i3++;
                    }
                }
            }
        }
        return this.argumentsSize - i;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final void popTypeVariables(int i) {
        int i2 = this.argumentsSize;
        int i3 = i2 - i;
        this.argumentsSize = i3;
        while (i3 < i2) {
            int i4 = i3;
            i3++;
            this.arguments[i4] = null;
        }
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final Class resolveTypeVariable(TypeVariable typeVariable) {
        for (int i = this.argumentsSize - 2; i >= 0; i -= 2) {
            Type type = this.arguments[i];
            if (type == typeVariable || type.equals(typeVariable)) {
                return (Class) this.arguments[i + 1];
            }
        }
        return null;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final int getGenericTypesSize() {
        return this.genericTypesSize;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.argumentsSize; i += 2) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(((TypeVariable) this.arguments[i]).getName());
            sb.append("=");
            sb.append(((Class) this.arguments[i + 1]).getSimpleName());
        }
        return sb.toString();
    }
}
