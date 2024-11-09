package io.github.classgraph;

import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/ArrayClassInfo.class */
public class ArrayClassInfo extends ClassInfo {
    private ArrayTypeSignature arrayTypeSignature;
    private ClassInfo elementClassInfo;

    ArrayClassInfo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayClassInfo(ArrayTypeSignature arrayTypeSignature) {
        super(arrayTypeSignature.getClassName(), 0, null);
        this.arrayTypeSignature = arrayTypeSignature;
        getElementClassInfo();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ClassInfo, io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
    }

    @Override // io.github.classgraph.ClassInfo
    public String getTypeSignatureStr() {
        return this.arrayTypeSignature.getTypeSignatureStr();
    }

    @Override // io.github.classgraph.ClassInfo
    public ClassTypeSignature getTypeSignature() {
        return null;
    }

    public ArrayTypeSignature getArrayTypeSignature() {
        return this.arrayTypeSignature;
    }

    public TypeSignature getElementTypeSignature() {
        return this.arrayTypeSignature.getElementTypeSignature();
    }

    public int getNumDimensions() {
        return this.arrayTypeSignature.getNumDimensions();
    }

    public ClassInfo getElementClassInfo() {
        if (this.elementClassInfo == null && !(this.arrayTypeSignature.getElementTypeSignature() instanceof BaseTypeSignature)) {
            this.elementClassInfo = this.arrayTypeSignature.getElementTypeSignature().getClassInfo();
            if (this.elementClassInfo != null) {
                this.classpathElement = this.elementClassInfo.classpathElement;
                this.classfileResource = this.elementClassInfo.classfileResource;
                this.classLoader = this.elementClassInfo.classLoader;
                this.isScannedClass = this.elementClassInfo.isScannedClass;
                this.isExternalClass = this.elementClassInfo.isExternalClass;
                this.moduleInfo = this.elementClassInfo.moduleInfo;
                this.packageInfo = this.elementClassInfo.packageInfo;
            }
        }
        return this.elementClassInfo;
    }

    public Class<?> loadElementClass(boolean z) {
        return this.arrayTypeSignature.loadElementClass(z);
    }

    public Class<?> loadElementClass() {
        return this.arrayTypeSignature.loadElementClass();
    }

    @Override // io.github.classgraph.ClassInfo, io.github.classgraph.ScanResultObject
    public Class<?> loadClass(boolean z) {
        if (this.classRef == null) {
            this.classRef = this.arrayTypeSignature.loadClass(z);
        }
        return this.classRef;
    }

    @Override // io.github.classgraph.ClassInfo, io.github.classgraph.ScanResultObject
    public Class<?> loadClass() {
        if (this.classRef == null) {
            this.classRef = this.arrayTypeSignature.loadClass();
        }
        return this.classRef;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ClassInfo, io.github.classgraph.ScanResultObject
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        super.findReferencedClassInfo(map, set, logNode);
    }

    @Override // io.github.classgraph.ClassInfo
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // io.github.classgraph.ClassInfo
    public int hashCode() {
        return super.hashCode();
    }
}
