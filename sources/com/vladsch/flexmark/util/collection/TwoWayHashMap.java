package com.vladsch.flexmark.util.collection;

import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/TwoWayHashMap.class */
public class TwoWayHashMap<F, S> {
    private HashMap<F, S> fToSMap = new HashMap<>();
    private HashMap<S, F> sToFMap = new HashMap<>();

    public void add(F f, S s) {
        this.fToSMap.put(f, s);
        this.sToFMap.put(s, f);
    }

    public S getSecond(F f) {
        return this.fToSMap.get(f);
    }

    public F getFirst(S s) {
        return this.sToFMap.get(s);
    }
}
