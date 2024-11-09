package net.bytebuddy.jar.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Type.class */
public final class Type {
    public static final int VOID = 0;
    public static final int BOOLEAN = 1;
    public static final int CHAR = 2;
    public static final int BYTE = 3;
    public static final int SHORT = 4;
    public static final int INT = 5;
    public static final int FLOAT = 6;
    public static final int LONG = 7;
    public static final int DOUBLE = 8;
    public static final int ARRAY = 9;
    public static final int OBJECT = 10;
    public static final int METHOD = 11;
    private static final int INTERNAL = 12;
    private static final String PRIMITIVE_DESCRIPTORS = "VZCBSIFJD";
    public static final Type VOID_TYPE = new Type(0, PRIMITIVE_DESCRIPTORS, 0, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, PRIMITIVE_DESCRIPTORS, 1, 2);
    public static final Type CHAR_TYPE = new Type(2, PRIMITIVE_DESCRIPTORS, 2, 3);
    public static final Type BYTE_TYPE = new Type(3, PRIMITIVE_DESCRIPTORS, 3, 4);
    public static final Type SHORT_TYPE = new Type(4, PRIMITIVE_DESCRIPTORS, 4, 5);
    public static final Type INT_TYPE = new Type(5, PRIMITIVE_DESCRIPTORS, 5, 6);
    public static final Type FLOAT_TYPE = new Type(6, PRIMITIVE_DESCRIPTORS, 6, 7);
    public static final Type LONG_TYPE = new Type(7, PRIMITIVE_DESCRIPTORS, 7, 8);
    public static final Type DOUBLE_TYPE = new Type(8, PRIMITIVE_DESCRIPTORS, 8, 9);
    private final int sort;
    private final String valueBuffer;
    private final int valueBegin;
    private final int valueEnd;

    private Type(int i, String str, int i2, int i3) {
        this.sort = i;
        this.valueBuffer = str;
        this.valueBegin = i2;
        this.valueEnd = i3;
    }

    public static Type getType(String str) {
        return getTypeInternal(str, 0, str.length());
    }

