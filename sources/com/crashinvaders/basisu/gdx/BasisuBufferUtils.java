package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.utils.BufferUtils;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuBufferUtils.class */
public class BasisuBufferUtils {
    public static boolean isUnsafeByteBuffer(ByteBuffer byteBuffer) {
        return BufferUtils.isUnsafeByteBuffer(byteBuffer);
    }

    public static ByteBuffer newUnsafeByteBuffer(int i) {
        return BufferUtils.newUnsafeByteBuffer(i);
    }

    public static void disposeUnsafeByteBuffer(ByteBuffer byteBuffer) {
        BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
    }
}
