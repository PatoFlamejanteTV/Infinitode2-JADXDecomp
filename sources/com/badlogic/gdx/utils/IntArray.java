package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/IntArray.class */
public class IntArray {
    public int[] items;
    public int size;
    public boolean ordered;

    public IntArray() {
        this(true, 16);
    }

    public IntArray(int i) {
        this(true, i);
    }

    public IntArray(boolean z, int i) {
        this.ordered = z;
        this.items = new int[i];
    }

    public IntArray(IntArray intArray) {
        this.ordered = intArray.ordered;
        this.size = intArray.size;
        this.items = new int[this.size];
        System.arraycopy(intArray.items, 0, this.items, 0, this.size);
    }

    public IntArray(int[] iArr) {
        this(true, iArr, 0, iArr.length);
    }

    public IntArray(boolean z, int[] iArr, int i, int i2) {
        this(z, i2);
        this.size = i2;
        System.arraycopy(iArr, i, this.items, 0, i2);
    }

    public void add(int i) {
        int[] iArr = this.items;
        if (this.size == iArr.length) {
            iArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        int i2 = this.size;
        this.size = i2 + 1;
        iArr[i2] = i;
    }

    public void add(int i, int i2) {
        int[] iArr = this.items;
        if (this.size + 1 >= iArr.length) {
            iArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        iArr[this.size] = i;
        iArr[this.size + 1] = i2;
        this.size += 2;
    }

    public void add(int i, int i2, int i3) {
        int[] iArr = this.items;
        if (this.size + 2 >= iArr.length) {
            iArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        iArr[this.size] = i;
        iArr[this.size + 1] = i2;
        iArr[this.size + 2] = i3;
        this.size += 3;
    }

    public void add(int i, int i2, int i3, int i4) {
        int[] iArr = this.items;
        if (this.size + 3 >= iArr.length) {
            iArr = resize(Math.max(8, (int) (this.size * 1.8f)));
        }
        iArr[this.size] = i;
        iArr[this.size + 1] = i2;
        iArr[this.size + 2] = i3;
        iArr[this.size + 3] = i4;
        this.size += 4;
    }

    public void addAll(IntArray intArray) {
        addAll(intArray.items, 0, intArray.size);
    }

    public void addAll(IntArray intArray, int i, int i2) {
        if (i + i2 > intArray.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + i + " + " + i2 + " <= " + intArray.size);
        }
        addAll(intArray.items, i, i2);
    }

    public void addAll(int... iArr) {
        addAll(iArr, 0, iArr.length);
    }

    public void addAll(int[] iArr, int i, int i2) {
        int[] iArr2 = this.items;
        int i3 = this.size + i2;
        if (i3 > iArr2.length) {
            iArr2 = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        System.arraycopy(iArr, i, iArr2, this.size, i2);
        this.size += i2;
    }

    public int get(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        return this.items[i];
    }

    public void set(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        this.items[i] = i2;
    }

    public void incr(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        int[] iArr = this.items;
        iArr[i] = iArr[i] + i2;
    }

    public void incr(int i) {
        int[] iArr = this.items;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3;
            iArr[i4] = iArr[i4] + i;
        }
    }

    public void mul(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        int[] iArr = this.items;
        iArr[i] = iArr[i] * i2;
    }

    public void mul(int i) {
        int[] iArr = this.items;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3;
            iArr[i4] = iArr[i4] * i;
        }
    }

    public void insert(int i, int i2) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        int[] iArr = this.items;
        if (this.size == iArr.length) {
            iArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        if (this.ordered) {
            System.arraycopy(iArr, i, iArr, i + 1, this.size - i);
        } else {
            iArr[this.size] = iArr[i];
        }
        this.size++;
        iArr[i] = i2;
    }

    public void insertRange(int i, int i2) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        int i3 = this.size + i2;
        if (i3 > this.items.length) {
            this.items = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        int[] iArr = this.items;
        System.arraycopy(iArr, i, iArr, i + i2, this.size - i);
        this.size = i3;
    }

    public void swap(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("first can't be >= size: " + i + " >= " + this.size);
        }
        if (i2 >= this.size) {
            throw new IndexOutOfBoundsException("second can't be >= size: " + i2 + " >= " + this.size);
        }
        int[] iArr = this.items;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    public boolean contains(int i) {
        int i2 = this.size - 1;
        int[] iArr = this.items;
        while (i2 >= 0) {
            int i3 = i2;
            i2--;
            if (iArr[i3] == i) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(int i) {
        int[] iArr = this.items;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3] == i) {
                return i3;
            }
        }
        return -1;
    }

