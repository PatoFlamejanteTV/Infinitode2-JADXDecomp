package com.badlogic.gdx.backends.lwjgl3.awt;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.UUID;
import java.util.zip.CRC32;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/awt/GlfwAWTLoader.class */
public class GlfwAWTLoader {
    public static boolean isMac = System.getProperty("os.name").contains("Mac");
    private static final Random random = new Random();

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    static String randomUUID() {
        return new UUID(random.nextLong(), random.nextLong()).toString();
    }

    public static String crc(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        CRC32 crc32 = new CRC32();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                crc32.update(bArr, 0, read);
            } catch (Exception unused) {
            } finally {
                closeQuietly(inputStream);
            }
        }
        return Long.toString(crc32.getValue(), 16);
    }

    private static File extractFile(String str, File file) {
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new GdxRuntimeException("Couldn't create ANGLE native library output directory " + file.getParentFile().getAbsolutePath());
            }
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                inputStream = GlfwAWTLoader.class.getResourceAsStream("/" + str);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        closeQuietly(fileOutputStream);
                        closeQuietly(inputStream);
                        return file;
                    }
                }
            } catch (Throwable th) {
                closeQuietly(fileOutputStream);
                closeQuietly(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            throw new GdxRuntimeException("Couldn't load ANGLE shared library " + str, th2);
        }
    }

    private static File getExtractedFile(String str, String str2) {
        File file = new File(System.getProperty("java.io.tmpdir") + "/libgdx" + System.getProperty("user.name") + "/" + str, str2);
        if (canWrite(file)) {
            return file;
        }
        try {
            File createTempFile = File.createTempFile(str, null);
            if (createTempFile.delete()) {
                File file2 = new File(createTempFile, str2);
                if (canWrite(file2)) {
                    return file2;
                }
            }
        } catch (IOException unused) {
        }
        File file3 = new File(System.getProperty("user.home") + "/.libgdx/" + str, str2);
        if (canWrite(file3)) {
            return file3;
        }
        File file4 = new File(".temp/" + str, str2);
        if (canWrite(file4)) {
            return file4;
        }
        if (System.getenv("APP_SANDBOX_CONTAINER_ID") != null) {
            return file;
        }
        return null;
    }

    private static boolean canWrite(File file) {
        File file2;
        File parentFile = file.getParentFile();
        if (!file.exists()) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                return false;
            }
            file2 = file;
        } else {
            if (!file.canWrite() || !canExecute(file)) {
                return false;
            }
            file2 = new File(parentFile, randomUUID().toString());
        }
        try {
            new FileOutputStream(file2).close();
            if (canExecute(file2)) {
                file2.delete();
                return true;
            }
            file2.delete();
            return false;
        } catch (Throwable unused) {
            file2.delete();
            return false;
        }
    }

    private static boolean canExecute(File file) {
        try {
            Method method = File.class.getMethod("canExecute", new Class[0]);
            if (((Boolean) method.invoke(file, new Object[0])).booleanValue()) {
                return true;
            }
            File.class.getMethod("setExecutable", Boolean.TYPE, Boolean.TYPE).invoke(file, Boolean.TRUE, Boolean.FALSE);
            return ((Boolean) method.invoke(file, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static File load() {
        if (!isMac) {
            return null;
        }
        if (!EventQueue.isDispatchThread()) {
            try {
                EventQueue.invokeAndWait(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.awt.GlfwAWTLoader.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Toolkit.getDefaultToolkit();
                    }
                });
            } catch (Throwable th) {
                throw new GdxRuntimeException("Couldn't initialize AWT.", th);
            }
        }
        String str = SharedLibraryLoader.isARM ? "macosarm64/libglfwarm64.dylib" : "macosx64/libglfw.dylib";
        File extractedFile = getExtractedFile(crc(GlfwAWTLoader.class.getResourceAsStream("/" + str)), new File(str).getName());
        extractFile(str, extractedFile);
        return extractedFile;
    }
}
