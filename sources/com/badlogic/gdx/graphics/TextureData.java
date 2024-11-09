package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ETC1TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/TextureData.class */
public interface TextureData {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/TextureData$TextureDataType.class */
    public enum TextureDataType {
        Pixmap,
        Custom
    }

    TextureDataType getType();

    boolean isPrepared();

    void prepare();

    Pixmap consumePixmap();

    boolean disposePixmap();

    void consumeCustomData(int i);

    int getWidth();

    int getHeight();

    Pixmap.Format getFormat();

    boolean useMipMaps();

    boolean isManaged();

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/TextureData$Factory.class */
    public static class Factory {
        public static TextureData loadFromFile(FileHandle fileHandle, boolean z) {
            return loadFromFile(fileHandle, null, z);
        }

        public static TextureData loadFromFile(FileHandle fileHandle, Pixmap.Format format, boolean z) {
            if (fileHandle == null) {
                return null;
            }
            return fileHandle.name().endsWith(".cim") ? new FileTextureData(fileHandle, PixmapIO.readCIM(fileHandle), format, z) : fileHandle.name().endsWith(".etc1") ? new ETC1TextureData(fileHandle, z) : (fileHandle.name().endsWith(".ktx") || fileHandle.name().endsWith(".zktx")) ? new KTXTextureData(fileHandle, z) : new FileTextureData(fileHandle, new Pixmap(fileHandle), format, z);
        }
    }
}
