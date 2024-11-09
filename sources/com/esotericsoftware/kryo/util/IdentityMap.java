package com.esotericsoftware.kryo.util;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/IdentityMap.class */
public class IdentityMap<K, V> extends ObjectMap<K, V> {
    public IdentityMap() {
    }

    public IdentityMap(int i) {
        super(i);
    }

    public IdentityMap(int i, float f) {
        super(i, f);
    }

    public IdentityMap(IdentityMap<K, V> identityMap) {
        super(identityMap);
    }

    @Override // com.esotericsoftware.kryo.util.ObjectMap
    protected int place(K k) {
        return System.identityHashCode(k) & this.mask;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.esotericsoftware.kryo.util.ObjectMap
    public <T extends K> V get(T t) {
        int place = place(t);
        while (true) {
            int i = place;
            K k = this.keyTable[i];
            if (k == null) {
                return null;
            }
            if (k == t) {
                return this.valueTable[i];
            }
            place = (i + 1) & this.mask;
        }
    }

    @Override // com.esotericsoftware.kryo.util.ObjectMap
    public V get(K k, V v) {
        int place = place(k);
        while (true) {
            int i = place;
            K k2 = this.keyTable[i];
            if (k2 == null) {
                return v;
            }
            if (k2 == k) {
                return this.valueTable[i];
            }
            place = (i + 1) & this.mask;
        }
    }

    @Override // com.esotericsoftware.kryo.util.ObjectMap
    int locateKey(K k) {
        if (k == null) {
            throw new IllegalArgumentException("key cannot be null.");
        }
        K[] kArr = this.keyTable;
        int place = place(k);
        while (true) {
            int i = place;
            K k2 = kArr[i];
            if (k2 == null) {
                return -(i + 1);
            }
            if (k2 == k) {
                return i;
            }
            place = (i + 1) & this.mask;
        }
    }

    @Override // com.esotericsoftware.kryo.util.ObjectMap
    public int hashCode() {
        int i = this.size;
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k = kArr[i2];
            if (k != null) {
                i += System.identityHashCode(k);
                V v = vArr[i2];
                if (v != null) {
                    i += v.hashCode();
                }
            }
        }
        return i;
    }
}
