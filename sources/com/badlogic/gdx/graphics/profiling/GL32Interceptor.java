package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL32;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/profiling/GL32Interceptor.class */
public class GL32Interceptor extends GL31Interceptor implements GL32 {
    final GL32 gl32;

    public GL32Interceptor(GLProfiler gLProfiler, GL32 gl32) {
        super(gLProfiler, gl32);
        this.gl32 = gl32;
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendBarrier() {
        this.calls++;
        this.gl32.glBlendBarrier();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glCopyImageSubData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        this.calls++;
        this.gl32.glCopyImageSubData(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDebugMessageControl(int i, int i2, int i3, IntBuffer intBuffer, boolean z) {
        this.calls++;
        this.gl32.glDebugMessageControl(i, i2, i3, intBuffer, z);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDebugMessageInsert(int i, int i2, int i3, int i4, String str) {
        this.calls++;
        this.gl32.glDebugMessageInsert(i, i2, i3, i4, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDebugMessageCallback(GL32.DebugProc debugProc) {
        this.calls++;
        this.gl32.glDebugMessageCallback(debugProc);
        check();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public int glGetDebugMessageLog(int i, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, IntBuffer intBuffer4, IntBuffer intBuffer5, ByteBuffer byteBuffer) {
        this.calls++;
        int glGetDebugMessageLog = this.gl32.glGetDebugMessageLog(i, intBuffer, intBuffer2, intBuffer3, intBuffer4, intBuffer5, byteBuffer);
        check();
        return glGetDebugMessageLog;
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glPushDebugGroup(int i, int i2, String str) {
        this.calls++;
        this.gl32.glPushDebugGroup(i, i2, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glPopDebugGroup() {
        this.calls++;
        this.gl32.glPopDebugGroup();
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glObjectLabel(int i, int i2, String str) {
        this.calls++;
        this.gl32.glObjectLabel(i, i2, str);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public String glGetObjectLabel(int i, int i2) {
        this.calls++;
        String glGetObjectLabel = this.gl32.glGetObjectLabel(i, i2);
        check();
        return glGetObjectLabel;
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public long glGetPointerv(int i) {
        this.calls++;
        long glGetPointerv = this.gl32.glGetPointerv(i);
        check();
        return glGetPointerv;
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glEnablei(int i, int i2) {
        this.calls++;
        this.gl32.glEnablei(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDisablei(int i, int i2) {
        this.calls++;
        this.gl32.glDisablei(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendEquationi(int i, int i2) {
        this.calls++;
        this.gl32.glBlendEquationi(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendEquationSeparatei(int i, int i2, int i3) {
        this.calls++;
        this.gl32.glBlendEquationSeparatei(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendFunci(int i, int i2, int i3) {
        this.calls++;
        this.gl32.glBlendFunci(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendFuncSeparatei(int i, int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl32.glBlendFuncSeparatei(i, i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glColorMaski(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this.calls++;
        this.gl32.glColorMaski(i, z, z2, z3, z4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public boolean glIsEnabledi(int i, int i2) {
        this.calls++;
        boolean glIsEnabledi = this.gl32.glIsEnabledi(i, i2);
        check();
        return glIsEnabledi;
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawElementsBaseVertex(int i, int i2, int i3, Buffer buffer, int i4) {
        this.vertexCount.put(i2);
        this.drawCalls++;
        this.calls++;
        this.gl32.glDrawElementsBaseVertex(i, i2, i3, buffer, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawRangeElementsBaseVertex(int i, int i2, int i3, int i4, int i5, Buffer buffer, int i6) {
        this.vertexCount.put(i4);
        this.drawCalls++;
        this.calls++;
        this.gl32.glDrawRangeElementsBaseVertex(i, i2, i3, i4, i5, buffer, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawElementsInstancedBaseVertex(int i, int i2, int i3, Buffer buffer, int i4, int i5) {
        this.vertexCount.put(i2);
        this.drawCalls++;
        this.calls++;
        this.gl32.glDrawElementsInstancedBaseVertex(i, i2, i3, buffer, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawElementsInstancedBaseVertex(int i, int i2, int i3, int i4, int i5, int i6) {
        this.vertexCount.put(i2);
        this.drawCalls++;
        this.calls++;
        this.gl32.glDrawElementsInstancedBaseVertex(i, i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glFramebufferTexture(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl32.glFramebufferTexture(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public int glGetGraphicsResetStatus() {
        this.calls++;
        int glGetGraphicsResetStatus = this.gl32.glGetGraphicsResetStatus();
        check();
        return glGetGraphicsResetStatus;
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glReadnPixels(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        this.calls++;
        this.gl32.glReadnPixels(i, i2, i3, i4, i5, i6, i7, buffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetnUniformfv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl32.glGetnUniformfv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetnUniformiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glGetnUniformiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetnUniformuiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glGetnUniformuiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glMinSampleShading(float f) {
        this.calls++;
        this.gl32.glMinSampleShading(f);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glPatchParameteri(int i, int i2) {
        this.calls++;
        this.gl32.glPatchParameteri(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexParameterIiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glTexParameterIiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glTexParameterIuiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetTexParameterIiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glGetTexParameterIiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetTexParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glGetTexParameterIuiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glSamplerParameterIiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glSamplerParameterIiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glSamplerParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glSamplerParameterIuiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetSamplerParameterIiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glGetSamplerParameterIiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetSamplerParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl32.glGetSamplerParameterIuiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexBuffer(int i, int i2, int i3) {
        this.calls++;
        this.gl32.glTexBuffer(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexBufferRange(int i, int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl32.glTexBufferRange(i, i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexStorage3DMultisample(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.calls++;
        this.gl32.glTexStorage3DMultisample(i, i2, i3, i4, i5, i6, z);
        check();
    }
}
