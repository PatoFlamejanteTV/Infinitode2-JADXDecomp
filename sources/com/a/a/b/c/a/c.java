package com.a.a.b.c.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/a/a/b/c/a/c.class */
public final class c extends b {
    @Override // com.a.a.b.c.a.b
    final long a() {
        return Double.doubleToRawLongBits(Double.NaN);
    }

    @Override // com.a.a.b.c.a.b
    final long b() {
        return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
    }

    @Override // com.a.a.b.c.a.b
    final long c() {
        return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
    }

    @Override // com.a.a.b.c.a.b
    final long a(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        return Double.doubleToRawLongBits(Double.isNaN(i3) ? Double.parseDouble(charSequence.subSequence(i, i2).toString()) : d.a(z, j, i3, z2, i4));
    }

    @Override // com.a.a.b.c.a.b
    final long b(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        long j2 = i3;
        return Double.doubleToRawLongBits(Double.isNaN(j2) ? Double.parseDouble(charSequence.subSequence(i, i2).toString()) : d.a(z, j, j2, z2, i4));
    }
}
