package com.a.a.b.d;

import com.a.a.b.j;
import com.a.a.b.l;
import com.a.a.b.o;
import com.a.a.b.p;
import com.a.a.b.s;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/a/a/b/d/g.class */
public final class g extends com.a.a.b.a.b {
    private static final int E = l.a.ALLOW_TRAILING_COMMA.c();
    private static final int F = l.a.ALLOW_NUMERIC_LEADING_ZEROS.c();
    private static final int G = l.a.ALLOW_NON_NUMERIC_NUMBERS.c();
    private static final int H = l.a.ALLOW_MISSING_VALUES.c();
    private static final int I = l.a.ALLOW_SINGLE_QUOTES.c();
    private static final int J = l.a.ALLOW_UNQUOTED_FIELD_NAMES.c();
    private static final int K = l.a.ALLOW_COMMENTS.c();
    private static final int L = l.a.ALLOW_YAML_COMMENTS.c();
    private static int[] M = com.a.a.b.c.b.a();
    private Reader N;
    private char[] O;
    private boolean P;
    private p Q;
    private com.a.a.b.e.b R;
    private int S;
    private boolean T;
    private long U;
    private int V;
    private int W;

    public g(com.a.a.b.c.a aVar, int i, Reader reader, p pVar, com.a.a.b.e.b bVar) {
        super(aVar, i);
        this.N = reader;
        this.O = aVar.g();
        this.e = 0;
        this.f = 0;
        this.Q = pVar;
        this.R = bVar;
        this.S = bVar.c();
        this.P = true;
    }

    @Override // com.a.a.b.l
    public final p a() {
        return this.Q;
    }

    @Override // com.a.a.b.l
    public final com.a.a.b.g.i<s> c() {
        return c;
    }

    private char c(String str, o oVar) {
        if (this.e >= this.f && !an()) {
            b(str, oVar);
        }
        char[] cArr = this.O;
        int i = this.e;
        this.e = i + 1;
        return cArr[i];
    }

    @Override // com.a.a.b.a.b
    protected final void W() {
        if (this.N != null) {
            if (this.d.b() || a(l.a.AUTO_CLOSE_SOURCE)) {
                this.N.close();
            }
            this.N = null;
        }
    }

    @Override // com.a.a.b.a.b
    protected final void X() {
        char[] cArr;
        super.X();
        this.R.b();
        if (this.P && (cArr = this.O) != null) {
            this.O = null;
            this.d.a(cArr);
        }
    }

    private void am() {
        if (!an()) {
            ak();
        }
    }

