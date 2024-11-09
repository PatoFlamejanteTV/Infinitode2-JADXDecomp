package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.g2d.Batch;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/Drawable.class */
public interface Drawable {
    void draw(Batch batch, float f, float f2, float f3, float f4);

    float getLeftWidth();

    void setLeftWidth(float f);

    float getRightWidth();

    void setRightWidth(float f);

    float getTopHeight();

    void setTopHeight(float f);

    float getBottomHeight();

    void setBottomHeight(float f);

    float getMinWidth();

    void setMinWidth(float f);

    float getMinHeight();

    void setMinHeight(float f);
}
