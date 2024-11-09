package com.d.e;

import java.text.BreakIterator;

/* loaded from: infinitode-2.jar:com/d/e/ae.class */
public final class ae implements com.d.d.g {

    /* renamed from: a, reason: collision with root package name */
    private final BreakIterator f1114a;

    /* renamed from: b, reason: collision with root package name */
    private String f1115b;
    private a c;

    public ae(BreakIterator breakIterator) {
        this.f1114a = breakIterator;
    }

    @Override // com.d.d.g
    public final int a() {
        b();
        a aVar = this.c;
        if (c()) {
            if (d()) {
                return -1;
            }
            if ("://".equals(c(new a(this.c.d(), -1, 2)))) {
                aVar = aVar.a(this.c.d() + 2);
                d();
            }
        }
        int b2 = b(a(aVar.b(this.c.d())));
        this.c = this.c.a(b2 >= 0 ? b2 : this.f1114a.current());
        return this.c.c();
    }

    private a a(a aVar) {
        while (aVar.c() < this.c.d() && ".,:;!?- \n\r\t/".indexOf(this.f1115b.charAt(aVar.c())) >= 0) {
            aVar = aVar.a();
        }
        while (aVar.d() > aVar.c() && ".,:;!?- \n\r\t/".indexOf(this.f1115b.charAt(aVar.d() - 1)) >= 0) {
            aVar = aVar.b();
        }
        return aVar;
    }

    private int b(a aVar) {
        int indexOf = this.f1115b.indexOf(47, aVar.c());
        if (indexOf < aVar.d()) {
            return indexOf;
        }
        return -1;
    }

    private String c(a aVar) {
        return this.f1115b.substring(Math.max(0, aVar.c()), Math.min(this.f1115b.length(), aVar.d()));
    }

    private void b() {
        if (this.c.c() > this.f1114a.current()) {
            throw new IllegalStateException("Iterator ahead of delegate.");
        }
    }

    private boolean c() {
        return this.c.c() == this.f1114a.current();
    }

    private boolean d() {
        int next = this.f1114a.next();
        this.c = this.c.b(next);
        return next == -1;
    }

    @Override // com.d.d.g
    public final void a(String str) {
        this.f1114a.setText(str);
        this.f1115b = str;
        this.c = new a(this.f1114a.current(), this.f1114a.current());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/e/ae$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final int f1116a;

        /* renamed from: b, reason: collision with root package name */
        private final int f1117b;

        public a(int i, int i2) {
            this.f1116a = i;
            this.f1117b = Math.max(i, i2);
        }

        public a(int i, int i2, int i3) {
            this(i - 1, i + 2);
        }

        public final a a(int i) {
            return new a(i, this.f1117b);
        }

        public final a b(int i) {
            return new a(this.f1116a, i);
        }

        public final a a() {
            int i = this.f1116a + 1;
            return new a(i, Math.max(i, this.f1117b));
        }

        public final a b() {
            int i = this.f1117b - 1;
            return new a(Math.min(this.f1116a, i), i);
        }

        public final int c() {
            return this.f1116a;
        }

        public final int d() {
            return this.f1117b;
        }

        public final String toString() {
            return "[" + this.f1116a + ", " + this.f1117b + ")";
        }
    }
}
