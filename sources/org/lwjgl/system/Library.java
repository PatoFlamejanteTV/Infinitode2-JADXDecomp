package org.lwjgl.system;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Pattern;
import net.bytebuddy.ClassFileVersion;
import org.lwjgl.Version;

/* loaded from: infinitode-2.jar:org/lwjgl/system/Library.class */
public final class Library {
    static final String JAVA_LIBRARY_PATH = "java.library.path";
    public static final String JNI_LIBRARY_NAME = Configuration.LIBRARY_NAME.get(Platform.mapLibraryNameBundled("lwjgl"));
    private static final Pattern PATH_SEPARATOR = Pattern.compile(File.pathSeparator);
    private static final Pattern NATIVES_JAR = Pattern.compile("/[\\w-]+?-natives-\\w+.jar!/");

    static {
        if (Checks.DEBUG) {
            APIUtil.DEBUG_STREAM.print("[LWJGL] Version: " + Version.getVersion() + "\n\t OS: " + System.getProperty("os.name") + " v" + System.getProperty("os.version") + "\n\tJRE: " + Platform.get().getName() + SequenceUtils.SPACE + System.getProperty("os.arch") + SequenceUtils.SPACE + System.getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION) + "\n\tJVM: " + System.getProperty("java.vm.name") + " v" + System.getProperty("java.vm.version") + " by " + System.getProperty("java.vm.vendor") + SequenceUtils.EOL);
        }
        loadSystem("org.lwjgl", JNI_LIBRARY_NAME);
    }

    private Library() {
    }

    public static void initialize() {
    }

    public static void loadSystem(String str, String str2) {
        loadSystem(System::load, System::loadLibrary, Library.class, str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016b  */
    /* JADX WARN: Type inference failed for: r15v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void loadSystem(java.util.function.Consumer<java.lang.String> r8, java.util.function.Consumer<java.lang.String> r9, java.lang.Class<?> r10, java.lang.String r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.lwjgl.system.Library.loadSystem(java.util.function.Consumer, java.util.function.Consumer, java.lang.Class, java.lang.String, java.lang.String):void");
    }

    private static boolean loadSystemFromLibraryPath(Consumer<String> consumer, Class<?> cls, String str, String str2, boolean z) {
        String str3 = Configuration.LIBRARY_PATH.get();
        return str3 != null && loadSystem(consumer, cls, str, str2, z, Configuration.LIBRARY_PATH.getProperty(), str3);
    }

    private static boolean loadSystem(Consumer<String> consumer, Class<?> cls, String str, String str2, boolean z, String str3, String str4) {
        Path findFile = findFile(str4, str, str2, z);
        if (findFile == null) {
            APIUtil.apiLogMore(str2 + " not found in " + str3 + "=" + str4);
            return false;
        }
        consumer.accept(findFile.toAbsolutePath().toString());
        APIUtil.apiLogMore("Loaded from " + str3 + ": " + findFile);
        if (z) {
            checkHash(cls, findFile, str, str2);
            return true;
        }
        return true;
    }

    public static SharedLibrary loadNative(String str, String str2) {
        return loadNative(Library.class, str, str2);
    }

    public static SharedLibrary loadNative(Class<?> cls, String str, String str2) {
        return loadNative(cls, str, str2, false);
    }

    public static SharedLibrary loadNative(Class<?> cls, String str, String str2, boolean z) {
        return loadNative(cls, str, str2, z, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x016c  */
    /* JADX WARN: Type inference failed for: r16v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.lwjgl.system.SharedLibrary loadNative(java.lang.Class<?> r7, java.lang.String r8, java.lang.String r9, boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 580
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.lwjgl.system.Library.loadNative(java.lang.Class, java.lang.String, java.lang.String, boolean, boolean):org.lwjgl.system.SharedLibrary");
    }

    private static SharedLibrary loadNativeFromSystem(String str) {
        SharedLibrary sharedLibrary;
        try {
            SharedLibrary apiCreateLibrary = APIUtil.apiCreateLibrary(str);
            sharedLibrary = apiCreateLibrary;
            String path = apiCreateLibrary.getPath();
            APIUtil.apiLogMore(path == null ? "Loaded from system paths" : "Loaded from system paths: " + path);
        } catch (UnsatisfiedLinkError unused) {
            sharedLibrary = null;
            APIUtil.apiLogMore(str + " not found in system paths");
        }
        return sharedLibrary;
    }

    private static SharedLibrary loadNativeFromLibraryPath(Class<?> cls, String str, String str2, boolean z) {
        String str3 = Configuration.LIBRARY_PATH.get();
        if (str3 == null) {
            return null;
        }
        return loadNative(cls, str, str2, z, Configuration.LIBRARY_PATH.getProperty(), str3);
    }

    private static SharedLibrary loadNative(Class<?> cls, String str, String str2, boolean z, String str3, String str4) {
        Path findFile = findFile(str4, str, str2, z);
        if (findFile == null) {
            APIUtil.apiLogMore(str2 + " not found in " + str3 + "=" + str4);
            return null;
        }
        SharedLibrary apiCreateLibrary = APIUtil.apiCreateLibrary(findFile.toAbsolutePath().toString());
        APIUtil.apiLogMore("Loaded from " + str3 + ": " + findFile);
        if (z) {
            checkHash(cls, findFile, str, str2);
        }
        return apiCreateLibrary;
    }

    public static SharedLibrary loadNative(Class<?> cls, String str, Configuration<String> configuration, String... strArr) {
        return loadNative(cls, str, configuration, (Supplier<SharedLibrary>) null, strArr);
    }

    public static SharedLibrary loadNative(Class<?> cls, String str, Configuration<String> configuration, Supplier<SharedLibrary> supplier, String... strArr) {
        String str2;
        if (strArr.length == 0) {
            throw new IllegalArgumentException("No default names specified.");
        }
        if (configuration != null && (str2 = configuration.get()) != null) {
            return loadNative(cls, str, str2);
        }
        if (supplier == null && strArr.length <= 1) {
            return loadNative(cls, str, strArr[0]);
        }
        try {
            return loadNative(cls, str, strArr[0], false, false);
        } catch (Throwable th) {
            int i = 1;
            while (i < strArr.length) {
                try {
                    return loadNative(cls, str, strArr[i], false, supplier == null && i == strArr.length - 1);
                } catch (Throwable unused) {
                    i++;
                }
            }
            if (supplier != null) {
                return supplier.get();
            }
            throw th;
        }
    }

    private static String getBundledPath(String str, String str2) {
        return Platform.mapLibraryPathBundled(str.replace('.', '/') + "/" + str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static URL findResource(Class<?> cls, String str, String str2, boolean z) {
        URL url = null;
        if (z) {
            String bundledPath = getBundledPath(str, str2);
            if (!bundledPath.equals(str2)) {
                url = cls.getClassLoader().getResource(bundledPath);
            }
        }
        return url == null ? cls.getClassLoader().getResource(str2) : url;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getRegularFilePath(URL url) {
        if (url.getProtocol().equals("file")) {
            try {
                Path path = Paths.get(url.toURI());
                if (path.isAbsolute() && Files.isReadable(path)) {
                    return path.toString();
                }
                return null;
            } catch (URISyntaxException unused) {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Path findFile(String str, String str2, String str3, boolean z) {
        Path findFile;
        if (z) {
            String bundledPath = getBundledPath(str2, str3);
            if (!bundledPath.equals(str3) && (findFile = findFile(str, bundledPath)) != null) {
                return findFile;
            }
        }
        return findFile(str, str3);
    }

    private static Path findFile(String str, String str2) {
        for (String str3 : PATH_SEPARATOR.split(str)) {
            Path path = Paths.get(str3, str2);
            if (Files.isReadable(path)) {
                return path;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2 */
    private static void detectPlatformMismatch(Class<?> cls, String str) {
        String value;
        if (!str.startsWith("org.lwjgl")) {
            return;
        }
        String str2 = str.equals("org.lwjgl") ? "lwjgl" : "lwjgl-" + str.substring(10);
        ArrayList arrayList = new ArrayList(8);
        try {
            Enumeration<URL> resources = cls.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                InputStream openStream = resources.nextElement().openStream();
                boolean z = false;
                ?? r10 = 0;
                try {
                    try {
                        Attributes mainAttributes = new Manifest(openStream).getMainAttributes();
                        z = str2.equals(mainAttributes.getValue("Implementation-Title"));
                        if (z && (value = mainAttributes.getValue("LWJGL-Platform")) != null) {
                            arrayList.add(value);
                        }
                        if (openStream != null) {
                            if (0 != 0) {
                                try {
                                    openStream.close();
                                } catch (Throwable th) {
                                    r10.addSuppressed(th);
                                }
                            } else {
                                openStream.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th2) {
                    r10 = z;
                    throw th2;
                }
            }
        } catch (IOException unused) {
        }
        if (!arrayList.isEmpty()) {
            APIUtil.DEBUG_STREAM.print("[LWJGL] Platform/architecture mismatch detected for module: " + str + "\n\tJVM platform:\t\t" + Platform.get().getName() + SequenceUtils.SPACE + System.getProperty("os.arch") + SequenceUtils.SPACE + System.getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION) + "\n\t\t" + System.getProperty("java.vm.name") + " v" + System.getProperty("java.vm.version") + " by " + System.getProperty("java.vm.vendor") + "\n\tPlatform" + (arrayList.size() == 1 ? "" : "s") + " available on classpath:\n\t\t" + String.join("\n\t\t", arrayList) + SequenceUtils.EOL);
        }
    }

    private static void printError(boolean z) {
        printError("[LWJGL] Failed to load a library. Possible solutions:\n" + (z ? "\ta) Add the directory that contains the shared library to -Djava.library.path or -Dorg.lwjgl.librarypath.\n\tb) Add the JAR that contains the shared library to the classpath." : "\ta) Install the library or the driver that provides the library.\n\tb) Ensure that the library is accessible from the system library paths."));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void printError(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(SequenceUtils.EOL);
        if (!Checks.DEBUG) {
            sb.append("[LWJGL] Enable debug mode with -Dorg.lwjgl.util.Debug=true for better diagnostics.\n");
            if (!Configuration.DEBUG_LOADER.get(Boolean.FALSE).booleanValue()) {
                sb.append("[LWJGL] Enable the SharedLibraryLoader debug mode with -Dorg.lwjgl.util.DebugLoader=true for better diagnostics.\n");
            }
        }
        APIUtil.DEBUG_STREAM.print(sb);
    }

    private static void checkHash(Class<?> cls, Path path, String str, String str2) {
        byte[] sha1;
        if (!Checks.CHECKS) {
            return;
        }
        try {
            URL url = null;
            URL url2 = null;
            Enumeration<URL> resources = cls.getClassLoader().getResources("META-INF/" + getBundledPath(str, str2) + ".sha1");
            while (resources.hasMoreElements()) {
                URL nextElement = resources.nextElement();
                if (NATIVES_JAR.matcher(nextElement.toExternalForm()).find()) {
                    url2 = nextElement;
                } else {
                    url = nextElement;
                }
            }
            if (url == null) {
                return;
            }
            byte[] sha12 = getSHA1(url);
            if (Checks.DEBUG || url2 == null) {
                sha1 = getSHA1(path);
            } else {
                sha1 = getSHA1(url2);
            }
            if (!Arrays.equals(sha12, sha1)) {
                APIUtil.DEBUG_STREAM.println("[LWJGL] [ERROR] Incompatible Java and native library versions detected.\nPossible reasons:\n\ta) -Djava.library.path is set to a folder containing shared libraries of an older LWJGL version.\n\tb) The classpath contains jar files of an older LWJGL version.\nPossible solutions:\n\ta) Make sure to not set -Djava.library.path (it is not needed for developing with LWJGL 3) or make\n\t   sure the folder it points to contains the shared libraries of the correct LWJGL version.\n\tb) Check the classpath and make sure to only have jar files of the same LWJGL version in it.");
            }
        } catch (Throwable th) {
            if (Checks.DEBUG) {
                APIUtil.apiLog("Failed to verify native library.");
                th.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v21 */
    private static byte[] getSHA1(URL url) {
        byte[] bArr = new byte[20];
        InputStream openStream = url.openStream();
        Throwable th = null;
        try {
            int i = 0;
            while (true) {
                ?? r0 = i;
                if (r0 >= 20) {
                    break;
                }
                try {
                    r0 = bArr;
                    r0[i] = (byte) ((Character.digit(openStream.read(), 16) << 4) | Character.digit(openStream.read(), 16));
                    i++;
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return bArr;
        } finally {
            if (openStream != null) {
                if (0 != 0) {
                    try {
                        openStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    openStream.close();
                }
            }
        }
    }

    private static byte[] getSHA1(Path path) {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        InputStream newInputStream = Files.newInputStream(path, new OpenOption[0]);
        int i = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        try {
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    i = newInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, i);
                }
                return messageDigest.digest();
            } catch (Throwable th) {
                throw th;
            }
        } finally {
            if (newInputStream != null) {
                if (0 != 0) {
                    try {
                        newInputStream.close();
                    } catch (Throwable th2) {
                        (b2 == true ? 1 : 0).addSuppressed(th2);
                    }
                } else {
                    newInputStream.close();
                }
            }
        }
    }
}
