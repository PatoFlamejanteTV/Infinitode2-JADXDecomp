package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.IntBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/DefaultTextureBinder.class */
public final class DefaultTextureBinder implements TextureBinder {
    public static final int ROUNDROBIN = 0;
    public static final int LRU = 1;
    public static final int MAX_GLES_UNITS = 32;
    private final int offset;
    private final int count;
    private final GLTexture[] textures;
    private int[] unitsLRU;
    private final int method;
    private boolean reused;
    private int reuseCount;
    private int bindCount;
    private final TextureDescriptor tempDesc;
    private int currentTexture;

    public DefaultTextureBinder(int i) {
        this(i, 0);
    }

    public DefaultTextureBinder(int i, int i2) {
        this(i, i2, -1);
    }

    public DefaultTextureBinder(int i, int i2, int i3) {
        this.reuseCount = 0;
        this.bindCount = 0;
        this.tempDesc = new TextureDescriptor();
        this.currentTexture = 0;
        int min = Math.min(getMaxTextureUnits(), 32);
        i3 = i3 < 0 ? min - i2 : i3;
        if (i2 < 0 || i3 < 0 || i2 + i3 > min) {
            throw new GdxRuntimeException("Illegal arguments");
        }
        this.method = i;
        this.offset = i2;
        this.count = i3;
        this.textures = new GLTexture[i3];
        this.unitsLRU = i == 1 ? new int[i3] : null;
    }

    private static int getMaxTextureUnits() {
        IntBuffer newIntBuffer = BufferUtils.newIntBuffer(16);
        Gdx.gl.glGetIntegerv(34930, newIntBuffer);
        return newIntBuffer.get(0);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final void begin() {
        for (int i = 0; i < this.count; i++) {
            this.textures[i] = null;
            if (this.unitsLRU != null) {
                int i2 = i;
                this.unitsLRU[i2] = i2;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final void end() {
        Gdx.gl.glActiveTexture(33984);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int bind(TextureDescriptor textureDescriptor) {
        return bindTexture(textureDescriptor, false);
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int bind(GLTexture gLTexture) {
        this.tempDesc.set(gLTexture, null, null, null, null);
        return bindTexture(this.tempDesc, false);
    }

    private final int bindTexture(TextureDescriptor textureDescriptor, boolean z) {
        int bindTextureLRU;
        GLTexture gLTexture = textureDescriptor.texture;
        this.reused = false;
        switch (this.method) {
            case 0:
                bindTextureLRU = this.offset + bindTextureRoundRobin(gLTexture);
                break;
            case 1:
                bindTextureLRU = this.offset + bindTextureLRU(gLTexture);
                break;
            default:
                return -1;
        }
        if (this.reused) {
            this.reuseCount++;
            if (z) {
                gLTexture.bind(bindTextureLRU);
            } else {
                Gdx.gl.glActiveTexture(bindTextureLRU + 33984);
            }
        } else {
            this.bindCount++;
        }
        gLTexture.unsafeSetWrap(textureDescriptor.uWrap, textureDescriptor.vWrap);
        gLTexture.unsafeSetFilter(textureDescriptor.minFilter, textureDescriptor.magFilter);
        return bindTextureLRU;
    }

    private final int bindTextureRoundRobin(GLTexture gLTexture) {
        for (int i = 0; i < this.count; i++) {
            int i2 = (this.currentTexture + i) % this.count;
            if (this.textures[i2] == gLTexture) {
                this.reused = true;
                return i2;
            }
        }
        this.currentTexture = (this.currentTexture + 1) % this.count;
        this.textures[this.currentTexture] = gLTexture;
        gLTexture.bind(this.offset + this.currentTexture);
        return this.currentTexture;
    }

    private final int bindTextureLRU(GLTexture gLTexture) {
        int i = 0;
        while (true) {
            if (i >= this.count) {
                break;
            }
            int i2 = this.unitsLRU[i];
            if (this.textures[i2] == gLTexture) {
                this.reused = true;
                break;
            }
            if (this.textures[i2] == null) {
                break;
            }
            i++;
        }
        if (i >= this.count) {
            i = this.count - 1;
        }
        int i3 = this.unitsLRU[i];
        while (i > 0) {
            int[] iArr = this.unitsLRU;
            iArr[i] = iArr[i - 1];
            i--;
        }
        this.unitsLRU[0] = i3;
        if (!this.reused) {
            this.textures[i3] = gLTexture;
            gLTexture.bind(this.offset + i3);
        }
        return i3;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int getBindCount() {
        return this.bindCount;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final int getReuseCount() {
        return this.reuseCount;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.TextureBinder
    public final void resetCounts() {
        this.reuseCount = 0;
        this.bindCount = 0;
    }
}
