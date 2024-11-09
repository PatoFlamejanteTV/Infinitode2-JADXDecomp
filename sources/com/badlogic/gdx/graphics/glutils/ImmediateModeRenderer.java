package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/ImmediateModeRenderer.class */
public interface ImmediateModeRenderer {
    void begin(Matrix4 matrix4, int i);

    void flush();

    void color(Color color);

    void color(float f, float f2, float f3, float f4);

    void color(float f);

    void texCoord(float f, float f2);

    void normal(float f, float f2, float f3);

    void vertex(float f, float f2, float f3);

    void end();

    int getNumVertices();

    int getMaxVertices();

    void dispose();
}
