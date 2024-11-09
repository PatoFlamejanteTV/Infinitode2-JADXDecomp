package com.google.common.base;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Enums.class */
public final class Enums {
    private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> enumConstantCache = new WeakHashMap();

    private Enums() {
    }

    public static Field getField(Enum<?> r4) {
        try {
            return r4.getDeclaringClass().getDeclaredField(r4.name());
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> cls, String str) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(str);
        return Platform.getEnumIfPresent(cls, str);
    }

    private static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(Class<T> cls) {
        HashMap hashMap = new HashMap();
        Iterator it = EnumSet.allOf(cls).iterator();
        while (it.hasNext()) {
            Enum r0 = (Enum) it.next();
            hashMap.put(r0.name(), new WeakReference(r0));
        }
        enumConstantCache.put(cls, hashMap);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(Class<T> cls) {
        Map<String, WeakReference<? extends Enum<?>>> map;
        synchronized (enumConstantCache) {
            Map<String, WeakReference<? extends Enum<?>>> map2 = enumConstantCache.get(cls);
            Map<String, WeakReference<? extends Enum<?>>> map3 = map2;
            if (map2 == null) {
                map3 = populateCache(cls);
            }
            map = map3;
        }
        return map;
    }

    public static <T extends Enum<T>> Converter<String, T> stringConverter(Class<T> cls) {
        return new StringConverter(cls);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Enums$StringConverter.class */
    private static final class StringConverter<T extends Enum<T>> extends Converter<String, T> implements Serializable {
        private final Class<T> enumClass;
        private static final long serialVersionUID = 0;

        StringConverter(Class<T> cls) {
            this.enumClass = (Class) Preconditions.checkNotNull(cls);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public final T doForward(String str) {
            return (T) Enum.valueOf(this.enumClass, str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public final String doBackward(T t) {
            return t.name();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public final boolean equals(Object obj) {
            if (obj instanceof StringConverter) {
                return this.enumClass.equals(((StringConverter) obj).enumClass);
            }
            return false;
        }

        public final int hashCode() {
            return this.enumClass.hashCode();
        }

        public final String toString() {
            String name = this.enumClass.getName();
            return new StringBuilder(29 + String.valueOf(name).length()).append("Enums.stringConverter(").append(name).append(".class)").toString();
        }
    }
}
