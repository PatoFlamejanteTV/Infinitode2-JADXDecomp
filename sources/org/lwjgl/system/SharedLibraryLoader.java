package org.lwjgl.system;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import org.lwjgl.Version;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/lwjgl/system/SharedLibraryLoader.class */
public final class SharedLibraryLoader {
    private static Path extractPath;
    private static boolean checkedLoad;
    private static final Lock EXTRACT_PATH_LOCK = new ReentrantLock();
    private static HashSet<Path> extractPaths = new HashSet<>(4);

    private SharedLibraryLoader() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FileChannel load(String str, String str2, URL url, Consumer<String> consumer) {
        Path path;
        try {
            EXTRACT_PATH_LOCK.lock();
            try {
                if (extractPath != null) {
                    path = extractPath.resolve(str2);
                } else {
                    Path extractPath2 = getExtractPath(str2, url, consumer);
                    path = extractPath2;
                    Path parent = extractPath2.getParent();
                    if (checkedLoad) {
                        extractPath = parent;
                    }
                    initExtractPath(parent);
                }
                EXTRACT_PATH_LOCK.unlock();
                return extract(path, url);
            } catch (Throwable th) {
                EXTRACT_PATH_LOCK.unlock();
                throw th;
            }
        } catch (Exception e) {
            throw new RuntimeException("\tFailed to extract " + str + " library", e);
        }
    }

    private static void initExtractPath(Path path) {
        if (extractPaths.contains(path)) {
            return;
        }
        extractPaths.add(path);
        String path2 = path.toAbsolutePath().toString();
        String str = Configuration.LIBRARY_PATH.get();
        if (str != null && !str.isEmpty()) {
            path2 = path2 + File.pathSeparator + str;
        }
        System.setProperty(Configuration.LIBRARY_PATH.getProperty(), path2);
        Configuration.LIBRARY_PATH.set(path2);
    }

    private static Path getExtractPath(String str, URL url, Consumer<String> consumer) {
        String str2 = Configuration.SHARED_LIBRARY_EXTRACT_PATH.get();
        if (str2 != null) {
            Path path = Paths.get(str2, new String[0]);
            Path resolve = path.resolve(str);
            if (!canWrite(path, resolve, url, consumer)) {
                APIUtil.apiLogMore("The path " + str2 + " is not accessible. Trying other paths.");
            } else {
                return resolve;
            }
        }
        String replace = Version.getVersion().replace(' ', '-');
        String lowerCase = Platform.getArchitecture().name().toLowerCase();
        Path path2 = Paths.get(System.getProperty("java.io.tmpdir"), new String[0]);
        Path resolve2 = path2.resolve(Paths.get(Configuration.SHARED_LIBRARY_EXTRACT_DIRECTORY.get("lwjgl_" + System.getProperty("user.name").trim()), replace, lowerCase, str));
        if (canWrite(path2, resolve2, url, consumer)) {
            return resolve2;
        }
        Path path3 = Paths.get("." + Configuration.SHARED_LIBRARY_EXTRACT_DIRECTORY.get("lwjgl"), replace, lowerCase, str);
        Path absolutePath = Paths.get("", new String[0]).toAbsolutePath();
        Path resolve3 = absolutePath.resolve(path3);
        if (canWrite(absolutePath, resolve3, url, consumer)) {
            return resolve3;
        }
        Path path4 = Paths.get(System.getProperty("user.home"), new String[0]);
        Path resolve4 = path4.resolve(path3);
        if (canWrite(path4, resolve4, url, consumer)) {
            return resolve4;
        }
        if (Platform.get() == Platform.WINDOWS) {
            String str3 = System.getenv("SystemRoot");
            if (str3 != null) {
                Path path5 = Paths.get(str3, "Temp");
                Path resolve5 = path5.resolve(path3);
                if (canWrite(path5, resolve5, url, consumer)) {
                    return resolve5;
                }
            }
            String str4 = System.getenv("SystemDrive");
            if (str4 != null) {
                Path path6 = Paths.get(str4 + "/", new String[0]);
                Path resolve6 = path6.resolve(Paths.get("Temp", new String[0]).resolve(path3));
                if (canWrite(path6, resolve6, url, consumer)) {
                    return resolve6;
                }
            }
        }
        try {
            Path createTempDirectory = Files.createTempDirectory("lwjgl", new FileAttribute[0]);
            Path parent = createTempDirectory.getParent();
            Path resolve7 = createTempDirectory.resolve(str);
            if (canWrite(parent, resolve7, url, consumer)) {
                return resolve7;
            }
        } catch (IOException unused) {
        }
        throw new RuntimeException("Failed to find an appropriate directory to extract the native library");
    }

