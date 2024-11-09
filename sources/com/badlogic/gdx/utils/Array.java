package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Predicate;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Array.class */
public class Array<T> implements Iterable<T> {
    public T[] items;
    public int size;
    public boolean ordered;
    private transient ArrayIterable<T> iterable;
    private transient Predicate.PredicateIterable<T> predicateIterable;

    public Array() {
        this(true, 16);
    }

    public Array(int i) {
        this(true, i);
    }

    public Array(boolean z, int i) {
        this.ordered = z;
        this.items = (T[]) new Object[i];
    }

    public Array(boolean z, int i, Class cls) {
        this.ordered = z;
        this.items = (T[]) ((Object[]) ArrayReflection.newInstance(cls, i));
    }

    public Array(Class cls) {
        this(true, 16, cls);
    }

    public Array(Array<? extends T> array) {
        this(array.ordered, array.size, array.items.getClass().getComponentType());
        this.size = array.size;
        System.arraycopy(array.items, 0, this.items, 0, this.size);
    }

    public Array(T[] tArr) {
        this(true, tArr, 0, tArr.length);
    }

    public Array(boolean z, T[] tArr, int i, int i2) {
        this(z, i2, tArr.getClass().getComponentType());
        this.size = i2;
        System.arraycopy(tArr, i, this.items, 0, this.size);
    }

    public void add(T t) {
        T[] tArr = this.items;
        if (this.size == tArr.length) {
            tArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        int i = this.size;
        this.size = i + 1;
        tArr[i] = t;
    }

    public void add(T t, T t2) {
        T[] tArr = this.items;
        if (this.size + 1 >= tArr.length) {
            tArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        tArr[this.size] = t;
        tArr[this.size + 1] = t2;
        this.size += 2;
    }

    public void add(T t, T t2, T t3) {
        T[] tArr = this.items;
        if (this.size + 2 >= tArr.length) {
            tArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        tArr[this.size] = t;
        tArr[this.size + 1] = t2;
        tArr[this.size + 2] = t3;
        this.size += 3;
    }

    public void add(T t, T t2, T t3, T t4) {
        T[] tArr = this.items;
        if (this.size + 3 >= tArr.length) {
            tArr = resize(Math.max(8, (int) (this.size * 1.8f)));
        }
        tArr[this.size] = t;
        tArr[this.size + 1] = t2;
        tArr[this.size + 2] = t3;
        tArr[this.size + 3] = t4;
        this.size += 4;
    }

    public void addAll(Array<? extends T> array) {
        addAll(array.items, 0, array.size);
    }

    public void addAll(Array<? extends T> array, int i, int i2) {
        if (i + i2 > array.size) {
            throw new IllegalArgumentException("start + count must be <= size: " + i + " + " + i2 + " <= " + array.size);
        }
        addAll(array.items, i, i2);
    }

    public void addAll(T... tArr) {
        addAll(tArr, 0, tArr.length);
    }

    public void addAll(T[] tArr, int i, int i2) {
        T[] tArr2 = this.items;
        int i3 = this.size + i2;
        if (i3 > tArr2.length) {
            tArr2 = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        System.arraycopy(tArr, i, tArr2, this.size, i2);
        this.size = i3;
    }

    public T get(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        return this.items[i];
    }

    public void set(int i, T t) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        this.items[i] = t;
    }

    public void insert(int i, T t) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        T[] tArr = this.items;
        if (this.size == tArr.length) {
            tArr = resize(Math.max(8, (int) (this.size * 1.75f)));
        }
        if (this.ordered) {
            System.arraycopy(tArr, i, tArr, i + 1, this.size - i);
        } else {
            tArr[this.size] = tArr[i];
        }
        this.size++;
        tArr[i] = t;
    }

    public void insertRange(int i, int i2) {
        if (i > this.size) {
            throw new IndexOutOfBoundsException("index can't be > size: " + i + " > " + this.size);
        }
        int i3 = this.size + i2;
        if (i3 > this.items.length) {
            this.items = resize(Math.max(Math.max(8, i3), (int) (this.size * 1.75f)));
        }
        T[] tArr = this.items;
        System.arraycopy(tArr, i, tArr, i + i2, this.size - i);
        this.size = i3;
    }

    public void swap(int i, int i2) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("first can't be >= size: " + i + " >= " + this.size);
        }
        if (i2 >= this.size) {
            throw new IndexOutOfBoundsException("second can't be >= size: " + i2 + " >= " + this.size);
        }
        T[] tArr = this.items;
        T t = tArr[i];
        tArr[i] = tArr[i2];
        tArr[i2] = t;
    }

