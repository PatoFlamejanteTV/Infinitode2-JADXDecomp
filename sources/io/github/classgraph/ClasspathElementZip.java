package io.github.classgraph;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import io.github.classgraph.Scanner;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.bytebuddy.build.Plugin;
import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
import nonapi.io.github.classgraph.concurrency.SingletonMap;
import nonapi.io.github.classgraph.concurrency.WorkQueue;
import nonapi.io.github.classgraph.fastzipfilereader.FastZipEntry;
import nonapi.io.github.classgraph.fastzipfilereader.LogicalZipFile;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.fastzipfilereader.ZipFileSlice;
import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;
import nonapi.io.github.classgraph.utils.URLPathEncoder;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:io/github/classgraph/ClasspathElementZip.class */
public class ClasspathElementZip extends ClasspathElement {
    private final String rawPath;
    LogicalZipFile logicalZipFile;
    private String zipFilePath;
    private final ConcurrentHashMap<String, Resource> relativePathToResource;
    private final Set<String> strippedAutomaticPackageRootPrefixes;
    private final NestedJarHandler nestedJarHandler;
    String moduleNameFromManifestFile;
    private String derivedAutomaticModuleName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClasspathElementZip(Scanner.ClasspathEntryWorkUnit classpathEntryWorkUnit, NestedJarHandler nestedJarHandler, ScanSpec scanSpec) {
        super(classpathEntryWorkUnit, scanSpec);
        this.relativePathToResource = new ConcurrentHashMap<>();
        this.strippedAutomaticPackageRootPrefixes = new HashSet();
        Object obj = classpathEntryWorkUnit.classpathEntryObj;
        String str = null;
        if (obj instanceof Path) {
            try {
                str = ((Path) obj).toUri().toString();
            } catch (IOError | SecurityException unused) {
            }
        }
        str = str == null ? obj.toString() : str;
        this.rawPath = str;
        this.zipFilePath = str;
        this.nestedJarHandler = nestedJarHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, LogNode logNode) {
        String str;
        if (!this.scanSpec.scanJars) {
            if (logNode != null) {
                log(this.classpathElementIdx, "Skipping classpath element, since jar scanning is disabled: " + this.rawPath, logNode);
            }
            this.skipClasspathElement = true;
            return;
        }
        LogNode log = logNode == null ? null : log(this.classpathElementIdx, "Opening jar: " + this.rawPath, logNode);
        int indexOf = this.rawPath.indexOf(33);
        try {
            if (!this.scanSpec.jarAcceptReject.isAcceptedAndNotRejected(FastPathResolver.resolve(FileUtils.currDirPath(), indexOf < 0 ? this.rawPath : this.rawPath.substring(0, indexOf)))) {
                if (log != null) {
                    log.log("Skipping jarfile that is rejected or not accepted: " + this.rawPath);
                }
                this.skipClasspathElement = true;
                return;
            }
            try {
                Map.Entry<LogicalZipFile, String> entry = this.nestedJarHandler.nestedPathToLogicalZipFileAndPackageRootMap.get(this.rawPath, log);
                this.logicalZipFile = entry.getKey();
                if (this.logicalZipFile == null) {
                    throw new IOException("Logical zipfile was null");
                }
                this.zipFilePath = FastPathResolver.resolve(FileUtils.currDirPath(), this.logicalZipFile.getPath());
                String value = entry.getValue();
                if (!value.isEmpty()) {
                    this.packageRootPrefix = value + "/";
                }
                if (!this.scanSpec.enableSystemJarsAndModules && this.logicalZipFile.isJREJar) {
                    if (log != null) {
                        log.log("Ignoring JRE jar: " + this.rawPath);
                    }
                    this.skipClasspathElement = true;
                    return;
                }
                if (!this.logicalZipFile.isAcceptedAndNotRejected(this.scanSpec.jarAcceptReject)) {
                    if (log != null) {
                        log.log("Skipping jarfile that is rejected or not accepted: " + this.rawPath);
                    }
                    this.skipClasspathElement = true;
                    return;
                }
                int i = 0;
                if (this.scanSpec.scanNestedJars) {
                    for (FastZipEntry fastZipEntry : this.logicalZipFile.entries) {
                        String[] strArr = ClassLoaderHandlerRegistry.AUTOMATIC_LIB_DIR_PREFIXES;
                        int length = strArr.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 < length) {
                                if (!fastZipEntry.entryNameUnversioned.startsWith(strArr[i2]) || !fastZipEntry.entryNameUnversioned.endsWith(".jar")) {
                                    i2++;
                                } else {
                                    String path = fastZipEntry.getPath();
                                    if (log != null) {
                                        log.log("Found nested lib jar: " + path);
                                    }
                                    int i3 = i;
                                    i++;
                                    workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(path, getClassLoader(), this, i3, ""));
                                }
                            }
                        }
                    }
                }
                HashSet hashSet = new HashSet();
                hashSet.add(this.rawPath);
                if (this.logicalZipFile.classPathManifestEntryValue != null) {
                    String parentDirPath = FileUtils.getParentDirPath(this.logicalZipFile.getPathWithinParentZipFileSlice());
                    for (String str2 : this.logicalZipFile.classPathManifestEntryValue.split(SequenceUtils.SPACE)) {
                        if (!str2.isEmpty()) {
                            String resolve = FastPathResolver.resolve(parentDirPath, str2);
                            ZipFileSlice parentZipFileSlice = this.logicalZipFile.getParentZipFileSlice();
                            String str3 = parentZipFileSlice == null ? resolve : parentZipFileSlice.getPath() + (resolve.startsWith("/") ? "!" : "!/") + resolve;
                            if (hashSet.add(str3)) {
                                int i4 = i;
                                i++;
                                workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(str3, getClassLoader(), this, i4, ""));
                            }
                        }
                    }
                }
                if (this.logicalZipFile.bundleClassPathManifestEntryValue != null) {
                    String str4 = this.zipFilePath + "!/";
                    for (String str5 : this.logicalZipFile.bundleClassPathManifestEntryValue.split(",")) {
                        while (true) {
                            str = str5;
                            if (!str.startsWith("/")) {
                                break;
                            } else {
                                str5 = str.substring(1);
                            }
                        }
                        if (!str.isEmpty() && !str.equals(".")) {
                            String str6 = str4 + FileUtils.sanitizeEntryPath(str, true, true);
                            if (hashSet.add(str6)) {
                                int i5 = i;
                                i++;
                                workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(str6, getClassLoader(), this, i5, ""));
                            }
                        }
                    }
                }
            } catch (SingletonMap.NewInstanceException | SingletonMap.NullSingletonException e) {
                throw new IOException("Could not get logical zipfile " + this.rawPath + " : " + (e.getCause() == null ? e : e.getCause()));
            }
        } catch (IOException | IllegalArgumentException e2) {
            if (log != null) {
                log.log("Could not open jarfile " + this.rawPath + " : " + e2);
            }
            this.skipClasspathElement = true;
        }
    }

    private Resource newResource(final FastZipEntry fastZipEntry, final String str) {
        return new Resource(this, fastZipEntry.uncompressedSize) { // from class: io.github.classgraph.ClasspathElementZip.1
            private final AtomicBoolean isOpen = new AtomicBoolean();

            @Override // io.github.classgraph.Resource
            public String getPath() {
                return str;
            }

            @Override // io.github.classgraph.Resource
            public String getPathRelativeToClasspathElement() {
                if (fastZipEntry.entryName.startsWith(ClasspathElementZip.this.packageRootPrefix)) {
                    return fastZipEntry.entryName.substring(ClasspathElementZip.this.packageRootPrefix.length());
                }
                return fastZipEntry.entryName;
            }

            @Override // io.github.classgraph.Resource
            public long getLastModified() {
                return fastZipEntry.getLastModifiedTimeMillis();
            }

            @Override // io.github.classgraph.Resource
            public Set<PosixFilePermission> getPosixFilePermissions() {
                HashSet hashSet;
                int i = fastZipEntry.fileAttributes;
                if (i == 0) {
                    hashSet = null;
                } else {
                    hashSet = new HashSet();
                    if ((i & 256) > 0) {
                        hashSet.add(PosixFilePermission.OWNER_READ);
                    }
                    if ((i & 128) > 0) {
                        hashSet.add(PosixFilePermission.OWNER_WRITE);
                    }
                    if ((i & 64) > 0) {
                        hashSet.add(PosixFilePermission.OWNER_EXECUTE);
                    }
                    if ((i & 32) > 0) {
                        hashSet.add(PosixFilePermission.GROUP_READ);
                    }
                    if ((i & 16) > 0) {
                        hashSet.add(PosixFilePermission.GROUP_WRITE);
                    }
                    if ((i & 8) > 0) {
                        hashSet.add(PosixFilePermission.GROUP_EXECUTE);
                    }
                    if ((i & 4) > 0) {
                        hashSet.add(PosixFilePermission.OTHERS_READ);
                    }
                    if ((i & 2) > 0) {
                        hashSet.add(PosixFilePermission.OTHERS_WRITE);
                    }
                    if ((i & 1) > 0) {
                        hashSet.add(PosixFilePermission.OTHERS_EXECUTE);
                    }
                }
                return hashSet;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // io.github.classgraph.Resource
            public ClassfileReader openClassfile() {
                return new ClassfileReader(open(), this);
            }

            @Override // io.github.classgraph.Resource
            public InputStream open() {
                if (ClasspathElementZip.this.skipClasspathElement) {
                    throw new IOException("Jarfile could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                try {
                    this.inputStream = fastZipEntry.getSlice().open(this);
                    this.length = fastZipEntry.uncompressedSize;
                    return this.inputStream;
                } catch (IOException e) {
                    close();
                    throw e;
                }
            }

            @Override // io.github.classgraph.Resource
            public ByteBuffer read() {
                if (ClasspathElementZip.this.skipClasspathElement) {
                    throw new IOException("Jarfile could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                try {
                    this.byteBuffer = fastZipEntry.getSlice().read();
                    this.length = this.byteBuffer.remaining();
                    return this.byteBuffer;
                } catch (IOException e) {
                    close();
                    throw e;
                }
            }

            @Override // io.github.classgraph.Resource
            public byte[] load() {
                if (ClasspathElementZip.this.skipClasspathElement) {
                    throw new IOException("Jarfile could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                Throwable th = null;
                try {
                    byte[] load = fastZipEntry.getSlice().load();
                    this.length = load.length;
                    if (0 != 0) {
                        try {
                            close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        close();
                    }
                    return load;
                } catch (Throwable th3) {
                    if (0 != 0) {
                        try {
                            close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    } else {
                        close();
                    }
                    throw th3;
                }
            }

            @Override // io.github.classgraph.Resource, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                if (this.isOpen.getAndSet(false)) {
                    if (this.byteBuffer != null) {
                        this.byteBuffer = null;
                    }
                    super.close();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public Resource getResource(String str) {
        return this.relativePathToResource.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public void scanPaths(LogNode logNode) {
        if (this.logicalZipFile == null) {
            this.skipClasspathElement = true;
        }
        if (!checkResourcePathAcceptReject(getZipFilePath(), logNode)) {
            this.skipClasspathElement = true;
        }
        if (this.skipClasspathElement) {
            return;
        }
        if (this.scanned.getAndSet(true)) {
            throw new IllegalArgumentException("Already scanned classpath element " + getZipFilePath());
        }
        LogNode log = logNode == null ? null : log(this.classpathElementIdx, "Scanning jarfile classpath element " + getZipFilePath(), logNode);
        boolean z = false;
        if (VersionFinder.JAVA_MAJOR_VERSION >= 9) {
            String str = this.moduleNameFromModuleDescriptor;
            String str2 = str;
            if (str == null || str2.isEmpty()) {
                str2 = this.moduleNameFromManifestFile;
            }
            if (str2 != null && str2.isEmpty()) {
                str2 = null;
            }
            if (str2 != null) {
                z = true;
            }
        }
        HashSet hashSet = null;
        String str3 = null;
        ScanSpec.ScanSpecPathMatch scanSpecPathMatch = null;
        for (FastZipEntry fastZipEntry : this.logicalZipFile.entries) {
            String str4 = fastZipEntry.entryNameUnversioned;
            if (!this.scanSpec.enableMultiReleaseVersions && str4.startsWith(LogicalZipFile.MULTI_RELEASE_PATH_PREFIX)) {
                if (log != null) {
                    if (VersionFinder.JAVA_MAJOR_VERSION < 9) {
                        log.log("Skipping versioned entry in jar, because JRE version " + VersionFinder.JAVA_MAJOR_VERSION + " does not support this: " + str4);
                    } else {
                        log.log("Found unexpected versioned entry in jar (the jar's manifest file may be missing the \"Multi-Release\" key) -- skipping: " + str4);
                    }
                }
            } else if (!z || str4.indexOf(47) >= 0 || !str4.endsWith(".class") || str4.equals(Plugin.Engine.MODULE_INFO)) {
                if (this.nestedClasspathRootPrefixes != null) {
                    boolean z2 = false;
                    Iterator<String> it = this.nestedClasspathRootPrefixes.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String next = it.next();
                        if (str4.startsWith(next)) {
                            if (log != null) {
                                if (hashSet == null) {
                                    hashSet = new HashSet();
                                }
                                if (hashSet.add(next)) {
                                    log.log("Reached nested classpath root, stopping recursion to avoid duplicate scanning: " + next);
                                }
                            }
                            z2 = true;
                        }
                    }
                    if (!z2) {
                    }
                }
                if (this.packageRootPrefix.isEmpty() || str4.startsWith(this.packageRootPrefix)) {
                    if (!this.packageRootPrefix.isEmpty()) {
                        str4 = str4.substring(this.packageRootPrefix.length());
                    } else {
                        for (int i = 0; i < ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES.length; i++) {
                            String str5 = ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES[i];
                            if (str4.startsWith(str5)) {
                                str4 = str4.substring(str5.length());
                                this.strippedAutomaticPackageRootPrefixes.add(str5.endsWith("/") ? str5.substring(0, str5.length() - 1) : str5);
                            }
                        }
                    }
                    if (checkResourcePathAcceptReject(str4, logNode)) {
                        int lastIndexOf = str4.lastIndexOf(47);
                        String substring = lastIndexOf < 0 ? "/" : str4.substring(0, lastIndexOf + 1);
                        String str6 = substring;
                        ScanSpec.ScanSpecPathMatch dirAcceptMatchStatus = !substring.equals(str3) ? this.scanSpec.dirAcceptMatchStatus(str6) : scanSpecPathMatch;
                        str3 = str6;
                        scanSpecPathMatch = dirAcceptMatchStatus;
                        if (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX) {
                            if (log != null) {
                                log.log("Skipping rejected path: " + str4);
                            }
                        } else {
                            Resource newResource = newResource(fastZipEntry, str4);
                            if (this.relativePathToResource.putIfAbsent(str4, newResource) == null) {
                                if (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX || dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_PATH || (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE && this.scanSpec.classfileIsSpecificallyAccepted(str4))) {
                                    addAcceptedResource(newResource, dirAcceptMatchStatus, false, log);
                                } else if (this.scanSpec.enableClassInfo && str4.equals(Plugin.Engine.MODULE_INFO)) {
                                    addAcceptedResource(newResource, dirAcceptMatchStatus, true, log);
                                }
                            }
                        }
                    }
                }
            }
        }
        File file = getFile();
        if (file != null) {
            this.fileToLastModified.put(file, Long.valueOf(file.lastModified()));
        }
        finishScanPaths(log);
    }

    @Override // io.github.classgraph.ClasspathElement
    public String getModuleName() {
        String str = this.moduleNameFromModuleDescriptor;
        String str2 = str;
        if (str == null || str2.isEmpty()) {
            str2 = this.moduleNameFromManifestFile;
        }
        if (str2 == null || str2.isEmpty()) {
            if (this.derivedAutomaticModuleName == null) {
                this.derivedAutomaticModuleName = JarUtils.derivedAutomaticModuleName(this.zipFilePath);
            }
            str2 = this.derivedAutomaticModuleName;
        }
        if (str2 == null || str2.isEmpty()) {
            return null;
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getZipFilePath() {
        return this.packageRootPrefix.isEmpty() ? this.zipFilePath : this.zipFilePath + "!/" + this.packageRootPrefix.substring(0, this.packageRootPrefix.length() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public URI getURI() {
        try {
            return new URI(URLPathEncoder.normalizeURLPath(getZipFilePath()));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Could not form URI: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public List<URI> getAllURIs() {
        if (this.strippedAutomaticPackageRootPrefixes.isEmpty()) {
            return Collections.singletonList(getURI());
        }
        URI uri = getURI();
        ArrayList arrayList = new ArrayList();
        arrayList.add(uri);
        String uri2 = uri.toString();
        Iterator<String> it = this.strippedAutomaticPackageRootPrefixes.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(new URI(uri2 + "!/" + it.next()));
            } catch (URISyntaxException unused) {
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public File getFile() {
        if (this.logicalZipFile != null) {
            return this.logicalZipFile.getPhysicalFile();
        }
        int indexOf = this.rawPath.indexOf(33);
        return new File(FastPathResolver.resolve(FileUtils.currDirPath(), indexOf < 0 ? this.rawPath : this.rawPath.substring(0, indexOf)));
    }

    public String toString() {
        return getZipFilePath();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClasspathElementZip)) {
            return false;
        }
        return getZipFilePath().equals(((ClasspathElementZip) obj).getZipFilePath());
    }

    public int hashCode() {
        return getZipFilePath().hashCode();
    }
}
