package io.github.classgraph;

import io.github.classgraph.Scanner;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import net.bytebuddy.build.Plugin;
import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
import nonapi.io.github.classgraph.concurrency.WorkQueue;
import nonapi.io.github.classgraph.fastzipfilereader.LogicalZipFile;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.fileslice.PathSlice;
import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.LogNode;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:io/github/classgraph/ClasspathElementDir.class */
public class ClasspathElementDir extends ClasspathElement {
    private final Path classpathEltPath;
    private final Set<Path> scannedCanonicalPaths;
    private final NestedJarHandler nestedJarHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClasspathElementDir(Scanner.ClasspathEntryWorkUnit classpathEntryWorkUnit, NestedJarHandler nestedJarHandler, ScanSpec scanSpec) {
        super(classpathEntryWorkUnit, scanSpec);
        this.scannedCanonicalPaths = new HashSet();
        this.classpathEltPath = (Path) classpathEntryWorkUnit.classpathEntryObj;
        this.nestedJarHandler = nestedJarHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    @Override // io.github.classgraph.ClasspathElement
    public void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, LogNode logNode) {
        if (!this.scanSpec.scanDirs) {
            if (logNode != null) {
                log(this.classpathElementIdx, "Skipping classpath element, since dir scanning is disabled: " + this.classpathEltPath, logNode);
            }
            this.skipClasspathElement = true;
            return;
        }
        try {
            int i = 0;
            for (String str : ClassLoaderHandlerRegistry.AUTOMATIC_LIB_DIR_PREFIXES) {
                Path resolve = this.classpathEltPath.resolve(str);
                if (FileUtils.canReadAndIsDir(resolve)) {
                    try {
                        DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(resolve, new DirectoryStream.Filter<Path>() { // from class: io.github.classgraph.ClasspathElementDir.1
                            @Override // java.nio.file.DirectoryStream.Filter
                            public boolean accept(Path path) {
                                return path.toString().toLowerCase().endsWith(".jar") && Files.isRegularFile(path, new LinkOption[0]);
                            }
                        });
                        boolean z = false;
                        boolean z2 = 0;
                        try {
                            try {
                                Iterator<Path> it = newDirectoryStream.iterator();
                                while (true) {
                                    z = it.hasNext();
                                    if (!z) {
                                        break;
                                    }
                                    Path next = it.next();
                                    if (logNode != null) {
                                        log(this.classpathElementIdx, "Found lib jar: " + next, logNode);
                                    }
                                    int i2 = i;
                                    i++;
                                    workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(next, getClassLoader(), this, i2, ""));
                                }
                                if (newDirectoryStream != null) {
                                    if (0 != 0) {
                                        try {
                                            newDirectoryStream.close();
                                        } catch (Throwable th) {
                                            z2.addSuppressed(th);
                                        }
                                    } else {
                                        newDirectoryStream.close();
                                    }
                                }
                            } catch (Throwable th2) {
                                if (newDirectoryStream != null) {
                                    if (z2) {
                                        try {
                                            newDirectoryStream.close();
                                        } catch (Throwable th3) {
                                            z2.addSuppressed(th3);
                                        }
                                    } else {
                                        newDirectoryStream.close();
                                    }
                                }
                                throw th2;
                            }
                        } catch (Throwable th4) {
                            z2 = z;
                            throw th4;
                        }
                    } catch (IOException unused) {
                    }
                }
            }
            if (this.packageRootPrefix.isEmpty()) {
                for (String str2 : ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES) {
                    Path resolve2 = this.classpathEltPath.resolve(str2);
                    if (FileUtils.canReadAndIsDir(resolve2)) {
                        if (logNode != null) {
                            log(this.classpathElementIdx, "Found package root: " + str2, logNode);
                        }
                        int i3 = i;
                        i++;
                        workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(resolve2, getClassLoader(), this, i3, str2));
                    }
                }
            }
        } catch (SecurityException unused2) {
            if (logNode != null) {
                log(this.classpathElementIdx, "Skipping classpath element, since dir cannot be accessed: " + this.classpathEltPath, logNode);
            }
            this.skipClasspathElement = true;
        }
    }

    private Resource newResource(final Path path, final BasicFileAttributes basicFileAttributes) {
        return new Resource(this, basicFileAttributes == null ? -2L : basicFileAttributes.size()) { // from class: io.github.classgraph.ClasspathElementDir.2
            private PathSlice pathSlice;
            private final AtomicBoolean isOpen = new AtomicBoolean();

            @Override // io.github.classgraph.Resource
            public long getLength() {
                if (this.length == -2) {
                    try {
                        this.length = Files.size(path);
                    } catch (IOException | SecurityException unused) {
                        this.length = -1L;
                    }
                }
                return this.length;
            }

            @Override // io.github.classgraph.Resource
            public String getPath() {
                String resolve = FastPathResolver.resolve(ClasspathElementDir.this.classpathEltPath.relativize(path).toString());
                while (true) {
                    String str = resolve;
                    if (str.startsWith("/")) {
                        resolve = str.substring(1);
                    } else {
                        return str;
                    }
                }
            }

            @Override // io.github.classgraph.Resource
            public String getPathRelativeToClasspathElement() {
                return ClasspathElementDir.this.packageRootPrefix.isEmpty() ? getPath() : ClasspathElementDir.this.packageRootPrefix + getPath();
            }

            @Override // io.github.classgraph.Resource
            public long getLastModified() {
                try {
                    return basicFileAttributes == null ? path.toFile().lastModified() : basicFileAttributes.lastModifiedTime().toMillis();
                } catch (UnsupportedOperationException unused) {
                    return 0L;
                }
            }

            @Override // io.github.classgraph.Resource
            public Set<PosixFilePermission> getPosixFilePermissions() {
                Set<PosixFilePermission> set = null;
                try {
                    if (basicFileAttributes instanceof PosixFileAttributes) {
                        set = ((PosixFileAttributes) basicFileAttributes).permissions();
                    } else {
                        set = ((PosixFileAttributes) Files.readAttributes(path, PosixFileAttributes.class, new LinkOption[0])).permissions();
                    }
                } catch (IOException | SecurityException | UnsupportedOperationException unused) {
                }
                return set;
            }

            @Override // io.github.classgraph.Resource
            public ByteBuffer read() {
                checkSkipState();
                openAndCreateSlice();
                this.byteBuffer = this.pathSlice.read();
                return this.byteBuffer;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // io.github.classgraph.Resource
            public ClassfileReader openClassfile() {
                checkSkipState();
                openAndCreateSlice();
                return new ClassfileReader(this.pathSlice, this);
            }

            @Override // io.github.classgraph.Resource
            public InputStream open() {
                checkSkipState();
                openAndCreateSlice();
                this.inputStream = this.pathSlice.open(this);
                return this.inputStream;
            }

            @Override // io.github.classgraph.Resource
            public byte[] load() {
                checkSkipState();
                try {
                    openAndCreateSlice();
                    return this.pathSlice.load();
                } finally {
                    close();
                }
            }

            @Override // io.github.classgraph.Resource, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                if (this.isOpen.getAndSet(false)) {
                    if (this.byteBuffer != null) {
                        this.byteBuffer = null;
                    }
                    if (this.pathSlice != null) {
                        this.pathSlice.close();
                        ClasspathElementDir.this.nestedJarHandler.markSliceAsClosed(this.pathSlice);
                        this.pathSlice = null;
                    }
                    super.close();
                }
            }

            private void checkSkipState() {
                if (ClasspathElementDir.this.skipClasspathElement) {
                    throw new IOException("Parent directory could not be opened");
                }
            }

            private void openAndCreateSlice() {
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                this.pathSlice = new PathSlice(path, false, 0L, ClasspathElementDir.this.nestedJarHandler, false);
                this.length = this.pathSlice.sliceLength;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public Resource getResource(String str) {
        Path resolve = this.classpathEltPath.resolve(str);
        if (FileUtils.canReadAndIsFile(resolve)) {
            return newResource(resolve, null);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v2 */
    private void scanPathRecursively(Path path, LogNode logNode) {
        String str;
        LogNode log;
        try {
            Path realPath = path.toRealPath(new LinkOption[0]);
            if (!this.scannedCanonicalPaths.add(realPath)) {
                if (logNode != null) {
                    logNode.log("Reached symlink cycle, stopping recursion: " + path);
                    return;
                }
                return;
            }
            String resolve = FastPathResolver.resolve(this.classpathEltPath.relativize(path).toString());
            while (true) {
                str = resolve;
                if (!str.startsWith("/")) {
                    break;
                } else {
                    resolve = str.substring(1);
                }
            }
            if (!str.endsWith("/")) {
                str = str + "/";
            }
            boolean equals = str.equals("/");
            if (this.nestedClasspathRootPrefixes != null && this.nestedClasspathRootPrefixes.contains(str)) {
                if (logNode != null) {
                    logNode.log("Reached nested classpath root, stopping recursion to avoid duplicate scanning: " + str);
                    return;
                }
                return;
            }
            if (!this.scanSpec.enableMultiReleaseVersions && str.startsWith(LogicalZipFile.MULTI_RELEASE_PATH_PREFIX)) {
                if (logNode != null) {
                    logNode.log("Found unexpected nested versioned entry in directory classpath element -- skipping: " + str);
                    return;
                }
                return;
            }
            if (!checkResourcePathAcceptReject(str, logNode)) {
                return;
            }
            ScanSpec.ScanSpecPathMatch dirAcceptMatchStatus = this.scanSpec.dirAcceptMatchStatus(str);
            if (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX) {
                if (logNode != null) {
                    logNode.log("Reached rejected directory, stopping recursive scan: " + str);
                    return;
                }
                return;
            }
            if (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.NOT_WITHIN_ACCEPTED_PATH) {
                return;
            }
            if (logNode == null) {
                log = null;
            } else {
                log = logNode.log("1:" + realPath, "Scanning Path: " + FastPathResolver.resolve(path.toString()) + (path.equals(realPath) ? "" : " ; canonical path: " + FastPathResolver.resolve(realPath.toString())));
            }
            LogNode logNode2 = log;
            ArrayList<Path> arrayList = new ArrayList();
            try {
                DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path);
                boolean z = false;
                ?? r16 = 0;
                try {
                    try {
                        Iterator<Path> it = newDirectoryStream.iterator();
                        while (true) {
                            z = it.hasNext();
                            if (!z) {
                                break;
                            } else {
                                arrayList.add(it.next());
                            }
                        }
                        if (newDirectoryStream != null) {
                            if (0 != 0) {
                                try {
                                    newDirectoryStream.close();
                                } catch (Throwable th) {
                                    r16.addSuppressed(th);
                                }
                            } else {
                                newDirectoryStream.close();
                            }
                        }
                        Collections.sort(arrayList);
                        FileUtils.FileAttributesGetter createCachedAttributesGetter = FileUtils.createCachedAttributesGetter();
                        boolean z2 = VersionFinder.JAVA_MAJOR_VERSION >= 9 && getModuleName() != null;
                        if (dirAcceptMatchStatus != ScanSpec.ScanSpecPathMatch.ANCESTOR_OF_ACCEPTED_PATH) {
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                Path path2 = (Path) it2.next();
                                BasicFileAttributes basicFileAttributes = createCachedAttributesGetter.get(path2);
                                if (basicFileAttributes.isRegularFile()) {
                                    it2.remove();
                                    Path relativize = this.classpathEltPath.relativize(path2);
                                    String resolve2 = FastPathResolver.resolve(relativize.toString());
                                    if (!z2 || !equals || !resolve2.endsWith(".class") || resolve2.equals(Plugin.Engine.MODULE_INFO)) {
                                        if (!checkResourcePathAcceptReject(resolve2, logNode2)) {
                                            return;
                                        }
                                        if (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX || dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_PATH || (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE && this.scanSpec.classfileIsSpecificallyAccepted(resolve2))) {
                                            addAcceptedResource(newResource(path2, basicFileAttributes), dirAcceptMatchStatus, false, logNode2);
                                            try {
                                                this.fileToLastModified.put(path2.toFile(), Long.valueOf(basicFileAttributes.lastModifiedTime().toMillis()));
                                            } catch (UnsupportedOperationException unused) {
                                            }
                                        } else if (logNode2 != null) {
                                            logNode2.log("Skipping non-accepted file: " + relativize);
                                        }
                                    }
                                }
                            }
                        } else if (this.scanSpec.enableClassInfo && str.equals("/")) {
                            Iterator it3 = arrayList.iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    break;
                                }
                                Path path3 = (Path) it3.next();
                                if (path3.getFileName().toString().equals(Plugin.Engine.MODULE_INFO)) {
                                    BasicFileAttributes basicFileAttributes2 = createCachedAttributesGetter.get(path3);
                                    if (basicFileAttributes2.isRegularFile()) {
                                        it3.remove();
                                        addAcceptedResource(newResource(path3, basicFileAttributes2), dirAcceptMatchStatus, true, logNode2);
                                        try {
                                            this.fileToLastModified.put(path3.toFile(), Long.valueOf(basicFileAttributes2.lastModifiedTime().toMillis()));
                                            break;
                                        } catch (UnsupportedOperationException unused2) {
                                        }
                                    }
                                }
                            }
                        }
                        for (Path path4 : arrayList) {
                            try {
                                if (createCachedAttributesGetter.get(path4).isDirectory()) {
                                    scanPathRecursively(path4, logNode2);
                                }
                            } catch (SecurityException e) {
                                if (logNode2 != null) {
                                    logNode2.log("Could not read sub-directory " + path4 + " : " + e.getMessage());
                                }
                            }
                        }
                        if (logNode2 != null) {
                            logNode2.addElapsedTime();
                        }
                        try {
                            File file = path.toFile();
                            this.fileToLastModified.put(file, Long.valueOf(file.lastModified()));
                        } catch (UnsupportedOperationException unused3) {
                        }
                    } finally {
                    }
                } catch (Throwable th2) {
                    r16 = z;
                    throw th2;
                }
            } catch (IOException | SecurityException e2) {
                if (logNode != null) {
                    logNode.log("Could not read directory " + path + " : " + e2.getMessage());
                }
            }
        } catch (IOException | SecurityException e3) {
            if (logNode != null) {
                logNode.log("Could not canonicalize path: " + path, e3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public void scanPaths(LogNode logNode) {
        if (!checkResourcePathAcceptReject(this.classpathEltPath.toString(), logNode)) {
            this.skipClasspathElement = true;
        }
        if (this.skipClasspathElement) {
            return;
        }
        if (this.scanned.getAndSet(true)) {
            throw new IllegalArgumentException("Already scanned classpath element " + this);
        }
        LogNode log = logNode == null ? null : log(this.classpathElementIdx, "Scanning Path classpath element " + getURI(), logNode);
        scanPathRecursively(this.classpathEltPath, log);
        finishScanPaths(log);
    }

    @Override // io.github.classgraph.ClasspathElement
    public String getModuleName() {
        if (this.moduleNameFromModuleDescriptor == null || this.moduleNameFromModuleDescriptor.isEmpty()) {
            return null;
        }
        return this.moduleNameFromModuleDescriptor;
    }

    @Override // io.github.classgraph.ClasspathElement
    public File getFile() {
        try {
            return this.classpathEltPath.toFile();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public URI getURI() {
        try {
            return this.classpathEltPath.toUri();
        } catch (IOError | SecurityException unused) {
            throw new IllegalArgumentException("Could not convert to URI: " + this.classpathEltPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public List<URI> getAllURIs() {
        return Collections.singletonList(getURI());
    }

    public String toString() {
        try {
            return this.classpathEltPath.toUri().toString();
        } catch (IOError | SecurityException unused) {
            return this.classpathEltPath.toString();
        }
    }

    public int hashCode() {
        return Objects.hash(this.classpathEltPath);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClasspathElementDir)) {
            return false;
        }
        return Objects.equals(this.classpathEltPath, ((ClasspathElementDir) obj).classpathEltPath);
    }
}
