package io.github.classgraph;

import io.github.classgraph.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import net.bytebuddy.build.Plugin;
import nonapi.io.github.classgraph.concurrency.SingletonMap;
import nonapi.io.github.classgraph.concurrency.WorkQueue;
import nonapi.io.github.classgraph.fastzipfilereader.LogicalZipFile;
import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
import nonapi.io.github.classgraph.recycler.RecycleOnClose;
import nonapi.io.github.classgraph.recycler.Recycler;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import nonapi.io.github.classgraph.utils.LogNode;
import nonapi.io.github.classgraph.utils.ProxyingInputStream;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:io/github/classgraph/ClasspathElementModule.class */
public class ClasspathElementModule extends ClasspathElement {
    final ModuleRef moduleRef;
    SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> moduleRefToModuleReaderProxyRecyclerMap;
    private Recycler<ModuleReaderProxy, IOException> moduleReaderProxyRecycler;
    private final Set<String> allResourcePaths;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClasspathElementModule(ModuleRef moduleRef, SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> singletonMap, Scanner.ClasspathEntryWorkUnit classpathEntryWorkUnit, ScanSpec scanSpec) {
        super(classpathEntryWorkUnit, scanSpec);
        this.allResourcePaths = new HashSet();
        this.moduleRefToModuleReaderProxyRecyclerMap = singletonMap;
        this.moduleRef = moduleRef;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, LogNode logNode) {
        if (!this.scanSpec.scanModules) {
            if (logNode != null) {
                log(this.classpathElementIdx, "Skipping module, since module scanning is disabled: " + getModuleName(), logNode);
            }
            this.skipClasspathElement = true;
        } else {
            try {
                this.moduleReaderProxyRecycler = this.moduleRefToModuleReaderProxyRecyclerMap.get(this.moduleRef, logNode);
            } catch (IOException | SingletonMap.NewInstanceException | SingletonMap.NullSingletonException e) {
                if (logNode != null) {
                    log(this.classpathElementIdx, "Skipping invalid module " + getModuleName() + " : " + (e.getCause() == null ? e : e.getCause()), logNode);
                }
                this.skipClasspathElement = true;
            }
        }
    }

