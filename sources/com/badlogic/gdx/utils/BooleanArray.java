package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/BooleanArray.class */
public class BooleanArray {
    public boolean[] items;
    public int size;
    public boolean ordered;

    public BooleanArray() {
        this(true, 16);
    }

    public BooleanArray(int i) {
        this(true, i);
    }

    public BooleanArray(boolean z, int i) {
        this.ordered = z;
        this.items = new boolean[i];
    }

    public BooleanArray(BooleanArray booleanArray) {
        this.ordered = booleanArray.ordered;
        this.size = booleanArray.size;
        this.items = new boolean[this.size];
        System.arraycopy(booleanArray.items, 0, this.items, 0, this.size);
    }

    public BooleanArray(boolean[] zArr) {
        this(true, zArr, 0, zArr.length);
    }

    public BooleanArray(boolean z, boolean[] zArr, int i, int i2) {
        this(z, i2);
        this.size = i2;
        System.arraycopy(zArr, i, this.items, 0, i2);
    }

    public void add(boolean z) {
        boolean[] zArr = this.items;
        if (this.size == zArr.length) {
            zArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        int i = this.size;
        this.size = i + 1;
        zArr[i] = z;
    }

    public void add(boolean z, boolean z2) {
        boolean[] zArr = this.items;
        if (this.size + 1 >= zArr.length) {
            zArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        zArr[this.size] = z;
        zArr[this.size + 1] = z2;
        this.size += 2;
    }

    public void add(boolean z, boolean z2, boolean z3) {
        boolean[] zArr = this.items;
        if (this.size + 2 >= zArr.length) {
            zArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        zArr[this.size] = z;
        zArr[this.size + 1] = z2;
        zArr[this.size + 2] = z3;
        this.size += 3;
    }

    public void add(boolean z, boolean z2, boolean z3, boolean z4) {
        boolean[] zArr = this.items;
        if (this.size + 3 >= zArr.length) {
            zArr = resize(Math.max(8, (int) (this.size * 1.8f)));
        }
        zArr[this.size] = z;
        zArr[this.size + 1] = z2;
        zArr[this.size + 2] = z3;
        zArr[this.size + 3] = z4;
        this.size += 4;
    }

    public void addAll(BooleanArray booleanArray) {
        addAll(booleanArray.items, 0, booleanArray.size);
    }

    public void addAll(BooleanArray booleanArray, int i, int i2) {
        if (i + i2 > booleanArray.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + i + " + " + i2 + " <= " + booleanArray.size);
        }
        addAll(booleanArray.items, i, i2);
    }

    public void addAll(boolean... zArr) {
        addAll(zArr, 0, zArr.length);
    }

    public void addAll(boolean[] zArr, int i, int i2) {
        boolean[] zArr2 = this.items;
        int i3 = this.size + i2;
        if (i3 > zArr2.length) {
            zArr2 = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        System.arraycopy(zArr, i, zArr2, this.size, i2);
        this.size += i2;
    }

    public boolean get(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        return this.items[i];
    }

    public void set(int i, boolean z) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        this.items[i] = z;
    }

    public void insert(int i, boolean z) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        boolean[] zArr = this.items;
        if (this.size == zArr.length) {
            zArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        if (this.ordered) {
            System.arraycopy(zArr, i, zArr, i + 1, this.size - i);
        } else {
            zArr[this.size] = zArr[i];
        }
        this.size++;
        zArr[i] = z;
    }

    public void insertRange(int i, int i2) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        int i3 = this.size + i2;
        if (i3 > this.items.length) {
            this.items = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        boolean[] zArr = this.items;
        System.arraycopy(zArr, i, zArr, i + i2, this.size - i);
        this.size = i3;
    }

    public void swap(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("first can't be >= size: " + i + " >= " + this.size);
        }
        if (i2 >= this.size) {
            throw new IndexOutOfBoundsException("second can't be >= size: " + i2 + " >= " + this.size);
        }
        boolean[] zArr = this.items;
        boolean z = zArr[i];
        zArr[i] = zArr[i2];
        zArr[i2] = z;
    }

    public boolean removeIndex(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        boolean[] zArr = this.items;
        boolean z = zArr[i];
        this.size--;
        if (this.ordered) {
            System.arraycopy(zArr, i + 1, zArr, i, this.size - i);
        } else {
            zArr[i] = zArr[this.size];
        }
        return z;
    }

    public void removeRange(int i, int i2) {
        int i3 = this.size;
        if (i2 >= i3) {
            throw new IndexOutOfBoundsException("end can't be >= size: " + i2 + " >= " + this.size);
        }
        if (i > i2) {
            throw new IndexOutOfBoundsException("start can't be > end: " + i + " > " + i2);
        }
        int i4 = (i2 - i) + 1;
        int i5 = i3 - i4;
        if (this.ordered) {
            System.arraycopy(this.items, i + i4, this.items, i, i3 - (i + i4));
        } else {
            int max = Math.max(i5, i2 + 1);
            boolean[] zArr = this.items;
            System.arraycopy(zArr, max, zArr, i, i3 - max);
        }
        this.size = i5;
    }

    public boolean removeAll(BooleanArray booleanArray) {
        int i = this.size;
        int i2 = i;
        boolean[] zArr = this.items;
        int i3 = booleanArray.size;
        for (int i4 = 0; i4 < i3; i4++) {
            boolean z = booleanArray.get(i4);
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                if (z != zArr[i5]) {
                    i5++;
                } else {
                    removeIndex(i5);
                    i2--;
                    break;
                }
            }
        }
        return i2 != i;
    }

