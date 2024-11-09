package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.MipMapGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import java.nio.FloatBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/GLTexture.class */
public abstract class GLTexture implements Disposable {
    public final int glTarget;
    protected int glHandle;
    protected Texture.TextureFilter minFilter;
    protected Texture.TextureFilter magFilter;
    protected Texture.TextureWrap uWrap;
    protected Texture.TextureWrap vWrap;
    protected float anisotropicFilterLevel;
    private static float maxAnisotropicFilterLevel = 0.0f;

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getDepth();

    public abstract boolean isManaged();

    protected abstract void reload();

    public GLTexture(int i) {
        this(i, Gdx.gl.glGenTexture());
    }

    public GLTexture(int i, int i2) {
        this.minFilter = Texture.TextureFilter.Nearest;
        this.magFilter = Texture.TextureFilter.Nearest;
        this.uWrap = Texture.TextureWrap.ClampToEdge;
        this.vWrap = Texture.TextureWrap.ClampToEdge;
        this.anisotropicFilterLevel = 1.0f;
        this.glTarget = i;
        this.glHandle = i2;
    }

    public void bind() {
        Gdx.gl.glBindTexture(this.glTarget, this.glHandle);
    }

    public void bind(int i) {
        Gdx.gl.glActiveTexture(i + 33984);
        Gdx.gl.glBindTexture(this.glTarget, this.glHandle);
    }

    public Texture.TextureFilter getMinFilter() {
        return this.minFilter;
    }

    public Texture.TextureFilter getMagFilter() {
        return this.magFilter;
    }

    public Texture.TextureWrap getUWrap() {
        return this.uWrap;
    }

    public Texture.TextureWrap getVWrap() {
        return this.vWrap;
    }

    public int getTextureObjectHandle() {
        return this.glHandle;
    }

