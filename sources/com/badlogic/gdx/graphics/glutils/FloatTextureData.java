package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.GLVersion;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.FloatBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/FloatTextureData.class */
public class FloatTextureData implements TextureData {
    int width;
    int height;
    int internalFormat;
    int format;
    int type;
    boolean isGpuOnly;
    boolean isPrepared = false;
    FloatBuffer buffer;

    public FloatTextureData(int i, int i2, int i3, int i4, int i5, boolean z) {
        this.width = 0;
        this.height = 0;
        this.width = i;
        this.height = i2;
        this.internalFormat = i3;
        this.format = i4;
        this.type = i5;
        this.isGpuOnly = z;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public TextureData.TextureDataType getType() {
        return TextureData.TextureDataType.Custom;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        if (this.isPrepared) {
            throw new GdxRuntimeException("Already prepared");
        }
        if (!this.isGpuOnly) {
            int i = 4;
            if (Gdx.graphics.getGLVersion().getType().equals(GLVersion.Type.OpenGL)) {
                if (this.internalFormat == 34842 || this.internalFormat == 34836) {
                    i = 4;
                }
                if (this.internalFormat == 34843 || this.internalFormat == 34837) {
                    i = 3;
                }
                if (this.internalFormat == 33327 || this.internalFormat == 33328) {
                    i = 2;
                }
                if (this.internalFormat == 33325 || this.internalFormat == 33326) {
                    i = 1;
                }
            }
            this.buffer = BufferUtils.newFloatBuffer(this.width * this.height * i);
        }
        this.isPrepared = true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i) {
        if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS || (Gdx.app.getType() == Application.ApplicationType.WebGL && !Gdx.graphics.isGL30Available())) {
            if (!Gdx.graphics.supportsExtension("OES_texture_float")) {
                throw new GdxRuntimeException("Extension OES_texture_float not supported!");
            }
            Gdx.gl.glTexImage2D(i, 0, 6408, this.width, this.height, 0, 6408, 5126, this.buffer);
        } else {
            if (!Gdx.graphics.isGL30Available() && !Gdx.graphics.supportsExtension("GL_ARB_texture_float")) {
                throw new GdxRuntimeException("Extension GL_ARB_texture_float not supported!");
            }
            Gdx.gl.glTexImage2D(i, 0, this.internalFormat, this.width, this.height, 0, this.format, 5126, this.buffer);
        }
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.width;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.height;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap.Format getFormat() {
        return Pixmap.Format.RGBA8888;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return false;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return true;
    }

    public FloatBuffer getBuffer() {
        return this.buffer;
    }
}
