package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBOcclusionQuery.class */
public class ARBOcclusionQuery {
    public static final int GL_SAMPLES_PASSED_ARB = 35092;
    public static final int GL_QUERY_COUNTER_BITS_ARB = 34916;
    public static final int GL_CURRENT_QUERY_ARB = 34917;
    public static final int GL_QUERY_RESULT_ARB = 34918;
    public static final int GL_QUERY_RESULT_AVAILABLE_ARB = 34919;

    public static native void nglGenQueriesARB(int i, long j);

    public static native void nglDeleteQueriesARB(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsQueryARB(@NativeType("GLuint") int i);

    public static native void glBeginQueryARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glEndQueryARB(@NativeType("GLenum") int i);

    public static native void nglGetQueryivARB(int i, int i2, long j);

    public static native void nglGetQueryObjectivARB(int i, int i2, long j);

    public static native void nglGetQueryObjectuivARB(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected ARBOcclusionQuery() {
        throw new UnsupportedOperationException();
    }

    public static void glGenQueriesARB(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenQueriesARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenQueriesARB() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenQueriesARB(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteQueriesARB(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteQueriesARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteQueriesARB(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteQueriesARB(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetQueryivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetQueryiARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetQueryivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjectivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetQueryObjectivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetQueryObjectivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") long j) {
        nglGetQueryObjectivARB(i, i2, j);
    }

    @NativeType("void")
    public static int glGetQueryObjectiARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetQueryObjectivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryObjectuivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetQueryObjectuivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetQueryObjectuivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") long j) {
        nglGetQueryObjectuivARB(i, i2, j);
    }

    @NativeType("void")
    public static int glGetQueryObjectuiARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetQueryObjectuivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenQueriesARB(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenQueriesARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteQueriesARB(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteQueriesARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetQueryivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetQueryivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetQueryObjectivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetQueryObjectivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetQueryObjectuivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetQueryObjectuivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
