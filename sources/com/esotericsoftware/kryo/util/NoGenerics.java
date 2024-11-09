package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.util.Generics;
import java.lang.reflect.TypeVariable;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/NoGenerics.class */
public final class NoGenerics implements Generics {
    public static final Generics INSTANCE = new NoGenerics();

    private NoGenerics() {
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final void pushGenericType(Generics.GenericType genericType) {
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final void popGenericType() {
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final Generics.GenericType[] nextGenericTypes() {
        return null;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final Class nextGenericClass() {
        return null;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final int pushTypeVariables(Generics.GenericsHierarchy genericsHierarchy, Generics.GenericType[] genericTypeArr) {
        return 0;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final void popTypeVariables(int i) {
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final Class resolveTypeVariable(TypeVariable typeVariable) {
        return null;
    }

    @Override // com.esotericsoftware.kryo.util.Generics
    public final int getGenericTypesSize() {
        return 0;
    }
}
