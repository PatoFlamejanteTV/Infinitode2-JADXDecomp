package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVXConditionalRender.class */
public class NVXConditionalRender {
    public static native void glBeginConditionalRenderNVX(@NativeType("GLuint") int i);

    public static native void glEndConditionalRenderNVX();

    static {
        GL.initialize();
    }

    protected NVXConditionalRender() {
        throw new UnsupportedOperationException();
    }
}
