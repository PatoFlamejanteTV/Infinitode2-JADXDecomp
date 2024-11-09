package com.a.a.b.d;

import com.a.a.b.j;
import com.a.a.b.l;
import com.a.a.b.o;
import com.a.a.b.p;
import com.a.a.b.s;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/a/a/b/d/h.class */
public final class h extends com.a.a.b.a.b {
    private static final int E = l.a.ALLOW_TRAILING_COMMA.c();
    private static final int F = l.a.ALLOW_NUMERIC_LEADING_ZEROS.c();
    private static final int G = l.a.ALLOW_NON_NUMERIC_NUMBERS.c();
    private static final int H = l.a.ALLOW_MISSING_VALUES.c();
    private static final int I = l.a.ALLOW_SINGLE_QUOTES.c();
    private static final int J = l.a.ALLOW_UNQUOTED_FIELD_NAMES.c();
    private static final int K = l.a.ALLOW_COMMENTS.c();
    private static final int L = l.a.ALLOW_YAML_COMMENTS.c();
    private static final int[] M = com.a.a.b.c.b.b();
    private static int[] N = com.a.a.b.c.b.a();
    private p O;
    private com.a.a.b.e.a P;
    private int[] Q;
    private boolean R;
    private int S;
    private int T;
    private int U;
    private int V;
    private InputStream W;
    private byte[] X;
    private boolean Y;

    public h(com.a.a.b.c.a aVar, int i, InputStream inputStream, p pVar, com.a.a.b.e.a aVar2, byte[] bArr, int i2, int i3, int i4, boolean z) {
        super(aVar, i);
        this.Q = new int[16];
        this.W = inputStream;
        this.O = pVar;
        this.P = aVar2;
        this.X = bArr;
        this.e = i2;
        this.f = i3;
        this.i = i2 - i4;
        this.g = (-i2) + i4;
        this.Y = z;
    }

    @Override // com.a.a.b.l
    public final p a() {
        return this.O;
    }

    @Override // com.a.a.b.l
    public final com.a.a.b.g.i<s> c() {
        return c;
    }

