package com.badlogic.gdx.utils;

import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.utils.Architecture;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/SharedLibraryLoader.class */
public class SharedLibraryLoader {
    public static Os os;
    public static Architecture.Bitness bitness;
    public static Architecture architecture;

    @Deprecated
    public static boolean isWindows;

    @Deprecated
    public static boolean isLinux;

    @Deprecated
    public static boolean isMac;

    @Deprecated
    public static boolean isIos;

    @Deprecated
    public static boolean isAndroid;

    @Deprecated
    public static boolean isARM;

    @Deprecated
    public static boolean is64Bit;
    private static final HashSet<String> loadedLibraries;
    private static final Random random;
    private String nativesJar;

    static {
        bitness = Architecture.Bitness._32;
        architecture = Architecture.x86;
        if (System.getProperty("os.name").contains(PurchaseManagerConfig.STORE_NAME_DESKTOP_WINDOWS)) {
            os = Os.Windows;
        } else if (System.getProperty("os.name").contains("Linux")) {
            os = Os.Linux;
        } else if (System.getProperty("os.name").contains("Mac")) {
            os = Os.MacOsX;
        }
        if (System.getProperty("os.arch").startsWith("arm") || System.getProperty("os.arch").startsWith("aarch64")) {
            architecture = Architecture.ARM;
        } else if (System.getProperty("os.arch").startsWith("riscv")) {
            architecture = Architecture.RISCV;
        } else if (System.getProperty("os.arch").startsWith("loongarch")) {
            architecture = Architecture.LOONGARCH;
        }
        if (System.getProperty("os.arch").contains("64") || System.getProperty("os.arch").startsWith("armv8")) {
            bitness = Architecture.Bitness._64;
        } else if (System.getProperty("os.arch").contains("128")) {
            bitness = Architecture.Bitness._128;
        }
        boolean z = System.getProperty("moe.platform.name") != null;
        String property = System.getProperty("java.runtime.name");
        if (property != null && property.contains("Android Runtime")) {
            os = Os.Android;
            bitness = Architecture.Bitness._32;
            architecture = Architecture.x86;
        }
        if (z || (os != Os.Android && os != Os.Windows && os != Os.Linux && os != Os.MacOsX)) {
            os = Os.IOS;
            bitness = Architecture.Bitness._32;
            architecture = Architecture.x86;
        }
        isWindows = os == Os.Windows;
        isLinux = os == Os.Linux;
        isMac = os == Os.MacOsX;
        isIos = os == Os.IOS;
        isAndroid = os == Os.Android;
        isARM = architecture == Architecture.ARM;
        is64Bit = bitness == Architecture.Bitness._64;
        loadedLibraries = new HashSet<>();
        random = new Random();
    }

    public SharedLibraryLoader() {
    }

    static String randomUUID() {
        return new UUID(random.nextLong(), random.nextLong()).toString();
    }

    public SharedLibraryLoader(String str) {
        this.nativesJar = str;
    }