    public boolean contains(@Null T t, boolean z) {
        T[] tArr = this.items;
        int i = this.size - 1;
        if (z || t == null) {
            while (i >= 0) {
                int i2 = i;
                i--;
                if (tArr[i2] == t) {
                    return true;
                }
            }
            return false;
        }
        while (i >= 0) {
            int i3 = i;
            i--;
            if (t.equals(tArr[i3])) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Array<? extends T> array, boolean z) {
        T[] tArr = array.items;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (!contains(tArr[i2], z)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsAny(Array<? extends T> array, boolean z) {
        T[] tArr = array.items;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (contains(tArr[i2], z)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(@Null T t, boolean z) {
        T[] tArr = this.items;
        if (z || t == null) {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (tArr[i2] == t) {
                    return i2;
                }
            }
            return -1;
        }
        int i3 = this.size;
        for (int i4 = 0; i4 < i3; i4++) {
            if (t.equals(tArr[i4])) {
                return i4;
            }
        }
        return -1;
    }

    public int lastIndexOf(@Null T t, boolean z) {
        T[] tArr = this.items;
        if (z || t == null) {
            for (int i = this.size - 1; i >= 0; i--) {
                if (tArr[i] == t) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = this.size - 1; i2 >= 0; i2--) {
            if (t.equals(tArr[i2])) {
                return i2;
            }
        }
        return -1;
    }

    public boolean removeValue(@Null T t, boolean z) {
        T[] tArr = this.items;
        if (z || t == null) {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (tArr[i2] == t) {
                    removeIndex(i2);
                    return true;
                }
            }
            return false;
        }
        int i3 = this.size;
        for (int i4 = 0; i4 < i3; i4++) {
            if (t.equals(tArr[i4])) {
                removeIndex(i4);
                return true;
            }
        }
        return false;
    }

    public T removeIndex(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.size);
        }
        T[] tArr = this.items;
        T t = tArr[i];
        this.size--;
        if (this.ordered) {
            System.arraycopy(tArr, i + 1, tArr, i, this.size - i);
        } else {
            tArr[i] = tArr[this.size];
        }
        tArr[this.size] = null;
        return t;
    }

    public void removeRange(int i, int i2) {
        int i3 = this.size;
        if (i2 >= i3) {
            throw new IndexOutOfBoundsException("end can't be >= size: " + i2 + " >= " + this.size);
        }
        if (i > i2) {
            throw new IndexOutOfBoundsException("start can't be > end: " + i + " > " + i2);
        }
        T[] tArr = this.items;
        int i4 = (i2 - i) + 1;
        int i5 = i3 - i4;
        if (this.ordered) {
            System.arraycopy(tArr, i + i4, tArr, i, i3 - (i + i4));
        } else {
            int max = Math.max(i5, i2 + 1);
            System.arraycopy(tArr, max, tArr, i, i3 - max);
        }
        for (int i6 = i5; i6 < i3; i6++) {
            tArr[i6] = null;
        }
        this.size = i5;
    }

    public boolean removeAll(Array<? extends T> array, boolean z) {
        int i = this.size;
        int i2 = i;
        T[] tArr = this.items;
        if (z) {
            int i3 = array.size;
            for (int i4 = 0; i4 < i3; i4++) {
                T t = array.get(i4);
                int i5 = 0;
                while (true) {
                    if (i5 >= i2) {
                        break;
                    }
                    if (t != tArr[i5]) {
                        i5++;
                    } else {
                        removeIndex(i5);
                        i2--;
                        break;
                    }
                }
            }
        } else {
            int i6 = array.size;
            for (int i7 = 0; i7 < i6; i7++) {
                T t2 = array.get(i7);
                int i8 = 0;
                while (true) {
                    if (i8 >= i2) {
                        break;
                    }
                    if (!t2.equals(tArr[i8])) {
                        i8++;
                    } else {
                        removeIndex(i8);
                        i2--;
                        break;
                    }
                }
            }
        }
        return i2 != i;
    }

    public T pop() {
        if (this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        this.size--;
        T t = this.items[this.size];
        this.items[this.size] = null;
        return t;
    }

    public T peek() {
        if (this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[this.size - 1];
    }

    public T first() {
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
        Arrays.fill(this.items, 0, this.size, (Object) null);
        this.size = 0;
    }

    public T[] shrink() {
        if (this.items.length != this.size) {
            resize(this.size);
        }
        return this.items;
    }

    public T[] ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + i);
        }
        int i2 = this.size + i;
        if (i2 > this.items.length) {
            resize(Math.max(Math.max(8, i2), (int) (this.size * 1.75f)));
        }
        return this.items;
    }

    public T[] setSize(int i) {
        truncate(i);
        if (i > this.items.length) {
            resize(Math.max(8, i));
        }
        this.size = i;
        return this.items;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T[] resize(int i) {
        T[] tArr = this.items;
        T[] tArr2 = (T[]) ((Object[]) ArrayReflection.newInstance(tArr.getClass().getComponentType(), i));
        System.arraycopy(tArr, 0, tArr2, 0, Math.min(this.size, tArr2.length));
        this.items = tArr2;
        return tArr2;
    }

    public void sort() {
        Sort.instance().sort(this.items, 0, this.size);
    }

    public void sort(Comparator<? super T> comparator) {
        Sort.instance().sort(this.items, comparator, 0, this.size);
    }

    public T selectRanked(Comparator<T> comparator, int i) {
        if (i <= 0) {
            throw new GdxRuntimeException("nth_lowest must be greater than 0, 1 = first, 2 = second...");
        }
        return (T) Select.instance().select(this.items, comparator, i, this.size);
    }

    public int selectRankedIndex(Comparator<T> comparator, int i) {
        if (i <= 0) {
            throw new GdxRuntimeException("nth_lowest must be greater than 0, 1 = first, 2 = second...");
        }
        return Select.instance().selectIndex(this.items, comparator, i, this.size);
    }

    public void reverse() {
        T[] tArr = this.items;
        int i = this.size - 1;
        int i2 = this.size / 2;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i - i3;
            T t = tArr[i3];
            tArr[i3] = tArr[i4];
            tArr[i4] = t;
        }
    }

    public void shuffle() {
        T[] tArr = this.items;
        for (int i = this.size - 1; i >= 0; i--) {
            int random = MathUtils.random(i);
            T t = tArr[i];
            tArr[i] = tArr[random];
            tArr[random] = t;
        }
    }

    @Override // java.lang.Iterable
    public ArrayIterator<T> iterator() {
        if (Collections.allocateIterators) {
            return new ArrayIterator<>(this, true);
        }
        if (this.iterable == null) {
            this.iterable = new ArrayIterable<>(this);
        }
        return this.iterable.iterator();
    }

    public Iterable<T> select(Predicate<T> predicate) {
        if (Collections.allocateIterators) {
            return new Predicate.PredicateIterable(this, predicate);
        }
        if (this.predicateIterable == null) {
            this.predicateIterable = new Predicate.PredicateIterable<>(this, predicate);
        } else {
            this.predicateIterable.set(this, predicate);
        }
        return this.predicateIterable;
    }

    public void truncate(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + i);
        }
        if (this.size <= i) {
            return;
        }
        for (int i2 = i; i2 < this.size; i2++) {
            this.items[i2] = null;
        }
        this.size = i;
    }

    @Null
    public T random() {
        if (this.size == 0) {
            return null;
        }
        return this.items[MathUtils.random(0, this.size - 1)];
    }

    public T[] toArray() {
        return (T[]) toArray(this.items.getClass().getComponentType());
    }

    public <V> V[] toArray(Class<V> cls) {
        V[] vArr = (V[]) ((Object[]) ArrayReflection.newInstance(cls, this.size));
        System.arraycopy(this.items, 0, vArr, 0, this.size);
        return vArr;
    }

    public int hashCode() {
        if (!this.ordered) {
            return super.hashCode();
        }
        T[] tArr = this.items;
        int i = 1;
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            i *= 31;
            T t = tArr[i3];
            if (t != null) {
                i += t.hashCode();
            }
        }
        return i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!this.ordered || !(obj instanceof Array)) {
            return false;
        }
        Array array = (Array) obj;
        if (!array.ordered || (i = this.size) != array.size) {
            return false;
        }
        T[] tArr = this.items;
        T[] tArr2 = array.items;
        for (int i2 = 0; i2 < i; i2++) {
            T t = tArr[i2];
            T t2 = tArr2[i2];
            if (t == null) {
                if (t2 != null) {
                    return false;
                }
            } else if (!t.equals(t2)) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIdentity(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!this.ordered || !(obj instanceof Array)) {
            return false;
        }
        Array array = (Array) obj;
        if (!array.ordered || (i = this.size) != array.size) {
            return false;
        }
        T[] tArr = this.items;
        T[] tArr2 = array.items;
        for (int i2 = 0; i2 < i; i2++) {
            if (tArr[i2] != tArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        T[] tArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(tArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(tArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public String toString(String str) {
        if (this.size == 0) {
            return "";
        }
        T[] tArr = this.items;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(tArr[0]);
        for (int i = 1; i < this.size; i++) {
            stringBuilder.append(str);
            stringBuilder.append(tArr[i]);
        }
        return stringBuilder.toString();
    }

    public static <T> Array<T> of(Class<T> cls) {
        return new Array<>(cls);
    }

    public static <T> Array<T> of(boolean z, int i, Class<T> cls) {
        return new Array<>(z, i, cls);
    }

    public static <T> Array<T> with(T... tArr) {
        return new Array<>(tArr);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Array$ArrayIterator.class */
    public static class ArrayIterator<T> implements Iterable<T>, Iterator<T> {
        private final Array<T> array;
        private final boolean allowRemove;
        int index;
        boolean valid;

        public ArrayIterator(Array<T> array) {
            this(array, true);
        }

        public ArrayIterator(Array<T> array, boolean z) {
            this.valid = true;
            this.array = array;
            this.allowRemove = z;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.valid) {
                return this.index < this.array.size;
            }
            throw new GdxRuntimeException("#iterator() cannot be used nested.");
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.index >= this.array.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if (!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            T[] tArr = this.array.items;
            int i = this.index;
            this.index = i + 1;
            return tArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.allowRemove) {
                throw new GdxRuntimeException("Remove not allowed.");
            }
            this.index--;
            this.array.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }

        @Override // java.lang.Iterable
        public ArrayIterator<T> iterator() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Array$ArrayIterable.class */
    public static class ArrayIterable<T> implements Iterable<T> {
        private final Array<T> array;
        private final boolean allowRemove;
        private transient ArrayIterator<T> iterator1;
        private transient ArrayIterator<T> iterator2;

        public ArrayIterable(Array<T> array) {
            this(array, true);
        }

        public ArrayIterable(Array<T> array, boolean z) {
            this.array = array;
            this.allowRemove = z;
        }

        @Override // java.lang.Iterable
        public ArrayIterator<T> iterator() {
            if (Collections.allocateIterators) {
                return new ArrayIterator<>(this.array, this.allowRemove);
            }
            if (this.iterator1 == null) {
                this.iterator1 = new ArrayIterator<>(this.array, this.allowRemove);
                this.iterator2 = new ArrayIterator<>(this.array, this.allowRemove);
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
