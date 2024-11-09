package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GREMEDYStringMarker.class */
public class GREMEDYStringMarker {
    public static native void nglStringMarkerGREMEDY(int i, long j);

    static {
        GL.initialize();
    }

    protected GREMEDYStringMarker() {
        throw new UnsupportedOperationException();
    }

    public static void glStringMarkerGREMEDY(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglStringMarkerGREMEDY(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glStringMarkerGREMEDY(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglStringMarkerGREMEDY(stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
