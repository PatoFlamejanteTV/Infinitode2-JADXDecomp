package io.github.classgraph;

import io.github.classgraph.ClassInfo;
import io.github.classgraph.Classfile;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.description.method.MethodDescription;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.TypeUtils;
import nonapi.io.github.classgraph.utils.Assert;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/MethodInfo.class */
public class MethodInfo extends ClassMemberInfo implements Comparable<MethodInfo> {
    private transient MethodTypeSignature typeDescriptor;
    private transient MethodTypeSignature typeSignature;
    private String[] parameterNames;
    private int[] parameterModifiers;
    AnnotationInfo[][] parameterAnnotationInfo;
    private transient MethodParameterInfo[] parameterInfo;
    private boolean hasBody;
    private int minLineNum;
    private int maxLineNum;
    private transient List<Classfile.MethodTypeAnnotationDecorator> typeAnnotationDecorators;
    private String[] thrownExceptionNames;
    private transient ClassInfoList thrownExceptions;

    MethodInfo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodInfo(String str, String str2, AnnotationInfoList annotationInfoList, int i, String str3, String str4, String[] strArr, int[] iArr, AnnotationInfo[][] annotationInfoArr, boolean z, int i2, int i3, List<Classfile.MethodTypeAnnotationDecorator> list, String[] strArr2) {
        super(str, str2, i, str3, str4, annotationInfoList);
        this.parameterNames = strArr;
        this.parameterModifiers = iArr;
        this.parameterAnnotationInfo = annotationInfoArr;
        this.hasBody = z;
        this.minLineNum = i2;
        this.maxLineNum = i3;
        this.typeAnnotationDecorators = list;
        this.thrownExceptionNames = strArr2;
    }

