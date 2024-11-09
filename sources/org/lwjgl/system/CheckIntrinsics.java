package org.lwjgl.system;

/* loaded from: infinitode-2.jar:org/lwjgl/system/CheckIntrinsics.class */
public final class CheckIntrinsics {
    private CheckIntrinsics() {
    }

    public static int checkIndex(int i, int i2) {
        if (i < 0 || i2 <= i) {
            throw new IndexOutOfBoundsException();
        }
        return i;
    }

    public static int checkFromToIndex(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i3 < i2) {
            throw new IndexOutOfBoundsException();
        }
        return i;
    }

    public static int checkFromIndexSize(int i, int i2, int i3) {
        if ((i3 | i | i2) < 0 || i3 - i < i2) {
            throw new IndexOutOfBoundsException();
        }
        return i;
    }
}