    public int lastIndexOf(int i) {
        int[] iArr = this.items;
        for (int i2 = this.size - 1; i2 >= 0; i2--) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public boolean removeValue(int i) {
        int[] iArr = this.items;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3] == i) {
                removeIndex(i3);
                return true;
            }
        }
        return false;
    }

    public int removeIndex(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        int[] iArr = this.items;
        int i2 = iArr[i];
        this.size--;
        if (this.ordered) {
            System.arraycopy(iArr, i + 1, iArr, i, this.size - i);
        } else {
            iArr[i] = iArr[this.size];
        }
        return i2;
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
            int[] iArr = this.items;
            System.arraycopy(iArr, max, iArr, i, i3 - max);
        }
        this.size = i5;
    }

    public boolean removeAll(IntArray intArray) {
        int i = this.size;
        int i2 = i;
        int[] iArr = this.items;
        int i3 = intArray.size;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = intArray.get(i4);
            int i6 = 0;
            while (true) {
                if (i6 >= i2) {
                    break;
                }
                if (i5 != iArr[i6]) {
                    i6++;
                } else {
                    removeIndex(i6);
                    i2--;
                    break;
                }
            }
        }
        return i2 != i;
    }

    public int pop() {
        int[] iArr = this.items;
        int i = this.size - 1;
        this.size = i;
        return iArr[i];
    }

    public int peek() {
        return this.items[this.size - 1];
    }

    public int first() {
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

    public int[] shrink() {
        if (this.items.length != this.size) {
            resize(this.size);
        }
        return this.items;
    }

    public int[] ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + i);
        }
        int i2 = this.size + i;
        if (i2 > this.items.length) {
            resize(Math.max(Math.max(8, i2), (int) (this.size * 1.75f)));
        }
        return this.items;
    }

    public int[] setSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + i);
        }
        if (i > this.items.length) {
            resize(Math.max(8, i));
        }
        this.size = i;
        return this.items;
    }

    protected int[] resize(int i) {
        int[] iArr = new int[i];
        System.arraycopy(this.items, 0, iArr, 0, Math.min(this.size, i));
        this.items = iArr;
        return iArr;
    }

    public void sort() {
        Arrays.sort(this.items, 0, this.size);
    }

    public void reverse() {
        int[] iArr = this.items;
        int i = this.size - 1;
        int i2 = this.size / 2;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i - i3;
            int i5 = iArr[i3];
            iArr[i3] = iArr[i4];
            iArr[i4] = i5;
        }
    }

    public void shuffle() {
        int[] iArr = this.items;
        for (int i = this.size - 1; i >= 0; i--) {
            int random = MathUtils.random(i);
            int i2 = iArr[i];
            iArr[i] = iArr[random];
            iArr[random] = i2;
        }
    }

    public void truncate(int i) {
        if (this.size > i) {
            this.size = i;
        }
    }

    public int random() {
        if (this.size == 0) {
            return 0;
        }
        return this.items[MathUtils.random(0, this.size - 1)];
    }

    public int[] toArray() {
        int[] iArr = new int[this.size];
        System.arraycopy(this.items, 0, iArr, 0, this.size);
        return iArr;
    }

    public int hashCode() {
        if (!this.ordered) {
            return super.hashCode();
        }
        int[] iArr = this.items;
        int i = 1;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            i = (i * 31) + iArr[i3];
        }
        return i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!this.ordered || !(obj instanceof IntArray)) {
            return false;
        }
        IntArray intArray = (IntArray) obj;
        if (!intArray.ordered || (i = this.size) != intArray.size) {
            return false;
        }
        int[] iArr = this.items;
        int[] iArr2 = intArray.items;
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        int[] iArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(iArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(iArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public String toString(String str) {
        if (this.size == 0) {
            return "";
        }
        int[] iArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(iArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(str);
            stringBuilder.append(iArr[i]);
        }
        return stringBuilder.toString();
    }

    public static IntArray with(int... iArr) {
        return new IntArray(iArr);
    }
}
