package io.github.classgraph;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import nonapi.io.github.classgraph.utils.Assert;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassMemberInfo.class */
public abstract class ClassMemberInfo extends ScanResultObject implements HasName {
    protected String declaringClassName;
    protected String name;
    protected int modifiers;
    protected String typeDescriptorStr;
    protected String typeSignatureStr;
    protected AnnotationInfoList annotationInfo;

    public abstract String getModifiersStr();

    public abstract HierarchicalTypeSignature getTypeDescriptor();

    public abstract HierarchicalTypeSignature getTypeSignature();

    public abstract HierarchicalTypeSignature getTypeSignatureOrTypeDescriptor();

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toStringWithSimpleNames() {
        return super.toStringWithSimpleNames();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassMemberInfo() {
    }

    public ClassMemberInfo(String str, String str2, int i, String str3, String str4, AnnotationInfoList annotationInfoList) {
        this.declaringClassName = str;
        this.name = str2;
        this.modifiers = i;
        this.typeDescriptorStr = str3;
        this.typeSignatureStr = str4;
        this.annotationInfo = (annotationInfoList == null || annotationInfoList.isEmpty()) ? null : annotationInfoList;
    }

    @Override // io.github.classgraph.ScanResultObject
    public ClassInfo getClassInfo() {
        return super.getClassInfo();
    }

    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        return this.declaringClassName;
    }

    @Override // io.github.classgraph.HasName
    public String getName() {
        return this.name;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public boolean isPublic() {
        return Modifier.isPublic(this.modifiers);
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(this.modifiers);
    }

    public boolean isProtected() {
        return Modifier.isProtected(this.modifiers);
    }

    public boolean isStatic() {
        return Modifier.isStatic(this.modifiers);
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.modifiers);
    }

    public boolean isSynthetic() {
        return (this.modifiers & 4096) != 0;
    }

    public String getTypeDescriptorStr() {
        return this.typeDescriptorStr;
    }

    public String getTypeSignatureStr() {
        return this.typeSignatureStr;
    }

    public String getTypeSignatureOrTypeDescriptorStr() {
        if (this.typeSignatureStr != null) {
            return this.typeSignatureStr;
        }
        return this.typeDescriptorStr;
    }

    public AnnotationInfoList getAnnotationInfo() {
        if (this.scanResult.scanSpec.enableAnnotationInfo) {
            return this.annotationInfo == null ? AnnotationInfoList.EMPTY_LIST : AnnotationInfoList.getIndirectAnnotations(this.annotationInfo, null);
        }
        throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
    }

    public AnnotationInfo getAnnotationInfo(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getAnnotationInfo(cls.getName());
    }

    public AnnotationInfo getAnnotationInfo(String str) {
        return getAnnotationInfo().get(str);
    }

    public AnnotationInfoList getAnnotationInfoRepeatable(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getAnnotationInfoRepeatable(cls.getName());
    }

    public AnnotationInfoList getAnnotationInfoRepeatable(String str) {
        return getAnnotationInfo().getRepeatable(str);
    }

    public boolean hasAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasAnnotation(cls.getName());
    }

    public boolean hasAnnotation(String str) {
        return getAnnotationInfo().containsName(str);
    }
}
