package net.bytebuddy.jar.asm;

import com.prineside.luaj.Lua;
import net.bytebuddy.implementation.auxiliary.TypeProxy;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Frame.class */
class Frame {
    private static final int ITEM_ASM_BOOLEAN = 9;
    private static final int ITEM_ASM_BYTE = 10;
    private static final int ITEM_ASM_CHAR = 11;
    private static final int ITEM_ASM_SHORT = 12;
    private static final int DIM_SIZE = 6;
    private static final int KIND_SIZE = 4;
    private static final int FLAGS_SIZE = 2;
    private static final int VALUE_SIZE = 20;
    private static final int DIM_SHIFT = 26;
    private static final int KIND_SHIFT = 22;
    private static final int FLAGS_SHIFT = 20;
    private static final int DIM_MASK = -67108864;
    private static final int KIND_MASK = 62914560;
    private static final int VALUE_MASK = 1048575;
    private static final int ARRAY_OF = 67108864;
    private static final int ELEMENT_OF = -67108864;
    private static final int CONSTANT_KIND = 4194304;
    private static final int REFERENCE_KIND = 8388608;
    private static final int UNINITIALIZED_KIND = 12582912;
    private static final int LOCAL_KIND = 16777216;
    private static final int STACK_KIND = 20971520;
    private static final int TOP_IF_LONG_OR_DOUBLE_FLAG = 1048576;
    private static final int TOP = 4194304;
    private static final int BOOLEAN = 4194313;
    private static final int BYTE = 4194314;
    private static final int CHAR = 4194315;
    private static final int SHORT = 4194316;
    private static final int INTEGER = 4194305;
    private static final int FLOAT = 4194306;
    private static final int LONG = 4194308;
    private static final int DOUBLE = 4194307;
    private static final int NULL = 4194309;
    private static final int UNINITIALIZED_THIS = 4194310;

