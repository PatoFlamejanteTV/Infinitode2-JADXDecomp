package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FileTextureArrayData;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/TextureArrayData.class */
public interface TextureArrayData {
    boolean isPrepared();

    void prepare();

    void consumeTextureArrayData();

    int getWidth();

    int getHeight();

    int getDepth();

    boolean isManaged();

    int getInternalFormat();

    int getGLType();

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/TextureArrayData$Factory.class */
    public static class Factory {
        public static TextureArrayData loadFromFiles(Pixmap.Format format, boolean z, FileHandle... fileHandleArr) {
            return new FileTextureArrayData(format, z, fileHandleArr);
        }
    }
}
