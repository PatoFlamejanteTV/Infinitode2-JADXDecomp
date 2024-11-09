package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.KryoException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/GenericsUtil.class */
public class GenericsUtil {
    public static Type resolveType(Class cls, Class cls2, Type type) {
        if (type instanceof Class) {
            return type;
        }
        if (type instanceof TypeVariable) {
            return resolveTypeVariable(cls, cls2, type, true);
        }
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            int i = 1;
            while (true) {
                Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                type = genericComponentType;
                if (!(genericComponentType instanceof GenericArrayType)) {
                    break;
                }
                i++;
            }
            Type resolveType = resolveType(cls, cls2, type);
            if (!(resolveType instanceof Class)) {
                return type;
            }
            if (i == 1) {
                return Array.newInstance((Class<?>) resolveType, 0).getClass();
            }
            return Array.newInstance((Class<?>) resolveType, new int[i]).getClass();
        }
        if (type instanceof WildcardType) {
            Type type2 = ((WildcardType) type).getUpperBounds()[0];
            if (type2 != Object.class) {
                return resolveType(cls, cls2, type2);
            }
            Type[] lowerBounds = ((WildcardType) type).getLowerBounds();
            if (lowerBounds.length != 0) {
                return resolveType(cls, cls2, lowerBounds[0]);
            }
            return Object.class;
        }
        throw new KryoException("Unable to resolve type: " + type);
    }

    private static Type resolveTypeVariable(Class cls, Class cls2, Type type, boolean z) {
        Type genericSuperclass = cls2.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return type;
        }
        Class superclass = cls2.getSuperclass();
        if (superclass != cls) {
            Type resolveTypeVariable = resolveTypeVariable(cls, superclass, type, false);
            if (resolveTypeVariable instanceof Class) {
                return resolveTypeVariable;
            }
            type = resolveTypeVariable;
        }
        String obj = type.toString();
        TypeVariable[] typeParameters = superclass.getTypeParameters();
        int length = typeParameters.length;
        for (int i = 0; i < length; i++) {
            if (typeParameters[i].getName().equals(obj)) {
                Type type2 = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[i];
                if (type2 instanceof Class) {
                    return type2;
                }
                if (!(type2 instanceof ParameterizedType) && !(type2 instanceof GenericArrayType)) {
                    if (type2 instanceof TypeVariable) {
                        return z ? type : type2;
                    }
                }
                return resolveType(cls, cls2, type2);
            }
        }
        return type;
    }

    public static Type[] resolveTypeParameters(Class cls, Class cls2, Type type) {
        Type genericComponentType;
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            int length = actualTypeArguments.length;
            Type[] typeArr = new Type[length];
            for (int i = 0; i < length; i++) {
                typeArr[i] = resolveType(cls, cls2, actualTypeArguments[i]);
            }
            return typeArr;
        }
        if (!(type instanceof GenericArrayType)) {
            return null;
        }
        do {
            genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            type = genericComponentType;
        } while (genericComponentType instanceof GenericArrayType);
        return resolveTypeParameters(cls, cls2, type);
    }
}
