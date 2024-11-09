package com.a.a.b.c.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/a/a/b/c/a/g.class */
public final class g extends b {
    @Override // com.a.a.b.c.a.b
    final long a() {
        return Float.floatToRawIntBits(Float.NaN);
    }

    @Override // com.a.a.b.c.a.b
    final long b() {
        return Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
    }

    @Override // com.a.a.b.c.a.b
    final long c() {
        return Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
    }

    @Override // com.a.a.b.c.a.b
    final long a(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        return Float.floatToRawIntBits(Float.isNaN(f.a(z, j, i3, z2, i4)) ? Float.parseFloat(charSequence.subSequence(i, i2).toString()) : r0);
    }

    @Override // com.a.a.b.c.a.b
    final long b(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        return Float.floatToRawIntBits(Float.isNaN(f.b(z, j, i3, z2, i4)) ? Float.parseFloat(charSequence.subSequence(i, i2).toString()) : r0);
    }
}
