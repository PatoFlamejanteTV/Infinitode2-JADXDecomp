package io.github.classgraph;

import io.github.classgraph.ClassInfo;
import io.github.classgraph.Classfile;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.TypeUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/FieldInfo.class */
public class FieldInfo extends ClassMemberInfo implements Comparable<FieldInfo> {
    private transient TypeSignature typeSignature;
    private transient TypeSignature typeDescriptor;
    private ObjectTypedValueWrapper constantInitializerValue;
    private transient List<Classfile.TypeAnnotationDecorator> typeAnnotationDecorators;

    FieldInfo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldInfo(String str, String str2, int i, String str3, String str4, Object obj, AnnotationInfoList annotationInfoList, List<Classfile.TypeAnnotationDecorator> list) {
        super(str, str2, i, str3, str4, annotationInfoList);
        if (str2 == null) {
            throw new IllegalArgumentException("fieldName must not be null");
        }
        this.constantInitializerValue = obj == null ? null : new ObjectTypedValueWrapper(obj);
        this.typeAnnotationDecorators = list;
    }

    @Deprecated
    public String getModifierStr() {
        return getModifiersStr();
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public String getModifiersStr() {
        StringBuilder sb = new StringBuilder();
        TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.FIELD, false, sb);
        return sb.toString();
    }

    public boolean isTransient() {
        return Modifier.isTransient(this.modifiers);
    }

    public boolean isEnum() {
        return (this.modifiers & 16384) != 0;
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public TypeSignature getTypeDescriptor() {
        if (this.typeDescriptorStr == null) {
            return null;
        }
        if (this.typeDescriptor == null) {
            try {
                this.typeDescriptor = TypeSignature.parse(this.typeDescriptorStr, this.declaringClassName);
                this.typeDescriptor.setScanResult(this.scanResult);
                if (this.typeAnnotationDecorators != null) {
                    Iterator<Classfile.TypeAnnotationDecorator> it = this.typeAnnotationDecorators.iterator();
                    while (it.hasNext()) {
                        it.next().decorate(this.typeDescriptor);
                    }
                }
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return this.typeDescriptor;
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public TypeSignature getTypeSignature() {
        if (this.typeSignatureStr == null) {
            return null;
        }
        if (this.typeSignature == null) {
            try {
                this.typeSignature = TypeSignature.parse(this.typeSignatureStr, this.declaringClassName);
                this.typeSignature.setScanResult(this.scanResult);
                if (this.typeAnnotationDecorators != null) {
                    Iterator<Classfile.TypeAnnotationDecorator> it = this.typeAnnotationDecorators.iterator();
                    while (it.hasNext()) {
                        it.next().decorate(this.typeSignature);
                    }
                }
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid type signature for field " + getClassName() + "." + getName() + (getClassInfo() != null ? " in classpath element " + getClassInfo().getClasspathElementURI() : "") + " : " + this.typeSignatureStr, e);
            }
        }
        return this.typeSignature;
    }

    @Override // io.github.classgraph.ClassMemberInfo
    public TypeSignature getTypeSignatureOrTypeDescriptor() {
        try {
            TypeSignature typeSignature = getTypeSignature();
            if (typeSignature != null) {
                return typeSignature;
            }
        } catch (Exception unused) {
        }
        return getTypeDescriptor();
    }

    public Object getConstantInitializerValue() {
        if (!this.scanResult.scanSpec.enableStaticFinalFieldConstantInitializerValues) {
            throw new IllegalArgumentException("Please call ClassGraph#enableStaticFinalFieldConstantInitializerValues() before #scan()");
        }
        if (this.constantInitializerValue == null) {
            return null;
        }
        return this.constantInitializerValue.get();
    }

    public Field loadClassAndGetField() {
        try {
            return loadClass().getField(getName());
        } catch (NoSuchFieldException unused) {
            try {
                return loadClass().getDeclaredField(getName());
            } catch (NoSuchFieldException unused2) {
                throw new IllegalArgumentException("No such field: " + getClassName() + "." + getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleRepeatableAnnotations(Set<String> set) {
        if (this.annotationInfo != null) {
            this.annotationInfo.handleRepeatableAnnotations(set, getClassInfo(), ClassInfo.RelType.FIELD_ANNOTATIONS, ClassInfo.RelType.CLASSES_WITH_FIELD_ANNOTATION, ClassInfo.RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.typeSignature != null) {
            this.typeSignature.setScanResult(scanResult);
        }
        if (this.typeDescriptor != null) {
            this.typeDescriptor.setScanResult(scanResult);
        }
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                ((AnnotationInfo) it.next()).setScanResult(scanResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        try {
            TypeSignature typeSignature = getTypeSignature();
            if (typeSignature != null) {
                typeSignature.findReferencedClassInfo(map, set, logNode);
            }
        } catch (IllegalArgumentException unused) {
            if (logNode != null) {
                logNode.log("Illegal type signature for field " + getClassName() + "." + getName() + ": " + getTypeSignatureStr());
            }
        }
        try {
            TypeSignature typeDescriptor = getTypeDescriptor();
            if (typeDescriptor != null) {
                typeDescriptor.findReferencedClassInfo(map, set, logNode);
            }
        } catch (IllegalArgumentException unused2) {
            if (logNode != null) {
                logNode.log("Illegal type descriptor for field " + getClassName() + "." + getName() + ": " + getTypeDescriptorStr());
            }
        }
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                ((AnnotationInfo) it.next()).findReferencedClassInfo(map, set, logNode);
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldInfo)) {
            return false;
        }
        FieldInfo fieldInfo = (FieldInfo) obj;
        return this.declaringClassName.equals(fieldInfo.declaringClassName) && this.name.equals(fieldInfo.name);
    }

    public int hashCode() {
        return this.name.hashCode() + (this.declaringClassName.hashCode() * 11);
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldInfo fieldInfo) {
        int compareTo = this.declaringClassName.compareTo(fieldInfo.declaringClassName);
        if (compareTo != 0) {
            return compareTo;
        }
        return this.name.compareTo(fieldInfo.name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void toString(boolean z, boolean z2, StringBuilder sb) {
        if (this.annotationInfo != null) {
            Iterator it = this.annotationInfo.iterator();
            while (it.hasNext()) {
                AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ' && sb.charAt(sb.length() - 1) != '(') {
                    sb.append(' ');
                }
                annotationInfo.toString(z2, sb);
            }
        }
        if (this.modifiers != 0 && z) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ' && sb.charAt(sb.length() - 1) != '(') {
                sb.append(' ');
            }
            TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.FIELD, false, sb);
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ' && sb.charAt(sb.length() - 1) != '(') {
            sb.append(' ');
        }
        getTypeSignatureOrTypeDescriptor().toStringInternal(z2, this.annotationInfo, sb);
        sb.append(' ');
        sb.append(this.name);
        if (this.constantInitializerValue != null) {
            Object obj = this.constantInitializerValue.get();
            sb.append(" = ");
            if (obj instanceof String) {
                sb.append('\"').append(((String) obj).replace("\\", "\\\\").replace("\"", "\\\"")).append('\"');
            } else if (obj instanceof Character) {
                sb.append('\'').append(((Character) obj).toString().replace("\\", "\\\\").replaceAll("'", "\\'")).append('\'');
            } else {
                sb.append(obj == null ? "null" : obj.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        toString(true, z, sb);
    }
}
