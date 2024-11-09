package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/MusicLoader.class */
public class MusicLoader extends AsynchronousAssetLoader<Music, MusicParameter> {
    private Music music;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/MusicLoader$MusicParameter.class */
    public static class MusicParameter extends AssetLoaderParameters<Music> {
    }

    public MusicLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    protected Music getLoadedMusic() {
        return this.music;
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, MusicParameter musicParameter) {
        this.music = Gdx.audio.newMusic(fileHandle);
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Music loadSync(AssetManager assetManager, String str, FileHandle fileHandle, MusicParameter musicParameter) {
        Music music = this.music;
        this.music = null;
        return music;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, MusicParameter musicParameter) {
        return null;
    }
}
