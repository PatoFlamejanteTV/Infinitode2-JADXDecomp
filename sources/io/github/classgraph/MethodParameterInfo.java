package io.github.classgraph;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import nonapi.io.github.classgraph.utils.Assert;

/* loaded from: infinitode-2.jar:io/github/classgraph/MethodParameterInfo.class */
public class MethodParameterInfo {
    private final MethodInfo methodInfo;
    final AnnotationInfo[] annotationInfo;
    private final int modifiers;
    private final TypeSignature typeDescriptor;
    private final TypeSignature typeSignature;
    private final String name;
    private ScanResult scanResult;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodParameterInfo(MethodInfo methodInfo, AnnotationInfo[] annotationInfoArr, int i, TypeSignature typeSignature, TypeSignature typeSignature2, String str) {
        this.methodInfo = methodInfo;
        this.name = str;
        this.modifiers = i;
        this.typeDescriptor = typeSignature;
        this.typeSignature = typeSignature2;
        this.annotationInfo = annotationInfoArr;
    }

    public MethodInfo getMethodInfo() {
        return this.methodInfo;
    }

    public String getName() {
        return this.name;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public String getModifiersStr() {
        StringBuilder sb = new StringBuilder();
        modifiersToString(this.modifiers, sb);
        return sb.toString();
    }

    public TypeSignature getTypeSignature() {
        return this.typeSignature;
    }

    public TypeSignature getTypeDescriptor() {
        return this.typeDescriptor;
    }

    public TypeSignature getTypeSignatureOrTypeDescriptor() {
        return this.typeSignature != null ? this.typeSignature : this.typeDescriptor;
    }

    public AnnotationInfoList getAnnotationInfo() {
        if (!this.scanResult.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
        }
        if (this.annotationInfo == null || this.annotationInfo.length == 0) {
            return AnnotationInfoList.EMPTY_LIST;
        }
        AnnotationInfoList annotationInfoList = new AnnotationInfoList(this.annotationInfo.length);
        Collections.addAll(annotationInfoList, this.annotationInfo);
        return AnnotationInfoList.getIndirectAnnotations(annotationInfoList, null);
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

    /* JADX INFO: Access modifiers changed from: protected */
    public void setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
        if (this.annotationInfo != null) {
            for (AnnotationInfo annotationInfo : this.annotationInfo) {
                annotationInfo.setScanResult(scanResult);
            }
        }
        if (this.typeDescriptor != null) {
            this.typeDescriptor.setScanResult(scanResult);
        }
        if (this.typeSignature != null) {
            this.typeSignature.setScanResult(scanResult);
        }
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.modifiers);
    }

    public boolean isSynthetic() {
        return (this.modifiers & 4096) != 0;
    }

    public boolean isMandated() {
        return (this.modifiers & 32768) != 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodParameterInfo)) {
            return false;
        }
        MethodParameterInfo methodParameterInfo = (MethodParameterInfo) obj;
        return Objects.equals(this.methodInfo, methodParameterInfo.methodInfo) && Objects.deepEquals(this.annotationInfo, methodParameterInfo.annotationInfo) && this.modifiers == methodParameterInfo.modifiers && Objects.equals(this.typeDescriptor, methodParameterInfo.typeDescriptor) && Objects.equals(this.typeSignature, methodParameterInfo.typeSignature) && Objects.equals(this.name, methodParameterInfo.name);
    }

    public int hashCode() {
        return Objects.hash(this.methodInfo, Integer.valueOf(Arrays.hashCode(this.annotationInfo)), this.typeDescriptor, this.typeSignature, this.name) + this.modifiers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void modifiersToString(int i, StringBuilder sb) {
        if ((i & 16) != 0) {
            sb.append("final ");
        }
        if ((i & 4096) != 0) {
            sb.append("synthetic ");
        }
        if ((i & 32768) != 0) {
            sb.append("mandated ");
        }
    }

    protected void toString(boolean z, StringBuilder sb) {
        if (this.annotationInfo != null) {
            for (AnnotationInfo annotationInfo : this.annotationInfo) {
                annotationInfo.toString(z, sb);
                sb.append(' ');
            }
        }
        modifiersToString(this.modifiers, sb);
        getTypeSignatureOrTypeDescriptor().toString(z, sb);
        sb.append(' ');
        sb.append(this.name == null ? "_unnamed_param" : this.name);
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
