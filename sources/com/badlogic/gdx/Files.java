package com.badlogic.gdx;

import com.badlogic.gdx.files.FileHandle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Files.class */
public interface Files {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Files$FileType.class */
    public enum FileType {
        Classpath,
        Internal,
        External,
        Absolute,
        Local
    }

    FileHandle getFileHandle(String str, FileType fileType);

    FileHandle classpath(String str);

    FileHandle internal(String str);

    FileHandle external(String str);

    FileHandle absolute(String str);

    FileHandle local(String str);

    String getExternalStoragePath();

    boolean isExternalStorageAvailable();

    String getLocalStoragePath();

    boolean isLocalStorageAvailable();
}
