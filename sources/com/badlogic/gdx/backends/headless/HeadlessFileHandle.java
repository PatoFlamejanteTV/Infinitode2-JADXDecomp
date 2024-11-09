package com.badlogic.gdx.backends.headless;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.File;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/headless/HeadlessFileHandle.class */
public final class HeadlessFileHandle extends FileHandle {
    public HeadlessFileHandle(String str, Files.FileType fileType) {
        super(str, fileType);
    }

    public HeadlessFileHandle(File file, Files.FileType fileType) {
        super(file, fileType);
    }

    @Override // com.badlogic.gdx.files.FileHandle
    public final FileHandle child(String str) {
        return this.file.getPath().length() == 0 ? new HeadlessFileHandle(new File(str), this.type) : new HeadlessFileHandle(new File(this.file, str), this.type);
    }

    @Override // com.badlogic.gdx.files.FileHandle
    public final FileHandle sibling(String str) {
        if (this.file.getPath().length() == 0) {
            throw new GdxRuntimeException("Cannot get the sibling of the root.");
        }
        return new HeadlessFileHandle(new File(this.file.getParent(), str), this.type);
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
        return new HeadlessFileHandle(file, this.type);
    }

    @Override // com.badlogic.gdx.files.FileHandle
    public final File file() {
        return this.type == Files.FileType.External ? new File(HeadlessFiles.externalPath, this.file.getPath()) : this.type == Files.FileType.Local ? new File(HeadlessFiles.localPath, this.file.getPath()) : this.file;
    }
}
