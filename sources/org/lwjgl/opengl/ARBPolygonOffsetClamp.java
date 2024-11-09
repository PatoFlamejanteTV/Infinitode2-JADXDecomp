package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBPolygonOffsetClamp.class */
public class ARBPolygonOffsetClamp {
    public static final int GL_POLYGON_OFFSET_CLAMP = 36379;

    static {
        GL.initialize();
    }

    protected ARBPolygonOffsetClamp() {
        throw new UnsupportedOperationException();
    }

    public static void glPolygonOffsetClamp(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3) {
        GL46C.glPolygonOffsetClamp(f, f2, f3);
    }
}
