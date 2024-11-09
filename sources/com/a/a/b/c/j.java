package com.a.a.b.c;

import com.a.a.b.g.o;
import java.io.Writer;

/* loaded from: infinitode-2.jar:com/a/a/b/c/j.class */
public final class j extends Writer {

    /* renamed from: a, reason: collision with root package name */
    private final o f119a;

    public j(com.a.a.b.g.a aVar) {
        this.f119a = new o(aVar);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public final Writer append(char c) {
        write(c);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public final Writer append(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        this.f119a.a(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public final Writer append(CharSequence charSequence, int i, int i2) {
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        this.f119a.a(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    @Override // java.io.Writer
    public final void write(char[] cArr) {
        this.f119a.c(cArr, 0, cArr.length);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        this.f119a.c(cArr, i, i2);
    }

    @Override // java.io.Writer
    public final void write(int i) {
        this.f119a.a((char) i);
    }

    @Override // java.io.Writer
    public final void write(String str) {
        this.f119a.a(str, 0, str.length());
    }

    @Override // java.io.Writer
    public final void write(String str, int i, int i2) {
        this.f119a.a(str, i, i2);
    }

    public final String a() {
        String e = this.f119a.e();
        this.f119a.a();
        return e;
    }
}
