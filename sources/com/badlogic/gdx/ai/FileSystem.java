package com.badlogic.gdx.ai;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import java.io.File;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/FileSystem.class */
public interface FileSystem {
    FileHandleResolver newResolver(Files.FileType fileType);

    FileHandle newFileHandle(String str);

    FileHandle newFileHandle(File file);

    FileHandle newFileHandle(String str, Files.FileType fileType);

    FileHandle newFileHandle(File file, Files.FileType fileType);
}
