package com.d.f;

import com.d.e.ac;
import org.w3c.dom.Element;

/* loaded from: infinitode-2.jar:com/d/f/h.class */
public final class h implements ac {

    /* renamed from: a, reason: collision with root package name */
    private Element f1188a;

    /* renamed from: b, reason: collision with root package name */
    private com.d.c.f.c f1189b;
    private h c;

    public h() {
    }

    public h(Element element, com.d.c.f.c cVar) {
        this.f1188a = element;
        this.f1189b = cVar;
    }

    public final Element b() {
        return this.f1188a;
    }

    @Override // com.d.e.ac
    public final com.d.c.f.c a() {
        return this.f1189b;
    }

    @Override // com.d.e.ac
    public final void a(Element element) {
        this.f1188a = element;
    }

    @Override // com.d.e.ac
    public final void a(com.d.c.f.c cVar) {
        this.f1189b = cVar;
    }

    public final h c() {
        return this.c;
    }

    public final void a(h hVar) {
        this.c = hVar;
    }
}
