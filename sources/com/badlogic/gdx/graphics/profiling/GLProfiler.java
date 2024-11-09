package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.GL31;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.math.FloatCounter;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/profiling/GLProfiler.class */
public class GLProfiler {
    private Graphics graphics;
    private GLInterceptor glInterceptor;
    private GLErrorListener listener;
    private boolean enabled = false;

    public GLProfiler(Graphics graphics) {
        this.graphics = graphics;
        GL32 gl32 = graphics.getGL32();
        GL31 gl31 = graphics.getGL31();
        GL30 gl30 = graphics.getGL30();
        if (gl32 != null) {
            this.glInterceptor = new GL32Interceptor(this, gl32);
        } else if (gl31 != null) {
            this.glInterceptor = new GL31Interceptor(this, gl31);
        } else if (gl30 != null) {
            this.glInterceptor = new GL30Interceptor(this, gl30);
        } else {
            this.glInterceptor = new GL20Interceptor(this, graphics.getGL20());
        }
        this.listener = GLErrorListener.LOGGING_LISTENER;
    }

    public void enable() {
        if (this.enabled) {
            return;
        }
        if (this.glInterceptor instanceof GL32) {
            this.graphics.setGL32((GL32) this.glInterceptor);
        }
        if (this.glInterceptor instanceof GL31) {
            this.graphics.setGL31((GL31) this.glInterceptor);
        }
        if (this.glInterceptor instanceof GL30) {
            this.graphics.setGL30((GL30) this.glInterceptor);
        }
        this.graphics.setGL20(this.glInterceptor);
        Gdx.gl32 = this.graphics.getGL32();
        Gdx.gl31 = this.graphics.getGL31();
        Gdx.gl30 = this.graphics.getGL30();
        Gdx.gl20 = this.graphics.getGL20();
        Gdx.gl = this.graphics.getGL20();
        this.enabled = true;
    }

    public void disable() {
        if (this.enabled) {
            if (this.glInterceptor instanceof GL32Interceptor) {
                this.graphics.setGL32(((GL32Interceptor) this.glInterceptor).gl32);
            }
            if (this.glInterceptor instanceof GL31Interceptor) {
                this.graphics.setGL31(((GL31Interceptor) this.glInterceptor).gl31);
            }
            if (this.glInterceptor instanceof GL30Interceptor) {
                this.graphics.setGL30(((GL30Interceptor) this.glInterceptor).gl30);
            }
            if (this.glInterceptor instanceof GL20Interceptor) {
                this.graphics.setGL20(((GL20Interceptor) this.graphics.getGL20()).gl20);
            }
            Gdx.gl32 = this.graphics.getGL32();
            Gdx.gl31 = this.graphics.getGL31();
            Gdx.gl30 = this.graphics.getGL30();
            Gdx.gl20 = this.graphics.getGL20();
            Gdx.gl = this.graphics.getGL20();
            this.enabled = false;
        }
    }

    public void setListener(GLErrorListener gLErrorListener) {
        this.listener = gLErrorListener;
    }

    public GLErrorListener getListener() {
        return this.listener;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public int getCalls() {
        return this.glInterceptor.getCalls();
    }

    public int getTextureBindings() {
        return this.glInterceptor.getTextureBindings();
    }

    public int getDrawCalls() {
        return this.glInterceptor.getDrawCalls();
    }

    public int getShaderSwitches() {
        return this.glInterceptor.getShaderSwitches();
    }

    public FloatCounter getVertexCount() {
        return this.glInterceptor.getVertexCount();
    }

    public void reset() {
        this.glInterceptor.reset();
    }
}
