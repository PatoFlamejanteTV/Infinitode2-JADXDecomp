package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/TimSort.class */
public class TimSort<T> {
    private static final int MIN_MERGE = 32;

    /* renamed from: a, reason: collision with root package name */
    private T[] f893a;
    private Comparator<? super T> c;
    private static final int MIN_GALLOP = 7;
    private int minGallop;
    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;
    private T[] tmp;
    private int tmpCount;
    private int stackSize;
    private final int[] runBase;
    private final int[] runLen;
    private static final boolean DEBUG = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimSort() {
        this.minGallop = 7;
        this.stackSize = 0;
        this.tmp = (T[]) new Object[256];
        this.runBase = new int[40];
        this.runLen = new int[40];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void doSort(T[] tArr, Comparator<T> comparator, int i, int i2) {
        int i3;
        this.stackSize = 0;
        rangeCheck(tArr.length, i, i2);
        int i4 = i2 - i;
        int i5 = i4;
        if (i4 < 2) {
            return;
        }
        if (i5 < 32) {
            binarySort(tArr, i, i2, i + countRunAndMakeAscending(tArr, i, i2, comparator), comparator);
            return;
        }
        this.f893a = tArr;
        this.c = comparator;
        this.tmpCount = 0;
        int minRunLength = minRunLength(i5);
        do {
            int countRunAndMakeAscending = countRunAndMakeAscending(tArr, i, i2, comparator);
            int i6 = countRunAndMakeAscending;
            if (countRunAndMakeAscending < minRunLength) {
                int i7 = i5 <= minRunLength ? i5 : minRunLength;
                int i8 = i;
                binarySort(tArr, i8, i8 + i7, i + i6, comparator);
                i6 = i7;
            }
            pushRun(i, i6);
            mergeCollapse();
            i += i6;
            i3 = i5 - i6;
            i5 = i3;
        } while (i3 != 0);
        mergeForceCollapse();
        this.f893a = null;
        this.c = null;
        T[] tArr2 = this.tmp;
        int i9 = this.tmpCount;
        for (int i10 = 0; i10 < i9; i10++) {
            tArr2[i10] = null;
        }
    }

    private TimSort(T[] tArr, Comparator<? super T> comparator) {
        int i;
        this.minGallop = 7;
        this.stackSize = 0;
        this.f893a = tArr;
        this.c = comparator;
        int length = tArr.length;
        this.tmp = (T[]) new Object[length < 512 ? length >>> 1 : 256];
        if (length < 120) {
            i = 5;
        } else if (length < 1542) {
            i = 10;
        } else {
            i = length < 119151 ? 19 : 40;
        }
        int i2 = i;
        this.runBase = new int[i2];
        this.runLen = new int[i2];
    }

    static <T> void sort(T[] tArr, Comparator<? super T> comparator) {
        sort(tArr, 0, tArr.length, comparator);
    }

    static <T> void sort(T[] tArr, int i, int i2, Comparator<? super T> comparator) {
        int i3;
        if (comparator == null) {
            Arrays.sort(tArr, i, i2);
            return;
        }
        rangeCheck(tArr.length, i, i2);
        int i4 = i2 - i;
        int i5 = i4;
        if (i4 < 2) {
            return;
        }
        if (i5 < 32) {
            binarySort(tArr, i, i2, i + countRunAndMakeAscending(tArr, i, i2, comparator), comparator);
            return;
        }
        TimSort timSort = new TimSort(tArr, comparator);
        int minRunLength = minRunLength(i5);
        do {
            int countRunAndMakeAscending = countRunAndMakeAscending(tArr, i, i2, comparator);
            int i6 = countRunAndMakeAscending;
            if (countRunAndMakeAscending < minRunLength) {
                int i7 = i5 <= minRunLength ? i5 : minRunLength;
                int i8 = i;
                binarySort(tArr, i8, i8 + i7, i + i6, comparator);
                i6 = i7;
            }
            timSort.pushRun(i, i6);
            timSort.mergeCollapse();
            i += i6;
            i3 = i5 - i6;
            i5 = i3;
        } while (i3 != 0);
        timSort.mergeForceCollapse();
    }

    private static <T> void binarySort(T[] tArr, int i, int i2, int i3, Comparator<? super T> comparator) {
        if (i3 == i) {
            i3++;
        }
        while (i3 < i2) {
            T t = tArr[i3];
            int i4 = i;
            int i5 = i3;
            while (i4 < i5) {
                int i6 = (i4 + i5) >>> 1;
                if (comparator.compare(t, tArr[i6]) < 0) {
                    i5 = i6;
                } else {
                    i4 = i6 + 1;
                }
            }
            int i7 = i3 - i4;
            switch (i7) {
                case 1:
                    break;
                case 2:
                    tArr[i4 + 2] = tArr[i4 + 1];
                    break;
                default:
                    System.arraycopy(tArr, i4, tArr, i4 + 1, i7);
                    continue;
            }
            tArr[i4 + 1] = tArr[i4];
            tArr[i4] = t;
            i3++;
        }
    }

    private static <T> int countRunAndMakeAscending(T[] tArr, int i, int i2, Comparator<? super T> comparator) {
        int i3 = i + 1;
        if (i3 == i2) {
            return 1;
        }
        int i4 = i3 + 1;
        if (comparator.compare(tArr[i3], tArr[i]) < 0) {
            while (i4 < i2 && comparator.compare(tArr[i4], tArr[i4 - 1]) < 0) {
                i4++;
            }
            reverseRange(tArr, i, i4);
        } else {
            while (i4 < i2 && comparator.compare(tArr[i4], tArr[i4 - 1]) >= 0) {
                i4++;
            }
        }
        return i4 - i;
    }

    private static void reverseRange(Object[] objArr, int i, int i2) {
        int i3 = i2 - 1;
        while (i < i3) {
            Object obj = objArr[i];
            int i4 = i;
            i++;
            objArr[i4] = objArr[i3];
            int i5 = i3;
            i3--;
            objArr[i5] = obj;
        }
    }

    private static int minRunLength(int i) {
        int i2 = 0;
        while (i >= 32) {
            i2 |= i & 1;
            i >>= 1;
        }
        return i + i2;
    }

    private void pushRun(int i, int i2) {
        this.runBase[this.stackSize] = i;
        this.runLen[this.stackSize] = i2;
        this.stackSize++;
    }

    private void mergeCollapse() {
        while (this.stackSize > 1) {
            int i = this.stackSize - 2;
            int i2 = i;
            if ((i > 0 && this.runLen[i2 - 1] <= this.runLen[i2] + this.runLen[i2 + 1]) || (i2 >= 2 && this.runLen[i2 - 2] <= this.runLen[i2] + this.runLen[i2 - 1])) {
                if (this.runLen[i2 - 1] < this.runLen[i2 + 1]) {
                    i2--;
                }
            } else if (this.runLen[i2] > this.runLen[i2 + 1]) {
                return;
            }
            mergeAt(i2);
        }
    }

    private void mergeForceCollapse() {
        while (this.stackSize > 1) {
            int i = this.stackSize - 2;
            int i2 = i;
            if (i > 0 && this.runLen[i2 - 1] < this.runLen[i2 + 1]) {
                i2--;
            }
            mergeAt(i2);
        }
    }

    private void mergeAt(int i) {
        int gallopLeft;
        int i2 = this.runBase[i];
        int i3 = this.runLen[i];
        int i4 = this.runBase[i + 1];
        int i5 = this.runLen[i + 1];
        this.runLen[i] = i3 + i5;
        if (i == this.stackSize - 3) {
            this.runBase[i + 1] = this.runBase[i + 2];
            this.runLen[i + 1] = this.runLen[i + 2];
        }
        this.stackSize--;
        int gallopRight = gallopRight(this.f893a[i4], this.f893a, i2, i3, 0, this.c);
        int i6 = i2 + gallopRight;
        int i7 = i3 - gallopRight;
        if (i7 == 0 || (gallopLeft = gallopLeft(this.f893a[(i6 + i7) - 1], this.f893a, i4, i5, i5 - 1, this.c)) == 0) {
            return;
        }
        if (i7 <= gallopLeft) {
            mergeLo(i6, i7, i4, gallopLeft);
        } else {
            mergeHi(i6, i7, i4, gallopLeft);
        }
    }

    private static <T> int gallopLeft(T t, T[] tArr, int i, int i2, int i3, Comparator<? super T> comparator) {
        int i4;
        int i5;
        int i6 = 0;
        int i7 = 1;
        if (comparator.compare(t, tArr[i + i3]) > 0) {
            int i8 = i2 - i3;
            while (i7 < i8 && comparator.compare(t, tArr[i + i3 + i7]) > 0) {
                i6 = i7;
                int i9 = (i7 << 1) + 1;
                i7 = i9;
                if (i9 <= 0) {
                    i7 = i8;
                }
            }
            if (i7 > i8) {
                i7 = i8;
            }
            i4 = i6 + i3;
            i5 = i7 + i3;
        } else {
            int i10 = i3 + 1;
            while (i7 < i10 && comparator.compare(t, tArr[(i + i3) - i7]) <= 0) {
                i6 = i7;
                int i11 = (i7 << 1) + 1;
                i7 = i11;
                if (i11 <= 0) {
                    i7 = i10;
                }
            }
            if (i7 > i10) {
                i7 = i10;
            }
            int i12 = i6;
            i4 = i3 - i7;
            i5 = i3 - i12;
        }
        int i13 = i4 + 1;
        while (i13 < i5) {
            int i14 = i13 + ((i5 - i13) >>> 1);
            if (comparator.compare(t, tArr[i + i14]) > 0) {
                i13 = i14 + 1;
            } else {
                i5 = i14;
            }
        }
        return i5;
    }

    private static <T> int gallopRight(T t, T[] tArr, int i, int i2, int i3, Comparator<? super T> comparator) {
        int i4;
        int i5;
        int i6 = 1;
        int i7 = 0;
        if (comparator.compare(t, tArr[i + i3]) < 0) {
            int i8 = i3 + 1;
            while (i6 < i8 && comparator.compare(t, tArr[(i + i3) - i6]) < 0) {
                i7 = i6;
                int i9 = (i6 << 1) + 1;
                i6 = i9;
                if (i9 <= 0) {
                    i6 = i8;
                }
            }
            if (i6 > i8) {
                i6 = i8;
            }
            int i10 = i7;
            i4 = i3 - i6;
            i5 = i3 - i10;
        } else {
            int i11 = i2 - i3;
            while (i6 < i11 && comparator.compare(t, tArr[i + i3 + i6]) >= 0) {
                i7 = i6;
                int i12 = (i6 << 1) + 1;
                i6 = i12;
                if (i12 <= 0) {
                    i6 = i11;
                }
            }
            if (i6 > i11) {
                i6 = i11;
            }
            i4 = i7 + i3;
            i5 = i6 + i3;
        }
        int i13 = i4 + 1;
        while (i13 < i5) {
            int i14 = i13 + ((i5 - i13) >>> 1);
            if (comparator.compare(t, tArr[i + i14]) < 0) {
                i5 = i14;
            } else {
                i13 = i14 + 1;
            }
        }
        return i5;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00cc, code lost:            r0 = gallopRight(r0[r15], r0, r14, r9, 0, r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00de, code lost:            if (r0 == 0) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00e1, code lost:            java.lang.System.arraycopy(r0, r14, r0, r16, r0);        r16 = r16 + r0;        r14 = r14 + r0;        r0 = r9 - r0;        r9 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0103, code lost:            if (r0 <= 1) goto L62;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0106, code lost:            r1 = r16;        r16 = r16 + 1;        r3 = r15;        r15 = r15 + 1;        r0[r1] = r0[r3];        r11 = r11 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x011b, code lost:            if (r11 == 0) goto L64;     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x011e, code lost:            r0 = gallopLeft(r0[r14], r0, r15, r11, 0, r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0131, code lost:            if (r0 == 0) goto L31;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0134, code lost:            java.lang.System.arraycopy(r0, r15, r0, r16, r0);        r16 = r16 + r0;        r15 = r15 + r0;        r0 = r11 - r0;        r11 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0157, code lost:            if (r0 == 0) goto L63;     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x015a, code lost:            r1 = r16;        r16 = r16 + 1;        r3 = r14;        r14 = r14 + 1;        r0[r1] = r0[r3];        r9 = r9 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016f, code lost:            if (r9 == 1) goto L66;     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0172, code lost:            r10 = r10 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0179, code lost:            if (r0 < 7) goto L36;     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x017c, code lost:            r0 = true;     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0185, code lost:            if (r0 < 7) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0188, code lost:            r1 = true;     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x018e, code lost:            if ((r0 | r1) != false) goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0192, code lost:            if (r10 >= 0) goto L68;     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0195, code lost:            r10 = 0;     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x018c, code lost:            r1 = false;     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0180, code lost:            r0 = false;     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00cc A[EDGE_INSN: B:18:0x00cc->B:19:0x00cc BREAK  A[LOOP:1: B:12:0x0071->B:67:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[LOOP:1: B:12:0x0071->B:67:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void mergeLo(int r8, int r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 485
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.TimSort.mergeLo(int, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00e8, code lost:            r4 = r10;        r0 = r10 - gallopRight(r0[r16], r0, r9, r4, r4 - 1, r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00fe, code lost:            if (r0 == 0) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0101, code lost:            r11 = r11 - r0;        r15 = r15 - r0;        r10 = r10 - r0;        java.lang.System.arraycopy(r0, r15 + 1, r0, r11 + 1, r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0123, code lost:            if (r10 == 0) goto L63;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0126, code lost:            r1 = r11;        r11 = r11 - 1;        r3 = r16;        r16 = r16 - 1;        r0[r1] = r0[r3];        r12 = r12 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x013b, code lost:            if (r12 == 1) goto L65;     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x013e, code lost:            r0 = r12 - gallopLeft(r0[r15], r0, 0, r12, r12 - 1, r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0156, code lost:            if (r0 == 0) goto L31;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0159, code lost:            r11 = r11 - r0;        r16 = r16 - r0;        r12 = r12 - r0;        java.lang.System.arraycopy(r0, r16 + 1, r0, r11 + 1, r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x017f, code lost:            if (r12 <= 1) goto L66;     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0182, code lost:            r1 = r11;        r11 = r11 - 1;        r3 = r15;        r15 = r15 - 1;        r0[r1] = r0[r3];        r10 = r10 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0195, code lost:            if (r10 == 0) goto L61;     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0198, code lost:            r18 = r18 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x019f, code lost:            if (r0 < 7) goto L36;     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01a2, code lost:            r0 = true;     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01ab, code lost:            if (r0 < 7) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x01ae, code lost:            r1 = true;     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01b4, code lost:            if ((r0 | r1) != false) goto L72;     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01b9, code lost:            if (r18 >= 0) goto L68;     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01bc, code lost:            r18 = 0;     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01b2, code lost:            r1 = false;     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01a6, code lost:            r0 = false;     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e8 A[EDGE_INSN: B:18:0x00e8->B:19:0x00e8 BREAK  A[LOOP:1: B:12:0x008d->B:67:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[LOOP:1: B:12:0x008d->B:67:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void mergeHi(int r9, int r10, int r11, int r12) {
        /*
            Method dump skipped, instructions count: 541
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.TimSort.mergeHi(int, int, int, int):void");
    }

    private T[] ensureCapacity(int i) {
        int min;
        this.tmpCount = Math.max(this.tmpCount, i);
        if (this.tmp.length < i) {
            int i2 = i | (i >> 1);
            int i3 = i2 | (i2 >> 2);
            int i4 = i3 | (i3 >> 4);
            int i5 = i4 | (i4 >> 8);
            int i6 = (i5 | (i5 >> 16)) + 1;
            if (i6 < 0) {
                min = i;
            } else {
                min = Math.min(i6, this.f893a.length >>> 1);
            }
            this.tmp = (T[]) new Object[min];
        }
        return this.tmp;
    }

    private static void rangeCheck(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException("fromIndex(" + i2 + ") > toIndex(" + i3 + ")");
        }
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        if (i3 > i) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
    }
}
