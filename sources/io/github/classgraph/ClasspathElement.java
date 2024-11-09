package io.github.classgraph;

import io.github.classgraph.Scanner;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import net.bytebuddy.build.Plugin;
import nonapi.io.github.classgraph.concurrency.WorkQueue;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:io/github/classgraph/ClasspathElement.class */
public abstract class ClasspathElement implements Comparable<ClasspathElement> {
    int classpathElementIdx;
    List<String> nestedClasspathRootPrefixes;
    boolean skipClasspathElement;
    boolean containsSpecificallyAcceptedClasspathElementResourcePath;
    final int classpathElementIdxWithinParent;
    Collection<ClasspathElement> childClasspathElements = new ConcurrentLinkedQueue();
    protected final List<Resource> acceptedResources = new ArrayList();
    protected List<Resource> acceptedClassfileResources = new ArrayList();
    protected final Map<File, Long> fileToLastModified = new ConcurrentHashMap();
    protected final AtomicBoolean scanned = new AtomicBoolean(false);
    protected ClassLoader classLoader;
    protected String packageRootPrefix;
    String moduleNameFromModuleDescriptor;
    final ScanSpec scanSpec;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, LogNode logNode);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void scanPaths(LogNode logNode);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Resource getResource(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract URI getURI();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List<URI> getAllURIs();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract File getFile();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getModuleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClasspathElement(Scanner.ClasspathEntryWorkUnit classpathEntryWorkUnit, ScanSpec scanSpec) {
        this.packageRootPrefix = classpathEntryWorkUnit.packageRootPrefix;
        this.classpathElementIdxWithinParent = classpathEntryWorkUnit.classpathElementIdxWithinParent;
        this.classLoader = classpathEntryWorkUnit.classLoader;
        this.scanSpec = scanSpec;
    }

    @Override // java.lang.Comparable
    public int compareTo(ClasspathElement classpathElement) {
        return this.classpathElementIdxWithinParent - classpathElement.classpathElementIdxWithinParent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    int getNumClassfileMatches() {
        if (this.acceptedClassfileResources == null) {
            return 0;
        }
        return this.acceptedClassfileResources.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean checkResourcePathAcceptReject(String str, LogNode logNode) {
        if (!this.scanSpec.classpathElementResourcePathAcceptReject.acceptAndRejectAreEmpty()) {
            if (this.scanSpec.classpathElementResourcePathAcceptReject.isRejected(str)) {
                if (logNode != null) {
                    logNode.log("Reached rejected classpath element resource path, stopping scanning: " + str);
                    return false;
                }
                return false;
            }
            if (this.scanSpec.classpathElementResourcePathAcceptReject.isSpecificallyAccepted(str)) {
                if (logNode != null) {
                    logNode.log("Reached specifically accepted classpath element resource path: " + str);
                }
                this.containsSpecificallyAcceptedClasspathElementResourcePath = true;
                return true;
            }
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void maskClassfiles(int i, Set<String> set, LogNode logNode) {
        ArrayList arrayList = new ArrayList(this.acceptedClassfileResources.size());
        boolean z = false;
        for (Resource resource : this.acceptedClassfileResources) {
            String path = resource.getPath();
            if (!path.equals(Plugin.Engine.MODULE_INFO) && !path.equals(Plugin.Engine.PACKAGE_INFO) && !path.endsWith("/package-info.class") && !set.add(path)) {
                z = true;
                if (logNode != null) {
                    logNode.log(String.format("%06d-1", Integer.valueOf(i)), "Ignoring duplicate (masked) class " + JarUtils.classfilePathToClassName(path) + " found at " + resource);
                }
            } else {
                arrayList.add(resource);
            }
        }
        if (z) {
            this.acceptedClassfileResources = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addAcceptedResource(io.github.classgraph.Resource r9, nonapi.io.github.classgraph.scanspec.ScanSpec.ScanSpecPathMatch r10, boolean r11, nonapi.io.github.classgraph.utils.LogNode r12) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.classgraph.ClasspathElement.addAcceptedResource(io.github.classgraph.Resource, nonapi.io.github.classgraph.scanspec.ScanSpec$ScanSpecPathMatch, boolean, nonapi.io.github.classgraph.utils.LogNode):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finishScanPaths(LogNode logNode) {
        if (logNode != null) {
            if (this.acceptedResources.isEmpty() && this.acceptedClassfileResources.isEmpty()) {
                logNode.log(this.scanSpec.enableClassInfo ? "No accepted classfiles or resources found" : "Classfile scanning is disabled, and no accepted resources found");
            } else if (this.acceptedResources.isEmpty()) {
                logNode.log("No accepted resources found");
            } else if (this.acceptedClassfileResources.isEmpty()) {
                logNode.log(this.scanSpec.enableClassInfo ? "No accepted classfiles found" : "Classfile scanning is disabled");
            }
        }
        if (logNode != null) {
            logNode.addElapsedTime();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LogNode log(int i, String str, LogNode logNode) {
        return logNode.log(String.format("%07d", Integer.valueOf(i)), str);
    }

    protected LogNode log(int i, String str, Throwable th, LogNode logNode) {
        return logNode.log(String.format("%07d", Integer.valueOf(i)), str, th);
    }
}
