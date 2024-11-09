package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTWin32KeyedMutex.class */
public class EXTWin32KeyedMutex {
    @NativeType("GLboolean")
    public static native boolean glAcquireKeyedMutexWin32EXT(@NativeType("GLuint") int i, @NativeType("GLuint64") long j, @NativeType("GLuint") int i2);

    @NativeType("GLboolean")
    public static native boolean glReleaseKeyedMutexWin32EXT(@NativeType("GLuint") int i, @NativeType("GLuint64") long j);

    static {
        GL.initialize();
    }

    protected EXTWin32KeyedMutex() {
        throw new UnsupportedOperationException();
    }
}
