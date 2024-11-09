package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVVertexArrayRange.class */
public class NVVertexArrayRange {
    public static final int GL_VERTEX_ARRAY_RANGE_NV = 34077;
    public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_NV = 34078;
    public static final int GL_VERTEX_ARRAY_RANGE_VALID_NV = 34079;
    public static final int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV = 34080;
    public static final int GL_VERTEX_ARRAY_RANGE_POINTER_NV = 34081;

    public static native void nglVertexArrayRangeNV(int i, long j);

    public static native void glFlushVertexArrayRangeNV();

    static {
        GL.initialize();
    }

    protected NVVertexArrayRange() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexArrayRangeNV(@NativeType("void *") ByteBuffer byteBuffer) {
        nglVertexArrayRangeNV(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }
}
