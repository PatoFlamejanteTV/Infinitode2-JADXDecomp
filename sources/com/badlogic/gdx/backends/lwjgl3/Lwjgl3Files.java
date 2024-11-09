package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import java.io.File;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Files.class */
public final class Lwjgl3Files implements Files {
    public static final String externalPath = System.getProperty("user.home") + File.separator;
    public static final String localPath = new File("").getAbsolutePath() + File.separator;

    @Override // com.badlogic.gdx.Files
    public final FileHandle getFileHandle(String str, Files.FileType fileType) {
        return new Lwjgl3FileHandle(str, fileType);
    }

    @Override // com.badlogic.gdx.Files
    public final FileHandle classpath(String str) {
        return new Lwjgl3FileHandle(str, Files.FileType.Classpath);
    }

    @Override // com.badlogic.gdx.Files
    public final FileHandle internal(String str) {
        return new Lwjgl3FileHandle(str, Files.FileType.Internal);
    }

    @Override // com.badlogic.gdx.Files
    public final FileHandle external(String str) {
        return new Lwjgl3FileHandle(str, Files.FileType.External);
    }

    @Override // com.badlogic.gdx.Files
    public final FileHandle absolute(String str) {
        return new Lwjgl3FileHandle(str, Files.FileType.Absolute);
    }

    @Override // com.badlogic.gdx.Files
    public final FileHandle local(String str) {
        return new Lwjgl3FileHandle(str, Files.FileType.Local);
    }

    @Override // com.badlogic.gdx.Files
    public final String getExternalStoragePath() {
        return externalPath;
    }

    @Override // com.badlogic.gdx.Files
    public final boolean isExternalStorageAvailable() {
        return true;
    }

    @Override // com.badlogic.gdx.Files
    public final String getLocalStoragePath() {
        return localPath;
    }

    @Override // com.badlogic.gdx.Files
    public final boolean isLocalStorageAvailable() {
        return true;
    }
}