    private Resource newResource(final String str) {
        return new Resource(this, -1L) { // from class: io.github.classgraph.ClasspathElementModule.1
            private ModuleReaderProxy moduleReaderProxy;
            private final AtomicBoolean isOpen = new AtomicBoolean();

            @Override // io.github.classgraph.Resource
            public String getPath() {
                return str;
            }

            @Override // io.github.classgraph.Resource
            public long getLastModified() {
                return 0L;
            }

            @Override // io.github.classgraph.Resource
            public Set<PosixFilePermission> getPosixFilePermissions() {
                return null;
            }

            @Override // io.github.classgraph.Resource
            public ByteBuffer read() {
                if (ClasspathElementModule.this.skipClasspathElement) {
                    throw new IOException("Module could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                try {
                    this.moduleReaderProxy = (ModuleReaderProxy) ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
                    this.byteBuffer = this.moduleReaderProxy.read(str);
                    this.length = this.byteBuffer.remaining();
                    return this.byteBuffer;
                } catch (OutOfMemoryError | SecurityException e) {
                    close();
                    throw new IOException("Could not open " + this, e);
                }
            }

            @Override // io.github.classgraph.Resource
            ClassfileReader openClassfile() {
                return new ClassfileReader(open(), this);
            }

            @Override // io.github.classgraph.Resource
            public URI getURI() {
                try {
                    ModuleReaderProxy moduleReaderProxy = (ModuleReaderProxy) ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
                    try {
                        return moduleReaderProxy.find(str);
                    } finally {
                        ClasspathElementModule.this.moduleReaderProxyRecycler.recycle(moduleReaderProxy);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override // io.github.classgraph.Resource
            public InputStream open() {
                if (ClasspathElementModule.this.skipClasspathElement) {
                    throw new IOException("Module could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                try {
                    this.moduleReaderProxy = (ModuleReaderProxy) ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
                    this.inputStream = new ProxyingInputStream(this.moduleReaderProxy.open(str)) { // from class: io.github.classgraph.ClasspathElementModule.1.1
                        @Override // nonapi.io.github.classgraph.utils.ProxyingInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                        public void close() {
                            super.close();
                            try {
                                this.close();
                            } catch (Exception unused) {
                            }
                        }
                    };
                    this.length = -1L;
                    return this.inputStream;
                } catch (SecurityException e) {
                    close();
                    throw new IOException("Could not open " + this, e);
                }
            }

            @Override // io.github.classgraph.Resource
            public byte[] load() {
                byte[] bArr;
                Throwable th = null;
                try {
                    read();
                    if (this.byteBuffer.hasArray() && this.byteBuffer.position() == 0 && this.byteBuffer.limit() == this.byteBuffer.capacity()) {
                        bArr = this.byteBuffer.array();
                    } else {
                        bArr = new byte[this.byteBuffer.remaining()];
                        this.byteBuffer.get(bArr);
                    }
                    this.length = bArr.length;
                    byte[] bArr2 = bArr;
                    if (0 != 0) {
                        try {
                            close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        close();
                    }
                    return bArr2;
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
                    if (this.moduleReaderProxy != null) {
                        if (this.byteBuffer != null) {
                            this.moduleReaderProxy.release(this.byteBuffer);
                            this.byteBuffer = null;
                        }
                        ClasspathElementModule.this.moduleReaderProxyRecycler.recycle(this.moduleReaderProxy);
                        this.moduleReaderProxy = null;
                    }
                    super.close();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public Resource getResource(String str) {
        if (this.allResourcePaths.contains(str)) {
            return newResource(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r11v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r11v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x028b: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE], block:B:156:0x028b */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0290: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:158:0x0290 */
    /* JADX WARN: Type inference failed for: r10v0, types: [nonapi.io.github.classgraph.recycler.RecycleOnClose] */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Throwable] */
    @Override // io.github.classgraph.ClasspathElement
    public void scanPaths(LogNode logNode) {
        if (this.skipClasspathElement) {
            return;
        }
        if (this.scanned.getAndSet(true)) {
            throw new IllegalArgumentException("Already scanned classpath element " + this);
        }
        LogNode log = logNode == null ? null : log(this.classpathElementIdx, "Scanning module " + this.moduleRef.getName(), logNode);
        boolean z = VersionFinder.JAVA_MAJOR_VERSION >= 9 && getModuleName() != null;
        try {
            try {
                RecycleOnClose<ModuleReaderProxy, IOException> acquireRecycleOnClose = this.moduleReaderProxyRecycler.acquireRecycleOnClose();
                Throwable th = null;
                try {
                    List<String> list = acquireRecycleOnClose.get().list();
                    CollectionUtils.sortIfNotEmpty(list);
                    String str = null;
                    ScanSpec.ScanSpecPathMatch scanSpecPathMatch = null;
                    for (String str2 : list) {
                        if (!str2.endsWith("/")) {
                            if (this.scanSpec.enableMultiReleaseVersions || !str2.startsWith(LogicalZipFile.MULTI_RELEASE_PATH_PREFIX)) {
                                if (!z || str2.indexOf(47) >= 0 || !str2.endsWith(".class") || str2.equals(Plugin.Engine.MODULE_INFO)) {
                                    if (checkResourcePathAcceptReject(str2, logNode)) {
                                        int lastIndexOf = str2.lastIndexOf(47);
                                        String substring = lastIndexOf < 0 ? "/" : str2.substring(0, lastIndexOf + 1);
                                        String str3 = substring;
                                        ScanSpec.ScanSpecPathMatch dirAcceptMatchStatus = (str == null || (!substring.equals(str))) ? this.scanSpec.dirAcceptMatchStatus(str3) : scanSpecPathMatch;
                                        str = str3;
                                        scanSpecPathMatch = dirAcceptMatchStatus;
                                        if (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX) {
                                            if (log != null) {
                                                log.log("Skipping rejected path: " + str2);
                                            }
                                        } else if (this.allResourcePaths.add(str2)) {
                                            if (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX || dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_PATH || (dirAcceptMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE && this.scanSpec.classfileIsSpecificallyAccepted(str2))) {
                                                addAcceptedResource(newResource(str2), dirAcceptMatchStatus, false, log);
                                            } else if (this.scanSpec.enableClassInfo && str2.equals(Plugin.Engine.MODULE_INFO)) {
                                                addAcceptedResource(newResource(str2), dirAcceptMatchStatus, true, log);
                                            }
                                        }
                                    }
                                }
                            } else if (log != null) {
                                log.log("Found unexpected nested versioned entry in module -- skipping: " + str2);
                            }
                        }
                    }
                    File locationFile = this.moduleRef.getLocationFile();
                    if (locationFile != null && locationFile.exists()) {
                        this.fileToLastModified.put(locationFile, Long.valueOf(locationFile.lastModified()));
                    }
                    if (acquireRecycleOnClose != null) {
                        if (0 != 0) {
                            try {
                                acquireRecycleOnClose.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            acquireRecycleOnClose.close();
                        }
                    }
                } catch (SecurityException e) {
                    if (log != null) {
                        log.log("Could not get resource list for module " + this.moduleRef.getName(), e);
                    }
                    if (acquireRecycleOnClose != null) {
                        if (0 == 0) {
                            acquireRecycleOnClose.close();
                            return;
                        }
                        try {
                            acquireRecycleOnClose.close();
                            return;
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                            return;
                        }
                    }
                    return;
                }
            } finally {
            }
        } catch (IOException e2) {
            if (log != null) {
                log.log("Exception opening module " + this.moduleRef.getName(), e2);
            }
            this.skipClasspathElement = true;
        }
        finishScanPaths(log);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModuleRef getModuleRef() {
        return this.moduleRef;
    }

    @Override // io.github.classgraph.ClasspathElement
    public String getModuleName() {
        String name = this.moduleRef.getName();
        String str = name;
        if (name == null || str.isEmpty()) {
            str = this.moduleNameFromModuleDescriptor;
        }
        if (str == null || str.isEmpty()) {
            return null;
        }
        return str;
    }

    private String getModuleNameOrEmpty() {
        String moduleName = getModuleName();
        return moduleName == null ? "" : moduleName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public URI getURI() {
        URI location = this.moduleRef.getLocation();
        if (location == null) {
            throw new IllegalArgumentException("Module " + getModuleName() + " has a null location");
        }
        return location;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public List<URI> getAllURIs() {
        return Collections.singletonList(getURI());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClasspathElement
    public File getFile() {
        try {
            URI location = this.moduleRef.getLocation();
            if (location != null && !location.getScheme().equals("jrt")) {
                File file = new File(location);
                if (file.exists()) {
                    return file;
                }
                return null;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString() {
        return this.moduleRef.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClasspathElementModule)) {
            return false;
        }
        return getModuleNameOrEmpty().equals(((ClasspathElementModule) obj).getModuleNameOrEmpty());
    }

    public int hashCode() {
        return getModuleNameOrEmpty().hashCode();
    }
}
