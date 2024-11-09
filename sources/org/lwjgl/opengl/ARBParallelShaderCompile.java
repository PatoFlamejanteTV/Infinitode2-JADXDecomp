package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBParallelShaderCompile.class */
public class ARBParallelShaderCompile {
    public static final int GL_MAX_SHADER_COMPILER_THREADS_ARB = 37296;
    public static final int GL_COMPLETION_STATUS_ARB = 37297;

    public static native void glMaxShaderCompilerThreadsARB(@NativeType("GLuint") int i);

    static {
        GL.initialize();
    }

    protected ARBParallelShaderCompile() {
        throw new UnsupportedOperationException();
    }
}
