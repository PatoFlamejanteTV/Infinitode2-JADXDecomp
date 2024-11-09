package io.github.classgraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;
import nonapi.io.github.classgraph.types.TypeUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassRefTypeSignature.class */
public final class ClassRefTypeSignature extends ClassRefOrTypeVariableSignature {
    final String className;
    private final List<TypeArgument> typeArguments;
    private final List<String> suffixes;
    private final List<List<TypeArgument>> suffixTypeArguments;
    private List<AnnotationInfoList> suffixTypeAnnotations;

    private ClassRefTypeSignature(String str, List<TypeArgument> list, List<String> list2, List<List<TypeArgument>> list3) {
        this.className = str;
        this.typeArguments = list;
        this.suffixes = list2;
        this.suffixTypeArguments = list3;
    }

    public final String getBaseClassName() {
        return this.className;
    }

    public final String getFullyQualifiedClassName() {
        if (this.suffixes.isEmpty()) {
            return this.className;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.className);
        for (String str : this.suffixes) {
            sb.append('$');
            sb.append(str);
        }
        return sb.toString();
    }

    public final List<TypeArgument> getTypeArguments() {
        return this.typeArguments;
    }

    public final List<String> getSuffixes() {
        return this.suffixes;
    }

    public final List<List<TypeArgument>> getSuffixTypeArguments() {
        return this.suffixTypeArguments;
    }

    public final List<AnnotationInfoList> getSuffixTypeAnnotationInfo() {
        return this.suffixTypeAnnotations;
    }

    private void addSuffixTypeAnnotation(int i, AnnotationInfo annotationInfo) {
        if (this.suffixTypeAnnotations == null) {
            this.suffixTypeAnnotations = new ArrayList(this.suffixes.size());
            for (int i2 = 0; i2 < this.suffixes.size(); i2++) {
                this.suffixTypeAnnotations.add(new AnnotationInfoList(1));
            }
        }
        this.suffixTypeAnnotations.get(i).add(annotationInfo);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0060, code lost:            r11 = -1;        r12 = -1;        r13 = r6.className;     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0077, code lost:            if (r11 < r6.suffixes.size()) goto L18;     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0091, code lost:            if (r11 == (r6.suffixes.size() - 1)) goto L33;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0094, code lost:            r0 = r6.scanResult.getClassInfo(r13);        r13 = r13 + '$' + r6.suffixes.get(r11 + 1);        r0 = r6.scanResult.getClassInfo(r13);     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00d5, code lost:            if (r0 == null) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00da, code lost:            if (r0 == null) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00e2, code lost:            if (r0.isInterfaceOrAnnotation() != false) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00ea, code lost:            if (r0.isInterfaceOrAnnotation() != false) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00f2, code lost:            if (r0.isStatic() != false) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ff, code lost:            if (r0.getInnerClasses().contains(r0) != false) goto L33;     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0102, code lost:            r0 = true;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x010a, code lost:            if (r0 != false) goto L59;     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x010d, code lost:            r12 = r12 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0113, code lost:            if (r12 >= r9) goto L57;     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x011f, code lost:            if (r10 != (-1)) goto L47;     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0125, code lost:            if (r11 != (-1)) goto L45;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0128, code lost:            addTypeAnnotation(r8);     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x012d, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x012e, code lost:            addSuffixTypeAnnotation(r11, r8);     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0135, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0139, code lost:            if (r11 != (-1)) goto L50;     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x013c, code lost:            r0 = r6.typeArguments;     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0151, code lost:            r14 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x015c, code lost:            if (r10 >= r14.size()) goto L61;     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x015f, code lost:            r14.get(r10).addTypeAnnotation(r7.subList(r9 + 1, r7.size()), r8);     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0182, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:50:?, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0143, code lost:            r0 = r6.suffixTypeArguments.get(r11);     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0116, code lost:            r11 = r11 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0106, code lost:            r0 = false;     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0083, code lost:            throw new java.lang.IllegalArgumentException("Ran out of nested types while trying to add type annotation");     */
    @Override // io.github.classgraph.TypeSignature, io.github.classgraph.HierarchicalTypeSignature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addTypeAnnotation(java.util.List<io.github.classgraph.Classfile.TypePathNode> r7, io.github.classgraph.AnnotationInfo r8) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.classgraph.ClassRefTypeSignature.addTypeAnnotation(java.util.List, io.github.classgraph.AnnotationInfo):void");
    }

    @Override // io.github.classgraph.ScanResultObject
    public final Class<?> loadClass(boolean z) {
        return super.loadClass(z);
    }

