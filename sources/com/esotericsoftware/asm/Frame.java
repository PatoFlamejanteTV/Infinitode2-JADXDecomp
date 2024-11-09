package com.esotericsoftware.asm;

import com.badlogic.gdx.net.HttpStatus;
import com.prineside.luaj.Lua;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.lwjgl.opengl.GL11;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/Frame.class */
public final class Frame {

    /* renamed from: a, reason: collision with root package name */
    static final int[] f1446a;

    /* renamed from: b, reason: collision with root package name */
    Label f1447b;
    int[] c;
    int[] d;
    private int[] e;
    private int[] f;
    private int g;
    private int h;
    private int[] i;

    private int a(int i) {
        if (this.e == null || i >= this.e.length) {
            return 33554432 | i;
        }
        int i2 = this.e[i];
        int i3 = i2;
        if (i2 == 0) {
            int i4 = 33554432 | i;
            this.e[i] = i4;
            i3 = i4;
        }
        return i3;
    }

    private void a(int i, int i2) {
        if (this.e == null) {
            this.e = new int[10];
        }
        int length = this.e.length;
        if (i >= length) {
            int[] iArr = new int[Math.max(i + 1, 2 * length)];
            System.arraycopy(this.e, 0, iArr, 0, length);
            this.e = iArr;
        }
        this.e[i] = i2;
    }

    private void b(int i) {
        if (this.f == null) {
            this.f = new int[10];
        }
        int length = this.f.length;
        if (this.g >= length) {
            int[] iArr = new int[Math.max(this.g + 1, 2 * length)];
            System.arraycopy(this.f, 0, iArr, 0, length);
            this.f = iArr;
        }
        int[] iArr2 = this.f;
        int i2 = this.g;
        this.g = i2 + 1;
        iArr2[i2] = i;
        int i3 = this.f1447b.f + this.g;
        if (i3 > this.f1447b.g) {
            this.f1447b.g = i3;
        }
    }

    private void a(ClassWriter classWriter, String str) {
        int b2 = b(classWriter, str);
        if (b2 != 0) {
            b(b2);
            if (b2 == 16777220 || b2 == 16777219) {
                b(16777216);
            }
        }
    }

    private static int b(ClassWriter classWriter, String str) {
        int m699c;
        int indexOf = str.charAt(0) == '(' ? str.indexOf(41) + 1 : 0;
        switch (str.charAt(indexOf)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                return 16777217;
            case 'D':
                return 16777219;
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
                int i = indexOf + 1;
                while (str.charAt(i) == '[') {
                    i++;
                }
                switch (str.charAt(i)) {
                    case 'B':
                        m699c = 16777226;
                        break;
                    case 'C':
                        m699c = 16777227;
                        break;
                    case 'D':
                        m699c = 16777219;
                        break;
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
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    default:
                        m699c = 24117248 | classWriter.m699c(str.substring(i + 1, str.length() - 1));
                        break;
                    case 'F':
                        m699c = 16777218;
                        break;
                    case 'I':
                        m699c = 16777217;
                        break;
                    case 'J':
                        m699c = 16777220;
                        break;
                    case 'S':
                        m699c = 16777228;
                        break;
                    case 'Z':
                        m699c = 16777225;
                        break;
                }
                return ((i - indexOf) << 28) | m699c;
            case 'F':
                return 16777218;
            case 'J':
                return 16777220;
            case 'L':
                return 24117248 | classWriter.m699c(str.substring(indexOf + 1, str.length() - 1));
            case 'V':
                return 0;
        }
    }

    private int a() {
        if (this.g > 0) {
            int[] iArr = this.f;
            int i = this.g - 1;
            this.g = i;
            return iArr[i];
        }
        Label label = this.f1447b;
        int i2 = label.f - 1;
        label.f = i2;
        return 50331648 | (-i2);
    }

    private void c(int i) {
        if (this.g >= i) {
            this.g -= i;
            return;
        }
        this.f1447b.f -= i - this.g;
        this.g = 0;
    }

