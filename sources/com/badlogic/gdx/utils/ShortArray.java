package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ShortArray.class */
public class ShortArray {
    public short[] items;
    public int size;
    public boolean ordered;

    public ShortArray() {
        this(true, 16);
    }

    public ShortArray(int i) {
        this(true, i);
    }

    public ShortArray(boolean z, int i) {
        this.ordered = z;
        this.items = new short[i];
    }

    public ShortArray(ShortArray shortArray) {
        this.ordered = shortArray.ordered;
        this.size = shortArray.size;
        this.items = new short[this.size];
        System.arraycopy(shortArray.items, 0, this.items, 0, this.size);
    }

    public ShortArray(short[] sArr) {
        this(true, sArr, 0, sArr.length);
    }

    public ShortArray(boolean z, short[] sArr, int i, int i2) {
        this(z, i2);
        this.size = i2;
        System.arraycopy(sArr, i, this.items, 0, i2);
    }

    public void add(int i) {
        short[] sArr = this.items;
        if (this.size == sArr.length) {
            sArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        int i2 = this.size;
        this.size = i2 + 1;
        sArr[i2] = (short) i;
    }

    public void add(short s) {
        short[] sArr = this.items;
        if (this.size == sArr.length) {
            sArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        int i = this.size;
        this.size = i + 1;
        sArr[i] = s;
    }

    public void add(short s, short s2) {
        short[] sArr = this.items;
        if (this.size + 1 >= sArr.length) {
            sArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        sArr[this.size] = s;
        sArr[this.size + 1] = s2;
        this.size += 2;
    }

    public void add(short s, short s2, short s3) {
        short[] sArr = this.items;
        if (this.size + 2 >= sArr.length) {
            sArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        sArr[this.size] = s;
        sArr[this.size + 1] = s2;
        sArr[this.size + 2] = s3;
        this.size += 3;
    }

    public void add(short s, short s2, short s3, short s4) {
        short[] sArr = this.items;
        if (this.size + 3 >= sArr.length) {
            sArr = resize(Math.max(8, (int) (this.size * 1.8f)));
        }
        sArr[this.size] = s;
        sArr[this.size + 1] = s2;
        sArr[this.size + 2] = s3;
        sArr[this.size + 3] = s4;
        this.size += 4;
    }

    public void addAll(ShortArray shortArray) {
        addAll(shortArray.items, 0, shortArray.size);
    }

    public void addAll(ShortArray shortArray, int i, int i2) {
        if (i + i2 > shortArray.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + i + " + " + i2 + " <= " + shortArray.size);
        }
        addAll(shortArray.items, i, i2);
    }

    public void addAll(short... sArr) {
        addAll(sArr, 0, sArr.length);
    }

    public void addAll(short[] sArr, int i, int i2) {
        short[] sArr2 = this.items;
        int i3 = this.size + i2;
        if (i3 > sArr2.length) {
            sArr2 = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        System.arraycopy(sArr, i, sArr2, this.size, i2);
        this.size += i2;
    }

    public short get(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        return this.items[i];
    }

    public void set(int i, short s) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        this.items[i] = s;
    }

    public void incr(int i, short s) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        short[] sArr = this.items;
        sArr[i] = (short) (sArr[i] + s);
    }

    public void incr(short s) {
        short[] sArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            sArr[i3] = (short) (sArr[i3] + s);
        }
    }

    public void mul(int i, short s) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        short[] sArr = this.items;
        sArr[i] = (short) (sArr[i] * s);
    }