    public static Type getType(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return INT_TYPE;
            }
            if (cls == Void.TYPE) {
                return VOID_TYPE;
            }
            if (cls == Boolean.TYPE) {
                return BOOLEAN_TYPE;
            }
            if (cls == Byte.TYPE) {
                return BYTE_TYPE;
            }
            if (cls == Character.TYPE) {
                return CHAR_TYPE;
            }
            if (cls == Short.TYPE) {
                return SHORT_TYPE;
            }
            if (cls == Double.TYPE) {
                return DOUBLE_TYPE;
            }
            if (cls == Float.TYPE) {
                return FLOAT_TYPE;
            }
            if (cls == Long.TYPE) {
                return LONG_TYPE;
            }
            throw new AssertionError();
        }
        return getType(getDescriptor(cls));
    }

    public static Type getType(Constructor<?> constructor) {
        return getType(getConstructorDescriptor(constructor));
    }

    public static Type getType(Method method) {
        return getType(getMethodDescriptor(method));
    }

    public final Type getElementType() {
        return getTypeInternal(this.valueBuffer, this.valueBegin + getDimensions(), this.valueEnd);
    }

    public static Type getObjectType(String str) {
        return new Type(str.charAt(0) == '[' ? 9 : 12, str, 0, str.length());
    }

    public static Type getMethodType(String str) {
        return new Type(11, str, 0, str.length());
    }

    public static Type getMethodType(Type type, Type... typeArr) {
        return getType(getMethodDescriptor(type, typeArr));
    }

    public final Type[] getArgumentTypes() {
        return getArgumentTypes(getDescriptor());
    }

    public static Type[] getArgumentTypes(String str) {
        int i = 0;
        int i2 = 1;
        while (str.charAt(i2) != ')') {
            while (str.charAt(i2) == '[') {
                i2++;
            }
            int i3 = i2;
            i2++;
            if (str.charAt(i3) == 'L') {
                i2 = Math.max(i2, str.indexOf(59, i2) + 1);
            }
            i++;
        }
        Type[] typeArr = new Type[i];
        int i4 = 1;
        int i5 = 0;
        while (str.charAt(i4) != ')') {
            int i6 = i4;
            while (str.charAt(i4) == '[') {
                i4++;
            }
            int i7 = i4;
            i4++;
            if (str.charAt(i7) == 'L') {
                i4 = Math.max(i4, str.indexOf(59, i4) + 1);
            }
            int i8 = i5;
            i5++;
            typeArr[i8] = getTypeInternal(str, i6, i4);
        }
        return typeArr;
    }

    public static Type[] getArgumentTypes(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Type[] typeArr = new Type[parameterTypes.length];
        for (int length = parameterTypes.length - 1; length >= 0; length--) {
            typeArr[length] = getType(parameterTypes[length]);
        }
        return typeArr;
    }

    public final Type getReturnType() {
        return getReturnType(getDescriptor());
    }

    public static Type getReturnType(String str) {
        return getTypeInternal(str, a(str), str.length());
    }

    public static Type getReturnType(Method method) {
        return getType(method.getReturnType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(String str) {
        int i = 1;
        while (str.charAt(i) != ')') {
            while (str.charAt(i) == '[') {
                i++;
            }
            int i2 = i;
            i++;
            if (str.charAt(i2) == 'L') {
                i = Math.max(i, str.indexOf(59, i) + 1);
            }
        }
        return i + 1;
    }

    private static Type getTypeInternal(String str, int i, int i2) {
        switch (str.charAt(i)) {
            case '(':
                return new Type(11, str, i, i2);
            case 'B':
                return BYTE_TYPE;
            case 'C':
                return CHAR_TYPE;
            case 'D':
                return DOUBLE_TYPE;
            case 'F':
                return FLOAT_TYPE;
            case 'I':
                return INT_TYPE;
            case 'J':
                return LONG_TYPE;
            case 'L':
                return new Type(10, str, i + 1, i2 - 1);
            case 'S':
                return SHORT_TYPE;
            case 'V':
                return VOID_TYPE;
            case 'Z':
                return BOOLEAN_TYPE;
            case '[':
                return new Type(9, str, i, i2);
            default:
                throw new IllegalArgumentException("Invalid descriptor: " + str);
        }
    }

    public final String getClassName() {
        switch (this.sort) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return "float";
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(getElementType().getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    sb.append("[]");
                }
                return sb.toString();
            case 10:
            case 12:
                return this.valueBuffer.substring(this.valueBegin, this.valueEnd).replace('/', '.');
            case 11:
            default:
                throw new AssertionError();
        }
    }

    public final String getInternalName() {
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }

    public static String getInternalName(Class<?> cls) {
        return cls.getName().replace('.', '/');
    }

    public final String getDescriptor() {
        if (this.sort == 10) {
            return this.valueBuffer.substring(this.valueBegin - 1, this.valueEnd + 1);
        }
        if (this.sort == 12) {
            return "L" + this.valueBuffer.substring(this.valueBegin, this.valueEnd) + ';';
        }
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }

    public static String getDescriptor(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        appendDescriptor(cls, sb);
        return sb.toString();
    }

    public static String getConstructorDescriptor(Constructor<?> constructor) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Class<?> cls : constructor.getParameterTypes()) {
            appendDescriptor(cls, sb);
        }
        return sb.append(")V").toString();
    }

    public static String getMethodDescriptor(Type type, Type... typeArr) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Type type2 : typeArr) {
            type2.appendDescriptor(sb);
        }
        sb.append(')');
        type.appendDescriptor(sb);
        return sb.toString();
    }

    public static String getMethodDescriptor(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Class<?> cls : method.getParameterTypes()) {
            appendDescriptor(cls, sb);
        }
        sb.append(')');
        appendDescriptor(method.getReturnType(), sb);
        return sb.toString();
    }

    private void appendDescriptor(StringBuilder sb) {
        if (this.sort == 10) {
            sb.append((CharSequence) this.valueBuffer, this.valueBegin - 1, this.valueEnd + 1);
        } else if (this.sort == 12) {
            sb.append('L').append((CharSequence) this.valueBuffer, this.valueBegin, this.valueEnd).append(';');
        } else {
            sb.append((CharSequence) this.valueBuffer, this.valueBegin, this.valueEnd);
        }
    }

    private static void appendDescriptor(Class<?> cls, StringBuilder sb) {
        Class<?> cls2;
        char c;
        Class<?> cls3 = cls;
        while (true) {
            cls2 = cls3;
            if (!cls2.isArray()) {
                break;
            }
            sb.append('[');
            cls3 = cls2.getComponentType();
        }
        if (cls2.isPrimitive()) {
            if (cls2 == Integer.TYPE) {
                c = 'I';
            } else if (cls2 == Void.TYPE) {
                c = 'V';
            } else if (cls2 == Boolean.TYPE) {
                c = 'Z';
            } else if (cls2 == Byte.TYPE) {
                c = 'B';
            } else if (cls2 == Character.TYPE) {
                c = 'C';
            } else if (cls2 == Short.TYPE) {
                c = 'S';
            } else if (cls2 == Double.TYPE) {
                c = 'D';
            } else if (cls2 == Float.TYPE) {
                c = 'F';
            } else if (cls2 == Long.TYPE) {
                c = 'J';
            } else {
                throw new AssertionError();
            }
            sb.append(c);
            return;
        }
        sb.append('L').append(getInternalName(cls2)).append(';');
    }

    public final int getSort() {
        if (this.sort == 12) {
            return 10;
        }
        return this.sort;
    }

    public final int getDimensions() {
        int i = 1;
        while (this.valueBuffer.charAt(this.valueBegin + i) == '[') {
            i++;
        }
        return i;
    }

    public final int getSize() {
        switch (this.sort) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 12:
                return 1;
            case 7:
            case 8:
                return 2;
            case 11:
            default:
                throw new AssertionError();
        }
    }

    public final int getArgumentsAndReturnSizes() {
        return getArgumentsAndReturnSizes(getDescriptor());
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i = 1;
        int i2 = 1;
        char charAt = str.charAt(1);
        while (true) {
            char c = charAt;
            if (c == ')') {
                break;
            }
            if (c == 'J' || c == 'D') {
                i2++;
                i += 2;
            } else {
                while (str.charAt(i2) == '[') {
                    i2++;
                }
                int i3 = i2;
                i2++;
                if (str.charAt(i3) == 'L') {
                    i2 = Math.max(i2, str.indexOf(59, i2) + 1);
                }
                i++;
            }
            charAt = str.charAt(i2);
        }
        char charAt2 = str.charAt(i2 + 1);
        if (charAt2 == 'V') {
            return i << 2;
        }
        return (i << 2) | ((charAt2 == 'J' || charAt2 == 'D') ? 2 : 1);
    }

    public final int getOpcode(int i) {
        if (i == 46 || i == 79) {
            switch (this.sort) {
                case 0:
                case 11:
                    throw new UnsupportedOperationException();
                case 1:
                case 3:
                    return i + 5;
                case 2:
                    return i + 6;
                case 4:
                    return i + 7;
                case 5:
                    return i;
                case 6:
                    return i + 2;
                case 7:
                    return i + 1;
                case 8:
                    return i + 3;
                case 9:
                case 10:
                case 12:
                    return i + 4;
                default:
                    throw new AssertionError();
            }
        }
        switch (this.sort) {
            case 0:
                if (i != 172) {
                    throw new UnsupportedOperationException();
                }
                return 177;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return i;
            case 6:
                return i + 2;
            case 7:
                return i + 1;
            case 8:
                return i + 3;
            case 9:
            case 10:
            case 12:
                if (i != 21 && i != 54 && i != 172) {
                    throw new UnsupportedOperationException();
                }
                return i + 4;
            case 11:
                throw new UnsupportedOperationException();
            default:
                throw new AssertionError();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return false;
        }
        Type type = (Type) obj;
        if ((this.sort == 12 ? 10 : this.sort) != (type.sort == 12 ? 10 : type.sort)) {
            return false;
        }
        int i = this.valueBegin;
        int i2 = this.valueEnd;
        int i3 = type.valueBegin;
        if (i2 - i != type.valueEnd - i3) {
            return false;
        }
        int i4 = i;
        int i5 = i3;
        while (i4 < i2) {
            if (this.valueBuffer.charAt(i4) == type.valueBuffer.charAt(i5)) {
                i4++;
                i5++;
            } else {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 13 * (this.sort == 12 ? 10 : this.sort);
        if (this.sort >= 9) {
            int i2 = this.valueEnd;
            for (int i3 = this.valueBegin; i3 < i2; i3++) {
                i = 17 * (i + this.valueBuffer.charAt(i3));
            }
        }
        return i;
    }

    public final String toString() {
        return getDescriptor();
    }
}