    @Override // io.github.classgraph.ClassMemberInfo, io.github.classgraph.HasName
    public String getName() {
        return this.name;
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public String getModifiersStr() {
        StringBuilder sb = new StringBuilder();
        TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.METHOD, isDefault(), sb);
        return sb.toString();
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public MethodTypeSignature getTypeDescriptor() {
        if (this.typeDescriptor == null) {
            try {
                this.typeDescriptor = MethodTypeSignature.parse(this.typeDescriptorStr, this.declaringClassName);
                this.typeDescriptor.setScanResult(this.scanResult);
                if (this.typeAnnotationDecorators != null) {
                    MethodTypeSignature typeSignature = getTypeSignature();
                    if (typeSignature == null) {
                        Iterator<Classfile.MethodTypeAnnotationDecorator> it = this.typeAnnotationDecorators.iterator();
                        while (it.hasNext()) {
                            it.next().decorate(this.typeDescriptor);
                        }
                    } else {
                        int size = this.typeDescriptor.getParameterTypeSignatures().size() - typeSignature.getParameterTypeSignatures().size();
                        if (size < 0) {
                            throw new IllegalArgumentException("Fewer params in method type descriptor than in method type signature");
                        }
                        if (size == 0) {
                            Iterator<Classfile.MethodTypeAnnotationDecorator> it2 = this.typeAnnotationDecorators.iterator();
                            while (it2.hasNext()) {
                                it2.next().decorate(this.typeDescriptor);
                            }
                        } else {
                            List<TypeSignature> parameterTypeSignatures = this.typeDescriptor.getParameterTypeSignatures();
                            List<TypeSignature> subList = parameterTypeSignatures.subList(0, size);
                            for (int i = 0; i < size; i++) {
                                parameterTypeSignatures.remove(0);
                            }
                            Iterator<Classfile.MethodTypeAnnotationDecorator> it3 = this.typeAnnotationDecorators.iterator();
                            while (it3.hasNext()) {
                                it3.next().decorate(this.typeDescriptor);
                            }
                            for (int i2 = size - 1; i2 >= 0; i2--) {
                                parameterTypeSignatures.add(0, subList.get(i2));
                            }
                        }
                    }
                }
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return this.typeDescriptor;
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public MethodTypeSignature getTypeSignature() {
        if (this.typeSignature == null && this.typeSignatureStr != null) {
            try {
                this.typeSignature = MethodTypeSignature.parse(this.typeSignatureStr, this.declaringClassName);
                this.typeSignature.setScanResult(this.scanResult);
                if (this.typeAnnotationDecorators != null) {
                    Iterator<Classfile.MethodTypeAnnotationDecorator> it = this.typeAnnotationDecorators.iterator();
                    while (it.hasNext()) {
                        it.next().decorate(this.typeSignature);
                    }
                }
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid type signature for method " + getClassName() + "." + getName() + (getClassInfo() != null ? " in classpath element " + getClassInfo().getClasspathElementURI() : "") + " : " + this.typeSignatureStr, e);
            }
        }
        return this.typeSignature;
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public MethodTypeSignature getTypeSignatureOrTypeDescriptor() {
        try {
            MethodTypeSignature typeSignature = getTypeSignature();
            if (typeSignature != null) {
                return typeSignature;
            }
        } catch (Exception unused) {
        }
        return getTypeDescriptor();
    }

    public ClassInfoList getThrownExceptions() {
        if (this.thrownExceptions == null && this.thrownExceptionNames != null) {
            this.thrownExceptions = new ClassInfoList(this.thrownExceptionNames.length);
            for (String str : this.thrownExceptionNames) {
                ClassInfo classInfo = this.scanResult.getClassInfo(str);
                if (classInfo != null) {
                    this.thrownExceptions.add(classInfo);
                    classInfo.setScanResult(this.scanResult);
                }
            }
        }
        return this.thrownExceptions == null ? ClassInfoList.EMPTY_LIST : this.thrownExceptions;
    }

    public String[] getThrownExceptionNames() {
        return this.thrownExceptionNames == null ? new String[0] : this.thrownExceptionNames;
    }

    public boolean isConstructor() {
        return MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(this.name);
    }

    public boolean isSynchronized() {
        return Modifier.isSynchronized(this.modifiers);
    }

    public boolean isBridge() {
        return (this.modifiers & 64) != 0;
    }

    public boolean isVarArgs() {
        return (this.modifiers & 128) != 0;
    }

    public boolean isNative() {
        return Modifier.isNative(this.modifiers);
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this.modifiers);
    }

    public boolean isStrict() {
        return Modifier.isStrict(this.modifiers);
    }

    public boolean hasBody() {
        return this.hasBody;
    }

    public int getMinLineNum() {
        return this.minLineNum;
    }

    public int getMaxLineNum() {
        return this.maxLineNum;
    }

    public boolean isDefault() {
        ClassInfo classInfo = getClassInfo();
        return classInfo != null && classInfo.isInterface() && this.hasBody;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v76, types: [io.github.classgraph.AnnotationInfo[]] */
    public MethodParameterInfo[] getParameterInfo() {
        if (this.parameterInfo == null) {
            List<TypeSignature> list = null;
            MethodTypeSignature typeSignature = getTypeSignature();
            if (typeSignature != null) {
                list = typeSignature.getParameterTypeSignatures();
            }
            List<TypeSignature> list2 = null;
            try {
                MethodTypeSignature typeDescriptor = getTypeDescriptor();
                if (typeDescriptor != null) {
                    list2 = typeDescriptor.getParameterTypeSignatures();
                }
            } catch (Exception unused) {
            }
            int size = list == null ? 0 : list.size();
            if (list2 != null && list2.size() > size) {
                size = list2.size();
            }
            if (this.parameterNames != null && this.parameterNames.length > size) {
                size = this.parameterNames.length;
            }
            if (this.parameterModifiers != null && this.parameterModifiers.length > size) {
                size = this.parameterModifiers.length;
            }
            if (this.parameterAnnotationInfo != null && this.parameterAnnotationInfo.length > size) {
                size = this.parameterAnnotationInfo.length;
            }
            String[] strArr = null;
            if (this.parameterNames != null && this.parameterNames.length > 0) {
                if (this.parameterNames.length == size) {
                    strArr = this.parameterNames;
                } else {
                    strArr = new String[size];
                    int length = size - this.parameterNames.length;
                    for (int i = 0; i < this.parameterNames.length; i++) {
                        strArr[length + i] = this.parameterNames[i];
                    }
                }
            }
            int[] iArr = null;
            if (this.parameterModifiers != null && this.parameterModifiers.length > 0) {
                if (this.parameterModifiers.length == size) {
                    iArr = this.parameterModifiers;
                } else {
                    iArr = new int[size];
                    int length2 = size - this.parameterModifiers.length;
                    for (int i2 = 0; i2 < this.parameterModifiers.length; i2++) {
                        iArr[length2 + i2] = this.parameterModifiers[i2];
                    }
                }
            }
            AnnotationInfo[][] annotationInfoArr = (AnnotationInfo[][]) null;
            if (this.parameterAnnotationInfo != null && this.parameterAnnotationInfo.length > 0) {
                if (this.parameterAnnotationInfo.length == size) {
                    annotationInfoArr = this.parameterAnnotationInfo;
                } else {
                    annotationInfoArr = new AnnotationInfo[size];
                    int length3 = size - this.parameterAnnotationInfo.length;
                    for (int i3 = 0; i3 < this.parameterAnnotationInfo.length; i3++) {
                        annotationInfoArr[length3 + i3] = this.parameterAnnotationInfo[i3];
                    }
                }
            }
            List<TypeSignature> list3 = null;
            if (list != null && list.size() > 0) {
                if (list.size() == size) {
                    list3 = list;
                } else {
                    list3 = new ArrayList(size);
                    int size2 = size - list.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        list3.add(null);
                    }
                    list3.addAll(list);
                }
            }
            List<TypeSignature> list4 = null;
            if (list2 != null && list2.size() > 0) {
                if (list2.size() == size) {
                    list4 = list2;
                } else {
                    list4 = new ArrayList(size);
                    int size3 = size - list2.size();
                    for (int i5 = 0; i5 < size3; i5++) {
                        list4.add(null);
                    }
                    list4.addAll(list2);
                }
            }
            this.parameterInfo = new MethodParameterInfo[size];
            for (int i6 = 0; i6 < size; i6++) {
                this.parameterInfo[i6] = new MethodParameterInfo(this, annotationInfoArr == null ? null : annotationInfoArr[i6], iArr == null ? 0 : iArr[i6], list4 == null ? null : list4.get(i6), list3 == null ? null : list3.get(i6), strArr == null ? null : strArr[i6]);
                this.parameterInfo[i6].setScanResult(this.scanResult);
            }
        }
        return this.parameterInfo;
    }

    public boolean hasParameterAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasParameterAnnotation(cls.getName());
    }

    public boolean hasParameterAnnotation(String str) {
        for (MethodParameterInfo methodParameterInfo : getParameterInfo()) {
            if (methodParameterInfo.hasAnnotation(str)) {
                return true;
            }
        }
        return false;
    }

    private Class<?>[] loadParameterClasses() {
        ReferenceTypeSignature referenceTypeSignature;
        MethodParameterInfo[] parameterInfo = getParameterInfo();
        ArrayList arrayList = new ArrayList(parameterInfo.length);
        for (MethodParameterInfo methodParameterInfo : parameterInfo) {
            TypeSignature typeSignatureOrTypeDescriptor = methodParameterInfo.getTypeSignatureOrTypeDescriptor();
            if (typeSignatureOrTypeDescriptor instanceof TypeVariableSignature) {
                TypeParameter resolve = ((TypeVariableSignature) typeSignatureOrTypeDescriptor).resolve();
                if (resolve.classBound != null) {
                    referenceTypeSignature = resolve.classBound;
                } else if (resolve.interfaceBounds != null && !resolve.interfaceBounds.isEmpty()) {
                    referenceTypeSignature = resolve.interfaceBounds.get(0);
                } else {
                    throw new IllegalArgumentException("TypeVariableSignature has no bounds");
                }
            } else {
                referenceTypeSignature = typeSignatureOrTypeDescriptor;
            }
            arrayList.add(referenceTypeSignature.loadClass());
        }
        return (Class[]) arrayList.toArray(new Class[0]);
    }

    public Method loadClassAndGetMethod() {
        if (isConstructor()) {
            throw new IllegalArgumentException("Need to call loadClassAndGetConstructor() for constructors, not loadClassAndGetMethod()");
        }
        Class<?>[] loadParameterClasses = loadParameterClasses();
        try {
            return loadClass().getMethod(getName(), loadParameterClasses);
        } catch (NoSuchMethodException unused) {
            try {
                return loadClass().getDeclaredMethod(getName(), loadParameterClasses);
            } catch (NoSuchMethodException unused2) {
                throw new IllegalArgumentException("Method not found: " + getClassName() + "." + getName());
            }
        }
    }

    public Constructor<?> loadClassAndGetConstructor() {
        if (!isConstructor()) {
            throw new IllegalArgumentException("Need to call loadClassAndGetMethod() for non-constructor methods, not loadClassAndGetConstructor()");
        }
        Class<?>[] loadParameterClasses = loadParameterClasses();
        try {
            return loadClass().getConstructor(loadParameterClasses);
        } catch (NoSuchMethodException unused) {
            try {
                return loadClass().getDeclaredConstructor(loadParameterClasses);
            } catch (NoSuchMethodException unused2) {
                throw new IllegalArgumentException("Constructor not found for class " + getClassName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleRepeatableAnnotations(Set<String> set) {
        if (this.annotationInfo != null) {
            this.annotationInfo.handleRepeatableAnnotations(set, getClassInfo(), ClassInfo.RelType.METHOD_ANNOTATIONS, ClassInfo.RelType.CLASSES_WITH_METHOD_ANNOTATION, ClassInfo.RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION);
        }
        if (this.parameterAnnotationInfo != null) {
            for (int i = 0; i < this.parameterAnnotationInfo.length; i++) {
                AnnotationInfo[] annotationInfoArr = this.parameterAnnotationInfo[i];
                if (annotationInfoArr != null && annotationInfoArr.length > 0) {
                    boolean z = false;
                    int length = annotationInfoArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        if (!set.contains(annotationInfoArr[i2].getName())) {
                            i2++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        AnnotationInfoList annotationInfoList = new AnnotationInfoList(annotationInfoArr.length);
                        annotationInfoList.addAll(Arrays.asList(annotationInfoArr));
                        annotationInfoList.handleRepeatableAnnotations(set, getClassInfo(), ClassInfo.RelType.METHOD_PARAMETER_ANNOTATIONS, ClassInfo.RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION, ClassInfo.RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION);
                        this.parameterAnnotationInfo[i] = (AnnotationInfo[]) annotationInfoList.toArray(new AnnotationInfo[0]);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.typeDescriptor != null) {
            this.typeDescriptor.setScanResult(scanResult);
        }
        if (this.typeSignature != null) {
            this.typeSignature.setScanResult(scanResult);
        }
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                ((AnnotationInfo) it.next()).setScanResult(scanResult);
            }
        }
        if (this.parameterAnnotationInfo != null) {
            for (AnnotationInfo[] annotationInfoArr : this.parameterAnnotationInfo) {
                if (annotationInfoArr != null) {
                    for (AnnotationInfo annotationInfo : annotationInfoArr) {
                        annotationInfo.setScanResult(scanResult);
                    }
                }
            }
        }
        if (this.parameterInfo != null) {
            for (MethodParameterInfo methodParameterInfo : this.parameterInfo) {
                methodParameterInfo.setScanResult(scanResult);
            }
        }
        if (this.thrownExceptions != null) {
            Iterator it2 = this.thrownExceptions.iterator();
            while (it2.hasNext()) {
                ClassInfo classInfo = (ClassInfo) it2.next();
                if (classInfo.scanResult == null) {
                    classInfo.setScanResult(scanResult);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.github.classgraph.ScanResultObject
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        ClassInfoList thrownExceptions;
        try {
            MethodTypeSignature typeSignature = getTypeSignature();
            if (typeSignature != null) {
                typeSignature.findReferencedClassInfo(map, set, logNode);
            }
        } catch (IllegalArgumentException unused) {
            if (logNode != null) {
                logNode.log("Illegal type signature for method " + getClassName() + "." + getName() + ": " + getTypeSignatureStr());
            }
        }
        try {
            MethodTypeSignature typeDescriptor = getTypeDescriptor();
            if (typeDescriptor != null) {
                typeDescriptor.findReferencedClassInfo(map, set, logNode);
            }
        } catch (IllegalArgumentException unused2) {
            if (logNode != null) {
                logNode.log("Illegal type descriptor for method " + getClassName() + "." + getName() + ": " + getTypeDescriptorStr());
            }
        }
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                ((AnnotationInfo) it.next()).findReferencedClassInfo(map, set, logNode);
            }
        }
        for (MethodParameterInfo methodParameterInfo : getParameterInfo()) {
            AnnotationInfo[] annotationInfoArr = methodParameterInfo.annotationInfo;
            if (annotationInfoArr != null) {
                for (AnnotationInfo annotationInfo : annotationInfoArr) {
                    annotationInfo.findReferencedClassInfo(map, set, logNode);
                }
            }
        }
        if (this.thrownExceptionNames != null && (thrownExceptions = getThrownExceptions()) != null) {
            for (int i = 0; i < thrownExceptions.size(); i++) {
                map.put(this.thrownExceptionNames[i], thrownExceptions.get(i));
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodInfo)) {
            return false;
        }
        MethodInfo methodInfo = (MethodInfo) obj;
        return this.declaringClassName.equals(methodInfo.declaringClassName) && this.typeDescriptorStr.equals(methodInfo.typeDescriptorStr) && this.name.equals(methodInfo.name);
    }

    public int hashCode() {
        return this.name.hashCode() + (this.typeDescriptorStr.hashCode() * 11) + (this.declaringClassName.hashCode() * 57);
    }

    @Override // java.lang.Comparable
    public int compareTo(MethodInfo methodInfo) {
        int compareTo = this.declaringClassName.compareTo(methodInfo.declaringClassName);
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = this.name.compareTo(methodInfo.name);
        if (compareTo2 != 0) {
            return compareTo2;
        }
        return this.typeDescriptorStr.compareTo(methodInfo.typeDescriptorStr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        String name;
        AnnotationInfoList annotationInfoList;
        MethodTypeSignature typeSignatureOrTypeDescriptor = getTypeSignatureOrTypeDescriptor();
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                annotationInfo.toString(z, sb);
            }
        }
        if (this.modifiers != 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.METHOD, isDefault(), sb);
        }
        List<TypeParameter> typeParameters = typeSignatureOrTypeDescriptor.getTypeParameters();
        if (!typeParameters.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append('<');
            for (int i = 0; i < typeParameters.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                typeParameters.get(i).toString(z, sb);
            }
            sb.append('>');
        }
        if (!isConstructor()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            typeSignatureOrTypeDescriptor.getResultType().toStringInternal(z, this.annotationInfo, sb);
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        if (this.name != null) {
            sb.append(z ? ClassInfo.getSimpleName(this.name) : this.name);
        }
        MethodParameterInfo[] parameterInfo = getParameterInfo();
        boolean z2 = false;
        int length = parameterInfo.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (parameterInfo[i2].getName() == null) {
                i2++;
            } else {
                z2 = true;
                break;
            }
        }
        int i3 = -1;
        if (isVarArgs()) {
            int length2 = parameterInfo.length - 1;
            while (true) {
                if (length2 < 0) {
                    break;
                }
                int modifiers = parameterInfo[length2].getModifiers();
                if ((modifiers & 4096) == 0 && (modifiers & 32768) == 0 && (parameterInfo[length2].getTypeSignatureOrTypeDescriptor() instanceof ArrayTypeSignature)) {
                    i3 = length2;
                    break;
                }
                length2--;
            }
        }
        sb.append('(');
        int length3 = parameterInfo.length;
        for (int i4 = 0; i4 < length3; i4++) {
            MethodParameterInfo methodParameterInfo = parameterInfo[i4];
            if (i4 > 0) {
                sb.append(", ");
            }
            if (methodParameterInfo.annotationInfo != null) {
                for (AnnotationInfo annotationInfo2 : methodParameterInfo.annotationInfo) {
                    annotationInfo2.toString(z, sb);
                    sb.append(' ');
                }
            }
            MethodParameterInfo.modifiersToString(methodParameterInfo.getModifiers(), sb);
            TypeSignature typeSignatureOrTypeDescriptor2 = methodParameterInfo.getTypeSignatureOrTypeDescriptor();
            if (typeSignatureOrTypeDescriptor2 != null) {
                if (i4 == i3) {
                    if (!(typeSignatureOrTypeDescriptor2 instanceof ArrayTypeSignature)) {
                        throw new IllegalArgumentException("Got non-array type for last parameter of varargs method " + this.name);
                    }
                    ArrayTypeSignature arrayTypeSignature = (ArrayTypeSignature) typeSignatureOrTypeDescriptor2;
                    if (arrayTypeSignature.getNumDimensions() == 0) {
                        throw new IllegalArgumentException("Got a zero-dimension array type for last parameter of varargs method " + this.name);
                    }
                    arrayTypeSignature.getElementTypeSignature().toString(z, sb);
                    for (int i5 = 0; i5 < arrayTypeSignature.getNumDimensions() - 1; i5++) {
                        sb.append("[]");
                    }
                    sb.append("...");
                } else {
                    if (methodParameterInfo.annotationInfo == null || methodParameterInfo.annotationInfo.length == 0) {
                        annotationInfoList = null;
                    } else {
                        AnnotationInfoList annotationInfoList2 = new AnnotationInfoList(methodParameterInfo.annotationInfo.length);
                        annotationInfoList = annotationInfoList2;
                        annotationInfoList2.addAll(Arrays.asList(methodParameterInfo.annotationInfo));
                    }
                    typeSignatureOrTypeDescriptor2.toStringInternal(z, annotationInfoList, sb);
                }
            }
            if (z2 && (name = methodParameterInfo.getName()) != null) {
                if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
                sb.append(name);
            }
        }
        sb.append(')');
        if (!typeSignatureOrTypeDescriptor.getThrowsSignatures().isEmpty()) {
            sb.append(" throws ");
            for (int i6 = 0; i6 < typeSignatureOrTypeDescriptor.getThrowsSignatures().size(); i6++) {
                if (i6 > 0) {
                    sb.append(", ");
                }
                typeSignatureOrTypeDescriptor.getThrowsSignatures().get(i6).toString(z, sb);
            }
            return;
        }
        if (this.thrownExceptionNames != null && this.thrownExceptionNames.length > 0) {
            sb.append(" throws ");
            for (int i7 = 0; i7 < this.thrownExceptionNames.length; i7++) {
                if (i7 > 0) {
                    sb.append(", ");
                }
                sb.append(z ? ClassInfo.getSimpleName(this.thrownExceptionNames[i7]) : this.thrownExceptionNames[i7]);
            }
        }
    }
}
