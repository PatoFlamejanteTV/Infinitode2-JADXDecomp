package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL21;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL41;
import org.lwjgl.opengl.GL43;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3GL30.class */
public class Lwjgl3GL30 extends Lwjgl3GL20 implements GL30 {
    @Override // com.badlogic.gdx.graphics.GL30
    public void glReadBuffer(int i) {
        GL11.glReadBuffer(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int i, int i2, int i3, int i4, int i5, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            GL12.glDrawRangeElements(i, i2, i3, (ByteBuffer) buffer);
        } else if (buffer instanceof ShortBuffer) {
            GL12.glDrawRangeElements(i, i2, i3, (ShortBuffer) buffer);
        } else {
            if (buffer instanceof IntBuffer) {
                GL12.glDrawRangeElements(i, i2, i3, (IntBuffer) buffer);
                return;
            }
            throw new GdxRuntimeException("indices must be byte, short or int buffer");
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int i, int i2, int i3, int i4, int i5, int i6) {
        GL12.glDrawRangeElements(i, i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        GL11.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        if (buffer == null) {
            GL12.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, (ByteBuffer) null);
            return;
        }
        if (buffer instanceof ByteBuffer) {
            GL12.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, (ByteBuffer) buffer);
            return;
        }
        if (buffer instanceof ShortBuffer) {
            GL12.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, (ShortBuffer) buffer);
            return;
        }
        if (buffer instanceof IntBuffer) {
            GL12.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, (IntBuffer) buffer);
        } else if (buffer instanceof FloatBuffer) {
            GL12.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, (FloatBuffer) buffer);
        } else {
            if (buffer instanceof DoubleBuffer) {
                GL12.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, (DoubleBuffer) buffer);
                return;
            }
            throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer, FloatBuffer or DoubleBuffer instead. Blame LWJGL");
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        GL12.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        GL11.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            GL12.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, (ByteBuffer) buffer);
            return;
        }
        if (buffer instanceof ShortBuffer) {
            GL12.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, (ShortBuffer) buffer);
            return;
        }
        if (buffer instanceof IntBuffer) {
            GL12.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, (IntBuffer) buffer);
        } else if (buffer instanceof FloatBuffer) {
            GL12.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, (FloatBuffer) buffer);
        } else {
            if (buffer instanceof DoubleBuffer) {
                GL12.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, (DoubleBuffer) buffer);
                return;
            }
            throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer, FloatBuffer or DoubleBuffer instead. Blame LWJGL");
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        GL12.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glCopyTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        GL12.glCopyTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            iArr[i3] = GL15.glGenQueries();
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int i, IntBuffer intBuffer) {
        for (int i2 = 0; i2 < i; i2++) {
            intBuffer.put(GL15.glGenQueries());
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            GL15.glDeleteQueries(iArr[i3]);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int i, IntBuffer intBuffer) {
        for (int i2 = 0; i2 < i; i2++) {
            GL15.glDeleteQueries(intBuffer.get());
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsQuery(int i) {
        return GL15.glIsQuery(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBeginQuery(int i, int i2) {
        GL15.glBeginQuery(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glEndQuery(int i) {
        GL15.glEndQuery(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetQueryiv(int i, int i2, IntBuffer intBuffer) {
        GL15.glGetQueryiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetQueryObjectuiv(int i, int i2, IntBuffer intBuffer) {
        GL15.glGetQueryObjectuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glUnmapBuffer(int i) {
        return GL15.glUnmapBuffer(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public Buffer glGetBufferPointerv(int i, int i2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawBuffers(int i, IntBuffer intBuffer) {
        int limit = intBuffer.limit();
        intBuffer.limit(i);
        GL20.glDrawBuffers(intBuffer);
        intBuffer.limit(limit);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL21.glUniformMatrix2x3fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL21.glUniformMatrix3x2fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL21.glUniformMatrix2x4fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL21.glUniformMatrix4x2fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL21.glUniformMatrix3x4fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL21.glUniformMatrix4x3fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBlitFramebuffer(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        org.lwjgl.opengl.GL30.glBlitFramebuffer(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int i, int i2) {
        org.lwjgl.opengl.GL30.glBindFramebuffer(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int i, int i2) {
        org.lwjgl.opengl.GL30.glBindRenderbuffer(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int i) {
        return org.lwjgl.opengl.GL30.glCheckFramebufferStatus(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int i, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glDeleteFramebuffers(intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int i) {
        org.lwjgl.opengl.GL30.glDeleteFramebuffers(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int i, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glDeleteRenderbuffers(intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int i) {
        org.lwjgl.opengl.GL30.glDeleteRenderbuffers(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int i) {
        org.lwjgl.opengl.GL30.glGenerateMipmap(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int i, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glGenFramebuffers(intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        return org.lwjgl.opengl.GL30.glGenFramebuffers();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int i, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glGenRenderbuffers(intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        return org.lwjgl.opengl.GL30.glGenRenderbuffers();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glGetRenderbufferParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int i) {
        return org.lwjgl.opengl.GL30.glIsFramebuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int i) {
        return org.lwjgl.opengl.GL30.glIsRenderbuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int i, int i2, int i3, int i4) {
        org.lwjgl.opengl.GL30.glRenderbufferStorage(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glRenderbufferStorageMultisample(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL30.glRenderbufferStorageMultisample(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL30.glFramebufferTexture2D(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int i, int i2, int i3, int i4) {
        org.lwjgl.opengl.GL30.glFramebufferRenderbuffer(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glFramebufferTextureLayer(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL30.glFramebufferTextureLayer(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public Buffer glMapBufferRange(int i, int i2, int i3, int i4) {
        return org.lwjgl.opengl.GL30.glMapBufferRange(i, i2, i3, i4, null);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glFlushMappedBufferRange(int i, int i2, int i3) {
        org.lwjgl.opengl.GL30.glFlushMappedBufferRange(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindVertexArray(int i) {
        org.lwjgl.opengl.GL30.glBindVertexArray(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            org.lwjgl.opengl.GL30.glDeleteVertexArrays(iArr[i3]);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int i, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glDeleteVertexArrays(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            iArr[i3] = org.lwjgl.opengl.GL30.glGenVertexArrays();
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int i, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glGenVertexArrays(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsVertexArray(int i) {
        return org.lwjgl.opengl.GL30.glIsVertexArray(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBeginTransformFeedback(int i) {
        org.lwjgl.opengl.GL30.glBeginTransformFeedback(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glEndTransformFeedback() {
        org.lwjgl.opengl.GL30.glEndTransformFeedback();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindBufferRange(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL30.glBindBufferRange(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindBufferBase(int i, int i2, int i3) {
        org.lwjgl.opengl.GL30.glBindBufferBase(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glTransformFeedbackVaryings(int i, String[] strArr, int i2) {
        org.lwjgl.opengl.GL30.glTransformFeedbackVaryings(i, strArr, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribIPointer(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL30.glVertexAttribIPointer(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glGetVertexAttribIiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIuiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glGetVertexAttribIuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4i(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL30.glVertexAttribI4i(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4ui(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL30.glVertexAttribI4ui(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetUniformuiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glGetUniformuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public int glGetFragDataLocation(int i, String str) {
        return org.lwjgl.opengl.GL30.glGetFragDataLocation(i, str);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform1uiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glUniform1uiv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform3uiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glUniform3uiv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniform4uiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glUniform4uiv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glClearBufferiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferuiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL30.glClearBufferuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfv(int i, int i2, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL30.glClearBufferfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfi(int i, int i2, float f, int i3) {
        org.lwjgl.opengl.GL30.glClearBufferfi(i, i2, f, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public String glGetStringi(int i, int i2) {
        return org.lwjgl.opengl.GL30.glGetStringi(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glCopyBufferSubData(int i, int i2, int i3, int i4, int i5) {
        GL31.glCopyBufferSubData(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetUniformIndices(int i, String[] strArr, IntBuffer intBuffer) {
        GL31.glGetUniformIndices(i, strArr, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformsiv(int i, int i2, IntBuffer intBuffer, int i3, IntBuffer intBuffer2) {
        GL31.glGetActiveUniformsiv(i, intBuffer, i3, intBuffer2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public int glGetUniformBlockIndex(int i, String str) {
        return GL31.glGetUniformBlockIndex(i, str);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockiv(int i, int i2, int i3, IntBuffer intBuffer) {
        GL31.glGetActiveUniformBlockiv(i, i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockName(int i, int i2, Buffer buffer, Buffer buffer2) {
        GL31.glGetActiveUniformBlockName(i, i2, (IntBuffer) buffer, (ByteBuffer) buffer2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public String glGetActiveUniformBlockName(int i, int i2) {
        return GL31.glGetActiveUniformBlockName(i, i2, 1024);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glUniformBlockBinding(int i, int i2, int i3) {
        GL31.glUniformBlockBinding(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawArraysInstanced(int i, int i2, int i3, int i4) {
        GL31.glDrawArraysInstanced(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDrawElementsInstanced(int i, int i2, int i3, int i4, int i5) {
        GL31.glDrawElementsInstanced(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetInteger64v(int i, LongBuffer longBuffer) {
        GL32.glGetInteger64v(i, longBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetBufferParameteri64v(int i, int i2, LongBuffer longBuffer) {
        longBuffer.put(GL32.glGetBufferParameteri64(i, i2));
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            iArr[i3] = GL33.glGenSamplers();
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int i, IntBuffer intBuffer) {
        GL33.glGenSamplers(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            GL33.glDeleteSamplers(iArr[i3]);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int i, IntBuffer intBuffer) {
        GL33.glDeleteSamplers(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsSampler(int i) {
        return GL33.glIsSampler(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindSampler(int i, int i2) {
        GL33.glBindSampler(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteri(int i, int i2, int i3) {
        GL33.glSamplerParameteri(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL33.glSamplerParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterf(int i, int i2, float f) {
        GL33.glSamplerParameterf(i, i2, f);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        GL33.glSamplerParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL33.glGetSamplerParameterIiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        GL33.glGetSamplerParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribDivisor(int i, int i2) {
        GL33.glVertexAttribDivisor(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glBindTransformFeedback(int i, int i2) {
        GL40.glBindTransformFeedback(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            GL40.glDeleteTransformFeedbacks(iArr[i3]);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int i, IntBuffer intBuffer) {
        GL40.glDeleteTransformFeedbacks(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int i, int[] iArr, int i2) {
        for (int i3 = i2; i3 < i2 + i; i3++) {
            iArr[i3] = GL40.glGenTransformFeedbacks();
        }
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int i, IntBuffer intBuffer) {
        GL40.glGenTransformFeedbacks(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public boolean glIsTransformFeedback(int i) {
        return GL40.glIsTransformFeedback(i);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glPauseTransformFeedback() {
        GL40.glPauseTransformFeedback();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glResumeTransformFeedback() {
        GL40.glResumeTransformFeedback();
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glProgramParameteri(int i, int i2, int i3) {
        GL41.glProgramParameteri(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glInvalidateFramebuffer(int i, int i2, IntBuffer intBuffer) {
        GL43.glInvalidateFramebuffer(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL30
    public void glInvalidateSubFramebuffer(int i, int i2, IntBuffer intBuffer, int i3, int i4, int i5, int i6) {
        GL43.glInvalidateSubFramebuffer(i, intBuffer, i3, i4, i5, i6);
    }
}
