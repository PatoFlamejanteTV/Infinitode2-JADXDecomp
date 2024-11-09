package com.badlogic.gdx.utils;

import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LongQueue.class */
public class LongQueue {
    protected long[] values;
    protected int head;
    protected int tail;
    public int size;

    public LongQueue() {
        this(16);
    }

    public LongQueue(int i) {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.values = new long[i];
    }

    public void addLast(long j) {
        long[] jArr = this.values;
        if (this.size == jArr.length) {
            resize(jArr.length << 1);
            jArr = this.values;
        }
        int i = this.tail;
        this.tail = i + 1;
        jArr[i] = j;
        if (this.tail == jArr.length) {
            this.tail = 0;
        }
        this.size++;
    }

    public void addFirst(long j) {
        long[] jArr = this.values;
        if (this.size == jArr.length) {
            resize(jArr.length << 1);
            jArr = this.values;
        }
        int i = this.head - 1;
        if (i == -1) {
            i = jArr.length - 1;
        }
        jArr[i] = j;
        this.head = i;
        this.size++;
    }

    public void ensureCapacity(int i) {
        int i2 = this.size + i;
        if (this.values.length < i2) {
            resize(i2);
        }
    }

    protected void resize(int i) {
        long[] jArr = this.values;
        int i2 = this.head;
        int i3 = this.tail;
        long[] jArr2 = new long[i];
        if (i2 < i3) {
            System.arraycopy(jArr, i2, jArr2, 0, i3 - i2);
        } else if (this.size > 0) {
            int length = jArr.length - i2;
            System.arraycopy(jArr, i2, jArr2, 0, length);
            System.arraycopy(jArr, 0, jArr2, length, i3);
        }
        this.values = jArr2;
        this.head = 0;
        this.tail = this.size;
    }

    public long removeFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        long[] jArr = this.values;
        long j = jArr[this.head];
        this.head++;
        if (this.head == jArr.length) {
            this.head = 0;
        }
        this.size--;
        return j;
    }

    public long removeLast() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        long[] jArr = this.values;
        int i = this.tail - 1;
        if (i == -1) {
            i = jArr.length - 1;
        }
        long j = jArr[i];
        this.tail = i;
        this.size--;
        return j;
    }

    public int indexOf(long j) {
        if (this.size == 0) {
            return -1;
        }
        long[] jArr = this.values;
        int i = this.head;
        int i2 = this.tail;
        if (i < i2) {
            for (int i3 = i; i3 < i2; i3++) {
                if (jArr[i3] == j) {
                    return i3 - i;
                }
            }
            return -1;
        }
        int length = jArr.length;
        for (int i4 = i; i4 < length; i4++) {
            if (jArr[i4] == j) {
                return i4 - i;
            }
        }
        for (int i5 = 0; i5 < i2; i5++) {
            if (jArr[i5] == j) {
                return (i5 + jArr.length) - i;
            }
        }
        return -1;
    }

    public boolean removeValue(long j) {
        int indexOf = indexOf(j);
        if (indexOf == -1) {
            return false;
        }
        removeIndex(indexOf);
        return true;
    }

    public long removeIndex(int i) {
        long j;
        if (i < 0) {
            throw new IndexOutOfBoundsException("index can't be < 0: " + i);
        }
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        long[] jArr = this.values;
        int i2 = this.head;
        int i3 = this.tail;
        int i4 = i + i2;
        if (i2 < i3) {
            j = jArr[i4];
            System.arraycopy(jArr, i4 + 1, jArr, i4, i3 - i4);
            this.tail--;
        } else if (i4 >= jArr.length) {
            int length = i4 - jArr.length;
            j = jArr[length];
            System.arraycopy(jArr, length + 1, jArr, length, i3 - length);
            this.tail--;
        } else {
            j = jArr[i4];
            System.arraycopy(jArr, i2, jArr, i2 + 1, i4 - i2);
            this.head++;
            if (this.head == jArr.length) {
                this.head = 0;
            }
        }
        this.size--;
        return j;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public long first() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return this.values[this.head];
    }

    public long last() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        long[] jArr = this.values;
        int i = this.tail - 1;
        if (i == -1) {
            i = jArr.length - 1;
        }
        return jArr[i];
    }

    public long get(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("index can't be < 0: " + i);
        }
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        long[] jArr = this.values;
        int i2 = this.head + i;
        int i3 = i2;
        if (i2 >= jArr.length) {
            i3 -= jArr.length;
        }
        return jArr[i3];
    }

    public void clear() {
        if (this.size == 0) {
            return;
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        long[] jArr = this.values;
        int i = this.head;
        int i2 = this.tail;
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append('[');
        stringBuilder.append(jArr[i]);
        int i3 = i + 1;
        int length = jArr.length;
        while (true) {
            int i4 = i3 % length;
            if (i4 != i2) {
                stringBuilder.append(", ").append(jArr[i4]);
                i3 = i4 + 1;
                length = jArr.length;
            } else {
                stringBuilder.append(']');
                return stringBuilder.toString();
            }
        }
    }

    public String toString(String str) {
        if (this.size == 0) {
            return "";
        }
        long[] jArr = this.values;
        int i = this.head;
        int i2 = this.tail;
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append(jArr[i]);
        int i3 = i + 1;
        int length = jArr.length;
        while (true) {
            int i4 = i3 % length;
            if (i4 != i2) {
                stringBuilder.append(str).append(jArr[i4]);
                i3 = i4 + 1;
                length = jArr.length;
            } else {
                return stringBuilder.toString();
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        long[] jArr = this.values;
        int length = jArr.length;
        int i2 = this.head;
        int i3 = i + 1;
        for (int i4 = 0; i4 < i; i4++) {
            long j = jArr[i2];
            int i5 = i3;
            i3 = i5 + (((int) (i5 ^ (j >>> 32))) * 31);
            i2++;
            if (i2 == length) {
                i2 = 0;
            }
        }
        return i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LongQueue)) {
            return false;
        }
        LongQueue longQueue = (LongQueue) obj;
        int i = this.size;
        if (longQueue.size != i) {
            return false;
        }
        long[] jArr = this.values;
        int length = jArr.length;
        long[] jArr2 = longQueue.values;
        int length2 = jArr2.length;
        int i2 = this.head;
        int i3 = longQueue.head;
        for (int i4 = 0; i4 < i; i4++) {
            if (jArr[i2] != jArr2[i3]) {
                return false;
            }
            i2++;
            i3++;
            if (i2 == length) {
                i2 = 0;
            }
            if (i3 == length2) {
                i3 = 0;
            }
        }
        return true;
    }
}
