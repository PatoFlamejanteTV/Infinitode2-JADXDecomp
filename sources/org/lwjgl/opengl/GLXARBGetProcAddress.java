package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXARBGetProcAddress.class */
public class GLXARBGetProcAddress {
    protected GLXARBGetProcAddress() {
        throw new UnsupportedOperationException();
    }

    public static long nglXGetProcAddressARB(long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetProcAddressARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPP(j, j2);
    }

    @NativeType("void *")
    public static long glXGetProcAddressARB(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglXGetProcAddressARB(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void *")
    public static long glXGetProcAddressARB(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglXGetProcAddressARB(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
