package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVVertexBufferUnifiedMemory.class */
public class NVVertexBufferUnifiedMemory {
    public static final int GL_VERTEX_ATTRIB_ARRAY_UNIFIED_NV = 36638;
    public static final int GL_ELEMENT_ARRAY_UNIFIED_NV = 36639;
    public static final int GL_VERTEX_ATTRIB_ARRAY_ADDRESS_NV = 36640;
    public static final int GL_TEXTURE_COORD_ARRAY_ADDRESS_NV = 36645;
    public static final int GL_VERTEX_ARRAY_ADDRESS_NV = 36641;
    public static final int GL_NORMAL_ARRAY_ADDRESS_NV = 36642;
    public static final int GL_COLOR_ARRAY_ADDRESS_NV = 36643;
    public static final int GL_INDEX_ARRAY_ADDRESS_NV = 36644;
    public static final int GL_EDGE_FLAG_ARRAY_ADDRESS_NV = 36646;
    public static final int GL_SECONDARY_COLOR_ARRAY_ADDRESS_NV = 36647;
    public static final int GL_FOG_COORD_ARRAY_ADDRESS_NV = 36648;
    public static final int GL_ELEMENT_ARRAY_ADDRESS_NV = 36649;
    public static final int GL_VERTEX_ATTRIB_ARRAY_LENGTH_NV = 36650;
    public static final int GL_TEXTURE_COORD_ARRAY_LENGTH_NV = 36655;
    public static final int GL_VERTEX_ARRAY_LENGTH_NV = 36651;
    public static final int GL_NORMAL_ARRAY_LENGTH_NV = 36652;
    public static final int GL_COLOR_ARRAY_LENGTH_NV = 36653;
    public static final int GL_INDEX_ARRAY_LENGTH_NV = 36654;
    public static final int GL_EDGE_FLAG_ARRAY_LENGTH_NV = 36656;
    public static final int GL_SECONDARY_COLOR_ARRAY_LENGTH_NV = 36657;
    public static final int GL_FOG_COORD_ARRAY_LENGTH_NV = 36658;
    public static final int GL_ELEMENT_ARRAY_LENGTH_NV = 36659;

    public static native void glBufferAddressRangeNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint64EXT") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glVertexFormatNV(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3);

    public static native void glNormalFormatNV(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2);

    public static native void glColorFormatNV(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3);

    public static native void glIndexFormatNV(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2);

    public static native void glTexCoordFormatNV(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3);

    public static native void glEdgeFlagFormatNV(@NativeType("GLsizei") int i);

    public static native void glSecondaryColorFormatNV(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3);

    public static native void glFogCoordFormatNV(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2);

    public static native void glVertexAttribFormatNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4);

    public static native void glVertexAttribIFormatNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4);

    public static native void nglGetIntegerui64i_vNV(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected NVVertexBufferUnifiedMemory() {
        throw new UnsupportedOperationException();
    }

    public static void glGetIntegerui64i_vNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetIntegerui64i_vNV(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetIntegerui64iNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetIntegerui64i_vNV(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetIntegerui64i_vNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetIntegerui64i_vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }
}
