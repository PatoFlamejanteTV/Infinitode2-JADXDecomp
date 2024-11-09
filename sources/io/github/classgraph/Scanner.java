package io.github.classgraph;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.Classfile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import net.bytebuddy.description.type.PackageDescription;
import nonapi.io.github.classgraph.classpath.ClasspathFinder;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.classpath.ModuleFinder;
import nonapi.io.github.classgraph.concurrency.AutoCloseableExecutorService;
import nonapi.io.github.classgraph.concurrency.InterruptionChecker;
import nonapi.io.github.classgraph.concurrency.SingletonMap;
import nonapi.io.github.classgraph.concurrency.WorkQueue;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:io/github/classgraph/Scanner.class */
public class Scanner implements Callable<ScanResult> {
    private final ScanSpec scanSpec;
    public boolean performScan;
    private final NestedJarHandler nestedJarHandler;
    private final ExecutorService executorService;
    private final InterruptionChecker interruptionChecker;
    private final int numParallelTasks;
    private final ClassGraph.ScanResultProcessor scanResultProcessor;
    private final ClassGraph.FailureHandler failureHandler;
    private final LogNode topLevelLog;
    private final ClasspathFinder classpathFinder;
    private final List<ClasspathElementModule> moduleOrder;
    private final SingletonMap<Object, ClasspathElement, IOException> classpathEntryObjToClasspathEntrySingletonMap = new SingletonMap<Object, ClasspathElement, IOException>() { // from class: io.github.classgraph.Scanner.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap
        public ClasspathElement newInstance(Object obj, LogNode logNode) {
            throw new IOException("Should not reach here");
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public Scanner(boolean z, ScanSpec scanSpec, ExecutorService executorService, int i, ClassGraph.ScanResultProcessor scanResultProcessor, ClassGraph.FailureHandler failureHandler, ReflectionUtils reflectionUtils, LogNode logNode) {
        this.scanSpec = scanSpec;
        this.performScan = z;
        scanSpec.sortPrefixes();
        scanSpec.log(logNode);
        if (logNode != null) {
            if (scanSpec.pathAcceptReject != null && scanSpec.packagePrefixAcceptReject.isSpecificallyAccepted("")) {
                logNode.log("Note: There is no need to accept the root package (\"\") -- not accepting anything will have the same effect of causing all packages to be scanned");
            }
            logNode.log("Number of worker threads: " + i);
        }
        this.executorService = executorService;
        this.interruptionChecker = executorService instanceof AutoCloseableExecutorService ? ((AutoCloseableExecutorService) executorService).interruptionChecker : new InterruptionChecker();
        this.nestedJarHandler = new NestedJarHandler(scanSpec, this.interruptionChecker, reflectionUtils);
        this.numParallelTasks = i;
        this.scanResultProcessor = scanResultProcessor;
        this.failureHandler = failureHandler;
        this.topLevelLog = logNode;
        LogNode log = logNode == null ? null : logNode.log("Finding classpath");
        this.classpathFinder = new ClasspathFinder(scanSpec, reflectionUtils, log);
        try {
            this.moduleOrder = new ArrayList();
            ModuleFinder moduleFinder = this.classpathFinder.getModuleFinder();
            if (moduleFinder != null) {
                List<ModuleRef> systemModuleRefs = moduleFinder.getSystemModuleRefs();
                ClassLoader[] classLoaderOrderRespectingParentDelegation = this.classpathFinder.getClassLoaderOrderRespectingParentDelegation();
                ClassLoader classLoader = (classLoaderOrderRespectingParentDelegation == null || classLoaderOrderRespectingParentDelegation.length == 0) ? null : classLoaderOrderRespectingParentDelegation[0];
                if (systemModuleRefs != null) {
                    for (ModuleRef moduleRef : systemModuleRefs) {
                        String name = moduleRef.getName();
                        if ((scanSpec.enableSystemJarsAndModules && scanSpec.moduleAcceptReject.acceptAndRejectAreEmpty()) || scanSpec.moduleAcceptReject.isSpecificallyAcceptedAndNotRejected(name)) {
                            ClasspathElementModule classpathElementModule = new ClasspathElementModule(moduleRef, this.nestedJarHandler.moduleRefToModuleReaderProxyRecyclerMap, new ClasspathEntryWorkUnit(null, classLoader, null, this.moduleOrder.size(), ""), scanSpec);
                            this.moduleOrder.add(classpathElementModule);
                            classpathElementModule.open(null, log);
                        } else if (log != null) {
                            log.log("Skipping non-accepted or rejected system module: " + name);
                        }
                    }
                }
                List<ModuleRef> nonSystemModuleRefs = moduleFinder.getNonSystemModuleRefs();
                if (nonSystemModuleRefs != null) {
                    for (ModuleRef moduleRef2 : nonSystemModuleRefs) {
                        String name2 = moduleRef2.getName();
                        String str = name2 == null ? "" : name2;
                        if (scanSpec.moduleAcceptReject.isAcceptedAndNotRejected(str)) {
                            ClasspathElementModule classpathElementModule2 = new ClasspathElementModule(moduleRef2, this.nestedJarHandler.moduleRefToModuleReaderProxyRecyclerMap, new ClasspathEntryWorkUnit(null, classLoader, null, this.moduleOrder.size(), ""), scanSpec);
                            this.moduleOrder.add(classpathElementModule2);
                            classpathElementModule2.open(null, log);
                        } else if (log != null) {
                            log.log("Skipping non-accepted or rejected module: " + str);
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            this.nestedJarHandler.close(null);
            throw e;
        }
    }

    private static void findClasspathOrderRec(ClasspathElement classpathElement, Set<ClasspathElement> set, List<ClasspathElement> list) {
        if (set.add(classpathElement)) {
            if (!classpathElement.skipClasspathElement) {
                list.add(classpathElement);
            }
            Iterator it = CollectionUtils.sortCopy(classpathElement.childClasspathElements).iterator();
            while (it.hasNext()) {
                findClasspathOrderRec((ClasspathElement) it.next(), set, list);
            }
        }
    }

    private List<ClasspathElement> findClasspathOrder(Set<ClasspathElement> set) {
        List sortCopy = CollectionUtils.sortCopy(set);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        Iterator it = sortCopy.iterator();
        while (it.hasNext()) {
            findClasspathOrderRec((ClasspathElement) it.next(), hashSet, arrayList);
        }
        return arrayList;
    }

    private <W> void processWorkUnits(Collection<W> collection, LogNode logNode, WorkQueue.WorkUnitProcessor<W> workUnitProcessor) {
        WorkQueue.runWorkQueue(collection, this.executorService, this.interruptionChecker, this.numParallelTasks, logNode, workUnitProcessor);
        if (logNode != null) {
            logNode.addElapsedTime();
        }
        this.interruptionChecker.check();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Scanner$ClasspathEntryWorkUnit.class */
    public static class ClasspathEntryWorkUnit {
        Object classpathEntryObj;
        final ClassLoader classLoader;
        final ClasspathElement parentClasspathElement;
        final int classpathElementIdxWithinParent;
        final String packageRootPrefix;

        public ClasspathEntryWorkUnit(Object obj, ClassLoader classLoader, ClasspathElement classpathElement, int i, String str) {
            this.classpathEntryObj = obj;
            this.classLoader = classLoader;
            this.parentClasspathElement = classpathElement;
            this.classpathElementIdxWithinParent = i;
            this.packageRootPrefix = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object normalizeClasspathEntry(Object obj) {
        if (obj == null) {
            throw new IOException("Got null classpath entry object");
        }
        Object obj2 = obj;
        if (!(obj instanceof Path)) {
            obj2 = FastPathResolver.resolve(FileUtils.currDirPath(), obj2.toString());
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            boolean matches = JarUtils.URL_SCHEME_PATTERN.matcher(str).matches();
            boolean contains = str.contains("!");
            if (matches || contains) {
                String replace = str.replace(SequenceUtils.SPACE, "%20").replace("#", "%23");
                if (!matches) {
                    replace = "file:" + replace;
                }
                if (contains) {
                    replace = ("jar:" + replace).replaceAll("!([^/])", "!/$1");
                }
                try {
                    URL url = new URL(replace);
                    obj2 = url;
                    if (!contains) {
                        try {
                            String protocol = url.getProtocol();
                            if (!"http".equals(protocol) && !"https".equals(protocol)) {
                                obj2 = Paths.get(url.toURI());
                            }
                        } catch (IllegalArgumentException | SecurityException | URISyntaxException unused) {
                        } catch (FileSystemNotFoundException unused2) {
                        }
                    }
                } catch (MalformedURLException unused3) {
                    try {
                        URI uri = new URI(replace);
                        obj2 = uri;
                        String scheme = uri.getScheme();
                        if (!"http".equals(scheme) && !"https".equals(scheme)) {
                            obj2 = Paths.get(uri);
                        }
                    } catch (IllegalArgumentException | SecurityException unused4) {
                    } catch (URISyntaxException e) {
                        throw new IOException("Malformed URI: " + obj2 + " : " + e);
                    } catch (FileSystemNotFoundException unused5) {
                    }
                }
            }
            if (obj2 instanceof String) {
                try {
                    obj2 = new File((String) obj2).toPath();
                } catch (Exception unused6) {
                    try {
                        obj2 = Paths.get((String) obj2, new String[0]);
                    } catch (InvalidPathException e2) {
                        throw new IOException("Malformed path: " + obj + " : " + e2);
                    }
                }
            }
        }
        if (obj2 instanceof Path) {
            try {
                obj2 = ((Path) obj2).toRealPath(new LinkOption[0]);
            } catch (IOException | SecurityException unused7) {
            }
        }
        return obj2;
    }

    private WorkQueue.WorkUnitProcessor<ClasspathEntryWorkUnit> newClasspathEntryWorkUnitProcessor(final Set<ClasspathElement> set, final Set<ClasspathElement> set2) {
        return new WorkQueue.WorkUnitProcessor<ClasspathEntryWorkUnit>() { // from class: io.github.classgraph.Scanner.2
            @Override // nonapi.io.github.classgraph.concurrency.WorkQueue.WorkUnitProcessor
            public void processWorkUnit(final ClasspathEntryWorkUnit classpathEntryWorkUnit, final WorkQueue<ClasspathEntryWorkUnit> workQueue, final LogNode logNode) {
                boolean z;
                try {
                    classpathEntryWorkUnit.classpathEntryObj = Scanner.normalizeClasspathEntry(classpathEntryWorkUnit.classpathEntryObj);
                    if ((classpathEntryWorkUnit.classpathEntryObj instanceof URL) || (classpathEntryWorkUnit.classpathEntryObj instanceof URI)) {
                        z = true;
                    } else if (classpathEntryWorkUnit.classpathEntryObj instanceof Path) {
                        Path path = (Path) classpathEntryWorkUnit.classpathEntryObj;
                        if ("JrtFileSystem".equals(path.getFileSystem().getClass().getSimpleName())) {
                            throw new IOException("Ignoring JrtFS filesystem path (modules are scanned using the JPMS API): " + path);
                        }
                        if (!FileUtils.canRead(path)) {
                            throw new IOException("Cannot read path: " + path);
                        }
                        BasicFileAttributes readAttributes = Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
                        if (readAttributes.isRegularFile()) {
                            z = true;
                        } else if (readAttributes.isDirectory()) {
                            z = false;
                        } else {
                            throw new IOException("Not a file or directory: " + path);
                        }
                    } else {
                        throw new IOException("Got unexpected classpath entry object type " + classpathEntryWorkUnit.classpathEntryObj.getClass().getName() + " : " + classpathEntryWorkUnit.classpathEntryObj);
                    }
                    final boolean z2 = z;
                    Scanner.this.classpathEntryObjToClasspathEntrySingletonMap.get(classpathEntryWorkUnit.classpathEntryObj, logNode, new SingletonMap.NewInstanceFactory<ClasspathElement, IOException>() { // from class: io.github.classgraph.Scanner.2.1
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceFactory
                        public ClasspathElement newInstance() {
                            ClasspathElement classpathElementZip = z2 ? new ClasspathElementZip(classpathEntryWorkUnit, Scanner.this.nestedJarHandler, Scanner.this.scanSpec) : new ClasspathElementDir(classpathEntryWorkUnit, Scanner.this.nestedJarHandler, Scanner.this.scanSpec);
                            set.add(classpathElementZip);
                            classpathElementZip.open(workQueue, logNode == null ? null : logNode.log(classpathElementZip.getURI().toString(), "Opening classpath element " + classpathElementZip));
                            if (classpathEntryWorkUnit.parentClasspathElement != null) {
                                classpathEntryWorkUnit.parentClasspathElement.childClasspathElements.add(classpathElementZip);
                            } else {
                                set2.add(classpathElementZip);
                            }
                            return classpathElementZip;
                        }
                    });
                } catch (Exception e) {
                    if (logNode != null) {
                        logNode.log("Skipping invalid classpath entry " + classpathEntryWorkUnit.classpathEntryObj + " : " + (e.getCause() == null ? e : e.getCause()));
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Scanner$ClassfileScanWorkUnit.class */
    public static class ClassfileScanWorkUnit {
        private final ClasspathElement classpathElement;
        private final Resource classfileResource;
        private final boolean isExternalClass;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ClassfileScanWorkUnit(ClasspathElement classpathElement, Resource resource, boolean z) {
            this.classpathElement = classpathElement;
            this.classfileResource = resource;
            this.isExternalClass = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/Scanner$ClassfileScannerWorkUnitProcessor.class */
    public static class ClassfileScannerWorkUnitProcessor implements WorkQueue.WorkUnitProcessor<ClassfileScanWorkUnit> {
        private final ScanSpec scanSpec;
        private final List<ClasspathElement> classpathOrder;
        private final Set<String> acceptedClassNamesFound;
        private final Queue<Classfile> scannedClassfiles;
        private final Set<String> classNamesScheduledForExtendedScanning = Collections.newSetFromMap(new ConcurrentHashMap());
        private final ConcurrentHashMap<String, String> stringInternMap = new ConcurrentHashMap<>();

        public ClassfileScannerWorkUnitProcessor(ScanSpec scanSpec, List<ClasspathElement> list, Set<String> set, Queue<Classfile> queue) {
            this.scanSpec = scanSpec;
            this.classpathOrder = list;
            this.acceptedClassNamesFound = set;
            this.scannedClassfiles = queue;
        }

        @Override // nonapi.io.github.classgraph.concurrency.WorkQueue.WorkUnitProcessor
        public void processWorkUnit(ClassfileScanWorkUnit classfileScanWorkUnit, WorkQueue<ClassfileScanWorkUnit> workQueue, LogNode logNode) {
            LogNode log = classfileScanWorkUnit.classfileResource.scanLog == null ? null : classfileScanWorkUnit.classfileResource.scanLog.log(classfileScanWorkUnit.classfileResource.getPath(), "Parsing classfile");
            try {
                this.scannedClassfiles.add(new Classfile(classfileScanWorkUnit.classpathElement, this.classpathOrder, this.acceptedClassNamesFound, this.classNamesScheduledForExtendedScanning, classfileScanWorkUnit.classfileResource.getPath(), classfileScanWorkUnit.classfileResource, classfileScanWorkUnit.isExternalClass, this.stringInternMap, workQueue, this.scanSpec, log));
                if (log != null) {
                    log.addElapsedTime();
                }
            } catch (Classfile.ClassfileFormatException e) {
                if (log != null) {
                    log.log(classfileScanWorkUnit.classfileResource.getPath(), "Invalid classfile: " + e.getMessage());
                    log.addElapsedTime();
                }
            } catch (Classfile.SkipClassException e2) {
                if (log != null) {
                    log.log(classfileScanWorkUnit.classfileResource.getPath(), "Skipping classfile: " + e2.getMessage());
                    log.addElapsedTime();
                }
            } catch (IOException e3) {
                if (log != null) {
                    log.log(classfileScanWorkUnit.classfileResource.getPath(), "Could not read classfile: " + e3);
                    log.addElapsedTime();
                }
            } catch (Exception e4) {
                if (log != null) {
                    log.log(classfileScanWorkUnit.classfileResource.getPath(), "Could not read classfile", e4);
                    log.addElapsedTime();
                }
            }
        }
    }

    private void findNestedClasspathElements(List<AbstractMap.SimpleEntry<String, ClasspathElement>> list, LogNode logNode) {
        char charAt;
        CollectionUtils.sortIfNotEmpty(list, new Comparator<AbstractMap.SimpleEntry<String, ClasspathElement>>() { // from class: io.github.classgraph.Scanner.3
            @Override // java.util.Comparator
            public int compare(AbstractMap.SimpleEntry<String, ClasspathElement> simpleEntry, AbstractMap.SimpleEntry<String, ClasspathElement> simpleEntry2) {
                return simpleEntry.getKey().compareTo(simpleEntry2.getKey());
            }
        });
        for (int i = 0; i < list.size(); i++) {
            AbstractMap.SimpleEntry<String, ClasspathElement> simpleEntry = list.get(i);
            String key = simpleEntry.getKey();
            int length = key.length();
            for (int i2 = i + 1; i2 < list.size(); i2++) {
                String key2 = list.get(i2).getKey();
                int length2 = key2.length();
                boolean z = false;
                if (key2.startsWith(key) && length2 > length && ((charAt = key2.charAt(length)) == '/' || charAt == '!')) {
                    String substring = key2.substring(length + 1);
                    if (substring.indexOf(33) < 0) {
                        z = true;
                        ClasspathElement value = simpleEntry.getValue();
                        if (value.nestedClasspathRootPrefixes == null) {
                            value.nestedClasspathRootPrefixes = new ArrayList();
                        }
                        value.nestedClasspathRootPrefixes.add(substring + "/");
                        if (logNode != null) {
                            logNode.log(key + " is a prefix of the nested element " + key2);
                        }
                    }
                }
                if (z) {
                }
            }
        }
    }

    private void preprocessClasspathElementsByType(List<ClasspathElement> list, LogNode logNode) {
        String path;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ClasspathElement classpathElement : list) {
            if (!(classpathElement instanceof ClasspathElementDir)) {
                if (classpathElement instanceof ClasspathElementZip) {
                    ClasspathElementZip classpathElementZip = (ClasspathElementZip) classpathElement;
                    arrayList2.add(new AbstractMap.SimpleEntry<>(classpathElementZip.getZipFilePath(), classpathElement));
                    if (classpathElementZip.logicalZipFile != null) {
                        if (classpathElementZip.logicalZipFile.addExportsManifestEntryValue != null) {
                            for (String str : JarUtils.smartPathSplit(classpathElementZip.logicalZipFile.addExportsManifestEntryValue, ' ', this.scanSpec)) {
                                this.scanSpec.modulePathInfo.addExports.add(str + "=ALL-UNNAMED");
                            }
                        }
                        if (classpathElementZip.logicalZipFile.addOpensManifestEntryValue != null) {
                            for (String str2 : JarUtils.smartPathSplit(classpathElementZip.logicalZipFile.addOpensManifestEntryValue, ' ', this.scanSpec)) {
                                this.scanSpec.modulePathInfo.addOpens.add(str2 + "=ALL-UNNAMED");
                            }
                        }
                        if (classpathElementZip.logicalZipFile.automaticModuleNameManifestEntryValue != null) {
                            classpathElementZip.moduleNameFromManifestFile = classpathElementZip.logicalZipFile.automaticModuleNameManifestEntryValue;
                        }
                    }
                }
            } else {
                File file = classpathElement.getFile();
                if (file == null) {
                    path = classpathElement.toString();
                } else {
                    path = file.getPath();
                }
                arrayList.add(new AbstractMap.SimpleEntry<>(path, classpathElement));
            }
        }
        findNestedClasspathElements(arrayList, logNode);
        findNestedClasspathElements(arrayList2, logNode);
    }

    private void maskClassfiles(List<ClasspathElement> list, LogNode logNode) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).maskClassfiles(i, hashSet, logNode);
        }
        if (logNode != null) {
            logNode.addElapsedTime();
        }
    }

    private ScanResult performScan(List<ClasspathElement> list, List<String> list2, ClasspathFinder classpathFinder) {
        if (this.scanSpec.enableClassInfo) {
            maskClassfiles(list, this.topLevelLog == null ? null : this.topLevelLog.log("Masking classfiles"));
        }
        HashMap hashMap = new HashMap();
        Iterator<ClasspathElement> it = list.iterator();
        while (it.hasNext()) {
            hashMap.putAll(it.next().fileToLastModified);
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        if (this.scanSpec.enableClassInfo) {
            ArrayList arrayList = new ArrayList();
            HashSet hashSet = new HashSet();
            for (ClasspathElement classpathElement : list) {
                for (Resource resource : classpathElement.acceptedClassfileResources) {
                    String classfilePathToClassName = JarUtils.classfilePathToClassName(resource.getPath());
                    if (!hashSet.add(classfilePathToClassName) && !classfilePathToClassName.equals("module-info") && !classfilePathToClassName.equals(PackageDescription.PACKAGE_CLASS_NAME) && !classfilePathToClassName.endsWith(".package-info")) {
                        throw new IllegalArgumentException("Class " + classfilePathToClassName + " should not have been scheduled more than once for scanning due to classpath masking -- please report this bug at: https://github.com/classgraph/classgraph/issues");
                    }
                    arrayList.add(new ClassfileScanWorkUnit(classpathElement, resource, false));
                }
            }
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            processWorkUnits(arrayList, this.topLevelLog == null ? null : this.topLevelLog.log("Scanning classfiles"), new ClassfileScannerWorkUnitProcessor(this.scanSpec, list, Collections.unmodifiableSet(hashSet), concurrentLinkedQueue));
            LogNode log = this.topLevelLog == null ? null : this.topLevelLog.log("Linking related classfiles");
            while (!concurrentLinkedQueue.isEmpty()) {
                ((Classfile) concurrentLinkedQueue.remove()).link(concurrentHashMap, hashMap2, hashMap3);
            }
            if (log != null) {
                log.addElapsedTime();
            }
        } else if (this.topLevelLog != null) {
            this.topLevelLog.log("Classfile scanning is disabled");
        }
        return new ScanResult(this.scanSpec, list, list2, classpathFinder, concurrentHashMap, hashMap2, hashMap3, hashMap, this.nestedJarHandler, this.topLevelLog);
    }

    private ScanResult openClasspathElementsThenScan() {
        ArrayList arrayList = new ArrayList();
        for (ClasspathOrder.ClasspathEntry classpathEntry : this.classpathFinder.getClasspathOrder().getOrder()) {
            arrayList.add(new ClasspathEntryWorkUnit(classpathEntry.classpathEntryObj, classpathEntry.classLoader, null, arrayList.size(), ""));
        }
        Set<ClasspathElement> newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        Set<ClasspathElement> newSetFromMap2 = Collections.newSetFromMap(new ConcurrentHashMap());
        processWorkUnits(arrayList, this.topLevelLog == null ? null : this.topLevelLog.log("Opening classpath elements"), newClasspathEntryWorkUnitProcessor(newSetFromMap, newSetFromMap2));
        List<ClasspathElement> findClasspathOrder = findClasspathOrder(newSetFromMap2);
        preprocessClasspathElementsByType(findClasspathOrder, this.topLevelLog == null ? null : this.topLevelLog.log("Finding nested classpath elements"));
        LogNode log = this.topLevelLog == null ? null : this.topLevelLog.log("Final classpath element order:");
        int size = this.moduleOrder.size() + findClasspathOrder.size();
        ArrayList<ClasspathElement> arrayList2 = new ArrayList(size);
        ArrayList arrayList3 = new ArrayList(size);
        int i = 0;
        for (ClasspathElementModule classpathElementModule : this.moduleOrder) {
            int i2 = i;
            i++;
            classpathElementModule.classpathElementIdx = i2;
            arrayList2.add(classpathElementModule);
            arrayList3.add(classpathElementModule.toString());
            if (log != null) {
                log.log(classpathElementModule.getModuleRef().toString());
            }
        }
        for (ClasspathElement classpathElement : findClasspathOrder) {
            int i3 = i;
            i++;
            classpathElement.classpathElementIdx = i3;
            arrayList2.add(classpathElement);
            arrayList3.add(classpathElement.toString());
            if (log != null) {
                log.log(classpathElement.toString());
            }
        }
        processWorkUnits(arrayList2, this.topLevelLog == null ? null : this.topLevelLog.log("Scanning classpath elements"), new WorkQueue.WorkUnitProcessor<ClasspathElement>() { // from class: io.github.classgraph.Scanner.4
            @Override // nonapi.io.github.classgraph.concurrency.WorkQueue.WorkUnitProcessor
            public void processWorkUnit(ClasspathElement classpathElement2, WorkQueue<ClasspathElement> workQueue, LogNode logNode) {
                classpathElement2.scanPaths(logNode);
            }
        });
        ArrayList arrayList4 = arrayList2;
        if (!this.scanSpec.classpathElementResourcePathAcceptReject.acceptIsEmpty()) {
            arrayList4 = new ArrayList(arrayList2.size());
            for (ClasspathElement classpathElement2 : arrayList2) {
                if (classpathElement2.containsSpecificallyAcceptedClasspathElementResourcePath) {
                    arrayList4.add(classpathElement2);
                }
            }
        }
        if (this.performScan) {
            return performScan(arrayList4, arrayList3, this.classpathFinder);
        }
        if (this.topLevelLog != null) {
            this.topLevelLog.log("Only returning classpath elements (not performing a scan)");
        }
        return new ScanResult(this.scanSpec, arrayList4, arrayList3, this.classpathFinder, null, null, null, null, this.nestedJarHandler, this.topLevelLog);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public ScanResult call() {
        String str;
        ScanResult scanResult = null;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = this.scanSpec.removeTemporaryFilesAfterScan;
        try {
            scanResult = openClasspathElementsThenScan();
            if (this.topLevelLog != null) {
                this.topLevelLog.log("~", String.format("Total time: %.3f sec", Double.valueOf((System.currentTimeMillis() - currentTimeMillis) * 0.001d)));
                this.topLevelLog.flush();
            }
            if (this.scanResultProcessor != null) {
                try {
                    this.scanResultProcessor.processScanResult(scanResult);
                    scanResult.close();
                } catch (Exception e) {
                    scanResult.close();
                    throw new ExecutionException(e);
                }
            }
        } catch (Throwable th) {
            if (this.topLevelLog != null) {
                LogNode logNode = this.topLevelLog;
                if ((th instanceof InterruptedException) || (th instanceof CancellationException)) {
                    str = "Scan interrupted or canceled";
                } else {
                    str = ((th instanceof ExecutionException) || (th instanceof RuntimeException)) ? "Uncaught exception during scan" : th.getMessage();
                }
                logNode.log("~", str, InterruptionChecker.getCause(th));
                this.topLevelLog.flush();
            }
            z = true;
            this.interruptionChecker.interrupt();
            if (this.failureHandler == null) {
                this.nestedJarHandler.close(this.topLevelLog);
                throw th;
            }
            try {
                this.failureHandler.onFailure(th);
            } catch (Exception e2) {
                if (this.topLevelLog != null) {
                    this.topLevelLog.log("~", "The failure handler threw an exception:", e2);
                    this.topLevelLog.flush();
                }
                ExecutionException executionException = new ExecutionException("Exception while calling failure handler", e2);
                executionException.addSuppressed(th);
                this.nestedJarHandler.close(this.topLevelLog);
                throw executionException;
            }
        }
        if (z) {
            this.nestedJarHandler.close(this.topLevelLog);
        }
        return scanResult;
    }
}
