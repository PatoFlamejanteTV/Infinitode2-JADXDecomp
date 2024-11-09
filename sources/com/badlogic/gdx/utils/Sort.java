package com.badlogic.gdx.utils;

import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Sort.class */
public class Sort {
    private static Sort instance;
    private TimSort timSort;
    private ComparableTimSort comparableTimSort;

    public <T extends Comparable> void sort(Array<T> array) {
        if (this.comparableTimSort == null) {
            this.comparableTimSort = new ComparableTimSort();
        }
        this.comparableTimSort.doSort(array.items, 0, array.size);
    }

    public void sort(Object[] objArr) {
        if (this.comparableTimSort == null) {
            this.comparableTimSort = new ComparableTimSort();
        }
        this.comparableTimSort.doSort(objArr, 0, objArr.length);
    }

    public void sort(Object[] objArr, int i, int i2) {
        if (this.comparableTimSort == null) {
            this.comparableTimSort = new ComparableTimSort();
        }
        this.comparableTimSort.doSort(objArr, i, i2);
    }

    public <T> void sort(Array<T> array, Comparator<? super T> comparator) {
        if (this.timSort == null) {
            this.timSort = new TimSort();
        }
        this.timSort.doSort(array.items, comparator, 0, array.size);
    }

    public <T> void sort(T[] tArr, Comparator<? super T> comparator) {
        if (this.timSort == null) {
            this.timSort = new TimSort();
        }
        this.timSort.doSort(tArr, comparator, 0, tArr.length);
    }

    public <T> void sort(T[] tArr, Comparator<? super T> comparator, int i, int i2) {
        if (this.timSort == null) {
            this.timSort = new TimSort();
        }
        this.timSort.doSort(tArr, comparator, i, i2);
    }

    public static Sort instance() {
        if (instance == null) {
            instance = new Sort();
        }
        return instance;
    }
}
