package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.IOException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PolygonRegionLoader.class */
public class PolygonRegionLoader extends SynchronousAssetLoader<PolygonRegion, PolygonRegionParameters> {
    private PolygonRegionParameters defaultParameters;
    private EarClippingTriangulator triangulator;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PolygonRegionLoader$PolygonRegionParameters.class */
    public static class PolygonRegionParameters extends AssetLoaderParameters<PolygonRegion> {
        public String texturePrefix = "i ";
        public int readerBuffer = 1024;
        public String[] textureExtensions = {"png", "PNG", "jpeg", "JPEG", "jpg", "JPG", "cim", "CIM", "etc1", "ETC1", "ktx", "KTX", "zktx", "ZKTX"};
    }

    public PolygonRegionLoader() {
        this(new InternalFileHandleResolver());
    }

    public PolygonRegionLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.defaultParameters = new PolygonRegionParameters();
        this.triangulator = new EarClippingTriangulator();
    }

    @Override // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public PolygonRegion load(AssetManager assetManager, String str, FileHandle fileHandle, PolygonRegionParameters polygonRegionParameters) {
        return load(new TextureRegion((Texture) assetManager.get(assetManager.getDependencies(str).first())), fileHandle);
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, PolygonRegionParameters polygonRegionParameters) {
        if (polygonRegionParameters == null) {
            polygonRegionParameters = this.defaultParameters;
        }
        String str2 = null;
        try {
            BufferedReader reader = fileHandle.reader(polygonRegionParameters.readerBuffer);
            String readLine = reader.readLine();
            while (true) {
                if (readLine == null) {
                    break;
                }
                if (!readLine.startsWith(polygonRegionParameters.texturePrefix)) {
                    readLine = reader.readLine();
                } else {
                    str2 = readLine.substring(polygonRegionParameters.texturePrefix.length());
                    break;
                }
            }
            reader.close();
            if (str2 == null && polygonRegionParameters.textureExtensions != null) {
                for (String str3 : polygonRegionParameters.textureExtensions) {
                    FileHandle sibling = fileHandle.sibling(fileHandle.nameWithoutExtension().concat("." + str3));
                    if (sibling.exists()) {
                        str2 = sibling.name();
                    }
                }
            }
            if (str2 != null) {
                Array<AssetDescriptor> array = new Array<>(1);
                array.add(new AssetDescriptor(fileHandle.sibling(str2), Texture.class));
                return array;
            }
            return null;
        } catch (IOException e) {
            throw new GdxRuntimeException("Error reading " + str, e);
        }
    }

    public PolygonRegion load(TextureRegion textureRegion, FileHandle fileHandle) {
        String readLine;
        BufferedReader reader = fileHandle.reader(256);
        do {
            try {
                try {
                    readLine = reader.readLine();
                    if (readLine == null) {
                        throw new GdxRuntimeException("Polygon shape not found: " + fileHandle);
                    }
                } catch (IOException e) {
                    throw new GdxRuntimeException("Error reading polygon shape file: " + fileHandle, e);
                }
            } finally {
                StreamUtils.closeQuietly(reader);
            }
        } while (!readLine.startsWith("s"));
        String[] split = readLine.substring(1).trim().split(",");
        float[] fArr = new float[split.length];
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            fArr[i] = Float.parseFloat(split[i]);
        }
        PolygonRegion polygonRegion = new PolygonRegion(textureRegion, fArr, this.triangulator.computeTriangles(fArr).toArray());
        StreamUtils.closeQuietly(reader);
        return polygonRegion;
    }
}
