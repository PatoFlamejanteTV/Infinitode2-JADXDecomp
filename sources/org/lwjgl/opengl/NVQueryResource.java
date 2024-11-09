package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVQueryResource.class */
public class NVQueryResource {
    public static final int GL_QUERY_RESOURCE_TYPE_VIDMEM_ALLOC_NV = 38208;
    public static final int GL_QUERY_RESOURCE_MEMTYPE_VIDMEM_NV = 38210;
    public static final int GL_QUERY_RESOURCE_SYS_RESERVED_NV = 38212;
    public static final int GL_QUERY_RESOURCE_TEXTURE_NV = 38213;
    public static final int GL_QUERY_RESOURCE_RENDERBUFFER_NV = 38214;
    public static final int GL_QUERY_RESOURCE_BUFFEROBJECT_NV = 38215;

    public static native int nglQueryResourceNV(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected NVQueryResource() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLint")
    public static int glQueryResourceNV(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        return nglQueryResourceNV(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("GLint")
    public static int glQueryResourceNV(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glQueryResourceNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(i, i2, iArr.length, iArr, j);
    }
}
