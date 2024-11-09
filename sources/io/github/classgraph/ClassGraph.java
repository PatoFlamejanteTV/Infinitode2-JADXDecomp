package io.github.classgraph;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import nonapi.io.github.classgraph.classpath.SystemJarFinder;
import nonapi.io.github.classgraph.concurrency.AutoCloseableExecutorService;
import nonapi.io.github.classgraph.concurrency.InterruptionChecker;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.AcceptReject;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraph.class */
public class ClassGraph {
    static final int DEFAULT_NUM_WORKER_THREADS = Math.max(2, (int) Math.ceil(Math.min(4.0d, Runtime.getRuntime().availableProcessors() * 0.75d) + (Runtime.getRuntime().availableProcessors() * 1.25d)));
    public static CircumventEncapsulationMethod CIRCUMVENT_ENCAPSULATION = CircumventEncapsulationMethod.NONE;
    private LogNode topLevelLog;
    ScanSpec scanSpec = new ScanSpec();
    private final ReflectionUtils reflectionUtils = new ReflectionUtils();

    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraph$CircumventEncapsulationMethod.class */
    public enum CircumventEncapsulationMethod {
        NONE,
        NARCISSUS,
        JVM_DRIVER
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraph$ClasspathElementFilter.class */
    public interface ClasspathElementFilter {
        boolean includeClasspathElement(String str);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraph$ClasspathElementURLFilter.class */
    public interface ClasspathElementURLFilter {
        boolean includeClasspathElement(URL url);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraph$FailureHandler.class */
    public interface FailureHandler {
        void onFailure(Throwable th);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraph$ScanResultProcessor.class */
    public interface ScanResultProcessor {
        void processScanResult(ScanResult scanResult);
    }

    public ClassGraph() {
        ScanResult.init(this.reflectionUtils);
    }

    public static String getVersion() {
        return VersionFinder.getVersion();
    }

    public ClassGraph verbose() {
        if (this.topLevelLog == null) {
            this.topLevelLog = new LogNode();
        }
        return this;
    }

    public ClassGraph verbose(boolean z) {
        if (z) {
            verbose();
        }
        return this;
    }

    public ClassGraph enableAllInfo() {
        enableClassInfo();
        enableFieldInfo();
        enableMethodInfo();
        enableAnnotationInfo();
        enableStaticFinalFieldConstantInitializerValues();
        ignoreClassVisibility();
        ignoreFieldVisibility();
        ignoreMethodVisibility();
        return this;
    }

    public ClassGraph enableClassInfo() {
        this.scanSpec.enableClassInfo = true;
        this.scanSpec.enableMultiReleaseVersions = false;
        return this;
    }

    public ClassGraph ignoreClassVisibility() {
        enableClassInfo();
        this.scanSpec.ignoreClassVisibility = true;
        return this;
    }

    public ClassGraph enableMethodInfo() {
        enableClassInfo();
        this.scanSpec.enableMethodInfo = true;
        return this;
    }

    public ClassGraph ignoreMethodVisibility() {
        enableClassInfo();
        enableMethodInfo();
        this.scanSpec.ignoreMethodVisibility = true;
        return this;
    }

    public ClassGraph enableFieldInfo() {
        enableClassInfo();
        this.scanSpec.enableFieldInfo = true;
        return this;
    }

    public ClassGraph ignoreFieldVisibility() {
        enableClassInfo();
        enableFieldInfo();
        this.scanSpec.ignoreFieldVisibility = true;
        return this;
    }

    public ClassGraph enableStaticFinalFieldConstantInitializerValues() {
        enableClassInfo();
        enableFieldInfo();
        this.scanSpec.enableStaticFinalFieldConstantInitializerValues = true;
        return this;
    }

    public ClassGraph enableAnnotationInfo() {
        enableClassInfo();
        this.scanSpec.enableAnnotationInfo = true;
        return this;
    }

