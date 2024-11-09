package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXNVCopyBuffer.class */
public class GLXNVCopyBuffer {
    protected GLXNVCopyBuffer() {
        throw new UnsupportedOperationException();
    }

    public static void glXCopyBufferSubDataNV(@NativeType("Display *") long j, @NativeType("GLXContext") long j2, @NativeType("GLXContext") long j3, @NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j4, @NativeType("GLintptr") long j5, @NativeType("GLsizeiptr") long j6) {
        long j7 = GL.getCapabilitiesGLXClient().glXCopyBufferSubDataNV;
        if (Checks.CHECKS) {
            Checks.check(j7);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        JNI.callPPPPPPV(j, j2, j3, i, i2, j4, j5, j6, j7);
    }

    public static void glXNamedCopyBufferSubDataNV(@NativeType("Display *") long j, @NativeType("GLXContext") long j2, @NativeType("GLXContext") long j3, @NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j4, @NativeType("GLintptr") long j5, @NativeType("GLsizeiptr") long j6) {
        long j7 = GL.getCapabilitiesGLXClient().glXNamedCopyBufferSubDataNV;
        if (Checks.CHECKS) {
            Checks.check(j7);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        JNI.callPPPPPPV(j, j2, j3, i, i2, j4, j5, j6, j7);
    }
}
