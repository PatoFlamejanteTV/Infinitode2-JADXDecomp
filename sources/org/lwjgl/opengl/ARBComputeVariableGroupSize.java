package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBComputeVariableGroupSize.class */
public class ARBComputeVariableGroupSize {
    public static final int GL_MAX_COMPUTE_VARIABLE_GROUP_INVOCATIONS_ARB = 37700;
    public static final int GL_MAX_COMPUTE_FIXED_GROUP_INVOCATIONS_ARB = 37099;
    public static final int GL_MAX_COMPUTE_VARIABLE_GROUP_SIZE_ARB = 37701;
    public static final int GL_MAX_COMPUTE_FIXED_GROUP_SIZE_ARB = 37311;

    public static native void glDispatchComputeGroupSizeARB(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6);

    static {
        GL.initialize();
    }

    protected ARBComputeVariableGroupSize() {
        throw new UnsupportedOperationException();
    }
}
