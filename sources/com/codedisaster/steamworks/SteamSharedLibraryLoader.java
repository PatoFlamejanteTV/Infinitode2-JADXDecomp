package com.codedisaster.steamworks;

import com.badlogic.gdx.pay.PurchaseManagerConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamSharedLibraryLoader.class */
class SteamSharedLibraryLoader {
    private static final PLATFORM OS;
    private static final boolean IS_64_BIT;
    private static final String SHARED_LIBRARY_EXTRACT_DIRECTORY = System.getProperty("com.codedisaster.steamworks.SharedLibraryExtractDirectory", "steamworks4j");
    private static final String SHARED_LIBRARY_EXTRACT_PATH = System.getProperty("com.codedisaster.steamworks.SharedLibraryExtractPath", null);
    private static final String SDK_REDISTRIBUTABLE_BIN_PATH = System.getProperty("com.codedisaster.steamworks.SDKRedistributableBinPath", "sdk/redistributable_bin");
    private static final String SDK_LIBRARY_PATH = System.getProperty("com.codedisaster.steamworks.SDKLibraryPath", "sdk/public/steam/lib");
    static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("com.codedisaster.steamworks.Debug", "false"));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamSharedLibraryLoader$PLATFORM.class */
    public enum PLATFORM {
        Windows,
        Linux,
        MacOS
    }

    SteamSharedLibraryLoader() {
    }

    static {
        String property = System.getProperty("os.name");
        String property2 = System.getProperty("os.arch");
        if (property.contains(PurchaseManagerConfig.STORE_NAME_DESKTOP_WINDOWS)) {
            OS = PLATFORM.Windows;
        } else if (property.contains("Linux")) {
            OS = PLATFORM.Linux;
        } else if (property.contains("Mac")) {
            OS = PLATFORM.MacOS;
        } else {
            throw new RuntimeException("Unknown host architecture: " + property + ", " + property2);
        }
        IS_64_BIT = property2.equals("amd64") || property2.equals("x86_64");
    }

    private static String getPlatformLibName(String str) {
        switch (OS) {
            case Windows:
                return str + (IS_64_BIT ? "64" : "") + ".dll";
            case Linux:
                return "lib" + str + ".so";
            case MacOS:
                return "lib" + str + ".dylib";
            default:
                throw new RuntimeException("Unknown host architecture");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSdkRedistributableBinPath() {
        File file;
        switch (OS) {
            case Windows:
                file = new File(SDK_REDISTRIBUTABLE_BIN_PATH, IS_64_BIT ? "win64" : "");
                break;
            case Linux:
                file = new File(SDK_REDISTRIBUTABLE_BIN_PATH, "linux64");
                break;
            case MacOS:
                file = new File(SDK_REDISTRIBUTABLE_BIN_PATH, "osx");
                break;
            default:
                return null;
        }
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }

    static String getSdkLibraryPath() {
        File file;
        switch (OS) {
            case Windows:
                file = new File(SDK_LIBRARY_PATH, IS_64_BIT ? "win64" : "win32");
                break;
            case Linux:
                file = new File(SDK_LIBRARY_PATH, "linux64");
                break;
            case MacOS:
                file = new File(SDK_LIBRARY_PATH, "osx");
                break;
            default:
                return null;
        }
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadLibrary(String str, String str2) {
        try {
            String platformLibName = getPlatformLibName(str);
            File discoverExtractLocation = discoverExtractLocation(SHARED_LIBRARY_EXTRACT_DIRECTORY + "/" + Version.getVersion(), platformLibName);
            if (str2 == null) {
                extractLibrary(discoverExtractLocation, platformLibName);
            } else {
                File file = new File(str2, platformLibName);
                if (OS != PLATFORM.Windows) {
                    extractLibrary(discoverExtractLocation, file);
                } else {
                    discoverExtractLocation = file;
                }
            }
            System.load(discoverExtractLocation.getCanonicalPath());
        } catch (IOException e) {
            throw new SteamException(e);
        }
    }

    private static void extractLibrary(File file, String str) {
        extractLibrary(file, SteamSharedLibraryLoader.class.getResourceAsStream("/" + str));
    }

    private static void extractLibrary(File file, File file2) {
        extractLibrary(file, new FileInputStream(file2));
    }

    private static void extractLibrary(File file, InputStream inputStream) {
        try {
            if (inputStream != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                Throwable th = null;
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, read);
                        }
                    }
                    fileOutputStream.close();
                    if (0 != 0) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        fileOutputStream.close();
                    }
                    return;
                } catch (Throwable th3) {
                    if (0 != 0) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    } else {
                        fileOutputStream.close();
                    }
                    throw th3;
                }
            }
            throw new IOException("Failed to read input stream for " + file.getCanonicalPath());
        } catch (IOException e) {
            if (!file.exists()) {
                throw e;
            }
        } finally {
            inputStream.close();
        }
    }

    private static File discoverExtractLocation(String str, String str2) {
        if (SHARED_LIBRARY_EXTRACT_PATH != null) {
            File file = new File(SHARED_LIBRARY_EXTRACT_PATH, str2);
            if (canWrite(file)) {
                return file;
            }
        }
        File file2 = new File(System.getProperty("java.io.tmpdir") + "/" + str, str2);
        if (canWrite(file2)) {
            return file2;
        }
        try {
            File createTempFile = File.createTempFile(str, null);
            if (createTempFile.delete()) {
                File file3 = new File(createTempFile, str2);
                if (canWrite(file3)) {
                    return file3;
                }
            }
        } catch (IOException unused) {
        }
        File file4 = new File(System.getProperty("user.home") + "/." + str, str2);
        if (canWrite(file4)) {
            return file4;
        }
        File file5 = new File(".tmp/" + str, str2);
        if (canWrite(file5)) {
            return file5;
        }
        throw new IOException("No suitable extraction path found");
    }

    /* JADX WARN: Finally extract failed */
    private static boolean canWrite(File file) {
        File parentFile = file.getParentFile();
        if (file.exists()) {
            if (!file.canWrite() || !canExecute(file)) {
                return false;
            }
        } else if ((!parentFile.exists() && !parentFile.mkdirs()) || !parentFile.isDirectory()) {
            return false;
        }
        File file2 = new File(parentFile, UUID.randomUUID().toString());
        try {
            new FileOutputStream(file2).close();
            boolean canExecute = canExecute(file2);
            file2.delete();
            return canExecute;
        } catch (IOException unused) {
            file2.delete();
            return false;
        } catch (Throwable th) {
            file2.delete();
            throw th;
        }
    }

    private static boolean canExecute(File file) {
        try {
            if (file.canExecute()) {
                return true;
            }
            if (file.setExecutable(true)) {
                return file.canExecute();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
