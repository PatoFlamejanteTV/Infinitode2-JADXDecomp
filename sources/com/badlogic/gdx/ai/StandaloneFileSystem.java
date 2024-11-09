package com.badlogic.gdx.ai;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.File;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/StandaloneFileSystem.class */
public class StandaloneFileSystem implements FileSystem {
    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandleResolver newResolver(final Files.FileType fileType) {
        return new FileHandleResolver() { // from class: com.badlogic.gdx.ai.StandaloneFileSystem.1
            @Override // com.badlogic.gdx.assets.loaders.FileHandleResolver
            public FileHandle resolve(String str) {
                return new DesktopFileHandle(str, fileType);
            }
        };
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(String str) {
        return new DesktopFileHandle(str, Files.FileType.Absolute);
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(File file) {
        return new DesktopFileHandle(file, Files.FileType.Absolute);
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(String str, Files.FileType fileType) {
        return new DesktopFileHandle(str, fileType);
    }

    @Override // com.badlogic.gdx.ai.FileSystem
    public FileHandle newFileHandle(File file, Files.FileType fileType) {
        return new DesktopFileHandle(file, fileType);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/StandaloneFileSystem$DesktopFileHandle.class */
    public static class DesktopFileHandle extends FileHandle {
        public static final String externalPath = System.getProperty("user.home") + File.separator;
        public static final String localPath = new File("").getAbsolutePath() + File.separator;

        public DesktopFileHandle(String str, Files.FileType fileType) {
            super(str, fileType);
        }

        public DesktopFileHandle(File file, Files.FileType fileType) {
            super(file, fileType);
        }

        @Override // com.badlogic.gdx.files.FileHandle
        public FileHandle child(String str) {
            return this.file.getPath().length() == 0 ? new DesktopFileHandle(new File(str), this.type) : new DesktopFileHandle(new File(this.file, str), this.type);
        }

        @Override // com.badlogic.gdx.files.FileHandle
        public FileHandle sibling(String str) {
            if (this.file.getPath().length() == 0) {
                throw new GdxRuntimeException("Cannot get the sibling of the root.");
            }
            return new DesktopFileHandle(new File(this.file.getParent(), str), this.type);
        }

        @Override // com.badlogic.gdx.files.FileHandle
        public FileHandle parent() {
            File parentFile = this.file.getParentFile();
            File file = parentFile;
            if (parentFile == null) {
                if (this.type == Files.FileType.Absolute) {
                    file = new File("/");
                } else {
                    file = new File("");
                }
            }
            return new DesktopFileHandle(file, this.type);
        }

        @Override // com.badlogic.gdx.files.FileHandle
        public File file() {
            return this.type == Files.FileType.External ? new File(externalPath, this.file.getPath()) : this.type == Files.FileType.Local ? new File(localPath, this.file.getPath()) : this.file;
        }
    }
}
