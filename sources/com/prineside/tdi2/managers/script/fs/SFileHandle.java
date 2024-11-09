package com.prineside.tdi2.managers.script.fs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.utils.REGS;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/fs/SFileHandle.class */
public final class SFileHandle {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f2566a = {"cache/script-temp-files/", "cache/script-data/", "i18n/", "levels/", "models/", "particles/", Config.RESOURCES_DIR, "resourcepacks/", "scripts/", "shaders/"};

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f2567b = {"res/luaj/"};
    private final FileHandle c;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/script/fs/SFileHandle$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<SFileHandle> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, SFileHandle sFileHandle) {
            output.writeString(sFileHandle.c.path());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public SFileHandle read2(Kryo kryo, Input input, Class<? extends SFileHandle> cls) {
            return new SFileHandle(input.readString());
        }
    }

    public static void assertAccessible(String str) {
        if (str.contains("..")) {
            throw new IllegalArgumentException("Paths can not contain '..'");
        }
        if (str.matches("[^a-zA-Z0-9_\\-./]")) {
            throw new IllegalArgumentException("Invalid characters found in the path, allowed characters: [a-zA-Z0-9_\\-./]");
        }
        String absolutePath = Gdx.files.local(str).file().getAbsolutePath();
        boolean z = false;
        String[] strArr = f2566a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (!absolutePath.startsWith(Gdx.files.local(strArr[i]).file().getAbsolutePath())) {
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (!z) {
            throw new IllegalArgumentException("Path is outside of the accessible dirs: " + Arrays.toString(f2566a));
        }
        for (String str2 : f2567b) {
            if (absolutePath.startsWith(Gdx.files.local(str2).file().getAbsolutePath())) {
                throw new IllegalArgumentException("Directory " + str2 + " is not accessible");
            }
        }
    }

    public static boolean isAccessible(String str) {
        try {
            if (str.contains("..") || str.matches("[^a-zA-Z0-9_\\-./]")) {
                return false;
            }
            String absolutePath = Gdx.files.local(str).file().getAbsolutePath();
            boolean z = false;
            String[] strArr = f2566a;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str2 = strArr[i];
                String absolutePath2 = Gdx.files.local(str2).file().getAbsolutePath();
                if (!absolutePath.startsWith(absolutePath2.substring(0, absolutePath2.length() - str2.length()))) {
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return false;
            }
            for (String str3 : f2567b) {
                String absolutePath3 = Gdx.files.local(str3).file().getAbsolutePath();
                if (absolutePath.startsWith(absolutePath3.substring(0, absolutePath3.length() - str3.length()))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public SFileHandle(String str) {
        assertAccessible(str);
        this.c = Gdx.files.local(str);
    }

    private SFileHandle(FileHandle fileHandle) {
        assertAccessible(fileHandle.path());
        this.c = fileHandle;
    }

    public final String path() {
        return this.c.path();
    }

    public final String name() {
        return this.c.name();
    }

    public final String extension() {
        return this.c.extension();
    }

    public final String nameWithoutExtension() {
        return this.c.nameWithoutExtension();
    }

    public final String pathWithoutExtension() {
        return this.c.pathWithoutExtension();
    }

    public final InputStream read() {
        return this.c.read();
    }

    public final BufferedInputStream read(int i) {
        return this.c.read(i);
    }

    public final Reader reader() {
        return this.c.reader();
    }

    public final Reader reader(String str) {
        return this.c.reader(str);
    }

    public final BufferedReader reader(int i) {
        return this.c.reader(i);
    }

    public final BufferedReader reader(int i, String str) {
        return reader(i, str);
    }

    public final String readString() {
        return this.c.readString();
    }

    public final String readString(String str) {
        return this.c.readString(str);
    }

    public final byte[] readBytes() {
        return this.c.readBytes();
    }

    public final int readBytes(byte[] bArr, int i, int i2) {
        return this.c.readBytes(bArr, i, i2);
    }

    public final ByteBuffer map() {
        return this.c.map();
    }

    public final ByteBuffer map(FileChannel.MapMode mapMode) {
        return this.c.map(mapMode);
    }

    public final OutputStream write(boolean z) {
        return this.c.write(z);
    }

    public final OutputStream write(boolean z, int i) {
        return this.c.write(z, i);
    }

    public final void write(InputStream inputStream, boolean z) {
        this.c.write(inputStream, z);
    }

    public final Writer writer(boolean z) {
        return this.c.writer(z);
    }

    public final Writer writer(boolean z, String str) {
        return this.c.writer(z, str);
    }

    public final void writeString(String str, boolean z) {
        this.c.writeString(str, z);
    }

    public final void writeString(String str, boolean z, String str2) {
        this.c.writeString(str, z, str2);
    }

    public final void writeBytes(byte[] bArr, boolean z) {
        this.c.writeBytes(bArr, z);
    }

    public final void writeBytes(byte[] bArr, int i, int i2, boolean z) {
        this.c.writeBytes(bArr, i, i2, z);
    }

    public final SFileHandle[] list() {
        FileHandle[] list = this.c.list();
        Array array = new Array(true, 1, SFileHandle.class);
        for (FileHandle fileHandle : list) {
            if (isAccessible(fileHandle.path())) {
                array.add(new SFileHandle(fileHandle.path()));
            }
        }
        return (SFileHandle[]) array.toArray();
    }

    public final SFileHandle[] list(FileFilter fileFilter) {
        FileHandle[] list = this.c.list(fileFilter);
        Array array = new Array(true, 1, SFileHandle.class);
        for (FileHandle fileHandle : list) {
            if (isAccessible(fileHandle.path())) {
                array.add(new SFileHandle(fileHandle.path()));
            }
        }
        return (SFileHandle[]) array.toArray();
    }

    public final SFileHandle[] list(FilenameFilter filenameFilter) {
        FileHandle[] list = this.c.list(filenameFilter);
        Array array = new Array(true, 1, SFileHandle.class);
        for (FileHandle fileHandle : list) {
            if (isAccessible(fileHandle.path())) {
                array.add(new SFileHandle(fileHandle.path()));
            }
        }
        return (SFileHandle[]) array.toArray();
    }

    public final SFileHandle[] list(String str) {
        FileHandle[] list = this.c.list(str);
        Array array = new Array(true, 1, SFileHandle.class);
        for (FileHandle fileHandle : list) {
            if (isAccessible(fileHandle.path())) {
                array.add(new SFileHandle(fileHandle.path()));
            }
        }
        return (SFileHandle[]) array.toArray();
    }

    public final boolean isDirectory() {
        return this.c.isDirectory();
    }

    public final SFileHandle child(String str) {
        return new SFileHandle(this.c.child(str));
    }

    public final SFileHandle sibling(String str) {
        return new SFileHandle(this.c.sibling(str));
    }

    public final SFileHandle parent() {
        return new SFileHandle(this.c.parent());
    }

    public final void mkdirs() {
        this.c.mkdirs();
    }

    public final boolean exists() {
        return this.c.exists();
    }

    public final boolean delete() {
        return this.c.delete();
    }

    public final boolean deleteDirectory() {
        return this.c.deleteDirectory();
    }

    public final void emptyDirectory() {
        this.c.emptyDirectory();
    }

    public final void emptyDirectory(boolean z) {
        this.c.emptyDirectory(z);
    }

    public final void copyTo(SFileHandle sFileHandle) {
        this.c.copyTo(sFileHandle.c);
    }

    public final void moveTo(SFileHandle sFileHandle) {
        this.c.moveTo(sFileHandle.c);
    }

    public final long length() {
        return this.c.length();
    }

    public final long lastModified() {
        return this.c.lastModified();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof SFileHandle) {
            return path().equals(((SFileHandle) obj).path());
        }
        return false;
    }

    public final int hashCode() {
        return 67 + path().hashCode();
    }

    public final String toString() {
        return this.c.toString();
    }
}
