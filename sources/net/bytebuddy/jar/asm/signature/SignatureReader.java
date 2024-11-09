package net.bytebuddy.jar.asm.signature;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/signature/SignatureReader.class */
public class SignatureReader {
    private final String signatureValue;

    public SignatureReader(String str) {
        this.signatureValue = str;
    }

    public void accept(SignatureVisitor signatureVisitor) {
        int i;
        char charAt;
        String str = this.signatureValue;
        int length = str.length();
        if (str.charAt(0) == '<') {
            i = 2;
            do {
                int indexOf = str.indexOf(58, i);
                signatureVisitor.visitFormalTypeParameter(str.substring(i - 1, indexOf));
                int i2 = indexOf + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 == 'L' || charAt2 == '[' || charAt2 == 'T') {
                    i2 = parseType(str, i2, signatureVisitor.visitClassBound());
                }
                while (true) {
                    int i3 = i2;
                    i = i2 + 1;
                    charAt = str.charAt(i3);
                    if (charAt != ':') {
                        break;
                    } else {
                        i2 = parseType(str, i, signatureVisitor.visitInterfaceBound());
                    }
                }
            } while (charAt != '>');
        } else {
            i = 0;
        }
        if (str.charAt(i) == '(') {
            int i4 = i + 1;
            while (str.charAt(i4) != ')') {
                i4 = parseType(str, i4, signatureVisitor.visitParameterType());
            }
            int parseType = parseType(str, i4 + 1, signatureVisitor.visitReturnType());
            while (true) {
                int i5 = parseType;
                if (i5 < length) {
                    parseType = parseType(str, i5 + 1, signatureVisitor.visitExceptionType());
                } else {
                    return;
                }
            }
        } else {
            int parseType2 = parseType(str, i, signatureVisitor.visitSuperclass());
            while (true) {
                int i6 = parseType2;
                if (i6 < length) {
                    parseType2 = parseType(str, i6, signatureVisitor.visitInterface());
                } else {
                    return;
                }
            }
        }
    }

    public void acceptType(SignatureVisitor signatureVisitor) {
        parseType(this.signatureValue, 0, signatureVisitor);
    }

    private static int parseType(String str, int i, SignatureVisitor signatureVisitor) {
        int i2 = i + 1;
        char charAt = str.charAt(i);
        switch (charAt) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'V':
            case 'Z':
                signatureVisitor.visitBaseType(charAt);
                return i2;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                throw new IllegalArgumentException();
            case 'L':
                int i3 = i2;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    int i4 = i2;
                    i2++;
                    char charAt2 = str.charAt(i4);
                    if (charAt2 == '.' || charAt2 == ';') {
                        if (!z) {
                            String substring = str.substring(i3, i2 - 1);
                            if (z2) {
                                signatureVisitor.visitInnerClassType(substring);
                            } else {
                                signatureVisitor.visitClassType(substring);
                            }
                        }
                        if (charAt2 == ';') {
                            signatureVisitor.visitEnd();
                            return i2;
                        }
                        i3 = i2;
                        z = false;
                        z2 = true;
                    } else if (charAt2 == '<') {
                        String substring2 = str.substring(i3, i2 - 1);
                        if (z2) {
                            signatureVisitor.visitInnerClassType(substring2);
                        } else {
                            signatureVisitor.visitClassType(substring2);
                        }
                        z = true;
                        while (true) {
                            char charAt3 = str.charAt(i2);
                            if (charAt3 != '>') {
                                switch (charAt3) {
                                    case '*':
                                        i2++;
                                        signatureVisitor.visitTypeArgument();
                                        break;
                                    case '+':
                                    case '-':
                                        i2 = parseType(str, i2 + 1, signatureVisitor.visitTypeArgument(charAt3));
                                        break;
                                    case ',':
                                    default:
                                        i2 = parseType(str, i2, signatureVisitor.visitTypeArgument('='));
                                        break;
                                }
                            }
                        }
                    }
                }
                break;
            case 'T':
                int indexOf = str.indexOf(59, i2);
                signatureVisitor.visitTypeVariable(str.substring(i2, indexOf));
                return indexOf + 1;
            case '[':
                return parseType(str, i2, signatureVisitor.visitArrayType());
        }
    }
}
