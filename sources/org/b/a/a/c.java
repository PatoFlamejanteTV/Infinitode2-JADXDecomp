package org.b.a.a;

/* loaded from: infinitode-2.jar:org/b/a/a/c.class */
public class c implements b {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f4697a;

    public static boolean a(char c) {
        if (c < 'A' || c > 'Z') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    public static boolean b(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean c(char c) {
        return a(c) || b(c);
    }

    public static boolean d(char c) {
        return c >= 128;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0026. Please report as an issue. */
    public static int a(CharSequence charSequence, int i) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        boolean z2 = false;
        int i5 = i;
        for (int i6 = i; i6 < charSequence.length(); i6++) {
            switch (charSequence.charAt(i6)) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case ' ':
                case '<':
                case '>':
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 132:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                case 149:
                case 150:
                case 151:
                case 152:
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                case 159:
                    break;
                case '!':
                case ',':
                case '.':
                case ':':
                case ';':
                case '?':
                case '\"':
                    boolean z3 = !z;
                    z = z3;
                    if (z3) {
                    }
                    i5 = i6;
                case '#':
                case '$':
                case '%':
                case '&':
                case '*':
                case '+':
                case '-':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '=':
                case '@':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '\\':
                case '^':
                case '_':
                case '`':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case '|':
                case '~':
                default:
                    i5 = i6;
                case '\'':
                    boolean z4 = !z2;
                    z2 = z4;
                    if (z4) {
                    }
                    i5 = i6;
                case '(':
                    i2++;
                case ')':
                    i2--;
                    if (i2 < 0) {
                        break;
                    }
                    i5 = i6;
                case '/':
                    if (i5 != i6 - 1) {
                    }
                    i5 = i6;
                case '[':
                    i3++;
                case ']':
                    i3--;
                    if (i3 < 0) {
                        break;
                    }
                    i5 = i6;
                case '{':
                    i4++;
                case '}':
                    i4--;
                    if (i4 < 0) {
                        break;
                    }
                    i5 = i6;
            }
            return i5;
        }
        return i5;
    }

    public c(boolean z) {
        this.f4697a = z;
    }

    @Override // org.b.a.a.b
    public org.b.a.c a(CharSequence charSequence, int i, int i2) {
        int b2;
        int b3 = b(charSequence, i - 1, i2);
        if (b3 != -1 && (b2 = b(charSequence, i + 1)) != -1) {
            return new a(org.b.a.d.EMAIL, b3, b2 + 1);
        }
        return null;
    }

    private int b(CharSequence charSequence, int i, int i2) {
        boolean z;
        int i3 = -1;
        boolean z2 = true;
        for (int i4 = i; i4 >= i2; i4--) {
            char charAt = charSequence.charAt(i4);
            if (e(charAt)) {
                i3 = i4;
                z = false;
            } else {
                if (charAt != '.' || z2) {
                    break;
                }
                z = true;
            }
            z2 = z;
        }
        return i3;
    }

    private int b(CharSequence charSequence, int i) {
        boolean z = true;
        boolean z2 = false;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = i; i4 < charSequence.length(); i4++) {
            char charAt = charSequence.charAt(i4);
            if (z) {
                if (!f(charAt)) {
                    break;
                }
                i3 = i4;
                z = false;
                z2 = true;
            } else if (charAt == '.') {
                if (!z2) {
                    break;
                }
                z = true;
                if (i2 == -1) {
                    i2 = i4;
                }
            } else {
                if (charAt == '-') {
                    z2 = false;
                } else {
                    if (!f(charAt)) {
                        break;
                    }
                    i3 = i4;
                    z2 = true;
                }
            }
        }
        if (this.f4697a && (i2 == -1 || i2 > i3)) {
            return -1;
        }
        return i3;
    }

    private static boolean e(char c) {
        if (c(c) || d(c)) {
            return true;
        }
        switch (c) {
            case '!':
            case '#':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '*':
            case '+':
            case '-':
            case '/':
            case '=':
            case '?':
            case '^':
            case '_':
            case '`':
            case '{':
            case '|':
            case '}':
            case '~':
                return true;
            default:
                return false;
        }
    }

    private static boolean f(char c) {
        return c(c) || d(c);
    }
}
