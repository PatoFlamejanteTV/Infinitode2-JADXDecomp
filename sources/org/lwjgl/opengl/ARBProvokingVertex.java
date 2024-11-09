package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBProvokingVertex.class */
public class ARBProvokingVertex {
    public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
    public static final int GL_LAST_VERTEX_CONVENTION = 36430;
    public static final int GL_PROVOKING_VERTEX = 36431;
    public static final int GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 36428;

    static {
        GL.initialize();
    }

    protected ARBProvokingVertex() {
        throw new UnsupportedOperationException();
    }

    public static void glProvokingVertex(@NativeType("GLenum") int i) {
        GL32C.glProvokingVertex(i);
    }
}
