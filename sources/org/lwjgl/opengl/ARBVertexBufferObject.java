package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexBufferObject.class */
public class ARBVertexBufferObject {
    public static final int GL_ARRAY_BUFFER_ARB = 34962;
    public static final int GL_ELEMENT_ARRAY_BUFFER_ARB = 34963;
    public static final int GL_ARRAY_BUFFER_BINDING_ARB = 34964;
    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING_ARB = 34965;
    public static final int GL_VERTEX_ARRAY_BUFFER_BINDING_ARB = 34966;
    public static final int GL_NORMAL_ARRAY_BUFFER_BINDING_ARB = 34967;
    public static final int GL_COLOR_ARRAY_BUFFER_BINDING_ARB = 34968;
    public static final int GL_INDEX_ARRAY_BUFFER_BINDING_ARB = 34969;
    public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING_ARB = 34970;
    public static final int GL_EDGE_FLAG_ARRAY_BUFFER_BINDING_ARB = 34971;
    public static final int GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING_ARB = 34972;
    public static final int GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING_ARB = 34973;
    public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING_ARB = 34974;
    public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING_ARB = 34975;
    public static final int GL_STREAM_DRAW_ARB = 35040;
    public static final int GL_STREAM_READ_ARB = 35041;
    public static final int GL_STREAM_COPY_ARB = 35042;
    public static final int GL_STATIC_DRAW_ARB = 35044;
    public static final int GL_STATIC_READ_ARB = 35045;
    public static final int GL_STATIC_COPY_ARB = 35046;
    public static final int GL_DYNAMIC_DRAW_ARB = 35048;
    public static final int GL_DYNAMIC_READ_ARB = 35049;
    public static final int GL_DYNAMIC_COPY_ARB = 35050;
    public static final int GL_READ_ONLY_ARB = 35000;
    public static final int GL_WRITE_ONLY_ARB = 35001;
    public static final int GL_READ_WRITE_ARB = 35002;
    public static final int GL_BUFFER_SIZE_ARB = 34660;
    public static final int GL_BUFFER_USAGE_ARB = 34661;
    public static final int GL_BUFFER_ACCESS_ARB = 35003;
    public static final int GL_BUFFER_MAPPED_ARB = 35004;
    public static final int GL_BUFFER_MAP_POINTER_ARB = 35005;

    public static native void glBindBufferARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglDeleteBuffersARB(int i, long j);

    public static native void nglGenBuffersARB(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsBufferARB(@NativeType("GLuint") int i);

    public static native void nglBufferDataARB(int i, long j, long j2, int i2);

    public static native void nglBufferSubDataARB(int i, long j, long j2, long j3);

    public static native void nglGetBufferSubDataARB(int i, long j, long j2, long j3);

    public static native long nglMapBufferARB(int i, int i2);

    @NativeType("GLboolean")
    public static native boolean glUnmapBufferARB(@NativeType("GLenum") int i);

    public static native void nglGetBufferParameterivARB(int i, int i2, long j);

    public static native void nglGetBufferPointervARB(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected ARBVertexBufferObject() {
        throw new UnsupportedOperationException();
    }

    public static void glDeleteBuffersARB(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteBuffersARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteBuffersARB(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteBuffersARB(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenBuffersARB(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenBuffersARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenBuffersARB() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenBuffersARB(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("GLsizeiptrARB") long j, @NativeType("GLenum") int i2) {
        nglBufferDataARB(i, j, 0L, i2);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2) {
        nglBufferDataARB(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLenum") int i2) {
        nglBufferDataARB(i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLenum") int i2) {
        nglBufferDataARB(i, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLenum") int i2) {
        nglBufferDataARB(i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer), i2);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLenum") int i2) {
        nglBufferDataARB(i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer), i2);
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglBufferSubDataARB(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglBufferSubDataARB(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") IntBuffer intBuffer) {
        nglBufferSubDataARB(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglBufferSubDataARB(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglBufferSubDataARB(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetBufferSubDataARB(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetBufferSubDataARB(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") IntBuffer intBuffer) {
        nglGetBufferSubDataARB(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetBufferSubDataARB(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetBufferSubDataARB(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return MemoryUtil.memByteBufferSafe(nglMapBufferARB(i, i2), glGetBufferParameteriARB(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapBufferARB(i, i2), glGetBufferParameteriARB(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, long j, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapBufferARB(i, i2), (int) j);
    }

    public static void glGetBufferParameterivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetBufferParameterivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetBufferParameteriARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetBufferParameterivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetBufferPointervARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetBufferPointervARB(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetBufferPointerARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetBufferPointervARB(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteBuffersARB(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteBuffersARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenBuffersARB(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenBuffersARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") short[] sArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferDataARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(sArr.length) << 1, sArr, i2, j);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferDataARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(iArr.length) << 2, iArr, i2, j);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") float[] fArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferDataARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(fArr.length) << 2, fArr, i2, j);
    }

    public static void glBufferDataARB(@NativeType("GLenum") int i, @NativeType("void const *") double[] dArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferDataARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(dArr.length) << 3, dArr, i2, j);
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") short[] sArr) {
        long j2 = GL.getICD().glBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") int[] iArr) {
        long j2 = GL.getICD().glBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") float[] fArr) {
        long j2 = GL.getICD().glBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void const *") double[] dArr) {
        long j2 = GL.getICD().glBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") short[] sArr) {
        long j2 = GL.getICD().glGetBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") int[] iArr) {
        long j2 = GL.getICD().glGetBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") float[] fArr) {
        long j2 = GL.getICD().glGetBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glGetBufferSubDataARB(@NativeType("GLenum") int i, @NativeType("GLintptrARB") long j, @NativeType("void *") double[] dArr) {
        long j2 = GL.getICD().glGetBufferSubDataARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glGetBufferParameterivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetBufferParameterivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
