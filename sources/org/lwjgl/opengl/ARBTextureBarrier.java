package org.lwjgl.opengl;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTextureBarrier.class */
public class ARBTextureBarrier {
    static {
        GL.initialize();
    }

    protected ARBTextureBarrier() {
        throw new UnsupportedOperationException();
    }

    public static void glTextureBarrier() {
        GL45C.glTextureBarrier();
    }
}
