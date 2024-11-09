package org.a.c.b;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

/* loaded from: infinitode-2.jar:org/a/c/b/f.class */
public final class f extends l {

    /* renamed from: a, reason: collision with root package name */
    private BigDecimal f4362a;

    /* renamed from: b, reason: collision with root package name */
    private String f4363b;

    public f(float f) {
        this.f4362a = new BigDecimal(String.valueOf(f));
        this.f4363b = b(this.f4362a.toPlainString());
    }

    public f(String str) {
        try {
            this.f4363b = str;
            this.f4362a = new BigDecimal(this.f4363b);
            d();
        } catch (NumberFormatException e) {
            if (str.startsWith("--")) {
                this.f4363b = str.substring(1);
            } else if (str.matches("^0\\.0*\\-\\d+")) {
                this.f4363b = "-" + this.f4363b.replaceFirst("\\-", "");
            } else {
                throw new IOException("Error expected floating point number actual='" + str + "'", e);
            }
            try {
                this.f4362a = new BigDecimal(this.f4363b);
                d();
            } catch (NumberFormatException e2) {
                throw new IOException("Error expected floating point number actual='" + str + "'", e2);
            }
        }
    }

    private void d() {
        float f;
        float f2;
        float floatValue = this.f4362a.floatValue();
        double doubleValue = this.f4362a.doubleValue();
        boolean z = false;
        if (floatValue == Float.NEGATIVE_INFINITY || floatValue == Float.POSITIVE_INFINITY) {
            if (Math.abs(doubleValue) > 3.4028234663852886E38d) {
                f = Float.MAX_VALUE;
                f2 = floatValue == Float.POSITIVE_INFINITY ? 1 : -1;
                floatValue = f * f2;
                z = true;
            }
        } else if (floatValue == 0.0f && doubleValue != 0.0d && Math.abs(doubleValue) < 1.1754943508222875E-38d) {
            f = Float.MIN_NORMAL;
            f2 = doubleValue >= 0.0d ? 1.0f : -1.0f;
            floatValue = f * f2;
            z = true;
        }
        if (z) {
            this.f4362a = new BigDecimal(floatValue);
            this.f4363b = b(this.f4362a.toPlainString());
        }
    }

    private static String b(String str) {
        if (str.indexOf(46) >= 0 && !str.endsWith(".0")) {
            while (str.endsWith("0") && !str.endsWith(".0")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    @Override // org.a.c.b.l
    public final float a() {
        return this.f4362a.floatValue();
    }

    @Override // org.a.c.b.l
    public final long b() {
        return this.f4362a.longValue();
    }

    @Override // org.a.c.b.l
    public final int c() {
        return this.f4362a.intValue();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof f) && Float.floatToIntBits(((f) obj).f4362a.floatValue()) == Float.floatToIntBits(this.f4362a.floatValue());
    }

    public final int hashCode() {
        return this.f4362a.hashCode();
    }

    public final String toString() {
        return "COSFloat{" + this.f4363b + "}";
    }

    @Override // org.a.c.b.b
    public final Object a(u uVar) {
        return uVar.a(this);
    }

    public final void a(OutputStream outputStream) {
        outputStream.write(this.f4363b.getBytes("ISO-8859-1"));
    }
}
