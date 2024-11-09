package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL31;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/profiling/GL31Interceptor.class */
public class GL31Interceptor extends GL30Interceptor implements GL31 {
    final GL31 gl31;

    public GL31Interceptor(GLProfiler gLProfiler, GL31 gl31) {
        super(gLProfiler, gl31);
        this.gl31 = gl31;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void check() {
        int glGetError = this.gl30.glGetError();
        while (true) {
            int i = glGetError;
            if (i != 0) {
                this.glProfiler.getListener().onError(i);
                glGetError = this.gl30.glGetError();
            } else {
                return;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDispatchCompute(int i, int i2, int i3) {
        this.calls++;
        this.gl31.glDispatchCompute(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDispatchComputeIndirect(long j) {
        this.calls++;
        this.gl31.glDispatchComputeIndirect(j);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDrawArraysIndirect(int i, long j) {
        this.drawCalls++;
        this.calls++;
        this.gl31.glDrawArraysIndirect(i, j);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDrawElementsIndirect(int i, int i2, long j) {
        this.drawCalls++;
        this.calls++;
        this.gl31.glDrawElementsIndirect(i, i2, j);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glFramebufferParameteri(int i, int i2, int i3) {
        this.calls++;
        this.gl31.glFramebufferParameteri(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetFramebufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glGetFramebufferParameteriv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetProgramInterfaceiv(int i, int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glGetProgramInterfaceiv(i, i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public int glGetProgramResourceIndex(int i, int i2, String str) {
        this.calls++;
        int glGetProgramResourceIndex = this.gl31.glGetProgramResourceIndex(i, i2, str);
        check();
        return glGetProgramResourceIndex;
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public String glGetProgramResourceName(int i, int i2, int i3) {
        this.calls++;
        String glGetProgramResourceName = this.gl31.glGetProgramResourceName(i, i2, i3);
        check();
        return glGetProgramResourceName;
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetProgramResourceiv(int i, int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3) {
        this.calls++;
        this.gl31.glGetProgramResourceiv(i, i2, i3, intBuffer, intBuffer2, intBuffer3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public int glGetProgramResourceLocation(int i, int i2, String str) {
        this.calls++;
        int glGetProgramResourceLocation = this.gl31.glGetProgramResourceLocation(i, i2, str);
        check();
        return glGetProgramResourceLocation;
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glUseProgramStages(int i, int i2, int i3) {
        this.calls++;
        this.gl31.glUseProgramStages(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glActiveShaderProgram(int i, int i2) {
        this.calls++;
        this.gl31.glActiveShaderProgram(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public int glCreateShaderProgramv(int i, String[] strArr) {
        this.calls++;
        int glCreateShaderProgramv = this.gl31.glCreateShaderProgramv(i, strArr);
        check();
        return glCreateShaderProgramv;
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glBindProgramPipeline(int i) {
        this.calls++;
        this.gl31.glBindProgramPipeline(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDeleteProgramPipelines(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glDeleteProgramPipelines(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGenProgramPipelines(int i, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glGenProgramPipelines(i, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public boolean glIsProgramPipeline(int i) {
        this.calls++;
        boolean glIsProgramPipeline = this.gl31.glIsProgramPipeline(i);
        check();
        return glIsProgramPipeline;
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetProgramPipelineiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glGetProgramPipelineiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1i(int i, int i2, int i3) {
        this.calls++;
        this.gl31.glProgramUniform1i(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2i(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl31.glProgramUniform2i(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3i(int i, int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl31.glProgramUniform3i(i, i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4i(int i, int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl31.glProgramUniform4i(i, i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1ui(int i, int i2, int i3) {
        this.calls++;
        this.gl31.glProgramUniform1ui(i, i2, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2ui(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl31.glProgramUniform2ui(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3ui(int i, int i2, int i3, int i4, int i5) {
        this.calls++;
        this.gl31.glProgramUniform3ui(i, i2, i3, i4, i5);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4ui(int i, int i2, int i3, int i4, int i5, int i6) {
        this.calls++;
        this.gl31.glProgramUniform4ui(i, i2, i3, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1f(int i, int i2, float f) {
        this.calls++;
        this.gl31.glProgramUniform1f(i, i2, f);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2f(int i, int i2, float f, float f2) {
        this.calls++;
        this.gl31.glProgramUniform2f(i, i2, f, f2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3f(int i, int i2, float f, float f2, float f3) {
        this.calls++;
        this.gl31.glProgramUniform3f(i, i2, f, f2, f3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4f(int i, int i2, float f, float f2, float f3, float f4) {
        this.calls++;
        this.gl31.glProgramUniform4f(i, i2, f, f2, f3, f4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform1iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform2iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform3iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4iv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform4iv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1uiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform1uiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2uiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform2uiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3uiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform3uiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4uiv(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glProgramUniform4uiv(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniform1fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniform2fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniform3fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4fv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniform4fv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix2fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix3fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix4fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix2x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix2x3fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix3x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix3x2fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix2x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix2x4fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix4x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix4x2fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix3x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix3x4fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix4x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glProgramUniformMatrix4x3fv(i, i2, z, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glValidateProgramPipeline(int i) {
        this.calls++;
        this.gl31.glValidateProgramPipeline(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public String glGetProgramPipelineInfoLog(int i) {
        this.calls++;
        String glGetProgramPipelineInfoLog = this.gl31.glGetProgramPipelineInfoLog(i);
        check();
        return glGetProgramPipelineInfoLog;
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glBindImageTexture(int i, int i2, int i3, boolean z, int i4, int i5, int i6) {
        this.calls++;
        this.gl31.glBindImageTexture(i, i2, i3, z, i4, i5, i6);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetBooleani_v(int i, int i2, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glGetBooleani_v(i, i2, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glMemoryBarrier(int i) {
        this.calls++;
        this.gl31.glMemoryBarrier(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glMemoryBarrierByRegion(int i) {
        this.calls++;
        this.gl31.glMemoryBarrierByRegion(i);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glTexStorage2DMultisample(int i, int i2, int i3, int i4, int i5, boolean z) {
        this.calls++;
        this.gl31.glTexStorage2DMultisample(i, i2, i3, i4, i5, z);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetMultisamplefv(int i, int i2, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glGetMultisamplefv(i, i2, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glSampleMaski(int i, int i2) {
        this.calls++;
        this.gl31.glSampleMaski(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetTexLevelParameteriv(int i, int i2, int i3, IntBuffer intBuffer) {
        this.calls++;
        this.gl31.glGetTexLevelParameteriv(i, i2, i3, intBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetTexLevelParameterfv(int i, int i2, int i3, FloatBuffer floatBuffer) {
        this.calls++;
        this.gl31.glGetTexLevelParameterfv(i, i2, i3, floatBuffer);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glBindVertexBuffer(int i, int i2, long j, int i3) {
        this.calls++;
        this.gl31.glBindVertexBuffer(i, i2, j, i3);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexAttribFormat(int i, int i2, int i3, boolean z, int i4) {
        this.calls++;
        this.gl31.glVertexAttribFormat(i, i2, i3, z, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexAttribIFormat(int i, int i2, int i3, int i4) {
        this.calls++;
        this.gl31.glVertexAttribIFormat(i, i2, i3, i4);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexAttribBinding(int i, int i2) {
        this.calls++;
        this.gl31.glVertexAttribBinding(i, i2);
        check();
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexBindingDivisor(int i, int i2) {
        this.calls++;
        this.gl31.glVertexBindingDivisor(i, i2);
        check();
    }
}
