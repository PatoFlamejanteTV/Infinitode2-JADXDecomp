package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/BitmapFontLoader.class */
public class BitmapFontLoader extends AsynchronousAssetLoader<BitmapFont, BitmapFontParameter> {
    BitmapFont.BitmapFontData data;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/BitmapFontLoader$BitmapFontParameter.class */
    public static class BitmapFontParameter extends AssetLoaderParameters<BitmapFont> {
        public boolean flip = false;
        public boolean genMipMaps = false;
        public Texture.TextureFilter minFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureFilter magFilter = Texture.TextureFilter.Nearest;
        public BitmapFont.BitmapFontData bitmapFontData = null;
        public String atlasName = null;
    }

    public BitmapFontLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, BitmapFontParameter bitmapFontParameter) {
        Array<AssetDescriptor> array = new Array<>();
        if (bitmapFontParameter != null && bitmapFontParameter.bitmapFontData != null) {
            this.data = bitmapFontParameter.bitmapFontData;
            return array;
        }
        this.data = new BitmapFont.BitmapFontData(fileHandle, bitmapFontParameter != null && bitmapFontParameter.flip);
        if (bitmapFontParameter != null && bitmapFontParameter.atlasName != null) {
            array.add(new AssetDescriptor(bitmapFontParameter.atlasName, TextureAtlas.class));
        } else {
            for (int i = 0; i < this.data.getImagePaths().length; i++) {
                FileHandle resolve = resolve(this.data.getImagePath(i));
                TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
                if (bitmapFontParameter != null) {
                    textureParameter.genMipMaps = bitmapFontParameter.genMipMaps;
                    textureParameter.minFilter = bitmapFontParameter.minFilter;
                    textureParameter.magFilter = bitmapFontParameter.magFilter;
                }
                array.add(new AssetDescriptor(resolve, Texture.class, textureParameter));
            }
        }
        return array;
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, BitmapFontParameter bitmapFontParameter) {
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public BitmapFont loadSync(AssetManager assetManager, String str, FileHandle fileHandle, BitmapFontParameter bitmapFontParameter) {
        if (bitmapFontParameter != null && bitmapFontParameter.atlasName != null) {
            TextureAtlas textureAtlas = (TextureAtlas) assetManager.get(bitmapFontParameter.atlasName, TextureAtlas.class);
            String str2 = fileHandle.sibling(this.data.imagePaths[0]).nameWithoutExtension().toString();
            TextureAtlas.AtlasRegion findRegion = textureAtlas.findRegion(str2);
            if (findRegion != null) {
                return new BitmapFont(fileHandle, findRegion);
            }
            throw new GdxRuntimeException("Could not find font region " + str2 + " in atlas " + bitmapFontParameter.atlasName);
        }
        int length = this.data.getImagePaths().length;
        Array array = new Array(length);
        for (int i = 0; i < length; i++) {
            array.add(new TextureRegion((Texture) assetManager.get(this.data.getImagePath(i), Texture.class)));
        }
        return new BitmapFont(this.data, (Array<TextureRegion>) array, true);
    }
}
