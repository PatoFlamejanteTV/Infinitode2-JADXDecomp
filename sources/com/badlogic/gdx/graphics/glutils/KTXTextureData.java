package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.ETC1;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.zip.GZIPInputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/KTXTextureData.class */
public class KTXTextureData implements CubemapData, TextureData {
    private FileHandle file;
    private int glType;
    private int glTypeSize;
    private int glFormat;
    private int glInternalFormat;
    private int glBaseInternalFormat;
    private int pixelWidth = -1;
    private int pixelHeight = -1;
    private int pixelDepth = -1;
    private int numberOfArrayElements;
    private int numberOfFaces;
    private int numberOfMipmapLevels;
    private int imagePos;
    private ByteBuffer compressedData;
    private boolean useMipMaps;
    private static final int GL_TEXTURE_1D = 4660;
    private static final int GL_TEXTURE_3D = 4660;
    private static final int GL_TEXTURE_1D_ARRAY_EXT = 4660;
    private static final int GL_TEXTURE_2D_ARRAY_EXT = 4660;

    public KTXTextureData(FileHandle fileHandle, boolean z) {
        this.file = fileHandle;
        this.useMipMaps = z;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public TextureData.TextureDataType getType() {
        return TextureData.TextureDataType.Custom;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public boolean isPrepared() {
        return this.compressedData != null;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public void prepare() {
        if (this.compressedData != null) {
            throw new GdxRuntimeException("Already prepared");
        }
        if (this.file == null) {
            throw new GdxRuntimeException("Need a file to load from");
        }
        if (this.file.name().endsWith(".zktx")) {
            byte[] bArr = new byte[10240];
            DataInputStream dataInputStream = null;
            try {
                try {
                    DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(this.file.read())));
                    dataInputStream = dataInputStream2;
                    this.compressedData = BufferUtils.newUnsafeByteBuffer(dataInputStream2.readInt());
                    while (true) {
                        int read = dataInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        } else {
                            this.compressedData.put(bArr, 0, read);
                        }
                    }
                    this.compressedData.position(0);
                    this.compressedData.limit(this.compressedData.capacity());
                    StreamUtils.closeQuietly(dataInputStream);
                } catch (Exception e) {
                    throw new GdxRuntimeException("Couldn't load zktx file '" + this.file + "'", e);
                }
            } catch (Throwable th) {
                StreamUtils.closeQuietly(dataInputStream);
                throw th;
            }
        } else {
            this.compressedData = ByteBuffer.wrap(this.file.readBytes());
        }
        if (this.compressedData.get() != -85) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 75) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 84) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 88) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 32) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 49) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 49) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != -69) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 13) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 10) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 26) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (this.compressedData.get() != 10) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        int i = this.compressedData.getInt();
        if (i != 67305985 && i != 16909060) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if (i != 67305985) {
            this.compressedData.order(this.compressedData.order() == ByteOrder.BIG_ENDIAN ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        }
        this.glType = this.compressedData.getInt();
        this.glTypeSize = this.compressedData.getInt();
        this.glFormat = this.compressedData.getInt();
        this.glInternalFormat = this.compressedData.getInt();
        this.glBaseInternalFormat = this.compressedData.getInt();
        this.pixelWidth = this.compressedData.getInt();
        this.pixelHeight = this.compressedData.getInt();
        this.pixelDepth = this.compressedData.getInt();
        this.numberOfArrayElements = this.compressedData.getInt();
        this.numberOfFaces = this.compressedData.getInt();
        this.numberOfMipmapLevels = this.compressedData.getInt();
        if (this.numberOfMipmapLevels == 0) {
            this.numberOfMipmapLevels = 1;
            this.useMipMaps = true;
        }
        this.imagePos = this.compressedData.position() + this.compressedData.getInt();
        if (!this.compressedData.isDirect()) {
            int i2 = this.imagePos;
            for (int i3 = 0; i3 < this.numberOfMipmapLevels; i3++) {
                i2 += (((this.compressedData.getInt(i2) + 3) & (-4)) * this.numberOfFaces) + 4;
            }
            this.compressedData.limit(i2);
            this.compressedData.position(0);
            ByteBuffer newUnsafeByteBuffer = BufferUtils.newUnsafeByteBuffer(i2);
            newUnsafeByteBuffer.order(this.compressedData.order());
            newUnsafeByteBuffer.put(this.compressedData);
            this.compressedData = newUnsafeByteBuffer;
        }
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public void consumeCubemapData() {
        consumeCustomData(34067);
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i) {
        if (this.compressedData == null) {
            throw new GdxRuntimeException("Call prepare() before calling consumeCompressedData()");
        }
        IntBuffer newIntBuffer = BufferUtils.newIntBuffer(16);
        boolean z = false;
        if (this.glType == 0 || this.glFormat == 0) {
            if (this.glType + this.glFormat != 0) {
                throw new GdxRuntimeException("either both or none of glType, glFormat must be zero");
            }
            z = true;
        }
        int i2 = 1;
        int i3 = 4660;
        if (this.pixelHeight > 0) {
            i2 = 2;
            i3 = 3553;
        }
        if (this.pixelDepth > 0) {
            i2 = 3;
            i3 = 4660;
        }
        if (this.numberOfFaces == 6) {
            if (i2 == 2) {
                i3 = 34067;
            } else {
                throw new GdxRuntimeException("cube map needs 2D faces");
            }
        } else if (this.numberOfFaces != 1) {
            throw new GdxRuntimeException("numberOfFaces must be either 1 or 6");
        }
        if (this.numberOfArrayElements > 0) {
            if (i3 == 4660) {
                i3 = 4660;
            } else if (i3 == 3553) {
                i3 = 4660;
            } else {
                throw new GdxRuntimeException("No API for 3D and cube arrays yet");
            }
            i2++;
        }
        if (i3 == 4660) {
            throw new GdxRuntimeException("Unsupported texture format (only 2D texture are supported in LibGdx for the time being)");
        }
        int i4 = -1;
        if (this.numberOfFaces == 6 && i != 34067) {
            if (34069 > i || i > 34074) {
                throw new GdxRuntimeException("You must specify either GL_TEXTURE_CUBE_MAP to bind all 6 faces of the cube or the requested face GL_TEXTURE_CUBE_MAP_POSITIVE_X and followings.");
            }
            i4 = i - 34069;
            i = 34069;
        } else if (this.numberOfFaces == 6 && i == 34067) {
            i = 34069;
        } else if (i != i3 && (34069 > i || i > 34074 || i != 3553)) {
            throw new GdxRuntimeException("Invalid target requested : 0x" + Integer.toHexString(i) + ", expecting : 0x" + Integer.toHexString(i3));
        }
        Gdx.gl.glGetIntegerv(3317, newIntBuffer);
        int i5 = newIntBuffer.get(0);
        if (i5 != 4) {
            Gdx.gl.glPixelStorei(3317, 4);
        }
        int i6 = this.glInternalFormat;
        int i7 = this.glFormat;
        int i8 = this.imagePos;
        for (int i9 = 0; i9 < this.numberOfMipmapLevels; i9++) {
            int max = Math.max(1, this.pixelWidth >> i9);
            int max2 = Math.max(1, this.pixelHeight >> i9);
            Math.max(1, this.pixelDepth >> i9);
            this.compressedData.position(i8);
            int i10 = this.compressedData.getInt();
            int i11 = (i10 + 3) & (-4);
            i8 += 4;
            for (int i12 = 0; i12 < this.numberOfFaces; i12++) {
                this.compressedData.position(i8);
                i8 += i11;
                if (i4 == -1 || i4 == i12) {
                    ByteBuffer slice = this.compressedData.slice();
                    slice.limit(i11);
                    if (i2 != 1 && i2 == 2) {
                        if (this.numberOfArrayElements > 0) {
                            max2 = this.numberOfArrayElements;
                        }
                        if (!z) {
                            Gdx.gl.glTexImage2D(i + i12, i9, i6, max, max2, 0, i7, this.glType, slice);
                        } else if (i6 != ETC1.ETC1_RGB8_OES) {
                            Gdx.gl.glCompressedTexImage2D(i + i12, i9, i6, max, max2, 0, i10, slice);
                        } else if (Gdx.graphics.supportsExtension("GL_OES_compressed_ETC1_RGB8_texture")) {
                            Gdx.gl.glCompressedTexImage2D(i + i12, i9, i6, max, max2, 0, i10, slice);
                        } else {
                            Pixmap decodeImage = ETC1.decodeImage(new ETC1.ETC1Data(max, max2, slice, 0), Pixmap.Format.RGB888);
                            Gdx.gl.glTexImage2D(i + i12, i9, decodeImage.getGLInternalFormat(), decodeImage.getWidth(), decodeImage.getHeight(), 0, decodeImage.getGLFormat(), decodeImage.getGLType(), decodeImage.getPixels());
                            decodeImage.dispose();
                        }
                    }
                }
            }
        }
        if (i5 != 4) {
            Gdx.gl.glPixelStorei(3317, i5);
        }
        if (useMipMaps()) {
            Gdx.gl.glGenerateMipmap(i);
        }
        disposePreparedData();
    }

    public void disposePreparedData() {
        if (this.compressedData != null) {
            BufferUtils.disposeUnsafeByteBuffer(this.compressedData);
        }
        this.compressedData = null;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public int getWidth() {
        return this.pixelWidth;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public int getHeight() {
        return this.pixelHeight;
    }

    public int getNumberOfMipMapLevels() {
        return this.numberOfMipmapLevels;
    }

    public int getNumberOfFaces() {
        return this.numberOfFaces;
    }

    public int getGlInternalFormat() {
        return this.glInternalFormat;
    }

    public ByteBuffer getData(int i, int i2) {
        int i3 = this.imagePos;
        for (int i4 = 0; i4 < this.numberOfMipmapLevels; i4++) {
            int i5 = (this.compressedData.getInt(i3) + 3) & (-4);
            i3 += 4;
            if (i4 == i) {
                for (int i6 = 0; i6 < this.numberOfFaces; i6++) {
                    if (i6 == i2) {
                        this.compressedData.position(i3);
                        ByteBuffer slice = this.compressedData.slice();
                        slice.limit(i5);
                        return slice;
                    }
                    i3 += i5;
                }
            } else {
                i3 += i5 * this.numberOfFaces;
            }
        }
        return null;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap.Format getFormat() {
        throw new GdxRuntimeException("This TextureData implementation directly handles texture formats.");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public boolean isManaged() {
        return true;
    }
}
