package com.badlogic.gdx.utils;

import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/SnapshotArray.class */
public class SnapshotArray<T> extends Array<T> {
    private T[] snapshot;
    private T[] recycled;
    private int snapshots;

    public SnapshotArray() {
    }

    public SnapshotArray(Array array) {
        super(array);
    }

    public SnapshotArray(boolean z, int i, Class cls) {
        super(z, i, cls);
    }

    public SnapshotArray(boolean z, int i) {
        super(z, i);
    }

    public SnapshotArray(boolean z, T[] tArr, int i, int i2) {
        super(z, tArr, i, i2);
    }

    public SnapshotArray(Class cls) {
        super(cls);
    }

    public SnapshotArray(int i) {
        super(i);
    }

    public SnapshotArray(T[] tArr) {
        super(tArr);
    }

    public T[] begin() {
        modified();
        this.snapshot = this.items;
        this.snapshots++;
        return this.items;
    }

    public void end() {
        this.snapshots = Math.max(0, this.snapshots - 1);
        if (this.snapshot == null) {
            return;
        }
        if (this.snapshot != this.items && this.snapshots == 0) {
            this.recycled = this.snapshot;
            int length = this.recycled.length;
            for (int i = 0; i < length; i++) {
                this.recycled[i] = null;
            }
        }
        this.snapshot = null;
    }

    private void modified() {
        if (this.snapshot == null || this.snapshot != this.items) {
            return;
        }
        if (this.recycled != null && this.recycled.length >= this.size) {
            System.arraycopy(this.items, 0, this.recycled, 0, this.size);
            this.items = this.recycled;
            this.recycled = null;
            return;
        }
        resize(this.items.length);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void set(int i, T t) {
        modified();
        super.set(i, t);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void insert(int i, T t) {
        modified();
        super.insert(i, t);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void insertRange(int i, int i2) {
        modified();
        super.insertRange(i, i2);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void swap(int i, int i2) {
        modified();
        super.swap(i, i2);
    }

    @Override // com.badlogic.gdx.utils.Array
    public boolean removeValue(T t, boolean z) {
        modified();
        return super.removeValue(t, z);
    }

    @Override // com.badlogic.gdx.utils.Array
    public T removeIndex(int i) {
        modified();
        return (T) super.removeIndex(i);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void removeRange(int i, int i2) {
        modified();
        super.removeRange(i, i2);
    }

    @Override // com.badlogic.gdx.utils.Array
    public boolean removeAll(Array<? extends T> array, boolean z) {
        modified();
        return super.removeAll(array, z);
    }

    @Override // com.badlogic.gdx.utils.Array
    public T pop() {
        modified();
        return (T) super.pop();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void clear() {
        modified();
        super.clear();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void sort() {
        modified();
        super.sort();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void sort(Comparator<? super T> comparator) {
        modified();
        super.sort(comparator);
    }

    @Override // com.badlogic.gdx.utils.Array
    public void reverse() {
        modified();
        super.reverse();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void shuffle() {
        modified();
        super.shuffle();
    }

    @Override // com.badlogic.gdx.utils.Array
    public void truncate(int i) {
        modified();
        super.truncate(i);
    }

    @Override // com.badlogic.gdx.utils.Array
    public T[] setSize(int i) {
        modified();
        return (T[]) super.setSize(i);
    }

    public static <T> SnapshotArray<T> with(T... tArr) {
        return new SnapshotArray<>(tArr);
    }
}
