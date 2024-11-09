package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3GL20.class */
public class Lwjgl3GL20 implements GL20 {
    private ByteBuffer buffer = null;
    private FloatBuffer floatBuffer = null;
    private IntBuffer intBuffer = null;

    private void ensureBufferCapacity(int i) {
        if (this.buffer == null || this.buffer.capacity() < i) {
            this.buffer = BufferUtils.newByteBuffer(i);
            this.floatBuffer = this.buffer.asFloatBuffer();
            this.intBuffer = this.buffer.asIntBuffer();
        }
    }

    private FloatBuffer toFloatBuffer(float[] fArr, int i, int i2) {
        ensureBufferCapacity(i2 << 2);
        this.floatBuffer.clear();
        this.floatBuffer.limit(i2);
        this.floatBuffer.put(fArr, i, i2);
        this.floatBuffer.position(0);
        return this.floatBuffer;
    }

    private IntBuffer toIntBuffer(int[] iArr, int i, int i2) {
        ensureBufferCapacity(i2 << 2);
        this.intBuffer.clear();
        this.intBuffer.limit(i2);
        this.intBuffer.put(iArr, i, i2);
        this.intBuffer.position(0);
        return this.intBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int i) {
        GL13.glActiveTexture(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int i, int i2) {
        org.lwjgl.opengl.GL20.glAttachShader(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int i, int i2, String str) {
        org.lwjgl.opengl.GL20.glBindAttribLocation(i, i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int i, int i2) {
        GL15.glBindBuffer(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int i, int i2) {
        EXTFramebufferObject.glBindFramebufferEXT(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int i, int i2) {
        EXTFramebufferObject.glBindRenderbufferEXT(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int i, int i2) {
        GL11.glBindTexture(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f, float f2, float f3, float f4) {
        GL14.glBlendColor(f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int i) {
        GL14.glBlendEquation(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int i, int i2) {
        org.lwjgl.opengl.GL20.glBlendEquationSeparate(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int i, int i2) {
        GL11.glBlendFunc(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int i, int i2, int i3, int i4) {
        GL14.glBlendFuncSeparate(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int i, int i2, Buffer buffer, int i3) {
        if (buffer == null) {
            GL15.glBufferData(i, i2, i3);
            return;
        }
        if (buffer instanceof ByteBuffer) {
            GL15.glBufferData(i, (ByteBuffer) buffer, i3);
            return;
        }
        if (buffer instanceof IntBuffer) {
            GL15.glBufferData(i, (IntBuffer) buffer, i3);
            return;
        }
        if (buffer instanceof FloatBuffer) {
            GL15.glBufferData(i, (FloatBuffer) buffer, i3);
        } else if (buffer instanceof DoubleBuffer) {
            GL15.glBufferData(i, (DoubleBuffer) buffer, i3);
        } else if (buffer instanceof ShortBuffer) {
            GL15.glBufferData(i, (ShortBuffer) buffer, i3);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int i, int i2, int i3, Buffer buffer) {
        if (buffer == null) {
            throw new GdxRuntimeException("Using null for the data not possible, blame LWJGL");
        }
        if (buffer instanceof ByteBuffer) {
            GL15.glBufferSubData(i, i2, (ByteBuffer) buffer);
            return;
        }
        if (buffer instanceof IntBuffer) {
            GL15.glBufferSubData(i, i2, (IntBuffer) buffer);
            return;
        }
        if (buffer instanceof FloatBuffer) {
            GL15.glBufferSubData(i, i2, (FloatBuffer) buffer);
        } else if (buffer instanceof DoubleBuffer) {
            GL15.glBufferSubData(i, i2, (DoubleBuffer) buffer);
        } else if (buffer instanceof ShortBuffer) {
            GL15.glBufferSubData(i, i2, (ShortBuffer) buffer);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int i) {
        return EXTFramebufferObject.glCheckFramebufferStatusEXT(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClear(int i) {
        GL11.glClear(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f, float f2, float f3, float f4) {
        GL11.glClearColor(f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f) {
        GL11.glClearDepth(f);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int i) {
        GL11.glClearStencil(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z, boolean z2, boolean z3, boolean z4) {
        GL11.glColorMask(z, z2, z3, z4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int i) {
        org.lwjgl.opengl.GL20.glCompileShader(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            GL13.glCompressedTexImage2D(i, i2, i3, i4, i5, i6, (ByteBuffer) buffer);
            return;
        }
        throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer instead.");
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        throw new GdxRuntimeException("not implemented");
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        GL11.glCopyTexImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        GL11.glCopyTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        return org.lwjgl.opengl.GL20.glCreateProgram();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int i) {
        return org.lwjgl.opengl.GL20.glCreateShader(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int i) {
        GL11.glCullFace(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int i, IntBuffer intBuffer) {
        GL15.glDeleteBuffers(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int i) {
        GL15.glDeleteBuffers(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int i, IntBuffer intBuffer) {
        EXTFramebufferObject.glDeleteFramebuffersEXT(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int i) {
        EXTFramebufferObject.glDeleteFramebuffersEXT(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int i) {
        org.lwjgl.opengl.GL20.glDeleteProgram(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int i, IntBuffer intBuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int i) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int i) {
        org.lwjgl.opengl.GL20.glDeleteShader(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int i, IntBuffer intBuffer) {
        GL11.glDeleteTextures(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int i) {
        GL11.glDeleteTextures(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int i) {
        GL11.glDepthFunc(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z) {
        GL11.glDepthMask(z);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f, float f2) {
        GL11.glDepthRange(f, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int i, int i2) {
        org.lwjgl.opengl.GL20.glDetachShader(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisable(int i) {
        GL11.glDisable(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int i) {
        org.lwjgl.opengl.GL20.glDisableVertexAttribArray(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int i, int i2, int i3) {
        GL11.glDrawArrays(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i, int i2, int i3, Buffer buffer) {
        if ((buffer instanceof ShortBuffer) && i3 == 5123) {
            ShortBuffer shortBuffer = (ShortBuffer) buffer;
            int position = shortBuffer.position();
            int limit = shortBuffer.limit();
            shortBuffer.limit(position + i2);
            GL11.glDrawElements(i, shortBuffer);
            shortBuffer.limit(limit);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i3 == 5123) {
            ShortBuffer asShortBuffer = ((ByteBuffer) buffer).asShortBuffer();
            int position2 = asShortBuffer.position();
            int limit2 = asShortBuffer.limit();
            asShortBuffer.limit(position2 + i2);
            GL11.glDrawElements(i, asShortBuffer);
            asShortBuffer.limit(limit2);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i3 == 5121) {
            ByteBuffer byteBuffer = (ByteBuffer) buffer;
            int position3 = byteBuffer.position();
            int limit3 = byteBuffer.limit();
            byteBuffer.limit(position3 + i2);
            GL11.glDrawElements(i, byteBuffer);
            byteBuffer.limit(limit3);
            return;
        }
        throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ShortBuffer or ByteBuffer instead. Blame LWJGL");
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnable(int i) {
        GL11.glEnable(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int i) {
        org.lwjgl.opengl.GL20.glEnableVertexAttribArray(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        GL11.glFinish();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        GL11.glFlush();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int i, int i2, int i3, int i4) {
        EXTFramebufferObject.glFramebufferRenderbufferEXT(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int i, int i2, int i3, int i4, int i5) {
        EXTFramebufferObject.glFramebufferTexture2DEXT(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int i) {
        GL11.glFrontFace(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int i, IntBuffer intBuffer) {
        GL15.glGenBuffers(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        return GL15.glGenBuffers();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int i, IntBuffer intBuffer) {
        EXTFramebufferObject.glGenFramebuffersEXT(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        return EXTFramebufferObject.glGenFramebuffersEXT();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int i, IntBuffer intBuffer) {
        EXTFramebufferObject.glGenRenderbuffersEXT(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        return EXTFramebufferObject.glGenRenderbuffersEXT();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int i, IntBuffer intBuffer) {
        GL11.glGenTextures(intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        return GL11.glGenTextures();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int i) {
        EXTFramebufferObject.glGenerateMipmapEXT(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        return org.lwjgl.opengl.GL20.glGetActiveAttrib(i, i2, 256, intBuffer, intBuffer2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        return org.lwjgl.opengl.GL20.glGetActiveUniform(i, i2, 256, intBuffer, intBuffer2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int i, int i2, Buffer buffer, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glGetAttachedShaders(i, (IntBuffer) buffer, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int i, String str) {
        return org.lwjgl.opengl.GL20.glGetAttribLocation(i, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int i, Buffer buffer) {
        GL11.glGetBooleanv(i, (ByteBuffer) buffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL15.glGetBufferParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        return GL11.glGetError();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int i, FloatBuffer floatBuffer) {
        GL11.glGetFloatv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int i, int i2, int i3, IntBuffer intBuffer) {
        EXTFramebufferObject.glGetFramebufferAttachmentParameterivEXT(i, i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int i, IntBuffer intBuffer) {
        GL11.glGetIntegerv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(10240);
        allocateDirect.order(ByteOrder.nativeOrder());
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        IntBuffer asIntBuffer = allocateDirect2.asIntBuffer();
        org.lwjgl.opengl.GL20.glGetProgramInfoLog(i, asIntBuffer, allocateDirect);
        byte[] bArr = new byte[asIntBuffer.get(0)];
        allocateDirect.get(bArr);
        return new String(bArr);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glGetProgramiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        EXTFramebufferObject.glGetRenderbufferParameterivEXT(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(10240);
        allocateDirect.order(ByteOrder.nativeOrder());
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        IntBuffer asIntBuffer = allocateDirect2.asIntBuffer();
        org.lwjgl.opengl.GL20.glGetShaderInfoLog(i, asIntBuffer, allocateDirect);
        byte[] bArr = new byte[asIntBuffer.get(0)];
        allocateDirect.get(bArr);
        return new String(bArr);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        throw new UnsupportedOperationException("unsupported, won't implement");
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glGetShaderiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetString(int i) {
        return GL11.glGetString(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        GL11.glGetTexParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL11.glGetTexParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int i, String str) {
        return org.lwjgl.opengl.GL20.glGetUniformLocation(i, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int i, int i2, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glGetUniformfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glGetUniformiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int i, int i2, Buffer buffer) {
        throw new UnsupportedOperationException("unsupported, won't implement");
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int i, int i2, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glGetVertexAttribfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glGetVertexAttribiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glHint(int i, int i2) {
        GL11.glHint(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int i) {
        return GL15.glIsBuffer(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int i) {
        return GL11.glIsEnabled(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int i) {
        return EXTFramebufferObject.glIsFramebufferEXT(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int i) {
        return org.lwjgl.opengl.GL20.glIsProgram(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int i) {
        return EXTFramebufferObject.glIsRenderbufferEXT(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int i) {
        return org.lwjgl.opengl.GL20.glIsShader(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int i) {
        return GL11.glIsTexture(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f) {
        GL11.glLineWidth(f);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int i) {
        org.lwjgl.opengl.GL20.glLinkProgram(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int i, int i2) {
        GL11.glPixelStorei(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f, float f2) {
        GL11.glPolygonOffset(f, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int i, int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            GL11.glReadPixels(i, i2, i3, i4, i5, i6, (ByteBuffer) buffer);
            return;
        }
        if (buffer instanceof ShortBuffer) {
            GL11.glReadPixels(i, i2, i3, i4, i5, i6, (ShortBuffer) buffer);
        } else if (buffer instanceof IntBuffer) {
            GL11.glReadPixels(i, i2, i3, i4, i5, i6, (IntBuffer) buffer);
        } else {
            if (buffer instanceof FloatBuffer) {
                GL11.glReadPixels(i, i2, i3, i4, i5, i6, (FloatBuffer) buffer);
                return;
            }
            throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer or FloatBuffer instead. Blame LWJGL");
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int i, int i2, int i3, int i4) {
        EXTFramebufferObject.glRenderbufferStorageEXT(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f, boolean z) {
        GL13.glSampleCoverage(f, z);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glScissor(int i, int i2, int i3, int i4) {
        GL11.glScissor(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int i, IntBuffer intBuffer, int i2, Buffer buffer, int i3) {
        throw new UnsupportedOperationException("unsupported, won't implement");
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int i, String str) {
        org.lwjgl.opengl.GL20.glShaderSource(i, str);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int i, int i2, int i3) {
        GL11.glStencilFunc(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int i, int i2, int i3, int i4) {
        org.lwjgl.opengl.GL20.glStencilFuncSeparate(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int i) {
        GL11.glStencilMask(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int i, int i2) {
        org.lwjgl.opengl.GL20.glStencilMaskSeparate(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int i, int i2, int i3) {
        GL11.glStencilOp(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int i, int i2, int i3, int i4) {
        org.lwjgl.opengl.GL20.glStencilOpSeparate(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        if (buffer == null) {
            GL11.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, (ByteBuffer) null);
            return;
        }
        if (buffer instanceof ByteBuffer) {
            GL11.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, (ByteBuffer) buffer);
            return;
        }
        if (buffer instanceof ShortBuffer) {
            GL11.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, (ShortBuffer) buffer);
            return;
        }
        if (buffer instanceof IntBuffer) {
            GL11.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, (IntBuffer) buffer);
        } else if (buffer instanceof FloatBuffer) {
            GL11.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, (FloatBuffer) buffer);
        } else {
            if (buffer instanceof DoubleBuffer) {
                GL11.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, (DoubleBuffer) buffer);
                return;
            }
            throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer, FloatBuffer or DoubleBuffer instead. Blame LWJGL");
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int i, int i2, float f) {
        GL11.glTexParameterf(i, i2, f);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        GL11.glTexParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int i, int i2, int i3) {
        GL11.glTexParameteri(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL11.glTexParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            GL11.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, (ByteBuffer) buffer);
            return;
        }
        if (buffer instanceof ShortBuffer) {
            GL11.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, (ShortBuffer) buffer);
            return;
        }
        if (buffer instanceof IntBuffer) {
            GL11.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, (IntBuffer) buffer);
        } else if (buffer instanceof FloatBuffer) {
            GL11.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, (FloatBuffer) buffer);
        } else {
            if (buffer instanceof DoubleBuffer) {
                GL11.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, (DoubleBuffer) buffer);
                return;
            }
            throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer, FloatBuffer or DoubleBuffer instead. Blame LWJGL");
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int i, float f) {
        org.lwjgl.opengl.GL20.glUniform1f(i, f);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i, int i2, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glUniform1fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i, int i2, float[] fArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform1fv(i, toFloatBuffer(fArr, i3, i2));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int i, int i2) {
        org.lwjgl.opengl.GL20.glUniform1i(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glUniform1iv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i, int i2, int[] iArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform1iv(i, toIntBuffer(iArr, i3, i2));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int i, float f, float f2) {
        org.lwjgl.opengl.GL20.glUniform2f(i, f, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i, int i2, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glUniform2fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i, int i2, float[] fArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform2fv(i, toFloatBuffer(fArr, i3, i2 << 1));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int i, int i2, int i3) {
        org.lwjgl.opengl.GL20.glUniform2i(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glUniform2iv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i, int i2, int[] iArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform2iv(i, toIntBuffer(iArr, i3, i2 << 1));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int i, float f, float f2, float f3) {
        org.lwjgl.opengl.GL20.glUniform3f(i, f, f2, f3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i, int i2, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glUniform3fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i, int i2, float[] fArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform3fv(i, toFloatBuffer(fArr, i3, i2 * 3));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int i, int i2, int i3, int i4) {
        org.lwjgl.opengl.GL20.glUniform3i(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glUniform3iv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i, int i2, int[] iArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform3iv(i, toIntBuffer(iArr, i3, i2 * 3));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int i, float f, float f2, float f3, float f4) {
        org.lwjgl.opengl.GL20.glUniform4f(i, f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i, int i2, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glUniform4fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i, int i2, float[] fArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform4fv(i, toFloatBuffer(fArr, i3, i2 << 2));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int i, int i2, int i3, int i4, int i5) {
        org.lwjgl.opengl.GL20.glUniform4i(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i, int i2, IntBuffer intBuffer) {
        org.lwjgl.opengl.GL20.glUniform4iv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i, int i2, int[] iArr, int i3) {
        org.lwjgl.opengl.GL20.glUniform4iv(i, toIntBuffer(iArr, i3, i2 << 2));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glUniformMatrix2fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i, int i2, boolean z, float[] fArr, int i3) {
        org.lwjgl.opengl.GL20.glUniformMatrix2fv(i, z, toFloatBuffer(fArr, i3, i2 << 2));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glUniformMatrix3fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i, int i2, boolean z, float[] fArr, int i3) {
        org.lwjgl.opengl.GL20.glUniformMatrix3fv(i, z, toFloatBuffer(fArr, i3, i2 * 9));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glUniformMatrix4fv(i, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i, int i2, boolean z, float[] fArr, int i3) {
        org.lwjgl.opengl.GL20.glUniformMatrix4fv(i, z, toFloatBuffer(fArr, i3, i2 << 4));
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int i) {
        org.lwjgl.opengl.GL20.glUseProgram(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int i) {
        org.lwjgl.opengl.GL20.glValidateProgram(i);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int i, float f) {
        org.lwjgl.opengl.GL20.glVertexAttrib1f(i, f);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int i, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glVertexAttrib1f(i, floatBuffer.get());
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int i, float f, float f2) {
        org.lwjgl.opengl.GL20.glVertexAttrib2f(i, f, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int i, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glVertexAttrib2f(i, floatBuffer.get(), floatBuffer.get());
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int i, float f, float f2, float f3) {
        org.lwjgl.opengl.GL20.glVertexAttrib3f(i, f, f2, f3);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int i, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glVertexAttrib3f(i, floatBuffer.get(), floatBuffer.get(), floatBuffer.get());
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int i, float f, float f2, float f3, float f4) {
        org.lwjgl.opengl.GL20.glVertexAttrib4f(i, f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int i, FloatBuffer floatBuffer) {
        org.lwjgl.opengl.GL20.glVertexAttrib4f(i, floatBuffer.get(), floatBuffer.get(), floatBuffer.get(), floatBuffer.get());
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            if (i3 == 5120) {
                org.lwjgl.opengl.GL20.glVertexAttribPointer(i, i2, i3, z, i4, (ByteBuffer) buffer);
                return;
            }
            if (i3 == 5121) {
                org.lwjgl.opengl.GL20.glVertexAttribPointer(i, i2, i3, z, i4, (ByteBuffer) buffer);
                return;
            }
            if (i3 == 5122) {
                org.lwjgl.opengl.GL20.glVertexAttribPointer(i, i2, i3, z, i4, ((ByteBuffer) buffer).asShortBuffer());
                return;
            } else if (i3 == 5123) {
                org.lwjgl.opengl.GL20.glVertexAttribPointer(i, i2, i3, z, i4, ((ByteBuffer) buffer).asShortBuffer());
                return;
            } else {
                if (i3 == 5126) {
                    org.lwjgl.opengl.GL20.glVertexAttribPointer(i, i2, i3, z, i4, ((ByteBuffer) buffer).asFloatBuffer());
                    return;
                }
                throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with type " + i3 + " with this method. Use ByteBuffer and one of GL_BYTE, GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT or GL_FLOAT for type. Blame LWJGL");
            }
        }
        if (buffer instanceof FloatBuffer) {
            if (i3 == 5126) {
                org.lwjgl.opengl.GL20.glVertexAttribPointer(i, i2, i3, z, i4, (FloatBuffer) buffer);
                return;
            }
            throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with type " + i3 + " with this method.");
        }
        throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer instead. Blame LWJGL");
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glViewport(int i, int i2, int i3, int i4) {
        GL11.glViewport(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i, int i2, int i3, int i4) {
        GL11.glDrawElements(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, int i5) {
        org.lwjgl.opengl.GL20.glVertexAttribPointer(i, i2, i3, z, i4, i5);
    }
}
