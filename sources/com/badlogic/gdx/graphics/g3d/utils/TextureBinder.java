package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/TextureBinder.class */
public interface TextureBinder {
    void begin();

    void end();

    int bind(TextureDescriptor textureDescriptor);

    int bind(GLTexture gLTexture);

    int getBindCount();

    int getReuseCount();

    void resetCounts();
}
