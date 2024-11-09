package com.a.a.b.d;

import com.a.a.b.c.l;
import com.a.a.b.f;
import com.a.a.b.p;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/a/a/b/d/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private final com.a.a.b.c.a f124a;

    /* renamed from: b, reason: collision with root package name */
    private final InputStream f125b;
    private final byte[] c;
    private int h;
    private boolean g = true;
    private int d = 0;
    private int e = 0;
    private final boolean f = true;

    public a(com.a.a.b.c.a aVar, InputStream inputStream) {
        this.f124a = aVar;
        this.f125b = inputStream;
        this.c = aVar.e();
    }

    private com.a.a.b.e a() {
        com.a.a.b.e eVar;
        boolean z = false;
        if (d(4)) {
            int i = (this.c[this.d] << 24) | ((this.c[this.d + 1] & 255) << 16) | ((this.c[this.d + 2] & 255) << 8) | (this.c[this.d + 3] & 255);
            if (a(i)) {
                z = true;
            } else if (b(i)) {
                z = true;
            } else if (c(i >>> 16)) {
                z = true;
            }
        } else if (d(2) && c(((this.c[this.d] & 255) << 8) | (this.c[this.d + 1] & 255))) {
            z = true;
        }
        if (!z) {
            eVar = com.a.a.b.e.UTF8;
        } else {
            switch (this.h) {
                case 1:
                    eVar = com.a.a.b.e.UTF8;
                    break;
                case 2:
                    eVar = this.g ? com.a.a.b.e.UTF16_BE : com.a.a.b.e.UTF16_LE;
                    break;
                case 3:
                default:
                    throw new RuntimeException("Internal error");
                case 4:
                    eVar = this.g ? com.a.a.b.e.UTF32_BE : com.a.a.b.e.UTF32_LE;
                    break;
            }
        }
        this.f124a.a(eVar);
        return eVar;
    }

    private Reader b() {
        com.a.a.b.e a2 = this.f124a.a();
        switch (a2.c()) {
            case 8:
            case 16:
                InputStream inputStream = this.f125b;
                InputStream inputStream2 = inputStream;
                if (inputStream == null) {
                    inputStream2 = new ByteArrayInputStream(this.c, this.d, this.e);
                } else if (this.d < this.e) {
                    inputStream2 = new com.a.a.b.c.g(this.f124a, inputStream2, this.c, this.d, this.e);
                }
                return new InputStreamReader(inputStream2, a2.a());
            case 32:
                return new l(this.f124a, this.f125b, this.c, this.d, this.e, this.f124a.a().b());
            default:
                throw new RuntimeException("Internal error");
        }
    }

    public final com.a.a.b.l a(int i, p pVar, com.a.a.b.e.a aVar, com.a.a.b.e.b bVar, int i2) {
        int i3 = this.d;
        com.a.a.b.e a2 = a();
        int i4 = this.d - i3;
        if (a2 == com.a.a.b.e.UTF8 && f.a.CANONICALIZE_FIELD_NAMES.a(i2)) {
            return new h(this.f124a, i, this.f125b, pVar, aVar.a(i2), this.c, this.d, this.e, i4, true);
        }
        return new g(this.f124a, i, b(), pVar, bVar.a(i2));
    }

    private boolean a(int i) {
        switch (i) {
            case -16842752:
                a("3412");
                break;
            case -131072:
                this.d += 4;
                this.h = 4;
                this.g = false;
                return true;
            case 65279:
                this.g = true;
                this.d += 4;
                this.h = 4;
                return true;
            case 65534:
                a("2143");
                break;
        }
        int i2 = i >>> 16;
        if (i2 == 65279) {
            this.d += 2;
            this.h = 2;
            this.g = true;
            return true;
        }
        if (i2 == 65534) {
            this.d += 2;
            this.h = 2;
            this.g = false;
            return true;
        }
        if ((i >>> 8) == 15711167) {
            this.d += 3;
            this.h = 1;
            this.g = true;
            return true;
        }
        return false;
    }

    private boolean b(int i) {
        if ((i >> 8) == 0) {
            this.g = true;
        } else if ((i & 16777215) == 0) {
            this.g = false;
        } else if ((i & (-16711681)) == 0) {
            a("3412");
        } else if ((i & (-65281)) == 0) {
            a("2143");
        } else {
            return false;
        }
        this.h = 4;
        return true;
    }

    private boolean c(int i) {
        if ((i & 65280) == 0) {
            this.g = true;
        } else if ((i & 255) == 0) {
            this.g = false;
        } else {
            return false;
        }
        this.h = 2;
        return true;
    }

    private static void a(String str) {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + str + ") detected");
    }

    private boolean d(int i) {
        int read;
        int i2 = this.e - this.d;
        while (true) {
            int i3 = i2;
            if (i3 < i) {
                if (this.f125b == null) {
                    read = -1;
                } else {
                    read = this.f125b.read(this.c, this.e, this.c.length - this.e);
                }
                if (read <= 0) {
                    return false;
                }
                this.e += read;
                i2 = i3 + read;
            } else {
                return true;
            }
        }
    }
}
