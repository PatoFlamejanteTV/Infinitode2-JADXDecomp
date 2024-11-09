package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVCommandList.class */
public class NVCommandList {
    public static final int GL_TERMINATE_SEQUENCE_COMMAND_NV = 0;
    public static final int GL_NOP_COMMAND_NV = 1;
    public static final int GL_DRAW_ELEMENTS_COMMAND_NV = 2;
    public static final int GL_DRAW_ARRAYS_COMMAND_NV = 3;
    public static final int GL_DRAW_ELEMENTS_STRIP_COMMAND_NV = 4;
    public static final int GL_DRAW_ARRAYS_STRIP_COMMAND_NV = 5;
    public static final int GL_DRAW_ELEMENTS_INSTANCED_COMMAND_NV = 6;
    public static final int GL_DRAW_ARRAYS_INSTANCED_COMMAND_NV = 7;
    public static final int GL_ELEMENT_ADDRESS_COMMAND_NV = 8;
    public static final int GL_ATTRIBUTE_ADDRESS_COMMAND_NV = 9;
    public static final int GL_UNIFORM_ADDRESS_COMMAND_NV = 10;
    public static final int GL_BLEND_COLOR_COMMAND_NV = 11;
    public static final int GL_STENCIL_REF_COMMAND_NV = 12;
    public static final int GL_LINE_WIDTH_COMMAND_NV = 13;
    public static final int GL_POLYGON_OFFSET_COMMAND_NV = 14;
    public static final int GL_ALPHA_REF_COMMAND_NV = 15;
    public static final int GL_VIEWPORT_COMMAND_NV = 16;
    public static final int GL_SCISSOR_COMMAND_NV = 17;
    public static final int GL_FRONT_FACE_COMMAND_NV = 18;

    public static native void nglCreateStatesNV(int i, long j);

    public static native void nglDeleteStatesNV(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsStateNV(@NativeType("GLuint") int i);

    public static native void glStateCaptureNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    @NativeType("GLuint")
    public static native int glGetCommandHeaderNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    @NativeType("GLushort")
    public static native short glGetStageIndexNV(@NativeType("GLenum") int i);

    public static native void nglDrawCommandsNV(int i, int i2, long j, long j2, int i3);

    public static native void nglDrawCommandsAddressNV(int i, long j, long j2, int i2);

    public static native void nglDrawCommandsStatesNV(int i, long j, long j2, long j3, long j4, int i2);

    public static native void nglDrawCommandsStatesAddressNV(long j, long j2, long j3, long j4, int i);

    public static native void nglCreateCommandListsNV(int i, long j);

    public static native void nglDeleteCommandListsNV(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsCommandListNV(@NativeType("GLuint") int i);

    public static native void nglListDrawCommandsStatesClientNV(int i, int i2, long j, long j2, long j3, long j4, int i3);

    public static native void glCommandListSegmentsNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glCompileCommandListNV(@NativeType("GLuint") int i);

    public static native void glCallCommandListNV(@NativeType("GLuint") int i);

    static {
        GL.initialize();
    }

    protected NVCommandList() {
        throw new UnsupportedOperationException();
    }

    public static void glCreateStatesNV(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateStatesNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateStatesNV() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateStatesNV(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteStatesNV(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteStatesNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteStatesNV(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteStatesNV(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDrawCommandsNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, pointerBuffer.remaining());
        }
        nglDrawCommandsNV(i, i2, MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer), pointerBuffer.remaining());
    }

    public static void glDrawCommandsAddressNV(@NativeType("GLenum") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, longBuffer.remaining());
        }
        nglDrawCommandsAddressNV(i, MemoryUtil.memAddress(longBuffer), MemoryUtil.memAddress(intBuffer), longBuffer.remaining());
    }

    public static void glDrawCommandsStatesNV(@NativeType("GLuint") int i, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2, @NativeType("GLuint const *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, pointerBuffer.remaining());
            Checks.check((Buffer) intBuffer2, pointerBuffer.remaining());
            Checks.check((Buffer) intBuffer3, pointerBuffer.remaining());
        }
        nglDrawCommandsStatesNV(i, MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), pointerBuffer.remaining());
    }

    public static void glDrawCommandsStatesAddressNV(@NativeType("GLuint64 const *") LongBuffer longBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2, @NativeType("GLuint const *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, longBuffer.remaining());
            Checks.check((Buffer) intBuffer2, longBuffer.remaining());
            Checks.check((Buffer) intBuffer3, longBuffer.remaining());
        }
        nglDrawCommandsStatesAddressNV(MemoryUtil.memAddress(longBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), longBuffer.remaining());
    }

    public static void glCreateCommandListsNV(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateCommandListsNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateCommandListsNV() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateCommandListsNV(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteCommandListsNV(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteCommandListsNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteCommandListsNV(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteCommandListsNV(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glListDrawCommandsStatesClientNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("void const **") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2, @NativeType("GLuint const *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, pointerBuffer.remaining());
            Checks.check((Buffer) intBuffer2, pointerBuffer.remaining());
            Checks.check((Buffer) intBuffer3, pointerBuffer.remaining());
        }
        nglListDrawCommandsStatesClientNV(i, i2, MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), pointerBuffer.remaining());
    }

    public static void glCreateStatesNV(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateStatesNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteStatesNV(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteStatesNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDrawCommandsNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") int[] iArr) {
        long j = GL.getICD().glDrawCommandsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, pointerBuffer.remaining());
        }
        JNI.callPPV(i, i2, MemoryUtil.memAddress(pointerBuffer), iArr, pointerBuffer.remaining(), j);
    }

    public static void glDrawCommandsAddressNV(@NativeType("GLenum") int i, @NativeType("GLuint64 const *") long[] jArr, @NativeType("GLsizei const *") int[] iArr) {
        long j = GL.getICD().glDrawCommandsAddressNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, jArr.length);
        }
        JNI.callPPV(i, jArr, iArr, jArr.length, j);
    }

    public static void glDrawCommandsStatesNV(@NativeType("GLuint") int i, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2, @NativeType("GLuint const *") int[] iArr3) {
        long j = GL.getICD().glDrawCommandsStatesNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, pointerBuffer.remaining());
            Checks.check(iArr2, pointerBuffer.remaining());
            Checks.check(iArr3, pointerBuffer.remaining());
        }
        JNI.callPPPPV(i, MemoryUtil.memAddress(pointerBuffer), iArr, iArr2, iArr3, pointerBuffer.remaining(), j);
    }

    public static void glDrawCommandsStatesAddressNV(@NativeType("GLuint64 const *") long[] jArr, @NativeType("GLsizei const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2, @NativeType("GLuint const *") int[] iArr3) {
        long j = GL.getICD().glDrawCommandsStatesAddressNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, jArr.length);
            Checks.check(iArr2, jArr.length);
            Checks.check(iArr3, jArr.length);
        }
        JNI.callPPPPV(jArr, iArr, iArr2, iArr3, jArr.length, j);
    }

    public static void glCreateCommandListsNV(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateCommandListsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteCommandListsNV(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteCommandListsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glListDrawCommandsStatesClientNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("void const **") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2, @NativeType("GLuint const *") int[] iArr3) {
        long j = GL.getICD().glListDrawCommandsStatesClientNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, pointerBuffer.remaining());
            Checks.check(iArr2, pointerBuffer.remaining());
            Checks.check(iArr3, pointerBuffer.remaining());
        }
        JNI.callPPPPV(i, i2, MemoryUtil.memAddress(pointerBuffer), iArr, iArr2, iArr3, pointerBuffer.remaining(), j);
    }
}
