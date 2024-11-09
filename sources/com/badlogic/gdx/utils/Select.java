package com.badlogic.gdx.utils;

import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Select.class */
public class Select {
    private static Select instance;
    private QuickSelect quickSelect;

    public static Select instance() {
        if (instance == null) {
            instance = new Select();
        }
        return instance;
    }

    public <T> T select(T[] tArr, Comparator<T> comparator, int i, int i2) {
        return tArr[selectIndex(tArr, comparator, i, i2)];
    }

    public <T> int selectIndex(T[] tArr, Comparator<T> comparator, int i, int i2) {
        int select;
        if (i2 <= 0) {
            throw new GdxRuntimeException("cannot select from empty array (size < 1)");
        }
        if (i > i2) {
            throw new GdxRuntimeException("Kth rank is larger than size. k: " + i + ", size: " + i2);
        }
        if (i == 1) {
            select = fastMin(tArr, comparator, i2);
        } else if (i == i2) {
            select = fastMax(tArr, comparator, i2);
        } else {
            if (this.quickSelect == null) {
                this.quickSelect = new QuickSelect();
            }
            select = this.quickSelect.select(tArr, comparator, i, i2);
        }
        return select;
    }

    private <T> int fastMin(T[] tArr, Comparator<T> comparator, int i) {
        int i2 = 0;
        for (int i3 = 1; i3 < i; i3++) {
            if (comparator.compare(tArr[i3], tArr[i2]) < 0) {
                i2 = i3;
            }
        }
        return i2;
    }

    private <T> int fastMax(T[] tArr, Comparator<T> comparator, int i) {
        int i2 = 0;
        for (int i3 = 1; i3 < i; i3++) {
            if (comparator.compare(tArr[i3], tArr[i2]) > 0) {
                i2 = i3;
            }
        }
        return i2;
    }
}
