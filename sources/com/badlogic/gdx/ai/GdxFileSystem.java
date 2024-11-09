package com.badlogic.gdx.ai;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import java.io.File;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/GdxFileSystem.class */
public class GdxFileSystem implements FileSystem {
    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandleResolver newResolver(Files.FileType fileType) {
        switch (fileType) {
            case Absolute:
                return new AbsoluteFileHandleResolver();
            case Classpath:
                return new ClasspathFileHandleResolver();
            case External:
                return new ExternalFileHandleResolver();
            case Internal:
                return new InternalFileHandleResolver();
            case Local:
                return new LocalFileHandleResolver();
            default:
                return null;
        }
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(String str) {
        return Gdx.files.absolute(str);
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(File file) {
        return Gdx.files.absolute(file.getAbsolutePath());
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(String str, Files.FileType fileType) {
        return Gdx.files.getFileHandle(str, fileType);
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(File file, Files.FileType fileType) {
        return Gdx.files.getFileHandle(file.getPath(), fileType);
    }
}
