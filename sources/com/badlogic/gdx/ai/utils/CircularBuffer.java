package com.badlogic.gdx.ai.utils;

import com.badlogic.gdx.utils.reflect.ArrayReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/CircularBuffer.class */
public class CircularBuffer<T> {
    private T[] items;
    private boolean resizable;
    private int head;
    private int tail;
    private int size;

    public CircularBuffer() {
        this(16, true);
    }

    public CircularBuffer(int i) {
        this(i, true);
    }

    public CircularBuffer(int i, boolean z) {
        this.items = (T[]) new Object[i];
        this.resizable = z;
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public boolean store(T t) {
        if (this.size == this.items.length) {
            if (!this.resizable) {
                return false;
            }
            resize(Math.max(8, (int) (this.items.length * 1.75f)));
        }
        this.size++;
        T[] tArr = this.items;
        int i = this.tail;
        this.tail = i + 1;
        tArr[i] = t;
        if (this.tail == this.items.length) {
            this.tail = 0;
            return true;
        }
        return true;
    }

    public T read() {
        if (this.size > 0) {
            this.size--;
            T t = this.items[this.head];
            this.items[this.head] = null;
            int i = this.head + 1;
            this.head = i;
            if (i == this.items.length) {
                this.head = 0;
            }
            return t;
        }
        return null;
    }

    public void clear() {
        T[] tArr = this.items;
        if (this.tail > this.head) {
            int i = this.head;
            int i2 = this.tail;
            do {
                int i3 = i;
                i++;
                tArr[i3] = null;
            } while (i < i2);
        } else if (this.size > 0) {
            int length = tArr.length;
            for (int i4 = this.head; i4 < length; i4++) {
                tArr[i4] = null;
            }
            int i5 = this.tail;
            for (int i6 = 0; i6 < i5; i6++) {
                tArr[i6] = null;
            }
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.items.length;
    }

    public int size() {
        return this.size;
    }

    public boolean isResizable() {
        return this.resizable;
    }

    public void setResizable(boolean z) {
        this.resizable = z;
    }

    public void ensureCapacity(int i) {
        int i2 = this.size + i;
        if (this.items.length < i2) {
            resize(i2);
        }
    }

    protected void resize(int i) {
        T[] tArr = (T[]) ((Object[]) ArrayReflection.newInstance(this.items.getClass().getComponentType(), i));
        if (this.tail > this.head) {
            System.arraycopy(this.items, this.head, tArr, 0, this.size);
        } else if (this.size > 0) {
            System.arraycopy(this.items, this.head, tArr, 0, this.items.length - this.head);
            System.arraycopy(this.items, 0, tArr, this.items.length - this.head, this.tail);
        }
        this.head = 0;
        this.tail = this.size;
        this.items = tArr;
    }
}
