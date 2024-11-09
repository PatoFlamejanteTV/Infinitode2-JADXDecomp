package com.a.a.b.g;

import com.a.a.b.r;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/b/g/e.class */
public class e implements f<e>, com.a.a.b.q, Serializable {
    public static final com.a.a.b.c.k c = new com.a.a.b.c.k(SequenceUtils.SPACE);
    private b d;
    private b e;
    private r f;
    private boolean g;
    private transient int h;
    private n i;
    private String j;

    /* loaded from: infinitode-2.jar:com/a/a/b/g/e$b.class */
    public interface b {
        void a(com.a.a.b.h hVar, int i);

        boolean a();
    }

    public e() {
        this(c);
    }

    private e(r rVar) {
        this.d = a.f158a;
        this.e = d.f157a;
        this.g = true;
        this.f = rVar;
        a(f187a);
    }

    private e(e eVar) {
        this(eVar, eVar.f);
    }

    private e(e eVar, r rVar) {
        this.d = a.f158a;
        this.e = d.f157a;
        this.g = true;
        this.d = eVar.d;
        this.e = eVar.e;
        this.g = eVar.g;
        this.h = eVar.h;
        this.i = eVar.i;
        this.j = eVar.j;
        this.f = rVar;
    }

    private e a(n nVar) {
        this.i = nVar;
        this.j = SequenceUtils.SPACE + nVar.b() + SequenceUtils.SPACE;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.b.g.f
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public e a() {
        if (getClass() != e.class) {
            throw new IllegalStateException("Failed `createInstance()`: " + getClass().getName() + " does not override method; it has to");
        }
        return new e(this);
    }

    @Override // com.a.a.b.q
    public final void a(com.a.a.b.h hVar) {
        if (this.f != null) {
            hVar.d(this.f);
        }
    }

    @Override // com.a.a.b.q
    public final void b(com.a.a.b.h hVar) {
        hVar.a('{');
        if (!this.e.a()) {
            this.h++;
        }
    }

    @Override // com.a.a.b.q
    public final void h(com.a.a.b.h hVar) {
        this.e.a(hVar, this.h);
    }

    @Override // com.a.a.b.q
    public final void d(com.a.a.b.h hVar) {
        if (this.g) {
            hVar.c(this.j);
        } else {
            hVar.a(this.i.b());
        }
    }

    @Override // com.a.a.b.q
    public final void c(com.a.a.b.h hVar) {
        hVar.a(this.i.c());
        this.e.a(hVar, this.h);
    }

    @Override // com.a.a.b.q
    public final void a(com.a.a.b.h hVar, int i) {
        if (!this.e.a()) {
            this.h--;
        }
        if (i > 0) {
            this.e.a(hVar, this.h);
        } else {
            hVar.a(' ');
        }
        hVar.a('}');
    }

    @Override // com.a.a.b.q
    public final void e(com.a.a.b.h hVar) {
        if (!this.d.a()) {
            this.h++;
        }
        hVar.a('[');
    }

    @Override // com.a.a.b.q
    public final void g(com.a.a.b.h hVar) {
        this.d.a(hVar, this.h);
    }

    @Override // com.a.a.b.q
    public final void f(com.a.a.b.h hVar) {
        hVar.a(this.i.d());
        this.d.a(hVar, this.h);
    }

    @Override // com.a.a.b.q
    public final void b(com.a.a.b.h hVar, int i) {
        if (!this.d.a()) {
            this.h--;
        }
        if (i > 0) {
            this.d.a(hVar, this.h);
        } else {
            hVar.a(' ');
        }
        hVar.a(']');
    }

    /* loaded from: infinitode-2.jar:com/a/a/b/g/e$c.class */
    public static class c implements b, Serializable {
        static {
            new c();
        }

        @Override // com.a.a.b.g.e.b
        public void a(com.a.a.b.h hVar, int i) {
        }

        @Override // com.a.a.b.g.e.b
        public boolean a() {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/b/g/e$a.class */
    public static class a extends c {

        /* renamed from: a, reason: collision with root package name */
        public static final a f158a = new a();

        @Override // com.a.a.b.g.e.c, com.a.a.b.g.e.b
        public final void a(com.a.a.b.h hVar, int i) {
            hVar.a(' ');
        }

        @Override // com.a.a.b.g.e.c, com.a.a.b.g.e.b
        public final boolean a() {
            return true;
        }
    }
}
