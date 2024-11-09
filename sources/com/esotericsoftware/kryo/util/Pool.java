package com.esotericsoftware.kryo.util;

import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/Pool.class */
public abstract class Pool<T> {
    private final Queue<T> freeObjects;
    private int peak;

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/Pool$Poolable.class */
    public interface Poolable {
        void reset();
    }

    protected abstract T create();

    public Pool(boolean z, boolean z2) {
        this(z, z2, Integer.MAX_VALUE);
    }

    public Pool(boolean z, boolean z2, final int i) {
        ArrayDeque<T> arrayDeque;
        if (z) {
            arrayDeque = new LinkedBlockingQueue<T>(i) { // from class: com.esotericsoftware.kryo.util.Pool.1
                @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue, java.util.concurrent.BlockingQueue
                public boolean add(T t) {
                    return super.offer(t);
                }
            };
        } else if (z2) {
            arrayDeque = new LinkedList<T>() { // from class: com.esotericsoftware.kryo.util.Pool.2
                @Override // java.util.LinkedList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque, java.util.Queue
                public boolean add(T t) {
                    if (size() >= i) {
                        return false;
                    }
                    super.add(t);
                    return true;
                }
            };
        } else {
            arrayDeque = new ArrayDeque<T>() { // from class: com.esotericsoftware.kryo.util.Pool.3
                @Override // java.util.ArrayDeque, java.util.Deque, java.util.Queue
                public boolean offer(T t) {
                    if (size() >= i) {
                        return false;
                    }
                    super.offer(t);
                    return true;
                }
            };
        }
        this.freeObjects = z2 ? new SoftReferenceQueue<>(arrayDeque) : arrayDeque;
    }

    public T obtain() {
        T poll = this.freeObjects.poll();
        return poll != null ? poll : create();
    }

    public void free(T t) {
        if (t == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        reset(t);
        if (!this.freeObjects.offer(t) && (this.freeObjects instanceof SoftReferenceQueue)) {
            ((SoftReferenceQueue) this.freeObjects).cleanOne();
            this.freeObjects.offer(t);
        }
        this.peak = Math.max(this.peak, this.freeObjects.size());
    }

    protected void reset(T t) {
        if (t instanceof Poolable) {
            ((Poolable) t).reset();
        }
    }

    public void clear() {
        this.freeObjects.clear();
    }

    public void clean() {
        if (this.freeObjects instanceof SoftReferenceQueue) {
            ((SoftReferenceQueue) this.freeObjects).clean();
        }
    }

    public int getFree() {
        return this.freeObjects.size();
    }

    public int getPeak() {
        return this.peak;
    }

    public void resetPeak() {
        this.peak = 0;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/Pool$SoftReferenceQueue.class */
    static class SoftReferenceQueue<T> implements Queue<T> {
        private final Queue<SoftReference<T>> delegate;

        public SoftReferenceQueue(Queue<SoftReference<T>> queue) {
            this.delegate = queue;
        }

        @Override // java.util.Queue
        public T poll() {
            T t;
            do {
                SoftReference<T> poll = this.delegate.poll();
                if (poll == null) {
                    return null;
                }
                t = poll.get();
            } while (t == null);
            return t;
        }

        @Override // java.util.Queue
        public boolean offer(T t) {
            return this.delegate.add(new SoftReference<>(t));
        }

        @Override // java.util.Collection
        public int size() {
            return this.delegate.size();
        }

        @Override // java.util.Collection
        public void clear() {
            this.delegate.clear();
        }

        void cleanOne() {
            Iterator<SoftReference<T>> it = this.delegate.iterator();
            while (it.hasNext()) {
                if (it.next().get() == null) {
                    it.remove();
                    return;
                }
            }
        }

        void clean() {
            this.delegate.removeIf(softReference -> {
                return softReference.get() == null;
            });
        }

        @Override // java.util.Queue, java.util.Collection
        public boolean add(T t) {
            return false;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return false;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return null;
        }

        @Override // java.util.Queue
        public T remove() {
            return null;
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return null;
        }

        @Override // java.util.Queue
        public T element() {
            return null;
        }

        @Override // java.util.Queue
        public T peek() {
            return null;
        }

        @Override // java.util.Collection
        public <E> E[] toArray(E[] eArr) {
            return null;
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            return false;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            return false;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends T> collection) {
            return false;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            return false;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            return false;
        }
    }
}