    public ClassGraph enableInterClassDependencies() {
        enableClassInfo();
        enableFieldInfo();
        enableMethodInfo();
        enableAnnotationInfo();
        ignoreClassVisibility();
        ignoreFieldVisibility();
        ignoreMethodVisibility();
        this.scanSpec.enableInterClassDependencies = true;
        return this;
    }

    public ClassGraph disableRuntimeInvisibleAnnotations() {
        enableClassInfo();
        this.scanSpec.disableRuntimeInvisibleAnnotations = true;
        return this;
    }

    public ClassGraph disableJarScanning() {
        this.scanSpec.scanJars = false;
        return this;
    }

    public ClassGraph disableNestedJarScanning() {
        this.scanSpec.scanNestedJars = false;
        return this;
    }

    public ClassGraph disableDirScanning() {
        this.scanSpec.scanDirs = false;
        return this;
    }

    public ClassGraph disableModuleScanning() {
        this.scanSpec.scanModules = false;
        return this;
    }

    public ClassGraph enableExternalClasses() {
        enableClassInfo();
        this.scanSpec.enableExternalClasses = true;
        return this;
    }

    public ClassGraph initializeLoadedClasses() {
        this.scanSpec.initializeLoadedClasses = true;
        return this;
    }

    public ClassGraph removeTemporaryFilesAfterScan() {
        this.scanSpec.removeTemporaryFilesAfterScan = true;
        return this;
    }

    public ClassGraph overrideClasspath(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Can't override classpath with an empty path");
        }
        for (String str2 : JarUtils.smartPathSplit(str, this.scanSpec)) {
            this.scanSpec.addClasspathOverride(str2);
        }
        return this;
    }

    public ClassGraph overrideClasspath(Iterable<?> iterable) {
        if (!iterable.iterator().hasNext()) {
            throw new IllegalArgumentException("Can't override classpath with an empty path");
        }
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            this.scanSpec.addClasspathOverride(it.next());
        }
        return this;
    }

    public ClassGraph overrideClasspath(Object... objArr) {
        if (objArr.length == 0) {
            throw new IllegalArgumentException("Can't override classpath with an empty path");
        }
        for (Object obj : objArr) {
            this.scanSpec.addClasspathOverride(obj);
        }
        return this;
    }

    public ClassGraph filterClasspathElements(ClasspathElementFilter classpathElementFilter) {
        this.scanSpec.filterClasspathElements(classpathElementFilter);
        return this;
    }

    public ClassGraph filterClasspathElementsByURL(ClasspathElementURLFilter classpathElementURLFilter) {
        this.scanSpec.filterClasspathElements(classpathElementURLFilter);
        return this;
    }

    public ClassGraph addClassLoader(ClassLoader classLoader) {
        this.scanSpec.addClassLoader(classLoader);
        return this;
    }

    public ClassGraph overrideClassLoaders(ClassLoader... classLoaderArr) {
        this.scanSpec.overrideClassLoaders(classLoaderArr);
        return this;
    }

    public ClassGraph ignoreParentClassLoaders() {
        this.scanSpec.ignoreParentClassLoaders = true;
        return this;
    }

    public ClassGraph addModuleLayer(Object obj) {
        this.scanSpec.addModuleLayer(obj);
        return this;
    }

    public ClassGraph overrideModuleLayers(Object... objArr) {
        this.scanSpec.overrideModuleLayers(objArr);
        return this;
    }

    public ClassGraph ignoreParentModuleLayers() {
        this.scanSpec.ignoreParentModuleLayers = true;
        return this;
    }