    private static FileChannel extract(Path path, URL url) {
        if (Files.exists(path, new LinkOption[0])) {
            InputStream openStream = url.openStream();
            Throwable th = null;
            try {
                InputStream newInputStream = Files.newInputStream(path, new OpenOption[0]);
                try {
                    try {
                        if (crc(openStream) == crc(newInputStream)) {
                            if (Configuration.DEBUG_LOADER.get(Boolean.FALSE).booleanValue()) {
                                APIUtil.apiLogMore("Found at: " + path);
                            }
                            FileChannel lock = lock(path);
                            if (newInputStream != null) {
                                if (r12 != null) {
                                    try {
                                        newInputStream.close();
                                    } catch (Throwable th2) {
                                        r12.addSuppressed(th2);
                                    }
                                } else {
                                    newInputStream.close();
                                }
                            }
                            return lock;
                        }
                        if (newInputStream != null) {
                            if (r12 != null) {
                                try {
                                    newInputStream.close();
                                } catch (Throwable th3) {
                                    r12.addSuppressed(th3);
                                }
                            } else {
                                newInputStream.close();
                            }
                        }
                        if (openStream != null) {
                            if (0 != 0) {
                                try {
                                    openStream.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            } else {
                                openStream.close();
                            }
                        }
                    } catch (Throwable th5) {
                        if (newInputStream != null) {
                            if (r12 != null) {
                                try {
                                    newInputStream.close();
                                } catch (Throwable th6) {
                                    r12.addSuppressed(th6);
                                }
                            } else {
                                newInputStream.close();
                            }
                        }
                        throw th5;
                    }
                } finally {
                    r12 = null;
                }
            } finally {
                if (openStream != null) {
                    if (0 != 0) {
                        try {
                            openStream.close();
                        } catch (Throwable th7) {
                            th.addSuppressed(th7);
                        }
                    } else {
                        openStream.close();
                    }
                }
            }
        }
        APIUtil.apiLogMore("Extracting: " + url.getPath());
        if (extractPath == null) {
            APIUtil.apiLogMore("        to: " + path);
        }
        Files.createDirectories(path.getParent(), new FileAttribute[0]);
        InputStream openStream2 = url.openStream();
        try {
            try {
                Files.copy(openStream2, path, StandardCopyOption.REPLACE_EXISTING);
                if (openStream2 != null) {
                    if (r10 != null) {
                        try {
                            openStream2.close();
                        } catch (Throwable th8) {
                            r10.addSuppressed(th8);
                        }
                    } else {
                        openStream2.close();
                    }
                }
                return lock(path);
            } finally {
                r10 = null;
            }
        } catch (Throwable th9) {
            if (openStream2 != null) {
                if (r10 != null) {
                    try {
                        openStream2.close();
                    } catch (Throwable th10) {
                        r10.addSuppressed(th10);
                    }
                } else {
                    openStream2.close();
                }
            }
            throw th9;
        }
    }

    private static FileChannel lock(Path path) {
        try {
            FileChannel open = FileChannel.open(path, new OpenOption[0]);
            if (open.tryLock(0L, Long.MAX_VALUE, true) == null) {
                if (Configuration.DEBUG_LOADER.get(Boolean.FALSE).booleanValue()) {
                    APIUtil.apiLogMore("File is locked by another process, waiting...");
                }
                open.lock(0L, Long.MAX_VALUE, true);
            }
            return open;
        } catch (Exception e) {
            throw new RuntimeException("Failed to lock file.", e);
        }
    }

    private static long crc(InputStream inputStream) {
        CRC32 crc32 = new CRC32();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                crc32.update(bArr, 0, read);
            } else {
                return crc32.getValue();
            }
        }
    }

    private static boolean canWrite(Path path, Path path2, URL url, Consumer<String> consumer) {
        Path path3;
        if (Files.exists(path2, new LinkOption[0])) {
            if (!Files.isWritable(path2)) {
                return false;
            }
            path3 = path2.getParent().resolve(".lwjgl.test");
        } else {
            try {
                Files.createDirectories(path2.getParent(), new FileAttribute[0]);
                path3 = path2;
            } catch (IOException unused) {
                return false;
            }
        }
        try {
            Files.write(path3, new byte[0], new OpenOption[0]);
            Files.delete(path3);
            if (consumer != null) {
                FileChannel extract = extract(path2, url);
                Throwable th = null;
                try {
                    consumer.accept(path2.toAbsolutePath().toString());
                    if (extract != null) {
                        if (0 != 0) {
                            try {
                                extract.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            extract.close();
                        }
                    }
                    checkedLoad = true;
                    return true;
                } finally {
                }
            }
            return true;
        } catch (Throwable unused2) {
            if (path2 == path3) {
                canWriteCleanup(path, path2);
                return false;
            }
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2 */
    private static void canWriteCleanup(Path path, Path path2) {
        Path parent;
        try {
            Files.deleteIfExists(path2);
        } catch (IOException unused) {
            return;
        }
        for (parent = path2.getParent(); !Files.isSameFile(parent, path); parent = parent.getParent()) {
            Stream<Path> list = Files.list(parent);
            boolean z = false;
            ?? r6 = 0;
            try {
                try {
                    z = list.findAny().isPresent();
                    if (z) {
                        if (list != null) {
                            if (0 != 0) {
                                try {
                                    list.close();
                                    return;
                                } catch (Throwable th) {
                                    r6.addSuppressed(th);
                                    return;
                                }
                            }
                            list.close();
                            return;
                        }
                        return;
                    }
                    if (list != null) {
                        if (0 != 0) {
                            try {
                                list.close();
                            } catch (Throwable th2) {
                                r6.addSuppressed(th2);
                            }
                        } else {
                            list.close();
                        }
                    }
                    Files.delete(parent);
                } finally {
                }
            } catch (Throwable th3) {
                r6 = z;
                throw th3;
            }
            return;
        }
    }
}
