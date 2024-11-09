package com.crashinvaders.basisu.wrapper;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/UniqueIdUtils.class */
public class UniqueIdUtils {

    /* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/UniqueIdUtils$UniqueIdValue.class */
    public interface UniqueIdValue {
        int getId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends UniqueIdValue> T findOrThrow(T[] tArr, int i) {
        for (int i2 = 0; i2 < tArr.length; i2++) {
            if (tArr[i2].getId() == i) {
                return tArr[i2];
            }
        }
        throw new IllegalArgumentException("Cannot find an enum value with ID: " + i);
    }
}