    private boolean am() {
        int length;
        if (this.W != null && (length = this.X.length) != 0) {
            int read = this.W.read(this.X, 0, length);
            if (read > 0) {
                int i = this.f;
                this.g += i;
                this.i -= i;
                this.T -= i;
                this.e = 0;
                this.f = read;
                return true;
            }
            W();
            if (read == 0) {
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + this.X.length + " bytes");
            }
            return false;
        }
        return false;
    }

    @Override // com.a.a.b.a.b
    protected final void W() {
        if (this.W != null) {
            if (this.d.b() || a(l.a.AUTO_CLOSE_SOURCE)) {
                this.W.close();
            }
            this.W = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.b.a.b
    public final void X() {
        byte[] bArr;
        super.X();
        this.P.b();
        if (this.Y && (bArr = this.X) != null && bArr != u) {
            this.X = u;
            this.d.a(bArr);
        }
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final String w() {
        if (this.D == o.VALUE_STRING) {
            if (this.R) {
                this.R = false;
                return as();
            }
            return this.o.e();
        }
        return c(this.D);
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final String R() {
        if (this.D == o.VALUE_STRING) {
            if (this.R) {
                this.R = false;
                return as();
            }
            return this.o.e();
        }
        if (this.D == o.FIELD_NAME) {
            return u();
        }
        return super.a((String) null);
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final String a(String str) {
        if (this.D == o.VALUE_STRING) {
            if (this.R) {
                this.R = false;
                return as();
            }
            return this.o.e();
        }
        if (this.D == o.FIELD_NAME) {
            return u();
        }
        return super.a(str);
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final int P() {
        o oVar = this.D;
        if (oVar == o.VALUE_NUMBER_INT || oVar == o.VALUE_NUMBER_FLOAT) {
            if ((this.s & 1) == 0) {
                if (this.s == 0) {
                    return ab();
                }
                if ((this.s & 1) == 0) {
                    ac();
                }
            }
            return this.t;
        }
        return super.d(0);
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final int d(int i) {
        o oVar = this.D;
        if (oVar == o.VALUE_NUMBER_INT || oVar == o.VALUE_NUMBER_FLOAT) {
            if ((this.s & 1) == 0) {
                if (this.s == 0) {
                    return ab();
                }
                if ((this.s & 1) == 0) {
                    ac();
                }
            }
            return this.t;
        }
        return super.d(i);
    }

    private String c(o oVar) {
        if (oVar == null) {
            return null;
        }
        switch (oVar.a()) {
            case 5:
                return this.m.g();
            case 6:
            case 7:
            case 8:
                return this.o.e();
            default:
                return oVar.b();
        }
    }

    @Override // com.a.a.b.l
    public final char[] x() {
        if (this.D != null) {
            switch (this.D.a()) {
                case 5:
                    if (!this.q) {
                        String g = this.m.g();
                        int length = g.length();
                        if (this.p == null) {
                            this.p = this.d.a(length);
                        } else if (this.p.length < length) {
                            this.p = new char[length];
                        }
                        g.getChars(0, length, this.p, 0);
                        this.q = true;
                    }
                    return this.p;
                case 6:
                    if (this.R) {
                        this.R = false;
                        ah();
                        break;
                    }
                    break;
                case 7:
                case 8:
                    break;
                default:
                    return this.D.c();
            }
            return this.o.d();
        }
        return null;
    }

    @Override // com.a.a.b.l
    public final int y() {
        if (this.D != null) {
            switch (this.D.a()) {
                case 5:
                    return this.m.g().length();
                case 6:
                    if (this.R) {
                        this.R = false;
                        ah();
                        break;
                    }
                    break;
                case 7:
                case 8:
                    break;
                default:
                    return this.D.c().length;
            }
            return this.o.b();
        }
        return 0;
    }

    @Override // com.a.a.b.l
    public final int z() {
        if (this.D != null) {
            switch (this.D.a()) {
                case 5:
                    return 0;
                case 6:
                    if (this.R) {
                        this.R = false;
                        ah();
                        break;
                    }
                    break;
                case 7:
                case 8:
                    break;
                default:
                    return 0;
            }
            return this.o.c();
        }
        return 0;
    }

    @Override // com.a.a.b.a.b, com.a.a.b.l
    public final byte[] a(com.a.a.b.a aVar) {
        if (this.D != o.VALUE_STRING && (this.D != o.VALUE_EMBEDDED_OBJECT || this.r == null)) {
            g("Current token (" + this.D + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this.R) {
            try {
                this.r = c(aVar);
                this.R = false;
            } catch (IllegalArgumentException e) {
                throw b("Failed to decode VALUE_STRING as base64 (" + aVar + "): " + e.getMessage());
            }
        } else if (this.r == null) {
            com.a.a.b.g.c aa = aa();
            a(w(), aa, aVar);
            this.r = aa.b();
        }
        return this.r;
    }

    @Override // com.a.a.b.l
    public final int a(com.a.a.b.a aVar, OutputStream outputStream) {
        if (!this.R || this.D != o.VALUE_STRING) {
            byte[] a2 = a(aVar);
            outputStream.write(a2);
            return a2.length;
        }
        byte[] f = this.d.f();
        try {
            return a(aVar, outputStream, f);
        } finally {
            this.d.b(f);
        }
    }

    private int a(com.a.a.b.a aVar, OutputStream outputStream, byte[] bArr) {
        int i = 0;
        int length = bArr.length - 3;
        int i2 = 0;
        while (true) {
            if (this.e >= this.f) {
                ar();
            }
            byte[] bArr2 = this.X;
            int i3 = this.e;
            this.e = i3 + 1;
            int i4 = bArr2[i3] & 255;
            if (i4 > 32) {
                int b2 = aVar.b(i4);
                int i5 = b2;
                if (b2 < 0) {
                    if (i4 == 34) {
                        break;
                    }
                    int a2 = a(aVar, i4, 0);
                    i5 = a2;
                    if (a2 < 0) {
                        continue;
                    }
                }
                if (i > length) {
                    i2 += i;
                    outputStream.write(bArr, 0, i);
                    i = 0;
                }
                int i6 = i5;
                if (this.e >= this.f) {
                    ar();
                }
                byte[] bArr3 = this.X;
                int i7 = this.e;
                this.e = i7 + 1;
                int i8 = bArr3[i7] & 255;
                int b3 = aVar.b(i8);
                int i9 = b3;
                if (b3 < 0) {
                    i9 = a(aVar, i8, 1);
                }
                int i10 = (i6 << 6) | i9;
                if (this.e >= this.f) {
                    ar();
                }
                byte[] bArr4 = this.X;
                int i11 = this.e;
                this.e = i11 + 1;
                int i12 = bArr4[i11] & 255;
                int b4 = aVar.b(i12);
                int i13 = b4;
                if (b4 < 0) {
                    if (i13 != -2) {
                        if (i12 == 34) {
                            int i14 = i;
                            i++;
                            bArr[i14] = (byte) (i10 >> 4);
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                        } else {
                            i13 = a(aVar, i12, 2);
                        }
                    }
                    if (i13 == -2) {
                        if (this.e >= this.f) {
                            ar();
                        }
                        byte[] bArr5 = this.X;
                        int i15 = this.e;
                        this.e = i15 + 1;
                        int i16 = bArr5[i15] & 255;
                        if (!aVar.a(i16) && a(aVar, i16, 3) != -2) {
                            throw a(aVar, i16, 3, "expected padding character '" + aVar.b() + "'");
                        }
                        int i17 = i;
                        i++;
                        bArr[i17] = (byte) (i10 >> 4);
                    }
                }
                int i18 = (i10 << 6) | i13;
                if (this.e >= this.f) {
                    ar();
                }
                byte[] bArr6 = this.X;
                int i19 = this.e;
                this.e = i19 + 1;
                int i20 = bArr6[i19] & 255;
                int b5 = aVar.b(i20);
                int i21 = b5;
                if (b5 < 0) {
                    if (i21 != -2) {
                        if (i20 == 34) {
                            int i22 = i18 >> 2;
                            int i23 = i;
                            int i24 = i + 1;
                            bArr[i23] = (byte) (i22 >> 8);
                            i = i24 + 1;
                            bArr[i24] = (byte) i22;
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                        } else {
                            i21 = a(aVar, i20, 3);
                        }
                    }
                    if (i21 == -2) {
                        int i25 = i18 >> 2;
                        int i26 = i;
                        int i27 = i + 1;
                        bArr[i26] = (byte) (i25 >> 8);
                        i = i27 + 1;
                        bArr[i27] = (byte) i25;
                    }
                }
                int i28 = (i18 << 6) | i21;
                int i29 = i;
                int i30 = i + 1;
                bArr[i29] = (byte) (i28 >> 16);
                int i31 = i30 + 1;
                bArr[i30] = (byte) (i28 >> 8);
                i = i31 + 1;
                bArr[i31] = (byte) i28;
            }
        }
        this.R = false;
        if (i > 0) {
            i2 += i;
            outputStream.write(bArr, 0, i);
        }
        return i2;
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final o g() {
        o n;
        if (this.D == o.FIELD_NAME) {
            return an();
        }
        this.s = 0;
        if (this.R) {
            at();
        }
        int aA = aA();
        int i = aA;
        if (aA < 0) {
            close();
            this.D = null;
            return null;
        }
        this.r = null;
        if (i == 93) {
            aO();
            o oVar = o.END_ARRAY;
            this.D = oVar;
            return oVar;
        }
        if (i == 125) {
            aP();
            o oVar2 = o.END_OBJECT;
            this.D = oVar2;
            return oVar2;
        }
        if (this.m.l()) {
            if (i != 44) {
                c(i, "was expecting comma to separate " + this.m.e() + " entries");
            }
            i = ay();
            if ((this.f177b & E) != 0 && (i == 93 || i == 125)) {
                return w(i);
            }
        }
        if (!this.m.d()) {
            aM();
            return h(i);
        }
        aN();
        this.m.a(k(i));
        this.D = o.FIELD_NAME;
        int aC = aC();
        aM();
        if (aC == 34) {
            this.R = true;
            this.n = o.VALUE_STRING;
            return this.D;
        }
        switch (aC) {
            case 43:
                if (a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.c())) {
                    n = b(false);
                    break;
                } else {
                    n = n(aC);
                    break;
                }
            case 45:
                n = b(true);
                break;
            case 46:
                n = a(false);
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                n = i(aC);
                break;
            case 91:
                n = o.START_ARRAY;
                break;
            case 102:
                aw();
                n = o.VALUE_FALSE;
                break;
            case 110:
                ax();
                n = o.VALUE_NULL;
                break;
            case 116:
                av();
                n = o.VALUE_TRUE;
                break;
            case 123:
                n = o.START_OBJECT;
                break;
            default:
                n = n(aC);
                break;
        }
        this.n = n;
        return this.D;
    }

    private final o h(int i) {
        if (i == 34) {
            this.R = true;
            o oVar = o.VALUE_STRING;
            this.D = oVar;
            return oVar;
        }
        switch (i) {
            case 43:
                if (!a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.c())) {
                    o n = n(i);
                    this.D = n;
                    return n;
                }
                o b2 = b(false);
                this.D = b2;
                return b2;
            case 45:
                o b3 = b(true);
                this.D = b3;
                return b3;
            case 46:
                o a2 = a(false);
                this.D = a2;
                return a2;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                o i2 = i(i);
                this.D = i2;
                return i2;
            case 91:
                this.m = this.m.a(this.k, this.l);
                o oVar2 = o.START_ARRAY;
                this.D = oVar2;
                return oVar2;
            case 102:
                aw();
                o oVar3 = o.VALUE_FALSE;
                this.D = oVar3;
                return oVar3;
            case 110:
                ax();
                o oVar4 = o.VALUE_NULL;
                this.D = oVar4;
                return oVar4;
            case 116:
                av();
                o oVar5 = o.VALUE_TRUE;
                this.D = oVar5;
                return oVar5;
            case 123:
                this.m = this.m.b(this.k, this.l);
                o oVar6 = o.START_OBJECT;
                this.D = oVar6;
                return oVar6;
            default:
                o n2 = n(i);
                this.D = n2;
                return n2;
        }
    }

    private final o an() {
        this.q = false;
        o oVar = this.n;
        this.n = null;
        if (oVar == o.START_ARRAY) {
            this.m = this.m.a(this.k, this.l);
        } else if (oVar == o.START_OBJECT) {
            this.m = this.m.b(this.k, this.l);
        }
        this.D = oVar;
        return oVar;
    }

    @Override // com.a.a.b.l
    public final String h() {
        o n;
        this.s = 0;
        if (this.D == o.FIELD_NAME) {
            an();
            return null;
        }
        if (this.R) {
            at();
        }
        int aA = aA();
        int i = aA;
        if (aA < 0) {
            close();
            this.D = null;
            return null;
        }
        this.r = null;
        if (i == 93) {
            aO();
            this.D = o.END_ARRAY;
            return null;
        }
        if (i == 125) {
            aP();
            this.D = o.END_OBJECT;
            return null;
        }
        if (this.m.l()) {
            if (i != 44) {
                c(i, "was expecting comma to separate " + this.m.e() + " entries");
            }
            i = ay();
            if ((this.f177b & E) != 0 && (i == 93 || i == 125)) {
                w(i);
                return null;
            }
        }
        if (!this.m.d()) {
            aM();
            h(i);
            return null;
        }
        aN();
        String k = k(i);
        this.m.a(k);
        this.D = o.FIELD_NAME;
        int aC = aC();
        aM();
        if (aC == 34) {
            this.R = true;
            this.n = o.VALUE_STRING;
            return k;
        }
        switch (aC) {
            case 43:
                if (a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.c())) {
                    n = b(false);
                    break;
                } else {
                    n = n(aC);
                    break;
                }
            case 45:
                n = b(true);
                break;
            case 46:
                n = a(false);
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                n = i(aC);
                break;
            case 91:
                n = o.START_ARRAY;
                break;
            case 102:
                aw();
                n = o.VALUE_FALSE;
                break;
            case 110:
                ax();
                n = o.VALUE_NULL;
                break;
            case 116:
                av();
                n = o.VALUE_TRUE;
                break;
            case 123:
                n = o.START_OBJECT;
                break;
            default:
                n = n(aC);
                break;
        }
        this.n = n;
        return k;
    }

    @Override // com.a.a.b.l
    public final String i() {
        if (this.D == o.FIELD_NAME) {
            this.q = false;
            o oVar = this.n;
            this.n = null;
            this.D = oVar;
            if (oVar == o.VALUE_STRING) {
                if (this.R) {
                    this.R = false;
                    return as();
                }
                return this.o.e();
            }
            if (oVar == o.START_ARRAY) {
                this.m = this.m.a(this.k, this.l);
                return null;
            }
            if (oVar == o.START_OBJECT) {
                this.m = this.m.b(this.k, this.l);
                return null;
            }
            return null;
        }
        if (g() == o.VALUE_STRING) {
            return w();
        }
        return null;
    }

    @Override // com.a.a.b.l
    public final int b(int i) {
        if (this.D != o.FIELD_NAME) {
            return g() == o.VALUE_NUMBER_INT ? G() : i;
        }
        this.q = false;
        o oVar = this.n;
        this.n = null;
        this.D = oVar;
        if (oVar == o.VALUE_NUMBER_INT) {
            return G();
        }
        if (oVar == o.START_ARRAY) {
            this.m = this.m.a(this.k, this.l);
        } else if (oVar == o.START_OBJECT) {
            this.m = this.m.b(this.k, this.l);
        }
        return i;
    }

    private o a(boolean z) {
        if (!a(e.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.c())) {
            return n(46);
        }
        char[] h = this.o.h();
        int i = 0;
        if (z) {
            i = 0 + 1;
            h[0] = '-';
        }
        return a(h, i, 46, z, 0);
    }

    private o i(int i) {
        char[] h = this.o.h();
        if (i == 48) {
            i = ao();
        }
        h[0] = (char) i;
        int i2 = 1;
        int i3 = 1;
        int min = Math.min(this.f, (this.e + h.length) - 1);
        while (this.e < min) {
            byte[] bArr = this.X;
            int i4 = this.e;
            this.e = i4 + 1;
            int i5 = bArr[i4] & 255;
            if (i5 >= 48 && i5 <= 57) {
                i2++;
                int i6 = i3;
                i3++;
                h[i6] = (char) i5;
            } else {
                if (i5 == 46 || i5 == 101 || i5 == 69) {
                    return a(h, i3, i5, false, i2);
                }
                this.e--;
                this.o.a(i3);
                if (this.m.c()) {
                    j(i5);
                }
                return a(false, i2);
            }
        }
        return a(h, i3, false, i2);
    }

    private final o b(boolean z) {
        char[] h = this.o.h();
        int i = 0;
        if (z) {
            i = 0 + 1;
            h[0] = '-';
        }
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr = this.X;
        int i2 = this.e;
        this.e = i2 + 1;
        int i3 = bArr[i2] & 255;
        int i4 = i3;
        if (i3 <= 48) {
            if (i4 != 48) {
                if (i4 == 46) {
                    return a(z);
                }
                return a(i4, z, true);
            }
            i4 = ao();
        } else if (i4 > 57) {
            return a(i4, z, true);
        }
        int i5 = i;
        int i6 = i + 1;
        h[i5] = (char) i4;
        int i7 = 1;
        int min = Math.min(this.f, (this.e + h.length) - i6);
        while (this.e < min) {
            byte[] bArr2 = this.X;
            int i8 = this.e;
            this.e = i8 + 1;
            int i9 = bArr2[i8] & 255;
            if (i9 >= 48 && i9 <= 57) {
                i7++;
                int i10 = i6;
                i6++;
                h[i10] = (char) i9;
            } else {
                if (i9 == 46 || i9 == 101 || i9 == 69) {
                    return a(h, i6, i9, z, i7);
                }
                this.e--;
                this.o.a(i6);
                if (this.m.c()) {
                    j(i9);
                }
                return a(z, i7);
            }
        }
        return a(h, i6, z, i7);
    }

    private final o a(char[] cArr, int i, boolean z, int i2) {
        int i3;
        while (true) {
            if (this.e >= this.f && !am()) {
                this.o.a(i);
                return a(z, i2);
            }
            byte[] bArr = this.X;
            int i4 = this.e;
            this.e = i4 + 1;
            i3 = bArr[i4] & 255;
            if (i3 > 57 || i3 < 48) {
                break;
            }
            if (i >= cArr.length) {
                cArr = this.o.j();
                i = 0;
            }
            int i5 = i;
            i++;
            cArr[i5] = (char) i3;
            i2++;
        }
        if (i3 == 46 || i3 == 101 || i3 == 69) {
            return a(cArr, i, i3, z, i2);
        }
        this.e--;
        this.o.a(i);
        if (this.m.c()) {
            j(this.X[this.e] & 255);
        }
        return a(z, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0050, code lost:            if (r5 == 48) goto L19;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005b, code lost:            if (r4.e < r4.f) goto L23;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0062, code lost:            if (am() == false) goto L33;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0065, code lost:            r0 = r4.X[r4.e] & 255;        r5 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0076, code lost:            if (r0 < 48) goto L34;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007c, code lost:            if (r5 <= 57) goto L29;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0082, code lost:            r4.e++;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008f, code lost:            if (r5 == 48) goto L37;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:            return 48;     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007f, code lost:            return 48;     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0093, code lost:            return r5;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int ao() {
        /*
            r4 = this;
            r0 = r4
            int r0 = r0.e
            r1 = r4
            int r1 = r1.f
            if (r0 < r1) goto L15
            r0 = r4
            boolean r0 = r0.am()
            if (r0 != 0) goto L15
            r0 = 48
            return r0
        L15:
            r0 = r4
            byte[] r0 = r0.X
            r1 = r4
            int r1 = r1.e
            r0 = r0[r1]
            r1 = 255(0xff, float:3.57E-43)
            r0 = r0 & r1
            r1 = r0
            r5 = r1
            r1 = 48
            if (r0 < r1) goto L2f
            r0 = r5
            r1 = 57
            if (r0 <= r1) goto L32
        L2f:
            r0 = 48
            return r0
        L32:
            r0 = r4
            int r0 = r0.f177b
            int r1 = com.a.a.b.d.h.F
            r0 = r0 & r1
            if (r0 != 0) goto L43
            r0 = r4
            java.lang.String r1 = "Leading zeroes not allowed"
            r0.c(r1)
        L43:
            r0 = r4
            r1 = r0
            int r1 = r1.e
            r2 = 1
            int r1 = r1 + r2
            r0.e = r1
            r0 = r5
            r1 = 48
            if (r0 != r1) goto L92
        L53:
            r0 = r4
            int r0 = r0.e
            r1 = r4
            int r1 = r1.f
            if (r0 < r1) goto L65
            r0 = r4
            boolean r0 = r0.am()
            if (r0 == 0) goto L92
        L65:
            r0 = r4
            byte[] r0 = r0.X
            r1 = r4
            int r1 = r1.e
            r0 = r0[r1]
            r1 = 255(0xff, float:3.57E-43)
            r0 = r0 & r1
            r1 = r0
            r5 = r1
            r1 = 48
            if (r0 < r1) goto L7f
            r0 = r5
            r1 = 57
            if (r0 <= r1) goto L82
        L7f:
            r0 = 48
            return r0
        L82:
            r0 = r4
            r1 = r0
            int r1 = r1.e
            r2 = 1
            int r1 = r1 + r2
            r0.e = r1
            r0 = r5
            r1 = 48
            if (r0 == r1) goto L53
        L92:
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.h.ao():int");
    }

    private final o a(char[] cArr, int i, int i2, boolean z, int i3) {
        int i4 = 0;
        boolean z2 = false;
        if (i2 == 46) {
            if (i >= cArr.length) {
                cArr = this.o.j();
                i = 0;
            }
            int i5 = i;
            i++;
            cArr[i5] = (char) i2;
            while (true) {
                if (this.e >= this.f && !am()) {
                    z2 = true;
                    break;
                }
                byte[] bArr = this.X;
                int i6 = this.e;
                this.e = i6 + 1;
                int i7 = bArr[i6] & 255;
                i2 = i7;
                if (i7 < 48 || i2 > 57) {
                    break;
                }
                i4++;
                if (i >= cArr.length) {
                    cArr = this.o.j();
                    i = 0;
                }
                int i8 = i;
                i++;
                cArr[i8] = (char) i2;
            }
            if (i4 == 0 && !a(e.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.c())) {
                b(i2, "Decimal point not followed by a digit");
            }
        }
        int i9 = 0;
        if (i2 == 101 || i2 == 69) {
            if (i >= cArr.length) {
                cArr = this.o.j();
                i = 0;
            }
            int i10 = i;
            i++;
            cArr[i10] = (char) i2;
            if (this.e >= this.f) {
                ar();
            }
            byte[] bArr2 = this.X;
            int i11 = this.e;
            this.e = i11 + 1;
            int i12 = bArr2[i11] & 255;
            i2 = i12;
            if (i12 == 45 || i2 == 43) {
                if (i >= cArr.length) {
                    cArr = this.o.j();
                    i = 0;
                }
                int i13 = i;
                i++;
                cArr[i13] = (char) i2;
                if (this.e >= this.f) {
                    ar();
                }
                byte[] bArr3 = this.X;
                int i14 = this.e;
                this.e = i14 + 1;
                i2 = bArr3[i14] & 255;
            }
            while (true) {
                if (i2 < 48 || i2 > 57) {
                    break;
                }
                i9++;
                if (i >= cArr.length) {
                    cArr = this.o.j();
                    i = 0;
                }
                int i15 = i;
                i++;
                cArr[i15] = (char) i2;
                if (this.e >= this.f && !am()) {
                    z2 = true;
                    break;
                }
                byte[] bArr4 = this.X;
                int i16 = this.e;
                this.e = i16 + 1;
                i2 = bArr4[i16] & 255;
            }
            if (i9 == 0) {
                b(i2, "Exponent indicator not followed by a digit");
            }
        }
        if (!z2) {
            this.e--;
            if (this.m.c()) {
                j(i2);
            }
        }
        this.o.a(i);
        return a(z, i3, i4, i9);
    }

    private final void j(int i) {
        this.e++;
        switch (i) {
            case 9:
            case 32:
                return;
            case 10:
                this.h++;
                this.i = this.e;
                return;
            case 13:
                this.e--;
                return;
            default:
                e(i);
                return;
        }
    }

    private String k(int i) {
        if (i != 34) {
            return m(i);
        }
        if (this.e + 13 > this.f) {
            return ap();
        }
        byte[] bArr = this.X;
        int[] iArr = N;
        int i2 = this.e;
        this.e = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] == 0) {
            int i4 = this.e;
            this.e = i4 + 1;
            int i5 = bArr[i4] & 255;
            if (iArr[i5] == 0) {
                int i6 = (i3 << 8) | i5;
                int i7 = this.e;
                this.e = i7 + 1;
                int i8 = bArr[i7] & 255;
                if (iArr[i8] == 0) {
                    int i9 = (i6 << 8) | i8;
                    int i10 = this.e;
                    this.e = i10 + 1;
                    int i11 = bArr[i10] & 255;
                    if (iArr[i11] == 0) {
                        int i12 = (i9 << 8) | i11;
                        int i13 = this.e;
                        this.e = i13 + 1;
                        int i14 = bArr[i13] & 255;
                        if (iArr[i14] == 0) {
                            this.S = i12;
                            return l(i14);
                        }
                        if (i14 == 34) {
                            return c(i12, 4);
                        }
                        return b(i12, i14, 4);
                    }
                    if (i11 == 34) {
                        return c(i9, 3);
                    }
                    return b(i9, i11, 3);
                }
                if (i8 == 34) {
                    return c(i6, 2);
                }
                return b(i6, i8, 2);
            }
            if (i5 == 34) {
                return c(i3, 1);
            }
            return b(i3, i5, 1);
        }
        if (i3 == 34) {
            return "";
        }
        return b(0, i3, 0);
    }

    private String l(int i) {
        byte[] bArr = this.X;
        int[] iArr = N;
        int i2 = this.e;
        this.e = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return c(this.S, i, 1);
            }
            return a(this.S, i, i3, 1);
        }
        int i4 = (i << 8) | i3;
        int i5 = this.e;
        this.e = i5 + 1;
        int i6 = bArr[i5] & 255;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                return c(this.S, i4, 2);
            }
            return a(this.S, i4, i6, 2);
        }
        int i7 = (i4 << 8) | i6;
        int i8 = this.e;
        this.e = i8 + 1;
        int i9 = bArr[i8] & 255;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                return c(this.S, i7, 3);
            }
            return a(this.S, i7, i9, 3);
        }
        int i10 = (i7 << 8) | i9;
        int i11 = this.e;
        this.e = i11 + 1;
        int i12 = bArr[i11] & 255;
        if (iArr[i12] != 0) {
            if (i12 == 34) {
                return c(this.S, i10, 4);
            }
            return a(this.S, i10, i12, 4);
        }
        return b(i12, i10);
    }

    private String b(int i, int i2) {
        byte[] bArr = this.X;
        int[] iArr = N;
        int i3 = this.e;
        this.e = i3 + 1;
        int i4 = bArr[i3] & 255;
        if (iArr[i4] != 0) {
            if (i4 == 34) {
                return b(this.S, i2, i, 1);
            }
            return a(this.S, i2, i, i4, 1);
        }
        int i5 = (i << 8) | i4;
        int i6 = this.e;
        this.e = i6 + 1;
        int i7 = bArr[i6] & 255;
        if (iArr[i7] != 0) {
            if (i7 == 34) {
                return b(this.S, i2, i5, 2);
            }
            return a(this.S, i2, i5, i7, 2);
        }
        int i8 = (i5 << 8) | i7;
        int i9 = this.e;
        this.e = i9 + 1;
        int i10 = bArr[i9] & 255;
        if (iArr[i10] != 0) {
            if (i10 == 34) {
                return b(this.S, i2, i8, 3);
            }
            return a(this.S, i2, i8, i10, 3);
        }
        int i11 = (i8 << 8) | i10;
        int i12 = this.e;
        this.e = i12 + 1;
        int i13 = bArr[i12] & 255;
        if (iArr[i13] != 0) {
            if (i13 == 34) {
                return b(this.S, i2, i11, 4);
            }
            return a(this.S, i2, i11, i13, 4);
        }
        return a(i13, i2, i11);
    }

    private String a(int i, int i2, int i3) {
        this.Q[0] = this.S;
        this.Q[1] = i2;
        this.Q[2] = i3;
        byte[] bArr = this.X;
        int[] iArr = N;
        int i4 = 3;
        while (this.e + 4 <= this.f) {
            int i5 = this.e;
            this.e = i5 + 1;
            int i6 = bArr[i5] & 255;
            if (iArr[i6] != 0) {
                if (i6 == 34) {
                    return a(this.Q, i4, i, 1);
                }
                return a(this.Q, i4, i, i6, 1);
            }
            int i7 = (i << 8) | i6;
            int i8 = this.e;
            this.e = i8 + 1;
            int i9 = bArr[i8] & 255;
            if (iArr[i9] != 0) {
                if (i9 == 34) {
                    return a(this.Q, i4, i7, 2);
                }
                return a(this.Q, i4, i7, i9, 2);
            }
            int i10 = (i7 << 8) | i9;
            int i11 = this.e;
            this.e = i11 + 1;
            int i12 = bArr[i11] & 255;
            if (iArr[i12] != 0) {
                if (i12 == 34) {
                    return a(this.Q, i4, i10, 3);
                }
                return a(this.Q, i4, i10, i12, 3);
            }
            int i13 = (i10 << 8) | i12;
            int i14 = this.e;
            this.e = i14 + 1;
            int i15 = bArr[i14] & 255;
            if (iArr[i15] != 0) {
                if (i15 == 34) {
                    return a(this.Q, i4, i13, 4);
                }
                return a(this.Q, i4, i13, i15, 4);
            }
            if (i4 >= this.Q.length) {
                this.Q = a(this.Q, i4);
            }
            int i16 = i4;
            i4++;
            this.Q[i16] = i13;
            i = i15;
        }
        return a(this.Q, i4, 0, i, 0);
    }

    private String ap() {
        if (this.e >= this.f && !am()) {
            b(": was expecting closing '\"' for name", o.FIELD_NAME);
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 34) {
            return "";
        }
        return a(this.Q, 0, 0, i2, 0);
    }

    private final String b(int i, int i2, int i3) {
        return a(this.Q, 0, i, i2, i3);
    }

    private final String a(int i, int i2, int i3, int i4) {
        this.Q[0] = i;
        return a(this.Q, 1, i2, i3, i4);
    }

    private final String a(int i, int i2, int i3, int i4, int i5) {
        this.Q[0] = i;
        this.Q[1] = i2;
        return a(this.Q, 2, i3, i4, i5);
    }

    private String a(int[] iArr, int i, int i2, int i3, int i4) {
        int[] iArr2 = N;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    a(i3, Attribute.NAME_ATTR);
                } else {
                    i3 = af();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            int[] iArr3 = iArr;
                            int[] a2 = a(iArr3, iArr3.length);
                            iArr = a2;
                            this.Q = a2;
                        }
                        int i5 = i;
                        i++;
                        iArr[i5] = i2;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | 192 | (i3 >> 6);
                        i4++;
                    } else {
                        int i6 = (i2 << 8) | 224 | (i3 >> 12);
                        int i7 = i4 + 1;
                        if (i7 >= 4) {
                            if (i >= iArr.length) {
                                int[] iArr4 = iArr;
                                int[] a3 = a(iArr4, iArr4.length);
                                iArr = a3;
                                this.Q = a3;
                            }
                            int i8 = i;
                            i++;
                            iArr[i8] = i6;
                            i6 = 0;
                            i7 = 0;
                        }
                        i2 = (i6 << 8) | 128 | ((i3 >> 6) & 63);
                        i4 = i7 + 1;
                    }
                    i3 = 128 | (i3 & 63);
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    int[] iArr5 = iArr;
                    int[] a4 = a(iArr5, iArr5.length);
                    iArr = a4;
                    this.Q = a4;
                }
                int i9 = i;
                i++;
                iArr[i9] = i2;
                i2 = i3;
                i4 = 1;
            }
            if (this.e >= this.f && !am()) {
                b(" in field name", o.FIELD_NAME);
            }
            byte[] bArr = this.X;
            int i10 = this.e;
            this.e = i10 + 1;
            i3 = bArr[i10] & 255;
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                int[] iArr6 = iArr;
                int[] a5 = a(iArr6, iArr6.length);
                iArr = a5;
                this.Q = a5;
            }
            int i11 = i;
            i++;
            iArr[i11] = d(i2, i4);
        }
        String a6 = this.P.a(iArr, i);
        String str = a6;
        if (a6 == null) {
            str = a(iArr, i, i4);
        }
        return str;
    }

    private String m(int i) {
        if (i == 39 && (this.f177b & I) != 0) {
            return aq();
        }
        if ((this.f177b & J) == 0) {
            c((char) o(i), "was expecting double-quote to start field name");
        }
        int[] d = com.a.a.b.c.b.d();
        if (d[i] != 0) {
            c(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArr = this.Q;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 < 4) {
                i4++;
                i3 = (i3 << 8) | i;
            } else {
                if (i2 >= iArr.length) {
                    int[] iArr2 = iArr;
                    int[] a2 = a(iArr2, iArr2.length);
                    iArr = a2;
                    this.Q = a2;
                }
                int i5 = i2;
                i2++;
                iArr[i5] = i3;
                i3 = i;
                i4 = 1;
            }
            if (this.e >= this.f && !am()) {
                b(" in field name", o.FIELD_NAME);
            }
            i = this.X[this.e] & 255;
            if (d[i] != 0) {
                break;
            }
            this.e++;
        }
        if (i4 > 0) {
            if (i2 >= iArr.length) {
                int[] iArr3 = iArr;
                int[] a3 = a(iArr3, iArr3.length);
                iArr = a3;
                this.Q = a3;
            }
            int i6 = i2;
            i2++;
            iArr[i6] = i3;
        }
        String a4 = this.P.a(iArr, i2);
        String str = a4;
        if (a4 == null) {
            str = a(iArr, i2, i4);
        }
        return str;
    }

    private String aq() {
        if (this.e >= this.f && !am()) {
            b(": was expecting closing ''' for field name", o.FIELD_NAME);
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        int i2 = bArr[i] & 255;
        int i3 = i2;
        if (i2 == 39) {
            return "";
        }
        int[] iArr = this.Q;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int[] iArr2 = N;
        while (i3 != 39) {
            if (iArr2[i3] != 0 && i3 != 34) {
                if (i3 != 92) {
                    a(i3, Attribute.NAME_ATTR);
                } else {
                    i3 = af();
                }
                if (i3 > 127) {
                    if (i6 >= 4) {
                        if (i4 >= iArr.length) {
                            int[] iArr3 = iArr;
                            int[] a2 = a(iArr3, iArr3.length);
                            iArr = a2;
                            this.Q = a2;
                        }
                        int i7 = i4;
                        i4++;
                        iArr[i7] = i5;
                        i5 = 0;
                        i6 = 0;
                    }
                    if (i3 < 2048) {
                        i5 = (i5 << 8) | 192 | (i3 >> 6);
                        i6++;
                    } else {
                        int i8 = (i5 << 8) | 224 | (i3 >> 12);
                        int i9 = i6 + 1;
                        if (i9 >= 4) {
                            if (i4 >= iArr.length) {
                                int[] iArr4 = iArr;
                                int[] a3 = a(iArr4, iArr4.length);
                                iArr = a3;
                                this.Q = a3;
                            }
                            int i10 = i4;
                            i4++;
                            iArr[i10] = i8;
                            i8 = 0;
                            i9 = 0;
                        }
                        i5 = (i8 << 8) | 128 | ((i3 >> 6) & 63);
                        i6 = i9 + 1;
                    }
                    i3 = 128 | (i3 & 63);
                }
            }
            if (i6 < 4) {
                i6++;
                i5 = (i5 << 8) | i3;
            } else {
                if (i4 >= iArr.length) {
                    int[] iArr5 = iArr;
                    int[] a4 = a(iArr5, iArr5.length);
                    iArr = a4;
                    this.Q = a4;
                }
                int i11 = i4;
                i4++;
                iArr[i11] = i5;
                i5 = i3;
                i6 = 1;
            }
            if (this.e >= this.f && !am()) {
                b(" in field name", o.FIELD_NAME);
            }
            byte[] bArr2 = this.X;
            int i12 = this.e;
            this.e = i12 + 1;
            i3 = bArr2[i12] & 255;
        }
        if (i6 > 0) {
            if (i4 >= iArr.length) {
                int[] iArr6 = iArr;
                int[] a5 = a(iArr6, iArr6.length);
                iArr = a5;
                this.Q = a5;
            }
            int i13 = i4;
            i4++;
            iArr[i13] = d(i5, i6);
        }
        String a6 = this.P.a(iArr, i4);
        String str = a6;
        if (a6 == null) {
            str = a(iArr, i4, i6);
        }
        return str;
    }

    private final String c(int i, int i2) {
        int d = d(i, i2);
        String b2 = this.P.b(d);
        if (b2 != null) {
            return b2;
        }
        this.Q[0] = d;
        return a(this.Q, 1, i2);
    }

    private final String c(int i, int i2, int i3) {
        int d = d(i2, i3);
        String a2 = this.P.a(i, d);
        if (a2 != null) {
            return a2;
        }
        this.Q[0] = i;
        this.Q[1] = d;
        return a(this.Q, 2, i3);
    }

    private final String b(int i, int i2, int i3, int i4) {
        int d = d(i3, i4);
        String a2 = this.P.a(i, i2, d);
        if (a2 != null) {
            return a2;
        }
        int[] iArr = this.Q;
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = d(d, i4);
        return a(iArr, 3, i4);
    }

    private final String a(int[] iArr, int i, int i2, int i3) {
        if (i >= iArr.length) {
            int[] a2 = a(iArr, iArr.length);
            iArr = a2;
            this.Q = a2;
        }
        int i4 = i + 1;
        iArr[i] = d(i2, i3);
        String a3 = this.P.a(iArr, i4);
        if (a3 == null) {
            return a(iArr, i4, i3);
        }
        return a3;
    }

    private final String a(int[] iArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            i3 = iArr[i - 1];
            iArr[i - 1] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] h = this.o.h();
        int i7 = 0;
        int i8 = 0;
        while (i8 < i6) {
            int i9 = (iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3)) & 255;
            i8++;
            if (i9 > 127) {
                if ((i9 & CGL.kCGLCPDispatchTableSize) == 192) {
                    i4 = i9 & 31;
                    i5 = 1;
                } else if ((i9 & User32.VK_OEM_ATTN) == 224) {
                    i4 = i9 & 15;
                    i5 = 2;
                } else if ((i9 & User32.VK_EXSEL) == 240) {
                    i4 = i9 & 7;
                    i5 = 3;
                } else {
                    u(i9);
                    i4 = 1;
                    i5 = 1;
                }
                if (i8 + i5 > i6) {
                    b(" in field name", o.FIELD_NAME);
                }
                int i10 = iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3);
                i8++;
                if ((i10 & 192) != 128) {
                    v(i10);
                }
                i9 = (i4 << 6) | (i10 & 63);
                if (i5 > 1) {
                    int i11 = iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3);
                    i8++;
                    if ((i11 & 192) != 128) {
                        v(i11);
                    }
                    i9 = (i9 << 6) | (i11 & 63);
                    if (i5 > 2) {
                        int i12 = iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3);
                        i8++;
                        if ((i12 & 192) != 128) {
                            v(i12 & 255);
                        }
                        i9 = (i9 << 6) | (i12 & 63);
                    }
                }
                if (i5 > 2) {
                    int i13 = i9 - 65536;
                    if (i7 >= h.length) {
                        h = this.o.k();
                    }
                    int i14 = i7;
                    i7++;
                    h[i14] = (char) (55296 + (i13 >> 10));
                    i9 = 56320 | (i13 & 1023);
                }
            }
            if (i7 >= h.length) {
                h = this.o.k();
            }
            int i15 = i7;
            i7++;
            h[i15] = (char) i9;
        }
        String str = new String(h, 0, i7);
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        return this.P.a(str, iArr, i);
    }

    private static final int d(int i, int i2) {
        return i2 == 4 ? i : i | ((-1) << (i2 << 3));
    }

    private void ar() {
        if (!am()) {
            ak();
        }
    }

    @Override // com.a.a.b.a.b
    protected final void ah() {
        int i = this.e;
        int i2 = i;
        if (i >= this.f) {
            ar();
            i2 = this.e;
        }
        int i3 = 0;
        char[] h = this.o.h();
        int[] iArr = M;
        int min = Math.min(this.f, i2 + h.length);
        byte[] bArr = this.X;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int i4 = bArr[i2] & 255;
            if (iArr[i4] != 0) {
                if (i4 == 34) {
                    this.e = i2 + 1;
                    this.o.a(i3);
                    return;
                }
            } else {
                i2++;
                int i5 = i3;
                i3++;
                h[i5] = (char) i4;
            }
        }
        this.e = i2;
        a(h, i3);
    }

    private String as() {
        int i = this.e;
        int i2 = i;
        if (i >= this.f) {
            ar();
            i2 = this.e;
        }
        int i3 = 0;
        char[] h = this.o.h();
        int[] iArr = M;
        int min = Math.min(this.f, i2 + h.length);
        byte[] bArr = this.X;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int i4 = bArr[i2] & 255;
            if (iArr[i4] != 0) {
                if (i4 == 34) {
                    this.e = i2 + 1;
                    return this.o.b(i3);
                }
            } else {
                i2++;
                int i5 = i3;
                i3++;
                h[i5] = (char) i4;
            }
        }
        this.e = i2;
        a(h, i3);
        return this.o.e();
    }

    private final void a(char[] cArr, int i) {
        int[] iArr = M;
        byte[] bArr = this.X;
        while (true) {
            int i2 = this.e;
            int i3 = i2;
            if (i2 >= this.f) {
                ar();
                i3 = this.e;
            }
            if (i >= cArr.length) {
                cArr = this.o.j();
                i = 0;
            }
            int min = Math.min(this.f, i3 + (cArr.length - i));
            while (true) {
                if (i3 < min) {
                    int i4 = i3;
                    i3++;
                    char c = (bArr[i4] & 255) == true ? 1 : 0;
                    if (iArr[c] != 0) {
                        this.e = i3;
                        if (c != '\"') {
                            switch (iArr[c]) {
                                case 1:
                                    c = af();
                                    break;
                                case 2:
                                    c = p(c);
                                    break;
                                case 3:
                                    if (this.f - this.e >= 2) {
                                        c = r(c);
                                        break;
                                    } else {
                                        c = q(c);
                                        break;
                                    }
                                case 4:
                                    int s = s(c);
                                    int i5 = i;
                                    i++;
                                    cArr[i5] = (char) (55296 | (s >> 10));
                                    if (i >= cArr.length) {
                                        cArr = this.o.j();
                                        i = 0;
                                    }
                                    c = 56320 | (s & 1023);
                                    break;
                                default:
                                    if (c < ' ') {
                                        a(c, "string value");
                                        break;
                                    } else {
                                        t(c);
                                        break;
                                    }
                            }
                            if (i >= cArr.length) {
                                cArr = this.o.j();
                                i = 0;
                            }
                            int i6 = i;
                            i++;
                            cArr[i6] = c;
                        } else {
                            this.o.a(i);
                            return;
                        }
                    } else {
                        int i7 = i;
                        i++;
                        cArr[i7] = c;
                    }
                } else {
                    this.e = i3;
                }
            }
        }
    }

    private void at() {
        this.R = false;
        int[] iArr = M;
        byte[] bArr = this.X;
        while (true) {
            int i = this.e;
            int i2 = this.f;
            if (i >= i2) {
                ar();
                i = this.e;
                i2 = this.f;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i;
                    i++;
                    int i4 = bArr[i3] & 255;
                    if (iArr[i4] != 0) {
                        this.e = i;
                        if (i4 != 34) {
                            switch (iArr[i4]) {
                                case 1:
                                    af();
                                    break;
                                case 2:
                                    aH();
                                    break;
                                case 3:
                                    aI();
                                    break;
                                case 4:
                                    aJ();
                                    break;
                                default:
                                    if (i4 < 32) {
                                        a(i4, "string value");
                                        break;
                                    } else {
                                        t(i4);
                                        break;
                                    }
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    this.e = i;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x004b, code lost:            if (r7.m.b() != false) goto L6;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0001. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x010d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.a.a.b.o n(int r8) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.h.n(int):com.a.a.b.o");
    }

    private o au() {
        int i = 0;
        char[] h = this.o.h();
        int[] iArr = M;
        byte[] bArr = this.X;
        while (true) {
            if (this.e >= this.f) {
                ar();
            }
            if (i >= h.length) {
                h = this.o.j();
                i = 0;
            }
            int i2 = this.f;
            int length = this.e + (h.length - i);
            if (length < i2) {
                i2 = length;
            }
            while (true) {
                if (this.e < i2) {
                    int i3 = this.e;
                    this.e = i3 + 1;
                    int i4 = bArr[i3] & 255;
                    char c = i4 == true ? 1 : 0;
                    if (i4 != 39) {
                        if (iArr[c] == 0 || c == '\"') {
                            int i5 = i;
                            i++;
                            h[i5] = c;
                        } else {
                            switch (iArr[c]) {
                                case 1:
                                    c = af();
                                    break;
                                case 2:
                                    c = p(c);
                                    break;
                                case 3:
                                    if (this.f - this.e >= 2) {
                                        c = r(c);
                                        break;
                                    } else {
                                        c = q(c);
                                        break;
                                    }
                                case 4:
                                    int s = s(c);
                                    int i6 = i;
                                    i++;
                                    h[i6] = (char) (55296 | (s >> 10));
                                    if (i >= h.length) {
                                        h = this.o.j();
                                        i = 0;
                                    }
                                    c = 56320 | (s & 1023);
                                    break;
                                default:
                                    if (c < ' ') {
                                        a(c, "string value");
                                    }
                                    t(c);
                                    break;
                            }
                            if (i >= h.length) {
                                h = this.o.j();
                                i = 0;
                            }
                            int i7 = i;
                            i++;
                            h[i7] = c;
                        }
                    } else {
                        this.o.a(i);
                        return o.VALUE_STRING;
                    }
                }
            }
        }
    }

    private o a(int i, boolean z, boolean z2) {
        String str;
        while (i == 73) {
            if (this.e >= this.f && !am()) {
                b(o.VALUE_NUMBER_FLOAT);
            }
            byte[] bArr = this.X;
            int i2 = this.e;
            this.e = i2 + 1;
            byte b2 = bArr[i2];
            i = b2;
            if (b2 == 78) {
                str = z ? "-INF" : "+INF";
            } else {
                if (i != 110) {
                    break;
                }
                str = z ? "-Infinity" : "+Infinity";
            }
            a(str, 3);
            if ((this.f177b & G) != 0) {
                return a(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            a("Non-standard token '%s': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow", (Object) str);
        }
        if (!a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.c()) && !z) {
            b(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
        }
        b(i, z ? "expected digit (0-9) to follow minus sign, for valid numeric value" : "expected digit (0-9) for valid numeric value");
        return null;
    }

    private void av() {
        int i;
        int i2 = this.e;
        if (i2 + 3 < this.f) {
            byte[] bArr = this.X;
            int i3 = i2 + 1;
            if (bArr[i2] == 114) {
                int i4 = i3 + 1;
                if (bArr[i3] == 117) {
                    int i5 = i4 + 1;
                    if (bArr[i4] == 101 && ((i = bArr[i5] & 255) < 48 || i == 93 || i == 125)) {
                        this.e = i5;
                        return;
                    }
                }
            }
        }
        b("true", 1);
    }

    private void aw() {
        int i;
        int i2 = this.e;
        if (i2 + 4 < this.f) {
            byte[] bArr = this.X;
            int i3 = i2 + 1;
            if (bArr[i2] == 97) {
                int i4 = i3 + 1;
                if (bArr[i3] == 108) {
                    int i5 = i4 + 1;
                    if (bArr[i4] == 115) {
                        int i6 = i5 + 1;
                        if (bArr[i5] == 101 && ((i = bArr[i6] & 255) < 48 || i == 93 || i == 125)) {
                            this.e = i6;
                            return;
                        }
                    }
                }
            }
        }
        b("false", 1);
    }

    private void ax() {
        int i;
        int i2 = this.e;
        if (i2 + 3 < this.f) {
            byte[] bArr = this.X;
            int i3 = i2 + 1;
            if (bArr[i2] == 117) {
                int i4 = i3 + 1;
                if (bArr[i3] == 108) {
                    int i5 = i4 + 1;
                    if (bArr[i4] == 108 && ((i = bArr[i5] & 255) < 48 || i == 93 || i == 125)) {
                        this.e = i5;
                        return;
                    }
                }
            }
        }
        b("null", 1);
    }

    private void a(String str, int i) {
        int length = str.length();
        if (this.e + length >= this.f) {
            b(str, i);
            return;
        }
        do {
            if (this.X[this.e] != str.charAt(i)) {
                h(str.substring(0, i));
            }
            this.e++;
            i++;
        } while (i < length);
        int i2 = this.X[this.e] & 255;
        if (i2 >= 48 && i2 != 93 && i2 != 125) {
            a(str, i, i2);
        }
    }

    private final void b(String str, int i) {
        int i2;
        int length = str.length();
        do {
            if ((this.e >= this.f && !am()) || this.X[this.e] != str.charAt(i)) {
                h(str.substring(0, i));
            }
            this.e++;
            i++;
        } while (i < length);
        if ((this.e < this.f || am()) && (i2 = this.X[this.e] & 255) >= 48 && i2 != 93 && i2 != 125) {
            a(str, i, i2);
        }
    }

    private final void a(String str, int i, int i2) {
        if (Character.isJavaIdentifierPart((char) o(i2))) {
            h(str.substring(0, i));
        }
    }

    private final int ay() {
        while (this.e < this.f) {
            byte[] bArr = this.X;
            int i = this.e;
            this.e = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 == 47 || i2 == 35) {
                    this.e--;
                    return az();
                }
                return i2;
            }
            if (i2 != 32) {
                if (i2 == 10) {
                    this.h++;
                    this.i = this.e;
                } else if (i2 == 13) {
                    aK();
                } else if (i2 != 9) {
                    f(i2);
                }
            }
        }
        return az();
    }

    private final int az() {
        int i;
        while (true) {
            if (this.e < this.f || am()) {
                byte[] bArr = this.X;
                int i2 = this.e;
                this.e = i2 + 1;
                i = bArr[i2] & 255;
                if (i > 32) {
                    if (i == 47) {
                        aD();
                    } else if (i != 35 || !aF()) {
                        break;
                    }
                } else if (i != 32) {
                    if (i == 10) {
                        this.h++;
                        this.i = this.e;
                    } else if (i == 13) {
                        aK();
                    } else if (i != 9) {
                        f(i);
                    }
                }
            } else {
                throw b("Unexpected end-of-input within/between " + this.m.e() + " entries");
            }
        }
        return i;
    }

    private final int aA() {
        if (this.e >= this.f && !am()) {
            return Z();
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 > 32) {
            if (i2 == 47 || i2 == 35) {
                this.e--;
                return aB();
            }
            return i2;
        }
        if (i2 != 32) {
            if (i2 == 10) {
                this.h++;
                this.i = this.e;
            } else if (i2 == 13) {
                aK();
            } else if (i2 != 9) {
                f(i2);
            }
        }
        while (this.e < this.f) {
            byte[] bArr2 = this.X;
            int i3 = this.e;
            this.e = i3 + 1;
            int i4 = bArr2[i3] & 255;
            if (i4 > 32) {
                if (i4 == 47 || i4 == 35) {
                    this.e--;
                    return aB();
                }
                return i4;
            }
            if (i4 != 32) {
                if (i4 == 10) {
                    this.h++;
                    this.i = this.e;
                } else if (i4 == 13) {
                    aK();
                } else if (i4 != 9) {
                    f(i4);
                }
            }
        }
        return aB();
    }

    private final int aB() {
        int i;
        while (true) {
            if (this.e < this.f || am()) {
                byte[] bArr = this.X;
                int i2 = this.e;
                this.e = i2 + 1;
                i = bArr[i2] & 255;
                if (i > 32) {
                    if (i == 47) {
                        aD();
                    } else if (i != 35 || !aF()) {
                        break;
                    }
                } else if (i != 32) {
                    if (i == 10) {
                        this.h++;
                        this.i = this.e;
                    } else if (i == 13) {
                        aK();
                    } else if (i != 9) {
                        f(i);
                    }
                }
            } else {
                return Z();
            }
        }
        return i;
    }

    private final int aC() {
        if (this.e + 4 >= this.f) {
            return c(false);
        }
        byte b2 = this.X[this.e];
        byte b3 = b2;
        if (b2 == 58) {
            byte[] bArr = this.X;
            int i = this.e + 1;
            this.e = i;
            byte b4 = bArr[i];
            if (b4 > 32) {
                if (b4 == 47 || b4 == 35) {
                    return c(true);
                }
                this.e++;
                return b4;
            }
            if (b4 == 32 || b4 == 9) {
                byte[] bArr2 = this.X;
                int i2 = this.e + 1;
                this.e = i2;
                byte b5 = bArr2[i2];
                if (b5 > 32) {
                    if (b5 == 47 || b5 == 35) {
                        return c(true);
                    }
                    this.e++;
                    return b5;
                }
            }
            return c(true);
        }
        if (b3 == 32 || b3 == 9) {
            byte[] bArr3 = this.X;
            int i3 = this.e + 1;
            this.e = i3;
            b3 = bArr3[i3];
        }
        if (b3 == 58) {
            byte[] bArr4 = this.X;
            int i4 = this.e + 1;
            this.e = i4;
            byte b6 = bArr4[i4];
            if (b6 > 32) {
                if (b6 == 47 || b6 == 35) {
                    return c(true);
                }
                this.e++;
                return b6;
            }
            if (b6 == 32 || b6 == 9) {
                byte[] bArr5 = this.X;
                int i5 = this.e + 1;
                this.e = i5;
                byte b7 = bArr5[i5];
                if (b7 > 32) {
                    if (b7 == 47 || b7 == 35) {
                        return c(true);
                    }
                    this.e++;
                    return b7;
                }
            }
            return c(true);
        }
        return c(false);
    }

    private final int c(boolean z) {
        while (true) {
            if (this.e < this.f || am()) {
                byte[] bArr = this.X;
                int i = this.e;
                this.e = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 == 47) {
                        aD();
                    } else if (i2 != 35 || !aF()) {
                        if (z) {
                            return i2;
                        }
                        if (i2 != 58) {
                            c(i2, "was expecting a colon to separate field name and value");
                        }
                        z = true;
                    }
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        this.h++;
                        this.i = this.e;
                    } else if (i2 == 13) {
                        aK();
                    } else if (i2 != 9) {
                        f(i2);
                    }
                }
            } else {
                b(" within/between " + this.m.e() + " entries", (o) null);
                return -1;
            }
        }
    }

    private final void aD() {
        if ((this.f177b & K) == 0) {
            c(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this.e >= this.f && !am()) {
            b(" in a comment", (o) null);
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 47) {
            aG();
        } else if (i2 == 42) {
            aE();
        } else {
            c(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0034. Please report as an issue. */
    private final void aE() {
        int[] e = com.a.a.b.c.b.e();
        while (true) {
            if (this.e < this.f || am()) {
                byte[] bArr = this.X;
                int i = this.e;
                this.e = i + 1;
                int i2 = bArr[i] & 255;
                int i3 = e[i2];
                if (i3 != 0) {
                    switch (i3) {
                        case 2:
                            aH();
                            break;
                        case 3:
                            aI();
                            break;
                        case 4:
                            aJ();
                            break;
                        case 10:
                            this.h++;
                            this.i = this.e;
                            break;
                        case 13:
                            aK();
                            break;
                        case 42:
                            if (this.e >= this.f && !am()) {
                                break;
                            } else if (this.X[this.e] != 47) {
                                break;
                            } else {
                                this.e++;
                                return;
                            }
                        default:
                            t(i2);
                            break;
                    }
                }
            }
        }
        b(" in a comment", (o) null);
    }

    private final boolean aF() {
        if ((this.f177b & L) == 0) {
            return false;
        }
        aG();
        return true;
    }

    private final void aG() {
        int[] e = com.a.a.b.c.b.e();
        while (true) {
            if (this.e < this.f || am()) {
                byte[] bArr = this.X;
                int i = this.e;
                this.e = i + 1;
                int i2 = bArr[i] & 255;
                int i3 = e[i2];
                if (i3 != 0) {
                    switch (i3) {
                        case 2:
                            aH();
                            break;
                        case 3:
                            aI();
                            break;
                        case 4:
                            aJ();
                            break;
                        case 10:
                            this.h++;
                            this.i = this.e;
                            return;
                        case 13:
                            aK();
                            return;
                        case 42:
                            break;
                        default:
                            if (i3 >= 0) {
                                break;
                            } else {
                                t(i2);
                                break;
                            }
                    }
                }
            } else {
                return;
            }
        }
    }

    @Override // com.a.a.b.a.b
    protected final char af() {
        if (this.e >= this.f && !am()) {
            b(" in character escape sequence", o.VALUE_STRING);
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        byte b2 = bArr[i];
        switch (b2) {
            case 34:
            case 47:
            case 92:
                return (char) b2;
            case 98:
                return '\b';
            case 102:
                return '\f';
            case 110:
                return '\n';
            case 114:
                return '\r';
            case 116:
                return '\t';
            case 117:
                int i2 = 0;
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this.e >= this.f && !am()) {
                        b(" in character escape sequence", o.VALUE_STRING);
                    }
                    byte[] bArr2 = this.X;
                    int i4 = this.e;
                    this.e = i4 + 1;
                    byte b3 = bArr2[i4];
                    int b4 = com.a.a.b.c.b.b(b3);
                    if (b4 < 0) {
                        c(b3 & 255, "expected a hex-digit for character escape sequence");
                    }
                    i2 = (i2 << 4) | b4;
                }
                return (char) i2;
            default:
                return a((char) o(b2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v30 */
    private int o(int i) {
        boolean z;
        int i2 = i & 255;
        int i3 = i2;
        if (i2 > 127) {
            if ((i3 & CGL.kCGLCPDispatchTableSize) == 192) {
                i3 &= 31;
                z = true;
            } else if ((i3 & User32.VK_OEM_ATTN) == 224) {
                i3 &= 15;
                z = 2;
            } else if ((i3 & User32.VK_EXSEL) == 240) {
                i3 &= 7;
                z = 3;
            } else {
                u(i3 & 255);
                z = true;
            }
            int aL = aL();
            if ((aL & 192) != 128) {
                v(aL & 255);
            }
            i3 = (i3 << 6) | (aL & 63);
            if (z > 1) {
                int aL2 = aL();
                if ((aL2 & 192) != 128) {
                    v(aL2 & 255);
                }
                i3 = (i3 << 6) | (aL2 & 63);
                if (z > 2) {
                    int aL3 = aL();
                    if ((aL3 & 192) != 128) {
                        v(aL3 & 255);
                    }
                    i3 = (i3 << 6) | (aL3 & 63);
                }
            }
        }
        return i3;
    }

    private final int p(int i) {
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr = this.X;
        int i2 = this.e;
        this.e = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) != 128) {
            e(b2 & 255, this.e);
        }
        return ((i & 31) << 6) | (b2 & 63);
    }

    private final int q(int i) {
        if (this.e >= this.f) {
            ar();
        }
        int i2 = i & 15;
        byte[] bArr = this.X;
        int i3 = this.e;
        this.e = i3 + 1;
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            e(b2 & 255, this.e);
        }
        int i4 = (i2 << 6) | (b2 & 63);
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr2 = this.X;
        int i5 = this.e;
        this.e = i5 + 1;
        byte b3 = bArr2[i5];
        if ((b3 & 192) != 128) {
            e(b3 & 255, this.e);
        }
        return (i4 << 6) | (b3 & 63);
    }

    private final int r(int i) {
        int i2 = i & 15;
        byte[] bArr = this.X;
        int i3 = this.e;
        this.e = i3 + 1;
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            e(b2 & 255, this.e);
        }
        int i4 = (i2 << 6) | (b2 & 63);
        byte[] bArr2 = this.X;
        int i5 = this.e;
        this.e = i5 + 1;
        byte b3 = bArr2[i5];
        if ((b3 & 192) != 128) {
            e(b3 & 255, this.e);
        }
        return (i4 << 6) | (b3 & 63);
    }

    private final int s(int i) {
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr = this.X;
        int i2 = this.e;
        this.e = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) != 128) {
            e(b2 & 255, this.e);
        }
        int i3 = ((i & 7) << 6) | (b2 & 63);
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr2 = this.X;
        int i4 = this.e;
        this.e = i4 + 1;
        byte b3 = bArr2[i4];
        if ((b3 & 192) != 128) {
            e(b3 & 255, this.e);
        }
        int i5 = (i3 << 6) | (b3 & 63);
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr3 = this.X;
        int i6 = this.e;
        this.e = i6 + 1;
        byte b4 = bArr3[i6];
        if ((b4 & 192) != 128) {
            e(b4 & 255, this.e);
        }
        return ((i5 << 6) | (b4 & 63)) - 65536;
    }

    private final void aH() {
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        byte b2 = bArr[i];
        if ((b2 & 192) != 128) {
            e(b2 & 255, this.e);
        }
    }

    private final void aI() {
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        byte b2 = bArr[i];
        if ((b2 & 192) != 128) {
            e(b2 & 255, this.e);
        }
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr2 = this.X;
        int i2 = this.e;
        this.e = i2 + 1;
        byte b3 = bArr2[i2];
        if ((b3 & 192) != 128) {
            e(b3 & 255, this.e);
        }
    }

    private final void aJ() {
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        byte b2 = bArr[i];
        if ((b2 & 192) != 128) {
            e(b2 & 255, this.e);
        }
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr2 = this.X;
        int i2 = this.e;
        this.e = i2 + 1;
        byte b3 = bArr2[i2];
        if ((b3 & 192) != 128) {
            e(b3 & 255, this.e);
        }
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr3 = this.X;
        int i3 = this.e;
        this.e = i3 + 1;
        byte b4 = bArr3[i3];
        if ((b4 & 192) != 128) {
            e(b4 & 255, this.e);
        }
    }

    private void aK() {
        if ((this.e < this.f || am()) && this.X[this.e] == 10) {
            this.e++;
        }
        this.h++;
        this.i = this.e;
    }

    private int aL() {
        if (this.e >= this.f) {
            ar();
        }
        byte[] bArr = this.X;
        int i = this.e;
        this.e = i + 1;
        return bArr[i] & 255;
    }

    private void h(String str) {
        a(str, ad());
    }

    private void a(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this.e >= this.f && !am()) {
                break;
            }
            byte[] bArr = this.X;
            int i = this.e;
            this.e = i + 1;
            char o = (char) o(bArr[i]);
            if (!Character.isJavaIdentifierPart(o)) {
                break;
            }
            sb.append(o);
            if (sb.length() >= 256) {
                sb.append("...");
                break;
            }
        }
        a("Unrecognized token '%s': was expecting %s", sb, str2);
    }

    private void t(int i) {
        if (i < 32) {
            f(i);
        }
        u(i);
    }

    private void u(int i) {
        g("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    private void v(int i) {
        g("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    private void e(int i, int i2) {
        this.e = i2;
        v(i);
    }

    private byte[] c(com.a.a.b.a aVar) {
        com.a.a.b.g.c aa = aa();
        while (true) {
            if (this.e >= this.f) {
                ar();
            }
            byte[] bArr = this.X;
            int i = this.e;
            this.e = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                int b2 = aVar.b(i2);
                int i3 = b2;
                if (b2 < 0) {
                    if (i2 == 34) {
                        return aa.b();
                    }
                    int a2 = a(aVar, i2, 0);
                    i3 = a2;
                    if (a2 < 0) {
                        continue;
                    }
                }
                int i4 = i3;
                if (this.e >= this.f) {
                    ar();
                }
                byte[] bArr2 = this.X;
                int i5 = this.e;
                this.e = i5 + 1;
                int i6 = bArr2[i5] & 255;
                int b3 = aVar.b(i6);
                int i7 = b3;
                if (b3 < 0) {
                    i7 = a(aVar, i6, 1);
                }
                int i8 = (i4 << 6) | i7;
                if (this.e >= this.f) {
                    ar();
                }
                byte[] bArr3 = this.X;
                int i9 = this.e;
                this.e = i9 + 1;
                int i10 = bArr3[i9] & 255;
                int b4 = aVar.b(i10);
                int i11 = b4;
                if (b4 < 0) {
                    if (i11 != -2) {
                        if (i10 == 34) {
                            aa.a(i8 >> 4);
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                            return aa.b();
                        }
                        i11 = a(aVar, i10, 2);
                    }
                    if (i11 == -2) {
                        if (this.e >= this.f) {
                            ar();
                        }
                        byte[] bArr4 = this.X;
                        int i12 = this.e;
                        this.e = i12 + 1;
                        int i13 = bArr4[i12] & 255;
                        if (!aVar.a(i13) && a(aVar, i13, 3) != -2) {
                            throw a(aVar, i13, 3, "expected padding character '" + aVar.b() + "'");
                        }
                        aa.a(i8 >> 4);
                    }
                }
                int i14 = (i8 << 6) | i11;
                if (this.e >= this.f) {
                    ar();
                }
                byte[] bArr5 = this.X;
                int i15 = this.e;
                this.e = i15 + 1;
                int i16 = bArr5[i15] & 255;
                int b5 = aVar.b(i16);
                int i17 = b5;
                if (b5 < 0) {
                    if (i17 != -2) {
                        if (i16 == 34) {
                            aa.b(i14 >> 2);
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                            return aa.b();
                        }
                        i17 = a(aVar, i16, 3);
                    }
                    if (i17 == -2) {
                        aa.b(i14 >> 2);
                    }
                }
                aa.c((i14 << 6) | i17);
            }
        }
    }

    @Override // com.a.a.b.a.b, com.a.a.b.l
    public final j f() {
        if (this.D == o.FIELD_NAME) {
            return new j(ag(), this.g + (this.T - 1), -1L, this.U, this.V);
        }
        return new j(ag(), this.j - 1, -1L, this.k, this.l);
    }

    @Override // com.a.a.b.a.b, com.a.a.b.l
    public final j e() {
        return new j(ag(), this.g + this.e, -1L, this.h, (this.e - this.i) + 1);
    }

    private final void aM() {
        this.k = this.h;
        int i = this.e;
        this.j = this.g + i;
        this.l = i - this.i;
    }

    private final void aN() {
        this.U = this.h;
        int i = this.e;
        this.T = i;
        this.V = i - this.i;
    }

    private final o w(int i) {
        if (i == 125) {
            aP();
            o oVar = o.END_OBJECT;
            this.D = oVar;
            return oVar;
        }
        aO();
        o oVar2 = o.END_ARRAY;
        this.D = oVar2;
        return oVar2;
    }

    private final void aO() {
        aM();
        if (!this.m.b()) {
            a(93, '}');
        }
        this.m = this.m.j();
    }

    private final void aP() {
        aM();
        if (!this.m.d()) {
            a(125, ']');
        }
        this.m = this.m.j();
    }
}