    private void a(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            c((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            c(2);
        } else {
            c(1);
        }
    }

    private void d(int i) {
        if (this.i == null) {
            this.i = new int[2];
        }
        int length = this.i.length;
        if (this.h >= length) {
            int[] iArr = new int[Math.max(this.h + 1, 2 * length)];
            System.arraycopy(this.i, 0, iArr, 0, length);
            this.i = iArr;
        }
        int[] iArr2 = this.i;
        int i2 = this.h;
        this.h = i2 + 1;
        iArr2[i2] = i;
    }

    private int a(ClassWriter classWriter, int i) {
        int m699c;
        if (i == 16777222) {
            m699c = 24117248 | classWriter.m699c(classWriter.I);
        } else {
            if ((i & (-1048576)) != 25165824) {
                return i;
            }
            m699c = 24117248 | classWriter.m699c(classWriter.H[i & GL11.GL_ALL_ATTRIB_BITS].g);
        }
        for (int i2 = 0; i2 < this.h; i2++) {
            int i3 = this.i[i2];
            int i4 = i3;
            int i5 = i3 & (-268435456);
            int i6 = i4 & 251658240;
            if (i6 == 33554432) {
                i4 = i5 + this.c[i4 & Lua.MASK_NOT_B];
            } else if (i6 == 50331648) {
                i4 = i5 + this.d[this.d.length - (i4 & Lua.MASK_NOT_B)];
            }
            if (i == i4) {
                return m699c;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ClassWriter classWriter, int i, Type[] typeArr, int i2) {
        this.c = new int[i2];
        this.d = new int[0];
        int i3 = 0;
        if ((i & 8) == 0) {
            if ((i & 524288) == 0) {
                i3 = 0 + 1;
                this.c[0] = 24117248 | classWriter.m699c(classWriter.I);
            } else {
                i3 = 0 + 1;
                this.c[0] = 16777222;
            }
        }
        for (Type type : typeArr) {
            int b2 = b(classWriter, type.getDescriptor());
            int i4 = i3;
            i3++;
            this.c[i4] = b2;
            if (b2 == 16777220 || b2 == 16777219) {
                i3++;
                this.c[i3] = 16777216;
            }
        }
        while (i3 < i2) {
            int i5 = i3;
            i3++;
            this.c[i5] = 16777216;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, int i2, ClassWriter classWriter, Item item) {
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
                b(16777221);
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
                b(16777217);
                return;
            case 9:
            case 10:
            case 22:
                b(16777220);
                b(16777216);
                return;
            case 11:
            case 12:
            case 13:
            case 23:
                b(16777218);
                return;
            case 14:
            case 15:
            case 24:
                b(16777219);
                b(16777216);
                return;
            case 18:
                switch (item.f1453b) {
                    case 3:
                        b(16777217);
                        return;
                    case 4:
                        b(16777218);
                        return;
                    case 5:
                        b(16777220);
                        b(16777216);
                        return;
                    case 6:
                        b(16777219);
                        b(16777216);
                        return;
                    case 7:
                        b(24117248 | classWriter.m699c(TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME));
                        return;
                    case 8:
                        b(24117248 | classWriter.m699c("java/lang/String"));
                        return;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    default:
                        b(24117248 | classWriter.m699c("java/lang/invoke/MethodHandle"));
                        return;
                    case 16:
                        b(24117248 | classWriter.m699c("java/lang/invoke/MethodType"));
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
            case 197:
            default:
                c(i2);
                a(classWriter, item.g);
                return;
            case 25:
                b(a(i2));
                return;
            case 46:
            case 51:
            case 52:
            case 53:
                c(2);
                b(16777217);
                return;
            case 47:
            case 143:
                c(2);
                b(16777220);
                b(16777216);
                return;
            case 48:
                c(2);
                b(16777218);
                return;
            case 49:
            case 138:
                c(2);
                b(16777219);
                b(16777216);
                return;
            case 50:
                c(1);
                b(a() - 268435456);
                return;
            case 54:
            case 56:
            case 58:
                a(i2, a());
                if (i2 > 0) {
                    int a2 = a(i2 - 1);
                    if (a2 == 16777220 || a2 == 16777219) {
                        a(i2 - 1, 16777216);
                        return;
                    } else {
                        if ((a2 & 251658240) != 16777216) {
                            a(i2 - 1, a2 | 8388608);
                            return;
                        }
                        return;
                    }
                }
                return;
            case 55:
            case 57:
                c(1);
                a(i2, a());
                a(i2 + 1, 16777216);
                if (i2 > 0) {
                    int a3 = a(i2 - 1);
                    if (a3 == 16777220 || a3 == 16777219) {
                        a(i2 - 1, 16777216);
                        return;
                    } else {
                        if ((a3 & 251658240) != 16777216) {
                            a(i2 - 1, a3 | 8388608);
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
                c(3);
                return;
            case 80:
            case 82:
                c(4);
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
                c(1);
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
                c(2);
                return;
            case 89:
                int a4 = a();
                b(a4);
                b(a4);
                return;
            case 90:
                int a5 = a();
                int a6 = a();
                b(a5);
                b(a6);
                b(a5);
                return;
            case 91:
                int a7 = a();
                int a8 = a();
                int a9 = a();
                b(a7);
                b(a9);
                b(a8);
                b(a7);
                return;
            case 92:
                int a10 = a();
                int a11 = a();
                b(a11);
                b(a10);
                b(a11);
                b(a10);
                return;
            case 93:
                int a12 = a();
                int a13 = a();
                int a14 = a();
                b(a13);
                b(a12);
                b(a14);
                b(a13);
                b(a12);
                return;
            case 94:
                int a15 = a();
                int a16 = a();
                int a17 = a();
                int a18 = a();
                b(a16);
                b(a15);
                b(a18);
                b(a17);
                b(a16);
                b(a15);
                return;
            case 95:
                int a19 = a();
                int a20 = a();
                b(a19);
                b(a20);
                return;
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
                c(2);
                b(16777217);
                return;
            case 97:
            case 101:
            case 105:
            case 109:
            case 113:
            case 127:
            case 129:
            case 131:
                c(4);
                b(16777220);
                b(16777216);
                return;
            case 98:
            case 102:
            case 106:
            case 110:
            case 114:
            case 137:
            case 144:
                c(2);
                b(16777218);
                return;
            case 99:
            case 103:
            case 107:
            case 111:
            case 115:
                c(4);
                b(16777219);
                b(16777216);
                return;
            case 121:
            case 123:
            case 125:
                c(3);
                b(16777220);
                b(16777216);
                return;
            case 132:
                a(i2, 16777217);
                return;
            case 133:
            case 140:
                c(1);
                b(16777220);
                b(16777216);
                return;
            case 134:
                c(1);
                b(16777218);
                return;
            case 135:
            case 141:
                c(1);
                b(16777219);
                b(16777216);
                return;
            case 139:
            case 190:
            case 193:
                c(1);
                b(16777217);
                return;
            case 148:
            case 151:
            case 152:
                c(4);
                b(16777217);
                return;
            case 168:
            case 169:
                throw new RuntimeException("JSR/RET are not supported with computeFrames option");
            case 178:
                a(classWriter, item.i);
                return;
            case 179:
                a(item.i);
                return;
            case 180:
                c(1);
                a(classWriter, item.i);
                return;
            case 181:
                a(item.i);
                a();
                return;
            case 182:
            case 183:
            case 184:
            case 185:
                a(item.i);
                if (i != 184) {
                    int a21 = a();
                    if (i == 183 && item.h.charAt(0) == '<') {
                        d(a21);
                    }
                }
                a(classWriter, item.i);
                return;
            case 186:
                a(item.h);
                a(classWriter, item.h);
                return;
            case 187:
                b(25165824 | classWriter.a(item.g, i2));
                return;
            case 188:
                a();
                switch (i2) {
                    case 4:
                        b(285212681);
                        return;
                    case 5:
                        b(285212683);
                        return;
                    case 6:
                        b(285212674);
                        return;
                    case 7:
                        b(285212675);
                        return;
                    case 8:
                        b(285212682);
                        return;
                    case 9:
                        b(285212684);
                        return;
                    case 10:
                        b(285212673);
                        return;
                    default:
                        b(285212676);
                        return;
                }
            case 189:
                String str = item.g;
                a();
                if (str.charAt(0) == '[') {
                    a(classWriter, new StringBuffer("[").append(str).toString());
                    return;
                } else {
                    b(292552704 | classWriter.m699c(str));
                    return;
                }
            case 192:
                String str2 = item.g;
                a();
                if (str2.charAt(0) == '[') {
                    a(classWriter, str2);
                    return;
                } else {
                    b(24117248 | classWriter.m699c(str2));
                    return;
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(ClassWriter classWriter, Frame frame, int i) {
        int i2;
        int i3;
        int i4;
        boolean z = false;
        int length = this.c.length;
        int length2 = this.d.length;
        if (frame.c == null) {
            frame.c = new int[length];
            z = true;
        }
        for (int i5 = 0; i5 < length; i5++) {
            if (this.e == null || i5 >= this.e.length || (i4 = this.e[i5]) == 0) {
                i3 = this.c[i5];
            } else {
                int i6 = i4 & (-268435456);
                int i7 = i4 & 251658240;
                if (i7 == 16777216) {
                    i3 = i4;
                } else {
                    i3 = i7 == 33554432 ? i6 + this.c[i4 & Lua.MASK_NOT_B] : i6 + this.d[length2 - (i4 & Lua.MASK_NOT_B)];
                    if ((i4 & 8388608) != 0 && (i3 == 16777220 || i3 == 16777219)) {
                        i3 = 16777216;
                    }
                }
            }
            if (this.i != null) {
                i3 = a(classWriter, i3);
            }
            z |= a(classWriter, i3, frame.c, i5);
        }
        if (i > 0) {
            for (int i8 = 0; i8 < length; i8++) {
                z |= a(classWriter, this.c[i8], frame.c, i8);
            }
            if (frame.d == null) {
                frame.d = new int[1];
                z = true;
            }
            return z | a(classWriter, i, frame.d, 0);
        }
        int length3 = this.d.length + this.f1447b.f;
        if (frame.d == null) {
            frame.d = new int[length3 + this.g];
            z = true;
        }
        for (int i9 = 0; i9 < length3; i9++) {
            int i10 = this.d[i9];
            if (this.i != null) {
                i10 = a(classWriter, i10);
            }
            z |= a(classWriter, i10, frame.d, i9);
        }
        for (int i11 = 0; i11 < this.g; i11++) {
            int i12 = this.f[i11];
            int i13 = i12 & (-268435456);
            int i14 = i12 & 251658240;
            if (i14 == 16777216) {
                i2 = i12;
            } else {
                i2 = i14 == 33554432 ? i13 + this.c[i12 & Lua.MASK_NOT_B] : i13 + this.d[length2 - (i12 & Lua.MASK_NOT_B)];
                if ((i12 & 8388608) != 0 && (i2 == 16777220 || i2 == 16777219)) {
                    i2 = 16777216;
                }
            }
            if (this.i != null) {
                i2 = a(classWriter, i2);
            }
            z |= a(classWriter, i2, frame.d, length3 + i11);
        }
        return z;
    }

    private static boolean a(ClassWriter classWriter, int i, int[] iArr, int i2) {
        int min;
        int i3 = iArr[i2];
        if (i3 == i) {
            return false;
        }
        if ((i & 268435455) == 16777221) {
            if (i3 == 16777221) {
                return false;
            }
            i = 16777221;
        }
        if (i3 == 0) {
            iArr[i2] = i;
            return true;
        }
        if ((i3 & 267386880) == 24117248 || (i3 & (-268435456)) != 0) {
            if (i == 16777221) {
                return false;
            }
            if ((i & (-1048576)) == (i3 & (-1048576))) {
                min = (i3 & 267386880) == 24117248 ? (i & (-268435456)) | 24117248 | classWriter.a(i & GL11.GL_ALL_ATTRIB_BITS, i3 & GL11.GL_ALL_ATTRIB_BITS) : ((-268435456) + (i3 & (-268435456))) | 24117248 | classWriter.m699c(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
            } else if ((i & 267386880) == 24117248 || (i & (-268435456)) != 0) {
                min = Math.min((((i & (-268435456)) == 0 || (i & 267386880) == 24117248) ? 0 : -268435456) + (i & (-268435456)), (((i3 & (-268435456)) == 0 || (i3 & 267386880) == 24117248) ? 0 : -268435456) + (i3 & (-268435456))) | 24117248 | classWriter.m699c(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
            } else {
                min = 16777216;
            }
        } else if (i3 == 16777221) {
            min = ((i & 267386880) == 24117248 || (i & (-268435456)) != 0) ? i : 16777216;
        } else {
            min = 16777216;
        }
        if (i3 == min) {
            return false;
        }
        iArr[i2] = min;
        return true;
    }

    static {
        _clinit_();
        int[] iArr = new int[HttpStatus.SC_ACCEPTED];
        for (int i = 0; i < 202; i++) {
            iArr[i] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i) - 'E';
        }
        f1446a = iArr;
    }

    static void _clinit_() {
    }
}
