package io.github.classgraph;

import io.github.classgraph.Classfile;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:io/github/classgraph/HierarchicalTypeSignature.class */
public abstract class HierarchicalTypeSignature extends ScanResultObject {
    protected AnnotationInfoList typeAnnotationInfo;

    protected abstract void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb);

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toStringWithSimpleNames() {
        return super.toStringWithSimpleNames();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addTypeAnnotation(AnnotationInfo annotationInfo) {
        if (this.typeAnnotationInfo == null) {
            this.typeAnnotationInfo = new AnnotationInfoList(1);
        }
        this.typeAnnotationInfo.add(annotationInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.typeAnnotationInfo != null) {
            Iterator it = this.typeAnnotationInfo.iterator();
            while (it.hasNext()) {
                ((AnnotationInfo) it.next()).setScanResult(scanResult);
            }
        }
    }

    public AnnotationInfoList getTypeAnnotationInfo() {
        return this.typeAnnotationInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        toStringInternal(z, null, sb);
    }
}
