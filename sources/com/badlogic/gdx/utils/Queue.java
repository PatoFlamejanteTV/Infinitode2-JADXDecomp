package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.reflect.ArrayReflection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Queue.class */
public class Queue<T> implements Iterable<T> {
    protected T[] values;
    protected int head;
    protected int tail;
    public int size;
    private transient QueueIterable iterable;

    public Queue() {
        this(16);
    }

    public Queue(int i) {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.values = (T[]) new Object[i];
    }

    public Queue(int i, Class<T> cls) {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.values = (T[]) ((Object[]) ArrayReflection.newInstance(cls, i));
    }

    public void addLast(@Null T t) {
        T[] tArr = this.values;
        if (this.size == tArr.length) {
            resize(tArr.length << 1);
            tArr = this.values;
        }
        int i = this.tail;
        this.tail = i + 1;
        tArr[i] = t;
        if (this.tail == tArr.length) {
            this.tail = 0;
        }
        this.size++;
    }

    public void addFirst(@Null T t) {
        T[] tArr = this.values;
        if (this.size == tArr.length) {
            resize(tArr.length << 1);
            tArr = this.values;
        }
        int i = this.head - 1;
        if (i == -1) {
            i = tArr.length - 1;
        }
        tArr[i] = t;
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
        T[] tArr = this.values;
        int i2 = this.head;
        int i3 = this.tail;
        T[] tArr2 = (T[]) ((Object[]) ArrayReflection.newInstance(tArr.getClass().getComponentType(), i));
        if (i2 < i3) {
            System.arraycopy(tArr, i2, tArr2, 0, i3 - i2);
        } else if (this.size > 0) {
            int length = tArr.length - i2;
            System.arraycopy(tArr, i2, tArr2, 0, length);
            System.arraycopy(tArr, 0, tArr2, length, i3);
        }
        this.values = tArr2;
        this.head = 0;
        this.tail = this.size;
    }

