package com.a.a.c.c.b;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/g.class */
public final class g extends ai<ByteBuffer> {
    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public final /* bridge */ /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        return a(lVar, gVar, (ByteBuffer) obj);
    }

    @Override // com.a.a.c.k
    public final /* bridge */ /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return a(lVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g() {
        super((Class<?>) ByteBuffer.class);
    }

    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Binary;
    }

    private static ByteBuffer a(com.a.a.b.l lVar) {
        return ByteBuffer.wrap(lVar.O());
    }

    private static ByteBuffer a(com.a.a.b.l lVar, com.a.a.c.g gVar, ByteBuffer byteBuffer) {
        com.a.a.c.m.h hVar = new com.a.a.c.m.h(byteBuffer);
        lVar.a(gVar.k(), hVar);
        hVar.close();
        return byteBuffer;
    }
}
