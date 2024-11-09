package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.Buffer;
import java.nio.IntBuffer;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuGdxGl.class */
public class BasisuGdxGl {
    public static int[] getSupportedTextureFormats() {
        IntBuffer newIntBuffer = BufferUtils.newIntBuffer(64);
        Gdx.gl.glGetIntegerv(34466, newIntBuffer);
        int i = newIntBuffer.get(0);
        if (newIntBuffer.capacity() < i) {
            newIntBuffer = BufferUtils.newIntBuffer(i);
        }
        int[] iArr = new int[i];
        Gdx.gl.glGetIntegerv(34467, newIntBuffer);
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = newIntBuffer.get(i2);
        }
        return iArr;
    }

    public static void glCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        Gdx.gl.glCompressedTexImage2D(i, i2, i3, i4, i5, i6, i7, buffer);
    }
}