    public T removeFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        T[] tArr = this.values;
        T t = tArr[this.head];
        tArr[this.head] = null;
        this.head++;
        if (this.head == tArr.length) {
            this.head = 0;
        }
        this.size--;
        return t;
    }

    public T removeLast() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        T[] tArr = this.values;
        int i = this.tail - 1;
        if (i == -1) {
            i = tArr.length - 1;
        }
        T t = tArr[i];
        tArr[i] = null;
        this.tail = i;
        this.size--;
        return t;
    }

    public int indexOf(T t, boolean z) {
        if (this.size == 0) {
            return -1;
        }
        T[] tArr = this.values;
        int i = this.head;
        int i2 = this.tail;
        if (z || t == null) {
            if (i < i2) {
                for (int i3 = i; i3 < i2; i3++) {
                    if (tArr[i3] == t) {
                        return i3 - i;
                    }
                }
                return -1;
            }
            int length = tArr.length;
            for (int i4 = i; i4 < length; i4++) {
                if (tArr[i4] == t) {
                    return i4 - i;
                }
            }
            for (int i5 = 0; i5 < i2; i5++) {
                if (tArr[i5] == t) {
                    return (i5 + tArr.length) - i;
                }
            }
            return -1;
        }
        if (i < i2) {
            for (int i6 = i; i6 < i2; i6++) {
                if (t.equals(tArr[i6])) {
                    return i6 - i;
                }
            }
            return -1;
        }
        int length2 = tArr.length;
        for (int i7 = i; i7 < length2; i7++) {
            if (t.equals(tArr[i7])) {
                return i7 - i;
            }
        }
        for (int i8 = 0; i8 < i2; i8++) {
            if (t.equals(tArr[i8])) {
                return (i8 + tArr.length) - i;
            }
        }
        return -1;
    }

    public boolean removeValue(T t, boolean z) {
        int indexOf = indexOf(t, z);
        if (indexOf == -1) {
            return false;
        }
        removeIndex(indexOf);
        return true;
    }

    public T removeIndex(int i) {
        T t;
        if (i < 0) {
            throw new IndexOutOfBoundsException("index can't be < 0: " + i);
        }
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        T[] tArr = this.values;
        int i2 = this.head;
        int i3 = this.tail;
        int i4 = i + i2;
        if (i2 < i3) {
            t = tArr[i4];
            System.arraycopy(tArr, i4 + 1, tArr, i4, i3 - i4);
            tArr[i3] = null;
            this.tail--;
        } else if (i4 >= tArr.length) {
            int length = i4 - tArr.length;
            t = tArr[length];
            System.arraycopy(tArr, length + 1, tArr, length, i3 - length);
            this.tail--;
        } else {
            t = tArr[i4];
            System.arraycopy(tArr, i2, tArr, i2 + 1, i4 - i2);
            tArr[i2] = null;
            this.head++;
            if (this.head == tArr.length) {
                this.head = 0;
            }
        }
        this.size--;
        return t;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T first() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return this.values[this.head];
    }

    public T last() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        T[] tArr = this.values;
        int i = this.tail - 1;
        if (i == -1) {
            i = tArr.length - 1;
        }
        return tArr[i];
    }

    public T get(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("index can't be < 0: " + i);
        }
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        T[] tArr = this.values;
        int i2 = this.head + i;
        int i3 = i2;
        if (i2 >= tArr.length) {
            i3 -= tArr.length;
        }
        return tArr[i3];
    }

    public void clear() {
        if (this.size == 0) {
            return;
        }
        T[] tArr = this.values;
        int i = this.head;
        int i2 = this.tail;
        if (i < i2) {
            for (int i3 = i; i3 < i2; i3++) {
                tArr[i3] = null;
            }
        } else {
            for (int i4 = i; i4 < tArr.length; i4++) {
                tArr[i4] = null;
            }
            for (int i5 = 0; i5 < i2; i5++) {
                tArr[i5] = null;
            }
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        if (Collections.allocateIterators) {
            return new QueueIterator(this, true);
        }
        if (this.iterable == null) {
            this.iterable = new QueueIterable(this);
        }
        return this.iterable.iterator();
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        T[] tArr = this.values;
        int i = this.head;
        int i2 = this.tail;
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append('[');
        stringBuilder.append(tArr[i]);
        int i3 = i + 1;
        int length = tArr.length;
        while (true) {
            int i4 = i3 % length;
            if (i4 != i2) {
                stringBuilder.append(", ").append(tArr[i4]);
                i3 = i4 + 1;
                length = tArr.length;
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
        T[] tArr = this.values;
        int i = this.head;
        int i2 = this.tail;
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append(tArr[i]);
        int i3 = i + 1;
        int length = tArr.length;
        while (true) {
            int i4 = i3 % length;
            if (i4 != i2) {
                stringBuilder.append(str).append(tArr[i4]);
                i3 = i4 + 1;
                length = tArr.length;
            } else {
                return stringBuilder.toString();
            }
        }
    }

    public int hashCode() {
        int i = this.size;
        T[] tArr = this.values;
        int length = tArr.length;
        int i2 = this.head;
        int i3 = i + 1;
        for (int i4 = 0; i4 < i; i4++) {
            T t = tArr[i2];
            i3 *= 31;
            if (t != null) {
                i3 += t.hashCode();
            }
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
        if (obj == null || !(obj instanceof Queue)) {
            return false;
        }
        Queue queue = (Queue) obj;
        int i = this.size;
        if (queue.size != i) {
            return false;
        }
        T[] tArr = this.values;
        int length = tArr.length;
        T[] tArr2 = queue.values;
        int length2 = tArr2.length;
        int i2 = this.head;
        int i3 = queue.head;
        for (int i4 = 0; i4 < i; i4++) {
            T t = tArr[i2];
            T t2 = tArr2[i3];
            if (t == null) {
                if (t2 != null) {
                    return false;
                }
            } else if (!t.equals(t2)) {
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

    public boolean equalsIdentity(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Queue)) {
            return false;
        }
        Queue queue = (Queue) obj;
        int i = this.size;
        if (queue.size != i) {
            return false;
        }
        T[] tArr = this.values;
        int length = tArr.length;
        T[] tArr2 = queue.values;
        int length2 = tArr2.length;
        int i2 = this.head;
        int i3 = queue.head;
        for (int i4 = 0; i4 < i; i4++) {
            if (tArr[i2] != tArr2[i3]) {
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Queue$QueueIterator.class */
    public static class QueueIterator<T> implements Iterable<T>, Iterator<T> {
        private final Queue<T> queue;
        private final boolean allowRemove;
        int index;
        boolean valid;

        public QueueIterator(Queue<T> queue) {
            this(queue, true);
        }

        public QueueIterator(Queue<T> queue, boolean z) {
            this.valid = true;
            this.queue = queue;
            this.allowRemove = z;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.index < this.queue.size;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.index >= this.queue.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            Queue<T> queue = this.queue;
            int i = this.index;
            this.index = i + 1;
            return queue.get(i);
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.allowRemove) {
                throw new GdxRuntimeException("Remove not allowed.");
            }
            this.index--;
            this.queue.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Queue$QueueIterable.class */
    public static class QueueIterable<T> implements Iterable<T> {
        private final Queue<T> queue;
        private final boolean allowRemove;
        private QueueIterator iterator1;
        private QueueIterator iterator2;

        public QueueIterable(Queue<T> queue) {
            this(queue, true);
        }

        public QueueIterable(Queue<T> queue, boolean z) {
            this.queue = queue;
            this.allowRemove = z;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            if (Collections.allocateIterators) {
                return new QueueIterator(this.queue, this.allowRemove);
            }
            if (this.iterator1 == null) {
                this.iterator1 = new QueueIterator(this.queue, this.allowRemove);
                this.iterator2 = new QueueIterator(this.queue, this.allowRemove);
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
