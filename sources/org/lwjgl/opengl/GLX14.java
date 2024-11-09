package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLX14.class */
public class GLX14 extends GLX13 {
    public static final int GLX_SAMPLE_BUFFERS = 100000;
    public static final int GLX_SAMPLES = 100001;

    protected GLX14() {
        throw new UnsupportedOperationException();
    }

    public static long nglXGetProcAddress(long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetProcAddress;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPP(j, j2);
    }

    @NativeType("void *")
    public static long glXGetProcAddress(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglXGetProcAddress(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void *")
    public static long glXGetProcAddress(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglXGetProcAddress(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
