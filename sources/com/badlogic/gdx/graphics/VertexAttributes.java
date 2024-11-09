package com.badlogic.gdx.graphics;

import com.badlogic.gdx.utils.Collections;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/VertexAttributes.class */
public final class VertexAttributes implements Comparable<VertexAttributes>, Iterable<VertexAttribute> {
    private final VertexAttribute[] attributes;
    public final int vertexSize;
    private long mask = -1;
    private int boneWeightUnits = -1;
    private int textureCoordinates = -1;
    private ReadonlyIterable<VertexAttribute> iterable;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/VertexAttributes$Usage.class */
    public static final class Usage {
        public static final int Position = 1;
        public static final int ColorUnpacked = 2;
        public static final int ColorPacked = 4;
        public static final int Normal = 8;
        public static final int TextureCoordinates = 16;
        public static final int Generic = 32;
        public static final int BoneWeight = 64;
        public static final int Tangent = 128;
        public static final int BiNormal = 256;
    }

    public VertexAttributes(VertexAttribute... vertexAttributeArr) {
        if (vertexAttributeArr.length == 0) {
            throw new IllegalArgumentException("attributes must be >= 1");
        }
        VertexAttribute[] vertexAttributeArr2 = new VertexAttribute[vertexAttributeArr.length];
        for (int i = 0; i < vertexAttributeArr.length; i++) {
            vertexAttributeArr2[i] = vertexAttributeArr[i];
        }
        this.attributes = vertexAttributeArr2;
        this.vertexSize = calculateOffsets();
    }

    public final int getOffset(int i, int i2) {
        VertexAttribute findByUsage = findByUsage(i);
        return findByUsage == null ? i2 : findByUsage.offset / 4;
    }

    public final int getOffset(int i) {
        return getOffset(i, 0);
    }

    public final VertexAttribute findByUsage(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (get(i2).usage == i) {
                return get(i2);
            }
        }
        return null;
    }

    private int calculateOffsets() {
        int i = 0;
        for (int i2 = 0; i2 < this.attributes.length; i2++) {
            VertexAttribute vertexAttribute = this.attributes[i2];
            vertexAttribute.offset = i;
            i += vertexAttribute.getSizeInBytes();
        }
        return i;
    }

    public final int size() {
        return this.attributes.length;
    }

    public final VertexAttribute get(int i) {
        return this.attributes[i];
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.attributes.length; i++) {
            sb.append("(");
            sb.append(this.attributes[i].alias);
            sb.append(", ");
            sb.append(this.attributes[i].usage);
            sb.append(", ");
            sb.append(this.attributes[i].numComponents);
            sb.append(", ");
            sb.append(this.attributes[i].offset);
            sb.append(")");
            sb.append(SequenceUtils.EOL);
        }
        sb.append("]");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VertexAttributes)) {
            return false;
        }
        VertexAttributes vertexAttributes = (VertexAttributes) obj;
        if (this.attributes.length != vertexAttributes.attributes.length) {
            return false;
        }
        for (int i = 0; i < this.attributes.length; i++) {
            if (!this.attributes[i].equals(vertexAttributes.attributes[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        long length = 61 * this.attributes.length;
        for (int i = 0; i < this.attributes.length; i++) {
            length = (length * 61) + this.attributes[i].hashCode();
        }
        long j = length;
        return (int) (j ^ (j >> 32));
    }

    public final long getMask() {
        if (this.mask == -1) {
            long j = 0;
            for (int i = 0; i < this.attributes.length; i++) {
                j |= this.attributes[i].usage;
            }
            this.mask = j;
        }
        return this.mask;
    }

    public final long getMaskWithSizePacked() {
        return getMask() | (this.attributes.length << 32);
    }

    public final int getBoneWeights() {
        if (this.boneWeightUnits < 0) {
            this.boneWeightUnits = 0;
            for (int i = 0; i < this.attributes.length; i++) {
                VertexAttribute vertexAttribute = this.attributes[i];
                if (vertexAttribute.usage == 64) {
                    this.boneWeightUnits = Math.max(this.boneWeightUnits, vertexAttribute.unit + 1);
                }
            }
        }
        return this.boneWeightUnits;
    }

    public final int getTextureCoordinates() {
        if (this.textureCoordinates < 0) {
            this.textureCoordinates = 0;
            for (int i = 0; i < this.attributes.length; i++) {
                VertexAttribute vertexAttribute = this.attributes[i];
                if (vertexAttribute.usage == 16) {
                    this.textureCoordinates = Math.max(this.textureCoordinates, vertexAttribute.unit + 1);
                }
            }
        }
        return this.textureCoordinates;
    }

    @Override // java.lang.Comparable
    public final int compareTo(VertexAttributes vertexAttributes) {
        if (this.attributes.length != vertexAttributes.attributes.length) {
            return this.attributes.length - vertexAttributes.attributes.length;
        }
        long mask = getMask();
        long mask2 = vertexAttributes.getMask();
        if (mask != mask2) {
            return mask < mask2 ? -1 : 1;
        }
        for (int length = this.attributes.length - 1; length >= 0; length--) {
            VertexAttribute vertexAttribute = this.attributes[length];
            VertexAttribute vertexAttribute2 = vertexAttributes.attributes[length];
            if (vertexAttribute.usage != vertexAttribute2.usage) {
                return vertexAttribute.usage - vertexAttribute2.usage;
            }
            if (vertexAttribute.unit != vertexAttribute2.unit) {
                return vertexAttribute.unit - vertexAttribute2.unit;
            }
            if (vertexAttribute.numComponents != vertexAttribute2.numComponents) {
                return vertexAttribute.numComponents - vertexAttribute2.numComponents;
            }
            if (vertexAttribute.normalized != vertexAttribute2.normalized) {
                return vertexAttribute.normalized ? 1 : -1;
            }
            if (vertexAttribute.type != vertexAttribute2.type) {
                return vertexAttribute.type - vertexAttribute2.type;
            }
        }
        return 0;
    }

    @Override // java.lang.Iterable
    public final Iterator<VertexAttribute> iterator() {
        if (this.iterable == null) {
            this.iterable = new ReadonlyIterable<>(this.attributes);
        }
        return this.iterable.iterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/VertexAttributes$ReadonlyIterator.class */
    public static class ReadonlyIterator<T> implements Iterable<T>, Iterator<T> {
        private final T[] array;
        int index;
        boolean valid = true;

        public ReadonlyIterator(T[] tArr) {
            this.array = tArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.index < this.array.length;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.index >= this.array.length) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            T[] tArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return tArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new GdxRuntimeException("Remove not allowed.");
        }

        public void reset() {
            this.index = 0;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/VertexAttributes$ReadonlyIterable.class */
    private static class ReadonlyIterable<T> implements Iterable<T> {
        private final T[] array;
        private ReadonlyIterator iterator1;
        private ReadonlyIterator iterator2;

        public ReadonlyIterable(T[] tArr) {
            this.array = tArr;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            if (Collections.allocateIterators) {
                return new ReadonlyIterator(this.array);
            }
            if (this.iterator1 == null) {
                this.iterator1 = new ReadonlyIterator(this.array);
                this.iterator2 = new ReadonlyIterator(this.array);
            }
            if (!this.iterator1.valid) {
                this.iterator1.index = 0;
                this.iterator1.valid = true;
                this.iterator2.valid = false;
                return this.iterator1;
            }
            this.iterator2.index = 0;
            this.iterator2.valid = true;
            this.iterator1.valid = false;
            return this.iterator2;
        }
    }
}
