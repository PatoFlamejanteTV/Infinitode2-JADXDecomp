package io.github.classgraph;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/ScanResultObject.class */
abstract class ScanResultObject {
    protected transient ScanResult scanResult;
    private transient ClassInfo classInfo;
    protected transient Class<?> classRef;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String getClassName();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void toString(boolean z, StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<ClassInfo> findReferencedClassInfo(LogNode logNode) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (this.scanResult != null) {
            findReferencedClassInfo(this.scanResult.classNameToClassInfo, linkedHashSet, logNode);
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        ClassInfo classInfo = getClassInfo();
        if (classInfo != null) {
            set.add(classInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfo getClassInfo() {
        if (this.classInfo == null) {
            if (this.scanResult == null) {
                return null;
            }
            String className = getClassName();
            if (className == null) {
                throw new IllegalArgumentException("Class name is not set");
            }
            this.classInfo = this.scanResult.getClassInfo(className);
        }
        return this.classInfo;
    }

    private String getClassInfoNameOrClassName() {
        String className;
        ClassInfo classInfo = null;
        try {
            classInfo = getClassInfo();
        } catch (IllegalArgumentException unused) {
        }
        if (classInfo == null) {
            classInfo = this.classInfo;
        }
        if (classInfo != null) {
            className = classInfo.getName();
        } else {
            className = getClassName();
        }
        if (className == null) {
            throw new IllegalArgumentException("Class name is not set");
        }
        return className;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> Class<T> loadClass(Class<T> cls, boolean z) {
        IllegalArgumentException illegalArgumentException;
        if (this.classRef == null) {
            String classInfoNameOrClassName = getClassInfoNameOrClassName();
            if (this.scanResult != null) {
                this.classRef = this.scanResult.loadClass(classInfoNameOrClassName, cls, z);
            } else {
                try {
                    this.classRef = Class.forName(classInfoNameOrClassName);
                } finally {
                    if (!z) {
                    }
                }
            }
        }
        return (Class<T>) this.classRef;
    }

    <T> Class<T> loadClass(Class<T> cls) {
        return loadClass(cls, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class<?> loadClass(boolean z) {
        IllegalArgumentException illegalArgumentException;
        if (this.classRef == null) {
            String classInfoNameOrClassName = getClassInfoNameOrClassName();
            if (this.scanResult != null) {
                this.classRef = this.scanResult.loadClass(classInfoNameOrClassName, z);
            } else {
                try {
                    this.classRef = Class.forName(classInfoNameOrClassName);
                } finally {
                    if (!z) {
                    }
                }
            }
        }
        return this.classRef;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class<?> loadClass() {
        return loadClass(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toString(boolean z) {
        StringBuilder sb = new StringBuilder();
        toString(z, sb);
        return sb.toString();
    }

    public String toStringWithSimpleNames() {
        StringBuilder sb = new StringBuilder();
        toString(true, sb);
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(false, sb);
        return sb.toString();
    }
}
