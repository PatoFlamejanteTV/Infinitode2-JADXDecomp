package io.github.classgraph;

import io.github.classgraph.Classfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.description.method.MethodDescription;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/MethodTypeSignature.class */
public final class MethodTypeSignature extends HierarchicalTypeSignature {
    final List<TypeParameter> typeParameters;
    private final List<TypeSignature> parameterTypeSignatures;
    private final TypeSignature resultType;
    private final List<ClassRefOrTypeVariableSignature> throwsSignatures;
    private AnnotationInfoList receiverTypeAnnotationInfo;

    private MethodTypeSignature(List<TypeParameter> list, List<TypeSignature> list2, TypeSignature typeSignature, List<ClassRefOrTypeVariableSignature> list3) {
        this.typeParameters = list;
        this.parameterTypeSignatures = list2;
        this.resultType = typeSignature;
        this.throwsSignatures = list3;
    }

    public final List<TypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<TypeSignature> getParameterTypeSignatures() {
        return this.parameterTypeSignatures;
    }

    public final TypeSignature getResultType() {
        return this.resultType;
    }

    public final List<ClassRefOrTypeVariableSignature> getThrowsSignatures() {
        return this.throwsSignatures;
    }

    @Override // io.github.classgraph.HierarchicalTypeSignature
    protected final void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo) {
        throw new IllegalArgumentException("Cannot call this method on " + MethodTypeSignature.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addRecieverTypeAnnotation(AnnotationInfo annotationInfo) {
        if (this.receiverTypeAnnotationInfo == null) {
            this.receiverTypeAnnotationInfo = new AnnotationInfoList(1);
        }
        this.receiverTypeAnnotationInfo.add(annotationInfo);
    }

    public final AnnotationInfoList getReceiverTypeAnnotationInfo() {
        return this.receiverTypeAnnotationInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public final String getClassName() {
        throw new IllegalArgumentException("getClassName() cannot be called here");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public final ClassInfo getClassInfo() {
        throw new IllegalArgumentException("getClassInfo() cannot be called here");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.HierarchicalTypeSignature, io.github.classgraph.ScanResultObject
    public final void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.typeParameters != null) {
            Iterator<TypeParameter> it = this.typeParameters.iterator();
            while (it.hasNext()) {
                it.next().setScanResult(scanResult);
            }
        }
        if (this.parameterTypeSignatures != null) {
            Iterator<TypeSignature> it2 = this.parameterTypeSignatures.iterator();
            while (it2.hasNext()) {
                it2.next().setScanResult(scanResult);
            }
        }
        if (this.resultType != null) {
            this.resultType.setScanResult(scanResult);
        }
        if (this.throwsSignatures != null) {
            Iterator<ClassRefOrTypeVariableSignature> it3 = this.throwsSignatures.iterator();
            while (it3.hasNext()) {
                it3.next().setScanResult(scanResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void findReferencedClassNames(Set<String> set) {
        for (TypeParameter typeParameter : this.typeParameters) {
            if (typeParameter != null) {
                typeParameter.findReferencedClassNames(set);
            }
        }
        for (TypeSignature typeSignature : this.parameterTypeSignatures) {
            if (typeSignature != null) {
                typeSignature.findReferencedClassNames(set);
            }
        }
        this.resultType.findReferencedClassNames(set);
        for (ClassRefOrTypeVariableSignature classRefOrTypeVariableSignature : this.throwsSignatures) {
            if (classRefOrTypeVariableSignature != null) {
                classRefOrTypeVariableSignature.findReferencedClassNames(set);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public final void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        HashSet hashSet = new HashSet();
        findReferencedClassNames(hashSet);
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            ClassInfo orCreateClassInfo = ClassInfo.getOrCreateClassInfo(it.next(), map);
            orCreateClassInfo.scanResult = this.scanResult;
            set.add(orCreateClassInfo);
        }
    }

    public final int hashCode() {
        return this.typeParameters.hashCode() + (this.parameterTypeSignatures.hashCode() * 7) + (this.resultType.hashCode() * 15) + (this.throwsSignatures.hashCode() * 31);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodTypeSignature)) {
            return false;
        }
        MethodTypeSignature methodTypeSignature = (MethodTypeSignature) obj;
        return methodTypeSignature.typeParameters.equals(this.typeParameters) && methodTypeSignature.parameterTypeSignatures.equals(this.parameterTypeSignatures) && methodTypeSignature.resultType.equals(this.resultType) && methodTypeSignature.throwsSignatures.equals(this.throwsSignatures);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public final void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb) {
        if (!this.typeParameters.isEmpty()) {
            sb.append('<');
            for (int i = 0; i < this.typeParameters.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                this.typeParameters.get(i).toString(z, sb);
            }
            sb.append('>');
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(this.resultType.toString());
        sb.append(" (");
        for (int i2 = 0; i2 < this.parameterTypeSignatures.size(); i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            this.parameterTypeSignatures.get(i2).toString(z, sb);
        }
        sb.append(')');
        if (!this.throwsSignatures.isEmpty()) {
            sb.append(" throws ");
            for (int i3 = 0; i3 < this.throwsSignatures.size(); i3++) {
                if (i3 > 0) {
                    sb.append(", ");
                }
                this.throwsSignatures.get(i3).toString(z, sb);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MethodTypeSignature parse(String str, String str2) {
        List emptyList;
        if (str.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
            return new MethodTypeSignature(Collections.emptyList(), Collections.emptyList(), new BaseTypeSignature('V'), Collections.emptyList());
        }
        Parser parser = new Parser(str);
        List<TypeParameter> parseList = TypeParameter.parseList(parser, str2);
        parser.expect('(');
        ArrayList arrayList = new ArrayList();
        while (parser.peek() != ')') {
            if (!parser.hasMore()) {
                throw new ParseException(parser, "Ran out of input while parsing method signature");
            }
            TypeSignature parse = TypeSignature.parse(parser, str2);
            if (parse == null) {
                throw new ParseException(parser, "Missing method parameter type signature");
            }
            arrayList.add(parse);
        }
        parser.expect(')');
        TypeSignature parse2 = TypeSignature.parse(parser, str2);
        if (parse2 == null) {
            throw new ParseException(parser, "Missing method result type signature");
        }
        if (parser.peek() == '^') {
            emptyList = new ArrayList();
            while (parser.peek() == '^') {
                parser.expect('^');
                ClassRefTypeSignature parse3 = ClassRefTypeSignature.parse(parser, str2);
                if (parse3 != null) {
                    emptyList.add(parse3);
                } else {
                    TypeVariableSignature parse4 = TypeVariableSignature.parse(parser, str2);
                    if (parse4 == null) {
                        throw new ParseException(parser, "Missing type variable signature");
                    }
                    emptyList.add(parse4);
                }
            }
        } else {
            emptyList = Collections.emptyList();
        }
        if (parser.hasMore()) {
            throw new ParseException(parser, "Extra characters at end of type descriptor");
        }
        MethodTypeSignature methodTypeSignature = new MethodTypeSignature(parseList, arrayList, parse2, emptyList);
        List list = (List) parser.getState();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((TypeVariableSignature) it.next()).containingMethodSignature = methodTypeSignature;
            }
        }
        return methodTypeSignature;
    }
}
