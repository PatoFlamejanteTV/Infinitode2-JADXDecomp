package com.badlogic.gdx.utils;

import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/QuickSelect.class */
public class QuickSelect<T> {
    private T[] array;
    private Comparator<? super T> comp;

    /* JADX WARN: Multi-variable type inference failed */
    public int select(T[] tArr, Comparator<T> comparator, int i, int i2) {
        this.array = tArr;
        this.comp = comparator;
        return recursiveSelect(0, i2 - 1, i);
    }

    private int partition(int i, int i2, int i3) {
        T t = this.array[i3];
        swap(i2, i3);
        int i4 = i;
        for (int i5 = i; i5 < i2; i5++) {
            if (this.comp.compare(this.array[i5], t) < 0) {
                swap(i4, i5);
                i4++;
            }
        }
        swap(i2, i4);
        return i4;
    }

    private int recursiveSelect(int i, int i2, int i3) {
        int recursiveSelect;
        if (i == i2) {
            return i;
        }
        int partition = partition(i, i2, medianOfThreePivot(i, i2));
        int i4 = (partition - i) + 1;
        if (i4 == i3) {
            recursiveSelect = partition;
        } else if (i3 < i4) {
            recursiveSelect = recursiveSelect(i, partition - 1, i3);
        } else {
            recursiveSelect = recursiveSelect(partition + 1, i2, i3 - i4);
        }
        return recursiveSelect;
    }

    private int medianOfThreePivot(int i, int i2) {
        T t = this.array[i];
        int i3 = (i + i2) / 2;
        T t2 = this.array[i3];
        T t3 = this.array[i2];
        if (this.comp.compare(t, t2) > 0) {
            if (this.comp.compare(t2, t3) > 0) {
                return i3;
            }
            if (this.comp.compare(t, t3) > 0) {
                return i2;
            }
            return i;
        }
        if (this.comp.compare(t, t3) > 0) {
            return i;
        }
        if (this.comp.compare(t2, t3) > 0) {
            return i2;
        }
        return i3;
    }

    private void swap(int i, int i2) {
        T t = this.array[i];
        T[] tArr = this.array;
        tArr[i] = tArr[i2];
        this.array[i2] = t;
    }
}
