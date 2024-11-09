package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.Array;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/Attributes.class */
public class Attributes implements Comparable<Attributes>, Iterable<Attribute>, Comparator<Attribute> {
    protected long mask;
    protected final Array<Attribute> attributes = new Array<>();
    protected boolean sorted = true;

    public final void sort() {
        if (!this.sorted) {
            this.attributes.sort(this);
            this.sorted = true;
        }
    }

    public final long getMask() {
        return this.mask;
    }

    public final Attribute get(long j) {
        if (has(j)) {
            for (int i = 0; i < this.attributes.size; i++) {
                if (this.attributes.get(i).type == j) {
                    return this.attributes.get(i);
                }
            }
            return null;
        }
        return null;
    }

    public final <T extends Attribute> T get(Class<T> cls, long j) {
        return (T) get(j);
    }

    public final Array<Attribute> get(Array<Attribute> array, long j) {
        for (int i = 0; i < this.attributes.size; i++) {
            if ((this.attributes.get(i).type & j) != 0) {
                array.add(this.attributes.get(i));
            }
        }
        return array;
    }

    public void clear() {
        this.mask = 0L;
        this.attributes.clear();
    }

    public int size() {
        return this.attributes.size;
    }

    private final void enable(long j) {
        this.mask |= j;
    }

    private final void disable(long j) {
        this.mask &= j ^ (-1);
    }

    public final void set(Attribute attribute) {
        int indexOf = indexOf(attribute.type);
        if (indexOf >= 0) {
            this.attributes.set(indexOf, attribute);
        } else {
            enable(attribute.type);
            this.attributes.add(attribute);
            this.sorted = false;
        }
        sort();
    }

    public final void set(Attribute attribute, Attribute attribute2) {
        set(attribute);
        set(attribute2);
    }

    public final void set(Attribute attribute, Attribute attribute2, Attribute attribute3) {
        set(attribute);
        set(attribute2);
        set(attribute3);
    }

    public final void set(Attribute attribute, Attribute attribute2, Attribute attribute3, Attribute attribute4) {
        set(attribute);
        set(attribute2);
        set(attribute3);
        set(attribute4);
    }

    public final void set(Attribute... attributeArr) {
        for (Attribute attribute : attributeArr) {
            set(attribute);
        }
    }

    public final void set(Iterable<Attribute> iterable) {
        Iterator<Attribute> it = iterable.iterator();
        while (it.hasNext()) {
            set(it.next());
        }
    }

    public final void remove(long j) {
        for (int i = this.attributes.size - 1; i >= 0; i--) {
            long j2 = this.attributes.get(i).type;
            if ((j & j2) == j2) {
                this.attributes.removeIndex(i);
                disable(j2);
                this.sorted = false;
            }
        }
        sort();
    }

    public final boolean has(long j) {
        return j != 0 && (this.mask & j) == j;
    }

    protected int indexOf(long j) {
        if (has(j)) {
            for (int i = 0; i < this.attributes.size; i++) {
                if (this.attributes.get(i).type == j) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    public final boolean same(Attributes attributes, boolean z) {
        if (attributes == this) {
            return true;
        }
        if (attributes == null || this.mask != attributes.mask) {
            return false;
        }
        if (!z) {
            return true;
        }
        sort();
        attributes.sort();
        for (int i = 0; i < this.attributes.size; i++) {
            if (!this.attributes.get(i).equals(attributes.attributes.get(i))) {
                return false;
            }
        }
        return true;
    }

    public final boolean same(Attributes attributes) {
        return same(attributes, false);
    }

    @Override // java.util.Comparator
    public final int compare(Attribute attribute, Attribute attribute2) {
        return (int) (attribute.type - attribute2.type);
    }

    @Override // java.lang.Iterable
    public final Iterator<Attribute> iterator() {
        return this.attributes.iterator();
    }

    public int attributesHash() {
        sort();
        int i = this.attributes.size;
        long j = 71 + this.mask;
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = (i2 * 7) & 65535;
            i2 = i4;
            j += this.mask * this.attributes.get(i3).hashCode() * i4;
        }
        long j2 = j;
        return (int) (j2 ^ (j2 >> 32));
    }

    public int hashCode() {
        return attributesHash();
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (!(obj instanceof Attributes)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return same((Attributes) obj, true);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attributes attributes) {
        if (attributes == this) {
            return 0;
        }
        if (this.mask != attributes.mask) {
            return this.mask < attributes.mask ? -1 : 1;
        }
        sort();
        attributes.sort();
        for (int i = 0; i < this.attributes.size; i++) {
            int compareTo = this.attributes.get(i).compareTo(attributes.attributes.get(i));
            if (compareTo != 0) {
                if (compareTo < 0) {
                    return -1;
                }
                return compareTo > 0 ? 1 : 0;
            }
        }
        return 0;
    }
}
