package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/HdpiUtils.class */
public class HdpiUtils {
    private static HdpiMode mode = HdpiMode.Logical;

    public static void setMode(HdpiMode hdpiMode) {
        mode = hdpiMode;
    }

    public static void glScissor(int i, int i2, int i3, int i4) {
        if (mode == HdpiMode.Logical && (Gdx.graphics.getWidth() != Gdx.graphics.getBackBufferWidth() || Gdx.graphics.getHeight() != Gdx.graphics.getBackBufferHeight())) {
            Gdx.gl.glScissor(toBackBufferX(i), toBackBufferY(i2), toBackBufferX(i3), toBackBufferY(i4));
        } else {
            Gdx.gl.glScissor(i, i2, i3, i4);
        }
    }

    public static void glViewport(int i, int i2, int i3, int i4) {
        if (mode == HdpiMode.Logical && (Gdx.graphics.getWidth() != Gdx.graphics.getBackBufferWidth() || Gdx.graphics.getHeight() != Gdx.graphics.getBackBufferHeight())) {
            Gdx.gl.glViewport(toBackBufferX(i), toBackBufferY(i2), toBackBufferX(i3), toBackBufferY(i4));
        } else {
            Gdx.gl.glViewport(i, i2, i3, i4);
        }
    }

    public static int toLogicalX(int i) {
        return (int) ((i * Gdx.graphics.getWidth()) / Gdx.graphics.getBackBufferWidth());
    }

    public static int toLogicalY(int i) {
        return (int) ((i * Gdx.graphics.getHeight()) / Gdx.graphics.getBackBufferHeight());
    }

    public static int toBackBufferX(int i) {
        return (int) ((i * Gdx.graphics.getBackBufferWidth()) / Gdx.graphics.getWidth());
    }

    public static int toBackBufferY(int i) {
        return (int) ((i * Gdx.graphics.getBackBufferHeight()) / Gdx.graphics.getHeight());
    }
}
