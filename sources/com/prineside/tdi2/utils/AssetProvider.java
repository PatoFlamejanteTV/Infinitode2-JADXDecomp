package com.prineside.tdi2.utils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AssetProvider.class */
public interface AssetProvider<T> {
    T get(String str);

    default T getDefault() {
        return get("default");
    }
}
