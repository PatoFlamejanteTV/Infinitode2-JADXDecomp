package nonapi.io.github.classgraph.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/FileUtils.class */
public final class FileUtils {
    private static Method directByteBufferCleanerMethod;
    private static Method cleanerCleanMethod;
    private static Method attachmentMethod;
    private static Object theUnsafe;
    private static AtomicBoolean initialized = new AtomicBoolean();
    private static String currDirPath;
    public static final int MAX_BUFFER_SIZE = 2147483639;

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/FileUtils$FileAttributesGetter.class */
    public interface FileAttributesGetter {
        BasicFileAttributes get(Path path);
    }

    private FileUtils() {
    }

    public static String currDirPath() {
        if (currDirPath == null) {
            Path path = null;
            String property = System.getProperty("user.dir");
            if (property != null) {
                try {
                    path = Paths.get(property, new String[0]);
                } catch (InvalidPathException unused) {
                }
            }
            if (path == null) {
                try {
                    path = Paths.get("", new String[0]);
                } catch (InvalidPathException unused2) {
                }
            }
            currDirPath = FastPathResolver.resolve(path == null ? "" : path.toString());
        }
        return currDirPath;
    }

    public static String sanitizeEntryPath(String str, boolean z, boolean z2) {
        if (str.isEmpty()) {
            return "";
        }
        boolean z3 = false;
        int length = str.length();
        char[] cArr = new char[length];
        str.getChars(0, length, cArr, 0);
        int i = -1;
        char c = 0;
        int i2 = 0;
        int i3 = length + 1;
        while (i2 < i3) {
            char c2 = i2 == length ? (char) 0 : cArr[i2];
            char c3 = c2;
            if (c2 == '/' || c3 == '!' || c3 == 0) {
                int i4 = i2 - (i + 1);
                if ((i4 == 0 && c == c3) || ((i4 == 1 && cArr[i2 - 1] == '.') || (i4 == 2 && cArr[i2 - 2] == '.' && cArr[i2 - 1] == '.'))) {
                    z3 = true;
                }
                i = i2;
            }
            c = c3;
            i2++;
        }
        boolean z4 = cArr[0] == '/';
        boolean z5 = z4;
        boolean z6 = z4 && length > 1 && cArr[1] == '/';
        StringBuilder sb = new StringBuilder(length + 16);
        if (z3) {
            ArrayList<List> arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.add(arrayList2);
            int i5 = -1;
            int i6 = 0;
            while (i6 < length + 1) {
                char c4 = i6 == length ? (char) 0 : cArr[i6];
                char c5 = c4;
                if (c4 == '/' || c5 == '!' || c5 == 0) {
                    int i7 = i5 + 1;
                    int i8 = i6 - i7;
                    if (i8 != 0 && (i8 != 1 || cArr[i7] != '.')) {
                        if (i8 != 2 || cArr[i7] != '.' || cArr[i7 + 1] != '.') {
                            arrayList2.add(str.subSequence(i7, i7 + i8));
                        } else if (!arrayList2.isEmpty()) {
                            ArrayList arrayList3 = arrayList2;
                            arrayList3.remove(arrayList3.size() - 1);
                        }
                    }
                    if (c5 == '!' && !arrayList2.isEmpty()) {
                        arrayList2 = new ArrayList();
                        arrayList.add(arrayList2);
                    }
                    i5 = i6;
                }
                i6++;
            }
            for (List<CharSequence> list : arrayList) {
                if (!list.isEmpty()) {
                    if (sb.length() > 0) {
                        sb.append('!');
                    }
                    for (CharSequence charSequence : list) {
                        sb.append('/');
                        sb.append(charSequence);
                    }
                }
            }
            if (sb.length() == 0 && z5) {
                sb.append('/');
            }
        } else {
            sb.append(str);
        }
        if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows && z6) {
            sb.insert(0, '/');
        }
        int i9 = 0;
        if (z || !z5) {
            while (i9 < sb.length() && sb.charAt(i9) == '/') {
                i9++;
            }
        }
        if (z2) {
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '/') {
                sb.setLength(sb.length() - 1);
            }
        }
        return sb.substring(i9);
    }

    public static boolean isClassfile(String str) {
        int length = str.length();
        return length > 6 && str.regionMatches(true, length - 6, ".class", 0, 6);
    }

    public static boolean canRead(File file) {
        try {
            return file.canRead();
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean canRead(Path path) {
        try {
            return canRead(path.toFile());
        } catch (UnsupportedOperationException unused) {
            try {
                return Files.isReadable(path);
            } catch (SecurityException unused2) {
                return false;
            }
        }
    }

    public static boolean canReadAndIsFile(File file) {
        try {
            if (!file.canRead()) {
                return false;
            }
            return file.isFile();
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean canReadAndIsFile(Path path) {
        try {
            return canReadAndIsFile(path.toFile());
        } catch (UnsupportedOperationException unused) {
            try {
                if (!Files.isReadable(path)) {
                    return false;
                }
                return Files.isRegularFile(path, new LinkOption[0]);
            } catch (SecurityException unused2) {
                return false;
            }
        }
    }

    public static boolean isFile(Path path) {
        try {
            return path.toFile().isFile();
        } catch (SecurityException unused) {
            return false;
        } catch (UnsupportedOperationException unused2) {
            return Files.isRegularFile(path, new LinkOption[0]);
        }
    }

    public static void checkCanReadAndIsFile(File file) {
        try {
            if (!file.canRead()) {
                throw new FileNotFoundException("File does not exist or cannot be read: " + file);
            }
            if (!file.isFile()) {
                throw new IOException("Not a regular file: " + file);
            }
        } catch (SecurityException e) {
            throw new FileNotFoundException("File " + file + " cannot be accessed: " + e);
        }
    }

    public static void checkCanReadAndIsFile(Path path) {
        try {
            checkCanReadAndIsFile(path.toFile());
        } catch (UnsupportedOperationException unused) {
            try {
                if (!Files.isReadable(path)) {
                    throw new FileNotFoundException("Path does not exist or cannot be read: " + path);
                }
                if (!Files.isRegularFile(path, new LinkOption[0])) {
                    throw new IOException("Not a regular file: " + path);
                }
            } catch (SecurityException e) {
                throw new FileNotFoundException("Path " + path + " cannot be accessed: " + e);
            }
        }
    }

    public static boolean canReadAndIsDir(File file) {
        try {
            if (!file.canRead()) {
                return false;
            }
            return file.isDirectory();
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean canReadAndIsDir(Path path) {
        try {
            return canReadAndIsDir(path.toFile());
        } catch (UnsupportedOperationException unused) {
            try {
                if (!Files.isReadable(path)) {
                    return false;
                }
                return Files.isDirectory(path, new LinkOption[0]);
            } catch (SecurityException unused2) {
                return false;
            }
        }
    }

    public static boolean isDir(Path path) {
        try {
            return path.toFile().isDirectory();
        } catch (SecurityException unused) {
            return false;
        } catch (UnsupportedOperationException unused2) {
            return Files.isDirectory(path, new LinkOption[0]);
        }
    }

    public static void checkCanReadAndIsDir(File file) {
        try {
            if (!file.canRead()) {
                throw new FileNotFoundException("Directory does not exist or cannot be read: " + file);
            }
            if (!file.isDirectory()) {
                throw new IOException("Not a directory: " + file);
            }
        } catch (SecurityException e) {
            throw new FileNotFoundException("File " + file + " cannot be accessed: " + e);
        }
    }

    public static String getParentDirPath(String str, char c) {
        int lastIndexOf = str.lastIndexOf(c);
        if (lastIndexOf <= 0) {
            return "";
        }
        return str.substring(0, lastIndexOf);
    }

    public static String getParentDirPath(String str) {
        return getParentDirPath(str, '/');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lookupCleanMethodPrivileged() {
        if (VersionFinder.JAVA_MAJOR_VERSION < 9) {
            try {
                Method declaredMethod = Class.forName("sun.misc.Cleaner").getDeclaredMethod("clean", new Class[0]);
                cleanerCleanMethod = declaredMethod;
                declaredMethod.setAccessible(true);
                Class<?> cls = Class.forName("sun.nio.ch.DirectBuffer");
                directByteBufferCleanerMethod = cls.getDeclaredMethod("cleaner", new Class[0]);
                Method method = cls.getMethod("attachment", new Class[0]);
                attachmentMethod = method;
                method.setAccessible(true);
                return;
            } catch (LinkageError | ReflectiveOperationException unused) {
                return;
            } catch (SecurityException e) {
                throw new RuntimeException("You need to grant classgraph RuntimePermission(\"accessClassInPackage.sun.misc\") and ReflectPermission(\"suppressAccessChecks\")", e);
            }
        }
        try {
            try {
                Class<?> cls2 = Class.forName("sun.misc.Unsafe");
                Field declaredField = cls2.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                theUnsafe = declaredField.get(null);
                Method method2 = cls2.getMethod("invokeCleaner", ByteBuffer.class);
                cleanerCleanMethod = method2;
                method2.setAccessible(true);
            } catch (LinkageError | ReflectiveOperationException unused2) {
            } catch (SecurityException e2) {
                throw new RuntimeException("You need to grant classgraph RuntimePermission(\"accessClassInPackage.sun.misc\") and ReflectPermission(\"suppressAccessChecks\")", e2);
            }
        } catch (LinkageError | ReflectiveOperationException e3) {
            throw new RuntimeException("Could not get class sun.misc.Unsafe", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean closeDirectByteBufferPrivileged(ByteBuffer byteBuffer, LogNode logNode) {
        if (!byteBuffer.isDirect()) {
            return true;
        }
        try {
            if (VersionFinder.JAVA_MAJOR_VERSION < 9) {
                if (attachmentMethod == null) {
                    if (logNode != null) {
                        logNode.log("Could not unmap ByteBuffer, attachmentMethod == null");
                        return false;
                    }
                    return false;
                }
                if (attachmentMethod.invoke(byteBuffer, new Object[0]) != null) {
                    return false;
                }
                if (directByteBufferCleanerMethod == null) {
                    if (logNode != null) {
                        logNode.log("Could not unmap ByteBuffer, cleanerMethod == null");
                        return false;
                    }
                    return false;
                }
                try {
                    directByteBufferCleanerMethod.setAccessible(true);
                    Object invoke = directByteBufferCleanerMethod.invoke(byteBuffer, new Object[0]);
                    if (invoke == null) {
                        if (logNode != null) {
                            logNode.log("Could not unmap ByteBuffer, cleaner == null");
                            return false;
                        }
                        return false;
                    }
                    if (cleanerCleanMethod != null) {
                        try {
                            cleanerCleanMethod.invoke(invoke, new Object[0]);
                            return true;
                        } catch (Exception e) {
                            if (logNode != null) {
                                logNode.log("Could not unmap ByteBuffer, cleanMethod.invoke(cleaner) failed: " + e);
                                return false;
                            }
                            return false;
                        }
                    }
                    if (logNode != null) {
                        logNode.log("Could not unmap ByteBuffer, cleanMethod == null");
                        return false;
                    }
                    return false;
                } catch (Exception unused) {
                    if (logNode != null) {
                        logNode.log("Could not unmap ByteBuffer, cleanerMethod.setAccessible(true) failed");
                        return false;
                    }
                    return false;
                }
            }
            if (theUnsafe == null) {
                if (logNode != null) {
                    logNode.log("Could not unmap ByteBuffer, theUnsafe == null");
                    return false;
                }
                return false;
            }
            if (cleanerCleanMethod == null) {
                if (logNode != null) {
                    logNode.log("Could not unmap ByteBuffer, cleanMethod == null");
                    return false;
                }
                return false;
            }
            try {
                cleanerCleanMethod.invoke(theUnsafe, byteBuffer);
                return true;
            } catch (IllegalArgumentException unused2) {
                return false;
            }
        } catch (ReflectiveOperationException | SecurityException e2) {
            if (logNode != null) {
                logNode.log("Could not unmap ByteBuffer: " + e2);
                return false;
            }
            return false;
        }
    }

    public static boolean closeDirectByteBuffer(final ByteBuffer byteBuffer, ReflectionUtils reflectionUtils, final LogNode logNode) {
        if (byteBuffer != null && byteBuffer.isDirect()) {
            if (!initialized.get()) {
                try {
                    reflectionUtils.doPrivileged(new Callable<Void>() { // from class: nonapi.io.github.classgraph.utils.FileUtils.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.concurrent.Callable
                        public final Void call() {
                            FileUtils.lookupCleanMethodPrivileged();
                            return null;
                        }
                    });
                    initialized.set(true);
                } catch (Throwable th) {
                    throw new RuntimeException("Cannot get buffer cleaner method", th);
                }
            }
            try {
                return ((Boolean) reflectionUtils.doPrivileged(new Callable<Boolean>() { // from class: nonapi.io.github.classgraph.utils.FileUtils.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public final Boolean call() {
                        return Boolean.valueOf(FileUtils.closeDirectByteBufferPrivileged(byteBuffer, logNode));
                    }
                })).booleanValue();
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    public static FileAttributesGetter createCachedAttributesGetter() {
        final HashMap hashMap = new HashMap();
        return new FileAttributesGetter() { // from class: nonapi.io.github.classgraph.utils.FileUtils.3
            @Override // nonapi.io.github.classgraph.utils.FileUtils.FileAttributesGetter
            public final BasicFileAttributes get(Path path) {
                BasicFileAttributes basicFileAttributes = (BasicFileAttributes) hashMap.get(path);
                BasicFileAttributes basicFileAttributes2 = basicFileAttributes;
                if (basicFileAttributes == null) {
                    basicFileAttributes2 = FileUtils.readAttributes(path);
                    hashMap.put(path, basicFileAttributes2);
                }
                return basicFileAttributes2;
            }
        };
    }

    public static BasicFileAttributes readAttributes(final Path path) {
        try {
            return Files.readAttributes(path, BasicFileAttributes.class, new LinkOption[0]);
        } catch (IOException unused) {
            return new BasicFileAttributes() { // from class: nonapi.io.github.classgraph.utils.FileUtils.4
                @Override // java.nio.file.attribute.BasicFileAttributes
                public final FileTime lastModifiedTime() {
                    return FileTime.fromMillis(path.toFile().lastModified());
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final FileTime lastAccessTime() {
                    throw new UnsupportedOperationException();
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final FileTime creationTime() {
                    return FileTime.fromMillis(0L);
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final boolean isRegularFile() {
                    return FileUtils.isFile(path);
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final boolean isDirectory() {
                    return FileUtils.isDir(path);
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final boolean isSymbolicLink() {
                    return false;
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final boolean isOther() {
                    return (isRegularFile() || isDirectory()) ? false : true;
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final long size() {
                    return path.toFile().length();
                }

                @Override // java.nio.file.attribute.BasicFileAttributes
                public final Object fileKey() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
