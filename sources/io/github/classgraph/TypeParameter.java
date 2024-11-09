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

/* loaded from: infinitode-2.jar:io/github/classgraph/TypeParameter.class */
public final class TypeParameter extends HierarchicalTypeSignature {
    final String name;
    final ReferenceTypeSignature classBound;
    final List<ReferenceTypeSignature> interfaceBounds;

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeParameter(String str, ReferenceTypeSignature referenceTypeSignature, List<ReferenceTypeSignature> list) {
        this.name = str;
        this.classBound = referenceTypeSignature;
        this.interfaceBounds = list;
    }

    public final String getName() {
        return this.name;
    }

    public final ReferenceTypeSignature getClassBound() {
        return this.classBound;
    }

    public final List<ReferenceTypeSignature> getInterfaceBounds() {
        return this.interfaceBounds;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public final void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo) {
        if (list.isEmpty()) {
            addTypeAnnotation(annotationInfo);
            return;
        }
        throw new IllegalArgumentException("Type parameter should have empty typePath");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<TypeParameter> parseList(Parser parser, String str) {
        List emptyList;
        if (parser.peek() != '<') {
            return Collections.emptyList();
        }
        parser.expect('<');
        ArrayList arrayList = new ArrayList(1);
        while (parser.peek() != '>') {
            if (!parser.hasMore()) {
                throw new ParseException(parser, "Missing '>'");
            }
            if (!TypeUtils.getIdentifierToken(parser, false)) {
                throw new ParseException(parser, "Could not parse identifier token");
            }
            String currToken = parser.currToken();
            ReferenceTypeSignature parseClassBound = ReferenceTypeSignature.parseClassBound(parser, str);
            if (parser.peek() == ':') {
                emptyList = new ArrayList();
                while (parser.peek() == ':') {
                    parser.expect(':');
                    ReferenceTypeSignature parseReferenceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, str);
                    if (parseReferenceTypeSignature == null) {
                        throw new ParseException(parser, "Missing interface type signature");
                    }
                    emptyList.add(parseReferenceTypeSignature);
                }
            } else {
                emptyList = Collections.emptyList();
            }
            arrayList.add(new TypeParameter(currToken, parseClassBound, emptyList));
        }
        parser.expect('>');
        return arrayList;
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
        if (this.classBound != null) {
            this.classBound.setScanResult(scanResult);
        }
        if (this.interfaceBounds != null) {
            Iterator<ReferenceTypeSignature> it = this.interfaceBounds.iterator();
            while (it.hasNext()) {
                it.next().setScanResult(scanResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void findReferencedClassNames(Set<String> set) {
        if (this.classBound != null) {
            this.classBound.findReferencedClassNames(set);
        }
        Iterator<ReferenceTypeSignature> it = this.interfaceBounds.iterator();
        while (it.hasNext()) {
            it.next().findReferencedClassNames(set);
        }
    }

    public final int hashCode() {
        return this.name.hashCode() + (this.classBound == null ? 0 : this.classBound.hashCode() * 7) + (this.interfaceBounds.hashCode() * 15);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeParameter)) {
            return false;
        }
        TypeParameter typeParameter = (TypeParameter) obj;
        if (typeParameter.name.equals(this.name) && Objects.equals(typeParameter.typeAnnotationInfo, this.typeAnnotationInfo)) {
            return ((typeParameter.classBound == null && this.classBound == null) || (typeParameter.classBound != null && typeParameter.classBound.equals(this.classBound))) && typeParameter.interfaceBounds.equals(this.interfaceBounds);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x008e, code lost:            if (((io.github.classgraph.ClassRefTypeSignature) r4.classBound).className.equals("java.lang.Object") == false) goto L26;     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d0  */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void toStringInternal(boolean r5, io.github.classgraph.AnnotationInfoList r6, java.lang.StringBuilder r7) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.classgraph.TypeParameter.toStringInternal(boolean, io.github.classgraph.AnnotationInfoList, java.lang.StringBuilder):void");
    }
}
