package org.lwjgl.system;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/StructBuffer.class */
public abstract class StructBuffer<T extends Struct<T>, SELF extends StructBuffer<T, SELF>> extends CustomBuffer<SELF> implements Iterable<T> {
    protected abstract T getElementFactory();

    /* JADX INFO: Access modifiers changed from: protected */
    public StructBuffer(ByteBuffer byteBuffer, int i) {
        super(MemoryUtil.memAddress(byteBuffer), byteBuffer, -1, 0, i, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StructBuffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        super(j, byteBuffer, i, i2, i3, i4);
    }

    @Override // org.lwjgl.system.CustomBuffer
    public int sizeof() {
        return getElementFactory().sizeof();
    }

    public T get() {
        return (T) getElementFactory().create(this.address + (Integer.toUnsignedLong(nextGetIndex()) * r0.sizeof()), this.container);
    }

    public SELF get(T t) {
        int sizeof = getElementFactory().sizeof();
        MemoryUtil.memCopy(this.address + (Integer.toUnsignedLong(nextGetIndex()) * sizeof), t.address(), sizeof);
        return (SELF) self();
    }

    public SELF put(T t) {
        int sizeof = getElementFactory().sizeof();
        MemoryUtil.memCopy(t.address(), this.address + (Integer.toUnsignedLong(nextPutIndex()) * sizeof), sizeof);
        return (SELF) self();
    }

    public T get(int i) {
        return (T) getElementFactory().create(this.address + (Integer.toUnsignedLong(check(i, this.limit)) * r0.sizeof()), this.container);
    }

    public SELF get(int i, T t) {
        int sizeof = getElementFactory().sizeof();
        MemoryUtil.memCopy(this.address + (Checks.check(i, this.limit) * sizeof), t.address(), sizeof);
        return (SELF) self();
    }

    public SELF put(int i, T t) {
        int sizeof = getElementFactory().sizeof();
        MemoryUtil.memCopy(t.address(), this.address + (Checks.check(i, this.limit) * sizeof), sizeof);
        return (SELF) self();
    }

    public SELF apply(Consumer<T> consumer) {
        consumer.accept(get());
        return (SELF) self();
    }

    public SELF apply(int i, Consumer<T> consumer) {
        consumer.accept(get(i));
        return (SELF) self();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new StructIterator(this.address, this.container, getElementFactory(), this.position, this.limit);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/StructBuffer$StructIterator.class */
    private static class StructIterator<T extends Struct<T>> implements Iterator<T> {
        private long address;
        private ByteBuffer container;
        private T factory;
        private int index;
        private int fence;

        StructIterator(long j, ByteBuffer byteBuffer, T t, int i, int i2) {
            this.address = j;
            this.container = byteBuffer;
            this.factory = t;
            this.index = i;
            this.fence = i2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.fence;
        }

        @Override // java.util.Iterator
        public T next() {
            if (Checks.CHECKS && this.fence <= this.index) {
                throw new NoSuchElementException();
            }
            T t = this.factory;
            long j = this.address;
            int i = this.index;
            this.index = i + 1;
            return (T) t.create(j + (Integer.toUnsignedLong(i) * this.factory.sizeof()), this.container);
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            int i = this.index;
            try {
                int sizeof = this.factory.sizeof();
                while (i < this.fence) {
                    consumer.accept(this.factory.create(this.address + (Integer.toUnsignedLong(i) * sizeof), this.container));
                    i++;
                }
            } finally {
                this.index = i;
            }
        }
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        T elementFactory = getElementFactory();
        int i = this.limit;
        elementFactory.sizeof();
        for (int i2 = this.position; i2 < i; i2++) {
            consumer.accept(elementFactory.create(this.address + (Integer.toUnsignedLong(i2) * sizeof()), this.container));
        }
    }

    @Override // java.lang.Iterable
    public Spliterator<T> spliterator() {
        return new StructSpliterator(this.address, this.container, getElementFactory(), this.position, this.limit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/StructBuffer$StructSpliterator.class */
    public static class StructSpliterator<T extends Struct<T>> implements Spliterator<T> {
        private long address;
        private ByteBuffer container;
        private T factory;
        private int index;
        private int fence;

        StructSpliterator(long j, ByteBuffer byteBuffer, T t, int i, int i2) {
            this.address = j;
            this.container = byteBuffer;
            this.factory = t;
            this.index = i;
            this.fence = i2;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            if (this.index < this.fence) {
                T t = this.factory;
                long j = this.address;
                int i = this.index;
                this.index = i + 1;
                consumer.accept(t.create(j + (Integer.toUnsignedLong(i) * this.factory.sizeof()), this.container));
                return true;
            }
            return false;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            int i = this.index;
            int i2 = (i + this.fence) >>> 1;
            if (i >= i2) {
                return null;
            }
            long j = this.address;
            ByteBuffer byteBuffer = this.container;
            T t = this.factory;
            this.index = i2;
            return new StructSpliterator(j, byteBuffer, t, i, i2);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 17744;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            int i = this.index;
            try {
                int sizeof = this.factory.sizeof();
                while (i < this.fence) {
                    consumer.accept(this.factory.create(this.address + (Integer.toUnsignedLong(i) * sizeof), this.container));
                    i++;
                }
            } finally {
                this.index = i;
            }
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            throw new IllegalStateException();
        }
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    private static int check(int i, int i2) {
        if (Checks.CHECKS && (i < 0 || i2 <= i)) {
            throw new IndexOutOfBoundsException();
        }
        return i;
    }
}
