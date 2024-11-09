package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture3DData;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/CustomTexture3DData.class */
public class CustomTexture3DData implements Texture3DData {
    private int width;
    private int height;
    private int depth;
    private int mipMapLevel;
    private int glFormat;
    private int glInternalFormat;
    private int glType;
    private ByteBuffer pixels;

    public CustomTexture3DData(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.width = i;
        this.height = i2;
        this.depth = i3;
        this.glFormat = i5;
        this.glInternalFormat = i6;
        this.glType = i7;
        this.mipMapLevel = i4;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public boolean isPrepared() {
        return true;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public void prepare() {
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public int getWidth() {
        return this.width;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public int getHeight() {
        return this.height;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public int getDepth() {
        return this.depth;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public boolean useMipMaps() {
        return false;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public boolean isManaged() {
        return this.pixels != null;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public int getInternalFormat() {
        return this.glInternalFormat;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public int getGLType() {
        return this.glType;
    }

    public int getGLFormat() {
        return this.glFormat;
    }

    public int getMipMapLevel() {
        return this.mipMapLevel;
    }

    public ByteBuffer getPixels() {
        int i;
        int i2;
        if (this.pixels == null) {
            if (this.glFormat == 6403 || this.glFormat == 36244 || this.glFormat == 6409 || this.glFormat == 6406) {
                i = 1;
            } else if (this.glFormat == 33319 || this.glFormat == 33320 || this.glFormat == 6410) {
                i = 2;
            } else if (this.glFormat == 6407 || this.glFormat == 36248) {
                i = 3;
            } else if (this.glFormat == 6408 || this.glFormat == 36249) {
                i = 4;
            } else {
                throw new GdxRuntimeException("unsupported glFormat: " + this.glFormat);
            }
            if (this.glType == 5121 || this.glType == 5120) {
                i2 = 1;
            } else if (this.glType == 5123 || this.glType == 5122 || this.glType == 5131) {
                i2 = 2;
            } else if (this.glType == 5125 || this.glType == 5124 || this.glType == 5126) {
                i2 = 4;
            } else {
                throw new GdxRuntimeException("unsupported glType: " + this.glType);
            }
            this.pixels = BufferUtils.newByteBuffer(this.width * this.height * this.depth * i * i2);
        }
        return this.pixels;
    }

    @Override // com.badlogic.gdx.graphics.Texture3DData
    public void consume3DData() {
        Gdx.gl30.glTexImage3D(32879, this.mipMapLevel, this.glInternalFormat, this.width, this.height, this.depth, 0, this.glFormat, this.glType, this.pixels);
    }
}