    public ClassGraph acceptPackages(String... strArr) {
        enableClassInfo();
        for (String str : strArr) {
            String normalizePackageOrClassName = AcceptReject.normalizePackageOrClassName(str);
            this.scanSpec.packageAcceptReject.addToAccept(normalizePackageOrClassName);
            String packageNameToPath = AcceptReject.packageNameToPath(normalizePackageOrClassName);
            this.scanSpec.pathAcceptReject.addToAccept(packageNameToPath + "/");
            if (normalizePackageOrClassName.isEmpty()) {
                this.scanSpec.pathAcceptReject.addToAccept("");
            }
            if (!normalizePackageOrClassName.contains("*")) {
                if (normalizePackageOrClassName.isEmpty()) {
                    this.scanSpec.packagePrefixAcceptReject.addToAccept("");
                    this.scanSpec.pathPrefixAcceptReject.addToAccept("");
                } else {
                    this.scanSpec.packagePrefixAcceptReject.addToAccept(normalizePackageOrClassName + ".");
                    this.scanSpec.pathPrefixAcceptReject.addToAccept(packageNameToPath + "/");
                }
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistPackages(String... strArr) {
        return acceptPackages(strArr);
    }

    public ClassGraph acceptPaths(String... strArr) {
        for (String str : strArr) {
            String normalizePath = AcceptReject.normalizePath(str);
            String pathToPackageName = AcceptReject.pathToPackageName(normalizePath);
            this.scanSpec.packageAcceptReject.addToAccept(pathToPackageName);
            this.scanSpec.pathAcceptReject.addToAccept(normalizePath + "/");
            if (normalizePath.isEmpty()) {
                this.scanSpec.pathAcceptReject.addToAccept("");
            }
            if (!normalizePath.contains("*")) {
                if (normalizePath.isEmpty()) {
                    this.scanSpec.packagePrefixAcceptReject.addToAccept("");
                    this.scanSpec.pathPrefixAcceptReject.addToAccept("");
                } else {
                    this.scanSpec.packagePrefixAcceptReject.addToAccept(pathToPackageName + ".");
                    this.scanSpec.pathPrefixAcceptReject.addToAccept(normalizePath + "/");
                }
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistPaths(String... strArr) {
        return acceptPaths(strArr);
    }

    public ClassGraph acceptPackagesNonRecursive(String... strArr) {
        enableClassInfo();
        for (String str : strArr) {
            String normalizePackageOrClassName = AcceptReject.normalizePackageOrClassName(str);
            if (!normalizePackageOrClassName.contains("*")) {
                this.scanSpec.packageAcceptReject.addToAccept(normalizePackageOrClassName);
                this.scanSpec.pathAcceptReject.addToAccept(AcceptReject.packageNameToPath(normalizePackageOrClassName) + "/");
                if (normalizePackageOrClassName.isEmpty()) {
                    this.scanSpec.pathAcceptReject.addToAccept("");
                }
            } else {
                throw new IllegalArgumentException("Cannot use a glob wildcard here: " + normalizePackageOrClassName);
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistPackagesNonRecursive(String... strArr) {
        return acceptPackagesNonRecursive(strArr);
    }

    public ClassGraph acceptPathsNonRecursive(String... strArr) {
        for (String str : strArr) {
            if (str.contains("*")) {
                throw new IllegalArgumentException("Cannot use a glob wildcard here: " + str);
            }
            String normalizePath = AcceptReject.normalizePath(str);
            this.scanSpec.packageAcceptReject.addToAccept(AcceptReject.pathToPackageName(normalizePath));
            this.scanSpec.pathAcceptReject.addToAccept(normalizePath + "/");
            if (normalizePath.isEmpty()) {
                this.scanSpec.pathAcceptReject.addToAccept("");
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistPathsNonRecursive(String... strArr) {
        return acceptPathsNonRecursive(strArr);
    }

    public ClassGraph rejectPackages(String... strArr) {
        enableClassInfo();
        for (String str : strArr) {
            String normalizePackageOrClassName = AcceptReject.normalizePackageOrClassName(str);
            if (!normalizePackageOrClassName.isEmpty()) {
                this.scanSpec.packageAcceptReject.addToReject(normalizePackageOrClassName);
                String packageNameToPath = AcceptReject.packageNameToPath(normalizePackageOrClassName);
                this.scanSpec.pathAcceptReject.addToReject(packageNameToPath + "/");
                if (!normalizePackageOrClassName.contains("*")) {
                    this.scanSpec.packagePrefixAcceptReject.addToReject(normalizePackageOrClassName + ".");
                    this.scanSpec.pathPrefixAcceptReject.addToReject(packageNameToPath + "/");
                }
            } else {
                throw new IllegalArgumentException("Rejecting the root package (\"\") will cause nothing to be scanned");
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph blacklistPackages(String... strArr) {
        return rejectPackages(strArr);
    }

    public ClassGraph rejectPaths(String... strArr) {
        for (String str : strArr) {
            String normalizePath = AcceptReject.normalizePath(str);
            if (normalizePath.isEmpty()) {
                throw new IllegalArgumentException("Rejecting the root package (\"\") will cause nothing to be scanned");
            }
            String pathToPackageName = AcceptReject.pathToPackageName(normalizePath);
            this.scanSpec.packageAcceptReject.addToReject(pathToPackageName);
            this.scanSpec.pathAcceptReject.addToReject(normalizePath + "/");
            if (!normalizePath.contains("*")) {
                this.scanSpec.packagePrefixAcceptReject.addToReject(pathToPackageName + ".");
                this.scanSpec.pathPrefixAcceptReject.addToReject(normalizePath + "/");
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph blacklistPaths(String... strArr) {
        return rejectPaths(strArr);
    }

    public ClassGraph acceptClasses(String... strArr) {
        enableClassInfo();
        for (String str : strArr) {
            String normalizePackageOrClassName = AcceptReject.normalizePackageOrClassName(str);
            this.scanSpec.classAcceptReject.addToAccept(normalizePackageOrClassName);
            this.scanSpec.classfilePathAcceptReject.addToAccept(AcceptReject.classNameToClassfilePath(normalizePackageOrClassName));
            String parentPackageName = PackageInfo.getParentPackageName(normalizePackageOrClassName);
            this.scanSpec.classPackageAcceptReject.addToAccept(parentPackageName);
            this.scanSpec.classPackagePathAcceptReject.addToAccept(AcceptReject.packageNameToPath(parentPackageName) + "/");
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistClasses(String... strArr) {
        return acceptClasses(strArr);
    }

    public ClassGraph rejectClasses(String... strArr) {
        enableClassInfo();
        for (String str : strArr) {
            String normalizePackageOrClassName = AcceptReject.normalizePackageOrClassName(str);
            this.scanSpec.classAcceptReject.addToReject(normalizePackageOrClassName);
            this.scanSpec.classfilePathAcceptReject.addToReject(AcceptReject.classNameToClassfilePath(normalizePackageOrClassName));
        }
        return this;
    }

    @Deprecated
    public ClassGraph blacklistClasses(String... strArr) {
        return rejectClasses(strArr);
    }

    public ClassGraph acceptJars(String... strArr) {
        for (String str : strArr) {
            String leafName = JarUtils.leafName(str);
            if (leafName.equals(str)) {
                this.scanSpec.jarAcceptReject.addToAccept(leafName);
            } else {
                throw new IllegalArgumentException("Can only accept jars by leafname: " + str);
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistJars(String... strArr) {
        return acceptJars(strArr);
    }

    public ClassGraph rejectJars(String... strArr) {
        for (String str : strArr) {
            String leafName = JarUtils.leafName(str);
            if (leafName.equals(str)) {
                this.scanSpec.jarAcceptReject.addToReject(leafName);
            } else {
                throw new IllegalArgumentException("Can only reject jars by leafname: " + str);
            }
        }
        return this;
    }

    @Deprecated
    public ClassGraph blacklistJars(String... strArr) {
        return rejectJars(strArr);
    }

    private void acceptOrRejectLibOrExtJars(boolean z, String... strArr) {
        if (strArr.length == 0) {
            Iterator<String> it = SystemJarFinder.getJreLibOrExtJars().iterator();
            while (it.hasNext()) {
                acceptOrRejectLibOrExtJars(z, JarUtils.leafName(it.next()));
            }
            return;
        }
        for (String str : strArr) {
            if (!JarUtils.leafName(str).equals(str)) {
                throw new IllegalArgumentException("Can only " + (z ? "accept" : "reject") + " jars by leafname: " + str);
            }
            if (str.contains("*")) {
                Pattern globToPattern = AcceptReject.globToPattern(str, true);
                boolean z2 = false;
                Iterator<String> it2 = SystemJarFinder.getJreLibOrExtJars().iterator();
                while (it2.hasNext()) {
                    String leafName = JarUtils.leafName(it2.next());
                    if (globToPattern.matcher(leafName).matches()) {
                        if (!leafName.contains("*")) {
                            acceptOrRejectLibOrExtJars(z, leafName);
                        }
                        z2 = true;
                    }
                }
                if (!z2 && this.topLevelLog != null) {
                    this.topLevelLog.log("Could not find lib or ext jar matching wildcard: " + str);
                }
            } else {
                boolean z3 = false;
                Iterator<String> it3 = SystemJarFinder.getJreLibOrExtJars().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    String next = it3.next();
                    if (str.equals(JarUtils.leafName(next))) {
                        if (z) {
                            this.scanSpec.libOrExtJarAcceptReject.addToAccept(str);
                        } else {
                            this.scanSpec.libOrExtJarAcceptReject.addToReject(str);
                        }
                        if (this.topLevelLog != null) {
                            this.topLevelLog.log((z ? "Accepting" : "Rejecting") + " lib or ext jar: " + next);
                        }
                        z3 = true;
                    }
                }
                if (!z3 && this.topLevelLog != null) {
                    this.topLevelLog.log("Could not find lib or ext jar: " + str);
                }
            }
        }
    }

    public ClassGraph acceptLibOrExtJars(String... strArr) {
        acceptOrRejectLibOrExtJars(true, strArr);
        return this;
    }

    @Deprecated
    public ClassGraph whitelistLibOrExtJars(String... strArr) {
        return acceptLibOrExtJars(strArr);
    }

    public ClassGraph rejectLibOrExtJars(String... strArr) {
        acceptOrRejectLibOrExtJars(false, strArr);
        return this;
    }

    @Deprecated
    public ClassGraph blacklistLibOrExtJars(String... strArr) {
        return rejectLibOrExtJars(strArr);
    }

    public ClassGraph acceptModules(String... strArr) {
        for (String str : strArr) {
            this.scanSpec.moduleAcceptReject.addToAccept(AcceptReject.normalizePackageOrClassName(str));
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistModules(String... strArr) {
        return acceptModules(strArr);
    }

    public ClassGraph rejectModules(String... strArr) {
        for (String str : strArr) {
            this.scanSpec.moduleAcceptReject.addToReject(AcceptReject.normalizePackageOrClassName(str));
        }
        return this;
    }

    @Deprecated
    public ClassGraph blacklistModules(String... strArr) {
        return rejectModules(strArr);
    }

    public ClassGraph acceptClasspathElementsContainingResourcePath(String... strArr) {
        for (String str : strArr) {
            this.scanSpec.classpathElementResourcePathAcceptReject.addToAccept(AcceptReject.normalizePath(str));
        }
        return this;
    }

    @Deprecated
    public ClassGraph whitelistClasspathElementsContainingResourcePath(String... strArr) {
        return acceptClasspathElementsContainingResourcePath(strArr);
    }

    public ClassGraph rejectClasspathElementsContainingResourcePath(String... strArr) {
        for (String str : strArr) {
            this.scanSpec.classpathElementResourcePathAcceptReject.addToReject(AcceptReject.normalizePath(str));
        }
        return this;
    }

    @Deprecated
    public ClassGraph blacklistClasspathElementsContainingResourcePath(String... strArr) {
        return rejectClasspathElementsContainingResourcePath(strArr);
    }

    public ClassGraph enableRemoteJarScanning() {
        this.scanSpec.enableURLScheme("http");
        this.scanSpec.enableURLScheme("https");
        return this;
    }

    public ClassGraph enableURLScheme(String str) {
        this.scanSpec.enableURLScheme(str);
        return this;
    }

    public ClassGraph enableSystemJarsAndModules() {
        enableClassInfo();
        this.scanSpec.enableSystemJarsAndModules = true;
        return this;
    }

    public ClassGraph setMaxBufferedJarRAMSize(int i) {
        this.scanSpec.maxBufferedJarRAMSize = i;
        return this;
    }

    public ClassGraph enableMemoryMapping() {
        this.scanSpec.enableMemoryMapping = true;
        return this;
    }

    public ClassGraph enableMultiReleaseVersions() {
        this.scanSpec.enableMultiReleaseVersions = true;
        this.scanSpec.enableClassInfo = false;
        this.scanSpec.ignoreClassVisibility = false;
        this.scanSpec.enableMethodInfo = false;
        this.scanSpec.ignoreMethodVisibility = false;
        this.scanSpec.enableFieldInfo = false;
        this.scanSpec.ignoreFieldVisibility = false;
        this.scanSpec.enableStaticFinalFieldConstantInitializerValues = false;
        this.scanSpec.enableAnnotationInfo = false;
        this.scanSpec.enableInterClassDependencies = false;
        this.scanSpec.disableRuntimeInvisibleAnnotations = false;
        this.scanSpec.enableExternalClasses = false;
        this.scanSpec.enableSystemJarsAndModules = false;
        return this;
    }

    public ClassGraph enableRealtimeLogging() {
        verbose();
        LogNode.logInRealtime(true);
        return this;
    }

    public void scanAsync(final ExecutorService executorService, final int i, final ScanResultProcessor scanResultProcessor, final FailureHandler failureHandler) {
        if (scanResultProcessor == null) {
            throw new IllegalArgumentException("scanResultProcessor cannot be null");
        }
        if (failureHandler == null) {
            throw new IllegalArgumentException("failureHandler cannot be null");
        }
        executorService.execute(new Runnable() { // from class: io.github.classgraph.ClassGraph.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new Scanner(true, ClassGraph.this.scanSpec, executorService, i, scanResultProcessor, failureHandler, ClassGraph.this.reflectionUtils, ClassGraph.this.topLevelLog).call();
                } catch (InterruptedException | CancellationException | ExecutionException e) {
                    failureHandler.onFailure(e);
                }
            }
        });
    }

    private Future<ScanResult> scanAsync(boolean z, ExecutorService executorService, int i) {
        try {
            return executorService.submit(new Scanner(z, this.scanSpec, executorService, i, null, null, this.reflectionUtils, this.topLevelLog));
        } catch (InterruptedException e) {
            return executorService.submit(new Callable<ScanResult>() { // from class: io.github.classgraph.ClassGraph.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public ScanResult call() {
                    throw e;
                }
            });
        }
    }

    public Future<ScanResult> scanAsync(ExecutorService executorService, int i) {
        return scanAsync(true, executorService, i);
    }

    public ScanResult scan(ExecutorService executorService, int i) {
        try {
            ScanResult scanResult = scanAsync(executorService, i).get();
            if (scanResult == null) {
                throw new NullPointerException();
            }
            return scanResult;
        } catch (InterruptedException | CancellationException e) {
            throw new ClassGraphException("Scan interrupted", e);
        } catch (ExecutionException e2) {
            throw new ClassGraphException("Uncaught exception during scan", InterruptionChecker.getCause(e2));
        }
    }

    public ScanResult scan(int i) {
        AutoCloseableExecutorService autoCloseableExecutorService = new AutoCloseableExecutorService(i);
        Throwable th = null;
        try {
            ScanResult scan = scan(autoCloseableExecutorService, i);
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            } else {
                autoCloseableExecutorService.close();
            }
            return scan;
        } catch (Throwable th3) {
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
            } else {
                autoCloseableExecutorService.close();
            }
            throw th3;
        }
    }

    public ScanResult scan() {
        return scan(DEFAULT_NUM_WORKER_THREADS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScanResult getClasspathScanResult(AutoCloseableExecutorService autoCloseableExecutorService) {
        try {
            ScanResult scanResult = scanAsync(false, autoCloseableExecutorService, DEFAULT_NUM_WORKER_THREADS).get();
            if (scanResult == null) {
                throw new NullPointerException();
            }
            return scanResult;
        } catch (InterruptedException | CancellationException e) {
            throw new ClassGraphException("Scan interrupted", e);
        } catch (ExecutionException e2) {
            throw new ClassGraphException("Uncaught exception during scan", InterruptionChecker.getCause(e2));
        }
    }

    public List<File> getClasspathFiles() {
        AutoCloseableExecutorService autoCloseableExecutorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS);
        Throwable th = null;
        try {
            ScanResult classpathScanResult = getClasspathScanResult(autoCloseableExecutorService);
            try {
                try {
                    List<File> classpathFiles = classpathScanResult.getClasspathFiles();
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th2) {
                                r8.addSuppressed(th2);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    return classpathFiles;
                } catch (Throwable th3) {
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th4) {
                                r8.addSuppressed(th4);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    throw th3;
                }
            } finally {
                r8 = null;
            }
        } finally {
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th5) {
                    th.addSuppressed(th5);
                }
            } else {
                autoCloseableExecutorService.close();
            }
        }
    }

    public String getClasspath() {
        return JarUtils.pathElementsToPathStr(getClasspathFiles());
    }

    public List<URI> getClasspathURIs() {
        AutoCloseableExecutorService autoCloseableExecutorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS);
        Throwable th = null;
        try {
            ScanResult classpathScanResult = getClasspathScanResult(autoCloseableExecutorService);
            try {
                try {
                    List<URI> classpathURIs = classpathScanResult.getClasspathURIs();
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th2) {
                                r8.addSuppressed(th2);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    return classpathURIs;
                } catch (Throwable th3) {
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th4) {
                                r8.addSuppressed(th4);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    throw th3;
                }
            } finally {
                r8 = null;
            }
        } finally {
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th5) {
                    th.addSuppressed(th5);
                }
            } else {
                autoCloseableExecutorService.close();
            }
        }
    }

    public List<URL> getClasspathURLs() {
        AutoCloseableExecutorService autoCloseableExecutorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS);
        Throwable th = null;
        try {
            ScanResult classpathScanResult = getClasspathScanResult(autoCloseableExecutorService);
            try {
                try {
                    List<URL> classpathURLs = classpathScanResult.getClasspathURLs();
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th2) {
                                r8.addSuppressed(th2);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    return classpathURLs;
                } catch (Throwable th3) {
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th4) {
                                r8.addSuppressed(th4);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    throw th3;
                }
            } finally {
                r8 = null;
            }
        } finally {
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th5) {
                    th.addSuppressed(th5);
                }
            } else {
                autoCloseableExecutorService.close();
            }
        }
    }

    public List<ModuleRef> getModules() {
        AutoCloseableExecutorService autoCloseableExecutorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS);
        Throwable th = null;
        try {
            ScanResult classpathScanResult = getClasspathScanResult(autoCloseableExecutorService);
            try {
                try {
                    List<ModuleRef> modules = classpathScanResult.getModules();
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th2) {
                                r8.addSuppressed(th2);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    return modules;
                } catch (Throwable th3) {
                    if (classpathScanResult != null) {
                        if (r8 != null) {
                            try {
                                classpathScanResult.close();
                            } catch (Throwable th4) {
                                r8.addSuppressed(th4);
                            }
                        } else {
                            classpathScanResult.close();
                        }
                    }
                    throw th3;
                }
            } finally {
                r8 = null;
            }
        } finally {
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th5) {
                    th.addSuppressed(th5);
                }
            } else {
                autoCloseableExecutorService.close();
            }
        }
    }

    public ModulePathInfo getModulePathInfo() {
        this.scanSpec.modulePathInfo.getRuntimeInfo(this.reflectionUtils);
        return this.scanSpec.modulePathInfo;
    }
}
