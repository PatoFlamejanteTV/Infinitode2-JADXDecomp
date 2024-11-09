package com.badlogic.gdx.graphics;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/CubemapData.class */
public interface CubemapData {
    boolean isPrepared();

    void prepare();

    void consumeCubemapData();

    int getWidth();

    int getHeight();

    boolean isManaged();
}
