package org.lwjgl.opengl;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTDepthBoundsTest.class */
public class EXTDepthBoundsTest {
    public static final int GL_DEPTH_BOUNDS_TEST_EXT = 34960;
    public static final int GL_DEPTH_BOUNDS_EXT = 34961;

    public static native void glDepthBoundsEXT(double d, double d2);

    static {
        GL.initialize();
    }

    protected EXTDepthBoundsTest() {
        throw new UnsupportedOperationException();
    }
}
