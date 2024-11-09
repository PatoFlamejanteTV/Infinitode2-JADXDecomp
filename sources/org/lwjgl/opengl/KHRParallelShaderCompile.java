package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/KHRParallelShaderCompile.class */
public class KHRParallelShaderCompile {
    public static final int GL_MAX_SHADER_COMPILER_THREADS_KHR = 37296;
    public static final int GL_COMPLETION_STATUS_KHR = 37297;

    public static native void glMaxShaderCompilerThreadsKHR(@NativeType("GLuint") int i);

    static {
        GL.initialize();
    }

    protected KHRParallelShaderCompile() {
        throw new UnsupportedOperationException();
    }
}
