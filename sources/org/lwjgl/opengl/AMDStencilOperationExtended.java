package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDStencilOperationExtended.class */
public class AMDStencilOperationExtended {
    public static final int GL_SET_AMD = 34634;
    public static final int GL_REPLACE_VALUE_AMD = 34635;
    public static final int GL_STENCIL_OP_VALUE_AMD = 34636;
    public static final int GL_STENCIL_BACK_OP_VALUE_AMD = 34637;

    public static native void glStencilOpValueAMD(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    protected AMDStencilOperationExtended() {
        throw new UnsupportedOperationException();
    }
}
