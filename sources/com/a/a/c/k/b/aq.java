package com.a.a.c.k.b;

import java.util.TimeZone;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/aq.class */
public final class aq extends an<TimeZone> {
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        a((TimeZone) obj, hVar);
    }

    public aq() {
        super(TimeZone.class);
    }

    private static void a(TimeZone timeZone, com.a.a.b.h hVar) {
        hVar.b(timeZone.getID());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.an, com.a.a.c.o
    public void a(TimeZone timeZone, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(timeZone, TimeZone.class, com.a.a.b.o.VALUE_STRING));
        a(timeZone, hVar);
        iVar.b(hVar, a2);
    }
}
