package com.a.a.c.k.b;

import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/h.class */
public final class h extends an<ByteBuffer> {
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        a((ByteBuffer) obj, hVar);
    }

    public h() {
        super(ByteBuffer.class);
    }

    private static void a(ByteBuffer byteBuffer, com.a.a.b.h hVar) {
        if (byteBuffer.hasArray()) {
            int position = byteBuffer.position();
            hVar.a(byteBuffer.array(), byteBuffer.arrayOffset() + position, byteBuffer.limit() - position);
            return;
        }
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        if (asReadOnlyBuffer.position() > 0) {
            asReadOnlyBuffer.rewind();
        }
        com.a.a.c.m.g gVar = new com.a.a.c.m.g(asReadOnlyBuffer);
        hVar.a((InputStream) gVar, asReadOnlyBuffer.remaining());
        gVar.close();
    }
}
