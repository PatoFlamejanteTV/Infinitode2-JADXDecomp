package com.badlogic.gdx.files;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/files/FileHandle.class */
public class FileHandle {
    protected File file;
    protected Files.FileType type;

    protected FileHandle() {
    }

    public FileHandle(String str) {
        this.file = new File(str);
        this.type = Files.FileType.Absolute;
    }

    public FileHandle(File file) {
        this.file = file;
        this.type = Files.FileType.Absolute;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FileHandle(String str, Files.FileType fileType) {
        this.type = fileType;
        this.file = new File(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FileHandle(File file, Files.FileType fileType) {
        this.file = file;
        this.type = fileType;
    }

    public String path() {
        return this.file.getPath().replace('\\', '/');
    }

    public String name() {
        return this.file.getName();
    }

    public String extension() {
        String name = this.file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : name.substring(lastIndexOf + 1);
    }

    public String nameWithoutExtension() {
        String name = this.file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return name;
        }
        return name.substring(0, lastIndexOf);
    }

    public String pathWithoutExtension() {
        String replace = this.file.getPath().replace('\\', '/');
        int lastIndexOf = replace.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return replace;
        }
        return replace.substring(0, lastIndexOf);
    }

    public Files.FileType type() {
        return this.type;
    }

    public File file() {
        return this.type == Files.FileType.External ? new File(Gdx.files.getExternalStoragePath(), this.file.getPath()) : this.file;
    }

    public InputStream read() {
        if (this.type == Files.FileType.Classpath || ((this.type == Files.FileType.Internal && !file().exists()) || (this.type == Files.FileType.Local && !file().exists()))) {
            InputStream resourceAsStream = FileHandle.class.getResourceAsStream("/" + this.file.getPath().replace('\\', '/'));
            if (resourceAsStream == null) {
                throw new GdxRuntimeException("File not found: " + this.file + " (" + this.type + ")");
            }
            return resourceAsStream;
        }
        try {
            return new FileInputStream(file());
        } catch (Exception e) {
            if (file().isDirectory()) {
                throw new GdxRuntimeException("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", e);
            }
            throw new GdxRuntimeException("Error reading file: " + this.file + " (" + this.type + ")", e);
        }
    }

    public BufferedInputStream read(int i) {
        return new BufferedInputStream(read(), i);
    }

    public Reader reader() {
        return new InputStreamReader(read());
    }

    public Reader reader(String str) {
        InputStream read = read();
        try {
            return new InputStreamReader(read, str);
        } catch (UnsupportedEncodingException e) {
            StreamUtils.closeQuietly(read);
            throw new GdxRuntimeException("Error reading file: " + this, e);
        }
    }

    public BufferedReader reader(int i) {
        return new BufferedReader(new InputStreamReader(read()), i);
    }

    public BufferedReader reader(int i, String str) {
        try {
            return new BufferedReader(new InputStreamReader(read(), str), i);
        } catch (UnsupportedEncodingException e) {
            throw new GdxRuntimeException("Error reading file: " + this, e);
        }
    }

    public String readString() {
        return readString(null);
    }

    public String readString(String str) {
        StringBuilder sb = new StringBuilder(estimateLength());
        InputStreamReader inputStreamReader = null;
        try {
            try {
                if (str == null) {
                    inputStreamReader = new InputStreamReader(read());
                } else {
                    inputStreamReader = new InputStreamReader(read(), str);
                }
                char[] cArr = new char[256];
                while (true) {
                    int read = inputStreamReader.read(cArr);
                    if (read != -1) {
                        sb.append(cArr, 0, read);
                    } else {
                        return sb.toString();
                    }
                }
            } catch (IOException e) {
                throw new GdxRuntimeException("Error reading layout file: " + this, e);
            }
        } finally {
            StreamUtils.closeQuietly(inputStreamReader);
        }
    }

    public byte[] readBytes() {
        InputStream read = read();
        try {
            try {
                byte[] copyStreamToByteArray = StreamUtils.copyStreamToByteArray(read, estimateLength());
                StreamUtils.closeQuietly(read);
                return copyStreamToByteArray;
            } catch (IOException e) {
                throw new GdxRuntimeException("Error reading file: " + this, e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(read);
            throw th;
        }
    }

    private int estimateLength() {
        int length = (int) length();
        if (length != 0) {
            return length;
        }
        return 512;
    }

    public int readBytes(byte[] bArr, int i, int i2) {
        InputStream read = read();
        int i3 = 0;
        while (true) {
            try {
                try {
                    int read2 = read.read(bArr, i + i3, i2 - i3);
                    if (read2 > 0) {
                        i3 += read2;
                    } else {
                        return i3 - i;
                    }
                } catch (IOException e) {
                    throw new GdxRuntimeException("Error reading file: " + this, e);
                }
            } finally {
                StreamUtils.closeQuietly(read);
            }
        }
    }

    public ByteBuffer map() {
        return map(FileChannel.MapMode.READ_ONLY);
    }

    public ByteBuffer map(FileChannel.MapMode mapMode) {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot map a classpath file: " + this);
        }
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                File file = file();
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, mapMode == FileChannel.MapMode.READ_ONLY ? "r" : "rw");
                randomAccessFile = randomAccessFile2;
                MappedByteBuffer map = randomAccessFile2.getChannel().map(mapMode, 0L, file.length());
                map.order(ByteOrder.nativeOrder());
                StreamUtils.closeQuietly(randomAccessFile);
                return map;
            } catch (Exception e) {
                throw new GdxRuntimeException("Error memory mapping file: " + this + " (" + this.type + ")", e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(randomAccessFile);
            throw th;
        }
    }

    public OutputStream write(boolean z) {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot write to a classpath file: " + this.file);
        }
        if (this.type == Files.FileType.Internal) {
            throw new GdxRuntimeException("Cannot write to an internal file: " + this.file);
        }
        parent().mkdirs();
        try {
            return new FileOutputStream(file(), z);
        } catch (Exception e) {
            if (file().isDirectory()) {
                throw new GdxRuntimeException("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", e);
            }
            throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", e);
        }
    }

