package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ByteArray.class */
public class ByteArray {
    public byte[] items;
    public int size;
    public boolean ordered;

    public ByteArray() {
        this(true, 16);
    }

    public ByteArray(int i) {
        this(true, i);
    }

    public ByteArray(boolean z, int i) {
        this.ordered = z;
        this.items = new byte[i];
    }

    public ByteArray(ByteArray byteArray) {
        this.ordered = byteArray.ordered;
        this.size = byteArray.size;
        this.items = new byte[this.size];
        System.arraycopy(byteArray.items, 0, this.items, 0, this.size);
    }

    public ByteArray(byte[] bArr) {
        this(true, bArr, 0, bArr.length);
    }

    public ByteArray(boolean z, byte[] bArr, int i, int i2) {
        this(z, i2);
        this.size = i2;
        System.arraycopy(bArr, i, this.items, 0, i2);
    }

    public void add(byte b2) {
        byte[] bArr = this.items;
        if (this.size == bArr.length) {
            bArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        int i = this.size;
        this.size = i + 1;
        bArr[i] = b2;
    }

    public void add(byte b2, byte b3) {
        byte[] bArr = this.items;
        if (this.size + 1 >= bArr.length) {
            bArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        bArr[this.size] = b2;
        bArr[this.size + 1] = b3;
        this.size += 2;
    }

    public void add(byte b2, byte b3, byte b4) {
        byte[] bArr = this.items;
        if (this.size + 2 >= bArr.length) {
            bArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        bArr[this.size] = b2;
        bArr[this.size + 1] = b3;
        bArr[this.size + 2] = b4;
        this.size += 3;
    }

    public void add(byte b2, byte b3, byte b4, byte b5) {
        byte[] bArr = this.items;
        if (this.size + 3 >= bArr.length) {
            bArr = resize(Math.max(8, (int) (this.size * 1.8f)));
        }
        bArr[this.size] = b2;
        bArr[this.size + 1] = b3;
        bArr[this.size + 2] = b4;
        bArr[this.size + 3] = b5;
        this.size += 4;
    }

    public void addAll(ByteArray byteArray) {
        addAll(byteArray.items, 0, byteArray.size);
    }

    public void addAll(ByteArray byteArray, int i, int i2) {
        if (i + i2 > byteArray.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + i + " + " + i2 + " <= " + byteArray.size);
        }
        addAll(byteArray.items, i, i2);
    }

    public void addAll(byte... bArr) {
        addAll(bArr, 0, bArr.length);
    }

    public void addAll(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.items;
        int i3 = this.size + i2;
        if (i3 > bArr2.length) {
            bArr2 = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        System.arraycopy(bArr, i, bArr2, this.size, i2);
        this.size += i2;
    }

    public byte get(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        return this.items[i];
    }

    public void set(int i, byte b2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        this.items[i] = b2;
    }

    public void incr(int i, byte b2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        byte[] bArr = this.items;
        bArr[i] = (byte) (bArr[i] + b2);
    }

    public void incr(byte b2) {
        byte[] bArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            bArr[i3] = (byte) (bArr[i3] + b2);
        }
    }

    public void mul(int i, byte b2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        byte[] bArr = this.items;
        bArr[i] = (byte) (bArr[i] * b2);
    }

    public void mul(byte b2) {
        byte[] bArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2;
            bArr[i3] = (byte) (bArr[i3] * b2);
        }
    }

    public void insert(int i, byte b2) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        byte[] bArr = this.items;
        if (this.size == bArr.length) {
            bArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        if (this.ordered) {
            System.arraycopy(bArr, i, bArr, i + 1, this.size - i);
        } else {
            bArr[this.size] = bArr[i];
        }
        this.size++;
        bArr[i] = b2;
    }

    public void insertRange(int i, int i2) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        int i3 = this.size + i2;
        if (i3 > this.items.length) {
            this.items = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        byte[] bArr = this.items;
        System.arraycopy(bArr, i, bArr, i + i2, this.size - i);
        this.size = i3;
    }

    public void swap(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("first can't be >= size: " + i + " >= " + this.size);
        }
        if (i2 >= this.size) {
            throw new IndexOutOfBoundsException("second can't be >= size: " + i2 + " >= " + this.size);
        }
        byte[] bArr = this.items;
        byte b2 = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b2;
    }

