package io.github.classgraph;

import nonapi.io.github.classgraph.types.Parser;

/* loaded from: infinitode-2.jar:io/github/classgraph/ReferenceTypeSignature.class */
public abstract class ReferenceTypeSignature extends TypeSignature {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReferenceTypeSignature parseReferenceTypeSignature(Parser parser, String str) {
        ClassRefTypeSignature parse = ClassRefTypeSignature.parse(parser, str);
        if (parse != null) {
            return parse;
        }
        TypeVariableSignature parse2 = TypeVariableSignature.parse(parser, str);
        if (parse2 != null) {
            return parse2;
        }
        ArrayTypeSignature parse3 = ArrayTypeSignature.parse(parser, str);
        if (parse3 != null) {
            return parse3;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReferenceTypeSignature parseClassBound(Parser parser, String str) {
        parser.expect(':');
        return parseReferenceTypeSignature(parser, str);
    }
}
