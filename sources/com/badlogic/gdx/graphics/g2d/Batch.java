package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/Batch.class */
public interface Batch extends Disposable {
    public static final int X1 = 0;
    public static final int Y1 = 1;
    public static final int C1 = 2;
    public static final int U1 = 3;
    public static final int V1 = 4;
    public static final int X2 = 5;
    public static final int Y2 = 6;
    public static final int C2 = 7;
    public static final int U2 = 8;
    public static final int V2 = 9;
    public static final int X3 = 10;
    public static final int Y3 = 11;
    public static final int C3 = 12;
    public static final int U3 = 13;
    public static final int V3 = 14;
    public static final int X4 = 15;
    public static final int Y4 = 16;
    public static final int C4 = 17;
    public static final int U4 = 18;
    public static final int V4 = 19;

    void begin();

    void end();

    void setColor(Color color);

    void setColor(float f, float f2, float f3, float f4);

    Color getColor();

    void setPackedColor(float f);

    float getPackedColor();

    void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, int i2, int i3, int i4, boolean z, boolean z2);

    void draw(Texture texture, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, boolean z, boolean z2);

    void draw(Texture texture, float f, float f2, int i, int i2, int i3, int i4);

    void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8);

    void draw(Texture texture, float f, float f2);

    void draw(Texture texture, float f, float f2, float f3, float f4);

    void draw(Texture texture, float[] fArr, int i, int i2);

    void draw(TextureRegion textureRegion, float f, float f2);

    void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4);

    void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9);

    void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z);

    void draw(TextureRegion textureRegion, float f, float f2, Affine2 affine2);

    void flush();

    void disableBlending();

    void enableBlending();

    void setBlendFunction(int i, int i2);

    void setBlendFunctionSeparate(int i, int i2, int i3, int i4);

    int getBlendSrcFunc();

    int getBlendDstFunc();

    int getBlendSrcFuncAlpha();

    int getBlendDstFuncAlpha();

    Matrix4 getProjectionMatrix();

    Matrix4 getTransformMatrix();

    void setProjectionMatrix(Matrix4 matrix4);

    void setTransformMatrix(Matrix4 matrix4);

    void setShader(ShaderProgram shaderProgram);

    ShaderProgram getShader();

    boolean isBlendingEnabled();

    boolean isDrawing();
}
