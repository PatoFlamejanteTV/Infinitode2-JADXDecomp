package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL15C.class */
public class GL15C extends GL14C {
    public static final int GL_SRC1_ALPHA = 34185;
    public static final int GL_ARRAY_BUFFER = 34962;
    public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
    public static final int GL_ARRAY_BUFFER_BINDING = 34964;
    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
    public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;
    public static final int GL_STREAM_DRAW = 35040;
    public static final int GL_STREAM_READ = 35041;
    public static final int GL_STREAM_COPY = 35042;
    public static final int GL_STATIC_DRAW = 35044;
    public static final int GL_STATIC_READ = 35045;
    public static final int GL_STATIC_COPY = 35046;
    public static final int GL_DYNAMIC_DRAW = 35048;
    public static final int GL_DYNAMIC_READ = 35049;
    public static final int GL_DYNAMIC_COPY = 35050;
    public static final int GL_READ_ONLY = 35000;
    public static final int GL_WRITE_ONLY = 35001;
    public static final int GL_READ_WRITE = 35002;
    public static final int GL_BUFFER_SIZE = 34660;
    public static final int GL_BUFFER_USAGE = 34661;
    public static final int GL_BUFFER_ACCESS = 35003;
    public static final int GL_BUFFER_MAPPED = 35004;
    public static final int GL_BUFFER_MAP_POINTER = 35005;
    public static final int GL_SAMPLES_PASSED = 35092;
    public static final int GL_QUERY_COUNTER_BITS = 34916;
    public static final int GL_CURRENT_QUERY = 34917;
    public static final int GL_QUERY_RESULT = 34918;
    public static final int GL_QUERY_RESULT_AVAILABLE = 34919;

    public static native void glBindBuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglDeleteBuffers(int i, long j);

    public static native void nglGenBuffers(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsBuffer(@NativeType("GLuint") int i);

    public static native void nglBufferData(int i, long j, long j2, int i2);

    public static native void nglBufferSubData(int i, long j, long j2, long j3);

    public static native void nglGetBufferSubData(int i, long j, long j2, long j3);

    public static native long nglMapBuffer(int i, int i2);

    @NativeType("GLboolean")
    public static native boolean glUnmapBuffer(@NativeType("GLenum") int i);

    public static native void nglGetBufferParameteriv(int i, int i2, long j);

    public static native void nglGetBufferPointerv(int i, int i2, long j);

    public static native void nglGenQueries(int i, long j);

    public static native void nglDeleteQueries(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsQuery(@NativeType("GLuint") int i);

    public static native void glBeginQuery(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glEndQuery(@NativeType("GLenum") int i);

    public static native void nglGetQueryiv(int i, int i2, long j);

    public static native void nglGetQueryObjectiv(int i, int i2, long j);

    public static native void nglGetQueryObjectuiv(int i, int i2, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL15C() {
        throw new UnsupportedOperationException();
    }

    public static void glDeleteBuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteBuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteBuffers(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteBuffers(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenBuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenBuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenBuffers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenBuffers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLenum") int i2) {
        nglBufferData(i, j, 0L, i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2) {
        nglBufferData(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLenum") int i2) {
        nglBufferData(i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLenum") int i2) {
        nglBufferData(i, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") LongBuffer longBuffer, @NativeType("GLenum") int i2) {
        nglBufferData(i, Integer.toUnsignedLong(longBuffer.remaining()) << 3, MemoryUtil.memAddress(longBuffer), i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLenum") int i2) {
        nglBufferData(i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer), i2);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLenum") int i2) {
        nglBufferData(i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer), i2);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglBufferSubData(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglBufferSubData(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") IntBuffer intBuffer) {
        nglBufferSubData(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") LongBuffer longBuffer) {
        nglBufferSubData(i, j, Integer.toUnsignedLong(longBuffer.remaining()) << 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglBufferSubData(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglBufferSubData(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetBufferSubData(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetBufferSubData(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") IntBuffer intBuffer) {
        nglGetBufferSubData(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") LongBuffer longBuffer) {
        nglGetBufferSubData(i, j, Integer.toUnsignedLong(longBuffer.remaining()) << 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetBufferSubData(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetBufferSubData(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void *")
    public static ByteBuffer glMapBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return MemoryUtil.memByteBufferSafe(nglMapBuffer(i, i2), glGetBufferParameteri(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapBuffer(i, i2), glGetBufferParameteri(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, long j, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapBuffer(i, i2), (int) j);
    }

    public static void glGetBufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetBufferParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetBufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetBufferParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetBufferPointerv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetBufferPointerv(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetBufferPointer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetBufferPointerv(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenQueries(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenQueries(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenQueries() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenQueries(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteQueries(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteQueries(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteQueries(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteQueries(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetQueryiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetQueryi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetQueryiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjectiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetQueryObjectiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetQueryObjectiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") long j) {
        nglGetQueryObjectiv(i, i2, j);
    }

    @NativeType("void")
    public static int glGetQueryObjecti(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetQueryObjectiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjectuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetQueryObjectuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetQueryObjectuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") long j) {
        nglGetQueryObjectuiv(i, i2, j);
    }

    @NativeType("void")
    public static int glGetQueryObjectui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetQueryObjectuiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteBuffers(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenBuffers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") short[] sArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(sArr.length) << 1, sArr, i2, j);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(iArr.length) << 2, iArr, i2, j);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") long[] jArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(jArr.length) << 3, jArr, i2, j);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") float[] fArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(fArr.length) << 2, fArr, i2, j);
    }

    public static void glBufferData(@NativeType("GLenum") int i, @NativeType("void const *") double[] dArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(dArr.length) << 3, dArr, i2, j);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") short[] sArr) {
        long j2 = GL.getICD().glBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") int[] iArr) {
        long j2 = GL.getICD().glBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") long[] jArr) {
        long j2 = GL.getICD().glBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(jArr.length) << 3, jArr, j2);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") float[] fArr) {
        long j2 = GL.getICD().glBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void const *") double[] dArr) {
        long j2 = GL.getICD().glBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") short[] sArr) {
        long j2 = GL.getICD().glGetBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") int[] iArr) {
        long j2 = GL.getICD().glGetBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") long[] jArr) {
        long j2 = GL.getICD().glGetBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(jArr.length) << 3, jArr, j2);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") float[] fArr) {
        long j2 = GL.getICD().glGetBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glGetBufferSubData(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("void *") double[] dArr) {
        long j2 = GL.getICD().glGetBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glGetBufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetBufferParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGenQueries(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenQueries;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteQueries(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteQueries;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetQueryiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetQueryiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetQueryObjectiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetQueryObjectiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetQueryObjectuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetQueryObjectuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