    private boolean an() {
        if (this.N != null) {
            int read = this.N.read(this.O, 0, this.O.length);
            if (read > 0) {
                int i = this.f;
                this.g += i;
                this.i -= i;
                this.U -= i;
                this.e = 0;
                this.f = read;
                return true;
            }
            W();
            if (read == 0) {
                throw new IOException("Reader returned 0 characters when trying to read " + this.f);
            }
            return false;
        }
        return false;
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final String w() {
        if (this.D == o.VALUE_STRING) {
            if (this.T) {
                this.T = false;
                ah();
            }
            return this.o.e();
        }
        return c(this.D);
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final String R() {
        if (this.D == o.VALUE_STRING) {
            if (this.T) {
                this.T = false;
                ah();
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
            if (this.T) {
                this.T = false;
                ah();
            }
            return this.o.e();
        }
        if (this.D == o.FIELD_NAME) {
            return u();
        }
        return super.a(str);
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
                    if (this.T) {
                        this.T = false;
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
                    if (this.T) {
                        this.T = false;
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
                    if (this.T) {
                        this.T = false;
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
        if (this.D == o.VALUE_EMBEDDED_OBJECT && this.r != null) {
            return this.r;
        }
        if (this.D != o.VALUE_STRING) {
            g("Current token (" + this.D + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this.T) {
            try {
                this.r = c(aVar);
                this.T = false;
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
        if (!this.T || this.D != o.VALUE_STRING) {
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
                am();
            }
            char[] cArr = this.O;
            int i3 = this.e;
            this.e = i3 + 1;
            char c = cArr[i3];
            if (c > ' ') {
                int b2 = aVar.b(c);
                int i4 = b2;
                if (b2 < 0) {
                    if (c == '\"') {
                        break;
                    }
                    int a2 = a(aVar, c, 0);
                    i4 = a2;
                    if (a2 < 0) {
                        continue;
                    }
                }
                if (i > length) {
                    i2 += i;
                    outputStream.write(bArr, 0, i);
                    i = 0;
                }
                int i5 = i4;
                if (this.e >= this.f) {
                    am();
                }
                char[] cArr2 = this.O;
                int i6 = this.e;
                this.e = i6 + 1;
                char c2 = cArr2[i6];
                int b3 = aVar.b(c2);
                int i7 = b3;
                if (b3 < 0) {
                    i7 = a(aVar, c2, 1);
                }
                int i8 = (i5 << 6) | i7;
                if (this.e >= this.f) {
                    am();
                }
                char[] cArr3 = this.O;
                int i9 = this.e;
                this.e = i9 + 1;
                char c3 = cArr3[i9];
                int b4 = aVar.b(c3);
                int i10 = b4;
                if (b4 < 0) {
                    if (i10 != -2) {
                        if (c3 == '\"') {
                            int i11 = i;
                            i++;
                            bArr[i11] = (byte) (i8 >> 4);
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                        } else {
                            i10 = a(aVar, c3, 2);
                        }
                    }
                    if (i10 == -2) {
                        if (this.e >= this.f) {
                            am();
                        }
                        char[] cArr4 = this.O;
                        int i12 = this.e;
                        this.e = i12 + 1;
                        char c4 = cArr4[i12];
                        if (!aVar.a(c4) && a(aVar, c4, 3) != -2) {
                            throw a(aVar, c4, 3, "expected padding character '" + aVar.b() + "'");
                        }
                        int i13 = i;
                        i++;
                        bArr[i13] = (byte) (i8 >> 4);
                    }
                }
                int i14 = (i8 << 6) | i10;
                if (this.e >= this.f) {
                    am();
                }
                char[] cArr5 = this.O;
                int i15 = this.e;
                this.e = i15 + 1;
                char c5 = cArr5[i15];
                int b5 = aVar.b(c5);
                int i16 = b5;
                if (b5 < 0) {
                    if (i16 != -2) {
                        if (c5 == '\"') {
                            int i17 = i14 >> 2;
                            int i18 = i;
                            int i19 = i + 1;
                            bArr[i18] = (byte) (i17 >> 8);
                            i = i19 + 1;
                            bArr[i19] = (byte) i17;
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                        } else {
                            i16 = a(aVar, c5, 3);
                        }
                    }
                    if (i16 == -2) {
                        int i20 = i14 >> 2;
                        int i21 = i;
                        int i22 = i + 1;
                        bArr[i21] = (byte) (i20 >> 8);
                        i = i22 + 1;
                        bArr[i22] = (byte) i20;
                    }
                }
                int i23 = (i14 << 6) | i16;
                int i24 = i;
                int i25 = i + 1;
                bArr[i24] = (byte) (i23 >> 16);
                int i26 = i25 + 1;
                bArr[i25] = (byte) (i23 >> 8);
                i = i26 + 1;
                bArr[i26] = (byte) i23;
            }
        }
        this.T = false;
        if (i > 0) {
            i2 += i;
            outputStream.write(bArr, 0, i);
        }
        return i2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final o g() {
        o l;
        if (this.D == o.FIELD_NAME) {
            return ao();
        }
        this.s = 0;
        if (this.T) {
            av();
        }
        int az = az();
        int i = az;
        if (az < 0) {
            close();
            this.D = null;
            return null;
        }
        this.r = null;
        if (i == 93 || i == 125) {
            n(i);
            return this.D;
        }
        if (this.m.l()) {
            i = m(i);
            if ((this.f177b & E) != 0 && (i == 93 || i == 125)) {
                n(i);
                return this.D;
            }
        }
        boolean d = this.m.d();
        if (d) {
            aJ();
            this.m.a(i == 34 ? ar() : k(i));
            this.D = o.FIELD_NAME;
            i = ax();
        }
        aI();
        switch (i) {
            case 34:
                this.T = true;
                l = o.VALUE_STRING;
                break;
            case 43:
                if (a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.c())) {
                    l = b(false);
                    break;
                } else {
                    l = l(i);
                    break;
                }
            case 45:
                l = b(true);
                break;
            case 46:
                l = a(false);
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
                l = i(i);
                break;
            case 91:
                if (!d) {
                    this.m = this.m.a(this.k, this.l);
                }
                l = o.START_ARRAY;
                break;
            case 102:
                aG();
                l = o.VALUE_FALSE;
                break;
            case 110:
                aH();
                l = o.VALUE_NULL;
                break;
            case 116:
                aF();
                l = o.VALUE_TRUE;
                break;
            case 123:
                if (!d) {
                    this.m = this.m.b(this.k, this.l);
                }
                l = o.START_OBJECT;
                break;
            case 125:
                c(i, "expected a value");
                aF();
                l = o.VALUE_TRUE;
                break;
            default:
                l = l(i);
                break;
        }
        if (d) {
            this.n = l;
            return this.D;
        }
        this.D = l;
        return l;
    }

    private final o ao() {
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
        o l;
        this.s = 0;
        if (this.D == o.FIELD_NAME) {
            ao();
            return null;
        }
        if (this.T) {
            av();
        }
        int az = az();
        int i = az;
        if (az < 0) {
            close();
            this.D = null;
            return null;
        }
        this.r = null;
        if (i == 93 || i == 125) {
            n(i);
            return null;
        }
        if (this.m.l()) {
            i = m(i);
            if ((this.f177b & E) != 0 && (i == 93 || i == 125)) {
                n(i);
                return null;
            }
        }
        if (!this.m.d()) {
            aI();
            h(i);
            return null;
        }
        aJ();
        String ar = i == 34 ? ar() : k(i);
        this.m.a(ar);
        this.D = o.FIELD_NAME;
        int ax = ax();
        aI();
        if (ax == 34) {
            this.T = true;
            this.n = o.VALUE_STRING;
            return ar;
        }
        switch (ax) {
            case 43:
                if (a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.c())) {
                    l = b(false);
                    break;
                } else {
                    l = l(ax);
                    break;
                }
            case 45:
                l = b(true);
                break;
            case 46:
                l = a(false);
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
                l = i(ax);
                break;
            case 91:
                l = o.START_ARRAY;
                break;
            case 102:
                aG();
                l = o.VALUE_FALSE;
                break;
            case 110:
                aH();
                l = o.VALUE_NULL;
                break;
            case 116:
                aF();
                l = o.VALUE_TRUE;
                break;
            case 123:
                l = o.START_OBJECT;
                break;
            default:
                l = l(ax);
                break;
        }
        this.n = l;
        return ar;
    }

    private final o h(int i) {
        if (i == 34) {
            this.T = true;
            o oVar = o.VALUE_STRING;
            this.D = oVar;
            return oVar;
        }
        switch (i) {
            case 44:
                if (!this.m.c() && (this.f177b & H) != 0) {
                    this.e--;
                    o oVar2 = o.VALUE_NULL;
                    this.D = oVar2;
                    return oVar2;
                }
                break;
            case 45:
                o b2 = b(true);
                this.D = b2;
                return b2;
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
                o oVar3 = o.START_ARRAY;
                this.D = oVar3;
                return oVar3;
            case 102:
                a("false", 1);
                o oVar4 = o.VALUE_FALSE;
                this.D = oVar4;
                return oVar4;
            case 110:
                a("null", 1);
                o oVar5 = o.VALUE_NULL;
                this.D = oVar5;
                return oVar5;
            case 116:
                a("true", 1);
                o oVar6 = o.VALUE_TRUE;
                this.D = oVar6;
                return oVar6;
            case 123:
                this.m = this.m.b(this.k, this.l);
                o oVar7 = o.START_OBJECT;
                this.D = oVar7;
                return oVar7;
        }
        o l = l(i);
        this.D = l;
        return l;
    }

    @Override // com.a.a.b.l
    public final String i() {
        if (this.D == o.FIELD_NAME) {
            this.q = false;
            o oVar = this.n;
            this.n = null;
            this.D = oVar;
            if (oVar == o.VALUE_STRING) {
                if (this.T) {
                    this.T = false;
                    ah();
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
            return l(46);
        }
        int i = this.e - 1;
        if (z) {
            i--;
        }
        return a(46, i, this.e, z, 0);
    }

    private o i(int i) {
        int i2 = this.e;
        int i3 = i2;
        int i4 = i2 - 1;
        int i5 = this.f;
        if (i == 48) {
            return b(false, i4);
        }
        int i6 = 1;
        while (i3 < i5) {
            int i7 = i3;
            i3++;
            char c = this.O[i7];
            if (c >= '0' && c <= '9') {
                i6++;
            } else {
                if (c == '.' || c == 'e' || c == 'E') {
                    this.e = i3;
                    return a(c, i4, i3, false, i6);
                }
                int i8 = i3 - 1;
                this.e = i8;
                if (this.m.c()) {
                    j(c);
                }
                this.o.a(this.O, i4, i8 - i4);
                return a(false, i6);
            }
        }
        this.e = i4;
        return b(false, i4);
    }

    private final o a(int i, int i2, int i3, boolean z, int i4) {
        int i5 = this.f;
        int i6 = 0;
        if (i == 46) {
            while (i3 < i5) {
                int i7 = i3;
                i3++;
                char c = this.O[i7];
                i = c;
                if (c >= '0' && i <= 57) {
                    i6++;
                } else if (i6 == 0 && !a(e.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.c())) {
                    b(i, "Decimal point not followed by a digit");
                }
            }
            return b(z, i2);
        }
        int i8 = 0;
        if (i == 101 || i == 69) {
            if (i3 >= i5) {
                this.e = i2;
                return b(z, i2);
            }
            int i9 = i3;
            i3++;
            char c2 = this.O[i9];
            i = c2;
            if (c2 == '-' || i == 43) {
                if (i3 >= i5) {
                    this.e = i2;
                    return b(z, i2);
                }
                i3++;
                i = this.O[i3];
            }
            while (i <= 57 && i >= 48) {
                i8++;
                if (i3 >= i5) {
                    this.e = i2;
                    return b(z, i2);
                }
                int i10 = i3;
                i3++;
                i = this.O[i10];
            }
            if (i8 == 0) {
                b(i, "Exponent indicator not followed by a digit");
            }
        }
        int i11 = i3 - 1;
        this.e = i11;
        if (this.m.c()) {
            j(i);
        }
        this.o.a(this.O, i2, i11 - i2);
        return a(z, i4, i6, i8);
    }

    private final o b(boolean z) {
        int i = this.e;
        int i2 = z ? i - 1 : i;
        int i3 = this.f;
        if (i >= i3) {
            return b(z, i2);
        }
        int i4 = i + 1;
        char c = this.O[i];
        if (c > '9' || c < '0') {
            this.e = i4;
            if (c == '.') {
                return a(z);
            }
            return a((int) c, z, true);
        }
        if (c == '0') {
            return b(z, i2);
        }
        int i5 = 1;
        while (i4 < i3) {
            int i6 = i4;
            i4++;
            char c2 = this.O[i6];
            if (c2 >= '0' && c2 <= '9') {
                i5++;
            } else {
                if (c2 == '.' || c2 == 'e' || c2 == 'E') {
                    this.e = i4;
                    return a(c2, i2, i4, z, i5);
                }
                int i7 = i4 - 1;
                this.e = i7;
                if (this.m.c()) {
                    j(c2);
                }
                this.o.a(this.O, i2, i7 - i2);
                return a(z, i5);
            }
        }
        return b(z, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0209, code lost:            r0 = r6.O;        r2 = r6.e;        r6.e = r2 + 1;        r0 = r0[r2];     */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0222  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.a.a.b.o b(boolean r7, int r8) {
        /*
            Method dump skipped, instructions count: 690
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.g.b(boolean, int):com.a.a.b.o");
    }

    private final char ap() {
        char c;
        if (this.e < this.f && ((c = this.O[this.e]) < '0' || c > '9')) {
            return '0';
        }
        return aq();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004c, code lost:            if (r5 == '0') goto L19;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0057, code lost:            if (r4.e < r4.f) goto L23;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:            if (an() == false) goto L36;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0061, code lost:            r0 = r4.O[r4.e];        r5 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:            if (r0 < '0') goto L35;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0074, code lost:            if (r5 <= '9') goto L29;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007a, code lost:            r4.e++;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0087, code lost:            if (r5 == '0') goto L37;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0077, code lost:            return '0';     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:            return '0';     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008b, code lost:            return r5;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private char aq() {
        /*
            r4 = this;
            r0 = r4
            int r0 = r0.e
            r1 = r4
            int r1 = r1.f
            if (r0 < r1) goto L15
            r0 = r4
            boolean r0 = r0.an()
            if (r0 != 0) goto L15
            r0 = 48
            return r0
        L15:
            r0 = r4
            char[] r0 = r0.O
            r1 = r4
            int r1 = r1.e
            char r0 = r0[r1]
            r1 = r0
            r5 = r1
            r1 = 48
            if (r0 < r1) goto L2b
            r0 = r5
            r1 = 57
            if (r0 <= r1) goto L2e
        L2b:
            r0 = 48
            return r0
        L2e:
            r0 = r4
            int r0 = r0.f177b
            int r1 = com.a.a.b.d.g.F
            r0 = r0 & r1
            if (r0 != 0) goto L3f
            r0 = r4
            java.lang.String r1 = "Leading zeroes not allowed"
            r0.c(r1)
        L3f:
            r0 = r4
            r1 = r0
            int r1 = r1.e
            r2 = 1
            int r1 = r1 + r2
            r0.e = r1
            r0 = r5
            r1 = 48
            if (r0 != r1) goto L8a
        L4f:
            r0 = r4
            int r0 = r0.e
            r1 = r4
            int r1 = r1.f
            if (r0 < r1) goto L61
            r0 = r4
            boolean r0 = r0.an()
            if (r0 == 0) goto L8a
        L61:
            r0 = r4
            char[] r0 = r0.O
            r1 = r4
            int r1 = r1.e
            char r0 = r0[r1]
            r1 = r0
            r5 = r1
            r1 = 48
            if (r0 < r1) goto L77
            r0 = r5
            r1 = 57
            if (r0 <= r1) goto L7a
        L77:
            r0 = 48
            return r0
        L7a:
            r0 = r4
            r1 = r0
            int r1 = r1.e
            r2 = 1
            int r1 = r1 + r2
            r0.e = r1
            r0 = r5
            r1 = 48
            if (r0 == r1) goto L4f
        L8a:
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.g.aq():char");
    }

    private o a(int i, boolean z) {
        return a(i, z, false);
    }

    private o a(int i, boolean z, boolean z2) {
        if (i == 73) {
            if (this.e >= this.f && !an()) {
                b(o.VALUE_NUMBER_INT);
            }
            char[] cArr = this.O;
            int i2 = this.e;
            this.e = i2 + 1;
            char c = cArr[i2];
            i = c;
            if (c == 'N') {
                String str = z ? "-INF" : "+INF";
                a(str, 3);
                if ((this.f177b & G) != 0) {
                    return a(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                g("Non-standard token '" + str + "': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow");
            } else if (i == 110) {
                String str2 = z ? "-Infinity" : "+Infinity";
                a(str2, 3);
                if ((this.f177b & G) != 0) {
                    return a(str2, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                g("Non-standard token '" + str2 + "': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow");
            }
        }
        if (!a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.c()) && z2 && !z) {
            b(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
        }
        b(i, z ? "expected digit (0-9) to follow minus sign, for valid numeric value" : "expected digit (0-9) for valid numeric value");
        return null;
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

    private String ar() {
        int i = this.e;
        int i2 = this.S;
        int[] iArr = M;
        while (true) {
            if (i >= this.f) {
                break;
            }
            char c = this.O[i];
            if (c >= iArr.length || iArr[c] == 0) {
                i2 = (i2 * 33) + c;
                i++;
            } else if (c == '\"') {
                int i3 = this.e;
                this.e = i + 1;
                return this.R.a(this.O, i3, i - i3, i2);
            }
        }
        int i4 = this.e;
        this.e = i;
        return a(i4, i2, 34);
    }

    private String a(int i, int i2, int i3) {
        this.o.a(this.O, i, this.e - i);
        char[] g = this.o.g();
        int i4 = this.o.i();
        while (true) {
            if (this.e >= this.f && !an()) {
                b(" in field name", o.FIELD_NAME);
            }
            char[] cArr = this.O;
            int i5 = this.e;
            this.e = i5 + 1;
            char c = cArr[i5];
            char c2 = c;
            if (c <= '\\') {
                if (c == '\\') {
                    c2 = af();
                } else if (c <= i3) {
                    if (c != i3) {
                        if (c < ' ') {
                            a(c, Attribute.NAME_ATTR);
                        }
                    } else {
                        this.o.a(i4);
                        com.a.a.b.g.o oVar = this.o;
                        return this.R.a(oVar.d(), oVar.c(), oVar.b(), i2);
                    }
                }
            }
            i2 = (i2 * 33) + c2;
            int i6 = i4;
            i4++;
            g[i6] = c2;
            if (i4 >= g.length) {
                g = this.o.j();
                i4 = 0;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x006b, code lost:            if (r7 < r0) goto L24;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:            r0 = r6.O[r7];     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0078, code lost:            if (r0 >= r0) goto L30;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007f, code lost:            if (r0[r0] == 0) goto L34;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c7, code lost:            r10 = (r10 * 33) + r0;        r7 = r7 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00d7, code lost:            if (r7 < r0) goto L41;     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0082, code lost:            r0 = r6.e - 1;        r6.e = r7;     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009f, code lost:            return r6.R.a(r6.O, r0, r7 - r0, r10);     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a6, code lost:            if (java.lang.Character.isJavaIdentifierPart(r0) != false) goto L34;     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a9, code lost:            r0 = r6.e - 1;        r6.e = r7;     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c6, code lost:            return r6.R.a(r6.O, r0, r7 - r0, r10);     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00da, code lost:            r0 = r6.e - 1;        r6.e = r7;     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00f0, code lost:            return a(r0, r10, r0);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String k(int r7) {
        /*
            Method dump skipped, instructions count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.g.k(int):java.lang.String");
    }

    private String as() {
        int i = this.e;
        int i2 = this.S;
        int i3 = this.f;
        if (i < i3) {
            int[] iArr = M;
            int length = iArr.length;
            do {
                char c = this.O[i];
                if (c == '\'') {
                    int i4 = this.e;
                    this.e = i + 1;
                    return this.R.a(this.O, i4, i - i4, i2);
                }
                if (c < length && iArr[c] != 0) {
                    break;
                }
                i2 = (i2 * 33) + c;
                i++;
            } while (i < i3);
        }
        int i5 = this.e;
        this.e = i;
        return a(i5, i2, 39);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0053, code lost:            if (r7.m.b() != false) goto L10;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0001. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.a.a.b.o l(int r8) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.g.l(int):com.a.a.b.o");
    }

    private o at() {
        char[] h = this.o.h();
        int i = this.o.i();
        while (true) {
            if (this.e >= this.f && !an()) {
                b(": was expecting closing quote for a string value", o.VALUE_STRING);
            }
            char[] cArr = this.O;
            int i2 = this.e;
            this.e = i2 + 1;
            char c = cArr[i2];
            char c2 = c;
            if (c <= '\\') {
                if (c == '\\') {
                    c2 = af();
                } else if (c <= '\'') {
                    if (c != '\'') {
                        if (c < ' ') {
                            a(c, "string value");
                        }
                    } else {
                        this.o.a(i);
                        return o.VALUE_STRING;
                    }
                }
            }
            if (i >= h.length) {
                h = this.o.j();
                i = 0;
            }
            int i3 = i;
            i++;
            h[i3] = c2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0081 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0027 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(int r7, int r8, int[] r9) {
        /*
            r6 = this;
            r0 = r6
            com.a.a.b.g.o r0 = r0.o
            r1 = r6
            char[] r1 = r1.O
            r2 = r7
            r3 = r6
            int r3 = r3.e
            r4 = r7
            int r3 = r3 - r4
            r0.a(r1, r2, r3)
            r0 = r6
            com.a.a.b.g.o r0 = r0.o
            char[] r0 = r0.g()
            r7 = r0
            r0 = r6
            com.a.a.b.g.o r0 = r0.o
            int r0 = r0.i()
            r10 = r0
            r0 = r9
            int r0 = r0.length
            r11 = r0
        L27:
            r0 = r6
            int r0 = r0.e
            r1 = r6
            int r1 = r1.f
            if (r0 < r1) goto L39
            r0 = r6
            boolean r0 = r0.an()
            if (r0 == 0) goto L8f
        L39:
            r0 = r6
            char[] r0 = r0.O
            r1 = r6
            int r1 = r1.e
            char r0 = r0[r1]
            r1 = r0
            r12 = r1
            r1 = r0
            r13 = r1
            r1 = r11
            if (r0 >= r1) goto L57
            r0 = r9
            r1 = r13
            r0 = r0[r1]
            if (r0 == 0) goto L5f
            goto L8f
        L57:
            r0 = r12
            boolean r0 = java.lang.Character.isJavaIdentifierPart(r0)
            if (r0 == 0) goto L8f
        L5f:
            r0 = r6
            r1 = r0
            int r1 = r1.e
            r2 = 1
            int r1 = r1 + r2
            r0.e = r1
            r0 = r8
            r1 = 33
            int r0 = r0 * r1
            r1 = r13
            int r0 = r0 + r1
            r8 = r0
            r0 = r7
            r1 = r10
            int r10 = r10 + 1
            r2 = r12
            r0[r1] = r2
            r0 = r10
            r1 = r7
            int r1 = r1.length
            if (r0 < r1) goto L8c
            r0 = r6
            com.a.a.b.g.o r0 = r0.o
            char[] r0 = r0.j()
            r7 = r0
            r0 = 0
            r10 = r0
        L8c:
            goto L27
        L8f:
            r0 = r6
            com.a.a.b.g.o r0 = r0.o
            r1 = r10
            r0.a(r1)
            r0 = r6
            com.a.a.b.g.o r0 = r0.o
            r1 = r0
            r12 = r1
            char[] r0 = r0.d()
            r13 = r0
            r0 = r12
            int r0 = r0.c()
            r7 = r0
            r0 = r12
            int r0 = r0.b()
            r9 = r0
            r0 = r6
            com.a.a.b.e.b r0 = r0.R
            r1 = r13
            r2 = r7
            r3 = r9
            r4 = r8
            java.lang.String r0 = r0.a(r1, r2, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.g.a(int, int, int[]):java.lang.String");
    }

    @Override // com.a.a.b.a.b
    protected final void ah() {
        int i = this.e;
        int i2 = this.f;
        if (i < i2) {
            int[] iArr = M;
            int length = iArr.length;
            while (true) {
                char c = this.O[i];
                if (c < length && iArr[c] != 0) {
                    if (c == '\"') {
                        com.a.a.b.g.o oVar = this.o;
                        char[] cArr = this.O;
                        int i3 = this.e;
                        oVar.a(cArr, i3, i - i3);
                        this.e = i + 1;
                        return;
                    }
                } else {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                }
            }
        }
        com.a.a.b.g.o oVar2 = this.o;
        char[] cArr2 = this.O;
        int i4 = this.e;
        oVar2.b(cArr2, i4, i - i4);
        this.e = i;
        au();
    }

    private void au() {
        char[] g = this.o.g();
        int i = this.o.i();
        int[] iArr = M;
        int length = iArr.length;
        while (true) {
            if (this.e >= this.f && !an()) {
                b(": was expecting closing quote for a string value", o.VALUE_STRING);
            }
            char[] cArr = this.O;
            int i2 = this.e;
            this.e = i2 + 1;
            char c = cArr[i2];
            char c2 = c;
            if (c < length && iArr[c] != 0) {
                if (c != '\"') {
                    if (c == '\\') {
                        c2 = af();
                    } else if (c < ' ') {
                        a(c, "string value");
                    }
                } else {
                    this.o.a(i);
                    return;
                }
            }
            if (i >= g.length) {
                g = this.o.j();
                i = 0;
            }
            int i3 = i;
            i++;
            g[i3] = c2;
        }
    }

    private void av() {
        this.T = false;
        int i = this.e;
        int i2 = this.f;
        char[] cArr = this.O;
        while (true) {
            if (i >= i2) {
                this.e = i;
                if (!an()) {
                    b(": was expecting closing quote for a string value", o.VALUE_STRING);
                }
                i = this.e;
                i2 = this.f;
            }
            int i3 = i;
            i++;
            char c = cArr[i3];
            if (c <= '\\') {
                if (c == '\\') {
                    this.e = i;
                    af();
                    i = this.e;
                    i2 = this.f;
                } else if (c > '\"') {
                    continue;
                } else if (c == '\"') {
                    this.e = i;
                    return;
                } else if (c < ' ') {
                    this.e = i;
                    a(c, "string value");
                }
            }
        }
    }

    private void aw() {
        if ((this.e < this.f || an()) && this.O[this.e] == '\n') {
            this.e++;
        }
        this.h++;
        this.i = this.e;
    }

    private final int ax() {
        if (this.e + 4 >= this.f) {
            return c(false);
        }
        char c = this.O[this.e];
        char c2 = c;
        if (c == ':') {
            char[] cArr = this.O;
            int i = this.e + 1;
            this.e = i;
            char c3 = cArr[i];
            if (c3 > ' ') {
                if (c3 == '/' || c3 == '#') {
                    return c(true);
                }
                this.e++;
                return c3;
            }
            if (c3 == ' ' || c3 == '\t') {
                char[] cArr2 = this.O;
                int i2 = this.e + 1;
                this.e = i2;
                char c4 = cArr2[i2];
                if (c4 > ' ') {
                    if (c4 == '/' || c4 == '#') {
                        return c(true);
                    }
                    this.e++;
                    return c4;
                }
            }
            return c(true);
        }
        if (c2 == ' ' || c2 == '\t') {
            char[] cArr3 = this.O;
            int i3 = this.e + 1;
            this.e = i3;
            c2 = cArr3[i3];
        }
        if (c2 == ':') {
            char[] cArr4 = this.O;
            int i4 = this.e + 1;
            this.e = i4;
            char c5 = cArr4[i4];
            if (c5 > ' ') {
                if (c5 == '/' || c5 == '#') {
                    return c(true);
                }
                this.e++;
                return c5;
            }
            if (c5 == ' ' || c5 == '\t') {
                char[] cArr5 = this.O;
                int i5 = this.e + 1;
                this.e = i5;
                char c6 = cArr5[i5];
                if (c6 > ' ') {
                    if (c6 == '/' || c6 == '#') {
                        return c(true);
                    }
                    this.e++;
                    return c6;
                }
            }
            return c(true);
        }
        return c(false);
    }

    private final int c(boolean z) {
        while (true) {
            if (this.e < this.f || an()) {
                char[] cArr = this.O;
                int i = this.e;
                this.e = i + 1;
                char c = cArr[i];
                if (c > ' ') {
                    if (c == '/') {
                        aB();
                    } else if (c != '#' || !aD()) {
                        if (z) {
                            return c;
                        }
                        if (c != ':') {
                            c(c, "was expecting a colon to separate field name and value");
                        }
                        z = true;
                    }
                } else if (c < ' ') {
                    if (c == '\n') {
                        this.h++;
                        this.i = this.e;
                    } else if (c == '\r') {
                        aw();
                    } else if (c != '\t') {
                        f(c);
                    }
                }
            } else {
                b(" within/between " + this.m.e() + " entries", (o) null);
                return -1;
            }
        }
    }

    private final int m(int i) {
        if (i != 44) {
            c(i, "was expecting comma to separate " + this.m.e() + " entries");
        }
        while (this.e < this.f) {
            char[] cArr = this.O;
            int i2 = this.e;
            this.e = i2 + 1;
            char c = cArr[i2];
            if (c > ' ') {
                if (c == '/' || c == '#') {
                    this.e--;
                    return ay();
                }
                return c;
            }
            if (c < ' ') {
                if (c == '\n') {
                    this.h++;
                    this.i = this.e;
                } else if (c == '\r') {
                    aw();
                } else if (c != '\t') {
                    f(c);
                }
            }
        }
        return ay();
    }

    private final int ay() {
        char c;
        while (true) {
            if (this.e < this.f || an()) {
                char[] cArr = this.O;
                int i = this.e;
                this.e = i + 1;
                c = cArr[i];
                if (c > ' ') {
                    if (c == '/') {
                        aB();
                    } else if (c != '#' || !aD()) {
                        break;
                    }
                } else if (c < ' ') {
                    if (c == '\n') {
                        this.h++;
                        this.i = this.e;
                    } else if (c == '\r') {
                        aw();
                    } else if (c != '\t') {
                        f(c);
                    }
                }
            } else {
                throw b("Unexpected end-of-input within/between " + this.m.e() + " entries");
            }
        }
        return c;
    }

    private final int az() {
        if (this.e >= this.f && !an()) {
            return Z();
        }
        char[] cArr = this.O;
        int i = this.e;
        this.e = i + 1;
        char c = cArr[i];
        if (c > ' ') {
            if (c == '/' || c == '#') {
                this.e--;
                return aA();
            }
            return c;
        }
        if (c != ' ') {
            if (c == '\n') {
                this.h++;
                this.i = this.e;
            } else if (c == '\r') {
                aw();
            } else if (c != '\t') {
                f(c);
            }
        }
        while (this.e < this.f) {
            char[] cArr2 = this.O;
            int i2 = this.e;
            this.e = i2 + 1;
            char c2 = cArr2[i2];
            if (c2 > ' ') {
                if (c2 == '/' || c2 == '#') {
                    this.e--;
                    return aA();
                }
                return c2;
            }
            if (c2 != ' ') {
                if (c2 == '\n') {
                    this.h++;
                    this.i = this.e;
                } else if (c2 == '\r') {
                    aw();
                } else if (c2 != '\t') {
                    f(c2);
                }
            }
        }
        return aA();
    }

    private int aA() {
        char c;
        while (true) {
            if (this.e >= this.f && !an()) {
                return Z();
            }
            char[] cArr = this.O;
            int i = this.e;
            this.e = i + 1;
            c = cArr[i];
            if (c > ' ') {
                if (c == '/') {
                    aB();
                } else if (c != '#' || !aD()) {
                    break;
                }
            } else if (c != ' ') {
                if (c == '\n') {
                    this.h++;
                    this.i = this.e;
                } else if (c == '\r') {
                    aw();
                } else if (c != '\t') {
                    f(c);
                }
            }
        }
        return c;
    }

    private void aB() {
        if ((this.f177b & K) == 0) {
            c(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this.e >= this.f && !an()) {
            b(" in a comment", (o) null);
        }
        char[] cArr = this.O;
        int i = this.e;
        this.e = i + 1;
        char c = cArr[i];
        if (c == '/') {
            aE();
        } else if (c == '*') {
            aC();
        } else {
            c(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private void aC() {
        while (true) {
            if (this.e >= this.f && !an()) {
                break;
            }
            char[] cArr = this.O;
            int i = this.e;
            this.e = i + 1;
            char c = cArr[i];
            if (c <= '*') {
                if (c == '*') {
                    if (this.e >= this.f && !an()) {
                        break;
                    } else if (this.O[this.e] == '/') {
                        this.e++;
                        return;
                    }
                } else if (c < ' ') {
                    if (c == '\n') {
                        this.h++;
                        this.i = this.e;
                    } else if (c == '\r') {
                        aw();
                    } else if (c != '\t') {
                        f(c);
                    }
                }
            }
        }
        b(" in a comment", (o) null);
    }

    private boolean aD() {
        if ((this.f177b & L) == 0) {
            return false;
        }
        aE();
        return true;
    }

    private void aE() {
        while (true) {
            if (this.e < this.f || an()) {
                char[] cArr = this.O;
                int i = this.e;
                this.e = i + 1;
                char c = cArr[i];
                if (c < ' ') {
                    if (c == '\n') {
                        this.h++;
                        this.i = this.e;
                        return;
                    } else if (c == '\r') {
                        aw();
                        return;
                    } else if (c != '\t') {
                        f(c);
                    }
                }
            } else {
                return;
            }
        }
    }

    @Override // com.a.a.b.a.b
    protected final char af() {
        if (this.e >= this.f && !an()) {
            b(" in character escape sequence", o.VALUE_STRING);
        }
        char[] cArr = this.O;
        int i = this.e;
        this.e = i + 1;
        char c = cArr[i];
        switch (c) {
            case '\"':
            case '/':
            case '\\':
                return c;
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                int i2 = 0;
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this.e >= this.f && !an()) {
                        b(" in character escape sequence", o.VALUE_STRING);
                    }
                    char[] cArr2 = this.O;
                    int i4 = this.e;
                    this.e = i4 + 1;
                    char c2 = cArr2[i4];
                    int b2 = com.a.a.b.c.b.b(c2);
                    if (b2 < 0) {
                        c(c2, "expected a hex-digit for character escape sequence");
                    }
                    i2 = (i2 << 4) | b2;
                }
                return (char) i2;
            default:
                return a(c);
        }
    }

    private final void aF() {
        int i;
        char c;
        int i2 = this.e;
        if (i2 + 3 < this.f) {
            char[] cArr = this.O;
            if (cArr[i2] == 'r') {
                int i3 = i2 + 1;
                if (cArr[i3] == 'u') {
                    int i4 = i3 + 1;
                    if (cArr[i4] == 'e' && ((c = cArr[(i = i4 + 1)]) < '0' || c == ']' || c == '}')) {
                        this.e = i;
                        return;
                    }
                }
            }
        }
        a("true", 1);
    }

    private final void aG() {
        int i;
        char c;
        int i2 = this.e;
        if (i2 + 4 < this.f) {
            char[] cArr = this.O;
            if (cArr[i2] == 'a') {
                int i3 = i2 + 1;
                if (cArr[i3] == 'l') {
                    int i4 = i3 + 1;
                    if (cArr[i4] == 's') {
                        int i5 = i4 + 1;
                        if (cArr[i5] == 'e' && ((c = cArr[(i = i5 + 1)]) < '0' || c == ']' || c == '}')) {
                            this.e = i;
                            return;
                        }
                    }
                }
            }
        }
        a("false", 1);
    }

    private final void aH() {
        int i;
        char c;
        int i2 = this.e;
        if (i2 + 3 < this.f) {
            char[] cArr = this.O;
            if (cArr[i2] == 'u') {
                int i3 = i2 + 1;
                if (cArr[i3] == 'l') {
                    int i4 = i3 + 1;
                    if (cArr[i4] == 'l' && ((c = cArr[(i = i4 + 1)]) < '0' || c == ']' || c == '}')) {
                        this.e = i;
                        return;
                    }
                }
            }
        }
        a("null", 1);
    }

    private void a(String str, int i) {
        int length = str.length();
        if (this.e + length >= this.f) {
            b(str, i);
            return;
        }
        do {
            if (this.O[this.e] != str.charAt(i)) {
                h(str.substring(0, i));
            }
            this.e++;
            i++;
        } while (i < length);
        char c = this.O[this.e];
        if (c >= '0' && c != ']' && c != '}') {
            a(str, i, c);
        }
    }

    private final void b(String str, int i) {
        char c;
        int length = str.length();
        do {
            if ((this.e >= this.f && !an()) || this.O[this.e] != str.charAt(i)) {
                h(str.substring(0, i));
            }
            this.e++;
            i++;
        } while (i < length);
        if ((this.e < this.f || an()) && (c = this.O[this.e]) >= '0' && c != ']' && c != '}') {
            a(str, i, c);
        }
    }

    private final void a(String str, int i, int i2) {
        if (Character.isJavaIdentifierPart((char) i2)) {
            h(str.substring(0, i));
        }
    }

    private byte[] c(com.a.a.b.a aVar) {
        com.a.a.b.g.c aa = aa();
        while (true) {
            if (this.e >= this.f) {
                am();
            }
            char[] cArr = this.O;
            int i = this.e;
            this.e = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                int b2 = aVar.b(c);
                int i2 = b2;
                if (b2 < 0) {
                    if (c == '\"') {
                        return aa.b();
                    }
                    int a2 = a(aVar, c, 0);
                    i2 = a2;
                    if (a2 < 0) {
                        continue;
                    }
                }
                int i3 = i2;
                if (this.e >= this.f) {
                    am();
                }
                char[] cArr2 = this.O;
                int i4 = this.e;
                this.e = i4 + 1;
                char c2 = cArr2[i4];
                int b3 = aVar.b(c2);
                int i5 = b3;
                if (b3 < 0) {
                    i5 = a(aVar, c2, 1);
                }
                int i6 = (i3 << 6) | i5;
                if (this.e >= this.f) {
                    am();
                }
                char[] cArr3 = this.O;
                int i7 = this.e;
                this.e = i7 + 1;
                char c3 = cArr3[i7];
                int b4 = aVar.b(c3);
                int i8 = b4;
                if (b4 < 0) {
                    if (i8 != -2) {
                        if (c3 == '\"') {
                            aa.a(i6 >> 4);
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                            return aa.b();
                        }
                        i8 = a(aVar, c3, 2);
                    }
                    if (i8 == -2) {
                        if (this.e >= this.f) {
                            am();
                        }
                        char[] cArr4 = this.O;
                        int i9 = this.e;
                        this.e = i9 + 1;
                        char c4 = cArr4[i9];
                        if (!aVar.a(c4) && a(aVar, c4, 3) != -2) {
                            throw a(aVar, c4, 3, "expected padding character '" + aVar.b() + "'");
                        }
                        aa.a(i6 >> 4);
                    }
                }
                int i10 = (i6 << 6) | i8;
                if (this.e >= this.f) {
                    am();
                }
                char[] cArr5 = this.O;
                int i11 = this.e;
                this.e = i11 + 1;
                char c5 = cArr5[i11];
                int b5 = aVar.b(c5);
                int i12 = b5;
                if (b5 < 0) {
                    if (i12 != -2) {
                        if (c5 == '\"') {
                            aa.b(i10 >> 2);
                            if (aVar.a()) {
                                this.e--;
                                b(aVar);
                            }
                            return aa.b();
                        }
                        i12 = a(aVar, c5, 3);
                    }
                    if (i12 == -2) {
                        aa.b(i10 >> 2);
                    }
                }
                aa.c((i10 << 6) | i12);
            }
        }
    }

    @Override // com.a.a.b.a.b, com.a.a.b.l
    public final j f() {
        if (this.D == o.FIELD_NAME) {
            return new j(ag(), -1L, this.g + (this.U - 1), this.V, this.W);
        }
        return new j(ag(), -1L, this.j - 1, this.k, this.l);
    }

    @Override // com.a.a.b.a.b, com.a.a.b.l
    public final j e() {
        return new j(ag(), -1L, this.g + this.e, this.h, (this.e - this.i) + 1);
    }

    private final void aI() {
        int i = this.e;
        this.j = this.g + i;
        this.k = this.h;
        this.l = i - this.i;
    }

    private final void aJ() {
        int i = this.e;
        this.U = i;
        this.V = this.h;
        this.W = i - this.i;
    }

    private void h(String str) {
        a(str, ad());
    }

    private void a(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this.e >= this.f && !an()) {
                break;
            }
            char c = this.O[this.e];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this.e++;
            sb.append(c);
            if (sb.length() >= 256) {
                sb.append("...");
                break;
            }
        }
        a("Unrecognized token '%s': was expecting %s", sb, str2);
    }

    private void n(int i) {
        if (i == 93) {
            aI();
            if (!this.m.b()) {
                a(i, '}');
            }
            this.m = this.m.j();
            this.D = o.END_ARRAY;
        }
        if (i == 125) {
            aI();
            if (!this.m.d()) {
                a(i, ']');
            }
            this.m = this.m.j();
            this.D = o.END_OBJECT;
        }
    }
}
