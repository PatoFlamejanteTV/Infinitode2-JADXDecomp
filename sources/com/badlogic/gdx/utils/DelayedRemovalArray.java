package com.badlogic.gdx.utils;

import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/DelayedRemovalArray.class */
public class DelayedRemovalArray<T> extends Array<T> {
    private int iterating;
    private IntArray remove;
    private int clear;

    public DelayedRemovalArray() {
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(Array array) {
        super(array);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(boolean z, int i, Class cls) {
        super(z, i, cls);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(boolean z, int i) {
        super(z, i);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(boolean z, T[] tArr, int i, int i2) {
        super(z, tArr, i, i2);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(Class cls) {
        super(cls);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(int i) {
        super(i);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(T[] tArr) {
        super(tArr);
        this.remove = new IntArray(0);
    }

    public void begin() {
        this.iterating++;
    }

    public void end() {
        if (this.iterating == 0) {
            throw new IllegalStateException("begin must be called before end.");
        }
        this.iterating--;
        if (this.iterating == 0) {
            if (this.clear > 0 && this.clear == this.size) {
                this.remove.clear();
                clear();
            } else {
                int i = this.remove.size;
                for (int i2 = 0; i2 < i; i2++) {
                    int pop = this.remove.pop();
                    if (pop >= this.clear) {
                        removeIndex(pop);
                    }
                }
                for (int i3 = this.clear - 1; i3 >= 0; i3--) {
                    removeIndex(i3);
                }
            }
            this.clear = 0;
        }
    }

    private void remove(int i) {
        if (i < this.clear) {
            return;
        }
        int i2 = this.remove.size;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.remove.get(i3);
            if (i == i4) {
                return;
            }
            if (i < i4) {
                this.remove.insert(i3, i);
                return;
            }
        }
        this.remove.add(i);
    }

    @Override // com.badlogic.gdx.utils.Array
    public boolean removeValue(T t, boolean z) {
        if (this.iterating > 0) {
            int indexOf = indexOf(t, z);
            if (indexOf == -1) {
                return false;
            }
            remove(indexOf);
            return true;
        }
        return super.removeValue(t, z);
    }

    @Override // com.badlogic.gdx.utils.Array
    public T removeIndex(int i) {
        if (this.iterating > 0) {
            remove(i);
            return get(i);
        }
        return (T) super.removeIndex(i);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void removeRange(int i, int i2) {
        if (this.iterating > 0) {
            for (int i3 = i2; i3 >= i; i3--) {
                remove(i3);
            }
            return;
        }
        super.removeRange(i, i2);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void clear() {
        if (this.iterating > 0) {
            this.clear = this.size;
        } else {
            super.clear();
        }
    }

    @Override // com.badlogic.gdx.utils.Array
    public void set(int i, T t) {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.set(i, t);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void insert(int i, T t) {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.insert(i, t);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void insertRange(int i, int i2) {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.insertRange(i, i2);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void swap(int i, int i2) {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.swap(i, i2);
    }

    @Override // com.badlogic.gdx.utils.Array
    public T pop() {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        return (T) super.pop();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void sort() {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.sort();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void sort(Comparator<? super T> comparator) {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.sort(comparator);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void reverse() {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.reverse();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void shuffle() {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.shuffle();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void truncate(int i) {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.truncate(i);
    }

    @Override // com.badlogic.gdx.utils.Array
    public T[] setSize(int i) {
        if (this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        return (T[]) super.setSize(i);
    }

    public static <T> DelayedRemovalArray<T> with(T... tArr) {
        return new DelayedRemovalArray<>(tArr);
    }
}
