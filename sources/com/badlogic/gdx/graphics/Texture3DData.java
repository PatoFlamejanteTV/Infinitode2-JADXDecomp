package com.badlogic.gdx.graphics;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Texture3DData.class */
public interface Texture3DData {
    boolean isPrepared();

    void prepare();

    int getWidth();

    int getHeight();

    int getDepth();

    int getInternalFormat();

    int getGLType();

    boolean useMipMaps();

    void consume3DData();

    boolean isManaged();
}
