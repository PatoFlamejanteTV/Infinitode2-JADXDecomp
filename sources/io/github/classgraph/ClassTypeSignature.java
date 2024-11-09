package io.github.classgraph;

import com.vladsch.flexmark.util.html.Attribute;
import io.github.classgraph.Classfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;
import nonapi.io.github.classgraph.types.TypeUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassTypeSignature.class */
public final class ClassTypeSignature extends HierarchicalTypeSignature {
    private final ClassInfo classInfo;
    final List<TypeParameter> typeParameters;
    private final ClassRefTypeSignature superclassSignature;
    private final List<ClassRefTypeSignature> superinterfaceSignatures;
    private final List<ClassRefOrTypeVariableSignature> throwsSignatures;

    private ClassTypeSignature(ClassInfo classInfo, List<TypeParameter> list, ClassRefTypeSignature classRefTypeSignature, List<ClassRefTypeSignature> list2, List<ClassRefOrTypeVariableSignature> list3) {
        this.classInfo = classInfo;
        this.typeParameters = list;
        this.superclassSignature = classRefTypeSignature;
        this.superinterfaceSignatures = list2;
        this.throwsSignatures = list3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassTypeSignature(ClassInfo classInfo, ClassInfo classInfo2, ClassInfoList classInfoList) {
        ClassRefTypeSignature classRefTypeSignature;
        List<ClassRefTypeSignature> emptyList;
        this.classInfo = classInfo;
        this.typeParameters = Collections.emptyList();
        ClassRefTypeSignature classRefTypeSignature2 = null;
        if (classInfo2 == null) {
            classRefTypeSignature = null;
        } else {
            try {
                classRefTypeSignature = (ClassRefTypeSignature) TypeSignature.parse("L" + classInfo2.getName().replace('.', '/') + ";", classInfo.getName());
            } catch (ParseException unused) {
            }
        }
        classRefTypeSignature2 = classRefTypeSignature;
        this.superclassSignature = classRefTypeSignature2;
        if (classInfoList == null || classInfoList.isEmpty()) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = new ArrayList<>(classInfoList.size());
        }
        this.superinterfaceSignatures = emptyList;
        if (classInfoList != null) {
            Iterator it = classInfoList.iterator();
            while (it.hasNext()) {
                try {
                    this.superinterfaceSignatures.add((ClassRefTypeSignature) TypeSignature.parse("L" + ((ClassInfo) it.next()).getName().replace('.', '/') + ";", classInfo.getName()));
                } catch (ParseException unused2) {
                }
            }
        }
        this.throwsSignatures = null;
    }

    public final List<TypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    public final ClassRefTypeSignature getSuperclassSignature() {
        return this.superclassSignature;
    }

    public final List<ClassRefTypeSignature> getSuperinterfaceSignatures() {
        return this.superinterfaceSignatures;
    }

    final List<ClassRefOrTypeVariableSignature> getThrowsSignatures() {
        return this.throwsSignatures;
    }

