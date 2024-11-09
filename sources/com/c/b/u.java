package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/u.class */
final class u extends s {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.c.b.s, com.c.b.j
    public final int a(a aVar, Object obj, float[][] fArr, int[] iArr, int i) {
        int i2 = 0;
        while (i2 < i && iArr[i2] == 0) {
            i2++;
        }
        if (i2 == i) {
            return 0;
        }
        return a(aVar, obj, fArr, i);
    }
}
