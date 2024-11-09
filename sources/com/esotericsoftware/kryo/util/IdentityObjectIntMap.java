package com.esotericsoftware.kryo.util;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/IdentityObjectIntMap.class */
public class IdentityObjectIntMap<K> extends ObjectIntMap<K> {
    public IdentityObjectIntMap() {
    }

    public IdentityObjectIntMap(int i) {
        super(i);
    }

    public IdentityObjectIntMap(int i, float f) {
        super(i, f);
    }

    public IdentityObjectIntMap(IdentityObjectIntMap<K> identityObjectIntMap) {
        super(identityObjectIntMap);
    }

    @Override // com.esotericsoftware.kryo.util.ObjectIntMap
    protected int place(K k) {
        return System.identityHashCode(k) & this.mask;
    }

    @Override // com.esotericsoftware.kryo.util.ObjectIntMap
    public int get(K k, int i) {
        int place = place(k);
        while (true) {
            int i2 = place;
            K k2 = this.keyTable[i2];
            if (k2 == null) {
                return i;
            }
            if (k2 == k) {
                return this.valueTable[i2];
            }
            place = (i2 + 1) & this.mask;
        }
    }

    @Override // com.esotericsoftware.kryo.util.ObjectIntMap
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

    @Override // com.esotericsoftware.kryo.util.ObjectIntMap
    public int hashCode() {
        int i = this.size;
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int length = kArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            K k = kArr[i2];
            if (k != null) {
                i += System.identityHashCode(k) + iArr[i2];
            }
        }
        return i;
    }
}
