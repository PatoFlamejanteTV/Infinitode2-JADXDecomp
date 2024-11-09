package com.a.a.c.k.b;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/g.class */
public final class g extends ao<byte[]> {
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        a((byte[]) obj, hVar, aaVar);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
        return a((byte[]) obj);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        a((byte[]) obj, hVar, aaVar, iVar);
    }

    public g() {
        super(byte[].class);
    }

    private static boolean a(byte[] bArr) {
        return bArr.length == 0;
    }

    private static void a(byte[] bArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        hVar.a(aaVar.a().v(), bArr, 0, bArr.length);
    }

    private static void a(byte[] bArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(bArr, com.a.a.b.o.VALUE_EMBEDDED_OBJECT));
        hVar.a(aaVar.a().v(), bArr, 0, bArr.length);
        iVar.b(hVar, a2);
    }
}
