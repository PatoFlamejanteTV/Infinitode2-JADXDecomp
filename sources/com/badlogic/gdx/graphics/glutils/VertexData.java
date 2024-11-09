package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.Disposable;
import java.nio.FloatBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/VertexData.class */
public interface VertexData extends Disposable {
    int getNumVertices();

    int getNumMaxVertices();

    VertexAttributes getAttributes();

    void setVertices(float[] fArr, int i, int i2);

    void updateVertices(int i, float[] fArr, int i2, int i3);

    @Deprecated
    FloatBuffer getBuffer();

    FloatBuffer getBuffer(boolean z);

    void bind(ShaderProgram shaderProgram);

    void bind(ShaderProgram shaderProgram, int[] iArr);

    void unbind(ShaderProgram shaderProgram);

    void unbind(ShaderProgram shaderProgram, int[] iArr);

    void invalidate();

    @Override // com.badlogic.gdx.utils.Disposable
    void dispose();
}
