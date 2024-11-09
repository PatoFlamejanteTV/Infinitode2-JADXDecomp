package com.a.a.c.c.b;

import java.util.Arrays;
import java.util.UUID;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ap.class */
public final class ap extends q<UUID> {

    /* renamed from: a, reason: collision with root package name */
    private static int[] f326a;

    static {
        int[] iArr = new int[127];
        f326a = iArr;
        Arrays.fill(iArr, -1);
        for (int i = 0; i < 10; i++) {
            f326a[i + 48] = i;
        }
        for (int i2 = 0; i2 < 6; i2++) {
            f326a[i2 + 97] = i2 + 10;
            f326a[i2 + 65] = i2 + 10;
        }
    }

    public ap() {
        super(UUID.class);
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return new UUID(0L, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.c.b.q
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public UUID a(String str, com.a.a.c.g gVar) {
        if (str.length() != 36) {
            if (str.length() == 24) {
                return a(com.a.a.b.b.a().a(str), gVar);
            }
            return c(str, gVar);
        }
        if (str.charAt(8) != '-' || str.charAt(13) != '-' || str.charAt(18) != '-' || str.charAt(23) != '-') {
            c(str, gVar);
        }
        return new UUID((a(str, 0, gVar) << 32) + ((b(str, 9, gVar) << 16) | b(str, 14, gVar)), (((b(str, 19, gVar) << 16) | b(str, 24, gVar)) << 32) | ((a(str, 28, gVar) << 32) >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.c.b.q
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public UUID a(Object obj, com.a.a.c.g gVar) {
        if (obj instanceof byte[]) {
            return a((byte[]) obj, gVar);
        }
        return (UUID) super.a(obj, gVar);
    }

    private UUID c(String str, com.a.a.c.g gVar) {
        return (UUID) gVar.b(a(), str, "UUID has to be represented by standard 36-char representation", new Object[0]);
    }

    private int a(String str, int i, com.a.a.c.g gVar) {
        return (c(str, i, gVar) << 24) + (c(str, i + 2, gVar) << 16) + (c(str, i + 4, gVar) << 8) + c(str, i + 6, gVar);
    }

    private int b(String str, int i, com.a.a.c.g gVar) {
        return (c(str, i, gVar) << 8) + c(str, i + 2, gVar);
    }

    private int c(String str, int i, com.a.a.c.g gVar) {
        int i2;
        char charAt = str.charAt(i);
        char charAt2 = str.charAt(i + 1);
        if (charAt <= 127 && charAt2 <= 127 && (i2 = (f326a[charAt] << 4) | f326a[charAt2]) >= 0) {
            return i2;
        }
        if (charAt > 127 || f326a[charAt] < 0) {
            return a(str, gVar, charAt);
        }
        return a(str, gVar, charAt2);
    }

    private int a(String str, com.a.a.c.g gVar, char c) {
        throw gVar.a(str, a(), String.format("Non-hex character '%c' (value 0x%s), not valid for UUID String", Character.valueOf(c), Integer.toHexString(c)));
    }

    private UUID a(byte[] bArr, com.a.a.c.g gVar) {
        if (bArr.length != 16) {
            throw com.a.a.c.d.c.a(gVar.j(), "Can only construct UUIDs from byte[16]; got " + bArr.length + " bytes", bArr, a());
        }
        return new UUID(a(bArr, 0), a(bArr, 8));
    }

    private static long a(byte[] bArr, int i) {
        return (b(bArr, i) << 32) | ((b(bArr, i + 4) << 32) >>> 32);
    }

    private static int b(byte[] bArr, int i) {
        return (bArr[i] << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
    }
}
