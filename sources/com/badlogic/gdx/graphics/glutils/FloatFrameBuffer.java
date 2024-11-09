package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/FloatFrameBuffer.class */
public class FloatFrameBuffer extends FrameBuffer {
    FloatFrameBuffer() {
        checkExtensions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FloatFrameBuffer(GLFrameBuffer.GLFrameBufferBuilder<? extends GLFrameBuffer<Texture>> gLFrameBufferBuilder) {
        super(gLFrameBufferBuilder);
        checkExtensions();
    }

    public FloatFrameBuffer(int i, int i2, boolean z) {
        checkExtensions();
        GLFrameBuffer.FloatFrameBufferBuilder floatFrameBufferBuilder = new GLFrameBuffer.FloatFrameBufferBuilder(i, i2);
        floatFrameBufferBuilder.addFloatAttachment(34836, 6408, 5126, false);
        if (z) {
            floatFrameBufferBuilder.addBasicDepthRenderBuffer();
        }
        this.bufferBuilder = floatFrameBufferBuilder;
        build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.graphics.glutils.FrameBuffer, com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public Texture createTexture(GLFrameBuffer.FrameBufferTextureAttachmentSpec frameBufferTextureAttachmentSpec) {
        Texture texture = new Texture(new FloatTextureData(this.bufferBuilder.width, this.bufferBuilder.height, frameBufferTextureAttachmentSpec.internalFormat, frameBufferTextureAttachmentSpec.format, frameBufferTextureAttachmentSpec.type, frameBufferTextureAttachmentSpec.isGpuOnly));
        if (Gdx.app.getType() == Application.ApplicationType.Desktop || Gdx.app.getType() == Application.ApplicationType.Applet) {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
        } else {
            Texture.TextureFilter textureFilter2 = Texture.TextureFilter.Nearest;
            texture.setFilter(textureFilter2, textureFilter2);
        }
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        texture.setWrap(textureWrap, textureWrap);
        return texture;
    }

    private void checkExtensions() {
        if (Gdx.graphics.isGL30Available() && Gdx.app.getType() == Application.ApplicationType.WebGL && !Gdx.graphics.supportsExtension("EXT_color_buffer_float")) {
            throw new GdxRuntimeException("Extension EXT_color_buffer_float not supported!");
        }
    }
}
