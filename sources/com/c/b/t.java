package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/t.class */
final class t extends s {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.s, com.c.b.j
    public final int a(a aVar, Object obj, float[][] fArr, int[] iArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] != 0) {
                int i4 = i2;
                i2++;
                fArr[i4] = fArr[i3];
            }
        }
        if (i2 != 0) {
            return a(aVar, obj, fArr, i2, 1);
        }
        return 0;
    }
}