    public void unsafeSetWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        unsafeSetWrap(textureWrap, textureWrap2, false);
    }

    public void unsafeSetWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2, boolean z) {
        if (textureWrap != null && (z || this.uWrap != textureWrap)) {
            Gdx.gl.glTexParameteri(this.glTarget, 10242, textureWrap.getGLEnum());
            this.uWrap = textureWrap;
        }
        if (textureWrap2 != null) {
            if (z || this.vWrap != textureWrap2) {
                Gdx.gl.glTexParameteri(this.glTarget, 10243, textureWrap2.getGLEnum());
                this.vWrap = textureWrap2;
            }
        }
    }

    public void setWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        this.uWrap = textureWrap;
        this.vWrap = textureWrap2;
        bind();
        Gdx.gl.glTexParameteri(this.glTarget, 10242, textureWrap.getGLEnum());
        Gdx.gl.glTexParameteri(this.glTarget, 10243, textureWrap2.getGLEnum());
    }

    public void unsafeSetFilter(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2) {
        unsafeSetFilter(textureFilter, textureFilter2, false);
    }

    public void unsafeSetFilter(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z) {
        if (textureFilter != null && (z || this.minFilter != textureFilter)) {
            Gdx.gl.glTexParameteri(this.glTarget, 10241, textureFilter.getGLEnum());
            this.minFilter = textureFilter;
        }
        if (textureFilter2 != null) {
            if (z || this.magFilter != textureFilter2) {
                Gdx.gl.glTexParameteri(this.glTarget, 10240, textureFilter2.getGLEnum());
                this.magFilter = textureFilter2;
            }
        }
    }

    public void setFilter(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2) {
        this.minFilter = textureFilter;
        this.magFilter = textureFilter2;
        bind();
        Gdx.gl.glTexParameteri(this.glTarget, 10241, textureFilter.getGLEnum());
        Gdx.gl.glTexParameteri(this.glTarget, 10240, textureFilter2.getGLEnum());
    }

    public float unsafeSetAnisotropicFilter(float f) {
        return unsafeSetAnisotropicFilter(f, false);
    }

    public float unsafeSetAnisotropicFilter(float f, boolean z) {
        float maxAnisotropicFilterLevel2 = getMaxAnisotropicFilterLevel();
        if (maxAnisotropicFilterLevel2 == 1.0f) {
            return 1.0f;
        }
        float min = Math.min(f, maxAnisotropicFilterLevel2);
        if (!z && MathUtils.isEqual(min, this.anisotropicFilterLevel, 0.1f)) {
            return this.anisotropicFilterLevel;
        }
        Gdx.gl20.glTexParameterf(3553, 34046, min);
        this.anisotropicFilterLevel = min;
        return min;
    }

    public float setAnisotropicFilter(float f) {
        float maxAnisotropicFilterLevel2 = getMaxAnisotropicFilterLevel();
        if (maxAnisotropicFilterLevel2 == 1.0f) {
            return 1.0f;
        }
        float min = Math.min(f, maxAnisotropicFilterLevel2);
        if (MathUtils.isEqual(min, this.anisotropicFilterLevel, 0.1f)) {
            return min;
        }
        bind();
        Gdx.gl20.glTexParameterf(3553, 34046, min);
        this.anisotropicFilterLevel = min;
        return min;
    }

    public float getAnisotropicFilter() {
        return this.anisotropicFilterLevel;
    }

    public static float getMaxAnisotropicFilterLevel() {
        if (maxAnisotropicFilterLevel > 0.0f) {
            return maxAnisotropicFilterLevel;
        }
        if (Gdx.graphics.supportsExtension("GL_EXT_texture_filter_anisotropic")) {
            FloatBuffer newFloatBuffer = BufferUtils.newFloatBuffer(16);
            newFloatBuffer.position(0);
            newFloatBuffer.limit(newFloatBuffer.capacity());
            Gdx.gl20.glGetFloatv(34047, newFloatBuffer);
            float f = newFloatBuffer.get(0);
            maxAnisotropicFilterLevel = f;
            return f;
        }
        maxAnisotropicFilterLevel = 1.0f;
        return 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void delete() {
        if (this.glHandle != 0) {
            Gdx.gl.glDeleteTexture(this.glHandle);
            this.glHandle = 0;
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        delete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void uploadImageData(int i, TextureData textureData) {
        uploadImageData(i, textureData, 0);
    }

    public static void uploadImageData(int i, TextureData textureData, int i2) {
        if (textureData == null) {
            return;
        }
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        if (textureData.getType() == TextureData.TextureDataType.Custom) {
            textureData.consumeCustomData(i);
            return;
        }
        Pixmap consumePixmap = textureData.consumePixmap();
        boolean disposePixmap = textureData.disposePixmap();
        if (textureData.getFormat() != consumePixmap.getFormat()) {
            Pixmap pixmap = new Pixmap(consumePixmap.getWidth(), consumePixmap.getHeight(), textureData.getFormat());
            pixmap.setBlending(Pixmap.Blending.None);
            pixmap.drawPixmap(consumePixmap, 0, 0, 0, 0, consumePixmap.getWidth(), consumePixmap.getHeight());
            if (textureData.disposePixmap()) {
                consumePixmap.dispose();
            }
            consumePixmap = pixmap;
            disposePixmap = true;
        }
        Gdx.gl.glPixelStorei(3317, 1);
        if (textureData.useMipMaps()) {
            Pixmap pixmap2 = consumePixmap;
            MipMapGenerator.generateMipMap(i, pixmap2, pixmap2.getWidth(), consumePixmap.getHeight());
        } else {
            Gdx.gl.glTexImage2D(i, i2, consumePixmap.getGLInternalFormat(), consumePixmap.getWidth(), consumePixmap.getHeight(), 0, consumePixmap.getGLFormat(), consumePixmap.getGLType(), consumePixmap.getPixels());
        }
        if (disposePixmap) {
            consumePixmap.dispose();
        }
    }
}
