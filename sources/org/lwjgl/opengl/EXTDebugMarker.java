package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTDebugMarker.class */
public class EXTDebugMarker {
    public static native void nglInsertEventMarkerEXT(int i, long j);

    public static native void nglPushGroupMarkerEXT(int i, long j);

    public static native void glPopGroupMarkerEXT();

    static {
        GL.initialize();
    }

    protected EXTDebugMarker() {
        throw new UnsupportedOperationException();
    }

    public static void glInsertEventMarkerEXT(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglInsertEventMarkerEXT(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glInsertEventMarkerEXT(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglInsertEventMarkerEXT(stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glPushGroupMarkerEXT(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglPushGroupMarkerEXT(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glPushGroupMarkerEXT(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglPushGroupMarkerEXT(stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
