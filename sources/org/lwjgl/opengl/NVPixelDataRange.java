package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVPixelDataRange.class */
public class NVPixelDataRange {
    public static final int GL_WRITE_PIXEL_DATA_RANGE_NV = 34936;
    public static final int GL_READ_PIXEL_DATA_RANGE_NV = 34937;
    public static final int GL_WRITE_PIXEL_DATA_RANGE_LENGTH_NV = 34938;
    public static final int GL_READ_PIXEL_DATA_RANGE_LENGTH_NV = 34939;
    public static final int GL_WRITE_PIXEL_DATA_RANGE_POINTER_NV = 34940;
    public static final int GL_READ_PIXEL_DATA_RANGE_POINTER_NV = 34941;

    public static native void nglPixelDataRangeNV(int i, int i2, long j);

    public static native void glFlushPixelDataRangeNV(@NativeType("GLenum") int i);

    static {
        GL.initialize();
    }

    protected NVPixelDataRange() {
        throw new UnsupportedOperationException();
    }

    public static void glPixelDataRangeNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglPixelDataRangeNV(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }
}