    @Override // io.github.classgraph.HierarchicalTypeSignature
    protected final void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo) {
        throw new IllegalArgumentException("Cannot call this method on " + ClassTypeSignature.class.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public final String getClassName() {
        if (this.classInfo != null) {
            return this.classInfo.getName();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public final ClassInfo getClassInfo() {
        return this.classInfo;
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
        if (this.superclassSignature != null) {
            this.superclassSignature.setScanResult(scanResult);
        }
        if (this.superinterfaceSignatures != null) {
            Iterator<ClassRefTypeSignature> it2 = this.superinterfaceSignatures.iterator();
            while (it2.hasNext()) {
                it2.next().setScanResult(scanResult);
            }
        }
    }

    protected final void findReferencedClassNames(Set<String> set) {
        Iterator<TypeParameter> it = this.typeParameters.iterator();
        while (it.hasNext()) {
            it.next().findReferencedClassNames(set);
        }
        if (this.superclassSignature != null) {
            this.superclassSignature.findReferencedClassNames(set);
        }
        if (this.superinterfaceSignatures != null) {
            Iterator<ClassRefTypeSignature> it2 = this.superinterfaceSignatures.iterator();
            while (it2.hasNext()) {
                it2.next().findReferencedClassNames(set);
            }
        }
        if (this.throwsSignatures != null) {
            Iterator<ClassRefOrTypeVariableSignature> it3 = this.throwsSignatures.iterator();
            while (it3.hasNext()) {
                it3.next().findReferencedClassNames(set);
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
        return this.typeParameters.hashCode() + ((this.superclassSignature == null ? 1 : this.superclassSignature.hashCode()) * 7) + ((this.superinterfaceSignatures == null ? 1 : this.superinterfaceSignatures.hashCode()) * 15);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassTypeSignature)) {
            return false;
        }
        ClassTypeSignature classTypeSignature = (ClassTypeSignature) obj;
        return Objects.equals(classTypeSignature.typeParameters, this.typeParameters) && Objects.equals(classTypeSignature.superclassSignature, this.superclassSignature) && Objects.equals(classTypeSignature.superinterfaceSignatures, this.superinterfaceSignatures);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void toStringInternal(String str, boolean z, int i, boolean z2, boolean z3, AnnotationInfoList annotationInfoList, StringBuilder sb) {
        if (this.throwsSignatures != null) {
            for (ClassRefOrTypeVariableSignature classRefOrTypeVariableSignature : this.throwsSignatures) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append("@throws(").append(classRefOrTypeVariableSignature).append(")");
            }
        }
        if (i != 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            TypeUtils.modifiersToString(i, TypeUtils.ModifierType.CLASS, false, sb);
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(z2 ? "@interface" : z3 ? "interface" : (i & 16384) != 0 ? "enum" : Attribute.CLASS_ATTR);
        sb.append(' ');
        if (str != null) {
            sb.append(z ? ClassInfo.getSimpleName(str) : str);
        }
        if (!this.typeParameters.isEmpty()) {
            sb.append('<');
            for (int i2 = 0; i2 < this.typeParameters.size(); i2++) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                this.typeParameters.get(i2).toStringInternal(z, null, sb);
            }
            sb.append('>');
        }
        if (this.superclassSignature != null) {
            String classRefTypeSignature = this.superclassSignature.toString(z);
            if (!classRefTypeSignature.equals("java.lang.Object") && (!classRefTypeSignature.equals("Object") || !this.superclassSignature.className.equals("java.lang.Object"))) {
                sb.append(" extends ");
                sb.append(classRefTypeSignature);
            }
        }
        if (this.superinterfaceSignatures != null && !this.superinterfaceSignatures.isEmpty()) {
            sb.append(z3 ? " extends " : " implements ");
            for (int i3 = 0; i3 < this.superinterfaceSignatures.size(); i3++) {
                if (i3 > 0) {
                    sb.append(", ");
                }
                this.superinterfaceSignatures.get(i3).toStringInternal(z, null, sb);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public final void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb) {
        toStringInternal(this.classInfo.getName(), z, this.classInfo.getModifiers(), this.classInfo.isAnnotation(), this.classInfo.isInterface(), annotationInfoList, sb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassTypeSignature parse(String str, ClassInfo classInfo) {
        List emptyList;
        ArrayList arrayList;
        Parser parser = new Parser(str);
        List<TypeParameter> parseList = TypeParameter.parseList(parser, null);
        ClassRefTypeSignature parse = ClassRefTypeSignature.parse(parser, (String) null);
        if (parser.hasMore()) {
            emptyList = new ArrayList();
            while (parser.hasMore() && parser.peek() != '^') {
                ClassRefTypeSignature parse2 = ClassRefTypeSignature.parse(parser, (String) null);
                if (parse2 == null) {
                    throw new ParseException(parser, "Could not parse superinterface signature");
                }
                emptyList.add(parse2);
            }
        } else {
            emptyList = Collections.emptyList();
        }
        if (parser.peek() == '^') {
            arrayList = new ArrayList();
            while (parser.peek() == '^') {
                parser.expect('^');
                ClassRefTypeSignature parse3 = ClassRefTypeSignature.parse(parser, classInfo.getName());
                if (parse3 != null) {
                    arrayList.add(parse3);
                } else {
                    TypeVariableSignature parse4 = TypeVariableSignature.parse(parser, classInfo.getName());
                    if (parse4 != null) {
                        arrayList.add(parse4);
                    } else {
                        throw new ParseException(parser, "Missing type variable signature");
                    }
                }
            }
        } else {
            arrayList = null;
        }
        if (parser.hasMore()) {
            throw new ParseException(parser, "Extra characters at end of type descriptor");
        }
        return new ClassTypeSignature(classInfo, parseList, parse, emptyList, arrayList);
    }
}
