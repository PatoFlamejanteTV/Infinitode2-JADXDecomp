package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/RenderContext.class */
public class RenderContext {
    public final TextureBinder textureBinder;
    private boolean blending;
    private int blendSFactor;
    private int blendDFactor;
    private int depthFunc;
    private float depthRangeNear;
    private float depthRangeFar;
    private boolean depthMask;
    private int cullFace;

    public RenderContext(TextureBinder textureBinder) {
        this.textureBinder = textureBinder;
    }

    public void begin() {
        Gdx.gl.glDisable(2929);
        this.depthFunc = 0;
        Gdx.gl.glDepthMask(true);
        this.depthMask = true;
        Gdx.gl.glDisable(3042);
        this.blending = false;
        Gdx.gl.glDisable(2884);
        this.blendDFactor = 0;
        this.blendSFactor = 0;
        this.cullFace = 0;
        this.textureBinder.begin();
    }

    public void end() {
        if (this.depthFunc != 0) {
            Gdx.gl.glDisable(2929);
        }
        if (!this.depthMask) {
            Gdx.gl.glDepthMask(true);
        }
        if (this.blending) {
            Gdx.gl.glDisable(3042);
        }
        if (this.cullFace > 0) {
            Gdx.gl.glDisable(2884);
        }
        this.textureBinder.end();
    }

    public void setDepthMask(boolean z) {
        if (this.depthMask != z) {
            GL20 gl20 = Gdx.gl;
            this.depthMask = z;
            gl20.glDepthMask(z);
        }
    }

    public void setDepthTest(int i) {
        setDepthTest(i, 0.0f, 1.0f);
    }

    public void setDepthTest(int i, float f, float f2) {
        boolean z = this.depthFunc != 0;
        boolean z2 = i != 0;
        if (this.depthFunc != i) {
            this.depthFunc = i;
            if (z2) {
                Gdx.gl.glEnable(2929);
                Gdx.gl.glDepthFunc(i);
            } else {
                Gdx.gl.glDisable(2929);
            }
        }
        if (z2) {
            if (!z || this.depthFunc != i) {
                GL20 gl20 = Gdx.gl;
                this.depthFunc = i;
                gl20.glDepthFunc(i);
            }
            if (!z || this.depthRangeNear != f || this.depthRangeFar != f2) {
                GL20 gl202 = Gdx.gl;
                this.depthRangeNear = f;
                this.depthRangeFar = f2;
                gl202.glDepthRangef(f, f2);
            }
        }
    }

    public void setBlending(boolean z, int i, int i2) {
        if (z != this.blending) {
            this.blending = z;
            if (z) {
                Gdx.gl.glEnable(3042);
            } else {
                Gdx.gl.glDisable(3042);
            }
        }
        if (z) {
            if (this.blendSFactor != i || this.blendDFactor != i2) {
                Gdx.gl.glBlendFunc(i, i2);
                this.blendSFactor = i;
                this.blendDFactor = i2;
            }
        }
    }

    public void setCullFace(int i) {
        if (i != this.cullFace) {
            this.cullFace = i;
            if (i == 1028 || i == 1029 || i == 1032) {
                Gdx.gl.glEnable(2884);
                Gdx.gl.glCullFace(i);
            } else {
                Gdx.gl.glDisable(2884);
            }
        }
    }
}
