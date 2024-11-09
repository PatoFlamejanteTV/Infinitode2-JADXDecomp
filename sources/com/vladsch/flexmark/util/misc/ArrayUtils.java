package com.vladsch.flexmark.util.misc;

import java.lang.reflect.Array;
import java.util.BitSet;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/ArrayUtils.class */
public class ArrayUtils {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ArrayUtils.class.desiredAssertionStatus();
    }

    public static <T> boolean contained(T t, T[] tArr) {
        return indexOf(t, tArr) != -1;
    }

    @SafeVarargs
    public static <T> T[] append(Class<T> cls, T[] tArr, T... tArr2) {
        if (tArr2.length > 0) {
            T[] tArr3 = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, tArr.length + tArr2.length));
            System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
            System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
            return tArr3;
        }
        return tArr;
    }

    public static boolean contained(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static <T> T firstOf(T[] tArr, Predicate<? super T> predicate) {
        return (T) firstOf(tArr, 0, tArr.length, predicate);
    }

    public static <T> T firstOf(T[] tArr, int i, Predicate<? super T> predicate) {
        return (T) firstOf(tArr, i, tArr.length, predicate);
    }

    public static <T> T firstOf(T[] tArr, int i, int i2, Predicate<? super T> predicate) {
        int indexOf = indexOf(tArr, i, i2, predicate);
        if (indexOf == -1) {
            return null;
        }
        return tArr[indexOf];
    }

    public static <T> int indexOf(T t, T[] tArr) {
        return indexOf(t, tArr, 0, tArr.length);
    }

    public static <T> int indexOf(T t, T[] tArr, int i) {
        return indexOf(t, tArr, i, tArr.length);
    }

    public static <T> int indexOf(T t, T[] tArr, int i, int i2) {
        return indexOf(tArr, i, i2, obj -> {
            return Objects.equals(t, obj);
        });
    }

    public static <T> int indexOf(T[] tArr, Predicate<? super T> predicate) {
        return indexOf(tArr, 0, tArr.length, predicate);
    }

    public static <T> int indexOf(T[] tArr, int i, Predicate<? super T> predicate) {
        return indexOf(tArr, i, tArr.length, predicate);
    }

    public static <T> int indexOf(T[] tArr, int i, int i2, Predicate<? super T> predicate) {
        int length = tArr.length;
        if (i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 > length) {
                i2 = length;
            }
            if (i < i2) {
                for (int i3 = i; i3 < i2; i3++) {
                    if (predicate.test(tArr[i3])) {
                        return i3;
                    }
                }
                return -1;
            }
            return -1;
        }
        return -1;
    }

    public static <T> T lastOf(T[] tArr, Predicate<? super T> predicate) {
        return (T) lastOf(tArr, 0, tArr.length, predicate);
    }

    public static <T> T lastOf(T[] tArr, int i, Predicate<? super T> predicate) {
        return (T) lastOf(tArr, 0, i, predicate);
    }

    public static <T> T lastOf(T[] tArr, int i, int i2, Predicate<? super T> predicate) {
        int lastIndexOf = lastIndexOf(tArr, i, i2, predicate);
        if (lastIndexOf == -1) {
            return null;
        }
        return tArr[lastIndexOf];
    }

    public static <T> int lastIndexOf(T t, T[] tArr) {
        return lastIndexOf(t, tArr, 0, tArr.length);
    }

    public static <T> int lastIndexOf(T t, T[] tArr, int i) {
        return lastIndexOf(t, tArr, 0, i);
    }

    public static <T> int lastIndexOf(T t, T[] tArr, int i, int i2) {
        return lastIndexOf(tArr, i, i2, obj -> {
            return Objects.equals(t, obj);
        });
    }

    public static <T> int lastIndexOf(T[] tArr, Predicate<? super T> predicate) {
        return lastIndexOf(tArr, 0, tArr.length, predicate);
    }

    public static <T> int lastIndexOf(T[] tArr, int i, Predicate<? super T> predicate) {
        return lastIndexOf(tArr, 0, i, predicate);
    }

    public static <T> int lastIndexOf(T[] tArr, int i, int i2, Predicate<? super T> predicate) {
        int length = tArr.length;
        if (i2 >= 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= length) {
                i2 = length - 1;
            }
            if (i < i2) {
                for (int i3 = i2; i3 >= i; i3--) {
                    if (predicate.test(tArr[i3])) {
                        return i3;
                    }
                }
                return -1;
            }
            return -1;
        }
        return -1;
    }

    public static int[] toArray(BitSet bitSet) {
        int cardinality = bitSet.cardinality();
        int i = cardinality;
        int[] iArr = new int[cardinality];
        int length = bitSet.length();
        while (length >= 0) {
            int previousSetBit = bitSet.previousSetBit(length - 1);
            length = previousSetBit;
            if (previousSetBit < 0) {
                break;
            }
            i--;
            iArr[i] = length;
        }
        if ($assertionsDisabled || i == 0) {
            return iArr;
        }
        throw new AssertionError();
    }
}
