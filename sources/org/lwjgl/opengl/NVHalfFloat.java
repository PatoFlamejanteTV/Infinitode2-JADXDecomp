package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVHalfFloat.class */
public class NVHalfFloat {
    public static final int GL_HALF_FLOAT_NV = 5131;

    public static native void glVertex2hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2);

    public static native void nglVertex2hvNV(long j);

    public static native void glVertex3hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3);

    public static native void nglVertex3hvNV(long j);

    public static native void glVertex4hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3, @NativeType("GLhalfNV") short s4);

    public static native void nglVertex4hvNV(long j);

    public static native void glNormal3hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3);

    public static native void nglNormal3hvNV(long j);

    public static native void glColor3hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3);

    public static native void nglColor3hvNV(long j);

    public static native void glColor4hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3, @NativeType("GLhalfNV") short s4);

    public static native void nglColor4hvNV(long j);

    public static native void glTexCoord1hNV(@NativeType("GLhalfNV") short s);

    public static native void nglTexCoord1hvNV(long j);

    public static native void glTexCoord2hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2);

    public static native void nglTexCoord2hvNV(long j);

    public static native void glTexCoord3hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3);

    public static native void nglTexCoord3hvNV(long j);

    public static native void glTexCoord4hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3, @NativeType("GLhalfNV") short s4);

    public static native void nglTexCoord4hvNV(long j);

    public static native void glMultiTexCoord1hNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV") short s);

    public static native void nglMultiTexCoord1hvNV(int i, long j);

    public static native void glMultiTexCoord2hNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2);

    public static native void nglMultiTexCoord2hvNV(int i, long j);

    public static native void glMultiTexCoord3hNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3);

    public static native void nglMultiTexCoord3hvNV(int i, long j);

    public static native void glMultiTexCoord4hNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3, @NativeType("GLhalfNV") short s4);

    public static native void nglMultiTexCoord4hvNV(int i, long j);

    public static native void glFogCoordhNV(@NativeType("GLhalfNV") short s);

    public static native void nglFogCoordhvNV(long j);

    public static native void glSecondaryColor3hNV(@NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3);

    public static native void nglSecondaryColor3hvNV(long j);

    public static native void glVertexWeighthNV(@NativeType("GLhalfNV") short s);

    public static native void nglVertexWeighthvNV(long j);

    public static native void glVertexAttrib1hNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV") short s);

    public static native void nglVertexAttrib1hvNV(int i, long j);

    public static native void glVertexAttrib2hNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2);

    public static native void nglVertexAttrib2hvNV(int i, long j);

    public static native void glVertexAttrib3hNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3);

    public static native void nglVertexAttrib3hvNV(int i, long j);

    public static native void glVertexAttrib4hNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV") short s, @NativeType("GLhalfNV") short s2, @NativeType("GLhalfNV") short s3, @NativeType("GLhalfNV") short s4);

    public static native void nglVertexAttrib4hvNV(int i, long j);

    public static native void nglVertexAttribs1hvNV(int i, int i2, long j);

    public static native void nglVertexAttribs2hvNV(int i, int i2, long j);

    public static native void nglVertexAttribs3hvNV(int i, int i2, long j);

    public static native void nglVertexAttribs4hvNV(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected NVHalfFloat() {
        throw new UnsupportedOperationException();
    }

    public static void glVertex2hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglVertex2hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertex3hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglVertex3hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertex4hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertex4hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glNormal3hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglNormal3hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColor3hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglColor3hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColor4hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglColor4hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord1hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglTexCoord1hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord2hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglTexCoord2hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord3hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglTexCoord3hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord4hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglTexCoord4hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord1hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglMultiTexCoord1hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord2hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglMultiTexCoord2hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord3hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglMultiTexCoord3hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord4hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglMultiTexCoord4hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glFogCoordhvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglFogCoordhvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glSecondaryColor3hvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglSecondaryColor3hvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexWeighthvNV(@NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglVertexWeighthvNV(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib1hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglVertexAttrib1hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib2hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglVertexAttrib2hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib3hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglVertexAttrib3hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4hvNV(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribs1hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        nglVertexAttribs1hvNV(i, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribs2hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        nglVertexAttribs2hvNV(i, shortBuffer.remaining() >> 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribs3hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        nglVertexAttribs3hvNV(i, shortBuffer.remaining() / 3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribs4hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") ShortBuffer shortBuffer) {
        nglVertexAttribs4hvNV(i, shortBuffer.remaining() >> 2, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertex2hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertex2hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(sArr, j);
    }

    public static void glVertex3hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertex3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glVertex4hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertex4hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glNormal3hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glNormal3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glColor3hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glColor3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glColor4hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glColor4hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord1hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glTexCoord1hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord2hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glTexCoord2hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord3hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glTexCoord3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord4hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glTexCoord4hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glMultiTexCoord1hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord1hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord2hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord2hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord3hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord4hvNV(@NativeType("GLenum") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord4hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glFogCoordhvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glFogCoordhvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(sArr, j);
    }

    public static void glSecondaryColor3hvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glSecondaryColor3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glVertexWeighthvNV(@NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexWeighthvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(sArr, j);
    }

    public static void glVertexAttrib1hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib1hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib2hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib2hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib3hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttribs1hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribs1hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, sArr.length, sArr, j);
    }

    public static void glVertexAttribs2hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribs2hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, sArr.length >> 1, sArr, j);
    }

    public static void glVertexAttribs3hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribs3hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, sArr.length / 3, sArr, j);
    }

    public static void glVertexAttribs4hvNV(@NativeType("GLuint") int i, @NativeType("GLhalfNV const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribs4hvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, sArr.length >> 2, sArr, j);
    }
}