    public boolean contains(byte b2) {
        int i = this.size - 1;
        byte[] bArr = this.items;
        while (i >= 0) {
            int i2 = i;
            i--;
            if (bArr[i2] == b2) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(byte b2) {
        byte[] bArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] == b2) {
                return i2;
            }
        }
        return -1;
    }

    public int lastIndexOf(byte b2) {
        byte[] bArr = this.items;
        for (int i = this.size - 1; i >= 0; i--) {
            if (bArr[i] == b2) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeValue(byte b2) {
        byte[] bArr = this.items;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] == b2) {
                removeIndex(i2);
                return true;
            }
        }
        return false;
    }

    public int removeIndex(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        byte[] bArr = this.items;
        byte b2 = bArr[i];
        this.size--;
        if (this.ordered) {
            System.arraycopy(bArr, i + 1, bArr, i, this.size - i);
        } else {
            bArr[i] = bArr[this.size];
        }
        return b2;
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
            byte[] bArr = this.items;
            System.arraycopy(bArr, max, bArr, i, i3 - max);
        }
        this.size = i5;
    }

    public boolean removeAll(ByteArray byteArray) {
        int i = this.size;
        int i2 = i;
        byte[] bArr = this.items;
        int i3 = byteArray.size;
        for (int i4 = 0; i4 < i3; i4++) {
            byte b2 = byteArray.get(i4);
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                if (b2 != bArr[i5]) {
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

    public byte pop() {
        byte[] bArr = this.items;
        int i = this.size - 1;
        this.size = i;
        return bArr[i];
    }

    public byte peek() {
        return this.items[this.size - 1];
    }

    public byte first() {
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

    public byte[] shrink() {
        if (this.items.length != this.size) {
            resize(this.size);
        }
        return this.items;
    }

    public byte[] ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + i);
        }
        int i2 = this.size + i;
        if (i2 > this.items.length) {
            resize(Math.max(Math.max(8, i2), (int) (this.size * 1.75f)));
        }
        return this.items;
    }

    public byte[] setSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + i);
        }
        if (i > this.items.length) {
            resize(Math.max(8, i));
        }
        this.size = i;
        return this.items;
    }

    protected byte[] resize(int i) {
        byte[] bArr = new byte[i];
        System.arraycopy(this.items, 0, bArr, 0, Math.min(this.size, i));
        this.items = bArr;
        return bArr;
    }

    public void sort() {
        Arrays.sort(this.items, 0, this.size);
    }

    public void reverse() {
        byte[] bArr = this.items;
        int i = this.size - 1;
        int i2 = this.size / 2;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i - i3;
            byte b2 = bArr[i3];
            bArr[i3] = bArr[i4];
            bArr[i4] = b2;
        }
    }

    public void shuffle() {
        byte[] bArr = this.items;
        for (int i = this.size - 1; i >= 0; i--) {
            int random = MathUtils.random(i);
            byte b2 = bArr[i];
            bArr[i] = bArr[random];
            bArr[random] = b2;
        }
    }

    public void truncate(int i) {
        if (this.size > i) {
            this.size = i;
        }
    }

    public byte random() {
        if (this.size == 0) {
            return (byte) 0;
        }
        return this.items[MathUtils.random(0, this.size - 1)];
    }

    public byte[] toArray() {
        byte[] bArr = new byte[this.size];
        System.arraycopy(this.items, 0, bArr, 0, this.size);
        return bArr;
    }

    public int hashCode() {
        if (!this.ordered) {
            return super.hashCode();
        }
        byte[] bArr = this.items;
        int i = 1;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            i = (i * 31) + bArr[i3];
        }
        return i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!this.ordered || !(obj instanceof ByteArray)) {
            return false;
        }
        ByteArray byteArray = (ByteArray) obj;
        if (!byteArray.ordered || (i = this.size) != byteArray.size) {
            return false;
        }
        byte[] bArr = this.items;
        byte[] bArr2 = byteArray.items;
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        byte[] bArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append((int) bArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append((int) bArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public String toString(String str) {
        if (this.size == 0) {
            return "";
        }
        byte[] bArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append((int) bArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(str);
            stringBuilder.append((int) bArr[i]);
        }
        return stringBuilder.toString();
    }

    public static ByteArray with(byte... bArr) {
        return new ByteArray(bArr);
    }
}
