package io.github.classgraph;

import io.github.classgraph.Classfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;
import nonapi.io.github.classgraph.types.TypeUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/TypeVariableSignature.class */
public final class TypeVariableSignature extends ClassRefOrTypeVariableSignature {
    private final String name;
    private final String definingClassName;
    MethodTypeSignature containingMethodSignature;
    private TypeParameter typeParameterCached;

    private TypeVariableSignature(String str, String str2) {
        this.name = str;
        this.definingClassName = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final TypeParameter resolve() {
        if (this.typeParameterCached != null) {
            return this.typeParameterCached;
        }
        if (this.containingMethodSignature != null && this.containingMethodSignature.typeParameters != null && !this.containingMethodSignature.typeParameters.isEmpty()) {
            for (TypeParameter typeParameter : this.containingMethodSignature.typeParameters) {
                if (typeParameter.name.equals(this.name)) {
                    this.typeParameterCached = typeParameter;
                    return typeParameter;
                }
            }
        }
        if (getClassName() != null) {
            ClassInfo classInfo = getClassInfo();
            if (classInfo == null) {
                throw new IllegalArgumentException("Could not find ClassInfo object for " + this.definingClassName);
            }
            ClassTypeSignature classTypeSignature = null;
            try {
                classTypeSignature = classInfo.getTypeSignature();
            } catch (Exception unused) {
            }
            if (classTypeSignature != null && classTypeSignature.typeParameters != null && !classTypeSignature.typeParameters.isEmpty()) {
                for (TypeParameter typeParameter2 : classTypeSignature.typeParameters) {
                    if (typeParameter2.name.equals(this.name)) {
                        this.typeParameterCached = typeParameter2;
                        return typeParameter2;
                    }
                }
            }
        }
        TypeParameter typeParameter3 = new TypeParameter(this.name, null, Collections.emptyList());
        typeParameter3.setScanResult(this.scanResult);
        this.typeParameterCached = typeParameter3;
        return typeParameter3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.TypeSignature, io.github.classgraph.HierarchicalTypeSignature
    public final void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo) {
        if (list.isEmpty()) {
            addTypeAnnotation(annotationInfo);
            return;
        }
        throw new IllegalArgumentException("Type variable should have empty typePath");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TypeVariableSignature parse(Parser parser, String str) {
        if (parser.peek() == 'T') {
            parser.next();
            if (!TypeUtils.getIdentifierToken(parser, false)) {
                throw new ParseException(parser, "Could not parse type variable signature");
            }
            parser.expect(';');
            TypeVariableSignature typeVariableSignature = new TypeVariableSignature(parser.currToken(), str);
            List list = (List) parser.getState();
            List list2 = list;
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                list2 = arrayList;
                parser.setState(arrayList);
            }
            list2.add(typeVariableSignature);
            return typeVariableSignature;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public final String getClassName() {
        return this.definingClassName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.TypeSignature
    public final void findReferencedClassNames(Set<String> set) {
    }

    @Override // io.github.classgraph.HierarchicalTypeSignature, io.github.classgraph.ScanResultObject
    final void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.typeParameterCached != null) {
            this.typeParameterCached.setScanResult(scanResult);
        }
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeVariableSignature)) {
            return false;
        }
        TypeVariableSignature typeVariableSignature = (TypeVariableSignature) obj;
        return typeVariableSignature.name.equals(this.name) && Objects.equals(typeVariableSignature.typeAnnotationInfo, this.typeAnnotationInfo);
    }

    @Override // io.github.classgraph.TypeSignature
    public final boolean equalsIgnoringTypeParams(TypeSignature typeSignature) {
        if (typeSignature instanceof ClassRefTypeSignature) {
            if (((ClassRefTypeSignature) typeSignature).className.equals("java.lang.Object")) {
                return true;
            }
            try {
                TypeParameter resolve = resolve();
                if (resolve.classBound == null && (resolve.interfaceBounds == null || resolve.interfaceBounds.isEmpty())) {
                    return true;
                }
                if (resolve.classBound != null) {
                    if (resolve.classBound instanceof ClassRefTypeSignature) {
                        if (resolve.classBound.equals(typeSignature)) {
                            return true;
                        }
                    } else {
                        if (resolve.classBound instanceof TypeVariableSignature) {
                            return equalsIgnoringTypeParams(resolve.classBound);
                        }
                        return false;
                    }
                }
                if (resolve.interfaceBounds != null) {
                    for (ReferenceTypeSignature referenceTypeSignature : resolve.interfaceBounds) {
                        if (referenceTypeSignature instanceof ClassRefTypeSignature) {
                            if (referenceTypeSignature.equals(typeSignature)) {
                                return true;
                            }
                        } else {
                            if (referenceTypeSignature instanceof TypeVariableSignature) {
                                return equalsIgnoringTypeParams(referenceTypeSignature);
                            }
                            return false;
                        }
                    }
                    return false;
                }
                return false;
            } catch (IllegalArgumentException unused) {
                return true;
            }
        }
        return equals(typeSignature);
    }

    public final String toStringWithTypeBound() {
        try {
            return resolve().toString();
        } catch (IllegalArgumentException unused) {
            return this.name;
        }
    }

    @Override // io.github.classgraph.HierarchicalTypeSignature
    protected final void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb) {
        if (this.typeAnnotationInfo != null) {
            Iterator it = this.typeAnnotationInfo.iterator();
            while (it.hasNext()) {
                AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
                if (annotationInfoList == null || !annotationInfoList.contains(annotationInfo)) {
                    annotationInfo.toString(z, sb);
                    sb.append(' ');
                }
            }
        }
        sb.append(this.name);
    }
}
