package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IdentityMap.class */
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

    @Override // com.badlogic.gdx.utils.ObjectMap
    protected int place(K k) {
        return (int) ((System.identityHashCode(k) * (-7046029254386353131L)) >>> this.shift);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.badlogic.gdx.utils.ObjectMap
    public int locateKey(K k) {
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

    @Override // com.badlogic.gdx.utils.ObjectMap
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
