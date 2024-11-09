package com.a.a.c.k.b;

import com.a.a.a.l;
import java.util.Objects;
import java.util.UUID;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/av.class */
public final class av extends an<UUID> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private static char[] f612a = "0123456789abcdef".toCharArray();

    /* renamed from: b, reason: collision with root package name */
    private Boolean f613b;

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        a((UUID) obj, hVar);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
        return a((UUID) obj);
    }

    public av() {
        this(null);
    }

    private av(Boolean bool) {
        super(UUID.class);
        this.f613b = bool;
    }

    private static boolean a(UUID uuid) {
        if (uuid.getLeastSignificantBits() == 0 && uuid.getMostSignificantBits() == 0) {
            return true;
        }
        return false;
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        Boolean bool = null;
        if (a2 != null) {
            l.c c = a2.c();
            if (c == l.c.BINARY) {
                bool = Boolean.TRUE;
            } else if (c == l.c.STRING) {
                bool = Boolean.FALSE;
            }
        }
        if (!Objects.equals(bool, this.f613b)) {
            return new av(bool);
        }
        return this;
    }

    private void a(UUID uuid, com.a.a.b.h hVar) {
        if (a(hVar)) {
            hVar.a(b(uuid));
            return;
        }
        char[] cArr = new char[36];
        long mostSignificantBits = uuid.getMostSignificantBits();
        a((int) (mostSignificantBits >> 32), cArr, 0);
        cArr[8] = '-';
        int i = (int) mostSignificantBits;
        b(i >>> 16, cArr, 9);
        cArr[13] = '-';
        b(i, cArr, 14);
        cArr[18] = '-';
        long leastSignificantBits = uuid.getLeastSignificantBits();
        b((int) (leastSignificantBits >>> 48), cArr, 19);
        cArr[23] = '-';
        b((int) (leastSignificantBits >>> 32), cArr, 24);
        a((int) leastSignificantBits, cArr, 28);
        hVar.a(cArr, 0, 36);
    }

    private boolean a(com.a.a.b.h hVar) {
        if (this.f613b != null) {
            return this.f613b.booleanValue();
        }
        return !(hVar instanceof com.a.a.c.m.ac) && hVar.f();
    }

    private static void a(int i, char[] cArr, int i2) {
        b(i >> 16, cArr, i2);
        b(i, cArr, i2 + 4);
    }

    private static void b(int i, char[] cArr, int i2) {
        cArr[i2] = f612a[(i >> 12) & 15];
        int i3 = i2 + 1;
        cArr[i3] = f612a[(i >> 8) & 15];
        int i4 = i3 + 1;
        cArr[i4] = f612a[(i >> 4) & 15];
        cArr[i4 + 1] = f612a[i & 15];
    }

    private static final byte[] b(UUID uuid) {
        byte[] bArr = new byte[16];
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        a((int) (mostSignificantBits >> 32), bArr, 0);
        a((int) mostSignificantBits, bArr, 4);
        a((int) (leastSignificantBits >> 32), bArr, 8);
        a((int) leastSignificantBits, bArr, 12);
        return bArr;
    }

    private static final void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >> 8);
        bArr[i4 + 1] = (byte) i;
    }
}
