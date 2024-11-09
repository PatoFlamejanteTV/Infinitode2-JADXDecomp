package com.d.j;

import com.d.m.l;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.xml.sax.InputSource;

/* loaded from: infinitode-2.jar:com/d/j/a.class */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private final EnumC0027a f1391a;

    /* renamed from: b, reason: collision with root package name */
    private InputSource f1392b;
    private InputStream c;
    private Reader d;
    private long e;

    /* renamed from: com.d.j.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/j/a$a.class */
    enum EnumC0027a {
        READER,
        STREAM,
        INPUT_SOURCE
    }

    private a(EnumC0027a enumC0027a) {
        System.currentTimeMillis();
        this.f1391a = enumC0027a;
    }

    public a(InputSource inputSource) {
        this(EnumC0027a.INPUT_SOURCE);
        this.f1392b = inputSource;
    }

    public a(Reader reader) {
        this(EnumC0027a.READER);
        this.d = reader;
    }

    public final InputSource a() {
        if (this.f1391a == EnumC0027a.STREAM && this.f1392b == null) {
            this.f1392b = new InputSource(new BufferedInputStream(this.c));
        }
        return this.f1392b;
    }

    public final Reader b() {
        if (this.f1391a == EnumC0027a.STREAM && this.d == null) {
            try {
                this.d = new InputStreamReader(this.c, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                l.a("Could not create reader for stream", e);
            }
        }
        return this.d;
    }

    public final long c() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(long j) {
        this.e = j;
    }
}
