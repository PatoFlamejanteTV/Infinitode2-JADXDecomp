package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/Attribute.class */
public abstract class Attribute implements Comparable<Attribute> {
    private static final Array<String> types = new Array<>();
    private static final int MAX_ATTRIBUTE_COUNT = 64;
    public final long type;
    private final int typeBit;

    public abstract Attribute copy();

    public static final long getAttributeType(String str) {
        for (int i = 0; i < types.size; i++) {
            if (types.get(i).compareTo(str) == 0) {
                return 1 << i;
            }
        }
        return 0L;
    }

    public static final String getAttributeAlias(long j) {
        int i = -1;
        while (j != 0) {
            i++;
            if (i >= 63 || ((j >> i) & 1) != 0) {
                break;
            }
        }
        if (i < 0 || i >= types.size) {
            return null;
        }
        return types.get(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final long register(String str) {
        long attributeType = getAttributeType(str);
        if (attributeType > 0) {
            return attributeType;
        }
        if (types.size >= 64) {
            throw new GdxRuntimeException("Cannot register " + str + ", maximum registered attribute count reached.");
        }
        types.add(str);
        return 1 << (types.size - 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Attribute(long j) {
        this.type = j;
        this.typeBit = Long.numberOfTrailingZeros(j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean equals(Attribute attribute) {
        return attribute.hashCode() == hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Attribute)) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        if (this.type != attribute.type) {
            return false;
        }
        return equals(attribute);
    }

    public String toString() {
        return getAttributeAlias(this.type);
    }

    public int hashCode() {
        return 7489 * this.typeBit;
    }
}
