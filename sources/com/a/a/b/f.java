package com.a.a.b;

import com.a.a.b.h;
import com.a.a.b.l;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

/* loaded from: infinitode-2.jar:com/a/a/b/f.class */
public class f extends v implements Serializable {
    private static int c = a.c();

    /* renamed from: a, reason: collision with root package name */
    protected static final int f142a = l.a.a();
    private static int d = h.a.a();
    private static r e = com.a.a.b.g.e.c;
    private transient com.a.a.b.e.b f;
    private transient com.a.a.b.e.a g;
    private int h;
    private int i;
    private int j;

    /* renamed from: b, reason: collision with root package name */
    protected p f143b;
    private com.a.a.b.c.c k;
    private com.a.a.b.c.c l;
    private com.a.a.b.c.c m;
    private r n;
    private char o;

    /* loaded from: infinitode-2.jar:com/a/a/b/f$a.class */
    public enum a implements com.a.a.b.g.h {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true),
        USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING(true);

        private final boolean e = true;

        public static int c() {
            int i = 0;
            for (a aVar : values()) {
                if (aVar.a()) {
                    i |= aVar.b();
                }
            }
            return i;
        }

        a(boolean z) {
        }

        @Override // com.a.a.b.g.h
        public final boolean a() {
            return this.e;
        }

        @Override // com.a.a.b.g.h
        public final boolean a(int i) {
            return (i & b()) != 0;
        }

        @Override // com.a.a.b.g.h
        public final int b() {
            return 1 << ordinal();
        }
    }

    public f() {
        this((p) null);
    }

    public f(p pVar) {
        this.f = com.a.a.b.e.b.a();
        this.g = com.a.a.b.e.a.a();
        this.h = c;
        this.i = f142a;
        this.j = d;
        this.n = e;
        this.f143b = pVar;
        this.o = '\"';
    }

    public final f a(l.a aVar) {
        this.i |= aVar.c();
        return this;
    }

    public final f a(p pVar) {
        this.f143b = pVar;
        return this;
    }

    public p a() {
        return this.f143b;
    }

    public final l a(InputStream inputStream) {
        com.a.a.b.c.a a2 = a(a((Object) inputStream), false);
        return a(b(inputStream, a2), a2);
    }

    public final l a(Reader reader) {
        com.a.a.b.c.a a2 = a(a((Object) reader), false);
        return a(b(reader, a2), a2);
    }

    public final h a(Writer writer) {
        com.a.a.b.c.a a2 = a(a((Object) writer), false);
        return a(b(writer, a2), a2);
    }

    private l a(InputStream inputStream, com.a.a.b.c.a aVar) {
        try {
            return new com.a.a.b.d.a(aVar, inputStream).a(this.i, this.f143b, this.g, this.f, this.h);
        } catch (IOException | RuntimeException e2) {
            if (aVar.b()) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                    e2.addSuppressed(e3);
                }
            }
            throw e2;
        }
    }

    private l a(Reader reader, com.a.a.b.c.a aVar) {
        return new com.a.a.b.d.g(aVar, this.i, reader, this.f143b, this.f.a(this.h));
    }

    private h a(Writer writer, com.a.a.b.c.a aVar) {
        com.a.a.b.d.i iVar = new com.a.a.b.d.i(aVar, this.j, this.f143b, writer, '\"');
        if (this.k != null) {
            iVar.a(this.k);
        }
        r rVar = this.n;
        if (rVar != e) {
            iVar.a(rVar);
        }
        return iVar;
    }

    private InputStream b(InputStream inputStream, com.a.a.b.c.a aVar) {
        if (this.l != null) {
            com.a.a.b.c.c cVar = this.l;
            throw null;
        }
        return inputStream;
    }

    private Reader b(Reader reader, com.a.a.b.c.a aVar) {
        if (this.l != null) {
            com.a.a.b.c.c cVar = this.l;
            throw null;
        }
        return reader;
    }

    private Writer b(Writer writer, com.a.a.b.c.a aVar) {
        if (this.m != null) {
            com.a.a.b.c.c cVar = this.m;
            throw null;
        }
        return writer;
    }

    public final com.a.a.b.g.a b() {
        if (a.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING.a(this.h)) {
            return com.a.a.b.g.b.a();
        }
        return new com.a.a.b.g.a();
    }

    private com.a.a.b.c.a a(com.a.a.b.c.d dVar, boolean z) {
        if (dVar == null) {
            dVar = com.a.a.b.c.d.a();
        }
        return new com.a.a.b.c.a(b(), dVar, false);
    }

    private com.a.a.b.c.d a(Object obj) {
        return com.a.a.b.c.d.a(true, obj);
    }
}