    /* renamed from: a, reason: collision with root package name */
    Label f4141a;
    private int[] inputLocals;
    private int[] inputStack;
    private int[] outputLocals;
    private int[] outputStack;
    private short outputStackStart;
    private short outputStackTop;
    private int initializationCount;
    private int[] initializations;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Frame(Label label) {
        this.f4141a = label;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Frame frame) {
        this.inputLocals = frame.inputLocals;
        this.inputStack = frame.inputStack;
        this.outputStackStart = (short) 0;
        this.outputLocals = frame.outputLocals;
        this.outputStack = frame.outputStack;
        this.outputStackTop = frame.outputStackTop;
        this.initializationCount = frame.initializationCount;
        this.initializations = frame.initializations;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(SymbolTable symbolTable, Object obj) {
        if (obj instanceof Integer) {
            return 4194304 | ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            return getAbstractTypeFromDescriptor(symbolTable, Type.getObjectType((String) obj).getDescriptor(), 0);
        }
        return 12582912 | symbolTable.a("", ((Label) obj).c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(SymbolTable symbolTable, String str) {
        return 8388608 | symbolTable.f(str);
    }

    private static int getAbstractTypeFromDescriptor(SymbolTable symbolTable, String str, int i) {
        int f;
        switch (str.charAt(i)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                return INTEGER;
            case 'D':
                return DOUBLE;
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
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                throw new IllegalArgumentException();
            case 'F':
                return FLOAT;
            case 'J':
                return LONG;
            case 'L':
                return 8388608 | symbolTable.f(str.substring(i + 1, str.length() - 1));
            case 'V':
                return 0;
            case '[':
                int i2 = i + 1;
                while (str.charAt(i2) == '[') {
                    i2++;
                }
                switch (str.charAt(i2)) {
                    case 'B':
                        f = BYTE;
                        break;
                    case 'C':
                        f = CHAR;
                        break;
                    case 'D':
                        f = DOUBLE;
                        break;
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
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    default:
                        throw new IllegalArgumentException();
                    case 'F':
                        f = FLOAT;
                        break;
                    case 'I':
                        f = INTEGER;
                        break;
                    case 'J':
                        f = LONG;
                        break;
                    case 'L':
                        f = 8388608 | symbolTable.f(str.substring(i2 + 1, str.length() - 1));
                        break;
                    case 'S':
                        f = SHORT;
                        break;
                    case 'Z':
                        f = BOOLEAN;
                        break;
                }
                return ((i2 - i) << 26) | f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SymbolTable symbolTable, int i, String str, int i2) {
        this.inputLocals = new int[i2];
        this.inputStack = new int[0];
        int i3 = 0;
        if ((i & 8) == 0) {
            if ((i & 262144) == 0) {
                i3 = 0 + 1;
                this.inputLocals[0] = 8388608 | symbolTable.f(symbolTable.c());
            } else {
                i3 = 0 + 1;
                this.inputLocals[0] = UNINITIALIZED_THIS;
            }
        }
        for (Type type : Type.getArgumentTypes(str)) {
            int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, type.getDescriptor(), 0);
            int i4 = i3;
            i3++;
            this.inputLocals[i4] = abstractTypeFromDescriptor;
            if (abstractTypeFromDescriptor == LONG || abstractTypeFromDescriptor == DOUBLE) {
                i3++;
                this.inputLocals[i3] = 4194304;
            }
        }
        while (i3 < i2) {
            int i5 = i3;
            i3++;
            this.inputLocals[i5] = 4194304;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SymbolTable symbolTable, int i, Object[] objArr, int i2, Object[] objArr2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3;
            i3++;
            this.inputLocals[i5] = a(symbolTable, objArr[i4]);
            if (objArr[i4] == Opcodes.LONG || objArr[i4] == Opcodes.DOUBLE) {
                i3++;
                this.inputLocals[i3] = 4194304;
            }
        }
        while (i3 < this.inputLocals.length) {
            int i6 = i3;
            i3++;
            this.inputLocals[i6] = 4194304;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            if (objArr2[i8] == Opcodes.LONG || objArr2[i8] == Opcodes.DOUBLE) {
                i7++;
            }
        }
        this.inputStack = new int[i2 + i7];
        int i9 = 0;
        for (int i10 = 0; i10 < i2; i10++) {
            int i11 = i9;
            i9++;
            this.inputStack[i11] = a(symbolTable, objArr2[i10]);
            if (objArr2[i10] == Opcodes.LONG || objArr2[i10] == Opcodes.DOUBLE) {
                i9++;
                this.inputStack[i9] = 4194304;
            }
        }
        this.outputStackTop = (short) 0;
        this.initializationCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.inputStack.length;
    }

    private int getLocal(int i) {
        if (this.outputLocals == null || i >= this.outputLocals.length) {
            return 16777216 | i;
        }
        int i2 = this.outputLocals[i];
        int i3 = i2;
        if (i2 == 0) {
            int i4 = 16777216 | i;
            this.outputLocals[i] = i4;
            i3 = i4;
        }
        return i3;
    }

    private void setLocal(int i, int i2) {
        if (this.outputLocals == null) {
            this.outputLocals = new int[10];
        }
        int length = this.outputLocals.length;
        if (i >= length) {
            int[] iArr = new int[Math.max(i + 1, 2 * length)];
            System.arraycopy(this.outputLocals, 0, iArr, 0, length);
            this.outputLocals = iArr;
        }
        this.outputLocals[i] = i2;
    }

    private void push(int i) {
        if (this.outputStack == null) {
            this.outputStack = new int[10];
        }
        int length = this.outputStack.length;
        if (this.outputStackTop >= length) {
            int[] iArr = new int[Math.max(this.outputStackTop + 1, 2 * length)];
            System.arraycopy(this.outputStack, 0, iArr, 0, length);
            this.outputStack = iArr;
        }
        int[] iArr2 = this.outputStack;
        short s = this.outputStackTop;
        this.outputStackTop = (short) (s + 1);
        iArr2[s] = i;
        short s2 = (short) (this.outputStackStart + this.outputStackTop);
        if (s2 > this.f4141a.f) {
            this.f4141a.f = s2;
        }
    }

    private void push(SymbolTable symbolTable, String str) {
        int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, str, str.charAt(0) == '(' ? Type.a(str) : 0);
        if (abstractTypeFromDescriptor != 0) {
            push(abstractTypeFromDescriptor);
            if (abstractTypeFromDescriptor == LONG || abstractTypeFromDescriptor == DOUBLE) {
                push(4194304);
            }
        }
    }

    private int pop() {
        if (this.outputStackTop > 0) {
            int[] iArr = this.outputStack;
            short s = (short) (this.outputStackTop - 1);
            this.outputStackTop = s;
            return iArr[s];
        }
        short s2 = (short) (this.outputStackStart - 1);
        this.outputStackStart = s2;
        return STACK_KIND | (-s2);
    }

    private void pop(int i) {
        if (this.outputStackTop >= i) {
            this.outputStackTop = (short) (this.outputStackTop - i);
        } else {
            this.outputStackStart = (short) (this.outputStackStart - (i - this.outputStackTop));
            this.outputStackTop = (short) 0;
        }
    }

    private void pop(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            pop((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            pop(2);
        } else {
            pop(1);
        }
    }

    private void addInitializedType(int i) {
        if (this.initializations == null) {
            this.initializations = new int[2];
        }
        int length = this.initializations.length;
        if (this.initializationCount >= length) {
            int[] iArr = new int[Math.max(this.initializationCount + 1, 2 * length)];
            System.arraycopy(this.initializations, 0, iArr, 0, length);
            this.initializations = iArr;
        }
        int[] iArr2 = this.initializations;
        int i2 = this.initializationCount;
        this.initializationCount = i2 + 1;
        iArr2[i2] = i;
    }

    private int getInitializedType(SymbolTable symbolTable, int i) {
        if (i == UNINITIALIZED_THIS || (i & (-4194304)) == 12582912) {
            for (int i2 = 0; i2 < this.initializationCount; i2++) {
                int i3 = this.initializations[i2];
                int i4 = i3;
                int i5 = i3 & (-67108864);
                int i6 = i4 & KIND_MASK;
                int i7 = i4 & 1048575;
                if (i6 == 16777216) {
                    i4 = i5 + this.inputLocals[i7];
                } else if (i6 == STACK_KIND) {
                    i4 = i5 + this.inputStack[this.inputStack.length - i7];
                }
                if (i == i4) {
                    if (i == UNINITIALIZED_THIS) {
                        return 8388608 | symbolTable.f(symbolTable.c());
                    }
                    return 8388608 | symbolTable.f(symbolTable.b(i & 1048575).e);
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2, Symbol symbol, SymbolTable symbolTable) {
        switch (i) {
            case 0:
            case 116:
            case 117:
            case 118:
            case 119:
            case 145:
            case 146:
            case 147:
            case 167:
            case 177:
                return;
            case 1:
                push(NULL);
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 16:
            case 17:
            case 21:
                push(INTEGER);
                return;
            case 9:
            case 10:
            case 22:
                push(LONG);
                push(4194304);
                return;
            case 11:
            case 12:
            case 13:
            case 23:
                push(FLOAT);
                return;
            case 14:
            case 15:
            case 24:
                push(DOUBLE);
                push(4194304);
                return;
            case 18:
                switch (symbol.f4147b) {
                    case 3:
                        push(INTEGER);
                        return;
                    case 4:
                        push(FLOAT);
                        return;
                    case 5:
                        push(LONG);
                        push(4194304);
                        return;
                    case 6:
                        push(DOUBLE);
                        push(4194304);
                        return;
                    case 7:
                        push(8388608 | symbolTable.f(TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME));
                        return;
                    case 8:
                        push(8388608 | symbolTable.f("java/lang/String"));
                        return;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    default:
                        throw new AssertionError();
                    case 15:
                        push(8388608 | symbolTable.f("java/lang/invoke/MethodHandle"));
                        return;
                    case 16:
                        push(8388608 | symbolTable.f("java/lang/invoke/MethodType"));
                        return;
                    case 17:
                        push(symbolTable, symbol.e);
                        return;
                }
            case 19:
            case 20:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 196:
            default:
                throw new IllegalArgumentException();
            case 25:
                push(getLocal(i2));
                return;
            case 46:
            case 51:
            case 52:
            case 53:
            case 96:
            case 100:
            case 104:
            case 108:
            case 112:
            case 120:
            case 122:
            case 124:
            case 126:
            case 128:
            case 130:
            case 136:
            case 142:
            case 149:
            case 150:
                pop(2);
                push(INTEGER);
                return;
            case 47:
            case 143:
                pop(2);
                push(LONG);
                push(4194304);
                return;
            case 48:
            case 98:
            case 102:
            case 106:
            case 110:
            case 114:
            case 137:
            case 144:
                pop(2);
                push(FLOAT);
                return;
            case 49:
            case 138:
                pop(2);
                push(DOUBLE);
                push(4194304);
                return;
            case 50:
                pop(1);
                int pop = pop();
                push(pop == NULL ? pop : pop - 67108864);
                return;
            case 54:
            case 56:
            case 58:
                setLocal(i2, pop());
                if (i2 > 0) {
                    int local = getLocal(i2 - 1);
                    if (local == LONG || local == DOUBLE) {
                        setLocal(i2 - 1, 4194304);
                        return;
                    } else {
                        if ((local & KIND_MASK) == 16777216 || (local & KIND_MASK) == STACK_KIND) {
                            setLocal(i2 - 1, local | 1048576);
                            return;
                        }
                        return;
                    }
                }
                return;
            case 55:
            case 57:
                pop(1);
                setLocal(i2, pop());
                setLocal(i2 + 1, 4194304);
                if (i2 > 0) {
                    int local2 = getLocal(i2 - 1);
                    if (local2 == LONG || local2 == DOUBLE) {
                        setLocal(i2 - 1, 4194304);
                        return;
                    } else {
                        if ((local2 & KIND_MASK) == 16777216 || (local2 & KIND_MASK) == STACK_KIND) {
                            setLocal(i2 - 1, local2 | 1048576);
                            return;
                        }
                        return;
                    }
                }
                return;
            case 79:
            case 81:
            case 83:
            case 84:
            case 85:
            case 86:
                pop(3);
                return;
            case 80:
            case 82:
                pop(4);
                return;
            case 87:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 170:
            case 171:
            case 172:
            case 174:
            case 176:
            case 191:
            case 194:
            case 195:
            case 198:
            case 199:
                pop(1);
                return;
            case 88:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 173:
            case 175:
                pop(2);
                return;
            case 89:
                int pop2 = pop();
                push(pop2);
                push(pop2);
                return;
            case 90:
                int pop3 = pop();
                int pop4 = pop();
                push(pop3);
                push(pop4);
                push(pop3);
                return;
            case 91:
                int pop5 = pop();
                int pop6 = pop();
                int pop7 = pop();
                push(pop5);
                push(pop7);
                push(pop6);
                push(pop5);
                return;
            case 92:
                int pop8 = pop();
                int pop9 = pop();
                push(pop9);
                push(pop8);
                push(pop9);
                push(pop8);
                return;
            case 93:
                int pop10 = pop();
                int pop11 = pop();
                int pop12 = pop();
                push(pop11);
                push(pop10);
                push(pop12);
                push(pop11);
                push(pop10);
                return;
            case 94:
                int pop13 = pop();
                int pop14 = pop();
                int pop15 = pop();
                int pop16 = pop();
                push(pop14);
                push(pop13);
                push(pop16);
                push(pop15);
                push(pop14);
                push(pop13);
                return;
            case 95:
                int pop17 = pop();
                int pop18 = pop();
                push(pop17);
                push(pop18);
                return;
            case 97:
            case 101:
            case 105:
            case 109:
            case 113:
            case 127:
            case 129:
            case 131:
                pop(4);
                push(LONG);
                push(4194304);
                return;
            case 99:
            case 103:
            case 107:
            case 111:
            case 115:
                pop(4);
                push(DOUBLE);
                push(4194304);
                return;
            case 121:
            case 123:
            case 125:
                pop(3);
                push(LONG);
                push(4194304);
                return;
            case 132:
                setLocal(i2, INTEGER);
                return;
            case 133:
            case 140:
                pop(1);
                push(LONG);
                push(4194304);
                return;
            case 134:
                pop(1);
                push(FLOAT);
                return;
            case 135:
            case 141:
                pop(1);
                push(DOUBLE);
                push(4194304);
                return;
            case 139:
            case 190:
            case 193:
                pop(1);
                push(INTEGER);
                return;
            case 148:
            case 151:
            case 152:
                pop(4);
                push(INTEGER);
                return;
            case 168:
            case 169:
                throw new IllegalArgumentException("JSR/RET are not supported with computeFrames option");
            case 178:
                push(symbolTable, symbol.e);
                return;
            case 179:
                pop(symbol.e);
                return;
            case 180:
                pop(1);
                push(symbolTable, symbol.e);
                return;
            case 181:
                pop(symbol.e);
                pop();
                return;
            case 182:
            case 183:
            case 184:
            case 185:
                pop(symbol.e);
                if (i != 184) {
                    int pop19 = pop();
                    if (i == 183 && symbol.d.charAt(0) == '<') {
                        addInitializedType(pop19);
                    }
                }
                push(symbolTable, symbol.e);
                return;
            case 186:
                pop(symbol.e);
                push(symbolTable, symbol.e);
                return;
            case 187:
                push(12582912 | symbolTable.a(symbol.e, i2));
                return;
            case 188:
                pop();
                switch (i2) {
                    case 4:
                        push(71303177);
                        return;
                    case 5:
                        push(71303179);
                        return;
                    case 6:
                        push(71303170);
                        return;
                    case 7:
                        push(71303171);
                        return;
                    case 8:
                        push(71303178);
                        return;
                    case 9:
                        push(71303180);
                        return;
                    case 10:
                        push(71303169);
                        return;
                    case 11:
                        push(71303172);
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            case 189:
                String str = symbol.e;
                pop();
                if (str.charAt(0) == '[') {
                    push(symbolTable, "[" + str);
                    return;
                } else {
                    push(75497472 | symbolTable.f(str));
                    return;
                }
            case 192:
                String str2 = symbol.e;
                pop();
                if (str2.charAt(0) == '[') {
                    push(symbolTable, str2);
                    return;
                } else {
                    push(8388608 | symbolTable.f(str2));
                    return;
                }
            case 197:
                pop(i2);
                push(symbolTable, symbol.e);
                return;
        }
    }

    private int getConcreteOutputType(int i, int i2) {
        int i3 = i & (-67108864);
        int i4 = i & KIND_MASK;
        if (i4 == 16777216) {
            int i5 = i3 + this.inputLocals[i & 1048575];
            if ((i & 1048576) != 0 && (i5 == LONG || i5 == DOUBLE)) {
                i5 = 4194304;
            }
            return i5;
        }
        if (i4 == STACK_KIND) {
            int i6 = i3 + this.inputStack[i2 - (i & 1048575)];
            if ((i & 1048576) != 0 && (i6 == LONG || i6 == DOUBLE)) {
                i6 = 4194304;
            }
            return i6;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(SymbolTable symbolTable, Frame frame, int i) {
        int i2;
        boolean z = false;
        int length = this.inputLocals.length;
        int length2 = this.inputStack.length;
        if (frame.inputLocals == null) {
            frame.inputLocals = new int[length];
            z = true;
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (this.outputLocals != null && i3 < this.outputLocals.length) {
                int i4 = this.outputLocals[i3];
                if (i4 == 0) {
                    i2 = this.inputLocals[i3];
                } else {
                    i2 = getConcreteOutputType(i4, length2);
                }
            } else {
                i2 = this.inputLocals[i3];
            }
            if (this.initializations != null) {
                i2 = getInitializedType(symbolTable, i2);
            }
            z |= merge(symbolTable, i2, frame.inputLocals, i3);
        }
        if (i > 0) {
            for (int i5 = 0; i5 < length; i5++) {
                z |= merge(symbolTable, this.inputLocals[i5], frame.inputLocals, i5);
            }
            if (frame.inputStack == null) {
                frame.inputStack = new int[1];
                z = true;
            }
            return z | merge(symbolTable, i, frame.inputStack, 0);
        }
        int length3 = this.inputStack.length + this.outputStackStart;
        if (frame.inputStack == null) {
            frame.inputStack = new int[length3 + this.outputStackTop];
            z = true;
        }
        for (int i6 = 0; i6 < length3; i6++) {
            int i7 = this.inputStack[i6];
            if (this.initializations != null) {
                i7 = getInitializedType(symbolTable, i7);
            }
            z |= merge(symbolTable, i7, frame.inputStack, i6);
        }
        for (int i8 = 0; i8 < this.outputStackTop; i8++) {
            int concreteOutputType = getConcreteOutputType(this.outputStack[i8], length2);
            if (this.initializations != null) {
                concreteOutputType = getInitializedType(symbolTable, concreteOutputType);
            }
            z |= merge(symbolTable, concreteOutputType, frame.inputStack, length3 + i8);
        }
        return z;
    }

    private static boolean merge(SymbolTable symbolTable, int i, int[] iArr, int i2) {
        int min;
        int i3 = iArr[i2];
        if (i3 == i) {
            return false;
        }
        int i4 = i;
        if ((i & Lua.MAXARG_Ax) == NULL) {
            if (i3 == NULL) {
                return false;
            }
            i4 = NULL;
        }
        if (i3 == 0) {
            iArr[i2] = i4;
            return true;
        }
        if ((i3 & (-67108864)) != 0 || (i3 & KIND_MASK) == 8388608) {
            if (i4 != NULL) {
                if ((i & (-4194304)) == (i3 & (-4194304))) {
                    if ((i3 & KIND_MASK) == 8388608) {
                        min = (i & (-67108864)) | 8388608 | symbolTable.a(i & 1048575, i3 & 1048575);
                    } else {
                        min = ((-67108864) + (i & (-67108864))) | 8388608 | symbolTable.f(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                    }
                } else if ((i & (-67108864)) != 0 || (i & KIND_MASK) == 8388608) {
                    int i5 = i & (-67108864);
                    int i6 = i5;
                    if (i5 != 0 && (i & KIND_MASK) != 8388608) {
                        i6 -= 67108864;
                    }
                    int i7 = i3 & (-67108864);
                    int i8 = i7;
                    if (i7 != 0 && (i3 & KIND_MASK) != 8388608) {
                        i8 -= 67108864;
                    }
                    min = Math.min(i6, i8) | 8388608 | symbolTable.f(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                } else {
                    min = 4194304;
                }
            } else {
                return false;
            }
        } else if (i3 == NULL) {
            min = ((i4 & (-67108864)) != 0 || (i4 & KIND_MASK) == 8388608) ? i : 4194304;
        } else {
            min = 4194304;
        }
        if (min != i3) {
            iArr[i2] = min;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(MethodWriter methodWriter) {
        int[] iArr = this.inputLocals;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < iArr.length) {
            int i4 = iArr[i3];
            i3 += (i4 == LONG || i4 == DOUBLE) ? 2 : 1;
            if (i4 == 4194304) {
                i2++;
            } else {
                i += i2 + 1;
                i2 = 0;
            }
        }
        int[] iArr2 = this.inputStack;
        int i5 = 0;
        int i6 = 0;
        while (i6 < iArr2.length) {
            int i7 = iArr2[i6];
            i6 += (i7 == LONG || i7 == DOUBLE) ? 2 : 1;
            i5++;
        }
        int a2 = methodWriter.a(this.f4141a.c, i, i5);
        int i8 = 0;
        while (true) {
            int i9 = i;
            i--;
            if (i9 <= 0) {
                break;
            }
            int i10 = iArr[i8];
            i8 += (i10 == LONG || i10 == DOUBLE) ? 2 : 1;
            int i11 = a2;
            a2++;
            methodWriter.a(i11, i10);
        }
        int i12 = 0;
        while (true) {
            int i13 = i5;
            i5--;
            if (i13 > 0) {
                int i14 = iArr2[i12];
                i12 += (i14 == LONG || i14 == DOUBLE) ? 2 : 1;
                int i15 = a2;
                a2++;
                methodWriter.a(i15, i14);
            } else {
                methodWriter.c();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x00c4. Please report as an issue. */
    public static void a(SymbolTable symbolTable, int i, ByteVector byteVector) {
        int i2 = (i & (-67108864)) >> 26;
        int i3 = i2;
        if (i2 == 0) {
            int i4 = i & 1048575;
            switch (i & KIND_MASK) {
                case 4194304:
                    byteVector.putByte(i4);
                    return;
                case 8388608:
                    byteVector.putByte(7).putShort(symbolTable.a(symbolTable.b(i4).e).f4146a);
                    return;
                case 12582912:
                    byteVector.putByte(8).putShort((int) symbolTable.b(i4).f);
                    return;
                default:
                    throw new AssertionError();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i5 = i3;
            i3--;
            if (i5 <= 0) {
                break;
            } else {
                sb.append('[');
            }
        }
        if ((i & KIND_MASK) == 8388608) {
            sb.append('L').append(symbolTable.b(i & 1048575).e).append(';');
        } else {
            switch (i & 1048575) {
                case 1:
                    sb.append('I');
                    break;
                case 2:
                    sb.append('F');
                    break;
                case 3:
                    sb.append('D');
                    break;
                case 4:
                    sb.append('J');
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                default:
                    throw new AssertionError();
                case 9:
                    sb.append('Z');
                    break;
                case 10:
                    sb.append('B');
                    break;
                case 11:
                    sb.append('C');
                    break;
                case 12:
                    sb.append('S');
                    break;
            }
        }
        byteVector.putByte(7).putShort(symbolTable.a(sb.toString()).f4146a);
    }
}
