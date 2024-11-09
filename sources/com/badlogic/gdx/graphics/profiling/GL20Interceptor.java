package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL20;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/profiling/GL20Interceptor.class */
public class GL20Interceptor extends GLInterceptor implements GL20 {
    protected final GL20 gl20;

    /* JADX INFO: Access modifiers changed from: protected */
    public GL20Interceptor(GLProfiler gLProfiler, GL20 gl20) {
        super(gLProfiler);
        this.gl20 = gl20;
    }

    private void check() {
        int glGetError = this.gl20.glGetError();
        while (true) {
            int i = glGetError;
            if (i != 0) {
                this.glProfiler.getListener().onError(i);
                glGetError = this.gl20.glGetError();
            } else {
                return;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int i) {
        this.calls++;
        this.gl20.glActiveTexture(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int i, int i2) {
        this.textureBindings++;
        this.calls++;
        this.gl20.glBindTexture(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int i, int i2) {
        this.calls++;
        this.gl20.glBlendFunc(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClear(int i) {
        this.calls++;
        this.gl20.glClear(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f, float f2, float f3, float f4) {
        this.calls++;
        this.gl20.glClearColor(f, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f) {
        this.calls++;
        this.gl20.glClearDepthf(f);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int i) {
        this.calls++;
        this.gl20.glClearStencil(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z, boolean z2, boolean z3, boolean z4) {
        this.calls++;
        this.gl20.glColorMask(z, z2, z3, z4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        this.calls++;
        this.gl20.glCompressedTexImage2D(i, i2, i3, i4, i5, i6, i7, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.calls++;
        this.gl20.glCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.calls++;
        this.gl20.glCopyTexImage2D(i, i2, i3, i4, i5, i6, i7, i8);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.calls++;
        this.gl20.glCopyTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int i) {
        this.calls++;
        this.gl20.glCullFace(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteTextures(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int i) {
        this.calls++;
        this.gl20.glDeleteTexture(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int i) {
        this.calls++;
        this.gl20.glDepthFunc(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z) {
        this.calls++;
        this.gl20.glDepthMask(z);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f, float f2) {
        this.calls++;
        this.gl20.glDepthRangef(f, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisable(int i) {
        this.calls++;
        this.gl20.glDisable(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int i, int i2, int i3) {
        this.vertexCount.put(i3);
        this.drawCalls++;
        this.calls++;
        this.gl20.glDrawArrays(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i, int i2, int i3, Buffer buffer) {
        this.vertexCount.put(i2);
        this.drawCalls++;
        this.calls++;
        this.gl20.glDrawElements(i, i2, i3, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnable(int i) {
        this.calls++;
        this.gl20.glEnable(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        this.calls++;
        this.gl20.glFinish();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        this.calls++;
        this.gl20.glFlush();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int i) {
        this.calls++;
        this.gl20.glFrontFace(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenTextures(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        this.calls++;
        int glGenTexture = this.gl20.glGenTexture();
        check();
        return glGenTexture;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        this.calls++;
        return this.gl20.glGetError();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetIntegerv(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetString(int i) {
        this.calls++;
        String glGetString = this.gl20.glGetString(i);
        check();
        return glGetString;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glHint(int i, int i2) {
        this.calls++;
        this.gl20.glHint(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f) {
        this.calls++;
        this.gl20.glLineWidth(f);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int i, int i2) {
        this.calls++;
        this.gl20.glPixelStorei(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f, float f2) {
        this.calls++;
        this.gl20.glPolygonOffset(f, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int i, int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        this.calls++;
        this.gl20.glReadPixels(i, i2, i3, i4, i5, i6, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glScissor(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glScissor(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int i, int i2, int i3) {
        this.calls++;
        this.gl20.glStencilFunc(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int i) {
        this.calls++;
        this.gl20.glStencilMask(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int i, int i2, int i3) {
        this.calls++;
        this.gl20.glStencilOp(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.calls++;
        this.gl20.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int i, int i2, float f) {
        this.calls++;
        this.gl20.glTexParameterf(i, i2, f);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.calls++;
        this.gl20.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glViewport(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glViewport(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int i, int i2) {
        this.calls++;
        this.gl20.glAttachShader(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int i, int i2, String str) {
        this.calls++;
        this.gl20.glBindAttribLocation(i, i2, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int i, int i2) {
        this.calls++;
        this.gl20.glBindBuffer(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int i, int i2) {
        this.calls++;
        this.gl20.glBindFramebuffer(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int i, int i2) {
        this.calls++;
        this.gl20.glBindRenderbuffer(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f, float f2, float f3, float f4) {
        this.calls++;
        this.gl20.glBlendColor(f, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int i) {
        this.calls++;
        this.gl20.glBlendEquation(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int i, int i2) {
        this.calls++;
        this.gl20.glBlendEquationSeparate(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glBlendFuncSeparate(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int i, int i2, Buffer buffer, int i3) {
        this.calls++;
        this.gl20.glBufferData(i, i2, buffer, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int i, int i2, int i3, Buffer buffer) {
        this.calls++;
        this.gl20.glBufferSubData(i, i2, i3, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int i) {
        this.calls++;
        int glCheckFramebufferStatus = this.gl20.glCheckFramebufferStatus(i);
        check();
        return glCheckFramebufferStatus;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int i) {
        this.calls++;
        this.gl20.glCompileShader(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        this.calls++;
        int glCreateProgram = this.gl20.glCreateProgram();
        check();
        return glCreateProgram;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int i) {
        this.calls++;
        int glCreateShader = this.gl20.glCreateShader(i);
        check();
        return glCreateShader;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int i) {
        this.calls++;
        this.gl20.glDeleteBuffer(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteBuffers(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int i) {
        this.calls++;
        this.gl20.glDeleteFramebuffer(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteFramebuffers(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int i) {
        this.calls++;
        this.gl20.glDeleteProgram(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int i) {
        this.calls++;
        this.gl20.glDeleteRenderbuffer(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glDeleteRenderbuffers(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int i) {
        this.calls++;
        this.gl20.glDeleteShader(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int i, int i2) {
        this.calls++;
        this.gl20.glDetachShader(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int i) {
        this.calls++;
        this.gl20.glDisableVertexAttribArray(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int i, int i2, int i3, int i4) {
        this.vertexCount.put(i2);
        this.drawCalls++;
        this.calls++;
        this.gl20.glDrawElements(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int i) {
        this.calls++;
        this.gl20.glEnableVertexAttribArray(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glFramebufferRenderbuffer(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int i, int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glFramebufferTexture2D(i, i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        this.calls++;
        int glGenBuffer = this.gl20.glGenBuffer();
        check();
        return glGenBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenBuffers(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int i) {
        this.calls++;
        this.gl20.glGenerateMipmap(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        this.calls++;
        int glGenFramebuffer = this.gl20.glGenFramebuffer();
        check();
        return glGenFramebuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenFramebuffers(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        this.calls++;
        int glGenRenderbuffer = this.gl20.glGenRenderbuffer();
        check();
        return glGenRenderbuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGenRenderbuffers(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        String glGetActiveAttrib = this.gl20.glGetActiveAttrib(i, i2, intBuffer, intBuffer2);
        check();
        return glGetActiveAttrib;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        String glGetActiveUniform = this.gl20.glGetActiveUniform(i, i2, intBuffer, intBuffer2);
        check();
        return glGetActiveUniform;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int i, int i2, Buffer buffer, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetAttachedShaders(i, i2, buffer, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int i, String str) {
        this.calls++;
        int glGetAttribLocation = this.gl20.glGetAttribLocation(i, str);
        check();
        return glGetAttribLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int i, Buffer buffer) {
        this.calls++;
        this.gl20.glGetBooleanv(i, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetBufferParameteriv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int i, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetFloatv(i, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int i, int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetFramebufferAttachmentParameteriv(i, i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetProgramiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int i) {
        this.calls++;
        String glGetProgramInfoLog = this.gl20.glGetProgramInfoLog(i);
        check();
        return glGetProgramInfoLog;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetRenderbufferParameteriv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetShaderiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int i) {
        this.calls++;
        String glGetShaderInfoLog = this.gl20.glGetShaderInfoLog(i);
        check();
        return glGetShaderInfoLog;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        this.calls++;
        this.gl20.glGetShaderPrecisionFormat(i, i2, intBuffer, intBuffer2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetTexParameterfv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetTexParameteriv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetUniformfv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetUniformiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int i, String str) {
        this.calls++;
        int glGetUniformLocation = this.gl20.glGetUniformLocation(i, str);
        check();
        return glGetUniformLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glGetVertexAttribfv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glGetVertexAttribiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int i, int i2, Buffer buffer) {
        this.calls++;
        this.gl20.glGetVertexAttribPointerv(i, i2, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int i) {
        this.calls++;
        boolean glIsBuffer = this.gl20.glIsBuffer(i);
        check();
        return glIsBuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int i) {
        this.calls++;
        boolean glIsEnabled = this.gl20.glIsEnabled(i);
        check();
        return glIsEnabled;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int i) {
        this.calls++;
        boolean glIsFramebuffer = this.gl20.glIsFramebuffer(i);
        check();
        return glIsFramebuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int i) {
        this.calls++;
        boolean glIsProgram = this.gl20.glIsProgram(i);
        check();
        return glIsProgram;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int i) {
        this.calls++;
        boolean glIsRenderbuffer = this.gl20.glIsRenderbuffer(i);
        check();
        return glIsRenderbuffer;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int i) {
        this.calls++;
        boolean glIsShader = this.gl20.glIsShader(i);
        check();
        return glIsShader;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int i) {
        this.calls++;
        boolean glIsTexture = this.gl20.glIsTexture(i);
        check();
        return glIsTexture;
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int i) {
        this.calls++;
        this.gl20.glLinkProgram(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
        this.calls++;
        this.gl20.glReleaseShaderCompiler();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glRenderbufferStorage(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f, boolean z) {
        this.calls++;
        this.gl20.glSampleCoverage(f, z);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int i, IntBuffer intBuffer, int i2, Buffer buffer, int i3) {
        this.calls++;
        this.gl20.glShaderBinary(i, intBuffer, i2, buffer, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int i, String str) {
        this.calls++;
        this.gl20.glShaderSource(i, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glStencilFuncSeparate(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int i, int i2) {
        this.calls++;
        this.gl20.glStencilMaskSeparate(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glStencilOpSeparate(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glTexParameterfv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int i, int i2, int i3) {
        this.calls++;
        this.gl20.glTexParameteri(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glTexParameteriv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int i, float f) {
        this.calls++;
        this.gl20.glUniform1f(i, f);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform1fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int i, int i2, float[] fArr, int i3) {
        this.calls++;
        this.gl20.glUniform1fv(i, i2, fArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int i, int i2) {
        this.calls++;
        this.gl20.glUniform1i(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform1iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int i, int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl20.glUniform1iv(i, i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int i, float f, float f2) {
        this.calls++;
        this.gl20.glUniform2f(i, f, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform2fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int i, int i2, float[] fArr, int i3) {
        this.calls++;
        this.gl20.glUniform2fv(i, i2, fArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int i, int i2, int i3) {
        this.calls++;
        this.gl20.glUniform2i(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform2iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int i, int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl20.glUniform2iv(i, i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int i, float f, float f2, float f3) {
        this.calls++;
        this.gl20.glUniform3f(i, f, f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform3fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int i, int i2, float[] fArr, int i3) {
        this.calls++;
        this.gl20.glUniform3fv(i, i2, fArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl20.glUniform3i(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform3iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int i, int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl20.glUniform3iv(i, i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int i, float f, float f2, float f3, float f4) {
        this.calls++;
        this.gl20.glUniform4f(i, f, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniform4fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int i, int i2, float[] fArr, int i3) {
        this.calls++;
        this.gl20.glUniform4fv(i, i2, fArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int i, int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl20.glUniform4i(i, i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl20.glUniform4iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int i, int i2, int[] iArr, int i3) {
        this.calls++;
        this.gl20.glUniform4iv(i, i2, iArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniformMatrix2fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int i, int i2, boolean z, float[] fArr, int i3) {
        this.calls++;
        this.gl20.glUniformMatrix2fv(i, i2, z, fArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniformMatrix3fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int i, int i2, boolean z, float[] fArr, int i3) {
        this.calls++;
        this.gl20.glUniformMatrix3fv(i, i2, z, fArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glUniformMatrix4fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int i, int i2, boolean z, float[] fArr, int i3) {
        this.calls++;
        this.gl20.glUniformMatrix4fv(i, i2, z, fArr, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int i) {
        this.shaderSwitches++;
        this.calls++;
        this.gl20.glUseProgram(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int i) {
        this.calls++;
        this.gl20.glValidateProgram(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int i, float f) {
        this.calls++;
        this.gl20.glVertexAttrib1f(i, f);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int i, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib1fv(i, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int i, float f, float f2) {
        this.calls++;
        this.gl20.glVertexAttrib2f(i, f, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int i, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib2fv(i, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int i, float f, float f2, float f3) {
        this.calls++;
        this.gl20.glVertexAttrib3f(i, f, f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int i, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib3fv(i, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int i, float f, float f2, float f3, float f4) {
        this.calls++;
        this.gl20.glVertexAttrib4f(i, f, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int i, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl20.glVertexAttrib4fv(i, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, Buffer buffer) {
        this.calls++;
        this.gl20.glVertexAttribPointer(i, i2, i3, z, i4, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, int i5) {
        this.calls++;
        this.gl20.glVertexAttribPointer(i, i2, i3, z, i4, i5);
        check();
    }
}
