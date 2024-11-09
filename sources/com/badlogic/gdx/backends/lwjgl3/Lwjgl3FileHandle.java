package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.File;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3FileHandle.class */
public final class Lwjgl3FileHandle extends FileHandle {
    public Lwjgl3FileHandle(String str, Files.FileType fileType) {
        super(str, fileType);
    }

    public Lwjgl3FileHandle(File file, Files.FileType fileType) {
        super(file, fileType);
    }

    @Override // com.badlogic.gdx.files.FileHandle
    public final FileHandle child(String str) {
        return this.file.getPath().length() == 0 ? new Lwjgl3FileHandle(new File(str), this.type) : new Lwjgl3FileHandle(new File(this.file, str), this.type);
    }

    @Override // com.badlogic.gdx.files.FileHandle
    public final FileHandle sibling(String str) {
        if (this.file.getPath().length() == 0) {
            throw new GdxRuntimeException("Cannot get the sibling of the root.");
        }
        return new Lwjgl3FileHandle(new File(this.file.getParent(), str), this.type);
    }

    @Override // com.badlogic.gdx.files.FileHandle
    public final FileHandle parent() {
        File parentFile = this.file.getParentFile();
        File file = parentFile;
        if (parentFile == null) {
            if (this.type == Files.FileType.Absolute) {
                file = new File("/");
            } else {
                file = new File("");
            }
        }
        return new Lwjgl3FileHandle(file, this.type);
    }

    @Override // com.badlogic.gdx.files.FileHandle
    public final File file() {
        return this.type == Files.FileType.External ? new File(Lwjgl3Files.externalPath, this.file.getPath()) : this.type == Files.FileType.Local ? new File(Lwjgl3Files.localPath, this.file.getPath()) : this.file;
    }
}
