package com.badlogic.gdx.ai.msg;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectSet;
import java.lang.Comparable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/msg/PriorityQueue.class */
public class PriorityQueue<E extends Comparable<E>> {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private static final double CAPACITY_RATIO_LOW = 1.5d;
    private static final double CAPACITY_RATIO_HI = 2.0d;
    private Object[] queue;
    private ObjectSet<E> set;
    private boolean uniqueness;
    private int size;

    public PriorityQueue() {
        this(11);
    }

    public PriorityQueue(int i) {
        this.size = 0;
        this.queue = new Object[i];
        this.set = new ObjectSet<>(i);
    }

    public boolean getUniqueness() {
        return this.uniqueness;
    }

    public void setUniqueness(boolean z) {
        this.uniqueness = z;
    }

    public boolean add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }
        if (this.uniqueness && !this.set.add(e)) {
            return false;
        }
        int i = this.size;
        if (i >= this.queue.length) {
            growToSize(i + 1);
        }
        this.size = i + 1;
        if (i == 0) {
            this.queue[0] = e;
            return true;
        }
        siftUp(i, e);
        return true;
    }

    public E peek() {
        if (this.size == 0) {
            return null;
        }
        return (E) this.queue[0];
    }

    public E get(int i) {
        if (i >= this.size) {
            return null;
        }
        return (E) this.queue[i];
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
        this.set.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public E poll() {
        if (this.size == 0) {
            return null;
        }
        int i = this.size - 1;
        this.size = i;
        E e = (E) this.queue[0];
        Comparable comparable = (Comparable) this.queue[i];
        this.queue[i] = null;
        if (i != 0) {
            siftDown(0, comparable);
        }
        if (this.uniqueness) {
            this.set.remove(e);
        }
        return e;
    }

    private void siftUp(int i, E e) {
        while (i > 0) {
            int i2 = (i - 1) >>> 1;
            Comparable comparable = (Comparable) this.queue[i2];
            if (e.compareTo(comparable) >= 0) {
                break;
            }
            this.queue[i] = comparable;
            i = i2;
        }
        this.queue[i] = e;
    }

    private void siftDown(int i, E e) {
        int i2 = this.size >>> 1;
        while (i < i2) {
            int i3 = (i << 1) + 1;
            Comparable comparable = (Comparable) this.queue[i3];
            int i4 = i3 + 1;
            if (i4 < this.size && comparable.compareTo((Comparable) this.queue[i4]) > 0) {
                i3 = i4;
                comparable = (Comparable) this.queue[i4];
            }
            if (e.compareTo(comparable) <= 0) {
                break;
            }
            this.queue[i] = comparable;
            i = i3;
        }
        this.queue[i] = e;
    }

    private void growToSize(int i) {
        if (i < 0) {
            throw new GdxRuntimeException("Capacity upper limit exceeded.");
        }
        int length = this.queue.length;
        int i2 = (int) (length < 64 ? (length + 1) * CAPACITY_RATIO_HI : length * CAPACITY_RATIO_LOW);
        int i3 = i2;
        if (i2 < 0) {
            i3 = Integer.MAX_VALUE;
        }
        if (i3 < i) {
            i3 = i;
        }
        Object[] objArr = new Object[i3];
        System.arraycopy(this.queue, 0, objArr, 0, this.size);
        this.queue = objArr;
    }
}