    public String crc(InputStream inputStream) {
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

    public String mapLibraryName(String str) {
        if (os == Os.Android) {
            return str;
        }
        return os.getLibPrefix() + str + architecture.toSuffix() + bitness.toSuffix() + "." + os.getLibExtension();
    }

    public void load(String str) {
        if (os == Os.IOS) {
            return;
        }
        synchronized (SharedLibraryLoader.class) {
            if (isLoaded(str)) {
                return;
            }
            String mapLibraryName = mapLibraryName(str);
            try {
                if (os == Os.Android) {
                    System.loadLibrary(mapLibraryName);
                } else {
                    loadFile(mapLibraryName);
                }
                setLoaded(str);
            } catch (Throwable th) {
                throw new SharedLibraryLoadRuntimeException("Couldn't load shared library '" + mapLibraryName + "' for target: " + (os == Os.Android ? "Android" : System.getProperty("os.name") + ", " + architecture.name() + ", " + bitness.name().substring(1) + "-bit"), th);
            }
        }
    }

    private InputStream readFile(String str) {
        if (this.nativesJar == null) {
            InputStream resourceAsStream = SharedLibraryLoader.class.getResourceAsStream("/" + str);
            if (resourceAsStream == null) {
                throw new SharedLibraryLoadRuntimeException("Unable to read file for extraction: " + str);
            }
            return resourceAsStream;
        }
        try {
            ZipFile zipFile = new ZipFile(this.nativesJar);
            ZipEntry entry = zipFile.getEntry(str);
            if (entry == null) {
                throw new SharedLibraryLoadRuntimeException("Couldn't find '" + str + "' in JAR: " + this.nativesJar);
            }
            return zipFile.getInputStream(entry);
        } catch (IOException e) {
            throw new SharedLibraryLoadRuntimeException("Error reading '" + str + "' in JAR: " + this.nativesJar, e);
        }
    }

    public File extractFile(String str, String str2) {
        try {
            String crc = crc(readFile(str));
            if (str2 == null) {
                str2 = crc;
            }
            File extractedFile = getExtractedFile(str2, new File(str).getName());
            File file = extractedFile;
            if (extractedFile == null) {
                File extractedFile2 = getExtractedFile(randomUUID(), new File(str).getName());
                file = extractedFile2;
                if (extractedFile2 == null) {
                    throw new SharedLibraryLoadRuntimeException("Unable to find writable path to extract file. Is the user home directory writable?");
                }
            }
            return extractFile(str, crc, file);
        } catch (RuntimeException e) {
            File file2 = new File(System.getProperty("java.library.path"), str);
            if (file2.exists()) {
                return file2;
            }
            throw e;
        }
    }

    public void extractFileTo(String str, File file) {
        extractFile(str, crc(readFile(str)), new File(file, new File(str).getName()));
    }

    private File getExtractedFile(String str, String str2) {
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

    private boolean canWrite(File file) {
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

    private boolean canExecute(File file) {
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

    private File extractFile(String str, String str2, File file) {
        String str3 = null;
        if (file.exists()) {
            try {
                str3 = crc(new FileInputStream(file));
            } catch (FileNotFoundException unused) {
            }
        }
        if (str3 == null || !str3.equals(str2)) {
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    inputStream = readFile(str);
                    file.getParentFile().mkdirs();
                    fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    closeQuietly(inputStream);
                    closeQuietly(fileOutputStream);
                } catch (IOException e) {
                    throw new SharedLibraryLoadRuntimeException("Error extracting file: " + str + "\nTo: " + file.getAbsolutePath(), e);
                }
            } catch (Throwable th) {
                closeQuietly(inputStream);
                closeQuietly(fileOutputStream);
                throw th;
            }
        }
        return file;
    }

    private void loadFile(String str) {
        String crc = crc(readFile(str));
        String name = new File(str).getName();
        Throwable loadFile = loadFile(str, crc, new File(System.getProperty("java.io.tmpdir") + "/libgdx" + System.getProperty("user.name") + "/" + crc, name));
        if (loadFile == null) {
            return;
        }
        try {
            File createTempFile = File.createTempFile(crc, null);
            if (createTempFile.delete()) {
                if (loadFile(str, crc, createTempFile) == null) {
                    return;
                }
            }
        } catch (Throwable unused) {
        }
        if (loadFile(str, crc, new File(System.getProperty("user.home") + "/.libgdx/" + crc, name)) == null || loadFile(str, crc, new File(".temp/" + crc, name)) == null) {
            return;
        }
        File file = new File(System.getProperty("java.library.path"), str);
        if (!file.exists()) {
            throw new SharedLibraryLoadRuntimeException(loadFile);
        }
        System.load(file.getAbsolutePath());
    }

    private Throwable loadFile(String str, String str2, File file) {
        try {
            System.load(extractFile(str, str2, file).getAbsolutePath());
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    public static synchronized void setLoaded(String str) {
        loadedLibraries.add(str);
    }

    public static synchronized boolean isLoaded(String str) {
        return loadedLibraries.contains(str);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
