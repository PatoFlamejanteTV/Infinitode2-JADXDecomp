package com.esotericsoftware.kryo.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/Generics.class */
public interface Generics {
    void pushGenericType(GenericType genericType);

    void popGenericType();

    GenericType[] nextGenericTypes();

    Class nextGenericClass();

    int pushTypeVariables(GenericsHierarchy genericsHierarchy, GenericType[] genericTypeArr);

    void popTypeVariables(int i);

    Class resolveTypeVariable(TypeVariable typeVariable);

    int getGenericTypesSize();

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/Generics$GenericsHierarchy.class */
    public static class GenericsHierarchy {
        final int total;
        final int rootTotal;
        final int[] counts;
        final TypeVariable[] parameters;

        public GenericsHierarchy(Class cls) {
            Class superclass;
            IntArray intArray = new IntArray();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            Class cls2 = cls;
            do {
                for (TypeVariable typeVariable : cls2.getTypeParameters()) {
                    arrayList.add(typeVariable);
                    intArray.add(1);
                    Class cls3 = cls2;
                    while (true) {
                        Type genericSuperclass = cls3.getGenericSuperclass();
                        cls3 = cls3.getSuperclass();
                        if (genericSuperclass instanceof ParameterizedType) {
                            TypeVariable[] typeParameters = cls3.getTypeParameters();
                            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                            int length = actualTypeArguments.length;
                            for (int i2 = 0; i2 < length; i2++) {
                                if (actualTypeArguments[i2] == typeVariable) {
                                    typeVariable = typeParameters[i2];
                                    arrayList.add(typeVariable);
                                    intArray.incr(intArray.size - 1, 1);
                                }
                            }
                        }
                    }
                    i += intArray.peek();
                }
                superclass = cls2.getSuperclass();
                cls2 = superclass;
            } while (superclass != null);
            this.total = i;
            this.rootTotal = cls.getTypeParameters().length;
            this.counts = intArray.toArray();
            this.parameters = (TypeVariable[]) arrayList.toArray(new TypeVariable[arrayList.size()]);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            int[] iArr = this.counts;
            TypeVariable[] typeVariableArr = this.parameters;
            int i = 0;
            for (int i2 : iArr) {
                int i3 = i + i2;
                while (i < i3) {
                    if (sb.length() > 1) {
                        sb.append(", ");
                    }
                    GenericDeclaration genericDeclaration = typeVariableArr[i].getGenericDeclaration();
                    if (genericDeclaration instanceof Class) {
                        sb.append(((Class) genericDeclaration).getSimpleName());
                    } else {
                        sb.append(genericDeclaration);
                    }
                    sb.append('<');
                    sb.append(typeVariableArr[i].getName());
                    sb.append('>');
                    i++;
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/Generics$GenericType.class */
    public static class GenericType {
        Type type;
        GenericType[] arguments;

        public GenericType(Class cls, Class cls2, Type type) {
            initialize(cls, cls2, type);
        }

        private void initialize(Class cls, Class cls2, Type type) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                this.type = (Class) parameterizedType.getRawType();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                int length = actualTypeArguments.length;
                this.arguments = new GenericType[length];
                for (int i = 0; i < length; i++) {
                    this.arguments[i] = new GenericType(cls, cls2, actualTypeArguments[i]);
                }
                return;
            }
            if (type instanceof GenericArrayType) {
                int i2 = 1;
                while (true) {
                    Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                    type = genericComponentType;
                    if (!(genericComponentType instanceof GenericArrayType)) {
                        break;
                    } else {
                        i2++;
                    }
                }
                initialize(cls, cls2, type);
                Type resolveType = GenericsUtil.resolveType(cls, cls2, type);
                if (resolveType instanceof Class) {
                    if (i2 == 1) {
                        this.type = Array.newInstance((Class<?>) resolveType, 0).getClass();
                        return;
                    } else {
                        this.type = Array.newInstance((Class<?>) resolveType, new int[i2]).getClass();
                        return;
                    }
                }
                return;
            }
            this.type = GenericsUtil.resolveType(cls, cls2, type);
        }

        public Class resolve(Generics generics) {
            return this.type instanceof Class ? (Class) this.type : generics.resolveTypeVariable((TypeVariable) this.type);
        }

        public Type getType() {
            return this.type;
        }

        public GenericType[] getTypeParameters() {
            return this.arguments;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(32);
            boolean z = false;
            if (this.type instanceof Class) {
                Class cls = (Class) this.type;
                z = cls.isArray();
                sb.append((z ? Util.getElementClass(cls) : cls).getSimpleName());
                if (this.arguments != null) {
                    sb.append('<');
                    int length = this.arguments.length;
                    for (int i = 0; i < length; i++) {
                        if (i > 0) {
                            sb.append(", ");
                        }
                        sb.append(this.arguments[i].toString());
                    }
                    sb.append('>');
                }
            } else {
                sb.append(this.type.toString());
            }
            if (z) {
                int dimensionCount = Util.getDimensionCount((Class) this.type);
                for (int i2 = 0; i2 < dimensionCount; i2++) {
                    sb.append("[]");
                }
            }
            return sb.toString();
        }
    }
}
