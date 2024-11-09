package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTPolygonOffsetClamp.class */
public class EXTPolygonOffsetClamp {
    public static final int GL_POLYGON_OFFSET_CLAMP_EXT = 36379;

    public static native void glPolygonOffsetClampEXT(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    static {
        GL.initialize();
    }

    protected EXTPolygonOffsetClamp() {
        throw new UnsupportedOperationException();
    }
}
