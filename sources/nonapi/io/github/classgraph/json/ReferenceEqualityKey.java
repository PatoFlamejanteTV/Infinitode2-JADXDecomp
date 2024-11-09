package nonapi.io.github.classgraph.json;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/ReferenceEqualityKey.class */
public class ReferenceEqualityKey<K> {
    private final K wrappedKey;

    public ReferenceEqualityKey(K k) {
        this.wrappedKey = k;
    }

    public K get() {
        return this.wrappedKey;
    }

    public int hashCode() {
        K k = this.wrappedKey;
        if (k == null) {
            return 0;
        }
        return System.identityHashCode(k);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ReferenceEqualityKey) && this.wrappedKey == ((ReferenceEqualityKey) obj).wrappedKey;
    }

    public String toString() {
        K k = this.wrappedKey;
        return k == null ? "null" : k.toString();
    }
}
