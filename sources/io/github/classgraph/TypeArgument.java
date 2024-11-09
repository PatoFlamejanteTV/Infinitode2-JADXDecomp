package io.github.classgraph;

import io.github.classgraph.Classfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import net.bytebuddy.description.type.TypeDescription;
import nonapi.io.github.classgraph.types.ParseException;
import nonapi.io.github.classgraph.types.Parser;

/* loaded from: infinitode-2.jar:io/github/classgraph/TypeArgument.class */
public final class TypeArgument extends HierarchicalTypeSignature {
    private final Wildcard wildcard;
    private final ReferenceTypeSignature typeSignature;

    /* loaded from: infinitode-2.jar:io/github/classgraph/TypeArgument$Wildcard.class */
    public enum Wildcard {
        NONE,
        ANY,
        EXTENDS,
        SUPER
    }

    private TypeArgument(Wildcard wildcard, ReferenceTypeSignature referenceTypeSignature) {
        this.wildcard = wildcard;
        this.typeSignature = referenceTypeSignature;
    }

    public final Wildcard getWildcard() {
        return this.wildcard;
    }

    public final ReferenceTypeSignature getTypeSignature() {
        return this.typeSignature;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public final void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo) {
        if (list.size() == 0 && this.wildcard != Wildcard.NONE) {
            addTypeAnnotation(annotationInfo);
            return;
        }
        if (list.size() > 0 && list.get(0).typePathKind == 2) {
            if (this.typeSignature != null) {
                this.typeSignature.addTypeAnnotation(list.subList(1, list.size()), annotationInfo);
            }
        } else if (this.typeSignature != null) {
            this.typeSignature.addTypeAnnotation(list, annotationInfo);
        }
    }

    private static TypeArgument parse(Parser parser, String str) {
        char peek = parser.peek();
        if (peek == '*') {
            parser.expect('*');
            return new TypeArgument(Wildcard.ANY, null);
        }
        if (peek == '+') {
            parser.expect('+');
            ReferenceTypeSignature parseReferenceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, str);
            if (parseReferenceTypeSignature != null) {
                return new TypeArgument(Wildcard.EXTENDS, parseReferenceTypeSignature);
            }
            throw new ParseException(parser, "Missing '+' type bound");
        }
        if (peek == '-') {
            parser.expect('-');
            ReferenceTypeSignature parseReferenceTypeSignature2 = ReferenceTypeSignature.parseReferenceTypeSignature(parser, str);
            if (parseReferenceTypeSignature2 != null) {
                return new TypeArgument(Wildcard.SUPER, parseReferenceTypeSignature2);
            }
            throw new ParseException(parser, "Missing '-' type bound");
        }
        ReferenceTypeSignature parseReferenceTypeSignature3 = ReferenceTypeSignature.parseReferenceTypeSignature(parser, str);
        if (parseReferenceTypeSignature3 != null) {
            return new TypeArgument(Wildcard.NONE, parseReferenceTypeSignature3);
        }
        throw new ParseException(parser, "Missing type bound");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<TypeArgument> parseList(Parser parser, String str) {
        if (parser.peek() == '<') {
            parser.expect('<');
            ArrayList arrayList = new ArrayList(2);
            while (parser.peek() != '>') {
                if (!parser.hasMore()) {
                    throw new ParseException(parser, "Missing '>'");
                }
                arrayList.add(parse(parser, str));
            }
            parser.expect('>');
            return arrayList;
        }
        return Collections.emptyList();
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
        if (this.typeSignature != null) {
            this.typeSignature.setScanResult(scanResult);
        }
    }

    public final void findReferencedClassNames(Set<String> set) {
        if (this.typeSignature != null) {
            this.typeSignature.findReferencedClassNames(set);
        }
    }

    public final int hashCode() {
        return (this.typeSignature != null ? this.typeSignature.hashCode() : 0) + (7 * this.wildcard.hashCode());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeArgument)) {
            return false;
        }
        TypeArgument typeArgument = (TypeArgument) obj;
        return Objects.equals(this.typeAnnotationInfo, typeArgument.typeAnnotationInfo) && Objects.equals(this.typeSignature, typeArgument.typeSignature) && typeArgument.wildcard.equals(this.wildcard);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public final void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb) {
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
        switch (this.wildcard) {
            case ANY:
                sb.append('?');
                return;
            case EXTENDS:
                String referenceTypeSignature = this.typeSignature.toString(z);
                sb.append(referenceTypeSignature.equals("java.lang.Object") ? TypeDescription.Generic.OfWildcardType.SYMBOL : "? extends " + referenceTypeSignature);
                return;
            case SUPER:
                sb.append("? super ");
                break;
        }
        this.typeSignature.toString(z, sb);
    }
}