    public void mul(short s) {
        short[] sArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            sArr[i3] = (short) (sArr[i3] * s);
        }
    }

    public void insert(int i, short s) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        short[] sArr = this.items;
        if (this.size == sArr.length) {
            sArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        if (this.ordered) {
            System.arraycopy(sArr, i, sArr, i + 1, this.size - i);
        } else {
            sArr[this.size] = sArr[i];
        }
        this.size++;
        sArr[i] = s;
    }

    public void insertRange(int i, int i2) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        int i3 = this.size + i2;
        if (i3 > this.items.length) {
            this.items = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        short[] sArr = this.items;
        System.arraycopy(sArr, i, sArr, i + i2, this.size - i);
        this.size = i3;
    }

    public void swap(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("first can't be >= size: " + i + " >= " + this.size);
        }
        if (i2 >= this.size) {
            throw new IndexOutOfBoundsException("second can't be >= size: " + i2 + " >= " + this.size);
        }
        short[] sArr = this.items;
        short s = sArr[i];
        sArr[i] = sArr[i2];
        sArr[i2] = s;
    }

    public boolean contains(short s) {
        int i = this.size - 1;
        short[] sArr = this.items;
        while (i >= 0) {
            int i2 = i;
            i--;
            if (sArr[i2] == s) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(short s) {
        short[] sArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (sArr[i2] == s) {
                return i2;
            }
        }
        return -1;
    }

    public int lastIndexOf(char c) {
        short[] sArr = this.items;
        for (int i = this.size - 1; i >= 0; i--) {
            if (sArr[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeValue(short s) {
        short[] sArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (sArr[i2] == s) {
                removeIndex(i2);
                return true;
            }
        }
        return false;
    }

    public short removeIndex(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        short[] sArr = this.items;
        short s = sArr[i];
        this.size--;
        if (this.ordered) {
            System.arraycopy(sArr, i + 1, sArr, i, this.size - i);
        } else {
            sArr[i] = sArr[this.size];
        }
        return s;
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
            short[] sArr = this.items;
            System.arraycopy(sArr, max, sArr, i, i3 - max);
        }
        this.size = i5;
    }

    public boolean removeAll(ShortArray shortArray) {
        int i = this.size;
        int i2 = i;
        short[] sArr = this.items;
        int i3 = shortArray.size;
        for (int i4 = 0; i4 < i3; i4++) {
            short s = shortArray.get(i4);
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                if (s != sArr[i5]) {
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

    public short pop() {
        short[] sArr = this.items;
        int i = this.size - 1;
        this.size = i;
        return sArr[i];
    }

    public short peek() {
        return this.items[this.size - 1];
    }

    public short first() {
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

    public short[] shrink() {
        if (this.items.length != this.size) {
            resize(this.size);
        }
        return this.items;
    }

    public short[] ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + i);
        }
        int i2 = this.size + i;
        if (i2 > this.items.length) {
            resize(Math.max(Math.max(8, i2), (int) (this.size * 1.75f)));
        }
        return this.items;
    }

    public short[] setSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + i);
        }
        if (i > this.items.length) {
            resize(Math.max(8, i));
        }
        this.size = i;
        return this.items;
    }

    protected short[] resize(int i) {
        short[] sArr = new short[i];
        System.arraycopy(this.items, 0, sArr, 0, Math.min(this.size, i));
        this.items = sArr;
        return sArr;
    }

    public void sort() {
        Arrays.sort(this.items, 0, this.size);
    }

    public void reverse() {
        short[] sArr = this.items;
        int i = this.size - 1;
        int i2 = this.size / 2;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i - i3;
            short s = sArr[i3];
            sArr[i3] = sArr[i4];
            sArr[i4] = s;
        }
    }

    public void shuffle() {
        short[] sArr = this.items;
        for (int i = this.size - 1; i >= 0; i--) {
            int random = MathUtils.random(i);
            short s = sArr[i];
            sArr[i] = sArr[random];
            sArr[random] = s;
        }
    }

    public void truncate(int i) {
        if (this.size > i) {
            this.size = i;
        }
    }

    public short random() {
        if (this.size == 0) {
            return (short) 0;
        }
        return this.items[MathUtils.random(0, this.size - 1)];
    }

    public short[] toArray() {
        short[] sArr = new short[this.size];
        System.arraycopy(this.items, 0, sArr, 0, this.size);
        return sArr;
    }

    public int hashCode() {
        if (!this.ordered) {
            return super.hashCode();
        }
        short[] sArr = this.items;
        int i = 1;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            i = (i * 31) + sArr[i3];
        }
        return i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!this.ordered || !(obj instanceof ShortArray)) {
            return false;
        }
        ShortArray shortArray = (ShortArray) obj;
        if (!shortArray.ordered || (i = this.size) != shortArray.size) {
            return false;
        }
        short[] sArr = this.items;
        short[] sArr2 = shortArray.items;
        for (int i2 = 0; i2 < i; i2++) {
            if (sArr[i2] != sArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        short[] sArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append((int) sArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append((int) sArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public String toString(String str) {
        if (this.size == 0) {
            return "";
        }
        short[] sArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append((int) sArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(str);
            stringBuilder.append((int) sArr[i]);
        }
        return stringBuilder.toString();
    }

    public static ShortArray with(short... sArr) {
        return new ShortArray(sArr);
    }
}
