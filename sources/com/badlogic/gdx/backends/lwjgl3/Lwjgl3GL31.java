package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.graphics.GL31;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL41;
import org.lwjgl.opengl.GL42;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GL46;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3GL31.class */
public class Lwjgl3GL31 extends Lwjgl3GL30 implements GL31 {
    private static final ByteBuffer tmpByteBuffer = BufferUtils.newByteBuffer(16);

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glInvalidateSubFramebuffer(int i, int i2, IntBuffer intBuffer, int i3, int i4, int i5, int i6) {
        super.glInvalidateSubFramebuffer(i, i2, intBuffer, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glInvalidateFramebuffer(int i, int i2, IntBuffer intBuffer) {
        super.glInvalidateFramebuffer(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glProgramParameteri(int i, int i2, int i3) {
        super.glProgramParameteri(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glResumeTransformFeedback() {
        super.glResumeTransformFeedback();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glPauseTransformFeedback() {
        super.glPauseTransformFeedback();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ boolean glIsTransformFeedback(int i) {
        return super.glIsTransformFeedback(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenTransformFeedbacks(int i, IntBuffer intBuffer) {
        super.glGenTransformFeedbacks(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenTransformFeedbacks(int i, int[] iArr, int i2) {
        super.glGenTransformFeedbacks(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteTransformFeedbacks(int i, IntBuffer intBuffer) {
        super.glDeleteTransformFeedbacks(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteTransformFeedbacks(int i, int[] iArr, int i2) {
        super.glDeleteTransformFeedbacks(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBindTransformFeedback(int i, int i2) {
        super.glBindTransformFeedback(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glVertexAttribDivisor(int i, int i2) {
        super.glVertexAttribDivisor(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetSamplerParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        super.glGetSamplerParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetSamplerParameteriv(int i, int i2, IntBuffer intBuffer) {
        super.glGetSamplerParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glSamplerParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        super.glSamplerParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glSamplerParameterf(int i, int i2, float f) {
        super.glSamplerParameterf(i, i2, f);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glSamplerParameteriv(int i, int i2, IntBuffer intBuffer) {
        super.glSamplerParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glSamplerParameteri(int i, int i2, int i3) {
        super.glSamplerParameteri(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBindSampler(int i, int i2) {
        super.glBindSampler(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ boolean glIsSampler(int i) {
        return super.glIsSampler(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteSamplers(int i, IntBuffer intBuffer) {
        super.glDeleteSamplers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteSamplers(int i, int[] iArr, int i2) {
        super.glDeleteSamplers(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenSamplers(int i, IntBuffer intBuffer) {
        super.glGenSamplers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenSamplers(int i, int[] iArr, int i2) {
        super.glGenSamplers(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetBufferParameteri64v(int i, int i2, LongBuffer longBuffer) {
        super.glGetBufferParameteri64v(i, i2, longBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetInteger64v(int i, LongBuffer longBuffer) {
        super.glGetInteger64v(i, longBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDrawElementsInstanced(int i, int i2, int i3, int i4, int i5) {
        super.glDrawElementsInstanced(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDrawArraysInstanced(int i, int i2, int i3, int i4) {
        super.glDrawArraysInstanced(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniformBlockBinding(int i, int i2, int i3) {
        super.glUniformBlockBinding(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ String glGetActiveUniformBlockName(int i, int i2) {
        return super.glGetActiveUniformBlockName(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetActiveUniformBlockName(int i, int i2, Buffer buffer, Buffer buffer2) {
        super.glGetActiveUniformBlockName(i, i2, buffer, buffer2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetActiveUniformBlockiv(int i, int i2, int i3, IntBuffer intBuffer) {
        super.glGetActiveUniformBlockiv(i, i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ int glGetUniformBlockIndex(int i, String str) {
        return super.glGetUniformBlockIndex(i, str);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetActiveUniformsiv(int i, int i2, IntBuffer intBuffer, int i3, IntBuffer intBuffer2) {
        super.glGetActiveUniformsiv(i, i2, intBuffer, i3, intBuffer2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetUniformIndices(int i, String[] strArr, IntBuffer intBuffer) {
        super.glGetUniformIndices(i, strArr, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glCopyBufferSubData(int i, int i2, int i3, int i4, int i5) {
        super.glCopyBufferSubData(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ String glGetStringi(int i, int i2) {
        return super.glGetStringi(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glClearBufferfi(int i, int i2, float f, int i3) {
        super.glClearBufferfi(i, i2, f, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glClearBufferfv(int i, int i2, FloatBuffer floatBuffer) {
        super.glClearBufferfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glClearBufferuiv(int i, int i2, IntBuffer intBuffer) {
        super.glClearBufferuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glClearBufferiv(int i, int i2, IntBuffer intBuffer) {
        super.glClearBufferiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniform4uiv(int i, int i2, IntBuffer intBuffer) {
        super.glUniform4uiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniform3uiv(int i, int i2, IntBuffer intBuffer) {
        super.glUniform3uiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniform1uiv(int i, int i2, IntBuffer intBuffer) {
        super.glUniform1uiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ int glGetFragDataLocation(int i, String str) {
        return super.glGetFragDataLocation(i, str);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetUniformuiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetUniformuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glVertexAttribI4ui(int i, int i2, int i3, int i4, int i5) {
        super.glVertexAttribI4ui(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glVertexAttribI4i(int i, int i2, int i3, int i4, int i5) {
        super.glVertexAttribI4i(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetVertexAttribIuiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetVertexAttribIuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetVertexAttribIiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetVertexAttribIiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glVertexAttribIPointer(int i, int i2, int i3, int i4, int i5) {
        super.glVertexAttribIPointer(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glTransformFeedbackVaryings(int i, String[] strArr, int i2) {
        super.glTransformFeedbackVaryings(i, strArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBindBufferBase(int i, int i2, int i3) {
        super.glBindBufferBase(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBindBufferRange(int i, int i2, int i3, int i4, int i5) {
        super.glBindBufferRange(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glEndTransformFeedback() {
        super.glEndTransformFeedback();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBeginTransformFeedback(int i) {
        super.glBeginTransformFeedback(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ boolean glIsVertexArray(int i) {
        return super.glIsVertexArray(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenVertexArrays(int i, IntBuffer intBuffer) {
        super.glGenVertexArrays(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenVertexArrays(int i, int[] iArr, int i2) {
        super.glGenVertexArrays(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteVertexArrays(int i, IntBuffer intBuffer) {
        super.glDeleteVertexArrays(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteVertexArrays(int i, int[] iArr, int i2) {
        super.glDeleteVertexArrays(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBindVertexArray(int i) {
        super.glBindVertexArray(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glFlushMappedBufferRange(int i, int i2, int i3) {
        super.glFlushMappedBufferRange(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ Buffer glMapBufferRange(int i, int i2, int i3, int i4) {
        return super.glMapBufferRange(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glFramebufferTextureLayer(int i, int i2, int i3, int i4, int i5) {
        super.glFramebufferTextureLayer(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glFramebufferRenderbuffer(int i, int i2, int i3, int i4) {
        super.glFramebufferRenderbuffer(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glFramebufferTexture2D(int i, int i2, int i3, int i4, int i5) {
        super.glFramebufferTexture2D(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glRenderbufferStorageMultisample(int i, int i2, int i3, int i4, int i5) {
        super.glRenderbufferStorageMultisample(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glRenderbufferStorage(int i, int i2, int i3, int i4) {
        super.glRenderbufferStorage(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ boolean glIsRenderbuffer(int i) {
        return super.glIsRenderbuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ boolean glIsFramebuffer(int i) {
        return super.glIsFramebuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetRenderbufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        super.glGetRenderbufferParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glGenRenderbuffer() {
        return super.glGenRenderbuffer();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGenRenderbuffers(int i, IntBuffer intBuffer) {
        super.glGenRenderbuffers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glGenFramebuffer() {
        return super.glGenFramebuffer();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGenFramebuffers(int i, IntBuffer intBuffer) {
        super.glGenFramebuffers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGenerateMipmap(int i) {
        super.glGenerateMipmap(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteRenderbuffer(int i) {
        super.glDeleteRenderbuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteRenderbuffers(int i, IntBuffer intBuffer) {
        super.glDeleteRenderbuffers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteFramebuffer(int i) {
        super.glDeleteFramebuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteFramebuffers(int i, IntBuffer intBuffer) {
        super.glDeleteFramebuffers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glCheckFramebufferStatus(int i) {
        return super.glCheckFramebufferStatus(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBindRenderbuffer(int i, int i2) {
        super.glBindRenderbuffer(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBindFramebuffer(int i, int i2) {
        super.glBindFramebuffer(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBlitFramebuffer(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        super.glBlitFramebuffer(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniformMatrix4x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix4x3fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniformMatrix3x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix3x4fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniformMatrix4x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix4x2fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniformMatrix2x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix2x4fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniformMatrix3x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix3x2fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glUniformMatrix2x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix2x3fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDrawBuffers(int i, IntBuffer intBuffer) {
        super.glDrawBuffers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ Buffer glGetBufferPointerv(int i, int i2) {
        return super.glGetBufferPointerv(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ boolean glUnmapBuffer(int i) {
        return super.glUnmapBuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetQueryObjectuiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetQueryObjectuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGetQueryiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetQueryiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glEndQuery(int i) {
        super.glEndQuery(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glBeginQuery(int i, int i2) {
        super.glBeginQuery(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ boolean glIsQuery(int i) {
        return super.glIsQuery(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteQueries(int i, IntBuffer intBuffer) {
        super.glDeleteQueries(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDeleteQueries(int i, int[] iArr, int i2) {
        super.glDeleteQueries(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenQueries(int i, IntBuffer intBuffer) {
        super.glGenQueries(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glGenQueries(int i, int[] iArr, int i2) {
        super.glGenQueries(i, iArr, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glCopyTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        super.glCopyTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        super.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, Buffer buffer) {
        super.glTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        super.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glTexImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        super.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glTexImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Buffer buffer) {
        super.glTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        super.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDrawRangeElements(int i, int i2, int i3, int i4, int i5, int i6) {
        super.glDrawRangeElements(i, i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glDrawRangeElements(int i, int i2, int i3, int i4, int i5, Buffer buffer) {
        super.glDrawRangeElements(i, i2, i3, i4, i5, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL30, com.badlogic.gdx.graphics.GL30
    public /* bridge */ /* synthetic */ void glReadBuffer(int i) {
        super.glReadBuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, int i5) {
        super.glVertexAttribPointer(i, i2, i3, z, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDrawElements(int i, int i2, int i3, int i4) {
        super.glDrawElements(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glViewport(int i, int i2, int i3, int i4) {
        super.glViewport(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, Buffer buffer) {
        super.glVertexAttribPointer(i, i2, i3, z, i4, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib4fv(int i, FloatBuffer floatBuffer) {
        super.glVertexAttrib4fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib4f(int i, float f, float f2, float f3, float f4) {
        super.glVertexAttrib4f(i, f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib3fv(int i, FloatBuffer floatBuffer) {
        super.glVertexAttrib3fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib3f(int i, float f, float f2, float f3) {
        super.glVertexAttrib3f(i, f, f2, f3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib2fv(int i, FloatBuffer floatBuffer) {
        super.glVertexAttrib2fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib2f(int i, float f, float f2) {
        super.glVertexAttrib2f(i, f, f2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib1fv(int i, FloatBuffer floatBuffer) {
        super.glVertexAttrib1fv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glVertexAttrib1f(int i, float f) {
        super.glVertexAttrib1f(i, f);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glValidateProgram(int i) {
        super.glValidateProgram(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUseProgram(int i) {
        super.glUseProgram(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniformMatrix4fv(int i, int i2, boolean z, float[] fArr, int i3) {
        super.glUniformMatrix4fv(i, i2, z, fArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniformMatrix4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix4fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniformMatrix3fv(int i, int i2, boolean z, float[] fArr, int i3) {
        super.glUniformMatrix3fv(i, i2, z, fArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniformMatrix3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix3fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniformMatrix2fv(int i, int i2, boolean z, float[] fArr, int i3) {
        super.glUniformMatrix2fv(i, i2, z, fArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniformMatrix2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        super.glUniformMatrix2fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform4iv(int i, int i2, int[] iArr, int i3) {
        super.glUniform4iv(i, i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform4iv(int i, int i2, IntBuffer intBuffer) {
        super.glUniform4iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform4i(int i, int i2, int i3, int i4, int i5) {
        super.glUniform4i(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform4fv(int i, int i2, float[] fArr, int i3) {
        super.glUniform4fv(i, i2, fArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform4fv(int i, int i2, FloatBuffer floatBuffer) {
        super.glUniform4fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform4f(int i, float f, float f2, float f3, float f4) {
        super.glUniform4f(i, f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform3iv(int i, int i2, int[] iArr, int i3) {
        super.glUniform3iv(i, i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform3iv(int i, int i2, IntBuffer intBuffer) {
        super.glUniform3iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform3i(int i, int i2, int i3, int i4) {
        super.glUniform3i(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform3fv(int i, int i2, float[] fArr, int i3) {
        super.glUniform3fv(i, i2, fArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform3fv(int i, int i2, FloatBuffer floatBuffer) {
        super.glUniform3fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform3f(int i, float f, float f2, float f3) {
        super.glUniform3f(i, f, f2, f3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform2iv(int i, int i2, int[] iArr, int i3) {
        super.glUniform2iv(i, i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform2iv(int i, int i2, IntBuffer intBuffer) {
        super.glUniform2iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform2i(int i, int i2, int i3) {
        super.glUniform2i(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform2fv(int i, int i2, float[] fArr, int i3) {
        super.glUniform2fv(i, i2, fArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform2fv(int i, int i2, FloatBuffer floatBuffer) {
        super.glUniform2fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform2f(int i, float f, float f2) {
        super.glUniform2f(i, f, f2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform1iv(int i, int i2, int[] iArr, int i3) {
        super.glUniform1iv(i, i2, iArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform1iv(int i, int i2, IntBuffer intBuffer) {
        super.glUniform1iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform1i(int i, int i2) {
        super.glUniform1i(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform1fv(int i, int i2, float[] fArr, int i3) {
        super.glUniform1fv(i, i2, fArr, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform1fv(int i, int i2, FloatBuffer floatBuffer) {
        super.glUniform1fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glUniform1f(int i, float f) {
        super.glUniform1f(i, f);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        super.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        super.glTexParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glTexParameteri(int i, int i2, int i3) {
        super.glTexParameteri(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        super.glTexParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glTexParameterf(int i, int i2, float f) {
        super.glTexParameterf(i, i2, f);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        super.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glStencilOpSeparate(int i, int i2, int i3, int i4) {
        super.glStencilOpSeparate(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glStencilOp(int i, int i2, int i3) {
        super.glStencilOp(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glStencilMaskSeparate(int i, int i2) {
        super.glStencilMaskSeparate(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glStencilMask(int i) {
        super.glStencilMask(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glStencilFuncSeparate(int i, int i2, int i3, int i4) {
        super.glStencilFuncSeparate(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glStencilFunc(int i, int i2, int i3) {
        super.glStencilFunc(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glShaderSource(int i, String str) {
        super.glShaderSource(i, str);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glShaderBinary(int i, IntBuffer intBuffer, int i2, Buffer buffer, int i3) {
        super.glShaderBinary(i, intBuffer, i2, buffer, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glScissor(int i, int i2, int i3, int i4) {
        super.glScissor(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glSampleCoverage(float f, boolean z) {
        super.glSampleCoverage(f, z);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glReleaseShaderCompiler() {
        super.glReleaseShaderCompiler();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glReadPixels(int i, int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        super.glReadPixels(i, i2, i3, i4, i5, i6, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glPolygonOffset(float f, float f2) {
        super.glPolygonOffset(f, f2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glPixelStorei(int i, int i2) {
        super.glPixelStorei(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glLinkProgram(int i) {
        super.glLinkProgram(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glLineWidth(float f) {
        super.glLineWidth(f);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ boolean glIsTexture(int i) {
        return super.glIsTexture(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ boolean glIsShader(int i) {
        return super.glIsShader(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ boolean glIsProgram(int i) {
        return super.glIsProgram(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ boolean glIsEnabled(int i) {
        return super.glIsEnabled(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ boolean glIsBuffer(int i) {
        return super.glIsBuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glHint(int i, int i2) {
        super.glHint(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetVertexAttribiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetVertexAttribiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetVertexAttribfv(int i, int i2, FloatBuffer floatBuffer) {
        super.glGetVertexAttribfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetVertexAttribPointerv(int i, int i2, Buffer buffer) {
        super.glGetVertexAttribPointerv(i, i2, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetUniformiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetUniformiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetUniformfv(int i, int i2, FloatBuffer floatBuffer) {
        super.glGetUniformfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glGetUniformLocation(int i, String str) {
        return super.glGetUniformLocation(i, str);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        super.glGetTexParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        super.glGetTexParameterfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ String glGetString(int i) {
        return super.glGetString(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetShaderiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetShaderiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetShaderPrecisionFormat(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        super.glGetShaderPrecisionFormat(i, i2, intBuffer, intBuffer2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ String glGetShaderInfoLog(int i) {
        return super.glGetShaderInfoLog(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetProgramiv(int i, int i2, IntBuffer intBuffer) {
        super.glGetProgramiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ String glGetProgramInfoLog(int i) {
        return super.glGetProgramInfoLog(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetIntegerv(int i, IntBuffer intBuffer) {
        super.glGetIntegerv(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetFramebufferAttachmentParameteriv(int i, int i2, int i3, IntBuffer intBuffer) {
        super.glGetFramebufferAttachmentParameteriv(i, i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetFloatv(int i, FloatBuffer floatBuffer) {
        super.glGetFloatv(i, floatBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glGetError() {
        return super.glGetError();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetBufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        super.glGetBufferParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetBooleanv(int i, Buffer buffer) {
        super.glGetBooleanv(i, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glGetAttribLocation(int i, String str) {
        return super.glGetAttribLocation(i, str);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGetAttachedShaders(int i, int i2, Buffer buffer, IntBuffer intBuffer) {
        super.glGetAttachedShaders(i, i2, buffer, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ String glGetActiveUniform(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        return super.glGetActiveUniform(i, i2, intBuffer, intBuffer2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ String glGetActiveAttrib(int i, int i2, IntBuffer intBuffer, IntBuffer intBuffer2) {
        return super.glGetActiveAttrib(i, i2, intBuffer, intBuffer2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glGenTexture() {
        return super.glGenTexture();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGenTextures(int i, IntBuffer intBuffer) {
        super.glGenTextures(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glGenBuffer() {
        return super.glGenBuffer();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glGenBuffers(int i, IntBuffer intBuffer) {
        super.glGenBuffers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glFrontFace(int i) {
        super.glFrontFace(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glFlush() {
        super.glFlush();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glFinish() {
        super.glFinish();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glEnableVertexAttribArray(int i) {
        super.glEnableVertexAttribArray(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glEnable(int i) {
        super.glEnable(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDrawElements(int i, int i2, int i3, Buffer buffer) {
        super.glDrawElements(i, i2, i3, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDrawArrays(int i, int i2, int i3) {
        super.glDrawArrays(i, i2, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDisableVertexAttribArray(int i) {
        super.glDisableVertexAttribArray(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDisable(int i) {
        super.glDisable(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDetachShader(int i, int i2) {
        super.glDetachShader(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDepthRangef(float f, float f2) {
        super.glDepthRangef(f, f2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDepthMask(boolean z) {
        super.glDepthMask(z);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDepthFunc(int i) {
        super.glDepthFunc(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteTexture(int i) {
        super.glDeleteTexture(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteTextures(int i, IntBuffer intBuffer) {
        super.glDeleteTextures(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteShader(int i) {
        super.glDeleteShader(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteProgram(int i) {
        super.glDeleteProgram(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteBuffer(int i) {
        super.glDeleteBuffer(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glDeleteBuffers(int i, IntBuffer intBuffer) {
        super.glDeleteBuffers(i, intBuffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glCullFace(int i) {
        super.glCullFace(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glCreateShader(int i) {
        return super.glCreateShader(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ int glCreateProgram() {
        return super.glCreateProgram();
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glCopyTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super.glCopyTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glCopyTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super.glCopyTexImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glCompressedTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        super.glCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        super.glCompressedTexImage2D(i, i2, i3, i4, i5, i6, i7, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glCompileShader(int i) {
        super.glCompileShader(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glColorMask(boolean z, boolean z2, boolean z3, boolean z4) {
        super.glColorMask(z, z2, z3, z4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glClearStencil(int i) {
        super.glClearStencil(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glClearDepthf(float f) {
        super.glClearDepthf(f);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glClearColor(float f, float f2, float f3, float f4) {
        super.glClearColor(f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glClear(int i) {
        super.glClear(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBufferSubData(int i, int i2, int i3, Buffer buffer) {
        super.glBufferSubData(i, i2, i3, buffer);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBufferData(int i, int i2, Buffer buffer, int i3) {
        super.glBufferData(i, i2, buffer, i3);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBlendFuncSeparate(int i, int i2, int i3, int i4) {
        super.glBlendFuncSeparate(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBlendFunc(int i, int i2) {
        super.glBlendFunc(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBlendEquationSeparate(int i, int i2) {
        super.glBlendEquationSeparate(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBlendEquation(int i) {
        super.glBlendEquation(i);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBlendColor(float f, float f2, float f3, float f4) {
        super.glBlendColor(f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBindTexture(int i, int i2) {
        super.glBindTexture(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBindBuffer(int i, int i2) {
        super.glBindBuffer(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glBindAttribLocation(int i, int i2, String str) {
        super.glBindAttribLocation(i, i2, str);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glAttachShader(int i, int i2) {
        super.glAttachShader(i, i2);
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL20, com.badlogic.gdx.graphics.GL20
    public /* bridge */ /* synthetic */ void glActiveTexture(int i) {
        super.glActiveTexture(i);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDispatchCompute(int i, int i2, int i3) {
        GL43.glDispatchCompute(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDispatchComputeIndirect(long j) {
        GL43.glDispatchComputeIndirect(j);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDrawArraysIndirect(int i, long j) {
        GL40.glDrawArraysIndirect(i, j);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDrawElementsIndirect(int i, int i2, long j) {
        GL40.glDrawElementsIndirect(i, i2, j);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glFramebufferParameteri(int i, int i2, int i3) {
        GL43.glFramebufferParameteri(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetFramebufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL43.glGetFramebufferParameteriv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetProgramInterfaceiv(int i, int i2, int i3, IntBuffer intBuffer) {
        GL43.glGetProgramInterfaceiv(i, i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public int glGetProgramResourceIndex(int i, int i2, String str) {
        return GL43.glGetProgramResourceIndex(i, i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public String glGetProgramResourceName(int i, int i2, int i3) {
        return GL43.glGetProgramResourceName(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetProgramResourceiv(int i, int i2, int i3, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3) {
        GL43.glGetProgramResourceiv(i, i2, i3, intBuffer, intBuffer2, intBuffer3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public int glGetProgramResourceLocation(int i, int i2, String str) {
        return GL43.glGetProgramResourceLocation(i, i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glUseProgramStages(int i, int i2, int i3) {
        GL41.glUseProgramStages(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glActiveShaderProgram(int i, int i2) {
        GL41.glActiveShaderProgram(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public int glCreateShaderProgramv(int i, String[] strArr) {
        return GL41.glCreateShaderProgramv(i, strArr);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glBindProgramPipeline(int i) {
        GL41.glBindProgramPipeline(i);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glDeleteProgramPipelines(int i, IntBuffer intBuffer) {
        int limit = intBuffer.limit();
        intBuffer.limit(i);
        GL41.glDeleteProgramPipelines(intBuffer);
        intBuffer.limit(limit);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGenProgramPipelines(int i, IntBuffer intBuffer) {
        int limit = intBuffer.limit();
        intBuffer.limit(i);
        GL41.glGenProgramPipelines(intBuffer);
        intBuffer.limit(limit);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public boolean glIsProgramPipeline(int i) {
        return GL41.glIsProgramPipeline(i);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetProgramPipelineiv(int i, int i2, IntBuffer intBuffer) {
        GL41.glGetProgramPipelineiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1i(int i, int i2, int i3) {
        GL41.glProgramUniform1i(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2i(int i, int i2, int i3, int i4) {
        GL41.glProgramUniform2i(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3i(int i, int i2, int i3, int i4, int i5) {
        GL41.glProgramUniform3i(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4i(int i, int i2, int i3, int i4, int i5, int i6) {
        GL41.glProgramUniform4i(i, i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1ui(int i, int i2, int i3) {
        GL41.glProgramUniform1ui(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2ui(int i, int i2, int i3, int i4) {
        GL41.glProgramUniform2ui(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3ui(int i, int i2, int i3, int i4, int i5) {
        GL41.glProgramUniform3ui(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4ui(int i, int i2, int i3, int i4, int i5, int i6) {
        GL41.glProgramUniform4ui(i, i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1f(int i, int i2, float f) {
        GL41.glProgramUniform1f(i, i2, f);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2f(int i, int i2, float f, float f2) {
        GL41.glProgramUniform2f(i, i2, f, f2);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3f(int i, int i2, float f, float f2, float f3) {
        GL41.glProgramUniform3f(i, i2, f, f2, f3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4f(int i, int i2, float f, float f2, float f3, float f4) {
        GL41.glProgramUniform4f(i, i2, f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1iv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform1iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2iv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform2iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3iv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform3iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4iv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform4iv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1uiv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform1uiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2uiv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform2uiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3uiv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform3uiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4uiv(int i, int i2, IntBuffer intBuffer) {
        GL41.glProgramUniform4uiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform1fv(int i, int i2, FloatBuffer floatBuffer) {
        GL41.glProgramUniform1fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform2fv(int i, int i2, FloatBuffer floatBuffer) {
        GL41.glProgramUniform2fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform3fv(int i, int i2, FloatBuffer floatBuffer) {
        GL41.glProgramUniform3fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniform4fv(int i, int i2, FloatBuffer floatBuffer) {
        GL41.glProgramUniform4fv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix2fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix3fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix4fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix2x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix2x3fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix3x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix3x2fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix2x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix2x4fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix4x2fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix4x2fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix3x4fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix3x4fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glProgramUniformMatrix4x3fv(int i, int i2, boolean z, FloatBuffer floatBuffer) {
        GL41.glProgramUniformMatrix4x3fv(i, i2, z, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glValidateProgramPipeline(int i) {
        GL41.glValidateProgramPipeline(i);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public String glGetProgramPipelineInfoLog(int i) {
        return GL41.glGetProgramPipelineInfoLog(i);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glBindImageTexture(int i, int i2, int i3, boolean z, int i4, int i5, int i6) {
        GL42.glBindImageTexture(i, i2, i3, z, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetBooleani_v(int i, int i2, IntBuffer intBuffer) {
        GL46.glGetBooleani_v(i, i2, tmpByteBuffer);
        intBuffer.put(tmpByteBuffer.asIntBuffer());
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glMemoryBarrier(int i) {
        GL42.glMemoryBarrier(i);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glMemoryBarrierByRegion(int i) {
        GL46.glMemoryBarrierByRegion(i);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glTexStorage2DMultisample(int i, int i2, int i3, int i4, int i5, boolean z) {
        GL43.glTexStorage2DMultisample(i, i2, i3, i4, i5, z);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetMultisamplefv(int i, int i2, FloatBuffer floatBuffer) {
        GL32.glGetMultisamplefv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glSampleMaski(int i, int i2) {
        GL32.glSampleMaski(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetTexLevelParameteriv(int i, int i2, int i3, IntBuffer intBuffer) {
        GL11.glGetTexLevelParameteriv(i, i2, i3, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glGetTexLevelParameterfv(int i, int i2, int i3, FloatBuffer floatBuffer) {
        GL11.glGetTexLevelParameterfv(i, i2, i3, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glBindVertexBuffer(int i, int i2, long j, int i3) {
        GL43.glBindVertexBuffer(i, i2, j, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexAttribFormat(int i, int i2, int i3, boolean z, int i4) {
        GL43.glVertexAttribFormat(i, i2, i3, z, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexAttribIFormat(int i, int i2, int i3, int i4) {
        GL43.glVertexAttribIFormat(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexAttribBinding(int i, int i2) {
        GL43.glVertexAttribBinding(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL31
    public void glVertexBindingDivisor(int i, int i2) {
        GL43.glVertexBindingDivisor(i, i2);
    }
}
