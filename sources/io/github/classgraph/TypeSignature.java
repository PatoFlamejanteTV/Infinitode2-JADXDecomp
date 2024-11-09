package io.github.classgraph;

import io.github.classgraph.Classfile;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/TypeSignature.class */
public abstract class TypeSignature extends HierarchicalTypeSignature {
    public abstract boolean equalsIgnoringTypeParams(TypeSignature typeSignature);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public abstract void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo);

    /* JADX INFO: Access modifiers changed from: protected */
    public void findReferencedClassNames(Set<String> set) {
        String className = getClassName();
        if (className != null && !className.isEmpty()) {
            set.add(getClassName());
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

    @Override // io.github.classgraph.HierarchicalTypeSignature
    public AnnotationInfoList getTypeAnnotationInfo() {
        return this.typeAnnotationInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TypeSignature parse(Parser parser, String str) {
        ReferenceTypeSignature parseReferenceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, str);
        if (parseReferenceTypeSignature != null) {
            return parseReferenceTypeSignature;
        }
        BaseTypeSignature parse = BaseTypeSignature.parse(parser);
        if (parse != null) {
            return parse;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TypeSignature parse(String str, String str2) {
        Parser parser = new Parser(str);
        TypeSignature parse = parse(parser, str2);
        if (parse == null) {
            throw new ParseException(parser, "Could not parse type signature");
        }
        if (parser.hasMore()) {
            throw new ParseException(parser, "Extra characters at end of type descriptor");
        }
        return parse;
    }
}
