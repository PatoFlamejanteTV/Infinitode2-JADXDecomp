package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ByteArray;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FileUtils.class */
public final class FileUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3828a = TLog.forClass(FileUtils.class);

    private FileUtils() {
    }

    public static void fileToZip(File file, File file2, String str) {
        if (!file.exists()) {
            f3828a.e("fileToZip: File %s does not exist", file);
            return;
        }
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file2, false));
            zipOutputStream.putNextEntry(new ZipEntry(str));
            byte[] bArr = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                int read = fileInputStream.read(bArr);
                fileInputStream.close();
                zipOutputStream.write(bArr, 0, read);
                zipOutputStream.closeEntry();
                zipOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException e) {
            f3828a.e("fileToZip: File %s does not exist", file, e);
        } catch (Exception e2) {
            f3828a.e("fileToZip: error (%s, %s)", file, file2, e2);
        }
    }

    public static Array<String> tail(File file, int i) {
        Array<String> array = new Array<>();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                ByteArray byteArray = new ByteArray();
                long length = file.length() - 1;
                randomAccessFile.seek(length);
                for (long j = length; j >= 0; j--) {
                    randomAccessFile.seek(j);
                    int read = randomAccessFile.read();
                    if (read == 10) {
                        byteArray.reverse();
                        array.add(new String(byteArray.toArray(), StandardCharsets.UTF_8));
                        i--;
                        if (i < 0) {
                            break;
                        }
                        byteArray.clear();
                    } else {
                        byteArray.add((byte) read);
                    }
                }
                randomAccessFile.close();
            } finally {
            }
        } catch (Exception unused) {
        }
        return array;
    }
}
