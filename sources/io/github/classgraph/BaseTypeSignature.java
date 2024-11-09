package io.github.classgraph;

import io.github.classgraph.Classfile;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import nonapi.io.github.classgraph.types.Parser;

/* loaded from: infinitode-2.jar:io/github/classgraph/BaseTypeSignature.class */
public class BaseTypeSignature extends TypeSignature {
    private final char typeSignatureChar;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseTypeSignature(char c) {
        switch (c) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'V':
            case 'Z':
                this.typeSignatureChar = c;
                return;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                throw new IllegalArgumentException("Illegal " + BaseTypeSignature.class.getSimpleName() + " type: '" + c + "'");
        }
    }

    static String getTypeStr(char c) {
        switch (c) {
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return "double";
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                return null;
            case 'F':
                return "float";
            case 'I':
                return "int";
            case 'J':
                return "long";
            case 'S':
                return "short";
            case 'V':
                return "void";
            case 'Z':
                return "boolean";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char getTypeChar(String str) {
        boolean z = -1;
        switch (str.hashCode()) {
            case -1325958191:
                if (str.equals("double")) {
                    z = 2;
                    break;
                }
                break;
            case 104431:
                if (str.equals("int")) {
                    z = 4;
                    break;
                }
                break;
            case 3039496:
                if (str.equals("byte")) {
                    z = false;
                    break;
                }
                break;
            case 3052374:
                if (str.equals("char")) {
                    z = true;
                    break;
                }
                break;
            case 3327612:
                if (str.equals("long")) {
                    z = 5;
                    break;
                }
                break;
            case 3625364:
                if (str.equals("void")) {
                    z = 8;
                    break;
                }
                break;
            case 64711720:
                if (str.equals("boolean")) {
                    z = 7;
                    break;
                }
                break;
            case 97526364:
                if (str.equals("float")) {
                    z = 3;
                    break;
                }
                break;
            case 109413500:
                if (str.equals("short")) {
                    z = 6;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return 'B';
            case true:
                return 'C';
            case true:
                return 'D';
            case true:
                return 'F';
            case true:
                return 'I';
            case true:
                return 'J';
            case true:
                return 'S';
            case true:
                return 'Z';
            case true:
                return 'V';
            default:
                return (char) 0;
        }
    }

    static Class<?> getType(char c) {
        switch (c) {
            case 'B':
                return Byte.TYPE;
            case 'C':
                return Character.TYPE;
            case 'D':
                return Double.TYPE;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                return null;
            case 'F':
                return Float.TYPE;
            case 'I':
                return Integer.TYPE;
            case 'J':
                return Long.TYPE;
            case 'S':
                return Short.TYPE;
            case 'V':
                return Void.TYPE;
            case 'Z':
                return Boolean.TYPE;
        }
    }

    public char getTypeSignatureChar() {
        return this.typeSignatureChar;
    }

    public String getTypeStr() {
        return getTypeStr(this.typeSignatureChar);
    }

    public Class<?> getType() {
        return getType(this.typeSignatureChar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.TypeSignature, io.github.classgraph.HierarchicalTypeSignature
    public void addTypeAnnotation(List<Classfile.TypePathNode> list, AnnotationInfo annotationInfo) {
        addTypeAnnotation(annotationInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public Class<?> loadClass() {
        return getType();
    }

    @Override // io.github.classgraph.ScanResultObject
    <T> Class<T> loadClass(Class<T> cls) {
        Class<T> cls2 = (Class<T>) getType();
        if (!cls.isAssignableFrom(cls2)) {
            throw new IllegalArgumentException("Primitive class " + getTypeStr() + " cannot be cast to " + cls.getName());
        }
        return cls2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BaseTypeSignature parse(Parser parser) {
        switch (parser.peek()) {
            case 'B':
                parser.next();
                return new BaseTypeSignature('B');
            case 'C':
                parser.next();
                return new BaseTypeSignature('C');
            case 'D':
                parser.next();
                return new BaseTypeSignature('D');
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                return null;
            case 'F':
                parser.next();
                return new BaseTypeSignature('F');
            case 'I':
                parser.next();
                return new BaseTypeSignature('I');
            case 'J':
                parser.next();
                return new BaseTypeSignature('J');
            case 'S':
                parser.next();
                return new BaseTypeSignature('S');
            case 'V':
                parser.next();
                return new BaseTypeSignature('V');
            case 'Z':
                parser.next();
                return new BaseTypeSignature('Z');
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        return getTypeStr();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public ClassInfo getClassInfo() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.TypeSignature
    public void findReferencedClassNames(Set<String> set) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.HierarchicalTypeSignature, io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
    }

    public int hashCode() {
        return this.typeSignatureChar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseTypeSignature)) {
            return false;
        }
        BaseTypeSignature baseTypeSignature = (BaseTypeSignature) obj;
        return Objects.equals(this.typeAnnotationInfo, baseTypeSignature.typeAnnotationInfo) && baseTypeSignature.typeSignatureChar == this.typeSignatureChar;
    }

    @Override // io.github.classgraph.TypeSignature
    public boolean equalsIgnoringTypeParams(TypeSignature typeSignature) {
        return (typeSignature instanceof BaseTypeSignature) && this.typeSignatureChar == ((BaseTypeSignature) typeSignature).typeSignatureChar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.HierarchicalTypeSignature
    public void toStringInternal(boolean z, AnnotationInfoList annotationInfoList, StringBuilder sb) {
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
        sb.append(getTypeStr());
    }
}
