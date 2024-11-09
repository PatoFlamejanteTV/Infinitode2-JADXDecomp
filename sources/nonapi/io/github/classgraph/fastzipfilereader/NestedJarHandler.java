package nonapi.io.github.classgraph.fastzipfilereader;

import io.github.classgraph.ModuleReaderProxy;
import io.github.classgraph.ModuleRef;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import nonapi.io.github.classgraph.concurrency.InterruptionChecker;
import nonapi.io.github.classgraph.concurrency.SingletonMap;
import nonapi.io.github.classgraph.fileslice.ArraySlice;
import nonapi.io.github.classgraph.fileslice.FileSlice;
import nonapi.io.github.classgraph.fileslice.Slice;
import nonapi.io.github.classgraph.recycler.Recycler;
import nonapi.io.github.classgraph.recycler.Resettable;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fastzipfilereader/NestedJarHandler.class */
public class NestedJarHandler {
    public final ScanSpec scanSpec;
    public ReflectionUtils reflectionUtils;
    public static final String TEMP_FILENAME_LEAF_SEPARATOR = "---";
    public InterruptionChecker interruptionChecker;
    private static final int DEFAULT_BUFFER_SIZE = 16384;
    private static final int MAX_INITIAL_BUFFER_SIZE = 16777216;
    private static final int HTTP_TIMEOUT = 5000;
    private static Method runFinalizationMethod;
    private SingletonMap<File, PhysicalZipFile, IOException> canonicalFileToPhysicalZipFileMap = new SingletonMap<File, PhysicalZipFile, IOException>() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.1
        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap
        public PhysicalZipFile newInstance(File file, LogNode logNode) {
            return new PhysicalZipFile(file, NestedJarHandler.this, logNode);
        }
    };
    private SingletonMap<FastZipEntry, ZipFileSlice, IOException> fastZipEntryToZipFileSliceMap = new SingletonMap<FastZipEntry, ZipFileSlice, IOException>() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.2
        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap
        public ZipFileSlice newInstance(FastZipEntry fastZipEntry, LogNode logNode) {
            ZipFileSlice zipFileSlice;
            if (!fastZipEntry.isDeflated) {
                zipFileSlice = new ZipFileSlice(fastZipEntry);
            } else {
                if (logNode != null) {
                    logNode.log("Inflating nested zip entry: " + fastZipEntry + " ; uncompressed size: " + fastZipEntry.uncompressedSize);
                }
                zipFileSlice = new ZipFileSlice(new PhysicalZipFile(fastZipEntry.getSlice().open(), (fastZipEntry.uncompressedSize < 0 || fastZipEntry.uncompressedSize > 2147483639) ? -1L : (int) fastZipEntry.uncompressedSize, fastZipEntry.entryName, NestedJarHandler.this, logNode), fastZipEntry);
            }
            return zipFileSlice;
        }
    };
    private SingletonMap<ZipFileSlice, LogicalZipFile, IOException> zipFileSliceToLogicalZipFileMap = new SingletonMap<ZipFileSlice, LogicalZipFile, IOException>() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.3
        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap
        public LogicalZipFile newInstance(ZipFileSlice zipFileSlice, LogNode logNode) {
            return new LogicalZipFile(zipFileSlice, NestedJarHandler.this, logNode, NestedJarHandler.this.scanSpec.enableMultiReleaseVersions);
        }
    };
    public SingletonMap<String, Map.Entry<LogicalZipFile, String>, IOException> nestedPathToLogicalZipFileAndPackageRootMap = new SingletonMap<String, Map.Entry<LogicalZipFile, String>, IOException>() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.4
        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap
        public Map.Entry<LogicalZipFile, String> newInstance(String str, LogNode logNode) {
            PhysicalZipFile physicalZipFile;
            String resolve = FastPathResolver.resolve(str);
            int lastIndexOf = resolve.lastIndexOf(33);
            if (lastIndexOf < 0) {
                if (!JarUtils.URL_SCHEME_PATTERN.matcher(resolve).matches()) {
                    try {
                        physicalZipFile = (PhysicalZipFile) NestedJarHandler.this.canonicalFileToPhysicalZipFileMap.get(new File(resolve).getCanonicalFile(), logNode);
                    } catch (SecurityException e) {
                        throw new IOException("Path component " + resolve + " could not be canonicalized: " + e);
                    } catch (SingletonMap.NewInstanceException | SingletonMap.NullSingletonException e2) {
                        throw new IOException("Could not get PhysicalZipFile for path " + resolve + " : " + (e2.getCause() == null ? e2 : e2.getCause()));
                    }
                } else {
                    String substring = resolve.substring(0, resolve.indexOf(58));
                    if (NestedJarHandler.this.scanSpec.allowedURLSchemes != null && NestedJarHandler.this.scanSpec.allowedURLSchemes.contains(substring)) {
                        physicalZipFile = NestedJarHandler.this.downloadJarFromURL(resolve, logNode);
                    } else {
                        throw new IOException("Scanning of URL scheme \"" + substring + "\" has not been enabled -- cannot scan classpath element: " + resolve);
                    }
                }
                ZipFileSlice zipFileSlice = new ZipFileSlice(physicalZipFile);
                try {
                    return new AbstractMap.SimpleEntry((LogicalZipFile) NestedJarHandler.this.zipFileSliceToLogicalZipFileMap.get(zipFileSlice, logNode), "");
                } catch (SingletonMap.NewInstanceException e3) {
                    throw new IOException("Could not get toplevel slice " + zipFileSlice, e3);
                } catch (SingletonMap.NullSingletonException e4) {
                    throw new IOException("Could not get toplevel slice " + zipFileSlice + " : " + e4);
                }
            }
            String substring2 = resolve.substring(0, lastIndexOf);
            String sanitizeEntryPath = FileUtils.sanitizeEntryPath(resolve.substring(lastIndexOf + 1), true, true);
            try {
                LogicalZipFile key = NestedJarHandler.this.nestedPathToLogicalZipFileAndPackageRootMap.get(substring2, logNode).getKey();
                boolean z = false;
                while (sanitizeEntryPath.endsWith("/")) {
                    z = true;
                    sanitizeEntryPath = sanitizeEntryPath.substring(0, sanitizeEntryPath.length() - 1);
                }
                FastZipEntry fastZipEntry = null;
                if (!z) {
                    Iterator<FastZipEntry> it = key.entries.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        FastZipEntry next = it.next();
                        if (next.entryName.equals(sanitizeEntryPath)) {
                            fastZipEntry = next;
                            break;
                        }
                    }
                }
                if (fastZipEntry == null) {
                    String str2 = sanitizeEntryPath + "/";
                    Iterator<FastZipEntry> it2 = key.entries.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        if (it2.next().entryName.startsWith(str2)) {
                            z = true;
                            break;
                        }
                    }
                }
                if (z) {
                    if (!sanitizeEntryPath.isEmpty()) {
                        if (logNode != null) {
                            logNode.log("Path " + sanitizeEntryPath + " in jarfile " + key + " is a directory, not a file -- using as package root");
                        }
                        key.classpathRoots.add(sanitizeEntryPath);
                    }
                    return new AbstractMap.SimpleEntry(key, sanitizeEntryPath);
                }
                if (fastZipEntry == null) {
                    throw new IOException("Path " + sanitizeEntryPath + " does not exist in jarfile " + key);
                }
                if (NestedJarHandler.this.scanSpec.scanNestedJars) {
                    try {
                        ZipFileSlice zipFileSlice2 = (ZipFileSlice) NestedJarHandler.this.fastZipEntryToZipFileSliceMap.get(fastZipEntry, logNode);
                        try {
                            return new AbstractMap.SimpleEntry((LogicalZipFile) NestedJarHandler.this.zipFileSliceToLogicalZipFileMap.get(zipFileSlice2, logNode == null ? null : logNode.log("Getting zipfile slice " + zipFileSlice2 + " for nested jar " + fastZipEntry.entryName)), "");
                        } catch (SingletonMap.NewInstanceException e5) {
                            throw new IOException("Could not get child logical zipfile " + zipFileSlice2, e5);
                        } catch (SingletonMap.NullSingletonException e6) {
                            throw new IOException("Could not get child logical zipfile " + zipFileSlice2 + " : " + e6);
                        }
                    } catch (SingletonMap.NewInstanceException e7) {
                        throw new IOException("Could not get child zip entry slice " + fastZipEntry, e7);
                    } catch (SingletonMap.NullSingletonException e8) {
                        throw new IOException("Could not get child zip entry slice " + fastZipEntry + " : " + e8);
                    }
                }
                throw new IOException("Nested jar scanning is disabled -- skipping nested jar " + resolve);
            } catch (SingletonMap.NewInstanceException e9) {
                throw new IOException("Could not get parent logical zipfile " + substring2, e9);
            } catch (SingletonMap.NullSingletonException e10) {
                throw new IOException("Could not get parent logical zipfile " + substring2 + " : " + e10);
            }
        }
    };
    public SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> moduleRefToModuleReaderProxyRecyclerMap = new SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException>() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.5
        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap
        public Recycler<ModuleReaderProxy, IOException> newInstance(final ModuleRef moduleRef, LogNode logNode) {
            return new Recycler<ModuleReaderProxy, IOException>() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.5.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // nonapi.io.github.classgraph.recycler.Recycler
                public ModuleReaderProxy newInstance() {
                    return moduleRef.open();
                }
            };
        }
    };
    private Recycler<RecyclableInflater, RuntimeException> inflaterRecycler = new Recycler<RecyclableInflater, RuntimeException>() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // nonapi.io.github.classgraph.recycler.Recycler
        public RecyclableInflater newInstance() {
            return new RecyclableInflater();
        }
    };
    private Set<Slice> openSlices = Collections.newSetFromMap(new ConcurrentHashMap());
    private Set<File> tempFiles = Collections.newSetFromMap(new ConcurrentHashMap());
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public NestedJarHandler(ScanSpec scanSpec, InterruptionChecker interruptionChecker, ReflectionUtils reflectionUtils) {
        this.scanSpec = scanSpec;
        this.interruptionChecker = interruptionChecker;
        this.reflectionUtils = reflectionUtils;
    }

    private static String leafname(String str) {
        return str.substring(str.lastIndexOf(47) + 1);
    }

    private String sanitizeFilename(String str) {
        return str.replace('/', '_').replace('\\', '_').replace(':', '_').replace('?', '_').replace('&', '_').replace('=', '_').replace(' ', '_');
    }

    public File makeTempFile(String str, boolean z) {
        File createTempFile = File.createTempFile("ClassGraph--", TEMP_FILENAME_LEAF_SEPARATOR + sanitizeFilename(z ? leafname(str) : str));
        createTempFile.deleteOnExit();
        this.tempFiles.add(createTempFile);
        return createTempFile;
    }

    void removeTempFile(File file) {
        if (this.tempFiles.remove(file)) {
            Files.delete(file.toPath());
            return;
        }
        throw new IOException("Not a temp file: " + file);
    }

    public void markSliceAsOpen(Slice slice) {
        this.openSlices.add(slice);
    }

    public void markSliceAsClosed(Slice slice) {
        this.openSlices.remove(slice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    public PhysicalZipFile downloadJarFromURL(String str, LogNode logNode) {
        URL url;
        LogNode logNode2;
        try {
            url = new URL(str);
        } catch (MalformedURLException unused) {
            try {
                url = new URI(str).toURL();
            } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused2) {
                throw new IOException("Could not parse URL: " + str);
            }
        }
        String protocol = url.getProtocol();
        if (!protocol.equalsIgnoreCase("http") && !protocol.equalsIgnoreCase("https")) {
            try {
                Path path = Paths.get(url.toURI());
                FileSystem fileSystem = path.getFileSystem();
                if (logNode != null) {
                    logNode.log("URL " + str + " is backed by filesystem " + fileSystem.getClass().getName());
                }
                logNode2 = logNode;
                return new PhysicalZipFile(path, this, logNode2);
            } catch (IllegalArgumentException | SecurityException | URISyntaxException e) {
                throw new IOException("Could not convert URL to URI (" + e + "): " + url);
            } catch (FileSystemNotFoundException unused3) {
            }
        }
        CloseableUrlConnection closeableUrlConnection = new CloseableUrlConnection(url);
        Throwable th = null;
        try {
            closeableUrlConnection.conn.setConnectTimeout(HTTP_TIMEOUT);
            closeableUrlConnection.conn.connect();
            if (closeableUrlConnection.httpConn != null) {
                if (closeableUrlConnection.httpConn.getResponseCode() != 200) {
                    throw new IOException("Got response code " + closeableUrlConnection.httpConn.getResponseCode() + " for URL " + url);
                }
            } else if (url.getProtocol().equalsIgnoreCase("file")) {
                try {
                    logNode2 = logNode;
                    PhysicalZipFile physicalZipFile = new PhysicalZipFile(Paths.get(url.toURI()).toFile(), this, logNode2);
                    if (0 != 0) {
                        try {
                            closeableUrlConnection.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        closeableUrlConnection.close();
                    }
                    return physicalZipFile;
                } catch (Exception unused4) {
                }
            }
            long contentLengthLong = closeableUrlConnection.conn.getContentLengthLong();
            long j = contentLengthLong;
            if (contentLengthLong < -1) {
                j = -1;
            }
            LogNode log = logNode == null ? null : logNode.log("Downloading jar from URL " + str);
            try {
                InputStream inputStream = closeableUrlConnection.conn.getInputStream();
                LogNode logNode3 = null;
                boolean z = 0;
                try {
                    try {
                        PhysicalZipFile physicalZipFile2 = new PhysicalZipFile(inputStream, j, str, this, log);
                        if (log != null) {
                            log.addElapsedTime();
                            logNode3 = log.log("***** Note that it is time-consuming to scan jars at non-\"file:\" URLs, the URL must be opened (possibly after an http(s) fetch) for every scan, and the same URL must also be separately opened by the ClassLoader *****");
                        }
                        if (inputStream != null) {
                            if (0 != 0) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th3) {
                                    z.addSuppressed(th3);
                                }
                            } else {
                                inputStream.close();
                            }
                        }
                        return physicalZipFile2;
                    } catch (Throwable th4) {
                        if (inputStream != null) {
                            if (z) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th5) {
                                    z.addSuppressed(th5);
                                }
                            } else {
                                inputStream.close();
                            }
                        }
                        throw th4;
                    }
                } catch (Throwable th6) {
                    z = logNode3;
                    throw th6;
                }
            } catch (MalformedURLException unused5) {
                throw new IOException("Malformed URL: " + str);
            }
        } finally {
            if (0 != 0) {
                try {
                    closeableUrlConnection.close();
                } catch (Throwable th7) {
                    th.addSuppressed(th7);
                }
            } else {
                closeableUrlConnection.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fastzipfilereader/NestedJarHandler$CloseableUrlConnection.class */
    public static class CloseableUrlConnection implements AutoCloseable {
        public final URLConnection conn;
        public final HttpURLConnection httpConn;

        public CloseableUrlConnection(URL url) {
            this.conn = url.openConnection();
            this.httpConn = this.conn instanceof HttpURLConnection ? (HttpURLConnection) this.conn : null;
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (this.httpConn != null) {
                this.httpConn.disconnect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fastzipfilereader/NestedJarHandler$RecyclableInflater.class */
    public static class RecyclableInflater implements AutoCloseable, Resettable {
        private final Inflater inflater;

        private RecyclableInflater() {
            this.inflater = new Inflater(true);
        }

        public Inflater getInflater() {
            return this.inflater;
        }

        @Override // nonapi.io.github.classgraph.recycler.Resettable
        public void reset() {
            this.inflater.reset();
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.inflater.end();
        }
    }

    public InputStream openInflaterInputStream(final InputStream inputStream) {
        return new InputStream() { // from class: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.7
            private final RecyclableInflater recyclableInflater;
            private final Inflater inflater;
            private final AtomicBoolean closed = new AtomicBoolean();
            private final byte[] buf = new byte[8192];
            private static final int INFLATE_BUF_SIZE = 8192;

            {
                this.recyclableInflater = (RecyclableInflater) NestedJarHandler.this.inflaterRecycler.acquire();
                this.inflater = this.recyclableInflater.getInflater();
            }

            @Override // java.io.InputStream
            public int read() {
                if (this.closed.get()) {
                    throw new IOException("Already closed");
                }
                if (this.inflater.finished() || read(this.buf, 0, 1) < 0) {
                    return -1;
                }
                return this.buf[0] & 255;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                if (this.closed.get()) {
                    throw new IOException("Already closed");
                }
                if (i2 < 0) {
                    throw new IllegalArgumentException("len cannot be negative");
                }
                if (i2 == 0) {
                    return 0;
                }
                int i3 = 0;
                while (!this.inflater.finished() && i3 < i2) {
                    try {
                        int inflate = this.inflater.inflate(bArr, i + i3, i2 - i3);
                        if (inflate != 0) {
                            i3 += inflate;
                        } else {
                            if (this.inflater.needsDictionary()) {
                                throw new IOException("Inflater needs preset dictionary");
                            }
                            if (this.inflater.needsInput()) {
                                int read = inputStream.read(this.buf, 0, this.buf.length);
                                if (read != -1) {
                                    this.inflater.setInput(this.buf, 0, read);
                                } else {
                                    this.buf[0] = 0;
                                    this.inflater.setInput(this.buf, 0, 1);
                                }
                            }
                        }
                    } catch (DataFormatException e) {
                        throw new ZipException(e.getMessage() != null ? e.getMessage() : "Invalid deflated zip entry data");
                    }
                }
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }

            @Override // java.io.InputStream
            public long skip(long j) {
                if (this.closed.get()) {
                    throw new IOException("Already closed");
                }
                if (j < 0) {
                    throw new IllegalArgumentException("numToSkip cannot be negative");
                }
                if (j == 0) {
                    return 0L;
                }
                if (this.inflater.finished()) {
                    return -1L;
                }
                long j2 = 0;
                while (true) {
                    long j3 = j2;
                    int read = read(this.buf, 0, (int) Math.min(j - j3, this.buf.length));
                    if (read > 0) {
                        j2 = j3 - read;
                    } else {
                        return j3;
                    }
                }
            }

            @Override // java.io.InputStream
            public int available() {
                if (this.closed.get()) {
                    throw new IOException("Already closed");
                }
                return this.inflater.finished() ? 0 : 1;
            }

            @Override // java.io.InputStream
            public synchronized void mark(int i) {
                throw new IllegalArgumentException("Not supported");
            }

            @Override // java.io.InputStream
            public synchronized void reset() {
                throw new IllegalArgumentException("Not supported");
            }

            @Override // java.io.InputStream
            public boolean markSupported() {
                return false;
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                if (!this.closed.getAndSet(true)) {
                    try {
                        inputStream.close();
                    } catch (Exception unused) {
                    }
                    NestedJarHandler.this.inflaterRecycler.recycle(this.recyclableInflater);
                }
            }
        };
    }

    public Slice readAllBytesWithSpilloverToDisk(InputStream inputStream, String str, long j, LogNode logNode) {
        int read;
        Throwable th = null;
        try {
            if (j > this.scanSpec.maxBufferedJarRAMSize) {
                FileSlice spillToDisk = spillToDisk(inputStream, str, null, null, logNode);
                if (inputStream != null) {
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        inputStream.close();
                    }
                }
                return spillToDisk;
            }
            byte[] bArr = new byte[j == -1 ? this.scanSpec.maxBufferedJarRAMSize : j == 0 ? 16384 : Math.min((int) j, this.scanSpec.maxBufferedJarRAMSize)];
            byte[] bArr2 = bArr;
            int length = bArr.length;
            int i = 0;
            while (true) {
                read = inputStream.read(bArr2, i, length - i);
                if (read <= 0) {
                    break;
                }
                i += read;
            }
            if (read == 0) {
                byte[] bArr3 = new byte[1];
                if (inputStream.read(bArr3, 0, 1) == 1) {
                    FileSlice spillToDisk2 = spillToDisk(inputStream, str, bArr2, bArr3, logNode);
                    if (inputStream != null) {
                        if (0 != 0) {
                            try {
                                inputStream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        } else {
                            inputStream.close();
                        }
                    }
                    return spillToDisk2;
                }
            }
            if (i < bArr2.length) {
                bArr2 = Arrays.copyOf(bArr2, i);
            }
            ArraySlice arraySlice = new ArraySlice(bArr2, false, 0L, this);
            if (inputStream != null) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    inputStream.close();
                }
            }
            return arraySlice;
        } catch (Throwable th5) {
            if (inputStream != null) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (Throwable th6) {
                        th.addSuppressed(th6);
                    }
                } else {
                    inputStream.close();
                }
            }
            throw th5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FileSlice spillToDisk(InputStream inputStream, String str, byte[] bArr, byte[] bArr2, LogNode logNode) {
        try {
            File makeTempFile = makeTempFile(str, true);
            if (logNode != null) {
                logNode.log("Could not fit InputStream content into max RAM buffer size, saving to temporary file: " + str + " -> " + makeTempFile);
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(makeTempFile));
            Throwable th = null;
            try {
                int i = bArr;
                if (i != 0) {
                    try {
                        bufferedOutputStream.write(bArr);
                        bufferedOutputStream.write(bArr2);
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                byte[] bArr3 = new byte[8192];
                while (true) {
                    i = inputStream.read(bArr3, 0, 8192);
                    if (i <= 0) {
                        break;
                    }
                    bufferedOutputStream.write(bArr3, 0, i);
                }
                return new FileSlice(makeTempFile, this, logNode);
            } finally {
                if (0 != 0) {
                    try {
                        bufferedOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    bufferedOutputStream.close();
                }
            }
        } catch (IOException e) {
            throw new IOException("Could not create temporary file: " + e.getMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0089, code lost:            if (r8 != r7.length) goto L27;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008c, code lost:            r0 = r7;     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0090, code lost:            r0 = java.util.Arrays.copyOf(r7, r8);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] readAllBytesAsArray(java.io.InputStream r6, long r7) {
        /*
            Method dump skipped, instructions count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.readAllBytesAsArray(java.io.InputStream, long):byte[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ac, code lost:            if (r5.openSlices != null) goto L30;     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b8, code lost:            if (r5.openSlices.isEmpty() != false) goto L81;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bb, code lost:            r0 = new java.util.ArrayList(r5.openSlices).iterator();     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d0, code lost:            if (r0.hasNext() == false) goto L82;     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d3, code lost:            r0 = (nonapi.io.github.classgraph.fileslice.Slice) r0.next();     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00de, code lost:            r0.close();     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f3, code lost:            r5.openSlices.clear();        r5.openSlices = null;     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0105, code lost:            if (r5.inflaterRecycler == null) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0108, code lost:            r5.inflaterRecycler.forceClose();        r5.inflaterRecycler = null;     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0118, code lost:            if (r5.tempFiles == null) goto L66;     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0124, code lost:            if (r5.tempFiles.isEmpty() != false) goto L50;     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0128, code lost:            if (r6 != null) goto L51;     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x012f, code lost:            r0 = r6.log("Removing temporary files");     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0135, code lost:            r8 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x013f, code lost:            if (r5.tempFiles.isEmpty() != false) goto L85;     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0142, code lost:            r0 = new java.util.ArrayList(r5.tempFiles).iterator();     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0159, code lost:            if (r0.hasNext() == false) goto L86;     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x015c, code lost:            r0 = (java.io.File) r0.next();     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0168, code lost:            removeTempFile(r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0173, code lost:            if (r8 != null) goto L87;     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0176, code lost:            r8.log("Removing temporary file failed: " + r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0192, code lost:            r5.tempFiles = null;     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x012b, code lost:            r0 = null;     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0198, code lost:            if (r7 == false) goto L93;     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x019b, code lost:            r5.interruptionChecker.interrupt();     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01a2, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:89:?, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void close(nonapi.io.github.classgraph.utils.LogNode r6) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler.close(nonapi.io.github.classgraph.utils.LogNode):void");
    }

    public void runFinalizationMethod() {
        if (runFinalizationMethod == null) {
            runFinalizationMethod = this.reflectionUtils.staticMethodForNameOrNull("System", "runFinalization");
        }
        if (runFinalizationMethod != null) {
            try {
                runFinalizationMethod.invoke(null, new Object[0]);
            } catch (Throwable unused) {
            }
        }
    }

    public void closeDirectByteBuffer(ByteBuffer byteBuffer) {
        FileUtils.closeDirectByteBuffer(byteBuffer, this.reflectionUtils, null);
    }
}
