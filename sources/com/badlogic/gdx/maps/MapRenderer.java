package com.badlogic.gdx.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/MapRenderer.class */
public interface MapRenderer {
    void setView(OrthographicCamera orthographicCamera);

    void setView(Matrix4 matrix4, float f, float f2, float f3, float f4);

    void render();

    void render(int[] iArr);
}