    public boolean pop() {
        boolean[] zArr = this.items;
        int i = this.size - 1;
        this.size = i;
        return zArr[i];
    }

    public boolean peek() {
        return this.items[this.size - 1];
    }

    public boolean first() {
        if (this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[0];
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean[] shrink() {
        if (this.items.length != this.size) {
            resize(this.size);
        }
        return this.items;
    }

    public boolean[] ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + i);
        }
        int i2 = this.size + i;
        if (i2 > this.items.length) {
            resize(Math.max(Math.max(8, i2), (int) (this.size * 1.75f)));
        }
        return this.items;
    }

    public boolean[] setSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + i);
        }
        if (i > this.items.length) {
            resize(Math.max(8, i));
        }
        this.size = i;
        return this.items;
    }

    protected boolean[] resize(int i) {
        boolean[] zArr = new boolean[i];
        System.arraycopy(this.items, 0, zArr, 0, Math.min(this.size, i));
        this.items = zArr;
        return zArr;
    }

    public void reverse() {
        boolean[] zArr = this.items;
        int i = this.size - 1;
        int i2 = this.size / 2;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i - i3;
            boolean z = zArr[i3];
            zArr[i3] = zArr[i4];
            zArr[i4] = z;
        }
    }

    public void shuffle() {
        boolean[] zArr = this.items;
        for (int i = this.size - 1; i >= 0; i--) {
            int random = MathUtils.random(i);
            boolean z = zArr[i];
            zArr[i] = zArr[random];
            zArr[random] = z;
        }
    }

    public void truncate(int i) {
        if (this.size > i) {
            this.size = i;
        }
    }

    public boolean random() {
        if (this.size == 0) {
            return false;
        }
        return this.items[MathUtils.random(0, this.size - 1)];
    }

    public boolean[] toArray() {
        boolean[] zArr = new boolean[this.size];
        System.arraycopy(this.items, 0, zArr, 0, this.size);
        return zArr;
    }

    public int hashCode() {
        if (!this.ordered) {
            return super.hashCode();
        }
        boolean[] zArr = this.items;
        int i = 1;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            i = (i * 31) + (zArr[i3] ? 1231 : 1237);
        }
        return i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!this.ordered || !(obj instanceof BooleanArray)) {
            return false;
        }
        BooleanArray booleanArray = (BooleanArray) obj;
        if (!booleanArray.ordered || (i = this.size) != booleanArray.size) {
            return false;
        }
        boolean[] zArr = this.items;
        boolean[] zArr2 = booleanArray.items;
        for (int i2 = 0; i2 < i; i2++) {
            if (zArr[i2] != zArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        boolean[] zArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(zArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(zArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public String toString(String str) {
        if (this.size == 0) {
            return "";
        }
        boolean[] zArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(zArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(str);
            stringBuilder.append(zArr[i]);
        }
        return stringBuilder.toString();
    }

    public static BooleanArray with(boolean... zArr) {
        return new BooleanArray(zArr);
    }
}
