package org.lwjgl.system;

import java.nio.file.Path;
import java.util.function.Supplier;

/* loaded from: infinitode-2.jar:org/lwjgl/system/LibraryResource.class */
public final class LibraryResource {
    static {
        Library.initialize();
    }

    private LibraryResource() {
    }

    public static Path load(String str, String str2) {
        return load(LibraryResource.class, str, str2);
    }

    public static Path load(Class<?> cls, String str, String str2) {
        return load(cls, str, str2, false, true);
    }

    public static Path load(Class<?> cls, String str, String str2, boolean z) {
        return load(cls, str, str2, z, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0163  */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.nio.file.Path load(java.lang.Class<?> r6, java.lang.String r7, java.lang.String r8, boolean r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.lwjgl.system.LibraryResource.load(java.lang.Class, java.lang.String, java.lang.String, boolean, boolean):java.nio.file.Path");
    }

    private static Path loadFromLibraryPath(String str, String str2, boolean z) {
        String str3 = Configuration.LIBRARY_PATH.get();
        if (str3 == null) {
            return null;
        }
        return load(str, str2, z, Configuration.LIBRARY_PATH.getProperty(), str3);
    }

    private static Path load(String str, String str2, boolean z, String str3, String str4) {
        Path findFile = Library.findFile(str4, str, str2, z);
        if (findFile != null) {
            APIUtil.apiLogMore("Loaded from " + str3 + ": " + findFile);
            return findFile;
        }
        APIUtil.apiLogMore(str2 + " not found in " + str3 + "=" + str4);
        return null;
    }

    public static Path load(Class<?> cls, String str, Configuration<String> configuration, String... strArr) {
        return load(cls, str, configuration, (Supplier<Path>) null, strArr);
    }

    public static Path load(Class<?> cls, String str, Configuration<String> configuration, Supplier<Path> supplier, String... strArr) {
        if (strArr.length == 0) {
            throw new IllegalArgumentException("No default names specified.");
        }
        String str2 = configuration.get();
        if (str2 != null) {
            return load(cls, str, str2);
        }
        if (supplier == null && strArr.length <= 1) {
            return load(cls, str, strArr[0]);
        }
        try {
            return load(cls, str, strArr[0], false, false);
        } catch (Throwable th) {
            int i = 1;
            while (i < strArr.length) {
                try {
                    return load(cls, str, strArr[i], false, supplier == null && i == strArr.length - 1);
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

    private static void printError() {
        Library.printError("[LWJGL] Failed to load a library resource. Possible solutions:\n\ta) Add the directory that contains the resource to -Djava.library.path or -Dorg.lwjgl.librarypath.\n\tb) Add the JAR that contains the resource to the classpath.");
    }
}
