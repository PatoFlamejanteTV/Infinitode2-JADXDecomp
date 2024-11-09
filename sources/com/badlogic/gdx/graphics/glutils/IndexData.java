package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.utils.Disposable;
import java.nio.ShortBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/IndexData.class */
public interface IndexData extends Disposable {
    int getNumIndices();

    int getNumMaxIndices();

    void setIndices(short[] sArr, int i, int i2);

    void setIndices(ShortBuffer shortBuffer);

    void updateIndices(int i, short[] sArr, int i2, int i3);

    @Deprecated
    ShortBuffer getBuffer();

    ShortBuffer getBuffer(boolean z);

    void bind();

    void unbind();

    void invalidate();

    @Override // com.badlogic.gdx.utils.Disposable
    void dispose();
}
