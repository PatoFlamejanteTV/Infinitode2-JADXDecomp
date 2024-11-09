package com.a.a.b.d;

import com.a.a.b.h;
import com.a.a.b.p;
import com.a.a.b.r;

/* loaded from: infinitode-2.jar:com/a/a/b/d/c.class */
public abstract class c extends com.a.a.b.a.a {
    private static int[] l = com.a.a.b.c.b.f();
    protected final com.a.a.b.c.a e;
    protected int[] f;
    protected int g;
    protected com.a.a.b.c.c h;
    protected r i;
    protected boolean j;
    protected boolean k;

    public c(com.a.a.b.c.a aVar, int i, p pVar) {
        super(i, pVar);
        this.f = l;
        this.i = com.a.a.b.g.e.c;
        this.e = aVar;
        if (h.a.ESCAPE_NON_ASCII.a(i)) {
            this.g = 127;
        }
        this.k = h.a.WRITE_HEX_UPPER_CASE.a(i);
        this.j = !h.a.QUOTE_FIELD_NAMES.a(i);
    }

    @Override // com.a.a.b.a.a, com.a.a.b.h
    public final com.a.a.b.h a(h.a aVar) {
        super.a(aVar);
        if (aVar == h.a.QUOTE_FIELD_NAMES) {
            this.j = true;
        } else if (aVar == h.a.WRITE_HEX_UPPER_CASE) {
            this.k = false;
        }
        return this;
    }

    @Override // com.a.a.b.a.a
    protected final void b(int i, int i2) {
        super.b(i, i2);
        this.j = !h.a.QUOTE_FIELD_NAMES.a(i);
        this.k = h.a.WRITE_HEX_UPPER_CASE.a(i);
    }

    @Override // com.a.a.b.h
    public final com.a.a.b.h b(int i) {
        this.g = i < 0 ? 0 : i;
        return this;
    }

    @Override // com.a.a.b.h
    public final com.a.a.b.h a(com.a.a.b.c.c cVar) {
        this.h = cVar;
        if (cVar != null) {
            throw null;
        }
        this.f = l;
        return this;
    }

    @Override // com.a.a.b.h
    public final com.a.a.b.h a(r rVar) {
        this.i = rVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str, int i) {
        switch (i) {
            case 0:
                if (this.d.b()) {
                    this.f170b.g(this);
                    return;
                } else {
                    if (this.d.d()) {
                        this.f170b.h(this);
                        return;
                    }
                    return;
                }
            case 1:
                this.f170b.f(this);
                return;
            case 2:
                this.f170b.d(this);
                return;
            case 3:
                this.f170b.a(this);
                return;
            case 4:
            default:
                m();
                return;
            case 5:
                h(str);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void h(String str) {
        f(String.format("Can not %s, expecting field name (context: %s)", str, this.d.e()));
    }
}
