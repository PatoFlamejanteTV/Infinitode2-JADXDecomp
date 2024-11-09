package org.lwjgl.opengl;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVTextureBarrier.class */
public class NVTextureBarrier {
    public static native void glTextureBarrierNV();

    static {
        GL.initialize();
    }

    protected NVTextureBarrier() {
        throw new UnsupportedOperationException();
    }
}
