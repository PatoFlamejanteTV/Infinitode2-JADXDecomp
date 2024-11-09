package com.badlogic.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ScreenUtils.class */
public final class ScreenUtils {
    public static void clear(Color color) {
        clear(color.r, color.g, color.f888b, color.f889a, false);
    }

    public static void clear(float f, float f2, float f3, float f4) {
        clear(f, f2, f3, f4, false);
    }

    public static void clear(Color color, boolean z) {
        clear(color.r, color.g, color.f888b, color.f889a, z);
    }

    public static void clear(float f, float f2, float f3, float f4, boolean z) {
        clear(f, f2, f3, f4, z, false);
    }

    public static void clear(float f, float f2, float f3, float f4, boolean z, boolean z2) {
        Gdx.gl.glClearColor(f, f2, f3, f4);
        int i = 16384;
        if (z) {
            i = 16640;
        }
        if (z2 && Gdx.graphics.getBufferFormat().coverageSampling) {
            i |= 32768;
        }
        Gdx.gl.glClear(i);
    }

    public static TextureRegion getFrameBufferTexture() {
        return getFrameBufferTexture(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    }

    public static TextureRegion getFrameBufferTexture(int i, int i2, int i3, int i4) {
        int nextPowerOfTwo = MathUtils.nextPowerOfTwo(i3);
        int nextPowerOfTwo2 = MathUtils.nextPowerOfTwo(i4);
        Pixmap createFromFrameBuffer = Pixmap.createFromFrameBuffer(i, i2, i3, i4);
        Pixmap pixmap = new Pixmap(nextPowerOfTwo, nextPowerOfTwo2, Pixmap.Format.RGBA8888);
        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.drawPixmap(createFromFrameBuffer, 0, 0);
        TextureRegion textureRegion = new TextureRegion(new Texture(pixmap), 0, i4, i3, -i4);
        pixmap.dispose();
        createFromFrameBuffer.dispose();
        return textureRegion;
    }

    @Deprecated
    public static Pixmap getFrameBufferPixmap(int i, int i2, int i3, int i4) {
        return Pixmap.createFromFrameBuffer(i, i2, i3, i4);
    }

    public static byte[] getFrameBufferPixels(boolean z) {
        return getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), z);
    }

    public static byte[] getFrameBufferPixels(int i, int i2, int i3, int i4, boolean z) {
        Gdx.gl.glPixelStorei(3333, 1);
        ByteBuffer newByteBuffer = BufferUtils.newByteBuffer((i3 * i4) << 2);
        Gdx.gl.glReadPixels(i, i2, i3, i4, 6408, 5121, newByteBuffer);
        byte[] bArr = new byte[(i3 * i4) << 2];
        if (z) {
            int i5 = i3 << 2;
            for (int i6 = 0; i6 < i4; i6++) {
                newByteBuffer.position(((i4 - i6) - 1) * i5);
                newByteBuffer.get(bArr, i6 * i5, i5);
            }
        } else {
            newByteBuffer.clear();
            newByteBuffer.get(bArr);
        }
        return bArr;
    }
}