    @Override // io.github.classgraph.ScanResultObject
    public final Class<?> loadClass() {
        return super.loadClass();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public final String getClassName() {
        return getFullyQualifiedClassName();
    }

    @Override // io.github.classgraph.ScanResultObject
    public final ClassInfo getClassInfo() {
        return super.getClassInfo();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.HierarchicalTypeSignature, io.github.classgraph.ScanResultObject
    public final void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        Iterator<TypeArgument> it = this.typeArguments.iterator();
        while (it.hasNext()) {
            it.next().setScanResult(scanResult);
        }
        Iterator<List<TypeArgument>> it2 = this.suffixTypeArguments.iterator();
        while (it2.hasNext()) {
            Iterator<TypeArgument> it3 = it2.next().iterator();
            while (it3.hasNext()) {
                it3.next().setScanResult(scanResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.TypeSignature
    public final void findReferencedClassNames(Set<String> set) {
        set.add(getFullyQualifiedClassName());
        Iterator<TypeArgument> it = this.typeArguments.iterator();
        while (it.hasNext()) {
            it.next().findReferencedClassNames(set);
        }
        Iterator<List<TypeArgument>> it2 = this.suffixTypeArguments.iterator();
        while (it2.hasNext()) {
            Iterator<TypeArgument> it3 = it2.next().iterator();
            while (it3.hasNext()) {
                it3.next().findReferencedClassNames(set);
            }
        }
    }

    public final int hashCode() {
        return this.className.hashCode() + (7 * this.typeArguments.hashCode()) + (15 * this.suffixTypeArguments.hashCode()) + (31 * (this.typeAnnotationInfo == null ? 0 : this.typeAnnotationInfo.hashCode())) + (64 * (this.suffixTypeAnnotations == null ? 0 : this.suffixTypeAnnotations.hashCode()));
    }

    private static boolean suffixesMatch(ClassRefTypeSignature classRefTypeSignature, ClassRefTypeSignature classRefTypeSignature2) {
        return classRefTypeSignature.suffixes.equals(classRefTypeSignature2.suffixes) && classRefTypeSignature.suffixTypeArguments.equals(classRefTypeSignature2.suffixTypeArguments) && Objects.equals(classRefTypeSignature.suffixTypeAnnotations, classRefTypeSignature2.suffixTypeAnnotations);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassRefTypeSignature)) {
            return false;
        }
        ClassRefTypeSignature classRefTypeSignature = (ClassRefTypeSignature) obj;
        return classRefTypeSignature.className.equals(this.className) && classRefTypeSignature.typeArguments.equals(this.typeArguments) && Objects.equals(this.typeAnnotationInfo, classRefTypeSignature.typeAnnotationInfo) && suffixesMatch(classRefTypeSignature, this);
    }

    @Override // io.github.classgraph.TypeSignature
    public final boolean equalsIgnoringTypeParams(TypeSignature typeSignature) {
        if (typeSignature instanceof TypeVariableSignature) {
            return typeSignature.equalsIgnoringTypeParams(this);
        }
        if (!(typeSignature instanceof ClassRefTypeSignature)) {
            return false;
        }
        ClassRefTypeSignature classRefTypeSignature = (ClassRefTypeSignature) typeSignature;
        return classRefTypeSignature.className.equals(this.className) && Objects.equals(this.typeAnnotationInfo, classRefTypeSignature.typeAnnotationInfo) && suffixesMatch(classRefTypeSignature, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public final void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb) {
        if (!z || this.suffixes.isEmpty()) {
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
            sb.append(z ? ClassInfo.getSimpleName(this.className) : this.className);
            if (!this.typeArguments.isEmpty()) {
                sb.append('<');
                for (int i = 0; i < this.typeArguments.size(); i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    this.typeArguments.get(i).toString(z, sb);
                }
                sb.append('>');
            }
        }
        if (!this.suffixes.isEmpty()) {
            for (int size = z ? this.suffixes.size() - 1 : 0; size < this.suffixes.size(); size++) {
                if (!z) {
                    sb.append('$');
                }
                AnnotationInfoList annotationInfoList2 = this.suffixTypeAnnotations == null ? null : this.suffixTypeAnnotations.get(size);
                AnnotationInfoList annotationInfoList3 = annotationInfoList2;
                if (annotationInfoList2 != null && !annotationInfoList3.isEmpty()) {
                    Iterator it2 = annotationInfoList3.iterator();
                    while (it2.hasNext()) {
                        ((AnnotationInfo) it2.next()).toString(z, sb);
                        sb.append(' ');
                    }
                }
                sb.append(this.suffixes.get(size));
                List<TypeArgument> list = this.suffixTypeArguments.get(size);
                if (!list.isEmpty()) {
                    sb.append('<');
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (i2 > 0) {
                            sb.append(", ");
                        }
                        list.get(i2).toString(z, sb);
                    }
                    sb.append('>');
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v39, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.util.List] */
    public static ClassRefTypeSignature parse(Parser parser, String str) {
        ArrayList arrayList;
        ArrayList arrayList2;
        if (parser.peek() == 'L') {
            parser.next();
            int position = parser.getPosition();
            if (!TypeUtils.getIdentifierToken(parser, true)) {
                throw new ParseException(parser, "Could not parse identifier token");
            }
            String currToken = parser.currToken();
            List<TypeArgument> parseList = TypeArgument.parseList(parser, str);
            boolean z = false;
            if (parser.peek() == '.' || parser.peek() == '$') {
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
                while (true) {
                    if (parser.peek() != '.' && parser.peek() != '$') {
                        break;
                    }
                    parser.advance(1);
                    if (!TypeUtils.getIdentifierToken(parser, true)) {
                        arrayList.add("");
                        arrayList2.add(Collections.emptyList());
                        z = true;
                    } else {
                        arrayList.add(parser.currToken());
                        arrayList2.add(TypeArgument.parseList(parser, str));
                    }
                }
                if (z) {
                    currToken = parser.getSubstring(position, parser.getPosition()).replace('/', '.');
                }
                parser.expect(';');
                return new ClassRefTypeSignature(currToken, parseList, arrayList, arrayList2);
            }
            arrayList = Collections.emptyList();
            arrayList2 = Collections.emptyList();
            parser.expect(';');
            return new ClassRefTypeSignature(currToken, parseList, arrayList, arrayList2);
        }
        return null;
    }
}
