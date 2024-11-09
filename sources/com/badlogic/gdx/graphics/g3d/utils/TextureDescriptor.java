package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/TextureDescriptor.class */
public class TextureDescriptor<T extends GLTexture> implements Comparable<TextureDescriptor<T>> {
    public T texture;
    public Texture.TextureFilter minFilter;
    public Texture.TextureFilter magFilter;
    public Texture.TextureWrap uWrap;
    public Texture.TextureWrap vWrap;

    public TextureDescriptor(T t, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        this.texture = null;
        set(t, textureFilter, textureFilter2, textureWrap, textureWrap2);
    }

    public TextureDescriptor(T t) {
        this(t, null, null, null, null);
    }

    public TextureDescriptor() {
        this.texture = null;
    }

    public void set(T t, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        this.texture = t;
        this.minFilter = textureFilter;
        this.magFilter = textureFilter2;
        this.uWrap = textureWrap;
        this.vWrap = textureWrap2;
    }

    public <V extends T> void set(TextureDescriptor<V> textureDescriptor) {
        this.texture = textureDescriptor.texture;
        this.minFilter = textureDescriptor.minFilter;
        this.magFilter = textureDescriptor.magFilter;
        this.uWrap = textureDescriptor.uWrap;
        this.vWrap = textureDescriptor.vWrap;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextureDescriptor)) {
            return false;
        }
        TextureDescriptor textureDescriptor = (TextureDescriptor) obj;
        return textureDescriptor.texture == this.texture && textureDescriptor.minFilter == this.minFilter && textureDescriptor.magFilter == this.magFilter && textureDescriptor.uWrap == this.uWrap && textureDescriptor.vWrap == this.vWrap;
    }

    public int hashCode() {
        long textureObjectHandle = (811 * ((811 * ((811 * ((811 * ((811 * (this.texture == null ? 0 : this.texture.glTarget)) + (this.texture == null ? 0 : this.texture.getTextureObjectHandle()))) + (this.minFilter == null ? 0 : this.minFilter.getGLEnum()))) + (this.magFilter == null ? 0 : this.magFilter.getGLEnum()))) + (this.uWrap == null ? 0 : this.uWrap.getGLEnum()))) + (this.vWrap == null ? 0 : this.vWrap.getGLEnum());
        return (int) (textureObjectHandle ^ (textureObjectHandle >> 32));
    }

    @Override // java.lang.Comparable
    public int compareTo(TextureDescriptor<T> textureDescriptor) {
        if (textureDescriptor == this) {
            return 0;
        }
        int i = this.texture == null ? 0 : this.texture.glTarget;
        int i2 = textureDescriptor.texture == null ? 0 : textureDescriptor.texture.glTarget;
        if (i != i2) {
            return i - i2;
        }
        int textureObjectHandle = this.texture == null ? 0 : this.texture.getTextureObjectHandle();
        int textureObjectHandle2 = textureDescriptor.texture == null ? 0 : textureDescriptor.texture.getTextureObjectHandle();
        if (textureObjectHandle != textureObjectHandle2) {
            return textureObjectHandle - textureObjectHandle2;
        }
        if (this.minFilter != textureDescriptor.minFilter) {
            return (this.minFilter == null ? 0 : this.minFilter.getGLEnum()) - (textureDescriptor.minFilter == null ? 0 : textureDescriptor.minFilter.getGLEnum());
        }
        if (this.magFilter != textureDescriptor.magFilter) {
            return (this.magFilter == null ? 0 : this.magFilter.getGLEnum()) - (textureDescriptor.magFilter == null ? 0 : textureDescriptor.magFilter.getGLEnum());
        }
        if (this.uWrap != textureDescriptor.uWrap) {
            return (this.uWrap == null ? 0 : this.uWrap.getGLEnum()) - (textureDescriptor.uWrap == null ? 0 : textureDescriptor.uWrap.getGLEnum());
        }
        if (this.vWrap != textureDescriptor.vWrap) {
            return (this.vWrap == null ? 0 : this.vWrap.getGLEnum()) - (textureDescriptor.vWrap == null ? 0 : textureDescriptor.vWrap.getGLEnum());
        }
        return 0;
    }
}
