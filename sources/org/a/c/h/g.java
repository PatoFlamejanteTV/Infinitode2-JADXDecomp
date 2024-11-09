package org.a.c.h;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.awt.geom.AffineTransform;
import java.io.Closeable;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Stack;
import org.a.c.h.e.u;
import org.a.c.h.f.a.m;
import org.a.c.h.f.a.o;
import org.a.c.h.f.a.t;
import org.a.c.h.g.b.q;

/* loaded from: infinitode-2.jar:org/a/c/h/g.class */
public final class g implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4602a = org.a.a.a.c.a(g.class);

    /* renamed from: b, reason: collision with root package name */
    private final b f4603b;
    private OutputStream c;
    private j d;
    private boolean e;
    private final Stack<u> f;
    private final Stack<org.a.c.h.f.a.f> g;
    private final Stack<org.a.c.h.f.a.f> h;
    private final NumberFormat i;
    private final byte[] j;

    /* loaded from: infinitode-2.jar:org/a/c/h/g$a.class */
    public enum a {
        OVERWRITE,
        APPEND,
        PREPEND;

        public final boolean a() {
            return this == OVERWRITE;
        }

        public final boolean b() {
            return this == PREPEND;
        }
    }

    public g(b bVar, e eVar, a aVar, boolean z) {
        this(bVar, eVar, aVar, z, false);
    }

    private g(b bVar, e eVar, a aVar, boolean z, boolean z2) {
        org.a.c.b.a aVar2;
        this.e = false;
        this.f = new Stack<>();
        this.g = new Stack<>();
        this.h = new Stack<>();
        this.i = NumberFormat.getNumberInstance(Locale.US);
        this.j = new byte[32];
        this.f4603b = bVar;
        org.a.c.b.j jVar = z ? org.a.c.b.j.bc : null;
        if (!aVar.a() && eVar.c()) {
            org.a.c.h.a.i iVar = new org.a.c.h.a.i(bVar);
            org.a.c.b.b a2 = eVar.f().a(org.a.c.b.j.af);
            if (a2 instanceof org.a.c.b.a) {
                aVar2 = (org.a.c.b.a) a2;
            } else {
                org.a.c.b.a aVar3 = new org.a.c.b.a();
                aVar2 = aVar3;
                aVar3.a(a2);
            }
            if (aVar.b()) {
                aVar2.a(0, (org.a.c.b.b) iVar.f());
            } else {
                aVar2.a(iVar);
            }
            eVar.f().a(org.a.c.b.j.af, (org.a.c.b.b) aVar2);
            this.c = iVar.a(jVar);
        } else {
            eVar.c();
            org.a.c.h.a.i iVar2 = new org.a.c.h.a.i(bVar);
            eVar.a(iVar2);
            this.c = iVar2.a(jVar);
        }
        this.d = eVar.d();
        if (this.d == null) {
            this.d = new j();
            eVar.a(this.d);
        }
        this.i.setMaximumFractionDigits(5);
        this.i.setGroupingUsed(false);
    }

    public g(b bVar, q qVar, OutputStream outputStream) {
        this.e = false;
        this.f = new Stack<>();
        this.g = new Stack<>();
        this.h = new Stack<>();
        this.i = NumberFormat.getNumberInstance(Locale.US);
        this.j = new byte[32];
        this.f4603b = bVar;
        this.c = outputStream;
        this.d = qVar.e();
        this.i.setMaximumFractionDigits(4);
        this.i.setGroupingUsed(false);
    }

    public final void a() {
        if (this.e) {
            throw new IllegalStateException("Error: Nested beginText() calls are not allowed.");
        }
        c("BT");
        this.e = true;
    }

    public final void b() {
        if (!this.e) {
            throw new IllegalStateException("Error: You must call beginText() before calling endText.");
        }
        c("ET");
        this.e = false;
    }

    public final void a(u uVar, float f) {
        if (this.f.isEmpty()) {
            this.f.add(uVar);
        } else {
            this.f.setElementAt(uVar, this.f.size() - 1);
        }
        if (uVar.k()) {
            this.f4603b.e().add(uVar);
        }
        a(this.d.a(uVar));
        d(f);
        c("Tf");
    }

    public final void a(Object[] objArr) {
        d("[");
        for (Object obj : objArr) {
            if (obj instanceof String) {
                b((String) obj);
            } else if (obj instanceof Float) {
                d(((Float) obj).floatValue());
            } else {
                throw new IllegalArgumentException("Argument must consist of array of Float and String types");
            }
        }
        d("] ");
        c("TJ");
    }

    public final void a(String str) {
        b(str);
        d(SequenceUtils.SPACE);
        c("Tj");
    }

    private void b(String str) {
        if (!this.e) {
            throw new IllegalStateException("Must call beginText() before showText()");
        }
        if (this.f.isEmpty()) {
            throw new IllegalStateException("Must call setFont() before showText()");
        }
        u peek = this.f.peek();
        if (peek.k()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= str.length()) {
                    break;
                }
                int codePointAt = str.codePointAt(i2);
                peek.f(codePointAt);
                i = i2 + Character.charCount(codePointAt);
            }
        }
        org.a.c.g.b.a(peek.a(str), this.c);
    }

    public final void a(float f, float f2) {
        if (!this.e) {
            throw new IllegalStateException("Error: must call beginText() before newLineAtOffset()");
        }
        d(f);
        d(f2);
        c("Td");
    }

    public final void a(org.a.c.i.d dVar) {
        if (!this.e) {
            throw new IllegalStateException("Error: must call beginText() before setTextMatrix");
        }
        a(dVar.a());
        c("Tm");
    }

    public final void a(org.a.c.h.f.c.b bVar, float f, float f2, float f3, float f4) {
        if (this.e) {
            throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
        }
        c();
        b(new org.a.c.i.d(new AffineTransform(f3, 0.0f, 0.0f, f4, f, f2)));
        a(this.d.a(bVar));
        c("Do");
        d();
    }

    public final void b(org.a.c.i.d dVar) {
        a(dVar.a());
        c("cm");
    }

    public final void c() {
        if (!this.f.isEmpty()) {
            this.f.push(this.f.peek());
        }
        if (!this.h.isEmpty()) {
            this.h.push(this.h.peek());
        }
        if (!this.g.isEmpty()) {
            this.g.push(this.g.peek());
        }
        c("q");
    }

    public final void d() {
        if (!this.f.isEmpty()) {
            this.f.pop();
        }
        if (!this.h.isEmpty()) {
            this.h.pop();
        }
        if (!this.g.isEmpty()) {
            this.g.pop();
        }
        c("Q");
    }

    private org.a.c.b.j a(org.a.c.h.f.a.f fVar) {
        if ((fVar instanceof org.a.c.h.f.a.i) || (fVar instanceof m) || (fVar instanceof org.a.c.h.f.a.g)) {
            return org.a.c.b.j.a(fVar.a());
        }
        return this.d.a(fVar);
    }

    public final void a(org.a.c.h.f.a.e eVar) {
        if (this.h.isEmpty() || this.h.peek() != eVar.c()) {
            a(a(eVar.c()));
            c("CS");
            b(eVar.c());
        }
        for (float f : eVar.a()) {
            d(f);
        }
        if (eVar.c() instanceof t) {
            a(eVar.b());
        }
        if ((eVar.c() instanceof t) || (eVar.c() instanceof org.a.c.h.f.a.u) || (eVar.c() instanceof org.a.c.h.f.a.j) || (eVar.c() instanceof o)) {
            c("SCN");
        } else {
            c("SC");
        }
    }

    public final void a(int i, int i2, int i3) {
        if (e(i) || e(i2) || e(i3)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        d(i / 255.0f);
        d(i2 / 255.0f);
        d(i3 / 255.0f);
        c("RG");
        b(m.f4580a);
    }

    public final void a(float f, float f2, float f3, float f4) {
        if (a(f) || a(f2) || a(f3) || a(f4)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f,%.2f)", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)));
        }
        d(f);
        d(f2);
        d(f3);
        d(f4);
        c("K");
        b(org.a.c.h.f.a.g.f4572a);
    }

    public final void b(org.a.c.h.f.a.e eVar) {
        if (this.g.isEmpty() || this.g.peek() != eVar.c()) {
            a(a(eVar.c()));
            c("cs");
            c(eVar.c());
        }
        for (float f : eVar.a()) {
            d(f);
        }
        if (eVar.c() instanceof t) {
            a(eVar.b());
        }
        if ((eVar.c() instanceof t) || (eVar.c() instanceof org.a.c.h.f.a.u) || (eVar.c() instanceof org.a.c.h.f.a.j) || (eVar.c() instanceof o)) {
            c("scn");
        } else {
            c("sc");
        }
    }

    public final void b(int i, int i2, int i3) {
        if (e(i) || e(i2) || e(i3)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        d(i / 255.0f);
        d(i2 / 255.0f);
        d(i3 / 255.0f);
        c("rg");
        c(m.f4580a);
    }

    public final void b(float f, float f2, float f3, float f4) {
        if (a(f) || a(f2) || a(f3) || a(f4)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f,%.2f)", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)));
        }
        d(f);
        d(f2);
        d(f3);
        d(f4);
        c("k");
        c(org.a.c.h.f.a.g.f4572a);
    }

    public final void a(int i) {
        if (e(0)) {
            throw new IllegalArgumentException("Parameter must be within 0..255, but is 0");
        }
        c(0.0f);
    }

    private void c(float f) {
        if (a(f)) {
            throw new IllegalArgumentException("Parameter must be within 0..1, but is " + f);
        }
        d(f);
        c("g");
        c(org.a.c.h.f.a.i.f4574a);
    }

    public final void c(float f, float f2, float f3, float f4) {
        if (this.e) {
            throw new IllegalStateException("Error: addRect is not allowed within a text block.");
        }
        d(f);
        d(f2);
        d(f3);
        d(f4);
        c("re");
    }

    public final void a(float f, float f2, float f3, float f4, float f5, float f6) {
        if (this.e) {
            throw new IllegalStateException("Error: curveTo is not allowed within a text block.");
        }
        d(f);
        d(f2);
        d(f3);
        d(f4);
        d(f5);
        d(f6);
        c("c");
    }

    public final void d(float f, float f2, float f3, float f4) {
        if (this.e) {
            throw new IllegalStateException("Error: curveTo1 is not allowed within a text block.");
        }
        d(f);
        d(f2);
        d(f3);
        d(f4);
        c("y");
    }

    public final void b(float f, float f2) {
        if (this.e) {
            throw new IllegalStateException("Error: moveTo is not allowed within a text block.");
        }
        d(f);
        d(f2);
        c("m");
    }

    public final void c(float f, float f2) {
        if (this.e) {
            throw new IllegalStateException("Error: lineTo is not allowed within a text block.");
        }
        d(f);
        d(f2);
        c("l");
    }

    public final void e() {
        if (this.e) {
            throw new IllegalStateException("Error: stroke is not allowed within a text block.");
        }
        c("S");
    }

    public final void f() {
        if (this.e) {
            throw new IllegalStateException("Error: closeAndStroke is not allowed within a text block.");
        }
        c("s");
    }

    public final void g() {
        if (this.e) {
            throw new IllegalStateException("Error: fill is not allowed within a text block.");
        }
        c("f");
    }

    public final void h() {
        if (this.e) {
            throw new IllegalStateException("Error: fillEvenOdd is not allowed within a text block.");
        }
        c("f*");
    }

    public final void i() {
        if (this.e) {
            throw new IllegalStateException("Error: closePath is not allowed within a text block.");
        }
        c("h");
    }

    public final void j() {
        if (this.e) {
            throw new IllegalStateException("Error: clip is not allowed within a text block.");
        }
        c("W");
        c("n");
    }

    public final void k() {
        if (this.e) {
            throw new IllegalStateException("Error: clipEvenOdd is not allowed within a text block.");
        }
        c("W*");
        c("n");
    }

    public final void a(float f) {
        if (this.e) {
            throw new IllegalStateException("Error: setLineWidth is not allowed within a text block.");
        }
        d(f);
        c("w");
    }

    public final void b(int i) {
        if (this.e) {
            throw new IllegalStateException("Error: setLineJoinStyle is not allowed within a text block.");
        }
        if (i >= 0 && i <= 2) {
            d(i);
            c("j");
            return;
        }
        throw new IllegalArgumentException("Error: unknown value for line join style");
    }

    public final void c(int i) {
        if (this.e) {
            throw new IllegalStateException("Error: setLineCapStyle is not allowed within a text block.");
        }
        if (i >= 0 && i <= 2) {
            d(i);
            c("J");
            return;
        }
        throw new IllegalArgumentException("Error: unknown value for line cap style");
    }

    public final void a(float[] fArr, float f) {
        if (this.e) {
            throw new IllegalStateException("Error: setLineDashPattern is not allowed within a text block.");
        }
        d("[");
        for (float f2 : fArr) {
            d(f2);
        }
        d("] ");
        d(f);
        c("d");
    }

    public final void b(float f) {
        if (this.e) {
            throw new IllegalStateException("Error: setMiterLimit is not allowed within a text block.");
        }
        if (f <= 0.0d) {
            throw new IllegalArgumentException("A miter limit <= 0 is invalid and will not render in Acrobat Reader");
        }
        d(f);
        c("M");
    }

    private void d(float f) {
        int a2 = org.a.c.i.e.a(f, this.i.getMaximumFractionDigits(), this.j);
        if (a2 != -1) {
            this.c.write(this.j, 0, a2);
        } else {
            d(this.i.format(f));
        }
        this.c.write(32);
    }

    private void d(int i) {
        d(this.i.format(i));
        this.c.write(32);
    }

    private void a(org.a.c.b.j jVar) {
        jVar.a(this.c);
        this.c.write(32);
    }

    private void c(String str) {
        this.c.write(str.getBytes(org.a.c.i.a.f4651a));
        this.c.write(10);
    }

    private void d(String str) {
        this.c.write(str.getBytes(org.a.c.i.a.f4651a));
    }

    private void a(AffineTransform affineTransform) {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            d((float) dArr[i]);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c != null) {
            this.c.close();
            this.c = null;
        }
    }

    private static boolean e(int i) {
        return i < 0 || i > 255;
    }

    private static boolean a(double d) {
        return d < 0.0d || d > 1.0d;
    }

    private void b(org.a.c.h.f.a.f fVar) {
        if (this.h.isEmpty()) {
            this.h.add(fVar);
        } else {
            this.h.setElementAt(fVar, this.h.size() - 1);
        }
    }

    private void c(org.a.c.h.f.a.f fVar) {
        if (this.g.isEmpty()) {
            this.g.add(fVar);
        } else {
            this.g.setElementAt(fVar, this.g.size() - 1);
        }
    }

    public final void a(org.a.c.h.f.e.a aVar) {
        d(aVar.a());
        c("Tr");
    }
}