    public OutputStream write(boolean z, int i) {
        return new BufferedOutputStream(write(z), i);
    }

    public void write(InputStream inputStream, boolean z) {
        OutputStream outputStream = null;
        try {
            try {
                outputStream = write(z);
                StreamUtils.copyStream(inputStream, outputStream);
                StreamUtils.closeQuietly(inputStream);
                StreamUtils.closeQuietly(outputStream);
            } catch (Exception e) {
                throw new GdxRuntimeException("Error stream writing to file: " + this.file + " (" + this.type + ")", e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(inputStream);
            StreamUtils.closeQuietly(outputStream);
            throw th;
        }
    }

    public Writer writer(boolean z) {
        return writer(z, null);
    }

    public Writer writer(boolean z, String str) {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot write to a classpath file: " + this.file);
        }
        if (this.type == Files.FileType.Internal) {
            throw new GdxRuntimeException("Cannot write to an internal file: " + this.file);
        }
        parent().mkdirs();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file(), z);
            if (str == null) {
                return new OutputStreamWriter(fileOutputStream);
            }
            return new OutputStreamWriter(fileOutputStream, str);
        } catch (IOException e) {
            if (file().isDirectory()) {
                throw new GdxRuntimeException("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", e);
            }
            throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", e);
        }
    }

    public void writeString(String str, boolean z) {
        writeString(str, z, null);
    }

    public void writeString(String str, boolean z, String str2) {
        Writer writer = null;
        try {
            try {
                Writer writer2 = writer(z, str2);
                writer = writer2;
                writer2.write(str);
                StreamUtils.closeQuietly(writer);
            } catch (Exception e) {
                throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(writer);
            throw th;
        }
    }

    public void writeBytes(byte[] bArr, boolean z) {
        OutputStream write = write(z);
        try {
            try {
                write.write(bArr);
                StreamUtils.closeQuietly(write);
            } catch (IOException e) {
                throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(write);
            throw th;
        }
    }

    public void writeBytes(byte[] bArr, int i, int i2, boolean z) {
        OutputStream write = write(z);
        try {
            try {
                write.write(bArr, i, i2);
                StreamUtils.closeQuietly(write);
            } catch (IOException e) {
                throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(write);
            throw th;
        }
    }

    public FileHandle[] list() {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        String[] list = file().list();
        if (list == null) {
            return new FileHandle[0];
        }
        FileHandle[] fileHandleArr = new FileHandle[list.length];
        int length = list.length;
        for (int i = 0; i < length; i++) {
            fileHandleArr[i] = child(list[i]);
        }
        return fileHandleArr;
    }

    public FileHandle[] list(FileFilter fileFilter) {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        String[] list = file().list();
        if (list == null) {
            return new FileHandle[0];
        }
        FileHandle[] fileHandleArr = new FileHandle[list.length];
        int i = 0;
        for (String str : list) {
            FileHandle child = child(str);
            if (fileFilter.accept(child.file())) {
                fileHandleArr[i] = child;
                i++;
            }
        }
        if (i < list.length) {
            FileHandle[] fileHandleArr2 = new FileHandle[i];
            System.arraycopy(fileHandleArr, 0, fileHandleArr2, 0, i);
            fileHandleArr = fileHandleArr2;
        }
        return fileHandleArr;
    }

    public FileHandle[] list(FilenameFilter filenameFilter) {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        File file = file();
        String[] list = file.list();
        if (list == null) {
            return new FileHandle[0];
        }
        FileHandle[] fileHandleArr = new FileHandle[list.length];
        int i = 0;
        for (String str : list) {
            if (filenameFilter.accept(file, str)) {
                fileHandleArr[i] = child(str);
                i++;
            }
        }
        if (i < list.length) {
            FileHandle[] fileHandleArr2 = new FileHandle[i];
            System.arraycopy(fileHandleArr, 0, fileHandleArr2, 0, i);
            fileHandleArr = fileHandleArr2;
        }
        return fileHandleArr;
    }

    public FileHandle[] list(String str) {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        String[] list = file().list();
        if (list == null) {
            return new FileHandle[0];
        }
        FileHandle[] fileHandleArr = new FileHandle[list.length];
        int i = 0;
        for (String str2 : list) {
            if (str2.endsWith(str)) {
                fileHandleArr[i] = child(str2);
                i++;
            }
        }
        if (i < list.length) {
            FileHandle[] fileHandleArr2 = new FileHandle[i];
            System.arraycopy(fileHandleArr, 0, fileHandleArr2, 0, i);
            fileHandleArr = fileHandleArr2;
        }
        return fileHandleArr;
    }

    public boolean isDirectory() {
        if (this.type == Files.FileType.Classpath) {
            return false;
        }
        return file().isDirectory();
    }

    public FileHandle child(String str) {
        return this.file.getPath().length() == 0 ? new FileHandle(new File(str), this.type) : new FileHandle(new File(this.file, str), this.type);
    }

    public FileHandle sibling(String str) {
        if (this.file.getPath().length() == 0) {
            throw new GdxRuntimeException("Cannot get the sibling of the root.");
        }
        return new FileHandle(new File(this.file.getParent(), str), this.type);
    }

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
        return new FileHandle(file, this.type);
    }

    public void mkdirs() {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot mkdirs with a classpath file: " + this.file);
        }
        if (this.type == Files.FileType.Internal) {
            throw new GdxRuntimeException("Cannot mkdirs with an internal file: " + this.file);
        }
        file().mkdirs();
    }

    public boolean exists() {
        switch (this.type) {
            case Internal:
                if (file().exists()) {
                    return true;
                }
                break;
            case Classpath:
                break;
            default:
                return file().exists();
        }
        return FileHandle.class.getResource(new StringBuilder("/").append(this.file.getPath().replace('\\', '/')).toString()) != null;
    }

    public boolean delete() {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot delete a classpath file: " + this.file);
        }
        if (this.type == Files.FileType.Internal) {
            throw new GdxRuntimeException("Cannot delete an internal file: " + this.file);
        }
        return file().delete();
    }

    public boolean deleteDirectory() {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot delete a classpath file: " + this.file);
        }
        if (this.type == Files.FileType.Internal) {
            throw new GdxRuntimeException("Cannot delete an internal file: " + this.file);
        }
        return deleteDirectory(file());
    }

    public void emptyDirectory() {
        emptyDirectory(false);
    }

    public void emptyDirectory(boolean z) {
        if (this.type == Files.FileType.Classpath) {
            throw new GdxRuntimeException("Cannot delete a classpath file: " + this.file);
        }
        if (this.type == Files.FileType.Internal) {
            throw new GdxRuntimeException("Cannot delete an internal file: " + this.file);
        }
        emptyDirectory(file(), z);
    }

    public void copyTo(FileHandle fileHandle) {
        if (!isDirectory()) {
            if (fileHandle.isDirectory()) {
                fileHandle = fileHandle.child(name());
            }
            copyFile(this, fileHandle);
            return;
        }
        if (fileHandle.exists()) {
            if (!fileHandle.isDirectory()) {
                throw new GdxRuntimeException("Destination exists but is not a directory: " + fileHandle);
            }
        } else {
            fileHandle.mkdirs();
            if (!fileHandle.isDirectory()) {
                throw new GdxRuntimeException("Destination directory cannot be created: " + fileHandle);
            }
        }
        copyDirectory(this, fileHandle.child(name()));
    }

    public void moveTo(FileHandle fileHandle) {
        switch (this.type) {
            case Internal:
                throw new GdxRuntimeException("Cannot move an internal file: " + this.file);
            case Classpath:
                throw new GdxRuntimeException("Cannot move a classpath file: " + this.file);
            case Absolute:
            case External:
                if (file().renameTo(fileHandle.file())) {
                    return;
                }
                break;
        }
        copyTo(fileHandle);
        delete();
        if (!exists() || !isDirectory()) {
            return;
        }
        deleteDirectory();
    }

    public long length() {
        if (this.type == Files.FileType.Classpath || (this.type == Files.FileType.Internal && !this.file.exists())) {
            InputStream read = read();
            try {
                return read.available();
            } catch (Exception unused) {
                return 0L;
            } finally {
                StreamUtils.closeQuietly(read);
            }
        }
        return file().length();
    }

    public long lastModified() {
        return file().lastModified();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FileHandle)) {
            return false;
        }
        FileHandle fileHandle = (FileHandle) obj;
        return this.type == fileHandle.type && path().equals(fileHandle.path());
    }

    public int hashCode() {
        return ((37 + this.type.hashCode()) * 67) + path().hashCode();
    }

    public String toString() {
        return this.file.getPath().replace('\\', '/');
    }

    public static FileHandle tempFile(String str) {
        try {
            return new FileHandle(File.createTempFile(str, null));
        } catch (IOException e) {
            throw new GdxRuntimeException("Unable to create temp file.", e);
        }
    }

    public static FileHandle tempDirectory(String str) {
        try {
            File createTempFile = File.createTempFile(str, null);
            if (!createTempFile.delete()) {
                throw new IOException("Unable to delete temp file: " + createTempFile);
            }
            if (!createTempFile.mkdir()) {
                throw new IOException("Unable to create temp directory: " + createTempFile);
            }
            return new FileHandle(createTempFile);
        } catch (IOException e) {
            throw new GdxRuntimeException("Unable to create temp file.", e);
        }
    }

    private static void emptyDirectory(File file, boolean z) {
        File[] listFiles;
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                if (!listFiles[i].isDirectory()) {
                    listFiles[i].delete();
                } else if (z) {
                    emptyDirectory(listFiles[i], true);
                } else {
                    deleteDirectory(listFiles[i]);
                }
            }
        }
    }

    private static boolean deleteDirectory(File file) {
        emptyDirectory(file, false);
        return file.delete();
    }

    private static void copyFile(FileHandle fileHandle, FileHandle fileHandle2) {
        try {
            fileHandle2.write(fileHandle.read(), false);
        } catch (Exception e) {
            throw new GdxRuntimeException("Error copying source file: " + fileHandle.file + " (" + fileHandle.type + ")\nTo destination: " + fileHandle2.file + " (" + fileHandle2.type + ")", e);
        }
    }

    private static void copyDirectory(FileHandle fileHandle, FileHandle fileHandle2) {
        fileHandle2.mkdirs();
        for (FileHandle fileHandle3 : fileHandle.list()) {
            FileHandle child = fileHandle2.child(fileHandle3.name());
            if (fileHandle3.isDirectory()) {
                copyDirectory(fileHandle3, child);
            } else {
                copyFile(fileHandle3, child);
            }
        }
    }
}
