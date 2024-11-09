package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.FloatCounter;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/profiling/GLInterceptor.class */
public abstract class GLInterceptor implements GL20 {
    protected int calls;
    protected int textureBindings;
    protected int drawCalls;
    protected int shaderSwitches;
    protected final FloatCounter vertexCount = new FloatCounter(0);
    protected GLProfiler glProfiler;

    /* JADX INFO: Access modifiers changed from: protected */
    public GLInterceptor(GLProfiler gLProfiler) {
        this.glProfiler = gLProfiler;
    }

    public static String resolveErrorNumber(int i) {
        switch (i) {
            case 1280:
                return "GL_INVALID_ENUM";
            case 1281:
                return "GL_INVALID_VALUE";
            case 1282:
                return "GL_INVALID_OPERATION";
            case 1283:
            case 1284:
            default:
                return "number " + i;
            case 1285:
                return "GL_OUT_OF_MEMORY";
            case 1286:
                return "GL_INVALID_FRAMEBUFFER_OPERATION";
        }
    }

    public int getCalls() {
        return this.calls;
    }

    public int getTextureBindings() {
        return this.textureBindings;
    }

    public int getDrawCalls() {
        return this.drawCalls;
    }

    public int getShaderSwitches() {
        return this.shaderSwitches;
    }

    public FloatCounter getVertexCount() {
        return this.vertexCount;
    }

    public void reset() {
        this.calls = 0;
        this.textureBindings = 0;
        this.drawCalls = 0;
        this.shaderSwitches = 0;
        this.vertexCount.reset();
    }
}
