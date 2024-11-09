package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GL45;
import org.lwjgl.opengl.GLDebugMessageCallbackI;
import org.lwjgl.opengl.KHRBlendEquationAdvanced;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3GL32.class */
public class Lwjgl3GL32 extends Lwjgl3GL31 implements GL32 {
    private static final PointerBuffer pb = PointerBuffer.allocateDirect(16);

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendBarrier() {
        KHRBlendEquationAdvanced.glBlendBarrierKHR();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glCopyImageSubData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        GL43.glCopyImageSubData(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDebugMessageControl(int i, int i2, int i3, IntBuffer intBuffer, boolean z) {
        GL43.glDebugMessageControl(i, i2, i3, intBuffer, z);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDebugMessageInsert(int i, int i2, int i3, int i4, String str) {
        GL43.glDebugMessageInsert(i, i2, i3, i4, str);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDebugMessageCallback(final GL32.DebugProc debugProc) {
        if (debugProc != null) {
            GL43.glDebugMessageCallback(new GLDebugMessageCallbackI() { // from class: com.badlogic.gdx.backends.lwjgl3.Lwjgl3GL32.1
                @Override // org.lwjgl.opengl.GLDebugMessageCallbackI
                public void invoke(int i, int i2, int i3, int i4, int i5, long j, long j2) {
                    debugProc.onMessage(i, i2, i3, i4, MemoryUtil.memUTF8(j, i5));
                }
            }, 0L);
        } else {
            GL43.glDebugMessageCallback(null, 0L);
        }
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public int glGetDebugMessageLog(int i, IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, IntBuffer intBuffer4, IntBuffer intBuffer5, ByteBuffer byteBuffer) {
        return GL43.glGetDebugMessageLog(i, intBuffer, intBuffer2, intBuffer3, intBuffer4, intBuffer5, byteBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glPushDebugGroup(int i, int i2, String str) {
        GL43.glPushDebugGroup(i, i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glPopDebugGroup() {
        GL43.glPopDebugGroup();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glObjectLabel(int i, int i2, String str) {
        GL43.glObjectLabel(i, i2, str);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public String glGetObjectLabel(int i, int i2) {
        return GL43.glGetObjectLabel(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public long glGetPointerv(int i) {
        pb.reset();
        GL43.glGetPointerv(i, pb);
        return pb.get();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glEnablei(int i, int i2) {
        GL30.glEnablei(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDisablei(int i, int i2) {
        GL30.glDisablei(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendEquationi(int i, int i2) {
        GL40.glBlendEquationi(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendEquationSeparatei(int i, int i2, int i3) {
        GL40.glBlendEquationSeparatei(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendFunci(int i, int i2, int i3) {
        GL40.glBlendFunci(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glBlendFuncSeparatei(int i, int i2, int i3, int i4, int i5) {
        GL40.glBlendFuncSeparatei(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glColorMaski(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        GL30.glColorMaski(i, z, z2, z3, z4);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public boolean glIsEnabledi(int i, int i2) {
        return GL30.glIsEnabledi(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawElementsBaseVertex(int i, int i2, int i3, Buffer buffer, int i4) {
        if ((buffer instanceof ShortBuffer) && i3 == 5123) {
            ShortBuffer shortBuffer = (ShortBuffer) buffer;
            int position = shortBuffer.position();
            int limit = shortBuffer.limit();
            shortBuffer.limit(position + i2);
            org.lwjgl.opengl.GL32.glDrawElementsBaseVertex(i, shortBuffer, i4);
            shortBuffer.limit(limit);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i3 == 5123) {
            ShortBuffer asShortBuffer = ((ByteBuffer) buffer).asShortBuffer();
            int position2 = asShortBuffer.position();
            int limit2 = asShortBuffer.limit();
            asShortBuffer.limit(position2 + i2);
            org.lwjgl.opengl.GL32.glDrawElementsBaseVertex(i, asShortBuffer, i4);
            asShortBuffer.limit(limit2);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i3 == 5121) {
            ByteBuffer byteBuffer = (ByteBuffer) buffer;
            int position3 = byteBuffer.position();
            int limit3 = byteBuffer.limit();
            byteBuffer.limit(position3 + i2);
            org.lwjgl.opengl.GL32.glDrawElementsBaseVertex(i, byteBuffer, i4);
            byteBuffer.limit(limit3);
            return;
        }
        throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ShortBuffer or ByteBuffer instead.");
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawRangeElementsBaseVertex(int i, int i2, int i3, int i4, int i5, Buffer buffer, int i6) {
        if ((buffer instanceof ShortBuffer) && i5 == 5123) {
            ShortBuffer shortBuffer = (ShortBuffer) buffer;
            int position = shortBuffer.position();
            int limit = shortBuffer.limit();
            shortBuffer.limit(position + i4);
            org.lwjgl.opengl.GL32.glDrawRangeElementsBaseVertex(i, i2, i3, shortBuffer, i6);
            shortBuffer.limit(limit);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i5 == 5123) {
            ShortBuffer asShortBuffer = ((ByteBuffer) buffer).asShortBuffer();
            int position2 = asShortBuffer.position();
            int limit2 = asShortBuffer.limit();
            asShortBuffer.limit(position2 + i4);
            org.lwjgl.opengl.GL32.glDrawRangeElementsBaseVertex(i, i2, i3, asShortBuffer, i6);
            asShortBuffer.limit(limit2);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i5 == 5121) {
            ByteBuffer byteBuffer = (ByteBuffer) buffer;
            int position3 = byteBuffer.position();
            int limit3 = byteBuffer.limit();
            byteBuffer.limit(position3 + i4);
            org.lwjgl.opengl.GL32.glDrawRangeElementsBaseVertex(i, i2, i3, byteBuffer, i6);
            byteBuffer.limit(limit3);
            return;
        }
        throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ShortBuffer or ByteBuffer instead.");
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawElementsInstancedBaseVertex(int i, int i2, int i3, Buffer buffer, int i4, int i5) {
        if ((buffer instanceof ShortBuffer) && i3 == 5123) {
            ShortBuffer shortBuffer = (ShortBuffer) buffer;
            int position = shortBuffer.position();
            int limit = shortBuffer.limit();
            shortBuffer.limit(position + i2);
            org.lwjgl.opengl.GL32.glDrawElementsInstancedBaseVertex(i, shortBuffer, i4, i5);
            shortBuffer.limit(limit);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i3 == 5123) {
            ShortBuffer asShortBuffer = ((ByteBuffer) buffer).asShortBuffer();
            int position2 = asShortBuffer.position();
            int limit2 = asShortBuffer.limit();
            asShortBuffer.limit(position2 + i2);
            org.lwjgl.opengl.GL32.glDrawElementsInstancedBaseVertex(i, asShortBuffer, i4, i5);
            asShortBuffer.limit(limit2);
            return;
        }
        if ((buffer instanceof ByteBuffer) && i3 == 5121) {
            ByteBuffer byteBuffer = (ByteBuffer) buffer;
            int position3 = byteBuffer.position();
            int limit3 = byteBuffer.limit();
            byteBuffer.limit(position3 + i2);
            org.lwjgl.opengl.GL32.glDrawElementsInstancedBaseVertex(i, byteBuffer, i4, i5);
            byteBuffer.limit(limit3);
            return;
        }
        throw new GdxRuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ShortBuffer or ByteBuffer instead.");
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glDrawElementsInstancedBaseVertex(int i, int i2, int i3, int i4, int i5, int i6) {
        org.lwjgl.opengl.GL32.glDrawElementsInstancedBaseVertex(i, i2, i3, i4, i5, i6);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glFramebufferTexture(int i, int i2, int i3, int i4) {
        org.lwjgl.opengl.GL32.glFramebufferTexture(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public int glGetGraphicsResetStatus() {
        return GL45.glGetGraphicsResetStatus();
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glReadnPixels(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        if (buffer == null) {
            GL45.glReadnPixels(i, i2, i3, i4, i5, i6, i7, 0L);
            return;
        }
        int limit = buffer.limit();
        buffer.limit(i7);
        if (buffer instanceof ByteBuffer) {
            GL45.glReadnPixels(i, i2, i3, i4, i5, i6, (ByteBuffer) buffer);
        } else if (buffer instanceof IntBuffer) {
            GL45.glReadnPixels(i, i2, i3, i4, i5, i6, (IntBuffer) buffer);
        } else if (buffer instanceof ShortBuffer) {
            GL45.glReadnPixels(i, i2, i3, i4, i5, i6, (ShortBuffer) buffer);
        } else if (buffer instanceof FloatBuffer) {
            GL45.glReadnPixels(i, i2, i3, i4, i5, i6, (FloatBuffer) buffer);
        } else {
            throw new GdxRuntimeException("buffer type not supported");
        }
        buffer.limit(limit);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetnUniformfv(int i, int i2, FloatBuffer floatBuffer) {
        GL45.glGetnUniformfv(i, i2, floatBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetnUniformiv(int i, int i2, IntBuffer intBuffer) {
        GL45.glGetnUniformiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetnUniformuiv(int i, int i2, IntBuffer intBuffer) {
        GL45.glGetnUniformuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glMinSampleShading(float f) {
        GL40.glMinSampleShading(f);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glPatchParameteri(int i, int i2) {
        GL40.glPatchParameteri(i, i2);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexParameterIiv(int i, int i2, IntBuffer intBuffer) {
        GL30.glTexParameterIiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        GL30.glTexParameterIuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetTexParameterIiv(int i, int i2, IntBuffer intBuffer) {
        GL30.glGetTexParameterIiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetTexParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        GL30.glGetTexParameterIuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glSamplerParameterIiv(int i, int i2, IntBuffer intBuffer) {
        GL33.glSamplerParameterIiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glSamplerParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        GL33.glSamplerParameterIuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetSamplerParameterIiv(int i, int i2, IntBuffer intBuffer) {
        GL33.glGetSamplerParameterIiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glGetSamplerParameterIuiv(int i, int i2, IntBuffer intBuffer) {
        GL33.glGetSamplerParameterIuiv(i, i2, intBuffer);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexBuffer(int i, int i2, int i3) {
        GL31.glTexBuffer(i, i2, i3);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexBufferRange(int i, int i2, int i3, int i4, int i5) {
        GL43.glTexBufferRange(i, i2, i3, i4, i5);
    }

    @Override // com.badlogic.gdx.graphics.GL32
    public void glTexStorage3DMultisample(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        GL43.glTexStorage3DMultisample(i, i2, i3, i4, i5, i6, z);
    }
}
