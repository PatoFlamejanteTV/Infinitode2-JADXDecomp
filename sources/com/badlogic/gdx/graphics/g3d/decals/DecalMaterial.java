package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/decals/DecalMaterial.class */
public class DecalMaterial {
    public static final int NO_BLEND = -1;
    protected TextureRegion textureRegion;
    protected int srcBlendFactor;
    protected int dstBlendFactor;

    public void set() {
        this.textureRegion.getTexture().bind(0);
        if (!isOpaque()) {
            Gdx.gl.glBlendFunc(this.srcBlendFactor, this.dstBlendFactor);
        }
    }

    public boolean isOpaque() {
        return this.srcBlendFactor == -1;
    }

    public int getSrcBlendFactor() {
        return this.srcBlendFactor;
    }

    public int getDstBlendFactor() {
        return this.dstBlendFactor;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        DecalMaterial decalMaterial = (DecalMaterial) obj;
        return this.dstBlendFactor == decalMaterial.dstBlendFactor && this.srcBlendFactor == decalMaterial.srcBlendFactor && this.textureRegion.getTexture() == decalMaterial.textureRegion.getTexture();
    }

    public int hashCode() {
        return ((((this.textureRegion.getTexture() != null ? this.textureRegion.getTexture().hashCode() : 0) * 31) + this.srcBlendFactor) * 31) + this.dstBlendFactor;
    }
}
